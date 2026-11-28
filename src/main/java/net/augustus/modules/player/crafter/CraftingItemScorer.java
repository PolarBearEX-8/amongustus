package net.augustus.modules.player.crafter;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class CraftingItemScorer {
   public static double scoreSword(ItemStack stack) {
      if (!(stack.getItem() instanceof ItemSword)) {
         return -1.0;
      } else {
         return (double)((ItemSword)stack.getItem()).getAttackDamage()
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
      }
   }

   public static double scoreArmor(ItemStack stack) {
      if (!(stack.getItem() instanceof ItemArmor)) {
         return -1.0;
      } else {
         ItemArmor item = (ItemArmor)stack.getItem();
         double d = (
                  (double)item.damageReduceAmount
                     + (double)(
                           6
                              + EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                                 * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack)
                        )
                        / 3.0
               )
               * 1.4
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 69.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack) / 11.0
            + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, stack) / 11.0;
         if (item.armorType == 0 && item.getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
            d -= 0.01;
         }

         return d;
      }
   }
}
