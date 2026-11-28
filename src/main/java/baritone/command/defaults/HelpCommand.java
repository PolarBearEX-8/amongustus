package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.ICommand;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandNotFoundException;
import baritone.api.command.helpers.Paginator;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.IBaritoneChatControl;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class HelpCommand extends Command {

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(1);
      if (argConsumer.hasAny() && !argConsumer.is(Integer.class)) {
         String str2 = argConsumer.getString().toLowerCase();
         ICommand command = this.baritone.getCommandManager().getCommand(str2);
         if (command == null) {
            throw new CommandNotFoundException(str2);
         }

         this.logDirect2(String.format("%s - %s", String.join(" / ", command.getNames()), command.getShortDesc()));
         this.logDirect2("");
         command.getLongDesc().forEach(obj -> this.logDirect2((String)obj));
         this.logDirect2("");
         ChatComponentText chatComponentText = new ChatComponentText("Click to return to the help menu");
         chatComponentText.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + str));
         this.logDirect(new IChatComponent[]{chatComponentText});
      } else {
         Paginator.paginate3(
            argConsumer,
            new Paginator((List)this.baritone.getCommandManager().getRegistry().descendingStream().filter(obj2 -> !((ICommand)obj2).hiddenFromHelp()).collect(Collectors.toList())),
            () -> this.logDirect2("All Baritone commands (clickable):"),
            obj3 -> {
               ICommand var1c = (ICommand)obj3;
               String str3 = String.join("/", var1c.getNames());
               String str4 = (String)var1c.getNames().get(0);
               ChatComponentText chatComponentText2 = new ChatComponentText(" - " + var1c.getShortDesc());
               chatComponentText2.getChatStyle().setColor(EnumChatFormatting.DARK_GRAY);
               ChatComponentText chatComponentText3 = new ChatComponentText(str3);
               chatComponentText3.getChatStyle().setColor(EnumChatFormatting.WHITE);
               ChatComponentText chatComponentText4 = new ChatComponentText("");
               chatComponentText4.getChatStyle().setColor(EnumChatFormatting.GRAY);
               chatComponentText4.appendSibling(chatComponentText3);
               chatComponentText4.appendText("\n" + var1c.getShortDesc());
               chatComponentText4.appendText("\n\nClick to view full help");
               String str5 = IBaritoneChatControl.FORCE_COMMAND_PREFIX + String.format("%s %s", str, var1c.getNames().get(0));
               ChatComponentText chatComponentText5 = new ChatComponentText(str4);
               chatComponentText5.getChatStyle().setColor(EnumChatFormatting.GRAY);
               chatComponentText5.appendSibling(chatComponentText2);
               chatComponentText5.getChatStyle()
                  .setChatHoverEvent(new HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, chatComponentText4))
                  .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, str5));
               return chatComponentText5;
            },
            IBaritoneChatControl.FORCE_COMMAND_PREFIX + str
         );
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "Using this command, you can view detailed help information on how to use certain commands of Baritone.",
         "",
         "Usage:",
         "> help - Lists all commands and their short descriptions.",
         "> help <command> - Displays help information on a specific command."
      );
   }

   public HelpCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"help", "?"});
   }

   @Override
   public String getShortDesc() {
      return "View all commands or help on specific ones";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      return argConsumer.hasExactlyOne() ? new TabCompleteHelper().addCommands(this.baritone.getCommandManager()).filterPrefix(argConsumer.getString()).getStream() : Stream.empty();
   }

}
