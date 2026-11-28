package net.augustus.modules.modes.anticheat;

public enum AntiCheatCategory {
   KILLAURA("KillAura"),
   SCAFFOLD("Scaffold"),

   AUTOCLICKER("AutoClicker");

   private final String displayName;

   private AntiCheatCategory(String str) {
      this.displayName = str;
   }

   public String getDisplayName() {
      return this.displayName;
   }
}
