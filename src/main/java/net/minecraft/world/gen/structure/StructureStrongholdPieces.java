package net.minecraft.world.gen.structure;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class StructureStrongholdPieces {
   private static final StructureStrongholdPieces.PieceWeight[] pieceWeightArray = new StructureStrongholdPieces.PieceWeight[]{
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Straight.class, 40, 0),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Prison.class, 5, 5),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.LeftTurn.class, 20, 0),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.RightTurn.class, 20, 0),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.RoomCrossing.class, 10, 6),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.StairsStraight.class, 5, 5),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Stairs.class, 5, 5),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Crossing.class, 5, 4),
      new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.ChestCorridor.class, 5, 4),
      new StructureStrongholdPieces.DefaultPieceWeight(StructureStrongholdPieces.Library.class, 10, 2),
      new StructureStrongholdPieces.DefaultPieceWeight2(StructureStrongholdPieces.PortalRoom.class, 20, 1)
   };
   private static List structurePieceList;
   private static Class strongComponentType;
   public static int totalWeight;
   private static final StructureStrongholdPieces.Stones strongholdStones = new StructureStrongholdPieces.Stones(null);

   private static StructureStrongholdPieces.Stronghold generatePieceFromSmallDoor(
      StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      if (!canAddStructurePieces()) {
         return null;
      } else {
         if (strongComponentType != null) {
            StructureStrongholdPieces.Stronghold stronghold = findAndCreatePieceFactory(strongComponentType, list, rand, n, n2, n3, facing, n4);
            strongComponentType = null;
            if (stronghold != null) {
               return stronghold;
            }
         }

         int n5 = 0;

         while (n5 < 5) {
            n5++;
            int n6 = rand.nextInt(totalWeight);

            for (StructureStrongholdPieces.PieceWeight pieceWeight : (Iterable<StructureStrongholdPieces.PieceWeight>)(structurePieceList)) {
               n6 -= pieceWeight.pieceWeight;
               if (n6 < 0) {
                  if (!pieceWeight.canSpawnMoreStructuresOfType(n4) || pieceWeight == stairs2.strongholdPieceWeight) {
                     break;
                  }

                  StructureStrongholdPieces.Stronghold stronghold2 = findAndCreatePieceFactory(pieceWeight.pieceClass, list, rand, n, n2, n3, facing, n4);
                  if (stronghold2 != null) {
                     pieceWeight.instancesSpawned++;
                     stairs2.strongholdPieceWeight = pieceWeight;
                     if (!pieceWeight.canSpawnMoreStructures()) {
                        structurePieceList.remove(pieceWeight);
                     }

                     return stronghold2;
                  }
               }
            }
         }

         StructureBoundingBox structureBoundingBox = StructureStrongholdPieces.Corridor.findPieceBox(list, rand, n, n2, n3, facing);
         return structureBoundingBox != null && structureBoundingBox.minY > 1 ? new StructureStrongholdPieces.Corridor(n4, rand, structureBoundingBox, facing) : null;
      }
   }

   private static boolean canAddStructurePieces() {
      boolean flag = false;
      totalWeight = 0;

      for (StructureStrongholdPieces.PieceWeight pieceWeight : (Iterable<StructureStrongholdPieces.PieceWeight>)(structurePieceList)) {
         if (pieceWeight.instancesLimit > 0 && pieceWeight.instancesSpawned < pieceWeight.instancesLimit) {
            flag = true;
         }

         totalWeight = totalWeight + pieceWeight.pieceWeight;
      }

      return flag;
   }

   public static StructureStrongholdPieces.Stones accessStrongholdStones() {
      return strongholdStones;
   }

   public static void registerStrongholdPieces() {
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.ChestCorridor.class, "SHCC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Corridor.class, "SHFC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Crossing.class, "SH5C");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.LeftTurn.class, "SHLT");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Library.class, "SHLi");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.PortalRoom.class, "SHPR");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Prison.class, "SHPH");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.RightTurn.class, "SHRT");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.RoomCrossing.class, "SHRC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Stairs.class, "SHSD");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Stairs2.class, "SHStart");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.Straight.class, "SHS");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces.StairsStraight.class, "SHSSD");
   }

   private static StructureComponent generateAndAddPiece(
      StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      if (n4 > 50) {
         return null;
      } else if (Math.abs(n - stairs2.getBoundingBox().minX) <= 112 && Math.abs(n3 - stairs2.getBoundingBox().minZ) <= 112) {
         StructureStrongholdPieces.Stronghold stronghold = generatePieceFromSmallDoor(stairs2, list, rand, n, n2, n3, facing, n4 + 1);
         if (stronghold != null) {
            list.add(stronghold);
            stairs2.pendingChildren.add(stronghold);
         }

         return stronghold;
      } else {
         return null;
      }
   }

   public static Class assignStrongComponentType(Class clazz) {
      strongComponentType = clazz;
      return clazz;
   }

   public static StructureComponent addStrongholdPiece(
      StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      return generateAndAddPiece(stairs2, list, rand, n, n2, n3, facing, n4);
   }

   private static StructureStrongholdPieces.Stronghold findAndCreatePieceFactory(Class clazz, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      Object obj = null;
      if (clazz == StructureStrongholdPieces.Straight.class) {
         obj = StructureStrongholdPieces.Straight.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.Prison.class) {
         obj = StructureStrongholdPieces.Prison.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.LeftTurn.class) {
         obj = StructureStrongholdPieces.LeftTurn.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.RightTurn.class) {
         obj = StructureStrongholdPieces.RightTurn.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.RoomCrossing.class) {
         obj = StructureStrongholdPieces.RoomCrossing.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.StairsStraight.class) {
         obj = StructureStrongholdPieces.StairsStraight.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.Stairs.class) {
         obj = StructureStrongholdPieces.Stairs.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.Crossing.class) {
         obj = StructureStrongholdPieces.Crossing.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.ChestCorridor.class) {
         obj = StructureStrongholdPieces.ChestCorridor.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.Library.class) {
         obj = StructureStrongholdPieces.Library.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (clazz == StructureStrongholdPieces.PortalRoom.class) {
         obj = StructureStrongholdPieces.PortalRoom.createPiece(list, rand, n, n2, n3, facing, n4);
      }

      return (StructureStrongholdPieces.Stronghold)obj;
   }

   public static void prepareStructurePieces() {
      structurePieceList = Lists.newArrayList();

      for (StructureStrongholdPieces.PieceWeight pieceWeight : pieceWeightArray) {
         pieceWeight.instancesSpawned = 0;
         structurePieceList.add(pieceWeight);
      }

      strongComponentType = null;
   }

public static final class DefaultPieceWeight extends StructureStrongholdPieces.PieceWeight {
      public DefaultPieceWeight(Class clazz, int n, int n2) {
         super(clazz, n, n2);
      }

      @Override
      public boolean canSpawnMoreStructuresOfType(int n) {
         return super.canSpawnMoreStructuresOfType(n) && n > 4;
      }
   }

   public static final class DefaultPieceWeight2 extends StructureStrongholdPieces.PieceWeight {
      @Override
      public boolean canSpawnMoreStructuresOfType(int n) {
         return super.canSpawnMoreStructuresOfType(n) && n > 5;
      }

      public DefaultPieceWeight2(Class clazz, int n, int n2) {
         super(clazz, n, n2);
      }
   }

public static class Prison extends StructureStrongholdPieces.Stronghold {
      public Prison(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 8, 4, 10, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 1, 0);
            this.fillWithBlocks(world, structureBoundingBox, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 1, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 3, 4, 3, 3, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 7, 4, 3, 7, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 9, 4, 3, 9, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithBlocks(world, structureBoundingBox, 4, 1, 4, 4, 3, 6, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 5, 7, 3, 5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 4, 3, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 4, 3, 8, structureBoundingBox);
            this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 8, structureBoundingBox);
            this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 8, structureBoundingBox);
            return true;
         }
      }

      public Prison() {
      }

      public static Prison createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 9, 5, 11, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Prison(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
      }
   }

   public static class Stairs extends StructureStrongholdPieces.Stronghold {
      private boolean source;

      public static Stairs createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -7, 0, 5, 11, 5, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Stairs(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.source = tag.getBoolean("Source");
      }

      public Stairs(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.source = false;
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
      }

      public Stairs(int n, Random rand, int n2, int n3) {
         super(n);
         this.source = true;
         this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);
         this.entryDoor = StructureStrongholdPieces.Stronghold.Door.OPENING;
         switch (this.coordBaseMode) {
            case SOUTH:
            case NORTH:
               this.boundingBox = new StructureBoundingBox(n2, 64, n3, n2 + 5 - 1, 74, n3 + 5 - 1);
               break;
            default:
               this.boundingBox = new StructureBoundingBox(n2, 64, n3, n2 + 5 - 1, 74, n3 + 5 - 1);
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Source", this.source);
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         if (this.source) {
            StructureStrongholdPieces.assignStrongComponentType(StructureStrongholdPieces.Crossing.class);
         }

         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 10, 4, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 7, 0);
            this.placeDoor(world, rand, structureBoundingBox, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 6, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 5, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 6, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 5, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 4, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 5, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 4, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 3, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 3, 4, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 3, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 2, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 3, 3, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 2, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 1, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 2, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 1, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.getMetadata()), 1, 1, 3, structureBoundingBox);
            return true;
         }
      }

      public Stairs() {
      }
   }

   public static class Stones extends StructureComponent.BlockSelector {
      public Stones(StructureStrongholdPieces.DefaultPieceWeight obj) {
         this();
      }

      private Stones() {
      }

      @Override
      public void selectBlocks(Random rand, int n, int n2, int n3, boolean flag) {
         if (flag) {
            float f = rand.nextFloat();
            if (f < 0.2F) {
               this.blockstate = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CRACKED_META);
            } else if (f < 0.5F) {
               this.blockstate = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.MOSSY_META);
            } else if (f < 0.55F) {
               this.blockstate = Blocks.monster_egg.getStateFromMeta(BlockSilverfish.EnumType.STONEBRICK.getMetadata());
            } else {
               this.blockstate = Blocks.stonebrick.getDefaultState();
            }
         } else {
            this.blockstate = Blocks.air.getDefaultState();
         }
      }
   }

   public static class Library extends StructureStrongholdPieces.Stronghold {
      private static final List strongholdLibraryChestContents = Lists.newArrayList(
         new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.book, 0, 1, 3, 20),
            new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20),
            new WeightedRandomChestContent(Items.map, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.compass, 0, 1, 1, 1)
         }
      );
      private boolean isLargeRoom;

      public static Library createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -4, -1, 0, 14, 11, 15, facing);
         if (!canStrongholdGoDeeper(structureBoundingBox) || StructureComponent.findIntersecting(list, structureBoundingBox) != null) {
            structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -4, -1, 0, 14, 6, 15, facing);
            if (!canStrongholdGoDeeper(structureBoundingBox) || StructureComponent.findIntersecting(list, structureBoundingBox) != null) {
               return null;
            }
         }

         return new Library(n4, rand, structureBoundingBox, facing);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.isLargeRoom = tag.getBoolean("Tall");
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            byte b = 11;
            if (!this.isLargeRoom) {
               b = 6;
            }

            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 13, b - 1, 14, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 4, 1, 0);
            this.generateMaybeBox(world, structureBoundingBox, rand, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.web.getDefaultState(), Blocks.web.getDefaultState(), false);

            for (int i = 1; i <= 13; i++) {
               if ((i - 1) % 4 == 0) {
                  this.fillWithBlocks(world, structureBoundingBox, 1, 1, i, 1, 4, i, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                  this.fillWithBlocks(world, structureBoundingBox, 12, 1, i, 12, 4, i, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 2, 3, i, structureBoundingBox);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 11, 3, i, structureBoundingBox);
                  if (this.isLargeRoom) {
                     this.fillWithBlocks(world, structureBoundingBox, 1, 6, i, 1, 9, i, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                     this.fillWithBlocks(world, structureBoundingBox, 12, 6, i, 12, 9, i, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                  }
               } else {
                  this.fillWithBlocks(world, structureBoundingBox, 1, 1, i, 1, 4, i, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                  this.fillWithBlocks(world, structureBoundingBox, 12, 1, i, 12, 4, i, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                  if (this.isLargeRoom) {
                     this.fillWithBlocks(world, structureBoundingBox, 1, 6, i, 1, 9, i, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                     this.fillWithBlocks(world, structureBoundingBox, 12, 6, i, 12, 9, i, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                  }
               }
            }

            for (int j = 3; j < 12; j += 2) {
               this.fillWithBlocks(world, structureBoundingBox, 3, 1, j, 4, 3, j, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 6, 1, j, 7, 3, j, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 9, 1, j, 10, 3, j, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            }

            if (this.isLargeRoom) {
               this.fillWithBlocks(world, structureBoundingBox, 1, 5, 1, 3, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 10, 5, 1, 12, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 4, 5, 1, 9, 5, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 4, 5, 12, 9, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
               this.setBlockState(world, Blocks.planks.getDefaultState(), 9, 5, 11, structureBoundingBox);
               this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 5, 11, structureBoundingBox);
               this.setBlockState(world, Blocks.planks.getDefaultState(), 9, 5, 10, structureBoundingBox);
               this.fillWithBlocks(world, structureBoundingBox, 3, 6, 2, 3, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 10, 6, 2, 10, 6, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 4, 6, 2, 9, 6, 2, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
               this.fillWithBlocks(world, structureBoundingBox, 4, 6, 12, 8, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 9, 6, 11, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 8, 6, 11, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 9, 6, 10, structureBoundingBox);
               int metadataWithOffset = this.getMetadataWithOffset(Blocks.ladder, 3);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 1, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 2, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 3, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 4, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 5, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 6, 13, structureBoundingBox);
               this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 10, 7, 13, structureBoundingBox);
               byte b2 = 7;
               byte b3 = 7;
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 1, 9, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2, 9, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 1, 8, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2, 8, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 1, 7, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2, 7, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 2, 7, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 + 1, 7, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 1, 7, b3 - 1, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2 - 1, 7, b3 + 1, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2, 7, b3 - 1, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_fence.getDefaultState(), b2, 7, b3 + 1, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2 - 2, 8, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2 + 1, 8, b3, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2 - 1, 8, b3 - 1, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2 - 1, 8, b3 + 1, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2, 8, b3 - 1, structureBoundingBox);
               this.setBlockState(world, Blocks.torch.getDefaultState(), b2, 8, b3 + 1, structureBoundingBox);
            }

            this.generateChestContents(
               world,
               structureBoundingBox,
               rand,
               3,
               3,
               5,
               WeightedRandomChestContent.combine(
                  strongholdLibraryChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand, 1, 5, 2)}
               ),
               1 + rand.nextInt(4)
            );
            if (this.isLargeRoom) {
               this.setBlockState(world, Blocks.air.getDefaultState(), 12, 9, 1, structureBoundingBox);
               this.generateChestContents(
                  world,
                  structureBoundingBox,
                  rand,
                  12,
                  8,
                  1,
                  WeightedRandomChestContent.combine(
                     strongholdLibraryChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand, 1, 5, 2)}
                  ),
                  1 + rand.nextInt(4)
               );
            }

            return true;
         }
      }

      public Library(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
         this.isLargeRoom = structureBoundingBox.getYSize() > 6;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Tall", this.isLargeRoom);
      }

      public Library() {
      }
   }

   public static class Stairs2 extends StructureStrongholdPieces.Stairs {
      public StructureStrongholdPieces.PieceWeight strongholdPieceWeight;
      public StructureStrongholdPieces.PortalRoom strongholdPortalRoom;
      public List pendingChildren = Lists.newArrayList();

      public Stairs2(int n, Random rand, int n2, int n3) {
         super(0, rand, n2, n3);
      }

      @Override
      public BlockPos getBoundingBoxCenter() {
         return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getBoundingBoxCenter() : super.getBoundingBoxCenter();
      }

      public Stairs2() {
      }
   }

   public static class Corridor extends StructureStrongholdPieces.Stronghold {
      private int steps;

      public Corridor() {
      }

      public Corridor(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.steps = facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH ? structureBoundingBox.getXSize() : structureBoundingBox.getZSize();
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("Steps", this.steps);
      }

      public static StructureBoundingBox findPieceBox(List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, 4, facing);
         StructureComponent structureComponent = StructureComponent.findIntersecting(list, structureBoundingBox);
         if (structureComponent == null) {
            return null;
         } else {
            if (structureComponent.getBoundingBox().minY == structureBoundingBox.minY) {
               for (int i = 3; i >= 1; i--) {
                  structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, i - 1, facing);
                  if (!structureComponent.getBoundingBox().intersectsWith(structureBoundingBox)) {
                     return StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, i, facing);
                  }
               }
            }

            return null;
         }
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            for (int i = 0; i < this.steps; i++) {
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 0, 0, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 0, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 0, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 0, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 4, 0, i, structureBoundingBox);

               for (int j = 1; j <= 3; j++) {
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 0, j, i, structureBoundingBox);
                  this.setBlockState(world, Blocks.air.getDefaultState(), 1, j, i, structureBoundingBox);
                  this.setBlockState(world, Blocks.air.getDefaultState(), 2, j, i, structureBoundingBox);
                  this.setBlockState(world, Blocks.air.getDefaultState(), 3, j, i, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 4, j, i, structureBoundingBox);
               }

               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 0, 4, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 4, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 4, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 4, i, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 4, 4, i, structureBoundingBox);
            }

            return true;
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.steps = tag.getInteger("Steps");
      }
   }

   public static class Crossing extends StructureStrongholdPieces.Stronghold {
      private boolean leftLow;
      private boolean leftHigh;
      private boolean rightLow;
      private boolean rightHigh;

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.leftLow = tag.getBoolean("leftLow");
         this.leftHigh = tag.getBoolean("leftHigh");
         this.rightLow = tag.getBoolean("rightLow");
         this.rightHigh = tag.getBoolean("rightHigh");
      }

      public static Crossing createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -4, -3, 0, 10, 9, 11, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Crossing(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         int n = 3;
         int n2 = 5;
         if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH) {
            n = 8 - n;
            n2 = 8 - n2;
         }

         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 5, 1);
         if (this.leftLow) {
            this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, n, 1);
         }

         if (this.leftHigh) {
            this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, n2, 7);
         }

         if (this.rightLow) {
            this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, n, 1);
         }

         if (this.rightHigh) {
            this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, n2, 7);
         }
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 9, 8, 10, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 4, 3, 0);
            if (this.leftLow) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 3, 1, 0, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            if (this.rightLow) {
               this.fillWithBlocks(world, structureBoundingBox, 9, 3, 1, 9, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            if (this.leftHigh) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 5, 7, 0, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            if (this.rightHigh) {
               this.fillWithBlocks(world, structureBoundingBox, 9, 5, 7, 9, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 10, 7, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 2, 1, 8, 2, 6, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 5, 4, 4, 9, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 8, 1, 5, 8, 4, 9, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 4, 7, 3, 4, 9, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 3, 5, 3, 3, 6, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithBlocks(world, structureBoundingBox, 1, 3, 4, 3, 3, 4, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 1, 4, 6, 3, 4, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 5, 1, 7, 7, 1, 8, false, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.fillWithBlocks(world, structureBoundingBox, 5, 1, 9, 7, 1, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 2, 7, 7, 2, 7, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 4, 5, 7, 4, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 8, 5, 7, 8, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 5, 5, 7, 7, 5, 9, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
            this.setBlockState(world, Blocks.torch.getDefaultState(), 6, 5, 6, structureBoundingBox);
            return true;
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("leftLow", this.leftLow);
         tag.setBoolean("leftHigh", this.leftHigh);
         tag.setBoolean("rightLow", this.rightLow);
         tag.setBoolean("rightHigh", this.rightHigh);
      }

      public Crossing(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
         this.leftLow = rand.nextBoolean();
         this.leftHigh = rand.nextBoolean();
         this.rightLow = rand.nextBoolean();
         this.rightHigh = rand.nextInt(3) > 0;
      }

      public Crossing() {
      }
   }

   public static class LeftTurn extends StructureStrongholdPieces.Stronghold {
      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
            this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
         } else {
            this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
         }
      }

      public LeftTurn(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
      }

      public LeftTurn() {
      }

      public static LeftTurn createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, 5, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new LeftTurn(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 4, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 1, 0);
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
               this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            return true;
         }
      }
   }

   public static class Straight extends StructureStrongholdPieces.Stronghold {
      private boolean expandsX;
      private boolean expandsZ;

      public Straight() {
      }

      public static Straight createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, 7, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Straight(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public Straight(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
         this.expandsX = rand.nextInt(2) == 0;
         this.expandsZ = rand.nextInt(2) == 0;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Left", this.expandsX);
         tag.setBoolean("Right", this.expandsZ);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.expandsX = tag.getBoolean("Left");
         this.expandsZ = tag.getBoolean("Right");
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 6, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 1, 0);
            this.placeDoor(world, rand, structureBoundingBox, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
            this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 1, 2, 1, Blocks.torch.getDefaultState());
            this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 3, 2, 1, Blocks.torch.getDefaultState());
            this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 1, 2, 5, Blocks.torch.getDefaultState());
            this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 3, 2, 5, Blocks.torch.getDefaultState());
            if (this.expandsX) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            if (this.expandsZ) {
               this.fillWithBlocks(world, structureBoundingBox, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
         if (this.expandsX) {
            this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 2);
         }

         if (this.expandsZ) {
            this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 2);
         }
      }
   }

   public static class RightTurn extends StructureStrongholdPieces.LeftTurn {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 4, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 1, 0);
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
               this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            } else {
               this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
            this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
         } else {
            this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
         }
      }
   }

   public static class PortalRoom extends StructureStrongholdPieces.Stronghold {
      private boolean hasSpawner;

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 10, 7, 15, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.placeDoor(world, rand, structureBoundingBox, StructureStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
         int n = 6;
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, n, 1, 1, n, 14, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, n, 1, 9, n, 14, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, n, 1, 8, n, 2, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, n, 14, 8, n, 14, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 1, 1, 2, 1, 4, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 8, 1, 1, 9, 1, 4, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 1, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 1, 1, 9, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 3, 1, 8, 7, 1, 12, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 9, 6, 1, 11, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);

         for (int i = 3; i < 14; i += 2) {
            this.fillWithBlocks(world, structureBoundingBox, 0, 3, i, 0, 4, i, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 10, 3, i, 10, 4, i, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
         }

         for (int j = 2; j < 9; j += 2) {
            this.fillWithBlocks(world, structureBoundingBox, j, 3, 15, j, 4, 15, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
         }

         int metadataWithOffset = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3);
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 5, 6, 1, 7, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 2, 6, 6, 2, 7, false, rand, StructureStrongholdPieces.accessStrongholdStones());
         this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 3, 7, 6, 3, 7, false, rand, StructureStrongholdPieces.accessStrongholdStones());

         for (int k = 4; k <= 6; k++) {
            this.setBlockState(world, Blocks.stone_brick_stairs.getStateFromMeta(metadataWithOffset), k, 1, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_brick_stairs.getStateFromMeta(metadataWithOffset), k, 2, 5, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_brick_stairs.getStateFromMeta(metadataWithOffset), k, 3, 6, structureBoundingBox);
         }

         int horizontalIndex = EnumFacing.NORTH.getHorizontalIndex();
         int horizontalIndex2 = EnumFacing.SOUTH.getHorizontalIndex();
         int horizontalIndex3 = EnumFacing.EAST.getHorizontalIndex();
         int horizontalIndex4 = EnumFacing.WEST.getHorizontalIndex();
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case SOUTH:
                  horizontalIndex = EnumFacing.SOUTH.getHorizontalIndex();
                  horizontalIndex2 = EnumFacing.NORTH.getHorizontalIndex();
                  break;
               case WEST:
                  horizontalIndex = EnumFacing.WEST.getHorizontalIndex();
                  horizontalIndex2 = EnumFacing.EAST.getHorizontalIndex();
                  horizontalIndex3 = EnumFacing.SOUTH.getHorizontalIndex();
                  horizontalIndex4 = EnumFacing.NORTH.getHorizontalIndex();
                  break;
               case EAST:
                  horizontalIndex = EnumFacing.EAST.getHorizontalIndex();
                  horizontalIndex2 = EnumFacing.WEST.getHorizontalIndex();
                  horizontalIndex3 = EnumFacing.SOUTH.getHorizontalIndex();
                  horizontalIndex4 = EnumFacing.NORTH.getHorizontalIndex();
            }
         }

         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 4, 3, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 5, 3, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 6, 3, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex2).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 4, 3, 12, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex2).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 5, 3, 12, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex2).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 6, 3, 12, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex3).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 3, 3, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex3).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 3, 3, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex3).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 3, 3, 11, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex4).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 7, 3, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex4).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 7, 3, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.end_portal_frame.getStateFromMeta(horizontalIndex4).withProperty(BlockEndPortalFrame.EYE, rand.nextFloat() > 0.9F), 7, 3, 11, structureBoundingBox);
         if (!this.hasSpawner) {
            n = this.getYWithOffset(3);
            BlockPos pos = new BlockPos(this.getXWithOffset(5, 6), n, this.getZWithOffset(5, 6));
            if (structureBoundingBox.isVecInside(pos)) {
               this.hasSpawner = true;
               world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
               TileEntity tileEntity = world.getTileEntity(pos);
               if (tileEntity instanceof TileEntityMobSpawner) {
                  ((TileEntityMobSpawner)tileEntity).getSpawnerBaseLogic().setEntityName("Silverfish");
               }
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         if (structureComponent != null) {
            ((StructureStrongholdPieces.Stairs2)structureComponent).strongholdPortalRoom = this;
         }
      }

      public PortalRoom(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Mob", this.hasSpawner);
      }

      public PortalRoom() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.hasSpawner = tag.getBoolean("Mob");
      }

      public static PortalRoom createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -4, -1, 0, 11, 8, 16, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new PortalRoom(n4, rand, structureBoundingBox, facing)
            : null;
      }
   }

   public static abstract class Stronghold extends StructureComponent {
      public StructureStrongholdPieces.Stronghold.Door entryDoor = StructureStrongholdPieces.Stronghold.Door.OPENING;

      public void placeDoor(World world, Random rand, StructureBoundingBox structureBoundingBox, StructureStrongholdPieces.Stronghold.Door door, int n, int n2, int n3) {
         switch (door) {
            case OPENING:
            default:
               this.fillWithBlocks(
                  world, structureBoundingBox, n, n2, n3, n + 3 - 1, n2 + 3 - 1, n3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false
               );
               break;
            case WOOD_DOOR:
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 1, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_door.getDefaultState(), n + 1, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_door.getStateFromMeta(8), n + 1, n2 + 1, n3, structureBoundingBox);
               break;
            case GRATES:
               this.setBlockState(world, Blocks.air.getDefaultState(), n + 1, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.air.getDefaultState(), n + 1, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n + 1, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n + 2, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n + 2, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_bars.getDefaultState(), n + 2, n2, n3, structureBoundingBox);
               break;
            case IRON_DOOR:
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 1, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2 + 2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stonebrick.getDefaultState(), n + 2, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_door.getDefaultState(), n + 1, n2, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.iron_door.getStateFromMeta(8), n + 1, n2 + 1, n3, structureBoundingBox);
               this.setBlockState(world, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 4)), n + 2, n2 + 1, n3 + 1, structureBoundingBox);
               this.setBlockState(world, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 3)), n + 2, n2 + 1, n3 - 1, structureBoundingBox);
         }
      }

      public StructureComponent getNextComponentZ(StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case SOUTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
               case WEST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
               case EAST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
               case NORTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
            }
         }

         return null;
      }

      public StructureComponent getNextComponentX(StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case SOUTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
               case WEST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
               case EAST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
               case NORTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
            }
         }

         return null;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         tag.setString("EntryDoor", this.entryDoor.name());
      }

      public static boolean canStrongholdGoDeeper(StructureBoundingBox structureBoundingBox) {
         return structureBoundingBox != null && structureBoundingBox.minY > 10;
      }

      public StructureStrongholdPieces.Stronghold.Door getRandomDoor(Random rand) {
         switch (rand.nextInt(5)) {
            case 0:
            case 1:
            default:
               return StructureStrongholdPieces.Stronghold.Door.OPENING;
            case 2:
               return StructureStrongholdPieces.Stronghold.Door.WOOD_DOOR;
            case 3:
               return StructureStrongholdPieces.Stronghold.Door.GRATES;
            case 4:
               return StructureStrongholdPieces.Stronghold.Door.IRON_DOOR;
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         this.entryDoor = StructureStrongholdPieces.Stronghold.Door.valueOf(tag.getString("EntryDoor"));
      }

      public StructureComponent getNextComponentNormal(StructureStrongholdPieces.Stairs2 stairs2, List list, Random rand, int n, int n2) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case SOUTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n,
                     this.boundingBox.minY + n2,
                     this.boundingBox.maxZ + 1,
                     this.coordBaseMode,
                     this.getComponentType()
                  );
               case WEST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ + n,
                     this.coordBaseMode,
                     this.getComponentType()
                  );
               case EAST:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ + n,
                     this.coordBaseMode,
                     this.getComponentType()
                  );
               case NORTH:
                  return StructureStrongholdPieces.addStrongholdPiece(
                     stairs2,
                     list,
                     rand,
                     this.boundingBox.minX + n,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ - 1,
                     this.coordBaseMode,
                     this.getComponentType()
                  );
            }
         }

         return null;
      }

      public Stronghold(int n) {
         super(n);
      }

      public Stronghold() {
      }

