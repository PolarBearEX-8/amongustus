package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public abstract class MapGenStructure extends MapGenBase {
   public Map structureMap = Maps.newHashMap();
   private MapGenStructureData structureData;

   public StructureStart getStructureAt(BlockPos pos) {
      for (StructureStart structureStart : (Iterable<StructureStart>)(this.structureMap.values())) {
         if (structureStart.isSizeableStructure() && structureStart.getBoundingBox().isVecInside(pos)) {
            for (StructureComponent structureComponent : (Iterable<StructureComponent>)(structureStart.getComponents())) {
               if (structureComponent.getBoundingBox().isVecInside(pos)) {
                  return structureStart;
               }
            }
         }
      }

      return null;
   }

   public boolean isInsideStructure(BlockPos pos) {
      this.initializeStructureData(this.worldObj);
      return this.getStructureAt(pos) != null;
   }

   public abstract String getStructureName();

   @Override
   public final void recursiveGenerate(World world, int n, int n2, int n3, int n4, ChunkPrimer chunkPrimer) {
      this.initializeStructureData(world);
      if (!this.structureMap.containsKey(ChunkCoordIntPair.chunkXZ2Int(n, n2))) {
         this.rand.nextInt();

         try {
            if (this.canSpawnStructureAtCoords(n, n2)) {
               StructureStart structureStart = this.getStructureStart(n, n2);
               this.structureMap.put(ChunkCoordIntPair.chunkXZ2Int(n, n2), structureStart);
               this.setStructureStart(n, n2, structureStart);
            }
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception preparing structure feature");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Feature being prepared");
            crashReportCategory.addCrashSectionCallable("Is feature chunk", () -> this.canSpawnStructureAtCoords(n, n2) ? "True" : "False");
            crashReportCategory.addCrashSection("Chunk location", String.format("%d,%d", n, n2));
            crashReportCategory.addCrashSectionCallable("Chunk pos hash", () -> String.valueOf(ChunkCoordIntPair.chunkXZ2Int(n, n2)));
            crashReportCategory.addCrashSectionCallable("Structure type", () -> this.getClass().getCanonicalName());
            throw new ReportedException(crashReport);
         }
      }
   }

   public List getCoordList() {
      return null;
   }

   public BlockPos getClosestStrongholdPos(World world, BlockPos pos) {
      this.worldObj = world;
      this.initializeStructureData(world);
      this.rand.setSeed(world.getSeed());
      long l = this.rand.nextLong();
      long l2 = this.rand.nextLong();
      long l3 = (long)(pos.getX() >> 4) * l;
      long l4 = (long)(pos.getZ() >> 4) * l2;
      this.rand.setSeed(l3 ^ l4 ^ world.getSeed());
      this.recursiveGenerate(world, pos.getX() >> 4, pos.getZ() >> 4, 0, 0, null);
      double maxvalue = Double.MAX_VALUE;
      BlockPos pos2 = null;

      for (StructureStart structureStart : (Iterable<StructureStart>)(this.structureMap.values())) {
         if (structureStart.isSizeableStructure()) {
            BlockPos boundingBoxCenter = ((StructureComponent)structureStart.getComponents().get(0)).getBoundingBoxCenter();
            double d = boundingBoxCenter.distanceSq(pos);
            if (d < maxvalue) {
               maxvalue = d;
               pos2 = boundingBoxCenter;
            }
         }
      }

      if (pos2 != null) {
         return pos2;
      } else {
         List coordList = this.getCoordList();
         if (coordList != null) {
            BlockPos pos3 = null;

            for (BlockPos pos4 : (Iterable<BlockPos>)(coordList)) {
               double d2 = pos4.distanceSq(pos);
               if (d2 < maxvalue) {
                  maxvalue = d2;
                  pos3 = pos4;
               }
            }

            return pos3;
         } else {
            return null;
         }
      }
   }

   public abstract StructureStart getStructureStart(int n, int n2);

   public boolean isPositionInStructure(World world, BlockPos pos) {
      this.initializeStructureData(world);

      for (StructureStart structureStart : (Iterable<StructureStart>)(this.structureMap.values())) {
         if (structureStart.isSizeableStructure() && structureStart.getBoundingBox().isVecInside(pos)) {
            return true;
         }
      }

      return false;
   }

   private void initializeStructureData(World world) {
      if (this.structureData == null) {
         this.structureData = (MapGenStructureData)world.loadItemData(MapGenStructureData.class, this.getStructureName());
         if (this.structureData == null) {
            this.structureData = new MapGenStructureData(this.getStructureName());
            world.setItemData(this.getStructureName(), this.structureData);
         } else {
            NBTTagCompound tagCompound = this.structureData.getTagCompound();

            for (String str : (Iterable<String>)(tagCompound.getKeySet())) {
               NBTBase tag = tagCompound.getTag(str);
               if (tag.getId() == 10) {
                  NBTTagCompound tag2 = (NBTTagCompound)tag;
                  if (tag2.hasKey("ChunkX") && tag2.hasKey("ChunkZ")) {
                     int integer = tag2.getInteger("ChunkX");
                     int integer2 = tag2.getInteger("ChunkZ");
                     StructureStart structureStart = MapGenStructureIO.getStructureStart(tag2, world);
                     if (structureStart != null) {
                        this.structureMap.put(ChunkCoordIntPair.chunkXZ2Int(integer, integer2), structureStart);
                     }
                  }
               }
            }
         }
      }
   }

   public abstract boolean canSpawnStructureAtCoords(int n, int n2);

   public boolean generateStructure(World world, Random rand2, ChunkCoordIntPair chunkCoordIntPair) {
      this.initializeStructureData(world);
      int n = (chunkCoordIntPair.chunkXPos << 4) + 8;
      int n2 = (chunkCoordIntPair.chunkZPos << 4) + 8;
      boolean flag = false;

      for (StructureStart structureStart : (Iterable<StructureStart>)(this.structureMap.values())) {
         if (structureStart.isSizeableStructure() && structureStart.isValidForPostProcess(chunkCoordIntPair) && structureStart.getBoundingBox().intersectsWith(n, n2, n + 15, n2 + 15)) {
            structureStart.generateStructure(world, rand2, new StructureBoundingBox(n, n2, n + 15, n2 + 15));
            structureStart.notifyPostProcessAt(chunkCoordIntPair);
            flag = true;
            this.setStructureStart(structureStart.getChunkPosX(), structureStart.getChunkPosZ(), structureStart);
         }
      }

      return flag;
   }

   private void setStructureStart(int n, int n2, StructureStart structureStart) {
      this.structureData.writeInstance(structureStart.writeStructureComponentsToNBT(n, n2), n, n2);
      this.structureData.markDirty();
   }

}
