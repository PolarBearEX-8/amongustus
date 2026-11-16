package client.util;

public class AngleAggregator implements AimValidator {
   public String labelText;
   public BoxesManager boxesManager;
   public PosXPosYType posXPosYType;

   public float getFactorAsFloat() {
      return this.posXPosYType.computeFactor(this.boxesManager);
   }

   public void applyFloat(float factor) {
      this.posXPosYType.applyBoxesManager(this.boxesManager, factor);
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   @Override
   public float getFactor() {
      return this.getFactorAsFloat();
   }

   @Override
   public String toString() {
      return this.labelText;
   }

   public AngleAggregator(String text, BoxesManager boxesManager2, PosXPosYType posXPosYType2) {
      this.labelText = text;
      this.boxesManager = boxesManager2;
      this.posXPosYType = posXPosYType2;
   }
}
