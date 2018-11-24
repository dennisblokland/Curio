package com.denbukki.curio.items;

import com.denbukki.curio.Curio;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMysticCrystal extends ItemBase{

    public ItemMysticCrystal(){
        super("ItemMystic_Crystal");
        this.setHasSubtypes(true);
        setMaxDamage(0);


    }
    public static final int[] Levels = new int[] {0, 5, 10, 15, 20, 25, 30, 35, 50, 100};

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.getDamage(stack) == 0){
            tooltip.add("The Crystal seems to have weak mystic properties");
        }else{
            tooltip.add("Level " + this.getDamage(stack)  );
        }

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {

            int damage = this.getDamage(stack);

            if(damage != 0) {

                player.addExperienceLevel(damage);
                stack.setCount(stack.getCount()-1);
                if(!player.isCreative()){
                    player.inventory.addItemStackToInventory(new ItemStack(CurioItems.itemMysticCrystalDust,1));
                }
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }


    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return this.getDamage(stack) != 0;
    }

    public String getUnlocalizedName(ItemStack stack)
    {

        return super.getUnlocalizedName();
    }

    public void registerItemModel() {
        for (int level : Levels) {
            Curio.proxy.registerItemRenderer(this, level, name);
        }
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

        if (this.isInCreativeTab(tab)) {
            for (int level : Levels) {
                items.add(new ItemStack(this, 1, level));

            }
        }
    }
}
