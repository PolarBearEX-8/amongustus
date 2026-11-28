package net.minecraft.entity.ai.attributes;

import net.minecraft.util.MathHelper;

public class RangedAttribute extends BaseAttribute {
   private final double minimumValue;
   private final double maximumValue;
   private String description;

   @Override
   public double clampValue(double d) {
      return MathHelper.clamp_double(d, this.minimumValue, this.maximumValue);
   }

   public RangedAttribute setDescription(String str) {
      this.description = str;
      return this;
   }

   public RangedAttribute(IAttribute attribute, String str, double d, double d2, double d3) {
      super(attribute, str, d);
      this.minimumValue = d2;
      this.maximumValue = d3;
      if (d2 > d3) {
         throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
      } else if (d < d2) {
         throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
      } else if (d > d3) {
         throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
      }
   }

   public String getDescription() {
      return this.description;
   }
}
