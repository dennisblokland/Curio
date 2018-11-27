package com.denbukki.curio.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class CurioItems {
    //normal items
    public static final Item itemMysticCrystal = new ItemMysticCrystal();
    public static final Item itemAirShard = new ItemBase("ItemAirShard");
    public static final Item itemEarthShard = new ItemBase("ItemEarthShard");
    public static final Item itemWaterShard = new ItemBase("ItemWaterShard");
    public static final Item itemFireShard = new ItemBase("ItemFireShard");

    public static final Item itemMysticCrystalDust = new ItemBase("ItemMysticCrystalDust");

    //bauble items
    public static final Item itemRing = new ItemRing();
    public static final Item itemAmulet = new ItemAmulet();
    public static final Item itemBelt = new ItemBelt();

    public static final Item itemAmuletFieryCore = new ItemAmuletFieryCore();
    public static final Item itemAmuletNightvision = new ItemAmuletNightvision();
    public static final Item itemDivingAmulet = new ItemDivingAmulet();
    public static final Item itemGrowthPendant = new ItemGrowthPendant();

    public static final Item itemBeltWaterWalking = new ItemBeltWaterWalking();
    public static final Item itemFallingBelt= new ItemFallingBelt();
    public static final Item itemSpeedBelt= new ItemSpeedBelt();

    public static final Item itemRingOfFlight= new ItemRingOfFlight();


    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                itemRing,
                itemAmulet,
                itemBelt,
                itemMysticCrystal,
                itemAirShard,
                itemEarthShard,
                itemWaterShard,
                itemFireShard,
                itemMysticCrystalDust,
                itemAmuletFieryCore,
                itemAmuletNightvision,
                itemDivingAmulet,
                itemGrowthPendant,
                itemBeltWaterWalking,
                itemFallingBelt,
                itemSpeedBelt,
                itemRingOfFlight
        );


    }
    public static void registerModels() {


        //basic items
        ((ItemBase)itemMysticCrystal).registerItemModel();
        ((ItemBase)itemAirShard).registerItemModel();
        ((ItemBase)itemEarthShard).registerItemModel();
        ((ItemBase)itemWaterShard).registerItemModel();
        ((ItemBase)itemFireShard).registerItemModel();
        ((ItemBase)itemMysticCrystalDust).registerItemModel();

        //baubles
        ((BaublesItemBase)itemRing).registerItemModel();
        ((BaublesItemBase)itemAmulet).registerItemModel();
        ((BaublesItemBase)itemBelt).registerItemModel();

        ((BaublesItemBase)itemAmuletFieryCore).registerItemModel();
        ((BaublesItemBase)itemAmuletNightvision).registerItemModel();
        ((BaublesItemBase)itemDivingAmulet).registerItemModel();
        ((BaublesItemBase)itemGrowthPendant).registerItemModel();
        ((BaublesItemBase)itemBeltWaterWalking).registerItemModel();
        ((BaublesItemBase)itemFallingBelt).registerItemModel();
        ((BaublesItemBase)itemSpeedBelt).registerItemModel();
        ((BaublesItemBase)itemRingOfFlight).registerItemModel();
    }
}