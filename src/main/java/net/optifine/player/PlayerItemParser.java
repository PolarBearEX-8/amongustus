package net.optifine.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.entity.model.CustomEntityModelParser;
import net.optifine.util.Json;

public class PlayerItemParser {
   private static JsonParser jsonParser = new JsonParser();
   public static final String ITEM_TYPE = null;
   public static final String ITEM_TEXTURE_SIZE = null;
   public static final String ITEM_USE_PLAYER_TEXTURE = null;
   public static final String ITEM_MODELS = null;
   public static final String MODEL_ID = null;
   public static final String MODEL_BASE_ID = null;
   public static final String MODEL_TYPE = null;
   public static final String MODEL_TEXTURE = null;
   public static final String MODEL_TEXTURE_SIZE = null;
   public static final String MODEL_ATTACH_TO = null;
   public static final String MODEL_INVERT_AXIS = null;
   public static final String MODEL_MIRROR_TEXTURE = null;
   public static final String MODEL_TRANSLATE = null;
   public static final String MODEL_ROTATE = null;
   public static final String MODEL_SCALE = null;
   public static final String MODEL_BOXES = null;
   public static final String MODEL_SPRITES = null;
   public static final String MODEL_SUBMODEL = null;
   public static final String MODEL_SUBMODELS = null;
   public static final String BOX_TEXTURE_OFFSET = null;
   public static final String BOX_COORDINATES = null;
   public static final String BOX_SIZE_ADD = null;
   public static final String BOX_UV_DOWN = null;
   public static final String BOX_UV_UP = null;
   public static final String BOX_UV_NORTH = null;
   public static final String BOX_UV_SOUTH = null;
   public static final String BOX_UV_WEST = null;
   public static final String BOX_UV_EAST = null;
   public static final String BOX_UV_FRONT = null;
   public static final String BOX_UV_BACK = null;
   public static final String BOX_UV_LEFT = null;
   public static final String BOX_UV_RIGHT = null;
   public static final String ITEM_TYPE_MODEL = null;
   public static final String MODEL_TYPE_BOX = null;

   public static PlayerItemModel parseItemModel(JsonObject json) {
      String string = Json.getString(json, "type");
      if (!Config.equals(string, "PlayerItem")) {
         throw new JsonParseException("Unknown model type: " + string);
      } else {
         int[] ints = Json.parseIntArray(json.get("textureSize"), 2);
         checkNull(ints, "Missing texture size");
         Dimension dimension = new Dimension(ints[0], ints[1]);
         boolean flag = Json.getBoolean(json, "usePlayerTexture", false);
         JsonArray array = (JsonArray)json.get("models");
         checkNull(array, "Missing elements");
         HashMap map = new HashMap();
         ArrayList list = new ArrayList();
         new ArrayList();

         for (int i = 0; i < array.size(); i++) {
            JsonObject json2 = (JsonObject)array.get(i);
            String string2 = Json.getString(json2, "baseId");
            if (string2 != null) {
               JsonObject json3 = (JsonObject)map.get(string2);
               if (json3 == null) {
                  Config.warn("BaseID not found: " + string2);
                  continue;
               }

               for (Entry entry : json3.entrySet()) {
                  if (!json2.has((String)entry.getKey())) {
                     json2.add((String)entry.getKey(), (JsonElement)entry.getValue());
                  }
               }
            }

            String string3 = Json.getString(json2, "id");
            if (string3 != null) {
               if (!map.containsKey(string3)) {
                  map.put(string3, json2);
               } else {
                  Config.warn("Duplicate model ID: " + string3);
               }
            }

            PlayerItemRenderer playerItemRenderer = parseItemRenderer(json2, dimension);
            if (playerItemRenderer != null) {
               list.add(playerItemRenderer);
            }
         }

         PlayerItemRenderer[] playerItemRenderers = (PlayerItemRenderer[])list.toArray(new PlayerItemRenderer[list.size()]);
         return new PlayerItemModel(dimension, flag, playerItemRenderers);
      }
   }

   private static ResourceLocation makeResourceLocation(String str) {
      int n = str.indexOf(58);
      if (n < 0) {
         return new ResourceLocation(str);
      } else {
         String str2 = str.substring(0, n);
         String str3 = str.substring(n + 1);
         return new ResourceLocation(str2, str3);
      }
   }

