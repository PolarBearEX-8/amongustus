package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalStrictDirection;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.argument.ICommandArgument;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TunnelCommand extends Command {

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The tunnel command sets a goal that tells Baritone to mine completely straight in the direction that you're facing.",
         "",
         "Usage:",
         "> tunnel - No arguments, mines in a 1x2 radius.",
         "> tunnel <height> <width> <depth> - Tunnels in a user defined height, width and depth."
      );
   }

   @Override
   public String getShortDesc() {
      return "Set a goal to tunnel in your current direction";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(3);
      if (argConsumer.hasExactly(3)) {
         boolean flag = true;
         int n = Integer.parseInt(((ICommandArgument)argConsumer.getArgs().get(0)).getValue());
         int n2 = Integer.parseInt(((ICommandArgument)argConsumer.getArgs().get(1)).getValue());
         int n3 = Integer.parseInt(((ICommandArgument)argConsumer.getArgs().get(2)).getValue());
         if (n2 < 1 || n < 2 || n3 < 1 || n > 255) {
            this.logDirect2("Width and depth must at least be 1 block; Height must at least be 2 blocks, and cannot be greater than the build limit.");
            flag = false;
         }

         if (flag) {
            n--;
            n2--;
            EnumFacing horizontalFacing = this.ctx.player().getHorizontalFacing();
            int n4 = n2 % 2 == 0 ? 0 : 1;
            BlockPos pos;
            BlockPos pos2;
            switch (horizontalFacing) {
               case EAST:
                  pos = new BlockPos(this.ctx.playerFeet().x2, this.ctx.playerFeet().y2, this.ctx.playerFeet().z2 - n2 / 2);
                  pos2 = new BlockPos(this.ctx.playerFeet().x2 + n3, this.ctx.playerFeet().y2 + n, this.ctx.playerFeet().z2 + n2 / 2 + n4);
                  break;
               case WEST:
                  pos = new BlockPos(this.ctx.playerFeet().x2, this.ctx.playerFeet().y2, this.ctx.playerFeet().z2 + n2 / 2 + n4);
                  pos2 = new BlockPos(this.ctx.playerFeet().x2 - n3, this.ctx.playerFeet().y2 + n, this.ctx.playerFeet().z2 - n2 / 2);
                  break;
               case NORTH:
                  pos = new BlockPos(this.ctx.playerFeet().x2 - n2 / 2, this.ctx.playerFeet().y2, this.ctx.playerFeet().z2);
                  pos2 = new BlockPos(this.ctx.playerFeet().x2 + n2 / 2 + n4, this.ctx.playerFeet().y2 + n, this.ctx.playerFeet().z2 - n3);
                  break;
               case SOUTH:
                  pos = new BlockPos(this.ctx.playerFeet().x2 + n2 / 2 + n4, this.ctx.playerFeet().y2, this.ctx.playerFeet().z2);
                  pos2 = new BlockPos(this.ctx.playerFeet().x2 - n2 / 2, this.ctx.playerFeet().y2 + n, this.ctx.playerFeet().z2 + n3);
                  break;
               default:
                  throw new IllegalStateException("Unexpected value: " + horizontalFacing);
            }

            this.logDirect2(String.format("Creating a tunnel %s block(s) high, %s block(s) wide, and %s block(s) deep", n + 1, n2 + 1, n3));
            this.baritone.getBuilderProcess().clearArea(pos, pos2);
         }
      } else {
         GoalStrictDirection goalStrictDirection = new GoalStrictDirection(this.ctx.playerFeet(), this.ctx.player().getHorizontalFacing());
         this.baritone.getCustomGoalProcess().setGoalAndPath(goalStrictDirection);
         this.logDirect2(String.format("Goal: %s", goalStrictDirection.toString()));
      }
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   public TunnelCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"tunnel"});
   }

}
