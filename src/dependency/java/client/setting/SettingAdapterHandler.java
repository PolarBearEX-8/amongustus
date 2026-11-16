package client.setting;

import client.module.Module;
import java.util.HashSet;
import java.util.Set;

public class SettingAdapterHandler extends SettingAdapter {
   public final ListenerValuePropertyManager listenerValuePropertyManager = new ListenerValuePropertyManager(this, "S", new HashSet());

   @Override
   public void updateState() {
      this.listenerValuePropertyManager.updateState();
   }

   @Override
   public void applySet(Set set) {
      this.listenerValuePropertyManager.applyObject2(set);
   }

   public SettingAdapterHandler(Module module, String text) {
      super(module, text);
   }

   @Override
   public Set getSet() {
      return (Set)this.listenerValuePropertyManager.getValue();
   }

   public SettingAdapterHandler(Module module, String text, Set set) {
      super(module, text);
      this.applySet(set);
      this.listenerValuePropertyManager.setDataRef(set);
   }
}
