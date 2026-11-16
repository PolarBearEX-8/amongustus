package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.AStarCalibrator;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstScheduler;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.GenericCustom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TunnelCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The tunnel command sets a goal that tells Baritone to mine completely straight in the direction that you're facing.",
         "",
         "Usage:",
         "> tunnel - No arguments, mines in a 1x2 radius.",
         "> tunnel <height> <width> <depth> - Tunnels in a user defined height, width and depth."
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(3);
      if (depthFirstManager.size2(3)) {
         boolean flag = true;
         int count = Integer.parseInt(((BreadthFirstScheduler)depthFirstManager.getLinkedList().get(0)).getLabelText());
         int count2 = Integer.parseInt(((BreadthFirstScheduler)depthFirstManager.getLinkedList().get(1)).getLabelText());
         int count3 = Integer.parseInt(((BreadthFirstScheduler)depthFirstManager.getLinkedList().get(2)).getLabelText());
         if (count2 < 1 || count < 2 || count3 < 1 || count > 255) {
            this.applyText("Width and depth must at least be 1 block; Height must at least be 2 blocks, and cannot be greater than the build limit.");
            flag = false;
         }

         if (flag) {
            count--;
            count2--;
            EnumFacing enumFacing = this.staticListener.getEntityPlayerSP().getEnumFacing2();
            int count4 = count2 % 2 == 0 ? 0 : 1;
            BlockPos blockPos;
            BlockPos blockPos2;
            switch (AStarCalibrator.countArray[enumFacing.ordinal()]) {
               case 1:
                  blockPos2 = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 - count2 / 2
                  );
                  blockPos = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 + count3,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 + count,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 + count2 / 2 + count4
                  );
                  break;
               case 2:
                  blockPos2 = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 + count2 / 2 + count4
                  );
                  blockPos = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 - count3,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 + count,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 - count2 / 2
                  );
                  break;
               case 3:
                  blockPos2 = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 - count2 / 2,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10
                  );
                  blockPos = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 + count2 / 2 + count4,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 + count,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 - count3
                  );
                  break;
               case 4:
                  blockPos2 = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 + count2 / 2 + count4,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10
                  );
                  blockPos = new BlockPos(
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count9 - count2 / 2,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 + count,
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count10 + count3
                  );
                  break;
               default:
                  throw new IllegalStateException("Unexpected value: " + enumFacing);
            }

            this.applyText(String.format("Creating a tunnel %s block(s) high, %s block(s) wide, and %s block(s) deep", count + 1, count2 + 1, count3));
            this.activeListener.getSchematicsHandler().applyBlockPos(blockPos2, blockPos);
         }
      } else {
         GenericCustom genericCustom = new GenericCustom(
            this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS(), this.staticListener.getEntityPlayerSP().getEnumFacing2()
         );
         this.activeListener.getINACTIVEHandlerExtension().applySecondaryListenerBySecondaryListene(genericCustom);
         this.applyText(String.format("Goal: %s", genericCustom.toString()));
      }
   }

   @Override
   public String getLabelText2() {
      return "Set a goal to tunnel in your current direction";
   }

   public TunnelCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"tunnel"});
   }
}
