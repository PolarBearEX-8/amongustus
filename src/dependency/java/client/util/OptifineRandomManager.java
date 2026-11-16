package client.util;

import client.event.PostRenderObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEntry;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class OptifineRandomManager {
   public static Map map = new HashMap();
   public static boolean enabled = false;
   public static RenderGlobal renderGlobal;
   public static EntityEntry entityEntry = new EntityEntry();
   public static StandardMapEntry standardMapEntry;
   public static DampenerWriter dampenerWriter = new DampenerWriter();
   public static boolean enabled2 = false;
   public static final String labelText2 = ".png";
   public static final String labelText6 = ".properties";
   public static final String labelText5 = "textures/entity/";
   public static final String labelText8 = "textures/painting/";
   public static final String labelText4 = "textures/";
   public static final String labelText = "optifine/random/";
   public static final String labelText3 = "mcpatcher/mob/";
   public static final String[] stringArray2 = new String[]{
      "_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"
   };
   public static final String labelText7 = "horse/";
   public static final String[] stringArray = EntityHorse.stringArray5;
   public static final String[] stringArray3 = EntityHorse.stringArray6;

   private static void updateState2() {
      renderGlobal = BuildManager.getRenderGlobalAsRenderGlobal();
      standardMapEntry = StandardMapEntry.standardMapEntry;
      String[] textArray = new String[]{"optifine/random/", "mcpatcher/mob/"};
      String[] textArray2 = new String[]{".png", ".properties"};
      String[] textArray3 = AssetsMinecraftManager.resolveTextArray(textArray, textArray2);
      HashSet set = new HashSet();

      for (int index = 0; index < textArray3.length; index++) {
         String text = SplineCollector.resolveText7(SplineCollector.resolveTextByText(textArray3[index], textArray2), "0123456789");
         String text2 = resolveTextByText(text + ".png");
         if (!set.contains(text2)) {
            set.add(text2);
            ResourceLocation location = new ResourceLocation(text2);
            if (BuildManager.matchesResourceLocation3(location) && (CurveEngine)map.get(text2) == null) {
               CurveEngine curveEngine = resolveCurveEngine(location, false);
               if (curveEngine == null) {
                  curveEngine = resolveCurveEngine(location, true);
               }

               if (curveEngine != null) {
                  map.put(text2, curveEngine);
               }
            }
         }
      }

      enabled = !map.isEmpty();
   }

   public static void applyEntity3(Entity entity, World world) {
      if (world != null) {
         DataWatcher dataWatcher = entity.getDataWatcher();
         dataWatcher.blockPos = entity.getBlockPos();
         dataWatcher.biomeIDIsOutOfBoundsManager = world.resolveBiomeIDIsOutOfBoundsManager(dataWatcher.blockPos);
         UUID entityUniqueID = entity.getEntityUniqueID();
         if (entity instanceof EntityVillager) {
            applyUUID(entityUniqueID, (EntityVillager)entity);
         }
      }
   }

   private static String resolveText(String text) {
      for (int index = 0; index < stringArray2.length; index++) {
         String text2 = stringArray2[index];
         if (text.endsWith(text2)) {
            return SplineCollector.resolveText3(text, text2);
         }
      }

      return null;
   }

   public static void applyText(String text) {
      BuildManager.applyText4("RandomEntities: " + text);
   }

   public static ResourceLocation resolveResourceLocationByResourceLocation(ResourceLocation location, boolean flag) {
      String text = location.getText();
      String text2 = location.getText1();
      String text3 = "textures/";
      String text4 = "optifine/random/";
      if (flag) {
         text3 = "textures/entity/";
         text4 = "mcpatcher/mob/";
      }

      if (!text2.startsWith(text3)) {
         return null;
      } else {
         String text5 = SplineCollector.resolveText(text2, text3, text4);
         return new ResourceLocation(text, text5);
      }
   }

   public static void applyWorldByWorld(World world, World world2) {
      if (world2 != null) {
         List loadedEntityList = world2.getLoadedEntityList();

         for (int index = 0; index < loadedEntityList.size(); index++) {
            applyEntity3((Entity)loadedEntityList.get(index), world2);
         }
      }

      entityEntry.setEntity((Entity)null);
      dampenerWriter.setTileEntity((TileEntity)null);
   }

   public static void applyEntityByEntity(Entity entity, World world) {
   }

   private static void applyUUID(UUID uuid, EntityVillager entityVillager) {
      Entity entity = FovProvider.resolveEntityByUUID(uuid);
      if (entity instanceof EntityVillager) {
         EntityVillager entityVillager2 = (EntityVillager)entity;
         int count = entityVillager2.getCount10();
         entityVillager.applyIndex10(count);
         entityVillager.count36 = entityVillager2.count36;
         entityVillager.count34 = entityVillager2.count34;
      }
   }

   private static ResourceLocation resolveResourceLocation3(ResourceLocation location, boolean flag) {
      ResourceLocation location2 = resolveResourceLocationByResourceLocation(location, flag);
      if (location2 == null) {
         return null;
      } else {
         String text = location2.getText();
         String text2 = SplineCollector.resolveText3(location2.getText1(), ".png");
         String text3 = text2 + ".properties";
         ResourceLocation location3 = new ResourceLocation(text, text3);
         if (BuildManager.matchesResourceLocation3(location3)) {
            return location3;
         } else {
            String text4 = resolveText(text2);
            if (text4 == null) {
               return null;
            } else {
               ResourceLocation location4 = new ResourceLocation(text, text4 + ".properties");
               return BuildManager.matchesResourceLocation3(location4) ? location4 : null;
            }
         }
      }
   }

   private static CurveEngine resolveCurveEngine(ResourceLocation location, ResourceLocation location2) {
      try {
         String text = location.getText1();
         applyText(location2.getText1() + ", properties: " + text);
         InputStream inputStream = BuildManager.resolveInputStream(location);
         if (inputStream == null) {
            applyTextByText("Properties not found: " + text);
            return null;
         } else {
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            CurveEngine curveEngine = new CurveEngine(propertiesAdapter, text, location2);
            return !curveEngine.matchesText(text) ? null : curveEngine;
         }
      } catch (FileNotFoundException fileNotFoundException) {
         applyTextByText("File not found: " + location2.getText1());
         return null;
      } catch (IOException ioException) {
         ioException.printStackTrace();
         return null;
      }
   }

   public static void applyTextByText(String text) {
      BuildManager.applyText3("RandomEntities: " + text);
   }

   private static String resolveText(String text, int count) {
      if (stringArray != null && stringArray3 != null) {
         for (int index = 0; index < stringArray3.length; index++) {
            String text2 = stringArray3[index];
            if (text.startsWith(text2, count)) {
               return stringArray[index];
            }
         }

         return text;
      } else {
         return text;
      }
   }

   private static CurveEngine resolveCurveEngine(ResourceLocation location, boolean flag) {
      String text = location.getText1();
      ResourceLocation location2 = resolveResourceLocation3(location, flag);
      if (location2 != null) {
         CurveEngine curveEngine = resolveCurveEngine(location2, location);
         if (curveEngine != null) {
            return curveEngine;
         }
      }

      ResourceLocation[] locationArray = resolveResourceLocationArray(location, flag);
      return locationArray == null ? null : new CurveEngine(text, locationArray);
   }

   private static ResourceLocation[] resolveResourceLocationArray(ResourceLocation location, boolean flag) {
      ArrayList list = new ArrayList();
      list.add(location);
      ResourceLocation location2 = resolveResourceLocationByResourceLocation(location, flag);
      if (location2 == null) {
         return null;
      } else {
         for (int index = 1; index < list.size() + 10; index++) {
            int count = index + 1;
            ResourceLocation location3 = resolveResourceLocation(location2, count);
            if (BuildManager.matchesResourceLocation3(location3)) {
               list.add(location3);
            }
         }

         if (list.size() <= 1) {
            return null;
         } else {
            ResourceLocation[] locationArray = (ResourceLocation[])list.toArray(new ResourceLocation[list.size()]);
            applyText(location.getText1() + ", variants: " + locationArray.length);
            return locationArray;
         }
      }
   }

   public static void updateState() {
      map.clear();
      enabled = false;
      if (BuildManager.isActive30()) {
         updateState2();
      }
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location, int count) {
      if (location == null) {
         return null;
      } else {
         String text = location.getText1();
         int count2 = text.lastIndexOf(46);
         if (count2 < 0) {
            return null;
         } else {
            String text2 = text.substring(0, count2);
            String text3 = text.substring(count2);
            String text4 = text2 + count + text3;
            return new ResourceLocation(location.getText(), text4);
         }
      }
   }

   private static String resolveTextByText(String text) {
      return text.startsWith("optifine/random/")
         ? SplineCollector.resolveText(text, "optifine/random/", "textures/")
         : (text.startsWith("mcpatcher/mob/") ? SplineCollector.resolveText(text, "mcpatcher/mob/", "textures/entity/") : null);
   }

   private static PostRenderObserver getPostRenderObserver() {
      if (renderGlobal.entity != null) {
         entityEntry.setEntity(renderGlobal.entity);
         return entityEntry;
      } else {
         if (standardMapEntry.tileEntity != null) {
            TileEntity tileEntity = standardMapEntry.tileEntity;
            if (tileEntity.getWorld() != null) {
               dampenerWriter.setTileEntity(tileEntity);
               return dampenerWriter;
            }
         }

         return null;
      }
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location) {
      if (!enabled) {
         return location;
      } else if (enabled2) {
         return location;
      } else {
         ResourceLocation location2;
         try {
            enabled2 = true;
            PostRenderObserver postRenderObserver = getPostRenderObserver();
            if (postRenderObserver == null) {
               return location;
            }

            String text = location.getText1();
            if (text.startsWith("horse/")) {
               text = resolveText(text, "horse/".length());
            }

            if (text.startsWith("textures/entity/") || text.startsWith("textures/painting/")) {
               CurveEngine curveEngine = (CurveEngine)map.get(text);
               if (curveEngine != null) {
                  return curveEngine.resolveResourceLocation(location, postRenderObserver);
               }

               return location;
            }

            location2 = location;
         } finally {
            enabled2 = false;
         }

         return location2;
      }
   }
}
