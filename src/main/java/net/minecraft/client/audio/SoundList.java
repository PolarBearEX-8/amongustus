package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.List;

public class SoundList {
   private final List soundList = Lists.newArrayList();
   private boolean replaceExisting;
   private SoundCategory category;

   public void setReplaceExisting(boolean flag) {
      this.replaceExisting = flag;
   }

   public List getSoundList() {
      return this.soundList;
   }

   public boolean canReplaceExisting() {
      return this.replaceExisting;
   }

   public SoundCategory getSoundCategory() {
      return this.category;
   }

   public void setSoundCategory(SoundCategory soundCategory) {
      this.category = soundCategory;
   }


   public static class SoundEntry {
      private String name;
      private float volume = 1.0F;
      private float pitch = 1.0F;
      private int weight = 1;
      private SoundList.SoundEntry.Type type = SoundList.SoundEntry.Type.FILE;
      private boolean streaming = false;

      public void setSoundEntryType(SoundList.SoundEntry.Type type2) {
         this.type = type2;
      }

      public String getSoundEntryName() {
         return this.name;
      }

      public void setStreaming(boolean flag) {
         this.streaming = flag;
      }

      public SoundList.SoundEntry.Type getSoundEntryType() {
         return this.type;
      }

      public float getSoundEntryPitch() {
         return this.pitch;
      }

      public void setSoundEntryName(String str) {
         this.name = str;
      }

      public void setSoundEntryVolume(float f) {
         this.volume = f;
      }

      public boolean isStreaming() {
         return this.streaming;
      }

      public int getSoundEntryWeight() {
         return this.weight;
      }

      public float getSoundEntryVolume() {
         return this.volume;
      }

      public void setSoundEntryWeight(int n) {
         this.weight = n;
      }

      public void setSoundEntryPitch(float f) {
         this.pitch = f;
      }


      public enum Type {
         FILE("file"),
         SOUND_EVENT("event");

      private final String typeName;
      private Type(String str) {
            this.typeName = str;
         }

         public static Type getType(String str) {
            for (Type type : values()) {
               if (type.typeName.equals(str)) {
                  return type;
               }
            }

            return null;
         }
      }
   }
}
