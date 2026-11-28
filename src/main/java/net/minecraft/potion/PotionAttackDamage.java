package net.minecraft.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;

public class PotionAttackDamage extends Potion {
   @Override
   public double getAttributeModifierAmount(int n, AttributeModifier attributeModifier) {
      return this.id == Potion.weakness.id ? (double)(-0.5F * (float)(n + 1)) : 1.3 * (double)(n + 1);
   }

   public PotionAttackDamage(int n, ResourceLocation location, boolean flag, int n2) {
      super(n, location, flag, n2);
   }
}
