package com.denbukki.baublelicious2.network;


import com.denbukki.baublelicious2.tiles.TileInfusionTable;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdateInfusionTable implements IMessage {

    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdateInfusionTable(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdateInfusionTable(TileInfusionTable te) {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }
    public PacketRequestUpdateInfusionTable(){

    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdateInfusionTable, PacketUpdateInfusionTable> {

        @Override
        public PacketUpdateInfusionTable onMessage(PacketRequestUpdateInfusionTable message, MessageContext ctx) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
            TileInfusionTable te = (TileInfusionTable)world.getTileEntity(message.pos);
            if (te != null) {
                return new PacketUpdateInfusionTable(te);
            } else {
                return null;
            }
        }

    }
}
