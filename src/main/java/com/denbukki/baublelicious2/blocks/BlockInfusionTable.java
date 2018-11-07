package com.denbukki.baublelicious2.blocks;

import com.denbukki.baublelicious2.items.ItemMysticCrystal;
import com.denbukki.baublelicious2.tiles.TileInfusionTable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

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

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ItemStack heldItem = player.getHeldItem(hand);
            TileInfusionTable tile = (TileInfusionTable) world.getTileEntity(pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            if (heldItem.getItem() instanceof ItemMysticCrystal && heldItem.getItemDamage() == 0) {
                if (!player.isSneaking()) {
                    if (tile.inventory.getStackInSlot(0).isEmpty()) {
                        itemHandler.insertItem(0, new ItemStack(heldItem.getItem(), 1, 0), false);
                        tile.infuseItem(player);
                        heldItem.setCount(heldItem.getCount() - 1);
                        tile.markDirty();
                    }
                }
            } else if (player.isSneaking()) {
                if(!tile.isWorking()){
                    player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
                }
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileInfusionTable tile = (TileInfusionTable) world.getTileEntity(pos);
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

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    }

}
