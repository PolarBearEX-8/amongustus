package client.util;

import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AdapterManager {
   public static boolean enabled = false;
   public static Map map = null;
   public static Map map2 = null;

   private static PreRenderDispatcher resolvePreRenderDispatcher(ClassEntry classEntry, GeometryConverter geometryConverter) {
      ResourceLocation resourceLocation = geometryConverter.getResourceLocation();
      FovEngine[] fovEngineArray = geometryConverter.getFovEngineArray();
      float factor = geometryConverter.getFactor();
      if (factor < 0.0F) {
         factor = classEntry.getFactor();
      }

      PassiveFloatEntry passiveFloatEntry = classEntry.getPassiveFloatEntry();
      if (passiveFloatEntry == null) {
         return null;
      } else {
         FovService fovService = new FovService(classEntry, passiveFloatEntry, fovEngineArray);
         if (!matchesClassEntry(classEntry, passiveFloatEntry, fovEngineArray, fovService)) {
            return null;
         } else {
            PreRenderDispatcher preRenderDispatcher = classEntry.resolvePreRenderDispatcher(passiveFloatEntry, factor);
            if (preRenderDispatcher == null) {
               throw new JsonParseException("Entity renderer is null, model: " + classEntry.getLabelText() + ", adapter: " + classEntry.getClass().getName());
            } else {
               if (resourceLocation != null) {
                  preRenderDispatcher.setResourceLocation(resourceLocation);
               }

               return preRenderDispatcher;
            }
         }
      }
   }

   private static boolean matchesClassEntry(ClassEntry classEntry, PassiveFloatEntry passiveFloatEntry, FovEngine[] fovEngineArray, FovService fovService) {
      for (int index = 0; index < fovEngineArray.length; index++) {
         FovEngine fovEngine = fovEngineArray[index];
         if (!matchesClassEntry(classEntry, passiveFloatEntry, fovEngine, fovService)) {
            return false;
         }
      }

      return true;
   }

   private static Map getMap() {
      Map entryMap = Minecraft.getMinecraft().getRenderManager().getEntryMap();
      if (entryMap == null) {
         return null;
      } else {
         if (map == null) {
            map = new HashMap(entryMap);
         }

         return entryMap;
      }
   }

   public static boolean isEnabled() {
      return enabled;
   }

   private static Map getMapAsMap() {
      Map map3 = StandardMapEntry.standardMapEntry.map;
      if (map2 == null) {
         map2 = new HashMap(map3);
      }

      return map3;
   }

   private static void applyTarget(Object entry, String text) {
      if (entry == null) {
         throw new JsonParseException(text);
      }
   }

   private static PreRenderDispatcher resolvePreRenderDispatcherByResourceLocation(ResourceLocation location) {
      try {
         return resolvePreRenderDispatcher(InterpolationCoordinator.resolveJsonObjectByResourceLocation(location), location.getText1());
      } catch (IOException ioException) {
         BuildManager.applyText2("" + ioException.getClass().getName() + ": " + ioException.getMessage());
         return null;
      } catch (JsonParseException jsonParseException) {
         BuildManager.applyText2("" + jsonParseException.getClass().getName() + ": " + jsonParseException.getMessage());
         return null;
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }

   private static boolean matchesClassEntry(ClassEntry classEntry, PassiveFloatEntry passiveFloatEntry, FovEngine fovEngine, FovService fovService) {
      String labelText = fovEngine.getLabelText();
      BoxesManager boxesManager = classEntry.resolveBoxesManager(passiveFloatEntry, labelText);
      if (boxesManager == null) {
         BuildManager.applyText3("Model part not found: " + labelText + ", model: " + passiveFloatEntry);
         return false;
      } else {
         if (!fovEngine.isEnabled()) {
            if (boxesManager.list != null) {
               boxesManager.list.clear();
            }

            if (boxesManager.list3 != null) {
               boxesManager.list3.clear();
            }

            if (boxesManager.list2 != null) {
               BoxesManager[] boxesManagerArray = classEntry.resolveBoxesManagerArray(passiveFloatEntry);
               Set set = Collections.newSetFromMap(new IdentityHashMap());
               set.addAll(Arrays.asList(boxesManagerArray));
               Iterator iterator = boxesManager.list2.iterator();

               while (iterator.hasNext()) {
                  BoxesManager boxesManager2 = (BoxesManager)iterator.next();
                  if (!set.contains(boxesManager2)) {
                     iterator.remove();
                  }
               }
            }
         }

         boxesManager.applyBoxesManager(fovEngine.getBoxesManager());
         RotationChecker rotationChecker = fovEngine.getRotationChecker();
         if (rotationChecker != null) {
            fovService.setBoxesManager(fovEngine.getBoxesManager());
            fovService.setBoxesManager2(boxesManager);
            if (!rotationChecker.matchesListener61Extension(fovService)) {
               return false;
            }

            fovEngine.getBoxesManager().setRotationChecker(rotationChecker);
         }

         return true;
      }
   }

   private static PreRenderDispatcher resolvePreRenderDispatcher(JsonObject jsonObject, String text) {
      GeometryConverter geometryConverter = InterpolationCoordinator.resolveGeometryConverter(jsonObject, text);
      String labelText = geometryConverter.getLabelText();
      ClassEntry classEntry = ExtendedNameManager.get(labelText);
      applyTarget(classEntry, "Entity not found: " + labelText);
      Class memberRef = classEntry.getMemberRef();
      applyTarget(memberRef, "Entity class not found: " + labelText);
      PreRenderDispatcher preRenderDispatcher = resolvePreRenderDispatcher(classEntry, geometryConverter);
      if (preRenderDispatcher == null) {
         return null;
      } else {
         preRenderDispatcher.setMemberRef(memberRef);
         return preRenderDispatcher;
      }
   }

   private static ResourceLocation[] getResourceLocationArray() {
      String text = "optifine/cem/";
      String text2 = ".jem";
      ArrayList list = new ArrayList();
      String[] textArray = ExtendedNameManager.getTextArray();

      for (int index = 0; index < textArray.length; index++) {
         String text3 = textArray[index];
         String text4 = text + text3 + text2;
         ResourceLocation location = new ResourceLocation(text4);
         if (BuildManager.matchesResourceLocation3(location)) {
            list.add(location);
         }
      }

      return ((ResourceLocation[])list.toArray(new ResourceLocation[list.size()]));
   }

   public static void updateState() {
      Map map3 = getMap();
      Map mapAsMap = getMapAsMap();
      if (map3 == null) {
         BuildManager.applyText3("Entity render map not found, custom entity models are DISABLED.");
      } else if (mapAsMap == null) {
         BuildManager.applyText3("Tile entity render map not found, custom entity models are DISABLED.");
      } else {
         enabled = false;
         map3.clear();
         mapAsMap.clear();
         map3.putAll(map);
         mapAsMap.putAll(map2);
         if (BuildManager.isActive72()) {
            ResourceLocation[] resourceLocationArray = getResourceLocationArray();

            for (int index = 0; index < resourceLocationArray.length; index++) {
               ResourceLocation location = resourceLocationArray[index];
               BuildManager.applyText4("CustomEntityModel: " + location.getText1());
               PreRenderDispatcher preRenderDispatcher = resolvePreRenderDispatcherByResourceLocation(location);
               if (preRenderDispatcher != null) {
                  Class memberRef = preRenderDispatcher.getMemberRef();
                  if (memberRef != null) {
                     if (preRenderDispatcher instanceof EntitySkinLocation) {
                        map3.put(memberRef, (EntitySkinLocation)preRenderDispatcher);
                     } else if (preRenderDispatcher instanceof ResourceLocationArrayHolder) {
                        mapAsMap.put(memberRef, (ResourceLocationArrayHolder)preRenderDispatcher);
                     } else {
                        BuildManager.applyText3("Unknown renderer type: " + preRenderDispatcher.getClass().getName());
                     }

                     enabled = true;
                  }
               }
            }
         }
      }
   }
}
