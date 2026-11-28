package net.minecraft.client.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import net.augustus.utils.BanMessageParser;
import net.augustus.ui.menu.ClientTabContainerScreen;
import net.augustus.ui.menu.AbstractMainMenuScreen;
import net.augustus.ui.account.AccountEntry;
import net.augustus.utils.network.DnsCacheFlusher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Mouse;

public class GuiDisconnected extends GuiScreen {
   private String reason;
   private IChatComponent message;
   private List multilineMessage;
   private int textHeight;

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
      this.textHeight = Math.min(20, this.multilineMessage.size()) * this.fontRendererObj.FONT_HEIGHT;
      this.buttonList
         .add(
            new GuiButton(0, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT, I18n.format("gui.toMenu", new Object[0]))
         );
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 25, "Reconnect"));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 50, "Augustus Menu"));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 75, "Copy Error"));
      this.buttonList.add(new GuiButton(3, 5, 5, 98, 20, "Flush DNS Cache"));
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, this.reason, this.width / 2, this.height / 2 - this.textHeight / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
      int n = this.height / 2 - this.textHeight / 2;
      if (this.multilineMessage != null) {
         for (String str : (Iterable<String>)(this.multilineMessage)) {
            drawCenteredString(this.fontRendererObj, str, this.width / 2, n, 16777215);
            n += this.fontRendererObj.FONT_HEIGHT;
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public GuiDisconnected(GuiScreen screen, String str, IChatComponent component) {
       super(screen);
      this.reason = I18n.format(str, new Object[0]);
      this.message = component;
      Mouse.setGrabbed(false);
      if (component != null
         && Minecraft.getMinecraft() != null
         && Minecraft.getMinecraft().getSession() != null
         && (Minecraft.getMinecraft().getSession().getAccountEntry() != null || GuiConnecting.lastRawServerIp.isEmpty())) {
         String unformattedText = component.getUnformattedText();
         if (BanMessageParser.isBanMessage(unformattedText)) {
            long l = BanMessageParser.parseBanDurationMillis(unformattedText);
            AccountEntry accountEntry = Minecraft.getMinecraft().getSession().getAccountEntry();
            String lastRawServerIp = GuiConnecting.lastRawServerIp;
            Long val = l;
            accountEntry.markBannedOnServer(lastRawServerIp, val);
         }
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         GuiScreen parentScreen2 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen2);
      } else if (button.id == 1) {
         String str = GuiConnecting.lastServerIp + ":" + GuiConnecting.lastServerPort;
         this.mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(AbstractMainMenuScreen.createMainMenuScreen()), this.mc, new ServerData(str, str, false)));
      } else if (button.id == 2) {
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.message.getFormattedText()), null);
      } else if (button.id == 3) {
         DnsCacheFlusher.flushDns();
      } else if (button.id == 4) {
         this.mc.displayGuiScreen(new ClientTabContainerScreen(this, 0));
      }
   }

}
