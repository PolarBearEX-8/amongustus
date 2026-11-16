package client.util;

import client.event.PostRenderConsumer;
import client.setting.ExtraOptionType;
import net.minecraft.entity.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TertiaryWorldEntry {
   public World world;
   public boolean enabled;
   public int count = -1;
   public int count2;
   public int count3;
   public SplineChecker splineChecker;
   public int count4;
   public int count5;
   public int count6;

   private Vec3 resolveVec3(BlockPos blockPos) {
      for (int index = 0; index < 10; index++) {
         BlockPos blockPos2 = blockPos.add(this.world.random.nextInt(16) - 8, this.world.random.nextInt(6) - 3, this.world.random.nextInt(16) - 8);
         if (this.splineChecker.matchesBlockPosByBlockPos(blockPos2) && InternalSetEntry.matchesExtraOptionType(ExtraOptionType.ON_GROUND, this.world, blockPos2)) {
            return new Vec3((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
         }
      }

      return null;
   }

   public TertiaryWorldEntry(World world2) {
      this.world = world2;
   }

   public void updateState() {
      if (this.world.isActive2()) {
         this.count = 0;
      } else if (this.count != 2) {
         if (this.count == 0) {
            float factor = this.world.computeFactor(0.0F);
            if ((double)factor < 0.5 || (double)factor > 0.501) {
               return;
            }

            this.count = this.world.random.nextInt(10) == 0 ? 1 : 2;
            this.enabled = false;
            if (this.count == 2) {
               return;
            }
         }

         if (this.count != -1) {
            if (!this.enabled) {
               if (!this.isActive()) {
                  return;
               }

               this.enabled = true;
            }

            if (this.count3 > 0) {
               this.count3--;
            } else {
               this.count3 = 2;
               if (this.count2 > 0) {
                  this.isActiveAsFlag();
                  this.count2--;
               } else {
                  this.count = 2;
               }
            }
         }
      }
   }

   private boolean isActiveAsFlag() {
      Vec3 vec = this.resolveVec3(new BlockPos(this.count4, this.count5, this.count6));
      if (vec == null) {
         return false;
      } else {
         EntityZombie entityZombie;
         try {
            entityZombie = new EntityZombie(this.world);
            entityZombie.resolvePostRenderConsumer(this.world.resolveGoldenOre(new BlockPos(entityZombie)), (PostRenderConsumer)null);
            entityZombie.setEnabled6(false);
         } catch (Exception exception) {
            exception.printStackTrace();
            return false;
         }

         entityZombie.applyDoubleByDouble(vec.xCoord, vec.yCoord, vec.zCoord, this.world.random.nextFloat() * 360.0F, 0.0F);
         this.world.matchesEntity(entityZombie);
         BlockPos blockPos = this.splineChecker.getBlockPos2();
         entityZombie.applyBlockPos(blockPos, this.splineChecker.getCount());
         return true;
      }
   }

   private boolean isActive() {
      for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable)(this.world.itemList7)) {
         if (!player.isActive23()) {
            this.splineChecker = this.world.getWorldCustomProperty().resolveSplineChecker(new BlockPos(player), 1);
            if (this.splineChecker != null
               && this.splineChecker.size() >= 10
               && this.splineChecker.getCountAsIndex() >= 20
               && this.splineChecker.getCount4() >= 20) {
               BlockPos blockPos = this.splineChecker.getBlockPos2();
               float count7 = (float)this.splineChecker.getCount();
               boolean flag = false;

               for (int index = 0; index < 10; index++) {
                  float factor = this.world.random.nextFloat() * (float) Math.PI * 2.0F;
                  this.count4 = blockPos.getX() + (int)((double)(MathHelper.cos(factor) * count7) * 0.9);
                  this.count5 = blockPos.getY();
                  this.count6 = blockPos.getZ() + (int)((double)(MathHelper.abs(factor) * count7) * 0.9);
                  flag = false;

                  for (SplineChecker splineChecker2 : (Iterable<SplineChecker>)(Iterable)(this.world.getWorldCustomProperty().getList3())) {
                     if (splineChecker2 != this.splineChecker && splineChecker2.matchesBlockPosByBlockPos(new BlockPos(this.count4, this.count5, this.count6))) {
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

               if (this.resolveVec3(new BlockPos(this.count4, this.count5, this.count6)) != null) {
                  this.count3 = 0;
                  this.count2 = 20;
                  return true;
               }
            }
         }
      }

      return false;
   }
}
