package net.minecraft.world;

public enum EnumDifficulty {
   PEACEFUL(0, "options.difficulty.peaceful"),
   EASY(1, "options.difficulty.easy"),
   NORMAL(2, "options.difficulty.normal"),
   HARD(3, "options.difficulty.hard");

private static final EnumDifficulty[] difficultyEnums = new EnumDifficulty[values().length];
   private final int difficultyId;
   private final String difficultyResourceKey;

   static {
      for (EnumDifficulty enumDifficulty : values()) {
         difficultyEnums[enumDifficulty.difficultyId] = enumDifficulty;
      }
   }

   public static EnumDifficulty getDifficultyEnum(int n) {
      return difficultyEnums[n % difficultyEnums.length];
   }

   private EnumDifficulty(int n, String str) {
      this.difficultyId = n;
      this.difficultyResourceKey = str;
   }

   public int getDifficultyId() {
      return this.difficultyId;
   }

   public String getDifficultyResourceKey() {
      return this.difficultyResourceKey;
   }
}
