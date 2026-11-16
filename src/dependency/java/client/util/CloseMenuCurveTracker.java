package client.util;

import client.gui.ExtendedTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CloseMenuCurveTracker implements CurveTracker {
   @Override
   public void applyFloat(float factor, int count) {
      Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(ExtendedTexturesGuiIconsPngManagerAdapter.resourceLocation4);
      TexturesGuiIconsPngManager.applyIndex(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public CloseMenuCurveTracker(SplineConverterCurveTracker splineConverterCurveTracker) {
      this();
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText("Close menu");
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   private CloseMenuCurveTracker() {
   }

   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
      splineConverter.updateState();
   }
}
