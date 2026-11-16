package client.util;

import client.setting.Setting;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FailedToLoadMaskFromManagerAdapter extends FailedToLoadMaskFromManager {
   @Override
   public void applyText(String text, BufferedImage bufferedImage) {
      ResourceLocation location = new ResourceLocation("morecosmetics/" + text);
      Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(location, new AuxiliaryLoggerEntryBridge(location, bufferedImage));
   }

   public static Setting[] getSettingArray() {
      return null;
   }
}
