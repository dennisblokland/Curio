package com.denbukki.curio.items;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ItemRingOfFlight extends BaublesItemBase {
    public ItemRingOfFlight() {
        super("ItemRingOfFlight");
    }


    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase entity) {
        stopFlying((EntityPlayer) entity);
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase entity) {
        startFlying((EntityPlayer) entity);
    }


    private void startFlying(EntityPlayer player) {
        if(!player.isCreative()) {
            player.capabilities.allowFlying = true;
            if (!player.getEntityWorld().isRemote) {
                player.sendPlayerAbilities();
            }
        }
    }

    private void stopFlying(EntityPlayer player) {
        if(!player.isCreative()){
            player.capabilities.isFlying = false;
            player.capabilities.allowFlying = false;

            if (!player.getEntityWorld().isRemote) {
                player.sendPlayerAbilities();
            }
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = ((EntityPlayer) entity);
            if (!player.capabilities.allowFlying) {
                startFlying(player);
            }
        }
    }

}