package client.util;

import client.setting.EnumFacingXType;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class InternalJsonDeserializerAdapter implements JsonDeserializer {
   private float computeFactor(JsonObject jsonObject) {
      float factor = MissingManager.computeFactor(jsonObject, "angle");
      if (factor != 0.0F && MathHelper.computeFactor(factor) != 22.5F && MathHelper.computeFactor(factor) != 45.0F) {
         throw new JsonParseException("Invalid rotation " + factor + " found, only -45/-22.5/0/22.5/45 allowed");
      } else {
         return factor;
      }
   }

   public Vector3fHolder resolveVector3fHolder(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      Vector3f vector3f = this.resolveVector3f(asJsonObject);
      Vector3f vector3f2 = this.resolveVector3fByJsonObject(asJsonObject);
      Vector3fEntry vector3fEntry = this.resolveVector3fEntry(asJsonObject);
      Map map = this.resolveMap(jsonDeserializationContext, asJsonObject);
      if (asJsonObject.has("shade") && !MissingManager.matchesJsonObject(asJsonObject, "shade")) {
         throw new JsonParseException("Expected shade to be a Boolean");
      } else {
         boolean flag = MissingManager.matchesJsonObject(asJsonObject, "shade", true);
         return new Vector3fHolder(vector3f, vector3f2, map, vector3fEntry, flag);
      }
   }

   private EnumFacingXType resolveEnumFacingXType(JsonObject jsonObject) {
      String text = MissingManager.resolveText(jsonObject, "axis");
      EnumFacingXType enumFacingXType = EnumFacingXType.resolveEnumFacingXType(text.toLowerCase());
      if (enumFacingXType == null) {
         throw new JsonParseException("Invalid rotation axis: " + text);
      } else {
         return enumFacingXType;
      }
   }

   private EnumFacing resolveEnumFacing(String text) {
      EnumFacing enumFacing = EnumFacing.resolveEnumFacing(text);
      if (enumFacing == null) {
         throw new JsonParseException("Unknown facing: " + text);
      } else {
         return enumFacing;
      }
   }

   private Map resolveMapByJsonDeserializat(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
      EnumMap enumMap = Maps.newEnumMap(EnumFacing.class);

      for (Entry entry : MissingManager.resolveJsonObject(jsonObject, "faces").entrySet()) {
         EnumFacing enumFacing = this.resolveEnumFacing((String)entry.getKey());
         enumMap.put(enumFacing, (EnumFacingHolder)jsonDeserializationContext.deserialize((JsonElement)entry.getValue(), EnumFacingHolder.class));
      }

      return enumMap;
   }

   private Vector3f resolveVector3fByJsonObject(JsonObject jsonObject) {
      Vector3f vector3f = this.resolveVector3f(jsonObject, "to");
      if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
         return vector3f;
      } else {
         throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + vector3f);
      }
   }

   private Map resolveMap(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
      Map map = this.resolveMapByJsonDeserializat(jsonDeserializationContext, jsonObject);
      if (map.isEmpty()) {
         throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
      } else {
         return map;
      }
   }

   private Vector3fEntry resolveVector3fEntry(JsonObject jsonObject) {
      Vector3fEntry vector3fEntry = null;
      if (jsonObject.has("rotation")) {
         JsonObject jsonObject2 = MissingManager.resolveJsonObject(jsonObject, "rotation");
         Vector3f vector3f = this.resolveVector3f(jsonObject2, "origin");
         vector3f.scale(0.0625F);
         EnumFacingXType enumFacingXType = this.resolveEnumFacingXType(jsonObject2);
         float factor = this.computeFactor(jsonObject2);
         boolean flag = MissingManager.matchesJsonObject(jsonObject2, "rescale", false);
         vector3fEntry = new Vector3fEntry(vector3f, enumFacingXType, factor, flag);
      }

      return vector3fEntry;
   }

   private Vector3f resolveVector3f(JsonObject jsonObject) {
      Vector3f vector3f = this.resolveVector3f(jsonObject, "from");
      if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
         return vector3f;
      } else {
         throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + vector3f);
      }
   }

   private Vector3f resolveVector3f(JsonObject jsonObject, String text) {
      JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, text);
      if (jsonArray.size() != 3) {
         throw new JsonParseException("Expected 3 " + text + " values, found: " + jsonArray.size());
      } else {
         float[] factorArray = new float[3];

         for (int index = 0; index < factorArray.length; index++) {
            factorArray[index] = MissingManager.computeFactor(jsonArray.get(index), text + "[" + index + "]");
         }

         return new Vector3f(factorArray[0], factorArray[1], factorArray[2]);
      }
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveVector3fHolder(jsonElement, type, jsonDeserializationContext);
   }
}
