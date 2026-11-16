package client.util;

public class TargetFilter {
   public final int count2;
   public final int count;
   public Object dataRef;
   public boolean enabled;

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setDataRef(Object dataRef2) {
      this.dataRef = dataRef2;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public static boolean matchesTargetFilter(TargetFilter targetFilter, boolean flag) {
      return targetFilter.enabled = flag;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public TargetFilter(int count3, int count4, Object entry) {
      this.count = count4;
      this.dataRef = entry;
      this.count2 = count3;
      this.enabled = true;
   }
}
