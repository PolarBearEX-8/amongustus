package client.setting;

import client.event.PrimaryListener;
import client.util.DampenerParser;

public enum MovementScaleProperty implements PrimaryListener {
   X("x"),
   Y("y"),
   Z("z"),
   NONE("none");

   public final String labelText;
   public static final MovementScaleProperty[] ENUM_VALUES = new MovementScaleProperty[]{X, Y, Z, NONE};

   @Override
   public String getText() {
      return this.labelText;
   }

   public static MovementScaleProperty ordinal(EnumFacingXType enumFacingXType) {
      switch (DampenerParser.countArray[enumFacingXType.ordinal()]) {
         case 1:
            return X;
         case 2:
            return Y;
         case 3:
            return Z;
         default:
            return NONE;
      }
   }

   private MovementScaleProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
