package com.denbukki.baublelicious2;


import com.denbukki.baublelicious2.blocks.Baublelicious2Blocks;
import com.denbukki.baublelicious2.client.gui.Baublelicious2GuiHandler;
import com.denbukki.baublelicious2.items.Baublelicious2Items;
import com.denbukki.baublelicious2.network.PacketRequestUpdateInfusionTable;
import com.denbukki.baublelicious2.network.PacketRequestUpdatePedestal;
import com.denbukki.baublelicious2.network.PacketUpdateInfusionTable;
import com.denbukki.baublelicious2.network.PacketUpdatePedestal;
import com.denbukki.baublelicious2.tiles.TileEntityInfusionTable;
import com.denbukki.baublelicious2.tiles.TileEntityPedestal;
import com.denbukki.baublelicious2.world.Baublelicious2WorldGeneration;
import com.denbukki.baublelicious2.world.WorldgenCrystal;
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

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)

public class Baublelicious2 {
    @SidedProxy(clientSide = "com.denbukki.baublelicious2.client.ClientProxy", serverSide = "com.denbukki.baublelicious2.CommonProxy")
    public static  CommonProxy proxy;
    public static final CreativeTabs Baublelicious2Tab = new Baublelicious2Tab(CreativeTabs.getNextID(), "Baublelicious2");
    @Mod.Instance(ModInfo.MOD_ID)
    public static Baublelicious2 instance;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);
        network.registerMessage(new PacketUpdateInfusionTable.Handler(), PacketUpdateInfusionTable.class, 0,Side.CLIENT);
        network.registerMessage(new PacketRequestUpdateInfusionTable.Handler(), PacketRequestUpdateInfusionTable.class, 1, Side.SERVER);
        network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0,Side.CLIENT);
        network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);
        GameRegistry.registerWorldGenerator(new Baublelicious2WorldGeneration(), 3);
        GameRegistry.registerWorldGenerator(new WorldgenCrystal(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new Baublelicious2GuiHandler());
        proxy.preInit(event);
    }

    @Mod.EventBusSubscriber
    public static class RegsitrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            Baublelicious2Items.register(event.getRegistry());
            GameRegistry.registerTileEntity(TileEntityInfusionTable.class, new ResourceLocation(ModInfo.MOD_ID,"TileEntityInfusionTable"));
            GameRegistry.registerTileEntity(TileEntityPedestal.class, new ResourceLocation(ModInfo.MOD_ID,"TileEntityPedestal"));
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
