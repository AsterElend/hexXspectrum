package aster.hexxspectrum.mixin;

import aster.hexxspectrum.registry.SpecItems;
import at.petrak.hexcasting.api.casting.eval.env.PlayerBasedCastEnv;


import net.minecraft.entity.LivingEntity;

import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.emi.trinkets.api.TrinketsApi;




@Mixin (value = PlayerBasedCastEnv.class, remap = false)
public abstract class OvercastCancelMixin {


    @Shadow @Final protected ServerPlayerEntity caster;


    @Unique
    protected boolean hasLei(LivingEntity player) {
        return TrinketsApi.getTrinketComponent(player).map(component ->
                component.isEquipped(SpecItems.ANCHOR)).orElse(false);

    }


    @Inject(method = "canOvercast", at = @At("HEAD"), cancellable = true)
    private void canOvercast(CallbackInfoReturnable<Boolean> callback) {
        if (hasLei(caster)) {
            callback.setReturnValue(false);
        }
    }

}