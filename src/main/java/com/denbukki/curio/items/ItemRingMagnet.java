package com.denbukki.curio.items;

import baubles.api.BaubleType;
import com.denbukki.curio.util.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class ItemRingMagnet extends BaublesItemBase {

    public static final float RANGE = 10f;

    public ItemRingMagnet() {
        super("ItemRingMagnet");
    }


    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {

        if (player.isSneaking() == false) {
            double x = player.posX;
            double y = player.posY + 0.75;
            double z = player.posZ;

            List<EntityItem> items = player.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - RANGE, y - RANGE, z - RANGE, x + RANGE, y + RANGE, z + RANGE));
            int pulled = 0;
            for (EntityItem item : items)
                if (canPullItem(item)) {
                    if (pulled > 200)
                        break;
                    moveEntityTo(item, new Vector3d(x, y, z), 0.45F);
                    pulled++;
                }

        }
    }

    private boolean canPullItem(EntityItem item) {
        if (item.isDead)
            return false;

        ItemStack stack = item.getItem();
        return !stack.isEmpty();
    }


    public void moveEntityTo(Entity entity, Vector3d position, double speed) {
        Vector3d entityPos = new Vector3d(entity.posX, entity.posY - entity.getEyeHeight() + entity.height / 2f, entity.posZ);
        Vector3d motionVector = position.sub(entityPos);
        if (motionVector.magnitude() > 1.0D) {
            motionVector.normalise();
        }

        entity.motionX = motionVector.x * speed;
        entity.motionY = motionVector.y * speed;
        entity.motionZ = motionVector.z * speed;
    }


}