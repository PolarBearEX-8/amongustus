package client.util;

import client.event.PostUpdateInterceptor;
import client.event.PreMotionBroadcaster;
import net.minecraft.item.AdapterItemEntry;

public interface CouldNotFindBoneHandler {
   default double nanoTime() {
      return (double)(System.nanoTime() / 1000000L) / 50.0;
   }

   void applyPreMotionBroadcaster(PreMotionBroadcaster preMotionBroadcaster, double offset);

   SupplementalStringEntry resolveSupplementalStringEntry(String text, PreMotionBroadcaster preMotionBroadcaster);

   default PostUpdateInterceptor resolvePostUpdateInterceptor(String text) {
      PostUpdateInterceptor postUpdateInterceptor = this.getAdapterItemEntry().resolvePostUpdateInterceptor(text);
      if (postUpdateInterceptor == null) {
         throw new RuntimeException("Could not find bone: " + text);
      } else {
         return postUpdateInterceptor;
      }
   }

   void applyTarget(Object entry, Integer count, CompositeListEntry compositeListEntry);

   default void applyTarget(Object entry, Integer count) {
      CompositeListEntry compositeListEntry = null;
      this.applyTarget(entry, count, compositeListEntry);
   }

   AdapterItemEntry getAdapterItemEntry();
}
