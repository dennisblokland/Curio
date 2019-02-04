package com.denbukki.curio.items;

import baubles.api.BaubleType;
import baubles.api.render.IRenderBauble;
import com.denbukki.curio.Curio;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemDeflectionTiara extends ItemTiara implements Infusable, IRenderBauble {

    Random random;
    public ItemDeflectionTiara() {
        super("ItemDeflectionTiara");
        this.setHasSubtypes(true);
        setMaxDamage(0);
        random = new Random();

    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.getDamage(stack) != 0){
            tooltip.add("Infused");
        }

    }

    @Override
    public void registerItemModel() {
        for (int level : getLevels()) {
            Curio.proxy.registerItemRenderer(this, level, name);
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HEAD;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        double range = 1.5;
        double yRange = 1.0;
        AxisAlignedBB bounds = new AxisAlignedBB(player.getEntityBoundingBox().minX - range, player.getEntityBoundingBox().minY - yRange, player.getEntityBoundingBox().minZ - range, player.getEntityBoundingBox().maxX + range, player.getEntityBoundingBox().maxY + yRange, player.getEntityBoundingBox().maxZ + range);
        List<Entity> deflectables = player.world.getEntitiesWithinAABB(EntityArrow.class, bounds);
        deflectables.addAll(player.world.getEntitiesWithinAABB(EntityThrowable.class, bounds));
        deflectables.addAll(player.world.getEntitiesWithinAABB(EntityFireball.class, bounds));
        for (Entity deflectable : deflectables) {
            if(player.world.isRemote) return;
            if(deflectable instanceof EntityThrowable){
                EntityThrowable throwable = (EntityThrowable)deflectable;
                if(throwable.getThrower()!= null && throwable.getThrower().getUniqueID() == player.getUniqueID())return;
            }
            if (deflectable.motionX != 0 && deflectable.motionY != 0 && deflectable.motionZ != 0) {
                if(random.nextInt( 1) == 1 || this.getMetadata(itemstack) != 0) {
                    deflectable.motionX = 0;
                    deflectable.motionY = 0;
                    deflectable.motionZ = 0;
                }
            }
        }
    }

    @Override
    public int[] getLevels() {
        return new int[] {0, 50};
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int level : getLevels()) {
                items.add(new ItemStack(this, 1, level));
            }
        }
    }

}
