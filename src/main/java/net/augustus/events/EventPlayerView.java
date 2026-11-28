package net.augustus.events;

import net.augustus.events.listeners.PlayerViewListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventPlayerView extends AbstractEvent<PlayerViewListener> {
   private float playerViewX;
   public static final int ID = 63;
   private float playerViewY;
   private float partialTicks;

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setPlayerViewX(float f) {
      this.playerViewX = f;
   }

   public float getPlayerViewX() {
      return this.playerViewX;
   }

   private void reset(float f, float f2, float f3) {
      this.playerViewX = f;
      this.playerViewY = f2;
      this.partialTicks = f3;
   }

   public static void reset(EventPlayerView event, float f, float f2, float f3) {
      event.reset(f, f2, f3);
   }

   @Override
   public void call(PlayerViewListener listener) {
      listener.onPlayerView(this);
   }

   public float getPlayerViewY() {
      return this.playerViewY;
   }


   public void setPlayerViewY(float f) {
      this.playerViewY = f;
   }
}
