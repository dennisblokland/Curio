package com.denbukki.baublelicious2.items;


import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemFallingBelt extends BaublesItemBase {

    public ItemFallingBelt() {
        super("ItemFallingBelt");
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.BELT;


    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        EntityPlayer player = (EntityPlayer) entity;
        player.fallDistance = 0;
    }

}
