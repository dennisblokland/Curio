package com.denbukki.curio.network;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.denbukki.curio.Curio;
import com.denbukki.curio.items.ItemGrowthPendant;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyBind implements IMessage, IMessageHandler<MessageKeyBind, IMessage> {

    public KeyPacket packetType;

    public MessageKeyBind() {}

    public MessageKeyBind(KeyPacket type) {
        packetType = type;
    }

    @Override
    public void toBytes(ByteBuf dataStream) {
        dataStream.writeInt(packetType.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf dataStream) {
        packetType = KeyPacket.values()[dataStream.readInt()];
    }

    @Override
    public IMessage onMessage(MessageKeyBind msg, MessageContext ctx) {
        EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
        WorldServer worldServer = entityPlayerMP.getServerWorld();

        worldServer.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                handleMessage(msg, ctx);
            }
        });

        return null;
    }

    public void handleMessage(MessageKeyBind msg, MessageContext ctx) {
        EntityPlayer player = Curio.proxy.getPlayer(ctx);
        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
        if(msg.packetType == KeyPacket.GROWTH) {
            ItemStack stack = baubles.getStackInSlot(0);
            if(stack != null && stack.getItem() instanceof ItemGrowthPendant) {
                ((ItemGrowthPendant)stack.getItem()).toggleState(stack, player);
            }
        }

    }

    public static enum KeyPacket {
        GROWTH,
    }
}
