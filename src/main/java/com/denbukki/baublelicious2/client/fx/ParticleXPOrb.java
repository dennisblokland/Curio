package com.denbukki.baublelicious2.client.fx;

import com.denbukki.baublelicious2.Baublelicious2;
import com.denbukki.baublelicious2.ModInfo;
import com.denbukki.baublelicious2.client.TextureStichHandler;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;


public class ParticleXPOrb extends ParticleBase {

    private double destX;
    private double destY;
    private double destZ;
    private boolean orbit;
    private final float scale;

    public ParticleXPOrb(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, boolean orbit) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
        this.setParticleTexture(TextureStichHandler.orb);
        this.orbit = orbit;
        destX = xCoord;
        destZ = zCoord;
        destY = yCoord;
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.particleGreen = 1.0f;
        this.particleRed = rand.nextFloat() * (1 - 0) + 1;
        this.particleBlue = 0.0f;

        this.particleScale *= 0.50F;
        scale = this.particleScale;

        this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int) ((float) this.particleMaxAge * scale);

    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float minU = 0;
        float maxU = 1;
        float minV = 0;
        float maxV = 1;

        if (this.particleTexture != null) {
            minU = particleTexture.getMinU();
            maxU = particleTexture.getMaxU();
            minV = particleTexture.getMinV();
            maxV = particleTexture.getMaxV();
        }

        float f10 = 0.1F * particleScale;
        float f11 = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float f12 = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float f13 = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

        int i = this.getBrightnessForRender(partialTicks);
        int j = i >> 16 & 65535;
        int k = i & 65535;
        buffer.pos(f11 - rotationX * f10 - rotationXY * f10, f12 - rotationZ * f10, f13 - rotationYZ * f10 - rotationXZ * f10).tex(maxU, maxV).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f11 - rotationX * f10 + rotationXY * f10, f12 + rotationZ * f10, f13 - rotationYZ * f10 + rotationXZ * f10).tex(maxU, minV).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f11 + rotationX * f10 + rotationXY * f10, f12 + rotationZ * f10, f13 + rotationYZ * f10 + rotationXZ * f10).tex(minU, minV).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f11 + rotationX * f10 - rotationXY * f10, f12 - rotationZ * f10, f13 + rotationYZ * f10 - rotationXZ * f10).tex(minU, maxV).color(particleRed, particleGreen, particleBlue, 1.0F).lightmap(j, k).endVertex();
    }

    // avoid calculating lighting for bees, it is too much processing
    @Override
    public int getBrightnessForRender(float p_189214_1_) {
        return 15728880;
    }

    @Override
    public void onUpdate() {
        if (orbit) {
            double d1 = (destX - this.posX) / 8.0D;
            double d2 = (destY + 0.25D / 2.0D - this.posY) / 8.0D;
            double d3 = (destZ - this.posZ) / 8.0D;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D) {
                d5 = d5 * d5;
                this.motionX += d1 / d4 * d5 * 0.01D;
                this.motionY += d2 / d4 * d5 * 0.01D;
                this.motionZ += d3 / d4 * d5 * 0.01D;
            }
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }
        this.move(this.motionX, this.motionY, this.motionZ);

    }

    @Override
    public int getFXLayer() {
        return 1;
    }

}
