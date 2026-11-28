package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class StructureNetherBridgePieces {
   private static final StructureNetherBridgePieces.PieceWeight[] PRIMARY_PIECE_WEIGHTS = new StructureNetherBridgePieces.PieceWeight[]{
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Straight.class, 30, 0, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing3.class, 10, 4),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing.class, 10, 4),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Stairs.class, 10, 3),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Throne.class, 5, 2),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Entrance.class, 5, 1)
   };
   private static final StructureNetherBridgePieces.PieceWeight[] SECONDARY_PIECE_WEIGHTS = new StructureNetherBridgePieces.PieceWeight[]{
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor5.class, 25, 0, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing2.class, 15, 5),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor2.class, 5, 10),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor.class, 5, 10),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor3.class, 10, 3, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor4.class, 7, 2),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.NetherStalkRoom.class, 5, 2)
   };

   public static StructureNetherBridgePieces.PieceWeight[] getPrimaryPieceWeights() {
      return PRIMARY_PIECE_WEIGHTS;
   }

   public static StructureNetherBridgePieces.PieceWeight[] getSecondaryPieceWeights() {
      return SECONDARY_PIECE_WEIGHTS;
   }

   private static StructureNetherBridgePieces.Piece findAndCreateBridgePieceFactory(
      StructureNetherBridgePieces.PieceWeight pieceWeight, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      Class weightClass = pieceWeight.weightClass;
      Object obj = null;
      if (weightClass == StructureNetherBridgePieces.Straight.class) {
         obj = StructureNetherBridgePieces.Straight.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Crossing3.class) {
         obj = StructureNetherBridgePieces.Crossing3.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Crossing.class) {
         obj = StructureNetherBridgePieces.Crossing.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Stairs.class) {
         obj = StructureNetherBridgePieces.Stairs.createPiece(list, rand, n, n2, n3, n4, facing);
      } else if (weightClass == StructureNetherBridgePieces.Throne.class) {
         obj = StructureNetherBridgePieces.Throne.createPiece(list, rand, n, n2, n3, n4, facing);
      } else if (weightClass == StructureNetherBridgePieces.Entrance.class) {
         obj = StructureNetherBridgePieces.Entrance.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Corridor5.class) {
         obj = StructureNetherBridgePieces.Corridor5.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Corridor2.class) {
         obj = StructureNetherBridgePieces.Corridor2.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Corridor.class) {
         obj = StructureNetherBridgePieces.Corridor.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Corridor3.class) {
         obj = StructureNetherBridgePieces.Corridor3.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Corridor4.class) {
         obj = StructureNetherBridgePieces.Corridor4.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.Crossing2.class) {
         obj = StructureNetherBridgePieces.Crossing2.createPiece(list, rand, n, n2, n3, facing, n4);
      } else if (weightClass == StructureNetherBridgePieces.NetherStalkRoom.class) {
         obj = StructureNetherBridgePieces.NetherStalkRoom.createPiece(list, rand, n, n2, n3, facing, n4);
      }

      return (StructureNetherBridgePieces.Piece)obj;
   }

   public static void registerNetherFortressPieces() {
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing3.class, "NeBCr");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.End.class, "NeBEF");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Straight.class, "NeBS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor3.class, "NeCCS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor4.class, "NeCTB");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Entrance.class, "NeCE");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing2.class, "NeSCSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor.class, "NeSCLT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor5.class, "NeSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor2.class, "NeSCRT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.NetherStalkRoom.class, "NeCSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Throne.class, "NeMT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing.class, "NeRC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Stairs.class, "NeSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Start.class, "NeStart");
   }

   public static StructureNetherBridgePieces.Piece createBridgePiece(
      StructureNetherBridgePieces.PieceWeight pieceWeight, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      return findAndCreateBridgePieceFactory(pieceWeight, list, rand, n, n2, n3, facing, n4);
   }

public static class End extends StructureNetherBridgePieces.Piece {
      private int fillSeed;

