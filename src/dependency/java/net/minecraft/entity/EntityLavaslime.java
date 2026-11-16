package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.GameSettingsPeacefulType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class EntityLavaslime extends EntitySlime {
   @Override
   public EntitySlime getEntitySlime() {
      return new EntityLavaslime(this.worldObj);
   }

   @Override
   public int computeCount(float factor) {
      return 15728880;
   }

   @Override
   public int getCount4() {
      return this.getCount10() * 3;
   }

   @Override
   public AttackEffectsExplosionNormalType getFLAME() {
      return AttackEffectsExplosionNormalType.FLAME;
   }

   @Override
   public String getTextAsText() {
      return this.getCount10() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.2F);
   }

   @Override
   public boolean isActive29() {
      return true;
   }

   @Override
   public boolean isActive23() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this)
         && this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty()
         && !this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2());
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      Item gunpowder = this.getGunpowder();
      if (gunpowder != null && this.getCount10() > 1) {
         int count2 = this.random.nextInt(4) - 2;
         if (count > 0) {
            count2 += this.random.nextInt(count + 1);
         }

         for (int index = 0; index < count2; index++) {
            this.resolveEntityItem(gunpowder, 1);
         }
      }
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public Item getGunpowder() {
      return Items.magmaCream;
   }

   @Override
   public float computeFactor(float factor) {
      return 1.0F;
   }

   @Override
   public boolean canExecute2() {
      return false;
   }

   @Override
   public void updateState47() {
      this.factor42 *= 0.9F;
   }

   @Override
   public int getCount9() {
      return super.getCount9() + 2;
   }

   @Override
   public int nextIntAsIndex() {
      return super.nextIntAsIndex() * 4;
   }

   @Override
   public void updateState34() {
      this.motionY = (double)(0.22F + (float)this.getCount10() * 0.05F);
      this.enabled4 = true;
   }

   @Override
   public boolean isActive30() {
      return true;
   }

   @Override
   public void closeScreen() {
      this.motionY = (double)(0.42F + (float)this.getCount10() * 0.1F);
      this.enabled4 = true;
   }

   @Override
   public boolean nextInt() {
      return this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL;
   }

   public EntityLavaslime(World world) {
      super(world);
      this.state4 = true;
   }
}
