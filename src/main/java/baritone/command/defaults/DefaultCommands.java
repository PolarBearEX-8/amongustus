package baritone.command.defaults;

import baritone.api.IBaritone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class DefaultCommands {

   public static List createAll(IBaritone baritone) {
      Objects.requireNonNull(baritone);
      ArrayList list = new ArrayList<>(
         Arrays.asList(
            new HelpCommand(baritone),
            new SetCommand(baritone),
            new AliasCommand(baritone, Arrays.asList("modified", "mod", "baritone", "modifiedsettings"), "List modified settings", "set modified"),
            new AliasCommand(baritone, "reset", "Reset all settings or just one", "set reset"),
            new GoalCommand(baritone),
            new GotoCommand(baritone),
            new PathCommand(baritone),
            new ProcCommand(baritone),
            new ETACommand(baritone),
            new VersionCommand(baritone),
            new RepackCommand(baritone),
            new BuildCommand(baritone),
            new ComeCommand(baritone),
            new AxisCommand(baritone),
            new ForceCancelCommand(baritone),
            new GcCommand(baritone),
            new InvertCommand(baritone),
            new TunnelCommand(baritone),
            new RenderCommand(baritone),
            new FarmCommand(baritone),
            new FollowCommand(baritone),
            new ExploreFilterCommand(baritone),
            new ReloadAllCommand(baritone),
            new SaveAllCommand(baritone),
            new ExploreCommand(baritone),
            new BlacklistCommand(baritone),
            new FindCommand(baritone),
            new MineCommand(baritone),
            new ClickCommand(baritone),
            new SurfaceCommand(baritone),
            new ThisWayCommand(baritone),
            new WaypointsCommand(baritone),
            new AliasCommand(baritone, "sethome", "Sets your home waypoint", "waypoints save home"),
            new AliasCommand(baritone, "home", "Path to your home waypoint", "waypoints goto home"),
            new SelCommand(baritone)
         )
      );
      ExecutionControlCommands executionControlCommands = new ExecutionControlCommands(baritone);
      list.add(executionControlCommands.pauseCommand);
      list.add(executionControlCommands.resumeCommand);
      list.add(executionControlCommands.pausedCommand);
      list.add(executionControlCommands.cancelCommand);
      return Collections.unmodifiableList(list);
   }

   private DefaultCommands() {
   }
}
