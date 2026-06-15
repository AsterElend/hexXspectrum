package aster.hexxspectrum.registry.casting;

import aster.hexxspectrum.HexXSpectrum
import at.petrak.hexcasting.api.casting.math.HexDir


import aster.hexxspectrum.HexXSpectrum.id;
import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.minecraft.registry.Registry

object SpecPatterns {
    @JvmStatic
    fun init(){
        register("bangle_break", "adaeqaqqqqqedad", HexDir.SOUTH_EAST, OpBangleBreak)
    }

    private fun register(name: String, signature: String, startDir: HexDir, action: Action) =
        Registry.register(HexActions.REGISTRY, id(name),
            ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), action)
        )
}
