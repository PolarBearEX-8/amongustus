package client.util;

import net.minecraft.entity.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class RotationFactory extends RotationPool {
   public EntityWolf entityWolf;
   public EntityPlayer entityPlayer;
   public World world;
   public float factor;
   public int count2;

   private boolean matchesEntityPlayer(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      return slot == null ? false : (!this.entityWolf.isActive32() && slot.getItem() == Items.bone ? true : this.entityWolf.matchesItemStackByItemStack(slot));
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.entityPlayer.canExecute()
         ? false
         : (
            this.entityWolf.getCoordinate(this.entityPlayer) > (double)(this.factor * this.factor)
               ? false
               : this.count2 > 0 && this.matchesEntityPlayer(this.entityPlayer)
         );
   }

   @Override
   public boolean isActive() {
      this.entityPlayer = this.world.resolveEntityPlayerByEntity(this.entityWolf, (double)this.factor);
      return this.entityPlayer == null ? false : this.matchesEntityPlayer(this.entityPlayer);
   }

   @Override
   public void updateState2() {
      this.entityWolf
         .getEntityRavagerAdapter()
         .applyDouble(
            this.entityPlayer.posX,
            this.entityPlayer.posY + (double)this.entityPlayer.getHeight2(),
            this.entityPlayer.posZ,
            10.0F,
            (float)this.entityWolf.getCount8()
         );
      this.count2--;
   }

   @Override
   public void updateState3() {
      this.entityWolf.setEnabled10(true);
      this.count2 = 40 + this.entityWolf.getRandom().nextInt(40);
   }

   public RotationFactory(EntityWolf entityWolf2, float factor2) {
      this.entityWolf = entityWolf2;
      this.world = entityWolf2.worldObj;
      this.factor = factor2;
      this.setCount(2);
   }

   @Override
   public void updateState() {
      this.entityWolf.setEnabled10(false);
      this.entityPlayer = null;
   }
}
