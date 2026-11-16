package client.util;

import client.setting.DisablerUndefinedType;
import client.setting.EnhancedOptionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class JS7CManager extends EnchantmentManager {
   public static final String[] stringArray = new String[]{"all", "undead", "arthropods"};
   public static final int[] countArray3 = new int[]{1, 5, 5};
   public static final int[] countArray2 = new int[]{11, 8, 8};
   public static final int[] countArray = new int[]{20, 20, 20};
   public final int count2;

   @Override
   public String getString() {
      return "enchantment.damage." + stringArray[this.count2];
   }

   @Override
   public int getCount() {
      return 5;
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, Entity entity, int count) {
      if (entity instanceof EntityLivingBase) {
         EntityLivingBase livingEntity2 = (EntityLivingBase)entity;
         if (this.count2 == 2 && livingEntity2.getUNDEAD() == DisablerUndefinedType.ARTHROPOD) {
            int count3 = 20 + livingEntity.getRandom().nextInt(10 * count);
            livingEntity2.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion3.indexOffset3, count3, 3));
         }
      }
   }

   @Override
   public boolean matchesEnchantmentManager(EnchantmentManager enchantmentManager) {
      return !(enchantmentManager instanceof JS7CManager);
   }

   @Override
   public int computeCount(int count) {
      return this.computeCountByIndex(count) + countArray[this.count2];
   }

   @Override
   public int computeCountByIndex(int count) {
      return countArray3[this.count2] + (count - 1) * countArray2[this.count2];
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.getItem() instanceof ItemGoldenAxe ? true : super.matchesItemStack(stack);
   }

   @Override
   public float computeFactor(int count, DisablerUndefinedType disablerUndefinedType) {
      return this.count2 == 0
         ? (float)count * 1.25F
         : (
            this.count2 == 1 && disablerUndefinedType == DisablerUndefinedType.UNDEAD
               ? (float)count * 2.5F
               : (this.count2 == 2 && disablerUndefinedType == DisablerUndefinedType.ARTHROPOD ? (float)count * 2.5F : 0.0F)
         );
   }

   public JS7CManager(int count, ResourceLocation location, int count3, int count4) {
      super(count, location, count3, EnhancedOptionType.WEAPON);
      this.count2 = count4;
   }
}
