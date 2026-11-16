package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.DisablerUndefinedType;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ExtendedClassEntry;
import client.util.SupplementalClientManagerAdapter;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityEndermite extends EntityMonster {
   public int count30 = 0;
   public boolean enabled28 = false;

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public String getText9() {
      return "mob.silverfish.kill";
   }

   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.ARTHROPOD;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count30 = tagCompound.computeCount("Lifetime");
      this.enabled28 = tagCompound.matchesText("PlayerSpawned");
   }

   @Override
   public float getHeight2() {
      return 0.1F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(8.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(2.0);
   }

   public void setEnabled28(boolean enabled) {
      this.enabled28 = enabled;
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
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Lifetime", this.count30);
      tagCompound.applyText("PlayerSpawned", this.enabled28);
   }

   @Override
   public String getText10() {
      return "mob.silverfish.hit";
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

   public boolean isEnabled28() {
      return this.enabled28;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (this.worldObj.state) {
         for (int index = 0; index < 2; index++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.PORTAL,
                  this.posX + (this.random.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.random.nextDouble() * (double)this.height,
                  this.posZ + (this.random.nextDouble() - 0.5) * (double)this.width,
                  (this.random.nextDouble() - 0.5) * 2.0,
                  -this.random.nextDouble(),
                  (this.random.nextDouble() - 0.5) * 2.0,
                  new int[0]
               );
         }
      } else {
         if (!this.isEnabled21()) {
            this.count30++;
         }

         if (this.count30 >= 2400) {
            this.updateState9();
         }
      }
   }

   @Override
   public boolean nextInt() {
      return super.nextInt() ? this.worldObj.resolveEntityPlayerByEntity(this, 5.0) == null : false;
   }

   public EntityEndermite(World world) {
      super(world);
      this.count26 = 3;
      this.applyFloat(0.4F, 0.3F);
      this.goalSetupManager.add(1, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new AuxiliaryWorldEntry(this, EntityPlayer.class, 1.0, false));
      this.goalSetupManager.add(3, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, true, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
   }
}
