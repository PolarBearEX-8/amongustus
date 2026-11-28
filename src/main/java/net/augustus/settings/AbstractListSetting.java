package net.augustus.settings;

import net.augustus.ClientManagers;
import java.util.List;
import net.augustus.modules.Module;

public abstract class AbstractListSetting extends Setting {

   public int size() {
      return this.getList().size();
   }

   @Override
   public void renderTooltip() {
   }

   public void clear() {
      this.getList().clear();
   }

   public abstract void setList(List list);

   public Object removeAt(int n) {
      return this.getList().remove(n);
   }

   public void add(Object obj) {
      this.getList().add(obj);
   }

   public boolean contains(Object obj) {
      return this.getList().contains(obj);
   }

   public Object get(int n) {
      return this.getList().get(n);
   }

   @Override
   public int getHeight() {
      return 0;
   }

   public abstract List getList();

   public boolean remove(Object obj) {
      return this.getList().remove(obj);
   }

   public AbstractListSetting(Module module, String str) {
       super(module, null, str, false);
      ClientManagers.settingManager.getSettings().add(this);
   }
}
