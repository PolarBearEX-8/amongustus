package client.gui;

import client.util.Callback15;
import client.util.InterpolationValidator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StatCollector;

public class InterpolationValidatAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public float factor2 = 1.0F;
   public boolean enabled5;
   public String labelText2;
   public final float factor3;
   public final float factor4;
   public final InterpolationValidator interpolationValidator;
   public Callback15 callback15;

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
         if (this.factor2 < 0.0F) {
            this.factor2 = 0.0F;
         }

         if (this.factor2 > 1.0F) {
            this.factor2 = 1.0F;
         }

         this.labelText = this.getText();
         this.interpolationValidator.applyIndex(this.count2, this.getFactor());
         this.enabled5 = true;
         return true;
      } else {
         return false;
      }
   }

   public void applyFloat(float factor, boolean flag) {
      this.factor2 = (factor - this.factor3) / (this.factor4 - this.factor3);
      this.labelText = this.getText();
      if (flag) {
         this.interpolationValidator.applyIndex(this.count2, this.getFactor());
      }
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         if (this.enabled5) {
            this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
            if (this.factor2 < 0.0F) {
               this.factor2 = 0.0F;
            }

            if (this.factor2 > 1.0F) {
               this.factor2 = 1.0F;
            }

            this.labelText = this.getText();
            this.interpolationValidator.applyIndex(this.count2, this.getFactor());
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)), this.count3, 0, 66, 4, 20);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)) + 4, this.count3, 196, 66, 4, 20);
      }
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.enabled5 = false;
   }

   private String getText() {
      return this.callback15 == null
         ? StatCollector.getText(this.labelText2, new Object[0]) + ": " + this.getFactor()
         : this.callback15.resolveText(this.count2, StatCollector.getText(this.labelText2, new Object[0]), this.getFactor());
   }

   public float getFactor2() {
      return this.factor2;
   }

   @Override
   public int computeCount(boolean flag) {
      return 0;
   }

   public void applyFloat(float factor) {
      this.factor2 = factor;
      this.labelText = this.getText();
      this.interpolationValidator.applyIndex(this.count2, this.getFactor());
   }

   public float getFactor() {
      return this.factor3 + (this.factor4 - this.factor3) * this.factor2;
   }

   public InterpolationValidatAuxiliaryTexturesGuiIc(
      InterpolationValidator interpolationValidator2, int count, int count2, int count3, String text, float posX, float posY, float posZ, Callback15 callback
   ) {
      super(count, count2, count3, 150, 20, "");
      this.labelText2 = text;
      this.factor3 = posX;
      this.factor4 = posY;
      this.factor2 = (posZ - posX) / (posY - posX);
      this.callback15 = callback;
      this.interpolationValidator = interpolationValidator2;
      this.labelText = this.getText();
   }
}
