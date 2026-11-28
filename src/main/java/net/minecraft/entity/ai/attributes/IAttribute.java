package net.minecraft.entity.ai.attributes;

public interface IAttribute {
   boolean getShouldWatch();

   String getAttributeUnlocalizedName();

   IAttribute getParent();

   double getDefaultValue();

   double clampValue(double d);
}
