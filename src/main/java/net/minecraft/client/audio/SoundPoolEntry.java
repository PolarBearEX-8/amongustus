package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public class SoundPoolEntry {
   private final ResourceLocation location;
   private final boolean streamingSound;
   private double pitch;
   private double volume;

   public SoundPoolEntry(SoundPoolEntry soundPoolEntry) {
      this.location = soundPoolEntry.location;
      this.pitch = soundPoolEntry.pitch;
      this.volume = soundPoolEntry.volume;
      this.streamingSound = soundPoolEntry.streamingSound;
   }

   public ResourceLocation getSoundPoolEntryLocation() {
      return this.location;
   }

   public boolean isStreamingSound() {
      return this.streamingSound;
   }

   public void setPitch(double d) {
      this.pitch = d;
   }

   public SoundPoolEntry(ResourceLocation location2, double d, double d2, boolean flag) {
      this.location = location2;
      this.pitch = d;
      this.volume = d2;
      this.streamingSound = flag;
   }

   public double getPitch() {
      return this.pitch;
   }

   public void setVolume(double d) {
      this.volume = d;
   }

   public double getVolume() {
      return this.volume;
   }
}
