package client.gui;

import client.setting.MovementKeybindProperty;
import client.util.PreEndHandManager;
import client.util.SplineDispatcher;
import net.minecraft.util.StatCollector;

public class MovementKeybindPropeAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public MovementKeybindProperty movementKeybindProperty = null;

   public MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty movementKeybindProperty2, int count, int count2, int count3, int count4) {
      super(movementKeybindProperty2.ordinal(), count, count2, count3, count4, resolveText(movementKeybindProperty2));
      this.movementKeybindProperty = movementKeybindProperty2;
   }

   public MovementKeybindProperty getMovementKeybindProperty() {
      return this.movementKeybindProperty;
   }

   public void renderElement() {
      this.labelText = resolveText(this.movementKeybindProperty);
   }

   private static String resolveText(MovementKeybindProperty movementKeybindProperty2) {
      String text = StatCollector.getText(movementKeybindProperty2.getLabelText2(), new Object[0]) + ": ";
      switch (MovementKeybindPropeAuxiMovementKeybindPropeSw.countArray[movementKeybindProperty2.ordinal()]) {
         case 1:
            return text + SplineDispatcher.resolveText(PreEndHandManager.count5);
         case 2:
            return text + SplineDispatcher.resolveText(PreEndHandManager.enabled15);
         case 3:
            return text + SplineDispatcher.resolveText(PreEndHandManager.enabled37);
         case 4:
            return text + SplineDispatcher.resolveTextByFloat(PreEndHandManager.factor31);
         case 5:
            return text + SplineDispatcher.resolveTextByFloat(PreEndHandManager.factor5);
         case 6:
            return text + SplineDispatcher.resolveText(PreEndHandManager.factor20);
         case 7:
            return text + SplineDispatcher.resolveText(PreEndHandManager.enabled30);
         case 8:
            return text + PreEndHandManager.managerAdapter2.getTextAsText();
         case 9:
            return text + PreEndHandManager.managerAdapter8.getTextAsText();
         case 10:
            return text + SplineDispatcher.resolveText(PreEndHandManager.enabled17);
         case 11:
            return text + SplineDispatcher.resolveText(PreEndHandManager.enabled10);
         default:
            return text + PreEndHandManager.resolveText(movementKeybindProperty2);
      }
   }
}
