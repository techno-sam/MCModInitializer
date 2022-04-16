package com.slimeist.base_mod.common.network;

import com.slimeist.base_mod.BaseMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class MessageDemo implements IMessage
{
    private BlockPos pos;
    private CompoundNBT nbt;

    //TODO get rid of NBT in packets
    public MessageDemo(BlockPos pos, CompoundNBT nbt)
    {
        this.pos = pos;
        this.nbt = nbt;
    }

    public MessageDemo(PacketBuffer buf)
    {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.nbt = buf.readNbt();
    }

    @Override
    public void toBytes(PacketBuffer buf)
    {
        buf.writeInt(pos.getX()).writeInt(pos.getY()).writeInt(pos.getZ());
        buf.writeNbt(this.nbt);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context)
    {
        NetworkEvent.Context ctx = context.get();
        if(ctx.getDirection().getReceptionSide()== LogicalSide.SERVER)
            ctx.enqueueWork(() -> {
                ServerWorld world = Objects.requireNonNull(ctx.getSender()).getLevel();
                if(world.isAreaLoaded(pos, 1))
                {
                    /*TileEntity tile = world.getBlockEntity(pos);
                    if(tile instanceof ForceModifierTileEntity)
                        ((ForceModifierTileEntity)tile).receiveMessageFromClient(ctx.getSender(), nbt);*/
                }
            });
        else
            ctx.enqueueWork(() -> {
                World world = BaseMod.proxy.getClientWorld();
                if(world!=null) // This can happen if the task is scheduled right before leaving the world
                {
                    /*TileEntity tile = world.getBlockEntity(pos);
                    if(tile instanceof ForceModifierTileEntity)
                        ((ForceModifierTileEntity)tile).receiveMessageFromServer(nbt);*/
                }
            });
    }
}