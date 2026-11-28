package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMinecartEmpty extends EntityMinecart {
   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.RIDEABLE;
   }

   @Override
   public void onActivatorRailPass(int n, int n2, int n3, boolean flag) {
      if (flag) {
         if (this.riddenByEntity != null) {
            this.riddenByEntity.mountEntity((Entity)null);
         }

         if (this.getRollingAmplitude() == 0) {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.setDamage(50.0F);
            this.setBeenAttacked();
         }
      }
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player) {
         return true;
      } else if (this.riddenByEntity != null && this.riddenByEntity != player) {
         return false;
      } else {
         if (!this.entityWorld.isRemote) {
            player.mountEntity(this);
         }

         return true;
      }
   }

   public EntityMinecartEmpty(World world) {
      super(world);
   }

   public EntityMinecartEmpty(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }
}
