package net.minecraft.item;

import org.lwjgl.util.vector.Vector3f;

public class AuxiliaryItemEntry {
   public static final AuxiliaryItemEntry auxiliaryItemEntry = new AuxiliaryItemEntry(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public final Vector3f vector3f2;
   public final Vector3f vector3f;
   public final Vector3f vector3f3;

   public AuxiliaryItemEntry(Vector3f vector3f4, Vector3f vector3f5, Vector3f vector3f6) {
      this.vector3f2 = new Vector3f(vector3f4);
      this.vector3f = new Vector3f(vector3f5);
      this.vector3f3 = new Vector3f(vector3f6);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (this.getClass() != other.getClass()) {
         return false;
      } else {
         AuxiliaryItemEntry auxiliaryItemEntry2 = (AuxiliaryItemEntry)other;
         return !this.vector3f2.equals(auxiliaryItemEntry2.vector3f2) ? false : (!this.vector3f3.equals(auxiliaryItemEntry2.vector3f3) ? false : this.vector3f.equals(auxiliaryItemEntry2.vector3f));
      }
   }

   @Override
   public int hashCode() {
      int hash = this.vector3f2.hashCode();
      int count = 31 * hash + this.vector3f.hashCode();
      return 31 * count + this.vector3f3.hashCode();
   }
}
