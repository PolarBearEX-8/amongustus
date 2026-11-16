package net.minecraft.entity;

import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;
import net.minecraft.entity.player.EntityPlayerAction;

public enum EntityActionType implements NamedOption {
    START_SPRINT("StartSprint", EntityPlayerAction.START_SPRINTING),
    STOP_SPRINT("StopSprint", EntityPlayerAction.STOP_SPRINTING),
    START_SNEAKING("StartSneaking", EntityPlayerAction.START_SNEAKING),
    STOP_SNEAKING("StopSneaking", EntityPlayerAction.STOP_SNEAKING),
    STOP_SLEEPING("StopSleeping", EntityPlayerAction.STOP_SLEEPING),
    RIDING_JUMP("RidingJump", EntityPlayerAction.RIDING_JUMP),
    OPEN_INVENTORY("OpenInventory", EntityPlayerAction.OPEN_INVENTORY);

   public String labelText;
   public EntityPlayerAction entityPlayerAction;
   public boolean enabled;
   public NumberSetting numberSetting;
   public BooleanSetting booleanSetting;
   public ModeSetting modeSetting;
   public ActionSetting actionSetting;
   public int count = 0;
   public static final EntityActionType[] ENUM_VALUES = new EntityActionType[]{
      START_SPRINT, STOP_SPRINT, START_SNEAKING, STOP_SNEAKING, STOP_SLEEPING, RIDING_JUMP, OPEN_INVENTORY
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static ModeSetting getModeSetting(EntityActionType entityActionType) {
      return entityActionType.modeSetting;
   }

   public static ActionSetting resolveActionSetting(EntityActionType entityActionType, ActionSetting setting) {
      return entityActionType.actionSetting = setting;
   }

   public static ActionSetting getActionSetting(EntityActionType entityActionType) {
      return entityActionType.actionSetting;
   }

   public static String getText(EntityActionType entityActionType) {
      return entityActionType.labelText;
   }

   public static boolean checkEntityActionType(EntityActionType entityActionType) {
      return entityActionType.enabled;
   }

   public static BooleanSetting getBooleanSetting(EntityActionType entityActionType) {
      return entityActionType.booleanSetting;
   }

   public static EntityPlayerAction getEntityPlayerAction(EntityActionType entityActionType) {
      return entityActionType.entityPlayerAction;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static int getIndex(EntityActionType entityActionType) {
      return entityActionType.count;
   }

   public static BooleanSetting getBooleanSetting(EntityActionType entityActionType, BooleanSetting setting) {
      return entityActionType.booleanSetting = setting;
   }

   public static ModeSetting getModeSetting(EntityActionType entityActionType, ModeSetting setting) {
      return entityActionType.modeSetting = setting;
   }

   public static NumberSetting getNumberSetting(EntityActionType entityActionType) {
      return entityActionType.numberSetting;
   }

   public static NumberSetting getNumberSetting(EntityActionType entityActionType, NumberSetting setting) {
      return entityActionType.numberSetting = setting;
   }

   public static int getIndex2(EntityActionType entityActionType) {
      return entityActionType.count++;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private EntityActionType(String text, EntityPlayerAction entityPlayerAction2) {
      this.labelText = text;
      this.entityPlayerAction = entityPlayerAction2;
   }


}
