package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class MOVINGPANNINGEnumAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final MOVINGPANNINGEnum mOVINGPANNINGEnum;
   public final DiagonalCalculator diagonalCalculator;

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (DiagonalCalculator.resolveMOVINGPANNINGEnum(this.diagonalCalculator) == this.mOVINGPANNINGEnum) {
         processAction(this.count - 2, this.count3 - 2, this.count + this.count4 + 2, this.count3 + this.count5 + 2, -16744704);
      }

      super.applyMinecraftByMinecraft(mc, count, count2);
      GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
      mc.getTextureManager().applyResourceLocation3(DiagonalCalculator.getResourceLocation5());
      this.processAction(this.count + 2, this.count3 + 2, this.mOVINGPANNINGEnum.overlayU, this.mOVINGPANNINGEnum.overlayV, 16, 16);
   }

   public MOVINGPANNINGEnumAuxiliaryTexturesGuiIc(DiagonalCalculator diagonalCalculator2, int count, int count2, int count3, MOVINGPANNINGEnum mOVINGPANNINGEnum2) {
      super(count, count2, count3, 20, 20, "");
      this.diagonalCalculator = diagonalCalculator2;
      this.mOVINGPANNINGEnum = mOVINGPANNINGEnum2;
   }
}
