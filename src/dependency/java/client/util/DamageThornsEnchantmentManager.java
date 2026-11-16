package client.util;

import client.setting.EnhancedOptionType;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class DamageThornsEnchantmentManager extends EnchantmentManager {
   public static boolean nextFloat(int count, Random random) {
      return count <= 0 ? false : random.nextFloat() < 0.15F * (float)count;
   }

   public static int nextInt(int count, Random random) {
      return count > 10 ? count - 10 : 1 + random.nextInt(4);
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public void applyEntityLivingBase2(EntityLivingBase livingEntity, Entity entity, int count) {
      Random random = livingEntity.getRandom();
      ItemStack stack = LvlManager.resolveItemStackByEnchantmentManag(EnchantmentManager.enchantmentManager12, livingEntity);
      if (nextFloat(count, random)) {
         if (entity != null) {
            entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntity(livingEntity), (float)nextInt(count, random));
            entity.applyText("damage.thorns", 0.5F, 1.0F);
         }

         if (stack != null) {
            stack.applyEntityLivingBaseByEntityLivingBase(3, livingEntity);
         }
      } else if (stack != null) {
         stack.applyEntityLivingBaseByEntityLivingBase(1, livingEntity);
      }
   }

   @Override
   public int computeCount(int count) {
      return super.computeCountByIndex(count) + 50;
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.getItem() instanceof ItemGoldenBoots ? true : super.matchesItemStack(stack);
   }

   @Override
   public int computeCountByIndex(int count) {
      return 10 + 20 * (count - 1);
   }

   public DamageThornsEnchantmentManager(int count, ResourceLocation location, int count2) {
      super(count, location, count2, EnhancedOptionType.ARMOR_TORSO);
      this.withLabelText("thorns");
   }
}
