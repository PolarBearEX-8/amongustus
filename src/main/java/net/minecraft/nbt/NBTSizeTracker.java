package net.minecraft.nbt;


public class NBTSizeTracker {
   public static final NBTSizeTracker INFINITE = new NBTSizeTracker.DefaultNBTSizeTracker(0L);
   private final long max;
   private long read;

   public NBTSizeTracker(long l) {
      this.max = l;
   }

   public void read(long l) {
      this.read += l / 8L;
      if (this.read > this.max) {
         throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.read + "bytes where max allowed: " + this.max);
      }
   }


   public static final class DefaultNBTSizeTracker extends NBTSizeTracker {
      public DefaultNBTSizeTracker(long l) {
         super(l);
      }

      @Override
      public void read(long l) {
      }
   }
}
