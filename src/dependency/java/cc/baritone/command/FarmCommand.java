package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.ModularListener;
import cc.baritone.utils.SystemCustom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FarmCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The farm command starts farming nearby plants. It harvests mature crops and plants new ones.",
         "",
         "Usage:",
         "> farm - farms every crop it can find.",
         "> farm <range> - farm crops within range from the starting position.",
         "> farm <range> <waypoint> - farm crops within range from waypoint."
      );
   }

   @Override
   public String getLabelText2() {
      return "Farm nearby crops";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(2);
      int count = 0;
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = null;
      if (depthFirstManager.size(1)) {
         count = (Integer)depthFirstManager.resolveTarget3(Integer.class);
      }

      if (depthFirstManager.size(1)) {
         ModularListener[] modularListenerArray = (ModularListener[])depthFirstManager.resolveTarget2(SystemCustom.INSTANCE);
         switch (modularListenerArray.length) {
            case 0:
               throw new InternalClientManager("No waypoints found");
            case 1:
               ModularListener modularListener = modularListenerArray[0];
               betterBlockPosXSYSZSBlockPos = modularListener.getBetterBlockPosXSYSZSBlockPos();
               break;
            default:
               throw new InternalClientManager("Multiple waypoints were found");
         }
      }

      this.activeListener.getCustomListener().applyBlockPos(count, betterBlockPosXSYSZSBlockPos);
      this.applyText("Farming");
   }

   public FarmCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"farm"});
   }
}
