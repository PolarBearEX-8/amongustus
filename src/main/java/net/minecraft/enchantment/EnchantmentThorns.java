package net.minecraft.enchantment;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThorns extends Enchantment {
   public EnchantmentThorns(int n, ResourceLocation location, int n2) {
      super(n, location, n2, EnumEnchantmentType.ARMOR_TORSO);
      this.setName("thorns");
   }

   public static int getDamage(int n, Random rand) {
      return n > 10 ? n - 10 : 1 + rand.nextInt(4);
   }

   @Override
   public int getMaxEnchantability(int n) {
      return super.getMinEnchantability(n) + 50;
   }

   @Override
   public int getMinEnchantability(int n) {
      return 10 + 20 * (n - 1);
   }

   public static boolean shouldHit(int n, Random rand) {
      return n <= 0 ? false : rand.nextFloat() < 0.15F * (float)n;
   }

   @Override
   public boolean canApply(ItemStack stack) {
      return stack.getItem() instanceof ItemArmor ? true : super.canApply(stack);
   }

   @Override
   public void onUserHurt(EntityLivingBase entity, Entity entity2, int n) {
      Random rng = entity.getRNG();
      ItemStack enchantedItem = EnchantmentHelper.getEnchantedItem(Enchantment.thorns, entity);
      if (shouldHit(n, rng)) {
         if (entity2 != null) {
            entity2.attackEntityFrom(DamageSource.causeThornsDamage(entity), (float)getDamage(n, rng));
            entity2.playSound("damage.thorns", 0.5F, 1.0F);
         }

         if (enchantedItem != null) {
            enchantedItem.damageItem(3, entity);
         }
      } else if (enchantedItem != null) {
         enchantedItem.damageItem(1, entity);
      }
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }
}
