package com.denbukki.baublelicious2;

import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
}
