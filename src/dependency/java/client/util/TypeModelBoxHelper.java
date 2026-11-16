package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraft.util.ResourceLocation;

public class TypeModelBoxHelper {
   public static JsonParser jsonParser = new JsonParser();
   public static final String labelText = "type";
   public static final String labelText2 = "textureSize";
   public static final String labelText3 = "usePlayerTexture";
   public static final String labelText4 = "models";
   public static final String labelText5 = "id";
   public static final String labelText6 = "baseId";
   public static final String labelText7 = "type";
   public static final String labelText8 = "texture";
   public static final String labelText9 = "textureSize";
   public static final String labelText10 = "attachTo";
   public static final String labelText11 = "invertAxis";
   public static final String labelText12 = "mirrorTexture";
   public static final String labelText13 = "translate";
   public static final String labelText14 = "rotate";
   public static final String labelText15 = "scale";
   public static final String labelText16 = "boxes";
   public static final String labelText17 = "sprites";
   public static final String labelText18 = "submodel";
   public static final String labelText19 = "submodels";
   public static final String labelText20 = "textureOffset";
   public static final String labelText21 = "coordinates";
   public static final String labelText22 = "sizeAdd";
   public static final String labelText23 = "uvDown";
   public static final String labelText24 = "uvUp";
   public static final String labelText25 = "uvNorth";
   public static final String labelText26 = "uvSouth";
   public static final String labelText27 = "uvWest";
   public static final String labelText28 = "uvEast";
   public static final String labelText29 = "uvFront";
   public static final String labelText30 = "uvBack";
   public static final String labelText31 = "uvLeft";
   public static final String labelText32 = "uvRight";
   public static final String labelText33 = "PlayerItem";
   public static final String labelText34 = "ModelBox";

   private static void applyTarget(Object entry, String text) {
      if (entry == null) {
         throw new JsonParseException(text);
      }
   }

   public static FovAccumulator resolveFovAccumulator(JsonObject jsonObject, Dimension dimension) {
      String text = WrongArrayHelper.resolveText(jsonObject, "type");
      if (!BuildManager.matchesTarget(text, "ModelBox")) {
         BuildManager.applyText3("Unknown model type: " + text);
         return null;
      } else {
         int count = computeCount(WrongArrayHelper.resolveText(jsonObject, "attachTo"));
         PassiveFloatEntryAdapter passiveFloatEntryAdapter = new PassiveFloatEntryAdapter();
         passiveFloatEntryAdapter.count2 = dimension.width;
         passiveFloatEntryAdapter.count = dimension.height;
         BoxesManager boxesManager = resolveBoxesManager(jsonObject, passiveFloatEntryAdapter, (int[])null, (String)null);
         return new FovAccumulator(count, boxesManager);
      }
   }

   public static DimensionEntry resolveDimensionEntry(JsonObject jsonObject) {
      String text = WrongArrayHelper.resolveText(jsonObject, "type");
      if (!BuildManager.matchesTarget(text, "PlayerItem")) {
         throw new JsonParseException("Unknown model type: " + text);
      } else {
         int[] countArray = WrongArrayHelper.resolveIndexArray(jsonObject.get("textureSize"), 2);
         applyTarget(countArray, "Missing texture size");
         Dimension dimension = new Dimension(countArray[0], countArray[1]);
         boolean flag = WrongArrayHelper.matchesJsonObject(jsonObject, "usePlayerTexture", false);
         JsonArray jsonArray = (JsonArray)jsonObject.get("models");
         applyTarget(jsonArray, "Missing elements");
         HashMap map = new HashMap();
         ArrayList list = new ArrayList();
         new ArrayList();

         for (int index = 0; index < jsonArray.size(); index++) {
            JsonObject jsonObject2 = (JsonObject)jsonArray.get(index);
            String text2 = WrongArrayHelper.resolveText(jsonObject2, "baseId");
            if (text2 != null) {
               JsonObject jsonObject3 = (JsonObject)map.get(text2);
               if (jsonObject3 == null) {
                  BuildManager.applyText3("BaseID not found: " + text2);
                  continue;
               }

               for (Entry entry : jsonObject3.entrySet()) {
                  if (!jsonObject2.has((String)entry.getKey())) {
                     jsonObject2.add((String)entry.getKey(), (JsonElement)entry.getValue());
                  }
               }
            }

            String text3 = WrongArrayHelper.resolveText(jsonObject2, "id");
            if (text3 != null) {
               if (!map.containsKey(text3)) {
                  map.put(text3, jsonObject2);
               } else {
                  BuildManager.applyText3("Duplicate model ID: " + text3);
               }
            }

            FovAccumulator fovAccumulator = resolveFovAccumulator(jsonObject2, dimension);
            if (fovAccumulator != null) {
               list.add(fovAccumulator);
            }
         }

         FovAccumulator[] fovAccumulatorArray = ((FovAccumulator[])list.toArray(new FovAccumulator[list.size()]));
         return new DimensionEntry(dimension, flag, fovAccumulatorArray);
      }
   }

