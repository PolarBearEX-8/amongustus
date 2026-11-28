package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTBase.NBTPrimitive {
   private long data;

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeLong(this.data);
   }

   public NBTTagLong() {
   }

   @Override
   public byte getId() {
      return 4;
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagLong nbtTagLong = (NBTTagLong)obj;
         return this.data == nbtTagLong.data;
      } else {
         return false;
      }
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public short getShort() {
      return (short)((int)(this.data & 65535L));
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ (int)(this.data ^ this.data >>> 32);
   }

   @Override
   public int getInt() {
      return (int)(this.data & -1L);
   }

   public NBTTagLong(long l) {
      this.data = l;
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(128L);
      this.data = dataInput.readLong();
   }

   @Override
   public NBTBase copy() {
      return new NBTTagLong(this.data);
   }

   @Override
   public byte getByte() {
      return (byte)((int)(this.data & 255L));
   }

   @Override
   public long getLong() {
      return this.data;
   }

   @Override
   public double getDouble() {
      return (double)this.data;
   }

   @Override
   public String toString() {
      return "" + this.data + "L";
   }
}
