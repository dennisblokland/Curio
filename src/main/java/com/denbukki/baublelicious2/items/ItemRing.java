package com.denbukki.baublelicious2.items;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public class ItemRing  extends  BaublesItemBase{
    public ItemRing() {
        this.setUnlocalizedName("ItemRing");
        setRegistryName("ItemRing");
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}
