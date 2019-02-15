package com.denbukki.curio;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
        // Do nothing
    }
    public void registerHandlers() {

    }
    public void registerRenderers() {
    }

    public void preInit(FMLPreInitializationEvent event) {
        registerRenderers();
    }

    public void orbFX(double x, double y, double z, double mX, double mY, double mZ, int age, boolean orbit) {

    }

    public void spawnParticle(EnumParticleTypes type, double x, double y, double z, double v, double v1, double v2) {
    }

    public EntityPlayer getPlayerFromUUID(String uuid, World world) {

        List<EntityPlayer> playerList = world.playerEntities;
        for (EntityPlayer player : playerList) {
            if (player.getUniqueID().toString().equals(uuid)) {
                return player;
            }
        }

        return null;
    }

    public String translate(String s) {
        return s;
    }


    public EntityPlayer getPlayer(MessageContext ctx) {
        return ctx.getServerHandler().player;
    }
}
