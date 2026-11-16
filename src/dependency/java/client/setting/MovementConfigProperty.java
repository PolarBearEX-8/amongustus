package client.setting;

public enum MovementConfigProperty {
    RIGHT_LEG(6, 1),
    LEFT_ARM(5, 2),
    RIGHT_ARM(4, 2),
    FREE(0),
    ABOVE_HEAD(3),
    BODY(2, 2),
    HEAD(1, 3),
    LEFT_LEG(7, 1);

   public int count2;
   public int count;
   public static final MovementConfigProperty[] VALUES = values();

   public int getCount() {
      return this.count;
   }

   public static MovementConfigProperty resolveMovementConfigProperty(int count3) {
      for (MovementConfigProperty movementConfigProperty : VALUES) {
         if (movementConfigProperty.getCount2() == count3) {
            return movementConfigProperty;
         }
      }

      return FREE;
   }

   public int getCount2() {
      return this.count2;
   }

   private MovementConfigProperty(int count3) {
      this(count3, -1);
   }

   private MovementConfigProperty(int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
   }



   @Override
   public String toString() {
      return this.name().replace("_", " ");
   }
}
