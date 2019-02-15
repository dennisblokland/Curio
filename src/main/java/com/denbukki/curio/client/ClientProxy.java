package com.denbukki.curio.client;

import com.denbukki.curio.CommonProxy;
import com.denbukki.curio.ModInfo;
import com.denbukki.curio.client.fx.ParticleXPOrb;
import com.denbukki.curio.client.handler.KeyHandler;
import com.denbukki.curio.tiles.TileEntityInfusionTable;
import com.denbukki.curio.tiles.TileEntityPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
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
    public void registerHandlers() {
        super.registerHandlers();

        MinecraftForge.EVENT_BUS.register(KeyHandler.instance);
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfusionTable.class, new TileEntityInfusionTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityPedestalRenderer());
        MinecraftForge.EVENT_BUS.register(new TextureStichHandler());
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);

    }

    @Override
    public void orbFX(double x, double y, double z, double mX, double mY, double mZ, int age, boolean orbit) {

        ParticleXPOrb particle = new ParticleXPOrb(Minecraft.getMinecraft().world, x, y, z, mX, mY, mZ, orbit);
        particle.setMaxAge(age);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    @Override
    public void spawnParticle(EnumParticleTypes type, double x, double y, double z, double v, double v1, double v2) {
        Minecraft.getMinecraft().world.spawnParticle(type, z, y, z, v, v1, v2);
    }
    public String translate(String s) {
        return  I18n.format(s);
    }

}
