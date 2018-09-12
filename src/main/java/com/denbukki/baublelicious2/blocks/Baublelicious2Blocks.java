package com.denbukki.baublelicious2.blocks;


import com.denbukki.baublelicious2.tiles.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class Baublelicious2Blocks {

    public static final BlockMysticOre blockMysticOre = new BlockMysticOre();
    public static final BlockCrystal blockFireCrystal = new BlockCrystal("FireCrystal", Items.DIAMOND);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockMysticOre,
                blockFireCrystal
        );
        GameRegistry.registerTileEntity(TileEntityCrystal.class, blockFireCrystal.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockMysticOre.createItemBlock(),
                blockFireCrystal.createItemBlock()
        );
    }

    public static void registerModels() {
        blockMysticOre.registerItemModel(Item.getItemFromBlock(blockMysticOre));
        blockFireCrystal.registerItemModel(Item.getItemFromBlock(blockFireCrystal));
    }
    @SideOnly(Side.CLIENT)
    public static void initModels() {

        blockFireCrystal.initModel();
    }
}
