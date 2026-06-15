package aster.hexxspectrum.registry.casting;

import aster.hexxspectrum.registry.SpecItems
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getBlockPos
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.mod.HexConfig
import at.petrak.hexcasting.xplat.IXplatAbstractions
import de.dafuqs.spectrum.enchantments.VoidingEnchantment
import dev.emi.trinkets.api.TrinketComponent
import dev.emi.trinkets.api.TrinketsApi
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import java.util.function.Function


 object OpBangleBreak : SpellAction {
    override val argc = 1
    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val pos = args.getBlockPos(0, argc)
        env.assertPosInRange(pos)
        if (env.castingEntity == null){
            throw MishapNoBangle()
        }
        var bangle = getBangle(env.castingEntity!!)
        if (bangle == null || bangle == ItemStack.EMPTY){
            throw MishapNoBangle()
        }

        var enchants = EnchantmentHelper.get(bangle)


        return SpellAction.Result(Spell(pos, enchants), calcCost(bangle).toLong(), listOf())
    }

    fun calcCost(bangle: ItemStack): Float {
        val enchants: Map<Enchantment?, Int?> = EnchantmentHelper.get(bangle) ?: return 0f
        var cost = 0f;
        for (enchantment in enchants) {
            if (enchantment.value != null){
                cost += (enchantment.value!! * MediaConstants.DUST_UNIT)
            }
            if (enchantment is VoidingEnchantment){
                cost = (MediaConstants.DUST_UNIT / 100).toFloat()
                break
            }
        }
        return cost
    }

    private data class Spell(val pos: BlockPos, val enchants: Map<Enchantment, kotlin.Int>) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val state = env.world.getBlockState(pos)
            val tier = HexConfig.server().opBreakHarvestLevel()
            if (
                !state.isAir
                && state.getHardness(env.world, pos) >= 0f
                && IXplatAbstractions.INSTANCE.isCorrectTierForDrops(tier, state)
                && IXplatAbstractions.INSTANCE.isBreakingAllowed(env.world, pos, state, env.castingEntity as? ServerPlayerEntity)
            ) {
                val blockEntity = env.world.getBlockEntity(pos)
                Block.dropStacks(state, env.world, pos, blockEntity, null, ItemStack(Items.DIAMOND_PICKAXE).apply
                  {
                      for (enchantment in enchants){
                          addEnchantment(enchantment.key, enchantment.value)
                      }
                  }
                )
                env.world.breakBlock(pos, false)
            }
        }
    }


    private fun getBangle(player: LivingEntity): ItemStack =
        TrinketsApi.getTrinketComponent(player)
            .map { c ->
                c.getEquipped(SpecItems.BANGLE)
                    .stream()
                    .findFirst()
                    .map { it.right }
                    .orElse(ItemStack.EMPTY)
            }
            .orElse(ItemStack.EMPTY)!!
}
