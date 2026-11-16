package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class MinecraftAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public float factor2;
   public boolean enabled5;
   public final String labelText2;
   public final int count6;

   public void applyIndex(int count) {
      this.factor2 = (float)count / (float)this.count6;
      this.labelText = StatCollector.getText(this.labelText2, new Object[]{this.getCount()});
   }

   @Override
   public boolean matchesMinecraft(Minecraft mc, int count, int count2) {
      if (super.matchesMinecraft(mc, count, count2)) {
         this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
         this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
         this.labelText = StatCollector.getText(this.labelText2, new Object[]{this.getCount()});
         this.enabled5 = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyIndex(int count, int count2) {
      this.enabled5 = false;
   }

   @Override
   public int computeCount(boolean flag) {
      return 0;
   }

   public int getCount() {
      return (int)(this.factor2 * (float)this.count6);
   }

   @Override
   public void applyMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         if (this.enabled5) {
            this.factor2 = (float)(count - (this.count + 4)) / (float)(this.count4 - 8);
            this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, 1.0F);
            this.enabled5 = true;
            this.labelText = StatCollector.getText(this.labelText2, new Object[]{this.getCount()});
         }

         mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         if (this.enabled3) {
            this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)), this.count3, 0, 66, 4, 20);
            this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)) + 4, this.count3, 196, 66, 4, 20);
         } else {
            this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)), this.count3, 0, 46, 4, 20);
            this.processAction(this.count + (int)(this.factor2 * (float)(this.count4 - 8)) + 4, this.count3, 196, 46, 4, 20);
         }
      }
   }

   public MinecraftAuxiliaryTexturesGuiIc(int count, int count2, int count3, int count4, int count5, String text, int count7, int count8) {
      super(count, count2, count3, count4, count5, text);
      this.labelText2 = text;
      this.count6 = count8;
      this.applyIndex(count7);
   }
}
