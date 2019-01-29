package com.denbukki.curio.client.gui.book;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonChangePage extends GuiButton {

    private final boolean previous;

    public GuiButtonChangePage(int id, int x, int y, boolean previous) {
        super(id, x, y, 23, 13, "");
        this.previous = previous;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        if (visible) {
            boolean mouseOver = mouseX >= this.x && mouseY >= y && mouseX < x + width && mouseY < y + height;

            mc.renderEngine.bindTexture(GuiCurioBook.texture);
            int u = 0;
            int v = 192;

            if (mouseOver) {
                u += 23;
            }

            if (previous) {
                v += 13;
            }

            drawTexturedModalRect(x, y, u, v, 23, 13);
        }
    }
}