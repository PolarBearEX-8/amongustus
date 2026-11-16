package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.AStarValidator;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.BreadthFirstHandler;
import cc.baritone.utils.BreadthFirstSystem;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DepthFirstResolver;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.ExtendedCustom;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.HeuristicEvaluator;
import cc.baritone.utils.HeuristicNavigator;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.ModularListener;
import cc.baritone.utils.NativeListener;
import cc.baritone.utils.SystemCustom;
import cc.baritone.utils.TertiaryClientManager;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class WpCommand extends ExtendedItemEntry {
   public Map map = new HashMap();

   private void updateState() {
      this.applyText("Multiple waypoints were found:");
   }

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActive()) {
         if (depthFirstManager.isActiveAsFlag()) {
            return new HeuristicEstimator()
               .resolveHeuristicEstimatorByTextArray(AStarValidator.getTextArray())
               .getHeuristicEstimator2()
               .resolveHeuristicEstimatorByText(depthFirstManager.getText())
               .getStream();
         }

         AStarValidator aStarValidator = AStarValidator.resolveAStarValidator(depthFirstManager.getText());
         if (depthFirstManager.isActiveAsFlag()) {
            if (aStarValidator != AStarValidator.LIST && aStarValidator != AStarValidator.SAVE && aStarValidator != AStarValidator.CLEAR) {
               if (aStarValidator == AStarValidator.RESTORE) {
                  return Stream.empty();
               }

               return depthFirstManager.resolveStream(SystemCustom.INSTANCE);
            }

            return new HeuristicEstimator()
               .resolveHeuristicEstimatorByTextArray(BreadthFirstSystem.getTextArray())
               .getHeuristicEstimator2()
               .resolveHeuristicEstimatorByText(depthFirstManager.getText())
               .getStream();
         }

         if (depthFirstManager.size(3) && aStarValidator == AStarValidator.SAVE) {
            depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
            depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
            return depthFirstManager.resolveStream(HeuristicNavigator.INSTANCE);
         }
      }

      return Stream.empty();
   }

   private static IChatComponent resolveIChatComponent(String text, ModularListener modularListener, AStarValidator aStarValidator) {
      ChatComponentText chatComponentText = new ChatComponentText("");
      ChatComponentText chatComponentText2 = new ChatComponentText(modularListener.getBreadthFirstSystem().name() + " ");
      chatComponentText2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      String labelText = modularListener.getLabelText();
      ChatComponentText chatComponentText3 = new ChatComponentText(!labelText.isEmpty() ? labelText : "<empty>");
      chatComponentText3.getStyleHasParentManager().withClientSettingsBlackType(!labelText.isEmpty() ? ClientSettingsBlackType.GRAY : ClientSettingsBlackType.DARK_GRAY);
      ChatComponentText chatComponentText4 = new ChatComponentText(" @ " + new Date(modularListener.getTimestamp()));
      chatComponentText4.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GRAY);
      chatComponentText.appendChatComponent(chatComponentText2);
      chatComponentText.appendChatComponent(chatComponentText3);
      chatComponentText.appendChatComponent(chatComponentText4);
      chatComponentText.getStyleHasParentManager()
         .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText("Click to select")))
         .getStyleHasParentManager(
            new ClickEventActionManager(
               ClientSettingsOpenUrlType.RUN_COMMAND,
               String.format(
                  "%s%s %s %s @ %d",
                  BreadthFirstHandler.labelText,
                  text,
                  AStarValidator.resolveTextArray(aStarValidator)[0],
                  modularListener.getBreadthFirstSystem().getText(),
                  modularListener.getTimestamp()
               )
            )
         );
      return chatComponentText;
   }

   @Override
   public List getList() {
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

   private static String resolveText(ModularListener modularListener) {
      return Long.toString(modularListener.getTimestamp());
   }

   @Override
   public String getLabelText2() {
      return "Manage waypoints";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      AStarValidator aStarValidator = depthFirstManager.isActive() ? AStarValidator.resolveAStarValidator(depthFirstManager.getText()) : AStarValidator.LIST;
      if (aStarValidator == null) {
         throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "an action");
      } else {
         BiFunction<ModularListener, AStarValidator, IChatComponent> biFunction = (waypoint, action) -> WpCommand.resolveIChatComponent(text, waypoint, action);
         Function<ModularListener, IChatComponent> mapper = waypoint -> WpCommand.apply(biFunction, aStarValidator, waypoint);
         if (aStarValidator == AStarValidator.LIST) {
            BreadthFirstSystem breadthFirstSystem = depthFirstManager.isActive() ? BreadthFirstSystem.resolveBreadthFirstSystem(depthFirstManager.getTextAsText()) : null;
            if (breadthFirstSystem != null) {
               depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
            }

            ModularListener[] modularListenerArray = breadthFirstSystem != null
               ? SystemCustom.resolveModularListenerArray(this.activeListener, breadthFirstSystem)
               : SystemCustom.resolveModularListenerArray(this.activeListener);
            if (modularListenerArray.length <= 0) {
               depthFirstManager.applyIndexByIndex(0);
               throw new InternalClientManager(breadthFirstSystem != null ? "No waypoints found by that tag" : "No waypoints found");
            }

            depthFirstManager.applyIndexByIndex(1);
            HeuristicEvaluator.applyDepthFirstManager(
               depthFirstManager,
               modularListenerArray,
               () -> this.applyBreadthFirstSystem(breadthFirstSystem),
               mapper,
               String.format(
                  "%s%s %s%s", BreadthFirstHandler.labelText, text, AStarValidator.resolveTextArray(aStarValidator)[0], breadthFirstSystem != null ? " " + breadthFirstSystem.getText() : ""
               )
            );
         } else if (aStarValidator == AStarValidator.SAVE) {
            BreadthFirstSystem breadthFirstSystem2 = depthFirstManager.isActive() ? BreadthFirstSystem.resolveBreadthFirstSystem(depthFirstManager.getTextAsText()) : null;
            if (breadthFirstSystem2 == null) {
               breadthFirstSystem2 = BreadthFirstSystem.USER;
            } else {
               depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
            }

            String text2 = !depthFirstManager.isActiveAsFlag() && !depthFirstManager.size2(4) ? "" : depthFirstManager.getText();
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = depthFirstManager.isActive()
               ? (BetterBlockPosXSYSZSBlockPos)depthFirstManager.resolveTarget(
                  HeuristicNavigator.INSTANCE, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
               )
               : this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
            depthFirstManager.applyIndexByIndex(0);
            ExtendedCustom extendedCustom = new ExtendedCustom(text2, breadthFirstSystem2, betterBlockPosXSYSZSBlockPos);
            SystemCustom.resolveHeuristicHandler(this.activeListener).applyModularListenerByModularListener(extendedCustom);
            ChatComponentText chatComponentText = new ChatComponentText("Waypoint added: ");
            chatComponentText.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
            chatComponentText.appendChatComponent((IChatComponent)biFunction.apply(extendedCustom, AStarValidator.INFO));
            this.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
         } else if (aStarValidator == AStarValidator.CLEAR) {
            depthFirstManager.applyIndexByIndex(1);
            String text3 = depthFirstManager.getText();
            BreadthFirstSystem breadthFirstSystem3 = BreadthFirstSystem.resolveBreadthFirstSystem(text3);
            if (breadthFirstSystem3 == null) {
               throw new InternalClientManager("Invalid tag, \"" + text3 + "\"");
            }

            ModularListener[] modularListenerArray2 = SystemCustom.resolveModularListenerArray(this.activeListener, breadthFirstSystem3);

            for (ModularListener modularListener : modularListenerArray2) {
               SystemCustom.resolveHeuristicHandler(this.activeListener).applyModularListener(modularListener);
            }

            ((List)this.map.computeIfAbsent(this.activeListener.getSupplementalListener().getNativeListener(), item -> WpCommand.resolveList((NativeListener)item))).addAll(Arrays.asList(modularListenerArray2));
            ChatComponentText chatComponentText2 = new ChatComponentText(String.format("Cleared %d waypoints, click to restore them", modularListenerArray2.length));
            chatComponentText2.getStyleHasParentManager()
               .getStyleHasParentManager(
                  new ClickEventActionManager(
                     ClientSettingsOpenUrlType.RUN_COMMAND,
                     String.format(
                        "%s%s restore @ %s", BreadthFirstHandler.labelText, text, Stream.of(modularListenerArray2).map(WpCommand::resolveText).collect(Collectors.joining(" "))
                     )
                  )
               );
            this.applyIChatComponentArray(new IChatComponent[]{chatComponentText2});
         } else if (aStarValidator == AStarValidator.RESTORE) {
            ArrayList list = new ArrayList();
            List orDefault = ((List)this.map.getOrDefault(this.activeListener.getSupplementalListener().getNativeListener(), Collections.emptyList()));
            if (!depthFirstManager.getTextAsText().equals("@")) {
               depthFirstManager.applyIndex(1);
               int size = orDefault.size();
               int count = Math.min(size, (Integer)depthFirstManager.resolveTarget3(Integer.class));
               list = new ArrayList(orDefault.subList(size - count, size));
            } else {
               depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();

               while (depthFirstManager.isActive()) {
                  long value = (Long)depthFirstManager.resolveTarget3(Long.class);

                  for (ModularListener modularListener2 : (Iterable<ModularListener>)(Iterable)(orDefault)) {
                     if (modularListener2.getTimestamp() == value) {
                        list.add(modularListener2);
                        break;
                     }
                  }
               }
            }

            ((List<ModularListener>)list).forEach(SystemCustom.resolveHeuristicHandler(this.activeListener)::applyModularListenerByModularListener);
            orDefault.removeIf(list::contains);
            this.applyText(String.format("Restored %d waypoints", list.size()));
         } else {
            ModularListener[] modularListenerArray3 = (ModularListener[])depthFirstManager.resolveTarget2(SystemCustom.INSTANCE);
            ModularListener modularListener3 = null;
            if (depthFirstManager.isActive() && depthFirstManager.getTextAsText().equals("@")) {
               depthFirstManager.applyIndex(2);
               depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
               long value2 = (Long)depthFirstManager.resolveTarget3(Long.class);

               for (ModularListener modularListener4 : modularListenerArray3) {
                  if (modularListener4.getTimestamp() == value2) {
                     modularListener3 = modularListener4;
                     break;
                  }
               }

               if (modularListener3 == null) {
                  throw new InternalClientManager("Timestamp was specified but no waypoint was found");
               }
            } else {
               switch (modularListenerArray3.length) {
                  case 0:
                     throw new InternalClientManager("No waypoints found");
                  case 1:
                     modularListener3 = modularListenerArray3[0];
               }
            }

            if (modularListener3 == null) {
               depthFirstManager.applyIndexByIndex(1);
               HeuristicEvaluator.applyDepthFirstManager(
                  depthFirstManager,
                  modularListenerArray3,
                  this::updateState,
                  mapper,
                  String.format("%s%s %s %s", BreadthFirstHandler.labelText, text, AStarValidator.resolveTextArray(aStarValidator)[0], depthFirstManager.getText2())
               );
            } else if (aStarValidator == AStarValidator.INFO) {
               this.applyIChatComponentArray(new IChatComponent[]{(IChatComponent)mapper.apply(modularListener3)});
               this.applyText(String.format("Position: %s", modularListener3.getBetterBlockPosXSYSZSBlockPos()));
               ChatComponentText chatComponentText3 = new ChatComponentText("Click to delete this waypoint");
               chatComponentText3.getStyleHasParentManager()
                  .getStyleHasParentManager(
                     new ClickEventActionManager(
                        ClientSettingsOpenUrlType.RUN_COMMAND,
                        String.format("%s%s delete %s @ %d", BreadthFirstHandler.labelText, text, modularListener3.getBreadthFirstSystem().getText(), modularListener3.getTimestamp())
                     )
                  );
               ChatComponentText chatComponentText4 = new ChatComponentText("Click to set goal to this waypoint");
               chatComponentText4.getStyleHasParentManager()
                  .getStyleHasParentManager(
                     new ClickEventActionManager(
                        ClientSettingsOpenUrlType.RUN_COMMAND,
                        String.format("%s%s goal %s @ %d", BreadthFirstHandler.labelText, text, modularListener3.getBreadthFirstSystem().getText(), modularListener3.getTimestamp())
                     )
                  );
               ChatComponentText chatComponentText5 = new ChatComponentText("Click to show a command to recreate this waypoint");
               chatComponentText5.getStyleHasParentManager()
                  .getStyleHasParentManager(
                     new ClickEventActionManager(
                        ClientSettingsOpenUrlType.SUGGEST_COMMAND,
                        String.format(
                           "%s%s save %s %s %s %s %s",
                           BaritoneManager.getSettings().prefix.dataRef,
                           text,
                           modularListener3.getBreadthFirstSystem().getText(),
                           modularListener3.getLabelText(),
                           modularListener3.getBetterBlockPosXSYSZSBlockPos().count9,
                           modularListener3.getBetterBlockPosXSYSZSBlockPos().count12,
                           modularListener3.getBetterBlockPosXSYSZSBlockPos().count10
                        )
                     )
                  );
               ChatComponentText chatComponentText6 = new ChatComponentText("Click to return to the waypoints list");
               chatComponentText6.getStyleHasParentManager()
                  .getStyleHasParentManager(
                     new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, String.format("%s%s list", BreadthFirstHandler.labelText, text))
                  );
               this.applyIChatComponentArray(new IChatComponent[]{chatComponentText3});
               this.applyIChatComponentArray(new IChatComponent[]{chatComponentText4});
               this.applyIChatComponentArray(new IChatComponent[]{chatComponentText5});
               this.applyIChatComponentArray(new IChatComponent[]{chatComponentText6});
            } else if (aStarValidator == AStarValidator.DELETE) {
               SystemCustom.resolveHeuristicHandler(this.activeListener).applyModularListener(modularListener3);
               ((List)this.map.computeIfAbsent(this.activeListener.getSupplementalListener().getNativeListener(), item -> WpCommand.resolveListByNativeListener((NativeListener)item))).add(modularListener3);
               ChatComponentText chatComponentText7 = new ChatComponentText("That waypoint has successfully been deleted, click to restore it");
               chatComponentText7.getStyleHasParentManager()
                  .getStyleHasParentManager(
                     new ClickEventActionManager(
                        ClientSettingsOpenUrlType.RUN_COMMAND, String.format("%s%s restore @ %s", BreadthFirstHandler.labelText, text, modularListener3.getTimestamp())
                     )
                  );
               this.applyIChatComponentArray(new IChatComponent[]{chatComponentText7});
            } else if (aStarValidator == AStarValidator.GOAL) {
               DepthFirstResolver depthFirstResolver = new DepthFirstResolver(modularListener3.getBetterBlockPosXSYSZSBlockPos());
               this.activeListener.getINACTIVEHandlerExtension().applySecondaryListener(depthFirstResolver);
               this.applyText(String.format("Goal: %s", depthFirstResolver));
            } else if (aStarValidator == AStarValidator.GOTO) {
               DepthFirstResolver depthFirstResolver2 = new DepthFirstResolver(modularListener3.getBetterBlockPosXSYSZSBlockPos());
               this.activeListener.getINACTIVEHandlerExtension().applySecondaryListenerBySecondaryListene(depthFirstResolver2);
               this.applyText(String.format("Going to: %s", depthFirstResolver2));
            }
         }
      }
   }

   private static List resolveList(NativeListener nativeListener) {
      return new ArrayList<>();
   }

   private static IChatComponent apply(BiFunction biFunction, AStarValidator aStarValidator, ModularListener modularListener) {
      return (IChatComponent)biFunction.apply(modularListener, aStarValidator == AStarValidator.LIST ? AStarValidator.INFO : aStarValidator);
   }

   private static List resolveListByNativeListener(NativeListener nativeListener) {
      return new ArrayList<>();
   }

   private void applyBreadthFirstSystem(BreadthFirstSystem breadthFirstSystem) {
      this.applyText(breadthFirstSystem != null ? String.format("All waypoints by tag %s:", breadthFirstSystem.name()) : "All waypoints:");
   }

   public WpCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"waypoints", "waypoint", "wp"});
   }
}
