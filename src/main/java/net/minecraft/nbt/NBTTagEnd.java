package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagEnd extends NBTBase {
   @Override
   public NBTBase copy() {
      return new NBTTagEnd();
   }

   @Override
   public String toString() {
      return "END";
   }

   @Override
   public byte getId() {
      return 0;
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(64L);
   }
}
