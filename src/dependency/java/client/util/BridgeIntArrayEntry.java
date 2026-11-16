package client.util;

import client.render.WorldRenderer;

public class BridgeIntArrayEntry {
   public final int[] countArray;
   public final AuxiliaryLoggerEntry auxiliaryLoggerEntry;
   public ServiceStringEntry[] serviceStringEntryArray;
   public final WorldRenderer worldRenderer;

   public AuxiliaryLoggerEntry getAuxiliaryLoggerEntry() {
      return this.auxiliaryLoggerEntry;
   }

   public int getCount() {
      return this.countArray.length / this.auxiliaryLoggerEntry.getCountAsIndex();
   }

   public int[] getCountArray() {
      return this.countArray;
   }

   public static ServiceStringEntry[] resolveServiceStringEntryArray(BridgeIntArrayEntry bridgeIntArrayEntry) {
      return bridgeIntArrayEntry.serviceStringEntryArray;
   }

   public BridgeIntArrayEntry(WorldRenderer worldRenderer2, int[] countArray2, AuxiliaryLoggerEntry auxiliaryLoggerEntry2, ServiceStringEntry[] serviceStringEntryArray2) {
      this.worldRenderer = worldRenderer2;
      this.countArray = countArray2;
      this.auxiliaryLoggerEntry = auxiliaryLoggerEntry2;
      this.serviceStringEntryArray = serviceStringEntryArray2;
   }

   public BridgeIntArrayEntry(WorldRenderer worldRenderer2, int[] countArray2, AuxiliaryLoggerEntry auxiliaryLoggerEntry2) {
      this.worldRenderer = worldRenderer2;
      this.countArray = countArray2;
      this.auxiliaryLoggerEntry = auxiliaryLoggerEntry2;
   }
}
