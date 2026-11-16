package client.gui;

import client.event.PreUpdateProcessor;
import client.util.AimCollector;
import client.util.AssetsMinecraftManager;
import client.util.BuildManager;
import client.util.CurveEvaluator;
import client.util.PlayerControllerMPHandler;
import client.util.PrimaryLoggerEntry;
import client.util.PropertiesAdapter;
import client.util.SplineBuilder;
import client.util.SplineEngine;
import client.util.SplinePipeline;
import client.util.SplinePool;
import client.util.SplineProfiler;
import client.util.SplineService;
import client.util.SplineSynchronizer;
import client.util.VectorPool;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChestContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class OptifineGuiContainerManager {
   public static Minecraft mc = BuildManager.getMc();
   public static PlayerControllerMPHandler playerControllerMPHandler = null;
   public static VectorPool[][] vectorPoolArray = null;
   public static boolean enabled = isActive();

   public static void setPlayerControllerMPHandler(PlayerControllerMPHandler playerControllerMPHandler2) {
      playerControllerMPHandler = playerControllerMPHandler2;
   }

   public static void renderElement() {
      vectorPoolArray = (VectorPool[][])null;
      if (BuildManager.isActive56()) {
         ArrayList list = new ArrayList();
         PreUpdateProcessor[] preUpdateProcessorArray = BuildManager.getPreUpdateProcessorArray();

         for (int index = preUpdateProcessorArray.length - 1; index >= 0; index--) {
            applyPreUpdateProcessor(preUpdateProcessorArray[index], list);
         }

         vectorPoolArray = resolveVectorPoolArray(list);
      }
   }

   private static ResourceLocation resolveResourceLocationByAimCollector(
      AimCollector aimCollector, BlockPos blockPos, IBlockAccess iBlockAccess, ResourceLocation location, GuiScreen screen
   ) {
      VectorPool[] vectorPoolArray2 = vectorPoolArray[aimCollector.ordinal()];
      if (vectorPoolArray2 == null) {
         return location;
      } else {
         for (int index = 0; index < vectorPoolArray2.length; index++) {
            VectorPool vectorPool = vectorPoolArray2[index];
            if (vectorPool.matchesAimCollectorByAimCollector(aimCollector, blockPos, iBlockAccess, screen)) {
               return vectorPool.get(location);
            }
         }

         return location;
      }
   }

   private static void applyPreUpdateProcessor(PreUpdateProcessor preUpdateProcessor, List list) {
      String[] textArray = AssetsMinecraftManager.resolveTextArray(preUpdateProcessor, "optifine/gui/container/", ".properties", (String[])null);
      Arrays.sort(textArray);

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         BuildManager.applyText4("CustomGuis: " + text);

         try {
            ResourceLocation location = new ResourceLocation(text);
            InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
            if (inputStream == null) {
               BuildManager.applyText3("CustomGuis file not found: " + text);
            } else {
               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               VectorPool vectorPool = new VectorPool(propertiesAdapter, text);
               if (vectorPool.matchesText(text)) {
                  applyVectorPool(vectorPool, list);
               }
            }
         } catch (FileNotFoundException fileNotFoundException) {
            BuildManager.applyText3("CustomGuis file not found: " + text);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   private static void applyVectorPool(VectorPool vectorPool, List list) {
      if (vectorPool.getAimCollector() == null) {
         applyText("Invalid container: " + vectorPool.getAimCollector());
      } else {
         int ordinal = vectorPool.getAimCollector().ordinal();

         while (list.size() <= ordinal) {
            list.add(null);
         }

         List entry = (List)list.get(ordinal);
         if (entry == null) {
            entry = new ArrayList();
            list.set(ordinal, entry);
         }

         entry.add(vectorPool);
      }
   }

   private static boolean isActive() {
      Calendar instance = Calendar.getInstance();
      return instance.get(2) + 1 == 12 && instance.get(5) >= 24 && instance.get(5) <= 26;
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location) {
      if (vectorPoolArray == null) {
         return location;
      } else {
         GuiScreen currentScreen = mc.currentScreen;
         if (!(currentScreen instanceof GuiContainer)) {
            return location;
         } else if (!location.getText().equals("minecraft") || !location.getText1().startsWith("textures/gui/")) {
            return location;
         } else if (playerControllerMPHandler == null) {
            return location;
         } else {
            WorldClient world = mc.theWorld;
            if (world == null) {
               return location;
            } else if (currentScreen instanceof ScrollableContainer) {
               return resolveResourceLocationByAimCollector(AimCollector.CREATIVE, mc.thePlayer.getBlockPos(), world, location, currentScreen);
            } else if (currentScreen instanceof GuiInventory) {
               return resolveResourceLocationByAimCollector(AimCollector.INVENTORY, mc.thePlayer.getBlockPos(), world, location, currentScreen);
            } else {
               BlockPos blockPos = playerControllerMPHandler.getBlockPos2();
               if (blockPos != null) {
                  if (currentScreen instanceof SplinePipeline) {
                     return resolveResourceLocationByAimCollector(AimCollector.ANVIL, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof CurveEvaluator) {
                     return resolveResourceLocationByAimCollector(AimCollector.BEACON, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof SplinePool) {
                     return resolveResourceLocationByAimCollector(AimCollector.BREWING_STAND, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof CustomGuiScreen) {
                     return resolveResourceLocationByAimCollector(AimCollector.CHEST, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiChestContainer) {
                     return resolveResourceLocationByAimCollector(AimCollector.CRAFTING, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof SplineProfiler) {
                     return resolveResourceLocationByAimCollector(AimCollector.DISPENSER, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof SplineService) {
                     return resolveResourceLocationByAimCollector(AimCollector.ENCHANTMENT, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof SplineBuilder) {
                     return resolveResourceLocationByAimCollector(AimCollector.FURNACE, blockPos, world, location, currentScreen);
                  }

                  if (currentScreen instanceof SplineEngine) {
                     return resolveResourceLocationByAimCollector(AimCollector.HOPPER, blockPos, world, location, currentScreen);
                  }
               }

               Entity entityAsEntity = playerControllerMPHandler.getEntityAsEntity();
               if (entityAsEntity != null) {
                  if (currentScreen instanceof SplineSynchronizer) {
                     return resolveResourceLocationByAimCollector(AimCollector.HORSE, entityAsEntity, world, location);
                  }

                  if (currentScreen instanceof PrimaryLoggerEntry) {
                     return resolveResourceLocationByAimCollector(AimCollector.VILLAGER, entityAsEntity, world, location);
                  }
               }

               return location;
            }
         }
      }
   }

   private static VectorPool[][] resolveVectorPoolArray(List list) {
      if (list.isEmpty()) {
         return (VectorPool[][])null;
      } else {
         VectorPool[][] vectorPoolMatrix = new VectorPool[AimCollector.VALUES.length][];

         for (int index = 0; index < vectorPoolMatrix.length; index++) {
            if (list.size() > index) {
               List list2 = (List)list.get(index);
               if (list2 != null) {
                  VectorPool[] vectorPoolArray2 = ((VectorPool[])list2.toArray(new VectorPool[list2.size()]));
                  vectorPoolMatrix[index] = vectorPoolArray2;
               }
            }
         }

         return vectorPoolMatrix;
      }
   }

   public static PlayerControllerMPHandler getPlayerControllerMPHandler() {
      return playerControllerMPHandler;
   }

   private static void applyText(String text) {
      BuildManager.applyText3("[CustomGuis] " + text);
   }

   private static ResourceLocation resolveResourceLocationByAimCollector(AimCollector aimCollector, Entity entity, IBlockAccess iBlockAccess, ResourceLocation location) {
      VectorPool[] vectorPoolArray2 = vectorPoolArray[aimCollector.ordinal()];
      if (vectorPoolArray2 == null) {
         return location;
      } else {
         for (int index = 0; index < vectorPoolArray2.length; index++) {
            VectorPool vectorPool = vectorPoolArray2[index];
            if (vectorPool.matchesAimCollectorByAimCollector(aimCollector, entity, iBlockAccess)) {
               return vectorPool.get(location);
            }
         }

         return location;
      }
   }
}
