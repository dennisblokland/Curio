package com.denbukki.baublelicious2.network;


import com.denbukki.baublelicious2.tiles.TileEntityPedestal;
import com.denbukki.baublelicious2.util.UuidAdapter;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketUpdatePedestal implements IMessage {


    private BlockPos pos;
    private ItemStack stack;


    public PacketUpdatePedestal(BlockPos pos, ItemStack stack) {
        this.pos = pos;
        this.stack = stack;
    }
    public PacketUpdatePedestal(TileEntityPedestal te) {
        this(te.getPos(), te.inventory.getStackInSlot(0));
    }


    public PacketUpdatePedestal(){

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, stack);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        stack = ByteBufUtils.readItemStack(buf);

    }

    public static class Handler implements IMessageHandler<PacketUpdatePedestal, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdatePedestal message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntityPedestal te = (TileEntityPedestal)Minecraft.getMinecraft().world.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.stack);
            });
            return null;
        }
    }
}