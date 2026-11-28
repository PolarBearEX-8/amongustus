package net.minecraft.entity.ai.attributes;

import java.util.Collection;
import java.util.UUID;

public interface IAttributeInstance {
   Collection getModifiers();

   void applyModifier(AttributeModifier attributeModifier);

   void removeModifier(AttributeModifier attributeModifier);

   IAttribute getAttribute();

   double getAttributeValue();

   boolean hasModifier(AttributeModifier attributeModifier);

   void removeAllModifiers();

   AttributeModifier getModifier(UUID uuid);

   Collection getModifiersByOperation(int n);

   double getBaseValue();

   void setBaseValue(double d);
}
