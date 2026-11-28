package net.augustus.events.listeners;


import net.augustus.events.EventDownloadTerrain;
import net.augustus.events.EventManager;
public interface DownloadTerrainListener {
   EventDownloadTerrain DOWNLOAD_TERRAIN_EVENT = new EventDownloadTerrain();

   void onDownloadTerrain();

   static void postDownloadTerrain() {
      EventManager.getEventBus().post(8, DOWNLOAD_TERRAIN_EVENT);
   }
}
