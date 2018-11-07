package com.denbukki.baublelicious2.network;


import com.denbukki.baublelicious2.tiles.TileInfusionTable;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateInfusionTable implements IMessage {


    public PacketUpdateInfusionTable(BlockPos pos, ItemStack stack, int level, int infuseTime) {
        this.pos = pos;
        this.stack = stack;
        this.level = level;
        this.infuseTime = infuseTime;
    }
    public PacketUpdateInfusionTable(TileInfusionTable te) {
        this(te.getPos(), te.inventory.getStackInSlot(0), te.level ,te.infuseTime);
    }

    private BlockPos pos;
    private ItemStack stack;
    private int level;
    private int infuseTime;

    public PacketUpdateInfusionTable(){

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeInt(level);
        buf.writeInt(infuseTime);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        stack = ByteBufUtils.readItemStack(buf);
        level = buf.readInt();
        infuseTime = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketUpdateInfusionTable, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateInfusionTable message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileInfusionTable te = (TileInfusionTable)Minecraft.getMinecraft().world.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.stack);
                te.level = message.level;
                te.infuseTime = message.infuseTime;
            });
            return null;
        }
    }
}