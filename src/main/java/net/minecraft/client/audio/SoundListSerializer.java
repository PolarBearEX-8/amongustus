package net.minecraft.client.audio;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class SoundListSerializer implements JsonDeserializer {

   public SoundList deserializeSoundList(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "entry");
      SoundList soundList = new SoundList();
      soundList.setReplaceExisting(JsonUtils.getBoolean(jsonObject, "replace", false));
      SoundCategory category = SoundCategory.getCategory(JsonUtils.getString(jsonObject, "category", SoundCategory.MASTER.getCategoryName()));
      soundList.setSoundCategory(category);
      Validate.notNull(category, "Invalid category", new Object[0]);
      if (jsonObject.has("sounds")) {
         JsonArray jsonArray = JsonUtils.getJsonArray(jsonObject, "sounds");

         for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement elem2 = jsonArray.get(i);
            SoundList.SoundEntry soundEntry = new SoundList.SoundEntry();
            if (JsonUtils.isString(elem2)) {
               soundEntry.setSoundEntryName(JsonUtils.getString(elem2, "sound"));
            } else {
               JsonObject jsonObject2 = JsonUtils.getJsonObject(elem2, "sound");
               soundEntry.setSoundEntryName(JsonUtils.getString(jsonObject2, "name"));
               if (jsonObject2.has("type")) {
                  SoundList.SoundEntry.Type type2 = SoundList.SoundEntry.Type.getType(JsonUtils.getString(jsonObject2, "type"));
                  Validate.notNull(type2, "Invalid type", new Object[0]);
                  soundEntry.setSoundEntryType(type2);
               }

               if (jsonObject2.has("volume")) {
                  float f = JsonUtils.getFloat(jsonObject2, "volume");
                  Validate.isTrue(f > 0.0F, "Invalid volume", new Object[0]);
                  soundEntry.setSoundEntryVolume(f);
               }

               if (jsonObject2.has("pitch")) {
                  float f2 = JsonUtils.getFloat(jsonObject2, "pitch");
                  Validate.isTrue(f2 > 0.0F, "Invalid pitch", new Object[0]);
                  soundEntry.setSoundEntryPitch(f2);
               }

               if (jsonObject2.has("weight")) {
                  int n = JsonUtils.getInt(jsonObject2, "weight");
                  Validate.isTrue(n > 0, "Invalid weight", new Object[0]);
                  soundEntry.setSoundEntryWeight(n);
               }

               if (jsonObject2.has("stream")) {
                  soundEntry.setStreaming(JsonUtils.getBoolean(jsonObject2, "stream"));
               }
            }

            soundList.getSoundList().add(soundEntry);
         }
      }

      return soundList;
   }

   public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
      return this.deserializeSoundList(elem, type, context);
   }
}
