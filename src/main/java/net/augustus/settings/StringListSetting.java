package net.augustus.settings;

import net.augustus.settings.property.StringListProperty;
import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.modules.Module;

public class StringListSetting extends AbstractListSetting {
   private final StringListProperty l;

   @Override
   public List getList() {
      return (List)this.l.getValue();
   }

   @Override
   public void resetToDefault() {
      this.l.resetToDefault();
   }

   public StringListSetting(Module module, String str, List list) {
       super(module, str);
      this.l = new StringListProperty(this, "L", Lists.newArrayList());
      this.setList(list);
      this.l.setDefaultValue(list);
   }

   @Override
   public void setList(List list) {
      this.l.setValue(list);
   }

   public StringListSetting(Module module, String str) {
       super(module, str);
      this.l = new StringListProperty(this, "L", Lists.newArrayList());
   }
}
