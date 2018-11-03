package com.denbukki.baublelicious2.blocks;

import com.denbukki.baublelicious2.client.fx.ParticleXPOrb;
import com.denbukki.baublelicious2.items.Baublelicious2Items;
import com.denbukki.baublelicious2.items.ItemMysticCrystal;
import com.denbukki.baublelicious2.tiles.TileInfusionTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockInfusionTable extends BlockBaseContrainer {
    private boolean isWorking;
    public BlockInfusionTable() {
        super(Material.ROCK, "Infusion_Table");
        this.setHardness(5.0F);
        this.setResistance(2000.0F);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        TileInfusionTable tile = (TileInfusionTable)worldIn.getTileEntity(pos);
        boolean active = tile.isWorking();


        if (active)
        {
            double d0 = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)pos.getY() + 0.0625F);
            double d2 = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            for(int i=1; i < rand.nextInt((15 - 5) + 1) + 5 ;i++){
                float f = 8 / 15.0F;
                float f1 = 0.2F;
                float f2 = -0.1f + rand.nextFloat() * (0.2f - -0.1f);
                float f3 = -0.1f + rand.nextFloat() * (0.2f - -0.1f);
                ParticleXPOrb particle = new ParticleXPOrb(worldIn,  d0, d1+0.75,  d2, (double)f2, (double)f1, (double)f3);
                particle.setMaxAge(10);
                net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(particle);
            }
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ItemStack heldItem = player.getHeldItem(hand);
            TileInfusionTable tile = (TileInfusionTable)world.getTileEntity(pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            ItemStack stack = itemHandler.getStackInSlot(0);
            if(heldItem.getItem() instanceof ItemMysticCrystal && heldItem.getItemDamage() == 0) {
                if (!player.isSneaking()) {
                    if(tile.inventory.getStackInSlot(0).isEmpty()){
                        itemHandler.insertItem(0, new ItemStack(heldItem.getItem(),1,0),false);
                        tile.InfuseItem(player);
                        heldItem.setCount(heldItem.getCount()-1);
                        tile.markDirty();
                    }
                }
            }else if(player.isSneaking()){
                player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
            }
        }

        return true;
    }
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileInfusionTable tile = (TileInfusionTable)world.getTileEntity(pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileInfusionTable();
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    }

}
