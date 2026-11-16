package net.minecraft.entity;

import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.CommonClientManagerAdapter;
import client.util.DoMobLootClientManager;
import client.util.HitBoxBuffer;
import client.util.StandardClientManagerAdapter;
import client.util.SupplementalClientManagerAdapter;
import client.util.UUIDEntryAdapter;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.GlobalItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityChicken extends HitBoxBuffer {
   public float factor51;
   public float factor53;
   public float factor52;
   public float factor54;
   public float factor49 = 1.0F;
   public int count32;
   public boolean enabled28;

   @Override
   public void updateState17() {
      super.updateState17();
      float factor = MathHelper.abs(this.scaleFactor8 * (float) Math.PI / 180.0F);
      float factor2 = MathHelper.cos(this.scaleFactor8 * (float) Math.PI / 180.0F);
      float factor3 = 0.1F;
      float factor4 = 0.0F;
      this.entity
         .processDouble2(
            this.posX + (double)(factor3 * factor),
            this.posY + (double)(this.height * 0.5F) + this.entity.getValue() + (double)factor4,
            this.posZ - (double)(factor3 * factor2)
         );
      if (this.entity instanceof EntityLivingBase) {
         ((EntityLivingBase)this.entity).scaleFactor8 = this.scaleFactor8;
      }
   }

   @Override
   public boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack != null && stack.getItem() == Items.wheatSeeds;
   }

   @Override
   public String getText9() {
      return "mob.chicken.hurt";
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.chicken.step", 0.15F, 1.0F);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled28 = tagCompound.matchesText("IsChickenJockey");
      if (tagCompound.containsKey("EggLayTime")) {
         this.count32 = tagCompound.computeCount("EggLayTime");
      }
   }

   @Override
   public int nextInt(EntityPlayer player) {
      return this.isEnabled28() ? 10 : super.nextInt(player);
   }

   @Override
   public float getHeight2() {
      return this.height;
   }

   public EntityChicken resolveEntityChicken(UUIDEntryAdapter uUIDEntryAdapter) {
      return new EntityChicken(this.worldObj);
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(4.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3) + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.feather, 1);
      }

      if (this.canExecute2()) {
         this.resolveEntityItem(Items.cookedChicken, 1);
      } else {
         this.resolveEntityItem(Items.chicken, 1);
      }
   }

   @Override
   public UUIDEntryAdapter resolveUUIDEntryAdapter(UUIDEntryAdapter uUIDEntryAdapter) {
      return this.resolveEntityChicken(uUIDEntryAdapter);
   }

   @Override
   public Item getGunpowder() {
      return Items.feather;
   }

   @Override
   public String getText3() {
      return "mob.chicken.say";
   }

   @Override
   public String getText10() {
      return "mob.chicken.hurt";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("IsChickenJockey", this.enabled28);
      tagCompound.put("EggLayTime", this.count32);
   }

   public boolean isEnabled28() {
      return this.enabled28;
   }

   public void setEnabled28(boolean enabled) {
      this.enabled28 = enabled;
   }

   @Override
   public boolean isActive24() {
      return this.isEnabled28() && this.entity == null;
   }

   @Override
   public void updateState33() {
      super.updateState33();
      this.factor54 = this.factor51;
      this.factor52 = this.factor53;
      this.factor53 = (float)((double)this.factor53 + (double)(this.onGround ? -1 : 4) * 0.3);
      this.factor53 = MathHelper.clamp_float(this.factor53, 0.0F, 1.0F);
      if (!this.onGround && this.factor49 < 1.0F) {
         this.factor49 = 1.0F;
      }

      this.factor49 = (float)((double)this.factor49 * 0.9);
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      this.factor51 = this.factor51 + this.factor49 * 2.0F;
      if (!this.worldObj.state && !this.isActive16() && !this.isEnabled28() && --this.count32 <= 0) {
         this.applyText("mob.chicken.plop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
         this.resolveEntityItem(Items.egg, 1);
         this.count32 = this.random.nextInt(6000) + 6000;
      }
   }

   public EntityChicken(World world) {
      super(world);
      this.applyFloat(0.4F, 0.7F);
      this.count32 = this.random.nextInt(6000) + 6000;
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(1, new CommonClientManagerAdapter(this, 1.4));
      this.goalSetupManager.add(2, new DoMobLootClientManager(this, 1.0));
      this.goalSetupManager.add(3, new StandardClientManagerAdapter(this, 1.0, Items.wheatSeeds, false));
      this.goalSetupManager.add(4, new GlobalItemEntry(this, 1.1));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(7, new ComplexItemEntry(this));
   }
}
