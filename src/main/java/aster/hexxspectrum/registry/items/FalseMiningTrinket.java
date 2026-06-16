package aster.hexxspectrum.registry.items;

import de.dafuqs.spectrum.api.item.ExtendedEnchantable;
import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FalseMiningTrinket extends MiningToolItem implements Trinket, ExtendedEnchantable {
    public static final TagKey<Block> NOTHING = TagKey.of(
            Registries.BLOCK.getKey(),
            new Identifier("hexxspectrum", "nothing")
    );

    public FalseMiningTrinket(Settings settings) {
        super(0, 0, ToolMaterials.DIAMOND, NOTHING, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (equipItem(user, stack)) {
            return TypedActionResult.success(stack, world.isClient());
        }
        return super.use(world, user, hand);
    }

    @Override
    public float getAttackDamage() {
        return 0f;
    }

    public static boolean equipItem(PlayerEntity user, ItemStack stack) {
        var optional = TrinketsApi.getTrinketComponent(user);
        if (optional.isPresent()) {
            TrinketComponent comp = optional.get();
            for (var group : comp.getInventory().values()) {
                for (TrinketInventory inv : group.values()) {
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.getStack(i).isEmpty()) {
                            SlotReference ref = new SlotReference(inv, i);
                            if (TrinketSlot.canInsert(stack, ref, user)) {
                                ItemStack newStack = stack.copy();
                                inv.setStack(i, newStack);
                                SoundEvent soundEvent = stack.getItem() instanceof Equipment eq ? eq.getEquipSound() : null;
                                if (!stack.isEmpty() && soundEvent != null) {
                                    user.emitGameEvent(GameEvent.EQUIP);
                                    user.playSound(soundEvent, 1.0F, 1.0F);
                                }
                                stack.setCount(0);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean acceptsEnchantment(Enchantment enchantment) {
        if (enchantment == Enchantments.MENDING) return false;
        if (enchantment == Enchantments.UNBREAKING) return false;
        if (enchantment == Enchantments.EFFICIENCY) return false;
        return true;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }
    @Override
    public boolean isEnchantable(ItemStack stack){
        return stack.getCount() == 1;
    }


}
