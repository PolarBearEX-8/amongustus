package cc.baritone.utils;

import client.setting.ClientSettingsShowTextType;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.HoverEventActionManager;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerEntry {
   public static Logger logger = LogManager.getLogger();
   public static boolean enabled = true;
   public static List list = new java.util.ArrayList();

   public static void applyText(String text, AltListener altListener, CORERECOMMENDEDManager cORERECOMMENDEDManager) {
      list.add(new MessageRegistrationNameManager(text, altListener, cORERECOMMENDEDManager));
      Pokechu22WorldDownloaderManager.properties.setProperty("Messages." + text, Boolean.toString(altListener.isEnabled()));
      Pokechu22WorldDownloaderManager.properties.setProperty("MessageGroup." + cORERECOMMENDEDManager.labelText, "true");
   }

   public static void applyCORERECOMMENDEDManager(CORERECOMMENDEDManager cORERECOMMENDEDManager) {
      Pokechu22WorldDownloaderManager.properties4.setProperty("MessageGroup." + cORERECOMMENDEDManager.labelText, Boolean.toString(!matchesCORERECOMMENDEDManager(cORERECOMMENDEDManager)));
   }

   public static void applyAltListener(AltListener altListener, String text, Object[] entryArray) {
      java.util.ArrayList list2 = new java.util.ArrayList();

      for (int index = 0; index < entryArray.length; index++) {
         if (entryArray[index] instanceof Entity) {
            Entity entity = (Entity)entryArray[index];
            String text2 = AStarCoordinator.resolveText(entity, (char)0, 0x21ff5cb16150L);
            HoverEventActionManager event = null;
            String text3 = null;

            try {
               event = entity.getChatComponent().getStyleHasParentManager().getHoverEventActionManager();
               if (entity.processAction()) {
                  text3 = entity.getText2();
               }
            } catch (Exception exception) {
            }

            Object entry;
            if (text3 != null) {
               entry = new BaseManagerStyleIChatComponentAdapter("wdl.messages.entityTypeAndCustomName", new Object[]{text2, text3});
            } else {
               entry = new ChatComponentText(text2);
            }

            ((IChatComponent)entry).getIChat(((IChatComponent)entry).getStyleHasParentManager().getStyleHasParentManager(event));
            entryArray[index] = entry;
         } else if (entryArray[index] instanceof Throwable) {
            Throwable throwable = (Throwable)entryArray[index];
            ChatComponentText chatComponentText = new ChatComponentText(throwable.toString());
            StringWriter stringWriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringWriter));
            String text4 = stringWriter.toString().replace("\r", "").replace("\t", "    ");
            HoverEventActionManager event2 = new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, new ChatComponentText(text4));
            chatComponentText.getIChat(chatComponentText.getStyleHasParentManager().getStyleHasParentManager(event2));
            logger.warn(throwable);
            entryArray[index] = chatComponentText;
            list2.add(throwable);
         }
      }

      applyAltListener(altListener, new BaseManagerStyleIChatComponentAdapter(text, entryArray));

      for (int index2 = 0; index2 < list2.size(); index2++) {
         logger.warn("Exception #" + (index2 + 1) + ": ", (Throwable)list2.get(index2));
      }
   }

   public static void updateState() {
      if (!Pokechu22WorldDownloaderManager.properties4.containsKey("Messages.enableAll")) {
         if (Pokechu22WorldDownloaderManager.properties4.containsKey("Debug.globalDebugEnabled")) {
            Pokechu22WorldDownloaderManager.properties4
               .put("Messages.enableAll", Pokechu22WorldDownloaderManager.properties4.remove("Debug.globalDebugEnabled"));
         } else {
            Pokechu22WorldDownloaderManager.properties4
               .setProperty("Messages.enableAll", Pokechu22WorldDownloaderManager.properties3.getProperty("Messages.enableAll", "true"));
         }
      }

      enabled = Pokechu22WorldDownloaderManager.properties4.getProperty("Messages.enableAll").equals("true");
   }

   private static MessageRegistrationNameManager resolveMessageRegistrationNameManag(AltListener altListener) {
      for (MessageRegistrationNameManager messageRegistrationNameManager : (Iterable<MessageRegistrationNameManager>)(Iterable<?>)list) {
         if (messageRegistrationNameManager.altListener.equals(altListener)) {
            return messageRegistrationNameManager;
         }
      }

      return null;
   }

   public static void applyAltListener(AltListener altListener) {
      MessageRegistrationNameManager messageRegistrationNameManager = resolveMessageRegistrationNameManag(altListener);
      if (messageRegistrationNameManager != null) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Messages." + messageRegistrationNameManager.labelText, Boolean.toString(!matchesAltListener(altListener)));
      }
   }

   public static boolean matchesAltListener(AltListener altListener) {
      if (altListener == null) {
         return false;
      } else if (!enabled) {
         return false;
      } else {
         MessageRegistrationNameManager messageRegistrationNameManager = resolveMessageRegistrationNameManag(altListener);
         if (messageRegistrationNameManager == null) {
            return false;
         } else if (!matchesCORERECOMMENDEDManager(messageRegistrationNameManager.cORERECOMMENDEDManager)) {
            return false;
         } else {
            if (!Pokechu22WorldDownloaderManager.properties4.containsKey("Messages." + messageRegistrationNameManager.labelText)) {
               if (Pokechu22WorldDownloaderManager.properties4.containsKey("Debug." + messageRegistrationNameManager.labelText)) {
                  Pokechu22WorldDownloaderManager.properties4
                     .put("Messages." + messageRegistrationNameManager.labelText, Pokechu22WorldDownloaderManager.properties4.remove("Debug." + messageRegistrationNameManager.labelText));
               } else {
                  Pokechu22WorldDownloaderManager.properties4.setProperty("Messages." + messageRegistrationNameManager.labelText, Boolean.toString(messageRegistrationNameManager.altListener.isEnabled()));
               }
            }

            return Pokechu22WorldDownloaderManager.properties4.getProperty("Messages." + messageRegistrationNameManager.labelText).equals("true");
         }
      }
   }

   public static void applyAltListener(AltListener altListener, String text) {
      applyAltListener(altListener, new ChatComponentText(text));
   }

   private static MessageRegistrationNameManager resolveMessageRegistrationNameManag(String text) {
      for (MessageRegistrationNameManager messageRegistrationNameManager : (Iterable<MessageRegistrationNameManager>)(Iterable<?>)list) {
         if (messageRegistrationNameManager.labelText.equals(text)) {
            return messageRegistrationNameManager;
         }
      }

      return null;
   }

   public static boolean matchesCORERECOMMENDEDManager(CORERECOMMENDEDManager cORERECOMMENDEDManager) {
      return !enabled ? false : Pokechu22WorldDownloaderManager.properties4.getProperty("MessageGroup." + cORERECOMMENDEDManager.labelText, "true").equals("true");
   }

   public static void applyByte(byte byteValue, int count, int count2) {
      Pokechu22WorldDownloaderManager.properties4.setProperty("Messages.enableAll", "true");
      enabled = Pokechu22WorldDownloaderManager.properties3.getProperty("Messages.enableAll", "true").equals("true");

      for (MessageRegistrationNameManager messageRegistrationNameManager : (Iterable<MessageRegistrationNameManager>)(Iterable<?>)list) {
         Pokechu22WorldDownloaderManager.properties4
            .setProperty(
               "MessageGroup." + messageRegistrationNameManager.cORERECOMMENDEDManager.labelText,
               Pokechu22WorldDownloaderManager.properties3.getProperty("MessageGroup." + messageRegistrationNameManager.cORERECOMMENDEDManager.labelText, "true")
            );
         Pokechu22WorldDownloaderManager.properties4
            .setProperty("Messages." + messageRegistrationNameManager.labelText, Pokechu22WorldDownloaderManager.properties3.getProperty("Messages." + messageRegistrationNameManager.labelText));
      }
   }

   public static ListMultimap getListMultimap() {
      LinkedListMultimap linkedListMultimap = LinkedListMultimap.create();

      for (MessageRegistrationNameManager messageRegistrationNameManager : (Iterable<MessageRegistrationNameManager>)(Iterable<?>)list) {
         linkedListMultimap.put(messageRegistrationNameManager.cORERECOMMENDEDManager, messageRegistrationNameManager.altListener);
      }

      return ImmutableListMultimap.copyOf(linkedListMultimap);
   }

   public static void applyAltListener(AltListener altListener, IChatComponent iChatComponent) {
      String text = StatCollector.getText("wdl.messages.tooltip", new Object[]{altListener.getText()}).replace("\r", "");
      ChatComponentText chatComponentText = new ChatComponentText(text);
      ChatComponentText chatComponentText2 = new ChatComponentText("");
      ChatComponentText chatComponentText3 = new ChatComponentText("[WorldDL]");
      chatComponentText3.getStyleHasParentManager().withClientSettingsBlackType(altListener.getClientSettingsBlackType());
      chatComponentText3.getStyleHasParentManager().getStyleHasParentManager(new HoverEventActionManager(ClientSettingsShowTextType.SHOW_TEXT, chatComponentText));
      ChatComponentText chatComponentText4 = new ChatComponentText(" ");
      chatComponentText4.getStyleHasParentManager().withClientSettingsBlackType(altListener.getClientSettingsBlackType2());
      chatComponentText4.appendChatComponent(iChatComponent);
      chatComponentText2.appendChatComponent(chatComponentText3);
      chatComponentText2.appendChatComponent(chatComponentText4);

      try {
         if (matchesAltListener(altListener)) {
            Minecraft.getMinecraft().ingameGUI.getInternalLoggerEntry().applyIChatComponent(chatComponentText2);
         } else {
            logger.info(chatComponentText2.getString());
         }
      } catch (Throwable throwable) {
      }
   }
}
