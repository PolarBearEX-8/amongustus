package client.util;

import java.util.ArrayList;
import java.util.List;

public class LocalListHolder {
   public List list = new ArrayList();

   public LocalListHolder add(String text) {
      this.list.add(new PrimaryStringEntry(text, (Boolean)null));
      return this;
   }

   public List getList() {
      return this.list;
   }

   public LocalListHolder resolveLocalListHolder(String text, int count) {
      assert count > 0;

      for (int index = 0; index < count; index++) {
         Boolean flag = false;
         this.add(text, flag);
      }

      return this;
   }

   public LocalListHolder clear() {
      this.list.clear();
      return this;
   }

   public LocalListHolder add(String text, Boolean flag) {
      this.list.add(new PrimaryStringEntry(text, flag));
      return this;
   }
}
