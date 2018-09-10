package com.denbukki.baublelicious2.items;

import com.denbukki.baublelicious2.Baublelicious2;
import com.denbukki.baublelicious2.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        super();
        this.name = name;
        setCreativeTab(Baublelicious2.Baublelicious2Tab);
        setUnlocalizedName(ModInfo.MOD_ID +"."+this.name);
        setRegistryName(new ResourceLocation(ModInfo.MOD_ID, this.name));
    }
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName();
    }

    public void registerItemModel() {
        Baublelicious2.proxy.registerItemRenderer(this, 0, name);
    }
}
