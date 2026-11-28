package net.augustus.config;

import net.augustus.ui.proxy.ProxyListEntry;
import net.augustus.ui.proxy.ProxyManagerScreen;
import net.augustus.ui.widgets.ScrollBar;
import net.augustus.utils.network.ProxyEntry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.function.Supplier;

public class ProxyConfigStorage extends AbstractIndexedFeatureConfigFile {

   @Override
   public boolean alwaysReserializeOnSave() {
      return true;
   }

   @Override
   public void deserializeIndexed(JsonObject json) {
      ProxyManagerScreen.proxies.clear();
      ProxyManagerScreen.proxyListEntries.clear();
      super.deserializeIndexed(json);
      ProxyManagerScreen.scrollBar.setItemCount(ProxyManagerScreen.proxies.size());
   }

   @Override
   public boolean markLoadedEvenIfEmpty() {
      return true;
   }

   public ProxyConfigStorage() {
      super(ProxyManagerScreen.proxies);
   }

   @Override
   public String getSubDirectory() {
      return "settings";
   }

   @Override
   public void onEntryLoaded(Supplier supplier) {
      ProxyEntry proxyEntry = (ProxyEntry)supplier.get();
      ProxyManagerScreen.proxies.add(proxyEntry);
      ProxyManagerScreen.proxyListEntries.put(proxyEntry, new ProxyListEntry(proxyEntry));
   }

   @Override
   public Supplier createEntrySupplier() {
      return () -> new ProxyEntry();
   }

   @Override
   public void deserialize(JsonElement elem) {
      this.deserializeIndexed((JsonObject)elem);
   }

   @Override
   public String getName() {
      return "Proxies_New";
   }


}
