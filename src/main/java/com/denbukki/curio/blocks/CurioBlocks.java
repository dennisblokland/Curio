package com.denbukki.curio.blocks;


import com.denbukki.curio.items.CurioItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class CurioBlocks {

    public static final BlockMysticOre blockMysticOre = new BlockMysticOre();

    public static final BlockCrystal blockFireCrystal = new BlockCrystal("BlockFireCrystal", CurioItems.itemFireShard);

    private static final ArrayList<BiomeDictionary.Type> earthBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.DENSE);
        add(BiomeDictionary.Type.FOREST);
    }};

    public static final BlockCrystal blockEarthCrystal = new BlockCrystal("BlockEarthCrystal", CurioItems.itemEarthShard,earthBiomesList );

    public static final BlockCrystal blockWaterCrystal = new BlockCrystal("BlockWaterCrystal", CurioItems.itemWaterShard);

    private static final ArrayList<BiomeDictionary.Type> airBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.MOUNTAIN);
        add(BiomeDictionary.Type.HILLS);
    }};

    public static final BlockCrystal blockAirCrystal = new BlockCrystal("BlockAirCrystal", CurioItems.itemAirShard,airBiomesList);

    public static final BlockPedestal blockPedestal = new BlockPedestal();

    public static final BlockInfusionTable blockInfusionTable = new BlockInfusionTable();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockMysticOre,
                blockFireCrystal,
                blockEarthCrystal,
                blockWaterCrystal,
                blockAirCrystal,
                blockInfusionTable,
                blockPedestal
        );

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockMysticOre.createItemBlock(),
                blockFireCrystal.createItemBlock(),
                blockEarthCrystal.createItemBlock(),
                blockWaterCrystal.createItemBlock(),
                blockAirCrystal.createItemBlock(),
                blockInfusionTable.createItemBlock(),
                blockPedestal.createItemBlock()
        );

    }

    public static void registerModels() {
        blockMysticOre.registerItemModel(Item.getItemFromBlock(blockMysticOre));
        blockFireCrystal.registerItemModel(Item.getItemFromBlock(blockFireCrystal));
        blockEarthCrystal.registerItemModel(Item.getItemFromBlock(blockEarthCrystal));
        blockWaterCrystal.registerItemModel(Item.getItemFromBlock(blockWaterCrystal));
        blockAirCrystal.registerItemModel(Item.getItemFromBlock(blockAirCrystal));
        blockInfusionTable.registerItemModel(Item.getItemFromBlock(blockInfusionTable));
        blockPedestal.registerItemModel(Item.getItemFromBlock(blockPedestal));
    }
}
