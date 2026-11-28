package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.EnumMap;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class BlockPart {
   public final Vector3f positionFrom;
   public final Vector3f positionTo;
   public final Map mapFaces;
   public final BlockPartRotation partRotation;
   public final boolean shade;

   private void setDefaultUvs() {
      for (Entry entry : (Iterable<Entry>)(this.mapFaces.entrySet())) {
         float[] faceUvs = this.getFaceUvs((EnumFacing)entry.getKey());
         ((BlockPartFace)entry.getValue()).blockFaceUV.setUvs(faceUvs);
      }
   }

   private float[] getFaceUvs(EnumFacing facing) {
      float[] floats;
      switch (facing) {
         case DOWN:
         case UP:
            floats = new float[]{this.positionFrom.x, this.positionFrom.z, this.positionTo.x, this.positionTo.z};
            break;
         case NORTH:
         case SOUTH:
            floats = new float[]{this.positionFrom.x, 16.0F - this.positionTo.y, this.positionTo.x, 16.0F - this.positionFrom.y};
            break;
         case WEST:
         case EAST:
            floats = new float[]{this.positionFrom.z, 16.0F - this.positionTo.y, this.positionTo.z, 16.0F - this.positionFrom.y};
            break;
         default:
            throw new NullPointerException();
      }

      return floats;
   }

   public BlockPart(Vector3f vector3f, Vector3f vector3f2, Map map, BlockPartRotation blockPartRotation, boolean flag) {
      this.positionFrom = vector3f;
      this.positionTo = vector3f2;
      this.mapFaces = map;
      this.partRotation = blockPartRotation;
      this.shade = flag;
      this.setDefaultUvs();
   }

public static class Deserializer implements JsonDeserializer {
      private Map parseFacesCheck(JsonDeserializationContext context, JsonObject json) {
         Map map = this.parseFaces(context, json);
         if (map.isEmpty()) {
            throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
         } else {
            return map;
         }
      }

      public BlockPart deserializeBlockPart(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         Vector3f vector3f = this.parsePositionFrom(asJsonObject);
         Vector3f vector3f2 = this.parsePositionTo(asJsonObject);
         BlockPartRotation blockPartRotation = this.parseRotation(asJsonObject);
         Map map = this.parseFacesCheck(context, asJsonObject);
         if (asJsonObject.has("shade") && !JsonUtils.isBoolean(asJsonObject, "shade")) {
            throw new JsonParseException("Expected shade to be a Boolean");
         } else {
            boolean flag = JsonUtils.getBoolean(asJsonObject, "shade", true);
            return new BlockPart(vector3f, vector3f2, map, blockPartRotation, flag);
         }
      }

      private EnumFacing parseEnumFacing(String str) {
         EnumFacing facing = EnumFacing.byName(str);
         if (facing == null) {
            throw new JsonParseException("Unknown facing: " + str);
         } else {
            return facing;
         }
      }

      private Map parseFaces(JsonDeserializationContext context, JsonObject json) {
         EnumMap enumMap = Maps.newEnumMap(EnumFacing.class);

         for (Entry entry : JsonUtils.getJsonObject(json, "faces").entrySet()) {
            enumMap.put(this.parseEnumFacing((String)entry.getKey()), (BlockPartFace)context.deserialize((JsonElement)entry.getValue(), BlockPartFace.class));
         }

         return enumMap;
      }

      private Vector3f parsePosition(JsonObject json, String str) {
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

      private Vector3f parsePositionFrom(JsonObject json) {
         Vector3f vector3f = this.parsePosition(json, "from");
         if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
            return vector3f;
         } else {
            throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + vector3f);
         }
      }

      private float parseAngle(JsonObject json) {
         float f = JsonUtils.getFloat(json, "angle");
         if (f != 0.0F && MathHelper.abs(f) != 22.5F && MathHelper.abs(f) != 45.0F) {
            throw new JsonParseException("Invalid rotation " + f + " found, only -45/-22.5/0/22.5/45 allowed");
         } else {
            return f;
         }
      }

      private Vector3f parsePositionTo(JsonObject json) {
         Vector3f vector3f = this.parsePosition(json, "to");
         if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
            return vector3f;
         } else {
            throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + vector3f);
         }
      }

      private BlockPartRotation parseRotation(JsonObject json) {
         BlockPartRotation blockPartRotation = null;
         if (json.has("rotation")) {
            JsonObject jsonObject = JsonUtils.getJsonObject(json, "rotation");
            Vector3f vector3f = this.parsePosition(jsonObject, "origin");
            vector3f.scale(0.0625F);
            EnumFacing.Axis axis = this.parseAxis(jsonObject);
            float f = this.parseAngle(jsonObject);
            boolean flag = JsonUtils.getBoolean(jsonObject, "rescale", false);
            blockPartRotation = new BlockPartRotation(vector3f, axis, f, flag);
         }

         return blockPartRotation;
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeBlockPart(elem, type, context);
      }

      private EnumFacing.Axis parseAxis(JsonObject json) {
         String string = JsonUtils.getString(json, "axis");
         EnumFacing.Axis axis = EnumFacing.Axis.byName(string.toLowerCase());
         if (axis == null) {
            throw new JsonParseException("Invalid rotation axis: " + string);
         } else {
            return axis;
         }
      }
   }
}
