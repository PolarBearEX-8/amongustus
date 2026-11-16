package client.setting;

import client.module.Module;
import java.util.HashSet;
import java.util.Set;

public class SettingAdapterImpl extends SettingAdapter {
   public final JsonObjectValuePropertyAdapter jsonObjectValuePropertyAdapter = new JsonObjectValuePropertyAdapter(this, "S", new HashSet());

   public SettingAdapterImpl(Module module, String text, Set set) {
      super(module, text);
      this.applySet(set);
      this.jsonObjectValuePropertyAdapter.setDataRef(set);
   }

   @Override
   public Set getSet() {
      return (Set)this.jsonObjectValuePropertyAdapter.getValue();
   }

   @Override
   public void updateState() {
      this.jsonObjectValuePropertyAdapter.updateState();
   }

   @Override
   public void applySet(Set set) {
      this.jsonObjectValuePropertyAdapter.applyObject2(set);
   }

   public SettingAdapterImpl(Module module, String text) {
      super(module, text);
   }
}
