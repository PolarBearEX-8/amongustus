package net.minecraft.entity;

import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.CommonClientManagerAdapter;
import client.util.DoMobLootClientManager;
import client.util.HUIHRVManager;
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
import net.minecraft.item.SimpleItemEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityPig extends HitBoxBuffer {
   public final SimpleItemEntry simpleItemEntry;

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack != null && stack.getItem() == Items.carrot;
   }

   @Override
   public String getText9() {
      return "mob.pig.death";
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.pig.step", 0.15F, 1.0F);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled7(tagCompound.matchesText("Saddle"));
   }

   public SimpleItemEntry getSimpleItemEntry() {
      return this.simpleItemEntry;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      super.processFloat2(factor, factor2);
      if (factor > 5.0F && this.entity instanceof EntityPlayer) {
         ((EntityPlayer)this.entity).applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter4);
      }
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3) + 1 + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         if (this.canExecute2()) {
            this.resolveEntityItem(Items.cookedPorkchop, 1);
         } else {
            this.resolveEntityItem(Items.porkchop, 1);
         }
      }

      if (this.isActive32()) {
         this.resolveEntityItem(Items.saddle, 1);
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityPig(uUIDEntryAdapter);
   }

   @Override
   public Item getGunpowder() {
      return this.canExecute2() ? Items.cookedPorkchop : Items.porkchop;
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      if (super.matchesEntityPlayer6(player)) {
         return true;
      } else if (this.isActive32() && !this.worldObj.state && (this.entity == null || this.entity == player)) {
         player.setEntity8(this);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String getText3() {
      return "mob.pig.say";
   }

   @Override
   public void applyHUIHRVManager(HUIHRVManager hUIHRVManager) {
      if (!this.worldObj.state && !this.isDead) {
         EntityPigzombie entityPigzombie = new EntityPigzombie(this.worldObj);
         entityPigzombie.applyItemStack(0, new ItemStack(Items.goldenSword));
         entityPigzombie.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         entityPigzombie.setEnabled5(this.isActive25());
         if (this.processAction()) {
            entityPigzombie.setLabelText(this.getText2());
            entityPigzombie.setEnabled(this.isActive6());
         }

         this.worldObj.matchesEntity(entityPigzombie);
         this.updateState9();
      }
   }

   @Override
   public String getText10() {
      return "mob.pig.say";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Saddle", this.isActive32());
   }

   public EntityPig resolveEntityPig(UUIDEntryAdapter uUIDEntryAdapter) {
      return new EntityPig(this.worldObj);
   }

   public boolean isActive32() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   @Override
   public boolean isActive27() {
      ItemStack stack = ((EntityPlayer)this.entity).getItemStack();
      return stack != null && stack.getItem() == Items.carrotOnAStick;
   }

   public void setEnabled7(boolean enabled) {
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)1);
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)0);
      }
   }

   public EntityPig(World world) {
      super(world);
      this.applyFloat(0.9F, 0.9F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new CommonClientManagerAdapter(this, 1.25));
      this.goalSetupManager.add(2, this.simpleItemEntry = new SimpleItemEntry(this, 0.3F));
      this.goalSetupManager.add(3, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(4, new StandardClientManagerAdapter(this, 1.2, Items.carrotOnAStick, false));
      this.goalSetupManager.add(4, new StandardClientManagerAdapter(this, 1.2, Items.carrot, false));
      this.goalSetupManager.add(5, new GlobalItemEntry(this, 1.1));
      this.goalSetupManager.add(6, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
   }
}
