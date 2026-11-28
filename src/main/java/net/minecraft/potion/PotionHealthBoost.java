package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;

public class PotionHealthBoost extends Potion {
   public PotionHealthBoost(int n, ResourceLocation location, boolean flag, int n2) {
      super(n, location, flag, n2);
   }

   @Override
   public void removeAttributesModifiersFromEntity(EntityLivingBase entity, BaseAttributeMap baseAttributeMap, int n) {
      super.removeAttributesModifiersFromEntity(entity, baseAttributeMap, n);
      if (entity.getCurrentHealth() > entity.getMaxHealth()) {
         entity.setHealth(entity.getMaxHealth());
      }
   }
}
