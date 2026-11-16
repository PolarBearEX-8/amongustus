package client.gui;

import client.util.AuxiliaryDoubleEntry7Entry;
import client.util.AuxiliaryTimeHelperEntry;
import client.util.ColorEntry;
import client.util.DoubleEntry7Entry;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DraggableWindowHeader {
   public final List list = new CopyOnWriteArrayList();

   public void renderElement() {
      this.list.add(new DropdownMenuWidget());
      this.list.add(new ColorEntry());
      this.list.add(new AuxiliaryTimeHelperEntry());
      this.list.add(new DoubleEntry7Entry());
      this.list.add(new ConfigCategoryPanel());
      this.list.add(new AuxiliaryDoubleEntry7Entry());
      this.list.add(new ThemeSelectorWidget());
   }

   public List getList() {
      return this.list;
   }
}
