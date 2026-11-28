package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.cache.IWaypoint;
import baritone.api.command.Command;
import baritone.api.command.datatypes.ForWaypoints;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FarmCommand extends Command {

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(2);
      int n = 0;
      BetterBlockPos betterBlockPos = null;
      if (argConsumer.has(1)) {
         n = (Integer)argConsumer.getAs(Integer.class);
      }

      if (argConsumer.has(1)) {
         IWaypoint[] datatypeFor = (IWaypoint[])argConsumer.getDatatypeFor(ForWaypoints.INSTANCE);
         IWaypoint waypoint = null;
         switch (datatypeFor.length) {
            case 0:
               throw new CommandInvalidStateException("No waypoints found");
            case 1:
               waypoint = datatypeFor[0];
               betterBlockPos = waypoint.getLocation();
               break;
            default:
               throw new CommandInvalidStateException("Multiple waypoints were found");
         }
      }

      this.baritone.getFarmProcess().farm(n, betterBlockPos);
      this.logDirect2("Farming");
   }

   @Override
   public String getShortDesc() {
      return "Farm nearby crops";
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The farm command starts farming nearby plants. It harvests mature crops and plants new ones.",
         "",
         "Usage:",
         "> farm - farms every crop it can find.",
         "> farm <range> - farm crops within range from the starting position.",
         "> farm <range> <waypoint> - farm crops within range from waypoint."
      );
   }

   public FarmCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"farm"});
   }
}
