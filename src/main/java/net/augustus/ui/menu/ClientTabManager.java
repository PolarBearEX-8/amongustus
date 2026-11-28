package net.augustus.ui.menu;

import net.augustus.ui.account.AccountManagerScreen;
import net.augustus.ui.proxy.ProxyManagerScreen;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientTabManager {
   private final List tabs = new CopyOnWriteArrayList();
   public ClientMenuTab activeTab;

   public List getTabs() {
      return this.tabs;
   }

   public void initTabs() {
      this.tabs.add(new AccountManagerScreen());
      this.tabs.add(new ProxyManagerScreen());
      this.tabs.add(new BackgroundMenuTab());
      this.tabs.add(new ChangelogMenuTab());
      this.tabs.add(new CreditsMenuTab());
   }
}
