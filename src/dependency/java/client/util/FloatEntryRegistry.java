package client.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class FloatEntryRegistry extends PassiveFloatEntry {
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager4;
   public BoxesManager boxesManager7;
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager5;
   public BoxesManager boxesManager6;
   public int count6;
   public int count5;
   public boolean enabled5;
   public boolean enabled6;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      GlStateManager.pushMatrix();
      if (this.enabled2) {
         float factor = 2.0F;
         GlStateManager.translate3f3(1.5F / factor, 1.5F / factor, 1.5F / factor);
         GlStateManager.translate(0.0F, 16.0F * posZ, 0.0F);
         this.boxesManager3.applyFloat3(posZ);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(1.0F / factor, 1.0F / factor, 1.0F / factor);
         GlStateManager.translate(0.0F, 24.0F * posZ, 0.0F);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         if (flag) {
            this.boxesManager4.applyFloat3(posZ);
         }
      } else {
         if (entity.isSprinting()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }

         this.boxesManager3.applyFloat3(posZ);
         this.boxesManager7.applyFloat3(posZ);
         this.boxesManager2.applyFloat3(posZ);
         this.boxesManager.applyFloat3(posZ);
         this.boxesManager5.applyFloat3(posZ);
         this.boxesManager6.applyFloat3(posZ);
         if (flag) {
            this.boxesManager4.applyFloat3(posZ);
         }
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      this.boxesManager3.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager3.factor = posY / (180.0F / (float)Math.PI);
      this.boxesManager2.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 2.0F * posX2 * 0.5F;
      this.boxesManager.factor = MathHelper.cos(posX * 0.6662F) * 2.0F * posX2 * 0.5F;
      this.boxesManager2.factor8 = 0.0F;
      this.boxesManager.factor8 = 0.0F;
      this.boxesManager5.factor = MathHelper.cos(posX * 0.6662F) * 1.4F * posX2;
      this.boxesManager6.factor = MathHelper.cos(posX * 0.6662F + (float) Math.PI) * 1.4F * posX2;
      this.boxesManager5.factor9 = 0.0F;
      this.boxesManager6.factor9 = 0.0F;
      if (this.enabled) {
         this.boxesManager2.factor += (float) (-Math.PI / 5);
         this.boxesManager.factor += (float) (-Math.PI / 5);
         this.boxesManager5.factor = (float) (-Math.PI * 2.0 / 5.0);
         this.boxesManager6.factor = (float) (-Math.PI * 2.0 / 5.0);
         this.boxesManager5.factor9 = (float) (Math.PI / 10);
         this.boxesManager6.factor9 = (float) (-Math.PI / 10);
      }

      if (this.count6 != 0) {
         this.boxesManager.factor = this.boxesManager.factor * 0.5F - (float) (Math.PI / 10) * (float)this.count6;
      }

      this.boxesManager2.factor9 = 0.0F;
      this.boxesManager2.factor8 = 0.0F;
      switch (this.count5) {
         case 0:
         case 2:
         default:
            break;
         case 1:
            this.boxesManager2.factor = this.boxesManager2.factor * 0.5F - (float) (Math.PI / 10) * (float)this.count5;
            break;
         case 3:
            this.boxesManager2.factor = this.boxesManager2.factor * 0.5F - (float) (Math.PI / 10) * (float)this.count5;
            this.boxesManager2.factor9 = (float) (-Math.PI / 6);
      }

      this.boxesManager.factor9 = 0.0F;
      if (this.factor > -9990.0F) {
         float factor = this.factor;
         this.boxesManager7.factor9 = MathHelper.abs(MathHelper.sin(factor) * (float) Math.PI * 2.0F) * 0.2F;
         this.boxesManager2.factor10 = MathHelper.abs(this.boxesManager7.factor9) * 5.0F;
         this.boxesManager2.factor5 = -MathHelper.cos(this.boxesManager7.factor9) * 5.0F;
         this.boxesManager.factor10 = -MathHelper.abs(this.boxesManager7.factor9) * 5.0F;
         this.boxesManager.factor5 = MathHelper.cos(this.boxesManager7.factor9) * 5.0F;
         this.boxesManager2.factor9 = this.boxesManager2.factor9 + this.boxesManager7.factor9;
         this.boxesManager.factor9 = this.boxesManager.factor9 + this.boxesManager7.factor9;
         this.boxesManager.factor = this.boxesManager.factor + this.boxesManager7.factor9;
         float factor2 = 1.0F - this.factor;
         float factor3 = factor2 * factor2;
         float factor4 = factor3 * factor3;
         float factor5 = MathHelper.abs((1.0F - factor4) * (float) Math.PI);
         float factor6 = MathHelper.abs(this.factor * (float) Math.PI) * -(this.boxesManager3.factor - 0.7F) * 0.75F;
         this.boxesManager2.factor = (float)((double)this.boxesManager2.factor - ((double)factor5 * 1.2 + (double)factor6));
         this.boxesManager2.factor9 = this.boxesManager2.factor9 + this.boxesManager7.factor9 * 2.0F;
         this.boxesManager2.factor8 = this.boxesManager2.factor8 + MathHelper.abs(this.factor * (float) Math.PI) * -0.4F;
      }

      if (this.enabled5) {
         this.boxesManager7.factor = 0.5F;
         this.boxesManager2.factor += 0.4F;
         this.boxesManager.factor += 0.4F;
         this.boxesManager5.factor10 = 4.0F;
         this.boxesManager6.factor10 = 4.0F;
         this.boxesManager5.factor2 = 9.0F;
         this.boxesManager6.factor2 = 9.0F;
         this.boxesManager3.factor2 = 1.0F;
      } else {
         this.boxesManager7.factor = 0.0F;
         this.boxesManager5.factor10 = 0.1F;
         this.boxesManager6.factor10 = 0.1F;
         this.boxesManager5.factor2 = 12.0F;
         this.boxesManager6.factor2 = 12.0F;
         this.boxesManager3.factor2 = 0.0F;
      }

      this.boxesManager2.factor8 = this.boxesManager2.factor8 + MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F;
      this.boxesManager.factor8 = this.boxesManager.factor8 - (MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F);
      this.boxesManager2.factor = this.boxesManager2.factor + MathHelper.abs(posX3 * 0.067F) * 0.05F;
      this.boxesManager.factor = this.boxesManager.factor - MathHelper.abs(posX3 * 0.067F) * 0.05F;
      if (this.enabled6) {
         float factor7 = 0.0F;
         float factor8 = 0.0F;
         this.boxesManager2.factor8 = 0.0F;
         this.boxesManager.factor8 = 0.0F;
         this.boxesManager2.factor9 = -(0.1F - factor7 * 0.6F) + this.boxesManager3.factor9;
         this.boxesManager.factor9 = 0.1F - factor7 * 0.6F + this.boxesManager3.factor9 + 0.4F;
         this.boxesManager2.factor = (float) (-Math.PI / 2) + this.boxesManager3.factor;
         this.boxesManager.factor = (float) (-Math.PI / 2) + this.boxesManager3.factor;
         this.boxesManager2.factor -= factor7 * 1.2F - factor8 * 0.4F;
         this.boxesManager.factor -= factor7 * 1.2F - factor8 * 0.4F;
         this.boxesManager2.factor8 = this.boxesManager2.factor8 + MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F;
         this.boxesManager.factor8 = this.boxesManager.factor8 - (MathHelper.cos(posX3 * 0.09F) * 0.05F + 0.05F);
         this.boxesManager2.factor = this.boxesManager2.factor + MathHelper.abs(posX3 * 0.067F) * 0.05F;
         this.boxesManager.factor = this.boxesManager.factor - MathHelper.abs(posX3 * 0.067F) * 0.05F;
      }

      applyBoxesManager(this.boxesManager3, this.boxesManager4);
   }

   @Override
   public void applyPassiveFloatEntry(PassiveFloatEntry passiveFloatEntry) {
      super.applyPassiveFloatEntry(passiveFloatEntry);
      if (passiveFloatEntry instanceof FloatEntryRegistry) {
         FloatEntryRegistry floatEntryRegistry = (FloatEntryRegistry)passiveFloatEntry;
         this.count6 = floatEntryRegistry.count6;
         this.count5 = floatEntryRegistry.count5;
         this.enabled5 = floatEntryRegistry.enabled5;
         this.enabled6 = floatEntryRegistry.enabled6;
      }
   }

   public void applyFloat(float factor) {
      this.boxesManager2.applyFloatByFloat(factor);
   }

   public void setEnabled(boolean enabled) {
      this.boxesManager3.enabled3 = enabled;
      this.boxesManager4.enabled3 = enabled;
      this.boxesManager7.enabled3 = enabled;
      this.boxesManager2.enabled3 = enabled;
      this.boxesManager.enabled3 = enabled;
      this.boxesManager5.enabled3 = enabled;
      this.boxesManager6.enabled3 = enabled;
   }

   public FloatEntryRegistry(float factor) {
      this(factor, 0.0F, 64, 32);
   }

   public FloatEntryRegistry(float factor, float factor2, int count, int count2) {
      this.count2 = count;
      this.count = count2;
      this.boxesManager3 = new BoxesManager(this, 0, 0);
      this.boxesManager3.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor);
      this.boxesManager3.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager4 = new BoxesManager(this, 32, 0);
      this.boxesManager4.applyFloatByFloat(-4.0F, -8.0F, -4.0F, 8, 8, 8, factor + 0.5F);
      this.boxesManager4.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager7 = new BoxesManager(this, 16, 16);
      this.boxesManager7.applyFloatByFloat(-4.0F, 0.0F, -2.0F, 8, 12, 4, factor);
      this.boxesManager7.applyFloat(0.0F, 0.0F + factor2, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 40, 16);
      this.boxesManager2.applyFloatByFloat(-3.0F, -2.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager2.applyFloat(-5.0F, 2.0F + factor2, 0.0F);
      this.boxesManager = new BoxesManager(this, 40, 16);
      this.boxesManager.enabled = true;
      this.boxesManager.applyFloatByFloat(-1.0F, -2.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager.applyFloat(5.0F, 2.0F + factor2, 0.0F);
      this.boxesManager5 = new BoxesManager(this, 0, 16);
      this.boxesManager5.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager5.applyFloat(-1.9F, 12.0F + factor2, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 0, 16);
      this.boxesManager6.enabled = true;
      this.boxesManager6.applyFloatByFloat(-2.0F, 0.0F, -2.0F, 4, 12, 4, factor);
      this.boxesManager6.applyFloat(1.9F, 12.0F + factor2, 0.0F);
   }

   public FloatEntryRegistry() {
      this(0.0F);
   }
}
