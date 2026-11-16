package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;

public class FloatEntryDispatcher extends FloatEntryRegistry {
   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      if (entity instanceof EntityArmorstand) {
         EntityArmorstand entityArmorstand = (EntityArmorstand)entity;
         this.boxesManager3.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry().getFactor3();
         this.boxesManager3.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry().getFactor();
         this.boxesManager3.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry().getFactor2();
         this.boxesManager3.applyFloat(0.0F, 1.0F, 0.0F);
         this.boxesManager7.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor3();
         this.boxesManager7.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor();
         this.boxesManager7.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor2();
         this.boxesManager.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry7().getFactor3();
         this.boxesManager.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry7().getFactor();
         this.boxesManager.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry7().getFactor2();
         this.boxesManager2.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry6().getFactor3();
         this.boxesManager2.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry6().getFactor();
         this.boxesManager2.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry6().getFactor2();
         this.boxesManager6.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry10().getFactor3();
         this.boxesManager6.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry10().getFactor();
         this.boxesManager6.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry10().getFactor2();
         this.boxesManager6.applyFloat(1.9F, 11.0F, 0.0F);
         this.boxesManager5.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry5().getFactor3();
         this.boxesManager5.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry5().getFactor();
         this.boxesManager5.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry5().getFactor2();
         this.boxesManager5.applyFloat(-1.9F, 11.0F, 0.0F);
         applyBoxesManager(this.boxesManager3, this.boxesManager4);
      }
   }

   public FloatEntryDispatcher(float factor, int count, int count2) {
      super(factor, 0.0F, count, count2);
   }

   public FloatEntryDispatcher() {
      this(0.0F);
   }

   public FloatEntryDispatcher(float factor) {
      this(factor, 64, 32);
   }
}
