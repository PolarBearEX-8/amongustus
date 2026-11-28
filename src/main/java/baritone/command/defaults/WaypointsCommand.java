package baritone.command.defaults;

import java.util.HashSet;
import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.cache.IWaypoint;
import baritone.api.cache.Waypoint;
import baritone.api.command.Command;
import baritone.api.command.datatypes.ForWaypoints;
import baritone.api.command.datatypes.RelativeBlockPos;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.helpers.Paginator;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.IBaritoneChatControl;
import baritone.api.cache.WaypointTag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class WaypointsCommand extends Command {
   private Map deletedWaypoints = new HashMap();

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The waypoint command allows you to manage Baritone's waypoints.",
         "",
         "Waypoints can be used to mark positions for later. Waypoints are each given a tag and an optional name.",
         "",
         "Note that the info, delete, and goal commands let you specify a waypoint by tag. If there is more than one waypoint with a certain tag, then they will let you select which waypoint you mean.",
         "",
         "Missing arguments for the save command use the USER tag, creating an unnamed waypoint and your current position as defaults.",
         "",
         "Usage:",
         "> wp [l/list] - List all waypoints.",
         "> wp <l/list> <tag> - List all waypoints by tag.",
         "> wp <s/save> - Save an unnamed USER waypoint at your current position",
         "> wp <s/save> [tag] [name] [pos] - Save a waypoint with the specified tag, name and position.",
         "> wp <i/info/show> <tag/name> - Show info on a waypoint by tag or name.",
         "> wp <d/delete> <tag/name> - Delete a waypoint by tag or name.",
         "> wp <restore> <n> - Restore the last n deleted waypoints.",
         "> wp <c/clear> <tag> - Delete all waypoints with the specified tag.",
         "> wp <g/goal> <tag/name> - Set a goal to a waypoint by tag or name.",
         "> wp <goto> <tag/name> - Set a goal to a waypoint by tag or name and start pathing."
      );
   }

   @Override
   public String getShortDesc() {
      return "Manage waypoints";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      WaypointsCommand.Action action = argConsumer.hasAny() ? WaypointsCommand.Action.getByName(argConsumer.getString()) : WaypointsCommand.Action.LIST;
      if (action == null) {
         throw new CommandInvalidTypeException(argConsumer.consumed(), "an action");
      } else {
         BiFunction<IWaypoint, WaypointsCommand.Action, IChatComponent> biFunction = (waypoint, action2) -> {
            ChatComponentText chatComponentText = new ChatComponentText("");
            ChatComponentText chatComponentText2 = new ChatComponentText(waypoint.getTag().name() + " ");
            chatComponentText2.getChatStyle().setColor(EnumChatFormatting.GRAY);
            String name = waypoint.getName();
            ChatComponentText chatComponentText3 = new ChatComponentText(!name.isEmpty() ? name : "<empty>");
            chatComponentText3.getChatStyle().setColor(!name.isEmpty() ? EnumChatFormatting.GRAY : EnumChatFormatting.DARK_GRAY);
            ChatComponentText chatComponentText4 = new ChatComponentText(" @ " + new Date(waypoint.getCreationTimestamp()));
            chatComponentText4.getChatStyle().setColor(EnumChatFormatting.DARK_GRAY);
            chatComponentText.appendSibling(chatComponentText2);
            chatComponentText.appendSibling(chatComponentText3);
            chatComponentText.appendSibling(chatComponentText4);
            chatComponentText.getChatStyle()
               .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to select")))
               .setChatClickEvent(
                  new ClickEvent(
                     net.minecraft.event.ClickEvent.Action.RUN_COMMAND,
                     String.format("%s%s %s %s @ %d", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, WaypointsCommand.Action.getNames(action2)[0], waypoint.getTag().getName(), waypoint.getCreationTimestamp())
                  )
               );
            return chatComponentText;
         };
         Function function = obj -> (IChatComponent)biFunction.apply((IWaypoint)obj, action == WaypointsCommand.Action.LIST ? WaypointsCommand.Action.INFO : action);
         if (action == WaypointsCommand.Action.LIST) {
            WaypointTag waypointTag = argConsumer.hasAny() ? WaypointTag.getByName(argConsumer.peekString()) : null;
            if (waypointTag != null) {
               argConsumer.get();
            }

            IWaypoint[] waypoints = waypointTag != null ? ForWaypoints.getWaypointsByTag(this.baritone, waypointTag) : ForWaypoints.getWaypoints(this.baritone);
            if (waypoints.length <= 0) {
               argConsumer.requireMax(0);
               throw new CommandInvalidStateException(waypointTag != null ? "No waypoints found by that tag" : "No waypoints found");
            }

            argConsumer.requireMax(1);
            WaypointTag waypointTag2 = waypointTag;
            Paginator.paginate(
               argConsumer,
               waypoints,
               () -> this.logDirect2(waypointTag2 != null ? String.format("All waypoints by tag %s:", waypointTag2.name()) : "All waypoints:"),
               function,
               String.format("%s%s %s%s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, WaypointsCommand.Action.getNames(action)[0], waypointTag != null ? " " + waypointTag.getName() : "")
            );
         } else if (action == WaypointsCommand.Action.SAVE) {
            WaypointTag waypointTag3 = argConsumer.hasAny() ? WaypointTag.getByName(argConsumer.peekString()) : null;
            if (waypointTag3 == null) {
               waypointTag3 = WaypointTag.USER;
            } else {
               argConsumer.get();
            }

            String str2 = !argConsumer.hasExactlyOne() && !argConsumer.hasExactly(4) ? "" : argConsumer.getString();
            BetterBlockPos betterBlockPos = argConsumer.hasAny() ? (BetterBlockPos)argConsumer.getDatatypePost(RelativeBlockPos.INSTANCE, this.ctx.playerFeet()) : this.ctx.playerFeet();
            argConsumer.requireMax(0);
            Waypoint waypoint2 = new Waypoint(str2, waypointTag3, betterBlockPos);
            ForWaypoints.waypoints(this.baritone).addWaypoint(waypoint2);
            ChatComponentText chatComponentText5 = new ChatComponentText("Waypoint added: ");
            chatComponentText5.getChatStyle().setColor(EnumChatFormatting.GRAY);
            chatComponentText5.appendSibling((IChatComponent)biFunction.apply(waypoint2, WaypointsCommand.Action.INFO));
            this.logDirect(new IChatComponent[]{chatComponentText5});
         } else if (action == WaypointsCommand.Action.CLEAR) {
            argConsumer.requireMax(1);
            String string = argConsumer.getString();
            WaypointTag byName = WaypointTag.getByName(string);
            if (byName == null) {
               throw new CommandInvalidStateException("Invalid tag, \"" + string + "\"");
            }

            IWaypoint[] waypointsByTag = ForWaypoints.getWaypointsByTag(this.baritone, byName);

            for (IWaypoint waypoint3 : waypointsByTag) {
               ForWaypoints.waypoints(this.baritone).removeWaypoint(waypoint3);
            }

            ((List)this.deletedWaypoints.computeIfAbsent(this.baritone.getWorldProvider().getCurrentWorld(), obj2 -> new ArrayList<>())).addAll(Arrays.asList(waypointsByTag));
            ChatComponentText chatComponentText6 = new ChatComponentText(String.format("Cleared %d waypoints, click to restore them", waypointsByTag.length));
            chatComponentText6.getChatStyle()
               .setChatClickEvent(
                  new ClickEvent(
                     net.minecraft.event.ClickEvent.Action.RUN_COMMAND,
                     String.format(
                        "%s%s restore @ %s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, Stream.of(waypointsByTag).map(waypoint4 -> Long.toString(waypoint4.getCreationTimestamp())).collect(Collectors.joining(" "))
                     )
                  )
               );
            this.logDirect(new IChatComponent[]{chatComponentText6});
         } else if (action == WaypointsCommand.Action.RESTORE) {
            ArrayList list = new ArrayList();
            List orDefault = (List)(this.deletedWaypoints.getOrDefault(this.baritone.getWorldProvider().getCurrentWorld(), Collections.emptyList()));
            if (!argConsumer.peekString().equals("@")) {
               argConsumer.requireExactly(1);
               int size = orDefault.size();
               int n = Math.min(size, (Integer)argConsumer.getAs(Integer.class));
               list = new ArrayList(orDefault.subList(size - n, size));
            } else {
               argConsumer.get();

               while (argConsumer.hasAny()) {
                  long as = (Long)argConsumer.getAs(Long.class);

                  for (IWaypoint waypoint5 : (Iterable<IWaypoint>)(orDefault)) {
                     if (waypoint5.getCreationTimestamp() == as) {
                        list.add(waypoint5);
                        break;
                     }
                  }
               }
            }

            list.forEach(obj3 -> ForWaypoints.waypoints(this.baritone).addWaypoint((IWaypoint)obj3));
            orDefault.removeIf(list::contains);
            this.logDirect2(String.format("Restored %d waypoints", list.size()));
         } else {
            IWaypoint[] datatypeFor = (IWaypoint[])argConsumer.getDatatypeFor(ForWaypoints.INSTANCE);
            IWaypoint waypoint6 = null;
            if (argConsumer.hasAny() && argConsumer.peekString().equals("@")) {
               argConsumer.requireExactly(2);
               argConsumer.get();
               long as2 = (Long)argConsumer.getAs(Long.class);

               for (IWaypoint waypoint7 : datatypeFor) {
                  if (waypoint7.getCreationTimestamp() == as2) {
                     waypoint6 = waypoint7;
                     break;
                  }
               }

               if (waypoint6 == null) {
                  throw new CommandInvalidStateException("Timestamp was specified but no waypoint was found");
               }
            } else {
               switch (datatypeFor.length) {
                  case 0:
                     throw new CommandInvalidStateException("No waypoints found");
                  case 1:
                     waypoint6 = datatypeFor[0];
               }
            }

            if (waypoint6 == null) {
               argConsumer.requireMax(1);
               Paginator.paginate(
                  argConsumer,
                  datatypeFor,
                  () -> this.logDirect2("Multiple waypoints were found:"),
                  function,
                  String.format("%s%s %s %s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, WaypointsCommand.Action.getNames(action)[0], argConsumer.consumedString())
               );
            } else if (action == WaypointsCommand.Action.INFO) {
               this.logDirect(new IChatComponent[]{(IChatComponent)function.apply(waypoint6)});
               this.logDirect2(String.format("Position: %s", waypoint6.getLocation()));
               ChatComponentText chatComponentText7 = new ChatComponentText("Click to delete this waypoint");
               chatComponentText7.getChatStyle()
                  .setChatClickEvent(
                     new ClickEvent(
                        net.minecraft.event.ClickEvent.Action.RUN_COMMAND,
                        String.format("%s%s delete %s @ %d", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, waypoint6.getTag().getName(), waypoint6.getCreationTimestamp())
                     )
                  );
               ChatComponentText chatComponentText8 = new ChatComponentText("Click to set goal to this waypoint");
               chatComponentText8.getChatStyle()
                  .setChatClickEvent(
                     new ClickEvent(
                        net.minecraft.event.ClickEvent.Action.RUN_COMMAND,
                        String.format("%s%s goal %s @ %d", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, waypoint6.getTag().getName(), waypoint6.getCreationTimestamp())
                     )
                  );
               ChatComponentText chatComponentText9 = new ChatComponentText("Click to show a command to recreate this waypoint");
               chatComponentText9.getChatStyle()
                  .setChatClickEvent(
                     new ClickEvent(
                        net.minecraft.event.ClickEvent.Action.SUGGEST_COMMAND,
                        String.format(
                           "%s%s save %s %s %s %s %s",
                           Baritone.settings().prefix.value,
                           str,
                           waypoint6.getTag().getName(),
                           waypoint6.getName(),
                           waypoint6.getLocation().x2,
                           waypoint6.getLocation().y2,
                           waypoint6.getLocation().z2
                        )
                     )
                  );
               ChatComponentText chatComponentText10 = new ChatComponentText("Click to return to the waypoints list");
               chatComponentText10.getChatStyle()
                  .setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, String.format("%s%s list", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str)));
               this.logDirect(new IChatComponent[]{chatComponentText7});
               this.logDirect(new IChatComponent[]{chatComponentText8});
               this.logDirect(new IChatComponent[]{chatComponentText9});
               this.logDirect(new IChatComponent[]{chatComponentText10});
            } else if (action == WaypointsCommand.Action.DELETE) {
               ForWaypoints.waypoints(this.baritone).removeWaypoint(waypoint6);
               ((List)this.deletedWaypoints.computeIfAbsent(this.baritone.getWorldProvider().getCurrentWorld(), obj4 -> new ArrayList<>())).add(waypoint6);
               ChatComponentText chatComponentText11 = new ChatComponentText("That waypoint has successfully been deleted, click to restore it");
               chatComponentText11.getChatStyle()
                  .setChatClickEvent(
                     new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, String.format("%s%s restore @ %s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, str, waypoint6.getCreationTimestamp()))
                  );
               this.logDirect(new IChatComponent[]{chatComponentText11});
            } else if (action == WaypointsCommand.Action.GOAL) {
               GoalBlock goalBlock = new GoalBlock(waypoint6.getLocation());
               this.baritone.getCustomGoalProcess().setGoal(goalBlock);
               this.logDirect2(String.format("Goal: %s", goalBlock));
            } else if (action == WaypointsCommand.Action.GOTO) {
               GoalBlock goalBlock2 = new GoalBlock(waypoint6.getLocation());
               this.baritone.getCustomGoalProcess().setGoalAndPath(goalBlock2);
               this.logDirect2(String.format("Going to: %s", goalBlock2));
            }
         }
      }
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasAny()) {
         if (argConsumer.hasExactlyOne()) {
            return new TabCompleteHelper().append2(WaypointsCommand.Action.getAllNames()).sortAlphabetically().filterPrefix(argConsumer.getString()).getStream();
         }

         WaypointsCommand.Action byName = WaypointsCommand.Action.getByName(argConsumer.getString());
         if (argConsumer.hasExactlyOne()) {
            if (byName != WaypointsCommand.Action.LIST && byName != WaypointsCommand.Action.SAVE && byName != WaypointsCommand.Action.CLEAR) {
               if (byName == WaypointsCommand.Action.RESTORE) {
                  return Stream.empty();
               }

               return argConsumer.tabCompleteDatatype(ForWaypoints.INSTANCE);
            }

            return new TabCompleteHelper().append2(WaypointTag.getAllNames()).sortAlphabetically().filterPrefix(argConsumer.getString()).getStream();
         }

         if (argConsumer.has(3) && byName == WaypointsCommand.Action.SAVE) {
            argConsumer.get();
            argConsumer.get();
            return argConsumer.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
         }
      }

      return Stream.empty();
   }

   public WaypointsCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"waypoints", "waypoint", "wp"});
   }



   public enum Action {
      LIST(new String[]{"list", "get", "l"}),
      CLEAR(new String[]{"clear", "c"}),
      SAVE(new String[]{"save", "s"}),
      INFO(new String[]{"info", "show", "i"}),
      DELETE(new String[]{"delete", "d"}),
      RESTORE(new String[]{"restore"}),
      GOAL(new String[]{"goal", "g"}),
      GOTO(new String[]{"goto"});
      private final String[] names;
      public static String[] getAllNames() {
         HashSet set = new HashSet();

         for (Action action : values()) {
            set.addAll(Arrays.asList(action.names));
         }

         return (String[])set.toArray(new String[0]);
      }

      private Action(String[] strings) {
         this.names = strings;
      }

      public static String[] getNames(Action action) {
         return action.names;
      }

      public static Action getByName(String str) {
         for (Action action : values()) {
            for (String str2 : action.names) {
               if (str2.equalsIgnoreCase(str)) {
                  return action;
               }
            }
         }

         return null;
      }

   }
}
