package net.augustus.ui.multiplayer;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.OldServerPinger;

public interface ServerListHost {
   default void selectServer(int n) {
   }

   default boolean canMoveUp(ServerListEntryNormal serverListEntryNormal, int n) {
      return false;
   }

   void setHoveringText(String str);

   default void connectToSelected() {
   }

   default boolean canMoveDown(ServerListEntryNormal serverListEntryNormal, int n) {
      return false;
   }

   default ServerList getServerList() {
      return null;
   }

   void confirmClicked(boolean flag, int n);

   default void moveServerUp(ServerListEntryNormal serverListEntryNormal, int n, boolean flag) {
   }

   default void moveServerDown(ServerListEntryNormal serverListEntryNormal, int n, boolean flag) {
   }

   void renderHoveringText(GuiScreen screen, int n, int n2);

   OldServerPinger getOldServerPinger();
}
