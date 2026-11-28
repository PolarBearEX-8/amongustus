package net.minecraft.entity.monster;

import net.augustus.events.EventLiquidJump;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMagmaCube extends EntitySlime {

   @Override
   public EnumParticleTypes getParticleType() {
      return EnumParticleTypes.FLAME;
   }

   @Override
   public boolean isNotColliding() {
      return this.entityWorld.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.entityWorld.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()
         && !this.entityWorld.isAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public void alterSquishAmount() {
      this.squishAmount *= 0.9F;
   }

   @Override
   public void handleJumpLava(EventLiquidJump event) {
      this.motionY = (double)(0.22F + (float)this.getSlimeSize() * 0.05F);
      this.isAirBorne = true;
   }

   @Override
   public Item getDropItem() {
      return Items.magma_cream;
   }

   @Override
   public void jump() {
      this.motionY = (double)(0.42F + (float)this.getSlimeSize() * 0.1F);
      this.isAirBorne = true;
   }

   @Override
   public int getBrightnessForRender(float f) {
      return 15728880;
   }

   public EntityMagmaCube(World world) {
      super(world);
      this.isImmuneToFire = true;
   }

   @Override
   public int getTotalArmorValue() {
      return this.getSlimeSize() * 3;
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.entityWorld.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   @Override
   public int getAttackStrength() {
      return super.getAttackStrength() + 2;
   }

   @Override
   public int getJumpDelay() {
      return super.getJumpDelay() * 4;
   }

   @Override
   public float getBrightness(float f) {
      return 1.0F;
   }

   @Override
   public String getJumpSound() {
      return this.getSlimeSize() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
   }

   @Override
   public EntitySlime createInstance() {
      return new EntityMagmaCube(this.entityWorld);
   }

   @Override
   public void dropFewItems(boolean flag, int n) {
      Item dropItem = this.getDropItem();
      if (dropItem != null && this.getSlimeSize() > 1) {
         int n2 = this.rand.nextInt(4) - 2;
         if (n > 0) {
            n2 += this.rand.nextInt(n + 1);
         }

         for (int i = 0; i < n2; i++) {
            this.dropItem(dropItem, 1);
         }
      }
   }

   @Override
   public boolean makesSoundOnLand() {
      return true;
   }

   @Override
   public void fall(float f, float f2) {
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
   }

   @Override
   public boolean canDamagePlayer() {
      return true;
   }
}
