package client.gui;

import client.util.CurveEvaluator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final ResourceLocation resourceLocation5;
   public final int count6;
   public final int count7;
   public boolean enabled5;

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public void setEnabled5(boolean enabled) {
      this.enabled5 = enabled;
   }

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         mc.getTextureManager().applyResourceLocation3(CurveEvaluator.getResourceLocation5());
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.enabled = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         int count3 = 0;
         if (!this.enabled3) {
            count3 += this.count4 * 2;
         } else if (this.enabled5) {
            count3 += this.count4 * 1;
         } else if (this.enabled) {
            count3 += this.count4 * 3;
         }

         this.processAction(this.count, this.count3, count3, 219, this.count4, this.count5);
         if (!CurveEvaluator.getResourceLocation5().equals(this.resourceLocation5)) {
            mc.getTextureManager().applyResourceLocation3(this.resourceLocation5);
         }

         this.processAction(this.count + 2, this.count3 + 2, this.count6, this.count7, 18, 18);
      }
   }

   public ResourceLocationAuxiliaryTexturesGuiIc(int count, int count2, int count3, ResourceLocation location, int count4, int count5) {
      super(count, count2, count3, 22, 22, "");
      this.resourceLocation5 = location;
      this.count6 = count4;
      this.count7 = count5;
   }
}
