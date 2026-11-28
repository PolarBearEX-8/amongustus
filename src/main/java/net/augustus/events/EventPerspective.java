package net.augustus.events;

import net.augustus.events.listeners.PerspectiveListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventPerspective extends AbstractEvent<PerspectiveListener> {
   private float aspectRatio;
   public static final int ID = 48;
   private boolean handPass;

   public void setAspectRatio(float f) {
      this.aspectRatio = f;
   }


   public boolean isHandPass() {
      return this.handPass;
   }

   public float getAspectRatio() {
      return this.aspectRatio;
   }

   private void reset(float f, boolean flag) {
      this.aspectRatio = f;
      this.handPass = flag;
   }

   public static void reset(EventPerspective event, float f, boolean flag) {
      event.reset(f, flag);
   }

   @Override
   public void call(PerspectiveListener listener) {
      listener.onPerspective(this);
   }
}
