package com.denbukki.baublelicious2.blocks;

import com.denbukki.baublelicious2.Baublelicious2;
import com.denbukki.baublelicious2.ModInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBaseContrainer extends BlockContainer {

    protected String name;

    public BlockBaseContrainer(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(ModInfo.MOD_ID +"."+this.name);
        setRegistryName(name);
        setCreativeTab(Baublelicious2.Baublelicious2Tab);
    }

    public void registerItemModel(Item itemBlock) {
        Baublelicious2.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBaseContrainer setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }


}
