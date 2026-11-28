package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum FastUseItemOption implements SelectableOption {
   FOOD("Food"),
   BOW("Bow"),
   OTHER("Other");

private String name;
   private boolean selected = true;
private FastUseItemOption(String str) {
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

   public static boolean isOptionSelected(FastUseItemOption fastUseItemOption) {
      return fastUseItemOption.selected;
   }
}
