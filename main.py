import os
import sys
import binaryornot.check


def pad(string, length):
    return string + (" "*max(0, length-len(string)))


class Renamer:
    def __init__(self, lower_case: str, upper_case: str, upper_space_case: str):
        """
        :param lower_case: lower_case mod name (Ex: epic_mod)
        :param upper_case: CamelCase mod name (Ex: EpicMod)
        :param upper_space_case: Display Case mod name (Ex: Epic Mod)
        """
        self.from_lower_case = "base_mod"
        self.from_upper_case = "BaseMod"
        self.from_upper_space_case = "Base Mod"

        self.to_lower_case = lower_case
        self.to_upper_case = upper_case
        self.to_upper_space_case = upper_space_case

    def convert_text(self, text: str):
        return text \
            .replace(self.from_lower_case, self.to_lower_case) \
            .replace(self.from_upper_case, self.to_upper_case) \
            .replace(self.from_upper_space_case, self.to_upper_space_case)\
            .replace("GITHIDE", "")  # So our github repo can contain .gitignore, etc. without git following them

    def __repr__(self):
        return f'Renamer("{self.to_lower_case}", "{self.to_upper_case}", "{self.to_upper_space_case}")'

    def __str__(self):
        l = max(len(self.from_lower_case), max(len(self.from_upper_case), len(self.from_upper_space_case)))
        out = ""
        out += "Renamer:"
        out += "\n\t"+pad(self.from_lower_case, l)+" -> "+self.to_lower_case
        out += "\n\t"+pad(self.from_upper_case, l)+" -> "+self.to_upper_case
        out += "\n\t"+pad(self.from_upper_space_case, l)+" -> "+self.to_upper_space_case
        return out


def raw_copy(path: str) -> None:
    """
    :param path: relative path to file/directory eg. 'src', not 'BaseMod/src'
    :return: None
    """
    orig = os.path.join(in_path, path)
    new = os.path.join(out_path, path)
    os.makedirs(os.path.dirname(new), exist_ok=True)
    cmd = f'cp -r "{orig}" "{new}"'
    print(cmd)
    os.system(cmd)


def recursive_rename_copy(path: str, converter: Renamer) -> None:
    """
    If path is a file, copy and rename contents, if path is a directory,
    recursively step through, renaming files and directories on the way.

    :param path: relative path to file/directory eg. 'src', not 'BaseMod/src'
    :param converter: Renamer instance to use to convert contents and names
    :return: None
    """
    orig = os.path.join(in_path, path)
    new = converter.convert_text(os.path.join(out_path, path))
    os.makedirs(os.path.dirname(new), exist_ok=True)
    if os.path.isdir(orig):
        for add_path in os.listdir(orig):
            recursive_rename_copy(os.path.join(path, add_path), converter)
    else:
        if binaryornot.check.is_binary(orig):
            print(f"File {orig} is binary, not renaming contents.")
            cmd = f'cp "{orig}" "{new}"'
            os.system(cmd)
        else:
            with open(orig) as f:
                contents = f.read()
            new_contents = converter.convert_text(contents)
            with open(new, "w") as f:
                f.write(new_contents)


renamer = Renamer(input("lower_case mod name: "), input("CamelCase mod name: "), input("Display Case mod name: "))
print(renamer)

base_path = os.path.dirname(__file__)
data_path = os.path.join(base_path, "data")
in_path = os.path.join(data_path, "BaseMod")
output_path = os.path.join(base_path, "out")
out_path = os.path.join(output_path, renamer.to_upper_case)


if __name__ == "__main__":
    rm_cmd = f'rm -r "{out_path}"'
    print(rm_cmd)
    os.system(rm_cmd)
    try:
        os.makedirs(out_path, exist_ok=False)
    except FileExistsError as e:
        raise FileExistsError("Mod already generated, delete it to regenerate") from e

    # raw_copy dirs
    raw_copy(".gradle")
    raw_copy(".idea")
    raw_copy(".settings")
    raw_copy("gradle")
    raw_copy("run")
    raw_copy("src/generated")
    raw_copy("src/test")
    # raw_copy files
    raw_copy(".gitattributes")
    raw_copy(".gitignore")
    raw_copy("gradle.properties")
    raw_copy("gradlew")
    raw_copy("gradlew.bat")
    raw_copy("LICENSE.txt")
    raw_copy("README.txt")
    # recursive_rename_copy dirs
    recursive_rename_copy("src/main", renamer)
    # recursive_rename_copy files
    recursive_rename_copy("build.gradle", renamer)
