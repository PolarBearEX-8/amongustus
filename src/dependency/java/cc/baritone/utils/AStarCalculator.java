package cc.baritone.utils;

public final class AStarCalculator {
   public final SecondaryOptionType secondaryOptionType;

   public AStarCalculator(SecondaryOptionType secondaryOptionType2) {
      this.secondaryOptionType = secondaryOptionType2;
   }

   public final SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }
}
