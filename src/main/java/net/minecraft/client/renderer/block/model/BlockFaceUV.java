package net.minecraft.client.renderer.block.model;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
public class BlockFaceUV {
   public float[] uvs;
   public final int rotation;

   private int getVertexRotated(int n) {
      return (n + this.rotation / 90) % 4;
   }

   public float getVertexU(int n) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int vertexRotated = this.getVertexRotated(n);
         return vertexRotated != 0 && vertexRotated != 1 ? this.uvs[2] : this.uvs[0];
      }
   }

   public BlockFaceUV(float[] floats, int n) {
      this.uvs = floats;
      this.rotation = n;
   }

   public float getVertexV(int n) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int vertexRotated = this.getVertexRotated(n);
         return vertexRotated != 0 && vertexRotated != 3 ? this.uvs[3] : this.uvs[1];
      }
   }

   public int getVertexRotatedRev(int n) {
      return (n + (4 - this.rotation / 90)) % 4;
   }

   public void setUvs(float[] floats) {
      if (this.uvs == null) {
         this.uvs = floats;
      }
   }


   public static class Deserializer implements JsonDeserializer {
      private float[] parseUV(JsonObject json) {
         if (!json.has("uv")) {
            return null;
         } else {
            JsonArray jsonArray = JsonUtils.getJsonArray(json, "uv");
            if (jsonArray.size() != 4) {
               throw new JsonParseException("Expected 4 uv values, found: " + jsonArray.size());
            } else {
               float[] floats = new float[4];

               for (int i = 0; i < floats.length; i++) {
                  floats[i] = JsonUtils.getFloat(jsonArray.get(i), "uv[" + i + "]");
               }

               return floats;
            }
         }
      }

      public BlockFaceUV deserializeBlockFaceUV(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         float[] floats = this.parseUV(asJsonObject);
         int n = this.parseRotation(asJsonObject);
         return new BlockFaceUV(floats, n);
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeBlockFaceUV(elem, type, context);
      }

      public int parseRotation(JsonObject json) {
         int n = JsonUtils.getInt(json, "rotation", 0);
         if (n >= 0 && n % 90 == 0 && n / 90 <= 3) {
            return n;
         } else {
            throw new JsonParseException("Invalid rotation " + n + " found, only 0/90/180/270 allowed");
         }
      }
   }
}
