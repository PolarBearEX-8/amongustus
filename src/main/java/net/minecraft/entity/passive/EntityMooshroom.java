package net.minecraft.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityMooshroom extends EntityCow {
   public EntityMooshroom createMooshroomChild(EntityAgeable entityAgeable) {
      return new EntityMooshroom(this.entityWorld);
   }

   @Override
   public boolean interact(EntityPlayer player) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() == Items.bowl && this.getGrowingAge() >= 0) {
         if (currentItem.stackSize == 1) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.mushroom_stew));
            return true;
         }

         if (player.inventory.addItemStackToInventory(new ItemStack(Items.mushroom_stew)) && !player.capabilities.isCreativeMode) {
            player.inventory.decrStackSize(player.inventory.currentItem, 1);
            return true;
         }
      }

      if (currentItem != null && currentItem.getItem() == Items.shears && this.getGrowingAge() >= 0) {
         this.setDead();
         this.entityWorld.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + (double)(this.height / 2.0F), this.posZ, 0.0, 0.0, 0.0, new int[0]);
         if (!this.entityWorld.isRemote) {
            EntityCow entityCow = new EntityCow(this.entityWorld);
            entityCow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entityCow.setHealth(this.getCurrentHealth());
            entityCow.renderYawOffset = this.renderYawOffset;
            if (this.hasCustomName()) {
               entityCow.setCustomNameTag(this.getCustomNameTag());
            }

            this.entityWorld.spawnEntityInWorld(entityCow);

            for (int i = 0; i < 5; i++) {
               this.entityWorld
                  .spawnEntityInWorld(new EntityItem(this.entityWorld, this.posX, this.posY + (double)this.height, this.posZ, new ItemStack(Blocks.red_mushroom)));
            }

            currentItem.damageItem(1, player);
            this.playSound("mob.sheep.shear", 1.0F, 1.0F);
         }

         return true;
      } else {
         return super.interact(player);
      }
   }

   @Override
   public EntityAgeable createAgeableChild(EntityAgeable entityAgeable) {
      return this.createMooshroomChild(entityAgeable);
   }

   @Override
   public EntityCow createChild(EntityAgeable entityAgeable) {
      return this.createMooshroomChild(entityAgeable);
   }

   public EntityMooshroom(World world) {
      super(world);
      this.setSize(0.9F, 1.3F);
      this.spawnableBlock = Blocks.mycelium;
   }
}
