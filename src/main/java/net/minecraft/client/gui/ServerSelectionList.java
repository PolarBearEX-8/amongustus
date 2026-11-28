package net.minecraft.client.gui;

import net.minecraft.client.network.LanServerDetector;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerList;

public class ServerSelectionList extends GuiListExtended {
   private final GuiMultiplayer owner;
   private final List serverListInternet = Lists.newArrayList();
   private final CopyOnWriteArrayList serverListLan = new CopyOnWriteArrayList();
   private final GuiListExtended.IGuiListEntry lanScanEntry = new ServerListEntryLanScan();
   private int selectedSlotIndex = -1;

   public void loadInternetServers(ServerList serverList) {
      this.serverListInternet.clear();

      for (int i = 0; i < serverList.countServers(); i++) {
         this.serverListInternet.add(new ServerListEntryNormal(this.owner, serverList.getServerData(i)));
      }
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 30;
   }

   @Override
   public int getSize() {
      return this.serverListInternet.size() + 1 + this.serverListLan.size();
   }

   public int getSelectedSlotIndex() {
      return this.selectedSlotIndex;
   }

   @Override
   public boolean isSelected(int n) {
      return n == this.selectedSlotIndex;
   }

   @Override
   public int getListWidth() {
      return super.getListWidth() + 85;
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      if (n < this.serverListInternet.size()) {
         return (GuiListExtended.IGuiListEntry)this.serverListInternet.get(n);
      } else {
         n -= this.serverListInternet.size();
         return n == 0 ? this.lanScanEntry : (GuiListExtended.IGuiListEntry)this.serverListLan.get(--n);
      }
   }

   public void setSelectedSlotIndex(int n) {
      this.selectedSlotIndex = n;
   }

   public ServerSelectionList(GuiMultiplayer guiMultiplayer, Minecraft mc2, int n, int n2, int n3, int n4, int n5) {
      super(mc2, n, n2, n3, n4, n5);
      this.owner = guiMultiplayer;
   }

   public void setLanServerList(List list) {
      this.serverListLan.clear();

      for (LanServerDetector.LanServer lanServer : (Iterable<LanServerDetector.LanServer>)(list)) {
         this.serverListLan.add(new ServerListEntryLanDetected(this.owner, lanServer));
      }
   }
}
