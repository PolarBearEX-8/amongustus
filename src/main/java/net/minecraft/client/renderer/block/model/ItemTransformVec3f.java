package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class ItemTransformVec3f {
   public static final ItemTransformVec3f DEFAULT = new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public final Vector3f rotation;
   public final Vector3f translation;
   public final Vector3f scale;

   public ItemTransformVec3f(Vector3f vector3f, Vector3f vector3f2, Vector3f vector3f3) {
      this.rotation = new Vector3f(vector3f);
      this.translation = new Vector3f(vector3f2);
      this.scale = new Vector3f(vector3f3);
   }

   @Override
   public int hashCode() {
      int n = this.rotation.hashCode();
      n = 31 * n + this.translation.hashCode();
      return 31 * n + this.scale.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ItemTransformVec3f itemTransformVec3f = (ItemTransformVec3f)obj;
         return !this.rotation.equals(itemTransformVec3f.rotation) ? false : (!this.scale.equals(itemTransformVec3f.scale) ? false : this.translation.equals(itemTransformVec3f.translation));
      }
   }


   public static class Deserializer implements JsonDeserializer {
      private static final Vector3f ROTATION_DEFAULT = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f TRANSLATION_DEFAULT = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f SCALE_DEFAULT = new Vector3f(1.0F, 1.0F, 1.0F);

      public ItemTransformVec3f deserializeItemTransform(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         Vector3f vector3f = this.parseVector3f(asJsonObject, "rotation", ROTATION_DEFAULT);
         Vector3f vector3f2 = this.parseVector3f(asJsonObject, "translation", TRANSLATION_DEFAULT);
         vector3f2.scale(0.0625F);
         vector3f2.x = MathHelper.clamp_float(vector3f2.x, -1.5F, 1.5F);
         vector3f2.y = MathHelper.clamp_float(vector3f2.y, -1.5F, 1.5F);
         vector3f2.z = MathHelper.clamp_float(vector3f2.z, -1.5F, 1.5F);
         Vector3f vector3f3 = this.parseVector3f(asJsonObject, "scale", SCALE_DEFAULT);
         vector3f3.x = MathHelper.clamp_float(vector3f3.x, -4.0F, 4.0F);
         vector3f3.y = MathHelper.clamp_float(vector3f3.y, -4.0F, 4.0F);
         vector3f3.z = MathHelper.clamp_float(vector3f3.z, -4.0F, 4.0F);
         return new ItemTransformVec3f(vector3f, vector3f2, vector3f3);
      }

      private Vector3f parseVector3f(JsonObject json, String str, Vector3f vector3f) {
         if (!json.has(str)) {
            return vector3f;
         } else {
            JsonArray jsonArray = JsonUtils.getJsonArray(json, str);
            if (jsonArray.size() != 3) {
               throw new JsonParseException("Expected 3 " + str + " values, found: " + jsonArray.size());
            } else {
               float[] floats = new float[3];

               for (int i = 0; i < floats.length; i++) {
                  floats[i] = JsonUtils.getFloat(jsonArray.get(i), str + "[" + i + "]");
               }

               return new Vector3f(floats[0], floats[1], floats[2]);
            }
         }
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeItemTransform(elem, type, context);
      }
   }
}
