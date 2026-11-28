package net.minecraft.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class EnchantmentProtection extends Enchantment {
   private static final String[] protectionName = new String[]{"all", "fire", "fall", "explosion", "projectile"};
   private static final int[] baseEnchantability = new int[]{1, 10, 5, 5, 3};
   private static final int[] levelEnchantability = new int[]{11, 8, 6, 8, 6};
   private static final int[] thresholdEnchantability = new int[]{20, 12, 10, 12, 15};
   public final int protectionType;

   @Override
   public int calcModifierDamage(int n, DamageSource damageSource) {
      if (damageSource.canHarmInCreative()) {
         return 0;
      } else {
         float f = (float)(6 + n * n) / 3.0F;
         return this.protectionType == 0
            ? MathHelper.floor_float(f * 0.75F)
            : (
               this.protectionType == 1 && damageSource.isFireDamage()
                  ? MathHelper.floor_float(f * 1.25F)
                  : (
                     this.protectionType == 2 && damageSource == DamageSource.fall
                        ? MathHelper.floor_float(f * 2.5F)
                        : (
                           this.protectionType == 3 && damageSource.isExplosion()
                              ? MathHelper.floor_float(f * 1.5F)
                              : (this.protectionType == 4 && damageSource.isProjectile() ? MathHelper.floor_float(f * 1.5F) : 0)
                        )
                  )
            );
      }
   }

   @Override
   public int getMinEnchantability(int n) {
      return baseEnchantability[this.protectionType] + (n - 1) * levelEnchantability[this.protectionType];
   }

   @Override
   public String getName() {
      return "enchantment.protect." + protectionName[this.protectionType];
   }

   @Override
   public int getMaxLevel() {
      return 4;
   }

   public EnchantmentProtection(int n, ResourceLocation location, int n2, int n3) {
      super(n, location, n2, EnumEnchantmentType.ARMOR);
      this.protectionType = n3;
      if (n3 == 2) {
         this.type = EnumEnchantmentType.ARMOR_FEET;
      }
   }

   public static int getFireTimeForEntity(Entity entity, int n) {
      int maxEnchantmentLevel = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.fireProtection.effectId, entity.getInventory());
      if (maxEnchantmentLevel > 0) {
         n -= MathHelper.floor_float((float)n * (float)maxEnchantmentLevel * 0.15F);
      }

      return n;
   }

   public static double getBlastDamageReduction(Entity entity, double d) {
      int maxEnchantmentLevel = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.blastProtection.effectId, entity.getInventory());
      if (maxEnchantmentLevel > 0) {
         d -= (double)MathHelper.floor_double(d * (double)((float)maxEnchantmentLevel * 0.15F));
      }

      return d;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + thresholdEnchantability[this.protectionType];
   }

   @Override
   public boolean canApplyTogether(Enchantment enchantment) {
      if (!(enchantment instanceof EnchantmentProtection)) {
         return super.canApplyTogether(enchantment);
      } else {
         EnchantmentProtection enchantmentProtection = (EnchantmentProtection)enchantment;
         return enchantmentProtection.protectionType == this.protectionType ? false : this.protectionType == 2 || enchantmentProtection.protectionType == 2;
      }
   }
}
