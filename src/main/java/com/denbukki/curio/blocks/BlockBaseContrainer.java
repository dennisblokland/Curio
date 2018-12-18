package com.denbukki.curio.blocks;

import com.denbukki.curio.Curio;
import com.denbukki.curio.ModInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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

    public String getDescriptionText() {
        return I18n.format(ModInfo.MOD_ID +"." + this.name + ".description");
    }

}
