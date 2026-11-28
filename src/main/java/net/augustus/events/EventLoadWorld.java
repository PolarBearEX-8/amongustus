package net.augustus.events;

import net.augustus.events.listeners.LoadWorldListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.world.World;

public class EventLoadWorld extends AbstractEvent<LoadWorldListener> {
   public static final int ID = 17;
   private World world;

   public World getWorld() {
      return this.world;
   }

   public static void reset(EventLoadWorld event, World world2) {
      event.setWorld(world2);
   }

   private void setWorld(World world2) {
      this.world = world2;
   }


   @Override
   public void call(LoadWorldListener listener) {
      listener.onLoadWorld(this);
   }

   private void clearWorld() {
      this.world = null;
   }

   public static void clearWorld(EventLoadWorld event) {
      event.clearWorld();
   }
}
