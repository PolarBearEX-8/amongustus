package client.util;

import client.setting.AutoPlayTargetEntity;
import client.setting.MovementAlphaProperty;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.apache.commons.lang3.Validate;

public class EntryJsonDeserializer implements JsonDeserializer {
   public CustomListEntry resolveCustomListEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "entry");
      CustomListEntry customListEntry = new CustomListEntry();
      customListEntry.setEnabled(MissingManager.matchesJsonObject(jsonObject, "replace", false));
      AutoPlayTargetEntity autoPlayTargetEntity = AutoPlayTargetEntity.get(MissingManager.resolveText(jsonObject, "category", AutoPlayTargetEntity.MASTER.getLabelText()));
      customListEntry.setAutoPlayTargetEntity(autoPlayTargetEntity);
      Validate.notNull(autoPlayTargetEntity, "Invalid category", new Object[0]);
      if (jsonObject.has("sounds")) {
         JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "sounds");

         for (int index = 0; index < jsonArray.size(); index++) {
            JsonElement jsonElement2 = jsonArray.get(index);
            BridgeStringEntry bridgeStringEntry = new BridgeStringEntry();
            if (MissingManager.matchesJsonElement(jsonElement2)) {
               bridgeStringEntry.setLabelText(MissingManager.resolveText(jsonElement2, "sound"));
            } else {
               JsonObject jsonObject2 = MissingManager.resolveJsonObject(jsonElement2, "sound");
               bridgeStringEntry.setLabelText(MissingManager.resolveText(jsonObject2, "name"));
               if (jsonObject2.has("type")) {
                  MovementAlphaProperty movementAlphaProperty = MovementAlphaProperty.resolveMovementAlphaProperty(MissingManager.resolveText(jsonObject2, "type"));
                  Validate.notNull(movementAlphaProperty, "Invalid type", new Object[0]);
                  bridgeStringEntry.setMovementAlphaProperty(movementAlphaProperty);
               }

               if (jsonObject2.has("volume")) {
                  float factor = MissingManager.computeFactor(jsonObject2, "volume");
                  Validate.isTrue(factor > 0.0F, "Invalid volume", new Object[0]);
                  bridgeStringEntry.setFactor(factor);
               }

               if (jsonObject2.has("pitch")) {
                  float factor2 = MissingManager.computeFactor(jsonObject2, "pitch");
                  Validate.isTrue(factor2 > 0.0F, "Invalid pitch", new Object[0]);
                  bridgeStringEntry.setFactor2(factor2);
               }

               if (jsonObject2.has("weight")) {
                  int count = MissingManager.computeCount(jsonObject2, "weight");
                  Validate.isTrue(count > 0, "Invalid weight", new Object[0]);
                  bridgeStringEntry.setCount(count);
               }

               if (jsonObject2.has("stream")) {
                  bridgeStringEntry.setEnabled(MissingManager.matchesJsonObject3(jsonObject2, "stream"));
               }
            }

            customListEntry.getList().add(bridgeStringEntry);
         }
      }

      return customListEntry;
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveCustomListEntry(jsonElement, type, jsonDeserializationContext);
   }
}
