package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SoundCategory {
   MASTER("master", 0),
   MUSIC("music", 1),
   RECORDS("record", 2),
   WEATHER("weather", 3),
   BLOCKS("block", 4),
   MOBS("hostile", 5),
   ANIMALS("neutral", 6),
   PLAYERS("player", 7),
   AMBIENT("ambient", 8),
   AUGUSTUS("augustus", 9);

private static final Map NAME_CATEGORY_MAP = Maps.newHashMap();
   private static final Map ID_CATEGORY_MAP = Maps.newHashMap();
   private final String categoryName;
   private final int categoryId;

   static {
      for (SoundCategory soundCategory : values()) {
         if (NAME_CATEGORY_MAP.containsKey(soundCategory.getCategoryName()) || ID_CATEGORY_MAP.containsKey(soundCategory.getCategoryId())) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + soundCategory);
         }

         NAME_CATEGORY_MAP.put(soundCategory.getCategoryName(), soundCategory);
         ID_CATEGORY_MAP.put(soundCategory.getCategoryId(), soundCategory);
      }
   }

   public static SoundCategory getCategory(String str) {
      return (SoundCategory)NAME_CATEGORY_MAP.get(str);
   }

   public String getCategoryName() {
      return this.categoryName;
   }

   private SoundCategory(String str, int n) {
      this.categoryName = str;
      this.categoryId = n;
   }

   public int getCategoryId() {
      return this.categoryId;
   }
}
