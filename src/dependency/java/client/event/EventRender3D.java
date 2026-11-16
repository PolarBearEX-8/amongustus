package client.event;

import client.setting.AuxiliaryOptionType;
import client.util.AltIntArrayEntry;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender3D extends AbstractEvent {
   public ScaledResolution scaledResolution;
   public AuxiliaryOptionType auxiliaryOptionType;
   public float factor;
   public static final int EVENT_ID = 32;

   public static void onRender3D(EventRender3D event, float factor2, ScaledResolution scaledResolution2, AuxiliaryOptionType auxiliaryOptionType2) {
      event.applyScaledResolution(factor2, scaledResolution2, auxiliaryOptionType2);
   }

   public void applyRenderListenerByRenderListener(short shortValue, char character, RenderListener renderListener, int count) {
      switch (AltIntArrayEntry.countArray[this.auxiliaryOptionType.ordinal()]) {
         case 1:
            float factor2 = this.factor;
            renderListener.onRender3D(factor2);
            break;
         case 2:
            ScaledResolution scaledResolution2 = this.scaledResolution;
            float factor3 = this.factor;
            renderListener.onRenderOverlay(factor3, scaledResolution2);
            break;
         case 3:
            ScaledResolution scaledResolution3 = this.scaledResolution;
            float factor4 = this.factor;
            renderListener.onRender2D(factor4, scaledResolution3);
      }
   }

   public float getFactor() {
      return this.factor;
   }

   public void call(Object entry) {
      RenderListener renderListener = (RenderListener)entry;
      this.applyRenderListenerByRenderListener((short)0, (char)4786, renderListener, -583568686);
   }

   private void applyScaledResolution(float factor2, ScaledResolution scaledResolution2, AuxiliaryOptionType auxiliaryOptionType2) {
      this.factor = factor2;
      this.auxiliaryOptionType = auxiliaryOptionType2;
      this.scaledResolution = scaledResolution2;
   }
}