   private static int parseAttachModel(String str) {
      if (str == null) {
         return 0;
      } else if (str.equals("body")) {
         return 0;
      } else if (str.equals("head")) {
         return 1;
      } else if (str.equals("leftArm")) {
         return 2;
      } else if (str.equals("rightArm")) {
         return 3;
      } else if (str.equals("leftLeg")) {
         return 4;
      } else if (str.equals("rightLeg")) {
         return 5;
      } else if (str.equals("cape")) {
         return 6;
      } else {
         Config.warn("Unknown attachModel: " + str);
         return 0;
      }
   }

   private static int[][] parseFaceUvs(JsonObject json) {
      int[][] intsArr = new int[][]{
         Json.parseIntArray(json.get("uvDown"), 4),
         Json.parseIntArray(json.get("uvUp"), 4),
         Json.parseIntArray(json.get("uvNorth"), 4),
         Json.parseIntArray(json.get("uvSouth"), 4),
         Json.parseIntArray(json.get("uvWest"), 4),
         Json.parseIntArray(json.get("uvEast"), 4)
      };
      if (intsArr[2] == null) {
         intsArr[2] = Json.parseIntArray(json.get("uvFront"), 4);
      }

      if (intsArr[3] == null) {
         intsArr[3] = Json.parseIntArray(json.get("uvBack"), 4);
      }

      if (intsArr[4] == null) {
         intsArr[4] = Json.parseIntArray(json.get("uvLeft"), 4);
      }

      if (intsArr[5] == null) {
         intsArr[5] = Json.parseIntArray(json.get("uvRight"), 4);
      }

      boolean flag = false;

      for (int i = 0; i < intsArr.length; i++) {
         if (intsArr[i] != null) {
            flag = true;
         }
      }

      return !flag ? (int[][])null : intsArr;
   }

