package net.minecraft.item;

import client.util.AuxiliaryLoggerEntryBridge;

public class CommonItemArrayEntry {
   public AuxiliaryLoggerEntryBridge[] auxiliaryLoggerEntryBridgeArray;
   public int count2;
   public int[] countArray;
   public long timestamp;
   public int count;

   public int getCount() {
      return this.countArray[this.count];
   }

   public void updateState() {
      AuxiliaryLoggerEntryBridge[] auxiliaryLoggerEntryBridgeArray2 = this.auxiliaryLoggerEntryBridgeArray;
      int length = auxiliaryLoggerEntryBridgeArray2.length;

      for (int index = 0; index < length; index++) {
         auxiliaryLoggerEntryBridgeArray2[index].updateState2();
      }
   }

   public AuxiliaryLoggerEntryBridge getAuxiliaryLoggerEntryBridge() {
      if (System.currentTimeMillis() - this.timestamp >= (long)this.countArray[this.count]) {
         this.timestamp = System.currentTimeMillis();
         if (this.count < this.count2 - 1) {
            this.count++;
         } else {
            this.count = 0;
         }
      }

      return this.auxiliaryLoggerEntryBridgeArray[this.count];
   }

   public CommonItemArrayEntry(AuxiliaryLoggerEntryBridge[] auxiliaryLoggerEntryBridgeArray2, int[] countArray2) {
      this.auxiliaryLoggerEntryBridgeArray = auxiliaryLoggerEntryBridgeArray2;
      this.countArray = countArray2;
      this.count2 = auxiliaryLoggerEntryBridgeArray2.length;
   }
}
