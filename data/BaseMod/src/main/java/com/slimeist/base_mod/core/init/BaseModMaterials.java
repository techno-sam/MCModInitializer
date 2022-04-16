package com.slimeist.base_mod.core.init;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public class BaseModMaterials {
    public static final Material UNPUSHABLE_GLASS = (new BaseModMaterials.Builder(MaterialColor.NONE)).notSolidBlocking().build();

    public static class Builder {
        private PushReaction pushReaction = PushReaction.NORMAL;
        private boolean blocksMotion = true;
        private boolean flammable;
        private boolean liquid;
        private boolean replaceable;
        private boolean solid = true;
        private final MaterialColor color;
        private boolean solidBlocking = true;

        public Builder(MaterialColor p_i48270_1_) {
            this.color = p_i48270_1_;
        }

        public Builder liquid() {
            this.liquid = true;
            return this;
        }

        public Builder nonSolid() {
            this.solid = false;
            return this;
        }

        public Builder noCollider() {
            this.blocksMotion = false;
            return this;
        }

        public Builder notSolidBlocking() {
            this.solidBlocking = false;
            return this;
        }

        protected Builder flammable() {
            this.flammable = true;
            return this;
        }

        public Builder replaceable() {
            this.replaceable = true;
            return this;
        }

        protected Builder destroyOnPush() {
            this.pushReaction = PushReaction.DESTROY;
            return this;
        }

        protected Builder notPushable() {
            this.pushReaction = PushReaction.BLOCK;
            return this;
        }

        public Material build() {
            return new Material(this.color, this.liquid, this.solid, this.blocksMotion, this.solidBlocking, this.flammable, this.replaceable, this.pushReaction);
        }
    }
}
