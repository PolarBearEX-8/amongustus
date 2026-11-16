package client.util;

import client.setting.MovementConfigProperty;
import java.util.ArrayList;
import java.util.List;

public class EnhancedListEntry extends PrimaryFloatEntry {
   public transient List list;
   public boolean enabled;
   public final String labelText;
   public final int count2;

   public boolean isEmpty() {
      return this.list != null && !this.list.isEmpty();
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount2() {
      return this.count2;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public List getList() {
      if (this.list == null) {
         this.list = new ArrayList();
      }

      return this.list;
   }

   public EnhancedListEntry(String text, int count, boolean flag) {
      super(MovementConfigProperty.FREE);
      this.labelText = text;
      this.count2 = count;
      this.enabled = flag;
   }
}
