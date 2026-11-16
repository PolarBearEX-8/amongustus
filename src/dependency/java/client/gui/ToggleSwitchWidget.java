package client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class ToggleSwitchWidget extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final boolean enabled5;

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         boolean flag = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         mc.getTextureManager().applyResourceLocation3(SpecialGuiScreenAdapter.getResourceLocation5());
         int byteValue = 0;
         int shortValue = 192;
         if (flag) {
            byteValue += 23;
         }

         if (!this.enabled5) {
            shortValue += 13;
         }

         this.processAction(this.count, this.count3, byteValue, shortValue, 23, 13);
      }
   }

   public ToggleSwitchWidget(int count, int count2, int count3, boolean flag) {
      super(count, count2, count3, 23, 13, "");
      this.enabled5 = flag;
   }
}
