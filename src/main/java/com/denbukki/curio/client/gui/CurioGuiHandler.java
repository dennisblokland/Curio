package com.denbukki.curio.client.gui;

import com.denbukki.curio.blocks.container.ContainerPedestal;
import com.denbukki.curio.client.gui.book.GuiCurioBook;
import com.denbukki.curio.tiles.TileEntityPedestal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CurioGuiHandler implements IGuiHandler {
    public static final int PEDESTAL = 0;
    public static final int BOOK = 1;

    @Override
    public Container getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case PEDESTAL:
                return new ContainerPedestal(player.inventory, (TileEntityPedestal)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case PEDESTAL:
                return new GuiPedestal(getServerGuiElement(id, player, world, x, y, z), player.inventory);
            case BOOK:
                return new GuiCurioBook();
            default:
                return null;
        }
    }
}
