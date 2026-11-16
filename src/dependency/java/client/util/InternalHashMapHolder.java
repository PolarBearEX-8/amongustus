package client.util;

import client.setting.Setting;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class InternalHashMapHolder implements MinecraftAccessor {
   public final HashMap hashMap = new HashMap();

   public void applyConsumerWrapper(long value, ConsumerWrapper consumerWrapper) {
      ConsumerWrapper consumerWrapper2 = (ConsumerWrapper)this.hashMap.get(value);
      if (consumerWrapper2 == null || !(consumerWrapper2.getValue() > consumerWrapper.getValue())) {
         this.hashMap.put(value, consumerWrapper);
      }
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public void applyText(String text) {
      String text2 = this.resolveText(text);
      Iterator iterator = this.hashMap.values().iterator();

      while (iterator.hasNext()) {
         ConsumerWrapper consumerWrapper = (ConsumerWrapper)iterator.next();
         if (consumerWrapper.currentTimeMillis()) {
            iterator.remove();
         } else if (consumerWrapper.matchesText(text2)) {
            ConsumerEntry consumerEntry = consumerWrapper.getConsumerEntry();
            long timestampAsTime = consumerWrapper.getTimestampAsTime();
            boolean flag = true;
            consumerEntry.applyFlag(flag, timestampAsTime);
         } else {
            ConsumerEntry consumerEntry2 = consumerWrapper.getConsumerEntry();
            long timestampAsTime2 = consumerWrapper.getTimestampAsTime();
            boolean flag2 = false;
            consumerEntry2.applyFlag(flag2, timestampAsTime2);
         }

         consumerWrapper.forEach();
      }
   }

   public void clear() {
      this.hashMap.clear();
   }

   public Collection values() {
      return this.hashMap.values();
   }

   public void updateState() {

      for (ConsumerWrapper consumerWrapper : (Iterable<ConsumerWrapper>)(Iterable)(this.hashMap.values())) {
         ConsumerEntry consumerEntry = consumerWrapper.getConsumerEntry();
         long timestampAsTime = consumerWrapper.getTimestampAsTime();
         boolean flag = false;
         consumerEntry.applyFlag(flag, timestampAsTime);
      }
   }

   private String resolveText(String text) {
      String text2 = text.toLowerCase();
      return text2.trim();
   }

   public void applyTextByText(String text) {
      String text2 = this.resolveText(text);

      for (ConsumerWrapper consumerWrapper : (Iterable<ConsumerWrapper>)(Iterable)(this.hashMap.values())) {
         if (consumerWrapper.matchesText(text2)) {
            ConsumerEntry consumerEntry = consumerWrapper.getConsumerEntry();
            long timestampAsTime = consumerWrapper.getTimestampAsTime();
            boolean flag = true;
            consumerEntry.applyFlag(flag, timestampAsTime);
         }
      }
   }
}
