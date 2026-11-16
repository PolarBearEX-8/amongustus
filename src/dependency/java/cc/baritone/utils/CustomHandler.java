package cc.baritone.utils;

import java.util.Objects;

public class CustomHandler implements SecondaryListener {
   public final SecondaryListener secondaryListener;
   public final SecondaryListener secondaryListener2;

   @Override
   public double computeValue(int count, int count2, int count3) {
      return this.secondaryListener.computeValue(count, count2, count3);
   }

   @Override
   public int hashCode() {
      return (1544707182 + this.secondaryListener.hashCode()) * -80327868 + this.secondaryListener2.hashCode();
   }

   @Override
   public String toString() {
      return "JankyComposite Primary: " + this.secondaryListener + " Fallback: " + this.secondaryListener2;
   }

   public CustomHandler(SecondaryListener secondaryListener3, SecondaryListener secondaryListener4) {
      this.secondaryListener = secondaryListener3;
      this.secondaryListener2 = secondaryListener4;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return this.secondaryListener.checkIndex(count, count2, count3) || this.secondaryListener2.checkIndex(count, count2, count3);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         CustomHandler customHandler = (CustomHandler)other;
         return Objects.equals(this.secondaryListener, customHandler.secondaryListener) && Objects.equals(this.secondaryListener2, customHandler.secondaryListener2);
      } else {
         return false;
      }
   }
}
