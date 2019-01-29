package com.denbukki.curio;

import com.denbukki.curio.client.gui.book.CurioBookPage;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BookManager {

    private List<CurioBookPage> allPages = Lists.newArrayList();

    public void addPage(String description, ItemStack stack){
        CurioBookPage page = new CurioBookPage(description, stack);
        allPages.add(page);
    }

    public List<CurioBookPage> getAllpages() {
        return allPages;
    }
}
