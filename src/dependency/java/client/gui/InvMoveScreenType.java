package client.gui;

import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;

public enum InvMoveScreenType implements NamedOption {
    INVENTORY("Inventory", true),
    CONTAINER("Container", true),
    DEATH_SCREEN("DeathScreen", true),
    OTHER_SCREENS("OtherScreens", true);

   public final String labelText;
   public boolean enabled;
   public ModeSetting modeSetting;
   public BooleanSetting booleanSetting4;
   public BooleanSetting booleanSetting2;
   public BooleanSetting booleanSetting5;
   public BooleanSetting booleanSetting;
   public BooleanSetting booleanSetting3;
   public BooleanSetting booleanSetting7;
   public ModeSetting modeSetting2;
   public BooleanSetting booleanSetting6;
   public NumberSetting numberSetting;
   public NumberSetting numberSetting2;
   public static final InvMoveScreenType[] ENUM_VALUES = new InvMoveScreenType[]{INVENTORY, CONTAINER, DEATH_SCREEN, OTHER_SCREENS};

   public static ModeSetting getModeSetting(InvMoveScreenType invMoveScreenType, ModeSetting setting) {
      return invMoveScreenType.modeSetting2 = setting;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static NumberSetting getNumberSetting(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.numberSetting2;
   }

   public static ModeSetting getModeSetting2(InvMoveScreenType invMoveScreenType, ModeSetting setting) {
      return invMoveScreenType.modeSetting = setting;
   }

   public static BooleanSetting getBooleanSetting(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting7 = setting;
   }

   public static BooleanSetting getBooleanSetting(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting4;
   }

   public static BooleanSetting getBooleanSetting2(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting3;
   }

   public static NumberSetting getNumberSetting(InvMoveScreenType invMoveScreenType, NumberSetting setting) {
      return invMoveScreenType.numberSetting2 = setting;
   }

   public static BooleanSetting getBooleanSetting3(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting7;
   }

   public static BooleanSetting getBooleanSetting2(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting6 = setting;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static BooleanSetting getBooleanSetting3(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting = setting;
   }

   public static BooleanSetting getBooleanSetting4(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting6;
   }

   public static BooleanSetting getBooleanSetting4(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting3 = setting;
   }

   public static BooleanSetting getBooleanSetting5(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting5;
   }

   public static BooleanSetting getBooleanSetting5(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting4 = setting;
   }

   public static NumberSetting getNumberSetting2(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.numberSetting;
   }

   public static BooleanSetting getBooleanSetting6(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting2;
   }

   public static BooleanSetting getBooleanSetting6(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting5 = setting;
   }

   public static ModeSetting getModeSetting(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.modeSetting;
   }

   public static ModeSetting getModeSetting2(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.modeSetting2;
   }

   public static NumberSetting getNumberSetting2(InvMoveScreenType invMoveScreenType, NumberSetting setting) {
      return invMoveScreenType.numberSetting = setting;
   }

   public static BooleanSetting getBooleanSetting7(InvMoveScreenType invMoveScreenType, BooleanSetting setting) {
      return invMoveScreenType.booleanSetting2 = setting;
   }

   public static BooleanSetting getBooleanSetting7(InvMoveScreenType invMoveScreenType) {
      return invMoveScreenType.booleanSetting;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private InvMoveScreenType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
