package com.denbukki.baublelicious2;


import com.denbukki.baublelicious2.items.Baublelicious2Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)

public class Baublelicious2 {
    @SidedProxy(clientSide = "com.denbukki.baublelicious2.client.ClientProxy", serverSide = "com.denbukki.baublelicious2.CommonProxy")
    public static CommonProxy proxy;
    public static final CreativeTabs Baublelicious2Tab = new Baublelicious2Tab(CreativeTabs.getNextID(), "Baublelicious2");
    @Mod.Instance(ModInfo.MOD_ID)
    public static Baublelicious2 instance;
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerModels();
    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {


    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


    @Mod.EventBusSubscriber
    public static class RegsitrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            Baublelicious2Items.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            Baublelicious2Items.registerModels();
        }

    }
}
