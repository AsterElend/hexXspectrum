package aster.hexxspectrum.registry.items;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class JewelSocksItem extends TrinketItem {
    public JewelSocksItem(Settings settings) {
        super(settings);
    }
    public static final UUID SOCK_HAND = UUID.fromString("10c64e9f-3462-424b-9fc1-3263c42cc98f");
    public static final UUID SOCK_OFFHAND = UUID.fromString("6b322bd2-494a-4d8d-9aac-b64a703f8f36");

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slot, entity, uuid);


       SlotAttributes.addSlotModifier(modifiers, "hand/ring", SOCK_HAND, 1, EntityAttributeModifier.Operation.ADDITION);
       SlotAttributes.addSlotModifier(modifiers, "offhand/ring", SOCK_OFFHAND, 1, EntityAttributeModifier.Operation.ADDITION);

        return modifiers;
    }
}
