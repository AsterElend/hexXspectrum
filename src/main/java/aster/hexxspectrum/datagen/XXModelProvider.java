package aster.hexxspectrum.datagen;

import aster.hexxspectrum.registry.SpecItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class XXModelProvider extends FabricModelProvider {
    public XXModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(SpecItems.ANCHOR, Models.GENERATED);
        generator.register(SpecItems.BANGLE, Models.GENERATED);
    }
}
