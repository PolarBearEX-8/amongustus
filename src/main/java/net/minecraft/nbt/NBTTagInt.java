package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTBase.NBTPrimitive {
   private int data;

   public NBTTagInt() {
   }

   @Override
   public byte getByte() {
      return (byte)(this.data & 0xFF);
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(96L);
      this.data = dataInput.readInt();
   }

   public NBTTagInt(int n) {
      this.data = n;
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagInt nbtTagInt = (NBTTagInt)obj;
         return this.data == nbtTagInt.data;
      } else {
         return false;
      }
   }

   @Override
   public NBTBase copy() {
      return new NBTTagInt(this.data);
   }

   @Override
   public long getLong() {
      return (long)this.data;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.data);
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public String toString() {
      return "" + this.data;
   }

   @Override
   public double getDouble() {
      return (double)this.data;
   }

   @Override
   public byte getId() {
      return 3;
   }

   @Override
   public short getShort() {
      return (short)(this.data & 65535);
   }
}
