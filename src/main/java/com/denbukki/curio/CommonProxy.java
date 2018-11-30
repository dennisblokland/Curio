package com.denbukki.curio;

import com.denbukki.curio.client.fx.ParticleXPOrb;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
        // Do nothing
    }

    public void registerRenderers() {
    }

    public void preInit(FMLPreInitializationEvent event) {
        registerRenderers();
    }

    public void OrbFX(double x, double y, double z, double mX, double mY, double mZ, int age, boolean orbit) {

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
}
