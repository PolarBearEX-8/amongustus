package augustus.modes.render;

import client.setting.ItemEspOptionAccess;
import client.setting.NamedOption;

public class ItemEspOption implements NamedOption {
   public String labelText;
   public boolean state;

   @Override
   public void setActive(boolean active) {
      this.state = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.state;
   }

   public ItemEspOption(String text, boolean flag, ItemEspOptionAccess itemEspOptionAccess) {
      this(text, flag);
   }

   private ItemEspOption(String text, boolean flag) {
      this.labelText = text;
      this.state = flag;
   }
}
