package client.util;

import client.event.TickListener;
import client.setting.RenderSliderProperty;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class SecondaryAbstractEventAdapter extends AbstractEvent {
   public static final int count = 40;
   public RenderSliderProperty renderSliderProperty;

   public void applyTickListener(TickListener tickListener) {
      switch (CurveBuffer.countArray[this.renderSliderProperty.ordinal()]) {
         case 1:
            tickListener.onPostTick();
            break;
         case 2:
            tickListener.onPreTick();
            break;
         case 3:
            tickListener.onTick();
            break;
         case 4:
            tickListener.onClientTick();
            break;
         case 5:
            tickListener.onRunTick();
      }
   }

   public void call(Object entry) {
      TickListener tickListener = (TickListener)entry;
      this.applyTickListener(tickListener);
   }

   private void setRenderSliderProperty(RenderSliderProperty renderSliderProperty2) {
      this.renderSliderProperty = renderSliderProperty2;
   }

   public static void applySecondaryAbstractEventAdap(SecondaryAbstractEventAdapter event, RenderSliderProperty renderSliderProperty2) {
      event.setRenderSliderProperty(renderSliderProperty2);
   }
}
