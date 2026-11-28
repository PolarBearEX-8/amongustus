package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.ForBlockOptionalMeta;
import baritone.api.command.datatypes.RelativeCoordinate;
import baritone.api.command.datatypes.RelativeGoal;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.Goal;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GotoCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Go to a coordinate or block";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(1);
      return argConsumer.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.peekDatatypeOrNull2(RelativeCoordinate.INSTANCE) != null) {
         argConsumer.requireMax(3);
         BetterBlockPos betterBlockPos = this.ctx.playerFeet();
         Goal datatypePost = (Goal)argConsumer.getDatatypePost(RelativeGoal.INSTANCE, betterBlockPos);
         this.logDirect2(String.format("Going to: %s", datatypePost.toString()));
         this.baritone.getCustomGoalProcess().setGoalAndPath(datatypePost);
      } else {
         argConsumer.requireMax(1);
         BlockOptionalMeta datatypeFor = (BlockOptionalMeta)argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
         this.baritone.getGetToBlockProcess().getToBlock(datatypeFor);
      }
   }

   public GotoCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"goto"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The goto command tells Baritone to head towards a given goal or block.",
         "",
         "Wherever a coordinate is expected, you can use ~ just like in regular Minecraft commands. Or, you can just use regular numbers.",
         "",
         "Usage:",
         "> goto <block> - Go to a block, wherever it is in the world",
         "> goto <y> - Go to a Y level",
         "> goto <x> <z> - Go to an X,Z position",
         "> goto <x> <y> <z> - Go to an X,Y,Z position"
      );
   }
}
