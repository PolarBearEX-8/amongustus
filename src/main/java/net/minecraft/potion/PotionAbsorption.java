package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;

public class PotionAbsorption extends Potion {
   public PotionAbsorption(int n, ResourceLocation location, boolean flag, int n2) {
      super(n, location, flag, n2);
   }

   @Override
   public void applyAttributesModifiersToEntity(EntityLivingBase entity, BaseAttributeMap baseAttributeMap, int n) {
      entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (float)(4 * (n + 1)));
      super.applyAttributesModifiersToEntity(entity, baseAttributeMap, n);
   }

   @Override
   public void removeAttributesModifiersFromEntity(EntityLivingBase entity, BaseAttributeMap baseAttributeMap, int n) {
      entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (float)(4 * (n + 1)));
      super.removeAttributesModifiersFromEntity(entity, baseAttributeMap, n);
   }
}
