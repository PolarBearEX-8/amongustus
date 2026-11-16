package cc.baritone.utils;

import java.util.Date;

public class ExtendedCustom implements ModularListener {
   public final String labelText;
   public final BreadthFirstSystem breadthFirstSystem;
   public final long timestamp;
   public final BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos;

   @Override
   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return this.betterBlockPosXSYSZSBlockPos;
   }

   public ExtendedCustom(String text, BreadthFirstSystem breadthFirstSystem2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      this(text, breadthFirstSystem2, betterBlockPosXSYSZSBlockPos2, System.currentTimeMillis());
   }

   @Override
   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else if (!(other instanceof ModularListener)) {
         return false;
      } else {
         ModularListener modularListener = (ModularListener)other;
         return this.labelText.equals(modularListener.getLabelText())
            && this.breadthFirstSystem == modularListener.getBreadthFirstSystem()
            && this.betterBlockPosXSYSZSBlockPos.equals(modularListener.getBetterBlockPosXSYSZSBlockPos());
      }
   }

   @Override
   public BreadthFirstSystem getBreadthFirstSystem() {
      return this.breadthFirstSystem;
   }

   @Override
   public int hashCode() {
      return this.labelText.hashCode() ^ this.breadthFirstSystem.hashCode() ^ this.betterBlockPosXSYSZSBlockPos.hashCode() ^ Long.hashCode(this.timestamp);
   }

   @Override
   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public String toString() {
      return String.format(
         "%s %s %s",
         this.labelText,
         BetterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.betterBlockPosXSYSZSBlockPos).toString(),
         new Date(this.timestamp).toString()
      );
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   public ExtendedCustom(String text, BreadthFirstSystem breadthFirstSystem2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2, long value) {
      this.labelText = text;
      this.breadthFirstSystem = breadthFirstSystem2;
      this.betterBlockPosXSYSZSBlockPos = betterBlockPosXSYSZSBlockPos2;
      this.timestamp = value;
   }
}
