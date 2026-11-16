package net.minecraft.entity;

import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryPredicateEntry;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ExtendedClassEntry;
import client.util.HUIHRVManager;
import client.util.SupplementalClientManagerAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.ExternalItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCreeper extends EntityMonster {
   public int count30;
   public int count31;
   public int count33 = 30;
   public int count35 = 3;
   public int count32 = 0;

   public boolean isActive32() {
      return this.dataWatcher.computeByteCode(18) != 0;
   }

   @Override
   public String getText9() {
      return "mob.creeper.death";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.dataWatcher.applyTargetByTarget(17, Byte.valueOf((byte)(tagCompound.matchesText("powered") ? 1 : 0)));
      if (tagCompound.matchesText("Fuse", 99)) {
         this.count33 = tagCompound.processValue("Fuse");
      }

      if (tagCompound.matchesText("ExplosionRadius", 99)) {
         this.count35 = tagCompound.computeByteCode("ExplosionRadius");
      }

      if (tagCompound.matchesText("ignited")) {
         this.updateState49();
      }
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      return true;
   }

   @Override
   public int getCount3() {
      return this.getEntityLivingBase4() == null ? 3 : 3 + (int)(this.getFloat2() - 1.0F);
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   private void updateState50() {
      if (!this.worldObj.state) {
         boolean flag = this.worldObj.getDoFireTickManager().matchesText("mobGriefing");
         float factor = this.isActive34() ? 2.0F : 1.0F;
         this.worldObj.resolveBlockBreakStateByEntity(this, this.posX, this.posY, this.posZ, (float)this.count35 * factor, flag);
         this.updateState9();
      }
   }

   @Override
   public void processFloat2(float factor, float factor2) {
      super.processFloat2(factor, factor2);
      this.count31 = (int)((float)this.count31 + factor * 1.5F);
      if (this.count31 > this.count33 - 5) {
         this.count31 = this.count33 - 5;
      }
   }

   @Override
   public Item getGunpowder() {
      return Items.gunpowder;
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() == Items.flintAndSteel) {
         this.worldObj.applyText(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.ignite", 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
         player.respawnPlayer();
         if (!this.worldObj.state) {
            this.updateState49();
            slot.applyEntityLivingBaseByEntityLivingBase(1, player);
            return true;
         }
      }

      return super.matchesEntityPlayer6(player);
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (damageSource.getEntity() instanceof EntitySkeleton) {
         int index = Item.getIndex(Items.record13);
         int index2 = Item.getIndex(Items.recordWait);
         int count = index + this.random.nextInt(index2 - index + 1);
         this.resolveEntityItem(Item.processAction(count), 1);
      } else if (damageSource.getEntity() instanceof EntityCreeper
         && damageSource.getEntity() != this
         && ((EntityCreeper)damageSource.getEntity()).isActive34()
         && ((EntityCreeper)damageSource.getEntity()).isActive33()) {
         ((EntityCreeper)damageSource.getEntity()).updateState48();
         this.resolveEntityItem(new ItemStack(Items.skull, 1, 4), 0.0F);
      }
   }

   public float computeFactor3(float factor) {
      return ((float)this.count30 + (float)(this.count31 - this.count30) * factor) / (float)(this.count33 - 2);
   }

   @Override
   public void applyHUIHRVManager(HUIHRVManager hUIHRVManager) {
      super.applyHUIHRVManager(hUIHRVManager);
      this.dataWatcher.applyTargetByTarget(17, (byte)1);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (this.dataWatcher.computeByteCode(17) == 1) {
         tagCompound.applyText("powered", true);
      }

      tagCompound.put("Fuse", (short)this.count33);
      tagCompound.put("ExplosionRadius", (byte)this.count35);
      tagCompound.applyText("ignited", this.isActive32());
   }

   @Override
   public String getText10() {
      return "mob.creeper.say";
   }

   public boolean isActive33() {
      return this.count32 < 1 && this.worldObj.getDoFireTickManager().matchesText("doMobLoot");
   }

   public void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(16, (byte)count);
   }

   public int getCount9() {
      return this.dataWatcher.computeByteCode(16);
   }

   @Override
   public void updateState() {
      if (this.canExecute()) {
         this.count30 = this.count31;
         if (this.isActive32()) {
            this.applyIndex8(1);
         }

         int count = this.getCount9();
         if (count > 0 && this.count31 == 0) {
            this.applyText("creeper.primed", 1.0F, 0.5F);
         }

         this.count31 += count;
         if (this.count31 < 0) {
            this.count31 = 0;
         }

         if (this.count31 >= this.count33) {
            this.count31 = this.count33;
            this.updateState50();
         }
      }

      super.updateState();
   }

   public boolean isActive34() {
      return this.dataWatcher.computeByteCode(17) == 1;
   }

   public void updateState49() {
      this.dataWatcher.applyTargetByTarget(18, (byte)1);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)-1);
      this.dataWatcher.applyTarget(17, (byte)0);
      this.dataWatcher.applyTarget(18, (byte)0);
   }

   public void updateState48() {
      this.count32++;
   }

   public EntityCreeper(World world) {
      super(world);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new ExternalItemEntry(this));
      this.goalSetupManager.add(3, new AuxiliaryPredicateEntry(this, EntityOzelot.class, 6.0F, 1.0, 1.2));
      this.goalSetupManager.add(4, new AuxiliaryWorldEntry(this, 1.0, false));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 0.8));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(6, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ExtendedClassEntry(this, EntityPlayer.class, true));
      this.goalSetupManager2.add(2, new ClassArrayEntry(this, false, new Class[0]));
   }
}
