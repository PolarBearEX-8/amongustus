package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.optifine.config.Config;
import net.optifine.override.PlayerControllerOF;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomGuis {
   private static Minecraft mc = Config.getMc();
   private static PlayerControllerOF playerControllerOF = null;
   private static CustomGuiProperties[][] guiProperties = (CustomGuiProperties[][])null;
   public static boolean isChristmas = isChristmas();

   private static CustomGuiProperties[][] propertyListToArray(List list) {
      if (list.isEmpty()) {
         return (CustomGuiProperties[][])null;
      } else {
         CustomGuiProperties[][] customGuiPropertiesArrs = new CustomGuiProperties[EnumGuiContainer.VALUES.length][];

         for (int i = 0; i < customGuiPropertiesArrs.length; i++) {
            if (list.size() > i) {
               List list2 = (List)list.get(i);
               if (list2 != null) {
                  customGuiPropertiesArrs[i] = (CustomGuiProperties[])list2.toArray(new CustomGuiProperties[list2.size()]);
               }
            }
         }

         return customGuiPropertiesArrs;
      }
   }

   private static ResourceLocation getTextureEntity(EnumGuiContainer enumGuiContainer, Entity entity, IBlockAccess world, ResourceLocation location) {
      CustomGuiProperties[] customGuiPropertiesArr = guiProperties[enumGuiContainer.ordinal()];
      if (customGuiPropertiesArr == null) {
         return location;
      } else {
         for (int i = 0; i < customGuiPropertiesArr.length; i++) {
            CustomGuiProperties customGuiProperties = customGuiPropertiesArr[i];
            if (customGuiProperties.matchesEntity(enumGuiContainer, entity, world)) {
               return customGuiProperties.getTextureLocation(location);
            }
         }

         return location;
      }
   }

   public static PlayerControllerOF getPlayerControllerOF() {
      return playerControllerOF;
   }

   private static boolean isChristmas() {
      Calendar instance = Calendar.getInstance();
      return instance.get(2) + 1 == 12 && instance.get(5) >= 24 && instance.get(5) <= 26;
   }

   public static ResourceLocation getTextureLocation(ResourceLocation location) {
      if (guiProperties == null) {
         return location;
      } else {
         GuiScreen currentScreen = mc.currentScreen;
         if (!(currentScreen instanceof GuiContainer)) {
            return location;
         } else if (!location.getResourceDomain().equals("minecraft") || !location.getResourcePath().startsWith("textures/gui/")) {
            return location;
         } else if (playerControllerOF == null) {
            return location;
         } else {
            WorldClient theWorld = mc.theWorld;
            if (theWorld == null) {
               return location;
            } else if (currentScreen instanceof GuiContainerCreative) {
               return getTexturePos(EnumGuiContainer.CREATIVE, mc.thePlayer.getPosition(), theWorld, location, currentScreen);
            } else if (currentScreen instanceof GuiInventory) {
               return getTexturePos(EnumGuiContainer.INVENTORY, mc.thePlayer.getPosition(), theWorld, location, currentScreen);
            } else {
               BlockPos lastClickBlockPos = playerControllerOF.getLastClickBlockPos();
               if (lastClickBlockPos != null) {
                  if (currentScreen instanceof GuiRepair) {
                     return getTexturePos(EnumGuiContainer.ANVIL, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiBeacon) {
                     return getTexturePos(EnumGuiContainer.BEACON, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiBrewingStand) {
                     return getTexturePos(EnumGuiContainer.BREWING_STAND, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiChest) {
                     return getTexturePos(EnumGuiContainer.CHEST, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiCrafting) {
                     return getTexturePos(EnumGuiContainer.CRAFTING, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiDispenser) {
                     return getTexturePos(EnumGuiContainer.DISPENSER, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiEnchantment) {
                     return getTexturePos(EnumGuiContainer.ENCHANTMENT, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiFurnace) {
                     return getTexturePos(EnumGuiContainer.FURNACE, lastClickBlockPos, theWorld, location, currentScreen);
                  }

                  if (currentScreen instanceof GuiHopper) {
                     return getTexturePos(EnumGuiContainer.HOPPER, lastClickBlockPos, theWorld, location, currentScreen);
                  }
               }

               Entity lastClickEntity = playerControllerOF.getLastClickEntity();
               if (lastClickEntity != null) {
                  if (currentScreen instanceof GuiScreenHorseInventory) {
                     return getTextureEntity(EnumGuiContainer.HORSE, lastClickEntity, theWorld, location);
                  }

                  if (currentScreen instanceof GuiMerchant) {
                     return getTextureEntity(EnumGuiContainer.VILLAGER, lastClickEntity, theWorld, location);
                  }
               }

               return location;
            }
         }
      }
   }

   private static void update(IResourcePack resourcePack, List list) {
      String[] strings = ResUtils.collectFiles(resourcePack, "optifine/gui/container/", ".properties", (String[])null);
      Arrays.sort(strings);

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         Config.dbg("CustomGuis: " + str);

         try {
            InputStream inputStream = resourcePack.getInputStream(new ResourceLocation(str));
            if (inputStream == null) {
               Config.warn("CustomGuis file not found: " + str);
            } else {
               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(inputStream);
               inputStream.close();
               CustomGuiProperties customGuiProperties = new CustomGuiProperties(propertiesOrdered, str);
               if (customGuiProperties.isValid(str)) {
                  addToList(customGuiProperties, list);
               }
            }
         } catch (FileNotFoundException e) {
            Config.warn("CustomGuis file not found: " + str);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }

   public static void setPlayerControllerOF(PlayerControllerOF playerControllerOF2) {
      playerControllerOF = playerControllerOF2;
   }

   public static void update() {
      guiProperties = (CustomGuiProperties[][])null;
      if (Config.isCustomGuis()) {
         ArrayList list = new ArrayList();
         IResourcePack[] resourcePacks = Config.getResourcePacks();

         for (int i = resourcePacks.length - 1; i >= 0; i--) {
            update(resourcePacks[i], list);
         }

         guiProperties = propertyListToArray(list);
      }
   }

   private static void addToList(CustomGuiProperties customGuiProperties, List list) {
      if (customGuiProperties.getContainer() == null) {
         warn("Invalid container: " + customGuiProperties.getContainer());
      } else {
         int n = customGuiProperties.getContainer().ordinal();

         while (list.size() <= n) {
            list.add(null);
         }

         List list2 = (List)list.get(n);
         if (list2 == null) {
            list2 = new ArrayList();
            list.set(n, list2);
         }

         list2.add(customGuiProperties);
      }
   }

   private static ResourceLocation getTexturePos(EnumGuiContainer enumGuiContainer, BlockPos pos, IBlockAccess world, ResourceLocation location, GuiScreen screen) {
      CustomGuiProperties[] customGuiPropertiesArr = guiProperties[enumGuiContainer.ordinal()];
      if (customGuiPropertiesArr == null) {
         return location;
      } else {
         for (int i = 0; i < customGuiPropertiesArr.length; i++) {
            CustomGuiProperties customGuiProperties = customGuiPropertiesArr[i];
            if (customGuiProperties.matchesPos(enumGuiContainer, pos, world, screen)) {
               return customGuiProperties.getTextureLocation(location);
            }
         }

         return location;
      }
   }

   private static void warn(String str) {
      Config.warn("[CustomGuis] " + str);
   }
}
