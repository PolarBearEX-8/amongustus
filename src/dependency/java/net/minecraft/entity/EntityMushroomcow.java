package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.UUIDEntryAdapter;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class EntityMushroomcow extends EntityCow {
   public EntityMushroomcow resolveEntityMushroomcow(UUIDEntryAdapter uUIDEntryAdapter) {
      return new EntityMushroomcow(this.worldObj);
   }

   @Override
   public EntityCow resolveEntityCow(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityMushroomcow(uUIDEntryAdapter);
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityMushroomcow(uUIDEntryAdapter);
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.bowl && this.getCount9() >= 0) {
         if (slot.animationsToGo == 1) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, new ItemStack(Items.mushroomStew));
            return true;
         }

         if (player.inventoryPlayer.checkItemStack3(new ItemStack(Items.mushroomStew)) && !player.invulnerableManager.enabled2) {
            player.inventoryPlayer.getItemStack(player.inventoryPlayer.currentItem, 1);
            return true;
         }
      }

      if (slot != null && slot.getItem() == Items.shears && this.getCount9() >= 0) {
         this.updateState9();
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.EXPLOSION_LARGE,
               this.posX,
               this.posY + (double)(this.height / 2.0F),
               this.posZ,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         if (!this.worldObj.state) {
            EntityCow entityCow = new EntityCow(this.worldObj);
            entityCow.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entityCow.applyFloat(this.getFloat2());
            entityCow.scaleFactor8 = this.scaleFactor8;
            if (this.processAction()) {
               entityCow.setLabelText(this.getText2());
            }

            this.worldObj.matchesEntity(entityCow);

            for (int index = 0; index < 5; index++) {
               this.worldObj
                  .matchesEntity(
                     new EntityItem(this.worldObj, this.posX, this.posY + (double)this.height, this.posZ, new ItemStack(Blocks.secondaryBlockAdapter))
                  );
            }

            slot.applyEntityLivingBaseByEntityLivingBase(1, player);
            this.applyText("mob.sheep.shear", 1.0F, 1.0F);
         }

         return true;
      } else {
         return super.matchesEntityPlayer6(player);
      }
   }

   public EntityMushroomcow(World world) {
      super(world);
      this.applyFloat(0.9F, 1.3F);
      this.block = Blocks.blockMycelium;
   }
}
