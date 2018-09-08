package com.denbukki.baublelicious2.items;

import com.denbukki.baublelicious2.Baublelicious2;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemBase extends Item {

    protected String name;

    public ItemBase() {
        super();
        setCreativeTab(Baublelicious2.Baublelicious2Tab);
    }
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName();
    }

    public void registerItemModel(Item item) {
        Baublelicious2.proxy.registerItemRenderer(item, 0, name);
    }
}
