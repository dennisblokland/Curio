package com.denbukki.baublelicious2.client;

import com.denbukki.baublelicious2.CommonProxy;
import com.denbukki.baublelicious2.ModInfo;
import com.denbukki.baublelicious2.blocks.Baublelicious2Blocks;
import com.denbukki.baublelicious2.client.fx.ParticleXPOrb;
import com.denbukki.baublelicious2.tiles.TileInfusionTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {

        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }


    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfusionTable.class, new TileEntityInfusionTableRenderer());
        MinecraftForge.EVENT_BUS.register(new TextureStichHandler());
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);

    }

    @Override
    public void OrbFX(double x, double y, double z, double mX, double mY, double mZ, int age, boolean orbit) {
        ParticleXPOrb particle = new ParticleXPOrb(Minecraft.getMinecraft().world, x, y, z, mX, mY, mZ, orbit);
        particle.setMaxAge(age);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    @Override
    public void spawnParticle(EnumParticleTypes type, double x, double y, double z, double v, double v1, double v2) {
        Minecraft.getMinecraft().world.spawnParticle(type, z, y, z, v, v1, v2);
    }
}
