package client.util;

import client.setting.MovementNumberProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class VectorConverter extends EntityMinecart {
   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (this.entity != null && this.entity instanceof EntityPlayer && this.entity != player) {
         return true;
      } else if (this.entity != null && this.entity != player) {
         return false;
      } else {
         if (!this.worldObj.state) {
            player.setEntity8(this);
         }

         return true;
      }
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.RIDEABLE;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, boolean flag) {
      if (flag) {
         if (this.entity != null) {
            this.entity.setEntity8((Entity)null);
         }

         if (this.getCount6() == 0) {
            this.applyIndex6(-this.getCount5());
            this.applyIndex5(10);
            this.applyFloat(50.0F);
            this.updateState8();
         }
      }
   }

   public VectorConverter(World world) {
      super(world);
   }

   public VectorConverter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
