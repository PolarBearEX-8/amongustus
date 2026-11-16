package net.minecraft.entity;

import client.setting.DisablerUndefinedType;
import client.util.AuxiliaryWorldEntry;
import client.util.ClassArrayEntry;
import client.util.DamageSourceAdapter;
import client.util.ExtendedClassEntry;
import client.util.HitBoxSynchronizer;
import client.util.HitBoxWatcher;
import client.util.SupplementalClientManagerAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySilverfish extends EntityMonster {
   public HitBoxWatcher hitBoxWatcher;

   @Override
   public float computeFactor(BlockPos blockPos) {
      return this.worldObj.getBlockState(blockPos.down()).getBlock() == Blocks.stone ? 10.0F : super.computeFactor(blockPos);
   }

   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.ARTHROPOD;
   }

   @Override
   public String getText9() {
      return "mob.silverfish.kill";
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public float getHeight2() {
      return 0.1F;
   }

   @Override
   public double getValue() {
      return 0.2;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(8.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(1.0);
   }

   @Override
   public Item getGunpowder() {
      return null;
   }

   @Override
   public String getText3() {
      return "mob.silverfish.say";
   }

   @Override
   public String getText10() {
      return "mob.silverfish.hit";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         if (damageSource instanceof DamageSourceAdapter || damageSource == DamageSource.damageSource5) {
            this.hitBoxWatcher.updateState4();
         }

         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public boolean isActive31() {
      return true;
   }

   @Override
   public void updateState() {
      this.scaleFactor8 = this.rotationYaw;
      super.updateState();
   }

   @Override
   public boolean nextInt() {
      return super.nextInt() ? this.worldObj.resolveEntityPlayerByEntity(this, 5.0) == null : false;
   }

   public EntitySilverfish(World world) {
      super(world);
      this.applyFloat(0.4F, 0.3F);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(3, this.hitBoxWatcher = new HitBoxWatcher(this));
      this.goalSetupManager.add(4, new AuxiliaryWorldEntry(this, EntityPlayer.class, 1.0, false));
      this.goalSetupManager.add(5, new HitBoxSynchronizer(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, true, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
   }
}
