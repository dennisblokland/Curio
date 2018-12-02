package com.denbukki.curio.items;

import baubles.api.BaubleType;
import baubles.api.render.IRenderBauble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTiara extends BaublesItemBase implements  IRenderBauble {
    public ItemTiara() {
        super("ItemTiara");
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.HEAD;
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, IRenderBauble.RenderType type, float partialTicks) {
        if(type != IRenderBauble.RenderType.HEAD)
            return;
        IRenderBauble.Helper.rotateIfSneaking(player);
        GlStateManager.translate(0.3D, -0.5D, 0);
        GlStateManager.rotate(180F,-0.0F, 0F, 1F);
        GlStateManager.rotate(90,-0.0F, 1F, 0F);
        GlStateManager.scale(0.5, 0.5, 0.5);
        Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
    }
}
