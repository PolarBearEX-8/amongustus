package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryPotionAdapter extends Potion {
   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, AdvancedMapEntry advancedMapEntry, int count) {
      livingEntity.applyFloat3(livingEntity.getScaleFactor13() + (float)(4 * (count + 1)));
      super.applyEntityLivingBase(livingEntity, advancedMapEntry, count);
   }

   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, AdvancedMapEntry advancedMapEntry, int count) {
      livingEntity.applyFloat3(livingEntity.getScaleFactor13() - (float)(4 * (count + 1)));
      super.applyEntityLivingBaseByEntityLivingBase(livingEntity, advancedMapEntry, count);
   }

   public AuxiliaryPotionAdapter(int count, ResourceLocation location, boolean flag, int count2) {
      super(count, location, flag, count2);
   }
}
