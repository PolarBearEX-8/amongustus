package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.optifine.config.EntityClassLocator;
import net.optifine.config.IObjectLocator;
import net.optifine.config.ItemLocator;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.util.PropertiesOrdered;

public class DynamicLights {
   private static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
   private static Map mapEntityLightLevels = new HashMap();
   private static Map mapItemLightLevels = new HashMap();
   private static long timeUpdateMs = 0L;
   private static final double MAX_DIST = 7.5;
   private static final double MAX_DIST_SQ = 56.25;
   private static final int LIGHT_LEVEL_MAX = 15;
   private static final int LIGHT_LEVEL_FIRE = 15;
   private static final int LIGHT_LEVEL_BLAZE = 10;
   private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
   private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
   private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
   private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
   private static boolean initialized;

   public static int getCombinedLight(int n, int n2, int n3, int n4) {
      return getCombinedLight(getLightLevel(n, n2, n3), n4);
   }

   public static void removeLights(RenderGlobal renderGlobal) {
      synchronized (mapDynamicLights) {
         List list = mapDynamicLights.valueList();

         for (int i = 0; i < list.size(); i++) {
            ((DynamicLight)list.get(i)).updateLitChunks(renderGlobal);
         }

         mapDynamicLights.clear();
      }
   }

   private static void updateMapDynamicLights(RenderGlobal renderGlobal) {
      WorldClient theWorld = renderGlobal.getTheWorld();
      if (theWorld != null) {
         for (Entity entity : (Iterable<Entity>)(theWorld.getLoadedEntityList())) {
            if (getLightLevel(entity) > 0) {
               int entityId = entity.getEntityId();
               DynamicLight dynamicLight = mapDynamicLights.get(entityId);
               if (dynamicLight == null) {
                  dynamicLight = new DynamicLight(entity);
                  mapDynamicLights.put(entityId, dynamicLight);
               }
            } else {
               int entityId2 = entity.getEntityId();
               DynamicLight dynamicLight2 = mapDynamicLights.remove(entityId2);
               if (dynamicLight2 != null) {
                  dynamicLight2.updateLitChunks(renderGlobal);
               }
            }
         }
      }
   }

   private static void initialize() {
      initialized = true;
      mapEntityLightLevels.clear();
      mapItemLightLevels.clear();
      if (!mapEntityLightLevels.isEmpty()) {
         Config.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
      }

      if (!mapItemLightLevels.isEmpty()) {
         Config.dbg("DynamicLights items: " + mapItemLightLevels.size());
      }
   }

   public static int getLightLevel2(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock) {
            Block block = ((ItemBlock)item).getBlock();
            if (block != null) {
               return block.getLightValue();
            }
         }

