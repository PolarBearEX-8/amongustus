package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagString extends NBTBase {
   private String data;

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeUTF(this.data);
   }

   @Override
   public String getString() {
      return this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagString(this.data);
   }

   public NBTTagString() {
      this.data = "";
   }

   @Override
   public String toString() {
      return "\"" + this.data.replace("\"", "\\\"") + "\"";
   }

   @Override
   public byte getId() {
      return 8;
   }

   public NBTTagString(String str) {
      this.data = str;
      if (str == null) {
         throw new IllegalArgumentException("Empty string not allowed");
      }
   }

   @Override
   public boolean hasNoTags() {
      return this.data.isEmpty();
   }

   @Override
   public boolean equals(Object obj) {
      if (!super.equals(obj)) {
         return false;
      } else {
         NBTTagString nbtTagString = (NBTTagString)obj;
         return this.data == null && nbtTagString.data == null || this.data != null && this.data.equals(nbtTagString.data);
      }
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data.hashCode();
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(288L);
      this.data = dataInput.readUTF();
      nbtSizeTracker.read((long)(16 * this.data.length()));
   }
}
