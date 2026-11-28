package net.minecraft.world.gen.structure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenVillage extends MapGenStructure {
   public static final List villageSpawnBiomes = Arrays.asList(BiomeGenBase.plains, BiomeGenBase.desert, BiomeGenBase.savanna);
   private int terrainType;
   private int distance = 32;
   private int minTownSeparation = 8;

   public MapGenVillage() {
   }

   @Override
   public String getStructureName() {
      return "Village";
   }

   public MapGenVillage(Map map) {
      this();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (((String)entry.getKey()).equals("size")) {
            this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.terrainType, 0);
         } else if (((String)entry.getKey()).equals("distance")) {
            this.distance = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.distance, this.minTownSeparation + 1);
         }
      }
   }

   @Override
   public boolean canSpawnStructureAtCoords(int n, int n2) {
      int n3 = n;
      int n4 = n2;
      if (n < 0) {
         n -= this.distance - 1;
      }

      if (n2 < 0) {
         n2 -= this.distance - 1;
      }

      int n5 = n / this.distance;
      int n6 = n2 / this.distance;
      Random rand2 = this.worldObj.setRandomSeed(n5, n6, 10387312);
      n5 *= this.distance;
      n6 *= this.distance;
      n5 += rand2.nextInt(this.distance - this.minTownSeparation);
      n6 += rand2.nextInt(this.distance - this.minTownSeparation);
      if (n3 == n5 && n4 == n6) {
         if (this.worldObj.getWorldChunkManager().areBiomesViable(n3 * 16 + 8, n4 * 16 + 8, 0, villageSpawnBiomes)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public StructureStart getStructureStart(int n, int n2) {
      return new MapGenVillage.Start(this.worldObj, this.rand, n, n2, this.terrainType);
   }


   public static class Start extends StructureStart {
      private boolean hasMoreThanTwoComponents;

      public Start() {
      }

      @Override
      public void writeToNBT(NBTTagCompound tag) {
         super.writeToNBT(tag);
         tag.setBoolean("Valid", this.hasMoreThanTwoComponents);
      }

      @Override
      public boolean isSizeableStructure() {
         return this.hasMoreThanTwoComponents;
      }

      @Override
      public void readFromNBT(NBTTagCompound tag) {
         super.readFromNBT(tag);
         this.hasMoreThanTwoComponents = tag.getBoolean("Valid");
      }

      public Start(World world, Random rand, int n, int n2, int n3) {
         super(n, n2);
         List list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, n3);
         StructureVillagePieces.Start start = new StructureVillagePieces.Start(world.getWorldChunkManager(), 0, rand, (n << 4) + 2, (n2 << 4) + 2, list, n3);
         this.components.add(start);
         start.buildComponent(start, this.components, rand);
         List pendingRoads = start.pendingRoads;
         List pendingHouses = start.pendingHouses;

         while (!pendingRoads.isEmpty() || !pendingHouses.isEmpty()) {
            if (pendingRoads.isEmpty()) {
               ((StructureComponent)pendingHouses.remove(rand.nextInt(pendingHouses.size()))).buildComponent(start, this.components, rand);
            } else {
               ((StructureComponent)pendingRoads.remove(rand.nextInt(pendingRoads.size()))).buildComponent(start, this.components, rand);
            }
         }

         this.updateBoundingBox();
         int n6 = 0;

         for (StructureComponent structureComponent3 : (Iterable<StructureComponent>)(this.components)) {
            if (!(structureComponent3 instanceof StructureVillagePieces.Road)) {
               n6++;
            }
         }

         this.hasMoreThanTwoComponents = n6 > 2;
      }
   }
}
