package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTBase.NBTPrimitive {
   private short data;

   @Override
   public byte getId() {
      return 2;
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagShort nbtTagShort = (NBTTagShort)obj;
         return this.data == nbtTagShort.data;
      } else {
         return false;
      }
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagShort(this.data);
   }

   @Override
   public byte getByte() {
      return (byte)(this.data & 255);
   }

   @Override
   public long getLong() {
      return (long)this.data;
   }

   @Override
   public short getShort() {
      return this.data;
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeShort(this.data);
   }

   @Override
   public String toString() {
      return "" + this.data + "s";
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(80L);
      this.data = dataInput.readShort();
   }

   public NBTTagShort(short s) {
      this.data = s;
   }

   public NBTTagShort() {
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public double getDouble() {
      return (double)this.data;
   }
}
