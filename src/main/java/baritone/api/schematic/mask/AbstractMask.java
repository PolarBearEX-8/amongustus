package baritone.api.schematic.mask;

public abstract class AbstractMask implements Mask {
   private final int widthX;
   private final int heightY;
   private final int lengthZ;

   @Override
   public int lengthZ() {
      return this.lengthZ;
   }

   @Override
   public int widthX() {
      return this.widthX;
   }

   public AbstractMask(int n, int n2, int n3) {
      this.widthX = n;
      this.heightY = n2;
      this.lengthZ = n3;
   }

   @Override
   public int heightY() {
      return this.heightY;
   }
}
