package net.optifine.entity.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.entity.model.anim.ModelVariableUpdater;
import net.optifine.player.PlayerItemParser;
import net.optifine.util.Json;

public class CustomEntityModelParser {
   public static final String ENTITY = null;
   public static final String TEXTURE = null;
   public static final String SHADOW_SIZE = null;
   public static final String ITEM_TYPE = null;
   public static final String ITEM_TEXTURE_SIZE = null;
   public static final String ITEM_USE_PLAYER_TEXTURE = null;
   public static final String ITEM_MODELS = null;
   public static final String ITEM_ANIMATIONS = null;
   public static final String MODEL_ID = null;
   public static final String MODEL_BASE_ID = null;
   public static final String MODEL_MODEL = null;
   public static final String MODEL_TYPE = null;
   public static final String MODEL_PART = null;
   public static final String MODEL_ATTACH = null;
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
   public static final String ENTITY_MODEL = null;
   public static final String ENTITY_MODEL_PART = null;

   private static void copyJsonElements(JsonObject json, JsonObject json2) {
      for (Entry entry : json.entrySet()) {
         if (!((String)entry.getKey()).equals("id") && !json2.has((String)entry.getKey())) {
            json2.add((String)entry.getKey(), (JsonElement)entry.getValue());
         }
      }
   }

   public static JsonObject loadJson(ResourceLocation location) throws IOException, JsonParseException {
      InputStream resourceStream = Config.getResourceStream(location);
      if (resourceStream == null) {
         return null;
      } else {
         String str = Config.readInputStream(resourceStream, "ASCII");
         resourceStream.close();
         return (JsonObject)new JsonParser().parse(str);
      }
   }

   public static CustomEntityRenderer parseEntityRender(JsonObject json, String str) {
      ConnectedParser connectedParser = new ConnectedParser("CustomEntityModels");
      String str2 = connectedParser.parseName(str);
      String str3 = connectedParser.parseBasePath(str);
      String string = Json.getString(json, "texture");
      int[] ints = Json.parseIntArray(json.get("textureSize"), 2);
      float f = Json.getFloat(json, "shadowSize", -1.0F);
      JsonArray array = (JsonArray)json.get("models");
      checkNull(array, "Missing models");
      HashMap map = new HashMap();
      ArrayList list = new ArrayList();

      for (int i = 0; i < array.size(); i++) {
         JsonObject json2 = (JsonObject)array.get(i);
         processBaseId(json2, map);
         processExternalModel(json2, map, str3);
         processId(json2, map);
         CustomModelRenderer customModelRenderer = parseCustomModelRenderer(json2, ints, str3);
         if (customModelRenderer != null) {
            list.add(customModelRenderer);
         }
      }

      CustomModelRenderer[] customModelRenderers = (CustomModelRenderer[])list.toArray(new CustomModelRenderer[list.size()]);
      ResourceLocation location = null;
      if (string != null) {
         location = parseResourceLocation(str3, string, ".png");
      }

      return new CustomEntityRenderer(str2, str3, location, customModelRenderers, f);
   }

   public static ResourceLocation parseResourceLocation(String str, String str2, String str3) {
      if (!str2.endsWith(str3)) {
         str2 = str2 + str3;
      }

      if (!str2.contains("/")) {
         str2 = str + "/" + str2;
      } else if (str2.startsWith("./")) {
         str2 = str + "/" + str2.substring(2);
      } else if (str2.startsWith("~/")) {
         str2 = "optifine/" + str2.substring(2);
      }

      return new ResourceLocation(str2);
   }

   private static void checkNull(Object obj, String str) {
      if (obj == null) {
         throw new JsonParseException(str);
      }
   }

   private static void processBaseId(JsonObject json, Map map) {
      String string = Json.getString(json, "baseId");
      if (string != null) {
         JsonObject json2 = (JsonObject)map.get(string);
         if (json2 == null) {
            Config.warn("BaseID not found: " + string);
         } else {
            copyJsonElements(json2, json);
         }
      }
   }

   private static void processId(JsonObject json, Map map) {
      String string = Json.getString(json, "id");
      if (string != null) {
         if (string.length() < 1) {
            Config.warn("Empty model ID: " + string);
         } else if (map.containsKey(string)) {
            Config.warn("Duplicate model ID: " + string);
         } else {
            map.put(string, json);
         }
      }
   }

   private static void processExternalModel(JsonObject json, Map map, String str) {
      String string = Json.getString(json, "model");
      if (string != null) {
         ResourceLocation location = parseResourceLocation(str, string, ".jpm");

         try {
            JsonObject json2 = loadJson(location);
            if (json2 == null) {
               Config.warn("Model not found: " + location);
               return;
            }

            copyJsonElements(json2, json);
         } catch (IOException e) {
            Config.error("" + e.getClass().getName() + ": " + e.getMessage());
         } catch (JsonParseException e2) {
            Config.error("" + e2.getClass().getName() + ": " + e2.getMessage());
         } catch (Exception e3) {
            e3.printStackTrace();
         }
      }
   }

   public static CustomModelRenderer parseCustomModelRenderer(JsonObject json, int[] ints, String str) {
      String string = Json.getString(json, "part");
      checkNull(string, "Model part not specified, missing \"replace\" or \"attachTo\".");
      boolean flag = Json.getBoolean(json, "attach", false);
      CustomEntityModel customEntityModel = new CustomEntityModel();
      if (ints != null) {
         customEntityModel.textureWidth = ints[0];
         customEntityModel.textureHeight = ints[1];
      }

      ModelUpdater modelUpdater = null;
      JsonArray array = (JsonArray)json.get("animations");
      if (array != null) {
         ArrayList list = new ArrayList();

         for (int i = 0; i < array.size(); i++) {

            for (Entry entry : ((JsonObject)array.get(i)).entrySet()) {
               String key = (String)entry.getKey();
               String asString = ((JsonElement)entry.getValue()).getAsString();
               list.add(new ModelVariableUpdater(key, asString));
            }
         }

         if (list.size() > 0) {
            ModelVariableUpdater[] modelVariableUpdaters = (ModelVariableUpdater[])list.toArray(new ModelVariableUpdater[list.size()]);
            modelUpdater = new ModelUpdater(modelVariableUpdaters);
         }
      }

      ModelRenderer modelRenderer = PlayerItemParser.parseModelRenderer(json, customEntityModel, ints, str);
      return new CustomModelRenderer(string, flag, modelRenderer, modelUpdater);
   }
}
