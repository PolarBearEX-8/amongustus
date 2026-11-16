package cc.baritone.command;

import cc.SettingsUtil;
import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.AuxiliaryStringEntry;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BreadthFirstHandler;
import cc.baritone.utils.BreadthFirstOptimizer;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.HeuristicEvaluator;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.TertiaryClientManager;
import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.ClientSettingsShowTextType;
import client.util.ClickEventActionManager;
import client.util.HoverEventActionManager;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SettingsCommand extends ExtendedItemEntry {
   private static boolean matchesTextByText(String text, AuxiliaryStringEntry auxiliaryStringEntry) {
      return auxiliaryStringEntry.getLabelText().equalsIgnoreCase(text);
   }

   private static boolean matchesText(String text, AuxiliaryStringEntry auxiliaryStringEntry) {
      return auxiliaryStringEntry.getLabelText().toLowerCase(Locale.US).contains(text.toLowerCase(Locale.US));
   }

   private static boolean matchesAuxiliaryStringEntry(AuxiliaryStringEntry auxiliaryStringEntry) {
      return !auxiliaryStringEntry.isEnabled();
   }

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActive()) {
         String text2 = depthFirstManager.getText();
         if (depthFirstManager.isActiveAsFlag() && !Arrays.asList("s", "save").contains(depthFirstManager.getTextAsText().toLowerCase(Locale.US))) {
            if (text2.equalsIgnoreCase("reset")) {
               return new HeuristicEstimator()
                  .getHeuristicEstimator()
                  .resolveHeuristicEstimator(new String[]{"all"})
                  .resolveHeuristicEstimatorByText(depthFirstManager.getText())
                  .getStream();
            }

            if (text2.equalsIgnoreCase("toggle")) {
               return new HeuristicEstimator().getHeuristicEstimator3().resolveHeuristicEstimatorByText(depthFirstManager.getText()).getStream();
            }

            if (Arrays.asList("ld", "load").contains(text2.toLowerCase(Locale.US))) {
               return BreadthFirstOptimizer.resolveStream(depthFirstManager, Minecraft.getMinecraft().mcDataDir.toPath().resolve("baritone").toFile());
            }

            AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)BaritoneManager.getSettings().byLowerName.get(text2.toLowerCase(Locale.US));
            if (auxiliaryStringEntry != null) {
               if (auxiliaryStringEntry.get() == Boolean.class) {
                  HeuristicEstimator heuristicEstimator = new HeuristicEstimator();
                  if ((Boolean)auxiliaryStringEntry.dataRef) {
                     heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"true", "false"});
                  } else {
                     heuristicEstimator.resolveHeuristicEstimatorByTextArray(new String[]{"false", "true"});
                  }

                  return heuristicEstimator.resolveHeuristicEstimatorByText(depthFirstManager.getText()).getStream();
               }

               return Stream.of(SettingsUtil.settingValueToString(auxiliaryStringEntry));
            }
         } else if (!depthFirstManager.isActive()) {
            return new HeuristicEstimator()
               .getHeuristicEstimatorAsHeuristicEstimat()
               .getHeuristicEstimator2()
               .resolveHeuristicEstimator(new String[]{"list", "modified", "reset", "toggle", "save", "load"})
               .resolveHeuristicEstimatorByText(text2)
               .getStream();
         }
      }

      return Stream.empty();
   }

   private static int computeCount(AuxiliaryStringEntry auxiliaryStringEntry, AuxiliaryStringEntry auxiliaryStringEntry2) {
      return String.CASE_INSENSITIVE_ORDER.compare(auxiliaryStringEntry.getLabelText(), auxiliaryStringEntry2.getLabelText());
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Using the set command, you can manage all of Baritone's settings. Almost every aspect is controlled by these settings - go wild!",
         "",
         "Usage:",
         "> set - Same as `set list`",
         "> set list [page] - View all settings",
         "> set modified [page] - View modified settings",
         "> set <setting> - View the current value of a setting",
         "> set <setting> <value> - Set the value of a setting",
         "> set reset all - Reset ALL SETTINGS to their defaults",
         "> set reset <setting> - Reset a setting to its default",
         "> set toggle <setting> - Toggle a boolean setting",
         "> set save - Save all settings (this is automatic tho)",
         "> set load - Load settings from settings.txt",
         "> set load [filename] - Load settings from another file in your minecraft/baritone"
      );
   }

   private void applyText4(String text, boolean flag) {
      this.applyText(
         !text.isEmpty()
            ? String.format("All %ssettings containing the string '%s':", flag ? "modified " : "", text)
            : String.format("All %ssettings:", flag ? "modified " : "")
      );
   }

   @Override
   public String getLabelText2() {
      return "View or change settings";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      String text2 = depthFirstManager.isActive() ? depthFirstManager.getText().toLowerCase(Locale.US) : "list";
      if (Arrays.asList("s", "save").contains(text2)) {
         SettingsUtil.save(BaritoneManager.getSettings());
         this.applyText("Settings saved");
      } else if (Arrays.asList("load", "ld").contains(text2)) {
         String text3 = "settings.txt";
         if (depthFirstManager.isActive()) {
            text3 = depthFirstManager.getText();
         }

         SettingsUtil.modifiedSettings(BaritoneManager.getSettings()).forEach(setting -> ((AuxiliaryStringEntry)setting).updateState2());
         SettingsUtil.readAndApply(BaritoneManager.getSettings(), text3);
         this.applyText("Settings reloaded from " + text3);
      } else {
         boolean flag = Arrays.asList("m", "mod", "modified").contains(text2);
         boolean flag2 = Arrays.asList("all", "l", "list").contains(text2);
         if (flag || flag2) {
            String text4 = depthFirstManager.isActive() && depthFirstManager.resolveTarget5(Integer.class) == null ? depthFirstManager.getText() : "";
            depthFirstManager.applyIndexByIndex(1);
            List list = (List)(flag ? SettingsUtil.modifiedSettings(BaritoneManager.getSettings()) : BaritoneManager.getSettings().allSettings)
               .stream()
               .filter((item -> SettingsCommand.matchesAuxiliaryStringEntry((AuxiliaryStringEntry)item)))
               .filter(setting -> SettingsCommand.matchesText(text4, (AuxiliaryStringEntry)setting))
               .sorted((first, second) -> SettingsCommand.computeCount((AuxiliaryStringEntry)first, (AuxiliaryStringEntry)second))
               .collect(Collectors.toList());
            HeuristicEvaluator.applyDepthFirstManager(
               depthFirstManager,
               new HeuristicEvaluator(list),
               () -> this.applyText4(text4, flag),
               setting -> SettingsCommand.resolveIChatComponent((AuxiliaryStringEntry)setting),
               BreadthFirstHandler.labelText + "set " + text2 + " " + text4
            );
         } else {
            depthFirstManager.applyIndexByIndex(1);
            boolean flag3 = text2.equalsIgnoreCase("reset");
            boolean flag4 = text2.equalsIgnoreCase("toggle");
            boolean flag5 = flag3 || flag4;
            if (flag3) {
               if (!depthFirstManager.isActive()) {
                  this.applyText("Please specify 'all' as an argument to reset to confirm you'd really like to do this");
                  this.applyText("ALL settings will be reset. Use the 'set modified' or 'modified' commands to see what will be reset.");
                  this.applyText("Specify a setting name instead of 'all' to only reset one setting");
               } else if (depthFirstManager.getTextAsText().equalsIgnoreCase("all")) {
                  SettingsUtil.modifiedSettings(BaritoneManager.getSettings()).forEach(setting -> ((AuxiliaryStringEntry)setting).updateState2());
                  this.applyText("All settings have been reset to their default values");
                  SettingsUtil.save(BaritoneManager.getSettings());
                  return;
               }
            }

            if (flag4) {
               depthFirstManager.applyIndex2(1);
            }

            String text5 = flag5 ? depthFirstManager.getText() : text2;
            AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)BaritoneManager.getSettings()
               .allSettings
               .stream()
               .filter(setting -> SettingsCommand.matchesTextByText(text5, (AuxiliaryStringEntry)setting))
               .findFirst()
               .orElse(null);
            if (auxiliaryStringEntry == null) {
               throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "a valid setting");
            } else if (auxiliaryStringEntry.isEnabled()) {
               throw new InternalClientManager(String.format("Setting %s can only be used via the api.", auxiliaryStringEntry.getLabelText()));
            } else {
               if (!flag5 && !depthFirstManager.isActive()) {
                  this.applyText(String.format("Value of setting %s:", auxiliaryStringEntry.getLabelText()));
                  this.applyText(SettingsUtil.settingValueToString(auxiliaryStringEntry));
               } else {
                  String text6 = SettingsUtil.settingValueToString(auxiliaryStringEntry);
                  if (flag3) {
                     auxiliaryStringEntry.updateState2();
                  } else if (flag4) {
                     if (auxiliaryStringEntry.getClassAsClass() != Boolean.class) {
                        throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "a toggleable setting", "some other setting");
                     }

                     auxiliaryStringEntry.dataRef = (Boolean)auxiliaryStringEntry.dataRef ^ true;
                     this.applyText(String.format("Toggled setting %s to %s", auxiliaryStringEntry.getLabelText(), Boolean.toString((Boolean)auxiliaryStringEntry.dataRef)));
                  } else {
                     String text7 = depthFirstManager.getText();

                     try {
                        SettingsUtil.parseAndApply(BaritoneManager.getSettings(), text2, text7);
                     } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "a valid value", throwable);
                     }
                  }

                  if (!flag4) {
                     this.applyText(
                        String.format("Successfully %s %s to %s", flag3 ? "reset" : "set", auxiliaryStringEntry.getLabelText(), SettingsUtil.settingValueToString(auxiliaryStringEntry))
                     );
                  }

                  ChatComponentText chatComponentText = new ChatComponentText(String.format("Old value: %s", text6));
                  chatComponentText.getStyleHasParentManager()
                     .withClientSettingsBlackType(ClientSettingsBlackType.GRAY)
                     .getStyleHasParentManager(
                        new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText("Click to set the setting back to this value"))
                     )
                     .getStyleHasParentManager(
                        new ClickEventActionManager(
                           ClientSettingsOpenUrlType.RUN_COMMAND, BreadthFirstHandler.labelText + String.format("set %s %s", auxiliaryStringEntry.getLabelText(), text6)
                        )
                     );
                  this.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
                  if ((
                        !auxiliaryStringEntry.getLabelText().equals("chatControl")
                           || (Boolean)auxiliaryStringEntry.dataRef
                           || (Boolean)BaritoneManager.getSettings().chatControlAnyway.dataRef
                     )
                     && (
                        !auxiliaryStringEntry.getLabelText().equals("chatControlAnyway")
                           || (Boolean)auxiliaryStringEntry.dataRef
                           || (Boolean)BaritoneManager.getSettings().chatControl.dataRef
                     )) {
                     if (auxiliaryStringEntry.getLabelText().equals("prefixControl") && !(Boolean)auxiliaryStringEntry.dataRef) {
                        this.applyText(
                           "Warning: Prefixed commands will no longer work. If you want to revert this change, use chat control (if enabled) or click the old value listed above.",
                           ClientSettingsBlackType.RED
                        );
                     }
                  } else {
                     this.applyText(
                        "Warning: Chat commands will no longer work. If you want to revert this change, use prefix control (if enabled) or click the old value listed above.",
                        ClientSettingsBlackType.RED
                     );
                  }
               }

               SettingsUtil.save(BaritoneManager.getSettings());
            }
         }
      }
   }

   private static IChatComponent resolveIChatComponent(AuxiliaryStringEntry auxiliaryStringEntry) {
      ChatComponentText chatComponentText = new ChatComponentText(String.format(" (%s)", SettingsUtil.settingTypeToString(auxiliaryStringEntry)));
      chatComponentText.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GRAY);
      ChatComponentText chatComponentText2 = new ChatComponentText("");
      chatComponentText2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      chatComponentText2.resolveIChatComponent(auxiliaryStringEntry.getLabelText());
      chatComponentText2.resolveIChatComponent(String.format("\nType: %s", SettingsUtil.settingTypeToString(auxiliaryStringEntry)));
      chatComponentText2.resolveIChatComponent(String.format("\n\nValue:\n%s", SettingsUtil.settingValueToString(auxiliaryStringEntry)));
      chatComponentText2.resolveIChatComponent(String.format("\n\nDefault Value:\n%s", SettingsUtil.settingDefaultToString(auxiliaryStringEntry)));
      String text = (String)BaritoneManager.getSettings().prefix.dataRef + String.format("set %s ", auxiliaryStringEntry.getLabelText());
      ChatComponentText chatComponentText3 = new ChatComponentText(auxiliaryStringEntry.getLabelText());
      chatComponentText3.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY);
      chatComponentText3.appendChatComponent(chatComponentText);
      chatComponentText3.getStyleHasParentManager()
         .getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, chatComponentText2))
         .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.SUGGEST_COMMAND, text));
      return chatComponentText3;
   }

   public SettingsCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"set", "setting", "settings"});
   }
}
