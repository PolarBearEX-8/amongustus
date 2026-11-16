package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ExtendedPotionAdapter extends Potion {
   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, AdvancedMapEntry advancedMapEntry, int count) {
      super.applyEntityLivingBaseByEntityLivingBase(livingEntity, advancedMapEntry, count);
      if (livingEntity.getFloat2() > livingEntity.getFloat()) {
         livingEntity.applyFloat(livingEntity.getFloat());
      }
   }

   public ExtendedPotionAdapter(int count, ResourceLocation location, boolean flag, int count2) {
      super(count, location, flag, count2);
   }
}
