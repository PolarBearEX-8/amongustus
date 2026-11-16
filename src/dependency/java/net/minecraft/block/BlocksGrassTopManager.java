package net.minecraft.block;

import client.setting.MovementOffsetProperty;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.ListenerAdapter27;
import client.util.NormalManager;
import client.util.PropertiesAdapter;
import client.util.ServiceStringEntry;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class BlocksGrassTopManager {
   public static boolean enabled6 = true;
   public static boolean enabled7 = true;
   public static boolean enabled3 = true;
   public static boolean enabled8 = true;
   public static boolean enabled5 = true;
   public static boolean enabled9 = true;
   public static boolean enabled2 = false;
   public static ServiceStringEntry serviceStringEntry = null;
   public static ServiceStringEntry serviceStringEntry2 = null;
   public static ServiceStringEntry serviceStringEntry4 = null;
   public static ServiceStringEntry serviceStringEntry5 = null;
   public static ServiceStringEntry serviceStringEntry3 = null;
   public static boolean enabled = false;
   public static IBakedModel iBakedModel2 = null;
   public static IBakedModel iBakedModel4 = null;
   public static IBakedModel iBakedModel3 = null;
   public static IBakedModel iBakedModel = null;
   public static boolean enabled4 = false;
   public static final String labelText = "blocks/grass_top";
   public static final String labelText3 = "blocks/grass_side";
   public static final String labelText4 = "blocks/mycelium_top";
   public static final String labelText2 = "blocks/dirt_podzol_top";
   public static final String labelText5 = "blocks/snow";

   public static List resolveListByIBlockAccess(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, List list) {
      if (enumFacing == EnumFacing.UP || enumFacing == EnumFacing.DOWN) {
         return list;
      } else if (!enabled4) {
         return list;
      } else {
         Block block = blockState.getBlock();
         return block instanceof BlockMycelium
            ? resolveList2(iBlockAccess, blockState, blockPos, enumFacing, list)
            : (
               block instanceof BlockDirt
                  ? resolveList3(iBlockAccess, blockState, blockPos, enumFacing, list)
                  : (block instanceof BlockGrass ? resolveList(iBlockAccess, blockState, blockPos, enumFacing, list) : list)
            );
      }
   }

   private static List resolveList(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, List list) {
      Block block = iBlockAccess.getBlockState(blockPos.up()).getBlock();
      boolean flag = block == Blocks.block61 || block == Blocks.block74;
      if (BuildManager.isActive64()) {
         if (flag) {
            if (enabled8 && resolveBlock(blockPos, enumFacing, iBlockAccess) == Blocks.block74) {
               return iBakedModel.resolveList(enumFacing);
            }
         } else if (enabled6 && resolveBlock(blockPos.down(), enumFacing, iBlockAccess) == Blocks.blockGrass) {
            return iBakedModel2.resolveList(enumFacing);
         }
      } else if (flag) {
         if (enabled8) {
            return iBakedModel.resolveList(enumFacing);
         }
      } else if (enabled6) {
         return iBakedModel2.resolveList(enumFacing);
      }

      return list;
   }

   private static void applyListenerAdapterByListenerAdapter2(ListenerAdapter27 listenerAdapter) {
      enabled6 = true;
      enabled7 = true;
      enabled3 = true;
      enabled8 = true;
      enabled5 = true;
      enabled9 = true;
      serviceStringEntry = listenerAdapter.resolveServiceStringEntry(new ResourceLocation("blocks/grass_top"));
      serviceStringEntry2 = listenerAdapter.resolveServiceStringEntry(new ResourceLocation("blocks/grass_side"));
      serviceStringEntry4 = listenerAdapter.resolveServiceStringEntry(new ResourceLocation("blocks/mycelium_top"));
      serviceStringEntry5 = listenerAdapter.resolveServiceStringEntry(new ResourceLocation("blocks/dirt_podzol_top"));
      serviceStringEntry3 = listenerAdapter.resolveServiceStringEntry(new ResourceLocation("blocks/snow"));
      enabled = true;
      String text = "optifine/bettergrass.properties";

      try {
         ResourceLocation location = new ResourceLocation(text);
         if (!BuildManager.matchesResourceLocation3(location)) {
            return;
         }

         InputStream inputStream = BuildManager.resolveInputStream(location);
         if (inputStream == null) {
            return;
         }

         if (BuildManager.matchesResourceLocationByResourceLocation(location)) {
            BuildManager.applyText4("BetterGrass: Parsing default configuration " + text);
         } else {
            BuildManager.applyText4("BetterGrass: Parsing configuration " + text);
         }

         PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
         propertiesAdapter.load(inputStream);
         inputStream.close();
         enabled6 = matchesProperties(propertiesAdapter, "grass", true);
         enabled7 = matchesProperties(propertiesAdapter, "mycelium", true);
         enabled3 = matchesProperties(propertiesAdapter, "podzol", true);
         enabled8 = matchesProperties(propertiesAdapter, "grass.snow", true);
         enabled5 = matchesProperties(propertiesAdapter, "mycelium.snow", true);
         enabled9 = matchesProperties(propertiesAdapter, "podzol.snow", true);
         enabled2 = matchesProperties(propertiesAdapter, "grass.multilayer", false);
         serviceStringEntry = resolveServiceStringEntry(propertiesAdapter, "texture.grass", "blocks/grass_top", listenerAdapter);
         serviceStringEntry2 = resolveServiceStringEntry(propertiesAdapter, "texture.grass_side", "blocks/grass_side", listenerAdapter);
         serviceStringEntry4 = resolveServiceStringEntry(propertiesAdapter, "texture.mycelium", "blocks/mycelium_top", listenerAdapter);
         serviceStringEntry5 = resolveServiceStringEntry(propertiesAdapter, "texture.podzol", "blocks/dirt_podzol_top", listenerAdapter);
         serviceStringEntry3 = resolveServiceStringEntry(propertiesAdapter, "texture.snow", "blocks/snow", listenerAdapter);
      } catch (IOException ioException) {
         BuildManager.applyText3("Error reading: " + text + ", " + ioException.getClass().getName() + ": " + ioException.getMessage());
      }
   }

   public static void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      enabled = false;
      enabled4 = false;
      applyListenerAdapterByListenerAdapter2(listenerAdapter);
   }

   private static Block resolveBlock(BlockPos blockPos, EnumFacing enumFacing, IBlockAccess iBlockAccess) {
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      return iBlockAccess.getBlockState(blockPos2).getBlock();
   }

   public static void updateState() {
      if (enabled) {
         iBakedModel2 = NormalManager.resolveIBakedModel(serviceStringEntry, 0);
         if (enabled2) {
            iBakedModel2 = NormalManager.resolveIBakedModel(NormalManager.resolveIBakedModel(serviceStringEntry2, -1), iBakedModel2);
         }

         iBakedModel4 = NormalManager.resolveIBakedModel(serviceStringEntry4, -1);
         iBakedModel3 = NormalManager.resolveIBakedModel(serviceStringEntry5, 0);
         iBakedModel = NormalManager.resolveIBakedModel(serviceStringEntry3, -1);
         enabled4 = true;
      }
   }

   private static List resolveList2(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, List list) {
      Block block = iBlockAccess.getBlockState(blockPos.up()).getBlock();
      boolean flag = block == Blocks.block61 || block == Blocks.block74;
      if (BuildManager.isActive64()) {
         if (flag) {
            if (enabled5 && resolveBlock(blockPos, enumFacing, iBlockAccess) == Blocks.block74) {
               return iBakedModel.resolveList(enumFacing);
            }
         } else if (enabled7 && resolveBlock(blockPos.down(), enumFacing, iBlockAccess) == Blocks.blockMycelium) {
            return iBakedModel4.resolveList(enumFacing);
         }
      } else if (flag) {
         if (enabled5) {
            return iBakedModel.resolveList(enumFacing);
         }
      } else if (enabled7) {
         return iBakedModel4.resolveList(enumFacing);
      }

      return list;
   }

   private static List resolveList3(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, List list) {
      Block block = resolveBlock(blockPos, EnumFacing.UP, iBlockAccess);
      if (blockState.getComparable(BlockDirt.immutableSetEntry) != MovementOffsetProperty.PODZOL) {
         return list;
      } else {
         boolean flag = block == Blocks.block61 || block == Blocks.block74;
         if (BuildManager.isActive64()) {
            if (flag) {
               if (enabled9 && resolveBlock(blockPos, enumFacing, iBlockAccess) == Blocks.block74) {
                  return iBakedModel.resolveList(enumFacing);
               }
            } else if (enabled3) {
               BlockPos blockPos2 = blockPos.down().offset(enumFacing);
               IBlockState blockState2 = iBlockAccess.getBlockState(blockPos2);
               if (blockState2.getBlock() == Blocks.block115 && blockState2.getComparable(BlockDirt.immutableSetEntry) == MovementOffsetProperty.PODZOL) {
                  return iBakedModel3.resolveList(enumFacing);
               }
            }
         } else if (flag) {
            if (enabled9) {
               return iBakedModel.resolveList(enumFacing);
            }
         } else if (enabled3) {
            return iBakedModel3.resolveList(enumFacing);
         }

         return list;
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry(Properties properties, String text, String text2, ListenerAdapter27 listenerAdapter) {
      String property = properties.getProperty(text);
      if (property == null) {
         property = text2;
      }

      ResourceLocation location = new ResourceLocation("textures/" + property + ".png");
      if (!BuildManager.matchesResourceLocation3(location)) {
         BuildManager.applyText3("BetterGrass texture not found: " + location);
         property = text2;
      }

      ResourceLocation location2 = new ResourceLocation(property);
      return listenerAdapter.resolveServiceStringEntry(location2);
   }

   private static boolean matchesProperties(Properties properties, String text, boolean flag) {
      String property = properties.getProperty(text);
      return property == null ? flag : Boolean.parseBoolean(property);
   }
}
