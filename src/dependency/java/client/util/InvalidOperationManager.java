package client.util;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.UUID;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class InvalidOperationManager {
   public final double value;
   public final int count;
   public final String labelText;
   public final UUID uUID;
   public boolean enabled = true;

   public String getLabelText() {
      return this.labelText;
   }

   public UUID getUUID() {
      return this.uUID;
   }

   public InvalidOperationManager withEnabled(boolean flag) {
      this.enabled = flag;
      return this;
   }

   public double getValue() {
      return this.value;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount() {
      return this.count;
   }

   public InvalidOperationManager(String text, double offset, int count2) {
      this(MathHelper.processAction(ThreadLocalRandom.current()), text, offset, count2);
   }

   public InvalidOperationManager(UUID uuid, String text, double offset, int count2) {
      this.uUID = uuid;
      this.labelText = text;
      this.value = offset;
      this.count = count2;
      Validate.notEmpty(text, "Modifier name cannot be empty", new Object[0]);
      Validate.inclusiveBetween(0L, 2L, (long)count2, "Invalid operation");
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         InvalidOperationManager invalidOperationManager = (InvalidOperationManager)other;
         if (this.uUID != null) {
            if (!this.uUID.equals(invalidOperationManager.uUID)) {
               return false;
            }
         } else if (invalidOperationManager.uUID != null) {
            return false;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "AttributeModifier{amount="
         + this.value
         + ", operation="
         + this.count
         + ", name='"
         + this.labelText
         + '\''
         + ", id="
         + this.uUID
         + ", serialize="
         + this.enabled
         + '}';
   }

   @Override
   public int hashCode() {
      return this.uUID != null ? this.uUID.hashCode() : 0;
   }
}
