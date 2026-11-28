package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTBase {
   public static final String[] NBT_TYPES = new String[]{
      "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"
   };

   public abstract byte getId();

   public static NBTBase createNewByType(byte b) {
      switch (b) {
         case 0:
            return new NBTTagEnd();
         case 1:
            return new NBTTagByte();
         case 2:
            return new NBTTagShort();
         case 3:
            return new NBTTagInt();
         case 4:
            return new NBTTagLong();
         case 5:
            return new NBTTagFloat();
         case 6:
            return new NBTTagDouble();
         case 7:
            return new NBTTagByteArray();
         case 8:
            return new NBTTagString();
         case 9:
            return new NBTTagList();
         case 10:
            return new NBTTagCompound();
         case 11:
            return new NBTTagIntArray();
         default:
            return null;
      }
   }

   public boolean hasNoTags() {
      return false;
   }

   public abstract void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException;

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof NBTBase)) {
         return false;
      } else {
         NBTBase nbt = (NBTBase)obj;
         return this.getId() == nbt.getId();
      }
   }

   @Override
   public int hashCode() {
      return this.getId();
   }

   public abstract void write(DataOutput dataOutput) throws IOException;

   @Override
   public abstract String toString();

   public abstract NBTBase copy();

   public String getString() {
      return this.toString();
   }


   public static abstract class NBTPrimitive extends NBTBase {
      public abstract byte getByte();

      public abstract double getDouble();

      public abstract float getFloat();

      public abstract int getInt();

      public abstract short getShort();

      public abstract long getLong();
   }
}
