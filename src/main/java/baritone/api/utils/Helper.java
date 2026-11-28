package baritone.api.utils;

import baritone.api.BaritoneAPI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public interface Helper {
   Helper HELPER = new Helper.DefaultHelper();
   @Deprecated
   Minecraft mc = Minecraft.getMinecraft();

   default void logDirectLine(EnumChatFormatting enumChatFormatting, boolean flag, String str) {
      ChatComponentText chatComponentText = new ChatComponentText(str.replace("\t", "    "));
      chatComponentText.getChatStyle().setColor(enumChatFormatting);
      this.logDirect2(flag, new IChatComponent[]{chatComponentText});
   }

   default void logDebug(String str) {
      if ((Boolean)BaritoneAPI.getSettings().chatDebug.value) {
         this.logDirect(str, false);
      }
   }

   default void logDirect2(boolean flag, IChatComponent[] components) {
      ChatComponentText chatComponentText = new ChatComponentText("");
      if (!flag) {
         chatComponentText.appendSibling(getPrefix());
         chatComponentText.appendSibling(new ChatComponentText(" "));
      }

      Arrays.asList(components).forEach(chatComponentText::appendSibling);
      if (flag) {
         this.logToast(getPrefix(), chatComponentText);
      } else {
         Minecraft minecraft = Minecraft.getMinecraft();
         Runnable runnable = () -> dispatchChatMessage(chatComponentText);
         minecraft.addScheduledTask(runnable);
      }
   }

   default void logDirect(IChatComponent[] components) {
      this.logDirect2((Boolean)BaritoneAPI.getSettings().logAsToast.value, components);
   }

   default void logToast(IChatComponent component, IChatComponent component2) {
   }

   default void logDirect(String str, boolean flag) {
      this.logDirect(str, EnumChatFormatting.GRAY, flag);
   }

   default void logToast(String str) {
      this.logToast(getPrefix(), new ChatComponentText(str));
   }

   default void logDirect(String str, EnumChatFormatting enumChatFormatting, boolean flag) {
      Stream.of(str.split("\n")).forEach(str2 -> this.logDirectLine(enumChatFormatting, flag, str2));
   }

   static IChatComponent getPrefix() {
      Calendar instance = Calendar.getInstance();
      boolean flag = instance.get(2) == 3 && instance.get(5) <= 3;
      ChatComponentText chatComponentText = new ChatComponentText(flag ? "Baritoe" : ((Boolean)BaritoneAPI.getSettings().shortBaritonePrefix.value ? "B" : "Baritone"));
      chatComponentText.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
      ChatComponentText chatComponentText2 = new ChatComponentText("");
      chatComponentText2.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);
      chatComponentText2.appendText("[");
      chatComponentText2.appendSibling(chatComponentText);
      chatComponentText2.appendText("]");
      return chatComponentText2;
   }

   default void logDirect3(String str, EnumChatFormatting enumChatFormatting) {
      this.logDirect(str, enumChatFormatting, (Boolean)BaritoneAPI.getSettings().logAsToast.value);
   }

   default void logNotification(String str, boolean flag) {
      if ((Boolean)BaritoneAPI.getSettings().desktopNotifications.value) {
         this.logNotificationDirect(str, flag);
      }
   }

   default void logDirect2(String str) {
      this.logDirect(str, (Boolean)BaritoneAPI.getSettings().logAsToast.value);
   }

   default void logToast2(String str, String str2) {
      this.logToast(new ChatComponentText(str), new ChatComponentText(str2));
   }

   default void logUnhandledException(Throwable t) {
      HELPER.logDirect3(
         "An unhandled exception occurred. The error is in your game's log, please report this at https://github.com/cabaletta/baritone/issues",
         EnumChatFormatting.RED
      );
      t.printStackTrace();
   }

   static void dispatchNotification(String str, boolean flag) {
      ((BiConsumer)BaritoneAPI.getSettings().notifier.value).accept(str, flag);
   }

   static void dispatchChatMessage(IChatComponent component) {
      ((Consumer)BaritoneAPI.getSettings().logger.value).accept(component);
   }

   default void logNotificationDirect(String str) {
      this.logNotificationDirect(str, false);
   }

   default void logNotification(String str) {
      this.logNotification(str, false);
   }

   default void logNotificationDirect(String str, boolean flag) {
      Minecraft minecraft = Minecraft.getMinecraft();
      Runnable runnable = () -> dispatchNotification(str, flag);
      minecraft.addScheduledTask(runnable);
   }



   public static final class DefaultHelper implements Helper {
   }
}
