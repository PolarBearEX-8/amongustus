package wdl;

import wdl.api.IWDLMessageType;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WDLMessages {
   private static Logger logger = LogManager.getLogger();
   public static boolean enableAllMessages = true;
   private static List registrations = new ArrayList();

   public static void chatMessage(IWDLMessageType wdlMessageType, IChatComponent component) {
      String str = I18n.format("wdl.messages.tooltip", new Object[]{wdlMessageType.getDisplayName()}).replace("\r", "");
      ChatComponentText chatComponentText = new ChatComponentText(str);
      ChatComponentText chatComponentText2 = new ChatComponentText("");
      ChatComponentText chatComponentText3 = new ChatComponentText("[WorldDL]");
      chatComponentText3.getChatStyle().setColor(wdlMessageType.getTitleColor());
      chatComponentText3.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, chatComponentText));
      ChatComponentText chatComponentText4 = new ChatComponentText(" ");
      chatComponentText4.getChatStyle().setColor(wdlMessageType.getTextColor());
      chatComponentText4.appendSibling(component);
      chatComponentText2.appendSibling(chatComponentText3);
      chatComponentText2.appendSibling(chatComponentText4);

      try {
         if (isEnabled(wdlMessageType)) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(chatComponentText2);
         } else {
            logger.info(chatComponentText2.getUnformattedText());
         }
      } catch (Throwable e) {
      }
   }

   public static ListMultimap getTypes() {
      LinkedListMultimap linkedListMultimap = LinkedListMultimap.create();

      for (MessageRegistration messageRegistration : (Iterable<MessageRegistration>)(registrations)) {
         linkedListMultimap.put(messageRegistration.category, messageRegistration.type);
      }

      return ImmutableListMultimap.copyOf(linkedListMultimap);
   }

   private static MessageRegistration getRegistration(String str) {
      for (MessageRegistration messageRegistration : (Iterable<MessageRegistration>)(registrations)) {
         if (messageRegistration.name.equals(str)) {
            return messageRegistration;
         }
      }

      return null;
   }

   public static void resetEnabledToDefaults() {
      WDL.baseProps.setProperty("Messages.enableAll", "true");
      enableAllMessages = WDL.globalProps.getProperty("Messages.enableAll", "true").equals("true");

      for (MessageRegistration messageRegistration : (Iterable<MessageRegistration>)(registrations)) {
         WDL.baseProps.setProperty("MessageGroup." + messageRegistration.category.internalName, WDL.globalProps.getProperty("MessageGroup." + messageRegistration.category.internalName, "true"));
         WDL.baseProps.setProperty("Messages." + messageRegistration.name, WDL.globalProps.getProperty("Messages." + messageRegistration.name));
      }
   }

   public static void chatMessageTranslated(IWDLMessageType wdlMessageType, String str, Object[] array) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < array.length; i++) {
         if (array[i] instanceof Entity) {
            Entity entity = (Entity)array[i];
            String entityType = EntityUtils.getEntityType(entity);
            HoverEvent hoverEvent = null;
            String str2 = null;

            try {
               hoverEvent = entity.getDisplayName().getChatStyle().getChatHoverEvent();
               if (entity.hasCustomName()) {
                  str2 = entity.getCustomNameTag();
               }
            } catch (Exception e) {
            }

            Object obj;
            if (str2 != null) {
               obj = new ChatComponentTranslation("wdl.messages.entityTypeAndCustomName", new Object[]{entityType, str2});
            } else {
               obj = new ChatComponentText(entityType);
            }

            ((IChatComponent)obj).setChatStyle(((IChatComponent)obj).getChatStyle().setChatHoverEvent(hoverEvent));
            array[i] = obj;
         } else if (array[i] instanceof Throwable) {
            Throwable t = (Throwable)array[i];
            ChatComponentText chatComponentText = new ChatComponentText(t.toString());
            StringWriter stringWriter = new StringWriter();
            t.printStackTrace(new PrintWriter(stringWriter));
            String str3 = stringWriter.toString();
            str3 = str3.replace("\r", "").replace("\t", "    ");
            HoverEvent hoverEvent2 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(str3));
            chatComponentText.setChatStyle(chatComponentText.getChatStyle().setChatHoverEvent(hoverEvent2));
            logger.warn(t);
            array[i] = chatComponentText;
            list.add(t);
         }
      }

      chatMessage(wdlMessageType, new ChatComponentTranslation(str, array));

      for (int j = 0; j < list.size(); j++) {
         logger.warn("Exception #" + (j + 1) + ": ", (Throwable)list.get(j));
      }
   }

   public static void toggleEnabled(IWDLMessageType wdlMessageType) {
      MessageRegistration registration2 = getRegistration2(wdlMessageType);
      if (registration2 != null) {
         WDL.baseProps.setProperty("Messages." + registration2.name, Boolean.toString(!isEnabled(wdlMessageType)));
      }
   }

   private static MessageRegistration getRegistration2(IWDLMessageType wdlMessageType) {
      for (MessageRegistration messageRegistration : (Iterable<MessageRegistration>)(registrations)) {
         if (messageRegistration.type.equals(wdlMessageType)) {
            return messageRegistration;
         }
      }

      return null;
   }

   public static void chatMessage2(IWDLMessageType wdlMessageType, String str) {
      chatMessage(wdlMessageType, new ChatComponentText(str));
   }

   public static void toggleGroupEnabled(MessageTypeCategory messageTypeCategory) {
      WDL.baseProps.setProperty("MessageGroup." + messageTypeCategory.internalName, Boolean.toString(!isGroupEnabled(messageTypeCategory)));
   }

   public static boolean isGroupEnabled(MessageTypeCategory messageTypeCategory) {
      return !enableAllMessages ? false : WDL.baseProps.getProperty("MessageGroup." + messageTypeCategory.internalName, "true").equals("true");
   }

   public static boolean isEnabled(IWDLMessageType wdlMessageType) {
      if (wdlMessageType == null) {
         return false;
      } else if (!enableAllMessages) {
         return false;
      } else {
         MessageRegistration registration2 = getRegistration2(wdlMessageType);
         if (registration2 == null) {
            return false;
         } else if (!isGroupEnabled(registration2.category)) {
            return false;
         } else {
            if (!WDL.baseProps.containsKey("Messages." + registration2.name)) {
               if (WDL.baseProps.containsKey("Debug." + registration2.name)) {
                  WDL.baseProps.put("Messages." + registration2.name, WDL.baseProps.remove("Debug." + registration2.name));
               } else {
                  WDL.baseProps.setProperty("Messages." + registration2.name, Boolean.toString(registration2.type.isEnabledByDefault()));
               }
            }

            return WDL.baseProps.getProperty("Messages." + registration2.name).equals("true");
         }
      }
   }

   public static void registerMessage(String str, IWDLMessageType wdlMessageType, MessageTypeCategory messageTypeCategory) {
      registrations.add(new MessageRegistration(str, wdlMessageType, messageTypeCategory));
      WDL.defaultProps.setProperty("Messages." + str, Boolean.toString(wdlMessageType.isEnabledByDefault()));
      WDL.defaultProps.setProperty("MessageGroup." + messageTypeCategory.internalName, "true");
   }

   public static void onNewServer() {
      if (!WDL.baseProps.containsKey("Messages.enableAll")) {
         if (WDL.baseProps.containsKey("Debug.globalDebugEnabled")) {
            WDL.baseProps.put("Messages.enableAll", WDL.baseProps.remove("Debug.globalDebugEnabled"));
         } else {
            WDL.baseProps.setProperty("Messages.enableAll", WDL.globalProps.getProperty("Messages.enableAll", "true"));
         }
      }

      enableAllMessages = WDL.baseProps.getProperty("Messages.enableAll").equals("true");
   }
}
