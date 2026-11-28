package net.minecraft.village;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;

public class VillageSiege {
   private World worldObj;
   private boolean hasSetupSiege;
   private int siegeState = -1;
   private int siegeCount;
   private int nextSpawnTime;
   private Village theVillage;
   private int spawnX;
   private int spawnY;
   private int spawnZ;

   public VillageSiege(World world) {
      this.worldObj = world;
   }

   private Vec3 findRandomSpawnPos(BlockPos pos) {
      for (int i = 0; i < 10; i++) {
         BlockPos pos2 = pos.add(this.worldObj.rand.nextInt(16) - 8, this.worldObj.rand.nextInt(6) - 3, this.worldObj.rand.nextInt(16) - 8);
         if (this.theVillage.isBlockPosWithinSqVillageRadius(pos2)
            && SpawnerAnimals.canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, this.worldObj, pos2)) {
            return new Vec3((double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ());
         }
      }

      return null;
   }

   private boolean spawnZombie() {
      Vec3 vec = this.findRandomSpawnPos(new BlockPos(this.spawnX, this.spawnY, this.spawnZ));
      if (vec == null) {
         return false;
      } else {
         EntityZombie entityZombie;
         try {
            entityZombie = new EntityZombie(this.worldObj);
            entityZombie.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(entityZombie)), (IEntityLivingData)null);
            entityZombie.setVillager(false);
         } catch (Exception e) {
            e.printStackTrace();
            return false;
         }

         entityZombie.setLocationAndAngles(vec.xCoord, vec.yCoord, vec.zCoord, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
         this.worldObj.spawnEntityInWorld(entityZombie);
         entityZombie.setHomePosAndDistance(this.theVillage.getCenter(), this.theVillage.getVillageRadius());
         return true;
      }
   }

   public void tick() {
      if (this.worldObj.isDaytime()) {
         this.siegeState = 0;
      } else if (this.siegeState != 2) {
         if (this.siegeState == 0) {
            float celestialAngle = this.worldObj.getCelestialAngle(0.0F);
            if ((double)celestialAngle < 0.5 || (double)celestialAngle > 0.501) {
               return;
            }

            this.siegeState = this.worldObj.rand.nextInt(10) == 0 ? 1 : 2;
            this.hasSetupSiege = false;
            if (this.siegeState == 2) {
               return;
            }
         }

         if (this.siegeState != -1) {
            if (!this.hasSetupSiege) {
               if (!this.trySetupSiege()) {
                  return;
               }

               this.hasSetupSiege = true;
            }

            if (this.nextSpawnTime > 0) {
               this.nextSpawnTime--;
            } else {
               this.nextSpawnTime = 2;
               if (this.siegeCount > 0) {
                  this.spawnZombie();
                  this.siegeCount--;
               } else {
                  this.siegeState = 2;
               }
            }
         }
      }
   }

   private boolean trySetupSiege() {
      for (EntityPlayer player : (Iterable<EntityPlayer>)(this.worldObj.playerEntities)) {
         if (!player.isSpectator()) {
            this.theVillage = this.worldObj.getVillageCollection().getNearestVillage(new BlockPos(player), 1);
            if (this.theVillage != null
               && this.theVillage.getNumVillageDoors() >= 10
               && this.theVillage.getTicksSinceLastDoorAdding() >= 20
               && this.theVillage.getNumVillagers() >= 20) {
               BlockPos center = this.theVillage.getCenter();
               float villageRadius = (float)this.theVillage.getVillageRadius();
               boolean flag = false;

               for (int i = 0; i < 10; i++) {
                  float f = this.worldObj.rand.nextFloat() * (float) Math.PI * 2.0F;
                  this.spawnX = center.getX() + (int)((double)(MathHelper.cos(f) * villageRadius) * 0.9);
                  this.spawnY = center.getY();
                  this.spawnZ = center.getZ() + (int)((double)(MathHelper.sin(f) * villageRadius) * 0.9);
                  flag = false;

                  for (Village village : (Iterable<Village>)(this.worldObj.getVillageCollection().getVillageList())) {
                     if (village != this.theVillage && village.isBlockPosWithinSqVillageRadius(new BlockPos(this.spawnX, this.spawnY, this.spawnZ))) {
                        flag = true;
                        break;
                     }
                  }

                  if (!flag) {
                     break;
                  }
               }

               if (flag) {
                  return false;
               }

               if (this.findRandomSpawnPos(new BlockPos(this.spawnX, this.spawnY, this.spawnZ)) != null) {
                  this.nextSpawnTime = 0;
                  this.siegeCount = 20;
                  return true;
               }
            }
         }
      }

      return false;
   }
}
