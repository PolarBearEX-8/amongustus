package net.augustus.modules.legit.helper;

public enum MlgJumpOutcome {
   NOTHING(null),
   IMPOSSIBLE("Impossible"),
   WALK("Walk"),
   JUMP("Jump"),
   BOTH("Both");

private final String displayName;
private MlgJumpOutcome(String str) {
      this.displayName = str;
   }

   public String getDisplayName() {
      return this.displayName;
   }
}
