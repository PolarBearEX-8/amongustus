package client.setting;

import client.module.Module;
import client.util.SmoothSelector;
import java.util.HashSet;
import java.util.Set;

public class MidClickFriendSetting extends SettingAdapter {
   public final SmoothSelector smoothSelector = new SmoothSelector(this, "S", new HashSet());

   @Override
   public void updateState() {
      this.smoothSelector.updateState();
   }

   @Override
   public Set getSet() {
      return (Set)this.smoothSelector.getValue();
   }

   @Override
   public void applySet(Set set) {
      this.smoothSelector.applyObject2(set);
   }

   public MidClickFriendSetting(Module module, String text) {
      super(module, text);
   }

   public MidClickFriendSetting(Module module, String text, Set set) {
      super(module, text);
      this.applySet(set);
      this.smoothSelector.setDataRef(set);
   }
}
