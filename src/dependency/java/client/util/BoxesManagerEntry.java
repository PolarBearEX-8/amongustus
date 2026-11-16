package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;

public class BoxesManagerEntry extends FloatEntryDispatcher {
   public BoxesManager boxesManager19;
   public BoxesManager boxesManager18;
   public BoxesManager boxesManager20;
   public BoxesManager boxesManager21;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      super.applyEntity(entity, posX, posX2, posX3, posX4, posY, posZ, flag);
      GlStateManager.pushMatrix();
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager19.applyFloat3(posZ);
         this.boxesManager18.applyFloat3(posZ);
         this.boxesManager20.applyFloat3(posZ);
         this.boxesManager21.applyFloat3(posZ);
      } else {
         if (entity.isSprinting()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.boxesManager19.applyFloat3(posZ);
         this.boxesManager18.applyFloat3(posZ);
         this.boxesManager20.applyFloat3(posZ);
         this.boxesManager21.applyFloat3(posZ);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      if (entity instanceof EntityArmorstand) {
         EntityArmorstand entityArmorstand = (EntityArmorstand)entity;
         this.boxesManager.enabled3 = entityArmorstand.isActive27();
         this.boxesManager2.enabled3 = entityArmorstand.isActive27();
         this.boxesManager21.enabled3 = !entityArmorstand.isActive23();
         this.boxesManager6.applyFloat(1.9F, 12.0F, 0.0F);
         this.boxesManager5.applyFloat(-1.9F, 12.0F, 0.0F);
         this.boxesManager19.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor3();
         this.boxesManager19.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor();
         this.boxesManager19.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor2();
         this.boxesManager18.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor3();
         this.boxesManager18.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor();
         this.boxesManager18.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor2();
         this.boxesManager20.factor = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor3();
         this.boxesManager20.factor9 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor();
         this.boxesManager20.factor8 = (float) (Math.PI / 180.0) * entityArmorstand.getManagedFloatEntry8().getFactor2();
         entityArmorstand.getManagedFloatEntry10().getFactor3();
         entityArmorstand.getManagedFloatEntry5().getFactor3();
         entityArmorstand.getManagedFloatEntry10().getFactor();
         entityArmorstand.getManagedFloatEntry5().getFactor();
         entityArmorstand.getManagedFloatEntry10().getFactor2();
         entityArmorstand.getManagedFloatEntry5().getFactor2();
         this.boxesManager21.factor = 0.0F;
         this.boxesManager21.factor9 = (float) (Math.PI / 180.0) * -entity.rotationYaw;
         this.boxesManager21.factor8 = 0.0F;
      }
   }

   @Override
   public void applyFloat(float factor) {
      boolean enabled = this.boxesManager2.enabled3;
      this.boxesManager2.enabled3 = true;
      super.applyFloat(factor);
      this.boxesManager2.enabled3 = enabled;
   }

   public BoxesManagerEntry() {
      this(0.0F);
   }

   public BoxesManagerEntry(float factor) {
      super(factor, 64, 64);
      this.boxesManager3 = new BoxesManager(this, 0, 0);
      this.boxesManager3.applyFloatByFloat(-1.0F, -7.0F, -1.0F, 2, 7, 2, factor);
      this.boxesManager3.applyFloat(0.0F, 0.0F, 0.0F);
      this.boxesManager7 = new BoxesManager(this, 0, 26);
      this.boxesManager7.applyFloatByFloat(-6.0F, 0.0F, -1.5F, 12, 3, 3, factor);
      this.boxesManager7.applyFloat(0.0F, 0.0F, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 24, 0);
      this.boxesManager2.applyFloatByFloat(-2.0F, -2.0F, -1.0F, 2, 12, 2, factor);
      this.boxesManager2.applyFloat(-5.0F, 2.0F, 0.0F);
      this.boxesManager = new BoxesManager(this, 32, 16);
      this.boxesManager.enabled = true;
      this.boxesManager.applyFloatByFloat(0.0F, -2.0F, -1.0F, 2, 12, 2, factor);
      this.boxesManager.applyFloat(5.0F, 2.0F, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 8, 0);
      this.boxesManager5.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 11, 2, factor);
      this.boxesManager5.applyFloat(-1.9F, 12.0F, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 40, 16);
      this.boxesManager6.enabled = true;
      this.boxesManager6.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 11, 2, factor);
      this.boxesManager6.applyFloat(1.9F, 12.0F, 0.0F);
      this.boxesManager19 = new BoxesManager(this, 16, 0);
      this.boxesManager19.applyFloatByFloat(-3.0F, 3.0F, -1.0F, 2, 7, 2, factor);
      this.boxesManager19.applyFloat(0.0F, 0.0F, 0.0F);
      this.boxesManager19.enabled3 = true;
      this.boxesManager18 = new BoxesManager(this, 48, 16);
      this.boxesManager18.applyFloatByFloat(1.0F, 3.0F, -1.0F, 2, 7, 2, factor);
      this.boxesManager18.applyFloat(0.0F, 0.0F, 0.0F);
      this.boxesManager20 = new BoxesManager(this, 0, 48);
      this.boxesManager20.applyFloatByFloat(-4.0F, 10.0F, -1.0F, 8, 2, 2, factor);
      this.boxesManager20.applyFloat(0.0F, 0.0F, 0.0F);
      this.boxesManager21 = new BoxesManager(this, 0, 32);
      this.boxesManager21.applyFloatByFloat(-6.0F, 11.0F, -6.0F, 12, 1, 12, factor);
      this.boxesManager21.applyFloat(0.0F, 12.0F, 0.0F);
   }
}
