package baritone.command;

import baritone.api.command.IBaritoneChatControl;
import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.Settings;
import baritone.api.command.exception.CommandNotEnoughArgumentsException;
import baritone.api.command.exception.CommandNotFoundException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.command.manager.ICommandManager;
import baritone.api.event.events.ChatEvent;
import baritone.api.event.events.TabCompleteEvent;
import baritone.api.utils.Helper;
import baritone.api.utils.SettingsUtil;
import baritone.behavior.Behavior;
import baritone.command.argument.ArgConsumer;
import baritone.command.argument.CommandArguments;
import baritone.command.manager.CommandManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Tuple;

public class ExampleBaritoneControl extends Behavior implements Helper {
   private static final Settings settings = BaritoneAPI.getSettings();
   private final ICommandManager manager;

   @Override
   public void onSendChatMessage(ChatEvent chatEvent) {
      String message = chatEvent.getMessage();
      String value = (String)settings.prefix.value;
      boolean flag = message.startsWith(IBaritoneChatControl.FORCE_COMMAND_PREFIX);
      if ((!(Boolean)settings.prefixControl.value || !message.startsWith(value)) && !flag) {
         if (((Boolean)settings.chatControl.value || (Boolean)settings.chatControlAnyway.value) && this.runCommand(message)) {
            chatEvent.cancel();
         }
      } else {
         chatEvent.cancel();
         String str = message.substring(flag ? IBaritoneChatControl.FORCE_COMMAND_PREFIX.length() : value.length());
         if (!this.runCommand(str) && !str.trim().isEmpty()) {
            new CommandNotFoundException((String)CommandManager.expand(str).getFirst()).handle(null, null);
         }
      }
   }

   public ExampleBaritoneControl(Baritone baritone2) {
      super(baritone2);
      this.manager = baritone2.getCommandManager2();
   }

   public Stream tabComplete(String str) {
      try {
         List list = CommandArguments.from(str, true);
         ArgConsumer argConsumer = new ArgConsumer(this.manager, list);
         if (argConsumer.hasAtMost(2)) {
            if (argConsumer.hasExactly(1)) {
               return new TabCompleteHelper().addCommands(this.manager).addSettings().filterPrefix(argConsumer.getString()).getStream();
            }

            Settings.Setting setting = (Settings.Setting)settings.byLowerName.get(argConsumer.getString().toLowerCase(Locale.US));
            if (setting != null && !setting.isJavaOnly()) {
               if (setting.getValueClass() == Boolean.class) {
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
         }

         return this.manager.tabComplete2(str);
      } catch (CommandNotEnoughArgumentsException e) {
         return Stream.empty();
      }
   }

   public boolean runCommand(String str) {
      if (str.trim().equalsIgnoreCase("damn")) {
         this.logDirect2("daniel");
         return false;
      } else if (str.trim().equalsIgnoreCase("orderpizza")) {
         try {
            this.ctx.getMc().currentScreen.openWebLink(new URI("https://www.dominos.com/en/pages/order/"));
         } catch (URISyntaxException | NullPointerException e) {
         }

         return false;
      } else if (str.isEmpty()) {
         return this.runCommand("help");
      } else {
         Tuple tuple = CommandManager.expand(str);
         String first = (String)tuple.getFirst();
         String str2 = str.substring(((String)tuple.getFirst()).length());
         ArgConsumer argConsumer = new ArgConsumer(this.manager, (List)tuple.getSecond());
         if (!argConsumer.hasAny()) {
            Settings.Setting setting = (Settings.Setting)settings.byLowerName.get(first.toLowerCase(Locale.US));
            if (setting != null) {
               this.logRanCommand(first, str2);
               if (setting.getValueClass() == Boolean.class) {
                  this.manager.execute(String.format("set toggle %s", setting.getName()));
               } else {
                  this.manager.execute(String.format("set %s", setting.getName()));
               }

               return true;
            }
         } else if (argConsumer.hasExactlyOne()) {
            for (Settings.Setting setting2 : settings.allSettings) {
               if (!setting2.isJavaOnly() && setting2.getName().equalsIgnoreCase((String)tuple.getFirst())) {
                  this.logRanCommand(first, str2);

                  try {
                     this.manager.execute(String.format("set %s %s", setting2.getName(), argConsumer.getString()));
                  } catch (CommandNotEnoughArgumentsException e2) {
                  }

                  return true;
               }
            }
         }

         if (this.manager.getCommand((String)tuple.getFirst()) != null) {
            this.logRanCommand(first, str2);
         }

         return this.manager.execute2(tuple);
      }
   }

   @Override
   public void onPreTabComplete(TabCompleteEvent tabCompleteEvent) {
      if ((Boolean)settings.prefixControl.value) {
         String prefix = tabCompleteEvent.prefix;
         String value = (String)settings.prefix.value;
         if (prefix.startsWith(value)) {
            String str = prefix.substring(value.length());
            List list = CommandArguments.from(str, true);
            Stream stream = this.tabComplete(str);
            if (list.size() == 1) {
               stream = stream.map(obj -> value + obj);
            }

            tabCompleteEvent.completions = (String[])stream.toArray(String[]::new);
         }
      }
   }

   private void logRanCommand(String str, String str2) {
      if ((Boolean)settings.echoCommands.value) {
         String str3 = str + str2;
         String str4 = (Boolean)settings.censorRanCommands.value ? str + " ..." : str3;
         ChatComponentText chatComponentText = new ChatComponentText(String.format("> %s", str4));
         chatComponentText.getChatStyle()
            .setColor(EnumChatFormatting.WHITE)
            .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to rerun command")))
            .setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + str3));
         this.logDirect(new IChatComponent[]{chatComponentText});
      }
   }

}
