package client.setting;

import client.event.DirectListener;
import java.util.ArrayList;
import java.util.List;

public abstract class ModuleModeOption implements DirectListener {
   public static int count = 0;
   public final List list = new ArrayList();
   public final String labelText;

   @Override
   public List getListAsList() {
      return this.list;
   }

   @Override
   public void add(ModeOptionConfigurable modeOptionConfigurable) {
      this.list.add(modeOptionConfigurable);
   }

   @Override
   public String getString() {
      return this.labelText;
   }

   public ModuleModeOption(String text) {
      this.labelText = text;
   }
}
