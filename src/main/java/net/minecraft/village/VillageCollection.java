package net.minecraft.village;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;

public class VillageCollection extends WorldSavedData {
   private World worldObj;
   private final List villagerPositionsList = Lists.newArrayList();
   private final List newDoors = Lists.newArrayList();
   private final List villageList = Lists.newArrayList();
   private int tickCounter;

   private void addDoor(BlockPos pos) {
      EnumFacing facing = BlockDoor.getFacing(this.worldObj, pos);
      EnumFacing opposite = facing.getOpposite();
      int n = this.countBlocksCanSeeSky(pos, facing, 5);
      int n2 = this.countBlocksCanSeeSky(pos, opposite, n + 1);
      if (n != n2) {
         this.newDoors.add(new VillageDoorInfo(pos, n < n2 ? facing : opposite, this.tickCounter));
      }
   }

   private void removeAnnihilatedVillages() {
      Iterator it = this.villageList.iterator();

      while (it.hasNext()) {
         if (((Village)it.next()).isAnnihilated()) {
            it.remove();
            this.markDirty();
         }
      }
   }

   public List getVillageList() {
      return this.villageList;
   }

   public VillageCollection(World world) {
      super(fileNameForProvider(world.provider));
      this.worldObj = world;
      this.markDirty();
   }

   public void addToVillagerPositionList(BlockPos pos) {
      if (this.villagerPositionsList.size() <= 64 && !this.positionInList(pos)) {
         this.villagerPositionsList.add(pos);
      }
   }

   public VillageCollection(String str) {
      super(str);
   }

   public static String fileNameForProvider(WorldProvider worldProvider) {
      return "villages" + worldProvider.getInternalNameSuffix();
   }

   private int countBlocksCanSeeSky(BlockPos pos, EnumFacing facing, int n) {
      int n2 = 0;

      for (int i = 1; i <= 5; i++) {
         int n3 = pos.getX() + facing.getFrontOffsetX() * i;
         int n4 = pos.getY() + facing.getFrontOffsetY() * i;
         int n5 = pos.getZ() + facing.getFrontOffsetZ() * i;
         if (this.worldObj.canSeeSky(n3, n4, n5)) {
            if (++n2 >= n) {
               return n2;
            }
         }
      }

      return n2;
   }

   private void addDoorsAround(BlockPos pos) {
      byte b = 16;
      byte b2 = 4;
      byte b3 = 16;

      for (int i = -b; i < b; i++) {
         for (int j = -b2; j < b2; j++) {
            for (int k = -b3; k < b3; k++) {
               BlockPos pos2 = pos.add(i, j, k);
               if (this.isWoodDoor(pos2)) {
                  VillageDoorInfo villageDoorInfo = this.checkDoorExistence(pos2);
                  if (villageDoorInfo == null) {
                     this.addDoor(pos2);
                  } else {
                     villageDoorInfo.setLastActivityTimestamp(this.tickCounter);
                  }
               }
            }
         }
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      tag.setInteger("Tick", this.tickCounter);
      NBTTagList tagList = new NBTTagList();

      for (Village village : (Iterable<Village>)(this.villageList)) {
         NBTTagCompound tag2 = new NBTTagCompound();
         village.writeVillageDataToNBT(tag2);
         tagList.appendTag(tag2);
      }

      tag.setTag("Villages", tagList);
   }

   private boolean positionInList(BlockPos pos) {
      for (BlockPos pos2 : (Iterable<BlockPos>)(this.villagerPositionsList)) {
         if (pos2.equals(pos)) {
            return true;
         }
      }

      return false;
   }

   private VillageDoorInfo checkDoorExistence(BlockPos pos) {
      for (VillageDoorInfo villageDoorInfo : (Iterable<VillageDoorInfo>)(this.newDoors)) {
         if (villageDoorInfo.getDoorBlockPos().getX() == pos.getX()
            && villageDoorInfo.getDoorBlockPos().getZ() == pos.getZ()
            && Math.abs(villageDoorInfo.getDoorBlockPos().getY() - pos.getY()) <= 1) {
            return villageDoorInfo;
         }
      }

      for (Village village : (Iterable<Village>)(this.villageList)) {
         VillageDoorInfo existedDoor = village.getExistedDoor(pos);
         if (existedDoor != null) {
            return existedDoor;
         }
      }

      return null;
   }

   public Village getNearestVillage(BlockPos pos, int n) {
      Village village = null;
      double maxvalue = Float.MAX_VALUE;

      for (Village village2 : (Iterable<Village>)(this.villageList)) {
         double d = village2.getCenter().distanceSq(pos);
         if (d < maxvalue) {
            float f = (float)(n + village2.getVillageRadius());
            if (d <= (double)(f * f)) {
               village = village2;
               maxvalue = d;
            }
         }
      }

      return village;
   }

   private void addNewDoorsToVillageOrCreateVillage() {
      for (int i = 0; i < this.newDoors.size(); i++) {
         VillageDoorInfo villageDoorInfo = (VillageDoorInfo)this.newDoors.get(i);
         Village nearestVillage = this.getNearestVillage(villageDoorInfo.getDoorBlockPos(), 32);
         if (nearestVillage == null) {
            nearestVillage = new Village(this.worldObj);
            this.villageList.add(nearestVillage);
            this.markDirty();
         }

         nearestVillage.addVillageDoorInfo(villageDoorInfo);
      }

      this.newDoors.clear();
   }

   public void tick() {
      this.tickCounter++;

      for (Village village : (Iterable<Village>)(this.villageList)) {
         village.tick(this.tickCounter);
      }

      this.removeAnnihilatedVillages();
      this.dropOldestVillagerPosition();
      this.addNewDoorsToVillageOrCreateVillage();
      if (this.tickCounter % 400 == 0) {
         this.markDirty();
      }
   }

   private boolean isWoodDoor(BlockPos pos) {
      Block block = this.worldObj.getBlockState(pos).getBlock();
      return block instanceof BlockDoor ? block.getMaterial() == Material.wood : false;
   }

   private void dropOldestVillagerPosition() {
      if (!this.villagerPositionsList.isEmpty()) {
         this.addDoorsAround((BlockPos)this.villagerPositionsList.remove(0));
      }
   }

   public void setWorldsForAll(World world) {
      this.worldObj = world;

      for (Village village : (Iterable<Village>)(this.villageList)) {
         village.setWorld(world);
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      this.tickCounter = tag.getInteger("Tick");
      NBTTagList tagList = tag.getTagList("Villages", 10);

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         Village village = new Village();
         village.readVillageDataFromNBT(compoundTagAt);
         this.villageList.add(village);
      }
   }
}
