package com.denbukki.curio.tiles;

import baubles.api.IBauble;
import com.denbukki.curio.Curio;
import com.denbukki.curio.network.PacketRequestUpdatePedestal;
import com.denbukki.curio.network.PacketUpdatePedestal;
import com.denbukki.curio.util.PlayerHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.UUID;

public class TileEntityPedestal extends TileEntity implements ITickable {


    private static final int RANGE = 25;
    private UUID playerUUID;
    public EntityItem itemEntity = null;
    public ItemStack cachedBauble = null;
    public boolean isActive = false;
    public WeakReference<EntityPlayer> cachedPlayer = new WeakReference<>(null);
    public ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            if (!world.isRemote) {
                Curio.network.sendToAllAround(new PacketUpdatePedestal(TileEntityPedestal.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
            }
        }
    };

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
    }

    @Override
    public void onLoad() {
        if (world.isRemote) {
            Curio.network.sendToServer(new PacketRequestUpdatePedestal(this));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setUniqueId("playerUUID", getPlayerUUID());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        playerUUID = compound.getUniqueId("playerUUID");

        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (this.hasWorld()) baublesUpdate();
    }
    public UUID getPlayerUUID(){
        if(playerUUID != null){
            return playerUUID;
        }else{
            return new UUID(0,0);
        }
    }
    public void setPlayerUUID(EntityPlayer player) {
        this.playerUUID = player.getUniqueID();
    }


    @SuppressWarnings("unchecked")
    private void baublesUpdate() {
        if (isActive) {
            EntityPlayer player = cachedPlayer.get();
            if (player != null) {
                ItemStack bauble = inventory.getStackInSlot(0);
                if (bauble != null && bauble.isItemEqual(cachedBauble)) {
                    if (playerUUID.equals(player.getUniqueID())) {
                        Item baubleItem = bauble.getItem();
                        if (baubleItem instanceof IBauble) {
                            if (PlayerHelper.isWithinRangeOf(player, getPos().getX(), getPos().getY(), getPos().getZ(), RANGE)) {
                                if (!PlayerHelper.isWearingBauble(player, (IBauble) cachedBauble.getItem()))
                                    ((IBauble) bauble.getItem()).onWornTick(bauble, player);
                            } else {
                                if (!PlayerHelper.isWearingBauble(player, (IBauble) cachedBauble.getItem()))
                                    deactivateBauble(player);
                            }
                        }
                    } else {
                        if (!PlayerHelper.isWearingBauble(player, (IBauble) cachedBauble.getItem()))
                            deactivateBauble(player);
                    }
                } else {
                    if (!PlayerHelper.isWearingBauble(player, (IBauble) cachedBauble.getItem()))
                        deactivateBauble(player);
                }
            } else {
                isActive = false;
                cachedPlayer = null;
                cachedBauble = null;
            }
        } else {
            ItemStack bauble = inventory.getStackInSlot(0);
            if (bauble != null && !playerUUID.equals(new UUID(0,0))) {
                Item baubleItem = bauble.getItem();
                if (baubleItem instanceof IBauble) {
                    EntityPlayer player = PlayerHelper.getPlayerFromUUID(playerUUID, this.world);
                    if (player != null && PlayerHelper.isWithinRangeOf(player, getPos().getX(), getPos().getY(), getPos().getZ(), RANGE)) {
                        if (!PlayerHelper.isWearingBauble(player, (IBauble) baubleItem)) activateBauble(bauble, player);
                    }
                }
            }
        }
    }
        private void activateBauble(ItemStack bauble, EntityPlayer player) {
            isActive = true;
            cachedBauble = bauble.copy();
            cachedPlayer = new WeakReference<>(player);
            ((IBauble) bauble.getItem()).onEquipped(bauble, player);
        }

        private void deactivateBauble(EntityPlayer player) {
            isActive = false;
            cachedPlayer = new WeakReference<>(null);
            ((IBauble) cachedBauble.getItem()).onUnequipped(cachedBauble, player);
            cachedBauble = null;
        }

}
