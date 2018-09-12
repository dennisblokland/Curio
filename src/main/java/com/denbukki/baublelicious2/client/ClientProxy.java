package com.denbukki.baublelicious2.client;

import com.denbukki.baublelicious2.CommonProxy;
import com.denbukki.baublelicious2.ModInfo;
import com.denbukki.baublelicious2.blocks.Baublelicious2Blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {

        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        Baublelicious2Blocks.initModels();
        OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);

    }
}
