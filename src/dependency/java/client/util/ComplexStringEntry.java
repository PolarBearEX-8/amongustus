package client.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComplexStringEntry {
   public int count = 0;
   public String labelText = null;
   public Map map = new LinkedHashMap();
   public byte[] byteFlagArray = null;

   public Map getMap() {
      return this.map;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public int getCount() {
      return this.count;
   }

   public String get(String text) {
      return (String)this.map.get(text);
   }

   public ComplexStringEntry(int count2, String text, Map map2, byte[] byteValueArray) {
      this.count = count2;
      this.labelText = text;
      this.map = map2;
      this.byteFlagArray = byteValueArray;
   }
}
