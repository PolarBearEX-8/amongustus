package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class InterpolationQueue extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         mc.getTextureManager().applyResourceLocation3(AuxiliaryTexturesGuiIconsPngManagerAdapter.resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         int count3 = 106;
         if (flag) {
            count3 += this.count5;
         }

         this.processAction(this.count, this.count3, 0, count3, this.count4, this.count5);
      }
   }

   public InterpolationQueue(int count, int count2, int count3) {
      super(count, count2, count3, 20, 20, "");
   }
}
