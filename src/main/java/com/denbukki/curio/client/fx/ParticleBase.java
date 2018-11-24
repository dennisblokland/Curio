package com.denbukki.curio.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class ParticleBase extends Particle {

    ParticleBase(World world, double posX, double posY, double posZ) {
        super(world, posX, posY, posZ);
    }

    ParticleBase(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        this(world, xCoord, yCoord, zCoord);

    }

    static TextureAtlasSprite getSprite(ResourceLocation location) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}