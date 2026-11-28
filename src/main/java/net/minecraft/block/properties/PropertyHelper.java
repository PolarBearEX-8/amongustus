package net.minecraft.block.properties;

import com.google.common.base.MoreObjects;

public abstract class PropertyHelper implements IProperty {
   private final Class valueClass;
   private final String name;

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public int hashCode() {
      return 31 * this.valueClass.hashCode() + this.name.hashCode();
   }

   @Override
   public Class getValueClass() {
      return this.valueClass;
   }

   public PropertyHelper(String str, Class clazz) {
      this.valueClass = clazz;
      this.name = str;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         PropertyHelper propertyHelper = (PropertyHelper)obj;
         return this.valueClass.equals(propertyHelper.valueClass) && this.name.equals(propertyHelper.name);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("name", this.name).add("clazz", this.valueClass).add("values", this.getAllowedValues()).toString();
   }
}