public enum Door {
         OPENING,
         WOOD_DOOR,
         GRATES,
         IRON_DOOR;
      }
   }

   public static class PieceWeight {
      public Class pieceClass;
      public final int pieceWeight;
      public int instancesSpawned;
      public int instancesLimit;

      public boolean canSpawnMoreStructures() {
         return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
      }

      public PieceWeight(Class clazz, int n, int n2) {
         this.pieceClass = clazz;
         this.pieceWeight = n;
         this.instancesLimit = n2;
      }

      public boolean canSpawnMoreStructuresOfType(int n) {
         return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
      }
   }

   public static class RoomCrossing extends StructureStrongholdPieces.Stronghold {
      private static final List strongholdRoomCrossingChestContents = Lists.newArrayList(
         new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
            new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10),
            new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1)
         }
      );
      public int roomType;

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 4, 1);
         this.getNextComponentX((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 4);
         this.getNextComponentZ((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 4);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("Type", this.roomType);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.roomType = tag.getInteger("Type");
      }

      public RoomCrossing() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 10, 6, 10, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 4, 1, 0);
            this.fillWithBlocks(world, structureBoundingBox, 4, 1, 10, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 0, 1, 4, 0, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 10, 1, 4, 10, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            switch (this.roomType) {
               case 0:
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 2, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 3, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 4, 3, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 6, 3, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 5, 3, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.torch.getDefaultState(), 5, 3, 6, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 4, 1, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 4, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 4, 1, 6, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 6, 1, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 6, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 6, 1, 6, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 5, 1, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.stone_slab.getDefaultState(), 5, 1, 6, structureBoundingBox);
                  break;
               case 1:
                  for (int i = 0; i < 5; i++) {
                     this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 1, 3 + i, structureBoundingBox);
                     this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 7, 1, 3 + i, structureBoundingBox);
                     this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3 + i, 1, 3, structureBoundingBox);
                     this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3 + i, 1, 7, structureBoundingBox);
                  }

                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 2, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 5, 3, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.flowing_water.getDefaultState(), 5, 4, 5, structureBoundingBox);
                  break;
               case 2:
                  for (int j = 1; j <= 9; j++) {
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 1, 3, j, structureBoundingBox);
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 9, 3, j, structureBoundingBox);
                  }

                  for (int k = 1; k <= 9; k++) {
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), k, 3, 1, structureBoundingBox);
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), k, 3, 9, structureBoundingBox);
                  }

                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 5, 1, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 5, 1, 6, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 5, 3, 4, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 5, 3, 6, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, 1, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 3, 5, structureBoundingBox);
                  this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, 3, 5, structureBoundingBox);

                  for (int m = 1; m <= 3; m++) {
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, m, 4, structureBoundingBox);
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, m, 4, structureBoundingBox);
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, m, 6, structureBoundingBox);
                     this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, m, 6, structureBoundingBox);
                  }

                  this.setBlockState(world, Blocks.torch.getDefaultState(), 5, 3, 5, structureBoundingBox);

                  for (int idx = 2; idx <= 8; idx++) {
                     this.setBlockState(world, Blocks.planks.getDefaultState(), 2, 3, idx, structureBoundingBox);
                     this.setBlockState(world, Blocks.planks.getDefaultState(), 3, 3, idx, structureBoundingBox);
                     if (idx <= 3 || idx >= 7) {
                        this.setBlockState(world, Blocks.planks.getDefaultState(), 4, 3, idx, structureBoundingBox);
                        this.setBlockState(world, Blocks.planks.getDefaultState(), 5, 3, idx, structureBoundingBox);
                        this.setBlockState(world, Blocks.planks.getDefaultState(), 6, 3, idx, structureBoundingBox);
                     }

                     this.setBlockState(world, Blocks.planks.getDefaultState(), 7, 3, idx, structureBoundingBox);
                     this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 3, idx, structureBoundingBox);
                  }

                  this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 1, 3, structureBoundingBox);
                  this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 2, 3, structureBoundingBox);
                  this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 3, 3, structureBoundingBox);
                  this.generateChestContents(
                     world,
                     structureBoundingBox,
                     rand,
                     3,
                     4,
                     8,
                     WeightedRandomChestContent.combine(
                        strongholdRoomCrossingChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand)}
                     ),
                     1 + rand.nextInt(4)
                  );
            }

            return true;
         }
      }

      public RoomCrossing(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
         this.roomType = rand.nextInt(5);
      }

      public static RoomCrossing createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -4, -1, 0, 11, 7, 11, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new RoomCrossing(n4, rand, structureBoundingBox, facing)
            : null;
      }
   }

   public static class ChestCorridor extends StructureStrongholdPieces.Stronghold {
      private static final List strongholdChestContents = Lists.newArrayList(
         new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
            new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
         }
      );
      private boolean hasMadeChest;

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 6, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 1, 0);
            this.placeDoor(world, rand, structureBoundingBox, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
            this.fillWithBlocks(world, structureBoundingBox, 3, 1, 2, 3, 1, 4, Blocks.stonebrick.getDefaultState(), Blocks.stonebrick.getDefaultState(), false);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.getMetadata()), 3, 1, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.getMetadata()), 3, 1, 5, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.getMetadata()), 3, 2, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.getMetadata()), 3, 2, 4, structureBoundingBox);

            for (int i = 2; i <= 4; i++) {
               this.setBlockState(world, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.getMetadata()), 2, 1, i, structureBoundingBox);
            }

            if (!this.hasMadeChest && structureBoundingBox.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
               this.hasMadeChest = true;
               this.generateChestContents(
                  world,
                  structureBoundingBox,
                  rand,
                  3,
                  2,
                  3,
                  WeightedRandomChestContent.combine(strongholdChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.getRandom(rand)}),
                  2 + rand.nextInt(2)
               );
            }

            return true;
         }
      }

      public static ChestCorridor createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -1, 0, 5, 5, 7, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new ChestCorridor(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
      }

      public ChestCorridor() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Chest", this.hasMadeChest);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.hasMadeChest = tag.getBoolean("Chest");
      }

      public ChestCorridor(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
      }
   }

   public static class StairsStraight extends StructureStrongholdPieces.Stronghold {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
            return false;
         } else {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 10, 7, true, rand, StructureStrongholdPieces.accessStrongholdStones());
            this.placeDoor(world, rand, structureBoundingBox, this.entryDoor, 1, 7, 0);
            this.placeDoor(world, rand, structureBoundingBox, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
            int metadataWithOffset = this.getMetadataWithOffset(Blocks.stone_stairs, 2);

            for (int i = 0; i < 6; i++) {
               this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(metadataWithOffset), 1, 6 - i, 1 + i, structureBoundingBox);
               this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(metadataWithOffset), 2, 6 - i, 1 + i, structureBoundingBox);
               this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(metadataWithOffset), 3, 6 - i, 1 + i, structureBoundingBox);
               if (i < 5) {
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 1, 5 - i, 1 + i, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 2, 5 - i, 1 + i, structureBoundingBox);
                  this.setBlockState(world, Blocks.stonebrick.getDefaultState(), 3, 5 - i, 1 + i, structureBoundingBox);
               }
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureStrongholdPieces.Stairs2)structureComponent, list, rand, 1, 1);
      }

      public static StairsStraight createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -7, 0, 5, 11, 8, facing);
         return canStrongholdGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new StairsStraight(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public StairsStraight(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.entryDoor = this.getRandomDoor(rand);
         this.boundingBox = structureBoundingBox;
      }

      public StairsStraight() {
      }
   }
}
