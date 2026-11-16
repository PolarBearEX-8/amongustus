package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.GameSettingsPeacefulType;
import client.util.AdvancedLoggerEntry;
import client.util.AlertNotificationSound;
import client.util.BaseLoggerEntry;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.EnhancedClientManagerAdapter;
import client.util.ExtraClientManagerAdapter;
import client.util.InfoManager;
import client.util.InternalClientManagerHandler;
import client.util.ManagedClientManagerAdapter;
import client.util.VectorPipeline;
import client.util.VirtualFloatEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySlime extends EntityMob implements VectorPipeline {
   public float factor42;
   public float factor44;
   public float factor40;
   public boolean enabled28;

   @Override
   public float getFactor() {
      return 0.4F * (float)this.getCount10();
   }

   public EntitySlime getEntitySlime() {
      return new EntitySlime(this.worldObj);
   }

   @Override
   public String getText9() {
      return "mob.slime." + (this.getCount10() > 1 ? "big" : "small");
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      int count = tagCompound.computeCount("Size");
      if (count < 0) {
         count = 0;
      }

      this.applyIndex8(count + 1);
      this.enabled28 = tagCompound.matchesText("wasOnGround");
   }

   @Override
   public void applyIndex4(int count) {
      if (count == 16) {
         int count2 = this.getCount10();
         this.applyFloat(0.51000005F * (float)count2, 0.51000005F * (float)count2);
         this.rotationYaw = this.scaleFactor12;
         this.scaleFactor8 = this.scaleFactor12;
         if (this.isInWater() && this.random.nextInt(20) == 0) {
            this.updateState11();
         }
      }

      super.applyIndex4(count);
   }

   @Override
   public float getHeight2() {
      return 0.625F * this.height;
   }

   public String getTextAsText() {
      return "mob.slime." + (this.getCount10() > 1 ? "big" : "small");
   }

   public AttackEffectsExplosionNormalType getFLAME() {
      return AttackEffectsExplosionNormalType.SLIME;
   }

   public boolean isActive29() {
      return this.getCount10() > 1;
   }

   @Override
   public void applyEntityPlayer2(EntityPlayer player) {
      if (this.isActive29()) {
         this.applyEntityLivingBase6(player);
      }
   }

   @Override
   public void applyEntity(Entity entity) {
      super.applyEntity(entity);
      if (entity instanceof EntityVillagergolem && this.isActive29()) {
         this.applyEntityLivingBase6((EntityLivingBase)entity);
      }
   }

   @Override
   public Item getGunpowder() {
      return this.getCount10() == 1 ? Items.slimeBall : null;
   }

   public void applyEntityLivingBase6(EntityLivingBase livingEntity) {
      int count = this.getCount10();
      if (this.checkEntity4(livingEntity)
         && this.getCoordinate(livingEntity) < 0.6 * (double)count * 0.6 * (double)count
         && livingEntity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), (float)this.getCount9())) {
         this.applyText("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         this.applyEntityLivingBase(this, livingEntity);
      }
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      int count = this.random.nextInt(3);
      if (count < 2 && this.random.nextFloat() < 0.5F * goldenOre.getFactor()) {
         count++;
      }

      int count2 = 1 << count;
      this.applyIndex8(count2);
      return super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
   }

   @Override
   public String getText10() {
      return "mob.slime." + (this.getCount10() > 1 ? "big" : "small");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Size", this.getCount10() - 1);
      tagCompound.applyText("wasOnGround", this.enabled28);
   }

   public int getCount10() {
      return this.dataWatcher.computeByteCode(16);
   }

   public void updateState47() {
      this.factor42 *= 0.6F;
   }

   public void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(16, (byte)count);
      this.applyFloat(0.51000005F * (float)count, 0.51000005F * (float)count);
      this.processDouble2(this.posX, this.posY, this.posZ);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble((double)(count * count));
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble((double)(0.2F + 0.1F * (float)count));
      this.applyFloat(this.getFloat());
      this.count26 = count;
   }

   @Override
   public int getCount8() {
      return 0;
   }

   @Override
   public void updateState9() {
      int count = this.getCount10();
      if (!this.worldObj.state && count > 1 && this.getFloat2() <= 0.0F) {
         int count2 = 2 + this.random.nextInt(3);

         for (int index = 0; index < count2; index++) {
            float factor = ((float)(index % 2) - 0.5F) * (float)count / 4.0F;
            float factor2 = ((float)(index / 2) - 0.5F) * (float)count / 4.0F;
            EntitySlime entitySlime = this.getEntitySlime();
            if (this.processAction()) {
               entitySlime.setLabelText(this.getText2());
            }

            if (this.isEnabled21()) {
               entitySlime.updateState5();
            }

            entitySlime.applyIndex8(count / 2);
            entitySlime.applyDoubleByDouble(this.posX + (double)factor, this.posY + 0.5, this.posZ + (double)factor2, this.random.nextFloat() * 360.0F, 0.0F);
            this.worldObj.matchesEntity(entitySlime);
         }
      }

      super.updateState9();
   }

   public int getCount9() {
      return this.getCount10();
   }

   @Override
   public void updateState() {
      if (!this.worldObj.state && this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.PEACEFUL && this.getCount10() > 0) {
         this.isDead = true;
      }

      this.factor44 = this.factor44 + (this.factor42 - this.factor44) * 0.5F;
      this.factor40 = this.factor44;
      super.updateState();
      if (this.onGround && !this.enabled28) {
         int count = this.getCount10();

         for (int index = 0; index < count * 8; index++) {
            float factor = this.random.nextFloat() * (float) Math.PI * 2.0F;
            float factor2 = this.random.nextFloat() * 0.5F + 0.5F;
            float factor3 = MathHelper.abs(factor) * (float)count * 0.5F * factor2;
            float factor4 = MathHelper.cos(factor) * (float)count * 0.5F * factor2;
            World world = this.worldObj;
            AttackEffectsExplosionNormalType fLAME = this.getFLAME();
            double offset = this.posX + (double)factor3;
            double offset2 = this.posZ + (double)factor4;
            world.applyAttackEffectsExplosionNorm(fLAME, offset, this.getBoundingBox2().minY, offset2, 0.0, 0.0, 0.0, new int[0]);
         }

         if (this.isActive30()) {
            this.applyText(this.getTextAsText(), this.getFactor(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         }

         this.factor42 = -0.5F;
      } else if (!this.onGround && this.enabled28) {
         this.factor42 = 1.0F;
      }

      this.enabled28 = this.onGround;
      this.updateState47();
   }

   public int nextIntAsIndex() {
      return this.random.nextInt(20) + 10;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)1);
   }

   public boolean isActive31() {
      return this.getCount10() > 0;
   }

   @Override
   public void closeScreen() {
      this.motionY = 0.42F;
      this.enabled4 = true;
   }

   public boolean isActive30() {
      return this.getCount10() > 2;
   }

   @Override
   public boolean nextInt() {
      BlockPos blockPos = new BlockPos(MathHelper.ceiling_double_int(this.posX), 0, MathHelper.ceiling_double_int(this.posZ));
      AlertNotificationSound loggerEntry = this.worldObj.getLoggerEntry(blockPos);
      if (this.worldObj.getRandomSeedManager().getInfoManager() == InfoManager.infoManager2 && this.random.nextInt(4) != 1) {
         return false;
      } else {
         if (this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL) {
            if (this.worldObj.resolveBiomeIDIsOutOfBoundsManager(blockPos) == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16
               && this.posY > 50.0
               && this.posY < 70.0
               && this.random.nextFloat() < 0.5F
               && this.random.nextFloat() < this.worldObj.getFactor()
               && this.worldObj
                     .computeCountByIndex(
                        MathHelper.ceiling_double_int(this.posX), MathHelper.ceiling_double_int(this.posY), MathHelper.ceiling_double_int(this.posZ)
                     )
                  <= this.random.nextInt(8)) {
               return super.nextInt();
            }

            if (this.random.nextInt(10) == 0 && loggerEntry.getRandom().nextInt(10) == 0 && this.posY < 40.0) {
               return super.nextInt();
            }
         }

         return false;
      }
   }

   public EntitySlime(World world) {
      super(world);
      this.entityPillagerAdapter = new VirtualFloatEntry(this);
      this.goalSetupManager.add(1, new ManagedClientManagerAdapter(this));
      this.goalSetupManager.add(2, new EnhancedClientManagerAdapter(this));
      this.goalSetupManager.add(3, new ExtraClientManagerAdapter(this));
      this.goalSetupManager.add(5, new InternalClientManagerHandler(this));
      this.goalSetupManager2.add(1, new BaseLoggerEntry(this));
      this.goalSetupManager2.add(3, new AdvancedLoggerEntry(this, EntityVillagergolem.class));
   }
}
