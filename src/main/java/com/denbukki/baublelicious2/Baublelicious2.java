package com.denbukki.baublelicious2;


import com.denbukki.baublelicious2.blocks.Baublelicious2Blocks;
import com.denbukki.baublelicious2.items.Baublelicious2Items;
import com.denbukki.baublelicious2.world.Baublelicious2WorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)

public class Baublelicious2 {
    @SidedProxy(clientSide = "com.denbukki.baublelicious2.client.ClientProxy", serverSide = "com.denbukki.baublelicious2.CommonProxy")
    public static  CommonProxy proxy;
    public static final CreativeTabs Baublelicious2Tab = new Baublelicious2Tab(CreativeTabs.getNextID(), "Baublelicious2");
    @Mod.Instance(ModInfo.MOD_ID)
    public static Baublelicious2 instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new Baublelicious2WorldGeneration(), 3);
        proxy.preInit(event);
    }

    @Mod.EventBusSubscriber
    public static class RegsitrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            Baublelicious2Items.register(event.getRegistry());
            Baublelicious2Blocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            Baublelicious2Blocks.register(event.getRegistry());


        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            Baublelicious2Items.registerModels();
            Baublelicious2Blocks.registerModels();
        }

    }
}
