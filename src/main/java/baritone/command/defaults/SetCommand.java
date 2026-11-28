package baritone.command.defaults;

import baritone.api.Settings;
import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.RelativeFile;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.helpers.Paginator;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.utils.SettingsUtil;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.IBaritoneChatControl;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class SetCommand extends Command {

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      String str2 = argConsumer.hasAny() ? argConsumer.getString().toLowerCase(Locale.US) : "list";
      if (Arrays.asList("s", "save").contains(str2)) {
         SettingsUtil.save(Baritone.settings());
         this.logDirect2("Settings saved");
      } else if (Arrays.asList("load", "ld").contains(str2)) {
         String str3 = "settings.txt";
         if (argConsumer.hasAny()) {
            str3 = argConsumer.getString();
         }

         SettingsUtil.modifiedSettings(Baritone.settings()).forEach(Settings.Setting::reset);
         SettingsUtil.readAndApply(Baritone.settings(), str3);
         this.logDirect2("Settings reloaded from " + str3);
      } else {
         boolean flag = Arrays.asList("m", "mod", "modified").contains(str2);
         boolean flag2 = Arrays.asList("all", "l", "list").contains(str2);
         if (flag || flag2) {
            String str4 = argConsumer.hasAny() && argConsumer.peekAsOrNull(Integer.class) == null ? argConsumer.getString() : "";
            argConsumer.requireMax(1);
            List list = (flag ? SettingsUtil.modifiedSettings(Baritone.settings()) : Baritone.settings().allSettings)
               .stream()
               .filter(setting -> !setting.isJavaOnly())
               .filter(setting2 -> setting2.getName().toLowerCase(Locale.US).contains(str4.toLowerCase(Locale.US)))
               .sorted((setting3, setting4) -> String.CASE_INSENSITIVE_ORDER.compare(setting3.getName(), setting4.getName()))
               .collect(Collectors.toList());
            Paginator paginator = new Paginator(list);
            boolean flag4 = flag;
            Paginator.paginate3(
               argConsumer,
               paginator,
               () -> this.logDirect2(
                     !str4.isEmpty()
                        ? String.format("All %ssettings containing the string '%s':", flag4 ? "modified " : "", str4)
                        : String.format("All %ssettings:", flag4 ? "modified " : "")
                  ),
               obj -> {
                  ChatComponentText chatComponentText = new ChatComponentText(String.format(" (%s)", SettingsUtil.settingTypeToString((Settings.Setting)obj)));
                  chatComponentText.getChatStyle().setColor(EnumChatFormatting.DARK_GRAY);
                  ChatComponentText chatComponentText2 = new ChatComponentText("");
                  chatComponentText2.getChatStyle().setColor(EnumChatFormatting.GRAY);
                  chatComponentText2.appendText(((Settings.Setting)obj).getName());
                  chatComponentText2.appendText(String.format("\nType: %s", SettingsUtil.settingTypeToString((Settings.Setting)obj)));
                  chatComponentText2.appendText(String.format("\n\nValue:\n%s", SettingsUtil.settingValueToString((Settings.Setting)obj)));
                  chatComponentText2.appendText(String.format("\n\nDefault Value:\n%s", SettingsUtil.settingDefaultToString((Settings.Setting)obj)));
                  String str5 = (String)Baritone.settings().prefix.value + String.format("set %s ", ((Settings.Setting)obj).getName());
                  ChatComponentText chatComponentText3 = new ChatComponentText(((Settings.Setting)obj).getName());
                  chatComponentText3.getChatStyle().setColor(EnumChatFormatting.GRAY);
                  chatComponentText3.appendSibling(chatComponentText);
                  chatComponentText3.getChatStyle()
                     .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, chatComponentText2))
                     .setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.SUGGEST_COMMAND, str5));
                  return chatComponentText3;
               },
               IBaritoneChatControl.FORCE_COMMAND_PREFIX + "set " + str2 + " " + str4
            );
         } else {
            argConsumer.requireMax(1);
            boolean flag5 = str2.equalsIgnoreCase("reset");
            boolean flag6 = str2.equalsIgnoreCase("toggle");
            boolean flag7 = flag5 || flag6;
            if (flag5) {
               if (!argConsumer.hasAny()) {
                  this.logDirect2("Please specify 'all' as an argument to reset to confirm you'd really like to do this");
                  this.logDirect2("ALL settings will be reset. Use the 'set modified' or 'modified' commands to see what will be reset.");
                  this.logDirect2("Specify a setting name instead of 'all' to only reset one setting");
               } else if (argConsumer.peekString().equalsIgnoreCase("all")) {
                  SettingsUtil.modifiedSettings(Baritone.settings()).forEach(Settings.Setting::reset);
                  this.logDirect2("All settings have been reset to their default values");
                  SettingsUtil.save(Baritone.settings());
                  return;
               }
            }

            if (flag6) {
               argConsumer.requireMin(1);
            }

            String str6 = flag7 ? argConsumer.getString() : str2;
            Settings.Setting setting5 = Baritone.settings().allSettings.stream().filter(setting6 -> setting6.getName().equalsIgnoreCase(str6)).findFirst().orElse(null);
            if (setting5 == null) {
               throw new CommandInvalidTypeException(argConsumer.consumed(), "a valid setting");
            } else if (setting5.isJavaOnly()) {
               throw new CommandInvalidStateException(String.format("Setting %s can only be used via the api.", setting5.getName()));
            } else {
               if (!flag7 && !argConsumer.hasAny()) {
                  this.logDirect2(String.format("Value of setting %s:", setting5.getName()));
                  this.logDirect2(SettingsUtil.settingValueToString(setting5));
               } else {
                  String str7 = SettingsUtil.settingValueToString(setting5);
                  if (flag5) {
                     setting5.reset();
                  } else if (flag6) {
                     if (setting5.getValueClass() != Boolean.class) {
                        throw new CommandInvalidTypeException(argConsumer.consumed(), "a toggleable setting", "some other setting");
                     }

                     setting5.value = (Boolean)setting5.value ^ true;
                     this.logDirect2(String.format("Toggled setting %s to %s", setting5.getName(), Boolean.toString((Boolean)setting5.value)));
                  } else {
                     String string = argConsumer.getString();

                     try {
                        SettingsUtil.parseAndApply(Baritone.settings(), str2, string);
                     } catch (Throwable e) {
                        e.printStackTrace();
                        throw new CommandInvalidTypeException(argConsumer.consumed(), "a valid value", e);
                     }
                  }

                  if (!flag6) {
                     this.logDirect2(String.format("Successfully %s %s to %s", flag5 ? "reset" : "set", setting5.getName(), SettingsUtil.settingValueToString(setting5)));
                  }

                  ChatComponentText chatComponentText4 = new ChatComponentText(String.format("Old value: %s", str7));
                  chatComponentText4.getChatStyle()
                     .setColor(EnumChatFormatting.GRAY)
                     .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to set the setting back to this value")))
                     .setChatClickEvent(
                        new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("set %s %s", setting5.getName(), str7))
                     );
                  this.logDirect(new IChatComponent[]{chatComponentText4});
                  if ((!setting5.getName().equals("chatControl") || (Boolean)setting5.value || (Boolean)Baritone.settings().chatControlAnyway.value)
                     && (!setting5.getName().equals("chatControlAnyway") || (Boolean)setting5.value || (Boolean)Baritone.settings().chatControl.value)) {
                     if (setting5.getName().equals("prefixControl") && !(Boolean)setting5.value) {
                        this.logDirect3(
                           "Warning: Prefixed commands will no longer work. If you want to revert this change, use chat control (if enabled) or click the old value listed above.",
                           EnumChatFormatting.RED
                        );
                     }
                  } else {
                     this.logDirect3(
                        "Warning: Chat commands will no longer work. If you want to revert this change, use prefix control (if enabled) or click the old value listed above.",
                        EnumChatFormatting.RED
                     );
                  }
               }

               SettingsUtil.save(Baritone.settings());
            }
         }
      }
   }

   public SetCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"set", "setting", "settings"});
   }

   @Override
   public List getLongDesc() {
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

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasAny()) {
         String string = argConsumer.getString();
         if (argConsumer.hasExactlyOne() && !Arrays.asList("s", "save").contains(argConsumer.peekString().toLowerCase(Locale.US))) {
            if (string.equalsIgnoreCase("reset")) {
               return new TabCompleteHelper().addModifiedSettings().prepend3(new String[]{"all"}).filterPrefix(argConsumer.getString()).getStream();
            }

            if (string.equalsIgnoreCase("toggle")) {
               return new TabCompleteHelper().addToggleableSettings().filterPrefix(argConsumer.getString()).getStream();
            }

            if (Arrays.asList("ld", "load").contains(string.toLowerCase(Locale.US))) {
               return RelativeFile.tabComplete(argConsumer, Minecraft.getMinecraft().mcDataDir.toPath().resolve("baritone").toFile());
            }

            Settings.Setting setting = (Settings.Setting)Baritone.settings().byLowerName.get(string.toLowerCase(Locale.US));
            if (setting != null) {
               if (setting.getType() == Boolean.class) {
                  TabCompleteHelper tabCompleteHelper = new TabCompleteHelper();
                  if ((Boolean)setting.value) {
                     tabCompleteHelper.append2(new String[]{"true", "false"});
                  } else {
                     tabCompleteHelper.append2(new String[]{"false", "true"});
                  }

                  return tabCompleteHelper.filterPrefix(argConsumer.getString()).getStream();
               }

               return Stream.of(SettingsUtil.settingValueToString(setting));
            }
         } else if (!argConsumer.hasAny()) {
            return new TabCompleteHelper().addSettings().sortAlphabetically().prepend3(new String[]{"list", "modified", "reset", "toggle", "save", "load"}).filterPrefix(string).getStream();
         }
      }

      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "View or change settings";
   }

}
