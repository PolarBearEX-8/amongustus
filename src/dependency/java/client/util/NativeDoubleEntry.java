package client.util;

import client.event.ExtendedListener;
import client.event.NameCannotBeNullListener;
import net.minecraft.util.MathHelper;

public class NativeDoubleEntry extends NameCannotBeNullListener {
   public final double value3;
   public final double value2;
   public String labelText2;

   public NativeDoubleEntry withLabelText2(String text) {
      this.labelText2 = text;
      return this;
   }

   @Override
   public double computeValue(double offset) {
      return MathHelper.getDouble7(offset, this.value3, this.value2);
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public NativeDoubleEntry(ExtendedListener extendedListener, String text, double posX, double posY, double posZ) {
      super(extendedListener, text, posX);
      this.value3 = posY;
      this.value2 = posZ;
      if (posY > posZ) {
         throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
      } else if (posX < posY) {
         throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
      } else if (posX > posZ) {
         throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
      }
   }
}
