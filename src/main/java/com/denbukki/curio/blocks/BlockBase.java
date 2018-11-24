package com.denbukki.curio.blocks;

import com.denbukki.curio.Curio;

import com.denbukki.curio.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

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
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


}