   private static ResourceLocation resolveResourceLocation(String text) {
      int count = text.indexOf(58);
      if (count < 0) {
         return new ResourceLocation(text);
      } else {
         String text2 = text.substring(0, count);
         String text3 = text.substring(count + 1);
         return new ResourceLocation(text2, text3);
      }
   }

   private static int[][] resolveIndexArray(JsonObject jsonObject) {
      int[][] countMatrix = new int[][]{
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvDown"), 4),
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvUp"), 4),
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvNorth"), 4),
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvSouth"), 4),
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvWest"), 4),
         WrongArrayHelper.resolveIndexArray(jsonObject.get("uvEast"), 4)
      };
      if (countMatrix[2] == null) {
         countMatrix[2] = WrongArrayHelper.resolveIndexArray(jsonObject.get("uvFront"), 4);
      }

      if (countMatrix[3] == null) {
         countMatrix[3] = WrongArrayHelper.resolveIndexArray(jsonObject.get("uvBack"), 4);
      }

      if (countMatrix[4] == null) {
         countMatrix[4] = WrongArrayHelper.resolveIndexArray(jsonObject.get("uvLeft"), 4);
      }

      if (countMatrix[5] == null) {
         countMatrix[5] = WrongArrayHelper.resolveIndexArray(jsonObject.get("uvRight"), 4);
      }

      boolean flag = false;

      for (int index = 0; index < countMatrix.length; index++) {
         if (countMatrix[index] != null) {
            flag = true;
         }
      }

      return !flag ? (int[][])null : countMatrix;
   }

   private static int computeCount(String text) {
      if (text == null) {
         return 0;
      } else if (text.equals("body")) {
         return 0;
      } else if (text.equals("head")) {
         return 1;
      } else if (text.equals("leftArm")) {
         return 2;
      } else if (text.equals("rightArm")) {
         return 3;
      } else if (text.equals("leftLeg")) {
         return 4;
      } else if (text.equals("rightLeg")) {
         return 5;
      } else if (text.equals("cape")) {
         return 6;
      } else {
         BuildManager.applyText3("Unknown attachModel: " + text);
         return 0;
      }
   }

