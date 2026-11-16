package net.minecraft.item;

import client.util.NativeLoggerEntry;
import client.util.VectorStack;

public final class BridgeItemEntry {
   public final NativeLoggerEntry nativeLoggerEntry2;
   public NativeLoggerEntry nativeLoggerEntry;

   private BridgeItemEntry(NativeLoggerEntry nativeLoggerEntry3) {
      this.nativeLoggerEntry2 = nativeLoggerEntry3;
   }

   public BridgeItemEntry(NativeLoggerEntry nativeLoggerEntry3, VectorStack vectorStack) {
      this(nativeLoggerEntry3);
   }
}
