package com.denbukki.curio.items;


import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class ItemWallClimbBelt extends BaublesItemBase {

    public ItemWallClimbBelt() {
        super("ItemWallClimbBelt");
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.BELT;


    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {

        if (entity.collidedHorizontally && !entity.isSneaking())
        {
            entity.motionY = 0.2D;
            entity.move(MoverType.SELF, entity.motionX, entity.motionY, entity.motionZ);

        }

    }

}
