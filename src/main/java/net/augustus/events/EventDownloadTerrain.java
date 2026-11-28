package net.augustus.events;

import net.augustus.events.listeners.DownloadTerrainListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventDownloadTerrain extends AbstractEvent<DownloadTerrainListener> {
   public static final int ID = 8;

   @Override
   public void call(DownloadTerrainListener listener) {
      listener.onDownloadTerrain();
   }

}
