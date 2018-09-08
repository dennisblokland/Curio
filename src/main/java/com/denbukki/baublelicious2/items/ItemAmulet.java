package com.denbukki.baublelicious2.items;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public class ItemAmulet extends BaublesItemBase {
    public ItemAmulet() {
        setUnlocalizedName("ItemAmulet");
        setRegistryName("ItemAmulet");

    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }
}
