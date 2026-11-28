package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class GuiUtilRenderComponents {
   public static List splitText(IChatComponent component, int n, FontRenderer fontRenderer, boolean flag, boolean flag2) {
      int n2 = 0;
      ChatComponentText chatComponentText = new ChatComponentText("");
      ArrayList list = Lists.newArrayList();
      ArrayList list2 = Lists.newArrayList(component);

      for (int i = 0; i < list2.size(); i++) {
         IChatComponent component2 = (IChatComponent)list2.get(i);
         String unformattedTextForChat = component2.getUnformattedTextForChat();
         unformattedTextForChat = fontRenderer.applyTextFilters(unformattedTextForChat);
         boolean flag3 = false;
         if (unformattedTextForChat.contains("\n")) {
            int n3 = unformattedTextForChat.indexOf(10);
            String str = unformattedTextForChat.substring(n3 + 1);
            unformattedTextForChat = unformattedTextForChat.substring(0, n3 + 1);
            ChatComponentText chatComponentText2 = new ChatComponentText(str);
            chatComponentText2.setChatStyle(component2.getChatStyle().createShallowCopy());
            list2.add(i + 1, chatComponentText2);
            flag3 = true;
         }

         String str2 = removeTextColorsIfConfigured(component2.getChatStyle().getFormattingCode() + unformattedTextForChat, flag2);
         String str3 = str2.endsWith("\n") ? str2.substring(0, str2.length() - 1) : str2;
         int stringWidthFloat = (int)fontRenderer.getStringWidthFloat(str3);
         ChatComponentText chatComponentText3 = new ChatComponentText(str3);
         chatComponentText3.setChatStyle(component2.getChatStyle().createShallowCopy());
         if (n2 + stringWidthFloat > n) {
            String str4 = fontRenderer.trimStringToWidth(str2, n - n2, false);
            String str5 = str4.length() < str2.length() ? str2.substring(str4.length()) : null;
            if (str5 != null && str5.length() > 0) {
               int n4 = str4.lastIndexOf(" ");
               if (n4 >= 0 && fontRenderer.getStringWidthFloat(str2.substring(0, n4)) > 0.0) {
                  str4 = str2.substring(0, n4);
                  if (flag) {
                     n4++;
                  }

                  str5 = str2.substring(n4);
               } else if (n2 > 0 && !str2.contains(" ")) {
                  str4 = "";
                  str5 = str2;
               }

               ChatComponentText chatComponentText4 = new ChatComponentText(str5);
               chatComponentText4.setChatStyle(component2.getChatStyle().createShallowCopy());
               list2.add(i + 1, chatComponentText4);
            }

            stringWidthFloat = (int)fontRenderer.getStringWidthFloat(str4);
            chatComponentText3 = new ChatComponentText(str4);
            chatComponentText3.setChatStyle(component2.getChatStyle().createShallowCopy());
            flag3 = true;
         }

         if (n2 + stringWidthFloat <= n) {
            n2 += stringWidthFloat;
            chatComponentText.appendSibling(chatComponentText3);
         } else {
            flag3 = true;
         }

         if (flag3) {
            list.add(chatComponentText);
            n2 = 0;
            chatComponentText = new ChatComponentText("");
         }
      }

      list.add(chatComponentText);
      return list;
   }

   private static int appendWrappedSegment(String str, int n, FontRenderer fontRenderer, boolean flag, StringBuilder sb, int n2) {
      int stringWidth = fontRenderer.getStringWidth(str);
      if (n2 + stringWidth <= n) {
         sb.append(str);
         n2 += stringWidth;
      } else {
         String str2 = fontRenderer.trimStringToWidth(str, n - n2, false);
         sb.append(str2);
         String str3 = str.substring(str2.length());
         if (!str3.isEmpty()) {
            if (flag) {
               int n4 = str2.lastIndexOf(32);
               if (n4 >= 0) {
                  str2 = str2.substring(0, n4);
                  str3 = str.substring(n4 + 1);
               }
            }

            sb.append("\n");
            n2 = appendWrappedSegment(str3, n, fontRenderer, flag, sb, 0);
         } else {
            n2 = 0;
         }
      }

      return n2;
   }

   public static String wrapTextToString(IChatComponent component, int n, FontRenderer fontRenderer, boolean flag, boolean flag2) {
      StringBuilder sb = new StringBuilder();
      int n2 = 0;

      for (IChatComponent component2 : (Iterable<IChatComponent>)(component)) {
         String formattingCode = component2.getChatStyle().getFormattingCode();
         String unformattedTextForChat = component2.getUnformattedTextForChat();
         int n3 = 0;

         while (n3 < unformattedTextForChat.length()) {
            int n4 = unformattedTextForChat.indexOf(10, n3);
            if (n4 == -1) {
               n4 = unformattedTextForChat.length();
            }

            n2 = appendWrappedSegment(removeTextColorsIfConfigured(formattingCode + unformattedTextForChat.substring(n3, n4), flag2), n, fontRenderer, flag, sb, n2);
            n3 = n4 + 1;
         }
      }

      return sb.toString().trim();
   }

   public static String removeTextColorsIfConfigured(String str, boolean flag) {
      return !flag && !Minecraft.getMinecraft().gameSettings.chatColours ? EnumChatFormatting.getTextWithoutFormattingCodes(str) : str;
   }
}
