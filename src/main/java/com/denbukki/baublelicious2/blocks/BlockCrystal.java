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
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCrystal extends BlockBase implements ITileEntityProvider {

    private Item breakItem;

    public BlockCrystal(String name, Item breakItem) {
        super(Material.GLASS, name);
        this.breakItem = breakItem;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
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
    @SideOnly(Side.CLIENT)
    public void initModel() {
        // Bind our TESR to our tile entity
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new TileEntityCrystalRenderer());

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
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    public TileEntityCrystal getTileEntity(IBlockAccess world, BlockPos pos) {
        return (TileEntityCrystal)world.getTileEntity(pos);
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCrystal();
    }
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

}
