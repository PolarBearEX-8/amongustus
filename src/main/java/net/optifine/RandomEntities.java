package net.optifine;

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
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.config.Config;
import net.optifine.util.IntegratedServerUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class RandomEntities {
   private static Map mapProperties = new HashMap();
   private static boolean active = false;
   private static RenderGlobal renderGlobal;
   private static RandomEntity randomEntity = new RandomEntity();
   private static TileEntityRendererDispatcher tileEntityRendererDispatcher;
   private static RandomTileEntity randomTileEntity = new RandomTileEntity();
   private static boolean working = false;
   public static final String SUFFIX_PNG = null;
   public static final String SUFFIX_PROPERTIES = null;
   public static final String PREFIX_TEXTURES_ENTITY = null;
   public static final String PREFIX_TEXTURES_PAINTING = null;
   public static final String PREFIX_TEXTURES = null;
   public static final String PREFIX_OPTIFINE_RANDOM = null;
   public static final String PREFIX_MCPATCHER_MOB = null;
   private static final String[] DEPENDANT_SUFFIXES = new String[]{
      "_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"
   };
   private static final String PREFIX_DYNAMIC_TEXTURE_HORSE = null;
   private static final String[] HORSE_TEXTURES = EntityHorse.horseArmorTextures;
   private static final String[] HORSE_TEXTURES_ABBR = EntityHorse.HORSE_ARMOR_TEXTURES_ABBR;

   private static void initialize() {
      renderGlobal = Config.getRenderGlobal();
      tileEntityRendererDispatcher = TileEntityRendererDispatcher.instance;
      String[] strings = new String[]{"optifine/random/", "mcpatcher/mob/"};
      String[] strings2 = new String[]{".png", ".properties"};
      String[] strings3 = ResUtils.collectFiles(strings, strings2);
      HashSet set = new HashSet();

      for (int i = 0; i < strings3.length; i++) {
         String str = strings3[i];
         str = StrUtils.removeSuffix(str, strings2);
         str = StrUtils.trimTrailing(str, "0123456789");
         str = str + ".png";
         String pathBase = getPathBase(str);
         if (!set.contains(pathBase)) {
            set.add(pathBase);
            ResourceLocation location = new ResourceLocation(pathBase);
            if (Config.hasResource(location)) {
               RandomEntityProperties randomEntityProperties = (RandomEntityProperties)mapProperties.get(pathBase);
               if (randomEntityProperties == null) {
                  randomEntityProperties = makeProperties(location, false);
                  if (randomEntityProperties == null) {
                     randomEntityProperties = makeProperties(location, true);
                  }

                  if (randomEntityProperties != null) {
                     mapProperties.put(pathBase, randomEntityProperties);
                  }
               }
            }
         }
      }

      active = !mapProperties.isEmpty();
   }

   private static String getHorseTexturePath(String str, int n) {
      if (HORSE_TEXTURES != null && HORSE_TEXTURES_ABBR != null) {
         for (int i = 0; i < HORSE_TEXTURES_ABBR.length; i++) {
            if (str.startsWith(HORSE_TEXTURES_ABBR[i], n)) {
               return HORSE_TEXTURES[i];
            }
         }

         return str;
      } else {
         return str;
      }
   }

   public static ResourceLocation getLocationRandom(ResourceLocation location, boolean flag) {
      String resourceDomain = location.getResourceDomain();
      String resourcePath = location.getResourcePath();
      String str = "textures/";
      String str2 = "optifine/random/";
      if (flag) {
         str = "textures/entity/";
         str2 = "mcpatcher/mob/";
      }

      if (!resourcePath.startsWith(str)) {
         return null;
      } else {
         String str3 = StrUtils.replacePrefix(resourcePath, str, str2);
         return new ResourceLocation(resourceDomain, str3);
      }
   }

   private static String getParentTexturePath(String str) {
      for (int i = 0; i < DEPENDANT_SUFFIXES.length; i++) {
         String str2 = DEPENDANT_SUFFIXES[i];
         if (str.endsWith(str2)) {
            return StrUtils.removeSuffix2(str, str2);
         }
      }

      return null;
   }

   public static ResourceLocation getTextureLocation(ResourceLocation location) {
      if (!active) {
         return location;
      } else if (working) {
         return location;
      } else {
         ResourceLocation location2;
         try {
            working = true;
            IRandomEntity randomEntityRendered = getRandomEntityRendered();
            if (randomEntityRendered == null) {
               return location;
            }

            String resourcePath = location.getResourcePath();
            if (resourcePath == null) {
               return location;
            }

            if (resourcePath.startsWith("horse/")) {
               resourcePath = getHorseTexturePath(resourcePath, "horse/".length());
            }

            if (resourcePath == null) {
               return location;
            }

            if (resourcePath.startsWith("textures/entity/") || resourcePath.startsWith("textures/painting/")) {
               RandomEntityProperties randomEntityProperties = (RandomEntityProperties)mapProperties.get(resourcePath);
               if (randomEntityProperties != null) {
                  return randomEntityProperties.getTextureLocation(location, randomEntityRendered);
               }

               return location;
            }

            location2 = location;
         } finally {
            working = false;
         }

         return location2;
      }
   }

   private static RandomEntityProperties makeProperties(ResourceLocation location, boolean flag) {
      String resourcePath = location.getResourcePath();
      ResourceLocation locationProperties = getLocationProperties(location, flag);
      if (locationProperties != null) {
         RandomEntityProperties randomEntityProperties = parseProperties(locationProperties, location);
         if (randomEntityProperties != null) {
            return randomEntityProperties;
         }
      }

      ResourceLocation[] locationsVariants = getLocationsVariants(location, flag);
      return locationsVariants == null ? null : new RandomEntityProperties(resourcePath, locationsVariants);
   }

   public static void warn(String str) {
      Config.warn("RandomEntities: " + str);
   }

   public static void worldChanged(World world, World world2) {
      if (world2 != null) {
         List loadedEntityList = world2.getLoadedEntityList();

         for (int i = 0; i < loadedEntityList.size(); i++) {
            entityLoaded((Entity)loadedEntityList.get(i), world2);
         }
      }

      randomEntity.setEntity((Entity)null);
      randomTileEntity.setTileEntity((TileEntity)null);
   }

   public static void entityLoaded(Entity entity, World world) {
      if (world != null) {
         DataWatcher dataWatcher = entity.getDataWatcher();
         dataWatcher.spawnPosition = entity.getPosition();
         dataWatcher.spawnBiome = world.getBiomeGenForCoords(dataWatcher.spawnPosition);
         UUID uniqueID = entity.getUniqueID();
         if (entity instanceof EntityVillager) {
            checkEntityVillager(uniqueID, (EntityVillager)entity);
         }
      }
   }

   private static String getPathBase(String str) {
      return str.startsWith("optifine/random/")
         ? StrUtils.replacePrefix(str, "optifine/random/", "textures/")
         : (str.startsWith("mcpatcher/mob/") ? StrUtils.replacePrefix(str, "mcpatcher/mob/", "textures/entity/") : null);
   }

   public static void entityUnloaded(Entity entity, World world) {
   }

   public static void update() {
      mapProperties.clear();
      active = false;
      if (Config.isRandomEntities()) {
         initialize();
      }
   }

   private static ResourceLocation getLocationProperties(ResourceLocation location, boolean flag) {
      ResourceLocation locationRandom = getLocationRandom(location, flag);
      if (locationRandom == null) {
         return null;
      } else {
         String resourceDomain = locationRandom.getResourceDomain();
         String str = StrUtils.removeSuffix2(locationRandom.getResourcePath(), ".png");
         String str2 = str + ".properties";
         ResourceLocation location2 = new ResourceLocation(resourceDomain, str2);
         if (Config.hasResource(location2)) {
            return location2;
         } else {
            String parentTexturePath = getParentTexturePath(str);
            if (parentTexturePath == null) {
               return null;
            } else {
               ResourceLocation location3 = new ResourceLocation(resourceDomain, parentTexturePath + ".properties");
               return Config.hasResource(location3) ? location3 : null;
            }
         }
      }
   }

   private static void checkEntityVillager(UUID uuid, EntityVillager entityVillager) {
      Entity entity = IntegratedServerUtils.getEntity(uuid);
      if (entity instanceof EntityVillager) {
         EntityVillager entityVillager2 = (EntityVillager)entity;
         entityVillager.setProfession(entityVillager2.getProfession());
         entityVillager.careerId = entityVillager2.careerId;
         entityVillager.careerLevel = entityVillager2.careerLevel;
      }
   }

   public static void dbg(String str) {
      Config.dbg("RandomEntities: " + str);
   }

   private static IRandomEntity getRandomEntityRendered() {
      if (renderGlobal.renderedEntity != null) {
         randomEntity.setEntity(renderGlobal.renderedEntity);
         return randomEntity;
      } else {
         if (tileEntityRendererDispatcher.tileEntityRendered != null) {
            TileEntity tileEntityRendered = tileEntityRendererDispatcher.tileEntityRendered;
            if (tileEntityRendered.getWorldObj() != null) {
               randomTileEntity.setTileEntity(tileEntityRendered);
               return randomTileEntity;
            }
         }

         return null;
      }
   }

   private static RandomEntityProperties parseProperties(ResourceLocation location, ResourceLocation location2) {
      try {
         String resourcePath = location.getResourcePath();
         dbg(location2.getResourcePath() + ", properties: " + resourcePath);
         InputStream resourceStream = Config.getResourceStream(location);
         if (resourceStream == null) {
            warn("Properties not found: " + resourcePath);
            return null;
         } else {
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(resourceStream);
            resourceStream.close();
            RandomEntityProperties randomEntityProperties = new RandomEntityProperties(propertiesOrdered, resourcePath, location2);
            return !randomEntityProperties.isValid(resourcePath) ? null : randomEntityProperties;
         }
      } catch (FileNotFoundException e) {
         warn("File not found: " + location2.getResourcePath());
         return null;
      } catch (IOException e2) {
         e2.printStackTrace();
         return null;
      }
   }

   private static ResourceLocation[] getLocationsVariants(ResourceLocation location, boolean flag) {
      ArrayList list = new ArrayList();
      list.add(location);
      ResourceLocation locationRandom = getLocationRandom(location, flag);
      if (locationRandom == null) {
         return null;
      } else {
         for (int i = 1; i < list.size() + 10; i++) {
            ResourceLocation locationIndexed = getLocationIndexed(locationRandom, i + 1);
            if (Config.hasResource(locationIndexed)) {
               list.add(locationIndexed);
            }
         }

         if (list.size() <= 1) {
            return null;
         } else {
            ResourceLocation[] locations = (ResourceLocation[])list.toArray(new ResourceLocation[list.size()]);
            dbg(location.getResourcePath() + ", variants: " + locations.length);
            return locations;
         }
      }
   }

   public static ResourceLocation getLocationIndexed(ResourceLocation location, int n) {
      if (location == null) {
         return null;
      } else {
         String resourcePath = location.getResourcePath();
         int n2 = resourcePath.lastIndexOf(46);
         if (n2 < 0) {
            return null;
         } else {
            String str = resourcePath.substring(0, n2);
            String str2 = resourcePath.substring(n2);
            String str3 = str + n + str2;
            return new ResourceLocation(location.getResourceDomain(), str3);
         }
      }
   }
}
