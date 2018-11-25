package com.denbukki.curio.client;

import com.denbukki.curio.tiles.TileEntityInfusionTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityInfusionTableRenderer extends TileEntitySpecialRenderer<TileEntityInfusionTable>
{

    public void render(TileEntityInfusionTable te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        World world = this.getWorld();
        TileEntityInfusionTable tile = (TileEntityInfusionTable)world.getTileEntity(te.getPos());
        int items = tile.inventory.getStackInSlot(1).getCount();
        float[] angles = new float[9];
        float anglePer = 360F / items;
        float totalAngle = 0F;
        for(int i = 0; i < angles.length; i++)
            angles[i] = totalAngle += anglePer;

        ItemStack stack = tile.inventory.getStackInSlot(0);
        ItemStack stack2 = tile.inventory.getStackInSlot(1);
        float time = te.getWorld().getTotalWorldTime() + partialTicks;
        for(int i = 0; i < tile.inventory.getStackInSlot(1).getCount(); i++) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.25, y + 0.25 , z+ 0.25);
            GlStateManager.scale(0.5,0.5,0.5);
            GlStateManager.translate(0.5F, 1.25F, 0.5F);
            GlStateManager.rotate(angles[i] + (float) time, 0F, 1F, 0F);
            GlStateManager.translate(1.125F, 0F, 0.25F);
            GlStateManager.rotate(90F, 0F, 1F, 0F);
            GlStateManager.translate(0D, 0.075 * Math.sin((time + i * 10) / 5D), 0F);

            Minecraft mc = Minecraft.getMinecraft();
            if(!stack2.isEmpty()) {
                mc.getRenderItem().renderItem(stack2, ItemCameraTransforms.TransformType.GROUND);
            }
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
        if (!stack.isEmpty()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 1.1, z + 0.5);
            GlStateManager.rotate((time) * 4, 0, 1, 0);

            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }
}