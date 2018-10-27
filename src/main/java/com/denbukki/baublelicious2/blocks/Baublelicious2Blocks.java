package com.denbukki.baublelicious2.blocks;


import com.denbukki.baublelicious2.tiles.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class Baublelicious2Blocks {

    public static final BlockMysticOre blockMysticOre = new BlockMysticOre();

    public static final BlockCrystal blockFireCrystal = new BlockCrystal("FireCrystal", Items.DIAMOND);

    private static final ArrayList<BiomeDictionary.Type> earthBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.DENSE);
        add(BiomeDictionary.Type.FOREST);
    }};

    public static final BlockCrystal blockEarthCrystal = new BlockCrystal("EarthCrystal", Items.DIAMOND,earthBiomesList );

    public static final BlockCrystal blockWaterCrystal = new BlockCrystal("WaterCrystal", Items.DIAMOND);

    private static final ArrayList<BiomeDictionary.Type> airBiomesList = new ArrayList<BiomeDictionary.Type>(){{
        add(BiomeDictionary.Type.MOUNTAIN);
        add(BiomeDictionary.Type.HILLS);
    }};

    public static final BlockCrystal blockAirCrystal = new BlockCrystal("AirCrystal", Items.DIAMOND,airBiomesList);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockMysticOre,
                blockFireCrystal,
                blockEarthCrystal,
                blockWaterCrystal,
                blockAirCrystal
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockMysticOre.createItemBlock(),
                blockFireCrystal.createItemBlock(),
                blockEarthCrystal.createItemBlock(),
                blockWaterCrystal.createItemBlock(),
                blockAirCrystal.createItemBlock()
        );
    }

    public static void registerModels() {
        blockMysticOre.registerItemModel(Item.getItemFromBlock(blockMysticOre));
        blockFireCrystal.registerItemModel(Item.getItemFromBlock(blockFireCrystal));
        blockEarthCrystal.registerItemModel(Item.getItemFromBlock(blockEarthCrystal));
        blockWaterCrystal.registerItemModel(Item.getItemFromBlock(blockWaterCrystal));
        blockAirCrystal.registerItemModel(Item.getItemFromBlock(blockAirCrystal));
    }
}
