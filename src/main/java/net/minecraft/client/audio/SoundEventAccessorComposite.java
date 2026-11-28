package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor {
   private final List soundPool = Lists.newArrayList();
   private final Random rnd = new Random();
   private final ResourceLocation soundLocation;
   private final SoundCategory category;
   private double eventPitch;
   private double eventVolume;

   public SoundEventAccessorComposite(ResourceLocation location, double d, double d2, SoundCategory soundCategory) {
      this.soundLocation = location;
      this.eventVolume = d2;
      this.eventPitch = d;
      this.category = soundCategory;
   }

   public void addSoundToEventPool(ISoundEventAccessor soundEventAccessor) {
      this.soundPool.add(soundEventAccessor);
   }

   public SoundPoolEntry cloneEntry() {
      int weight = this.getWeight();
      if (!this.soundPool.isEmpty() && weight != 0) {
         int n = this.rnd.nextInt(weight);

         for (ISoundEventAccessor soundEventAccessor : (Iterable<ISoundEventAccessor>)(this.soundPool)) {
            n -= soundEventAccessor.getWeight();
            if (n < 0) {
               SoundPoolEntry soundPoolEntry = (SoundPoolEntry)soundEventAccessor.cloneEntryObject();
               soundPoolEntry.setPitch(soundPoolEntry.getPitch() * this.eventPitch);
               soundPoolEntry.setVolume(soundPoolEntry.getVolume() * this.eventVolume);
               return soundPoolEntry;
            }
         }

         return SoundHandler.missing_sound;
      } else {
         return SoundHandler.missing_sound;
      }
   }

   @Override
   public Object cloneEntryObject() {
      return this.cloneEntry();
   }

   public SoundCategory getSoundCategory() {
      return this.category;
   }

   public ResourceLocation getSoundEventLocation() {
      return this.soundLocation;
   }

   @Override
   public int getWeight() {
      int n = 0;

      for (ISoundEventAccessor soundEventAccessor : (Iterable<ISoundEventAccessor>)(this.soundPool)) {
         n += soundEventAccessor.getWeight();
      }

      return n;
   }
}
