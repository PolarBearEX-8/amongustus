package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import java.util.Iterator;

public final class AuxiliaryCustom {
   public final Long2DoubleOpenHashMap long2DoubleOpenHashMap = new Long2DoubleOpenHashMap();

   private void applyBetterBlockPosXSYSZSBlockP(double offset, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      this.long2DoubleOpenHashMap.put(BetterBlockPosXSYSZSBlockPos.computeTimestamp(betterBlockPosXSYSZSBlockPos), offset);
   }

   public double get(long value) {
      return this.long2DoubleOpenHashMap.get(value);
   }

   public boolean isEmpty() {
      return this.long2DoubleOpenHashMap.isEmpty();
   }

   public AuxiliaryCustom(StaticListener staticListener, Custom custom, ItemStackEntry itemStackEntry) {
      this(custom, itemStackEntry);
      Iterator iterator = MobSpawnerController.resolveList(staticListener).iterator();

      while (iterator.hasNext()) {
         ((MobSpawnerController)iterator.next()).applyLong2DoubleOpenHashMap(this.long2DoubleOpenHashMap);
      }

      DRNHandler.dRNHandler.applyTextByText("Favoring size: " + this.long2DoubleOpenHashMap.size());
   }

   public AuxiliaryCustom(Custom custom, ItemStackEntry itemStackEntry) {
      this.long2DoubleOpenHashMap.defaultReturnValue(1.0);
      double value = itemStackEntry.value3;
      if (value != 1.0 && custom != null) {
         custom.unmodifiableList().forEach(pos -> this.applyBetterBlockPosXSYSZSBlockP(value, (BetterBlockPosXSYSZSBlockPos)pos));
      }
   }
}
