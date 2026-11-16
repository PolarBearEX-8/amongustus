package client.util;

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
import net.minecraft.util.ResourceLocation;

public class InterpolationCoordinator {
   public static final String labelText = "entity";
   public static final String labelText2 = "texture";
   public static final String labelText3 = "shadowSize";
   public static final String labelText4 = "type";
   public static final String labelText5 = "textureSize";
   public static final String labelText6 = "usePlayerTexture";
   public static final String labelText7 = "models";
   public static final String labelText8 = "animations";
   public static final String labelText9 = "id";
   public static final String labelText10 = "baseId";
   public static final String labelText11 = "model";
   public static final String labelText12 = "type";
   public static final String labelText13 = "part";
   public static final String labelText14 = "attach";
   public static final String labelText15 = "invertAxis";
   public static final String labelText16 = "mirrorTexture";
   public static final String labelText17 = "translate";
   public static final String labelText18 = "rotate";
   public static final String labelText19 = "scale";
   public static final String labelText20 = "boxes";
   public static final String labelText21 = "sprites";
   public static final String labelText22 = "submodel";
   public static final String labelText23 = "submodels";
   public static final String labelText24 = "textureOffset";
   public static final String labelText25 = "coordinates";
   public static final String labelText26 = "sizeAdd";
   public static final String labelText27 = "EntityModel";
   public static final String labelText28 = "EntityModelPart";

   private static void applyJsonObject(JsonObject jsonObject, Map map) {
      String text = WrongArrayHelper.resolveText(jsonObject, "baseId");
      if (text != null) {
         JsonObject jsonObject2 = (JsonObject)map.get(text);
         if (jsonObject2 == null) {
            BuildManager.applyText3("BaseID not found: " + text);
         } else {
            applyJsonObject(jsonObject2, jsonObject);
         }
      }
   }

   private static void applyJsonObjectByJsonObject(JsonObject jsonObject, Map map) {
      String text = WrongArrayHelper.resolveText(jsonObject, "id");
      if (text != null) {
         if (text.length() < 1) {
            BuildManager.applyText3("Empty model ID: " + text);
         } else if (map.containsKey(text)) {
            BuildManager.applyText3("Duplicate model ID: " + text);
         } else {
            map.put(text, jsonObject);
         }
      }
   }

   public static ResourceLocation resolveResourceLocation(String text, String text2, String text3) {
      String text4 = text2;
      if (!text2.endsWith(text3)) {
         text4 = text2 + text3;
      }

      if (!text4.contains("/")) {
         text4 = text + "/" + text4;
      } else if (text4.startsWith("./")) {
         text4 = text + "/" + text4.substring(2);
      } else if (text4.startsWith("~/")) {
         text4 = "optifine/" + text4.substring(2);
      }

      return new ResourceLocation(text4);
   }

   public static JsonObject resolveJsonObjectByResourceLocation(ResourceLocation location) throws IOException, JsonParseException {
      InputStream inputStream = BuildManager.resolveInputStream(location);
      if (inputStream == null) {
         return null;
      } else {
         String text = BuildManager.resolveText(inputStream, "ASCII");
         inputStream.close();
         return (JsonObject)new JsonParser().parse(text);
      }
   }

   private static void applyJsonObject(JsonObject jsonObject, JsonObject jsonObject2) {
      for (Entry entry : jsonObject.entrySet()) {
         if (!((String)entry.getKey()).equals("id") && !jsonObject2.has((String)entry.getKey())) {
            jsonObject2.add((String)entry.getKey(), (JsonElement)entry.getValue());
         }
      }
   }

