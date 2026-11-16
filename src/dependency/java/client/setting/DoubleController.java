package client.setting;

import java.util.Objects;

public class DoubleController {
   public final Double memberRef;
   public final ProviderOptionType providerOptionType;

   @Override
   public int hashCode() {
      return Objects.hash(this.providerOptionType, this.memberRef);
   }

   public DoubleController(ProviderOptionType providerOptionType2, Double offset) {
      this.providerOptionType = providerOptionType2;
      this.memberRef = offset;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DoubleController doubleController = (DoubleController)other;
         return this.providerOptionType == doubleController.providerOptionType && Objects.equals(this.memberRef, doubleController.memberRef);
      } else {
         return false;
      }
   }
}
