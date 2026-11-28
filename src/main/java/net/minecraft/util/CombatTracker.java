package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CombatTracker {
   private final List combatEntries = Lists.newArrayList();
   private final EntityLivingBase fighter;
   private int lastDamageTime;
   private int combatStartTime;
   private int combatEndTime;
   private boolean inCombat;
   private boolean takingDamage;
   private String fallSuffix;

   private String getFallSuffix(CombatEntry combatEntry) {
      return combatEntry.getFallSuffix() == null ? "generic" : combatEntry.getFallSuffix();
   }

   public EntityLivingBase getFighter() {
      return this.fighter;
   }

   public void calculateFallSuffix() {
      this.resetFallSuffix();
      if (this.fighter.isOnLadder()) {
         Block block = this.fighter.entityWorld.getBlockState(new BlockPos(this.fighter.posX, this.fighter.getEntityBoundingBox().minY, this.fighter.posZ)).getBlock();
         if (block == Blocks.ladder) {
            this.fallSuffix = "ladder";
         } else if (block == Blocks.vine) {
            this.fallSuffix = "vines";
         }
      } else if (this.fighter.isInWater()) {
         this.fallSuffix = "water";
      }
   }

   public int getCombatDuration() {
      return this.inCombat ? this.fighter.ticksExisted - this.combatStartTime : this.combatEndTime - this.combatStartTime;
   }

   public void trackDamage(DamageSource damageSource, float f, float f2) {
      this.reset();
      this.calculateFallSuffix();
      CombatEntry combatEntry = new CombatEntry(damageSource, this.fighter.ticksExisted, f, f2, this.fallSuffix, this.fighter.fallDistance);
      this.combatEntries.add(combatEntry);
      this.lastDamageTime = this.fighter.ticksExisted;
      this.takingDamage = true;
      if (combatEntry.isLivingDamageSrc() && !this.inCombat && this.fighter.isEntityAlive()) {
         this.inCombat = true;
         this.combatStartTime = this.fighter.ticksExisted;
         this.combatEndTime = this.combatStartTime;
         this.fighter.sendEnterCombat();
      }
   }

   public void reset() {
      int n = this.inCombat ? 300 : 100;
      if (this.takingDamage && (!this.fighter.isEntityAlive() || this.fighter.ticksExisted - this.lastDamageTime > n)) {
         boolean inCombat2 = this.inCombat;
         this.takingDamage = false;
         this.inCombat = false;
         this.combatEndTime = this.fighter.ticksExisted;
         if (inCombat2) {
            this.fighter.sendEndCombat();
         }

         this.combatEntries.clear();
      }
   }

   private void resetFallSuffix() {
      this.fallSuffix = null;
   }

   public CombatTracker(EntityLivingBase entity) {
      this.fighter = entity;
   }

   public EntityLivingBase getBestAttacker() {
      EntityLivingBase entity = null;
      EntityPlayer player = null;
      float f = 0.0F;
      float f2 = 0.0F;

      for (CombatEntry combatEntry : (Iterable<CombatEntry>)(this.combatEntries)) {
         if (combatEntry.getDamageSrc().getEntity() instanceof EntityPlayer && (player == null || combatEntry.getDamage() > f2)) {
            f2 = combatEntry.getDamage();
            player = (EntityPlayer)combatEntry.getDamageSrc().getEntity();
         }

         if (combatEntry.getDamageSrc().getEntity() instanceof EntityLivingBase && (entity == null || combatEntry.getDamage() > f)) {
            f = combatEntry.getDamage();
            entity = (EntityLivingBase)combatEntry.getDamageSrc().getEntity();
         }
      }

      return (EntityLivingBase)(player != null && f2 >= f / 3.0F ? player : entity);
   }

   private CombatEntry getBestCombatEntry() {
      CombatEntry combatEntry = null;
      CombatEntry combatEntry2 = null;
      byte b = 0;
      float f = 0.0F;

      for (int i = 0; i < this.combatEntries.size(); i++) {
         CombatEntry combatEntry3 = (CombatEntry)this.combatEntries.get(i);
         CombatEntry combatEntry4 = i > 0 ? (CombatEntry)this.combatEntries.get(i - 1) : null;
         if ((combatEntry3.getDamageSrc() == DamageSource.fall || combatEntry3.getDamageSrc() == DamageSource.outOfWorld)
            && combatEntry3.getDamageAmount() > 0.0F
            && (combatEntry == null || combatEntry3.getDamageAmount() > f)) {
            if (i > 0) {
               combatEntry = combatEntry4;
            } else {
               combatEntry = combatEntry3;
            }

            f = combatEntry3.getDamageAmount();
         }

         if (combatEntry3.getFallSuffix() != null && (combatEntry2 == null || combatEntry3.getDamage() > (float)b)) {
            combatEntry2 = combatEntry3;
         }
      }

      if (f > 5.0F && combatEntry != null) {
         return combatEntry;
      } else {
         return b > 5 && combatEntry2 != null ? combatEntry2 : null;
      }
   }

   public IChatComponent getDeathMessage() {
      if (this.combatEntries.size() == 0) {
         return new ChatComponentTranslation("death.attack.generic", new Object[]{this.fighter.getDisplayName()});
      } else {
         CombatEntry bestCombatEntry = this.getBestCombatEntry();
         CombatEntry combatEntry = (CombatEntry)this.combatEntries.get(this.combatEntries.size() - 1);
         IChatComponent damageSrcDisplayName = combatEntry.getDamageSrcDisplayName();
         Entity entity = combatEntry.getDamageSrc().getEntity();
         Object obj;
         if (bestCombatEntry != null && combatEntry.getDamageSrc() == DamageSource.fall) {
            IChatComponent damageSrcDisplayName2 = bestCombatEntry.getDamageSrcDisplayName();
            if (bestCombatEntry.getDamageSrc() == DamageSource.fall || bestCombatEntry.getDamageSrc() == DamageSource.outOfWorld) {
               obj = new ChatComponentTranslation("death.fell.accident." + this.getFallSuffix(bestCombatEntry), new Object[]{this.fighter.getDisplayName()});
            } else if (damageSrcDisplayName2 != null && (damageSrcDisplayName == null || !damageSrcDisplayName2.equals(damageSrcDisplayName))) {
               Entity entity2 = bestCombatEntry.getDamageSrc().getEntity();
               ItemStack stack = entity2 instanceof EntityLivingBase ? ((EntityLivingBase)entity2).getHeldItem() : null;
               if (stack != null && stack.hasDisplayName()) {
                  obj = new ChatComponentTranslation("death.fell.assist.item", new Object[]{this.fighter.getDisplayName(), damageSrcDisplayName2, stack.getChatComponent()});
               } else {
                  obj = new ChatComponentTranslation("death.fell.assist", new Object[]{this.fighter.getDisplayName(), damageSrcDisplayName2});
               }
            } else if (damageSrcDisplayName != null) {
               ItemStack stack2 = entity instanceof EntityLivingBase ? ((EntityLivingBase)entity).getHeldItem() : null;
               if (stack2 != null && stack2.hasDisplayName()) {
                  obj = new ChatComponentTranslation("death.fell.finish.item", new Object[]{this.fighter.getDisplayName(), damageSrcDisplayName, stack2.getChatComponent()});
               } else {
                  obj = new ChatComponentTranslation("death.fell.finish", new Object[]{this.fighter.getDisplayName(), damageSrcDisplayName});
               }
            } else {
               obj = new ChatComponentTranslation("death.fell.killer", new Object[]{this.fighter.getDisplayName()});
            }
         } else {
            obj = combatEntry.getDamageSrc().getDeathMessage(this.fighter);
         }

         return (IChatComponent)obj;
      }
   }
}
