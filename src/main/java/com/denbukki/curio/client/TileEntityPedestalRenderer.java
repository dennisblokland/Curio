package com.denbukki.curio.client;

import com.denbukki.curio.blocks.BlockPedestal;
import com.denbukki.curio.tiles.TileEntityPedestal;
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
public class TileEntityPedestalRenderer extends TileEntitySpecialRenderer<TileEntityPedestal>
{

    public void render(TileEntityPedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        World world = this.getWorld();
        TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(te.getPos());
        ItemStack stack = tile.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 0.7, z + 0.5);
            GlStateManager.rotate(180,0,1,0);

            GlStateManager.rotate( BlockPedestal.getRationFromMeta(te.getBlockMetadata()), 0, 1, 0);
            GlStateManager.translate(0, 0, -0.085);
            GlStateManager.rotate(-45, 1, 0, 0);
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }


    }
}