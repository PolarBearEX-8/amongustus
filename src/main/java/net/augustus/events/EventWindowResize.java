package net.augustus.events;

import net.augustus.events.listeners.WindowResizeListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventWindowResize extends AbstractEvent<WindowResizeListener> {
   public static final int ID = 47;
   private int width;
   private int height;

   private void reset(int n, int n2) {
      this.width = n;
      this.height = n2;
   }

   public static void reset(EventWindowResize event, int n, int n2) {
      event.reset(n, n2);
   }

   @Override
   public void call(WindowResizeListener listener) {
      int height2 = this.height;
      listener.onWindowResize(this.width, height2);
   }

}