   public static BoxesManager resolveBoxesManager(JsonObject jsonObject, PassiveFloatEntry passiveFloatEntry, int[] countArray, String text) {
      BoxesManager boxesManager = new BoxesManager(passiveFloatEntry);
      String text2 = WrongArrayHelper.resolveText(jsonObject, "id");
      boxesManager.setLabelText2(text2);
      float factor = WrongArrayHelper.computeFactor(jsonObject, "scale", 1.0F);
      boxesManager.factor7 = factor;
      boxesManager.factor13 = factor;
      boxesManager.factor14 = factor;
      String text3 = WrongArrayHelper.resolveText(jsonObject, "texture");
      if (text3 != null) {
         boxesManager.setResourceLocation(InterpolationCoordinator.resolveResourceLocation(text, text3, ".png"));
      }

      int[] countArray2 = WrongArrayHelper.resolveIndexArray(jsonObject.get("textureSize"), 2);
      if (countArray2 == null) {
         countArray2 = countArray;
      }

      if (countArray2 != null) {
         boxesManager.resolveBoxesManager(countArray2[0], countArray2[1]);
      }

      String text4 = WrongArrayHelper.resolveText(jsonObject, "invertAxis", "").toLowerCase();
      boolean flag = text4.contains("x");
      boolean flag2 = text4.contains("y");
      boolean flag3 = text4.contains("z");
      float[] factorArray = WrongArrayHelper.resolveFloatArray(jsonObject.get("translate"), 3, new float[3]);
      if (flag) {
         factorArray[0] = -factorArray[0];
      }

      if (flag2) {
         factorArray[1] = -factorArray[1];
      }

      if (flag3) {
         factorArray[2] = -factorArray[2];
      }

      float[] factorArray2 = WrongArrayHelper.resolveFloatArray(jsonObject.get("rotate"), 3, new float[3]);

      for (int index = 0; index < factorArray2.length; index++) {
         factorArray2[index] = factorArray2[index] / 180.0F * (float) Math.PI;
      }

      if (flag) {
         factorArray2[0] = -factorArray2[0];
      }

      if (flag2) {
         factorArray2[1] = -factorArray2[1];
      }

      if (flag3) {
         factorArray2[2] = -factorArray2[2];
      }

      boxesManager.applyFloat(factorArray[0], factorArray[1], factorArray[2]);
      boxesManager.factor = factorArray2[0];
      boxesManager.factor9 = factorArray2[1];
      boxesManager.factor8 = factorArray2[2];
      String text5 = WrongArrayHelper.resolveText(jsonObject, "mirrorTexture", "").toLowerCase();
      boolean flag4 = text5.contains("u");
      boolean flag5 = text5.contains("v");
      if (flag4) {
         boxesManager.enabled = true;
      }

      if (flag5) {
         boxesManager.enabled6 = true;
      }

      JsonArray asJsonArray = jsonObject.getAsJsonArray("boxes");
      if (asJsonArray != null) {
         for (int index2 = 0; index2 < asJsonArray.size(); index2++) {
            JsonObject asJsonObject = asJsonArray.get(index2).getAsJsonObject();
            int[] countArray3 = WrongArrayHelper.resolveIndexArray(asJsonObject.get("textureOffset"), 2);
            int[][] countMatrix = resolveIndexArray(asJsonObject);
            if (countArray3 == null && countMatrix == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] factorArray3 = WrongArrayHelper.resolveFloatArray(asJsonObject.get("coordinates"), 6);
            if (factorArray3 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (flag) {
               factorArray3[0] = -factorArray3[0] - factorArray3[3];
            }

            if (flag2) {
               factorArray3[1] = -factorArray3[1] - factorArray3[4];
            }

            if (flag3) {
               factorArray3[2] = -factorArray3[2] - factorArray3[5];
            }

            float factor2 = WrongArrayHelper.computeFactor(asJsonObject, "sizeAdd", 0.0F);
            if (countMatrix != null) {
               boxesManager.applyIndexArray(countMatrix, factorArray3[0], factorArray3[1], factorArray3[2], factorArray3[3], factorArray3[4], factorArray3[5], factor2);
            } else {
               boxesManager.resolveBoxesManagerByIndex(countArray3[0], countArray3[1]);
               boxesManager.applyFloatByFloat(factorArray3[0], factorArray3[1], factorArray3[2], (int)factorArray3[3], (int)factorArray3[4], (int)factorArray3[5], factor2);
            }
         }
      }

      JsonArray asJsonArray2 = jsonObject.getAsJsonArray("sprites");
      if (asJsonArray2 != null) {
         for (int index3 = 0; index3 < asJsonArray2.size(); index3++) {
            JsonObject asJsonObject2 = asJsonArray2.get(index3).getAsJsonObject();
            int[] countArray4 = WrongArrayHelper.resolveIndexArray(asJsonObject2.get("textureOffset"), 2);
            if (countArray4 == null) {
               throw new JsonParseException("Texture offset not specified");
            }

            float[] factorArray4 = WrongArrayHelper.resolveFloatArray(asJsonObject2.get("coordinates"), 6);
            if (factorArray4 == null) {
               throw new JsonParseException("Coordinates not specified");
            }

            if (flag) {
               factorArray4[0] = -factorArray4[0] - factorArray4[3];
            }

            if (flag2) {
               factorArray4[1] = -factorArray4[1] - factorArray4[4];
            }

            if (flag3) {
               factorArray4[2] = -factorArray4[2] - factorArray4[5];
            }

            float factor3 = WrongArrayHelper.computeFactor(asJsonObject2, "sizeAdd", 0.0F);
            boxesManager.resolveBoxesManagerByIndex(countArray4[0], countArray4[1]);
            boxesManager.applyFloat(factorArray4[0], factorArray4[1], factorArray4[2], (int)factorArray4[3], (int)factorArray4[4], (int)factorArray4[5], factor3);
         }
      }

      JsonObject jsonObject2 = (JsonObject)jsonObject.get("submodel");
      if (jsonObject2 != null) {
         BoxesManager boxesManager2 = resolveBoxesManager(jsonObject2, passiveFloatEntry, countArray2, text);
         boxesManager.applyBoxesManager(boxesManager2);
      }

      JsonArray jsonArray = (JsonArray)jsonObject.get("submodels");
      if (jsonArray != null) {
         for (int index4 = 0; index4 < jsonArray.size(); index4++) {
            BoxesManager boxesManager3 = resolveBoxesManager((JsonObject)jsonArray.get(index4), passiveFloatEntry, countArray2, text);
            if (boxesManager3.getLabelText2() != null && boxesManager.resolveBoxesManager(boxesManager3.getLabelText2()) != null) {
               BuildManager.applyText3("Duplicate model ID: " + boxesManager3.getLabelText2());
            }

            boxesManager.applyBoxesManager(boxesManager3);
         }
      }

      return boxesManager;
   }
}
