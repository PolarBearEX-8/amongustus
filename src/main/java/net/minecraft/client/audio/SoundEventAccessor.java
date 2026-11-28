package net.minecraft.client.audio;

public class SoundEventAccessor implements ISoundEventAccessor {
   private final SoundPoolEntry entry;
   private final int weight;

   @Override
   public Object cloneEntryObject() {
      return this.cloneEntry();
   }

   public SoundPoolEntry cloneEntry() {
      return new SoundPoolEntry(this.entry);
   }

   @Override
   public int getWeight() {
      return this.weight;
   }

   public SoundEventAccessor(SoundPoolEntry soundPoolEntry, int n) {
      this.entry = soundPoolEntry;
      this.weight = n;
   }
}
