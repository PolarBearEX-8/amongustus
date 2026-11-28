package net.augustus.settings;

import net.augustus.settings.property.IntegerListProperty;
import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.modules.Module;

public class IntegerListSetting extends AbstractListSetting {
   private final IntegerListProperty l;

   @Override
   public List getList() {
      return (List)this.l.getValue();
   }

   @Override
   public void resetToDefault() {
      this.l.resetToDefault();
   }

   @Override
   public void setList(List list) {
      this.l.setValue(list);
   }

   public IntegerListSetting(Module module, String str) {
       super(module, str);
      this.l = new IntegerListProperty(this, "L", Lists.newArrayList());
   }

   public IntegerListSetting(Module module, String str, List list) {
       super(module, str);
      this.l = new IntegerListProperty(this, "L", Lists.newArrayList());
      this.setList(list);
      this.l.setDefaultValue(list);
   }
}
