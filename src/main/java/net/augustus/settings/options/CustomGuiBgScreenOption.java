package net.augustus.settings.options;

import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.utils.UnaryFunction;
import net.augustus.modules.render.ClickGui;
import net.augustus.settings.BooleanValue;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;

public enum CustomGuiBgScreenOption implements SelectableOption {
   CLICK_GUI("ClickGui", true, obj -> isClickGuiScreen((GuiScreen)obj)),
   INVENTORY("Inventory", true, obj -> isInventoryScreen((GuiScreen)obj)),
   ESCAPE_MENU("EscapeMenu", true, obj -> isEscapeMenuScreen((GuiScreen)obj)),
   OTHER_MENUS("OtherMenus", true, obj -> isOtherMenuScreen((GuiScreen)obj));

   private final String name;
   private boolean selected;
   private final UnaryFunction screenMatcher;
   private ColorSetting backgroundColorSetting;
   private BooleanValue blurSetting;

   private CustomGuiBgScreenOption(String str, boolean flag, UnaryFunction unaryFunction) {
      this.name = str;
      this.selected = flag;
      this.screenMatcher = unaryFunction;
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

   private static Boolean isOtherMenuScreen(GuiScreen screen) {
      return !(Boolean)CLICK_GUI.screenMatcher.apply(screen) || !(Boolean)INVENTORY.screenMatcher.apply(screen) || !(Boolean)ESCAPE_MENU.screenMatcher.apply(screen);
   }

   private static Boolean isEscapeMenuScreen(GuiScreen screen) {
      return screen instanceof GuiIngameMenu;
   }

   private static Boolean isInventoryScreen(GuiScreen screen) {
      return screen instanceof GuiInventory || screen instanceof GuiContainerCreative;
   }

   private static Boolean isClickGuiScreen(GuiScreen screen) {
      return screen instanceof ClickGuiScreen;
   }

   public static ColorSetting setBackgroundColorSetting(CustomGuiBgScreenOption customGuiBgScreenOption, ColorSetting colorSetting) {
      return customGuiBgScreenOption.backgroundColorSetting = colorSetting;
   }

   public static BooleanValue setBlurSetting(CustomGuiBgScreenOption customGuiBgScreenOption, BooleanValue booleanValue) {
      return customGuiBgScreenOption.blurSetting = booleanValue;
   }

   public static UnaryFunction getScreenMatcher(CustomGuiBgScreenOption customGuiBgScreenOption) {
      return customGuiBgScreenOption.screenMatcher;
   }

   public static BooleanValue getBlurSetting(CustomGuiBgScreenOption customGuiBgScreenOption) {
      return customGuiBgScreenOption.blurSetting;
   }

   public static ColorSetting getBackgroundColorSetting(CustomGuiBgScreenOption customGuiBgScreenOption) {
      return customGuiBgScreenOption.backgroundColorSetting;
   }


}
