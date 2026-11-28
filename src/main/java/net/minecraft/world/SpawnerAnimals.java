package net.minecraft.world;

import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;

public final class SpawnerAnimals {
   private static final int MOB_COUNT_DIV = (int)Math.pow(17.0, 2.0);
   private final Set eligibleChunksForSpawning = Sets.newHashSet();
   private final Map mapSampleEntitiesByClass = new HashMap();
   private int lastPlayerChunkX = Integer.MAX_VALUE;
   private int lastPlayerChunkZ = Integer.MAX_VALUE;
   private int countChunkPos;

   public static boolean canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType spawnPlacementType, World world, BlockPos pos) {
      if (!world.getWorldBorder().contains(pos)) {
         return false;
      } else if (spawnPlacementType == null) {
         return false;
      } else {
         Block block = world.getBlockState(pos).getBlock();
         if (spawnPlacementType == EntityLiving.SpawnPlacementType.IN_WATER) {
            return block.getMaterial().isLiquid()
               && world.getBlockState(pos.down()).getBlock().getMaterial().isLiquid()
               && !world.getBlockState(pos.up()).getBlock().isNormalCube();
         } else {
            if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
               return false;
            } else {
               Block block2 = world.getBlockState2(pos.getX(), pos.getY() - 1, pos.getZ()).getBlock();
               return (block2 != Blocks.bedrock && block2 != Blocks.barrier) && !block.isNormalCube() && !block.getMaterial().isLiquid() && !world.getBlockState(pos.up()).getBlock().isNormalCube();
            }
         }
      }
   }

   private static BlockPosM getRandomChunkPosition(World world, int n, int n2, BlockPosM blockPosM) {
      Chunk chunkFromChunkCoords = world.getChunkFromChunkCoords(n, n2);
      int n3 = n * 16 + world.rand.nextInt(16);
      int n4 = n2 * 16 + world.rand.nextInt(16);
      int n5 = MathHelper.roundUp(chunkFromChunkCoords.getHeightValue(n3 & 15, n4 & 15) + 1, 16);
      blockPosM.setXyz(n3, world.rand.nextInt(n5 > 0 ? n5 : chunkFromChunkCoords.getTopFilledSegment() + 16 - 1), n4);
      return blockPosM;
   }

   public int findChunksForSpawning(WorldServer world, boolean flag, boolean flag2, boolean flag3) {
      if (!flag && !flag2) {
         return 0;
      } else {
         boolean flag4 = true;
         EntityPlayer player = null;
         if (world.playerEntities.size() == 1) {
            player = (EntityPlayer)world.playerEntities.get(0);
            if (this.eligibleChunksForSpawning.size() > 0 && player != null && player.chunkCoordX == this.lastPlayerChunkX && player.chunkCoordZ == this.lastPlayerChunkZ) {
               flag4 = false;
            }
         }

         if (flag4) {
            this.eligibleChunksForSpawning.clear();
            int n = 0;

            for (EntityPlayer player2 : (Iterable<EntityPlayer>)(world.playerEntities)) {
               if (!player2.isSpectator()) {
                  int n2 = MathHelper.floor_double(player2.posX / 16.0);
                  int n3 = MathHelper.floor_double(player2.posZ / 16.0);
                  byte b = 8;

                  for (int i = -b; i <= b; i++) {
                     for (int j = -b; j <= b; j++) {
                        boolean flag5 = i == -b || i == b || j == -b || j == b;
                        ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(i + n2, j + n3);
                        if (!this.eligibleChunksForSpawning.contains(chunkCoordIntPair)) {
                           n++;
                           if (!flag5 && world.getWorldBorder().contains(chunkCoordIntPair)) {
                              this.eligibleChunksForSpawning.add(chunkCoordIntPair);
                           }
                        }
                     }
                  }
               }
            }

            this.countChunkPos = n;
            if (player != null) {
               this.lastPlayerChunkX = player.chunkCoordX;
               this.lastPlayerChunkZ = player.chunkCoordZ;
            }
         }

         int n4 = 0;
         BlockPos spawnPoint = world.getSpawnPoint();
         BlockPosM blockPosM = new BlockPosM(0, 0, 0);
         new BlockPos.MutableBlockPos();

         for (EnumCreatureType enumCreatureType : EnumCreatureType.values()) {
            if ((!enumCreatureType.getPeacefulCreature() || flag2) && (enumCreatureType.getPeacefulCreature() || flag) && (!enumCreatureType.getAnimal() || flag3)) {
               if (world.countEntities(enumCreatureType.getCreatureClass()) <= (enumCreatureType.getMaxNumberOfCreature() * this.countChunkPos / MOB_COUNT_DIV)) {
                  outerLoop:
                  for (ChunkCoordIntPair chunkCoordIntPair2 : (Iterable<ChunkCoordIntPair>)(this.eligibleChunksForSpawning)) {
                     BlockPosM randomChunkPosition = getRandomChunkPosition(world, chunkCoordIntPair2.chunkXPos, chunkCoordIntPair2.chunkZPos, blockPosM);
                     int x = randomChunkPosition.getX();
                     int y = randomChunkPosition.getY();
                     int z = randomChunkPosition.getZ();
                     if (!world.getBlockState(randomChunkPosition).getBlock().isNormalCube()) {
                        int n7 = 0;

                        for (int k = 0; k < 3; k++) {
                           int n8 = x;
                           int n9 = y;
                           int n10 = z;
                           byte b2 = 6;
                           BiomeGenBase.SpawnListEntry spawnListEntry = null;
                           IEntityLivingData entityLivingData = null;

                           for (int m = 0; m < 4; m++) {
                              n8 += world.rand.nextInt(b2) - world.rand.nextInt(b2);
                              n9 += world.rand.nextInt(1) - world.rand.nextInt(1);
                              n10 += world.rand.nextInt(b2) - world.rand.nextInt(b2);
                              BlockPos pos = new BlockPos(n8, n9, n10);
                              float f = (float)n8 + 0.5F;
                              float f2 = (float)n10 + 0.5F;
                              if (!world.isAnyPlayerWithinRangeAt((double)f, (double)n9, (double)f2, 24.0)
                                 && spawnPoint.distanceSq((double)f, (double)n9, (double)f2) >= 576.0) {
                                 if (spawnListEntry == null) {
                                    spawnListEntry = world.getSpawnListEntryForTypeAt(enumCreatureType, pos);
                                    if (spawnListEntry == null) {
                                       break;
                                    }
                                 }

                                 if (world.canCreatureTypeSpawnHere(enumCreatureType, spawnListEntry, pos)
                                    && canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.getPlacementForEntity(spawnListEntry.entityClass), world, pos)) {
                                    EntityLiving entityLiving;
                                    try {
                                       entityLiving = (EntityLiving)this.mapSampleEntitiesByClass.get(spawnListEntry.entityClass);
                                       if (entityLiving == null) {
                                          entityLiving = (EntityLiving)spawnListEntry.entityClass.getConstructor(World.class).newInstance(world);
                                          this.mapSampleEntitiesByClass.put(spawnListEntry.entityClass, entityLiving);
                                       }
                                    } catch (Exception e) {
                                       e.printStackTrace();
                                       return n4;
                                    }

                                    entityLiving.setLocationAndAngles((double)f, (double)n9, (double)f2, world.rand.nextFloat() * 360.0F, 0.0F);
                                    if (entityLiving.getCanSpawnHere() && entityLiving.isNotColliding()) {
                                       this.mapSampleEntitiesByClass.remove(spawnListEntry.entityClass);
                                       entityLivingData = entityLiving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityLiving)), entityLivingData);
                                       if (entityLiving.isNotColliding()) {
                                          n7++;
                                          world.spawnEntityInWorld(entityLiving);
                                       }

                                       if (n7 >= entityLiving.getMaxSpawnedInChunk()) {
                                          continue outerLoop;
                                       }
                                    }

                                    n4 += n7;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return n4;
      }
   }

   public static void performWorldGenSpawning(World world, BiomeGenBase biomeGenBase, int n, int n2, int n3, int n4, Random rand) {
      List spawnableList = biomeGenBase.getSpawnableList(EnumCreatureType.CREATURE);
      if (!spawnableList.isEmpty()) {
         while (rand.nextFloat() < biomeGenBase.getSpawningChance()) {
            BiomeGenBase.SpawnListEntry randomItem = (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(world.rand, spawnableList);
            int n5 = randomItem.minGroupCount + rand.nextInt(1 + randomItem.maxGroupCount - randomItem.minGroupCount);
            IEntityLivingData entityLivingData = null;
            int n6 = n + rand.nextInt(n3);
            int n7 = n2 + rand.nextInt(n4);
            int n8 = n6;
            int n9 = n7;

            for (int i = 0; i < n5; i++) {
               boolean flag = false;

               for (int j = 0; !flag && j < 4; j++) {
                  BlockPos topSolidOrLiquidBlock = world.getTopSolidOrLiquidBlock(new BlockPos(n6, 0, n7));
                  if (canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, world, topSolidOrLiquidBlock)) {
                     EntityLiving entityLiving;
                     try {
                        entityLiving = (EntityLiving)randomItem.entityClass.getConstructor(World.class).newInstance(world);
                     } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                     }

                     entityLiving.setLocationAndAngles(
                        (double)((float)n6 + 0.5F), (double)topSolidOrLiquidBlock.getY(), (double)((float)n7 + 0.5F), rand.nextFloat() * 360.0F, 0.0F
                     );
                     world.spawnEntityInWorld(entityLiving);
                     entityLivingData = entityLiving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityLiving)), entityLivingData);
                     flag = true;
                  }

                  n6 += rand.nextInt(5) - rand.nextInt(5);

                  for (n7 += rand.nextInt(5) - rand.nextInt(5);
                     n6 < n || n6 >= n + n3 || n7 < n2 || n7 >= n2 + n3;
                     n7 = n9 + rand.nextInt(5) - rand.nextInt(5)
                  ) {
                     n6 = n8 + rand.nextInt(5) - rand.nextInt(5);
                  }
               }
            }
         }
      }
   }

   private static BlockPos getRandomChunkPosition(World world, int n, int n2) {
      Chunk chunkFromChunkCoords = world.getChunkFromChunkCoords(n, n2);
      int n3 = n * 16 + world.rand.nextInt(16);
      int n4 = n2 * 16 + world.rand.nextInt(16);
      int n5 = MathHelper.roundUp(chunkFromChunkCoords.getHeight(new BlockPos(n3, 0, n4)) + 1, 16);
      int n6 = world.rand.nextInt(n5 > 0 ? n5 : chunkFromChunkCoords.getTopFilledSegment() + 16 - 1);
      return new BlockPos(n3, n6, n4);
   }
}
