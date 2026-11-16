package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.world.World;

public class EventWorldLoad extends AbstractEvent {
   public static final int count = 17;
   public World world;

   public void applyWorldLoadListener(WorldLoadListener worldLoadListener) {
      worldLoadListener.onWorldEvent(this);
   }

   public void call(Object entry) {
      WorldLoadListener worldLoadListener = (WorldLoadListener)entry;
      this.applyWorldLoadListener(worldLoadListener);
   }

   private void updateState() {
      this.world = null;
   }

   public World getWorld() {
      return this.world;
   }

   public static void onWorldLoad(EventWorldLoad event) {
      event.updateState();
   }

   public static void onWorldLoadByEventWorldLoad(EventWorldLoad event, World world2) {
      event.setWorld(world2);
   }

   private void setWorld(World world2) {
      this.world = world2;
   }
}
