package net.minecraft.village;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Village {
   private World worldObj;
   private final List villageDoorInfoList = Lists.newArrayList();
   private BlockPos centerHelper = BlockPos.ORIGIN;
   private BlockPos center = BlockPos.ORIGIN;
   private int villageRadius;
   private int lastAddDoorTimestamp;
   private int tickCounter;
   private int numVillagers;
   private int noBreedTicks;
   private TreeMap playerReputation = new TreeMap();
   private List villageAgressors = Lists.newArrayList();
   private int numIronGolems;

   private void updateVillageRadiusAndCenter() {
      int size = this.villageDoorInfoList.size();
      if (size == 0) {
         this.center = new BlockPos(0, 0, 0);
         this.villageRadius = 0;
      } else {
         this.center = new BlockPos(this.centerHelper.getX() / size, this.centerHelper.getY() / size, this.centerHelper.getZ() / size);
         int n = 0;

         for (VillageDoorInfo villageDoorInfo : (Iterable<VillageDoorInfo>)(this.villageDoorInfoList)) {
            n = Math.max(villageDoorInfo.getDistanceToDoorBlockSq(this.center), n);
         }

         this.villageRadius = Math.max(32, (int)Math.sqrt((double)n) + 1);
      }
   }

   private void removeDeadAndOutOfRangeDoors() {
      boolean flag = false;
      boolean flag2 = this.worldObj.rand.nextInt(50) == 0;
      Iterator it = this.villageDoorInfoList.iterator();

      while (it.hasNext()) {
         VillageDoorInfo villageDoorInfo = (VillageDoorInfo)it.next();
         if (flag2) {
            villageDoorInfo.resetDoorOpeningRestrictionCounter();
         }

         if (!this.isWoodDoor(villageDoorInfo.getDoorBlockPos()) || Math.abs(this.tickCounter - villageDoorInfo.getInsidePosY()) > 1200) {
            this.centerHelper = this.centerHelper.subtract(villageDoorInfo.getDoorBlockPos());
            flag = true;
            villageDoorInfo.setIsDetachedFromVillageFlag(true);
            it.remove();
         }
      }

      if (flag) {
         this.updateVillageRadiusAndCenter();
      }
   }

   public int getNumVillageDoors() {
      return this.villageDoorInfoList.size();
   }

   public int getTicksSinceLastDoorAdding() {
      return this.tickCounter - this.lastAddDoorTimestamp;
   }

   public VillageDoorInfo getDoorInfo(BlockPos pos) {
      VillageDoorInfo villageDoorInfo = null;
      int maxvalue = Integer.MAX_VALUE;

      for (VillageDoorInfo villageDoorInfo2 : (Iterable<VillageDoorInfo>)(this.villageDoorInfoList)) {
         int distanceToDoorBlockSq = villageDoorInfo2.getDistanceToDoorBlockSq(pos);
         if (distanceToDoorBlockSq > 256) {
            distanceToDoorBlockSq *= 1000;
         } else {
            distanceToDoorBlockSq = villageDoorInfo2.getDoorOpeningRestrictionCounter();
         }

         if (distanceToDoorBlockSq < maxvalue) {
            villageDoorInfo = villageDoorInfo2;
            maxvalue = distanceToDoorBlockSq;
         }
      }

      return villageDoorInfo;
   }

   public void endMatingSeason() {
      this.noBreedTicks = this.tickCounter;
   }

   public boolean isBlockPosWithinSqVillageRadius(BlockPos pos) {
      return this.center.distanceSq(pos) < (double)(this.villageRadius * this.villageRadius);
   }

   public int setReputationForPlayer(String str, int n) {
      int n2 = MathHelper.clamp_int(this.getReputationForPlayer(str) + n, -30, 10);
      this.playerReputation.put(str, n2);
      return n2;
   }

   private void updateNumVillagers() {
      List entitiesWithinAABB = this.worldObj
         .getEntitiesWithinAABB(
            EntityVillager.class,
            new AxisAlignedBB(
               (double)(this.center.getX() - this.villageRadius),
               (double)(this.center.getY() - 4),
               (double)(this.center.getZ() - this.villageRadius),
               (double)(this.center.getX() + this.villageRadius),
               (double)(this.center.getY() + 4),
               (double)(this.center.getZ() + this.villageRadius)
            )
         );
      this.numVillagers = entitiesWithinAABB.size();
      if (this.numVillagers == 0) {
         this.playerReputation.clear();
      }
   }

   public List getVillageDoorInfoList() {
      return this.villageDoorInfoList;
   }

   public Village(World world) {
      this.worldObj = world;
   }

   public int getVillageRadius() {
      return this.villageRadius;
   }

   public void writeVillageDataToNBT(NBTTagCompound tag) {
      tag.setInteger("PopSize", this.numVillagers);
      tag.setInteger("Radius", this.villageRadius);
      tag.setInteger("Golems", this.numIronGolems);
      tag.setInteger("Stable", this.lastAddDoorTimestamp);
      tag.setInteger("Tick", this.tickCounter);
      tag.setInteger("MTick", this.noBreedTicks);
      tag.setInteger("CX", this.center.getX());
      tag.setInteger("CY", this.center.getY());
      tag.setInteger("CZ", this.center.getZ());
      tag.setInteger("ACX", this.centerHelper.getX());
      tag.setInteger("ACY", this.centerHelper.getY());
      tag.setInteger("ACZ", this.centerHelper.getZ());
      NBTTagList tagList = new NBTTagList();

      for (VillageDoorInfo villageDoorInfo : (Iterable<VillageDoorInfo>)(this.villageDoorInfoList)) {
         NBTTagCompound tag2 = new NBTTagCompound();
         tag2.setInteger("X", villageDoorInfo.getDoorBlockPos().getX());
         tag2.setInteger("Y", villageDoorInfo.getDoorBlockPos().getY());
         tag2.setInteger("Z", villageDoorInfo.getDoorBlockPos().getZ());
         tag2.setInteger("IDX", villageDoorInfo.getInsideOffsetX());
         tag2.setInteger("IDZ", villageDoorInfo.getInsideOffsetZ());
         tag2.setInteger("TS", villageDoorInfo.getInsidePosY());
         tagList.appendTag(tag2);
      }

      tag.setTag("Doors", tagList);
      NBTTagList tagList2 = new NBTTagList();

      for (String str : (Iterable<String>)(this.playerReputation.keySet())) {
         NBTTagCompound tag3 = new NBTTagCompound();
         GameProfile gameProfileForUsername = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(str);
         if (gameProfileForUsername != null) {
            tag3.setString("UUID", gameProfileForUsername.getId().toString());
            tag3.setInteger("S", (Integer)this.playerReputation.get(str));
            tagList2.appendTag(tag3);
         }
      }

      tag.setTag("Players", tagList2);
   }

   public Village() {
   }

   public void readVillageDataFromNBT(NBTTagCompound tag) {
      this.numVillagers = tag.getInteger("PopSize");
      this.villageRadius = tag.getInteger("Radius");
      this.numIronGolems = tag.getInteger("Golems");
      this.lastAddDoorTimestamp = tag.getInteger("Stable");
      this.tickCounter = tag.getInteger("Tick");
      this.noBreedTicks = tag.getInteger("MTick");
      this.center = new BlockPos(tag.getInteger("CX"), tag.getInteger("CY"), tag.getInteger("CZ"));
      this.centerHelper = new BlockPos(tag.getInteger("ACX"), tag.getInteger("ACY"), tag.getInteger("ACZ"));
      NBTTagList tagList = tag.getTagList("Doors", 10);

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         VillageDoorInfo villageDoorInfo = new VillageDoorInfo(
            new BlockPos(compoundTagAt.getInteger("X"), compoundTagAt.getInteger("Y"), compoundTagAt.getInteger("Z")),
            compoundTagAt.getInteger("IDX"),
            compoundTagAt.getInteger("IDZ"),
            compoundTagAt.getInteger("TS")
         );
         this.villageDoorInfoList.add(villageDoorInfo);
      }

      NBTTagList tagList2 = tag.getTagList("Players", 10);

      for (int j = 0; j < tagList2.tagCount(); j++) {
         NBTTagCompound compoundTagAt2 = tagList2.getCompoundTagAt(j);
         if (compoundTagAt2.hasKey("UUID")) {
            GameProfile profileByUUID = MinecraftServer.getServer().getPlayerProfileCache().getProfileByUUID(UUID.fromString(compoundTagAt2.getString("UUID")));
            if (profileByUUID != null) {
               this.playerReputation.put(profileByUUID.getName(), compoundTagAt2.getInteger("S"));
            }
         } else {
            this.playerReputation.put(compoundTagAt2.getString("Name"), compoundTagAt2.getInteger("S"));
         }
      }
   }

   public void addVillageDoorInfo(VillageDoorInfo villageDoorInfo) {
      this.villageDoorInfoList.add(villageDoorInfo);
      this.centerHelper = this.centerHelper.add(villageDoorInfo.getDoorBlockPos());
      this.updateVillageRadiusAndCenter();
      this.lastAddDoorTimestamp = villageDoorInfo.getInsidePosY();
   }

   public void tick(int n) {
      this.tickCounter = n;
      this.removeDeadAndOutOfRangeDoors();
      this.removeDeadAndOldAgressors();
      if (n % 20 == 0) {
         this.updateNumVillagers();
      }

      if (n % 30 == 0) {
         this.updateNumIronGolems();
      }

      int n2 = this.numVillagers / 10;
      if (this.numIronGolems < n2 && this.villageDoorInfoList.size() > 20 && this.worldObj.rand.nextInt(7000) == 0) {
         Vec3 vec = this.findRandomSpawnPos(this.center, 2, 4, 2);
         if (vec != null) {
            EntityIronGolem entityIronGolem = new EntityIronGolem(this.worldObj);
            entityIronGolem.setPosition(vec.xCoord, vec.yCoord, vec.zCoord);
            this.worldObj.spawnEntityInWorld(entityIronGolem);
            this.numIronGolems++;
         }
      }
   }

   public void setWorld(World world) {
      this.worldObj = world;
   }

   public void addOrRenewAgressor(EntityLivingBase entity) {
      for (Village.VillageAggressor villageAggressor : (Iterable<Village.VillageAggressor>)(this.villageAgressors)) {
         if (villageAggressor.agressor == entity) {
            villageAggressor.agressionTime = this.tickCounter;
            return;
         }
      }

      this.villageAgressors.add(new Village.VillageAggressor(this, entity, this.tickCounter));
   }

   public EntityLivingBase findNearestVillageAggressor(EntityLivingBase entity) {
      double maxvalue = Double.MAX_VALUE;
      Village.VillageAggressor villageAggressor = null;

      for (int i = 0; i < this.villageAgressors.size(); i++) {
         Village.VillageAggressor villageAggressor2 = (Village.VillageAggressor)this.villageAgressors.get(i);
         double distanceSqToEntity = villageAggressor2.agressor.getDistanceSqToEntity(entity);
         if (distanceSqToEntity <= maxvalue) {
            villageAggressor = villageAggressor2;
            maxvalue = distanceSqToEntity;
         }
      }

      return villageAggressor != null ? villageAggressor.agressor : null;
   }

   private void updateNumIronGolems() {
      List entitiesWithinAABB = this.worldObj
         .getEntitiesWithinAABB(
            EntityIronGolem.class,
            new AxisAlignedBB(
               (double)(this.center.getX() - this.villageRadius),
               (double)(this.center.getY() - 4),
               (double)(this.center.getZ() - this.villageRadius),
               (double)(this.center.getX() + this.villageRadius),
               (double)(this.center.getY() + 4),
               (double)(this.center.getZ() + this.villageRadius)
            )
         );
      this.numIronGolems = entitiesWithinAABB.size();
   }

   public VillageDoorInfo getExistedDoor(BlockPos pos) {
      if (this.center.distanceSq(pos) > (double)(this.villageRadius * this.villageRadius)) {
         return null;
      } else {
         for (VillageDoorInfo villageDoorInfo : (Iterable<VillageDoorInfo>)(this.villageDoorInfoList)) {
            if (villageDoorInfo.getDoorBlockPos().getX() == pos.getX()
               && villageDoorInfo.getDoorBlockPos().getZ() == pos.getZ()
               && Math.abs(villageDoorInfo.getDoorBlockPos().getY() - pos.getY()) <= 1) {
               return villageDoorInfo;
            }
         }

         return null;
      }
   }

   public int getReputationForPlayer(String str) {
      Integer num = (Integer)this.playerReputation.get(str);
      return num != null ? num : 0;
   }

   public void setDefaultPlayerReputation(int n) {
      for (String str : (Iterable<String>)(this.playerReputation.keySet())) {
         this.setReputationForPlayer(str, n);
      }
   }

   private boolean isWoodDoor(BlockPos pos) {
      Block block = this.worldObj.getBlockState(pos).getBlock();
      return block instanceof BlockDoor ? block.getMaterial() == Material.wood : false;
   }

   public boolean isMatingSeason() {
      return this.noBreedTicks == 0 || this.tickCounter - this.noBreedTicks >= 3600;
   }

   public boolean isPlayerReputationTooLow(String str) {
      return this.getReputationForPlayer(str) <= -15;
   }

   public VillageDoorInfo getNearestDoor(BlockPos pos) {
      VillageDoorInfo villageDoorInfo = null;
      int maxvalue = Integer.MAX_VALUE;

      for (VillageDoorInfo villageDoorInfo2 : (Iterable<VillageDoorInfo>)(this.villageDoorInfoList)) {
         int distanceToDoorBlockSq = villageDoorInfo2.getDistanceToDoorBlockSq(pos);
         if (distanceToDoorBlockSq < maxvalue) {
            villageDoorInfo = villageDoorInfo2;
            maxvalue = distanceToDoorBlockSq;
         }
      }

      return villageDoorInfo;
   }

   public int getNumVillagers() {
      return this.numVillagers;
   }

   public BlockPos getCenter() {
      return this.center;
   }

   public boolean isAnnihilated() {
      return this.villageDoorInfoList.isEmpty();
   }

   private Vec3 findRandomSpawnPos(BlockPos pos, int n, int n2, int n3) {
      for (int i = 0; i < 10; i++) {
         BlockPos pos2 = pos.add(this.worldObj.rand.nextInt(16) - 8, this.worldObj.rand.nextInt(6) - 3, this.worldObj.rand.nextInt(16) - 8);
         if (this.isBlockPosWithinSqVillageRadius(pos2) && this.isAreaClearAround(new BlockPos(n, n2, n3), pos2)) {
            return new Vec3((double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ());
         }
      }

      return null;
   }

   private void removeDeadAndOldAgressors() {
      Iterator it = this.villageAgressors.iterator();

      while (it.hasNext()) {
         Village.VillageAggressor villageAggressor = (Village.VillageAggressor)it.next();
         if (!villageAggressor.agressor.isEntityAlive() || Math.abs(this.tickCounter - villageAggressor.agressionTime) > 300) {
            it.remove();
         }
      }
   }

   private boolean isAreaClearAround(BlockPos pos, BlockPos pos2) {
      if (!World.doesBlockHaveSolidTopSurface(this.worldObj, pos2.getX(), pos2.getY() - 1, pos2.getZ())) {
         return false;
      } else {
         int n = pos2.getX() - pos.getX() / 2;
         int n2 = pos2.getZ() - pos.getZ() / 2;

         for (int i = n; i < n + pos.getX(); i++) {
            for (int j = pos2.getY(); j < pos2.getY() + pos.getY(); j++) {
               for (int k = n2; k < n2 + pos.getZ(); k++) {
                  if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().isNormalCube()) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public EntityPlayer getNearestTargetPlayer(EntityLivingBase entity) {
      double maxvalue = Double.MAX_VALUE;
      EntityPlayer player = null;

      for (String str : (Iterable<String>)(this.playerReputation.keySet())) {
         if (this.isPlayerReputationTooLow(str)) {
            EntityPlayer playerEntityByName = this.worldObj.getPlayerEntityByName(str);
            if (playerEntityByName != null) {
               double distanceSqToEntity = playerEntityByName.getDistanceSqToEntity(entity);
               if (distanceSqToEntity <= maxvalue) {
                  player = playerEntityByName;
                  maxvalue = distanceSqToEntity;
               }
            }
         }
      }

      return player;
   }


   public static class VillageAggressor {
      public EntityLivingBase agressor;
      public int agressionTime;
      public final Village village;

      public VillageAggressor(Village village2, EntityLivingBase entity, int n) {
         this.village = village2;
         this.agressor = entity;
         this.agressionTime = n;
      }
   }
}
