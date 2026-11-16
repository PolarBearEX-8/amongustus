package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class InterpolationAdapter extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public boolean enabled6 = false;

   public void setEnabled6(boolean enabled) {
      this.enabled6 = enabled;
   }

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         mc.getTextureManager().applyResourceLocation3(AuxiliaryTexturesGuiIconsPngManagerAdapter.resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         LOCKEDUNLOCKEDEnum lOCKEDUNLOCKEDEnum;
         if (this.enabled6) {
            if (!this.enabled3) {
               lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.LOCKED_DISABLED;
            } else if (flag) {
               lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.LOCKED_HOVER;
            } else {
               lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.LOCKED;
            }
         } else if (!this.enabled3) {
            lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.UNLOCKED_DISABLED;
         } else if (flag) {
            lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.UNLOCKED_HOVER;
         } else {
            lOCKEDUNLOCKEDEnum = LOCKEDUNLOCKEDEnum.UNLOCKED;
         }

         this.processAction(this.count, this.count3, lOCKEDUNLOCKEDEnum.getCount(), lOCKEDUNLOCKEDEnum.getCount2(), this.count4, this.count5);
      }
   }

   public InterpolationAdapter(int count, int count2, int count3) {
      super(count, count2, count3, 20, 20, "");
   }

   public boolean isEnabled6() {
      return this.enabled6;
   }
}
