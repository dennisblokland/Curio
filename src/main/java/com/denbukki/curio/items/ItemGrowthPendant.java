package com.denbukki.curio.items;


import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGrowthPendant extends BaublesItemBase {
    public ItemGrowthPendant() {
        super("ItemGrowthPendant");
        setMaxDamage(2002);
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getTagCompound() != null) {
            if (stack.getTagCompound().getBoolean("isActive")) {
                tooltip.add("Activated");
            } else {
                tooltip.add("Deactivated");
            }

        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer) || entity.world.isRemote) {
            return;
        }

        World world = ((EntityPlayer) entity).world;
        EntityPlayer player = (EntityPlayer) entity;
        ItemStack amulet = BaublesApi.getBaublesHandler(player).getStackInSlot(0);


        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (stack.getTagCompound().getBoolean("isActive")) {
            int tileRange = 5;
            int fullPotencyRange = 3;
            int xO = (int) Math.round(entity.posX - 0.5f);
            int yO = (int) entity.posY;
            int zO = (int) Math.round(entity.posZ - 0.5f);
            if (world.rand.nextInt(20) == 0) {
                for (int xD = -tileRange; xD <= tileRange; xD++) {
                    for (int yD = -1; yD <= tileRange; yD++) {
                        for (int zD = -tileRange; zD <= tileRange; zD++) {
                            int x = xO + xD;
                            int y = yO + yD;
                            int z = zO + zD;

                            double distance = Math.sqrt(Math.pow(x - xO, 2) + Math.pow(y - yO, 2) + Math.pow(z - zO, 2));
                            distance -= fullPotencyRange;
                            distance = Math.min(1D, distance);
                            double distanceCoefficient = 1D - (distance / tileRange);

                            IBlockState cropState = world.getBlockState(new BlockPos(x, y, z));
                            Block cropBlock = cropState.getBlock();

                            if (cropBlock instanceof IPlantable || cropBlock instanceof IGrowable) {

                                world.scheduleBlockUpdate(new BlockPos(x, y, z), cropBlock, (int) (distanceCoefficient * (float) 1 * 20F), 1);
                                cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
                            }
                        }
                    }
                }
            }

            if (amulet != null && amulet.getItem() == this && world.rand.nextInt(40) == 0) {

                stack.damageItem(1, entity);
                if (stack.getItemDamage() == 1001 && amulet.getItem() == null)
                    player.playSound(SoundEvents.ENTITY_ITEM_BREAK, .75F, 2f);
            }
        }



    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!player.isSneaking()) {
            return super.onItemRightClick(world, player, hand);
        }

        ItemStack stack = player.getHeldItem(hand);
        toggleState(stack, player);

        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public void toggleState(ItemStack stack, EntityPlayer player) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = stack.getTagCompound();
        tag.setBoolean("isActive", !(tag.getBoolean("isActive")));

    }
}
