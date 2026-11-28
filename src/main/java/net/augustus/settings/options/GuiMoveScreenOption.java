package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public enum GuiMoveScreenOption implements SelectableOption {
   INVENTORY("Inventory", true),
   CONTAINER("Container", true),
   DEATH_SCREEN("DeathScreen", true),
   OTHER_SCREENS("OtherScreens", true);

private final String name;
   private boolean selected;
   private StringValue modeSetting;
   private BooleanValue sneakSetting;
   private BooleanValue jumpSetting;
   private BooleanValue rotateSetting;
   private BooleanValue rotateOnlyAtClickSetting;
   private BooleanValue moveOnlyOnGroundSetting;
   private BooleanValue sneakMotionWhileMovingSetting;
   private StringValue sprintModeSetting;
   private BooleanValue sprintJumpBoostSetting;
   private DoubleValue forwardMotionMultiplierSetting;
   private DoubleValue diagonalMotionMultiplierSetting;
private GuiMoveScreenOption(String str, boolean flag) {
      this.name = str;
      this.selected = flag;
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

   public static StringValue setModeSetting(GuiMoveScreenOption guiMoveScreenOption, StringValue booleansSetting) {
      return guiMoveScreenOption.modeSetting = booleansSetting;
   }

   public static BooleanValue setSneakSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.sneakSetting = booleanValue;
   }

   public static BooleanValue setJumpSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.jumpSetting = booleanValue;
   }

   public static BooleanValue setRotateSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.rotateSetting = booleanValue;
   }

   public static BooleanValue setRotateOnlyAtClickSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.rotateOnlyAtClickSetting = booleanValue;
   }

   public static BooleanValue getRotateSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.rotateSetting;
   }

   public static BooleanValue setMoveOnlyOnGroundSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.moveOnlyOnGroundSetting = booleanValue;
   }

   public static BooleanValue setSneakMotionWhileMovingSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.sneakMotionWhileMovingSetting = booleanValue;
   }

   public static StringValue setSprintModeSetting(GuiMoveScreenOption guiMoveScreenOption, StringValue booleansSetting) {
      return guiMoveScreenOption.sprintModeSetting = booleansSetting;
   }

   public static BooleanValue setSprintJumpBoostSetting(GuiMoveScreenOption guiMoveScreenOption, BooleanValue booleanValue) {
      return guiMoveScreenOption.sprintJumpBoostSetting = booleanValue;
   }

   public static DoubleValue setForwardMotionMultiplierSetting(GuiMoveScreenOption guiMoveScreenOption, DoubleValue stringValue) {
      return guiMoveScreenOption.forwardMotionMultiplierSetting = stringValue;
   }

   public static DoubleValue setDiagonalMotionMultiplierSetting(GuiMoveScreenOption guiMoveScreenOption, DoubleValue stringValue) {
      return guiMoveScreenOption.diagonalMotionMultiplierSetting = stringValue;
   }

   public static BooleanValue getRotateOnlyAtClickSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.rotateOnlyAtClickSetting;
   }

   public static BooleanValue getMoveOnlyOnGroundSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.moveOnlyOnGroundSetting;
   }

   public static StringValue getModeSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.modeSetting;
   }

   public static BooleanValue getSneakSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.sneakSetting;
   }

   public static BooleanValue getJumpSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.jumpSetting;
   }

   public static BooleanValue getSneakMotionWhileMovingSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.sneakMotionWhileMovingSetting;
   }

   public static StringValue getSprintModeSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.sprintModeSetting;
   }

   public static BooleanValue getSprintJumpBoostSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.sprintJumpBoostSetting;
   }

   public static DoubleValue getForwardMotionMultiplierSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.forwardMotionMultiplierSetting;
   }

   public static DoubleValue getDiagonalMotionMultiplierSetting(GuiMoveScreenOption guiMoveScreenOption) {
      return guiMoveScreenOption.diagonalMotionMultiplierSetting;
   }
}
