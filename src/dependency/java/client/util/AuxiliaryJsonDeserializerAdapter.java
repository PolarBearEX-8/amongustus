package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.item.AuxiliaryItemEntry;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class AuxiliaryJsonDeserializerAdapter implements JsonDeserializer {
   public static final Vector3f vector3f2 = new Vector3f(0.0F, 0.0F, 0.0F);
   public static final Vector3f vector3f3 = new Vector3f(0.0F, 0.0F, 0.0F);
   public static final Vector3f vector3f = new Vector3f(1.0F, 1.0F, 1.0F);

   private Vector3f resolveVector3f(JsonObject jsonObject, String text, Vector3f vector3f4) {
      if (!jsonObject.has(text)) {
         return vector3f4;
      } else {
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
   }

   public AuxiliaryItemEntry resolveAuxiliaryItemEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      Vector3f vector3f4 = this.resolveVector3f(asJsonObject, "rotation", vector3f2);
      Vector3f vector3f5 = this.resolveVector3f(asJsonObject, "translation", vector3f3);
      vector3f5.scale(0.0625F);
      vector3f5.x = MathHelper.clamp_float(vector3f5.x, -1.5F, 1.5F);
      vector3f5.y = MathHelper.clamp_float(vector3f5.y, -1.5F, 1.5F);
      vector3f5.z = MathHelper.clamp_float(vector3f5.z, -1.5F, 1.5F);
      Vector3f vector3f6 = this.resolveVector3f(asJsonObject, "scale", vector3f);
      vector3f6.x = MathHelper.clamp_float(vector3f6.x, -4.0F, 4.0F);
      vector3f6.y = MathHelper.clamp_float(vector3f6.y, -4.0F, 4.0F);
      vector3f6.z = MathHelper.clamp_float(vector3f6.z, -4.0F, 4.0F);
      return new AuxiliaryItemEntry(vector3f4, vector3f5, vector3f6);
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveAuxiliaryItemEntry(jsonElement, type, jsonDeserializationContext);
   }
}
