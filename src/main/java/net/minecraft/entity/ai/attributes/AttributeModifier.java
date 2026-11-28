package net.minecraft.entity.ai.attributes;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.UUID;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class AttributeModifier {
   private final double amount;
   private final int operation;
   private final String name;
   private final UUID id;
   private boolean isSaved = true;

   public boolean isSaved() {
      return this.isSaved;
   }

   public int getOperation() {
      return this.operation;
   }

   public UUID getID() {
      return this.id;
   }

   public AttributeModifier(String str, double d, int n) {
      this(MathHelper.getRandomUuid(ThreadLocalRandom.current()), str, d, n);
   }

   public double getAmount() {
      return this.amount;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         AttributeModifier attributeModifier = (AttributeModifier)obj;
         if (this.id != null) {
            if (!this.id.equals(attributeModifier.id)) {
               return false;
            }
         } else if (attributeModifier.id != null) {
            return false;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.id != null ? this.id.hashCode() : 0;
   }

   public AttributeModifier(UUID uuid, String str, double d, int n) {
      this.id = uuid;
      this.name = str;
      this.amount = d;
      this.operation = n;
      Validate.notEmpty(str, "Modifier name cannot be empty", new Object[0]);
      Validate.inclusiveBetween(0L, 2L, (long)n, "Invalid operation");
   }

   @Override
   public String toString() {
      return "AttributeModifier{amount="
         + this.amount
         + ", operation="
         + this.operation
         + ", name='"
         + this.name
         + '\''
         + ", id="
         + this.id
         + ", serialize="
         + this.isSaved
         + '}';
   }

   public String getName() {
      return this.name;
   }

   public AttributeModifier setSaved(boolean flag) {
      this.isSaved = flag;
      return this;
   }
}
