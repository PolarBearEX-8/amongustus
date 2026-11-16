package client.util;

import java.util.HashMap;

public class SharedDoubleEntry {
   public Double memberRef;
   public double value;
   public boolean enabled;
   public double value2;
   public HashMap hashMap2;
   public boolean enabled2;
   public HashMap hashMap;
   public Object dataRef;

   public void setHashMap(HashMap hashMap3) {
      this.hashMap = hashMap3;
   }

   public double getValue() {
      return this.value;
   }

   public void setValue(double value3) {
      getIndexArray();
      this.value = value3 < 0.0 ? 0.0 : value3;
   }

   public void updateState() {
      this.hashMap = new HashMap();
   }

   public FunctionEntry resolveFunctionEntry(FunctionEntry functionEntry) {
      return ((FunctionEntry)this.hashMap2.put(functionEntry.getLabelText(), functionEntry));
   }

   public static int[] getIndexArray() {
      return null;
   }

   public HashMap getHashMap2() {
      return this.hashMap2;
   }

   public HashMap getHashMap() {
      return this.hashMap;
   }

   public SharedDoubleEntry() {
      getIndexArray();
      this.hashMap = new HashMap();
      this.hashMap2 = new HashMap();
      this.enabled2 = true;
      this.value = 1.0;
      this.enabled = false;
   }
}
