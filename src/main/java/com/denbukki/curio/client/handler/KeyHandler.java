package com.denbukki.curio.client.handler;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.denbukki.curio.Curio;
import com.denbukki.curio.ModInfo;
import com.denbukki.curio.items.ItemGrowthPendant;
import com.denbukki.curio.network.MessageKeyBind;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class KeyHandler {

    public static final KeyHandler instance = new KeyHandler();
    static final Minecraft mc = Minecraft.getMinecraft();
    private static KeyBinding growthKey;

    private static ArrayList<KeyBinding> keys = new ArrayList<>();

    public KeyHandler() {
        growthKey = new KeyBinding(ModInfo.MOD_ID + ".keybind.growthToggle", Keyboard.KEY_G, ModInfo.MOD_ID + ".category.curio");
        ClientRegistry.registerKeyBinding(growthKey);
    }
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityPlayer player = FMLClientHandler.instance().getClient().player;
        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
        for (KeyBinding keyBindings : keys) {
            int button = keyBindings.getKeyCode();
            if (button > 0 && keyBindings.isPressed()) {
                if (keyBindings.getKeyDescription().equals(ModInfo.MOD_ID + ".keybind.growthToggle")) {
                    if (baubles.getStackInSlot(0).getItem() instanceof ItemGrowthPendant) {
                        ItemGrowthPendant pendant = (ItemGrowthPendant)baubles.getStackInSlot(0).getItem();
                        pendant.toggleState(baubles.getStackInSlot(0), player);
                        Curio.network.sendToServer(new MessageKeyBind(MessageKeyBind.KeyPacket.GROWTH));


                    }
                }
            }

        }
    }
    public static void addKeys() {
        keys.add(growthKey);
    }
}
