package wdl;

import wdl.api.IEntityAdder;
import wdl.api.ISpecialEntityHandler;
import wdl.api.ModInfo;
import wdl.api.WDLApi;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityUtils {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final Map stringToClassMapping;
   public static final Map classToStringMapping;

   public static boolean isEntityEnabled2(Entity entity) {
      return isEntityEnabled(getEntityType(entity));
   }

   public static boolean isEntityEnabled(String str) {
      boolean flag = WDL.worldProps.getProperty("EntityGroup." + getEntityGroup(str) + ".Enabled", "true").equals("true");
      boolean flag2 = WDL.worldProps.getProperty("Entity." + str + ".Enabled", "true").equals("true");
      return flag && flag2;
   }

   public static Set getEntityTypes() {
      HashSet set = new HashSet();

      for (Entry entry : (Iterable<Entry>)(stringToClassMapping.entrySet())) {
         if (!Modifier.isAbstract(((Class)entry.getValue()).getModifiers())) {
            set.add(entry.getKey());
         }
      }

      for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {
         set.addAll(((ISpecialEntityHandler)modInfo.mod).getSpecialEntities().values());
      }

      return set;
   }

   public static int getEntityRangeFromClassMap(String str) {
      return getEntityRangeFromClassMap((String)classToStringMapping.get(str));
   }

   public static int getDefaultEntityRange2(Entity entity) {
      return WDL.isSpigot() ? getDefaultSpigotEntityRange(entity.getClass()) : getDefaultVanillaEntityRange2(getEntityType(entity));
   }

   public static int getEntityTrackDistance2(String str, Entity entity) {
      if ("default".equals(str)) {
         return getDefaultEntityRange2(entity);
      } else if ("server".equals(str)) {
         int entityRange = WDLPluginChannels.getEntityRange(getEntityType(entity));
         if (entityRange < 0) {
            int defaultEntityRange2 = getDefaultEntityRange2(entity);
            return defaultEntityRange2 < 0 ? WDLPluginChannels.getEntityRange(EntityList.getEntityString(entity)) : defaultEntityRange2;
         } else {
            return entityRange;
         }
      } else if ("user".equals(str)) {
         int n = Integer.parseInt(WDL.worldProps.getProperty("Entity." + getEntityType(entity) + ".TrackDistance", "-1"));
         return n == -1 ? getEntityTrackDistance2("server", entity) : n;
      } else {
         throw new IllegalArgumentException("Mode is not a valid mode: " + str);
      }
   }

   public static int getEntityTrackDistance(Entity entity) {
      return getEntityTrackDistance2(getTrackDistanceMode(), entity);
   }

   public static String getEntityType(Entity entity) {
      String entityString = EntityList.getEntityString(entity);

      for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {
         if (((ISpecialEntityHandler)modInfo.mod).getSpecialEntities().containsKey(entityString)) {
            String specialEntityName = ((ISpecialEntityHandler)modInfo.mod).getSpecialEntityName(entity);
            if (specialEntityName != null) {
               return specialEntityName;
            }
         }
      }

      return entityString;
   }

   public static int getDefaultEntityRange(String str) {
      if (WDL.isSpigot()) {
         Class clazz = (Class)stringToClassMapping.get(str);
         return clazz != null ? getDefaultSpigotEntityRange(clazz) : getDefaultVanillaEntityRange2(str);
      } else {
         return getDefaultVanillaEntityRange2(str);
      }
   }

   public static int getDefaultSpigotEntityRange(Class clazz) {
      if (EntityMob.class.isAssignableFrom(clazz) || EntitySlime.class.isAssignableFrom(clazz)) {
         return 48;
      } else if (EntityCreature.class.isAssignableFrom(clazz) || EntityAmbientCreature.class.isAssignableFrom(clazz)) {
         return 48;
      } else {
         return !EntityItemFrame.class.isAssignableFrom(clazz)
               && !EntityPainting.class.isAssignableFrom(clazz)
               && !EntityItem.class.isAssignableFrom(clazz)
               && !EntityXPOrb.class.isAssignableFrom(clazz)
            ? 64
            : 32;
      }
   }

   static {
      try {
         Map map = null;
         Map map2 = null;

         for (Field field : EntityList.class.getDeclaredFields()) {
            if (field.getType().equals(Map.class)) {
               field.setAccessible(true);
               Map map3 = (Map)field.get(null);
               Entry entry = (Entry)map3.entrySet().toArray()[0];
               if (entry.getKey() instanceof String && entry.getValue() instanceof Class) {
                  map = map3;
               }

               if (entry.getKey() instanceof Class && entry.getValue() instanceof String) {
                  map2 = map3;
               }
            }
         }

         if (map == null) {
            throw new Exception("WDL: Failed to find stringToClassMapping!");
         } else if (map2 == null) {
            throw new Exception("WDL: Failed to find classToStringMapping!");
         } else {
            stringToClassMapping = map;
            classToStringMapping = map2;
         }
      } catch (Exception e) {
         Minecraft.getMinecraft().crashed(new CrashReport("World Downloader Mod: failed to set up entity ranges!", e));
         throw new Error("World Downloader Mod: failed to set up entity ranges!", e);
      }
   }

   public static String getEntityGroup(String str) {
      if (str == null) {
         return null;
      } else {
         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IEntityAdder.class))) {
            List modEntities = ((IEntityAdder)modInfo.mod).getModEntities();
            if (modEntities == null) {
               LOGGER.warn(modInfo + " returned null for getModEntities()!");
            } else if (modEntities.contains(str)) {
               return ((IEntityAdder)modInfo.mod).getEntityCategory(str);
            }
         }

         for (ModInfo modInfo2 : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {
            Multimap specialEntities = ((ISpecialEntityHandler)modInfo2.mod).getSpecialEntities();
            if (specialEntities == null) {
               LOGGER.warn(modInfo2 + " returned null for getSpecialEntities()!");
            } else if (specialEntities.containsValue(str)) {
               return ((ISpecialEntityHandler)modInfo2.mod).getSpecialEntityCategory(str);
            }
         }

         if (stringToClassMapping.containsKey(str)) {
            Class clazz = (Class)stringToClassMapping.get(str);
            if (IMob.class.isAssignableFrom(clazz)) {
               return "Hostile";
            } else {
               return IAnimals.class.isAssignableFrom(clazz) ? "Passive" : "Other";
            }
         } else {
            return null;
         }
      }
   }

   public static int getDefaultVanillaEntityRange2(String str) {
      if (str == null) {
         return -1;
      } else {
         for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(IEntityAdder.class))) {
            List modEntities = ((IEntityAdder)modInfo.mod).getModEntities();
            if (modEntities == null) {
               LOGGER.warn(modInfo + " returned null for getModEntities()!");
            } else if (modEntities.contains(str)) {
               return ((IEntityAdder)modInfo.mod).getDefaultEntityTrackDistance(str);
            }
         }

         for (ModInfo modInfo2 : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {
            Multimap specialEntities = ((ISpecialEntityHandler)modInfo2.mod).getSpecialEntities();
            if (specialEntities == null) {
               LOGGER.warn(modInfo2 + " returned null for getSpecialEntities()!");
            } else {
               for (Entry entry : (Iterable<Entry>)(specialEntities.entries())) {
                  if (((String)entry.getValue()).equals(str)) {
                     int n = ((ISpecialEntityHandler)modInfo2.mod).getSpecialEntityTrackDistance((String)entry.getValue());
                     if (n < 0) {
                        n = getDefaultEntityRange((String)entry.getKey());
                     }

                     return n;
                  }
               }
            }
         }

         return getDefaultVanillaEntityRange((Class)stringToClassMapping.get(str));
      }
   }

   public static int getDefaultVanillaEntityRange(Class clazz) {
      if (clazz == null) {
         return -1;
      } else if (EntityFishHook.class.isAssignableFrom(clazz)
         || EntityArrow.class.isAssignableFrom(clazz)
         || EntitySmallFireball.class.isAssignableFrom(clazz)
         || EntityFireball.class.isAssignableFrom(clazz)
         || EntitySnowball.class.isAssignableFrom(clazz)
         || EntityEnderPearl.class.isAssignableFrom(clazz)
         || EntityEnderEye.class.isAssignableFrom(clazz)
         || EntityEgg.class.isAssignableFrom(clazz)
         || EntityPotion.class.isAssignableFrom(clazz)
         || EntityExpBottle.class.isAssignableFrom(clazz)
         || EntityFireworkRocket.class.isAssignableFrom(clazz)
         || EntityItem.class.isAssignableFrom(clazz)
         || EntitySquid.class.isAssignableFrom(clazz)) {
         return 64;
      } else if (EntityMinecart.class.isAssignableFrom(clazz)
         || EntityBoat.class.isAssignableFrom(clazz)
         || EntityWither.class.isAssignableFrom(clazz)
         || EntityBat.class.isAssignableFrom(clazz)
         || IAnimals.class.isAssignableFrom(clazz)) {
         return 80;
      } else if (EntityDragon.class.isAssignableFrom(clazz)
         || EntityTNTPrimed.class.isAssignableFrom(clazz)
         || EntityFallingBlock.class.isAssignableFrom(clazz)
         || EntityHanging.class.isAssignableFrom(clazz)
         || EntityArmorStand.class.isAssignableFrom(clazz)
         || EntityXPOrb.class.isAssignableFrom(clazz)) {
         return 160;
      } else {
         return EntityEnderCrystal.class.isAssignableFrom(clazz) ? 256 : -1;
      }
   }

   public static String getTrackDistanceMode() {
      return WDL.worldProps.getProperty("Entity.TrackDistanceMode", "server");
   }

   public static Multimap getEntitiesByGroup() {
      HashMultimap hashMultimap = HashMultimap.create();

      for (String str : (Iterable<String>)(getEntityTypes())) {
         hashMultimap.put(getEntityGroup(str), str);
      }

      return hashMultimap;
   }

   public static int getEntityTrackDistance2(String str) {
      return getEntityTrackDistance(getTrackDistanceMode(), str);
   }

   public static int getEntityTrackDistance(String str, String str2) {
      if ("default".equals(str)) {
         int defaultEntityRange = getDefaultEntityRange(str2);
         if (defaultEntityRange < 0) {
            for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {

               for (Entry entry : (Iterable<Entry>)(((ISpecialEntityHandler)modInfo.mod).getSpecialEntities().entries())) {
                  if (str2.equals(entry.getValue())) {
                     return getEntityTrackDistance(str, (String)entry.getKey());
                  }
               }
            }
         }

         return defaultEntityRange;
      } else if (!"server".equals(str)) {
         if ("user".equals(str)) {
            int n = Integer.parseInt(WDL.worldProps.getProperty("Entity." + str2 + ".TrackDistance", "-1"));
            return n == -1 ? getEntityTrackDistance("server", str2) : n;
         } else {
            throw new IllegalArgumentException("Mode is not a valid mode: " + str);
         }
      } else {
         int entityRange = WDLPluginChannels.getEntityRange(str2);
         if (entityRange < 0) {
            int defaultEntityRange2 = getDefaultEntityRange(str2);
            if (defaultEntityRange2 >= 0) {
               return defaultEntityRange2;
            }

            for (ModInfo modInfo2 : (Iterable<ModInfo>)(WDLApi.getImplementingExtensions(ISpecialEntityHandler.class))) {

               for (Entry entry2 : (Iterable<Entry>)(((ISpecialEntityHandler)modInfo2.mod).getSpecialEntities().entries())) {
                  if (str2.equals(entry2.getValue())) {
                     return getEntityTrackDistance(str, (String)entry2.getKey());
                  }
               }
            }
         }

         return entityRange;
      }
   }

}
