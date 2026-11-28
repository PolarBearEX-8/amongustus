package baritone.api.command.helpers;

import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.utils.Helper;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class Paginator implements Helper {
   public final List entries;
   public int pageSize = 8;
   public int page = 1;

   public void display(Function function, String str) {
      int n = (this.page - 1) * this.pageSize;

      for (int j = n; j < n + this.pageSize; j++) {
         if (j < this.entries.size()) {
            this.logDirect(new IChatComponent[]{(IChatComponent)function.apply(this.entries.get(j))});
         } else {
            this.logDirect3("--", EnumChatFormatting.DARK_GRAY);
         }
      }

      boolean flag = str != null && this.validPage(this.page - 1);
      boolean flag2 = str != null && this.validPage(this.page + 1);
      ChatComponentText chatComponentText = new ChatComponentText("<<");
      if (flag) {
         chatComponentText.getChatStyle()
            .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("%s %d", str, this.page - 1)))
            .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to view previous page")));
      } else {
         chatComponentText.getChatStyle().setColor(EnumChatFormatting.DARK_GRAY);
      }

      ChatComponentText chatComponentText2 = new ChatComponentText(">>");
      if (flag2) {
         chatComponentText2.getChatStyle()
            .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("%s %d", str, this.page + 1)))
            .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to view next page")));
      } else {
         chatComponentText2.getChatStyle().setColor(EnumChatFormatting.DARK_GRAY);
      }

      ChatComponentText chatComponentText3 = new ChatComponentText("");
      chatComponentText3.getChatStyle().setColor(EnumChatFormatting.GRAY);
      chatComponentText3.appendSibling(chatComponentText);
      chatComponentText3.appendText(" | ");
      chatComponentText3.appendSibling(chatComponentText2);
      chatComponentText3.appendText(String.format(" %d/%d", this.page, this.getMaxPage()));
      this.logDirect(new IChatComponent[]{chatComponentText3});
   }

   public void display(Function function) {
      this.display(function, null);
   }

   public static void paginate(IArgConsumer argConsumer, List list, Runnable runnable, Function function) throws CommandException {
      paginate3(argConsumer, new Paginator(list), runnable, function, null);
   }

   public static void paginate5(IArgConsumer argConsumer, Object[] array, Function function, String str) throws CommandException {
      paginate2(argConsumer, Arrays.asList(array), null, function, str);
   }

   public boolean validPage(int n) {
      return n > 0 && n <= this.getMaxPage();
   }

   public Paginator skipPages(int n) {
      this.page += n;
      return this;
   }

   public static void paginate3(IArgConsumer argConsumer, List list, Function function) throws CommandException {
      paginate3(argConsumer, new Paginator(list), null, function, null);
   }

   public static void paginate2(IArgConsumer argConsumer, Paginator paginator, Function function) throws CommandException {
      paginate3(argConsumer, paginator, null, function, null);
   }

   public static void paginate(IArgConsumer argConsumer, Object[] array, Function function) throws CommandException {
      paginate2(argConsumer, Arrays.asList(array), null, function, null);
   }

   public static void paginate6(IArgConsumer argConsumer, Paginator paginator, Runnable runnable, Function function) throws CommandException {
      paginate3(argConsumer, paginator, runnable, function, null);
   }

   public static void paginate3(IArgConsumer argConsumer, Paginator paginator, Runnable runnable, Function function, String str) throws CommandException {
      int n = 1;
      argConsumer.requireMax(1);
      if (argConsumer.hasAny()) {
         n = (Integer)argConsumer.getAs(Integer.class);
         if (!paginator.validPage(n)) {
            throw new CommandInvalidTypeException(argConsumer.consumed(), String.format("a valid page (1-%d)", paginator.getMaxPage()), argConsumer.consumed().getValue());
         }
      }

      paginator.skipPages(n - paginator.page);
      if (runnable != null) {
         runnable.run();
      }

      paginator.display(function, str);
   }

   public Paginator setPageSize(int n) {
      this.pageSize = n;
      return this;
   }

   public Paginator(List list) {
      this.entries = list;
   }

   public static void paginate(IArgConsumer argConsumer, Object[] array, Runnable runnable, Function function, String str) throws CommandException {
      paginate2(argConsumer, Arrays.asList(array), runnable, function, str);
   }

   public static void paginate4(IArgConsumer argConsumer, Object[] array, Runnable runnable, Function function) throws CommandException {
      paginate2(argConsumer, Arrays.asList(array), runnable, function, null);
   }

   public static void paginate2(IArgConsumer argConsumer, Paginator paginator, Function function, String str) throws CommandException {
      paginate3(argConsumer, paginator, null, function, str);
   }

   public static void paginate2(IArgConsumer argConsumer, List list, Runnable runnable, Function function, String str) throws CommandException {
      paginate3(argConsumer, new Paginator(list), runnable, function, str);
   }

   public Paginator(Object[] array) {
      this.entries = Arrays.asList(array);
   }

   public int getMaxPage() {
      return (this.entries.size() - 1) / this.pageSize + 1;
   }

   public static void paginate3(IArgConsumer argConsumer, List list, Function function, String str) throws CommandException {
      paginate3(argConsumer, new Paginator(list), null, function, str);
   }
}