      public static End createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -3, 0, 5, 10, 8, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new End(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public End(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.fillSeed = rand.nextInt();
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         Random rand2 = new Random((long)this.fillSeed);

         for (int i = 0; i <= 4; i++) {
            for (int j = 3; j <= 4; j++) {
               this.fillWithBlocks(
                  world, structureBoundingBox, i, j, 0, i, j, rand2.nextInt(8), Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false
               );
            }
         }

         int n2 = rand2.nextInt(8);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 0, 5, n2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         n2 = rand2.nextInt(8);
         this.fillWithBlocks(world, structureBoundingBox, 4, 5, 0, 4, 5, n2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int k = 0; k <= 4; k++) {
            this.fillWithBlocks(world, structureBoundingBox, k, 2, 0, k, 2, rand2.nextInt(5), Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         }

         for (int m = 0; m <= 4; m++) {
            for (int idx = 0; idx <= 1; idx++) {
               this.fillWithBlocks(
                  world, structureBoundingBox, m, idx, 0, m, idx, rand2.nextInt(3), Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false
               );
            }
         }

         return true;
      }

      public End() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("Seed", this.fillSeed);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.fillSeed = tag.getInteger("Seed");
      }
   }

   public static abstract class Piece extends StructureComponent {
      public static final List CHEST_CONTENT = Lists.newArrayList(
         new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.golden_chestplate, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5),
            new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8),
            new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3),
            new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 2, 4, 2)
         }
      );

      private int getTotalWeight(List list) {
         boolean flag = false;
         int n = 0;

         for (StructureNetherBridgePieces.PieceWeight pieceWeight : (Iterable<StructureNetherBridgePieces.PieceWeight>)(list)) {
            if (pieceWeight.maxPlaceCount > 0 && pieceWeight.placeCount < pieceWeight.maxPlaceCount) {
               flag = true;
            }

            n += pieceWeight.weight;
         }

         return flag ? n : -1;
      }

      public StructureComponent getNextComponentZ(StructureNetherBridgePieces.Start start, List list, Random rand, int n, int n2, boolean flag) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType(),
                     flag
                  );
               case SOUTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType(),
                     flag
                  );
               case WEST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType(),
                     flag
                  );
               case EAST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType(),
                     flag
                  );
            }
         }

         return null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
      }

      public Piece() {
      }

      public StructureComponent getNextComponentX(StructureNetherBridgePieces.Start start, List list, Random rand, int n, int n2, boolean flag) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType(),
                     flag
                  );
               case SOUTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType(),
                     flag
                  );
               case WEST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType(),
                     flag
                  );
               case EAST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType(),
                     flag
                  );
            }
         }

         return null;
      }

      public Piece(int n) {
         super(n);
      }

      public static boolean isAboveGround(StructureBoundingBox structureBoundingBox) {
         return structureBoundingBox != null && structureBoundingBox.minY > 10;
      }

      private StructureComponent generateAndAddComponent(
         StructureNetherBridgePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4, boolean flag
      ) {
         if (Math.abs(n - start.getBoundingBox().minX) <= 112 && Math.abs(n3 - start.getBoundingBox().minZ) <= 112) {
            List primaryWeights = start.primaryWeights;
            if (flag) {
               primaryWeights = start.secondaryWeights;
            }

            Piece piece = this.generatePiece(start, primaryWeights, list, rand, n, n2, n3, facing, n4 + 1);
            if (piece != null) {
               list.add(piece);
               start.pendingChildren.add(piece);
            }

            return piece;
         } else {
            return StructureNetherBridgePieces.End.createPiece(list, rand, n, n2, n3, facing, n4);
         }
      }

      public StructureComponent getNextComponentNormal(StructureNetherBridgePieces.Start start, List list, Random rand, int n, int n2, boolean flag) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ - 1,
                     this.coordBaseMode,
                     this.getComponentType(),
                     flag
                  );
               case SOUTH:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n,
                     this.boundingBox.minY + n2,
                     this.boundingBox.maxZ + 1,
                     this.coordBaseMode,
                     this.getComponentType(),
                     flag
                  );
               case WEST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ + n,
                     this.coordBaseMode,
                     this.getComponentType(),
                     flag
                  );
               case EAST:
                  return this.generateAndAddComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n2,
                     this.boundingBox.minZ + n,
                     this.coordBaseMode,
                     this.getComponentType(),
                     flag
                  );
            }
         }

         return null;
      }

      private Piece generatePiece(
         StructureNetherBridgePieces.Start start, List list, List list2, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         int totalWeight = this.getTotalWeight(list);
         boolean flag = totalWeight > 0 && n4 <= 30;
         int n5 = 0;

         while (n5 < 5 && flag) {
            n5++;
            int n6 = rand.nextInt(totalWeight);

            for (StructureNetherBridgePieces.PieceWeight pieceWeight : (Iterable<StructureNetherBridgePieces.PieceWeight>)(list)) {
               n6 -= pieceWeight.weight;
               if (n6 < 0) {
                  if (!pieceWeight.doPlace(n4) || pieceWeight == start.theNetherBridgePieceWeight && !pieceWeight.allowInRow) {
                     break;
                  }

                  Piece piece = StructureNetherBridgePieces.createBridgePiece(pieceWeight, list2, rand, n, n2, n3, facing, n4);
                  if (piece != null) {
                     pieceWeight.placeCount++;
                     start.theNetherBridgePieceWeight = pieceWeight;
                     if (!pieceWeight.isValid()) {
                        list.remove(pieceWeight);
                     }

                     return piece;
                  }
               }
            }
         }

         return StructureNetherBridgePieces.End.createPiece(list2, rand, n, n2, n3, facing, n4);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
      }
   }

   public static class Start extends StructureNetherBridgePieces.Crossing3 {
      public StructureNetherBridgePieces.PieceWeight theNetherBridgePieceWeight;
      public List primaryWeights;
      public List secondaryWeights;
      public List pendingChildren = Lists.newArrayList();

      public Start(Random rand, int n, int n2) {
         super(rand, n, n2);
         this.primaryWeights = Lists.newArrayList();

         for (StructureNetherBridgePieces.PieceWeight pieceWeight : StructureNetherBridgePieces.getPrimaryPieceWeights()) {
            pieceWeight.placeCount = 0;
            this.primaryWeights.add(pieceWeight);
         }

         this.secondaryWeights = Lists.newArrayList();

         for (StructureNetherBridgePieces.PieceWeight pieceWeight2 : StructureNetherBridgePieces.getSecondaryPieceWeights()) {
            pieceWeight2.placeCount = 0;
            this.secondaryWeights.add(pieceWeight2);
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
      }

      public Start() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
      }
   }

   public static class Stairs extends StructureNetherBridgePieces.Piece {
      public Stairs(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public static Stairs createPiece(List list, Random rand, int n, int n2, int n3, int n4, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -2, 0, 0, 7, 11, 7, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Stairs(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 6, 2, false);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 6, 10, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 1, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 0, 6, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 1, 0, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 1, 6, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 6, 5, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 2, 6, 5, 2, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 4, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 5, 2, 5, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 5, 4, 3, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 2, 5, 3, 4, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 2, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 5, 1, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 7, 1, 5, 7, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 8, 2, 6, 8, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 6, 0, 4, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      public Stairs() {
      }
   }

   public static class Throne extends StructureNetherBridgePieces.Piece {
      private boolean hasSpawner;

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Mob", this.hasSpawner);
      }

      public Throne() {
      }

      public static Throne createPiece(List list, Random rand, int n, int n2, int n3, int n4, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -2, 0, 0, 7, 8, 9, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Throne(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 6, 7, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 5, 1, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 1, 5, 2, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 2, 5, 3, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 3, 5, 4, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 1, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 0, 5, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 2, 1, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 5, 2, 5, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 3, 0, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 5, 3, 6, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 8, 5, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 1, 6, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 5, 6, 3, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 3, 0, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 6, 3, 6, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 6, 8, 5, 7, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 8, 8, 4, 8, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         if (!this.hasSpawner) {
            BlockPos pos = new BlockPos(this.getXWithOffset(3, 5), this.getYWithOffset(5), this.getZWithOffset(3, 5));
            if (structureBoundingBox.isVecInside(pos)) {
               this.hasSpawner = true;
               world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
               TileEntity tileEntity = world.getTileEntity(pos);
               if (tileEntity instanceof TileEntityMobSpawner) {
                  ((TileEntityMobSpawner)tileEntity).getSpawnerBaseLogic().setEntityName("Blaze");
               }
            }
         }

         for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      public Throne(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.hasSpawner = tag.getBoolean("Mob");
      }
   }

   public static class Corridor extends StructureNetherBridgePieces.Piece {
      private boolean chest;

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Chest", this.chest);
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentX((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 1, true);
      }

      public Corridor() {
      }

      public static Corridor createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, 0, 0, 5, 7, 5, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Corridor(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.chest = tag.getBoolean("Chest");
      }

      public Corridor(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.chest = rand.nextInt(3) == 0;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 4, 3, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         if (this.chest && structureBoundingBox.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
            this.chest = false;
            this.generateChestContents(world, structureBoundingBox, rand, 3, 2, 3, CHEST_CONTENT, 2 + rand.nextInt(4));
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }
   }

   public static class Crossing extends StructureNetherBridgePieces.Piece {
      public Crossing(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public Crossing() {
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 2, 0, false);
         this.getNextComponentX((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 2, false);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 2, false);
      }

      public static Crossing createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -2, 0, 0, 7, 9, 7, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Crossing(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 6, 7, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 1, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 6, 1, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 0, 6, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 2, 6, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 5, 0, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 0, 6, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 5, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 6, 0, 4, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 6, 6, 4, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 6, 4, 5, 6, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 2, 0, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 6, 2, 6, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 5, 2, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }
   }

   public static class Entrance extends StructureNetherBridgePieces.Piece {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 8, 0, 7, 8, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int i = 1; i <= 11; i += 2) {
            this.fillWithBlocks(
               world, structureBoundingBox, i, 10, 0, i, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, i, 10, 12, i, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 0, 10, i, 0, 11, i, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 12, 10, i, 12, 11, i, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), i, 13, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), i, 13, 12, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 0, 13, i, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 12, 13, i, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), i + 1, 13, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), i + 1, 13, 12, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, i + 1, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 12, 13, i + 1, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, structureBoundingBox);

         for (int j = 3; j <= 9; j += 2) {
            this.fillWithBlocks(
               world, structureBoundingBox, 1, 7, j, 1, 8, j, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 11, 7, j, 11, 8, j, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
         }

         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int k = 4; k <= 8; k++) {
            for (int m = 0; m <= 2; m++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), k, -1, m, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), k, -1, 12 - m, structureBoundingBox);
            }
         }

         for (int idx = 0; idx <= 2; idx++) {
            for (int index = 4; index <= 8; index++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), idx, -1, index, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), 12 - idx, -1, index, structureBoundingBox);
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 5, 5, 5, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 6, 6, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 6, 0, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.flowing_lava.getDefaultState(), 6, 5, 6, structureBoundingBox);
         BlockPos pos = new BlockPos(this.getXWithOffset(6, 6), this.getYWithOffset(5), this.getZWithOffset(6, 6));
         if (structureBoundingBox.isVecInside(pos)) {
            world.forceBlockUpdateTick(Blocks.flowing_lava, pos, rand);
         }

         return true;
      }

      public Entrance() {
      }

      public static Entrance createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -5, -3, 0, 13, 14, 13, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Entrance(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public Entrance(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 5, 3, true);
      }
   }

   public static class Straight extends StructureNetherBridgePieces.Piece {
      public static Straight createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -3, 0, 5, 10, 19, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Straight(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 4, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 0, 3, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 0, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 5, 0, 4, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 4, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 13, 4, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 15, 4, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 2; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, 18 - j, structureBoundingBox);
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 4, 0, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 14, 0, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 17, 0, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 4, 4, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 14, 4, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 17, 4, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         return true;
      }

      public Straight() {
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 1, 3, false);
      }

      public Straight(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }
   }

   public static class Corridor2 extends StructureNetherBridgePieces.Piece {
      private boolean chest;

      public Corridor2(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.chest = rand.nextInt(3) == 0;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Chest", this.chest);
      }

      public Corridor2() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         if (this.chest && structureBoundingBox.isVecInside(new BlockPos(this.getXWithOffset(1, 3), this.getYWithOffset(2), this.getZWithOffset(1, 3)))) {
            this.chest = false;
            this.generateChestContents(world, structureBoundingBox, rand, 1, 2, 3, CHEST_CONTENT, 2 + rand.nextInt(4));
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      public static Corridor2 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, 0, 0, 5, 7, 5, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Corridor2(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 1, true);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.chest = tag.getBoolean("Chest");
      }
   }

   public static class Corridor3 extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 1, 0, true);
      }

      public Corridor3() {
      }

      public Corridor3(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public static Corridor3 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, -7, 0, 5, 14, 10, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Corridor3(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         int metadataWithOffset = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 2);

         for (int i = 0; i <= 9; i++) {
            int n = Math.max(1, 7 - i);
            int n2 = Math.min(Math.max(n + 5, 14 - i), 13);
            int n3 = i;
            this.fillWithBlocks(world, structureBoundingBox, 0, 0, i, 4, n, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.fillWithBlocks(world, structureBoundingBox, 1, n + 1, i, 3, n2 - 1, i, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            if (i <= 6) {
               this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset), 1, n + 1, i, structureBoundingBox);
               this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset), 2, n + 1, i, structureBoundingBox);
               this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset), 3, n + 1, i, structureBoundingBox);
            }

            this.fillWithBlocks(world, structureBoundingBox, 0, n2, i, 4, n2, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.fillWithBlocks(
               world, structureBoundingBox, 0, n + 1, i, 0, n2 - 1, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 4, n + 1, i, 4, n2 - 1, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false
            );
            if ((i & 1) == 0) {
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  0,
                  n + 2,
                  i,
                  0,
                  n + 3,
                  i,
                  Blocks.nether_brick_fence.getDefaultState(),
                  Blocks.nether_brick_fence.getDefaultState(),
                  false
               );
               this.fillWithBlocks(
                  world,
                  structureBoundingBox,
                  4,
                  n + 2,
                  i,
                  4,
                  n + 3,
                  i,
                  Blocks.nether_brick_fence.getDefaultState(),
                  Blocks.nether_brick_fence.getDefaultState(),
                  false
               );
            }

            for (int j = 0; j <= 4; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), j, -1, n3, structureBoundingBox);
            }
         }

         return true;
      }
   }

   public static class Corridor4 extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         byte b = 1;
         if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH) {
            b = 5;
         }

         this.getNextComponentX((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, b, rand.nextInt(8) > 0);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, b, rand.nextInt(8) > 0);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 8, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 8, 5, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 0, 8, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 2, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 2, 0, 8, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 0, 1, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 3, 0, 7, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 4, 8, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 4, 2, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 4, 7, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 8, 8, 3, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 6, 0, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 3, 6, 8, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 4, 0, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 3, 4, 8, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 3, 5, 7, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 5, 1, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 4, 5, 7, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 8; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), j, -1, i, structureBoundingBox);
            }
         }

         return true;
      }

      public static Corridor4 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -3, 0, 0, 9, 7, 9, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Corridor4(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public Corridor4() {
      }

      public Corridor4(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }
   }

   public static class Corridor5 extends StructureNetherBridgePieces.Piece {
      public Corridor5() {
      }

      public Corridor5(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      public static Corridor5 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, 0, 0, 5, 7, 5, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Corridor5(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 1, 0, true);
      }
   }

   public static class Crossing2 extends StructureNetherBridgePieces.Piece {
      public Crossing2(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 4, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      public static Crossing2 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -1, 0, 0, 5, 7, 5, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Crossing2(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public Crossing2() {
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 1, 0, true);
         this.getNextComponentX((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 1, true);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 0, 1, true);
      }
   }

   public static class Crossing3 extends StructureNetherBridgePieces.Piece {
      public Crossing3() {
      }

      public static Crossing3 createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -8, -3, 0, 19, 10, 19, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Crossing3(n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 7, 3, 0, 11, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 7, 18, 4, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 0, 10, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 8, 18, 7, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 5, 0, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 5, 11, 7, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 0, 11, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 11, 11, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 7, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 7, 18, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 11, 7, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 11, 18, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 2, 0, 11, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 2, 13, 11, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 0, 0, 11, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 0, 15, 11, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 7; i <= 11; i++) {
            for (int j = 0; j <= 2; j++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i, -1, 18 - j, structureBoundingBox);
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 7, 5, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 13, 2, 7, 18, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 7, 3, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 15, 0, 7, 18, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int k = 0; k <= 2; k++) {
            for (int m = 7; m <= 11; m++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), k, -1, m, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), 18 - k, -1, m, structureBoundingBox);
            }
         }

         return true;
      }

      public Crossing3(Random rand, int n, int n2) {
         super(0);
         this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);
         switch (this.coordBaseMode) {
            case NORTH:
            case SOUTH:
               this.boundingBox = new StructureBoundingBox(n, 64, n2, n + 19 - 1, 73, n2 + 19 - 1);
               break;
            default:
               this.boundingBox = new StructureBoundingBox(n, 64, n2, n + 19 - 1, 73, n2 + 19 - 1);
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 8, 3, false);
         this.getNextComponentX((StructureNetherBridgePieces.Start)structureComponent, list, rand, 3, 8, false);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)structureComponent, list, rand, 3, 8, false);
      }

      public Crossing3(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }
   }

   public static class PieceWeight {
      public Class weightClass;
      public final int weight;
      public int placeCount;
      public int maxPlaceCount;
      public boolean allowInRow;

      public boolean doPlace(int n) {
         return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
      }

      public PieceWeight(Class clazz, int n, int n2) {
         this(clazz, n, n2, false);
      }

      public PieceWeight(Class clazz, int n, int n2, boolean flag) {
         this.weightClass = clazz;
         this.weight = n;
         this.maxPlaceCount = n2;
         this.allowInRow = flag;
      }

      public boolean isValid() {
         return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
      }
   }

   public static class NetherStalkRoom extends StructureNetherBridgePieces.Piece {
      public NetherStalkRoom() {
      }

      public static NetherStalkRoom createPiece(List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, -5, -3, 0, 13, 14, 13, facing);
         return isAboveGround(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new NetherStalkRoom(n4, rand, structureBoundingBox, facing)
            : null;
      }

      public NetherStalkRoom(int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 5, 3, true);
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)structureComponent, list, rand, 5, 11, true);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         this.fillWithBlocks(world, structureBoundingBox, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int i = 1; i <= 11; i += 2) {
            this.fillWithBlocks(
               world, structureBoundingBox, i, 10, 0, i, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, i, 10, 12, i, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 0, 10, i, 0, 11, i, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 12, 10, i, 12, 11, i, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), i, 13, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), i, 13, 12, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 0, 13, i, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick.getDefaultState(), 12, 13, i, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), i + 1, 13, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), i + 1, 13, 12, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, i + 1, structureBoundingBox);
            this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 12, 13, i + 1, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, structureBoundingBox);

         for (int j = 3; j <= 9; j += 2) {
            this.fillWithBlocks(
               world, structureBoundingBox, 1, 7, j, 1, 8, j, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
            this.fillWithBlocks(
               world, structureBoundingBox, 11, 7, j, 11, 8, j, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false
            );
         }

         int metadataWithOffset = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 3);

         for (int k = 0; k <= 6; k++) {
            int n = k + 4;

            for (int m = 5; m <= 7; m++) {
               this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset), m, 5 + k, n, structureBoundingBox);
            }

            if (n >= 5 && n <= 8) {
               this.fillWithBlocks(world, structureBoundingBox, 5, 5, n, 7, k + 4, n, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            } else if (n >= 9 && n <= 10) {
               this.fillWithBlocks(world, structureBoundingBox, 5, 8, n, 7, k + 4, n, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            }

            if (k >= 1) {
               this.fillWithBlocks(world, structureBoundingBox, 5, 6 + k, n, 7, 9 + k, n, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
         }

         for (int idx = 5; idx <= 7; idx++) {
            this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset), idx, 12, 11, structureBoundingBox);
         }

         this.fillWithBlocks(world, structureBoundingBox, 5, 6, 7, 5, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 6, 7, 7, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 13, 12, 7, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 2, 3, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 9, 3, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 5, 4, 2, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 5, 2, 10, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 5, 9, 10, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 5, 4, 10, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         int metadataWithOffset2 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 0);
         int metadataWithOffset3 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 1);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset3), 4, 5, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset3), 4, 5, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset3), 4, 5, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset3), 4, 5, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset2), 8, 5, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset2), 8, 5, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset2), 8, 5, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.nether_brick_stairs.getStateFromMeta(metadataWithOffset2), 8, 5, 10, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 3, 4, 4, 4, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 4, 4, 9, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 5, 4, 4, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 5, 4, 9, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int index = 4; index <= 8; index++) {
            for (int i2 = 0; i2 <= 2; i2++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), index, -1, i2, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), index, -1, 12 - i2, structureBoundingBox);
            }
         }

         for (int i3 = 0; i3 <= 2; i3++) {
            for (int i4 = 4; i4 <= 8; i4++) {
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), i3, -1, i4, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.nether_brick.getDefaultState(), 12 - i3, -1, i4, structureBoundingBox);
            }
         }

         return true;
      }
   }
}
