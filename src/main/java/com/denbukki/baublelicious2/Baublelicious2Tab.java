package com.denbukki.baublelicious2;

import com.denbukki.baublelicious2.items.Baublelicious2Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Baublelicious2Tab extends CreativeTabs {
    public Baublelicious2Tab(int par1, String par2Str) {
        super(par1, par2Str);

    }

    @Override

    public ItemStack getTabIconItem(){
// Here you make the Icon of the creative Tab
        return new ItemStack(Baublelicious2Items.itemRing);
    }
}
