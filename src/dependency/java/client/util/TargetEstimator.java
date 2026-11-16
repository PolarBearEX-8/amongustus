package client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntityOffsetYController;
import net.minecraft.util.ResourceLocation;

public class TargetEstimator {
   public static EntityOffsetYControlController entityOffsetYControlController = new EntityOffsetYControlController();
   public static Map map = new HashMap();
   public static Map map2 = new HashMap();
   public static long timestamp = 0L;
   public static final double value = 7.5;
   public static final double value2 = 56.25;
   public static final int count = 15;
   public static final int count2 = 15;
   public static final int count3 = 10;
   public static final int count4 = 8;
   public static final int count5 = 13;
   public static final int count6 = 8;
   public static final int count7 = 8;
   public static boolean enabled;

   public static int getCount() {
      synchronized (entityOffsetYControlController) {
         return entityOffsetYControlController.size();
      }
   }

   public static double computeValue(int count8, int count9, int count10) {
      double offset = 0.0;
      synchronized (entityOffsetYControlController) {
         List list = entityOffsetYControlController.getList();
         int size = list.size();

         for (int index = 0; index < size; index++) {
            EntityOffsetYController entityOffsetYController = (EntityOffsetYController)list.get(index);
            int count11 = entityOffsetYController.getCount();
            if (count11 > 0) {
               double value3 = entityOffsetYController.getValue2();
               double value4 = entityOffsetYController.getValue3();
               double value5 = entityOffsetYController.getValue4();
               double offset2 = (double)count8 - value3;
               double offset3 = (double)count9 - value4;
               double offset4 = (double)count10 - value5;
               double offset5 = offset2 * offset2 + offset3 * offset3 + offset4 * offset4;
               if (entityOffsetYController.isEnabled() && !BuildManager.isActive34()) {
                  count11 = BuildManager.computeCount(count11 - 2, 0, 15);
                  offset5 *= 2.0;
               }

               if (offset5 <= 56.25) {
                  double offset6 = Math.sqrt(offset5);
                  double offset7 = (1.0 - offset6 / 7.5) * (double)count11;
                  if (offset7 > offset) {
                     offset = offset7;
                  }
               }
            }
         }
      }

      return BuildManager.computeValue(offset, 0.0, 15.0);
   }

   public static void applyRenderGlobal3(RenderGlobal renderGlobal) {
      long timestamp2 = System.currentTimeMillis();
      if (timestamp2 >= timestamp + 50L) {
         timestamp = timestamp2;
         if (!enabled) {
            updateState2();
         }

         synchronized (entityOffsetYControlController) {
            applyRenderGlobal4(renderGlobal);
            if (entityOffsetYControlController.size() > 0) {
               List list = entityOffsetYControlController.getList();

               for (int index = 0; index < list.size(); index++) {
                  ((EntityOffsetYController)list.get(index)).applyRenderGlobalByRenderGlobal(renderGlobal);
               }
            }
         }
      }
   }

   private static void applyRenderGlobal4(RenderGlobal renderGlobal) {
      WorldClient worldClient = renderGlobal.getWorldClient();
      if (worldClient != null) {
         for (Entity entity : (Iterable<Entity>)(Iterable<?>)worldClient.getLoadedEntityList()) {
            if (computeCount(entity) > 0) {
               int entityId = entity.getEntityId();
               if (entityOffsetYControlController.resolveEntityOffsetYController(entityId) == null) {
                  EntityOffsetYController entityOffsetYController = new EntityOffsetYController(entity);
                  entityOffsetYControlController.resolveEntityOffsetYController(entityId, entityOffsetYController);
               }
            } else {
               int entityId2 = entity.getEntityId();
               EntityOffsetYController entityOffsetYController2 = entityOffsetYControlController.resolveEntityOffsetYControllerByIndex(entityId2);
               if (entityOffsetYController2 != null) {
                  entityOffsetYController2.applyRenderGlobal(renderGlobal);
               }
            }
         }
      }
   }

   public static void applyEntity2(Entity entity, RenderGlobal renderGlobal) {
      synchronized (entityOffsetYControlController) {
         EntityOffsetYController entityOffsetYController = entityOffsetYControlController.resolveEntityOffsetYControllerByIndex(entity.getEntityId());
         if (entityOffsetYController != null) {
            entityOffsetYController.applyRenderGlobal(renderGlobal);
         }
      }
   }

   public static int computeCount(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock) {
            Block block = ((ItemBlock)item).getBlock();
            if (block != null) {
               return block.getIndexOffset1();
            }
         }

