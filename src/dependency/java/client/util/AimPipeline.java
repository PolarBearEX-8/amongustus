package client.util;

import client.event.NativeListener;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class AimPipeline extends EntityMob implements NativeListener {
   @Override
   public int nextInt(EntityPlayer player) {
      return 1 + this.worldObj.random.nextInt(3);
   }

   @Override
   public boolean isActive23() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this);
   }

   @Override
   public boolean isActive17() {
      return true;
   }

   @Override
   public int getCount7() {
      return 120;
   }

   @Override
   public void updateState10() {
      int count = this.getCount();
      super.updateState10();
      if (this.canExecute() && !this.isInWater()) {
         this.applyIndex3(--count);
         if (this.getCount() == -20) {
            this.applyIndex3(0);
            this.matchesDamageSource(DamageSource.damageSource, 2.0F);
         }
      } else {
         this.applyIndex3(300);
      }
   }

   @Override
   public boolean isActive8() {
      return false;
   }

   @Override
   public boolean isActive24() {
      return true;
   }

   @Override
   public boolean nextInt() {
      return true;
   }

   public AimPipeline(World world) {
      super(world);
   }
}
