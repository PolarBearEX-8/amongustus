package client.util;

import client.event.PreMotionBroadcaster;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeListEntry {
   public final List list;
   public final float factor3;
   public final boolean enabled;
   public final float factor2;
   public FunctionEntry functionEntry;
   public final float factor;
   public final PreMotionBroadcaster preMotionBroadcaster;
   public double value;

   public double getValue() {
      return this.value;
   }

   private static Object cast(Class clazz, Object entry) {
      return clazz.cast(entry);
   }

   public float getFactor() {
      return this.factor;
   }

   public FunctionEntry getFunctionEntry() {
      return this.functionEntry;
   }

   private static boolean matchesClass(Class clazz, Object entry) {
      return clazz.isAssignableFrom(entry.getClass());
   }

   public PreMotionBroadcaster getPreMotionBroadcaster() {
      return this.preMotionBroadcaster;
   }

   public List getList() {
      return this.list;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public List resolveList(Class clazz) {
      return (List)this.list.stream().filter(item -> CompositeListEntry.matchesClass(clazz, item)).map(item -> CompositeListEntry.cast(clazz, item)).collect(Collectors.toList());
   }

   public void setFunctionEntry(FunctionEntry functionEntry2) {
      this.functionEntry = functionEntry2;
   }

   public CompositeListEntry(PreMotionBroadcaster preMotionBroadcaster2, float posX, float posY, float posZ, boolean flag, List list2) {
      this.preMotionBroadcaster = preMotionBroadcaster2;
      this.factor = posX;
      this.factor3 = posY;
      this.factor2 = posZ;
      this.enabled = flag;
      this.list = list2;
   }
}
