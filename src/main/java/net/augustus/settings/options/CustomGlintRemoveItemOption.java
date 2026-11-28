package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.utils.UnaryFunction;
import net.augustus.modules.render.ClickGui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum CustomGlintRemoveItemOption implements SelectableOption {
   SWORDS("Swords", obj -> isSwordItem((Item)obj)),
   TOOLS("Tools", obj -> isToolItem((Item)obj)),
   ARMOR("Armor", obj -> isArmorItem((Item)obj)),
   REST("Rest", obj -> isOtherItem((Item)obj));

   private final UnaryFunction itemMatcher;
   private final String name;
   private boolean selected;

   private CustomGlintRemoveItemOption(String str, UnaryFunction unaryFunction) {
      this.name = str;
      this.itemMatcher = unaryFunction;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public boolean matchesItem(ItemStack stack) {
      Item item = stack != null ? stack.getItem() : null;
      return (Boolean)this.itemMatcher.apply(item);
   }

   public static boolean shouldRemoveGlint(ItemStack stack) {

      for (int i = values().length - 1; i >= 0; i--) {
         CustomGlintRemoveItemOption option = values()[i];
         if (option.matchesItem(stack)) {
            return option.isSelected();
         }
      }

      return false;
   }

   private static Boolean isOtherItem(Item item) {
      return !(Boolean)SWORDS.itemMatcher.apply(item) && !(Boolean)TOOLS.itemMatcher.apply(item) && !(Boolean)ARMOR.itemMatcher.apply(item);
   }

   private static Boolean isArmorItem(Item item) {
      return item instanceof ItemArmor;
   }

   private static Boolean isToolItem(Item item) {
      return item instanceof ItemTool || item instanceof ItemShears;
   }

   private static Boolean isSwordItem(Item item) {
      return item instanceof ItemSword;
   }


}
