package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;
import java.util.Arrays;
import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public interface DRNHandler {
   DRNHandler dRNHandler = new DRNHandlerAdapter();
   @Deprecated
   Minecraft mc = Minecraft.getMinecraft();

   default void applyText(String prefix, String message) {
      this.applyIChatComponent(new ChatComponentText(prefix), new ChatComponentText(message));
   }

   static void applyText2(String message, boolean useToast) {
      ((BiConsumer)SettingsTxtManager.getSettings().notifier.dataRef).accept(message, useToast);
   }

   default void applyText2(String message) {
      this.applyIChatComponent(getIChatComponent(), new ChatComponentText(message));
   }

   default void applyIChatComponentArray(IChatComponent[] components) {
      this.applyIChatComponentArray((Boolean)SettingsTxtManager.getSettings().logAsToast.dataRef, components);
   }

   default void applyTextByText(String message, boolean useToast) {
      Minecraft minecraftInstance = Minecraft.getMinecraft();
      Runnable task = () -> DRNHandler.applyText2(message, useToast);
      minecraftInstance.resolveListenableFuture(task);
   }

   default void applyClientSettingsBlackType(ClientSettingsBlackType color, boolean useToast, String line) {
      ChatComponentText formatted = new ChatComponentText(line.replace("\t", "    "));
      formatted.getStyleHasParentManager().withClientSettingsBlackType(color);
      this.applyIChatComponentArray(useToast, new IChatComponent[]{formatted});
   }

   default void applyIChatComponent(IChatComponent first, IChatComponent second) {
   }

   default void applyThrowable(Throwable throwable) {
      dRNHandler.applyText(
         "An unhandled exception occurred. The error is in your game's log, please report this at https://github.com/cabaletta/baritone/issues",
         ClientSettingsBlackType.RED
      );
      throwable.printStackTrace();
   }

   default void applyText3(String message) {
      this.applyTextByText(message, false);
   }

   default void applyText4(String message) {
      this.applyText(message, false);
   }

   static IChatComponent getIChatComponent() {
      Calendar calendar = Calendar.getInstance();
      boolean aprilFools = calendar.get(2) == 3 && calendar.get(5) <= 3;
      ChatComponentText tagComponent = new ChatComponentText(
         aprilFools ? "Baritoe" : ((Boolean)SettingsTxtManager.getSettings().shortBaritonePrefix.dataRef ? "B" : "Baritone")
      );
      tagComponent.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.LIGHT_PURPLE);
      ChatComponentText wrapperComponent = new ChatComponentText("");
      wrapperComponent.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_PURPLE);
      wrapperComponent.resolveIChatComponent("[");
      wrapperComponent.appendChatComponent(tagComponent);
      wrapperComponent.resolveIChatComponent("]");
      return wrapperComponent;
   }

   default void applyText3(String message, boolean useToast) {
      this.applyText(message, ClientSettingsBlackType.GRAY, useToast);
   }

   default void applyIChatComponentArray(boolean useToast, IChatComponent[] components) {
      ChatComponentText wrapperComponent = new ChatComponentText("");
      if (!useToast) {
         wrapperComponent.appendChatComponent(getIChatComponent());
         wrapperComponent.appendChatComponent(new ChatComponentText(" "));
      }

      Arrays.asList(components).forEach(wrapperComponent::appendChatComponent);
      if (useToast) {
         this.applyIChatComponent(getIChatComponent(), wrapperComponent);
      } else {
         Minecraft minecraftInstance = Minecraft.getMinecraft();
         Runnable task = () -> DRNHandler.applyIChatComponent(wrapperComponent);
         minecraftInstance.resolveListenableFuture(task);
      }
   }

   default void applyText(String message) {
      this.applyText3(message, (Boolean)SettingsTxtManager.getSettings().logAsToast.dataRef);
   }

   default void applyText(String message, ClientSettingsBlackType color) {
      this.applyText(message, color, (Boolean)SettingsTxtManager.getSettings().logAsToast.dataRef);
   }

   default void applyText(String message, boolean useToast) {
      if ((Boolean)SettingsTxtManager.getSettings().desktopNotifications.dataRef) {
         this.applyTextByText(message, useToast);
      }
   }

   default void applyText(String message, ClientSettingsBlackType color, boolean useToast) {
      Stream.of(message.split("\n")).forEach(line -> this.applyClientSettingsBlackType(color, useToast, line));
   }

   static void applyIChatComponent(IChatComponent component) {
      ((Consumer)SettingsTxtManager.getSettings().logger.dataRef).accept(component);
   }

   default void applyTextByText(String message) {
      if ((Boolean)SettingsTxtManager.getSettings().chatDebug.dataRef) {
         this.applyText3(message, false);
      }
   }
}
