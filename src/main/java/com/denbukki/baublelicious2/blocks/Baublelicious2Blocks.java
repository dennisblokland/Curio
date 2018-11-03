package com.denbukki.baublelicious2.blocks;


import com.denbukki.baublelicious2.items.Baublelicious2Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class Baublelicious2Blocks {

    public static final BlockMysticOre blockMysticOre = new BlockMysticOre();

    public static final BlockInfusionTable blockInfusionTable = new BlockInfusionTable();

    public static final BlockCrystal blockFireCrystal = new BlockCrystal("FireCrystal", Baublelicious2Items.itemFireShard);

    private static final ArrayList<BiomeDictionary.Type> earthBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.DENSE);
        add(BiomeDictionary.Type.FOREST);
    }};

    public static final BlockCrystal blockEarthCrystal = new BlockCrystal("EarthCrystal", Baublelicious2Items.itemEarthShard,earthBiomesList );

    public static final BlockCrystal blockWaterCrystal = new BlockCrystal("WaterCrystal", Baublelicious2Items.itemWaterShard);

    private static final ArrayList<BiomeDictionary.Type> airBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.MOUNTAIN);
        add(BiomeDictionary.Type.HILLS);
    }};

    public static final BlockCrystal blockAirCrystal = new BlockCrystal("AirCrystal", Baublelicious2Items.itemAirShard,airBiomesList);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockMysticOre,
                blockFireCrystal,
                blockEarthCrystal,
                blockWaterCrystal,
                blockAirCrystal,
                blockInfusionTable
        );

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockMysticOre.createItemBlock(),
                blockFireCrystal.createItemBlock(),
                blockEarthCrystal.createItemBlock(),
                blockWaterCrystal.createItemBlock(),
                blockAirCrystal.createItemBlock(),
                blockInfusionTable.createItemBlock()
        );
    }

    public static void registerModels() {
        blockMysticOre.registerItemModel(Item.getItemFromBlock(blockMysticOre));
        blockFireCrystal.registerItemModel(Item.getItemFromBlock(blockFireCrystal));
        blockEarthCrystal.registerItemModel(Item.getItemFromBlock(blockEarthCrystal));
        blockWaterCrystal.registerItemModel(Item.getItemFromBlock(blockWaterCrystal));
        blockAirCrystal.registerItemModel(Item.getItemFromBlock(blockAirCrystal));
        blockInfusionTable.registerItemModel(Item.getItemFromBlock(blockInfusionTable));
    }
}
