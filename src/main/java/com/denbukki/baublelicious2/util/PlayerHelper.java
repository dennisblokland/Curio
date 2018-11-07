package com.denbukki.baublelicious2.util;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.denbukki.baublelicious2.Baublelicious2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class PlayerHelper {

    public static EntityPlayer getPlayerFromUUID(UUID uuid, World world) {
        return getPlayerFromUUID(uuid.toString(),world);
    }

    @SuppressWarnings("unchecked")
    public static EntityPlayer getPlayerFromUUID(String uuid, World world) {
        return Baublelicious2.proxy.getPlayerFromUUID(uuid, world);
    }

    public static boolean isWithinRangeOf(EntityPlayer player, int x, int y, int z, int range) {
        return player.posX >= x - range && player.posX <= x + range + 1 && player.posY >= y - range && player.posY <= y + range + 1 && player.posZ >= z - range && player.posZ <= z + range + 1;
    }

    public static boolean isWearingBauble(EntityPlayer player, IBauble bauble) {
        IBaublesItemHandler inventory = BaublesApi.getBaublesHandler(player);
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack != null && stack.getItem() == bauble) {
                return true;
            }
        }
        return false;
    }
}
