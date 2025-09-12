package aster.hexxspectrum;

import at.petrak.hexcasting.api.addldata.ADMediaHolder;
import at.petrak.hexcasting.api.item.MediaHolderItem;
import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.fabric.cc.adimpl.CCMediaHolder;
import de.dafuqs.spectrum.registries.SpectrumItems;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import static at.petrak.hexcasting.fabric.cc.HexCardinalComponents.MEDIA_HOLDER;

public class HexXspectrumCC implements ItemComponentInitializer {
    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {

        registry.register(i -> i instanceof MediaHolderItem, MEDIA_HOLDER, CCMediaHolder.ItemBased::new);


        registry.register(SpectrumItems.CITRINE_POWDER, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.DUST_UNIT, ADMediaHolder.AMETHYST_DUST_PRIORITY, s ));
        registry.register(SpectrumItems.CITRINE_SHARD, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.SHARD_UNIT, ADMediaHolder.AMETHYST_SHARD_PRIORITY, s ));

        registry.register(SpectrumItems.TOPAZ_POWDER, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.DUST_UNIT, ADMediaHolder.AMETHYST_DUST_PRIORITY, s ));
        registry.register(SpectrumItems.TOPAZ_SHARD, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.SHARD_UNIT, ADMediaHolder.AMETHYST_SHARD_PRIORITY, s ));


        registry.register(SpectrumItems.ONYX_POWDER, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.QUENCHED_SHARD_UNIT / 5, 1500, s ));
        registry.register(SpectrumItems.ONYX_SHARD, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.QUENCHED_SHARD_UNIT, ADMediaHolder.QUENCHED_SHARD_PRIORITY, s ));


        registry.register(SpectrumItems.MOONSTONE_POWDER, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.CRYSTAL_UNIT / 5, 2500, s ));
        registry.register(SpectrumItems.CITRINE_SHARD, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.CRYSTAL_UNIT, ADMediaHolder.CHARGED_AMETHYST_PRIORITY, s ));


        registry.register(SpectrumItems.AMETHYST_POWDER, MEDIA_HOLDER, s -> new CCMediaHolder.Static(
                () -> MediaConstants.DUST_UNIT, ADMediaHolder.AMETHYST_DUST_PRIORITY, s ));


    }
}
