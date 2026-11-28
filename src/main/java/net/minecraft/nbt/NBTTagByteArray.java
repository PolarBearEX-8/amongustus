package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagByteArray extends NBTBase {
   private byte[] data;

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.data.length);
      dataOutput.write(this.data);
   }

   public NBTTagByteArray() {
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(192L);
      int n2 = dataInput.readInt();
      nbtSizeTracker.read((long)(8 * n2));
      this.data = new byte[n2];
      dataInput.readFully(this.data);
   }

   @Override
   public NBTBase copy() {
      byte[] bytes = new byte[this.data.length];
      System.arraycopy(this.data, 0, bytes, 0, this.data.length);
      return new NBTTagByteArray(bytes);
   }

   @Override
   public String toString() {
      return "[" + this.data.length + " bytes]";
   }

   @Override
   public boolean equals(Object obj) {
      return super.equals(obj) ? Arrays.equals(this.data, ((NBTTagByteArray)obj).data) : false;
   }

   public NBTTagByteArray(byte[] bytes) {
      this.data = bytes;
   }

   @Override
   public byte getId() {
      return 7;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.data);
   }

   public byte[] getByteArray() {
      return this.data;
   }
}
