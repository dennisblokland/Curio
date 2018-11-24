package com.denbukki.curio.items;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public class ItemRing  extends  BaublesItemBase{
    public ItemRing() {
        super("ItemRing");
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}
