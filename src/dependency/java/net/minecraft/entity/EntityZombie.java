package net.minecraft.entity;

import client.event.AuxiliaryListener;
import client.event.ExtendedListener;
import client.event.PostRenderConsumer;
import client.setting.DisablerUndefinedType;
import client.setting.GameSettingsPeacefulType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.CoreClientManagerAdapter;
import client.util.CustomClientManagerAdapter;
import client.util.ExtendedClassEntry;
import client.util.InvalidOperationManager;
import client.util.LivingEntityFilter;
import client.util.NativeDoubleEntry;
import client.util.PathfindManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEntry80Entry;
import net.minecraft.item.ItemEntryBridge;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZombie extends EntityMonster {
   public static final ExtendedListener extendedListener = new NativeDoubleEntry((ExtendedListener)null, "zombie.spawnReinforcements", 0.0, 0.0, 1.0)
      .withLabelText2("Spawn Reinforcements Chance");
   public static final UUID uUID4 = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   public static final InvalidOperationManager invalidOperationManager3 = new InvalidOperationManager(uUID4, "Baby speed boost", 0.5, 1);
   public final ItemEntryBridge itemEntryBridge = new ItemEntryBridge(this);
   public int count32;
   public boolean enabled28 = false;
   public float factor42 = -1.0F;
   public float factor41;

   @Override
   public String getText9() {
      return "mob.zombie.death";
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.zombie.step", 0.15F, 1.0F);
   }

   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.UNDEAD;
   }

   @Override
   public int nextInt(EntityPlayer player) {
      if (this.isActive16()) {
         this.count26 = (int)((float)this.count26 * 2.5F);
      }

      return super.nextInt(player);
   }

   @Override
   public float getHeight2() {
      float factor = 1.74F;
      if (this.isActive16()) {
         factor = (float)((double)factor - 0.81);
      }

      return factor;
   }

   @Override
   public int getCount4() {
      int count = super.getCount4() + 2;
      if (count > 20) {
         count = 20;
      }

      return count;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("IsBaby")) {
         this.setEnabled8(true);
      }

      if (tagCompound.matchesText("IsVillager")) {
         this.setEnabled6(true);
      }

      if (tagCompound.matchesText("ConversionTime", 99) && tagCompound.computeCount("ConversionTime") > -1) {
         this.applyIndex8(tagCompound.computeCount("ConversionTime"));
      }

      this.setEnabled9(tagCompound.matchesText("CanBreakDoors"));
   }

   @Override
   public final void applyFloat(float factor, float factor2) {
      boolean flag = this.factor42 > 0.0F && this.factor41 > 0.0F;
      this.factor42 = factor;
      this.factor41 = factor2;
      if (!flag) {
         this.applyFloat5(1.0F);
      }
   }

   @Override
   public double getValue() {
      return this.isActive16() ? 0.0 : -0.35;
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      boolean flag = super.matchesEntity4(entity);
      if (flag) {
         int index = this.worldObj.getGameSettingsPeacefulType().getIndex();
         if (this.getItemStack() == null && this.canExecute2() && this.random.nextFloat() < (float)index * 0.3F) {
            entity.applyIndexByIndex(2 * index);
         }
      }

      return flag;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(35.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.23F);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(3.0);
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(extendedListener).applyDouble(this.random.nextDouble() * 0.1F);
   }

   @Override
   public void updateState2() {
      switch (this.random.nextInt(3)) {
         case 0:
            this.resolveEntityItem(Items.ironIngot, 1);
            break;
         case 1:
            this.resolveEntityItem(Items.carrot, 1);
            break;
         case 2:
            this.resolveEntityItem(Items.potato, 1);
      }
   }

   @Override
   public void applyGoldenOre(GoldenOre goldenOre) {
      super.applyGoldenOre(goldenOre);
      if (this.random.nextFloat() < (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD ? 0.05F : 0.01F)) {
         if (this.random.nextInt(3) == 0) {
            this.applyItemStack(0, new ItemStack(Items.ironSword));
         } else {
            this.applyItemStack(0, new ItemStack(Items.ironShovel));
         }
      }
   }

   public final void applyFloat5(float factor) {
      super.applyFloat(this.factor42 * factor, this.factor41 * factor);
   }

   @Override
   public Item getGunpowder() {
      return Items.rottenFlesh;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 16) {
         if (!this.isActive7()) {
            this.worldObj
               .applyText(
                  this.posX + 0.5,
                  this.posY + 0.5,
                  this.posZ + 0.5,
                  "mob.zombie.remedy",
                  1.0F + this.random.nextFloat(),
                  this.random.nextFloat() * 0.7F + 0.3F,
                  false
               );
         }
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      ItemStack stack = player.getHeldItem();
      if (stack != null && stack.getItem() == Items.goldenApple && stack.getIndexOffset() == 0 && this.isActive32() && this.checkPotion(Potion.potion26)) {
         if (!player.invulnerableManager.enabled2) {
            stack.animationsToGo--;
         }

         if (stack.animationsToGo <= 0) {
            player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
         }

         if (!this.worldObj.state) {
            this.applyIndex8(this.random.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      Object entry = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      float factor = goldenOre.getFactor();
      this.setEnabled22(this.random.nextFloat() < 0.55F * factor);
      if (entry == null) {
         entry = new ItemEntry80Entry(this, this.worldObj.random.nextFloat() < 0.05F, this.worldObj.random.nextFloat() < 0.05F, null);
      }

      if (entry instanceof ItemEntry80Entry) {
         ItemEntry80Entry itemEntry80Entry = (ItemEntry80Entry)entry;
         if (itemEntry80Entry.enabled) {
            this.setEnabled6(true);
         }

         if (itemEntry80Entry.enabled2) {
            this.setEnabled8(true);
            if ((double)this.worldObj.random.nextFloat() < 0.05) {
               List list = this.worldObj.resolveList(EntityChicken.class, this.getBoundingBox2().offset(5.0, 3.0, 5.0), LivingEntityFilter.predicate2);
               if (!list.isEmpty()) {
                  EntityChicken entityChicken = (EntityChicken)list.get(0);
                  entityChicken.setEnabled28(true);
                  this.setEntity8(entityChicken);
               }
            } else if ((double)this.worldObj.random.nextFloat() < 0.05) {
               EntityChicken entityChicken2 = new EntityChicken(this.worldObj);
               entityChicken2.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
               entityChicken2.resolvePostRenderConsumer(goldenOre, (PostRenderConsumer)null);
               entityChicken2.setEnabled28(true);
               this.worldObj.matchesEntity(entityChicken2);
               this.setEntity8(entityChicken2);
            }
         }
      }

      this.setEnabled9(this.random.nextFloat() < factor * 0.1F);
      this.applyGoldenOre(goldenOre);
      this.applyGoldenOreByGoldenOre(goldenOre);
      if (this.resolveItemStack(4) == null) {
         Calendar calendar = this.worldObj.getCalendar();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.random.nextFloat() < 0.25F) {
            this.applyItemStack(4, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.block120 : Blocks.block146));
            this.factorArray[4] = 0.0F;
         }
      }

      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener2)
         .applyInvalidOperationManager(new InvalidOperationManager("Random spawn bonus", this.random.nextDouble() * 0.05F, 0));
      double offset = this.random.nextDouble() * 1.5 * (double)factor;
      if (offset > 1.0) {
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4)
            .applyInvalidOperationManager(new InvalidOperationManager("Random zombie-spawn bonus", offset, 2));
      }

      if (this.random.nextFloat() < factor * 0.05F) {
         this.getAuxiliaryListener(extendedListener)
            .applyInvalidOperationManager(new InvalidOperationManager("Leader zombie bonus", this.random.nextDouble() * 0.25 + 0.5, 0));
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5)
            .applyInvalidOperationManager(new InvalidOperationManager("Leader zombie bonus", this.random.nextDouble() * 3.0 + 1.0, 2));
         this.setEnabled9(true);
      }

      return (PostRenderConsumer)entry;
   }

   @Override
   public String getText3() {
      return "mob.zombie.say";
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (damageSource.getEntity() instanceof EntityCreeper
         && !(this instanceof EntityPigzombie)
         && ((EntityCreeper)damageSource.getEntity()).isActive34()
         && ((EntityCreeper)damageSource.getEntity()).isActive33()) {
         ((EntityCreeper)damageSource.getEntity()).updateState48();
         this.resolveEntityItem(new ItemStack(Items.skull, 1, 2), 0.0F);
      }
   }

   @Override
   public String getText10() {
      return "mob.zombie.hurt";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (super.matchesDamageSource(damageSource, factor)) {
         EntityLivingBase entityLivingBase = this.getEntityLivingBase4();
         if (entityLivingBase == null && damageSource.getEntity() instanceof EntityLivingBase) {
            entityLivingBase = (EntityLivingBase)damageSource.getEntity();
         }

         if (entityLivingBase != null
            && this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD
            && (double)this.random.nextFloat() < this.getAuxiliaryListener(extendedListener).getDouble()) {
            int count = MathHelper.ceiling_double_int(this.posX);
            int count2 = MathHelper.ceiling_double_int(this.posY);
            int count3 = MathHelper.ceiling_double_int(this.posZ);
            EntityZombie entityZombie = new EntityZombie(this.worldObj);

            for (int index = 0; index < 50; index++) {
               int count4 = count + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
               int count5 = count2 + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
               int count6 = count3 + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
               if (World.matchesIBlockAccess(this.worldObj, count4, count5 - 1, count6) && this.worldObj.computeCountByIndex(count4, count5, count6) < 10) {
                  entityZombie.processDouble2((double)count4, (double)count5, (double)count6);
                  if (!this.worldObj.checkDouble((double)count4, (double)count5, (double)count6, 7.0)
                     && this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(entityZombie.getBoundingBox2(), entityZombie)
                     && this.worldObj.getList2(entityZombie, entityZombie.getBoundingBox2()).isEmpty()
                     && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(entityZombie.getBoundingBox2())) {
                     this.worldObj.matchesEntity(entityZombie);
                     entityZombie.setEntityLivingBase(entityLivingBase);
                     entityZombie.resolvePostRenderConsumer(this.worldObj.resolveGoldenOre(new BlockPos(entityZombie)), (PostRenderConsumer)null);
                     this.getAuxiliaryListener(extendedListener)
                        .applyInvalidOperationManager(new InvalidOperationManager("Zombie reinforcement caller charge", -0.05F, 0));
                     entityZombie.getAuxiliaryListener(extendedListener)
                        .applyInvalidOperationManager(new InvalidOperationManager("Zombie reinforcement callee charge", -0.05F, 0));
                     break;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity) {
      super.applyEntityLivingBase(livingEntity);
      if ((
            this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.NORMAL
               || this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD
         )
         && livingEntity instanceof EntityVillager) {
         if (this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.HARD && this.random.nextBoolean()) {
            return;
         }

         EntityMob entityMob = (EntityMob)livingEntity;
         EntityZombie entityZombie = new EntityZombie(this.worldObj);
         entityZombie.applyEntity5(livingEntity);
         this.worldObj.spawnEntityInWorld(livingEntity);
         entityZombie.resolvePostRenderConsumer(this.worldObj.resolveGoldenOre(new BlockPos(entityZombie)), (PostRenderConsumer)null);
         entityZombie.setEnabled6(true);
         if (livingEntity.isActive16()) {
            entityZombie.setEnabled8(true);
         }

         entityZombie.setEnabled5(entityMob.isActive25());
         if (entityMob.processAction()) {
            entityZombie.setLabelText(entityMob.getText2());
            entityZombie.setEnabled(entityMob.isActive6());
         }

         this.worldObj.matchesEntity(entityZombie);
         this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
      }
   }

   @Override
   public boolean isActive16() {
      return this.getDataWatcher().computeByteCode(12) == 1;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (this.isActive16()) {
         tagCompound.applyText("IsBaby", true);
      }

      if (this.isActive32()) {
         tagCompound.applyText("IsVillager", true);
      }

      tagCompound.put("ConversionTime", this.isActive33() ? this.count32 : -1);
      tagCompound.applyText("CanBreakDoors", this.isEnabled28());
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return stack.getItem() == Items.egg && this.isActive16() && this.isSneaking() ? false : super.matchesItemStack(stack);
   }

   public void updateState48() {
      this.goalSetupManager.add(4, new AuxiliaryWorldEntry(this, EntityVillager.class, 1.0, true));
      this.goalSetupManager.add(4, new AuxiliaryWorldEntry(this, EntityVillagergolem.class, 1.0, true));
      this.goalSetupManager.add(6, new CustomClientManagerAdapter(this, 1.0, false));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, true, new Class[]{EntityPigzombie.class}));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityVillager.class, false));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityVillagergolem.class, true));
   }

   public void setEnabled9(boolean enabled) {
      if (this.enabled28 != enabled) {
         this.enabled28 = enabled;
         if (enabled) {
            this.goalSetupManager.add(1, this.itemEntryBridge);
         } else {
            this.goalSetupManager.applyRotationPool(this.itemEntryBridge);
         }
      }
   }

   public void setEnabled8(boolean enabled) {
      this.getDataWatcher().applyTargetByTarget(12, (byte)(enabled ? 1 : 0));
      if (this.worldObj != null && !this.worldObj.state) {
         AuxiliaryListener auxiliaryListener = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3);
         auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager3);
         if (enabled) {
            auxiliaryListener.applyInvalidOperationManager(invalidOperationManager3);
         }
      }

      this.setEnabled7(enabled);
   }

   public int getCount9() {
      int count = 1;
      if (this.random.nextFloat() < 0.01F) {
         int count2 = 0;
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         for (int index = (int)this.posX - 4; index < (int)this.posX + 4 && count2 < 14; index++) {
            for (int index2 = (int)this.posY - 4; index2 < (int)this.posY + 4 && count2 < 14; index2++) {
               for (int index3 = (int)this.posZ - 4; index3 < (int)this.posZ + 4 && count2 < 14; index3++) {
                  Block block = this.worldObj.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, index2, index3)).getBlock();
                  if (block == Blocks.block127 || block == Blocks.bed) {
                     if (this.random.nextFloat() < 0.3F) {
                        count++;
                     }

                     count2++;
                  }
               }
            }
         }
      }

      return count;
   }

   public void setEnabled7(boolean enabled) {
      this.applyFloat5(enabled ? 0.5F : 1.0F);
   }

   public void updateState49() {
      EntityVillager entityVillager = new EntityVillager(this.worldObj);
      entityVillager.applyEntity5(this);
      entityVillager.resolvePostRenderConsumer(this.worldObj.resolveGoldenOre(new BlockPos(entityVillager)), (PostRenderConsumer)null);
      entityVillager.updateState51();
      if (this.isActive16()) {
         entityVillager.applyIndex8(-24000);
      }

      this.worldObj.spawnEntityInWorld(this);
      entityVillager.setEnabled5(this.isActive25());
      if (this.processAction()) {
         entityVillager.setLabelText(this.getText2());
         entityVillager.setEnabled(this.isActive6());
      }

      this.worldObj.matchesEntity(entityVillager);
      entityVillager.applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion15.indexOffset3, 200, 0));
      this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1017, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
   }

   public void applyIndex8(int count) {
      this.count32 = count;
      this.getDataWatcher().applyTargetByTarget(14, (byte)1);
      this.applyIndex5(Potion.potion26.indexOffset3);
      this.applyPotionEffectByPotionEffect(
         new PotionEffect(Potion.potion.indexOffset3, count, Math.min(this.worldObj.getGameSettingsPeacefulType().getIndex() - 1, 0))
      );
      this.worldObj.applyEntity(this, (byte)16);
   }

   public void setEnabled6(boolean enabled) {
      this.getDataWatcher().applyTargetByTarget(13, (byte)(enabled ? 1 : 0));
   }

   public boolean isActive33() {
      return this.getDataWatcher().computeByteCode(14) == 1;
   }

   public boolean isActive32() {
      return this.getDataWatcher().computeByteCode(13) == 1;
   }

   @Override
   public void updateState() {
      if (!this.worldObj.state && this.isActive33()) {
         int count = this.getCount9();
         this.count32 -= count;
         if (this.count32 <= 0) {
            this.updateState49();
         }
      }

      super.updateState();
   }

   public boolean isEnabled28() {
      return this.enabled28;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.getDataWatcher().applyTarget(12, (byte)0);
      this.getDataWatcher().applyTarget(13, (byte)0);
      this.getDataWatcher().applyTarget(14, (byte)0);
   }

   @Override
   public boolean isActive24() {
      return !this.isActive33();
   }

   @Override
   public void updateState33() {
      if (this.worldObj.isActive2() && !this.worldObj.state && !this.isActive16()) {
         float factor = this.computeFactor(1.0F);
         if (factor > 0.5F
            && this.random.nextFloat() * 30.0F < (factor - 0.4F) * 2.0F
            && this.worldObj
               .checkIndex2(
                  MathHelper.ceiling_double_int(this.posX),
                  MathHelper.ceiling_double_int((double)Math.round(this.posY)),
                  MathHelper.ceiling_double_int(this.posZ)
               )) {
            boolean flag = true;
            ItemStack stack = this.resolveItemStack(4);
            if (stack != null) {
               if (stack.isActive3()) {
                  stack.applyIndexByIndex(stack.getIndexOffset2() + this.random.nextInt(2));
                  if (stack.getIndexOffset2() >= stack.processAction()) {
                     this.applyItemStackByItemStack(stack);
                     this.applyItemStack(4, (ItemStack)null);
                  }
               }

               flag = false;
            }

            if (flag) {
               this.applyIndexByIndex(8);
            }
         }
      }

      if (this.isSneaking() && this.getEntityLivingBase4() != null && this.ridingEntity instanceof EntityChicken) {
         ((EntityMob)this.ridingEntity).getPathfindManager().matchesInterpolationConverter(this.getPathfindManager().getInterpolationConverter(), 1.5);
      }

      super.updateState33();
   }

   public EntityZombie(World world) {
      super(world);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new AuxiliaryWorldEntry(this, EntityPlayer.class, 1.0, false));
      this.goalSetupManager.add(5, new CoreClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(8, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.updateState48();
      this.applyFloat(0.6F, 1.95F);
   }
}
