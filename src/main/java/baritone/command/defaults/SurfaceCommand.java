package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.BlockAir;

public class SurfaceCommand extends Command {

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "Used to get out of caves, mines, ...";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      int actualHeight = this.ctx.world().getActualHeight();
      if (betterBlockPos.getY() > -1 && this.ctx.world().getBlockState(betterBlockPos.up2()).getBlock() instanceof BlockAir) {
         this.logDirect2("Already at surface");
      } else {
         int n = Math.max(betterBlockPos.getY(), -1);

         for (int j = n; j < actualHeight; j++) {
            BetterBlockPos betterBlockPos2 = new BetterBlockPos(betterBlockPos.getX(), j, betterBlockPos.getZ());
            if (!(this.ctx.world().getBlockState(betterBlockPos2).getBlock() instanceof BlockAir) && betterBlockPos2.getY() > betterBlockPos.getY()) {
               GoalBlock goalBlock = new GoalBlock(betterBlockPos2.up2());
               this.logDirect2(String.format("Going to: %s", goalBlock.toString()));
               this.baritone.getCustomGoalProcess().setGoalAndPath(goalBlock);
               return;
            }
         }

         this.logDirect2("No higher location found");
      }
   }

   @Override
   public List getLongDesc() {
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

   public SurfaceCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"surface", "top"});
   }
}
