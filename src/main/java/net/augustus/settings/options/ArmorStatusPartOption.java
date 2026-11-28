package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import java.util.Arrays;
import java.util.List;
import net.augustus.modules.render.ClickGui;

public class ArmorStatusPartOption implements SelectableOption {
   public static List OPTIONS = Arrays.asList(new ArmorStatusPartOption("Boots"), new ArmorStatusPartOption("Leggings"), new ArmorStatusPartOption("Chestplate"), new ArmorStatusPartOption("Helmet"));
   private final String name;
   private boolean selected = true;

   public static boolean isPartEnabled(int n) {
      ArmorStatusPartOption armorStatusPartOption = (ArmorStatusPartOption)OPTIONS.get(n);
      return armorStatusPartOption != null && armorStatusPartOption.isSelected();
   }

   private ArmorStatusPartOption(String str) {
      this.name = str;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }
}
