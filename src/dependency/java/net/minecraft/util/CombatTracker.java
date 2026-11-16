package net.minecraft.util;

import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.DamageSourceHolder;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CombatTracker {
   public final List list = Lists.newArrayList();
   public final EntityLivingBase entityLivingBase;
   public int count3;
   public int count2;
   public int count;
   public boolean enabled2;
   public boolean enabled;
   public String labelText;

   public void applyDamageSource(DamageSource damageSource, float factor, float factor2) {
      this.updateState();
      this.updateState2();
      DamageSourceHolder damageSourceHolder = new DamageSourceHolder(damageSource, this.entityLivingBase.ticksExisted, factor, factor2, this.labelText, this.entityLivingBase.fallDistance);
      this.list.add(damageSourceHolder);
      this.count3 = this.entityLivingBase.ticksExisted;
      this.enabled = true;
      if (damageSourceHolder.isActive() && !this.enabled2 && this.entityLivingBase.canExecute()) {
         this.enabled2 = true;
         this.count2 = this.entityLivingBase.ticksExisted;
         this.count = this.count2;
         this.entityLivingBase.updateState32();
      }
   }

   public void updateState() {
      int count4 = this.enabled2 ? 300 : 100;
      if (this.enabled && (!this.entityLivingBase.canExecute() || this.entityLivingBase.ticksExisted - this.count3 > count4)) {
         boolean enabled3 = this.enabled2;
         this.enabled = false;
         this.enabled2 = false;
         this.count = this.entityLivingBase.ticksExisted;
         if (enabled3) {
            this.entityLivingBase.updateState29();
         }

         this.list.clear();
      }
   }

   private String resolveText(DamageSourceHolder damageSourceHolder) {
      return damageSourceHolder.getLabelText() == null ? "generic" : damageSourceHolder.getLabelText();
   }

   private DamageSourceHolder getDamageSourceHolder() {
      DamageSourceHolder damageSourceHolder = null;
      DamageSourceHolder damageSourceHolder2 = null;
      float factor = 0.0F;

      for (int index = 0; index < this.list.size(); index++) {
         DamageSourceHolder damageSourceHolder3 = (DamageSourceHolder)this.list.get(index);
         DamageSourceHolder damageSourceHolder4 = index > 0 ? (DamageSourceHolder)this.list.get(index - 1) : null;
         if ((damageSourceHolder3.getDamageSource() == DamageSource.damageSource11 || damageSourceHolder3.getDamageSource() == DamageSource.damageSource10)
            && damageSourceHolder3.getFactorAsFloat() > 0.0F
            && (damageSourceHolder == null || damageSourceHolder3.getFactorAsFloat() > factor)) {
            if (index > 0) {
               damageSourceHolder = damageSourceHolder4;
            } else {
               damageSourceHolder = damageSourceHolder3;
            }

            factor = damageSourceHolder3.getFactorAsFloat();
         }

         if (damageSourceHolder3.getLabelText() != null && (damageSourceHolder2 == null || damageSourceHolder3.getFactor() > (float)0)) {
            damageSourceHolder2 = damageSourceHolder3;
         }
      }

      return factor > 5.0F && damageSourceHolder != null ? damageSourceHolder : null;
   }

   public IChatComponent getIChatComponent() {
      if (this.list.size() == 0) {
         return new BaseManagerStyleIChatComponentAdapter("death.attack.generic", new Object[]{this.entityLivingBase.getChatComponent()});
      } else {
         DamageSourceHolder damageSourceHolder = this.getDamageSourceHolder();
         DamageSourceHolder damageSourceHolder2 = (DamageSourceHolder)this.list.get(this.list.size() - 1);
         IChatComponent iChatComponent = damageSourceHolder2.getIChatComponent();
         Entity entity = damageSourceHolder2.getDamageSource().getEntity();
         Object entry;
         if (damageSourceHolder != null && damageSourceHolder2.getDamageSource() == DamageSource.damageSource11) {
            IChatComponent iChatComponent2 = damageSourceHolder.getIChatComponent();
            if (damageSourceHolder.getDamageSource() == DamageSource.damageSource11 || damageSourceHolder.getDamageSource() == DamageSource.damageSource10) {
               entry = new BaseManagerStyleIChatComponentAdapter(
                  "death.fell.accident." + this.resolveText(damageSourceHolder), new Object[]{this.entityLivingBase.getChatComponent()}
               );
            } else if (iChatComponent2 != null && (iChatComponent == null || !iChatComponent2.equals(iChatComponent))) {
               Entity entity2 = damageSourceHolder.getDamageSource().getEntity();
               ItemStack stack = entity2 instanceof EntityLivingBase ? ((EntityLivingBase)entity2).getItemStack() : null;
               if (stack != null && stack.isActive()) {
                  entry = new BaseManagerStyleIChatComponentAdapter(
                     "death.fell.assist.item", new Object[]{this.entityLivingBase.getChatComponent(), iChatComponent2, stack.getIChatComponent()}
                  );
               } else {
                  entry = new BaseManagerStyleIChatComponentAdapter("death.fell.assist", new Object[]{this.entityLivingBase.getChatComponent(), iChatComponent2});
               }
            } else if (iChatComponent != null) {
               ItemStack stack2 = entity instanceof EntityLivingBase ? ((EntityLivingBase)entity).getItemStack() : null;
               if (stack2 != null && stack2.isActive()) {
                  entry = new BaseManagerStyleIChatComponentAdapter(
                     "death.fell.finish.item", new Object[]{this.entityLivingBase.getChatComponent(), iChatComponent, stack2.getIChatComponent()}
                  );
               } else {
                  entry = new BaseManagerStyleIChatComponentAdapter("death.fell.finish", new Object[]{this.entityLivingBase.getChatComponent(), iChatComponent});
               }
            } else {
               entry = new BaseManagerStyleIChatComponentAdapter("death.fell.killer", new Object[]{this.entityLivingBase.getChatComponent()});
            }
         } else {
            entry = damageSourceHolder2.getDamageSource().resolveIChatComponent(this.entityLivingBase);
         }

         return (IChatComponent)entry;
      }
   }

   public EntityLivingBase getEntityLivingBase2() {
      return this.entityLivingBase;
   }

   private void updateState3() {
      this.labelText = null;
   }

   public int getCount() {
      return this.enabled2 ? this.entityLivingBase.ticksExisted - this.count2 : this.count - this.count2;
   }

   public void updateState2() {
      this.updateState3();
      if (this.entityLivingBase.canExecute9()) {
         Block block = this.entityLivingBase
            .worldObj
            .getBlockState(new BlockPos(this.entityLivingBase.posX, this.entityLivingBase.getBoundingBox2().minY, this.entityLivingBase.posZ))
            .getBlock();
         if (block == Blocks.block81) {
            this.labelText = "ladder";
         } else if (block == Blocks.block144) {
            this.labelText = "vines";
         }
      } else if (this.entityLivingBase.isInWater()) {
         this.labelText = "water";
      }
   }

   public EntityLivingBase getEntityLivingBase() {
      EntityLivingBase livingEntity = null;
      EntityPlayer player = null;
      float factor = 0.0F;
      float factor2 = 0.0F;

      for (DamageSourceHolder damageSourceHolder : (Iterable<DamageSourceHolder>) (Iterable) (this.list)) {
         if (damageSourceHolder.getDamageSource().getEntity() instanceof EntityPlayer && (player == null || damageSourceHolder.getFactor() > factor2)) {
            factor2 = damageSourceHolder.getFactor();
            player = (EntityPlayer)damageSourceHolder.getDamageSource().getEntity();
         }

         if (damageSourceHolder.getDamageSource().getEntity() instanceof EntityLivingBase && (livingEntity == null || damageSourceHolder.getFactor() > factor)) {
            factor = damageSourceHolder.getFactor();
            livingEntity = (EntityLivingBase)damageSourceHolder.getDamageSource().getEntity();
         }
      }

      return (EntityLivingBase)(player != null && factor2 >= factor / 3.0F ? player : livingEntity);
   }

   public CombatTracker(EntityLivingBase livingEntity) {
      this.entityLivingBase = livingEntity;
   }
}
