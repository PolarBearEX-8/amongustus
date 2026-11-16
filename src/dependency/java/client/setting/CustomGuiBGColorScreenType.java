package client.setting;

import client.gui.ClickGuiScreen;
import client.gui.ScrollableContainer;
import java.util.function.Function;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.inventory.GuiInventory;

public enum CustomGuiBGColorScreenType implements NamedOption {
    CLICK_GUI("ClickGui", true, (item -> CustomGuiBGColorScreenType.resolveBoolean6((GuiScreen)item))),
    INVENTORY("Inventory", true, (item -> CustomGuiBGColorScreenType.resolveBoolean2((GuiScreen)item))),
    ESCAPE_MENU("EscapeMenu", true, (item -> CustomGuiBGColorScreenType.resolveBoolean4((GuiScreen)item))),
    OTHER_MENUS("OtherMenus", true, (item -> CustomGuiBGColorScreenType.resolveBoolean((GuiScreen)item)));

   public final String labelText;
   public boolean enabled;
   public final Function function;
   public ColorSetting colorSetting;
   public BooleanSetting booleanSetting;
   public static final CustomGuiBGColorScreenType[] ENUM_VALUES = new CustomGuiBGColorScreenType[]{CLICK_GUI, INVENTORY, ESCAPE_MENU, OTHER_MENUS};

   private static Boolean resolveBoolean4(GuiScreen screen) {
      return screen instanceof GuiScreenServerList;
   }

   public static BooleanSetting getBooleanSetting(CustomGuiBGColorScreenType customGuiBGColorScreenType, BooleanSetting setting) {
      return customGuiBGColorScreenType.booleanSetting = setting;
   }

   public static ColorSetting getColorSetting(CustomGuiBGColorScreenType customGuiBGColorScreenType) {
      return customGuiBGColorScreenType.colorSetting;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   private static Boolean resolveBoolean2(GuiScreen screen) {
      return screen instanceof GuiInventory || screen instanceof ScrollableContainer;
   }

   public static Function getFunction(CustomGuiBGColorScreenType customGuiBGColorScreenType) {
      return customGuiBGColorScreenType.function;
   }

   private static Boolean resolveBoolean(GuiScreen screen) {
      return !(Boolean)CLICK_GUI.function.apply(screen) || !(Boolean)INVENTORY.function.apply(screen) || !(Boolean)ESCAPE_MENU.function.apply(screen);
   }

   public static BooleanSetting getBooleanSetting(CustomGuiBGColorScreenType customGuiBGColorScreenType) {
      return customGuiBGColorScreenType.booleanSetting;
   }

   public static ColorSetting getColorSetting(CustomGuiBGColorScreenType customGuiBGColorScreenType, ColorSetting setting) {
      return customGuiBGColorScreenType.colorSetting = setting;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   private static Boolean resolveBoolean6(GuiScreen screen) {
      return screen instanceof ClickGuiScreen;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private CustomGuiBGColorScreenType(String text, boolean flag, Function mapper) {
      this.labelText = text;
      this.enabled = flag;
      this.function = mapper;
   }


}
