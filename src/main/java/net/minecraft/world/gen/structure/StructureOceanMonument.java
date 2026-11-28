package net.minecraft.world.gen.structure;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureOceanMonument extends MapGenStructure {
   private int spacing = 32;
   private int separation = 5;
   public static final List WATER_BIOMES = Arrays.asList(
      BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver
   );
   private static final List MONUMENT_ENEMIES = Lists.newArrayList();

   @Override
   public String getStructureName() {
      return "Monument";
   }

   static {
      MONUMENT_ENEMIES.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
   }

   public StructureOceanMonument(Map map) {
      this();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (((String)entry.getKey()).equals("spacing")) {
            this.spacing = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.spacing, 1);
         } else if (((String)entry.getKey()).equals("separation")) {
            this.separation = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.separation, 1);
         }
      }
   }

   public StructureOceanMonument() {
   }

   public List getMonumentEnemies() {
      return MONUMENT_ENEMIES;
   }

   @Override
   public boolean canSpawnStructureAtCoords(int n, int n2) {
      int n3 = n;
      int n4 = n2;
      if (n < 0) {
         n -= this.spacing - 1;
      }

      if (n2 < 0) {
         n2 -= this.spacing - 1;
      }

      int n5 = n / this.spacing;
      int n6 = n2 / this.spacing;
      Random rand2 = this.worldObj.setRandomSeed(n5, n6, 10387313);
      n5 *= this.spacing;
      n6 *= this.spacing;
      n5 += (rand2.nextInt(this.spacing - this.separation) + rand2.nextInt(this.spacing - this.separation)) / 2;
      n6 += (rand2.nextInt(this.spacing - this.separation) + rand2.nextInt(this.spacing - this.separation)) / 2;
      if (n3 == n5 && n4 == n6) {
         if (this.worldObj.getWorldChunkManager().getBiome(n3 * 16 + 8, n4 * 16 + 8, (BiomeGenBase)null) != BiomeGenBase.deepOcean) {
            return false;
         }

         if (this.worldObj.getWorldChunkManager().areBiomesViable(n3 * 16 + 8, n4 * 16 + 8, 29, WATER_BIOMES)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public StructureStart getStructureStart(int n, int n2) {
      return new StructureOceanMonument.StartMonument(this.worldObj, this.rand, n, n2);
   }


   public static class StartMonument extends StructureStart {
      private Set processed = Sets.newHashSet();
      private boolean wasCreated;

      @Override
      public void notifyPostProcessAt(ChunkCoordIntPair chunkCoordIntPair) {
         super.notifyPostProcessAt(chunkCoordIntPair);
         this.processed.add(chunkCoordIntPair);
      }

      @Override
      public void readFromNBT(NBTTagCompound tag) {
         super.readFromNBT(tag);
         if (tag.hasKey("Processed", 9)) {
            NBTTagList tagList = tag.getTagList("Processed", 10);

            for (int i = 0; i < tagList.tagCount(); i++) {
               NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
               this.processed.add(new ChunkCoordIntPair(compoundTagAt.getInteger("X"), compoundTagAt.getInteger("Z")));
            }
         }
      }

      @Override
      public void generateStructure(World world, Random rand, StructureBoundingBox structureBoundingBox) {
         if (!this.wasCreated) {
            this.components.clear();
            this.create(world, rand, this.getChunkPosX(), this.getChunkPosZ());
         }

         super.generateStructure(world, rand, structureBoundingBox);
      }

      @Override
      public boolean isValidForPostProcess(ChunkCoordIntPair chunkCoordIntPair) {
         return this.processed.contains(chunkCoordIntPair) ? false : super.isValidForPostProcess(chunkCoordIntPair);
      }

      @Override
      public void writeToNBT(NBTTagCompound tag) {
         super.writeToNBT(tag);
         NBTTagList tagList = new NBTTagList();

         for (ChunkCoordIntPair chunkCoordIntPair : (Iterable<ChunkCoordIntPair>)(this.processed)) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setInteger("X", chunkCoordIntPair.chunkXPos);
            tag2.setInteger("Z", chunkCoordIntPair.chunkZPos);
            tagList.appendTag(tag2);
         }

         tag.setTag("Processed", tagList);
      }

      private void create(World world, Random rand, int n, int n2) {
         rand.setSeed(world.getSeed());
         rand.setSeed(((long)n * rand.nextLong()) ^ ((long)n2 * rand.nextLong()) ^ world.getSeed());
         int n3 = n * 16 + 8 - 29;
         int n4 = n2 * 16 + 8 - 29;
         EnumFacing facing = EnumFacing.Plane.HORIZONTAL.random(rand);
         this.components.add(new StructureOceanMonumentPieces.MonumentBuilding(rand, n3, n4, facing));
         this.updateBoundingBox();
         this.wasCreated = true;
      }

      public StartMonument(World world, Random rand, int n, int n2) {
         super(n, n2);
         this.create(world, rand, n, n2);
      }

      public StartMonument() {
      }
   }
}
