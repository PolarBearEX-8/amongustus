package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.augustus.modules.render.ClickGui;
import net.minecraft.potion.Potion;

public class PotionHudEffectOption implements SelectableOption {
   private final Potion potion;
   private final String name;
   private boolean selected = true;

   private PotionHudEffectOption(Potion potion2, String str) {
      this.potion = potion2;
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

   public Potion getPotion() {
      return this.potion;
   }

   public static List createAllOptions() {
      ArrayList list = new ArrayList(Potion.potionTypes.length);

      for (Potion potion2 : Potion.potionTypes) {
         if (potion2 != null) {
            String str = potion2.getName().substring(7);
            list.add(new PotionHudEffectOption(potion2, str.substring(0, 1).toUpperCase() + str.substring(1)));
         }
      }

      return (List)(list.stream().sorted(Comparator.comparing(obj -> ((PotionHudEffectOption)obj).name)).collect(Collectors.toList()));
   }
}
