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

    public ItemTiara(String name) {
        super(name);
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.HEAD;
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, IRenderBauble.RenderType type, float partialTicks) {
        if(type != RenderType.HEAD)
            return;
        IRenderBauble.Helper.translateToHeadLevel(player);
        IRenderBauble.Helper.translateToFace();
        IRenderBauble.Helper.defaultTransforms();
        GlStateManager.translate(0, 0.45D, 0);
        Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
    }
}
