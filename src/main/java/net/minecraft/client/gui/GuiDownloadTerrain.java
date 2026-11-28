package net.minecraft.client.gui;

import java.io.IOException;
import net.augustus.events.listeners.DownloadTerrainListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.util.EnumChatFormatting;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class GuiDownloadTerrain extends GuiScreen {
   private NetHandlerPlayClient netHandlerPlayClient;
   private int progress;
   private CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      if (button.id == 0) {
         this.mc.theWorld.sendQuittingDisconnectingPacket();
         this.mc.loadWorld((WorldClient)null);
         this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
      } else if (button.id == 1) {
         this.mc.displayGuiScreen((GuiScreen)null);
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 2, EnumChatFormatting.BOLD + EnumChatFormatting.GOLD.toString() + "Disconnect"));
      this.buttonList
         .add(new GuiButton(1, this.width / 2 - 100, this.height - 20 - 2, EnumChatFormatting.BOLD + EnumChatFormatting.RED.toString() + "Force Load"));
      DownloadTerrainListener.postDownloadTerrain();
   }

   @Override
   public void updateScreen() {
      this.progress++;
      if (this.progress % 20 == 0) {
         this.netHandlerPlayClient.addToSendQueue(new C00PacketKeepAlive());
      }
   }

   public GuiDownloadTerrain(NetHandlerPlayClient netHandlerPlayClient2) {
      this.netHandlerPlayClient = netHandlerPlayClient2;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.customLoadingScreen != null) {
         this.customLoadingScreen.drawBackground(this.width, this.height);
      } else {
         this.drawBackground(0);
      }

      drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]), this.width / 2, this.height / 2 - 50, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

}
