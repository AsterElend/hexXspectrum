package aster.hexxspectrum;

import aster.hexxspectrum.registry.SpecItems;
import aster.hexxspectrum.registry.casting.SpecPatterns;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vazkii.patchouli.api.PatchouliAPI;

import static at.petrak.hexcasting.interop.HexInterop.PATCHOULI_ANY_INTEROP_FLAG;

public class HexXSpectrum implements ModInitializer {
    public static final String MOD_ID = "hexxspectrum";

    public static Identifier id(String name){
        return new Identifier(MOD_ID, name);
    }

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        SpecItems.register();
        SpecPatterns.init();
        PatchouliAPI.get().setConfigFlag(PATCHOULI_ANY_INTEROP_FLAG, true);

        LOGGER.info("Hello Fabric world!");
    }
}