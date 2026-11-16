package client.util;

import client.event.PreMotionBroadcaster;
import java.util.HashMap;

public class CommonHashMapEntry {
   public HashMap hashMap = new HashMap();
   public final PreMotionBroadcaster preMotionBroadcaster;

   public SharedDoubleEntry get(Integer count) {
      SharedDoubleEntry.getIndexArray();
      if (!this.hashMap.containsKey(count)) {
         SharedDoubleEntry sharedDoubleEntry = new SharedDoubleEntry();
         this.preMotionBroadcaster.applySharedDoubleEntry(sharedDoubleEntry);
         this.hashMap.put(count, sharedDoubleEntry);
      }

      return (SharedDoubleEntry)this.hashMap.get(count);
   }

   public CommonHashMapEntry(PreMotionBroadcaster preMotionBroadcaster2) {
      this.preMotionBroadcaster = preMotionBroadcaster2;
   }
}
