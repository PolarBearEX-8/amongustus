package client.util;

public class ModuleDisableRule {
   public final Object dataRef;
   public final Object entry1;
   public final Object entry;

   public Object getEntry() {
      return this.entry;
   }

   public Object getEntry2() {
      return this.dataRef;
   }

   public Object getEntry3() {
      return this.entry1;
   }

   public ModuleDisableRule(Object entry2, Object entry3, Object entry4) {
      this.dataRef = entry2;
      this.entry1 = entry3;
      this.entry = entry4;
   }
}
