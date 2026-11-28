package net.augustus.settings;

import net.augustus.settings.property.DoubleListProperty;
import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.modules.Module;

public class DoubleListSetting extends AbstractListSetting {
   private final DoubleListProperty l;

   public DoubleListSetting(Module module, String str, List list) {
       super(module, str);
      this.l = new DoubleListProperty(this, "L", Lists.newArrayList());
      this.setList(list);
      this.l.setDefaultValue(list);
   }

   @Override
   public void setList(List list) {
      this.l.setValue(list);
   }

   public DoubleListSetting(Module module, String str) {
       super(module, str);
      this.l = new DoubleListProperty(this, "L", Lists.newArrayList());
   }

   @Override
   public List getList() {
      return (List)this.l.getValue();
   }

   @Override
   public void resetToDefault() {
      this.l.resetToDefault();
   }
}
