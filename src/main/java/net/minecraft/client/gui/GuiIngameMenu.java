package net.minecraft.client.gui;

import wdl.WDLHooks;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.IOException;
import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.ui.menu.AbstractMainMenuScreen;
import net.augustus.ui.menu.ClientTabContainerScreen;
import net.augustus.ui.multiplayer.ServerListHost;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;

public class GuiIngameMenu extends GuiScreen implements ServerListHost {
   private ServerData reconnectServerData;
   private static final OldServerPinger oldServerPinger = new OldServerPinger();
   private String hoveringText;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      int offset = -16;
      boolean hideCustomMenu = ClientManagers.moduleManager.invisMode.isToggled()
         && ClientManagers.moduleManager.invisMode.shouldDisableCustomEscapeMenu();

      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + offset, I18n.format("menu.returnToMenu", new Object[0])));
      if (!this.mc.isIntegratedServerRunning()) {
         ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
      }

      this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + offset, I18n.format("menu.returnToGame", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + offset, 98, 20, I18n.format("menu.options", new Object[0])));
      GuiButton lanButton;
      this.buttonList.add(lanButton = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + offset, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
      this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + offset, 98, 20, I18n.format("gui.achievements", new Object[0])));
      this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + offset, 98, 20, I18n.format("gui.stats", new Object[0])));
      lanButton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();

      if (!hideCustomMenu) {
         this.buttonList.add(new GuiButton(1000, this.width / 2 - 100, this.height / 4 + 72 + offset, 98, 20, Augustus.getInstance().getName()));
         this.buttonList.add(new GuiButton(1001, this.width / 2 + 2, this.height / 4 + 72 + offset, 98, 20, I18n.format("selectServer.direct", new Object[0])));
         WDLHooks.injectWDLButtons(this, this.buttonList);
      }
   }

   public OldServerPinger getOldServerPinger() {
      return oldServerPinger;
   }

   public void renderHoveringText(GuiScreen screen, int mouseX, int mouseY) {
      if (this.hoveringText != null) {
         screen.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), mouseX, mouseY);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      WDLHooks.handleWDLButtonClick(this, button);
      switch (button.id) {
         case 0:
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
            break;
         case 1:
            button.enabled = false;
            this.disconnectFromWorld();
            break;
         case 4:
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
            break;
         case 5:
            this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
            break;
         case 6:
            this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
            break;
         case 7:
            this.mc.displayGuiScreen(new GuiShareToLan(this));
            break;
         case 1000:
            this.mc.displayGuiScreen(new ClientTabContainerScreen(this, 0));
            break;
         case 1001:
            this.mc.displayGuiScreen(new GuiScreenServerList(
               this, this.reconnectServerData = new ServerData("", this.mc.isSingleplayer() ? "" : this.mc.getCurrentServerData().serverIP, false)
            ));
            break;
         default:
            break;
      }
   }

   private void disconnectFromWorld() {
      boolean singleplayer = this.mc.isIntegratedServerRunning();
      this.mc.theWorld.sendQuittingDisconnectingPacket();
      this.mc.loadWorld(null);
      if (singleplayer) {
         this.mc.displayGuiScreen(new GuiMainMenu());
      } else {
         this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
      }
   }

   @Override
   public void confirmClicked(boolean result, int id) {
      if (result) {
         this.disconnectFromWorld();
         this.mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(AbstractMainMenuScreen.createMainMenuScreen()), this.mc, this.reconnectServerData));
      } else {
         this.mc.displayGuiScreen(this);
      }
   }

   public void setHoveringText(String text) {
      this.hoveringText = text;
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
   }
}
