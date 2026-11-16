package net.minecraft.entity;

import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.CommonClientManagerAdapter;
import client.util.DoMobLootClientManager;
import client.util.HitBoxBuffer;
import client.util.PathfindManagerAdapter;
import client.util.StandardClientManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GlobalItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityCow extends HitBoxBuffer {
   @Override
   public float getFactor() {
      return 0.4F;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.cow.step", 0.15F, 1.0F);
   }

   @Override
   public String getText9() {
      return "mob.cow.hurt";
   }

   public EntityCow resolveEntityCow(UUIDEntryAdapter uUIDEntryAdapter) {
      return new EntityCow(this.worldObj);
   }

   @Override
   public float getHeight2() {
      return this.height;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.2F);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3) + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.leather, 1);
      }

      int count3 = this.random.nextInt(3) + 1 + this.random.nextInt(1 + count);

      for (int index2 = 0; index2 < count3; index2++) {
         if (this.canExecute2()) {
            this.resolveEntityItem(Items.cookedBeef, 1);
         } else {
            this.resolveEntityItem(Items.beef, 1);
         }
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityCow(uUIDEntryAdapter);
   }

   @Override
   public Item getGunpowder() {
      return Items.leather;
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.bucket && !player.invulnerableManager.enabled2 && !this.isActive16()) {
         if (slot.animationsToGo-- == 1) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, new ItemStack(Items.milkBucket));
         } else if (!player.inventoryPlayer.checkItemStack3(new ItemStack(Items.milkBucket))) {
            player.resolveEntityItem(new ItemStack(Items.milkBucket, 1, 0), false);
         }

         return true;
      } else {
         return super.matchesEntityPlayer6(player);
      }
   }

   @Override
   public String getText3() {
      return "mob.cow.say";
   }

   @Override
   public String getText10() {
      return "mob.cow.hurt";
   }

   public EntityCow(World world) {
      super(world);
      this.applyFloat(0.9F, 1.3F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new CommonClientManagerAdapter(this, 2.0));
      this.goalSetupManager.add(2, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(3, new StandardClientManagerAdapter(this, 1.25, Items.wheat, false));
      this.goalSetupManager.add(4, new GlobalItemEntry(this, 1.25));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(7, new ComplexItemEntry(this));
   }
}
