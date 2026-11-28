package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
public class StructureOceanMonumentPieces {

   public static void registerOceanMonumentPieces() {
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.MonumentBuilding.class, "OMB");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.MonumentCoreRoom.class, "OMCR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleXRoom.class, "OMDXR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleXYRoom.class, "OMDXYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleYRoom.class, "OMDYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleYZRoom.class, "OMDYZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleZRoom.class, "OMDZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.EntryRoom.class, "OMEntry");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.Penthouse.class, "OMPenthouse");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.SimpleRoom.class, "OMSimple");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.SimpleTopRoom.class, "OMSimpleT");
   }

   public static abstract class Piece extends StructureComponent {
      public static final IBlockState ROUGH_PRISMARINE = Blocks.prismarine.getStateFromMeta(BlockPrismarine.ROUGH_META);
      public static final IBlockState BRICKS_PRISMARINE = Blocks.prismarine.getStateFromMeta(BlockPrismarine.BRICKS_META);
      public static final IBlockState DARK_PRISMARINE = Blocks.prismarine.getStateFromMeta(BlockPrismarine.DARK_META);
      public static final IBlockState DOT_DECO_DATA = BRICKS_PRISMARINE;
      public static final IBlockState SEA_LANTERN = Blocks.sea_lantern.getDefaultState();
      public static final IBlockState WATER = Blocks.water.getDefaultState();
      public static final int GRIDROOM_SOURCE_INDEX = getRoomIndex(2, 0, 0);
      public static final int GRIDROOM_TOP_CONNECT_INDEX = getRoomIndex(2, 2, 0);
      public static final int GRIDROOM_LEFTWING_CONNECT_INDEX = getRoomIndex(0, 1, 0);
      public static final int GRIDROOM_RIGHTWING_CONNECT_INDEX = getRoomIndex(4, 1, 0);
      public StructureOceanMonumentPieces.RoomDefinition roomDefinition;

      public Piece(int n) {
         super(n);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
      }

      public Piece() {
         super(0);
      }

      public void generateDefaultFloor(World world, StructureBoundingBox structureBoundingBox, int n, int n2, boolean flag) {
         if (flag) {
            this.fillWithBlocks(world, structureBoundingBox, n + 0, 0, n2 + 0, n + 2, 0, n2 + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 5, 0, n2 + 0, n + 8 - 1, 0, n2 + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 3, 0, n2 + 0, n + 4, 0, n2 + 2, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 3, 0, n2 + 5, n + 4, 0, n2 + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 3, 0, n2 + 2, n + 4, 0, n2 + 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 3, 0, n2 + 5, n + 4, 0, n2 + 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 2, 0, n2 + 3, n + 2, 0, n2 + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 5, 0, n2 + 3, n + 5, 0, n2 + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         } else {
            this.fillWithBlocks(world, structureBoundingBox, n + 0, 0, n2 + 0, n + 8 - 1, 0, n2 + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }
      }

      public boolean spawnElder(World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3) {
         int xWithOffset = this.getXWithOffset(n, n3);
         int yWithOffset = this.getYWithOffset(n2);
         int zWithOffset = this.getZWithOffset(n, n3);
         if (structureBoundingBox.isVecInside(new BlockPos(xWithOffset, yWithOffset, zWithOffset))) {
            EntityGuardian entityGuardian = new EntityGuardian(world);
            entityGuardian.setElder(true);
            entityGuardian.heal(entityGuardian.getMaxHealth());
            entityGuardian.setLocationAndAngles((double)xWithOffset + 0.5, (double)yWithOffset, (double)zWithOffset + 0.5, 0.0F, 0.0F);
            entityGuardian.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityGuardian)), (IEntityLivingData)null);
            world.spawnEntityInWorld(entityGuardian);
            return true;
         } else {
            return false;
         }
      }

      public Piece(EnumFacing facing, StructureBoundingBox structureBoundingBox) {
         super(1);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public Piece(int n, EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, int n2, int n3, int n4) {
         super(n);
         this.coordBaseMode = facing;
         this.roomDefinition = roomDefinition2;
         int index = roomDefinition2.index;
         int n5 = index % 5;
         int n6 = index / 5 % 5;
         int n7 = index / 25;
         if (facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH) {
            this.boundingBox = new StructureBoundingBox(0, 0, 0, n4 * 8 - 1, n3 * 4 - 1, n2 * 8 - 1);
         } else {
            this.boundingBox = new StructureBoundingBox(0, 0, 0, n2 * 8 - 1, n3 * 4 - 1, n4 * 8 - 1);
         }

         switch (facing) {
            case NORTH:
               this.boundingBox.offset(n5 * 8, n7 * 4, -(n6 + n4) * 8 + 1);
               break;
            case SOUTH:
               this.boundingBox.offset(n5 * 8, n7 * 4, n6 * 8);
               break;
            case WEST:
               this.boundingBox.offset(-(n6 + n4) * 8 + 1, n7 * 4, n5 * 8);
               break;
            default:
               this.boundingBox.offset(n6 * 8, n7 * 4, n5 * 8);
         }
      }

      public void generateWaterBox(World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6, boolean flag) {
         for (int i = n2; i <= n5; i++) {
            for (int j = n; j <= n4; j++) {
               for (int k = n3; k <= n6; k++) {
                  if (!flag || this.getBlockStateFromPos(world, j, i, k, structureBoundingBox).getBlock().getMaterial() != Material.air) {
                     if (this.getYWithOffset(i) >= world.getSeaLevel()) {
                        this.setBlockState(world, Blocks.air.getDefaultState(), j, i, k, structureBoundingBox);
                     } else {
                        this.setBlockState(world, WATER, j, i, k, structureBoundingBox);
                     }
                  }
               }
            }
         }
      }

      public static final int getRoomIndex(int n, int n2, int n3) {
         return n2 * 25 + n3 * 5 + n;
      }

      public boolean doesChunkIntersect(StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4) {
         int xWithOffset = this.getXWithOffset(n, n2);
         int zWithOffset = this.getZWithOffset(n, n2);
         int xWithOffset2 = this.getXWithOffset(n3, n4);
         int zWithOffset2 = this.getZWithOffset(n3, n4);
         return structureBoundingBox.intersectsWith(Math.min(xWithOffset, xWithOffset2), Math.min(zWithOffset, zWithOffset2), Math.max(xWithOffset, xWithOffset2), Math.max(zWithOffset, zWithOffset2));
      }

      public void generateBoxOnFillOnly(World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6, IBlockState state) {
         for (int i = n2; i <= n5; i++) {
            for (int j = n; j <= n4; j++) {
               for (int k = n3; k <= n6; k++) {
                  if (this.getBlockStateFromPos(world, j, i, k, structureBoundingBox) == WATER) {
                     this.setBlockState(world, state, j, i, k, structureBoundingBox);
                  }
               }
            }
         }
      }
   }

   public static class WingRoom extends StructureOceanMonumentPieces.Piece {
      private int mainDesign;

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.mainDesign == 0) {
            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 7, 0, 6, 15, 0, 16, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 0, 6, 6, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 16, 0, 6, 16, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 7, 7, 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 15, 1, 7, 15, 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 6, 9, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 13, 1, 6, 15, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 8, 1, 7, 9, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 13, 1, 7, 14, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 9, 0, 5, 13, 0, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 10, 0, 7, 12, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 8, 0, 10, 8, 0, 12, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 14, 0, 10, 14, 0, 12, DARK_PRISMARINE, DARK_PRISMARINE, false);

            for (int j = 18; j >= 7; j -= 3) {
               this.setBlockState(world, SEA_LANTERN, 6, 3, j, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, 16, 3, j, structureBoundingBox);
            }

            this.setBlockState(world, SEA_LANTERN, 10, 0, 10, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 12, 0, 10, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 10, 0, 12, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 12, 0, 12, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 8, 3, 6, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 14, 3, 6, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 4, 2, 4, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 4, 1, 4, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 4, 0, 4, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 18, 2, 4, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 18, 1, 4, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 18, 0, 4, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 4, 2, 18, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 4, 1, 18, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 4, 0, 18, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 18, 2, 18, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 18, 1, 18, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 18, 0, 18, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 9, 7, 20, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 13, 7, 20, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 6, 0, 21, 7, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 15, 0, 21, 16, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.spawnElder(world, structureBoundingBox, 11, 2, 16);
         } else if (this.mainDesign == 1) {
            this.fillWithBlocks(world, structureBoundingBox, 9, 3, 18, 13, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 9, 0, 18, 9, 2, 18, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 13, 0, 18, 13, 2, 18, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            byte b = 9;
            byte b2 = 20;
            byte b3 = 5;

            for (int k = 0; k < 2; k++) {
               this.setBlockState(world, BRICKS_PRISMARINE, b, b3 + 1, b2, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, b, b3, b2, structureBoundingBox);
               this.setBlockState(world, BRICKS_PRISMARINE, b, b3 - 1, b2, structureBoundingBox);
               b = 13;
            }

            this.fillWithBlocks(world, structureBoundingBox, 7, 3, 7, 15, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            b = 10;

            for (int m = 0; m < 2; m++) {
               this.fillWithBlocks(world, structureBoundingBox, b, 0, 10, b, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b, 0, 12, b, 6, 12, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.setBlockState(world, SEA_LANTERN, b, 0, 10, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, b, 0, 12, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, b, 4, 10, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, b, 4, 12, structureBoundingBox);
               b = 12;
            }

            b = 8;

            for (int idx = 0; idx < 2; idx++) {
               this.fillWithBlocks(world, structureBoundingBox, b, 0, 7, b, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b, 0, 14, b, 2, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               b = 14;
            }

            this.fillWithBlocks(world, structureBoundingBox, 8, 3, 8, 8, 3, 13, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 14, 3, 8, 14, 3, 13, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.spawnElder(world, structureBoundingBox, 11, 5, 13);
         }

         return true;
      }

      public WingRoom(EnumFacing facing, StructureBoundingBox structureBoundingBox, int n) {
         super(facing, structureBoundingBox);
         this.mainDesign = n & 1;
      }

      public WingRoom() {
      }
   }

   public static class EntryRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 2, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 3, 0, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 1, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 0, 7, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 1, 0, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 7, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 2, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, false);
         }

         if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 1, 2, 4, false);
         }

         if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 6, 1, 3, 7, 2, 4, false);
         }

         return true;
      }

      public EntryRoom() {
      }

      public EntryRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2) {
         super(1, facing, roomDefinition2, 1, 1, 1);
      }
   }

   public static class Penthouse extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 2, -1, 2, 11, -1, 11, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, -1, 0, 1, -1, 11, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 12, -1, 0, 13, -1, 11, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, -1, 0, 11, -1, 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, -1, 12, 11, -1, 13, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 13, 0, 0, 13, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 12, 0, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 13, 12, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

         for (int i = 2; i <= 11; i += 3) {
            this.setBlockState(world, SEA_LANTERN, 0, 0, i, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 13, 0, i, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, i, 0, 0, structureBoundingBox);
         }

         this.fillWithBlocks(world, structureBoundingBox, 2, 0, 3, 4, 0, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 0, 3, 11, 0, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 9, 9, 0, 11, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(world, BRICKS_PRISMARINE, 5, 0, 8, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 8, 0, 8, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 10, 0, 10, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 3, 0, 10, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 3, 0, 3, 3, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 0, 3, 10, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 0, 10, 7, 0, 10, DARK_PRISMARINE, DARK_PRISMARINE, false);
         byte b = 3;

         for (int j = 0; j < 2; j++) {
            for (int k = 2; k <= 8; k += 3) {
               this.fillWithBlocks(world, structureBoundingBox, b, 0, k, b, 2, k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            b = 10;
         }

         this.fillWithBlocks(world, structureBoundingBox, 5, 0, 10, 5, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 0, 10, 8, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, -1, 7, 7, -1, 8, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.generateWaterBox(world, structureBoundingBox, 6, -1, 3, 7, -1, 4, false);
         this.spawnElder(world, structureBoundingBox, 6, 1, 6);
         return true;
      }

      public Penthouse() {
      }

      public Penthouse(EnumFacing facing, StructureBoundingBox structureBoundingBox) {
         super(facing, structureBoundingBox);
      }
   }

   public static class SimpleRoom extends StructureOceanMonumentPieces.Piece {
      private int mainDesign;

      public SimpleRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 1, 1, 1);
         this.mainDesign = rand.nextInt(3);
      }

      public SimpleRoom() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (this.roomDefinition.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 4, 1, 6, 4, 6, ROUGH_PRISMARINE);
         }

         boolean flag = this.mainDesign != 0
            && rand.nextBoolean()
            && !this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]
            && !this.roomDefinition.hasOpening[EnumFacing.UP.getIndex()]
            && this.roomDefinition.countOpenings() > 1;
         if (this.mainDesign == 0) {
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 2, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 2, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 2, 2, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 2, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(world, SEA_LANTERN, 1, 2, 1, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 0, 7, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 3, 0, 7, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 2, 0, 7, 2, 2, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 2, 0, 6, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(world, SEA_LANTERN, 6, 2, 1, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 5, 2, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 3, 5, 2, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 2, 5, 0, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 2, 7, 2, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(world, SEA_LANTERN, 1, 2, 6, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 5, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 3, 5, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 2, 5, 7, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 2, 7, 6, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(world, SEA_LANTERN, 6, 2, 6, structureBoundingBox);
            if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 3, 3, 0, 4, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 3, 3, 0, 4, 3, 1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, 2, 0, 4, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, 1, 0, 4, 1, 1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 3, 3, 7, 4, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 3, 3, 6, 4, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, 2, 7, 4, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, 1, 6, 4, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 3, 3, 0, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 0, 3, 3, 1, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, 2, 3, 0, 2, 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, 1, 3, 1, 1, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 7, 3, 3, 7, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 6, 3, 3, 7, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 7, 2, 3, 7, 2, 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 6, 1, 3, 7, 1, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }
         } else if (this.mainDesign == 1) {
            this.fillWithBlocks(world, structureBoundingBox, 2, 1, 2, 2, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 2, 1, 5, 2, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 5, 5, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 2, 5, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(world, SEA_LANTERN, 2, 2, 2, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 2, 2, 5, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 5, 2, 5, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 5, 2, 2, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 1, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 7, 1, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 6, 0, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 7, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 6, 7, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 0, 7, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 1, 7, 3, 1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(world, ROUGH_PRISMARINE, 1, 2, 0, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 0, 2, 1, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 1, 2, 7, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 0, 2, 6, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 6, 2, 7, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 7, 2, 6, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 6, 2, 0, structureBoundingBox);
            this.setBlockState(world, ROUGH_PRISMARINE, 7, 2, 1, structureBoundingBox);
            if (!this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 6, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 6, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (!this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 1, 3, 7, 6, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 1, 2, 7, 6, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 1, 1, 7, 6, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (!this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 3, 1, 0, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, 2, 1, 0, 2, 6, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 1, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            if (!this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 7, 3, 1, 7, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 7, 2, 1, 7, 2, 6, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 7, 1, 1, 7, 1, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }
         } else if (this.mainDesign == 2) {
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 0, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 6, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 7, 6, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 2, 0, 7, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 6, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 2, 7, 6, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 3, 0, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 3, 7, 6, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, 1, 3, 7, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
               this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]) {
               this.generateWaterBox(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]) {
               this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, false);
            }

            if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]) {
               this.generateWaterBox(world, structureBoundingBox, 7, 1, 3, 7, 2, 4, false);
            }
         }

         if (flag) {
            this.fillWithBlocks(world, structureBoundingBox, 3, 1, 3, 4, 1, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 3, 2, 3, 4, 2, 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 3, 3, 3, 4, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         return true;
      }
   }

   public static class DoubleXRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleXRoom() {
      }

      public DoubleXRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 2, 1, 1);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3 = this.roomDefinition;
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 8, 0, roomDefinition2.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, roomDefinition3.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (roomDefinition3.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 4, 1, 7, 4, 6, ROUGH_PRISMARINE);
         }

         if (roomDefinition2.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 8, 4, 1, 14, 4, 6, ROUGH_PRISMARINE);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 15, 3, 0, 15, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 15, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 7, 14, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 15, 2, 0, 15, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 15, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 7, 14, 2, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 15, 1, 0, 15, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 15, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 7, 14, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 0, 10, 1, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 0, 9, 2, 3, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 3, 0, 10, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(world, SEA_LANTERN, 6, 2, 3, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 9, 2, 3, structureBoundingBox);
         if (roomDefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 1, 0, 12, 2, 0, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 1, 7, 12, 2, 7, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 15, 1, 3, 15, 2, 4, false);
         }

         return true;
      }
   }

   public static class DoubleYRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 1, 2, 1);
      }

      public DoubleYRoom() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = this.roomDefinition.connections[EnumFacing.UP.getIndex()];
         if (roomDefinition2.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 8, 1, 6, 8, 6, ROUGH_PRISMARINE);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 0, 0, 4, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 4, 0, 7, 4, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 0, 6, 4, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 7, 6, 4, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 4, 1, 2, 4, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 2, 1, 4, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 4, 1, 5, 4, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 4, 2, 6, 4, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 4, 5, 2, 4, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 5, 1, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 4, 5, 5, 4, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 4, 5, 6, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3 = this.roomDefinition;

         for (int i = 1; i <= 5; i += 4) {
            byte b = 0;
            if (roomDefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 2, i, b, 2, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 5, i, b, 5, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, i + 2, b, 4, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 0, i, b, 7, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, i + 1, b, 7, i + 1, b, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            b = 7;
            if (roomDefinition3.hasOpening[EnumFacing.NORTH.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, 2, i, b, 2, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 5, i, b, 5, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 3, i + 2, b, 4, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 0, i, b, 7, i + 2, b, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 0, i + 1, b, 7, i + 1, b, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            byte b2 = 0;
            if (roomDefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 2, b2, i + 2, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 5, b2, i + 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i + 2, 3, b2, i + 2, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 0, b2, i + 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i + 1, 0, b2, i + 1, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            b2 = 7;
            if (roomDefinition3.hasOpening[EnumFacing.EAST.getIndex()]) {
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 2, b2, i + 2, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 5, b2, i + 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i + 2, 3, b2, i + 2, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, b2, i, 0, b2, i + 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, b2, i + 1, 0, b2, i + 1, 7, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            roomDefinition3 = roomDefinition2;
         }

         return true;
      }
   }

   public static class DoubleZRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3 = this.roomDefinition;
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 0, 8, roomDefinition2.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, roomDefinition3.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (roomDefinition3.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 4, 1, 6, 4, 7, ROUGH_PRISMARINE);
         }

         if (roomDefinition2.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 4, 8, 6, 4, 14, ROUGH_PRISMARINE);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 3, 0, 7, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 7, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 15, 6, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 2, 0, 7, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 7, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 15, 6, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 1, 0, 7, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 7, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 15, 6, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 1, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 1, 6, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 1, 1, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 1, 6, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 13, 1, 1, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 13, 6, 1, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 13, 1, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 13, 6, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 6, 2, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 6, 5, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 9, 2, 3, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 9, 5, 3, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 2, 6, 4, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 2, 9, 4, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 2, 7, 2, 2, 8, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 7, 5, 2, 8, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(world, SEA_LANTERN, 2, 2, 5, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 5, 2, 5, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 2, 2, 10, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 5, 2, 10, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 2, 3, 5, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 5, 3, 5, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 2, 3, 10, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 5, 3, 10, structureBoundingBox);
         if (roomDefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 3, 7, 2, 4, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 15, 4, 2, 15, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 11, 0, 2, 12, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 11, 7, 2, 12, false);
         }

         return true;
      }

      public DoubleZRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 1, 1, 2);
      }

      public DoubleZRoom() {
      }
   }

   public static class DoubleXYRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3 = this.roomDefinition;
         StructureOceanMonumentPieces.RoomDefinition roomDefinition4 = roomDefinition3.connections[EnumFacing.UP.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition5 = roomDefinition2.connections[EnumFacing.UP.getIndex()];
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 8, 0, roomDefinition2.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, roomDefinition3.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (roomDefinition4.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 8, 1, 7, 8, 6, ROUGH_PRISMARINE);
         }

         if (roomDefinition5.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 8, 8, 1, 14, 8, 6, ROUGH_PRISMARINE);
         }

         for (int i = 1; i <= 7; i++) {
            IBlockState state = BRICKS_PRISMARINE;
            if (i == 2 || i == 6) {
               state = ROUGH_PRISMARINE;
            }

            this.fillWithBlocks(world, structureBoundingBox, 0, i, 0, 0, i, 7, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 15, i, 0, 15, i, 7, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, i, 0, 15, i, 0, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, i, 7, 14, i, 7, state, state, false);
         }

         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 3, 2, 7, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 2, 4, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 5, 4, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 13, 1, 3, 13, 7, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 1, 2, 12, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 1, 5, 12, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 3, 5, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 1, 3, 10, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 7, 2, 10, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 5, 2, 5, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 5, 2, 10, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 5, 5, 5, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 5, 5, 10, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(world, BRICKS_PRISMARINE, 6, 6, 2, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 9, 6, 2, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 6, 6, 5, structureBoundingBox);
         this.setBlockState(world, BRICKS_PRISMARINE, 9, 6, 5, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 5, 4, 3, 6, 4, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 4, 3, 10, 4, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.setBlockState(world, SEA_LANTERN, 5, 4, 2, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 5, 4, 5, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 10, 4, 2, structureBoundingBox);
         this.setBlockState(world, SEA_LANTERN, 10, 4, 5, structureBoundingBox);
         if (roomDefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 1, 0, 12, 2, 0, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 1, 7, 12, 2, 7, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 15, 1, 3, 15, 2, 4, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 5, 0, 4, 6, 0, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 5, 7, 4, 6, 7, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 5, 3, 0, 6, 4, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 5, 0, 12, 6, 0, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 11, 5, 7, 12, 6, 7, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 15, 5, 3, 15, 6, 4, false);
         }

         return true;
      }

      public DoubleXYRoom() {
      }

      public DoubleXYRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 2, 2, 1);
      }
   }

   public static class DoubleYZRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYZRoom() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3 = this.roomDefinition;
         StructureOceanMonumentPieces.RoomDefinition roomDefinition4 = roomDefinition2.connections[EnumFacing.UP.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition roomDefinition5 = roomDefinition3.connections[EnumFacing.UP.getIndex()];
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 0, 8, roomDefinition2.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, roomDefinition3.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (roomDefinition5.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 8, 1, 6, 8, 7, ROUGH_PRISMARINE);
         }

         if (roomDefinition4.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 8, 8, 6, 8, 14, ROUGH_PRISMARINE);
         }

         for (int i = 1; i <= 7; i++) {
            IBlockState state = BRICKS_PRISMARINE;
            if (i == 2 || i == 6) {
               state = ROUGH_PRISMARINE;
            }

            this.fillWithBlocks(world, structureBoundingBox, 0, i, 0, 0, i, 15, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 7, i, 0, 7, i, 15, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, i, 0, 6, i, 0, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, i, 15, 6, i, 15, state, state, false);
         }

         for (int j = 1; j <= 7; j++) {
            IBlockState state2 = DARK_PRISMARINE;
            if (j == 2 || j == 6) {
               state2 = SEA_LANTERN;
            }

            this.fillWithBlocks(world, structureBoundingBox, 3, j, 7, 4, j, 8, state2, state2, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 3, 7, 2, 4, false);
         }

         if (roomDefinition3.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 15, 4, 2, 15, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 11, 0, 2, 12, false);
         }

         if (roomDefinition2.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 11, 7, 2, 12, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 5, 0, 4, 6, 0, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 5, 3, 7, 6, 4, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 4, 2, 6, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 2, 6, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 5, 6, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         if (roomDefinition5.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 5, 3, 0, 6, 4, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 4, 2, 2, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 2, 1, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 5, 1, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.NORTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 5, 15, 4, 6, 15, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.WEST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 0, 5, 11, 0, 6, 12, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 4, 10, 2, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 10, 1, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 13, 1, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         if (roomDefinition4.hasOpening[EnumFacing.EAST.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 7, 5, 11, 7, 6, 12, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 4, 10, 6, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 10, 6, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, 1, 13, 6, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         }

         return true;
      }

      public DoubleYZRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 1, 2, 2);
      }
   }

   public static class SimpleTopRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.roomDefinition.index / 25 > 0) {
            this.generateDefaultFloor(world, structureBoundingBox, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
         }

         if (this.roomDefinition.connections[EnumFacing.UP.getIndex()] == null) {
            this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 4, 1, 6, 4, 6, ROUGH_PRISMARINE);
         }

         for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
               if (rand.nextInt(3) != 0) {
                  this.fillWithBlocks(world, structureBoundingBox, i, 2 + (rand.nextInt(4) == 0 ? 0 : 1), j, i, 3, j, Blocks.sponge.getStateFromMeta(1), Blocks.sponge.getStateFromMeta(1), false);
               }
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 1, 0, 7, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 6, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 7, 6, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 2, 0, 7, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 6, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 7, 6, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 0, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 3, 0, 7, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 6, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 7, 6, 3, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 3, 0, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 1, 3, 7, 2, 4, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 7, 4, 2, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
         if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]) {
            this.generateWaterBox(world, structureBoundingBox, 3, 1, 0, 4, 2, 0, false);
         }

         return true;
      }

      public SimpleTopRoom() {
      }

      public SimpleTopRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 1, 1, 1);
      }
   }

   public static class RoomDefinition {
      public int index;
      public RoomDefinition[] connections = new RoomDefinition[6];
      public boolean[] hasOpening = new boolean[6];
      public boolean claimed;
      public boolean isSource;
      public int scanIndex;

      public boolean isSpecial() {
         return this.index >= 75;
      }

      public boolean findSource(int n) {
         if (this.isSource) {
            return true;
         } else {
            this.scanIndex = n;

            for (int i = 0; i < 6; i++) {
               if (this.connections[i] != null && this.hasOpening[i] && this.connections[i].scanIndex != n && this.connections[i].findSource(n)) {
                  return true;
               }
            }

            return false;
         }
      }

      public RoomDefinition(int n) {
         this.index = n;
      }

      public void setConnection(EnumFacing facing, RoomDefinition roomDefinition) {
         this.connections[facing.getIndex()] = roomDefinition;
         roomDefinition.connections[facing.getOpposite().getIndex()] = this;
      }

      public int countOpenings() {
         int n = 0;

         for (int i = 0; i < 6; i++) {
            if (this.hasOpening[i]) {
               n++;
            }
         }

         return n;
      }

      public void updateOpenings() {
         for (int i = 0; i < 6; i++) {
            this.hasOpening[i] = this.connections[i] != null;
         }
      }
   }

   public static class MonumentBuilding extends StructureOceanMonumentPieces.Piece {
      private StructureOceanMonumentPieces.RoomDefinition sourceRoom;
      private StructureOceanMonumentPieces.RoomDefinition coreRoom;
      private List childPieces = Lists.newArrayList();

      private void generateRoofPiece(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 21, 21, 36, 36)) {
            this.fillWithBlocks(world, structureBoundingBox, 21, 0, 22, 36, 0, 36, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 21, 1, 22, 36, 23, 36, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 25, 16, 25, 32, 16, 32, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 25, 17, 25, 25, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 32, 17, 25, 32, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 25, 17, 32, 25, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 32, 17, 32, 32, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(world, BRICKS_PRISMARINE, 26, 20, 26, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 27, 21, 27, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 27, 20, 27, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 26, 20, 31, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 27, 21, 30, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 27, 20, 30, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 31, 20, 31, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 30, 21, 30, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 30, 20, 30, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 31, 20, 26, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 30, 21, 27, structureBoundingBox);
            this.setBlockState(world, SEA_LANTERN, 30, 20, 27, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 28, 21, 27, 29, 21, 27, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 27, 21, 28, 27, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 28, 21, 30, 29, 21, 30, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 30, 21, 28, 30, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.generateWaterBox(world, structureBoundingBox, 0, 0, 0, 58, Math.max(world.getSeaLevel(), 64) - this.boundingBox.minY, 58, false);
         this.generateWing(false, 0, world, rand, structureBoundingBox);
         this.generateWing(true, 33, world, rand, structureBoundingBox);
         this.generateEntranceArchs(world, rand, structureBoundingBox);
         this.generateEntranceWall(world, rand, structureBoundingBox);
         this.generateRoofPiece(world, rand, structureBoundingBox);
         this.generateLowerWall(world, rand, structureBoundingBox);
         this.generateMiddleWall(world, rand, structureBoundingBox);
         this.generateUpperWall(world, rand, structureBoundingBox);

         for (int i = 0; i < 7; i++) {
            int n2 = 0;

            while (n2 < 7) {
               if (n2 == 0 && i == 3) {
                  n2 = 6;
               }

               int n3 = i * 9;
               int n4 = n2 * 9;

               for (int j = 0; j < 4; j++) {
                  for (int k = 0; k < 4; k++) {
                     this.setBlockState(world, BRICKS_PRISMARINE, n3 + j, 0, n4 + k, structureBoundingBox);
                     this.replaceAirAndLiquidDownwards(world, BRICKS_PRISMARINE, n3 + j, -1, n4 + k, structureBoundingBox);
                  }
               }

               if (i != 0 && i != 6) {
                  n2 += 6;
               } else {
                  n2++;
               }
            }
         }

         for (int m = 0; m < 5; m++) {
            this.generateWaterBox(world, structureBoundingBox, -1 - m, 0 + m * 2, -1 - m, -1 - m, 23, 58 + m, false);
            this.generateWaterBox(world, structureBoundingBox, 58 + m, 0 + m * 2, -1 - m, 58 + m, 23, 58 + m, false);
            this.generateWaterBox(world, structureBoundingBox, 0 - m, 0 + m * 2, -1 - m, 57 + m, 23, -1 - m, false);
            this.generateWaterBox(world, structureBoundingBox, 0 - m, 0 + m * 2, 58 + m, 57 + m, 23, 58 + m, false);
         }

         for (StructureOceanMonumentPieces.Piece piece : (Iterable<StructureOceanMonumentPieces.Piece>)(this.childPieces)) {
            if (piece.getBoundingBox().intersectsWith(structureBoundingBox)) {
               piece.addComponentParts(world, rand, structureBoundingBox);
            }
         }

         return true;
      }

      public MonumentBuilding(Random rand, int n, int n2, EnumFacing facing) {
         super(0);
         this.coordBaseMode = facing;
         switch (this.coordBaseMode) {
            case NORTH:
            case SOUTH:
               this.boundingBox = new StructureBoundingBox(n, 39, n2, n + 58 - 1, 61, n2 + 58 - 1);
               break;
            default:
               this.boundingBox = new StructureBoundingBox(n, 39, n2, n + 58 - 1, 61, n2 + 58 - 1);
         }

         List list = this.generateRoomGraph(rand);
         this.sourceRoom.claimed = true;
         this.childPieces.add(new StructureOceanMonumentPieces.EntryRoom(this.coordBaseMode, this.sourceRoom));
         this.childPieces.add(new StructureOceanMonumentPieces.MonumentCoreRoom(this.coordBaseMode, this.coreRoom, rand));
         ArrayList list2 = Lists.newArrayList();
         list2.add(new StructureOceanMonumentPieces.XYDoubleRoomFitHelper());
         list2.add(new StructureOceanMonumentPieces.YZDoubleRoomFitHelper());
         list2.add(new StructureOceanMonumentPieces.ZDoubleRoomFitHelper());
         list2.add(new StructureOceanMonumentPieces.XDoubleRoomFitHelper());
         list2.add(new StructureOceanMonumentPieces.YDoubleRoomFitHelper());
         list2.add(new StructureOceanMonumentPieces.FitSimpleRoomTopHelper());
         list2.add(new StructureOceanMonumentPieces.FitSimpleRoomHelper());

         for (StructureOceanMonumentPieces.RoomDefinition roomDefinition2 : (Iterable<StructureOceanMonumentPieces.RoomDefinition>)(list)) {
            if (!roomDefinition2.claimed && !roomDefinition2.isSpecial()) {
               for (StructureOceanMonumentPieces.MonumentRoomFitHelper monumentRoomFitHelper : (Iterable<StructureOceanMonumentPieces.MonumentRoomFitHelper>)(list2)) {
                  if (monumentRoomFitHelper.fits(roomDefinition2)) {
                     this.childPieces.add(monumentRoomFitHelper.create(this.coordBaseMode, roomDefinition2, rand));
                     break;
                  }
               }
            }
         }

         int minY = this.boundingBox.minY;
         int xWithOffset = this.getXWithOffset(9, 22);
         int zWithOffset = this.getZWithOffset(9, 22);

         for (StructureOceanMonumentPieces.Piece piece : (Iterable<StructureOceanMonumentPieces.Piece>)(this.childPieces)) {
            piece.getBoundingBox().offset(xWithOffset, minY, zWithOffset);
         }

         StructureBoundingBox structureBoundingBox = StructureBoundingBox.createProper(
            this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1), this.getXWithOffset(23, 21), this.getYWithOffset(8), this.getZWithOffset(23, 21)
         );
         StructureBoundingBox structureBoundingBox2 = StructureBoundingBox.createProper(
            this.getXWithOffset(34, 1), this.getYWithOffset(1), this.getZWithOffset(34, 1), this.getXWithOffset(56, 21), this.getYWithOffset(8), this.getZWithOffset(56, 21)
         );
         StructureBoundingBox structureBoundingBox3 = StructureBoundingBox.createProper(
            this.getXWithOffset(22, 22), this.getYWithOffset(13), this.getZWithOffset(22, 22), this.getXWithOffset(35, 35), this.getYWithOffset(17), this.getZWithOffset(35, 35)
         );
         int n3 = rand.nextInt();
         this.childPieces.add(new StructureOceanMonumentPieces.WingRoom(this.coordBaseMode, structureBoundingBox, n3++));
         this.childPieces.add(new StructureOceanMonumentPieces.WingRoom(this.coordBaseMode, structureBoundingBox2, n3++));
         this.childPieces.add(new StructureOceanMonumentPieces.Penthouse(this.coordBaseMode, structureBoundingBox3));
      }

      private List generateRoomGraph(Random rand) {
         StructureOceanMonumentPieces.RoomDefinition[] roomDefinitions = new StructureOceanMonumentPieces.RoomDefinition[75];

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
               int roomIndex = getRoomIndex(i, (byte)0, j);
               roomDefinitions[roomIndex] = new StructureOceanMonumentPieces.RoomDefinition(roomIndex);
            }
         }

         for (int k = 0; k < 5; k++) {
            for (int m = 0; m < 4; m++) {
               int roomIndex2 = getRoomIndex(k, (byte)1, m);
               roomDefinitions[roomIndex2] = new StructureOceanMonumentPieces.RoomDefinition(roomIndex2);
            }
         }

         for (int idx = 1; idx < 4; idx++) {
            for (int index = 0; index < 2; index++) {
               int roomIndex3 = getRoomIndex(idx, (byte)2, index);
               roomDefinitions[roomIndex3] = new StructureOceanMonumentPieces.RoomDefinition(roomIndex3);
            }
         }

         this.sourceRoom = roomDefinitions[GRIDROOM_SOURCE_INDEX];

         for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
               for (int i4 = 0; i4 < 3; i4++) {
                  int roomIndex4 = getRoomIndex(i2, i4, i3);
                  if (roomDefinitions[roomIndex4] != null) {
                     for (EnumFacing facing : EnumFacing.getValues()) {
                        int n = i2 + facing.getFrontOffsetX();
                        int n2 = i4 + facing.getFrontOffsetY();
                        int n3 = i3 + facing.getFrontOffsetZ();
                        if (n >= 0 && n < 5 && n3 >= 0 && n3 < 5 && n2 >= 0 && n2 < 3) {
                           int roomIndex5 = getRoomIndex(n, n2, n3);
                           if (roomDefinitions[roomIndex5] != null) {
                              if (n3 != i3) {
                                 roomDefinitions[roomIndex4].setConnection(facing.getOpposite(), roomDefinitions[roomIndex5]);
                              } else {
                                 roomDefinitions[roomIndex4].setConnection(facing, roomDefinitions[roomIndex5]);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         StructureOceanMonumentPieces.RoomDefinition roomDefinition2;
         roomDefinitions[GRIDROOM_TOP_CONNECT_INDEX].setConnection(EnumFacing.UP, roomDefinition2 = new StructureOceanMonumentPieces.RoomDefinition(1003));
         StructureOceanMonumentPieces.RoomDefinition roomDefinition3;
         roomDefinitions[GRIDROOM_LEFTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, roomDefinition3 = new StructureOceanMonumentPieces.RoomDefinition(1001));
         StructureOceanMonumentPieces.RoomDefinition roomDefinition4;
         roomDefinitions[GRIDROOM_RIGHTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, roomDefinition4 = new StructureOceanMonumentPieces.RoomDefinition(1002));
         roomDefinition2.claimed = true;
         roomDefinition3.claimed = true;
         roomDefinition4.claimed = true;
         this.sourceRoom.isSource = true;
         this.coreRoom = roomDefinitions[getRoomIndex(rand.nextInt(4), 0, 2)];
         this.coreRoom.claimed = true;
         this.coreRoom.connections[EnumFacing.EAST.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.NORTH.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.UP.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
         this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
         ArrayList list = Lists.newArrayList();

         for (StructureOceanMonumentPieces.RoomDefinition roomDefinition5 : roomDefinitions) {
            if (roomDefinition5 != null) {
               roomDefinition5.updateOpenings();
               list.add(roomDefinition5);
            }
         }

         roomDefinition2.updateOpenings();
         Collections.shuffle(list, rand);
         int n4 = 1;

         for (StructureOceanMonumentPieces.RoomDefinition roomDefinition6 : (Iterable<StructureOceanMonumentPieces.RoomDefinition>)(list)) {
            int n5 = 0;
            int n6 = 0;

            while (n5 < 2 && n6 < 5) {
               n6++;
               int n7 = rand.nextInt(6);
               if (roomDefinition6.hasOpening[n7]) {
                  int index2 = EnumFacing.getFront(n7).getOpposite().getIndex();
                  roomDefinition6.hasOpening[n7] = false;
                  roomDefinition6.connections[n7].hasOpening[index2] = false;
                  if (roomDefinition6.findSource(n4++) && roomDefinition6.connections[n7].findSource(n4++)) {
                     n5++;
                  } else {
                     roomDefinition6.hasOpening[n7] = true;
                     roomDefinition6.connections[n7].hasOpening[index2] = true;
                  }
               }
            }
         }

         list.add(roomDefinition2);
         list.add(roomDefinition3);
         list.add(roomDefinition4);
         return list;
      }

      private void generateUpperWall(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 14, 21, 20, 43)) {
            this.fillWithBlocks(world, structureBoundingBox, 14, 0, 21, 20, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 14, 1, 22, 20, 14, 43, false);
            this.fillWithBlocks(world, structureBoundingBox, 18, 12, 22, 20, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 18, 12, 21, 20, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 23; j <= 39; j += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 19, 13, j, structureBoundingBox);
            }
         }

         if (this.doesChunkIntersect(structureBoundingBox, 37, 21, 43, 43)) {
            this.fillWithBlocks(world, structureBoundingBox, 37, 0, 21, 43, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 37, 1, 22, 43, 14, 43, false);
            this.fillWithBlocks(world, structureBoundingBox, 37, 12, 22, 39, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 37, 12, 21, 39, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

            for (int k = 0; k < 4; k++) {
               this.fillWithBlocks(world, structureBoundingBox, 43 - k, k + 9, 21, 43 - k, k + 9, 43 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int m = 23; m <= 39; m += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 38, 13, m, structureBoundingBox);
            }
         }

         if (this.doesChunkIntersect(structureBoundingBox, 15, 37, 42, 43)) {
            this.fillWithBlocks(world, structureBoundingBox, 21, 0, 37, 36, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 21, 1, 37, 36, 14, 43, false);
            this.fillWithBlocks(world, structureBoundingBox, 21, 12, 37, 36, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int idx = 0; idx < 4; idx++) {
               this.fillWithBlocks(world, structureBoundingBox, 15 + idx, idx + 9, 43 - idx, 42 - idx, idx + 9, 43 - idx, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int index = 21; index <= 36; index += 3) {
               this.setBlockState(world, DOT_DECO_DATA, index, 13, 38, structureBoundingBox);
            }
         }
      }

      private void generateEntranceArchs(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 22, 5, 35, 17)) {
            this.generateWaterBox(world, structureBoundingBox, 25, 0, 0, 32, 8, 20, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.setBlockState(world, BRICKS_PRISMARINE, 25, 5, 5 + i * 4, structureBoundingBox);
               this.setBlockState(world, BRICKS_PRISMARINE, 26, 6, 5 + i * 4, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, 26, 5, 5 + i * 4, structureBoundingBox);
               this.fillWithBlocks(world, structureBoundingBox, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.setBlockState(world, BRICKS_PRISMARINE, 32, 5, 5 + i * 4, structureBoundingBox);
               this.setBlockState(world, BRICKS_PRISMARINE, 31, 6, 5 + i * 4, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, 31, 5, 5 + i * 4, structureBoundingBox);
               this.fillWithBlocks(world, structureBoundingBox, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }
         }
      }

      private void generateMiddleWall(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 7, 21, 13, 50)) {
            this.fillWithBlocks(world, structureBoundingBox, 7, 0, 21, 13, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 21, 13, 10, 50, false);
            this.fillWithBlocks(world, structureBoundingBox, 11, 8, 21, 13, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, i + 7, i + 5, 21, i + 7, i + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 21; j <= 45; j += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 12, 9, j, structureBoundingBox);
            }
         }

         if (this.doesChunkIntersect(structureBoundingBox, 44, 21, 50, 54)) {
            this.fillWithBlocks(world, structureBoundingBox, 44, 0, 21, 50, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 44, 1, 21, 50, 10, 50, false);
            this.fillWithBlocks(world, structureBoundingBox, 44, 8, 21, 46, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int k = 0; k < 4; k++) {
               this.fillWithBlocks(world, structureBoundingBox, 50 - k, k + 5, 21, 50 - k, k + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int m = 21; m <= 45; m += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 45, 9, m, structureBoundingBox);
            }
         }

         if (this.doesChunkIntersect(structureBoundingBox, 8, 44, 49, 54)) {
            this.fillWithBlocks(world, structureBoundingBox, 14, 0, 44, 43, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 14, 1, 44, 43, 10, 50, false);

            for (int idx = 12; idx <= 45; idx += 3) {
               this.setBlockState(world, DOT_DECO_DATA, idx, 9, 45, structureBoundingBox);
               this.setBlockState(world, DOT_DECO_DATA, idx, 9, 52, structureBoundingBox);
               if (idx == 12 || idx == 18 || idx == 24 || idx == 33 || idx == 39 || idx == 45) {
                  this.setBlockState(world, DOT_DECO_DATA, idx, 9, 47, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 9, 50, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 10, 45, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 10, 46, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 10, 51, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 10, 52, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 11, 47, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 11, 50, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 12, 48, structureBoundingBox);
                  this.setBlockState(world, DOT_DECO_DATA, idx, 12, 49, structureBoundingBox);
               }
            }

            for (int index = 0; index < 3; index++) {
               this.fillWithBlocks(world, structureBoundingBox, 8 + index, 5 + index, 54, 49 - index, 5 + index, 54, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 11, 8, 54, 46, 8, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 14, 8, 44, 43, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }
      }

      private void generateEntranceWall(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 15, 20, 42, 21)) {
            this.fillWithBlocks(world, structureBoundingBox, 15, 0, 21, 42, 0, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 26, 1, 21, 31, 3, 21, false);
            this.fillWithBlocks(world, structureBoundingBox, 21, 12, 21, 36, 12, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 17, 11, 21, 40, 11, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 16, 10, 21, 41, 10, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 15, 7, 21, 42, 9, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 16, 6, 21, 41, 6, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 17, 5, 21, 40, 5, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 21, 4, 21, 36, 4, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 22, 3, 21, 26, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 31, 3, 21, 35, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 23, 2, 21, 25, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 32, 2, 21, 34, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 28, 4, 20, 29, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(world, BRICKS_PRISMARINE, 27, 3, 21, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 30, 3, 21, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 26, 2, 21, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 31, 2, 21, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 25, 1, 21, structureBoundingBox);
            this.setBlockState(world, BRICKS_PRISMARINE, 32, 1, 21, structureBoundingBox);

            for (int i = 0; i < 7; i++) {
               this.setBlockState(world, DARK_PRISMARINE, 28 - i, 6 + i, 21, structureBoundingBox);
               this.setBlockState(world, DARK_PRISMARINE, 29 + i, 6 + i, 21, structureBoundingBox);
            }

            for (int j = 0; j < 4; j++) {
               this.setBlockState(world, DARK_PRISMARINE, 28 - j, 9 + j, 21, structureBoundingBox);
               this.setBlockState(world, DARK_PRISMARINE, 29 + j, 9 + j, 21, structureBoundingBox);
            }

            this.setBlockState(world, DARK_PRISMARINE, 28, 12, 21, structureBoundingBox);
            this.setBlockState(world, DARK_PRISMARINE, 29, 12, 21, structureBoundingBox);

            for (int k = 0; k < 3; k++) {
               this.setBlockState(world, DARK_PRISMARINE, 22 - k * 2, 8, 21, structureBoundingBox);
               this.setBlockState(world, DARK_PRISMARINE, 22 - k * 2, 9, 21, structureBoundingBox);
               this.setBlockState(world, DARK_PRISMARINE, 35 + k * 2, 8, 21, structureBoundingBox);
               this.setBlockState(world, DARK_PRISMARINE, 35 + k * 2, 9, 21, structureBoundingBox);
            }

            this.generateWaterBox(world, structureBoundingBox, 15, 13, 21, 42, 15, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 15, 1, 21, 15, 6, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 16, 1, 21, 16, 5, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 17, 1, 21, 20, 4, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 21, 1, 21, 21, 3, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 22, 1, 21, 22, 2, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 23, 1, 21, 24, 1, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 42, 1, 21, 42, 6, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 41, 1, 21, 41, 5, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 37, 1, 21, 40, 4, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 36, 1, 21, 36, 3, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 33, 1, 21, 34, 1, 21, false);
            this.generateWaterBox(world, structureBoundingBox, 35, 1, 21, 35, 2, 21, false);
         }
      }

      private void generateLowerWall(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, 0, 21, 6, 58)) {
            this.fillWithBlocks(world, structureBoundingBox, 0, 0, 21, 6, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 0, 1, 21, 6, 7, 57, false);
            this.fillWithBlocks(world, structureBoundingBox, 4, 4, 21, 6, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, i, i + 1, 21, i, i + 1, 57 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 23; j < 53; j += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 5, 5, j, structureBoundingBox);
            }

            this.setBlockState(world, DOT_DECO_DATA, 5, 5, 52, structureBoundingBox);

            for (int k = 0; k < 4; k++) {
               this.fillWithBlocks(world, structureBoundingBox, k, k + 1, 21, k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 4, 1, 52, 6, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 51, 5, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }

         if (this.doesChunkIntersect(structureBoundingBox, 51, 21, 58, 58)) {
            this.fillWithBlocks(world, structureBoundingBox, 51, 0, 21, 57, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 51, 1, 21, 57, 7, 57, false);
            this.fillWithBlocks(world, structureBoundingBox, 51, 4, 21, 53, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int m = 0; m < 4; m++) {
               this.fillWithBlocks(world, structureBoundingBox, 57 - m, m + 1, 21, 57 - m, m + 1, 57 - m, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int idx = 23; idx < 53; idx += 3) {
               this.setBlockState(world, DOT_DECO_DATA, 52, 5, idx, structureBoundingBox);
            }

            this.setBlockState(world, DOT_DECO_DATA, 52, 5, 52, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, 51, 1, 52, 53, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, 52, 1, 51, 52, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }

         if (this.doesChunkIntersect(structureBoundingBox, 0, 51, 57, 57)) {
            this.fillWithBlocks(world, structureBoundingBox, 7, 0, 51, 50, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, 7, 1, 51, 50, 10, 57, false);

            for (int index = 0; index < 4; index++) {
               this.fillWithBlocks(world, structureBoundingBox, index + 1, index + 1, 57 - index, 56 - index, index + 1, 57 - index, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }
         }
      }

      private void generateWing(boolean flag, int n, World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.doesChunkIntersect(structureBoundingBox, n, 0, n + 23, 20)) {
            this.fillWithBlocks(world, structureBoundingBox, n + 0, 0, 0, n + 24, 0, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(world, structureBoundingBox, n + 0, 1, 0, n + 24, 10, 20, false);

            for (int i = 0; i < 4; i++) {
               this.fillWithBlocks(world, structureBoundingBox, n + i, i + 1, i, n + i, i + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, n + i + 7, i + 5, i + 7, n + i + 7, i + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, n + 17 - i, i + 5, i + 7, n + 17 - i, i + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, n + 24 - i, i + 1, i, n + 24 - i, i + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, n + i + 1, i + 1, i, n + 23 - i, i + 1, i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
               this.fillWithBlocks(world, structureBoundingBox, n + i + 8, i + 5, i + 7, n + 16 - i, i + 5, i + 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, n + 4, 4, 4, n + 6, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 7, 4, 4, n + 17, 4, 6, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 18, 4, 4, n + 20, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 11, 8, 11, n + 13, 8, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(world, DOT_DECO_DATA, n + 12, 9, 12, structureBoundingBox);
            this.setBlockState(world, DOT_DECO_DATA, n + 12, 9, 15, structureBoundingBox);
            this.setBlockState(world, DOT_DECO_DATA, n + 12, 9, 18, structureBoundingBox);
            int n2 = flag ? n + 19 : n + 5;
            int n3 = flag ? n + 5 : n + 19;

            for (int j = 20; j >= 5; j -= 3) {
               this.setBlockState(world, DOT_DECO_DATA, n2, 5, j, structureBoundingBox);
            }

            for (int k = 19; k >= 7; k -= 3) {
               this.setBlockState(world, DOT_DECO_DATA, n3, 5, k, structureBoundingBox);
            }

            for (int m = 0; m < 4; m++) {
               int n4 = flag ? n + (24 - (17 - m * 3)) : n + 17 - m * 3;
               this.setBlockState(world, DOT_DECO_DATA, n4, 5, 5, structureBoundingBox);
            }

            this.setBlockState(world, DOT_DECO_DATA, n3, 5, 5, structureBoundingBox);
            this.fillWithBlocks(world, structureBoundingBox, n + 11, 1, 12, n + 13, 7, 12, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(world, structureBoundingBox, n + 12, 1, 11, n + 12, 7, 13, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
         }
      }

      public MonumentBuilding() {
      }
   }

   public static class MonumentCoreRoom extends StructureOceanMonumentPieces.Piece {
      public MonumentCoreRoom() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.generateBoxOnFillOnly(world, structureBoundingBox, 1, 8, 0, 14, 8, 14, ROUGH_PRISMARINE);
         byte b = 7;
         IBlockState state = BRICKS_PRISMARINE;
         this.fillWithBlocks(world, structureBoundingBox, 0, b, 0, 0, b, 15, state, state, false);
         this.fillWithBlocks(world, structureBoundingBox, 15, b, 0, 15, b, 15, state, state, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, b, 0, 15, b, 0, state, state, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, b, 15, 14, b, 15, state, state, false);

         for (int i = 1; i <= 6; i++) {
            state = BRICKS_PRISMARINE;
            if (i == 2 || i == 6) {
               state = ROUGH_PRISMARINE;
            }

            for (int j = 0; j <= 15; j += 15) {
               this.fillWithBlocks(world, structureBoundingBox, j, i, 0, j, i, 1, state, state, false);
               this.fillWithBlocks(world, structureBoundingBox, j, i, 6, j, i, 9, state, state, false);
               this.fillWithBlocks(world, structureBoundingBox, j, i, 14, j, i, 15, state, state, false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 1, i, 0, 1, i, 0, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 6, i, 0, 9, i, 0, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 14, i, 0, 14, i, 0, state, state, false);
            this.fillWithBlocks(world, structureBoundingBox, 1, i, 15, 14, i, 15, state, state, false);
         }

         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 6, 9, 6, 9, DARK_PRISMARINE, DARK_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 4, 7, 8, 5, 8, Blocks.gold_block.getDefaultState(), Blocks.gold_block.getDefaultState(), false);

         for (int k = 3; k <= 6; k += 3) {
            for (int m = 6; m <= 9; m += 3) {
               this.setBlockState(world, SEA_LANTERN, m, k, 6, structureBoundingBox);
               this.setBlockState(world, SEA_LANTERN, m, k, 9, structureBoundingBox);
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 6, 5, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 9, 5, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 1, 6, 10, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 1, 9, 10, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 5, 6, 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 1, 5, 9, 2, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 10, 6, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 1, 10, 9, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 5, 5, 6, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 10, 5, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 2, 5, 10, 6, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 2, 10, 10, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 7, 1, 5, 7, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 7, 1, 10, 7, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 7, 9, 5, 7, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 7, 9, 10, 7, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 7, 5, 6, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 7, 10, 6, 7, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 7, 5, 14, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 7, 10, 14, 7, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 2, 2, 1, 3, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 2, 3, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 13, 1, 2, 13, 1, 3, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 12, 1, 2, 12, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 12, 2, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 13, 3, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 13, 1, 12, 13, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         this.fillWithBlocks(world, structureBoundingBox, 12, 1, 13, 12, 1, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
         return true;
      }

      public MonumentCoreRoom(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition2, Random rand) {
         super(1, facing, roomDefinition2, 2, 2, 2);
      }
   }

   public static class FitSimpleRoomHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {

      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         return true;
      }

      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         return new StructureOceanMonumentPieces.SimpleRoom(facing, roomDefinition, rand);
      }

      public FitSimpleRoomHelper() {
      }
   }

   public static class XDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         return roomDefinition.hasOpening[EnumFacing.EAST.getIndex()] && !roomDefinition.connections[EnumFacing.EAST.getIndex()].claimed;
      }

      public XDoubleRoomFitHelper() {
      }


      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         roomDefinition.connections[EnumFacing.EAST.getIndex()].claimed = true;
         return new StructureOceanMonumentPieces.DoubleXRoom(facing, roomDefinition, rand);
      }
   }

   public static class YDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      public YDoubleRoomFitHelper() {
      }

      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         roomDefinition.connections[EnumFacing.UP.getIndex()].claimed = true;
         return new StructureOceanMonumentPieces.DoubleYRoom(facing, roomDefinition, rand);
      }


      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         return roomDefinition.hasOpening[EnumFacing.UP.getIndex()] && !roomDefinition.connections[EnumFacing.UP.getIndex()].claimed;
      }
   }

   public static class ZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         return roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()] && !roomDefinition.connections[EnumFacing.NORTH.getIndex()].claimed;
      }


      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = roomDefinition;
         if (!roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()] || roomDefinition.connections[EnumFacing.NORTH.getIndex()].claimed) {
            roomDefinition2 = roomDefinition.connections[EnumFacing.SOUTH.getIndex()];
         }

         roomDefinition2.claimed = true;
         roomDefinition2.connections[EnumFacing.NORTH.getIndex()].claimed = true;
         return new StructureOceanMonumentPieces.DoubleZRoom(facing, roomDefinition2, rand);
      }

      public ZDoubleRoomFitHelper() {
      }
   }

   public interface MonumentRoomFitHelper {
      boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition);

      StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand);
   }

   public static class XYDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         roomDefinition.connections[EnumFacing.EAST.getIndex()].claimed = true;
         roomDefinition.connections[EnumFacing.UP.getIndex()].claimed = true;
         roomDefinition.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
         return new StructureOceanMonumentPieces.DoubleXYRoom(facing, roomDefinition, rand);
      }


      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         if (roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]
            && !roomDefinition.connections[EnumFacing.EAST.getIndex()].claimed
            && roomDefinition.hasOpening[EnumFacing.UP.getIndex()]
            && !roomDefinition.connections[EnumFacing.UP.getIndex()].claimed) {
            StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = roomDefinition.connections[EnumFacing.EAST.getIndex()];
            return roomDefinition2.hasOpening[EnumFacing.UP.getIndex()] && !roomDefinition2.connections[EnumFacing.UP.getIndex()].claimed;
         } else {
            return false;
         }
      }

      public XYDoubleRoomFitHelper() {
      }
   }

   public static class YZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {

      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         if (roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]
            && !roomDefinition.connections[EnumFacing.NORTH.getIndex()].claimed
            && roomDefinition.hasOpening[EnumFacing.UP.getIndex()]
            && !roomDefinition.connections[EnumFacing.UP.getIndex()].claimed) {
            StructureOceanMonumentPieces.RoomDefinition roomDefinition2 = roomDefinition.connections[EnumFacing.NORTH.getIndex()];
            return roomDefinition2.hasOpening[EnumFacing.UP.getIndex()] && !roomDefinition2.connections[EnumFacing.UP.getIndex()].claimed;
         } else {
            return false;
         }
      }

      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         roomDefinition.connections[EnumFacing.NORTH.getIndex()].claimed = true;
         roomDefinition.connections[EnumFacing.UP.getIndex()].claimed = true;
         roomDefinition.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
         return new StructureOceanMonumentPieces.DoubleYZRoom(facing, roomDefinition, rand);
      }

      public YZDoubleRoomFitHelper() {
      }
   }

   public static class FitSimpleRoomTopHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      public FitSimpleRoomTopHelper() {
      }

      @Override
      public StructureOceanMonumentPieces.Piece create(EnumFacing facing, StructureOceanMonumentPieces.RoomDefinition roomDefinition, Random rand) {
         roomDefinition.claimed = true;
         return new StructureOceanMonumentPieces.SimpleTopRoom(facing, roomDefinition, rand);
      }


      @Override
      public boolean fits(StructureOceanMonumentPieces.RoomDefinition roomDefinition) {
         return !roomDefinition.hasOpening[EnumFacing.WEST.getIndex()]
            && !roomDefinition.hasOpening[EnumFacing.EAST.getIndex()]
            && !roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()]
            && !roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()]
            && !roomDefinition.hasOpening[EnumFacing.UP.getIndex()];
      }
   }
}
