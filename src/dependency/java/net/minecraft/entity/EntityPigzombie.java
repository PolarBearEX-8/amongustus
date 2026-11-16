package net.minecraft.entity;

import client.event.AuxiliaryListener;
import client.event.PostRenderConsumer;
import client.setting.GameSettingsPeacefulType;
import client.util.HitBoxCollector;
import client.util.HitBoxRanker;
import client.util.InvalidOperationManager;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPigzombie extends EntityZombie {
   public static final UUID uUID6 = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
   public static final InvalidOperationManager invalidOperationManager4 = new InvalidOperationManager(uUID6, "Attacking speed boost", 0.05, 0)
      .withEnabled(false);
   public int count35;
   public int count34;
   public UUID uUID5;

   public static void applyEntityPigzombie(EntityPigzombie entityPigzombie, Entity entity) {
      entityPigzombie.applyEntity20(entity);
   }

   @Override
   public String getText9() {
      return "mob.zombiepig.zpigdeath";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count35 = tagCompound.processValue("Anger");
      String text = tagCompound.resolveText("HurtBy");
      if (text.length() > 0) {
         this.uUID5 = UUID.fromString(text);
         EntityPlayer playerEntityByUUID = this.worldObj.getPlayerEntityByUUID(this.uUID5);
         this.applyEntityLivingBase2(playerEntityByUUID);
         if (playerEntityByUUID != null) {
            this.entityPlayer = playerEntityByUUID;
            this.count11 = this.getIndexOffset5();
         }
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(extendedListener).applyDouble(0.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.23F);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(5.0);
   }

   @Override
   public void updateState2() {
      this.resolveEntityItem(Items.goldIngot, 1);
   }

   @Override
   public boolean isActive23() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this)
         && this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()
         && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2());
   }

   @Override
   public void applyGoldenOre(GoldenOre goldenOre) {
      this.applyItemStack(0, new ItemStack(Items.goldenSword));
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(2 + count);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.rottenFlesh, 1);
      }

      int count3 = this.random.nextInt(2 + count);

      for (int index2 = 0; index2 < count3; index2++) {
         this.resolveEntityItem(Items.goldNugget, 1);
      }
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      return false;
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      this.setEnabled6(false);
      return postRenderConsumer;
   }

   @Override
   public String getText3() {
      return "mob.zombiepig.zpig";
   }

   private void applyEntity20(Entity entity) {
      this.count35 = 400 + this.random.nextInt(400);
      this.count34 = this.random.nextInt(40);
      if (entity instanceof EntityLivingBase) {
         this.applyEntityLivingBase2((EntityLivingBase)entity);
      }
   }

   @Override
   public String getText10() {
      return "mob.zombiepig.zpighurt";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Anger", (short)this.count35);
      if (this.uUID5 != null) {
         tagCompound.put("HurtBy", this.uUID5.toString());
      } else {
         tagCompound.put("HurtBy", "");
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         if (entity instanceof EntityPlayer) {
            this.applyEntity20(entity);
         }

         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public void applyEntityLivingBase2(EntityLivingBase livingEntity) {
      super.applyEntityLivingBase2(livingEntity);
      if (livingEntity != null) {
         this.uUID5 = livingEntity.getEntityUniqueID();
      }
   }

   @Override
   public void updateState48() {
      this.goalSetupManager2.add(1, new HitBoxRanker(this));
      this.goalSetupManager2.add(2, new HitBoxCollector(this));
   }

   @Override
   public void updateState3() {
      AuxiliaryListener auxiliaryListener = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3);
      if (this.isActive34()) {
         if (!this.isActive16() && !auxiliaryListener.matchesInvalidOperationManager(invalidOperationManager4)) {
            auxiliaryListener.applyInvalidOperationManager(invalidOperationManager4);
         }

         this.count35--;
      } else if (auxiliaryListener.matchesInvalidOperationManager(invalidOperationManager4)) {
         auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager4);
      }

      if (this.count34 > 0 && --this.count34 == 0) {
         this.applyText("mob.zombiepig.zpigangry", this.getFactor() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
      }

      if (this.count35 > 0 && this.uUID5 != null && this.getTargetEntity() == null) {
         EntityPlayer playerEntityByUUID = this.worldObj.getPlayerEntityByUUID(this.uUID5);
         this.applyEntityLivingBase2(playerEntityByUUID);
         this.entityPlayer = playerEntityByUUID;
         this.count11 = this.getIndexOffset5();
      }

      super.updateState3();
   }

   public boolean isActive34() {
      return this.count35 > 0;
   }

   @Override
   public void updateState() {
      super.updateState();
   }

   @Override
   public boolean nextInt() {
      return this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL;
   }

   public EntityPigzombie(World world) {
      super(world);
      this.state4 = true;
   }
}
