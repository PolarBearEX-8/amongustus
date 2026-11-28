package net.optifine.entity.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.entity.model.anim.ModelResolver;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomEntityModels {
   private static boolean active = false;
   private static Map originalEntityRenderMap = null;
   private static Map originalTileEntityRenderMap = null;

   private static boolean modifyModel2(ModelAdapter modelAdapter, ModelBase modelBase, CustomModelRenderer[] customModelRenderers, ModelResolver modelResolver) {
      for (int i = 0; i < customModelRenderers.length; i++) {
         if (!modifyModel(modelAdapter, modelBase, customModelRenderers[i], modelResolver)) {
            return false;
         }
      }

      return true;
   }

   private static IEntityRenderer parseEntityRender(JsonObject json, String str) {
      CustomEntityRenderer customEntityRenderer = CustomEntityModelParser.parseEntityRender(json, str);
      String name = customEntityRenderer.getName();
      ModelAdapter modelAdapter = CustomModelRegistry.getModelAdapter(name);
      checkNull(modelAdapter, "Entity not found: " + name);
      Class entityClass = modelAdapter.getEntityClass();
      checkNull(entityClass, "Entity class not found: " + name);
      IEntityRenderer entityRenderer = parseEntityRender2(modelAdapter, customEntityRenderer);
      if (entityRenderer == null) {
         return null;
      } else {
         entityRenderer.setEntityClass(entityClass);
         return entityRenderer;
      }
   }

   private static ResourceLocation[] getModelLocations() {
      String str = "optifine/cem/";
      String str2 = ".jem";
      ArrayList list = new ArrayList();
      String[] modelNames = CustomModelRegistry.getModelNames();

      for (int i = 0; i < modelNames.length; i++) {
         String str4 = str + modelNames[i] + str2;
         ResourceLocation location = new ResourceLocation(str4);
         if (Config.hasResource(location)) {
            list.add(location);
         }
      }

      return (ResourceLocation[])list.toArray(new ResourceLocation[list.size()]);
   }

   private static IEntityRenderer parseEntityRender2(ModelAdapter modelAdapter, CustomEntityRenderer customEntityRenderer) {
      ResourceLocation textureLocation = customEntityRenderer.getTextureLocation();
      CustomModelRenderer[] customModelRenderers = customEntityRenderer.getCustomModelRenderers();
      float shadowSize = customEntityRenderer.getShadowSize();
      if (shadowSize < 0.0F) {
         shadowSize = modelAdapter.getShadowSize();
      }

      ModelBase modelBase = modelAdapter.makeModel();
      if (modelBase == null) {
         return null;
      } else {
         if (!modifyModel2(modelAdapter, modelBase, customModelRenderers, new ModelResolver(modelAdapter, modelBase, customModelRenderers))) {
            return null;
         } else {
            IEntityRenderer entityRenderer = modelAdapter.makeEntityRender(modelBase, shadowSize);
            if (entityRenderer == null) {
               throw new JsonParseException("Entity renderer is null, model: " + modelAdapter.getName() + ", adapter: " + modelAdapter.getClass().getName());
            } else {
               if (textureLocation != null) {
                  entityRenderer.setLocationTextureCustom(textureLocation);
               }

               return entityRenderer;
            }
         }
      }
   }

   private static Map getEntityRenderMap() {
      Map entityRenderMap = Minecraft.getMinecraft().getRenderManager().getEntityRenderMap();
      if (entityRenderMap == null) {
         return null;
      } else {
         if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap(entityRenderMap);
         }

         return entityRenderMap;
      }
   }

   public static void update() {
      Map entityRenderMap = getEntityRenderMap();
      Map tileEntityRenderMap = getTileEntityRenderMap();
      if (entityRenderMap == null) {
         Config.warn("Entity render map not found, custom entity models are DISABLED.");
      } else if (tileEntityRenderMap == null) {
         Config.warn("Tile entity render map not found, custom entity models are DISABLED.");
      } else {
         active = false;
         entityRenderMap.clear();
         tileEntityRenderMap.clear();
         entityRenderMap.putAll(originalEntityRenderMap);
         tileEntityRenderMap.putAll(originalTileEntityRenderMap);
         if (Config.isCustomEntityModels()) {
            ResourceLocation[] modelLocations = getModelLocations();

            for (int i = 0; i < modelLocations.length; i++) {
               ResourceLocation location = modelLocations[i];
               Config.dbg("CustomEntityModel: " + location.getResourcePath());
               IEntityRenderer entityRenderer = parseEntityRender(location);
               if (entityRenderer != null) {
                  Class entityClass = entityRenderer.getEntityClass();
                  if (entityClass != null) {
                     if (entityRenderer instanceof Render) {
                        entityRenderMap.put(entityClass, (Render)entityRenderer);
                     } else if (entityRenderer instanceof TileEntitySpecialRenderer) {
                        tileEntityRenderMap.put(entityClass, (TileEntitySpecialRenderer)entityRenderer);
                     } else {
                        Config.warn("Unknown renderer type: " + entityRenderer.getClass().getName());
                     }

                     active = true;
                  }
               }
            }
         }
      }
   }

   private static boolean modifyModel(ModelAdapter modelAdapter, ModelBase modelBase, CustomModelRenderer customModelRenderer, ModelResolver modelResolver) {
      String modelPart = customModelRenderer.getModelPart();
      ModelRenderer modelRenderer = modelAdapter.getModelRenderer(modelBase, modelPart);
      if (modelRenderer == null) {
         Config.warn("Model part not found: " + modelPart + ", model: " + modelBase);
         return false;
      } else {
         if (!customModelRenderer.isAttach()) {
            if (modelRenderer.cubeList != null) {
               modelRenderer.cubeList.clear();
            }

            if (modelRenderer.spriteList != null) {
               modelRenderer.spriteList.clear();
            }

            if (modelRenderer.childModels != null) {
               ModelRenderer[] modelRenderers = modelAdapter.getModelRenderers(modelBase);
               Set set = Collections.newSetFromMap(new IdentityHashMap());
               set.addAll(Arrays.asList(modelRenderers));
               Iterator it = modelRenderer.childModels.iterator();

               while (it.hasNext()) {
                  if (!set.contains((ModelRenderer)it.next())) {
                     it.remove();
                  }
               }
            }
         }

         modelRenderer.addChild(customModelRenderer.getModelRenderer());
         ModelUpdater modelUpdater = customModelRenderer.getModelUpdater();
         if (modelUpdater != null) {
            modelResolver.setThisModelRenderer(customModelRenderer.getModelRenderer());
            modelResolver.setPartModelRenderer(modelRenderer);
            if (!modelUpdater.initialize(modelResolver)) {
               return false;
            }

            customModelRenderer.getModelRenderer().setModelUpdater(modelUpdater);
         }

         return true;
      }
   }

   private static Map getTileEntityRenderMap() {
      Map mapSpecialRenderers = TileEntityRendererDispatcher.instance.mapSpecialRenderers;
      if (originalTileEntityRenderMap == null) {
         originalTileEntityRenderMap = new HashMap(mapSpecialRenderers);
      }

      return mapSpecialRenderers;
   }

   private static void checkNull(Object obj, String str) {
      if (obj == null) {
         throw new JsonParseException(str);
      }
   }

   private static IEntityRenderer parseEntityRender(ResourceLocation location) {
      try {
         return parseEntityRender(CustomEntityModelParser.loadJson(location), location.getResourcePath());
      } catch (IOException e) {
         Config.error("" + e.getClass().getName() + ": " + e.getMessage());
         return null;
      } catch (JsonParseException e2) {
         Config.error("" + e2.getClass().getName() + ": " + e2.getMessage());
         return null;
      } catch (Exception e3) {
         e3.printStackTrace();
         return null;
      }
   }

   public static boolean isActive() {
      return active;
   }
}
