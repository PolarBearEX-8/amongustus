package client.event;

import client.util.CommonHashMapEntry;
import client.util.SharedDoubleEntry;

public interface PreMotionBroadcaster {
   void applySharedDoubleEntry(SharedDoubleEntry sharedDoubleEntry);

   CommonHashMapEntry getCommonHashMapEntry();
}
