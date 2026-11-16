package net.minecraft.entity;

import client.event.AuxiliaryListener;
import client.event.SystemListener;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ExtendedClassEntry;
import client.util.InvalidOperationManager;
import client.util.SupplementalClientManagerAdapter;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PassiveItemEntry;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWitch extends EntityMonster implements SystemListener {
   public static final UUID uUID4;
   public static final InvalidOperationManager invalidOperationManager3;
   public static final Item[] itemArray = new Item[]{
      Items.glowstoneDust, Items.sugar, Items.redstone, Items.spiderEye, Items.glassBottle, Items.gunpowder, Items.stick, Items.stick
   };
   public int count30;

   @Override
   public String getText9() {
      return null;
   }

   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      if (!this.isActive32()) {
         EntityThrownpotion entityThrownpotion = new EntityThrownpotion(this.worldObj, this, 32732);
         double offset = livingEntity.posY + (double)livingEntity.getHeight2() - 1.1F;
         entityThrownpotion.rotationPitch -= -20.0F;
         double offset2 = livingEntity.posX + livingEntity.motionX - this.posX;
         double offset3 = offset - this.posY;
         double offset4 = livingEntity.posZ + livingEntity.motionZ - this.posZ;
         float factor2 = MathHelper.sqrt_double(offset2 * offset2 + offset4 * offset4);
         if (factor2 >= 8.0F && !livingEntity.checkPotion(Potion.potion3)) {
            entityThrownpotion.applyIndex5(32698);
         } else if (livingEntity.getFloat2() >= 8.0F && !livingEntity.checkPotion(Potion.potion24)) {
            entityThrownpotion.applyIndex5(32660);
         } else if (factor2 <= 3.0F && !livingEntity.checkPotion(Potion.potion26) && this.random.nextFloat() < 0.25F) {
            entityThrownpotion.applyIndex5(32696);
         }

         entityThrownpotion.applyDouble(offset2, offset3 + (double)(factor2 * 0.2F), offset4, 0.75F, 8.0F);
         this.worldObj.matchesEntity(entityThrownpotion);
      }
   }

   @Override
   public float getHeight2() {
      return 1.62F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(26.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3) + 1;

      for (int index = 0; index < count2; index++) {
         int count3 = this.random.nextInt(3);
         Item item = itemArray[this.random.nextInt(itemArray.length)];
         if (count > 0) {
            count3 += this.random.nextInt(count + 1);
         }

         for (int index2 = 0; index2 < count3; index2++) {
            this.resolveEntityItem(item, 1);
         }
      }
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 15) {
         for (int index = 0; index < this.random.nextInt(35) + 10; index++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SPELL_WITCH,
                  this.posX + this.random.nextGaussian() * 0.13F,
                  this.getBoundingBox2().maxY + 0.5 + this.random.nextGaussian() * 0.13F,
                  this.posZ + this.random.nextGaussian() * 0.13F,
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
         }
      } else {
         super.processByte(byteValue);
      }
   }

   @Override
   public float computeFactor(DamageSource damageSource, float factor) {
      float factor2 = super.computeFactor(damageSource, factor);
      if (damageSource.getEntity() == this) {
         factor2 = 0.0F;
      }

      if (damageSource.isEnabled3()) {
         factor2 = (float)((double)factor2 * 0.15);
      }

      return factor2;
   }

   @Override
   public String getText3() {
      return null;
   }

   @Override
   public String getText10() {
      return null;
   }

   public boolean isActive32() {
      return this.getDataWatcher().computeByteCode(21) == 1;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.getDataWatcher().applyTarget(21, (byte)0);
   }

   @Override
   public void updateState33() {
      if (!this.worldObj.state) {
         if (this.isActive32()) {
            if (this.count30-- <= 0) {
               this.setEnabled6(false);
               ItemStack stack = this.getItemStack();
               this.applyItemStack(0, (ItemStack)null);
               if (stack != null && stack.getItem() == Items.potion) {
                  List list = Items.potion.getList(stack);
                  if (list != null) {
                     for (PotionEffect potionEffect : (List<PotionEffect>)list) {
                        this.applyPotionEffectByPotionEffect(new PotionEffect(potionEffect));
                     }
                  }
               }

               this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyInvalidOperationManagerByInvalidOperation(invalidOperationManager3);
            }
         } else {
            short shortValue = -1;
            if (this.random.nextFloat() < 0.15F && this.matchesMaterial(Material.coreItemEntry1) && !this.checkPotion(Potion.potion18)) {
               shortValue = 8237;
            } else if (this.random.nextFloat() < 0.15F && this.canExecute2() && !this.checkPotion(Potion.potion13)) {
               shortValue = 16307;
            } else if (this.random.nextFloat() < 0.05F && this.getFloat2() < this.getFloat()) {
               shortValue = 16341;
            } else if (this.random.nextFloat() < 0.25F
               && this.getEntityLivingBase4() != null
               && !this.checkPotion(Potion.potion2)
               && this.getEntityLivingBase4().getCoordinate(this) > 121.0) {
               shortValue = 16274;
            } else if (this.random.nextFloat() < 0.25F
               && this.getEntityLivingBase4() != null
               && !this.checkPotion(Potion.potion2)
               && this.getEntityLivingBase4().getCoordinate(this) > 121.0) {
               shortValue = 16274;
            }

            if (shortValue > -1) {
               this.applyItemStack(0, new ItemStack(Items.potion, 1, shortValue));
               this.count30 = this.getItemStack().processAction6();
               this.setEnabled6(true);
               AuxiliaryListener auxiliaryListener = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3);
               auxiliaryListener.applyInvalidOperationManagerByInvalidOperation(invalidOperationManager3);
               auxiliaryListener.applyInvalidOperationManager(invalidOperationManager3);
            }
         }

         if (this.random.nextFloat() < 7.5E-4F) {
            this.worldObj.applyEntity(this, (byte)15);
         }
      }

      super.updateState33();
   }

   public void setEnabled6(boolean enabled) {
      this.getDataWatcher().applyTargetByTarget(21, (byte)(enabled ? 1 : 0));
   }

   public EntityWitch(World world) {
      super(world);
      this.applyFloat(0.6F, 1.95F);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new PassiveItemEntry(this, 1.0, 60, 10.0F));
      this.goalSetupManager.add(2, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(3, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(3, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
   }

   static {
      String[] textArray = new String[]{"5CD17E52-A79A-43D3-A529-90FDE04B181E", "Drinking speed penalty"};
      uUID4 = UUID.fromString(textArray[0]);
      invalidOperationManager3 = new InvalidOperationManager(uUID4, textArray[1], -0.25, 0).withEnabled(false);
   }
}
