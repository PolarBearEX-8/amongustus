package client.util;

import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;

public enum PlayerDiggingAction implements NamedOption {
    START_DESTROY_BLOCK("StartDestroyBlock", PlayerDiggingActionType.START_DESTROY_BLOCK),
    ABORT_DESTROY_BLOCK("AbortDestroyBlock", PlayerDiggingActionType.ABORT_DESTROY_BLOCK),
    STOP_DESTROY_BLOCK("StopDestroyBlock", PlayerDiggingActionType.STOP_DESTROY_BLOCK),
    DROP_ALL_ITEMS("DropAllItems", PlayerDiggingActionType.DROP_ALL_ITEMS),
    DROP_ITEM("DropItem", PlayerDiggingActionType.DROP_ITEM),
    RELEASE_USE_ITEM("ReleaseUseItem", PlayerDiggingActionType.RELEASE_USE_ITEM);

   public String labelText;
   public PlayerDiggingActionType playerDiggingActionType;
   public boolean enabled;
   public NumberSetting numberSetting;
   public BooleanSetting booleanSetting;
   public ModeSetting modeSetting;
   public ActionSetting actionSetting;
   public int count = 0;
   public static final PlayerDiggingAction[] ENUM_VALUES = new PlayerDiggingAction[]{
      START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static ActionSetting resolveActionSetting(PlayerDiggingAction playerDiggingAction, ActionSetting setting) {
      return playerDiggingAction.actionSetting = setting;
   }

   public static ActionSetting getActionSetting(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.actionSetting;
   }

   public static BooleanSetting getBooleanSetting(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.booleanSetting;
   }

   public static NumberSetting getNumberSetting(PlayerDiggingAction playerDiggingAction, NumberSetting setting) {
      return playerDiggingAction.numberSetting = setting;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static String getText(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.labelText;
   }

   public static boolean checkPlayerDiggingAction(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.enabled;
   }

   public static ModeSetting getModeSetting(PlayerDiggingAction playerDiggingAction, ModeSetting setting) {
      return playerDiggingAction.modeSetting = setting;
   }

   public static int getIndex(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.count++;
   }

   public static ModeSetting getModeSetting(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.modeSetting;
   }

   public static NumberSetting getNumberSetting(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.numberSetting;
   }

   public static int getIndex2(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.count;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public static BooleanSetting getBooleanSetting(PlayerDiggingAction playerDiggingAction, BooleanSetting setting) {
      return playerDiggingAction.booleanSetting = setting;
   }

   public static PlayerDiggingActionType getPlayerDiggingActionType(PlayerDiggingAction playerDiggingAction) {
      return playerDiggingAction.playerDiggingActionType;
   }

   private PlayerDiggingAction(String text, PlayerDiggingActionType playerDiggingActionType2) {
      this.labelText = text;
      this.playerDiggingActionType = playerDiggingActionType2;
   }


}
