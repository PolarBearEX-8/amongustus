package cc.baritone.utils;

import java.util.HashSet;
import java.util.List;

public interface Custom {
   SecondaryListener getSecondaryListener();

   default BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return (BetterBlockPosXSYSZSBlockPos)this.unmodifiableList().get(0);
   }

   int getCount();

   default int size() {
      return this.unmodifiableList().size();
   }

   default Custom resolveCustom(Object entry) {
      throw new UnsupportedOperationException();
   }

   List unmodifiableListAsList();

   default Custom getCustom() {
      throw new UnsupportedOperationException();
   }

   default void updateState() {
      List list = this.unmodifiableList();
      List list2 = this.unmodifiableListAsList();
      if (!this.getBetterBlockPosXSYSZSBlockPos().equals(list.get(0))) {
         throw new IllegalStateException("Start node does not equal first path element");
      } else if (!this.getBetterBlockPosXSYSZSBlockPos2().equals(list.get(list.size() - 1))) {
         throw new IllegalStateException("End node does not equal last path element");
      } else if (list.size() != list2.size() + 1) {
         throw new IllegalStateException("Size of path array is unexpected");
      } else {
         HashSet set = new HashSet();

         for (int index = 0; index < list.size() - 1; index++) {
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = (BetterBlockPosXSYSZSBlockPos)list.get(index);
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = (BetterBlockPosXSYSZSBlockPos)list.get(index + 1);
            WAITINGCANCELEDEnumService wAITINGCANCELEDEnumService = (WAITINGCANCELEDEnumService)list2.get(index);
            if (!betterBlockPosXSYSZSBlockPos.equals(wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos())) {
               throw new IllegalStateException("Path source is not equal to the movement source");
            }

            if (!betterBlockPosXSYSZSBlockPos2.equals(wAITINGCANCELEDEnumService.getBetterBlockPosXSYSZSBlockPos2())) {
               throw new IllegalStateException("Path destination is not equal to the movement destination");
            }

            if (set.contains(betterBlockPosXSYSZSBlockPos)) {
               throw new IllegalStateException("Path doubles back on itself, making a loop");
            }

            set.add(betterBlockPosXSYSZSBlockPos);
         }
      }
   }

   default BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2() {
      List list = this.unmodifiableList();
      return (BetterBlockPosXSYSZSBlockPos)list.get(list.size() - 1);
   }

   default double computeValue(int count) {
      double offset = 0.0;
      List list = this.unmodifiableListAsList();

      for (int index = count; index < list.size(); index++) {
         offset += ((WAITINGCANCELEDEnumService)list.get(index)).doubleValue();
      }

      return offset;
   }

   default Custom resolveCustom(SecondaryListener secondaryListener) {
      throw new UnsupportedOperationException();
   }

   List unmodifiableList();
}
