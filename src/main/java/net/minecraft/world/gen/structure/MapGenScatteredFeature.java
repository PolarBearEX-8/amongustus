package net.minecraft.world.gen.structure;

import net.minecraft.world.World;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenScatteredFeature extends MapGenStructure {
   private static final List biomelist = Arrays.asList(
      BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland
   );
   private List scatteredFeatureSpawnList = Lists.newArrayList();
   private int maxDistanceBetweenScatteredFeatures = 32;
   private int minDistanceBetweenScatteredFeatures = 8;

   public MapGenScatteredFeature() {
      this.scatteredFeatureSpawnList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
   }

   public List getScatteredFeatureSpawnList() {
      return this.scatteredFeatureSpawnList;
   }

   public MapGenScatteredFeature(Map map) {
      this();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         if (((String)entry.getKey()).equals("distance")) {
            this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax(
               (String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1
            );
         }
      }
   }

   @Override
   public String getStructureName() {
      return "Temple";
   }

   @Override
   public StructureStart getStructureStart(int n, int n2) {
      return new MapGenScatteredFeature.Start(this.worldObj, this.rand, n, n2);
   }

   public boolean isSwampHut(BlockPos pos) {
      StructureStart structureAt = this.getStructureAt(pos);
      if (structureAt != null && structureAt instanceof MapGenScatteredFeature.Start && !structureAt.components.isEmpty()) {
         return ((StructureComponent)structureAt.components.getFirst()) instanceof ComponentScatteredFeaturePieces.SwampHut;
      } else {
         return false;
      }
   }

   @Override
   public boolean canSpawnStructureAtCoords(int n, int n2) {
      int n3 = n;
      int n4 = n2;
      if (n < 0) {
         n -= this.maxDistanceBetweenScatteredFeatures - 1;
      }

      if (n2 < 0) {
         n2 -= this.maxDistanceBetweenScatteredFeatures - 1;
      }

      int n5 = n / this.maxDistanceBetweenScatteredFeatures;
      int n6 = n2 / this.maxDistanceBetweenScatteredFeatures;
      Random rand2 = this.worldObj.setRandomSeed(n5, n6, 14357617);
      n5 *= this.maxDistanceBetweenScatteredFeatures;
      n6 *= this.maxDistanceBetweenScatteredFeatures;
      n5 += rand2.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
      n6 += rand2.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
      if (n3 == n5 && n4 == n6) {
         BiomeGenBase biomeGenerator = this.worldObj.getWorldChunkManager().getBiomeGenerator(new BlockPos(n3 * 16 + 8, 0, n4 * 16 + 8));
         if (biomeGenerator == null) {
            return false;
         }

         for (BiomeGenBase biomeGenBase : (Iterable<BiomeGenBase>)(biomelist)) {
            if (biomeGenerator == biomeGenBase) {
               return true;
            }
         }
      }

      return false;
   }


   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World world, Random rand, int n, int n2) {
         super(n, n2);
         BiomeGenBase biomeGenForCoords = world.getBiomeGenForCoords(new BlockPos(n * 16 + 8, 0, n2 * 16 + 8));
         if (biomeGenForCoords == BiomeGenBase.jungle || biomeGenForCoords == BiomeGenBase.jungleHills) {
            ComponentScatteredFeaturePieces.JunglePyramid junglePyramid = new ComponentScatteredFeaturePieces.JunglePyramid(rand, n * 16, n2 * 16);
            this.components.add(junglePyramid);
         } else if (biomeGenForCoords == BiomeGenBase.swampland) {
            ComponentScatteredFeaturePieces.SwampHut swampHut = new ComponentScatteredFeaturePieces.SwampHut(rand, n * 16, n2 * 16);
            this.components.add(swampHut);
         } else if (biomeGenForCoords == BiomeGenBase.desert || biomeGenForCoords == BiomeGenBase.desertHills) {
            ComponentScatteredFeaturePieces.DesertPyramid desertPyramid = new ComponentScatteredFeaturePieces.DesertPyramid(rand, n * 16, n2 * 16);
            this.components.add(desertPyramid);
         }

         this.updateBoundingBox();
      }
   }
}
