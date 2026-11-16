package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StandardClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public double value2;
   public double value3;
   public double value4;
   public double value5;
   public double value6;
   public EntityPlayer entityPlayer;
   public int count2;
   public boolean enabled;
   public Item item;
   public boolean enabled2;
   public boolean enabled3;

   @Override
   public boolean isActive() {
      if (this.count2 > 0) {
         this.count2--;
         return false;
      } else {
         this.entityPlayer = this.auxiliaryUUIDEntry.worldObj.resolveEntityPlayerByEntity(this.auxiliaryUUIDEntry, 10.0);
         if (this.entityPlayer == null) {
            return false;
         } else {
            ItemStack stack = this.entityPlayer.getHeldItem();
            return stack == null ? false : stack.getItem() == this.item;
         }
      }
   }

   @Override
   public void updateState() {
      this.entityPlayer = null;
      this.auxiliaryUUIDEntry.getPathfindManager().updateState4();
      this.count2 = 100;
      this.enabled = false;
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled3(this.enabled3);
   }

   @Override
   public void updateState2() {
      this.auxiliaryUUIDEntry.getEntityRavagerAdapter().applyEntityByEntity(this.entityPlayer, 30.0F, (float)this.auxiliaryUUIDEntry.getCount8());
      if (this.auxiliaryUUIDEntry.getCoordinate(this.entityPlayer) < 6.25) {
         this.auxiliaryUUIDEntry.getPathfindManager().updateState4();
      } else {
         this.auxiliaryUUIDEntry.getPathfindManager().matchesEntityByEntity(this.entityPlayer, this.value);
      }
   }

   @Override
   public void updateState3() {
      this.value2 = this.entityPlayer.posX;
      this.value3 = this.entityPlayer.posY;
      this.value4 = this.entityPlayer.posZ;
      this.enabled = true;
      this.enabled3 = ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).isActive4();
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled3(false);
   }

   public StandardClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset, Item item2, boolean flag) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value = offset;
      this.item = item2;
      this.enabled2 = flag;
      this.setCount(3);
      if (!(auxiliaryUUIDEntry2.getPathfindManager() instanceof PathfindManagerAdapter)) {
         throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public boolean isActiveAsFlag() {
      if (this.enabled2) {
         if (this.auxiliaryUUIDEntry.getCoordinate(this.entityPlayer) < 36.0) {
            if (this.entityPlayer.getDouble3(this.value2, this.value3, this.value4) > 0.010000000000000002) {
               return false;
            }

            if (Math.abs((double)this.entityPlayer.rotationPitch - this.value5) > 5.0 || Math.abs((double)this.entityPlayer.rotationYaw - this.value6) > 5.0) {
               return false;
            }
         } else {
            this.value2 = this.entityPlayer.posX;
            this.value3 = this.entityPlayer.posY;
            this.value4 = this.entityPlayer.posZ;
         }

         this.value5 = (double)this.entityPlayer.rotationPitch;
         this.value6 = (double)this.entityPlayer.rotationYaw;
      }

      return this.isActive();
   }
}
