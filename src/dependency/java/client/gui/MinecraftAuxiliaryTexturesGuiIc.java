package client.gui;

import client.util.PrimaryLoggerEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class MinecraftAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final boolean enabled5;

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         mc.getTextureManager().applyResourceLocation3(PrimaryLoggerEntry.getResourceLocation5());
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         int count3 = 0;
         int count4 = 176;
         if (!this.enabled3) {
            count4 += this.count4 * 2;
         } else if (flag) {
            count4 += this.count4;
         }

         if (!this.enabled5) {
            count3 += this.count5;
         }

         this.processAction(this.count, this.count3, count4, count3, this.count4, this.count5);
      }
   }

   public MinecraftAuxiliaryTexturesGuiIc(int count, int count2, int count3, boolean flag) {
      super(count, count2, count3, 12, 19, "");
      this.enabled5 = flag;
   }
}
