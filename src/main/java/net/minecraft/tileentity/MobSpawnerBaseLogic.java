package net.minecraft.tileentity;

import net.minecraft.entity.item.EntityMinecart;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public abstract class MobSpawnerBaseLogic {
   private int spawnDelay = 20;
   private String mobID = "Pig";
   private final List minecartToSpawn = Lists.newArrayList();
   private MobSpawnerBaseLogic.WeightedRandomMinecart randomEntity;
   private double mobRotation;
   private double prevMobRotation;
   private int minSpawnDelay = 200;
   private int maxSpawnDelay = 800;
   private int spawnCount = 4;
   private Entity cachedEntity;
   private int maxNearbyEntities = 6;
   private int activatingRangeFromPlayer = 16;
   private int spawnRange = 4;

   public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart weightedRandomMinecart) {
      this.randomEntity = weightedRandomMinecart;
   }

   public boolean setDelayToMin(int n) {
      if (n == 1 && this.getSpawnerWorld().isRemote) {
         this.spawnDelay = this.minSpawnDelay;
         return true;
      } else {
         return false;
      }
   }

   private boolean isActivated() {
      BlockPos spawnerPosition = this.getSpawnerPosition();
      return this.getSpawnerWorld()
         .isAnyPlayerWithinRangeAt((double)spawnerPosition.getX() + 0.5, (double)spawnerPosition.getY() + 0.5, (double)spawnerPosition.getZ() + 0.5, (double)this.activatingRangeFromPlayer);
   }

   public abstract void broadcastEvent(int n);

   public void setEntityName(String str) {
      this.mobID = str;
   }

   public abstract BlockPos getSpawnerPosition();

   public abstract World getSpawnerWorld();

   private String getEntityNameToSpawn() {
      if (this.getRandomEntity() == null) {
         if (this.mobID != null && this.mobID.equals("Minecart")) {
            this.mobID = "MinecartRideable";
         }

         return this.mobID;
      } else {
         return MobSpawnerBaseLogic.WeightedRandomMinecart.accessEntityType(this.getRandomEntity());
      }
   }

   private void resetTimer() {
      if (this.maxSpawnDelay <= this.minSpawnDelay) {
         this.spawnDelay = this.minSpawnDelay;
      } else {
         int n = this.maxSpawnDelay - this.minSpawnDelay;
         this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(n);
      }

      if (this.minecartToSpawn.size() > 0) {
         this.setRandomEntity((MobSpawnerBaseLogic.WeightedRandomMinecart)WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.minecartToSpawn));
      }

      this.broadcastEvent(1);
   }

   public void updateSpawner() {
      if (this.isActivated()) {
         BlockPos spawnerPosition = this.getSpawnerPosition();
         if (this.getSpawnerWorld().isRemote) {
            double d = (double)((float)spawnerPosition.getX() + this.getSpawnerWorld().rand.nextFloat());
            double d2 = (double)((float)spawnerPosition.getY() + this.getSpawnerWorld().rand.nextFloat());
            double d3 = (double)((float)spawnerPosition.getZ() + this.getSpawnerWorld().rand.nextFloat());
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
            this.getSpawnerWorld().spawnParticle(EnumParticleTypes.FLAME, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
            if (this.spawnDelay > 0) {
               this.spawnDelay--;
            }

            this.prevMobRotation = this.mobRotation;
            this.mobRotation = (this.mobRotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
         } else {
            if (this.spawnDelay == -1) {
               this.resetTimer();
            }

            if (this.spawnDelay > 0) {
               this.spawnDelay--;
               return;
            }

            boolean flag = false;

            for (int i = 0; i < this.spawnCount; i++) {
               Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());
               if (entity == null) {
                  return;
               }

               int size = this.getSpawnerWorld()
                  .getEntitiesWithinAABB(
                     entity.getClass(),
                     new AxisAlignedBB(
                           (double)spawnerPosition.getX(),
                           (double)spawnerPosition.getY(),
                           (double)spawnerPosition.getZ(),
                           (double)(spawnerPosition.getX() + 1),
                           (double)(spawnerPosition.getY() + 1),
                           (double)(spawnerPosition.getZ() + 1)
                        )
                        .expand((double)this.spawnRange, (double)this.spawnRange, (double)this.spawnRange)
                  )
                  .size();
               if (size >= this.maxNearbyEntities) {
                  this.resetTimer();
                  return;
               }

               double d4 = (double)spawnerPosition.getX()
                  + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange
                  + 0.5;
               double d5 = (double)(spawnerPosition.getY() + this.getSpawnerWorld().rand.nextInt(3) - 1);
               double d6 = (double)spawnerPosition.getZ()
                  + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange
                  + 0.5;
               EntityLiving entityLiving = entity instanceof EntityLiving ? (EntityLiving)entity : null;
               entity.setLocationAndAngles(d4, d5, d6, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);
               if (entityLiving == null || entityLiving.getCanSpawnHere() && entityLiving.isNotColliding()) {
                  this.spawnNewEntity(entity, true);
                  this.getSpawnerWorld().playAuxSFX(2004, spawnerPosition, 0);
                  if (entityLiving != null) {
                     entityLiving.spawnExplosionParticle();
                  }

                  flag = true;
               }
            }

            if (flag) {
               this.resetTimer();
            }
         }
      }
   }

   public void readFromNBT(NBTTagCompound tag) {
      this.mobID = tag.getString("EntityId");
      this.spawnDelay = tag.getShort("Delay");
      this.minecartToSpawn.clear();
      if (tag.hasKey("SpawnPotentials", 9)) {
         NBTTagList tagList = tag.getTagList("SpawnPotentials", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            this.minecartToSpawn.add(new MobSpawnerBaseLogic.WeightedRandomMinecart(this, tagList.getCompoundTagAt(i)));
         }
      }

      if (tag.hasKey("SpawnData", 10)) {
         this.setRandomEntity(new MobSpawnerBaseLogic.WeightedRandomMinecart(this, tag.getCompoundTag("SpawnData"), this.mobID));
      } else {
         this.setRandomEntity((MobSpawnerBaseLogic.WeightedRandomMinecart)null);
      }

      if (tag.hasKey("MinSpawnDelay", 99)) {
         this.minSpawnDelay = tag.getShort("MinSpawnDelay");
         this.maxSpawnDelay = tag.getShort("MaxSpawnDelay");
         this.spawnCount = tag.getShort("SpawnCount");
      }

      if (tag.hasKey("MaxNearbyEntities", 99)) {
         this.maxNearbyEntities = tag.getShort("MaxNearbyEntities");
         this.activatingRangeFromPlayer = tag.getShort("RequiredPlayerRange");
      }

      if (tag.hasKey("SpawnRange", 99)) {
         this.spawnRange = tag.getShort("SpawnRange");
      }

      if (this.getSpawnerWorld() != null) {
         this.cachedEntity = null;
      }
   }

   public Entity getCachedEntity(World world) {
      if (this.cachedEntity == null) {
         Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), world);
         if (entity != null) {
            entity = this.spawnNewEntity(entity, false);
            this.cachedEntity = entity;
         }
      }

      return this.cachedEntity;
   }

   private Entity spawnNewEntity(Entity entity, boolean flag) {
      if (this.getRandomEntity() != null) {
         NBTTagCompound tag = new NBTTagCompound();
         entity.writeToNBTOptional(tag);

         for (String str : (Iterable<String>)(MobSpawnerBaseLogic.WeightedRandomMinecart.accessNbtData(this.getRandomEntity()).getKeySet())) {
            tag.setTag(str, MobSpawnerBaseLogic.WeightedRandomMinecart.accessNbtData(this.getRandomEntity()).getTag(str).copy());
         }

         entity.readFromNBT(tag);
         if (entity.entityWorld != null && flag) {
            entity.entityWorld.spawnEntityInWorld(entity);
         }

         Entity entity2 = entity;

         while (tag.hasKey("Riding", 10)) {
            NBTTagCompound compoundTag = tag.getCompoundTag("Riding");
            Entity entity3 = EntityList.createEntityByName(compoundTag.getString("id"), entity.entityWorld);
            if (entity3 != null) {
               NBTTagCompound tag3 = new NBTTagCompound();
               entity3.writeToNBTOptional(tag3);

               for (String str2 : (Iterable<String>)(compoundTag.getKeySet())) {
                  tag3.setTag(str2, compoundTag.getTag(str2).copy());
               }

               entity3.readFromNBT(tag3);
               entity3.setLocationAndAngles(entity2.posX, entity2.posY, entity2.posZ, entity2.rotationYaw, entity2.rotationPitch);
               if (entity.entityWorld != null && flag) {
                  entity.entityWorld.spawnEntityInWorld(entity3);
               }

               entity2.mountEntity(entity3);
            }

            entity2 = entity3;
            tag = compoundTag;
         }
      } else if (entity instanceof EntityLivingBase && entity.entityWorld != null && flag) {
         if (entity instanceof EntityLiving) {
            ((EntityLiving)entity).onInitialSpawn(entity.entityWorld.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);
         }

         entity.entityWorld.spawnEntityInWorld(entity);
      }

      return entity;
   }

   private MobSpawnerBaseLogic.WeightedRandomMinecart getRandomEntity() {
      return this.randomEntity;
   }

   public void writeToNBT(NBTTagCompound tag) {
      String entityNameToSpawn = this.getEntityNameToSpawn();
      if (!StringUtils.isNullOrEmpty(entityNameToSpawn)) {
         tag.setString("EntityId", entityNameToSpawn);
         tag.setShort("Delay", (short)this.spawnDelay);
         tag.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
         tag.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
         tag.setShort("SpawnCount", (short)this.spawnCount);
         tag.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
         tag.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
         tag.setShort("SpawnRange", (short)this.spawnRange);
         if (this.getRandomEntity() != null) {
            tag.setTag("SpawnData", MobSpawnerBaseLogic.WeightedRandomMinecart.accessNbtData(this.getRandomEntity()).copy());
         }

         if (this.getRandomEntity() != null || this.minecartToSpawn.size() > 0) {
            NBTTagList tagList = new NBTTagList();
            if (this.minecartToSpawn.size() > 0) {
               for (MobSpawnerBaseLogic.WeightedRandomMinecart weightedRandomMinecart : (Iterable<MobSpawnerBaseLogic.WeightedRandomMinecart>)(this.minecartToSpawn)) {
                  tagList.appendTag(weightedRandomMinecart.toNBT());
               }
            } else {
               tagList.appendTag(this.getRandomEntity().toNBT());
            }

            tag.setTag("SpawnPotentials", tagList);
         }
      }
   }

   public double getMobRotation() {
      return this.mobRotation;
   }

   public double getPrevMobRotation() {
      return this.prevMobRotation;
   }


   public static class WeightedRandomMinecart extends WeightedRandom.Item {
      private final NBTTagCompound nbtData;
      private final String entityType;
      public final MobSpawnerBaseLogic spawnerLogic;

      public static String accessEntityType(WeightedRandomMinecart weightedRandomMinecart) {
         return weightedRandomMinecart.entityType;
      }

      public WeightedRandomMinecart(MobSpawnerBaseLogic mobSpawnerBaseLogic, NBTTagCompound tag) {
         this(mobSpawnerBaseLogic, tag.getCompoundTag("Properties"), tag.getString("Type"), tag.getInteger("Weight"));
      }

      public static NBTTagCompound accessNbtData(WeightedRandomMinecart weightedRandomMinecart) {
         return weightedRandomMinecart.nbtData;
      }

      private WeightedRandomMinecart(MobSpawnerBaseLogic mobSpawnerBaseLogic, NBTTagCompound tag, String str, int n) {
         super(n);
         this.spawnerLogic = mobSpawnerBaseLogic;
         if (str.equals("Minecart")) {
            if (tag != null) {
               str = EntityMinecart.EnumMinecartType.byNetworkID(tag.getInteger("Type")).getName();
            } else {
               str = "MinecartRideable";
            }
         }

         this.nbtData = tag;
         this.entityType = str;
      }

      public WeightedRandomMinecart(MobSpawnerBaseLogic mobSpawnerBaseLogic, NBTTagCompound tag, String str) {
         this(mobSpawnerBaseLogic, tag, str, 1);
      }

      public NBTTagCompound toNBT() {
         NBTTagCompound tag = new NBTTagCompound();
         tag.setTag("Properties", this.nbtData);
         tag.setString("Type", this.entityType);
         tag.setInteger("Weight", this.itemWeight);
         return tag;
      }
   }
}