         if (item == Items.lava_bucket) {
            return Blocks.lava.getLightValue();
         } else if (item == Items.blaze_rod || item == Items.blaze_powder) {
            return 10;
         } else if (item == Items.glowstone_dust) {
            return 8;
         } else if (item == Items.prismarine_crystals) {
            return 8;
         } else if (item == Items.magma_cream) {
            return 8;
         } else if (item == Items.nether_star) {
            return Blocks.beacon.getLightValue() / 2;
         } else {
            if (!mapItemLightLevels.isEmpty()) {
               Integer num = (Integer)mapItemLightLevels.get(item);
               if (num != null) {
                  return num;
               }
            }

            return 0;
         }
      }
   }

   public static double getLightLevel3(BlockPos pos) {
      return getLightLevel(pos.getX(), pos.getY(), pos.getZ());
   }

   private static void loadModConfiguration(InputStream in, String str, String str2) {
      if (in != null) {
         try {
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(in);
            in.close();
            Config.dbg("DynamicLights: Parsing " + str);
            ConnectedParser connectedParser = new ConnectedParser("DynamicLights");
            loadModLightLevels(propertiesOrdered.getProperty("entities"), mapEntityLightLevels, new EntityClassLocator(), connectedParser, str, str2);
            loadModLightLevels(propertiesOrdered.getProperty("items"), mapItemLightLevels, new ItemLocator(), connectedParser, str, str2);
         } catch (IOException e) {
            Config.warn("DynamicLights: Error reading " + str);
         }
      }
   }

   public static void entityRemoved(Entity entity, RenderGlobal renderGlobal) {
      synchronized (mapDynamicLights) {
         DynamicLight dynamicLight = mapDynamicLights.remove(entity.getEntityId());
         if (dynamicLight != null) {
            dynamicLight.updateLitChunks(renderGlobal);
         }
      }
   }

   public static ItemStack getItemStack(EntityItem entityItem) {
      return entityItem.getDataWatcher().getWatchableObjectItemStack(10);
   }

   public static int getCombinedLight(double d, int n) {
      if (d > 0.0) {
         int n2 = (int)(d * 16.0);
         if (n2 > (n & 0xFF)) {
            n &= -256;
            n |= n2;
         }
      }

      return n;
   }

   public static void entityAdded(Entity entity, RenderGlobal renderGlobal) {
   }

   public static void update(RenderGlobal renderGlobal) {
      long l = System.currentTimeMillis();
      if (l >= timeUpdateMs + 50L) {
         timeUpdateMs = l;
         if (!initialized) {
            initialize();
         }

         synchronized (mapDynamicLights) {
            updateMapDynamicLights(renderGlobal);
            if (mapDynamicLights.size() > 0) {
               List list = mapDynamicLights.valueList();

               for (int i = 0; i < list.size(); i++) {
                  ((DynamicLight)list.get(i)).update(renderGlobal);
               }
            }
         }
      }
   }

   public static int getCombinedLight2(Entity entity, int n) {
      return getCombinedLight((double)getLightLevel(entity), n);
   }

   public static double getLightLevel(int n, int n2, int n3) {
      double d = 0.0;
      synchronized (mapDynamicLights) {
         List list = mapDynamicLights.valueList();
         int size = list.size();

         for (int i = 0; i < size; i++) {
            DynamicLight dynamicLight = (DynamicLight)list.get(i);
            int lastLightLevel = dynamicLight.getLastLightLevel();
            if (lastLightLevel > 0) {
               double lastPosX = dynamicLight.getLastPosX();
               double lastPosY = dynamicLight.getLastPosY();
               double lastPosZ = dynamicLight.getLastPosZ();
               double d2 = (double)n - lastPosX;
               double d3 = (double)n2 - lastPosY;
               double d4 = (double)n3 - lastPosZ;
               double d5 = d2 * d2 + d3 * d3 + d4 * d4;
               if (dynamicLight.isUnderwater() && !Config.isClearWater()) {
                  lastLightLevel = Config.limit(lastLightLevel - 2, 0, 15);
                  d5 *= 2.0;
               }

               if (d5 <= 56.25) {
                  double d6 = Math.sqrt(d5);
                  double d8 = (1.0 - d6 / 7.5) * (double)lastLightLevel;
                  if (d8 > d) {
                     d = d8;
                  }
               }
            }
         }
      }

      return Config.limit2(d, 0.0, 15.0);
   }

   public static void clear() {
      synchronized (mapDynamicLights) {
         mapDynamicLights.clear();
      }
   }

   public static int getCount() {
      synchronized (mapDynamicLights) {
         return mapDynamicLights.size();
      }
   }

   public static int getLightLevel(Entity entity) {
      if (entity == Config.getMc().getRenderViewEntity() && !Config.isDynamicHandLight()) {
         return 0;
      } else {
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isSpectator()) {
               return 0;
            }
         }

         if (entity.isBurning()) {
            return 15;
         } else {
            if (!mapEntityLightLevels.isEmpty()) {
               Integer num = (Integer)mapEntityLightLevels.get(entity.getClass());
               if (num != null) {
                  return num;
               }
            }

            if (entity instanceof EntityFireball) {
               return 15;
            } else if (entity instanceof EntityTNTPrimed) {
               return 15;
            } else if (entity instanceof EntityBlaze) {
               return ((EntityBlaze)entity).isCharged() ? 15 : 10;
            } else if (entity instanceof EntityMagmaCube) {
               return (double)((EntityMagmaCube)entity).squishFactor > 0.6 ? 13 : 8;
            } else {
               if (entity instanceof EntityCreeper) {
                  if ((double)((EntityCreeper)entity).getCreeperFlashIntensity(0.0F) > 0.001) {
                     return 15;
                  }
               }

               if (entity instanceof EntityLivingBase) {
                  EntityLivingBase entity2 = (EntityLivingBase)entity;
                  return Math.max(getLightLevel2(entity2.getHeldItem()), getLightLevel2(entity2.getEquipmentInSlot(4)));
               } else if (entity instanceof EntityItem) {
                  return getLightLevel2(getItemStack((EntityItem)entity));
               } else {
                  return 0;
               }
            }
         }
      }
   }

   private static void loadModLightLevels(String str, Map map, IObjectLocator objectLocator, ConnectedParser connectedParser, String str2, String str3) {
      if (str != null) {
         String[] strings = Config.tokenize(str, " ");

         for (int i = 0; i < strings.length; i++) {
            String str4 = strings[i];
            String[] strings2 = Config.tokenize(str4, ":");
            if (strings2.length != 2) {
               connectedParser.warn("Invalid entry: " + str4 + ", in:" + str2);
            } else {
               String str5 = strings2[0];
               String str6 = strings2[1];
               String str7 = str3 + ":" + str5;
               Object object = objectLocator.getObject(new ResourceLocation(str7));
               if (object == null) {
                  connectedParser.warn("Object not found: " + str7);
               } else {
                  int n = connectedParser.parseInt(str6, -1);
                  if (n >= 0 && n <= 15) {
                     map.put(object, new Integer(n));
                  } else {
                     connectedParser.warn("Invalid light level: " + str4);
                  }
               }
            }
         }
      }
   }
}
