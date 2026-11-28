package net.minecraft.world.gen.structure;

import net.minecraft.world.World;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenStronghold extends MapGenStructure {
   private List allowedBiomes;
   private boolean ranBiomeCheck;
   private ChunkCoordIntPair[] structureCoords = new ChunkCoordIntPair[3];
   private double distance = 32.0;
   private int spread = 3;

   @Override
   public boolean canSpawnStructureAtCoords(int n, int n2) {
      if (!this.ranBiomeCheck) {
         Random rand2 = new Random();
         rand2.setSeed(this.worldObj.getSeed());
         double d = rand2.nextDouble() * Math.PI * 2.0;
         int n3 = 1;

         for (int i = 0; i < this.structureCoords.length; i++) {
            double d2 = (1.25 * (double)n3 + rand2.nextDouble()) * this.distance * (double)n3;
            int n4 = (int)Math.round(Math.cos(d) * d2);
            int n5 = (int)Math.round(Math.sin(d) * d2);
            BlockPos pos = this.worldObj.getWorldChunkManager().findBiomePosition((n4 << 4) + 8, (n5 << 4) + 8, 112, this.allowedBiomes, rand2);
            if (pos != null) {
               n4 = pos.getX() >> 4;
               n5 = pos.getZ() >> 4;
            }

            this.structureCoords[i] = new ChunkCoordIntPair(n4, n5);
            d += (Math.PI * 2) * (double)n3 / (double)this.spread;
            if (i == this.spread) {
               n3 += 2 + rand2.nextInt(5);
               this.spread = this.spread + 1 + rand2.nextInt(2);
            }
         }

         this.ranBiomeCheck = true;
      }

      for (ChunkCoordIntPair chunkCoordIntPair : this.structureCoords) {
         if (n == chunkCoordIntPair.chunkXPos && n2 == chunkCoordIntPair.chunkZPos) {
            return true;
         }
      }

      return false;
   }

   public MapGenStronghold() {
      this.allowedBiomes = Lists.newArrayList();

      for (BiomeGenBase biomeGenBase : BiomeGenBase.getBiomeGenArray()) {
         if (biomeGenBase != null && biomeGenBase.minHeight > 0.0F) {
            this.allowedBiomes.add(biomeGenBase);
         }
      }
   }

   @Override
   public StructureStart getStructureStart(int n, int n2) {
      MapGenStronghold.Start start = new MapGenStronghold.Start(this.worldObj, this.rand, n, n2);

      while (start.getComponents().isEmpty() || ((StructureStrongholdPieces.Stairs2)start.getComponents().get(0)).strongholdPortalRoom == null) {
         start = new MapGenStronghold.Start(this.worldObj, this.rand, n, n2);
      }

      return start;
   }

   @Override
   public List getCoordList() {
      ArrayList list = Lists.newArrayList();

      for (ChunkCoordIntPair chunkCoordIntPair : this.structureCoords) {
         if (chunkCoordIntPair != null) {
            list.add(chunkCoordIntPair.getCenterBlock(64));
         }
      }

      return list;
   }

   @Override
   public String getStructureName() {
      return "Stronghold";
   }

   public MapGenStronghold(Map map) {
      this();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (((String)entry.getKey()).equals("distance")) {
            this.distance = MathHelper.parseDoubleWithDefaultAndMax((String)entry.getValue(), this.distance, 1.0);
         } else if (((String)entry.getKey()).equals("count")) {
            this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.structureCoords.length, 1)];
         } else if (((String)entry.getKey()).equals("spread")) {
            this.spread = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.spread, 1);
         }
      }
   }


   public static class Start extends StructureStart {
      public Start(World world, Random rand, int n, int n2) {
         super(n, n2);
         StructureStrongholdPieces.prepareStructurePieces();
         StructureStrongholdPieces.Stairs2 stairs2 = new StructureStrongholdPieces.Stairs2(0, rand, (n << 4) + 2, (n2 << 4) + 2);
         this.components.add(stairs2);
         stairs2.buildComponent(stairs2, this.components, rand);
         List pendingChildren = stairs2.pendingChildren;

         while (!pendingChildren.isEmpty()) {
            ((StructureComponent)pendingChildren.remove(rand.nextInt(pendingChildren.size()))).buildComponent(stairs2, this.components, rand);
         }

         this.updateBoundingBox();
         this.markAvailableHeight(world, rand, 10);
      }

      public Start() {
      }
   }
}
