package com.denbukki.curio.blocks;

import com.denbukki.curio.Curio;
import com.denbukki.curio.ModInfo;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class BlockCrystal extends  BlockHorizontal {

    private Item breakItem;
    private ArrayList<BiomeDictionary.Type> biomes;
    protected String name;

    protected static final AxisAlignedBB CRYSTAL_COLLISION_AABB = new AxisAlignedBB(0.1625D, 0.0D, 0.1625D, 0.8D, 0.8D, 0.8D);
    protected static final AxisAlignedBB CRYSTAL_AABB = new AxisAlignedBB(0.1625D, 0.0D, 0.1625D, 0.8D, 0.8D, 0.8D);

    public BlockCrystal(String name, Item breakItem , ArrayList<BiomeDictionary.Type> biomes) {
        super(Material.GLASS, MapColor.ADOBE);
        this.name = name;
        setUnlocalizedName(ModInfo.MOD_ID +"."+this.name);
        setRegistryName(name);
        setCreativeTab(Curio.CURIO_TAB);
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

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public Item createItemBlock() {
        Item item = new ItemBlock(this).setRegistryName(getRegistryName());
        Curio.bookManager.AddPage(this.getDescriptionText(), new ItemStack(item,1,0));

        return item;

    }
    public String getDescriptionText() {
        return I18n.format(ModInfo.MOD_ID +"." + this.name + ".description");
    }

    public void registerItemModel(Item itemBlock) {
        Curio.proxy.registerItemRenderer(itemBlock, 0, name);
    }
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return CRYSTAL_COLLISION_AABB;
    }

    /**
     * Return an AABB (in world coords!) that should be highlighted when the player is targeting this Block
     */
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return CRYSTAL_AABB.offset(pos);
    }
}
