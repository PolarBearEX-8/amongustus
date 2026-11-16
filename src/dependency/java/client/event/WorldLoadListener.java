package client.event;

import net.minecraft.world.World;

public interface WorldLoadListener {
   EventWorldLoad eventWorldLoad = new EventWorldLoad();

   static void applyWorldByWorld(World world) {
      EventWorldLoad.onWorldLoadByEventWorldLoad(eventWorldLoad, world);
      EventBusManager.getDietrichEvents2().post(17, eventWorldLoad);
      EventWorldLoad.onWorldLoad(eventWorldLoad);
   }

   void onWorldEvent(EventWorldLoad event);
}
