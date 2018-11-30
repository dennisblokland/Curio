package com.denbukki.curio.blocks;

import com.denbukki.curio.items.Infusable;
import com.denbukki.curio.items.ItemMysticCrystal;
import com.denbukki.curio.tiles.TileEntityInfusionTable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
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
            TileEntityInfusionTable tile = (TileEntityInfusionTable) world.getTileEntity(pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            ItemStack tileItem = tile.inventory.getStackInSlot(0);
                if (!player.isSneaking()) {
                    if (heldItem.getItem() instanceof Infusable && heldItem.getItemDamage() == 0) {
                        if (tile.inventory.getStackInSlot(0).isEmpty()) {
                            itemHandler.insertItem(0, new ItemStack(heldItem.getItem(), 1, 0), false);

                            heldItem.setCount(heldItem.getCount() - 1);
                            tile.markDirty();
                        }
                    }
                    else if(!tileItem.isEmpty() &&heldItem.getItem() instanceof ItemDye && heldItem.getItemDamage() == 4){
                        Infusable infusable = (Infusable)tileItem.getItem();
                        if (tile.inventory.getStackInSlot(1).getCount() < infusable.getLevels().length - 2) {
                            itemHandler.insertItem(1, new ItemStack(heldItem.getItem(), 1, heldItem.getMetadata()), false);
                            heldItem.setCount(heldItem.getCount() - 1);
                            tile.markDirty();
                        }
                    } else if(heldItem.isEmpty()){
                        tile.infuseItem(player, ((Infusable) tile.inventory.getStackInSlot(0).getItem()).getLevels()[tile.inventory.getStackInSlot(1).getCount() + 1]);
                    }

            } else if (player.isSneaking()) {
                if(!tile.isWorking()){
                    if(!tile.inventory.getStackInSlot(1).isEmpty()){
                        player.inventory.addItemStackToInventory(itemHandler.extractItem(1, 1, false));
                    }else{
                        player.inventory.addItemStackToInventory(itemHandler.extractItem(0, 64, false));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityInfusionTable tile = (TileEntityInfusionTable) world.getTileEntity(pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);
        ItemStack stack2 = itemHandler.getStackInSlot(1);
        if (!stack.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }
        if (!stack2.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack2);
            world.spawnEntity(item);

        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityInfusionTable();
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    }

}
