package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryPredicateEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClientManagerBridge;
import client.util.DoMobLootClientManager;
import client.util.HitBoxAdapter;
import client.util.HitBoxBuffer;
import client.util.HitBoxDispatcher;
import client.util.HitBoxEngine;
import client.util.HitBoxScheduler;
import client.util.PathfindManagerAdapter;
import client.util.RotationScheduler;
import client.util.StandardClientManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOzelot extends HitBoxAdapter {
   public AuxiliaryPredicateEntry auxiliaryPredicateEntry;
   public StandardClientManagerAdapter standardClientManagerAdapter;

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack != null && stack.getItem() == Items.fish;
   }

   @Override
   public float getFactor() {
      return 0.4F;
   }

   @Override
   public String getText9() {
      return "mob.cat.hitt";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.applyIndex10(tagCompound.computeCount("CatType"));
   }

   @Override
   public String getText() {
      return this.processAction() ? this.getText2() : (this.isActive32() ? I18n.getString2("entity.Cat.name") : super.getText());
   }

   @Override
   public boolean matchesHitBoxBuffer(HitBoxBuffer hitBoxBuffer) {
      if (hitBoxBuffer == this) {
         return false;
      } else if (!this.isActive32()) {
         return false;
      } else if (!(hitBoxBuffer instanceof EntityOzelot)) {
         return false;
      } else {
         EntityOzelot entityOzelot = (EntityOzelot)hitBoxBuffer;
         return !entityOzelot.isActive32() ? false : this.isActive31() && entityOzelot.isActive31();
      }
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      return entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), 3.0F);
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
   }

   @Override
   public boolean isActive23() {
      if (this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this)
         && this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()
         && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2())) {
         BlockPos blockPos = new BlockPos(this.posX, this.getBoundingBox2().minY, this.posZ);
         if (blockPos.getY() < this.worldObj.getIndexOffset1()) {
            return false;
         }

         Block block = this.worldObj.getBlockState(blockPos.down()).getBlock();
         if (block == Blocks.blockGrass || block.getMaterial() == Material.material9) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public Item getGunpowder() {
      return Items.leather;
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityOzelot(uUIDEntryAdapter);
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (this.isActive32()) {
         if (this.matchesEntityLivingBase3(player) && !this.worldObj.state && !this.matchesItemStackByItemStack(slot)) {
            this.hitBoxCoordinator.setEnabled(!this.isActive33());
         }
      } else if (this.standardClientManagerAdapter.isEnabled() && slot != null && slot.getItem() == Items.fish && player.getCoordinate(this) < 9.0) {
         if (!player.invulnerableManager.enabled2) {
            slot.animationsToGo--;
         }

         if (slot.animationsToGo <= 0) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
         }

         if (!this.worldObj.state) {
            if (this.random.nextInt(3) == 0) {
               this.setEnabled9(true);
               this.applyIndex10(1 + this.worldObj.random.nextInt(3));
               this.applyText(player.getEntityUniqueID().toString());
               this.setEnabled7(true);
               this.hitBoxCoordinator.setEnabled(true);
               this.worldObj.applyEntity(this, (byte)7);
            } else {
               this.setEnabled7(false);
               this.worldObj.applyEntity(this, (byte)6);
            }
         }

         return true;
      }

      return super.matchesEntityPlayer6(player);
   }

   @Override
   public String getText3() {
      return this.isActive32() ? (this.isActive31() ? "mob.cat.purr" : (this.random.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      PostRenderConsumer postRenderConsumer2 = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      if (this.worldObj.random.nextInt(7) == 0) {
         for (int index = 0; index < 2; index++) {
            EntityOzelot entityOzelot = new EntityOzelot(this.worldObj);
            entityOzelot.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityOzelot.applyIndex8(-24000);
            this.worldObj.matchesEntity(entityOzelot);
         }
      }

      return postRenderConsumer2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("CatType", this.getCount10());
   }

   @Override
   public String getText10() {
      return "mob.cat.hitt";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.hitBoxCoordinator.setEnabled(false);
         return super.matchesDamageSource(damageSource, factor);
      }
   }

   public EntityOzelot resolveEntityOzelot(UUIDEntryAdapter uUIDEntryAdapter) {
      EntityOzelot entityOzelot = new EntityOzelot(this.worldObj);
      if (this.isActive32()) {
         entityOzelot.applyText(this.getTextAsText());
         entityOzelot.setEnabled9(true);
         entityOzelot.applyIndex10(this.getCount10());
      }

      return entityOzelot;
   }

   public int getCount10() {
      return this.dataWatcher.computeByteCode(18);
   }

   @Override
   public void updateState3() {
      if (this.getEntityPillagerAdapter().isEnabled()) {
         double value = this.getEntityPillagerAdapter().getValue();
         if (value == 0.6) {
            this.setEnabled2(true);
            this.setState3(false);
         } else if (value == 1.33) {
            this.setEnabled2(false);
            this.setState3(true);
         } else {
            this.setEnabled2(false);
            this.setState3(false);
         }
      } else {
         this.setEnabled2(false);
         this.setState3(false);
      }
   }

   @Override
   public void updateState50() {
      if (this.auxiliaryPredicateEntry == null) {
         this.auxiliaryPredicateEntry = new AuxiliaryPredicateEntry(this, EntityPlayer.class, 16.0F, 0.8, 1.33);
      }

      this.goalSetupManager.applyRotationPool(this.auxiliaryPredicateEntry);
      if (!this.isActive32()) {
         this.goalSetupManager.add(4, this.auxiliaryPredicateEntry);
      }
   }

   public void applyIndex10(int count) {
      this.dataWatcher.applyTargetByTarget(18, (byte)count);
   }

   @Override
   public void setEnabled9(boolean enabled) {
      super.setEnabled9(enabled);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(18, (byte)0);
   }

   @Override
   public boolean isActive24() {
      return !this.isActive32() && this.ticksExisted > 2400;
   }

   @Override
   public boolean nextInt() {
      return this.worldObj.random.nextInt(3) != 0;
   }

   public EntityOzelot(World world) {
      super(world);
      this.applyFloat(0.6F, 0.7F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, this.hitBoxCoordinator);
      this.goalSetupManager.add(3, this.standardClientManagerAdapter = new StandardClientManagerAdapter(this, 0.6, Items.fish, true));
      this.goalSetupManager.add(5, new RotationScheduler(this, 1.0, 10.0F, 5.0F));
      this.goalSetupManager.add(6, new HitBoxDispatcher(this, 0.8));
      this.goalSetupManager.add(7, new ClientManagerBridge(this, 0.3F));
      this.goalSetupManager.add(8, new HitBoxEngine(this));
      this.goalSetupManager.add(9, new DoMobLootClientManager(this, 0.8));
      this.goalSetupManager.add(10, new AdvancedClientManagerAdapter(this, 0.8));
      this.goalSetupManager.add(11, new BaseClientManagerAdapter(this, EntityPlayer.class, 10.0F));
      this.goalSetupManager2.add(1, new HitBoxScheduler(this, EntityChicken.class, false, (Predicate)null));
   }
}
