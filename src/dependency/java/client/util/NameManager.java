package client.util;

import com.google.common.base.MoreObjects;
import net.minecraft.block.properties.IProperty;

public abstract class NameManager implements IProperty {
   public final Class memberRef;
   public final String labelText;

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public Class getMemberRef() {
      return this.memberRef;
   }

   public NameManager(String text, Class clazz) {
      this.memberRef = clazz;
      this.labelText = text;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         NameManager nameManager = (NameManager)other;
         return this.memberRef.equals(nameManager.memberRef) && this.labelText.equals(nameManager.labelText);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("name", this.labelText).add("clazz", this.memberRef).add("values", this.getCollection()).toString();
   }

   @Override
   public int hashCode() {
      return 31 * this.memberRef.hashCode() + this.labelText.hashCode();
   }
}
