package com.denbukki.baublelicious2.blocks;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class Baublelicious2Blocks {

    public static final BlockMysticOre blockMysticOre = new BlockMysticOre();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockMysticOre
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockMysticOre.createItemBlock()
        );
    }

    public static void registerModels() {
        blockMysticOre.registerItemModel(Item.getItemFromBlock(blockMysticOre));
    }
}
