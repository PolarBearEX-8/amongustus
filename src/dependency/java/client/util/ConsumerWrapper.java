package client.util;

import client.network.CosmeticsNetworkHandler;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class ConsumerWrapper {
   public final ConsumerEntry consumerEntry;
   public final long timestamp2;
   public final long timestamp;
   public final long timestamp3;
   public final CopyOnWriteArrayList copyOnWriteArrayList;
   public double value;
   public final int count;
   public boolean enabled;
   public final HashSetEntry hashSetEntry;

   private void updateState() {
      if (!this.enabled) {
         double offset;
         try {
            offset = this.getConsumerEntry().getValueAsDouble();
         } catch (Exception exception) {
            offset = 0.0;
         }

         CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         DefaultListenerAdapter defaultListenerAdapter = new DefaultListenerAdapter(SupplementalLongEntry.byteIndexArray, this.getTimestamp2(), offset);
         simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(defaultListenerAdapter);
         this.enabled = true;
      }
   }

   public int getCount() {
      return this.count;
   }

   public String getText() {
      return resolveText(this.timestamp - System.currentTimeMillis()) + " remaining";
   }

   private static String resolveText(long value2) {
      long value3 = value2 / 1000L;
      long value4 = value3 / 60L;
      long value5 = value4 / 60L;
      long value6 = value5 / 24L;
      long value7 = value3 % 60L;
      long value8 = value4 % 60L;
      long value9 = value5 % 24L;
      return value6 + "d, " + value9 + "h, " + value8 + "m, " + value7 + "s";
   }

   public ConsumerEntry getConsumerEntry() {
      return this.consumerEntry;
   }

   public HashSetEntry getHashSetEntry() {
      return this.hashSetEntry;
   }

   private long getTimestamp2() {
      return this.timestamp2;
   }

   public boolean matchesText(String text) {
      return this.getHashSetEntry().matchesText(text) && !this.currentTimeMillis();
   }

   public long getTimestampAsTime() {
      return this.timestamp2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getTextAsText() {
      return this.getConsumerEntry().getText()
         + " "
         + (int)this.getConsumerEntry().getValue()
         + " "
         + this.getConsumerEntry().getTextAsText()
         + " on "
         + this.getHashSetEntry().getLabelText();
   }

   public boolean currentTimeMillis() {
      return System.currentTimeMillis() > this.timestamp;
   }

   private void applyDouble(Double offset) {
      this.value = offset;
      if (offset >= 100.0) {
         this.copyOnWriteArrayList.add((Runnable)this::updateState);
      }
   }

   public void forEach() {
      ((CopyOnWriteArrayList<Runnable>)this.copyOnWriteArrayList).forEach(Runnable::run);
   }

   public double getValue() {
      return this.value;
   }

   public ConsumerWrapper(HashSetEntry hashSetEntry2, ConsumerEntry consumerEntry2, long value2, long value3, int count2, long value4, boolean flag, int count3) {
        super();
      
      this.copyOnWriteArrayList = new CopyOnWriteArrayList();
      this.hashSetEntry = hashSetEntry2;
      this.consumerEntry = consumerEntry2;
      ConsumerEntry consumerEntry3 = this.consumerEntry;
      Consumer consumer = (item -> this.applyDouble((Double)item));
      consumerEntry3.setConsumer(consumer);

      try {
         double offset = (double)count3;
         this.consumerEntry.applyDoubleByDouble(offset);
      } catch (Exception exception) {
         throw new RuntimeException(exception);
      }

      this.timestamp3 = value2;
      this.timestamp = value3 + System.currentTimeMillis();
      this.count = count2;
      this.timestamp2 = value4;
      this.enabled = flag;
   }
}
