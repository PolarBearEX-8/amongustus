package net.augustus.events.listeners;

import net.augustus.events.EventLoadWorld;
import net.augustus.events.EventManager;
import net.minecraft.world.World;

public interface LoadWorldListener {
   EventLoadWorld LOAD_WORLD_EVENT = new EventLoadWorld();

   static void postLoadWorld(World world) {
      EventLoadWorld.reset(LOAD_WORLD_EVENT, world);
      EventManager.getEventBus().post(17, LOAD_WORLD_EVENT);
      EventLoadWorld.clearWorld(LOAD_WORLD_EVENT);
   }

   void onLoadWorld(EventLoadWorld event);
}
