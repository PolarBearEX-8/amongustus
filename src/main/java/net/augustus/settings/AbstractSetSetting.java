package net.augustus.settings;

import net.augustus.ClientManagers;
import java.util.Set;
import net.augustus.modules.Module;

public abstract class AbstractSetSetting extends Setting {

   public void add(Object obj) {
      this.getSet().add(obj);
   }

   public boolean remove(Object obj) {
      return this.getSet().remove(obj);
   }

   public abstract Set getSet();

   public abstract void setSet(Set set);

   @Override
   public int getHeight() {
      return 0;
   }

   public int size() {
      return this.getSet().size();
   }

   public AbstractSetSetting(Module module, String str) {
       super(module, null, str, false);
      ClientManagers.settingManager.getSettings().add(this);
   }

   @Override
   public void renderTooltip() {
   }

   public void clear() {
      this.getSet().clear();
   }

   public boolean contains(Object obj) {
      return this.getSet().contains(obj);
   }
}
