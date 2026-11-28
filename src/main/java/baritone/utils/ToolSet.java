package baritone.utils;

import baritone.Baritone;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;

public class ToolSet {
   private final Map breakStrengthCache = new HashMap();
   private final Function backendCalculation;
   private final EntityPlayerSP player;

   public int getBestSlot(Block block, boolean flag) {
      return this.getBestSlot(block, flag, false);
   }

   public static double calculateSpeedVsBlock(ItemStack stack, IBlockState state) {
      if (stack == null) {
         return 0.0;
      } else {
         float blockHardness = state.getBlock().getBlockHardness(null, null);
         if (blockHardness < 0.0F) {
            return -1.0;
         } else {
            float strVsBlock = stack.getItem().getStrVsBlock(stack, state.getBlock());
            if (strVsBlock > 1.0F) {
               int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
               if (enchantmentLevel > 0 && stack.getItem() != null) {
                  strVsBlock += (float)(enchantmentLevel * enchantmentLevel + 1);
               }
            }

            strVsBlock /= blockHardness;
            return !state.getBlock().getMaterial().isToolNotRequired() && (stack.getItem() == null || !stack.canHarvestBlock(state.getBlock()))
               ? (double)(strVsBlock / 100.0F)
               : (double)(strVsBlock / 30.0F);
         }
      }
   }

   public boolean hasSilkTouch(ItemStack stack) {
      return EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0;
   }

   public int getBestSlot(Block block, boolean flag, boolean flag2) {
      if (!(Boolean)Baritone.settings().autoTool.value && flag2) {
         return this.player.inventory.currentItem;
      } else {
         int n = 0;
         double negativeinfinity = Double.NEGATIVE_INFINITY;
         int minvalue = Integer.MIN_VALUE;
         boolean flag3 = false;
         IBlockState defaultState = block.getDefaultState();

         for (int i = 0; i < 9; i++) {
            ItemStack stackInSlot = this.player.inventory.getStackInSlot(i);
            if (((Boolean)Baritone.settings().useSwordToMine.value || !(stackInSlot.getItem() instanceof ItemSword))
               && (
                  !(Boolean)Baritone.settings().itemSaver.value
                     || stackInSlot.getItemDamage() + (Integer)Baritone.settings().itemSaverThreshold.value < stackInSlot.getMaxDamage()
                     || stackInSlot.getMaxDamage() <= 1
               )) {
               double d = calculateSpeedVsBlock(stackInSlot, defaultState);
               boolean flag4 = this.hasSilkTouch(stackInSlot);
               if (d > negativeinfinity) {
                  negativeinfinity = d;
                  n = i;
                  minvalue = this.getMaterialCost(stackInSlot);
                  flag3 = flag4;
               } else if (d == negativeinfinity) {
                  int materialCost = this.getMaterialCost(stackInSlot);
                  if (materialCost < minvalue && (flag4 || !flag3) || flag && !flag3 && flag4) {
                     negativeinfinity = d;
                     n = i;
                     minvalue = materialCost;
                     flag3 = flag4;
                  }
               }
            }
         }

         return n;
      }
   }

   public double getStrVsBlock(IBlockState state) {
      return (Double)this.breakStrengthCache.computeIfAbsent(state.getBlock(), this.backendCalculation);
   }

   private int getMaterialCost(ItemStack stack) {
      if (stack == null) {
         return -1;
      } else if (stack.getItem() instanceof ItemTool) {
         return ((ItemTool)stack.getItem()).getToolMaterial().getHarvestLevel();
      } else {
         return -1;
      }
   }

   public ToolSet(EntityPlayerSP player2) {
      this.player = player2;
      if ((Boolean)Baritone.settings().considerPotionEffects.value) {
         double d = this.potionAmplifier();
         Function<Double, Double> function = d2 -> d * d2;
         this.backendCalculation = function.compose(obj -> {
            return calculateSpeedVsBlock(this.player.inventory.getStackInSlot(this.getBestSlot((Block)obj, false, true)), ((Block)obj).getDefaultState()) * this.avoidanceMultiplier((Block)obj);
         });
      } else {
         this.backendCalculation = obj2 -> {
            return calculateSpeedVsBlock(this.player.inventory.getStackInSlot(this.getBestSlot((Block)obj2, false, true)), ((Block)obj2).getDefaultState()) * this.avoidanceMultiplier((Block)obj2);
         };
      }
   }

   private double avoidanceMultiplier(Block block) {
      return ((List)Baritone.settings().blocksToAvoidBreaking.value).contains(block) ? (Double)Baritone.settings().avoidBreakingMultiplier.value : 1.0;
   }

   private double potionAmplifier() {
      double d = 1.0;
      if (this.player.isPotionActive(Potion.digSpeed)) {
         d *= 1.0 + (double)(this.player.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2;
      }

      if (this.player.isPotionActive(Potion.digSlowdown)) {
         switch (this.player.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) {
            case 0:
               d *= 0.3;
               break;
            case 1:
               d *= 0.09;
               break;
            case 2:
               d *= 0.0027;
               break;
            default:
               d *= 8.1E-4;
         }
      }

      return d;
   }
}
