package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public abstract class StructureStart {
   public LinkedList components = new LinkedList();
   public StructureBoundingBox boundingBox;
   private int chunkPosX;
   private int chunkPosZ;

   public int getChunkPosZ() {
      return this.chunkPosZ;
   }

   public void notifyPostProcessAt(ChunkCoordIntPair chunkCoordIntPair) {
   }

   public boolean isValidForPostProcess(ChunkCoordIntPair chunkCoordIntPair) {
      return true;
   }

   public StructureBoundingBox getBoundingBox() {
      return this.boundingBox;
   }

   public int getChunkPosX() {
      return this.chunkPosX;
   }

   public NBTTagCompound writeStructureComponentsToNBT(int n, int n2) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("id", MapGenStructureIO.getStructureStartName(this));
      tag.setInteger("ChunkX", n);
      tag.setInteger("ChunkZ", n2);
      tag.setTag("BB", this.boundingBox.toNBTTagIntArray());
      NBTTagList tagList = new NBTTagList();

      for (StructureComponent structureComponent : (Iterable<StructureComponent>)(this.components)) {
         tagList.appendTag(structureComponent.createStructureBaseNBT());
      }

      tag.setTag("Children", tagList);
      this.writeToNBT(tag);
      return tag;
   }

   public void readStructureComponentsFromNBT(World world, NBTTagCompound tag) {
      this.chunkPosX = tag.getInteger("ChunkX");
      this.chunkPosZ = tag.getInteger("ChunkZ");
      if (tag.hasKey("BB")) {
         this.boundingBox = new StructureBoundingBox(tag.getIntArray("BB"));
      }

      NBTTagList tagList = tag.getTagList("Children", 10);

      for (int i = 0; i < tagList.tagCount(); i++) {
         this.components.add(MapGenStructureIO.getStructureComponent(tagList.getCompoundTagAt(i), world));
      }

      this.readFromNBT(tag);
   }

   public void markAvailableHeight(World world, Random rand, int n) {
      int n2 = world.getSeaLevel() - n;
      int n3 = this.boundingBox.getYSize() + 1;
      if (n3 < n2) {
         n3 += rand.nextInt(n2 - n3);
      }

      int n4 = n3 - this.boundingBox.maxY;
      this.boundingBox.offset(0, n4, 0);

      for (StructureComponent structureComponent : (Iterable<StructureComponent>)(this.components)) {
         structureComponent.offset(0, n4, 0);
      }
   }

   public StructureStart() {
   }

   public void readFromNBT(NBTTagCompound tag) {
   }

   public boolean isSizeableStructure() {
      return true;
   }

   public void generateStructure(World world, Random rand, StructureBoundingBox structureBoundingBox) {
      Iterator it = this.components.iterator();

      while (it.hasNext()) {
         StructureComponent structureComponent = (StructureComponent)it.next();
         if (structureComponent.getBoundingBox().intersectsWith(structureBoundingBox) && !structureComponent.addComponentParts(world, rand, structureBoundingBox)) {
            it.remove();
         }
      }
   }

   public void updateBoundingBox() {
      this.boundingBox = StructureBoundingBox.getNewBoundingBox();

      for (StructureComponent structureComponent : (Iterable<StructureComponent>)(this.components)) {
         this.boundingBox.expandTo(structureComponent.getBoundingBox());
      }
   }

   public void setRandomHeight(World world, Random rand, int n, int n2) {
      int n3 = n2 - n + 1 - this.boundingBox.getYSize();
      int n4 = 1;
      if (n3 > 1) {
         n4 = n + rand.nextInt(n3);
      } else {
         n4 = n;
      }

      int n5 = n4 - this.boundingBox.minY;
      this.boundingBox.offset(0, n5, 0);

      for (StructureComponent structureComponent : (Iterable<StructureComponent>)(this.components)) {
         structureComponent.offset(0, n5, 0);
      }
   }

   public StructureStart(int n, int n2) {
      this.chunkPosX = n;
      this.chunkPosZ = n2;
   }

   public void writeToNBT(NBTTagCompound tag) {
   }

   public LinkedList getComponents() {
      return this.components;
   }
}