         if (item == Items.lavaBucket) {
            return Blocks.blockLava.getIndexOffset1();
         } else if (item == Items.blazeRod || item == Items.blazePowder) {
            return 10;
         } else if (item == Items.glowstoneDust) {
            return 8;
         } else if (item == Items.prismarineCrystals) {
            return 8;
         } else if (item == Items.magmaCream) {
            return 8;
         } else if (item == Items.netherStar) {
            return Blocks.blockBeacon.getIndexOffset1() / 2;
         } else {
            if (!map2.isEmpty()) {
               Integer count8 = (Integer)map2.get(item);
               if (count8 != null) {
                  return count8;
               }
            }

            return 0;
         }
      }
   }

   public static void updateState() {
      synchronized (entityOffsetYControlController) {
         entityOffsetYControlController.updateState();
      }
   }

   public static int computeCount(int count8, int count9, int count10, int count11) {
      return computeCount(computeValue(count8, count9, count10), count11);
   }

   private static void updateState2() {
      enabled = true;
      map.clear();
      map2.clear();
      if (!map.isEmpty()) {
         BuildManager.applyText4("DynamicLights entities: " + map.size());
      }

      if (!map2.isEmpty()) {
         BuildManager.applyText4("DynamicLights items: " + map2.size());
      }
   }

   public static double computeValue(BlockPos blockPos) {
      return computeValue(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public static int computeCount(Entity entity, int count8) {
      return computeCount((double)computeCount(entity), count8);
   }

   public static void applyRenderGlobalByRenderGlobal(RenderGlobal renderGlobal) {
      synchronized (entityOffsetYControlController) {
         List list = entityOffsetYControlController.getList();

         for (int index = 0; index < list.size(); index++) {
            ((EntityOffsetYController)list.get(index)).applyRenderGlobal(renderGlobal);
         }

         entityOffsetYControlController.updateState();
      }
   }

   public static int computeCount(Entity entity) {
      if (entity == BuildManager.getMc().getRenderViewEntity() && !BuildManager.isActive58()) {
         return 0;
      } else if (entity instanceof EntityPlayer && ((EntityPlayer)entity).isActive23()) {
         return 0;
      } else if (entity.canExecute2()) {
         return 15;
      } else {
         if (!map.isEmpty()) {
            Integer count8 = (Integer)map.get(entity.getClass());
            if (count8 != null) {
               return count8;
            }
         }

         if (entity instanceof EntityFireball) {
            return 15;
         } else if (entity instanceof EntityPrimedtnt) {
            return 15;
         } else if (entity instanceof EntityBlaze) {
            return ((EntityBlaze)entity).isActive32() ? 15 : 10;
         } else if (entity instanceof EntityLavaslime) {
            return (double)((EntityLavaslime)entity).factor44 > 0.6 ? 13 : 8;
         } else if (entity instanceof EntityCreeper && (double)((EntityCreeper)entity).computeFactor3(0.0F) > 0.001) {
            return 15;
         } else if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntity = (EntityLivingBase)entity;
            int count9 = computeCount(livingEntity.getItemStack());
            int count10 = computeCount(livingEntity.resolveItemStack(4));
            return Math.max(count9, count10);
         } else {
            return entity instanceof EntityItem ? computeCount(resolveItemStack((EntityItem)entity)) : 0;
         }
      }
   }

   private static void applyInputStream(InputStream inputStream, String text, String text2) {
      if (inputStream != null) {
         try {
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            BuildManager.applyText4("DynamicLights: Parsing " + text);
            NetherManager netherManager = new NetherManager("DynamicLights");
            applyText(propertiesAdapter.getProperty("entities"), map, new ResourceLocationResourceLocationCallba(), netherManager, text, text2);
            applyText(propertiesAdapter.getProperty("items"), map2, new ResourceLocationResourceLocationCallbaImpl(), netherManager, text, text2);
         } catch (IOException ioException) {
            BuildManager.applyText3("DynamicLights: Error reading " + text);
         }
      }
   }

   public static ItemStack resolveItemStack(EntityItem entityItem) {
      return entityItem.getDataWatcher().getItemStack(10);
   }

   public static void applyEntity(Entity entity, RenderGlobal renderGlobal) {
   }

   public static int computeCount(double offset, int count8) {
      int count9 = count8;
      if (offset > 0.0) {
         int count10 = (int)(offset * 16.0);
         int count11 = count8 & 0xFF;
         if (count10 > count11) {
            count9 = count8 & -256;
            count9 |= count10;
         }
      }

      return count9;
   }

   private static void applyText(String text, Map map3, ResourceLocationCallback resourceLocationCallback, NetherManager netherManager, String text2, String text3) {
      if (text != null) {
         String[] textArray = BuildManager.resolveTextArray(text, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text4 = textArray[index];
            String[] textArray2 = BuildManager.resolveTextArray(text4, ":");
            if (textArray2.length != 2) {
               netherManager.applyText("Invalid entry: " + text4 + ", in:" + text2);
            } else {
               String text5 = textArray2[0];
               String text6 = textArray2[1];
               String text7 = text3 + ":" + text5;
               ResourceLocation location = new ResourceLocation(text7);
               Object entry = resourceLocationCallback.resolveTarget(location);
               if (entry == null) {
                  netherManager.applyText("Object not found: " + text7);
               } else {
                  int count8 = netherManager.computeCount(text6, -1);
                  if (count8 >= 0 && count8 <= 15) {
                     map3.put(entry, new Integer(count8));
                  } else {
                     netherManager.applyText("Invalid light level: " + text4);
                  }
               }
            }
         }
      }
   }
}
