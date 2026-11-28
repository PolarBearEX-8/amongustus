package net.augustus.ui.menu;

import net.augustus.Augustus;
import net.augustus.ui.TabbedMenuScreen;
import net.minecraft.client.gui.GuiScreen;

public class ClientTabContainerScreen extends TabbedMenuScreen {
   public ClientTabContainerScreen(GuiScreen screen, int initialTabIndex) {
      super(Augustus.getInstance().getClientTabManager().getTabs(), screen, initialTabIndex);
   }
}
