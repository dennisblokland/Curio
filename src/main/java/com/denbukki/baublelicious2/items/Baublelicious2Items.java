package com.denbukki.baublelicious2.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class Baublelicious2Items {
    //normal items
    public static final Item itemMysticCrystal = new ItemMysticCrystal();

    //bauble items
    public static final Item itemRing = new ItemRing();
    public static final Item itemAmulet = new ItemAmulet();
    public static final Item itemBelt = new ItemBelt();

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                itemRing,
                itemAmulet,
                itemBelt,
                itemMysticCrystal
        );


    }
    public static void registerModels() {
        ((BaublesItemBase)itemRing).registerItemModel();
        ((BaublesItemBase)itemAmulet).registerItemModel();
        ((BaublesItemBase)itemBelt).registerItemModel();
        ((ItemBase)itemMysticCrystal).registerItemModel();
        //basic items

    }
}
