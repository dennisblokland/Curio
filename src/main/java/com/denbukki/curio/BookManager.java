package com.denbukki.curio;

import com.denbukki.curio.client.gui.book.CurioBookPage;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Stack;

public class BookManager {

    private List<CurioBookPage> allPages = Lists.newArrayList();

    public void AddPage(String Description, ItemStack stack){
        CurioBookPage page = new CurioBookPage(Description, stack);
        allPages.add(page);
    }

    public List<CurioBookPage> getAllpages() {
        return allPages;
    }
}
