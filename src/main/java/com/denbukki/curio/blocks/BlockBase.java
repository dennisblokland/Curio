package com.denbukki.curio.blocks;

import com.denbukki.curio.Curio;

import com.denbukki.curio.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockBase extends Block {

    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(ModInfo.MOD_ID +"."+this.name);
        setRegistryName(name);
        setCreativeTab(Curio.CURIO_TAB);

    }


    public void registerItemModel(Item itemBlock) {
        Curio.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        Item item = new ItemBlock(this).setRegistryName(getRegistryName());
        Curio.bookManager.AddPage(this.getDescriptionText(), new ItemStack(item,1,0));

        return item;

    }

    public String getDescriptionText() {
        return I18n.format(ModInfo.MOD_ID +"." + this.name + ".description");
    }

}
