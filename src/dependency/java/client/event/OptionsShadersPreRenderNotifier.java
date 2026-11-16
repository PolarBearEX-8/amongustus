package client.event;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.MovementKeybindPropeAuxiliaryTexturesGuiIc;
import client.gui.OptifineTexturesAuxiliaryTexturesGuiIc;
import client.setting.MovementKeybindProperty;
import client.util.TooltipManager;
import java.awt.Rectangle;
import net.minecraft.client.gui.GuiScreen;

public class OptionsShadersPreRenderNotifier implements PreRenderNotifier {
   @Override
   public Rectangle resolveRectangle(GuiScreen screen, int count, int count2) {
      int count3 = screen.width - 450;
      int byteValue = 35;
      if (count3 < 10) {
         count3 = 10;
      }

      if (count2 <= byteValue + 94) {
         byteValue += 100;
      }

      int count4 = count3 + 150 + 150;
      int count5 = byteValue + 84 + 10;
      return new Rectangle(count3, byteValue, count4 - count3, count5 - byteValue);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   private String[] resolveTextArray(MovementKeybindProperty movementKeybindProperty) {
      return TooltipManager.resolveTextArray(movementKeybindProperty.getLabelText2());
   }

   @Override
   public String[] resolveTextArray(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, int count) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof OptifineTexturesAuxiliaryTexturesGuiIc) {
         return TooltipManager.resolveTextArray("of.options.shaders.DOWNLOAD");
      } else if (!(auxiliaryTexturesGuiIconsPngManagerAdapter instanceof MovementKeybindPropeAuxiliaryTexturesGuiIc)) {
         return null;
      } else {
         MovementKeybindProperty movementKeybindProperty = ((MovementKeybindPropeAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter).getMovementKeybindProperty();
         return this.resolveTextArray(movementKeybindProperty);
      }
   }
}
