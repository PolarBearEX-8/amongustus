package client.util;

import client.event.PostUpdateInterceptor;
import client.event.PreMotionBroadcaster;
import java.util.Collections;
import net.minecraft.item.AdapterItemEntry;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.ItemEntry20Entry;

public abstract class CouldNotFindBoneHandlerAdapter implements CouldNotFindBoneHandler {
   public double value2;
   public final AdapterItemEntry adapterItemEntry;
   public ExtendedHashMapHolder extendedHashMapHolder;
   public double value;
   public boolean enabled = false;
   public GenericListEntry genericListEntry;

   @Override
   public void applyPreMotionBroadcaster(PreMotionBroadcaster preMotionBroadcaster, double offset) {
   }

   public void applyPostUpdateInterceptor(PostUpdateInterceptor postUpdateInterceptor) {
      this.adapterItemEntry.applyPostUpdateInterceptor(postUpdateInterceptor);
   }

   @Override
   public SupplementalStringEntry resolveSupplementalStringEntry(String text, PreMotionBroadcaster preMotionBroadcaster) {
      return this.extendedHashMapHolder.get(text);
   }

   public void applyListenerAdapter23(ListenerAdapter23 listenerAdapter) {
      this.applyPostUpdateInterceptor(listenerAdapter);

      for (ListenerAdapter23 listenerAdapter2 : (Iterable<ListenerAdapter23>)(Iterable<?>)listenerAdapter.list2) {
         this.applyListenerAdapter23(listenerAdapter2);
      }
   }

   public GenericListEntry getGenericListEntry() {
      return this.genericListEntry;
   }

   @Override
   public void applyTarget(Object entry, Integer count, CompositeListEntry compositeListEntry) {
      PreMotionBroadcaster preMotionBroadcaster = (PreMotionBroadcaster)entry;
      this.applyPreMotionBroadcaster(preMotionBroadcaster, count, compositeListEntry);
   }

   public ExtendedHashMapHolder getExtendedHashMapHolder() {
      return this.extendedHashMapHolder;
   }

   public void applyPreMotionBroadcaster(PreMotionBroadcaster preMotionBroadcaster, Integer count, CompositeListEntry compositeListEntry) {
      SharedDoubleEntry sharedDoubleEntry = preMotionBroadcaster.getCommonHashMapEntry().get(count);
      if (sharedDoubleEntry.dataRef == null) {
         BezierPipeline bezierPipeline = new BezierPipeline(sharedDoubleEntry);
         sharedDoubleEntry.dataRef = bezierPipeline;
         MoreCosmeticsManager.getMoreCosmeticsManager().add(bezierPipeline);
      }

      this.value = sharedDoubleEntry.value2 + (double)ExtraFloatEntry.factor2;
      CompositeListEntry compositeListEntry2;
      if (compositeListEntry == null) {
         compositeListEntry2 = new CompositeListEntry(preMotionBroadcaster, 0.0F, 0.0F, 0.0F, false, Collections.emptyList());
      } else {
         compositeListEntry2 = compositeListEntry;
      }

      compositeListEntry2.value = this.value;
      AdapterItemEntry adapterItemEntry2 = this.adapterItemEntry;
      PreMotionBroadcaster preMotionBroadcaster2 = compositeListEntry2.getPreMotionBroadcaster();
      double value4 = this.value;
      PreMotionBroadcaster preMotionBroadcaster3 = preMotionBroadcaster2;
      adapterItemEntry2.applyPreMotionBroadcaster(preMotionBroadcaster3, value4);
      if (!this.adapterItemEntry.getList().isEmpty()) {
         boolean enabled2 = this.enabled;
         DefaultItemEntry defaultItemEntry = ItemEntry20Entry.defaultItemEntry;
         double value5 = this.value;
         this.adapterItemEntry.applyPreMotionBroadcaster(preMotionBroadcaster, count, value5, compositeListEntry2, defaultItemEntry, enabled2);
      }
   }

   @Override
   public AdapterItemEntry getAdapterItemEntry() {
      return this.adapterItemEntry;
   }

   public CouldNotFindBoneHandlerAdapter(GenericListEntry genericListEntry2, ExtendedHashMapHolder extendedHashMapHolder2) {
      this.adapterItemEntry = new AdapterItemEntry(this);
      this.adapterItemEntry.clear();

      for (ListenerAdapter23 listenerAdapter : (Iterable<ListenerAdapter23>)(Iterable<?>)genericListEntry2.list) {
         this.applyListenerAdapter23(listenerAdapter);
      }

      this.genericListEntry = genericListEntry2;
      this.extendedHashMapHolder = extendedHashMapHolder2;
   }
}
