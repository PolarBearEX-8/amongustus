package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.event.SystemListener;
import client.setting.DisablerUndefinedType;
import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryPredicateEntry;
import client.util.AuxiliaryUUIDEntry;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.DefaultClientManagerAdapter;
import client.util.EnchantmentManager;
import client.util.ExtendedClassEntry;
import client.util.LvlManager;
import client.util.NetherFloatArrayEntry;
import client.util.SupplementalClientManagerAdapter;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.DirectItemEntry;
import net.minecraft.item.GoldenOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PassiveItemEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySkeleton extends EntityMonster implements SystemListener {
   public PassiveItemEntry passiveItemEntry = new PassiveItemEntry(this, 1.0, 20, 60, 15.0F);
   public AuxiliaryWorldEntry auxiliaryWorldEntry = new AuxiliaryWorldEntry(this, EntityPlayer.class, 1.2, false);

   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      EntityArrow entityArrow = new EntityArrow(this.worldObj, this, livingEntity, 1.6F, (float)(14 - this.worldObj.getGameSettingsPeacefulType().getIndex() * 4));
      int index = LvlManager.getIndex(EnchantmentManager.enchantmentManager10.indexOffset, this.getItemStack());
      int index2 = LvlManager.getIndex(EnchantmentManager.enchantmentManager8.indexOffset, this.getItemStack());
      entityArrow.setValue6((double)(factor * 2.0F) + this.random.nextGaussian() * 0.25 + (double)((float)this.worldObj.getGameSettingsPeacefulType().getIndex() * 0.11F));
      if (index > 0) {
         entityArrow.setValue6(entityArrow.getValue6() + (double)index * 0.5 + 0.5);
      }

      if (index2 > 0) {
         entityArrow.setCount13(index2);
      }

      if (LvlManager.getIndex(EnchantmentManager.enchantmentManager17.indexOffset, this.getItemStack()) > 0 || this.getCount9() == 1) {
         entityArrow.applyIndexByIndex(100);
      }

      this.applyText("random.bow", 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
      this.worldObj.matchesEntity(entityArrow);
   }

   @Override
   public String getText9() {
      return "mob.skeleton.death";
   }

   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.UNDEAD;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.skeleton.step", 0.15F, 1.0F);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("SkeletonType", 99)) {
         byte byteValue = tagCompound.computeByteCode("SkeletonType");
         this.applyIndex8(byteValue);
      }

      this.updateState48();
   }

   @Override
   public float getHeight2() {
      return this.getCount9() == 1 ? super.getHeight2() : 1.74F;
   }

   @Override
   public void updateState21() {
      super.updateState21();
      if (this.ridingEntity instanceof AuxiliaryUUIDEntry) {
         AuxiliaryUUIDEntry entity = (AuxiliaryUUIDEntry)this.ridingEntity;
         this.scaleFactor8 = entity.scaleFactor8;
      }
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
      super.applyItemStack(count, stack);
      if (!this.worldObj.state && count == 0) {
         this.updateState48();
      }
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      if (super.matchesEntity4(entity)) {
         if (this.getCount9() == 1 && entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion32.indexOffset3, 200));
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public double getValue() {
      return this.isActive16() ? 0.0 : -0.35;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   @Override
   public void updateState2() {
      if (this.getCount9() == 1) {
         this.resolveEntityItem(new ItemStack(Items.skull, 1, 1), 0.0F);
      }
   }

   public int getCount9() {
      return this.dataWatcher.computeByteCode(13);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (this.getCount9() == 1) {
         int count2 = this.random.nextInt(3 + count) - 1;

         for (int index = 0; index < count2; index++) {
            this.resolveEntityItem(Items.coal, 1);
         }
      } else {
         int count3 = this.random.nextInt(3 + count);

         for (int index2 = 0; index2 < count3; index2++) {
            this.resolveEntityItem(Items.arrow, 1);
         }
      }

      int count4 = this.random.nextInt(3 + count);

      for (int index3 = 0; index3 < count4; index3++) {
         this.resolveEntityItem(Items.bone, 1);
      }
   }

   @Override
   public void applyGoldenOre(GoldenOre goldenOre) {
      super.applyGoldenOre(goldenOre);
      this.applyItemStack(0, new ItemStack(Items.bow));
   }

   @Override
   public Item getGunpowder() {
      return Items.arrow;
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      PostRenderConsumer postRenderConsumer2 = super.resolvePostRenderConsumer(goldenOre, postRenderConsumer);
      if (this.worldObj.secondaryFloatArrayEntry instanceof NetherFloatArrayEntry && this.getRandom().nextInt(5) > 0) {
         this.goalSetupManager.add(4, this.auxiliaryWorldEntry);
         this.applyIndex8(1);
         this.applyItemStack(0, new ItemStack(Items.stoneSword));
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(4.0);
      } else {
         this.goalSetupManager.add(4, this.passiveItemEntry);
         this.applyGoldenOre(goldenOre);
         this.applyGoldenOreByGoldenOre(goldenOre);
      }

      this.setEnabled22(this.random.nextFloat() < 0.55F * goldenOre.getFactor());
      if (this.resolveItemStack(4) == null) {
         Calendar calendar = this.worldObj.getCalendar();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.random.nextFloat() < 0.25F) {
            this.applyItemStack(4, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.block120 : Blocks.block146));
            this.factorArray[4] = 0.0F;
         }
      }

      return postRenderConsumer2;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (damageSource.getEntityAsEntity() instanceof EntityArrow && damageSource.getEntity() instanceof EntityPlayer) {
         EntityPlayer entity = (EntityPlayer)damageSource.getEntity();
         double offset = entity.posX - this.posX;
         double offset2 = entity.posZ - this.posZ;
         if (offset * offset + offset2 * offset2 >= 2500.0) {
            entity.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter32);
         }
      } else if (damageSource.getEntity() instanceof EntityCreeper && ((EntityCreeper)damageSource.getEntity()).isActive34() && ((EntityCreeper)damageSource.getEntity()).isActive33()) {
         ((EntityCreeper)damageSource.getEntity()).updateState48();
         this.resolveEntityItem(new ItemStack(Items.skull, 1, this.getCount9() == 1 ? 1 : 0), 0.0F);
      }
   }

   @Override
   public String getText3() {
      return "mob.skeleton.say";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("SkeletonType", (byte)this.getCount9());
   }

   @Override
   public String getText10() {
      return "mob.skeleton.hurt";
   }

   public void updateState48() {
      this.goalSetupManager.applyRotationPool(this.auxiliaryWorldEntry);
      this.goalSetupManager.applyRotationPool(this.passiveItemEntry);
      ItemStack stack = this.getItemStack();
      if (stack != null && stack.getItem() == Items.bow) {
         this.goalSetupManager.add(4, this.passiveItemEntry);
      } else {
         this.goalSetupManager.add(4, this.auxiliaryWorldEntry);
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(13, (byte)0);
   }

   public void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(13, (byte)count);
      this.state4 = count == 1;
      if (count == 1) {
         this.applyFloat(0.72F, 2.535F);
      } else {
         this.applyFloat(0.6F, 1.95F);
      }
   }

   @Override
   public void updateState33() {
      if (this.worldObj.isActive2() && !this.worldObj.state) {
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

      if (this.worldObj.state && this.getCount9() == 1) {
         this.applyFloat(0.72F, 2.535F);
      }

      super.updateState33();
   }

   public EntitySkeleton(World world) {
      super(world);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new DefaultClientManagerAdapter(this));
      this.goalSetupManager.add(3, new DirectItemEntry(this, 1.0));
      this.goalSetupManager.add(3, new AuxiliaryPredicateEntry(this, EntityWolf.class, 6.0F, 1.0, 1.2));
      this.goalSetupManager.add(4, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(6, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
      this.goalSetupManager2.add(3, new ExtendedClassEntry(this, EntityVillagergolem.class, true));
      if (world != null && !world.state) {
         this.updateState48();
      }
   }
}
