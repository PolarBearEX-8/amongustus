package client.util;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SplineChecker {
   public World world;
   public final List list = Lists.newArrayList();
   public BlockPos blockPos = BlockPos.origin;
   public BlockPos blockPos2 = BlockPos.origin;
   public int count;
   public int count2;
   public int count3;
   public int count4;
   public int count5;
   public TreeMap treeMap = new TreeMap();
   public List list2 = Lists.newArrayList();
   public int count6;

   public int getCount4() {
      return this.count4;
   }

   public EntityPlayer resolveEntityPlayer(EntityLivingBase livingEntity) {
      double offset = Double.MAX_VALUE;
      EntityPlayer player = null;

      for (String text : (Iterable<String>)(Iterable)this.treeMap.keySet()) {
         if (this.matchesText(text)) {
            EntityPlayer player2 = this.world.resolveEntityPlayerByText(text);
            if (player2 != null) {
               double coordinate = player2.getCoordinate(livingEntity);
               if (coordinate <= offset) {
                  player = player2;
                  offset = coordinate;
               }
            }
         }
      }

      return player;
   }

   private boolean matchesBlockPos(BlockPos blockPos3, BlockPos blockPos4) {
      if (!World.matchesIBlockAccess(this.world, blockPos4.getX(), blockPos4.getY() - 1, blockPos4.getZ())) {
         return false;
      } else {
         int count7 = blockPos4.getX() - blockPos3.getX() / 2;
         int count8 = blockPos4.getZ() - blockPos3.getZ() / 2;

         for (int index = count7; index < count7 + blockPos3.getX(); index++) {
            for (int index2 = blockPos4.getY(); index2 < blockPos4.getY() + blockPos3.getY(); index2++) {
               for (int index3 = count8; index3 < count8 + blockPos3.getZ(); index3++) {
                  if (this.world.getBlockState(new BlockPos(index, index2, index3)).getBlock().isActive2()) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public int getCountAsIndex() {
      return this.count3 - this.count2;
   }

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   public SplineChecker() {
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("PopSize", this.count4);
      tagCompound.put("Radius", this.count);
      tagCompound.put("Golems", this.count6);
      tagCompound.put("Stable", this.count2);
      tagCompound.put("Tick", this.count3);
      tagCompound.put("MTick", this.count5);
      tagCompound.put("CX", this.blockPos2.getX());
      tagCompound.put("CY", this.blockPos2.getY());
      tagCompound.put("CZ", this.blockPos2.getZ());
      tagCompound.put("ACX", this.blockPos.getX());
      tagCompound.put("ACY", this.blockPos.getY());
      tagCompound.put("ACZ", this.blockPos.getZ());
      NBTTagList nBTTagList = new NBTTagList();

      for (SmoothTransformer smoothTransformer : (Iterable<SmoothTransformer>)(Iterable)this.list) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound2.put("X", smoothTransformer.getBlockPos().getX());
         tagCompound2.put("Y", smoothTransformer.getBlockPos().getY());
         tagCompound2.put("Z", smoothTransformer.getBlockPos().getZ());
         tagCompound2.put("IDX", smoothTransformer.getCount());
         tagCompound2.put("IDZ", smoothTransformer.getCount3());
         tagCompound2.put("TS", smoothTransformer.getCountAsIndex());
         nBTTagList.applyLISTManager(tagCompound2);
      }

      tagCompound.applyText("Doors", nBTTagList);
      NBTTagList nBTTagList2 = new NBTTagList();

      for (String text : (Iterable<String>)(Iterable)this.treeMap.keySet()) {
         NBTTagCompound tagCompound3 = new NBTTagCompound();
         GameProfile gameProfile = VanillaRunnable.getVanillaRunnable().getYyyyMMDdHHMmSsZManager().resolveGameProfile(text);
         if (gameProfile != null) {
            tagCompound3.put("UUID", gameProfile.getId().toString());
            tagCompound3.put("S", (Integer)this.treeMap.get(text));
            nBTTagList2.applyLISTManager(tagCompound3);
         }
      }

      tagCompound.applyText("Players", nBTTagList2);
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos3) {
      return this.blockPos2.getCoordinate(blockPos3) < (double)(this.count * this.count);
   }

   public void setWorld(World world2) {
      this.world = world2;
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.count4 = tagCompound.computeCount("PopSize");
      this.count = tagCompound.computeCount("Radius");
      this.count6 = tagCompound.computeCount("Golems");
      this.count2 = tagCompound.computeCount("Stable");
      this.count3 = tagCompound.computeCount("Tick");
      this.count5 = tagCompound.computeCount("MTick");
      this.blockPos2 = new BlockPos(tagCompound.computeCount("CX"), tagCompound.computeCount("CY"), tagCompound.computeCount("CZ"));
      this.blockPos = new BlockPos(tagCompound.computeCount("ACX"), tagCompound.computeCount("ACY"), tagCompound.computeCount("ACZ"));
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Doors", 10);

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         SmoothTransformer smoothTransformer = new SmoothTransformer(
            new BlockPos(nBTTagCompound.computeCount("X"), nBTTagCompound.computeCount("Y"), nBTTagCompound.computeCount("Z")),
            nBTTagCompound.computeCount("IDX"),
            nBTTagCompound.computeCount("IDZ"),
            nBTTagCompound.computeCount("TS")
         );
         this.list.add(smoothTransformer);
      }

      NBTTagList extendedLoggerEntry2 = tagCompound.getExtendedLoggerEntry("Players", 10);

      for (int index2 = 0; index2 < extendedLoggerEntry2.getInt(); index2++) {
         NBTTagCompound nBTTagCompound2 = extendedLoggerEntry2.getNBTTagCompound(index2);
         if (nBTTagCompound2.containsKey("UUID")) {
            GameProfile gameProfile = VanillaRunnable.getVanillaRunnable().getYyyyMMDdHHMmSsZManager().resolveGameProfile(UUID.fromString(nBTTagCompound2.resolveText("UUID")));
            if (gameProfile != null) {
               this.treeMap.put(gameProfile.getName(), nBTTagCompound2.computeCount("S"));
            }
         } else {
            this.treeMap.put(nBTTagCompound2.resolveText("Name"), nBTTagCompound2.computeCount("S"));
         }
      }
   }

   public SmoothTransformer resolveSmoothTransformer2(BlockPos blockPos3) {
      if (this.blockPos2.getCoordinate(blockPos3) > (double)(this.count * this.count)) {
         return null;
      } else {
         for (SmoothTransformer smoothTransformer : (Iterable<SmoothTransformer>)(Iterable)this.list) {
            if (smoothTransformer.getBlockPos().getX() == blockPos3.getX() && smoothTransformer.getBlockPos().getZ() == blockPos3.getZ() && Math.abs(smoothTransformer.getBlockPos().getY() - blockPos3.getY()) <= 1) {
               return smoothTransformer;
            }
         }

         return null;
      }
   }

   public int computeCount(String text) {
      Integer count7 = (Integer)this.treeMap.get(text);
      return count7 != null ? count7 : 0;
   }

   public SmoothTransformer resolveSmoothTransformer(BlockPos blockPos3) {
      SmoothTransformer smoothTransformer = null;
      int count7 = Integer.MAX_VALUE;

      for (SmoothTransformer smoothTransformer2 : (Iterable<SmoothTransformer>)(Iterable)this.list) {
         int count8 = smoothTransformer2.computeCount(blockPos3);
         if (count8 < count7) {
            smoothTransformer = smoothTransformer2;
            count7 = count8;
         }
      }

      return smoothTransformer;
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   private void updateState2() {
      List list3 = this.world
         .resolveList(
            EntityVillagergolem.class,
            new AxisAlignedBB(
               (double)(this.blockPos2.getX() - this.count),
               (double)(this.blockPos2.getY() - 4),
               (double)(this.blockPos2.getZ() - this.count),
               (double)(this.blockPos2.getX() + this.count),
               (double)(this.blockPos2.getY() + 4),
               (double)(this.blockPos2.getZ() + this.count)
            )
         );
      this.count6 = list3.size();
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity) {
      for (EntityLivingBaseRecord entityLivingBaseRecord : (Iterable<EntityLivingBaseRecord>)(Iterable)this.list2) {
         if (entityLivingBaseRecord.entityLivingBase == livingEntity) {
            entityLivingBaseRecord.count = this.count3;
            return;
         }
      }

      this.list2.add(new EntityLivingBaseRecord(this, livingEntity, this.count3));
   }

   private void updateState5() {
      int size = this.list.size();
      if (size == 0) {
         this.blockPos2 = new BlockPos(0, 0, 0);
         this.count = 0;
      } else {
         this.blockPos2 = new BlockPos(this.blockPos.getX() / size, this.blockPos.getY() / size, this.blockPos.getZ() / size);
         int count7 = 0;
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            count7 = Math.max(((SmoothTransformer)iterator.next()).computeCount(this.blockPos2), count7);
         }

         this.count = Math.max(32, (int)Math.sqrt((double)count7) + 1);
      }
   }

   public EntityLivingBase resolveEntityLivingBase(EntityLivingBase livingEntity) {
      double offset = Double.MAX_VALUE;
      EntityLivingBaseRecord entityLivingBaseRecord = null;

      for (int index = 0; index < this.list2.size(); index++) {
         EntityLivingBaseRecord entityLivingBaseRecord2 = (EntityLivingBaseRecord)this.list2.get(index);
         double coordinate = entityLivingBaseRecord2.entityLivingBase.getCoordinate(livingEntity);
         if (coordinate <= offset) {
            entityLivingBaseRecord = entityLivingBaseRecord2;
            offset = coordinate;
         }
      }

      return entityLivingBaseRecord != null ? entityLivingBaseRecord.entityLivingBase : null;
   }

   public void applyIndex(int count7) {
      for (String text : (Iterable<String>)(Iterable)this.treeMap.keySet()) {
         this.computeCount(text, count7);
      }
   }

   public boolean matchesText(String text) {
      return this.computeCount(text) <= -15;
   }

   public SmoothTransformer resolveSmoothTransformerByBlockPos(BlockPos blockPos3) {
      SmoothTransformer smoothTransformer = null;
      int count7 = Integer.MAX_VALUE;

      for (SmoothTransformer smoothTransformer2 : (Iterable<SmoothTransformer>)(Iterable)this.list) {
         int count8 = smoothTransformer2.computeCount(blockPos3);
         int count9;
         if (count8 > 256) {
            count9 = count8 * 1000;
         } else {
            count9 = smoothTransformer2.getCount2();
         }

         if (count9 < count7) {
            smoothTransformer = smoothTransformer2;
            count7 = count9;
         }
      }

      return smoothTransformer;
   }

   public SplineChecker(World world2) {
      this.world = world2;
   }

   private boolean matchesBlockPos(BlockPos blockPos3) {
      Block block = this.world.getBlockState(blockPos3).getBlock();
      return block instanceof BlockDarkOakDoor ? block.getMaterial() == Material.material : false;
   }

   private void updateState3() {
      List list3 = this.world
         .resolveList(
            EntityVillager.class,
            new AxisAlignedBB(
               (double)(this.blockPos2.getX() - this.count),
               (double)(this.blockPos2.getY() - 4),
               (double)(this.blockPos2.getZ() - this.count),
               (double)(this.blockPos2.getX() + this.count),
               (double)(this.blockPos2.getY() + 4),
               (double)(this.blockPos2.getZ() + this.count)
            )
         );
      this.count4 = list3.size();
      if (this.count4 == 0) {
         this.treeMap.clear();
      }
   }

   private void updateState() {
      boolean flag = false;
      boolean flag2 = this.world.random.nextInt(50) == 0;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         SmoothTransformer smoothTransformer = (SmoothTransformer)iterator.next();
         if (flag2) {
            smoothTransformer.updateState2();
         }

         if (!this.matchesBlockPos(smoothTransformer.getBlockPos()) || Math.abs(this.count3 - smoothTransformer.getCountAsIndex()) > 1200) {
            this.blockPos = this.blockPos.getBlockPos(smoothTransformer.getBlockPos());
            flag = true;
            smoothTransformer.setEnabled(true);
            iterator.remove();
         }
      }

      if (flag) {
         this.updateState5();
      }
   }

   public void applyIndexByIndex(int count7) {
      this.count3 = count7;
      this.updateState();
      this.updateState4();
      if (count7 % 20 == 0) {
         this.updateState3();
      }

      if (count7 % 30 == 0) {
         this.updateState2();
      }

      int count8 = this.count4 / 10;
      if (this.count6 < count8 && this.list.size() > 20 && this.world.random.nextInt(7000) == 0) {
         Vec3 vec = this.resolveVec3(this.blockPos2, 2, 4, 2);
         if (vec != null) {
            EntityVillagergolem entityVillagergolem = new EntityVillagergolem(this.world);
            entityVillagergolem.processDouble2(vec.xCoord, vec.yCoord, vec.zCoord);
            this.world.matchesEntity(entityVillagergolem);
            this.count6++;
         }
      }
   }

   public int getCount() {
      return this.count;
   }

   public int size() {
      return this.list.size();
   }

   public int computeCount(String text, int count7) {
      int intItem = MathHelper.getInt12(this.computeCount(text) + count7, -30, 10);
      this.treeMap.put(text, intItem);
      return intItem;
   }

   public boolean isActive() {
      return this.count5 == 0 || this.count3 - this.count5 >= 3600;
   }

   private void updateState4() {
      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         EntityLivingBaseRecord entityLivingBaseRecord = (EntityLivingBaseRecord)iterator.next();
         if (!entityLivingBaseRecord.entityLivingBase.canExecute() || Math.abs(this.count3 - entityLivingBaseRecord.count) > 300) {
            iterator.remove();
         }
      }
   }

   public void updateState6() {
      this.count5 = this.count3;
   }

   public List getList() {
      return this.list;
   }

   private Vec3 resolveVec3(BlockPos blockPos3, int count7, int count8, int count9) {
      for (int index = 0; index < 10; index++) {
         BlockPos blockPos4 = blockPos3.add(this.world.random.nextInt(16) - 8, this.world.random.nextInt(6) - 3, this.world.random.nextInt(16) - 8);
         if (this.matchesBlockPosByBlockPos(blockPos4) && this.matchesBlockPos(new BlockPos(count7, count8, count9), blockPos4)) {
            return new Vec3((double)blockPos4.getX(), (double)blockPos4.getY(), (double)blockPos4.getZ());
         }
      }

      return null;
   }

   public void applySmoothTransformer(SmoothTransformer smoothTransformer) {
      this.list.add(smoothTransformer);
      this.blockPos = this.blockPos.getBlockPos2(smoothTransformer.getBlockPos());
      this.updateState5();
      this.count2 = smoothTransformer.getCountAsIndex();
   }
}
