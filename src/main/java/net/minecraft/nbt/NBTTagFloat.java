package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class NBTTagFloat extends NBTBase.NBTPrimitive {
   private float data;

   @Override
   public int getInt() {
      return MathHelper.floor_float(this.data);
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Float.floatToIntBits(this.data);
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagFloat nbtTagFloat = (NBTTagFloat)obj;
         return this.data == nbtTagFloat.data;
      } else {
         return false;
      }
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeFloat(this.data);
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(96L);
      this.data = dataInput.readFloat();
   }

   @Override
   public byte getId() {
      return 5;
   }

   @Override
   public long getLong() {
      return (long)this.data;
   }

   public NBTTagFloat(float f) {
      this.data = f;
   }

   public NBTTagFloat() {
   }

   @Override
   public byte getByte() {
      return (byte)(MathHelper.floor_float(this.data) & 0xFF);
   }

   @Override
   public float getFloat() {
      return this.data;
   }

   @Override
   public String toString() {
      return "" + this.data + "f";
   }

   @Override
   public NBTBase copy() {
      return new NBTTagFloat(this.data);
   }

   @Override
   public double getDouble() {
      return (double)this.data;
   }

   @Override
   public short getShort() {
      return (short)(MathHelper.floor_float(this.data) & 65535);
   }
}
