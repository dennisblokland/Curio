package com.denbukki.baublelicious2.items;

import com.denbukki.baublelicious2.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class Baublelicious2Items {
    //normal items


    //bauble items
    public static Item ItemRing = new ItemRing();
    public static Item ItemAmulet = new ItemAmulet();
    public static Item ItemBelt = new ItemBelt();

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ItemRing,
                ItemAmulet,
                ItemBelt
        );


    }
    public static void registerModels() {
        ((BaublesItemBase)ItemRing).registerItemModel();
        ((BaublesItemBase)ItemAmulet).registerItemModel();
        ((BaublesItemBase)ItemBelt).registerItemModel();
        //basic items

    }
}
