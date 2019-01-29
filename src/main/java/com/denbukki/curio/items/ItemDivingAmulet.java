package com.denbukki.curio.items;

import baubles.api.BaubleType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ItemDivingAmulet extends BaublesItemBase {

    public ItemDivingAmulet() {
        super("ItemDivingAmulet");
        setMaxDamage(250);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }


    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        int i = MathHelper.floor(player.posX);
        int j = MathHelper.floor(player.getEntityBoundingBox().minY + 1);
        int k = MathHelper.floor(player.posZ);
        Material m = player.world.getBlockState(new BlockPos(i, j, k)).getMaterial();
            if (m == Material.WATER && itemstack.getItemDamage() == 0 && !player.isPotionActive(Potion.getPotionById(13)) && player instanceof EntityPlayer && player.getAir() == 1) {
                player.setAir(300);
                itemstack.damageItem(1, player);
            }

    }
}
