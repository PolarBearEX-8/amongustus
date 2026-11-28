package net.minecraft.client.gui;

import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanDetected implements GuiListExtended.IGuiListEntry {
   private final GuiMultiplayer screen;
   public final Minecraft mc;
   public final LanServerDetector.LanServer lanServer;
   private long lastClickTime = 0L;

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      this.mc.fontRendererObj.drawString(I18n.format("lanServer.title", new Object[0]), n2 + 32 + 3, n3 + 1, 16777215);
      this.mc.fontRendererObj.drawString(this.lanServer.getServerMotd(), n2 + 32 + 3, n3 + 12, 8421504);
      if (this.mc.gameSettings.hideServerAddress) {
         this.mc.fontRendererObj.drawString(I18n.format("selectServer.hiddenAddress", new Object[0]), n2 + 32 + 3, n3 + 12 + 11, 3158064);
      } else {
         this.mc.fontRendererObj.drawString(this.lanServer.getServerIpPort(), n2 + 32 + 3, n3 + 12 + 11, 3158064);
      }
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   public ServerListEntryLanDetected(GuiMultiplayer guiMultiplayer, LanServerDetector.LanServer lanServer2) {
      this.screen = guiMultiplayer;
      this.lanServer = lanServer2;
      this.mc = Minecraft.getMinecraft();
   }

   public LanServerDetector.LanServer getLanServer() {
      return this.lanServer;
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      this.screen.selectServer(n);
      if (Minecraft.getSystemTime() - this.lastClickTime < 250L) {
         this.screen.connectToSelected();
      }

      this.lastClickTime = Minecraft.getSystemTime();
      return false;
   }
}
