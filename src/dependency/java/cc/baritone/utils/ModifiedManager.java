package cc.baritone.utils;

import cc.baritone.command.BlacklistCommand;
import cc.baritone.command.BuildCommand;
import cc.baritone.command.ClickCommand;
import cc.baritone.command.ComeCommand;
import cc.baritone.command.EtaCommand;
import cc.baritone.command.ExploreCommand;
import cc.baritone.command.ExplorefilterCommand;
import cc.baritone.command.FarmCommand;
import cc.baritone.command.FindCommand;
import cc.baritone.command.FollowCommand;
import cc.baritone.command.ForcecancelCommand;
import cc.baritone.command.ForwardCommand;
import cc.baritone.command.GcCommand;
import cc.baritone.command.GoalCommand;
import cc.baritone.command.GotoCommand;
import cc.baritone.command.HelpCommand;
import cc.baritone.command.HighwayCommand;
import cc.baritone.command.InvertCommand;
import cc.baritone.command.MineCommand;
import cc.baritone.command.PathCommand;
import cc.baritone.command.ProcCommand;
import cc.baritone.command.ReloadallCommand;
import cc.baritone.command.RenderCommand;
import cc.baritone.command.RescanCommand;
import cc.baritone.command.SaveallCommand;
import cc.baritone.command.SelectionCommand;
import cc.baritone.command.SettingsCommand;
import cc.baritone.command.ThisCommandIsAnAliasForSManager;
import cc.baritone.command.TopCommand;
import cc.baritone.command.TunnelCommand;
import cc.baritone.command.VersionCommand;
import cc.baritone.command.WpCommand;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ModifiedManager {
   public static List resolveList(ActiveListener activeListener) {
      Objects.requireNonNull(activeListener);
      java.util.ArrayList list = new java.util.ArrayList<>(
         Arrays.asList(
            new HelpCommand(activeListener),
            new SettingsCommand(activeListener),
            new ThisCommandIsAnAliasForSManager(
               activeListener, Arrays.asList("modified", "mod", "baritone", "modifiedsettings"), "List modified settings", "set modified"
            ),
            new ThisCommandIsAnAliasForSManager(activeListener, "reset", "Reset all settings or just one", "set reset"),
            new GoalCommand(activeListener),
            new GotoCommand(activeListener),
            new PathCommand(activeListener),
            new ProcCommand(activeListener),
            new EtaCommand(activeListener),
            new VersionCommand(activeListener),
            new RescanCommand(activeListener),
            new BuildCommand(activeListener),
            new ComeCommand(activeListener),
            new HighwayCommand(activeListener),
            new ForcecancelCommand(activeListener),
            new GcCommand(activeListener),
            new InvertCommand(activeListener),
            new TunnelCommand(activeListener),
            new RenderCommand(activeListener),
            new FarmCommand(activeListener),
            new FollowCommand(activeListener),
            new ExplorefilterCommand(activeListener),
            new ReloadallCommand(activeListener),
            new SaveallCommand(activeListener),
            new ExploreCommand(activeListener),
            new BlacklistCommand(activeListener),
            new FindCommand(activeListener),
            new MineCommand(activeListener),
            new ClickCommand(activeListener),
            new TopCommand(activeListener),
            new ForwardCommand(activeListener),
            new WpCommand(activeListener),
            new ThisCommandIsAnAliasForSManager(activeListener, "sethome", "Sets your home waypoint", "waypoints save home"),
            new ThisCommandIsAnAliasForSManager(activeListener, "home", "Path to your home waypoint", "waypoints goto home"),
            new SelectionCommand(activeListener)
         )
      );
      PauseManager pauseManager = new PauseManager(activeListener);
      list.add(pauseManager.extendedItemEntry2);
      list.add(pauseManager.extendedItemEntry4);
      list.add(pauseManager.extendedItemEntry);
      list.add(pauseManager.extendedItemEntry3);
      return Collections.unmodifiableList(list);
   }

   private ModifiedManager() {
   }
}
