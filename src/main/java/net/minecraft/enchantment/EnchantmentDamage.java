package net.minecraft.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDamage extends Enchantment {
   private static final String[] protectionName = new String[]{"all", "undead", "arthropods"};
   private static final int[] baseEnchantability = new int[]{1, 5, 5};
   private static final int[] levelEnchantability = new int[]{11, 8, 8};
   private static final int[] thresholdEnchantability = new int[]{20, 20, 20};
   public final int damageType;

   @Override
   public float calcDamageByCreature(int n, EnumCreatureAttribute enumCreatureAttribute) {
      return this.damageType == 0
         ? (float)n * 1.25F
         : (
            this.damageType == 1 && enumCreatureAttribute == EnumCreatureAttribute.UNDEAD
               ? (float)n * 2.5F
               : (this.damageType == 2 && enumCreatureAttribute == EnumCreatureAttribute.ARTHROPOD ? (float)n * 2.5F : 0.0F)
         );
   }

   @Override
   public String getName() {
      return "enchantment.damage." + protectionName[this.damageType];
   }

   @Override
   public boolean canApplyTogether(Enchantment enchantment) {
      return !(enchantment instanceof EnchantmentDamage);
   }

   @Override
   public boolean canApply(ItemStack stack) {
      return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
   }

   @Override
   public void onEntityDamaged(EntityLivingBase entity, Entity entity2, int n) {
      if (entity2 instanceof EntityLivingBase) {
         EntityLivingBase entity3 = (EntityLivingBase)entity2;
         if (this.damageType == 2 && entity3.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
            int n2 = 20 + entity.getRNG().nextInt(10 * n);
            entity3.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, n2, 3));
         }
      }
   }

   public EnchantmentDamage(int n, ResourceLocation location, int n2, int n3) {
      super(n, location, n2, EnumEnchantmentType.WEAPON);
      this.damageType = n3;
   }

   @Override
   public int getMaxEnchantability(int n) {
      return this.getMinEnchantability(n) + thresholdEnchantability[this.damageType];
   }

   @Override
   public int getMaxLevel() {
      return 5;
   }

   @Override
   public int getMinEnchantability(int n) {
      return baseEnchantability[this.damageType] + (n - 1) * levelEnchantability[this.damageType];
   }
}
