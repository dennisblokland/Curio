package com.denbukki.baublelicious2.blocks;

import com.denbukki.baublelicious2.tiles.TileEntityCrystal;
import com.denbukki.baublelicious2.client.TileEntityCrystalRenderer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class BlockCrystal extends BlockBase {

    private Item breakItem;
    private ArrayList<BiomeDictionary.Type> biomes;

    public BlockCrystal(String name, Item breakItem , ArrayList<BiomeDictionary.Type> biomes) {
        super(Material.GLASS, name);
        this.breakItem = breakItem;
        this.biomes = biomes;
        this.setHardness(2.0F);
        this.setResistance(3.0F);
        this.setHarvestLevel("pickaxe", 1);



    }
    public BlockCrystal(String name, Item breakItem) {
        this(name, breakItem, new ArrayList<BiomeDictionary.Type>());

    }

    public ArrayList<BiomeDictionary.Type> getBiomes(){
        return biomes;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return breakItem;

    }

    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(1) + 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

}
