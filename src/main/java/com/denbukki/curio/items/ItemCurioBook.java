package com.denbukki.curio.items;

import com.denbukki.curio.Curio;
import com.denbukki.curio.client.gui.CurioGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCurioBook extends ItemBase {
    public ItemCurioBook() {
        super("ItemCurioBook");
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.openGui(Curio.instance, CurioGuiHandler.BOOK, worldIn, 0, 0, 0);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
