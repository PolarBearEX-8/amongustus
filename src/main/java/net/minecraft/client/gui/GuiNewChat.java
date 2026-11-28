package net.minecraft.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.augustus.utils.render.ShaderRenderPass;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiNewChat extends Gui {
   private static final Logger logger = LogManager.getLogger();
   private final Minecraft mc;
   private final List sentMessages = Lists.newArrayList();
   private final List chatLines = Lists.newArrayList();
   private final List drawnChatLines = Lists.newArrayList();
   private int scrollPos;
   private boolean isScrolled;

   public void refreshChat() {
      this.drawnChatLines.clear();
      this.resetScroll();

      for (int i = this.chatLines.size() - 1; i >= 0; i--) {
         ChatLine chatLine = (ChatLine)this.chatLines.get(i);
         this.setChatLine(chatLine.getChatComponent(), chatLine.getChatLineID(), chatLine.getUpdatedCounter(), true);
      }
   }

   public boolean getChatOpen() {
      return this.mc.currentScreen instanceof GuiChat;
   }

   public void clearChatMessages() {
      if (!ClientManagers.moduleManager.chat.isToggled() || !ClientManagers.moduleManager.chat.noChatLimit.getValue()) {
         this.drawnChatLines.clear();
         this.chatLines.clear();
         this.sentMessages.clear();
      }
   }

   public int getChatHeight() {
      return calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
   }

   public void addToSentMessages(String str) {
      if (this.sentMessages.isEmpty() || !((String)this.sentMessages.get(this.sentMessages.size() - 1)).equals(str)) {
         this.sentMessages.add(str);
      }
   }

   public IChatComponent getChatComponent(int n, int n2) {
      if (!this.getChatOpen()) {
         return null;
      } else {
         int scaleFactor = new ScaledResolution(this.mc).getScaleFactor();
         float chatScale = this.getChatScale();
         int n3 = n / scaleFactor - 3;
         int n4 = n2 / scaleFactor - 27;
         n3 = MathHelper.floor_float((float)n3 / chatScale);
         n4 = MathHelper.floor_float((float)n4 / chatScale);
         if (n3 >= 0 && n4 >= 0) {
            int n5 = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (n3 <= MathHelper.floor_float((float)this.getChatWidth() / this.getChatScale()) && n4 < this.mc.fontRendererObj.FONT_HEIGHT * n5 + n5) {
               int n6 = n4 / this.mc.fontRendererObj.FONT_HEIGHT + this.scrollPos;
               if (n6 >= 0 && n6 < this.drawnChatLines.size()) {
                  ChatLine chatLine = (ChatLine)this.drawnChatLines.get(n6);
                  int n7 = 0;

                  for (IChatComponent component : (Iterable<IChatComponent>)(chatLine.getChatComponent())) {
                     if (component instanceof ChatComponentText) {
                        n7 += this.mc
                           .fontRendererObj
                           .getStringWidth(GuiUtilRenderComponents.removeTextColorsIfConfigured(((ChatComponentText)component).getChatComponentText_TextValue(), false));
                        if (n7 > n3) {
                           return component;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public int getScrollPos() {
      return this.scrollPos;
   }

   public synchronized void printChatMessage(IChatComponent component) {
      this.printChatMessageWithOptionalDeletion(component, 0);
   }

   public int getLineCount() {
      return this.getChatHeight() / 9;
   }

   public void printChatMessageWithOptionalDeletion(IChatComponent component, int n) {
      this.setChatLine(component, n, this.mc.ingameGUI.getUpdateCounter(), false);
      logger.info("[CHAT] " + component.getUnformattedText());
   }

   public float getChatScale() {
      return this.mc.gameSettings.chatScale;
   }

   public static int calculateChatboxHeight(float f) {
      byte b = 20;
      return MathHelper.floor_float(f * (float)(((short)180) - b) + (float)b);
   }

   public static int calculateChatboxWidth(float f) {
      byte b = 40;
      return MathHelper.floor_float(f * (float)(((short)320) - b) + (float)b);
   }

   public List getDrawnChatLines() {
      return this.drawnChatLines;
   }

   public void resetScroll() {
      this.scrollPos = 0;
      this.isScrolled = false;
   }

   public GuiNewChat(Minecraft mc2) {
      this.mc = mc2;
   }

   public int getChatWidth() {
      return calculateChatboxWidth(this.mc.gameSettings.chatWidth);
   }

   public void scroll(int n) {
      this.scrollPos += n;
      int size = this.drawnChatLines.size();
      if (this.scrollPos > size - this.getLineCount()) {
         this.scrollPos = size - this.getLineCount();
      }

      if (this.scrollPos <= 0) {
         this.scrollPos = 0;
         this.isScrolled = false;
      }
   }

   public void deleteChatLine(int n) {
      Iterator it = this.drawnChatLines.iterator();

      while (it.hasNext()) {
         if (((ChatLine)it.next()).getChatLineID() == n) {
            it.remove();
         }
      }

      it = this.chatLines.iterator();

      while (it.hasNext()) {
         if (((ChatLine)it.next()).getChatLineID() == n) {
            it.remove();
            break;
         }
      }
   }

   private void setChatLine(IChatComponent component, int n, int n2, boolean flag) {
      if (n != 0) {
         this.deleteChatLine(n);
      }

      List list = GuiUtilRenderComponents.splitText(component, MathHelper.floor_float((float)this.getChatWidth() / this.getChatScale()), this.mc.fontRendererObj, false, false);
      boolean chatOpen = this.getChatOpen();

      for (IChatComponent component2 : (Iterable<IChatComponent>)(list)) {
         if (chatOpen && this.scrollPos > 0) {
            this.isScrolled = true;
            this.scroll(1);
         }

         this.drawnChatLines.add(0, new ChatLine(n2, component2, n));
      }

      if (!ClientManagers.moduleManager.chat.isToggled() || !ClientManagers.moduleManager.chat.noChatLimit.getValue()) {
         while (this.drawnChatLines.size() > 100) {
            this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
         }

         if (!flag) {
            this.chatLines.add(0, new ChatLine(n2, component, n));

            while (this.chatLines.size() > 100) {
               this.chatLines.remove(this.chatLines.size() - 1);
            }
         }
      }
   }

   public List getSentMessages() {
      return this.sentMessages;
   }

   public void drawChatCustom(int n, boolean flag, boolean flag2, int n2) {
      if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
         int lineCount = this.getLineCount();
         int n3 = 0;
         int size = this.drawnChatLines.size();
         float f = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;
         if (size > 0) {
            boolean flag3;
            if (this.getChatOpen()) {
               flag3 = true;
            } else {
               flag3 = false;
            }

            float chatScale = this.getChatScale();
            int n4 = MathHelper.ceiling_float_int((float)this.getChatWidth() / chatScale);
            GlStateManager.pushMatrix();
            GlStateManager.translate(2.0F, 20.0F, 0.0F);
            GlStateManager.scale(chatScale, chatScale, 1.0F);
            if (flag2 && ClientManagers.moduleManager.chat.isToggled()) {
               BlurShadowManager.enableOneShotPass(ClientManagers.moduleManager.chat.blurShadowSettings.isBlurActive(), ClientManagers.moduleManager.chat.blurShadowSettings.isShadowActive());
               if (n2 == Integer.MIN_VALUE) {
                  n2 = 2130706432;
               }

               int n5 = n2;
               boolean flag4 = flag3;
               BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
                  this.drawChatBackground(flag4, this.mc.ingameGUI.getUpdateCounter(), f, lineCount, n4, ClientManagers.moduleManager.chat.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n5)));
               });
            } else if (flag2) {
               this.drawChatBackground(flag3, this.mc.ingameGUI.getUpdateCounter(), f, lineCount, n4, 2130706432);
            }

            int n7 = -1;

            for (int i = 0; i + this.scrollPos < this.drawnChatLines.size() && i < lineCount; i++) {
               ChatLine chatLine = (ChatLine)this.drawnChatLines.get(i + this.scrollPos);
               if (chatLine != null) {
                  n7++;
                  if (ClientManagers.moduleManager.antiStrike.shouldHideChat()) {
                     if (!chatLine.getChatComponent().getUnformattedText().startsWith("§6[§9" + Augustus.getInstance().getName() + "§6] ")) {
                        n7--;
                        continue;
                     }
                  }

                  int n8 = n - chatLine.getUpdatedCounter();
                  if (n8 < 200 || flag3) {
                     int n9;
                     if (flag3) {
                        n9 = 255;
                     } else {
                        double d = (double)n8 / 200.0;
                        d = 1.0 - d;
                        d *= 10.0;
                        d = MathHelper.clamp_double(d, 0.0, 1.0);
                        d *= d;
                        n9 = (int)(255.0 * d);
                     }

                     n3++;
                     n9 = (int)((float)n9 * f);
                     if (n9 > 3) {
                        int n10 = -n7 * 9;
                        String formattedText = chatLine.getChatComponent().getFormattedText();
                        GlStateManager.enableBlend();
                        this.mc.fontRendererObj.drawStringFiltered(formattedText, 0.0F, (float)(n10 - 8), 16777215 + (n9 << 24), flag, false, false);
                        GlStateManager.disableAlpha();
                        GlStateManager.disableBlend();
                     }
                  }
               }
            }

            if (flag3) {
               int fontheight = this.mc.fontRendererObj.FONT_HEIGHT;
               GlStateManager.translate(-3.0F, 0.0F, 0.0F);
               int n11 = size * fontheight + size;
               int n12 = n3 * fontheight + n3;
               int n13 = this.scrollPos * n12 / size;
               int n14 = n12 * n12 / n11;
               if (n11 != n12) {
                  int n15 = n13 > 0 ? 170 : 96;
                  int n16 = this.isScrolled ? 13382451 : 3355562;
                  if (flag2) {
                     drawRect(0, -n13, 2, -n13 - n14, n16 + (n15 << 24));
                     drawRect(2, -n13, 1, -n13 - n14, 13421772 + (n15 << 24));
                  }
               }
            }

            GlStateManager.popMatrix();
         } else {
            boolean flag5 = false;
         }
      }
   }

   public void drawChatBackground(boolean flag, int n, float f, int n2, int n3, int n4) {
      int n5 = -1;
      byte b = 0;
      int n6 = 0;
      int n7 = 0;
      int n8 = 0;

      for (int i = 0; i + this.scrollPos < this.drawnChatLines.size() && i < n2; i++) {
         ChatLine chatLine = (ChatLine)this.drawnChatLines.get(i + this.scrollPos);
         if (chatLine != null) {
            n5++;
            if (ClientManagers.moduleManager.antiStrike.shouldHideChat()) {
               if (!chatLine.getChatComponent().getUnformattedText().startsWith("§6[§9" + Augustus.getInstance().getName() + "§6] ")) {
                  n5--;
                  continue;
               }
            }

            int n9 = n - chatLine.getUpdatedCounter();
            if (n9 < 200 || flag) {
               int n10;
               if (flag) {
                  n10 = 255;
               } else {
                  double d = (double)n9 / 200.0;
                  d = 1.0 - d;
                  d *= 10.0;
                  d = MathHelper.clamp_double(d, 0.0, 1.0);
                  d *= d;
                  n10 = (int)(255.0 * d);
               }

               n10 = (int)((float)n10 * f);
               if (n10 > 3) {
                  byte b2 = 0;
                  int n11 = -n5 * 9;
                  if (i == 0) {
                     b = b2;
                     n8 = n11;
                  }

                  n6 = b2 + n3 + 4;
                  n7 = n11 - 9;
               }
            }
         }
      }

      if (n6 != 0) {
         RenderUtil.drawRect(b, n7, n6, n8, n4);
      }
   }

}
