package aster.hexxspectrum.registry;

import aster.hexxspectrum.HexXSpectrum;
import aster.hexxspectrum.registry.items.FalseMiningTrinket;
import aster.hexxspectrum.registry.items.JewelSocksItem;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class SpecItems {
    public static final Item ANCHOR = registerItem("anchor", new TrinketItem(new FabricItemSettings().maxCount(1)));
    public static final Item BANGLE = registerItem("bangle", new FalseMiningTrinket(new FabricItemSettings().maxCount(1)));
    public static final Item JEWEL_SOCKS = registerItem("jewel_socks", new JewelSocksItem(new FabricItemSettings().maxCount(1)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HexXSpectrum.MOD_ID, name), item); }

    private static void addToHexGroup(FabricItemGroupEntries entries) {

        entries.add(ANCHOR);
        entries.add(BANGLE);
        entries.add(JEWEL_SOCKS);
    }


    public static void register() {

        HexXSpectrum.LOGGER.info("Registering Mod SpecItems for " + HexXSpectrum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier("hexcasting:hexcasting"))).register(SpecItems::addToHexGroup);
    }
}