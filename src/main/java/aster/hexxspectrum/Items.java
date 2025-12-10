package aster.hexxspectrum;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Items {
    public static final Item ANCHOR = registerItem("anchor", new Anchor(new FabricItemSettings().maxCount(1)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HexXSpectrum.MOD_ID, name), item); }

    private static void addToHexGroup(FabricItemGroupEntries entries) {entries.add(ANCHOR);}

    public static void register() {

        HexXSpectrum.LOGGER.info("Registering Mod Items for " + HexXSpectrum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(Items::addToHexGroup);
    }
}