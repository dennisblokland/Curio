package com.denbukki.curio.client.gui.book;

import com.denbukki.curio.Curio;
import com.denbukki.curio.items.CurioItems;
import com.denbukki.curio.items.ItemBase;
import com.google.common.collect.Ordering;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;

public class GuiCurioBook  extends GuiScreen {

    static final ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/book.png");

    private static final int BUTTON_NEXT = 0;
    private static final int BUTTON_PREV = 1;

    private int pageIndex = 0;
    private GuiButtonChangePage nextPage;
    private GuiButtonChangePage prevPage;

    private final List<CurioBookPage> pages;

    public GuiCurioBook() {
        pages = Ordering.from(String.CASE_INSENSITIVE_ORDER)
                .onResultOf(new Function<CurioBookPage, String>() {
                    @Override
                    public String apply(CurioBookPage input) {
                        return input.getItemStack().getDisplayName();
                    }
                })
                .immutableSortedCopy(Iterables.concat(Curio.bookManager.getAllpages()));
    }

    @Override
    public void initGui() {
        super.initGui();
        @SuppressWarnings("unchecked")
        List<GuiButton> buttons = buttonList;

        int bookXBegin = (width - 192) / 2;

        buttons.add(nextPage = new GuiButtonChangePage(BUTTON_NEXT, bookXBegin + 120, 2 + 154, false));
        buttons.add(prevPage = new GuiButtonChangePage(BUTTON_PREV, bookXBegin + 38, 2 + 154, true));
        updateButtonState();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case BUTTON_NEXT:
                ++pageIndex;
                break;
            case BUTTON_PREV:
                --pageIndex;
                break;
        }
        updateButtonState();
    }

    private void updateButtonState() {
        nextPage.visible = pageIndex < pages.size() - 1;
        prevPage.visible = pageIndex > 0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartials) {

        this.drawDefaultBackground();
        int bookXStart = (width - 192) / 2;
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(bookXStart, 2, 0, 0, 192, 192);

        CurioBookPage page = pages.get(this.pageIndex);
        ItemStack stack =page.getItemStack();
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(stack, bookXStart + 40, 14);


        fontRenderer.drawString("§n", bookXStart + 40 + 4 + 16, 17, 0x000000);
        fontRenderer.drawSplitString("§n" + page.getItemStack().getDisplayName(), bookXStart + 40, 17 + 15, 112, 0x000000);
        fontRenderer.drawSplitString("§n" + page.getDescriptionText(), bookXStart + 40, 17 + 35, 112, 0x282828);

        super.drawScreen(mouseX, mouseY, renderPartials);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char c, int key) {
        char lowerCase = Character.toLowerCase(c);
        if (key == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
        } else if (Character.getType(lowerCase) == Character.LOWERCASE_LETTER) {
            for (int i = 0, len = pages.size(); i < len; ++i) {
                CurioBookPage page = pages.get(i);
                if (Character.toLowerCase(page.getItemStack().getDisplayName().charAt(0)) == c) {
                    pageIndex = i;
                    updateButtonState();
                    break;
                }
            }
        }
    }
}