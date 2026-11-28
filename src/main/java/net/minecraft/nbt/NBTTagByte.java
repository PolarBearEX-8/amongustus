package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase.NBTPrimitive {
   private byte data;

   public NBTTagByte() {
   }

   @Override
   public short getShort() {
      return (short)this.data;
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(72L);
      this.data = dataInput.readByte();
   }

   @Override
   public String toString() {
      return "" + this.data + "b";
   }

   @Override
   public byte getId() {
      return 1;
   }

   @Override
   public long getLong() {
      return (long)this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagByte(this.data);
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(this.data);
   }

   @Override
   public byte getByte() {
      return this.data;
   }

   public NBTTagByte(byte b) {
      this.data = b;
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagByte nbtTagByte = (NBTTagByte)obj;
         return this.data == nbtTagByte.data;
      } else {
         return false;
      }
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public double getDouble() {
      return (double)this.data;
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }
}
