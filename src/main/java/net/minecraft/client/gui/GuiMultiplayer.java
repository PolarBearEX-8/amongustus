package net.minecraft.client.gui;

import net.minecraft.client.network.LanServerDetector;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.augustus.modules.misc.ClientSettings;
import net.augustus.viaversion.MultiplayerProtocolMenu;
import net.augustus.ui.multiplayer.ServerListHost;
import net.augustus.discord.DiscordRpcManager;
import net.augustus.ui.account.AccountEntry;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen implements ServerListHost, GuiYesNoCallback {
   public static final ResourceLocation AUGUSTUS_TEXTURE = new ResourceLocation("augustus", "pictures/true.png");
   public static final ResourceLocation AUGUSTUS_TEXTURE2 = new ResourceLocation("augustus", "pictures/false.png");
   public static final ResourceLocation AUGUSTUS_TEXTURE3 = new ResourceLocation("augustus", "pictures/unknown.png");
   private static final Logger LOGGER2 = LogManager.getLogger();
   private final OldServerPinger oldServerPinger = new OldServerPinger();
   private ServerSelectionList serverListSelector;
   private ServerList savedServerList;
   private GuiButton btnEditServer;
   private GuiButton btnSelectServer;
   private GuiButton btnDeleteServer;
   private boolean deletingServer;
   private boolean addingServer;
   private boolean editingServer;
   private boolean directConnect;
   private String hoveringText;
   private ServerData selectedServer;
   private LanServerDetector.LanServerList lanServerList;
   private LanServerDetector.ThreadLanServerFind lanServerDetector;
   private boolean initialized;

   public void moveServerUp(ServerListEntryNormal serverListEntryNormal, int n, boolean flag) {
      int n2 = flag ? 0 : n - 1;
      this.savedServerList.swapServers(n, n2);
      if (this.serverListSelector.getSelectedSlotIndex() == n) {
         this.selectServer(n2);
      }

      this.serverListSelector.loadInternetServers(this.savedServerList);
   }

   public void connectToSelected() {
      GuiListExtended.IGuiListEntry guiListEntry = this.serverListSelector.getSelectedSlotIndex() < 0 ? null : this.serverListSelector.getEntry(this.serverListSelector.getSelectedSlotIndex());
      if (guiListEntry instanceof ServerListEntryNormal) {
         this.connectToServer(((ServerListEntryNormal)guiListEntry).getServerData());
      } else if (guiListEntry instanceof ServerListEntryLanDetected) {
         LanServerDetector.LanServer lanServer = ((ServerListEntryLanDetected)guiListEntry).getLanServer();
         this.connectToServer(new ServerData(lanServer.getServerMotd(), lanServer.getServerIpPort(), true));
      }
   }

   public void renderHoveringText(GuiScreen screen, int n, int n2) {
      if (this.hoveringText != null) {
         screen.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), n, n2);
      }
   }

   @Override
   public void initGui() {
      MultiplayerProtocolMenu.getSidebar().initSidebar();
      DiscordRpcManager discordRpcManager = Augustus.getInstance().getDiscordRpcManager();
      String str = "Multiplayer Menu";
      discordRpcManager.updatePresence("Idle", str);
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      if (!this.initialized) {
         this.initialized = true;
         this.savedServerList = new ServerList(this.mc);
         this.savedServerList.loadServerList();
         this.lanServerList = new LanServerDetector.LanServerList();

         try {
            this.lanServerDetector = new LanServerDetector.ThreadLanServerFind(this.lanServerList);
            this.lanServerDetector.start();
         } catch (Exception e) {
            LOGGER2.warn("Unable to start LAN server detection: " + e.getMessage());
         }

         this.serverListSelector = new ServerSelectionList(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
         this.serverListSelector.loadInternetServers(this.savedServerList);
      } else {
         this.serverListSelector.setDimensions(this.width, this.height, 32, this.height - 64);
      }

      this.createButtons();
   }

   public void selectServer(int n) {
      this.serverListSelector.setSelectedSlotIndex(n);
      GuiListExtended.IGuiListEntry guiListEntry = n < 0 ? null : this.serverListSelector.getEntry(n);
      this.btnSelectServer.enabled = false;
      this.btnEditServer.enabled = false;
      this.btnDeleteServer.enabled = false;
      if (guiListEntry != null && !(guiListEntry instanceof ServerListEntryLanScan)) {
         this.btnSelectServer.enabled = true;
         if (guiListEntry instanceof ServerListEntryNormal) {
            this.btnEditServer.enabled = true;
            this.btnDeleteServer.enabled = true;
         }
      }
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      if (!MultiplayerProtocolMenu.getSidebar().handleMouseWheel()) {
         this.serverListSelector.handleMouseInput();
      }
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      MultiplayerProtocolMenu.getSidebar().mouseClickMove(mouseX, mouseY);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (!MultiplayerProtocolMenu.getSidebar().mouseClicked(mouseX, mouseY, mouseButton)) {
         this.serverListSelector.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   public void moveServerDown(ServerListEntryNormal serverListEntryNormal, int n, boolean flag) {
      int n2 = flag ? this.savedServerList.countServers() - 1 : n + 1;
      this.savedServerList.swapServers(n, n2);
      if (this.serverListSelector.getSelectedSlotIndex() == n) {
         this.selectServer(n2);
      }

      this.serverListSelector.loadInternetServers(this.savedServerList);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (this.lanServerList.getWasUpdated()) {
         List lanServers = this.lanServerList.getLanServers();
         this.lanServerList.setWasNotUpdated();
         this.serverListSelector.setLanServerList(lanServers);
      }

      this.oldServerPinger.pingPendingNetworks();
   }

   public GuiMultiplayer(GuiScreen screen) {
      super(screen);
   }

   public boolean canMoveUp(ServerListEntryNormal serverListEntryNormal, int n) {
      return n > 0;
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      GuiListExtended.IGuiListEntry guiListEntry = this.serverListSelector.getSelectedSlotIndex() < 0 ? null : this.serverListSelector.getEntry(this.serverListSelector.getSelectedSlotIndex());
      if (this.deletingServer) {
         this.deletingServer = false;
         if (flag && guiListEntry instanceof ServerListEntryNormal) {
            this.savedServerList.removeServerData(this.serverListSelector.getSelectedSlotIndex());
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.loadInternetServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.directConnect) {
         this.directConnect = false;
         if (flag) {
            this.connectToServer(this.selectedServer);
         } else {
            this.mc.displayGuiScreen(this);
         }
      } else if (this.addingServer) {
         this.addingServer = false;
         if (flag) {
            this.savedServerList.addServerData(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.loadInternetServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      } else if (this.editingServer) {
         this.editingServer = false;
         if (flag && guiListEntry instanceof ServerListEntryNormal) {
            ServerData serverData = ((ServerListEntryNormal)guiListEntry).getServerData();
            serverData.serverName = this.selectedServer.serverName;
            serverData.serverIP = this.selectedServer.serverIP;
            serverData.copyFrom(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.loadInternetServers(this.savedServerList);
         }

         this.mc.displayGuiScreen(this);
      }
   }

   public void createButtons() {
      this.buttonList
         .add(this.btnEditServer = new GuiButton(7, this.width / 2 - 154, this.height - 28, 74, 20, I18n.format("selectServer.edit", new Object[0])));
      this.buttonList
         .add(this.btnDeleteServer = new GuiButton(2, this.width / 2 - 76, this.height - 28, 74, 20, I18n.format("selectServer.delete", new Object[0])));
      this.buttonList
         .add(this.btnSelectServer = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
      this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
      this.buttonList.add(new GuiButton(8, this.width / 2 + 2, this.height - 28, 74, 20, I18n.format("selectServer.refresh", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 + 80, this.height - 28, 74, 20, I18n.format("gui.cancel", new Object[0])));
      this.buttonList.add(new GuiButton(-1338, this.width / 2 + 80, 6, 74, 20, "Mark banned"));
      this.selectServer(this.serverListSelector.getSelectedSlotIndex());
   }

   private void connectToServer(ServerData serverData) {
      this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, serverData));
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      if (this.lanServerDetector != null) {
         this.lanServerDetector.interrupt();
         this.lanServerDetector = null;
      }

      this.oldServerPinger.clearPendingNetworks();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.hoveringText = null;
      this.drawDefaultBackground();
      this.serverListSelector.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.title", new Object[0]), this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      ClientSettings.renderMainMenuAccountInfo();
      this.renderHoveringText(this, mouseX, mouseY);
      MultiplayerProtocolMenu.getSidebar().drawSidebar(mouseX, mouseY, partialTicks);
   }

   private void refreshServerList() {
      this.mc.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         GuiListExtended.IGuiListEntry guiListEntry = this.serverListSelector.getSelectedSlotIndex() < 0 ? null : this.serverListSelector.getEntry(this.serverListSelector.getSelectedSlotIndex());
         if (button.id == 2 && guiListEntry instanceof ServerListEntryNormal) {
            String serverName = ((ServerListEntryNormal)guiListEntry).getServerData().serverName;
            if (serverName != null) {
               this.deletingServer = true;
               String str = I18n.format("selectServer.deleteQuestion", new Object[0]);
               String str2 = "'" + serverName + "' " + I18n.format("selectServer.deleteWarning", new Object[0]);
               String str3 = I18n.format("selectServer.deleteButton", new Object[0]);
               String str4 = I18n.format("gui.cancel", new Object[0]);
               GuiYesNo guiYesNo = new GuiYesNo(this, str, str2, str3, str4, this.serverListSelector.getSelectedSlotIndex());
               this.mc.displayGuiScreen(guiYesNo);
            }
         } else if (button.id == 1) {
            this.connectToSelected();
         } else if (button.id == 4) {
            this.directConnect = true;
            this.mc.displayGuiScreen(new GuiScreenServerList(
               this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "", false)
            ));
         } else if (button.id == 3) {
            this.addingServer = true;
            this.mc.displayGuiScreen((GuiScreen)new GuiScreenAddServer(
               this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "", false)
            ));
         } else if (button.id == 7 && guiListEntry instanceof ServerListEntryNormal) {
            this.editingServer = true;
            ServerData serverData = ((ServerListEntryNormal)guiListEntry).getServerData();
            this.selectedServer = new ServerData(serverData.serverName, serverData.serverIP, false);
            this.selectedServer.copyFrom(serverData);
            this.mc.displayGuiScreen((GuiScreen)new GuiScreenAddServer(this, this.selectedServer));
         } else if (button.id == 0) {
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         } else if (button.id == 8) {
            this.refreshServerList();
         } else if (button.id == -1338
            && guiListEntry instanceof ServerListEntryNormal
            && Minecraft.getMinecraft().getSession() != null
            && Minecraft.getMinecraft().getSession().getAccountEntry() != null) {
            ServerData serverData2 = ((ServerListEntryNormal)guiListEntry).getServerData();
            if (serverData2 != null) {
               AccountEntry accountEntry = Minecraft.getMinecraft().getSession().getAccountEntry();
               String serverIP = serverData2.serverIP;
               Long val = 0L;
               accountEntry.markBannedOnServer(serverIP, val);
            }
         }
      }
   }

   public OldServerPinger getOldServerPinger() {
      return this.oldServerPinger;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      int selectedSlotIndex = this.serverListSelector.getSelectedSlotIndex();
      GuiListExtended.IGuiListEntry guiListEntry = selectedSlotIndex < 0 ? null : this.serverListSelector.getEntry(selectedSlotIndex);
      if (keyCode >= 2 && keyCode <= 10) {
         int n = keyCode - 2;
         if (n < this.serverListSelector.getSize()) {
            this.selectServer(n);
         }
      }

      if (keyCode == 63) {
         this.refreshServerList();
      } else if (selectedSlotIndex >= 0) {
         if (keyCode == 200) {
            if (isShiftKeyDown()) {
               if (selectedSlotIndex > 0 && guiListEntry instanceof ServerListEntryNormal) {
                  this.savedServerList.swapServers(selectedSlotIndex, selectedSlotIndex - 1);
                  this.selectServer(this.serverListSelector.getSelectedSlotIndex() - 1);
                  this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
                  this.serverListSelector.loadInternetServers(this.savedServerList);
               }
            } else if (selectedSlotIndex > 0) {
               this.selectServer(this.serverListSelector.getSelectedSlotIndex() - 1);
               this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getEntry(this.serverListSelector.getSelectedSlotIndex()) instanceof ServerListEntryLanScan
                  && this.serverListSelector.getSelectedSlotIndex() > 0) {
                  this.selectServer(this.serverListSelector.getSize() - 1);
                  this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
               }
            }
         } else if (keyCode == 208) {
            if (isShiftKeyDown()) {
               if (selectedSlotIndex < this.savedServerList.countServers() - 1) {
                  this.savedServerList.swapServers(selectedSlotIndex, selectedSlotIndex + 1);
                  this.selectServer(selectedSlotIndex + 1);
                  this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
                  this.serverListSelector.loadInternetServers(this.savedServerList);
               }
            } else if (selectedSlotIndex < this.serverListSelector.getSize() - 1) {
               this.selectServer(this.serverListSelector.getSelectedSlotIndex() + 1);
               this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getEntry(this.serverListSelector.getSelectedSlotIndex()) instanceof ServerListEntryLanScan
                  && this.serverListSelector.getSelectedSlotIndex() < this.serverListSelector.getSize() - 1) {
                  this.selectServer(this.serverListSelector.getSize() + 1);
                  this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
               }
            }
         } else if (keyCode != 28 && keyCode != 156) {
            super.keyTyped(typedChar, keyCode);
         } else {
            this.actionPerformed((GuiButton)this.buttonList.get(2));
         }
      } else {
         super.keyTyped(typedChar, keyCode);
      }
   }

   public boolean canMoveDown(ServerListEntryNormal serverListEntryNormal, int n) {
      return n < this.savedServerList.countServers() - 1;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      this.serverListSelector.mouseReleased(mouseX, mouseY, state);
   }

   public ServerList getServerList() {
      return this.savedServerList;
   }

   public void setHoveringText(String str) {
      this.hoveringText = str;
   }

}
