package com.denbukki.curio.world;

import com.denbukki.curio.blocks.CurioBlocks;
import com.denbukki.curio.blocks.BlockCrystal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldgenCrystal implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) { // the overworld
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
        else if (world.provider.getDimension() == -1) { // the overworld
            generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }
    private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateNetherCrystal(CurioBlocks.blockFireCrystal, world, random, chunkX, chunkZ, 1);
    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateCrystal(CurioBlocks.blockEarthCrystal, world, random, chunkX, chunkZ, 45, 2);
        generateCrystal(CurioBlocks.blockAirCrystal, world, random, chunkX, chunkZ, 80, 2);

        generateWaterCrystal(CurioBlocks.blockWaterCrystal, world, random, chunkX, chunkZ,  2);

    }

    private void generateCrystal(BlockCrystal crystal, World world, Random random, int x, int z, int minY, int chances) {
        int xPos = x * 16 + 8;
        int zPos = z * 16 + 8;
        for (int i = 0; i < chances; i++) {
            final int posX = xPos + random.nextInt(16);
            final int posY = random.nextInt(world.getActualHeight() - minY) + minY;
            final int posZ = zPos + random.nextInt(16);
            final BlockPos newPos = new BlockPos(posX, posY, posZ);
            boolean biome = false;
            for (BiomeDictionary.Type type : crystal.getBiomes()) {
                if (BiomeDictionary.getTypes(world.getBiome(newPos)).contains(type)) {
                    biome = true;
                }
            }
            if (newPos != null) {
                IBlockState state = crystal.getStateFromMeta(5);                for (int tries = 0; tries < 4; tries++) {
                    BlockPos blockpos = newPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8));
                    final BlockPos underpos = blockpos.down();


                    if (biome && (world.isAirBlock(blockpos) || world.getBlockState(blockpos).getBlock().isReplaceable(world, blockpos)) && !world.isAirBlock(underpos) && world.isBlockNormalCube(underpos, true) && world.getBlockState(blockpos).getMaterial() != Material.WATER) {
                        world.setBlockState(blockpos, state, 2);
                    }
                }
            }
        }
    }

    private void generateWaterCrystal(BlockCrystal crystal, World world, Random random, int x, int z, int chances) {
        int xPos = x * 16 + 8;
        int zPos = z * 16 + 8;
        for (int i = 0; i < chances; i++) {
            final int posX = xPos + random.nextInt(16);
            final int posY = random.nextInt(world.getActualHeight() );
            final int posZ = zPos + random.nextInt(16);
            final BlockPos newPos = new BlockPos(posX, posY, posZ);
            if (newPos != null) {
                IBlockState state = crystal.getStateFromMeta(5);
                for (int tries = 0; tries < 8; tries++) {
                    BlockPos blockpos = newPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8));
                    final BlockPos underpos = blockpos.down();
                    if (world.getBlockState(blockpos).getMaterial() == Material.WATER && world.getBlockState( blockpos.up()).getMaterial() == Material.WATER && world.isBlockNormalCube(underpos, true)) {
                        world.setBlockState(blockpos, state, 2);
                    }
                }
            }
        }
    }

    private void generateNetherCrystal(BlockCrystal crystal, World world, Random random, int x, int z, int chances) {
        int xPos = x * 16 + 8;
        int zPos = z * 16 + 8;
        for (int i = 0; i < chances; i++) {
            final int posX = xPos + random.nextInt(16);
            final int posY = random.nextInt(world.getActualHeight() );
            final int posZ = zPos + random.nextInt(16);
            final BlockPos newPos = new BlockPos(posX, posY, posZ);
            if (newPos != null) {
                IBlockState state = crystal.getStateFromMeta(5);
                for (int tries = 0; tries < 4; tries++) {
                    BlockPos blockpos = newPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8), random.nextInt(8) - random.nextInt(8));
                    final BlockPos underpos = blockpos.down();
                    if ((world.isAirBlock(blockpos) || world.getBlockState(blockpos).getBlock().isReplaceable(world, blockpos)) && !world.isAirBlock(underpos) && world.isBlockNormalCube(underpos, true) && world.getBlockState(blockpos).getMaterial() != Material.LAVA) {
                        world.setBlockState(blockpos, state, 2);
                    }
                }
            }
        }
    }
}


