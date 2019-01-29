package com.denbukki.curio.blocks;

import com.denbukki.curio.Curio;
import com.denbukki.curio.ModInfo;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class BlockCrystal extends BlockRotatedPillar {

    private Item breakItem;
    private ArrayList<BiomeDictionary.Type> biomes;
    protected String name;
    public static final PropertyEnum<BlockCrystal.EnumOrientation> FACING = PropertyEnum.<BlockCrystal.EnumOrientation>create("facing", BlockCrystal.EnumOrientation.class);
    protected static final AxisAlignedBB CRYSTAL_UP_AABB = new AxisAlignedBB(0.1625D, 0.0D, 0.1625D, 0.8D, 0.8D, 0.8D);
    protected static final AxisAlignedBB CRYSTAL_DOWN_AABB = new AxisAlignedBB(0.1625D, 0.2D, 0.1625D, 0.8D, 1.0D, 0.8D);

    protected static final AxisAlignedBB CRYSTAL_SOUTH_AABB = new AxisAlignedBB(0.1625D, 0.1625D, 0.0D, 0.8D, 0.8D, 0.8D);
    protected static final AxisAlignedBB CRYSTAL_NORTH_AABB = new AxisAlignedBB(0.1625D, 0.1625D, 0.2D, 0.8D, 0.8D, 1.0D);

    protected static final AxisAlignedBB CRYSTAL_WEST_AABB =new AxisAlignedBB(0.2D, 0.1625D, 0.1625D, 1.0D, 0.8D, 0.8D);
    protected static final AxisAlignedBB CRYSTAL_EAST_AABB = new AxisAlignedBB(0.0D, 0.1625D, 0.1625D, 0.8D, 0.8D, 0.8D);

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


    public Item createItemBlock() {
        Item item = new ItemBlock(this).setRegistryName(getRegistryName());
        Curio.bookManager.addPage(this.getDescriptionText(), new ItemStack(item,1,0));

        return item;

    }
    public String getDescriptionText() {
        return Curio.proxy.translate(ModInfo.MOD_ID +"." + this.name + ".description");
    }

    public void registerItemModel(Item itemBlock) {
        Curio.proxy.registerItemRenderer(itemBlock, 0, name);
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((BlockCrystal.EnumOrientation)state.getValue(FACING))
        {
            case WEST:
                return CRYSTAL_WEST_AABB;
            case SOUTH:
                return CRYSTAL_SOUTH_AABB;
            case NORTH:
                return CRYSTAL_NORTH_AABB;
            case UP_Z:
            case UP_X:
                return CRYSTAL_UP_AABB;
            case DOWN_X:
            case DOWN_Z:
                return CRYSTAL_DOWN_AABB;
            case EAST:
            default:
                return CRYSTAL_EAST_AABB;
        }
    }
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(((BlockCrystal.EnumOrientation)state.getValue(FACING)).getFacing()));
    }
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:

                switch ((BlockCrystal.EnumOrientation)state.getValue(FACING))
                {
                    case EAST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.WEST);
                    case WEST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.EAST);
                    case SOUTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.NORTH);
                    case NORTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.SOUTH);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch ((BlockCrystal.EnumOrientation)state.getValue(FACING))
                {
                    case EAST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.NORTH);
                    case WEST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.SOUTH);
                    case SOUTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.EAST);
                    case NORTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.WEST);
                    case UP_Z:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.UP_X);
                    case UP_X:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.DOWN_X);
                    default:
                        return state;
                }

            case CLOCKWISE_90:

                switch ((BlockCrystal.EnumOrientation)state.getValue(FACING))
                {
                    case EAST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.SOUTH);
                    case WEST:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.NORTH);
                    case SOUTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.WEST);
                    case NORTH:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.EAST);
                    case UP_Z:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.UP_X);
                    case UP_X:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.UP_Z);
                    case DOWN_X:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.DOWN_Z);
                    case DOWN_Z:
                        return state.withProperty(FACING, BlockCrystal.EnumOrientation.DOWN_X);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, BlockCrystal.EnumOrientation.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((BlockCrystal.EnumOrientation)state.getValue(FACING)).getMetadata();

        return i;
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getDefaultState();

        return iblockstate.withProperty(FACING, BlockCrystal.EnumOrientation.forFacings(facing, placer.getHorizontalFacing()));
    }
    public enum EnumOrientation implements IStringSerializable
    {
        DOWN_X(0, "down_x", EnumFacing.DOWN),
        EAST(1, "east", EnumFacing.EAST),
        WEST(2, "west", EnumFacing.WEST),
        SOUTH(3, "south", EnumFacing.SOUTH),
        NORTH(4, "north", EnumFacing.NORTH),
        UP_Z(5, "up_z", EnumFacing.UP),
        UP_X(6, "up_x", EnumFacing.UP),
        DOWN_Z(7, "down_z", EnumFacing.DOWN);

        private static final BlockCrystal.EnumOrientation[] META_LOOKUP = new BlockCrystal.EnumOrientation[values().length];
        private final int meta;
        private final String name;
        private final EnumFacing facing;

        private EnumOrientation(int meta, String name, EnumFacing facing)
        {
            this.meta = meta;
            this.name = name;
            this.facing = facing;
        }
        public int getMetadata()
        {
            return this.meta;
        }

        public EnumFacing getFacing()
        {
            return this.facing;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        public static BlockCrystal.EnumOrientation byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public static BlockCrystal.EnumOrientation forFacings(EnumFacing clickedSide, EnumFacing entityFacing)
        {
            switch (clickedSide)
            {
                case DOWN:

                    switch (entityFacing.getAxis())
                    {
                        case X:
                            return DOWN_X;
                        case Z:
                            return DOWN_Z;
                        default:
                            throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                    }

                case UP:

                    switch (entityFacing.getAxis())
                    {
                        case X:
                            return UP_X;
                        case Z:
                            return UP_Z;
                        default:
                            throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                    }

                case NORTH:
                    return NORTH;
                case SOUTH:
                    return SOUTH;
                case WEST:
                    return WEST;
                case EAST:
                    return EAST;
                default:
                    throw new IllegalArgumentException("Invalid facing: " + clickedSide);
            }
        }

        public String getName()
        {
            return this.name;
        }

        static
        {
            for (BlockCrystal.EnumOrientation orientation : values())
            {
                META_LOOKUP[orientation.getMetadata()] = orientation;
            }
        }
    }


}

