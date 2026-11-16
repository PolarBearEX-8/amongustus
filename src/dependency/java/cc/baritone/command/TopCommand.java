package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DepthFirstResolver;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.BlockAir;

public class TopCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The surface/top command tells Baritone to head towards the closest surface-like area.",
         "",
         "This can be the surface or the highest available air space, depending on circumstances.",
         "",
         "Usage:",
         "> surface - Used to get out of caves, mines, ...",
         "> top - Used to get out of caves, mines, ..."
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      int count = this.staticListener.getWorld().getCount3();
      if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() > -1 && this.staticListener.getWorld().getBlockState(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getBetterBlockPosXSYSZSBlockPos3()).getBlock() instanceof BlockAir) {
         this.applyText("Already at surface");
      } else {
         for (int index = Math.max(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY(), -1); index < count; index++) {
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getX(), index, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getZ());
            if (!(this.staticListener.getWorld().getBlockState(betterBlockPosXSYSZSBlockPos).getBlock() instanceof BlockAir) && betterBlockPosXSYSZSBlockPos.getY() > betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY()) {
               DepthFirstResolver depthFirstResolver = new DepthFirstResolver(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3());
               this.applyText(String.format("Going to: %s", depthFirstResolver.toString()));
               this.activeListener.getINACTIVEHandlerExtension().applySecondaryListenerBySecondaryListene(depthFirstResolver);
               return;
            }
         }

         this.applyText("No higher location found");
      }
   }

   @Override
   public String getLabelText2() {
      return "Used to get out of caves, mines, ...";
   }

   public TopCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"surface", "top"});
   }
}
