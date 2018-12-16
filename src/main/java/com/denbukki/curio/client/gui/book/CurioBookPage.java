package com.denbukki.curio.client.gui.book;

import net.minecraft.item.ItemStack;

public class CurioBookPage {

    private String DescriptionText;
    public CurioBookPage(String DescriptionText, ItemStack stack){
        this.DescriptionText = DescriptionText;
        this.ItemStack = stack;
    }

    public String getDescriptionText() {
        return DescriptionText;
    }

    public ItemStack getItemStack() {
        return ItemStack;
    }

    private ItemStack ItemStack;


}