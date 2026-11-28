package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class NBTTagDouble extends NBTBase.NBTPrimitive {
   private double data;

   @Override
   public byte getByte() {
      return (byte)(MathHelper.floor_double(this.data) & 0xFF);
   }

   @Override
   public int getInt() {
      return MathHelper.floor_double(this.data);
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(128L);
      this.data = dataInput.readDouble();
   }

   @Override
   public String toString() {
      return "" + this.data + "d";
   }

   @Override
   public long getLong() {
      return (long)Math.floor(this.data);
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeDouble(this.data);
   }

   public NBTTagDouble() {
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagDouble nbtTagDouble = (NBTTagDouble)obj;
         return this.data == nbtTagDouble.data;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      long l = Double.doubleToLongBits(this.data);
      return super.hashCode() ^ (int)(l ^ l >>> 32);
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public short getShort() {
      return (short)(MathHelper.floor_double(this.data) & 65535);
   }

   @Override
   public byte getId() {
      return 6;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagDouble(this.data);
   }

   public NBTTagDouble(double d) {
      this.data = d;
   }
}
