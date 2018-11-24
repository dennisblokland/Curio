package com.denbukki.curio;

import com.denbukki.curio.items.CurioItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CurioTab extends CreativeTabs {
    public CurioTab(int par1, String par2Str) {
        super(par1, par2Str);

    }

    @Override

    public ItemStack getTabIconItem(){
// Here you make the Icon of the creative Tab
        return new ItemStack(CurioItems.itemRing);
    }
}
