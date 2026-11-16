package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityCow;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class DoMobLootClientManager extends RotationPool {
   public HitBoxBuffer hitBoxBuffer2;
   public World world;
   public HitBoxBuffer hitBoxBuffer;
   public int count2;
   public double value;

   private void updateState4() {
      UUIDEntryAdapter uUIDEntryAdapter = this.hitBoxBuffer2.resolveUUIDEntryAdapter(this.hitBoxBuffer);
      if (uUIDEntryAdapter != null) {
         EntityPlayer entityPlayer = this.hitBoxBuffer2.getEntityPlayer2();
         if (entityPlayer == null && this.hitBoxBuffer.getEntityPlayer2() != null) {
            entityPlayer = this.hitBoxBuffer.getEntityPlayer2();
         }

         if (entityPlayer != null) {
            entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager10);
            if (this.hitBoxBuffer2 instanceof EntityCow) {
               entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter6);
            }
         }

         this.hitBoxBuffer2.applyIndex8(6000);
         this.hitBoxBuffer.applyIndex8(6000);
         this.hitBoxBuffer2.updateState49();
         this.hitBoxBuffer.updateState49();
         uUIDEntryAdapter.applyIndex8(-24000);
         uUIDEntryAdapter.applyDoubleByDouble(this.hitBoxBuffer2.posX, this.hitBoxBuffer2.posY, this.hitBoxBuffer2.posZ, 0.0F, 0.0F);
         this.world.matchesEntity(uUIDEntryAdapter);
         Random random = this.hitBoxBuffer2.getRandom();

         for (int index = 0; index < 7; index++) {
            double offset = random.nextGaussian() * 0.02;
            double offset2 = random.nextGaussian() * 0.02;
            double offset3 = random.nextGaussian() * 0.02;
            double offset4 = random.nextDouble() * (double)this.hitBoxBuffer2.width * 2.0 - (double)this.hitBoxBuffer2.width;
            double offset5 = 0.5 + random.nextDouble() * (double)this.hitBoxBuffer2.height;
            double offset6 = random.nextDouble() * (double)this.hitBoxBuffer2.width * 2.0 - (double)this.hitBoxBuffer2.width;
            this.world
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.HEART,
                  this.hitBoxBuffer2.posX + offset4,
                  this.hitBoxBuffer2.posY + offset5,
                  this.hitBoxBuffer2.posZ + offset6,
                  offset,
                  offset2,
                  offset3,
                  new int[0]
               );
         }

         if (this.world.getDoFireTickManager().matchesText("doMobLoot")) {
            this.world
               .matchesEntity(
                  new EntityXporb(this.world, this.hitBoxBuffer2.posX, this.hitBoxBuffer2.posY, this.hitBoxBuffer2.posZ, random.nextInt(7) + 1)
               );
         }
      }
   }

   @Override
   public void updateState() {
      this.hitBoxBuffer = null;
      this.count2 = 0;
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.hitBoxBuffer.canExecute() && this.hitBoxBuffer.isActive31() && this.count2 < 60;
   }

   private HitBoxBuffer getHitBoxBuffer() {
      float factor = 8.0F;
      List list = this.world.resolveList(this.hitBoxBuffer2.getClass(), this.hitBoxBuffer2.getBoundingBox2().offset((double)factor, (double)factor, (double)factor));
      double offset = Double.MAX_VALUE;
      HitBoxBuffer hitBoxBuffer3 = null;

      for (HitBoxBuffer hitBoxBuffer4 : (Iterable<HitBoxBuffer>)(Iterable<?>)list) {
         if (this.hitBoxBuffer2.matchesHitBoxBuffer(hitBoxBuffer4) && this.hitBoxBuffer2.getCoordinate(hitBoxBuffer4) < offset) {
            hitBoxBuffer3 = hitBoxBuffer4;
            offset = this.hitBoxBuffer2.getCoordinate(hitBoxBuffer4);
         }
      }

      return hitBoxBuffer3;
   }

   @Override
   public void updateState2() {
      this.hitBoxBuffer2.getEntityRavagerAdapter().applyEntityByEntity(this.hitBoxBuffer, 10.0F, (float)this.hitBoxBuffer2.getCount8());
      this.hitBoxBuffer2.getPathfindManager().matchesEntityByEntity(this.hitBoxBuffer, this.value);
      this.count2++;
      if (this.count2 >= 60 && this.hitBoxBuffer2.getCoordinate(this.hitBoxBuffer) < 9.0) {
         this.updateState4();
      }
   }

   @Override
   public boolean isActive() {
      if (!this.hitBoxBuffer2.isActive31()) {
         return false;
      } else {
         this.hitBoxBuffer = this.getHitBoxBuffer();
         return this.hitBoxBuffer != null;
      }
   }

   public DoMobLootClientManager(HitBoxBuffer hitBoxBuffer3, double offset) {
      this.hitBoxBuffer2 = hitBoxBuffer3;
      this.world = hitBoxBuffer3.worldObj;
      this.value = offset;
      this.setCount(3);
   }
}
