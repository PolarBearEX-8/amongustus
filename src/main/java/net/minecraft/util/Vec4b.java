package net.minecraft.util;

public class Vec4b {
   private byte type;
   private byte x;
   private byte y;
   private byte rotation;

   public Vec4b(Vec4b vec4b) {
      this.type = vec4b.type;
      this.x = vec4b.x;
      this.y = vec4b.y;
      this.rotation = vec4b.rotation;
   }

   public byte getRotation() {
      return this.rotation;
   }

   @Override
   public int hashCode() {
      int type2 = this.type;
      type2 = 31 * type2 + this.x;
      type2 = 31 * type2 + this.y;
      return 31 * type2 + this.rotation;
   }

   public byte getX() {
      return this.x;
   }

   public Vec4b(byte b, byte b2, byte b3, byte b4) {
      this.type = b;
      this.x = b2;
      this.y = b3;
      this.rotation = b4;
   }

   public byte getType() {
      return this.type;
   }

   public byte getY() {
      return this.y;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof Vec4b)) {
         return false;
      } else {
         Vec4b vec4b = (Vec4b)obj;
         return this.type != vec4b.type ? false : (this.rotation != vec4b.rotation ? false : (this.x != vec4b.x ? false : this.y == vec4b.y));
      }
   }
}
