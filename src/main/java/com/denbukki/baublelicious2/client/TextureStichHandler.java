package com.denbukki.baublelicious2.client;

import com.denbukki.baublelicious2.ModInfo;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStichHandler {
    public static TextureAtlasSprite orb;

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre e) {
        orb = e.getMap().registerSprite(new ResourceLocation(ModInfo.MOD_ID, "particle/orb"));
    }
}
