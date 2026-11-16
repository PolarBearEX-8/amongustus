package client.util;

import client.setting.AutoSoupPerformRespawnType;
import net.minecraft.entity.player.EntityPlayerAction;

public class PassiveIntArrayEntry {
   public static final int[] countArray;
   public static final int[] countArray2;
   public static final int[] countArray3 = new int[AutoSoupPerformRespawnType.values().length];

   static {
      try {
         countArray3[AutoSoupPerformRespawnType.PERFORM_RESPAWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray3[AutoSoupPerformRespawnType.REQUEST_STATS.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray3[AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      countArray2 = new int[EntityPlayerAction.values().length];

      try {
         countArray2[EntityPlayerAction.START_SNEAKING.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray2[EntityPlayerAction.STOP_SNEAKING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[EntityPlayerAction.START_SPRINTING.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[EntityPlayerAction.STOP_SPRINTING.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[EntityPlayerAction.STOP_SLEEPING.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray2[EntityPlayerAction.RIDING_JUMP.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray2[EntityPlayerAction.OPEN_INVENTORY.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      countArray = new int[PlayerDiggingActionType.values().length];

      try {
         countArray[PlayerDiggingActionType.DROP_ITEM.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[PlayerDiggingActionType.DROP_ALL_ITEMS.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[PlayerDiggingActionType.RELEASE_USE_ITEM.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[PlayerDiggingActionType.START_DESTROY_BLOCK.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray[PlayerDiggingActionType.ABORT_DESTROY_BLOCK.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray[PlayerDiggingActionType.STOP_DESTROY_BLOCK.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }
   }
}
