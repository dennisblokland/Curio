package com.denbukki.curio.client.gui.book;

import net.minecraft.item.ItemStack;

public class CurioBookPage {

    private String descriptionText;
    private ItemStack itemStack;

    public CurioBookPage(String descriptionText, ItemStack stack){
        this.descriptionText = descriptionText;
        this.itemStack = stack;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }




}