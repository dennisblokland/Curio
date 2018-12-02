package com.denbukki.curio;


import com.denbukki.curio.blocks.CurioBlocks;
import com.denbukki.curio.client.gui.CurioGuiHandler;
import com.denbukki.curio.items.CurioItems;
import com.denbukki.curio.network.PacketRequestUpdateInfusionTable;
import com.denbukki.curio.network.PacketRequestUpdatePedestal;
import com.denbukki.curio.network.PacketUpdateInfusionTable;
import com.denbukki.curio.network.PacketUpdatePedestal;
import com.denbukki.curio.tiles.TileEntityInfusionTable;
import com.denbukki.curio.tiles.TileEntityPedestal;
import com.denbukki.curio.world.CurioWorldGeneration;
import com.denbukki.curio.world.WorldgenCrystal;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:baubles")

public class Curio {
    @SidedProxy(clientSide = "com.denbukki.curio.client.ClientProxy", serverSide = "com.denbukki.curio.CommonProxy")
    public static  CommonProxy proxy;
    public static final CreativeTabs CURIO_TAB = new CurioTab(CreativeTabs.getNextID(), "Curio");
    @Mod.Instance(ModInfo.MOD_ID)
    public static Curio instance;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new CurioWorldGeneration(), 3);
        GameRegistry.registerWorldGenerator(new WorldgenCrystal(), 2);
        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
        network.registerMessage(new PacketUpdateInfusionTable.Handler(), PacketUpdateInfusionTable.class, 0,Side.CLIENT);
        network.registerMessage(new PacketRequestUpdateInfusionTable.Handler(), PacketRequestUpdateInfusionTable.class, 1, Side.SERVER);
        network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 2,Side.CLIENT);
        network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 3, Side.SERVER);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new CurioGuiHandler());
        proxy.preInit(event);
    }

    @Mod.EventBusSubscriber
    public static class RegsitrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {

            GameRegistry.registerTileEntity(TileEntityInfusionTable.class, new ResourceLocation(ModInfo.MOD_ID,"TileEntityInfusionTable"));
            GameRegistry.registerTileEntity(TileEntityPedestal.class, new ResourceLocation(ModInfo.MOD_ID,"TileEntityPedestal"));
            CurioBlocks.registerItemBlocks(event.getRegistry());
            CurioItems.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            CurioBlocks.register(event.getRegistry());


        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            CurioItems.registerModels();
            CurioBlocks.registerModels();
        }

    }
}
