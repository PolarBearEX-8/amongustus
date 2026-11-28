package net.optifine.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.optifine.Lang;
import net.optifine.config.Config;

public class GuiScreenCapeOF extends GuiScreenOF {
   private final GuiScreen parentScreen2;
   private String title;
   private String message;
   private long messageHideTimeMs;
   private String linkUrl;
   private GuiButtonOF buttonCopyLink;
   private FontRenderer fontRenderer = Config.getMc().fontRendererObj;

   private void showMessage(String str, long l) {
      this.message = str;
      this.messageHideTimeMs = System.currentTimeMillis() + l;
      this.setLinkUrl((String)null);
   }

   public void setLinkUrl(String str) {
      this.linkUrl = str;
      this.buttonCopyLink.visible = str != null;
   }

   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == 200) {
            GuiScreen parentScreen22 = this.parentScreen2;
            this.mc.displayGuiScreen(parentScreen22);
         }

         if (button.id == 210) {
            try {
               if (this.mc.getSession().getProfile().getId() == null) {
                  throw new InvalidCredentialsException("Cracked Account?");
               }

               String name = this.mc.getSession().getProfile().getName();
               String str = this.mc.getSession().getProfile().getId().toString().replace("-", "");
               String token = this.mc.getSession().getToken();
               Random rand = new Random();
               Random rand2 = new Random((long)System.identityHashCode(new Object()));
               String str2 = new BigInteger(128, rand).xor(new BigInteger(128, rand2)).toString(16);
               this.mc.getSessionService().joinServer(this.mc.getSession().getProfile(), token, str2);
               String str3 = "https://optifine.net/capeChange?u=" + str + "&n=" + name + "&s=" + str2;
               if (Config.openWebLink(new URI(str3))) {
                  this.showMessage(Lang.get("of.message.capeOF.openEditor"), 10000L);
               } else {
                  this.showMessage(Lang.get("of.message.capeOF.openEditorError"), 10000L);
                  this.setLinkUrl(str3);
               }
            } catch (InvalidCredentialsException e) {
               Config.showGuiMessage(I18n.format("of.message.capeOF.error1", new Object[0]), I18n.format("of.message.capeOF.error2", new Object[]{e.getMessage()}));
               Config.warn("Mojang authentication failed");
               Config.warn(e.getClass().getName() + ": " + e.getMessage());
            } catch (Exception e2) {
               e2.printStackTrace();
               Config.warn("Error opening OptiFine cape link");
               Config.warn(e2.getClass().getName() + ": " + e2.getMessage());
            }
         }

         if (button.id == 220) {
            this.showMessage(Lang.get("of.message.capeOF.reloadCape"), 15000L);
            if (this.mc.thePlayer != null) {
               long l2 = System.currentTimeMillis() + 15000L;
               this.mc.thePlayer.setReloadCapeTimeMs(l2);
            }
         }

         if (button.id == 230 && this.linkUrl != null) {
            setClipboardString(this.linkUrl);
         }
      }
   }

   public GuiScreenCapeOF(GuiScreen screen) {
      this.parentScreen2 = screen;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
      if (this.message != null) {
         drawCenteredString(this.fontRenderer, this.message, this.width / 2, this.height / 6 + 60, 16777215);
         if (System.currentTimeMillis() > this.messageHideTimeMs) {
            this.message = null;
            this.setLinkUrl((String)null);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void initGui() {
      int n = 0;
      this.title = I18n.format("of.options.capeOF.title", new Object[0]);
      n += 2;
      this.buttonList
         .add(
            new GuiButtonOF(210, this.width / 2 - 155, this.height / 6 + 24 * (n >> 1), 150, 20, I18n.format("of.options.capeOF.openEditor", new Object[0]))
         );
      this.buttonList
         .add(
            new GuiButtonOF(
               220, this.width / 2 - 155 + 160, this.height / 6 + 24 * (n >> 1), 150, 20, I18n.format("of.options.capeOF.reloadCape", new Object[0])
            )
         );
      n += 6;
      this.buttonCopyLink = new GuiButtonOF(
         230, this.width / 2 - 100, this.height / 6 + 24 * (n >> 1), 200, 20, I18n.format("of.options.capeOF.copyEditorLink", new Object[0])
      );
      this.buttonCopyLink.visible = this.linkUrl != null;
      this.buttonList.add(this.buttonCopyLink);
      n += 4;
      this.buttonList.add(new GuiButtonOF(200, this.width / 2 - 100, this.height / 6 + 24 * (n >> 1), I18n.format("gui.done", new Object[0])));
   }

}
