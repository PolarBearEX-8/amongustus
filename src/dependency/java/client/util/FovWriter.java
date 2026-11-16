package client.util;

import client.setting.ClientSettingsBlackType;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class FovWriter {
   private static int computeCountByText(String text, int count, FontRenderer fontRenderer, boolean flag, StringBuilder builder, int count2) {
      int index = fontRenderer.getIndex(text);
      int count3;
      if (count2 + index <= count) {
         builder.append(text);
         count3 = count2 + index;
      } else {
         int count4 = count - count2;
         String text2 = fontRenderer.resolveText(text, count4, false);
         builder.append(text2);
         String text3 = text.substring(text2.length());
         if (!text3.isEmpty()) {
            if (flag) {
               int count5 = text2.lastIndexOf(32);
               if (count5 >= 0) {
                  text2.substring(0, count5);
                  text3 = text.substring(count5 + 1);
               }
            }

            builder.append("\n");
            count3 = computeCountByText(text3, count, fontRenderer, flag, builder, 0);
         } else {
            count3 = 0;
         }
      }

      return count3;
   }

   public static String resolveText(IChatComponent iChatComponent, int count, FontRenderer fontRenderer, boolean flag, boolean flag2) {
      StringBuilder builder = new StringBuilder();
      int count2 = 0;

      for (IChatComponent iChatComponent2 : (Iterable<IChatComponent>) (Iterable) iChatComponent) {
         String text = iChatComponent2.getStyleHasParentManager().getText();
         String labelText = iChatComponent2.getLabelText();
         int count3 = 0;

         while (count3 < labelText.length()) {
            int count4 = labelText.indexOf(10, count3);
            if (count4 == -1) {
               count4 = labelText.length();
            }

            String text2 = labelText.substring(count3, count4);
            count2 = computeCountByText(resolveText(text + text2, flag2), count, fontRenderer, flag, builder, count2);
            count3 = count4 + 1;
         }
      }

      return builder.toString().trim();
   }

   public static String resolveText(String text, boolean flag) {
      return !flag && !Minecraft.getMinecraft().gameSettings.state27 ? ClientSettingsBlackType.resolveText(text) : text;
   }

   public static List resolveList(IChatComponent iChatComponent, int count, FontRenderer fontRenderer, boolean flag, boolean flag2) {
      int count2 = 0;
      ChatComponentText chatComponentText = new ChatComponentText("");
      ArrayList list = Lists.newArrayList();
      ArrayList list2 = Lists.newArrayList(iChatComponent);

      for (int index = 0; index < list2.size(); index++) {
         IChatComponent iChatComponent2 = (IChatComponent)list2.get(index);
         String labelText = iChatComponent2.getLabelText();
         String text = fontRenderer.resolveText2(labelText);
         boolean flag3 = false;
         if (text.contains("\n")) {
            int count3 = text.indexOf(10);
            String text2 = text.substring(count3 + 1);
            text = text.substring(0, count3 + 1);
            ChatComponentText chatComponentText2 = new ChatComponentText(text2);
            chatComponentText2.getIChat(iChatComponent2.getStyleHasParentManager().getStyleHasParentManager2());
            list2.add(index + 1, chatComponentText2);
            flag3 = true;
         }

         String text3 = resolveText(iChatComponent2.getStyleHasParentManager().getText() + text, flag2);
         String text4 = text3.endsWith("\n") ? text3.substring(0, text3.length() - 1) : text3;
         int count4 = fontRenderer.computeCount(text4);
         ChatComponentText chatComponentText3 = new ChatComponentText(text4);
         chatComponentText3.getIChat(iChatComponent2.getStyleHasParentManager().getStyleHasParentManager2());
         if (count2 + count4 > count) {
            String text5 = fontRenderer.resolveText(text3, count - count2, false);
            String text6 = text5.length() < text3.length() ? text3.substring(text5.length()) : null;
            if (text6 != null && text6.length() > 0) {
               int count5 = text5.lastIndexOf(" ");
               if (count5 >= 0 && fontRenderer.computeCount(text3.substring(0, count5)) > 0) {
                  text5 = text3.substring(0, count5);
                  if (flag) {
                     count5++;
                  }

                  text6 = text3.substring(count5);
               } else if (count2 > 0 && !text3.contains(" ")) {
                  text5 = "";
                  text6 = text3;
               }

               ChatComponentText chatComponentText4 = new ChatComponentText(text6);
               chatComponentText4.getIChat(iChatComponent2.getStyleHasParentManager().getStyleHasParentManager2());
               list2.add(index + 1, chatComponentText4);
            }

            count4 = fontRenderer.computeCount(text5);
            chatComponentText3 = new ChatComponentText(text5);
            chatComponentText3.getIChat(iChatComponent2.getStyleHasParentManager().getStyleHasParentManager2());
            flag3 = true;
         }

         if (count2 + count4 <= count) {
            count2 += count4;
            chatComponentText.appendChatComponent(chatComponentText3);
         } else {
            flag3 = true;
         }

         if (flag3) {
            list.add(chatComponentText);
            count2 = 0;
            chatComponentText = new ChatComponentText("");
         }
      }

      list.add(chatComponentText);
      return list;
   }
}
