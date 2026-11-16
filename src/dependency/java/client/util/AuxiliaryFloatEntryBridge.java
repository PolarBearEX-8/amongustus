package client.util;

import client.ClientGlobals;
import client.render.ObjModelMesh;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class AuxiliaryFloatEntryBridge extends FloatEntryRegistry {
   public BoxesManager boxesManager14;
   public BoxesManager boxesManager13;
   public BoxesManager boxesManager12;
   public BoxesManager boxesManager10;
   public BoxesManager boxesManager11;
   public BoxesManager boxesManager15;
   public BoxesManager boxesManager16;
   public boolean enabled9;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      if (ClientGlobals.moduleManager.modelRenderer.checkEntity(entity)) {
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 1.5F, 0.0F);
         GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.enableBlend();
         GlStateManager.applyState14();
         ObjModelMesh.renderElement();
         GlStateManager.popMatrix();
      } else {
         super.applyEntity(entity, posX, posX2, posX3, posX4, posY, posZ, flag);
         if (flag) {
            GlStateManager.pushMatrix();
            if (this.enabled2) {
               float factor = 2.0F;
               GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
               GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
               this.boxesManager12.applyFloat3(posZ);
               this.boxesManager10.applyFloat3(posZ);
               this.boxesManager14.applyFloat3(posZ);
               this.boxesManager13.applyFloat3(posZ);
               this.boxesManager11.applyFloat3(posZ);
            } else {
               if (entity.isSprinting()) {
                  GlStateManager.translate(0.0F, 0.2F, 0.0F);
               }

               this.boxesManager12.applyFloat3(posZ);
               this.boxesManager10.applyFloat3(posZ);
               this.boxesManager14.applyFloat3(posZ);
               this.boxesManager13.applyFloat3(posZ);
               this.boxesManager11.applyFloat3(posZ);
            }

            GlStateManager.popMatrix();
         }
      }
   }

   public void applyFloatByFloat(float factor) {
      applyBoxesManager(this.boxesManager3, this.boxesManager16);
      this.boxesManager16.factor5 = 0.0F;
      this.boxesManager16.factor2 = 0.0F;
      this.boxesManager16.applyFloat3(factor);
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      applyBoxesManager(this.boxesManager6, this.boxesManager12);
      applyBoxesManager(this.boxesManager5, this.boxesManager10);
      applyBoxesManager(this.boxesManager, this.boxesManager14);
      applyBoxesManager(this.boxesManager2, this.boxesManager13);
      applyBoxesManager(this.boxesManager7, this.boxesManager11);
   }

   public void updateState2() {
      this.boxesManager.applyFloat3(0.0625F);
      this.boxesManager14.applyFloat3(0.0625F);
   }

   public void applyFloat2(float factor) {
      this.boxesManager15.applyFloat3(factor);
   }

   public void updateState() {
      this.boxesManager2.applyFloat3(0.0625F);
      this.boxesManager13.applyFloat3(0.0625F);
   }

   @Override
   public void setEnabled(boolean enabled) {
      super.setEnabled(enabled);
      this.boxesManager14.enabled3 = enabled;
      this.boxesManager13.enabled3 = enabled;
      this.boxesManager12.enabled3 = enabled;
      this.boxesManager10.enabled3 = enabled;
      this.boxesManager11.enabled3 = enabled;
      this.boxesManager15.enabled3 = enabled;
      this.boxesManager16.enabled3 = enabled;
   }

   @Override
   public void applyFloat(float factor) {
      if (this.enabled9) {
         this.boxesManager2.factor5++;
         this.boxesManager2.applyFloatByFloat(factor);
         this.boxesManager2.factor5--;
      } else {
         this.boxesManager2.applyFloatByFloat(factor);
      }
   }

   public AuxiliaryFloatEntryBridge(float factor, boolean flag) {
      super(factor, 0.0F, 64, 64);
      this.enabled9 = flag;
      this.boxesManager16 = new BoxesManager(this, 24, 0);
      this.boxesManager16.applyFloatByFloat(-3.0F, -6.0F, -1.0F, 6, 6, 1, factor);
      this.boxesManager15 = new BoxesManager(this, 0, 0);
      this.boxesManager15.resolveBoxesManager(64, 32);
      this.boxesManager15.applyFloatByFloat(-5.0F, 0.0F, -1.0F, 10, 16, 1, factor);
      if (flag) {
         this.boxesManager = new BoxesManager(this, 32, 48);
         this.boxesManager.applyFloatByFloat(-1.0F, -2.0F, -2.0F, 3, 12, 4, factor);
         this.boxesManager.applyFloat(5.0F, 2.5F, 0.0F);
         this.boxesManager2 = new BoxesManager(this, 40, 16);
         this.boxesManager2.applyFloatByFloat(-2.0F, -2.0F, -2.0F, 3, 12, 4, factor);
         this.boxesManager2.applyFloat(-5.0F, 2.5F, 0.0F);
         this.boxesManager14 = new BoxesManager(this, 48, 48);
         this.boxesManager14.applyFloatByFloat(-1.0F, -2.0F, -2.0F, 3, 12, 4, factor + 0.25F);
         this.boxesManager14.applyFloat(5.0F, 2.5F, 0.0F);
         this.boxesManager13 = new BoxesManager(this, 40, 32);
         this.boxesManager13.applyFloatByFloat(-2.0F, -2.0F, -2.0F, 3, 12, 4, factor + 0.25F);
         this.boxesManager13.applyFloat(-5.0F, 2.5F, 10.0F);
      } else {
         this.boxesManager = new BoxesManager(this, 32, 48);
         this.boxesManager.applyFloatByFloat(-1.0F, -2.0F, -2.0F, 4, 12, 4, factor);
         this.boxesManager.applyFloat(5.0F, 2.0F, 0.0F);
         this.boxesManager14 = new BoxesManager(this, 48, 48);
         this.boxesManager14.applyFloatByFloat(-1.0F, -2.0F, -2.0F, 4, 12, 4, factor + 0.25F);
         this.boxesManager14.applyFloat(5.0F, 2.0F, 0.0F);
         this.boxesManager13 = new BoxesManager(this, 40, 32);
         this.boxesManager13.applyFloatByFloat(-3.0F, -2.0F, -2.0F, 4, 12, 4, factor + 0.25F);
         this.boxesManager13.applyFloat(-5.0F, 2.0F, 10.0F);
      }

      this.boxesManager6 = new BoxesManager(this, 16, 48);
      this.boxesManager6.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager6.applyFloat(1.9F, 12.0F, 0.0F);
      this.boxesManager12 = new BoxesManager(this, 0, 48);
      this.boxesManager12.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor + 0.25F);
      this.boxesManager12.applyFloat(1.9F, 12.0F, 0.0F);
      this.boxesManager10 = new BoxesManager(this, 0, 32);
      this.boxesManager10.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor + 0.25F);
      this.boxesManager10.applyFloat(-1.9F, 12.0F, 0.0F);
      this.boxesManager11 = new BoxesManager(this, 16, 32);
      this.boxesManager11.applyFloatByFloat(-4.0F, 0.0F, -2.0F, 8, 12, 4, factor + 0.25F);
      this.boxesManager11.applyFloat(0.0F, 0.0F, 0.0F);
   }
}
