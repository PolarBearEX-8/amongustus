package cc.baritone.utils;

import client.util.EnchantmentManager;
import client.util.IBlockState;
import client.util.LvlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;

public class DepthFirstPolicy {
   public final Map map = new HashMap();
   public final Function function;
   public final EntityPlayerSP entityPlayerSP;

   private int computeCount(ItemStack stack) {
      if (stack == null) {
         return -1;
      } else {
         return stack.getItem() instanceof ItemTool ? ((ItemTool)stack.getItem()).getGlobalOptionType().getCount() : -1;
      }
   }

   private static Double resolveDouble(double offset, Double offset2) {
      return offset * offset2;
   }

   private double computeValueByBlock(Block block) {
      return computeValue(this.entityPlayerSP.inventoryPlayer.processAction3(this.computeCount(block, false, true)), block.getDefaultState())
         * this.computeValue(block);
   }

   public int computeCount(Block block, boolean flag) {
      return this.computeCount(block, flag, false);
   }

   public boolean matchesItemStack(ItemStack stack) {
      return LvlManager.getIndex(EnchantmentManager.enchantmentManager4.indexOffset, stack) > 0;
   }

   public static double computeValue(ItemStack stack, IBlockState blockState) {
      if (stack == null) {
         return 0.0;
      } else {
         float factor = blockState.getBlock().processAction8(null, null);
         if (factor < 0.0F) {
            return -1.0;
         } else {
            float factor2 = stack.getItem().processAction(stack, blockState.getBlock());
            if (factor2 > 1.0F) {
               int index = LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, stack);
               if (index > 0 && stack.getItem() != null) {
                  factor2 += (float)(index * index + 1);
               }
            }

            float factor3 = factor2 / factor;
            return !blockState.getBlock().getMaterial().isEnabled3() && (stack.getItem() == null || !stack.processAction2(blockState.getBlock()))
               ? (double)(factor3 / 100.0F)
               : (double)(factor3 / 30.0F);
         }
      }
   }

   private double computeValue(Block block) {
      return ((List)BaritoneManager.getSettings().blocksToAvoidBreaking.dataRef).contains(block)
         ? (Double)BaritoneManager.getSettings().avoidBreakingMultiplier.dataRef
         : 1.0;
   }

   private double getValue() {
      double offset = 1.0;
      if (this.entityPlayerSP.checkPotion(Potion.potion28)) {
         offset *= 1.0 + (double)(this.entityPlayerSP.getPotionEffect(Potion.potion28).getIndexOffset2() + 1) * 0.2;
      }

      if (this.entityPlayerSP.checkPotion(Potion.potion7)) {
         switch (this.entityPlayerSP.getPotionEffect(Potion.potion7).getIndexOffset2()) {
            case 0:
               offset *= 0.3;
               break;
            case 1:
               offset *= 0.09;
               break;
            case 2:
               offset *= 0.0027;
               break;
            default:
               offset *= 8.1E-4;
         }
      }

      return offset;
   }

   public int computeCount(Block block, boolean flag, boolean flag2) {
      if (!(Boolean)BaritoneManager.getSettings().autoTool.dataRef && flag2) {
         return this.entityPlayerSP.inventoryPlayer.currentItem;
      } else {
         int count = 0;
         double offset = Double.NEGATIVE_INFINITY;
         int count2 = Integer.MIN_VALUE;
         boolean flag3 = false;
         IBlockState defaultState = block.getDefaultState();

         for (int index = 0; index < 9; index++) {
            ItemStack stack = this.entityPlayerSP.inventoryPlayer.processAction3(index);
            if (((Boolean)BaritoneManager.getSettings().useSwordToMine.dataRef || !(stack.getItem() instanceof ItemSword))
               && (
                  !(Boolean)BaritoneManager.getSettings().itemSaver.dataRef
                     || stack.getIndexOffset2() + (Integer)BaritoneManager.getSettings().itemSaverThreshold.dataRef < stack.processAction()
                     || stack.processAction() <= 1
               )) {
               double offset2 = computeValue(stack, defaultState);
               boolean flag4 = this.matchesItemStack(stack);
               if (offset2 > offset) {
                  offset = offset2;
                  count = index;
                  count2 = this.computeCount(stack);
                  flag3 = flag4;
               } else if (offset2 == offset) {
                  int count3 = this.computeCount(stack);
                  if (count3 < count2 && (flag4 || !flag3) || flag && !flag3 && flag4) {
                     offset = offset2;
                     count = index;
                     count2 = count3;
                     flag3 = flag4;
                  }
               }
            }
         }

         return count;
      }
   }

   public DepthFirstPolicy(EntityPlayerSP player) {
      this.entityPlayerSP = player;
      if ((Boolean)BaritoneManager.getSettings().considerPotionEffects.dataRef) {
         double speedMultiplier = this.getValue();
         Function<Double, Double> mapper = value -> DepthFirstPolicy.resolveDouble(speedMultiplier, value);
         this.function = mapper.compose(item -> this.computeValueByBlock((Block)item));
      } else {
         this.function = (item -> this.computeValueByBlock((Block)item));
      }
   }

   public double computeValue(IBlockState blockState) {
      return (Double)this.map.computeIfAbsent(blockState.getBlock(), this.function);
   }
}