   public static ModelRenderer parseModelRenderer(JsonObject json, ModelBase modelBase, int[] ints, String str) {
      ModelRenderer modelRenderer = new ModelRenderer(modelBase);
      modelRenderer.setId(Json.getString(json, "id"));
      float f = Json.getFloat(json, "scale", 1.0F);
      modelRenderer.scaleX = f;
      modelRenderer.scaleY = f;
      modelRenderer.scaleZ = f;
      String string2 = Json.getString(json, "texture");
      if (string2 != null) {
         modelRenderer.setTextureLocation(CustomEntityModelParser.parseResourceLocation(str, string2, ".png"));
      }

      int[] ints2 = Json.parseIntArray(json.get("textureSize"), 2);
      if (ints2 == null) {
         ints2 = ints;
      }

      if (ints2 != null) {
         modelRenderer.setTextureSize(ints2[0], ints2[1]);
      }

      String str2 = Json.getString(json, "invertAxis", "").toLowerCase();
      boolean flag = str2.contains("x");
      boolean flag2 = str2.contains("y");
      boolean flag3 = str2.contains("z");
      float[] floats = Json.parseFloatArray(json.get("translate"), 3, new float[3]);
      if (flag) {
         floats[0] = -floats[0];
      }

      if (flag2) {
         floats[1] = -floats[1];
      }

      if (flag3) {
         floats[2] = -floats[2];
      }

      float[] floats2 = Json.parseFloatArray(json.get("rotate"), 3, new float[3]);

      for (int i = 0; i < floats2.length; i++) {
         floats2[i] = floats2[i] / 180.0F * (float) Math.PI;
      }

      if (flag) {
         floats2[0] = -floats2[0];
      }

      if (flag2) {
         floats2[1] = -floats2[1];
      }

      if (flag3) {
         floats2[2] = -floats2[2];
      }

      modelRenderer.setRotationPoint(floats[0], floats[1], floats[2]);
      modelRenderer.rotateAngleX = floats2[0];
      modelRenderer.rotateAngleY = floats2[1];
      modelRenderer.rotateAngleZ = floats2[2];
      String str3 = Json.getString(json, "mirrorTexture", "").toLowerCase();
      boolean flag4 = str3.contains("u");
      boolean flag5 = str3.contains("v");
      if (flag4) {
         modelRenderer.mirror = true;
      }

      if (flag5) {
         modelRenderer.mirrorV = true;
      }

      JsonArray asJsonArray = json.getAsJsonArray("boxes");
      if (asJsonArray != null) {
         for (int j = 0; j < asJsonArray.size(); j++) {
            JsonObject asJsonObject = asJsonArray.get(j).getAsJsonObject();
            int[] ints3 = Json.parseIntArray(asJsonObject.get("textureOffset"), 2);
            int[][] intsArr = parseFaceUvs(asJsonObject);
            if (ints3 == null && intsArr == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] floats3 = Json.parseFloatArray(asJsonObject.get("coordinates"), 6);
            if (floats3 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (flag) {
               floats3[0] = -floats3[0] - floats3[3];
            }

            if (flag2) {
               floats3[1] = -floats3[1] - floats3[4];
            }

            if (flag3) {
               floats3[2] = -floats3[2] - floats3[5];
            }

            float f2 = Json.getFloat(asJsonObject, "sizeAdd", 0.0F);
            if (intsArr != null) {
               modelRenderer.addBox(intsArr, floats3[0], floats3[1], floats3[2], floats3[3], floats3[4], floats3[5], f2);
            } else {
               modelRenderer.setTextureOffset(ints3[0], ints3[1]);
               modelRenderer.addBox(floats3[0], floats3[1], floats3[2], (int)floats3[3], (int)floats3[4], (int)floats3[5], f2);
            }
         }
      }

      JsonArray asJsonArray2 = json.getAsJsonArray("sprites");
      if (asJsonArray2 != null) {
         for (int k = 0; k < asJsonArray2.size(); k++) {
            JsonObject asJsonObject2 = asJsonArray2.get(k).getAsJsonObject();
            int[] ints4 = Json.parseIntArray(asJsonObject2.get("textureOffset"), 2);
            if (ints4 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] floats4 = Json.parseFloatArray(asJsonObject2.get("coordinates"), 6);
            if (floats4 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (flag) {
               floats4[0] = -floats4[0] - floats4[3];
            }

            if (flag2) {
               floats4[1] = -floats4[1] - floats4[4];
            }

            if (flag3) {
               floats4[2] = -floats4[2] - floats4[5];
            }

            float f3 = Json.getFloat(asJsonObject2, "sizeAdd", 0.0F);
            modelRenderer.setTextureOffset(ints4[0], ints4[1]);
            modelRenderer.addSprite(floats4[0], floats4[1], floats4[2], (int)floats4[3], (int)floats4[4], (int)floats4[5], f3);
         }
      }

      JsonObject json2 = (JsonObject)json.get("submodel");
      if (json2 != null) {
         modelRenderer.addChild(parseModelRenderer(json2, modelBase, ints2, str));
      }

      JsonArray array = (JsonArray)json.get("submodels");
      if (array != null) {
         for (int m = 0; m < array.size(); m++) {
            ModelRenderer modelRenderer3 = parseModelRenderer((JsonObject)array.get(m), modelBase, ints2, str);
            if (modelRenderer3.getId() != null) {
               if (modelRenderer.getChild(modelRenderer3.getId()) != null) {
                  Config.warn("Duplicate model ID: " + modelRenderer3.getId());
               }
            }

            modelRenderer.addChild(modelRenderer3);
         }
      }

      return modelRenderer;
   }

   public static PlayerItemRenderer parseItemRenderer(JsonObject json, Dimension dimension) {
      String string = Json.getString(json, "type");
      if (!Config.equals(string, "ModelBox")) {
         Config.warn("Unknown model type: " + string);
         return null;
      } else {
         int n = parseAttachModel(Json.getString(json, "attachTo"));
         ModelPlayerItem modelPlayerItem = new ModelPlayerItem();
         modelPlayerItem.textureWidth = dimension.width;
         modelPlayerItem.textureHeight = dimension.height;
         ModelRenderer modelRenderer = parseModelRenderer(json, modelPlayerItem, (int[])null, (String)null);
         return new PlayerItemRenderer(n, modelRenderer);
      }
   }

   private static void checkNull(Object obj, String str) {
      if (obj == null) {
         throw new JsonParseException(str);
      }
   }
}
