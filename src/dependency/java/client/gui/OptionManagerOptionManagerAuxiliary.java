package client.gui;

import client.setting.OptionManager;
import client.util.SplineDirector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class OptionManagerOptionManagerAuxiliary extends OptionManagerAuxiliaryTexturesGuiIc {
   public float factor2 = 1.0F;
   public boolean enabled5;
   public OptionManager optionManager2 = null;

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         if (this.enabled5 && !GuiScreen.isActiveAsFlag()) {
            this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
            this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
            this.optionManager2.applyFloat(this.factor2);
            this.factor2 = this.optionManager2.getFactor();
            this.labelText = SplineDirector.resolveText(this.optionManager2, this.count4);
         }

         mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)), this.count3, 0, 66, 4, 20);
         this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)) + 4, this.count3, 196, 66, 4, 20);
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void renderElement() {
      this.factor2 = this.optionManager2.getFactor();
   }

   public OptionManagerOptionManagerAuxiliary(int count, int count2, int count3, int count4, int count5, OptionManager optionManager, String text) {
      super(count, count2, count3, count4, count5, optionManager, text);
      this.optionManager2 = optionManager;
      this.factor2 = optionManager.getFactor();
      this.labelText = SplineDirector.resolveText(optionManager, this.count4);
   }

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
         this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
         this.optionManager2.applyFloat(this.factor2);
         this.labelText = SplineDirector.resolveText(this.optionManager2, this.count4);
         this.enabled5 = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int computeCount(boolean flag) {
      return 0;
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.enabled5 = false;
   }
}
