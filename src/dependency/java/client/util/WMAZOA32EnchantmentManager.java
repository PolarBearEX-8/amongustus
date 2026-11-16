package client.util;

import client.setting.EnhancedOptionType;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class WMAZOA32EnchantmentManager extends EnchantmentManager {
   public static final String[] stringArray = new String[]{"all", "fire", "fall", "explosion", "projectile"};
   public static final int[] countArray2 = new int[]{1, 10, 5, 5, 3};
   public static final int[] countArray3 = new int[]{11, 8, 6, 8, 6};
   public static final int[] countArray = new int[]{20, 12, 10, 12, 15};
   public final int count2;

   @Override
   public String getString() {
      return "enchantment.protect." + stringArray[this.count2];
   }

   public static int computeCount(Entity entity, int count) {
      int count3 = count;
      int count4 = LvlManager.computeCount(EnchantmentManager.enchantmentManager14.indexOffset, entity.getItemStackArray2());
      if (count4 > 0) {
         count3 = count - MathHelper.floor_float((float)count * (float)count4 * 0.15F);
      }

      return count3;
   }

   public static double computeValueByEntity(Entity entity, double offset) {
      double offset2 = offset;
      int count = LvlManager.computeCount(EnchantmentManager.enchantmentManager18.indexOffset, entity.getItemStackArray2());
      if (count > 0) {
         offset2 = offset - (double)MathHelper.ceiling_double_int(offset * (double)((float)count * 0.15F));
      }

      return offset2;
   }

   @Override
   public int getCount() {
      return 4;
   }

   @Override
   public int computeCount(int count, DamageSource damageSource) {
      if (damageSource.isEnabled4()) {
         return 0;
      } else {
         float factor = (float)(6 + count * count) / 3.0F;
         return this.count2 == 0
            ? MathHelper.floor_float(factor * 0.75F)
            : (
               this.count2 == 1 && damageSource.isEnabled6()
                  ? MathHelper.floor_float(factor * 1.25F)
                  : (
                     this.count2 == 2 && damageSource == DamageSource.damageSource11
                        ? MathHelper.floor_float(factor * 2.5F)
                        : (
                           this.count2 == 3 && damageSource.isEnabled5()
                              ? MathHelper.floor_float(factor * 1.5F)
                              : (this.count2 == 4 && damageSource.isEnabled8() ? MathHelper.floor_float(factor * 1.5F) : 0)
                        )
                  )
            );
      }
   }

   @Override
   public boolean matchesEnchantmentManager(EnchantmentManager enchantmentManager) {
      if (!(enchantmentManager instanceof WMAZOA32EnchantmentManager)) {
         return super.matchesEnchantmentManager(enchantmentManager);
      } else {
         WMAZOA32EnchantmentManager wMAZOA32EnchantmentManager = (WMAZOA32EnchantmentManager)enchantmentManager;
         return wMAZOA32EnchantmentManager.count2 == this.count2 ? false : this.count2 == 2 || wMAZOA32EnchantmentManager.count2 == 2;
      }
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + countArray[this.count2];
   }

   @Override
   public int computeCountByIndex(int count) {
      return countArray2[this.count2] + (count - 1) * countArray3[this.count2];
   }

   public WMAZOA32EnchantmentManager(int count, ResourceLocation location, int count3, int count4) {
      super(count, location, count3, EnhancedOptionType.ARMOR);
      this.count2 = count4;
      if (count4 == 2) {
         this.enhancedOptionType = EnhancedOptionType.ARMOR_FEET;
      }
   }
}
