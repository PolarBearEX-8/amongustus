package client.util;

import client.event.RenderTickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class StandardAbstractEventAdapter extends AbstractEvent {
   public static final int count = 29;

   public void call(Object entry) {
      RenderTickListener renderTickListener = (RenderTickListener)entry;
      this.applyRenderTickListener(renderTickListener);
   }

   public void applyRenderTickListener(RenderTickListener renderTickListener) {
      renderTickListener.onPostRenderTick();
   }
}
