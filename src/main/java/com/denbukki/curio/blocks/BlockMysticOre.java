package com.denbukki.curio.blocks;

import com.denbukki.curio.items.CurioItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMysticOre extends BlockBase {
    public BlockMysticOre() {
        super(Material.ROCK, "Mystic_Ore");
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setLightLevel(0.25F);

    }
    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return CurioItems.itemMysticCrystal;

    }
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(1) + 1;
    }
    /**
     * Get the quantity dropped based on the given fortune level
     */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            return MathHelper.getInt(rand, 2, 5);
        }
        return 0;
    }
}
