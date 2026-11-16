package client.util;

import java.util.HashMap;

public class CustomHashMapEntry {
   public HashMap hashMap;

   public float computeFactor(float factor, float factor2) {
      if (this.hashMap != null) {
         String text = factor + "_" + factor2;
         if (this.hashMap.containsKey(text)) {
            return (Float)this.hashMap.get(text);
         }

         float factor3 = RecordNode16Helper.computeFactor(factor, factor2);
         this.hashMap.put(text, factor3);
      }

      return RecordNode16Helper.computeFactor(factor, factor2);
   }
}
