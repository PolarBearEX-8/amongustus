package net.minecraft.world.gen.structure;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class StructureVillagePieces {

   private static StructureVillagePieces.Village generateComponent(
      StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
   ) {
      int n5 = updatePieceWeight(start.structureVillageWeightedPieceList);
      if (n5 <= 0) {
         return null;
      } else {
         int n6 = 0;

         while (n6 < 5) {
            n6++;
            int n7 = rand.nextInt(n5);

            for (StructureVillagePieces.PieceWeight pieceWeight : (Iterable<StructureVillagePieces.PieceWeight>)(start.structureVillageWeightedPieceList)) {
               n7 -= pieceWeight.villagePieceWeight;
               if (n7 < 0) {
                  if (!pieceWeight.canSpawnMoreVillagePiecesOfType(n4)
                     || pieceWeight == start.structVillagePieceWeight && start.structureVillageWeightedPieceList.size() > 1) {
                     break;
                  }

                  StructureVillagePieces.Village village = findAndCreateComponentFactory(start, pieceWeight, list, rand, n, n2, n3, facing, n4);
                  if (village != null) {
                     pieceWeight.villagePiecesSpawned++;
                     start.structVillagePieceWeight = pieceWeight;
                     if (!pieceWeight.canSpawnMoreVillagePieces()) {
                        start.structureVillageWeightedPieceList.remove(pieceWeight);
                     }

                     return village;
                  }
               }
            }
         }

         StructureBoundingBox structureBoundingBox = StructureVillagePieces.Torch.findPieceBox(start, list, rand, n, n2, n3, facing);
         return structureBoundingBox != null ? new StructureVillagePieces.Torch(start, n4, rand, structureBoundingBox, facing) : null;
      }
   }

   private static int updatePieceWeight(List list) {
      boolean flag = false;
      int n = 0;

      for (StructureVillagePieces.PieceWeight pieceWeight : (Iterable<StructureVillagePieces.PieceWeight>)(list)) {
         if (pieceWeight.villagePiecesLimit > 0 && pieceWeight.villagePiecesSpawned < pieceWeight.villagePiecesLimit) {
            flag = true;
         }

         n += pieceWeight.villagePieceWeight;
      }

      return flag ? n : -1;
   }

   private static StructureVillagePieces.Village findAndCreateComponentFactory(
      StructureVillagePieces.Start start,
      StructureVillagePieces.PieceWeight pieceWeight,
      List list,
      Random rand,
      int n,
      int n2,
      int n3,
      EnumFacing facing,
      int n4
   ) {
      Class villagePieceClass = pieceWeight.villagePieceClass;
      Object obj = null;
      if (villagePieceClass == StructureVillagePieces.House4Garden.class) {
         obj = StructureVillagePieces.House4Garden.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.Church.class) {
         obj = StructureVillagePieces.Church.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.House1.class) {
         obj = StructureVillagePieces.House1.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.WoodHut.class) {
         obj = StructureVillagePieces.WoodHut.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.Hall.class) {
         obj = StructureVillagePieces.Hall.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.Field1.class) {
         obj = StructureVillagePieces.Field1.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.Field2.class) {
         obj = StructureVillagePieces.Field2.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.House2.class) {
         obj = StructureVillagePieces.House2.createPiece(start, list, rand, n, n2, n3, facing, n4);
      } else if (villagePieceClass == StructureVillagePieces.House3.class) {
         obj = StructureVillagePieces.House3.createPiece(start, list, rand, n, n2, n3, facing, n4);
      }

      return (StructureVillagePieces.Village)obj;
   }

   public static List getStructureVillageWeightedPieceList(Random rand, int n) {
      ArrayList list = Lists.newArrayList();
      list.add(
         new StructureVillagePieces.PieceWeight(StructureVillagePieces.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(rand, 2 + n, 4 + n * 2))
      );
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Church.class, 20, MathHelper.getRandomIntegerInRange(rand, 0 + n, 1 + n)));
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House1.class, 20, MathHelper.getRandomIntegerInRange(rand, 0 + n, 2 + n)));
      list.add(
         new StructureVillagePieces.PieceWeight(StructureVillagePieces.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + n, 5 + n * 3))
      );
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Hall.class, 15, MathHelper.getRandomIntegerInRange(rand, 0 + n, 2 + n)));
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Field1.class, 3, MathHelper.getRandomIntegerInRange(rand, 1 + n, 4 + n)));
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Field2.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + n, 4 + n * 2)));
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House2.class, 15, MathHelper.getRandomIntegerInRange(rand, 0, 1 + n)));
      list.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House3.class, 8, MathHelper.getRandomIntegerInRange(rand, 0 + n, 3 + n * 2)));
      Iterator it = list.iterator();

      while (it.hasNext()) {
         if (((StructureVillagePieces.PieceWeight)it.next()).villagePiecesLimit == 0) {
            it.remove();
         }
      }

      return list;
   }

   public static void registerVillagePieces() {
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House1.class, "ViBH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Field1.class, "ViDF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Field2.class, "ViF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Torch.class, "ViL");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Hall.class, "ViPH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House4Garden.class, "ViSH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.WoodHut.class, "ViSmH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Church.class, "ViST");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House2.class, "ViS");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Start.class, "ViStart");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Path.class, "ViSR");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House3.class, "ViTRH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Well.class, "ViW");
   }

   private static StructureComponent generateAndAddComponent(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      if (n4 > 50) {
         return null;
      } else if (Math.abs(n - start.getBoundingBox().minX) <= 112 && Math.abs(n3 - start.getBoundingBox().minZ) <= 112) {
         StructureVillagePieces.Village village = generateComponent(start, list, rand, n, n2, n3, facing, n4 + 1);
         if (village != null) {
            int n5 = (village.boundingBox.minX + village.boundingBox.maxX) / 2;
            int n6 = (village.boundingBox.minZ + village.boundingBox.maxZ) / 2;
            int n7 = village.boundingBox.maxX - village.boundingBox.minX;
            int n8 = village.boundingBox.maxZ - village.boundingBox.minZ;
            int n9 = n7 > n8 ? n7 : n8;
            if (start.getWorldChunkManager().areBiomesViable(n5, n6, n9 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               list.add(village);
               start.pendingHouses.add(village);
               return village;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static StructureComponent addVillageComponent(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      return generateAndAddComponent(start, list, rand, n, n2, n3, facing, n4);
   }

   public static StructureComponent addVillageRoadPiece(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      return generateAndAddRoadPiece(start, list, rand, n, n2, n3, facing, n4);
   }

   private static StructureComponent generateAndAddRoadPiece(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4) {
      if (n4 > 3 + start.terrainType) {
         return null;
      } else if (Math.abs(n - start.getBoundingBox().minX) <= 112 && Math.abs(n3 - start.getBoundingBox().minZ) <= 112) {
         StructureBoundingBox structureBoundingBox = StructureVillagePieces.Path.findPieceBox(start, list, rand, n, n2, n3, facing);
         if (structureBoundingBox != null && structureBoundingBox.minY > 10) {
            StructureVillagePieces.Path path = new StructureVillagePieces.Path(start, n4, rand, structureBoundingBox, facing);
            int n5 = (path.boundingBox.minX + path.boundingBox.maxX) / 2;
            int n6 = (path.boundingBox.minZ + path.boundingBox.maxZ) / 2;
            int n7 = path.boundingBox.maxX - path.boundingBox.minX;
            int n8 = path.boundingBox.maxZ - path.boundingBox.minZ;
            int n9 = n7 > n8 ? n7 : n8;
            if (start.getWorldChunkManager().areBiomesViable(n5, n6, n9 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               list.add(path);
               start.pendingRoads.add(path);
               return path;
            }
         }

         return null;
      } else {
         return null;
      }
   }

public static class Hall extends StructureVillagePieces.Village {
      @Override
      public int chooseProfession(int n, int n2) {
         return n == 0 ? 4 : super.chooseProfession(n, n2);
      }

      public static Hall createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 9, 7, 11, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Hall(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      public Hall() {
      }

      public Hall(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 0, 6, 8, 0, 10, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, 0, 6, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 6, 2, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 1, 6, 8, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 10, 7, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 0, 0, 8, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 5, 7, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 5, 7, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 4, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 0, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 0, 4, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 4, 3, structureBoundingBox);
         int metadataWithOffset = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
         int metadataWithOffset2 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

         for (int i = -1; i <= 2; i++) {
            for (int j = 0; j <= 8; j++) {
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset), j, 4 + i, i, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset2), j, 4 + i, 5 - i, structureBoundingBox);
            }
         }

         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 2, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 3, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 6, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 2, 1, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 1, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 3, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 5, 0, 1, 7, 0, 3, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
         this.setBlockState(world, Blocks.double_stone_slab.getDefaultState(), 6, 1, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.double_stone_slab.getDefaultState(), 6, 1, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
         if (this.getBlockStateFromPos(world, 2, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 2, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.air.getDefaultState(), 6, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 6, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 6, 3, 4, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 6, 1, 5, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

         for (int k = 0; k < 5; k++) {
            for (int m = 0; m < 9; m++) {
               this.clearCurrentPositionBlocksUpwards(world, m, 7, k, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), m, -1, k, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 4, 1, 2, 2);
         return true;
      }
   }

   public static class Path extends StructureVillagePieces.Road {
      private int length;

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("Length", this.length);
      }

      public Path() {
      }

      public static StructureBoundingBox findPieceBox(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         for (int i = 7 * MathHelper.getRandomIntegerInRange(rand, 3, 5); i >= 7; i -= 7) {
            StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 3, 3, i, facing);
            if (StructureComponent.findIntersecting(list, structureBoundingBox) == null) {
               return structureBoundingBox;
            }
         }

         return null;
      }

      public Path(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.length = Math.max(structureBoundingBox.getXSize(), structureBoundingBox.getZSize());
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         IBlockState state = this.getBiomeSpecificBlockState(Blocks.gravel.getDefaultState());
         IBlockState state2 = this.getBiomeSpecificBlockState(Blocks.cobblestone.getDefaultState());

         for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; i++) {
            for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; j++) {
               BlockPos pos = new BlockPos(i, 64, j);
               if (structureBoundingBox.isVecInside(pos)) {
                  pos = world.getTopSolidOrLiquidBlock(pos).down();
                  world.setBlockState(pos, state, 2);
                  world.setBlockState(pos.down(), state2, 2);
               }
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         boolean flag = false;

         for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5)) {
            StructureComponent nextComponentNN = this.getNextComponentNN((StructureVillagePieces.Start)structureComponent, list, rand, 0, i);
            if (nextComponentNN != null) {
               i += Math.max(nextComponentNN.boundingBox.getXSize(), nextComponentNN.boundingBox.getZSize());
               flag = true;
            }
         }

         for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5)) {
            StructureComponent nextComponentPP = this.getNextComponentPP((StructureVillagePieces.Start)structureComponent, list, rand, 0, j);
            if (nextComponentPP != null) {
               j += Math.max(nextComponentPP.boundingBox.getXSize(), nextComponentPP.boundingBox.getZSize());
               flag = true;
            }
         }

         if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY,
                     this.boundingBox.minZ,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
                  break;
               case SOUTH:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY,
                     this.boundingBox.maxZ - 2,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
                  break;
               case WEST:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.minX,
                     this.boundingBox.minY,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
                  break;
               case EAST:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.maxX - 2,
                     this.boundingBox.minY,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
            }
         }

         if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY,
                     this.boundingBox.minZ,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
                  break;
               case SOUTH:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY,
                     this.boundingBox.maxZ - 2,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
                  break;
               case WEST:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.minX,
                     this.boundingBox.minY,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
                  break;
               case EAST:
                  StructureVillagePieces.addVillageRoadPiece(
                     (StructureVillagePieces.Start)structureComponent,
                     list,
                     rand,
                     this.boundingBox.maxX - 2,
                     this.boundingBox.minY,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
            }
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.length = tag.getInteger("Length");
      }
   }

   public static abstract class Road extends StructureVillagePieces.Village {
      public Road() {
      }

      public Road(StructureVillagePieces.Start start, int n) {
         super(start, n);
      }
   }

   public static class Well extends StructureVillagePieces.Village {
      public Well(StructureVillagePieces.Start start, int n, Random rand, int n2, int n3) {
         super(start, n);
         this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);
         switch (this.coordBaseMode) {
            case NORTH:
            case SOUTH:
               this.boundingBox = new StructureBoundingBox(n2, 64, n3, n2 + 6 - 1, 78, n3 + 6 - 1);
               break;
            default:
               this.boundingBox = new StructureBoundingBox(n2, 64, n3, n2 + 6 - 1, 78, n3 + 6 - 1);
         }
      }

      @Override
      public void buildComponent(StructureComponent structureComponent, List list, Random rand) {
         StructureVillagePieces.addVillageRoadPiece(
            (StructureVillagePieces.Start)structureComponent,
            list,
            rand,
            this.boundingBox.minX - 1,
            this.boundingBox.maxY - 4,
            this.boundingBox.minZ + 1,
            EnumFacing.WEST,
            this.getComponentType()
         );
         StructureVillagePieces.addVillageRoadPiece(
            (StructureVillagePieces.Start)structureComponent,
            list,
            rand,
            this.boundingBox.maxX + 1,
            this.boundingBox.maxY - 4,
            this.boundingBox.minZ + 1,
            EnumFacing.EAST,
            this.getComponentType()
         );
         StructureVillagePieces.addVillageRoadPiece(
            (StructureVillagePieces.Start)structureComponent,
            list,
            rand,
            this.boundingBox.minX + 1,
            this.boundingBox.maxY - 4,
            this.boundingBox.minZ - 1,
            EnumFacing.NORTH,
            this.getComponentType()
         );
         StructureVillagePieces.addVillageRoadPiece(
            (StructureVillagePieces.Start)structureComponent,
            list,
            rand,
            this.boundingBox.minX + 1,
            this.boundingBox.maxY - 4,
            this.boundingBox.maxZ + 1,
            EnumFacing.SOUTH,
            this.getComponentType()
         );
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 4, 12, 4, Blocks.cobblestone.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 12, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 3, 12, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 12, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 3, 12, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 13, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 14, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 13, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 14, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 13, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 14, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 13, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 14, 4, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 1, 15, 1, 4, 15, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);

         for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
               if (j == 0 || j == 5 || i == 0 || i == 5) {
                  this.setBlockState(world, Blocks.gravel.getDefaultState(), j, 11, i, structureBoundingBox);
                  this.clearCurrentPositionBlocksUpwards(world, j, 12, i, structureBoundingBox);
               }
            }
         }

         return true;
      }

      public Well() {
      }
   }

   public static class Start extends StructureVillagePieces.Well {
      public WorldChunkManager worldChunkMngr;
      public boolean inDesert;
      public int terrainType;
      public StructureVillagePieces.PieceWeight structVillagePieceWeight;
      public List structureVillageWeightedPieceList;
      public List pendingHouses = Lists.newArrayList();
      public List pendingRoads = Lists.newArrayList();

      public WorldChunkManager getWorldChunkManager() {
         return this.worldChunkMngr;
      }

      public Start(WorldChunkManager worldChunkManager, int n, Random rand, int n2, int n3, List list, int n4) {
         super((Start)null, 0, rand, n2, n3);
         this.worldChunkMngr = worldChunkManager;
         this.structureVillageWeightedPieceList = list;
         this.terrainType = n4;
         BiomeGenBase biome = worldChunkManager.getBiome(n2, n3, BiomeGenBase.DEFAULT);
         this.inDesert = biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills;
         this.setIsDesertVillage(this.inDesert);
      }

      public Start() {
      }
   }

   public static class Torch extends StructureVillagePieces.Village {
      public Torch() {
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 0, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.wool.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, structureBoundingBox);
         boolean flag = this.coordBaseMode == EnumFacing.EAST || this.coordBaseMode == EnumFacing.NORTH;
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), flag ? 2 : 0, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 1, 3, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), flag ? 0 : 2, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 1, 3, -1, structureBoundingBox);
         return true;
      }

      public Torch(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public static StructureBoundingBox findPieceBox(StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 3, 4, 2, facing);
         return StructureComponent.findIntersecting(list, structureBoundingBox) != null ? null : structureBoundingBox;
      }
   }

   public static class Church extends StructureVillagePieces.Village {
      public Church(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 3, 3, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 1, 3, 9, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 3, 0, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 3, 10, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 4, 0, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 4, 4, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 8, 3, 4, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 4, 3, 10, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 5, 3, 5, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 9, 0, 4, 9, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 0, 4, 4, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 11, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 11, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 2, 11, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 2, 11, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 1, 1, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 1, 1, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 2, 1, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 3, 1, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 3, 1, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 1, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 3, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 1)), 1, 2, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 0)), 3, 2, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 3, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 3, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 6, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 7, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 6, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 7, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 6, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 7, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 6, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 7, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 3, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 3, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 3, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 2, 4, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), 1, 4, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), 3, 4, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 4, 5, structureBoundingBox);
         int metadataWithOffset = this.getMetadataWithOffset(Blocks.ladder, 4);

         for (int i = 1; i <= 9; i++) {
            this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 3, i, 3, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
         if (this.getBlockStateFromPos(world, 2, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 2, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBox);
         }

         for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 5; k++) {
               this.clearCurrentPositionBlocksUpwards(world, k, 12, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), k, -1, j, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 2, 1, 2, 1);
         return true;
      }

      public Church() {
      }

      @Override
      public int chooseProfession(int n, int n2) {
         return 2;
      }

      public static Church createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 5, 12, 9, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Church(start, n4, rand, structureBoundingBox, facing)
            : null;
      }
   }

   public static class Field1 extends StructureVillagePieces.Village {
      private Block cropTypeA;
      private Block cropTypeB;
      private Block cropTypeC;
      private Block cropTypeD;

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.cropTypeA = Block.getBlockById(tag.getInteger("CA"));
         this.cropTypeB = Block.getBlockById(tag.getInteger("CB"));
         this.cropTypeC = Block.getBlockById(tag.getInteger("CC"));
         this.cropTypeD = Block.getBlockById(tag.getInteger("CD"));
      }

      public Field1(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.cropTypeA = this.getRandomCropType(rand);
         this.cropTypeB = this.getRandomCropType(rand);
         this.cropTypeC = this.getRandomCropType(rand);
         this.cropTypeD = this.getRandomCropType(rand);
      }

      private Block getRandomCropType(Random rand) {
         switch (rand.nextInt(5)) {
            case 0:
               return Blocks.carrots;
            case 1:
               return Blocks.potatoes;
            default:
               return Blocks.wheat;
         }
      }

      public Field1() {
      }

      public static Field1 createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 13, 4, 9, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Field1(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 0, 1, 8, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 10, 0, 1, 11, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 12, 0, 0, 12, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 11, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 8, 11, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

         for (int i = 1; i <= 7; i++) {
            this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 1, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 2, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 4, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 5, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 7, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 8, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 10, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 11, 1, i, structureBoundingBox);
         }

         for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 13; k++) {
               this.clearCurrentPositionBlocksUpwards(world, k, 4, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.dirt.getDefaultState(), k, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
         tag.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
         tag.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
         tag.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
      }
   }

   public static class Field2 extends StructureVillagePieces.Village {
      private Block cropTypeA;
      private Block cropTypeB;

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 5, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 8, 5, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

         for (int i = 1; i <= 7; i++) {
            this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 1, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 2, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 4, 1, i, structureBoundingBox);
            this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 5, 1, i, structureBoundingBox);
         }

         for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 7; k++) {
               this.clearCurrentPositionBlocksUpwards(world, k, 4, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.dirt.getDefaultState(), k, -1, j, structureBoundingBox);
            }
         }

         return true;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
         tag.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
      }

      public Field2(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.cropTypeA = this.getRandomCropType(rand);
         this.cropTypeB = this.getRandomCropType(rand);
      }

      private Block getRandomCropType(Random rand) {
         switch (rand.nextInt(5)) {
            case 0:
               return Blocks.carrots;
            case 1:
               return Blocks.potatoes;
            default:
               return Blocks.wheat;
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.cropTypeA = Block.getBlockById(tag.getInteger("CA"));
         this.cropTypeB = Block.getBlockById(tag.getInteger("CB"));
      }

      public Field2() {
      }

      public static Field2 createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 7, 4, 9, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new Field2(start, n4, rand, structureBoundingBox, facing)
            : null;
      }
   }

   public static class House1 extends StructureVillagePieces.Village {
      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 7, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 8, 0, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 8, 5, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 6, 1, 8, 6, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 7, 2, 8, 7, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         int metadataWithOffset = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
         int metadataWithOffset2 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

         for (int i = -1; i <= 2; i++) {
            for (int j = 0; j <= 8; j++) {
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset), j, 6 + i, i, structureBoundingBox);
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset2), j, 6 + i, 5 - i, structureBoundingBox);
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 5, 8, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 1, 0, 8, 1, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 0, 0, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 5, 0, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 2, 5, 8, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 2, 0, 8, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 2, 1, 0, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 5, 7, 4, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 2, 1, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 7, 4, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 6, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 6, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 3, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 3, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 3, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 3, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 3, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 6, 2, 5, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 1, 7, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 4, 7, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 3, 4, 7, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 7, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 0)), 7, 1, 3, structureBoundingBox);
         int metadataWithOffset3 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset3), 6, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset3), 5, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset3), 4, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset3), 3, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 6, 1, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.wooden_pressure_plate.getDefaultState(), 6, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 1, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.wooden_pressure_plate.getDefaultState(), 4, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.crafting_table.getDefaultState(), 7, 1, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 1, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 1, 2, 0, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
         if (this.getBlockStateFromPos(world, 1, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 1, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, structureBoundingBox);
         }

         for (int k = 0; k < 6; k++) {
            for (int m = 0; m < 9; m++) {
               this.clearCurrentPositionBlocksUpwards(world, m, 9, k, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), m, -1, k, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 2, 1, 2, 1);
         return true;
      }

      public House1() {
      }

      public static House1 createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 9, 9, 6, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new House1(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      public House1(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public int chooseProfession(int n, int n2) {
         return 1;
      }
   }

   public static class House2 extends StructureVillagePieces.Village {
      private static final List villageBlacksmithChestContents = Lists.newArrayList(
         new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15),
            new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5),
            new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5),
            new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
            new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
         }
      );
      private boolean hasMadeChest;

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Chest", this.hasMadeChest);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 9, 0, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 0, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 0, 9, 5, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 0, 3, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 6, 0, 4, 6, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 3, 3, 1, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 2, 3, 3, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 3, 5, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 6, 5, 3, 6, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 1, 0, 5, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 9, 1, 0, 9, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 1, 4, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.setBlockState(world, Blocks.flowing_lava.getDefaultState(), 7, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.flowing_lava.getDefaultState(), 8, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 9, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 9, 2, 4, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 6, 1, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.furnace.getDefaultState(), 6, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.furnace.getDefaultState(), 6, 3, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.double_stone_slab.getDefaultState(), 8, 1, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 2, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 2, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 1, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 4, structureBoundingBox);
         if (!this.hasMadeChest && structureBoundingBox.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)))) {
            this.hasMadeChest = true;
            this.generateChestContents(world, structureBoundingBox, rand, 5, 1, 5, villageBlacksmithChestContents, 3 + rand.nextInt(6));
         }

         for (int i = 6; i <= 8; i++) {
            if (this.getBlockStateFromPos(world, i, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
               && this.getBlockStateFromPos(world, i, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
               this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), i, 0, -1, structureBoundingBox);
            }
         }

         for (int j = 0; j < 7; j++) {
            for (int k = 0; k < 10; k++) {
               this.clearCurrentPositionBlocksUpwards(world, k, 6, j, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), k, -1, j, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 7, 1, 1, 1);
         return true;
      }

      public static House2 createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 10, 6, 7, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new House2(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      public House2() {
      }

      public House2(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      @Override
      public int chooseProfession(int n, int n2) {
         return 3;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.hasMadeChest = tag.getBoolean("Chest");
      }
   }

   public static class House3 extends StructureVillagePieces.Village {
      public House3(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
      }

      public House3() {
      }

      public static House3 createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 9, 7, 12, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new House3(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 0, 5, 8, 0, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 8, 0, 0, 8, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 7, 2, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 5, 2, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 2, 0, 6, 2, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 0, 10, 7, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 2, 5, 2, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 4, 3, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 0, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 0, 4, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 4, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 4, 4, structureBoundingBox);
         int metadataWithOffset = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
         int metadataWithOffset2 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

         for (int i = -1; i <= 2; i++) {
            for (int j = 0; j <= 8; j++) {
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset), j, 4 + i, i, structureBoundingBox);
               if ((i > -1 || j <= 1) && (i > 0 || j <= 3) && (i > 1 || j <= 4 || j >= 6)) {
                  this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset2), j, 4 + i, 5 - i, structureBoundingBox);
               }
            }
         }

         this.fillWithBlocks(world, structureBoundingBox, 3, 4, 5, 3, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 7, 4, 2, 7, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 5, 4, 4, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 6, 5, 4, 6, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 5, 6, 3, 5, 6, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         int metadataWithOffset3 = this.getMetadataWithOffset(Blocks.oak_stairs, 0);

         for (int k = 4; k >= 1; k--) {
            this.setBlockState(world, Blocks.planks.getDefaultState(), k, 2 + k, 7 - k, structureBoundingBox);

            for (int m = 8 - k; m <= 10; m++) {
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset3), k, 2 + k, m, structureBoundingBox);
            }
         }

         int metadataWithOffset4 = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 6, 6, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 7, 5, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset4), 6, 6, 4, structureBoundingBox);

         for (int idx = 6; idx <= 8; idx++) {
            for (int index = 5; index <= 10; index++) {
               this.setBlockState(world, Blocks.oak_stairs.getStateFromMeta(metadataWithOffset4), idx, 12 - idx, index, structureBoundingBox);
            }
         }

         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 2, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 4, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 6, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 8, 2, 5, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 8, 2, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 8, 2, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 2, 2, 6, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 7, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 8, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 2, 2, 9, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 4, 4, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 5, 4, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 6, 4, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 5, 5, 10, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (this.getBlockStateFromPos(world, 2, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 2, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBox);
         }

         for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 9; i3++) {
               this.clearCurrentPositionBlocksUpwards(world, i3, 7, i2, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), i3, -1, i2, structureBoundingBox);
            }
         }

         for (int i4 = 5; i4 < 11; i4++) {
            for (int i5 = 2; i5 < 9; i5++) {
               this.clearCurrentPositionBlocksUpwards(world, i5, 7, i4, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), i5, -1, i4, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 4, 1, 2, 2);
         return true;
      }
   }

   public static abstract class Village extends StructureComponent {
      public int averageGroundLvl = -1;
      private int villagersSpawned;
      private boolean isDesertVillage;

      public void setIsDesertVillage(boolean flag) {
         this.isDesertVillage = flag;
      }

      public StructureComponent getNextComponentNN(StructureVillagePieces.Start start, List list, Random rand, int n, int n2) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
               case SOUTH:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX - 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.WEST,
                     this.getComponentType()
                  );
               case WEST:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
               case EAST:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ - 1,
                     EnumFacing.NORTH,
                     this.getComponentType()
                  );
            }
         }

         return null;
      }

      public Village() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         this.averageGroundLvl = tag.getInteger("HPos");
         this.villagersSpawned = tag.getInteger("VCount");
         this.isDesertVillage = tag.getBoolean("Desert");
      }

      public int chooseProfession(int n, int n2) {
         return n2;
      }

      @Override
      public void replaceAirAndLiquidDownwards(World world, IBlockState state, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
         super.replaceAirAndLiquidDownwards(world, this.getBiomeSpecificBlockState(state), n, n2, n3, structureBoundingBox);
      }

      public IBlockState getBiomeSpecificBlockState(IBlockState state) {
         if (this.isDesertVillage) {
            if (state.getBlock() == Blocks.log || state.getBlock() == Blocks.log2) {
               return Blocks.sandstone.getDefaultState();
            }

            if (state.getBlock() == Blocks.cobblestone) {
               return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
            }

            if (state.getBlock() == Blocks.planks) {
               return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
            }

            if (state.getBlock() == Blocks.oak_stairs) {
               return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
            }

            if (state.getBlock() == Blocks.stone_stairs) {
               return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
            }

            if (state.getBlock() == Blocks.gravel) {
               return Blocks.sandstone.getDefaultState();
            }
         }

         return state;
      }

      public StructureComponent getNextComponentPP(StructureVillagePieces.Start start, List list, Random rand, int n, int n2) {
         if (this.coordBaseMode != null) {
            switch (this.coordBaseMode) {
               case NORTH:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
               case SOUTH:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.maxX + 1,
                     this.boundingBox.minY + n,
                     this.boundingBox.minZ + n2,
                     EnumFacing.EAST,
                     this.getComponentType()
                  );
               case WEST:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
               case EAST:
                  return StructureVillagePieces.addVillageComponent(
                     start,
                     list,
                     rand,
                     this.boundingBox.minX + n2,
                     this.boundingBox.minY + n,
                     this.boundingBox.maxZ + 1,
                     EnumFacing.SOUTH,
                     this.getComponentType()
                  );
            }
         }

         return null;
      }

      public int getAverageGroundLevel(World world, StructureBoundingBox structureBoundingBox) {
         int n = 0;
         int n2 = 0;
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = this.boundingBox.minZ; i <= this.boundingBox.maxZ; i++) {
            for (int j = this.boundingBox.minX; j <= this.boundingBox.maxX; j++) {
               pos.set(j, 64, i);
               if (structureBoundingBox.isVecInside(pos)) {
                  n += Math.max(world.getTopSolidOrLiquidBlock(pos).getY(), world.provider.getAverageGroundLevel());
                  n2++;
               }
            }
         }

         return n2 == 0 ? -1 : n / n2;
      }

      @Override
      public void fillWithBlocks(
         World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4, int n5, int n6, IBlockState state, IBlockState state2, boolean flag
      ) {
         super.fillWithBlocks(world, structureBoundingBox, n, n2, n3, n4, n5, n6, this.getBiomeSpecificBlockState(state), this.getBiomeSpecificBlockState(state2), flag);
      }

      public Village(StructureVillagePieces.Start start, int n) {
         super(n);
         if (start != null) {
            this.isDesertVillage = start.inDesert;
         }
      }

      @Override
      public void setBlockState(World world, IBlockState state, int n, int n2, int n3, StructureBoundingBox structureBoundingBox) {
         super.setBlockState(world, this.getBiomeSpecificBlockState(state), n, n2, n3, structureBoundingBox);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         tag.setInteger("HPos", this.averageGroundLvl);
         tag.setInteger("VCount", this.villagersSpawned);
         tag.setBoolean("Desert", this.isDesertVillage);
      }

      public void spawnVillagers(World world, StructureBoundingBox structureBoundingBox, int n, int n2, int n3, int n4) {
         if (this.villagersSpawned < n4) {
            for (int i = this.villagersSpawned; i < n4; i++) {
               int xWithOffset = this.getXWithOffset(n + i, n3);
               int yWithOffset = this.getYWithOffset(n2);
               int zWithOffset = this.getZWithOffset(n + i, n3);
               if (!structureBoundingBox.isVecInside(new BlockPos(xWithOffset, yWithOffset, zWithOffset))) {
                  break;
               }

               this.villagersSpawned++;
               EntityVillager entityVillager = new EntityVillager(world);
               entityVillager.setLocationAndAngles((double)xWithOffset + 0.5, (double)yWithOffset, (double)zWithOffset + 0.5, 0.0F, 0.0F);
               entityVillager.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityVillager)), (IEntityLivingData)null);
               entityVillager.setProfession(this.chooseProfession(i, entityVillager.getProfession()));
               world.spawnEntityInWorld(entityVillager);
            }
         }
      }

      public static boolean canVillageGoDeeper(StructureBoundingBox structureBoundingBox) {
         return structureBoundingBox != null && structureBoundingBox.minY > 10;
      }
   }

   public static class WoodHut extends StructureVillagePieces.Village {
      private boolean isTallHouse;
      private int tablePosition;

      public static WoodHut createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 4, 6, 5, facing);
         return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null
            ? new WoodHut(start, n4, rand, structureBoundingBox, facing)
            : null;
      }

      public WoodHut() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setInteger("T", this.tablePosition);
         tag.setBoolean("C", this.isTallHouse);
      }

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 3, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 2, 0, 3, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
         if (this.isTallHouse) {
            this.fillWithBlocks(world, structureBoundingBox, 1, 4, 1, 2, 4, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         } else {
            this.fillWithBlocks(world, structureBoundingBox, 1, 5, 1, 2, 5, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         }

         this.setBlockState(world, Blocks.log.getDefaultState(), 1, 4, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 2, 4, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 1, 4, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 2, 4, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 4, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 0, 4, 3, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 3, 4, 1, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 3, 4, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.log.getDefaultState(), 3, 4, 3, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 0, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 0, 3, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 4, 0, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 4, 3, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 3, 1, 1, 3, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 4, 2, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 3, 2, 2, structureBoundingBox);
         if (this.tablePosition > 0) {
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), this.tablePosition, 1, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.wooden_pressure_plate.getDefaultState(), this.tablePosition, 2, 3, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.air.getDefaultState(), 1, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.air.getDefaultState(), 1, 2, 0, structureBoundingBox);
         this.placeDoorCurrentPosition(world, structureBoundingBox, rand, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
         if (this.getBlockStateFromPos(world, 1, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 1, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, structureBoundingBox);
         }

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
               this.clearCurrentPositionBlocksUpwards(world, j, 6, i, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), j, -1, i, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 1, 1, 2, 1);
         return true;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.tablePosition = tag.getInteger("T");
         this.isTallHouse = tag.getBoolean("C");
      }

      public WoodHut(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.isTallHouse = rand.nextBoolean();
         this.tablePosition = rand.nextInt(3);
      }
   }

   public static class PieceWeight {
      public Class villagePieceClass;
      public final int villagePieceWeight;
      public int villagePiecesSpawned;
      public int villagePiecesLimit;

      public boolean canSpawnMoreVillagePieces() {
         return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
      }

      public boolean canSpawnMoreVillagePiecesOfType(int n) {
         return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
      }

      public PieceWeight(Class clazz, int n, int n2) {
         this.villagePieceClass = clazz;
         this.villagePieceWeight = n;
         this.villagePiecesLimit = n2;
      }
   }

   public static class House4Garden extends StructureVillagePieces.Village {
      private boolean isRoofAccessible;

      @Override
      public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLvl < 0) {
               return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
         }

         this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 4, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 0, 4, 0, 4, 4, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 4, 1, 3, 4, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 0, 3, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 1, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.cobblestone.getDefaultState(), 4, 3, 4, structureBoundingBox);
         this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 4, 3, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 0, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 2, 2, 4, structureBoundingBox);
         this.setBlockState(world, Blocks.glass_pane.getDefaultState(), 4, 2, 2, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 1, 1, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 1, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 1, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 2, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 3, 3, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 3, 2, 0, structureBoundingBox);
         this.setBlockState(world, Blocks.planks.getDefaultState(), 3, 1, 0, structureBoundingBox);
         if (this.getBlockStateFromPos(world, 2, 0, -1, structureBoundingBox).getBlock().getMaterial() == Material.air
            && this.getBlockStateFromPos(world, 2, -1, -1, structureBoundingBox).getBlock().getMaterial() != Material.air) {
            this.setBlockState(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBox);
         }

         this.fillWithBlocks(world, structureBoundingBox, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (this.isRoofAccessible) {
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 0, 5, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 5, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 2, 5, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 3, 5, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 5, 0, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 0, 5, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 1, 5, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 2, 5, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 3, 5, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 5, 4, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 5, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 5, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 4, 5, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 0, 5, 1, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 0, 5, 2, structureBoundingBox);
            this.setBlockState(world, Blocks.oak_fence.getDefaultState(), 0, 5, 3, structureBoundingBox);
         }

         if (this.isRoofAccessible) {
            int metadataWithOffset = this.getMetadataWithOffset(Blocks.ladder, 3);
            this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 3, 1, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 3, 2, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 3, 3, 3, structureBoundingBox);
            this.setBlockState(world, Blocks.ladder.getStateFromMeta(metadataWithOffset), 3, 4, 3, structureBoundingBox);
         }

         this.setBlockState(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBox);

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
               this.clearCurrentPositionBlocksUpwards(world, j, 6, i, structureBoundingBox);
               this.replaceAirAndLiquidDownwards(world, Blocks.cobblestone.getDefaultState(), j, -1, i, structureBoundingBox);
            }
         }

         this.spawnVillagers(world, structureBoundingBox, 1, 1, 2, 1);
         return true;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound tag) {
         super.writeStructureToNBT(tag);
         tag.setBoolean("Terrace", this.isRoofAccessible);
      }

      public House4Garden(StructureVillagePieces.Start start, int n, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing) {
         super(start, n);
         this.coordBaseMode = facing;
         this.boundingBox = structureBoundingBox;
         this.isRoofAccessible = rand.nextBoolean();
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound tag) {
         super.readStructureFromNBT(tag);
         this.isRoofAccessible = tag.getBoolean("Terrace");
      }

      public static House4Garden createPiece(
         StructureVillagePieces.Start start, List list, Random rand, int n, int n2, int n3, EnumFacing facing, int n4
      ) {
         StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(n, n2, n3, 0, 0, 0, 5, 6, 5, facing);
         return StructureComponent.findIntersecting(list, structureBoundingBox) != null ? null : new House4Garden(start, n4, rand, structureBoundingBox, facing);
      }

      public House4Garden() {
      }
   }
}
