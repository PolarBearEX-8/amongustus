package client.setting;

import client.module.Category;

public enum PanicCombatType implements NamedOption {
    COMBAT("Combat", Category.COMBAT),
    LEGIT("Legit", Category.LEGIT),
    LATENCY("Latency", Category.LATENCY),
    MOVEMENT("Movement", Category.MOVEMENT),
    PLAYER("Player", Category.PLAYER),
    RENDER("Render", Category.RENDER),
    WORLD("World", Category.WORLD),
    MISC("Misc", Category.MISC),
    FUN("Fun", Category.FUN);

   public final String labelText;
   public final Category category;
   public boolean enabled;
   public static final PanicCombatType[] ENUM_VALUES = new PanicCombatType[]{COMBAT, LEGIT, LATENCY, MOVEMENT, PLAYER, RENDER, WORLD, MISC, FUN};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkPanicCombatType(PanicCombatType panicCombatType) {
      return panicCombatType.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static Category getCategory(PanicCombatType panicCombatType) {
      return panicCombatType.category;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private PanicCombatType(String text, Category category2) {
      this.labelText = text;
      this.category = category2;
   }


}
