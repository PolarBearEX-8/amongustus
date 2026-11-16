package client.util;

import client.event.PreRenderConsumer;
import java.util.Map;

public final class ResponseManager implements PreRenderConsumer {
   public final Map map;

   @Override
   public void applyHEADManager(HEADManager hEADManager, ComplexStringEntry complexStringEntry) {
      synchronized (this.map) {
         this.map.put("Response", complexStringEntry);
         this.map.notifyAll();
      }
   }

   @Override
   public void applyHEADManager(HEADManager hEADManager, Exception exception) {
      synchronized (this.map) {
         this.map.put("Exception", exception);
         this.map.notifyAll();
      }
   }

   public ResponseManager(Map map2) {
      this.map = map2;
   }
}
