package client.util;

import client.event.PreUpdateProcessor;
import client.setting.MovementTimeoutProperty;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlassPane;
import net.minecraft.block.BlockQuartzBlock;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlocksGrassTopManager;
import net.minecraft.block.CommonBlockAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class McpatcherCtmDefaultManager {
   public static Map[] mapArray3 = null;
   public static Map[] mapArray = null;
   public static Map[][] mapArray2 = null;
   public static AuxiliaryPngManager[][] auxiliaryPngManagerArray = null;
   public static AuxiliaryPngManager[][] auxiliaryPngManagerArray2 = null;
   public static boolean enabled = false;
   public static final int count10 = -1;
   public static final int count8 = 0;
   public static final int count7 = 1;
   public static final int count3 = 2;
   public static final int count5 = 3;
   public static final int count = 4;
   public static final int count6 = 5;
   public static final int count9 = 0;
   public static final int count2 = 1;
   public static final int count4 = 2;
   public static final IBlockState iBlockState = Blocks.air.getDefaultState();
   public static ServiceStringEntry serviceStringEntry = null;
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray11 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.WEST, MovementTimeoutProperty.EAST, MovementTimeoutProperty.NORTH, MovementTimeoutProperty.SOUTH
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray15 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.WEST, MovementTimeoutProperty.EAST, MovementTimeoutProperty.SOUTH, MovementTimeoutProperty.NORTH
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.EAST, MovementTimeoutProperty.WEST, MovementTimeoutProperty.DOWN, MovementTimeoutProperty.UP
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray10 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.WEST, MovementTimeoutProperty.EAST, MovementTimeoutProperty.DOWN, MovementTimeoutProperty.UP
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray14 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.NORTH, MovementTimeoutProperty.SOUTH, MovementTimeoutProperty.DOWN, MovementTimeoutProperty.UP
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray16 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.SOUTH, MovementTimeoutProperty.NORTH, MovementTimeoutProperty.DOWN, MovementTimeoutProperty.UP
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray8 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.WEST, MovementTimeoutProperty.EAST, MovementTimeoutProperty.UP, MovementTimeoutProperty.DOWN
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray7 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.NORTH, MovementTimeoutProperty.SOUTH, MovementTimeoutProperty.UP, MovementTimeoutProperty.DOWN
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray5 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.NORTH_EAST, MovementTimeoutProperty.NORTH_WEST, MovementTimeoutProperty.SOUTH_EAST, MovementTimeoutProperty.SOUTH_WEST
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray13 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.SOUTH_EAST, MovementTimeoutProperty.SOUTH_WEST, MovementTimeoutProperty.NORTH_EAST, MovementTimeoutProperty.NORTH_WEST
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray6 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.DOWN_WEST, MovementTimeoutProperty.DOWN_EAST, MovementTimeoutProperty.UP_WEST, MovementTimeoutProperty.UP_EAST
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray9 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.DOWN_EAST, MovementTimeoutProperty.DOWN_WEST, MovementTimeoutProperty.UP_EAST, MovementTimeoutProperty.UP_WEST
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray12 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.DOWN_SOUTH, MovementTimeoutProperty.DOWN_NORTH, MovementTimeoutProperty.UP_SOUTH, MovementTimeoutProperty.UP_NORTH
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray4 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.DOWN_NORTH, MovementTimeoutProperty.DOWN_SOUTH, MovementTimeoutProperty.UP_NORTH, MovementTimeoutProperty.UP_SOUTH
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray3 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.UP_EAST, MovementTimeoutProperty.UP_WEST, MovementTimeoutProperty.DOWN_EAST, MovementTimeoutProperty.DOWN_WEST
   };
   public static final MovementTimeoutProperty[] movementTimeoutPropertyArray2 = new MovementTimeoutProperty[]{
      MovementTimeoutProperty.UP_SOUTH, MovementTimeoutProperty.UP_NORTH, MovementTimeoutProperty.DOWN_SOUTH, MovementTimeoutProperty.DOWN_NORTH
   };
   public static final ServiceStringEntry serviceStringEntry2 = new ServiceStringEntry("<default>");

   private static ServiceStringEntry resolveServiceStringEntry(AuxiliaryPngManager auxiliaryPngManager, BlockPos blockPos, int count11) {
      if (auxiliaryPngManager.serviceStringEntryArray.length == 1) {
         return auxiliaryPngManager.serviceStringEntryArray[0];
      } else {
         int coordX = blockPos.getX();
         int coordY = blockPos.getY();
         int coordZ = blockPos.getZ();
         int count12 = 0;
         int count13 = 0;
         switch (count11) {
            case 0:
               count12 = coordX;
               count13 = -coordZ - 1;
               break;
            case 1:
               count12 = coordX;
               count13 = coordZ;
               break;
            case 2:
               count12 = -coordX - 1;
               count13 = -coordY;
               break;
            case 3:
               count12 = coordX;
               count13 = -coordY;
               break;
            case 4:
               count12 = coordZ;
               count13 = -coordY;
               break;
            case 5:
               count12 = -coordZ - 1;
               count13 = -coordY;
         }

         int count14 = count12 % auxiliaryPngManager.count11;
         int count15 = count13 % auxiliaryPngManager.count26;
         if (count14 < 0) {
            count14 += auxiliaryPngManager.count11;
         }

         if (count15 < 0) {
            count15 += auxiliaryPngManager.count26;
         }

         int count16 = count15 * auxiliaryPngManager.count11 + count14;
         return auxiliaryPngManager.serviceStringEntryArray[count16];
      }
   }

   private static synchronized LocalIntArrayEntry[] resolveLocalIntArrayEntryArray2(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockState blockState,
      BlockPos blockPos,
      int count11,
      int count12,
      LocalIntArrayEntry localIntArrayEntry,
      int count13,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      ServiceStringEntry serviceStringEntry3 = localIntArrayEntry.getServiceStringEntry();
      return SmoothEngine.resolveLocalIntArrayEntryArray(computeCount(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13, baseIBlockStateEntry), auxiliaryPngManager, count12, localIntArrayEntry, baseIBlockStateEntry);
   }

   private static boolean matchesAuxiliaryPngManager2(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, ServiceStringEntry serviceStringEntry3, int count12
   ) {
      IBlockState blockState2 = iBlockAccess.getBlockState(blockPos);
      if (blockState2 == iBlockState) {
         return false;
      } else {
         if (auxiliaryPngManager.smoothMatcherArray2 != null && blockState2 instanceof IBlockStateAdapter) {
            IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState2;
            if (!auxiliaryPngManager.checkIndex(iBlockStateAdapter.getCount2(), iBlockStateAdapter.getCountAsIndex())) {
               return false;
            }
         }

         if (auxiliaryPngManager.serviceStringEntryArray2 != null && resolveServiceStringEntry(iBlockAccess, blockState, blockPos, blockState2, count11) != serviceStringEntry3) {
            return false;
         } else {
            IBlockState blockState3 = iBlockAccess.getBlockState(blockPos.offset(resolveEnumFacing(count11)));
            return blockState3.getBlock().processAction15() ? false : count11 != 1 || blockState3.getBlock() != Blocks.block74;
         }
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry2(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, int count12, ServiceStringEntry serviceStringEntry3, int count13
   ) {
      ServiceStringEntry[] serviceStringEntryArray = auxiliaryPngManager.serviceStringEntryArray;
      ServiceStringEntry serviceStringEntry4 = resolveServiceStringEntry4(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13);
      if (serviceStringEntry4 != null && serviceStringEntry4 != serviceStringEntry3 && serviceStringEntry4 != serviceStringEntryArray[3]) {
         return serviceStringEntry4;
      } else {
         ServiceStringEntry serviceStringEntry5 = resolveServiceStringEntryByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13);
         return serviceStringEntry5 == serviceStringEntryArray[0] ? serviceStringEntryArray[4] : (serviceStringEntry5 == serviceStringEntryArray[1] ? serviceStringEntryArray[5] : (serviceStringEntry5 == serviceStringEntryArray[2] ? serviceStringEntryArray[6] : serviceStringEntry5));
      }
   }

   private static void applyFlagArray(int count11, int count12, boolean[] flagArray) {
      boolean flag = flagArray[count11];
      flagArray[count11] = flagArray[count12];
      flagArray[count12] = flag;
   }

   private static void applyAuxiliaryPngManager(AuxiliaryPngManager auxiliaryPngManager, List list, int count11) {
      while (count11 >= list.size()) {
         list.add(null);
      }

      List entry = (List)list.get(count11);
      if (entry == null) {
         entry = new ArrayList();
         list.set(count11, entry);
      }

      entry.add(auxiliaryPngManager);
   }

   private static String[] getTextArray() {
      ArrayList list = new ArrayList();
      String text = "mcpatcher/ctm/default/";
      if (BuildManager.matchesResourceLocationByResourceLocation(new ResourceLocation("textures/blocks/glass.png"))) {
         list.add(text + "glass.properties");
         list.add(text + "glasspane.properties");
      }

      if (BuildManager.matchesResourceLocationByResourceLocation(new ResourceLocation("textures/blocks/bookshelf.png"))) {
         list.add(text + "bookshelf.properties");
      }

      if (BuildManager.matchesResourceLocationByResourceLocation(new ResourceLocation("textures/blocks/sandstone_normal.png"))) {
         list.add(text + "sandstone.properties");
      }

      String[] textArray = new String[]{
         "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"
      };

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         if (BuildManager.matchesResourceLocationByResourceLocation(new ResourceLocation("textures/blocks/glass_" + text2 + ".png"))) {
            list.add(text + index + "_glass_" + text2 + "/glass_" + text2 + ".properties");
            list.add(text + index + "_glass_" + text2 + "/glass_pane_" + text2 + ".properties");
         }
      }

      return ((String[])list.toArray(new String[list.size()]));
   }

   private static ServiceStringEntry resolveServiceStringEntry(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, IBlockState blockState2, int count11) {
      IBlockState blockState3 = blockState2.getBlock().processAction(blockState2, iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      IBakedModel iBakedModel = Minecraft.getMinecraft().getGoldenCrystal().getCommonMapEntry().resolveIBakedModel(blockState3);
      if (iBakedModel == null) {
         return null;
      } else {
         EnumFacing enumFacing = resolveEnumFacing(count11);
         List list = iBakedModel.resolveList(enumFacing);
         if (list == null) {
            return null;
         } else {
            if (BuildManager.isActive36()) {
               list = BlocksGrassTopManager.resolveListByIBlockAccess(iBlockAccess, blockState3, blockPos, enumFacing, list);
            }

            if (list.size() > 0) {
               return ((LocalIntArrayEntry)list.get(0)).getServiceStringEntry();
            } else {
               List list2 = iBakedModel.getList();
               if (list2 == null) {
                  return null;
               } else {
                  for (int index = 0; index < list2.size(); index++) {
                     LocalIntArrayEntry localIntArrayEntry = (LocalIntArrayEntry)list2.get(index);
                     if (localIntArrayEntry.getEnumFacing() == enumFacing) {
                        return localIntArrayEntry.getServiceStringEntry();
                     }
                  }

                  return null;
               }
            }
         }
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockStateAdapter iBlockStateAdapter, BlockPos blockPos, int count11, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (!localIntArrayEntry.isActive()) {
         return null;
      } else {
         ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(auxiliaryPngManager.blockSolidType);

         try {
            ServiceStringEntry serviceStringEntry3 = resolveServiceStringEntry(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count11);
            if (serviceStringEntry3 != null) {
               managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(serviceStringEntry3, localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
            }
         } finally {
            if (managedListEntry.size() > 0) {
               baseIBlockStateEntry.setEnabled(true);
            }
         }

         return (LocalIntArrayEntry[])null;
      }
   }

   private static EnumFacing resolveEnumFacing(int count11) {
      switch (count11) {
         case 0:
            return EnumFacing.DOWN;
         case 1:
            return EnumFacing.UP;
         case 2:
            return EnumFacing.NORTH;
         case 3:
            return EnumFacing.SOUTH;
         case 4:
            return EnumFacing.WEST;
         case 5:
            return EnumFacing.EAST;
         default:
            return EnumFacing.UP;
      }
   }

   public static void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      auxiliaryPngManagerArray = (AuxiliaryPngManager[][])null;
      auxiliaryPngManagerArray2 = (AuxiliaryPngManager[][])null;
      mapArray3 = null;
      mapArray2 = (Map[][])null;
      if (BuildManager.isActive47()) {
         PreUpdateProcessor[] preUpdateProcessorArray = BuildManager.getPreUpdateProcessorArray();

         for (int index = preUpdateProcessorArray.length - 1; index >= 0; index--) {
            PreUpdateProcessor preUpdateProcessor = preUpdateProcessorArray[index];
            applyListenerAdapter27(listenerAdapter, preUpdateProcessor);
         }

         applyListenerAdapter27(listenerAdapter, BuildManager.getIReloadableResourceManager());
         ResourceLocation location = new ResourceLocation("mcpatcher/ctm/default/empty");
         serviceStringEntry = listenerAdapter.resolveServiceStringEntry(location);
         mapArray3 = new Map[listenerAdapter.getCountAsIndex() + 1];
         mapArray = new Map[listenerAdapter.getCountAsIndex() + 1];
         mapArray2 = new Map[listenerAdapter.getCountAsIndex() + 1][];
         if (auxiliaryPngManagerArray.length <= 0) {
            auxiliaryPngManagerArray = (AuxiliaryPngManager[][])null;
         }

         if (auxiliaryPngManagerArray2.length <= 0) {
            auxiliaryPngManagerArray2 = (AuxiliaryPngManager[][])null;
         }
      }
   }

   private static boolean matchesIBlockState(IBlockState blockState) {
      if (blockState.getBlock().processAction14()) {
         return true;
      } else {
         Block block = blockState.getBlock();
         return block instanceof BlockGlass ? true : block instanceof BlockStainedGlass;
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockState blockState,
      BlockPos blockPos,
      int count11,
      int count12,
      LocalIntArrayEntry localIntArrayEntry,
      int count13,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (!localIntArrayEntry.isActive()) {
         return null;
      } else {
         ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(auxiliaryPngManager.blockSolidType);

         try {
            ServiceStringEntry serviceStringEntry3 = resolveServiceStringEntry(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, localIntArrayEntry.getServiceStringEntry(), count13, baseIBlockStateEntry);
            if (serviceStringEntry3 != null) {
               managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(serviceStringEntry3, localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
            }
         } finally {
            if (managedListEntry.size() > 0) {
               baseIBlockStateEntry.setEnabled(true);
            }
         }

         return (LocalIntArrayEntry[])null;
      }
   }

   private static MovementTimeoutProperty[] resolveMovementTimeoutPropertyArray(int count11, int count12) {
      switch (count11) {
         case 0:
            return movementTimeoutPropertyArray5;
         case 1:
            return movementTimeoutPropertyArray13;
         case 2:
            if (count12 == 1) {
               return movementTimeoutPropertyArray3;
            }

            return movementTimeoutPropertyArray6;
         case 3:
            return movementTimeoutPropertyArray9;
         case 4:
            return movementTimeoutPropertyArray12;
         case 5:
            if (count12 == 2) {
               return movementTimeoutPropertyArray2;
            }

            return movementTimeoutPropertyArray4;
         default:
            throw new IllegalArgumentException("Unknown side: " + count11);
      }
   }

   private static boolean matchesAuxiliaryPngManager(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, IBlockState blockState2, int count11, ServiceStringEntry serviceStringEntry3, int count12
   ) {
      if (blockState == blockState2) {
         return true;
      } else if (auxiliaryPngManager.count13 == 2) {
         if (blockState2 == null) {
            return false;
         } else {
            return blockState2 == iBlockState ? false : resolveServiceStringEntry(iBlockAccess, blockState, blockPos, blockState2, count11) == serviceStringEntry3;
         }
      } else if (auxiliaryPngManager.count13 == 3) {
         return blockState2 == null ? false : (blockState2 == iBlockState ? false : blockState2.getBlock().getMaterial() == blockState.getBlock().getMaterial());
      } else if (!(blockState2 instanceof IBlockStateAdapter)) {
         return false;
      } else {
         IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState2;
         Block block = iBlockStateAdapter.getBlock();
         int countAsIndex = iBlockStateAdapter.getCountAsIndex();
         return block == blockState.getBlock() && countAsIndex == count12;
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, int count12, ServiceStringEntry serviceStringEntry3, int count13
   ) {
      ServiceStringEntry[] serviceStringEntryArray = auxiliaryPngManager.serviceStringEntryArray;
      ServiceStringEntry serviceStringEntry4 = resolveServiceStringEntryByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13);
      if (serviceStringEntry4 != null && serviceStringEntry4 != serviceStringEntry3 && serviceStringEntry4 != serviceStringEntryArray[3]) {
         return serviceStringEntry4;
      } else {
         ServiceStringEntry serviceStringEntry5 = resolveServiceStringEntry4(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13);
         return serviceStringEntry5 == serviceStringEntryArray[0] ? serviceStringEntryArray[4] : (serviceStringEntry5 == serviceStringEntryArray[1] ? serviceStringEntryArray[5] : (serviceStringEntry5 == serviceStringEntryArray[2] ? serviceStringEntryArray[6] : serviceStringEntry5));
      }
   }

   private static AuxiliaryPngManager[][] resolveAuxiliaryPngManagerArray(List list) {
      AuxiliaryPngManager[][] auxiliaryPngManagerMatrix = new AuxiliaryPngManager[list.size()][];

      for (int index = 0; index < list.size(); index++) {
         List list2 = (List)list.get(index);
         if (list2 != null) {
            AuxiliaryPngManager[] auxiliaryPngManagerArray3 = ((AuxiliaryPngManager[])list2.toArray(new AuxiliaryPngManager[list2.size()]));
            auxiliaryPngManagerMatrix[index] = auxiliaryPngManagerArray3;
         }
      }

      return auxiliaryPngManagerMatrix;
   }

   private static ServiceStringEntry resolveServiceStringEntry(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockState blockState,
      BlockPos blockPos,
      int count11,
      int count12,
      ServiceStringEntry serviceStringEntry3,
      int count13,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      int count14 = computeCount(auxiliaryPngManager, iBlockAccess, blockState, blockPos, count11, count12, serviceStringEntry3, count13, baseIBlockStateEntry);
      return auxiliaryPngManager.serviceStringEntryArray[count14];
   }

   private static boolean matchesAuxiliaryPngManager(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, ServiceStringEntry serviceStringEntry3, int count12
   ) {
      IBlockState blockState2 = iBlockAccess.getBlockState(blockPos);
      if (!matchesIBlockState(blockState2)) {
         return false;
      } else {
         if (auxiliaryPngManager.smoothMatcherArray != null) {
            IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState2;
            if (!GeometrySelector.matchesSmoothMatcherArray(iBlockStateAdapter.getCount2(), iBlockStateAdapter.getCountAsIndex(), auxiliaryPngManager.smoothMatcherArray)) {
               return false;
            }
         }

         if (auxiliaryPngManager.serviceStringEntryArray3 != null
            && !BuildManager.matchesTargetByTarget(resolveServiceStringEntry(iBlockAccess, blockState, blockPos, blockState2, count11), auxiliaryPngManager.serviceStringEntryArray3)) {
            return false;
         } else {
            IBlockState blockState3 = iBlockAccess.getBlockState(blockPos.offset(resolveEnumFacing(count11)));
            return blockState3.getBlock().processAction15()
               ? false
               : (count11 == 1 && blockState3.getBlock() == Blocks.block74 ? false : !matchesAuxiliaryPngManager(auxiliaryPngManager, iBlockAccess, blockState, blockPos, blockState2, count11, serviceStringEntry3, count12));
         }
      }
   }

   private static ServiceStringEntry resolveServiceStringEntryByAuxiliaryPngMana(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, int count12, ServiceStringEntry serviceStringEntry3, int count13
   ) {
      boolean flag;
      boolean flag2;
      flag = false;
      flag2 = false;
      searchBlock:
      switch (count11) {
         case 0:
            switch (count12) {
               case 0:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 1:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 2:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 3:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 4:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 5:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               default:
                  break searchBlock;
            }
         case 1:
            switch (count12) {
               case 0:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 1:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 2:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 3:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 4:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
                  break searchBlock;
               case 5:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
               default:
                  break searchBlock;
            }
         case 2:
            switch (count12) {
               case 0:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  break;
               case 1:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
                  break;
               case 2:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
                  break;
               case 3:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
                  break;
               case 4:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
                  break;
               case 5:
                  flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            }
      }

      byte byteValue;
      if (flag) {
         if (flag2) {
            byteValue = 1;
         } else {
            byteValue = 2;
         }
      } else if (flag2) {
         byteValue = 0;
      } else {
         byteValue = 3;
      }

      return auxiliaryPngManager.serviceStringEntryArray[byteValue];
   }

   private static int computeCountByIndex(int count11, int count12) {
      switch ((count12 & 12) >> 2) {
         case 1:
            return 2;
         case 2:
            return 1;
         default:
            return 0;
      }
   }

   private static List resolveList(AuxiliaryPngManager[][] auxiliaryPngManagerMatrix) {
      ArrayList list = new ArrayList();
      if (auxiliaryPngManagerMatrix != null) {
         for (int index = 0; index < auxiliaryPngManagerMatrix.length; index++) {
            AuxiliaryPngManager[] auxiliaryPngManagerArray3 = auxiliaryPngManagerMatrix[index];
            ArrayList list2 = null;
            if (auxiliaryPngManagerArray3 != null) {
               list2 = new ArrayList<>(Arrays.asList(auxiliaryPngManagerArray3));
            }

            list.add(list2);
         }
      }

      return list;
   }

   private static synchronized LocalIntArrayEntry resolveLocalIntArrayEntry(ServiceStringEntry serviceStringEntry3, LocalIntArrayEntry localIntArrayEntry) {
      if (mapArray3 == null) {
         return localIntArrayEntry;
      } else {
         int count11 = serviceStringEntry3.getCount8();
         if (count11 >= 0 && count11 < mapArray3.length) {
            Map entry = mapArray3[count11];
            if (entry == null) {
               entry = new IdentityHashMap(1);
               mapArray3[count11] = (Map)entry;
            }

            LocalIntArrayEntry localIntArrayEntry2 = (LocalIntArrayEntry)entry.get(localIntArrayEntry);
            if (localIntArrayEntry2 == null) {
               localIntArrayEntry2 = resolveLocalIntArrayEntry(localIntArrayEntry, serviceStringEntry3);
               entry.put(localIntArrayEntry, localIntArrayEntry2);
            }

            return localIntArrayEntry2;
         } else {
            return localIntArrayEntry;
         }
      }
   }

   public static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, LocalIntArrayEntry localIntArrayEntry, boolean flag, int count11, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      blockState.getBlock();
      if (!(blockState instanceof IBlockStateAdapter)) {
         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry);
      } else {
         IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState;
         ServiceStringEntry serviceStringEntry3 = localIntArrayEntry.getServiceStringEntry();
         if (auxiliaryPngManagerArray2 != null) {
            int count12 = serviceStringEntry3.getCount8();
            if (count12 >= 0 && count12 < auxiliaryPngManagerArray2.length) {
               AuxiliaryPngManager[] auxiliaryPngManagerArray3 = auxiliaryPngManagerArray2[count12];
               if (auxiliaryPngManagerArray3 != null) {
                  int count13 = computeCount(enumFacing);

                  for (int index = 0; index < auxiliaryPngManagerArray3.length; index++) {
                     AuxiliaryPngManager auxiliaryPngManager = auxiliaryPngManagerArray3[index];
                     if (auxiliaryPngManager != null && auxiliaryPngManager.checkIndex(iBlockStateAdapter.getCount2())) {
                        LocalIntArrayEntry[] localIntArrayEntryArray = resolveLocalIntArrayEntryArray(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, localIntArrayEntry, count11, baseIBlockStateEntry);
                        if (localIntArrayEntryArray != null) {
                           return localIntArrayEntryArray;
                        }
                     }
                  }
               }
            }
         }

         if (auxiliaryPngManagerArray != null && flag) {
            int count14 = baseIBlockStateEntry.getCount();
            if (count14 >= 0 && count14 < auxiliaryPngManagerArray.length) {
               AuxiliaryPngManager[] auxiliaryPngManagerArray4 = auxiliaryPngManagerArray[count14];
               if (auxiliaryPngManagerArray4 != null) {
                  int count15 = computeCount(enumFacing);

                  for (int index2 = 0; index2 < auxiliaryPngManagerArray4.length; index2++) {
                     AuxiliaryPngManager auxiliaryPngManager2 = auxiliaryPngManagerArray4[index2];
                     if (auxiliaryPngManager2 != null && auxiliaryPngManager2.matchesServiceStringEntry(serviceStringEntry3)) {
                        LocalIntArrayEntry[] localIntArrayEntryArray2 = resolveLocalIntArrayEntryArray(auxiliaryPngManager2, iBlockAccess, iBlockStateAdapter, blockPos, count15, localIntArrayEntry, count11, baseIBlockStateEntry);
                        if (localIntArrayEntryArray2 != null) {
                           return localIntArrayEntryArray2;
                        }
                     }
                  }
               }
            }
         }

         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry);
      }
   }

   public static int computeCount(EnumFacing enumFacing) {
      if (enumFacing == null) {
         return -1;
      } else {
         switch (SmoothBuilder.countArray[enumFacing.ordinal()]) {
            case 1:
               return 0;
            case 2:
               return 1;
            case 3:
               return 5;
            case 4:
               return 4;
            case 5:
               return 2;
            case 6:
               return 3;
            default:
               return -1;
         }
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry(AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockStateAdapter iBlockStateAdapter, BlockPos blockPos, int count11) {
      BlockPos blockPos2 = blockPos;
      if (auxiliaryPngManager.serviceStringEntryArray.length == 1) {
         return auxiliaryPngManager.serviceStringEntryArray[0];
      } else {
         int count12 = count11 / auxiliaryPngManager.count35 * auxiliaryPngManager.count35;
         if (auxiliaryPngManager.enabled) {
            BlockPos blockPos3 = blockPos.down();

            for (IBlockState blockState = iBlockAccess.getBlockState(blockPos3); blockState.getBlock() == iBlockStateAdapter.getBlock(); blockState = iBlockAccess.getBlockState(blockPos3)) {
               blockPos2 = blockPos3;
               blockPos3 = blockPos3.down();
               if (blockPos3.getY() < 0) {
                  break;
               }
            }
         }

         int count13 = BuildManager.computeCount(blockPos2, count12) & 2147483647;

         for (int index = 0; index < auxiliaryPngManager.count28; index++) {
            count13 = BuildManager.computeCount(count13);
         }

         int count14 = 0;
         if (auxiliaryPngManager.countArray == null) {
            count14 = count13 % auxiliaryPngManager.serviceStringEntryArray.length;
         } else {
            int count15 = count13 % auxiliaryPngManager.count23;
            int[] countArray = auxiliaryPngManager.countArray3;

            for (int index2 = 0; index2 < countArray.length; index2++) {
               if (count15 < countArray[index2]) {
                  count14 = index2;
                  break;
               }
            }
         }

         return auxiliaryPngManager.serviceStringEntryArray[count14];
      }
   }

   private static LocalIntArrayEntry resolveLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry, ServiceStringEntry serviceStringEntry3) {
      int[] countArray = (int[])localIntArrayEntry.getIndexArray().clone();
      ServiceStringEntry serviceStringEntry4 = localIntArrayEntry.getServiceStringEntry();

      for (int index = 0; index < 4; index++) {
         applyIndexArray(countArray, index, serviceStringEntry4, serviceStringEntry3);
      }

      return new LocalIntArrayEntry(countArray, localIntArrayEntry.getCount(), localIntArrayEntry.getEnumFacing(), serviceStringEntry3);
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      LocalIntArrayEntry[] localIntArrayEntryArray = resolveLocalIntArrayEntryArray(iBlockAccess, blockState, blockPos, enumFacing, localIntArrayEntry, true, 0, baseIBlockStateEntry);
      if (!enabled) {
         return localIntArrayEntryArray;
      } else if (localIntArrayEntryArray.length == 1 && localIntArrayEntryArray[0] == localIntArrayEntry) {
         return localIntArrayEntryArray;
      } else {
         List list = baseIBlockStateEntry.resolveList(localIntArrayEntryArray);

         for (int index = 0; index < list.size(); index++) {
            LocalIntArrayEntry localIntArrayEntry2 = (LocalIntArrayEntry)list.get(index);

            for (int index2 = 0; index2 < 3; index2++) {
               LocalIntArrayEntry[] localIntArrayEntryArray2 = resolveLocalIntArrayEntryArray(iBlockAccess, blockState, blockPos, enumFacing, localIntArrayEntry2, false, index2 + 1, baseIBlockStateEntry);
               if (localIntArrayEntryArray2.length != 1 || localIntArrayEntryArray2[0] == localIntArrayEntry2) {
                  break;
               }

               localIntArrayEntry2 = localIntArrayEntryArray2[0];
            }

            list.set(index, localIntArrayEntry2);
         }

         for (int index3 = 0; index3 < localIntArrayEntryArray.length; index3++) {
            localIntArrayEntryArray[index3] = (LocalIntArrayEntry)list.get(index3);
         }

         return localIntArrayEntryArray;
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(AuxiliaryPngManager auxiliaryPngManager, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry) {
      if (!localIntArrayEntry.isActive()) {
         return null;
      } else {
         ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(auxiliaryPngManager.blockSolidType);

         try {
            ServiceStringEntry serviceStringEntry3 = resolveServiceStringEntry(auxiliaryPngManager);
            if (serviceStringEntry3 != null) {
               managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(serviceStringEntry3, localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
            }
         } finally {
            if (managedListEntry.size() > 0) {
               baseIBlockStateEntry.setEnabled(true);
            }
         }

         return (LocalIntArrayEntry[])null;
      }
   }

   public static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (localIntArrayEntry.getServiceStringEntry() == null) {
         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry);
      } else {
         blockState.getBlock();
         if (matchesIBlockAccess(iBlockAccess, blockState, blockPos, localIntArrayEntry, baseIBlockStateEntry)) {
            LocalIntArrayEntry localIntArrayEntry2 = resolveLocalIntArrayEntry(serviceStringEntry, localIntArrayEntry);
            return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2);
         } else {
            EnumFacing enumFacing = localIntArrayEntry.getEnumFacing();
            return resolveLocalIntArrayEntryArray(iBlockAccess, blockState, blockPos, enumFacing, localIntArrayEntry, baseIBlockStateEntry);
         }
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry4(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, int count12, ServiceStringEntry serviceStringEntry3, int count13
   ) {
      boolean flag = false;
      boolean flag2 = false;
      switch (count11) {
         case 0:
            if (count12 == 1) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            } else if (count12 == 0) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            } else {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            }
            break;
         case 1:
            if (count12 == 3) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            } else if (count12 == 2) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            } else {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            }
            break;
         case 2:
            if (count12 == 5) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            } else if (count12 == 4) {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            } else {
               flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
               flag2 = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
            }
      }

      byte byteValue;
      if (flag) {
         if (flag2) {
            byteValue = 1;
         } else {
            byteValue = 2;
         }
      } else if (flag2) {
         byteValue = 0;
      } else {
         byteValue = 3;
      }

      return auxiliaryPngManager.serviceStringEntryArray[byteValue];
   }

   private static void applyIndexArray(int[] countArray, int count11, ServiceStringEntry serviceStringEntry3, ServiceStringEntry serviceStringEntry4) {
      int count12 = countArray.length / 4 * count11;
      float factor = Float.intBitsToFloat(countArray[count12 + 4]);
      float factor2 = Float.intBitsToFloat(countArray[count12 + 4 + 1]);
      double offset = serviceStringEntry3.computeValue(factor);
      double offset2 = serviceStringEntry3.computeValueByFloat(factor2);
      countArray[count12 + 4] = Float.floatToRawIntBits(serviceStringEntry4.computeFactorByDouble(offset));
      countArray[count12 + 4 + 1] = Float.floatToRawIntBits(serviceStringEntry4.computeFactor(offset2));
   }

   private static int computeCount2(int count11, int count12) {
      switch (count12) {
         case 3:
            return 2;
         case 4:
            return 1;
         default:
            return 0;
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockStateAdapter iBlockStateAdapter,
      BlockPos blockPos,
      int count11,
      LocalIntArrayEntry localIntArrayEntry,
      int count12,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      int count13 = 0;
      int countAsIndex = iBlockStateAdapter.getCountAsIndex();
      int count14 = countAsIndex;
      Block block = iBlockStateAdapter.getBlock();
      if (block instanceof CommonBlockAdapter) {
         count13 = computeCountByIndex(count11, countAsIndex);
         if (auxiliaryPngManager.getCountAsIndex() <= 3) {
            count14 = countAsIndex & 3;
         }
      }

      if (block instanceof BlockQuartzBlock) {
         count13 = computeCount2(count11, countAsIndex);
         if (auxiliaryPngManager.getCountAsIndex() <= 2 && count14 > 2) {
            count14 = 2;
         }
      }

      if (!auxiliaryPngManager.checkIndex(iBlockStateAdapter.getCount2(), count14)) {
         return null;
      } else {
         if (count11 >= 0 && auxiliaryPngManager.count17 != 63) {
            int count15 = count11;
            if (count13 != 0) {
               count15 = computeCount(count11, count13);
            }

            if ((1 << count15 & auxiliaryPngManager.count17) == 0) {
               return null;
            }
         }

         int coordY = blockPos.getY();
         if (auxiliaryPngManager.targetAggregator != null && !auxiliaryPngManager.targetAggregator.checkIndex(coordY)) {
            return null;
         } else {
            if (auxiliaryPngManager.biomeIDIsOutOfBoundsManagerArray != null) {
               BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos);
               if (!auxiliaryPngManager.matchesBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager)) {
                  return null;
               }
            }

            if (auxiliaryPngManager.geometryObserver != null) {
               String text = AngleCounter.resolveText(iBlockAccess, blockPos);
               if (!auxiliaryPngManager.geometryObserver.matchesText(text)) {
                  return null;
               }
            }

            ServiceStringEntry serviceStringEntry3 = localIntArrayEntry.getServiceStringEntry();
            switch (auxiliaryPngManager.count2) {
               case 1:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex, baseIBlockStateEntry), localIntArrayEntry, baseIBlockStateEntry);
               case 2:
                  return resolveLocalIntArrayEntryArray(
                     resolveServiceStringEntryByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex), localIntArrayEntry, baseIBlockStateEntry
                  );
               case 3:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry3(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex), localIntArrayEntry, baseIBlockStateEntry);
               case 4:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count11), localIntArrayEntry, baseIBlockStateEntry);
               case 5:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry(auxiliaryPngManager, blockPos, count11), localIntArrayEntry, baseIBlockStateEntry);
               case 6:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry4(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex), localIntArrayEntry, baseIBlockStateEntry);
               case 7:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry(auxiliaryPngManager), localIntArrayEntry, baseIBlockStateEntry);
               case 8:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex), localIntArrayEntry, baseIBlockStateEntry);
               case 9:
                  return resolveLocalIntArrayEntryArray(resolveServiceStringEntry2(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, serviceStringEntry3, countAsIndex), localIntArrayEntry, baseIBlockStateEntry);
               case 10:
                  if (count12 == 0) {
                     return resolveLocalIntArrayEntryArray2(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, localIntArrayEntry, countAsIndex, baseIBlockStateEntry);
                  }
               default:
                  return null;
               case 11:
                  return resolveLocalIntArrayEntryArrayByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, localIntArrayEntry, countAsIndex, baseIBlockStateEntry);
               case 12:
                  return resolveLocalIntArrayEntryArray(auxiliaryPngManager, localIntArrayEntry, baseIBlockStateEntry);
               case 13:
                  return resolveLocalIntArrayEntryArray(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count11, localIntArrayEntry, baseIBlockStateEntry);
               case 14:
                  return resolveLocalIntArrayEntryArray(auxiliaryPngManager, blockPos, count11, localIntArrayEntry, baseIBlockStateEntry);
               case 15:
                  return resolveLocalIntArrayEntryArray(auxiliaryPngManager, iBlockAccess, iBlockStateAdapter, blockPos, count13, count11, localIntArrayEntry, countAsIndex, baseIBlockStateEntry);
            }
         }
      }
   }

   private static void applyListenerAdapterByListenerAdapter2(ListenerAdapter27 listenerAdapter) {
   }

   private static synchronized LocalIntArrayEntry resolveLocalIntArrayEntry(ServiceStringEntry serviceStringEntry3, LocalIntArrayEntry localIntArrayEntry, int count11) {
      if (mapArray == null) {
         return null;
      } else if (serviceStringEntry3 == null) {
         return null;
      } else {
         int count12 = serviceStringEntry3.getCount8();
         if (count12 >= 0 && count12 < mapArray.length) {
            Map entry = mapArray[count12];
            if (entry == null) {
               entry = new EnumMap<>(EnumFacing.class);
               mapArray[count12] = (Map)entry;
            }

            EnumFacing enumFacing = localIntArrayEntry.getEnumFacing();
            LocalIntArrayEntry localIntArrayEntry2 = (LocalIntArrayEntry)entry.get(enumFacing);
            if (localIntArrayEntry2 == null) {
               localIntArrayEntry2 = NormalManager.resolveLocalIntArrayEntry(enumFacing, serviceStringEntry3, count11);
               entry.put(enumFacing, localIntArrayEntry2);
            }

            return localIntArrayEntry2;
         } else {
            return null;
         }
      }
   }

   private static boolean matchesAuxiliaryPngManagerByAuxiliaryPngMana(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, ServiceStringEntry serviceStringEntry3, int count12
   ) {
      IBlockState blockState2 = iBlockAccess.getBlockState(blockPos);
      return matchesAuxiliaryPngManager(auxiliaryPngManager, iBlockAccess, blockState, blockPos, blockState2, count11, serviceStringEntry3, count12);
   }

   private static void applyAuxiliaryPngManager(AuxiliaryPngManager auxiliaryPngManager, List list) {
      if (auxiliaryPngManager.smoothMatcherArray2 != null) {
         for (int index = 0; index < auxiliaryPngManager.smoothMatcherArray2.length; index++) {
            int count11 = auxiliaryPngManager.smoothMatcherArray2[index].getCount();
            if (count11 < 0) {
               BuildManager.applyText3("Invalid block ID: " + count11);
            } else {
               applyAuxiliaryPngManager(auxiliaryPngManager, list, count11);
            }
         }
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry(AuxiliaryPngManager auxiliaryPngManager) {
      return auxiliaryPngManager.serviceStringEntryArray[0];
   }

   private static int computeCount(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockState blockState,
      BlockPos blockPos,
      int count11,
      int count12,
      ServiceStringEntry serviceStringEntry3,
      int count13,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      boolean[] flagArrayAsFlagArray = baseIBlockStateEntry.getFlagArrayAsFlagArray();
      switch (count12) {
         case 0:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPos2 = blockPos.down();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos2.getBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos2.getBlockPos3(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos2.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos2.getBlockPos2(), count12, serviceStringEntry3, count13);
            }
            break;
         case 1:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPos3 = blockPos.up();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos3.getBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos3.getBlockPos3(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos3.getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos3.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            }
            break;
         case 2:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPosAsBlockPos = blockPos.getBlockPosAsBlockPos();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos.getBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos.down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos.up(), count12, serviceStringEntry3, count13);
            }

            if (count11 == 1) {
               applyFlagArray(0, 1, flagArrayAsFlagArray);
               applyFlagArray(2, 3, flagArrayAsFlagArray);
            }
            break;
         case 3:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPos4 = blockPos.getBlockPos2();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos4.getBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos4.getBlockPos3(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos4.down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos4.up(), count12, serviceStringEntry3, count13);
            }
            break;
         case 4:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPos5 = blockPos.getBlockPos();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos5.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos5.getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos5.down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos5.up(), count12, serviceStringEntry3, count13);
            }
            break;
         case 5:
            flagArrayAsFlagArray[0] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[1] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[2] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down(), count12, serviceStringEntry3, count13);
            flagArrayAsFlagArray[3] = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            if (auxiliaryPngManager.enabled2) {
               BlockPos blockPos6 = blockPos.getBlockPos3();
               flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos6.getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos6.getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos6.down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] && !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos6.up(), count12, serviceStringEntry3, count13);
            }

            if (count11 == 2) {
               applyFlagArray(0, 1, flagArrayAsFlagArray);
               applyFlagArray(2, 3, flagArrayAsFlagArray);
            }
      }

      byte byteValue = 0;
      if (flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 3;
      } else if (!flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 1;
      } else if (!flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 12;
      } else if (!flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 36;
      } else if (flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 2;
      } else if (!flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 24;
      } else if (flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 15;
      } else if (flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 39;
      } else if (!flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 13;
      } else if (!flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 37;
      } else if (!flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 25;
      } else if (flagArrayAsFlagArray[0] & !flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 27;
      } else if (flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & !flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 38;
      } else if (flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & !flagArrayAsFlagArray[3]) {
         byteValue = 14;
      } else if (flagArrayAsFlagArray[0] & flagArrayAsFlagArray[1] & flagArrayAsFlagArray[2] & flagArrayAsFlagArray[3]) {
         byteValue = 26;
      }

      if (byteValue == 0) {
         return byteValue;
      } else if (!BuildManager.isActive24()) {
         return byteValue;
      } else {
         switch (count12) {
            case 0:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().getBlockPos2(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPos7 = blockPos.down();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0]
                     || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos7.getBlockPos3().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1]
                     || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos7.getBlockPos().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos7.getBlockPos3().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos7.getBlockPos().getBlockPos2(), count12, serviceStringEntry3, count13);
               }
               break;
            case 1:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPos8 = blockPos.up();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos8.getBlockPos3().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos8.getBlockPos().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2]
                     || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos8.getBlockPos3().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3]
                     || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos8.getBlockPos().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               }
               break;
            case 2:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().up(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().up(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPosAsBlockPos2 = blockPos.getBlockPosAsBlockPos();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos2.getBlockPos().down(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos2.getBlockPos3().down(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos2.getBlockPos().up(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPosAsBlockPos2.getBlockPos3().up(), count12, serviceStringEntry3, count13);
               }

               if (count11 == 1) {
                  applyFlagArray(0, 3, flagArrayAsFlagArray);
                  applyFlagArray(1, 2, flagArrayAsFlagArray);
               }
               break;
            case 3:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().down(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3().up(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos().up(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPos9 = blockPos.getBlockPos2();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos9.getBlockPos3().down(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos9.getBlockPos().down(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos9.getBlockPos3().up(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos9.getBlockPos().up(), count12, serviceStringEntry3, count13);
               }
               break;
            case 4:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPos10 = blockPos.getBlockPos();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos10.down().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos10.down().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos10.up().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos10.up().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               }
               break;
            case 5:
               flagArrayAsFlagArray[0] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[1] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.down().getBlockPos2(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[2] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
               flagArrayAsFlagArray[3] = !matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up().getBlockPos2(), count12, serviceStringEntry3, count13);
               if (auxiliaryPngManager.enabled2) {
                  BlockPos blockPos11 = blockPos.getBlockPos3();
                  flagArrayAsFlagArray[0] = flagArrayAsFlagArray[0] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos11.down().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[1] = flagArrayAsFlagArray[1] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos11.down().getBlockPos2(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[2] = flagArrayAsFlagArray[2] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos11.up().getBlockPosAsBlockPos(), count12, serviceStringEntry3, count13);
                  flagArrayAsFlagArray[3] = flagArrayAsFlagArray[3] || matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos11.up().getBlockPos2(), count12, serviceStringEntry3, count13);
               }

               if (count11 == 2) {
                  applyFlagArray(0, 3, flagArrayAsFlagArray);
                  applyFlagArray(1, 2, flagArrayAsFlagArray);
               }
         }

         if (byteValue == 13 && flagArrayAsFlagArray[0]) {
            byteValue = 4;
         } else if (byteValue == 15 && flagArrayAsFlagArray[1]) {
            byteValue = 5;
         } else if (byteValue == 37 && flagArrayAsFlagArray[2]) {
            byteValue = 16;
         } else if (byteValue == 39 && flagArrayAsFlagArray[3]) {
            byteValue = 17;
         } else if (byteValue == 14 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1]) {
            byteValue = 7;
         } else if (byteValue == 25 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[2]) {
            byteValue = 6;
         } else if (byteValue == 27 && flagArrayAsFlagArray[3] && flagArrayAsFlagArray[1]) {
            byteValue = 19;
         } else if (byteValue == 38 && flagArrayAsFlagArray[3] && flagArrayAsFlagArray[2]) {
            byteValue = 18;
         } else if (byteValue == 14 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1]) {
            byteValue = 31;
         } else if (byteValue == 25 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[2]) {
            byteValue = 30;
         } else if (byteValue == 27 && !flagArrayAsFlagArray[3] && flagArrayAsFlagArray[1]) {
            byteValue = 41;
         } else if (byteValue == 38 && flagArrayAsFlagArray[3] && !flagArrayAsFlagArray[2]) {
            byteValue = 40;
         } else if (byteValue == 14 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1]) {
            byteValue = 29;
         } else if (byteValue == 25 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[2]) {
            byteValue = 28;
         } else if (byteValue == 27 && flagArrayAsFlagArray[3] && !flagArrayAsFlagArray[1]) {
            byteValue = 43;
         } else if (byteValue == 38 && !flagArrayAsFlagArray[3] && flagArrayAsFlagArray[2]) {
            byteValue = 42;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 46;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 9;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 21;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 8;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 20;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 11;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 22;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 23;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 10;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 34;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 35;
         } else if (byteValue == 26 && flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 32;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 33;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && !flagArrayAsFlagArray[3]) {
            byteValue = 44;
         } else if (byteValue == 26 && !flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
            byteValue = 45;
         }

         return byteValue;
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry3(
      AuxiliaryPngManager auxiliaryPngManager, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, int count11, int count12, ServiceStringEntry serviceStringEntry3, int count13
   ) {
      boolean flag = false;
      switch (count11) {
         case 0:
            if (count12 == 1 || count12 == 0) {
               return null;
            }

            flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.up(), count12, serviceStringEntry3, count13);
            break;
         case 1:
            if (count12 == 3 || count12 == 2) {
               return null;
            }

            flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos2(), count12, serviceStringEntry3, count13);
            break;
         case 2:
            if (count12 == 5 || count12 == 4) {
               return null;
            }

            flag = matchesAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, iBlockAccess, blockState, blockPos.getBlockPos3(), count12, serviceStringEntry3, count13);
      }

      return flag ? auxiliaryPngManager.serviceStringEntryArray[0] : null;
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      AuxiliaryPngManager auxiliaryPngManager, BlockPos blockPos, int count11, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (!localIntArrayEntry.isActive()) {
         return null;
      } else {
         ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(auxiliaryPngManager.blockSolidType);

         try {
            ServiceStringEntry serviceStringEntry3 = resolveServiceStringEntry(auxiliaryPngManager, blockPos, count11);
            if (serviceStringEntry3 != null) {
               managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(serviceStringEntry3, localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
            }
         } finally {
            if (managedListEntry.size() > 0) {
               baseIBlockStateEntry.setEnabled(true);
            }
         }

         return (LocalIntArrayEntry[])null;
      }
   }

   private static void applyAuxiliaryPngManagerByAuxiliaryPngMana(AuxiliaryPngManager auxiliaryPngManager, List list) {
      if (auxiliaryPngManager.serviceStringEntryArray2 != null) {
         for (int index = 0; index < auxiliaryPngManager.serviceStringEntryArray2.length; index++) {
            ServiceStringEntry serviceStringEntry3 = auxiliaryPngManager.serviceStringEntryArray2[index];
            if (!(serviceStringEntry3 instanceof ServiceStringEntry)) {
               BuildManager.applyText3("TextureAtlasSprite is not TextureAtlasSprite: " + serviceStringEntry3 + ", name: " + serviceStringEntry3.getLabelText3());
            } else {
               int count11 = serviceStringEntry3.getCount8();
               if (count11 < 0) {
                  BuildManager.applyText3("Invalid tile ID: " + count11 + ", icon: " + serviceStringEntry3.getLabelText3());
               } else {
                  applyAuxiliaryPngManager(auxiliaryPngManager, list, count11);
               }
            }
         }
      }
   }

   private static boolean isActive() {
      ArrayList list = new ArrayList();

      for (int index = 0; index < auxiliaryPngManagerArray2.length; index++) {
         AuxiliaryPngManager[] auxiliaryPngManagerArray3 = auxiliaryPngManagerArray2[index];
         if (auxiliaryPngManagerArray3 != null) {
            list.addAll(Arrays.asList(auxiliaryPngManagerArray3));
         }
      }

      for (int index2 = 0; index2 < auxiliaryPngManagerArray.length; index2++) {
         AuxiliaryPngManager[] auxiliaryPngManagerArray4 = auxiliaryPngManagerArray[index2];
         if (auxiliaryPngManagerArray4 != null) {
            list.addAll(Arrays.asList(auxiliaryPngManagerArray4));
         }
      }

      AuxiliaryPngManager[] auxiliaryPngManagerArray5 = ((AuxiliaryPngManager[])list.toArray(new AuxiliaryPngManager[list.size()]));
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();

      for (int index3 = 0; index3 < auxiliaryPngManagerArray5.length; index3++) {
         AuxiliaryPngManager auxiliaryPngManager = auxiliaryPngManagerArray5[index3];
         if (auxiliaryPngManager.serviceStringEntryArray2 != null) {
            set.addAll(Arrays.asList(auxiliaryPngManager.serviceStringEntryArray2));
         }

         if (auxiliaryPngManager.serviceStringEntryArray != null) {
            set2.addAll(Arrays.asList(auxiliaryPngManager.serviceStringEntryArray));
         }
      }

      set.retainAll(set2);
      return !set.isEmpty();
   }

   private static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry) {
      Block block = blockState.getBlock();
      if (block instanceof BlockGlassPane && localIntArrayEntry.getServiceStringEntry().getLabelText3().startsWith("minecraft:blocks/glass_pane_top")) {
         return iBlockAccess.getBlockState(blockPos.offset(localIntArrayEntry.getEnumFacing())) == blockState;
      } else {
         if (block instanceof BlockGlassPane) {
            EnumFacing enumFacing = localIntArrayEntry.getEnumFacing();
            if (enumFacing != EnumFacing.UP && enumFacing != EnumFacing.DOWN) {
               return false;
            }

            if (!localIntArrayEntry.isActiveAsFlag()) {
               return false;
            }

            BlockPos blockPos2 = blockPos.offset(localIntArrayEntry.getEnumFacing());
            IBlockState blockState2 = iBlockAccess.getBlockState(blockPos2);
            if (blockState2.getBlock() != block) {
               return false;
            }

            if (block == Blocks.blockStainedGlassPane
               && blockState2.getComparable(BlockStainedGlassPane.immutableSetEntry) != blockState.getComparable(BlockStainedGlassPane.immutableSetEntry)) {
               return false;
            }

            IBlockState blockState3 = blockState2.getBlock().processAction(blockState2, iBlockAccess, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
            double factor = (double)localIntArrayEntry.getFactor();
            if (factor < 0.4) {
               if ((Boolean)blockState3.getComparable(BlockGlassPane.internalImmutableSetEntry3)) {
                  return true;
               }
            } else if (factor > 0.6) {
               if ((Boolean)blockState3.getComparable(BlockGlassPane.internalImmutableSetEntry2)) {
                  return true;
               }
            } else {
               double valueAsDouble = localIntArrayEntry.getValueAsDouble();
               if (valueAsDouble < 0.4) {
                  if ((Boolean)blockState3.getComparable(BlockGlassPane.internalImmutableSetEntry)) {
                     return true;
                  }
               } else {
                  if (valueAsDouble <= 0.6) {
                     return true;
                  }

                  if ((Boolean)blockState3.getComparable(BlockGlassPane.internalImmutableSetEntry4)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArrayByAuxiliaryPngMana(
      AuxiliaryPngManager auxiliaryPngManager,
      IBlockAccess iBlockAccess,
      IBlockState blockState,
      BlockPos blockPos,
      int count11,
      int count12,
      LocalIntArrayEntry localIntArrayEntry,
      int count13,
      BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      if (!localIntArrayEntry.isActive()) {
         return null;
      } else {
         ServiceStringEntry serviceStringEntry3 = localIntArrayEntry.getServiceStringEntry();
         MovementTimeoutProperty[] movementTimeoutPropertyArray17 = resolveMovementTimeoutPropertyArrayByIndex(count12, count11);
         boolean[] flagArrayAsFlagArray = baseIBlockStateEntry.getFlagArrayAsFlagArray();

         for (int index = 0; index < 4; index++) {
            flagArrayAsFlagArray[index] = matchesAuxiliaryPngManager(auxiliaryPngManager, iBlockAccess, blockState, movementTimeoutPropertyArray17[index].resolveBlockPos(blockPos), count12, serviceStringEntry3, count13);
         }

         ManagedListEntry managedListEntry = baseIBlockStateEntry.resolveManagedListEntry(auxiliaryPngManager.blockSolidType);

         try {
            if (!flagArrayAsFlagArray[0] || !flagArrayAsFlagArray[1] || !flagArrayAsFlagArray[2] || !flagArrayAsFlagArray[3]) {
               if (flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[5], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[0] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[6], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2] && flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[12], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[0] && flagArrayAsFlagArray[1] && flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[13], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  return (LocalIntArrayEntry[])null;
               }

               MovementTimeoutProperty[] movementTimeoutPropertyArray18 = resolveMovementTimeoutPropertyArray(count12, count11);
               boolean[] flagArray = baseIBlockStateEntry.getFlagArray2();

               for (int index2 = 0; index2 < 4; index2++) {
                  flagArray[index2] = matchesAuxiliaryPngManager(auxiliaryPngManager, iBlockAccess, blockState, movementTimeoutPropertyArray18[index2].resolveBlockPos(blockPos), count12, serviceStringEntry3, count13);
               }

               if (flagArrayAsFlagArray[1] && flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[3], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  if (flagArray[3]) {
                     managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[16], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  }

                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[0] && flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[4], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  if (flagArray[2]) {
                     managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[14], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  }

                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[1] && flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[10], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  if (flagArray[1]) {
                     managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[2], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  }

                  return (LocalIntArrayEntry[])null;
               }

               if (flagArrayAsFlagArray[0] && flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[11], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  if (flagArray[0]) {
                     managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[0], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
                  }

                  return (LocalIntArrayEntry[])null;
               }

               boolean[] flagArray2 = baseIBlockStateEntry.getFlagArray();

               for (int index3 = 0; index3 < 4; index3++) {
                  flagArray2[index3] = matchesAuxiliaryPngManager2(auxiliaryPngManager, iBlockAccess, blockState, movementTimeoutPropertyArray17[index3].resolveBlockPos(blockPos), count12, serviceStringEntry3, count13);
               }

               if (flagArrayAsFlagArray[0]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[9], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArrayAsFlagArray[1]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[7], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[1], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[15], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArray[0] && (flagArray2[1] || flagArray2[2]) && !flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[0], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArray[1] && (flagArray2[0] || flagArray2[2]) && !flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[2]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[2], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArray[2] && (flagArray2[1] || flagArray2[3]) && !flagArrayAsFlagArray[1] && !flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[14], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               if (flagArray[3] && (flagArray2[0] || flagArray2[3]) && !flagArrayAsFlagArray[0] && !flagArrayAsFlagArray[3]) {
                  managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[16], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
               }

               return (LocalIntArrayEntry[])null;
            }

            managedListEntry.applyLocalIntArrayEntry(resolveLocalIntArrayEntry(auxiliaryPngManager.serviceStringEntryArray[8], localIntArrayEntry, auxiliaryPngManager.count32), auxiliaryPngManager.iBlockState);
         } finally {
            if (managedListEntry.size() > 0) {
               baseIBlockStateEntry.setEnabled(true);
            }
         }

         return (LocalIntArrayEntry[])null;
      }
   }

   public static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(ServiceStringEntry serviceStringEntry3, LocalIntArrayEntry localIntArrayEntry, BaseIBlockStateEntry baseIBlockStateEntry) {
      if (serviceStringEntry3 == null) {
         return null;
      } else if (serviceStringEntry3 == serviceStringEntry2) {
         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry);
      } else {
         LocalIntArrayEntry localIntArrayEntry2 = resolveLocalIntArrayEntry(serviceStringEntry3, localIntArrayEntry);
         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2);
      }
   }

   private static int computeCount(int count11, int count12) {
      switch (count12) {
         case 0:
            return count11;
         case 1:
            switch (count11) {
               case 0:
                  return 2;
               case 1:
                  return 3;
               case 2:
                  return 1;
               case 3:
                  return 0;
               default:
                  return count11;
            }
         case 2:
            switch (count11) {
               case 0:
                  return 4;
               case 1:
                  return 5;
               case 2:
               case 3:
               default:
                  return count11;
               case 4:
                  return 1;
               case 5:
                  return 0;
            }
         default:
            return count11;
      }
   }

   public static void applyListenerAdapter27(ListenerAdapter27 listenerAdapter, PreUpdateProcessor preUpdateProcessor) {
      String[] textArray = AssetsMinecraftManager.resolveTextArray(preUpdateProcessor, "mcpatcher/ctm/", ".properties", getTextArray());
      Arrays.sort((Object[])textArray);
      List list = resolveList(auxiliaryPngManagerArray2);
      List list2 = resolveList(auxiliaryPngManagerArray);

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         BuildManager.applyText4("ConnectedTextures: " + text);

         try {
            ResourceLocation location = new ResourceLocation(text);
            InputStream inputStream = preUpdateProcessor.resolveInputStream(location);
            if (inputStream == null) {
               BuildManager.applyText3("ConnectedTextures file not found: " + text);
            } else {
               PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
               propertiesAdapter.load(inputStream);
               inputStream.close();
               AuxiliaryPngManager auxiliaryPngManager = new AuxiliaryPngManager(propertiesAdapter, text);
               if (auxiliaryPngManager.matchesText10(text)) {
                  auxiliaryPngManager.applyListenerAdapter27(listenerAdapter);
                  applyAuxiliaryPngManagerByAuxiliaryPngMana(auxiliaryPngManager, list);
                  applyAuxiliaryPngManager(auxiliaryPngManager, list2);
               }
            }
         } catch (FileNotFoundException fileNotFoundException) {
            BuildManager.applyText3("ConnectedTextures file not found: " + text);
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }

      auxiliaryPngManagerArray = resolveAuxiliaryPngManagerArray(list2);
      auxiliaryPngManagerArray2 = resolveAuxiliaryPngManagerArray(list);
      enabled = isActive();
      BuildManager.applyText4("Multipass connected textures: " + enabled);
   }

   private static MovementTimeoutProperty[] resolveMovementTimeoutPropertyArrayByIndex(int count11, int count12) {
      switch (count11) {
         case 0:
            return movementTimeoutPropertyArray11;
         case 1:
            return movementTimeoutPropertyArray15;
         case 2:
            if (count12 == 1) {
               return movementTimeoutPropertyArray8;
            }

            return movementTimeoutPropertyArray;
         case 3:
            return movementTimeoutPropertyArray10;
         case 4:
            return movementTimeoutPropertyArray14;
         case 5:
            if (count12 == 2) {
               return movementTimeoutPropertyArray7;
            }

            return movementTimeoutPropertyArray16;
         default:
            throw new IllegalArgumentException("Unknown side: " + count11);
      }
   }

   public static Map[][] getMapArray2() {
      return mapArray2;
   }
}
