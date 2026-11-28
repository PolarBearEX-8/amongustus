package net.augustus.utils;

import net.augustus.Augustus;
import com.viaversion.viaversion.libs.mcstructs.text.TextComponent;
import com.viaversion.viaversion.libs.mcstructs.text.serializer.TextComponentSerializer;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.StringFormat;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.handling.ColorHandling;
import com.viaversion.viaversion.libs.mcstructs.text.stringformat.handling.DeserializerUnknownHandling;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.IChatComponent;

public class ChatUtil implements MC {

   private static void printRawChat(String str) {
      if (mc != null && mc.thePlayer != null && mc.theWorld != null && mc.ingameGUI != null && mc.ingameGUI.getChatGUI() != null) {
         IChatComponent component = parseChatComponent(str);
         if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            mc.ingameGUI.getChatGUI().printChatMessage(component);
         } else {
            PacketThreadUtil.runOnMainThreadImmediate(() -> mc.ingameGUI.getChatGUI().printChatMessage(component));
         }
      }
   }

   public static void sendPrefixedChat(String prefix, String message) {
      printRawChat("§6[§9" + prefix + "§6] §7" + message);
   }

   public static String sendFormattedChat(String prefix, String message) {
      String str3 = "§6[§9" + prefix + "§6] §r" + message;
      printRawChat(str3);
      return str3;
   }

   public static IChatComponent parseChatComponent(String str) {
      TextComponent textComponent = StringFormat.vanilla().fromString(str, ColorHandling.RESET, DeserializerUnknownHandling.WHITE);
      return IChatComponent.Serializer.jsonToComponent(TextComponentSerializer.V1_8.serialize(textComponent));
   }

   public static void sendChat(String message) {
      sendPrefixedChat(Augustus.getInstance().getName(), message);
   }

   public static void sendChat(Object obj) {
      sendChat(obj + "");
   }

   public static void sendChat(float f) {
      sendChat(f + "");
   }

   public static void sendChat(double d) {
      sendChat(d + "");
   }


}
