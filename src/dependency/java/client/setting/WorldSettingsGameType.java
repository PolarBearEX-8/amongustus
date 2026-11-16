package client.setting;

import client.util.InvulnerableManager;

public enum WorldSettingsGameType {
    NOT_SET(-1, ""),
    SURVIVAL(0, "survival"),
    CREATIVE(1, "creative"),
    ADVENTURE(2, "adventure"),
    SPECTATOR(3, "spectator");

   public int count;
   public String labelText;
   public static final WorldSettingsGameType[] ENUM_VALUES = new WorldSettingsGameType[]{NOT_SET, SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR};

   public boolean isActive() {
      return this == ADVENTURE || this == SPECTATOR;
   }

   public static WorldSettingsGameType resolveWorldSettingsGameType(int count2) {
      for (WorldSettingsGameType worldSettingsGameType : values()) {
         if (worldSettingsGameType.count == count2) {
            return worldSettingsGameType;
         }
      }

      return SURVIVAL;
   }

   public static WorldSettingsGameType resolveWorldSettingsGameType(String text) {
      for (WorldSettingsGameType worldSettingsGameType : values()) {
         if (worldSettingsGameType.labelText.equals(text)) {
            return worldSettingsGameType;
         }
      }

      return SURVIVAL;
   }

   public int getIndex() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean canExecute() {
      return this == CREATIVE;
   }

   public boolean canExecute2() {
      return this == SURVIVAL || this == ADVENTURE;
   }

   public void updateInvulnerableManager(InvulnerableManager invulnerableManager) {
      if (this == CREATIVE) {
         invulnerableManager.state2 = true;
         invulnerableManager.enabled2 = true;
         invulnerableManager.enabled = true;
      } else if (this == SPECTATOR) {
         invulnerableManager.state2 = true;
         invulnerableManager.enabled2 = false;
         invulnerableManager.enabled = true;
         invulnerableManager.state = true;
      } else {
         invulnerableManager.state2 = false;
         invulnerableManager.enabled2 = false;
         invulnerableManager.enabled = false;
         invulnerableManager.state = false;
      }

      invulnerableManager.state1 = !this.isActive();
   }

   private WorldSettingsGameType(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }


}
