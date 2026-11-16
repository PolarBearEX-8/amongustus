package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum AutoPlayTargetEntity {
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

   public static final Map map2 = Maps.newHashMap();
   public static final Map map = Maps.newHashMap();
   public final String labelText;
   public final int count;

   public String getLabelText() {
      return this.labelText;
   }

   public static AutoPlayTargetEntity get(String text) {
      return (AutoPlayTargetEntity)map2.get(text);
   }

   public int getCount() {
      return this.count;
   }

   private AutoPlayTargetEntity(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }


   static {
      for (AutoPlayTargetEntity entity : values()) {
         if (map2.containsKey(entity.getLabelText()) || map.containsKey(entity.getCount())) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + entity);
         }
         map2.put(entity.getLabelText(), entity);
         map.put(entity.getCount(), entity);
      }
   }
}