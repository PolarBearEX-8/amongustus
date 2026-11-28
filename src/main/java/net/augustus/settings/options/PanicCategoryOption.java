package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.modules.Categorys;

public enum PanicCategoryOption implements SelectableOption {
   COMBAT("Combat", Categorys.COMBAT),
   LEGIT("Legit", Categorys.LEGIT),
   LATENCY("Latency", Categorys.LATENCY),
   MOVEMENT("Movement", Categorys.MOVEMENT),
   PLAYER("Player", Categorys.PLAYER),
   RENDER("Render", Categorys.RENDER),
   WORLD("World", Categorys.WORLD),
   MISC("Misc", Categorys.MISC),
   FUN("Fun", Categorys.FUN);

private final String name;
   private final Categorys categorys;
   private boolean selected;
private PanicCategoryOption(String str, Categorys categorys2) {
      this.name = str;
      this.categorys = categorys2;
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

   public static Categorys getCategory(PanicCategoryOption panicCategoryOption) {
      return panicCategoryOption.categorys;
   }

   public static boolean isOptionSelected(PanicCategoryOption panicCategoryOption) {
      return panicCategoryOption.selected;
   }
}