   private static void applyJsonObject(JsonObject jsonObject, Map map, String text) {
      String text2 = WrongArrayHelper.resolveText(jsonObject, "model");
      if (text2 != null) {
         ResourceLocation location = resolveResourceLocation(text, text2, ".jpm");

         try {
            JsonObject jsonObject2 = resolveJsonObjectByResourceLocation(location);
            if (jsonObject2 == null) {
               BuildManager.applyText3("Model not found: " + location);
               return;
            }

            applyJsonObject(jsonObject2, jsonObject);
         } catch (IOException ioException) {
            BuildManager.applyText2("" + ioException.getClass().getName() + ": " + ioException.getMessage());
         } catch (JsonParseException jsonParseException) {
            BuildManager.applyText2("" + jsonParseException.getClass().getName() + ": " + jsonParseException.getMessage());
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   private static void applyTarget(Object entry, String text) {
      if (entry == null) {
         throw new JsonParseException(text);
      }
   }

   public static GeometryConverter resolveGeometryConverter(JsonObject jsonObject, String text) {
      NetherManager netherManager = new NetherManager("CustomEntityModels");
      String text2 = netherManager.resolveText(text);
      String text3 = netherManager.resolveTextByText(text);
      String text4 = WrongArrayHelper.resolveText(jsonObject, "texture");
      int[] countArray = WrongArrayHelper.resolveIndexArray(jsonObject.get("textureSize"), 2);
      float factor = WrongArrayHelper.computeFactor(jsonObject, "shadowSize", -1.0F);
      JsonArray jsonArray = (JsonArray)jsonObject.get("models");
      applyTarget(jsonArray, "Missing models");
      HashMap map = new HashMap();
      ArrayList list = new ArrayList();

      for (int index = 0; index < jsonArray.size(); index++) {
         JsonObject jsonObject2 = (JsonObject)jsonArray.get(index);
         applyJsonObject(jsonObject2, map);
         applyJsonObject(jsonObject2, map, text3);
         applyJsonObjectByJsonObject(jsonObject2, map);
         FovEngine fovEngine = resolveFovEngine(jsonObject2, countArray, text3);
         if (fovEngine != null) {
            list.add(fovEngine);
         }
      }

      FovEngine[] fovEngineArray = ((FovEngine[])list.toArray(new FovEngine[list.size()]));
      ResourceLocation location = null;
      if (text4 != null) {
         location = resolveResourceLocation(text3, text4, ".png");
      }

      return new GeometryConverter(text2, text3, location, fovEngineArray, factor);
   }

   public static FovEngine resolveFovEngine(JsonObject jsonObject, int[] countArray, String text) {
      String text2 = WrongArrayHelper.resolveText(jsonObject, "part");
      applyTarget(text2, "Model part not specified, missing \"replace\" or \"attachTo\".");
      boolean flag = WrongArrayHelper.matchesJsonObject(jsonObject, "attach", false);
      PassiveFloatEntryImpl passiveFloatEntryImpl = new PassiveFloatEntryImpl();
      if (countArray != null) {
         passiveFloatEntryImpl.count2 = countArray[0];
         passiveFloatEntryImpl.count = countArray[1];
      }

      RotationChecker rotationChecker = null;
      JsonArray jsonArray = (JsonArray)jsonObject.get("animations");
      if (jsonArray != null) {
         ArrayList list = new ArrayList();

         for (int index = 0; index < jsonArray.size(); index++) {
            for (Entry entry : ((JsonObject)jsonArray.get(index)).entrySet()) {
               String key = (String)entry.getKey();
               String asString = ((JsonElement)entry.getValue()).getAsString();
               ModelVariableController modelVariableController = new ModelVariableController(key, asString);
               list.add(modelVariableController);
            }
         }

         if (list.size() > 0) {
            ModelVariableController[] modelVariableControllerArray = ((ModelVariableController[])list.toArray(new ModelVariableController[list.size()]));
            rotationChecker = new RotationChecker(modelVariableControllerArray);
         }
      }

      BoxesManager boxesManager = TypeModelBoxHelper.resolveBoxesManager(jsonObject, passiveFloatEntryImpl, countArray, text);
      return new FovEngine(text2, flag, boxesManager, rotationChecker);
   }
}
