package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;

public class MapGenNetherBridge extends MapGenStructure {
   private List spawnList = Lists.newArrayList();

   public List getSpawnList() {
      return this.spawnList;
   }

   @Override
   public boolean canSpawnStructureAtCoords(int n, int n2) {
      int n3 = n >> 4;
      int n4 = n2 >> 4;
      this.rand.setSeed((long)(n3 ^ n4 << 4) ^ this.worldObj.getSeed());
      this.rand.nextInt();
      return this.rand.nextInt(3) != 0 ? false : (n != (n3 << 4) + 4 + this.rand.nextInt(8) ? false : n2 == (n4 << 4) + 4 + this.rand.nextInt(8));
   }

   @Override
   public String getStructureName() {
      return "Fortress";
   }

   public MapGenNetherBridge() {
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   @Override
   public StructureStart getStructureStart(int n, int n2) {
      return new MapGenNetherBridge.Start(this.worldObj, this.rand, n, n2);
   }


   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World world, Random rand, int n, int n2) {
         super(n, n2);
         StructureNetherBridgePieces.Start start = new StructureNetherBridgePieces.Start(rand, (n << 4) + 2, (n2 << 4) + 2);
         this.components.add(start);
         start.buildComponent(start, this.components, rand);
         List pendingChildren = start.pendingChildren;

         while (!pendingChildren.isEmpty()) {
            ((StructureComponent)pendingChildren.remove(rand.nextInt(pendingChildren.size()))).buildComponent(start, this.components, rand);
         }

         this.updateBoundingBox();
         this.setRandomHeight(world, rand, 48, 70);
      }
   }
}
