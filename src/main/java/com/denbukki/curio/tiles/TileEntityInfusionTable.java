package com.denbukki.curio.tiles;

import com.denbukki.curio.Curio;
import com.denbukki.curio.blocks.CurioBlocks;
import com.denbukki.curio.items.Infusable;
import com.denbukki.curio.network.PacketRequestUpdateInfusionTable;
import com.denbukki.curio.network.PacketUpdateInfusionTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;


public class TileEntityInfusionTable extends TileEntity implements ITickable {


    private static final int CRAFT_DONE_EVENT = 2;
    private static final int CRAFT_EFFECT_EVENT = 55;
    private int infuseTime;
    private int level = 0;
    private int totalInfuseTime = 200;

    public void setInfuseTime(int infuseTime) {
        this.infuseTime = infuseTime;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getInfuseTime() {
        return infuseTime;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalInfuseTime() {
        return totalInfuseTime;
    }

    Random rand = new Random();

    public ItemStackHandler getInventory() {
        return inventory;
    }

    private ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            if (!world.isRemote) {
                Curio.network.sendToAllAround(new PacketUpdateInfusionTable(TileEntityInfusionTable.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
                if (slot == 0 && inventory.getStackInSlot(0).getItemDamage() != 0) {
                    world.addBlockEvent(getPos(), CurioBlocks.blockInfusionTable, CRAFT_DONE_EVENT, 0);

                }
            }
        }
    };



    @Override
    public boolean receiveClientEvent(int id, int param) {
        switch (id) {
            case CRAFT_EFFECT_EVENT: {
 ;
                double d0 = (double) pos.getX() + 0.5D + ((double) rand.nextFloat() - 0.5D) * 0.2D;
                double d1 = (double) ((float) pos.getY() + 0.0625F);
                double d2 = (double) pos.getZ() + 0.5D + ((double) rand.nextFloat() - 0.5D) * 0.2D;
                for (int i = 1; i < level; i++) {
                    float f1 = 0.025F;
                    float f2 = -0.1f + rand.nextFloat() * (0.1f - -0.1f);
                    float f3 = -0.1f + rand.nextFloat() * (0.1f - -0.1f);
                    Curio.proxy.orbFX(d0, d1 + 1.1, d2, (double) f2, (double) f1, (double) f3, 200, true);
                }
                return true;
            }
            case CRAFT_DONE_EVENT: {
                infusionFinished();
                return true;
            }
            default:
                return super.receiveClientEvent(id, param);
        }
    }

    public void infusionFinished() {
        double x = (double) pos.getX() + 0.5D;
        double y = ((float) pos.getY() + 0.75D);
        double z = (double) pos.getZ() + 0.5D;

        for (int i = 0; i < 360; i++) {
            int yaw = 0;
            if (i < 180) {
                yaw = i;
            } else {
                yaw = i - 360;
            }
            Double motionX = Math.cos(yaw / 180.0 * Math.PI);
            Double MotionZ = Math.sin(yaw / 180.0 * Math.PI);
            Curio.proxy.orbFX(x, y, z, motionX, (double) 0, MotionZ, 25, false);

            this.world.playSound(null, pos.getX(), (double) pos.getY() + 0.5D, pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1F, 1);

        }

    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
    }

    public void infuseItem(EntityPlayer player, int level) {

        if (player.experienceLevel < level || level == 0) {
            return;
        }
        if(this.inventory.getStackInSlot(0).getItem() instanceof Infusable == false || this.inventory.getStackInSlot(0).getMetadata() != 0){
            return;
        }
        this.level = level;
        this.world.playSound(null, pos.getX(), (double) pos.getY() + 0.5D, pos.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1F, 1);
        this.inventory.setStackInSlot(1, new ItemStack(Items.AIR));
        player.onEnchant(null, level);
        world.addBlockEvent(getPos(), CurioBlocks.blockInfusionTable, CRAFT_EFFECT_EVENT, 0);


    }

    @Override
    public void update() {
        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.getItem() instanceof Infusable) {
            if (level != 0) {
                ++this.infuseTime;
                if (this.infuseTime == this.totalInfuseTime) {
                    inventory.setStackInSlot(0, new ItemStack(this.inventory.getStackInSlot(0).getItem(), 1, level));
                    this.infuseTime = 0;
                    level = 0;
                    if (!world.isRemote) {

                    }
                }
            }
        }

        if (isWorking()) {

        }
    }

    public boolean isWorking() {
        return this.infuseTime > 0;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("level", (short) level);
        compound.setInteger("infuseTime", (short) infuseTime);

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        level = compound.getInteger("level");
        infuseTime = compound.getInteger("infuseTime");
        super.readFromNBT(compound);
    }

    @Override
    public void onLoad() {
        if (world.isRemote) {
            Curio.network.sendToServer(new PacketRequestUpdateInfusionTable(this));
        }
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }
}
