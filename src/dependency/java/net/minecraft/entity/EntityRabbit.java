package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.ServiceOptionType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AimScheduler;
import client.util.BaseClientManagerAdapter;
import client.util.BezierScheduler;
import client.util.ClassArrayEntry;
import client.util.DoMobLootClientManager;
import client.util.ExtendedClassEntry;
import client.util.HitBoxBuffer;
import client.util.HitBoxMonitor;
import client.util.InterpolationConverter;
import client.util.PathfindManagerAdapter;
import client.util.PredicateEntryAdapter;
import client.util.RotationEstimator;
import client.util.StandardClientManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.IronHoe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEntryAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRabbit extends HitBoxBuffer {
   public PredicateEntryAdapter predicateEntryAdapter;
   public int count32 = 0;
   public int count33 = 0;
   public boolean enabled29 = false;
   public boolean enabled28 = false;
   public int count34 = 0;
   public ServiceOptionType serviceOptionType = ServiceOptionType.HOP;
   public int count35 = 0;
   public EntityPlayer entityPlayer3 = null;

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack != null && this.matchesItem(stack.getItem());
   }

   private void applyDouble(double offset, double offset2) {
      this.rotationYaw = (float)(MathHelper.getDouble6(offset2 - this.posZ, offset - this.posX) * 180.0 / Math.PI) - 90.0F;
   }

   @Override
   public String getText9() {
      return "mob.rabbit.death";
   }

   public static boolean matchesEntityRabbit(EntityRabbit entityRabbit) {
      return entityRabbit.isActive32();
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.applyIndex10(tagCompound.computeCount("RabbitType"));
      this.count35 = tagCompound.computeCount("MoreCarrotTicks");
   }

   @Override
   public int getCount4() {
      return this.getCount11() == 99 ? 8 : super.getCount4();
   }

   public String getTextAsText() {
      return "mob.rabbit.hop";
   }

   public EntityRabbit resolveEntityRabbit(UUIDEntryAdapter uUIDEntryAdapter) {
      EntityRabbit entityRabbit = new EntityRabbit(this.worldObj);
      if (uUIDEntryAdapter instanceof EntityRabbit) {
         entityRabbit.applyIndex10(this.random.nextBoolean() ? this.getCount11() : ((EntityRabbit)uUIDEntryAdapter).getCount11());
      }

      return entityRabbit;
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      if (this.getCount11() == 99) {
         this.applyText("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         return entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), 8.0F);
      } else {
         return entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), 3.0F);
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
   }

   @Override
   public void updateState2() {
      this.resolveEntityItem(new ItemStack(Items.rabbitFoot, 1), 0.0F);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(2) + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.rabbitHide, 1);
      }

      int count3 = this.random.nextInt(2);

      for (int index2 = 0; index2 < count3; index2++) {
         if (this.canExecute2()) {
            this.resolveEntityItem(Items.cookedRabbit, 1);
         } else {
            this.resolveEntityItem(Items.rabbit, 1);
         }
      }
   }

   public void applyDouble(double offset) {
      this.getPathfindManager().setValue(offset);
      this.entityPillagerAdapter
         .applyDouble(this.entityPillagerAdapter.getValue4(), this.entityPillagerAdapter.getValue2(), this.entityPillagerAdapter.getValue3(), offset);
   }

   public void applyServiceOptionType(ServiceOptionType serviceOptionType2) {
      this.applyServiceOptionType(true, serviceOptionType2);
      this.count33 = serviceOptionType2.getCount2();
      this.count32 = 0;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 1) {
         this.updateState20();
         this.count33 = 10;
         this.count32 = 0;
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityRabbit(uUIDEntryAdapter);
   }

   @Override
   public float getFloat6() {
      return this.entityPillagerAdapter.isEnabled() && this.entityPillagerAdapter.getValue2() > this.posY + 0.5
         ? 0.5F
         : this.serviceOptionType.getFactor2();
   }

   @Override
   public String getText3() {
      return "mob.rabbit.idle";
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      Object entry = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      int count = this.random.nextInt(6);
      boolean flag = false;
      if (entry instanceof AimScheduler) {
         count = ((AimScheduler)entry).count;
         flag = true;
      } else {
         entry = new AimScheduler(count);
      }

      this.applyIndex10(count);
      if (flag) {
         this.applyIndex8(-24000);
      }

      return (PostRenderConsumer)entry;
   }

   private boolean matchesItem(Item item) {
      return item == Items.carrot || item == Items.goldenCarrot || item == Item.processAction2(Blocks.immutableSetEntry2Entry);
   }

   @Override
   public String getText10() {
      return "mob.rabbit.hurt";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return this.matchesDamageSource(damageSource) ? false : super.matchesDamageSource(damageSource, factor);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("RabbitType", this.getCount11());
      tagCompound.put("MoreCarrotTicks", this.count35);
   }

   public void setServiceOptionType(ServiceOptionType serviceOptionType2) {
      this.serviceOptionType = serviceOptionType2;
   }

   public void applyServiceOptionType(boolean flag, ServiceOptionType serviceOptionType2) {
      super.setState31(flag);
      if (!flag) {
         if (this.serviceOptionType == ServiceOptionType.ATTACK) {
            this.serviceOptionType = ServiceOptionType.HOP;
         }
      } else {
         this.applyDouble(1.5 * (double)serviceOptionType2.getFactor());
         this.applyText(this.getTextAsText(), this.getFactor(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
      }

      this.enabled29 = flag;
   }

   public float computeFactor3(float factor) {
      return this.count33 == 0 ? 0.0F : ((float)this.count32 + factor) / (float)this.count33;
   }

   @Override
   public void updateState3() {
      if (this.entityPillagerAdapter.getValue() > 0.8) {
         this.setServiceOptionType(ServiceOptionType.SPRINT);
      } else if (this.serviceOptionType != ServiceOptionType.ATTACK) {
         this.setServiceOptionType(ServiceOptionType.HOP);
      }

      if (this.count34 > 0) {
         this.count34--;
      }

      if (this.count35 > 0) {
         this.count35 = this.count35 - this.random.nextInt(3);
         if (this.count35 < 0) {
            this.count35 = 0;
         }
      }

      if (this.onGround) {
         if (!this.enabled28) {
            this.applyServiceOptionType(false, ServiceOptionType.NONE);
            this.updateState54();
         }

         if (this.getCount11() == 99 && this.count34 == 0) {
            EntityLivingBase entityLivingBase = this.getEntityLivingBase4();
            if (entityLivingBase != null && this.getCoordinate(entityLivingBase) < 16.0) {
               this.applyDouble(entityLivingBase.posX, entityLivingBase.posZ);
               this.entityPillagerAdapter.applyDouble(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, this.entityPillagerAdapter.getValue());
               this.applyServiceOptionType(ServiceOptionType.ATTACK);
               this.enabled28 = true;
            }
         }

         ItemEntryAdapter primaryItemEntry = (ItemEntryAdapter)this.primaryItemEntry;
         if (!primaryItemEntry.isEnabled()) {
            if (this.entityPillagerAdapter.isEnabled() && this.count34 == 0) {
               InterpolationConverter interpolationConverter = this.pathfindManager.getInterpolationConverter();
               Vec3 vec = new Vec3(this.entityPillagerAdapter.getValue4(), this.entityPillagerAdapter.getValue2(), this.entityPillagerAdapter.getValue3());
               if (interpolationConverter != null && interpolationConverter.getCount() < interpolationConverter.getCount2()) {
                  vec = interpolationConverter.resolveVec3(this);
               }

               this.applyDouble(vec.xCoord, vec.zCoord);
               this.applyServiceOptionType(this.serviceOptionType);
            }
         } else if (!primaryItemEntry.isEnabled2()) {
            this.updateState52();
         }
      }

      this.enabled28 = this.onGround;
   }

   private void updateState51() {
      ((ItemEntryAdapter)this.primaryItemEntry).setEnabled2(false);
   }

   private void updateState52() {
      ((ItemEntryAdapter)this.primaryItemEntry).setEnabled2(true);
   }

   public boolean onReset() {
      return this.enabled29;
   }

   public void updateState50() {
      this.worldObj
         .applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.BLOCK_DUST,
            this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
            this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
            this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
            0.0,
            0.0,
            0.0,
            new int[]{Block.processAction5(Blocks.block66.processAction(7))}
         );
      this.count35 = 100;
   }

   public int getCount11() {
      return this.dataWatcher.computeByteCode(18);
   }

   private void updateState54() {
      this.updateState53();
      this.updateState51();
   }

   private void updateState53() {
      this.count34 = this.getCount10();
   }

   public int getCount10() {
      return this.serviceOptionType.getCount();
   }

   @Override
   public void updateState18() {
   }

   private boolean isActive32() {
      return this.count35 == 0;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(18, (byte)0);
   }

   public void applyIndex10(int count) {
      if (count == 99) {
         this.goalSetupManager.applyRotationPool(this.predicateEntryAdapter);
         this.goalSetupManager.add(4, new RotationEstimator(this));
         this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
         this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
         this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityWolf.class, true));
         if (!this.processAction()) {
            this.setLabelText(I18n.getString2("entity.KillerBunny.name"));
         }
      }

      this.dataWatcher.applyTargetByTarget(18, (byte)count);
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (this.count32 != this.count33) {
         if (this.count32 == 0 && !this.worldObj.state) {
            this.worldObj.applyEntity(this, (byte)1);
         }

         this.count32++;
      } else if (this.count33 != 0) {
         this.count32 = 0;
         this.count33 = 0;
      }
   }

   public EntityRabbit(World world) {
      super(world);
      this.applyFloat(0.6F, 0.7F);
      this.primaryItemEntry = new ItemEntryAdapter(this, this);
      this.entityPillagerAdapter = new BezierScheduler(this);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.pathfindManager.setFactor(2.5F);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new HitBoxMonitor(this, 1.33));
      this.goalSetupManager.add(2, new StandardClientManagerAdapter(this, 1.0, Items.carrot, false));
      this.goalSetupManager.add(2, new StandardClientManagerAdapter(this, 1.0, Items.goldenCarrot, false));
      this.goalSetupManager.add(2, new StandardClientManagerAdapter(this, 1.0, Item.processAction2(Blocks.immutableSetEntry2Entry), false));
      this.goalSetupManager.add(3, new DoMobLootClientManager(this, 0.8));
      this.goalSetupManager.add(5, new IronHoe(this));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 0.6));
      this.goalSetupManager.add(11, new BaseClientManagerAdapter(this, EntityPlayer.class, 10.0F));
      this.predicateEntryAdapter = new PredicateEntryAdapter(this, EntityWolf.class, 16.0F, 1.33, 1.33);
      this.goalSetupManager.add(4, this.predicateEntryAdapter);
      this.applyDouble(0.0);
   }
}
