package net.augustus.ui.menu;

import net.augustus.ui.AugustusGuiScreen;
import java.awt.Color;

public abstract class ClientMenuTab extends AugustusGuiScreen {
   private Color statusColor;
   private final String tabTitle;
   private String statusText = null;

   public static Color getStatusColor(ClientMenuTab clientMenuTab) {
      return clientMenuTab.statusColor;
   }

   public void setStatus(String str, Color color) {
      this.statusText = str;
      this.statusColor = color;
   }

   public ClientMenuTab(String str) {
      this.statusColor = null;
      this.tabTitle = str;
   }

   public static String getStatusText(ClientMenuTab clientMenuTab) {
      return clientMenuTab.statusText;
   }

   public String getTabTitle() {
      return this.tabTitle;
   }
}
