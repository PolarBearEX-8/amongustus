package net.minecraft.entity;

import client.util.AchievementTheEnd2Manager;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity {
   public static final Logger logger = LogManager.getLogger();
   public int count12;
   public int count14;
   public int count11 = 5;
   public String labelText;
   public String labelText2;
   public float rotationPitch2 = (float)(Math.random() * Math.PI * 2.0);

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public void setLabelTextByText(String labelTextByText) {
      this.labelText = labelTextByText;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count11 = tagCompound.processValue("Health") & 255;
      this.count12 = tagCompound.processValue("Age");
      if (tagCompound.containsKey("PickupDelay")) {
         this.count14 = tagCompound.processValue("PickupDelay");
      }

      if (tagCompound.containsKey("Owner")) {
         this.labelText2 = tagCompound.resolveText("Owner");
      }

      if (tagCompound.containsKey("Thrower")) {
         this.labelText = tagCompound.resolveText("Thrower");
      }

      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Item");
      this.applyItemStack(ItemStack.resolveItemStack(nBTTagCompound));
      if (this.getItemStack() == null) {
         this.updateState9();
      }
   }

   @Override
   public String getText() {
      return this.processAction() ? this.getText2() : I18n.getString2("item." + this.getItemStack().processAction7());
   }

   private boolean matchesEntityItem(EntityItem entityItem) {
      if (entityItem == this) {
         return false;
      } else if (entityItem.canExecute() && this.canExecute()) {
         ItemStack stack = this.getItemStack();
         ItemStack stack2 = entityItem.getItemStack();
         if (this.count14 == 32767 || entityItem.count14 == 32767) {
            return false;
         } else if (this.count12 != -32768 && entityItem.count12 != -32768) {
            if (stack2.getItem() != stack.getItem()) {
               return false;
            } else if (stack2.canExecute() ^ stack.canExecute()) {
               return false;
            } else if (stack2.canExecute() && !stack2.getStackTagCompound().equals(stack.getStackTagCompound())) {
               return false;
            } else if (stack2.getItem() == null) {
               return false;
            } else if (stack2.getItem().isState() && stack2.getIndexOffset() != stack.getIndexOffset()) {
               return false;
            } else if (stack2.animationsToGo < stack.animationsToGo) {
               return entityItem.matchesEntityItem(this);
            } else if (stack2.animationsToGo + stack.animationsToGo > stack2.processAction5()) {
               return false;
            } else {
               stack2.animationsToGo = stack2.animationsToGo + stack.animationsToGo;
               entityItem.count14 = Math.max(entityItem.count14, this.count14);
               entityItem.count12 = Math.min(entityItem.count12, this.count12);
               entityItem.applyItemStack(stack2);
               this.updateState9();
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public void applyEntityPlayer2(EntityPlayer player) {
      if (!this.worldObj.state) {
         ItemStack stack = this.getItemStack();
         int animationsToGo = stack.animationsToGo;
         if (this.count14 == 0
            && (this.labelText2 == null || 6000 - this.count12 <= 200 || this.labelText2.equals(player.getText()))
            && player.inventoryPlayer.checkItemStack3(stack)) {
            if (stack.getItem() == Item.processAction2(Blocks.block140)) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter14);
            }

            if (stack.getItem() == Item.processAction2(Blocks.block104)) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter14);
            }

            if (stack.getItem() == Items.leather) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter20);
            }

            if (stack.getItem() == Items.diamond) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter22);
            }

            if (stack.getItem() == Items.blazeRod) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter27);
            }

            if (stack.getItem() == Items.diamond && this.getLabelText() != null) {
               EntityPlayer player2 = this.worldObj.resolveEntityPlayerByText(this.getLabelText());
               if (player2 != null && player2 != player) {
                  player2.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter3);
               }
            }

            if (!this.isActive7()) {
               this.worldObj.applyEntityByEntity(player, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }

            player.applyEntity2(this, animationsToGo);
            if (stack.animationsToGo <= 0) {
               this.updateState9();
            }
         }
      }
   }

   @Override
   public void applyIndex(int count) {
      this.matchesDamageSource(DamageSource.damageSource8, (float)count);
   }

   public void updateState28() {
      this.count14 = 32767;
   }

   public int getIndex() {
      return this.count12;
   }

   public ItemStack getItemStack() {
      ItemStack stack = this.getDataWatcher().getItemStack(10);
      if (stack == null) {
         if (this.worldObj != null) {
            logger.error("Item entity " + this.getEntityId() + " has no item?!");
         }

         return new ItemStack(Blocks.stone);
      } else {
         return stack;
      }
   }

   public void setCount14(int count) {
      this.count14 = count;
   }

   public boolean isActive16() {
      return this.count14 > 0;
   }

   @Override
   public void applyIndex2(int count) {
      super.applyIndex2(count);
      if (!this.worldObj.state) {
         this.updateState4();
      }
   }

   public void applyItemStack(ItemStack stack) {
      this.getDataWatcher().applyTargetByTarget(10, stack);
      this.getDataWatcher().applyIndex(10);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Health", (short)((byte)this.count11));
      tagCompound.put("Age", (short)this.count12);
      tagCompound.put("PickupDelay", (short)this.count14);
      if (this.getLabelText() != null) {
         tagCompound.put("Thrower", this.labelText);
      }

      if (this.getLabelText2() != null) {
         tagCompound.put("Owner", this.labelText2);
      }

      if (this.getItemStack() != null) {
         tagCompound.applyText("Item", this.getItemStack().resolveNBTTagCompound(new NBTTagCompound()));
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (this.getItemStack() != null && this.getItemStack().getItem() == Items.netherStar && damageSource.isEnabled5()) {
         return false;
      } else {
         this.updateState8();
         this.count11 = (int)((float)this.count11 - factor);
         if (this.count11 <= 0) {
            this.updateState9();
         }

         return false;
      }
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public void updateState2() {
      this.count14 = 0;
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   public void updateState3() {
      this.count12 = 4800;
   }

   public void updateState6() {
      this.count14 = 10;
   }

   public void updateState5() {
      this.count12 = -6000;
   }

   public void updateState27() {
      this.updateState28();
      this.count12 = 5999;
   }

   private void updateState4() {
      for (EntityItem entityItem : (Iterable<EntityItem>)(Iterable)(this.worldObj.resolveList(EntityItem.class, this.getBoundingBox2().offset(0.5, 0.0, 0.5)))) {
         this.matchesEntityItem(entityItem);
      }
   }

   @Override
   public boolean isActive2() {
      if (this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), Material.coreItemEntry1, this)) {
         if (!this.inWater && !this.enabled2) {
            this.updateState11();
         }

         this.inWater = true;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   @Override
   public void updateState() {
      if (this.getItemStack() == null) {
         this.updateState9();
      } else {
         super.updateState();
         if (this.count14 > 0 && this.count14 != 32767) {
            this.count14--;
         }

         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.motionY -= 0.04F;
         this.noClip = this.checkDouble(this.posX, (this.getBoundingBox2().minY + this.getBoundingBox2().maxY) / 2.0, this.posZ);
         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         if ((int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ || this.ticksExisted % 25 == 0) {
            if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.material29) {
               this.motionY = 0.2F;
               this.motionX = (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
               this.motionZ = (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
               this.applyText("random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
            }

            if (!this.worldObj.state) {
               this.updateState4();
            }
         }

         float factor = 0.98F;
         if (this.onGround) {
            factor = this.worldObj
                  .getBlockState(
                     new BlockPos(
                        MathHelper.ceiling_double_int(this.posX),
                        MathHelper.ceiling_double_int(this.getBoundingBox2().minY) - 1,
                        MathHelper.ceiling_double_int(this.posZ)
                     )
                  )
                  .getBlock()
                  .scaleFactor
               * 0.98F;
         }

         this.motionX *= (double)factor;
         this.motionY *= 0.98F;
         this.motionZ *= (double)factor;
         if (this.onGround) {
            this.motionY *= -0.5;
         }

         if (this.count12 != -32768) {
            this.count12++;
         }

         this.isActive2();
         if (!this.worldObj.state && this.count12 >= 6000) {
            this.updateState9();
         }
      }
   }

   @Override
   public void updateState7() {
      this.getDataWatcher().applyIndex(10, 5);
   }

   public EntityItem(World world, double posX, double posY, double posZ) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
      this.processDouble2(posX, posY, posZ);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.motionX = (double)((float)(Math.random() * 0.2F - 0.1F));
      this.motionY = 0.2F;
      this.motionZ = (double)((float)(Math.random() * 0.2F - 0.1F));
   }

   public EntityItem(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
      this.applyItemStack(new ItemStack(Blocks.air, 0));
   }

   public EntityItem(World world, double posX, double posY, double posZ, ItemStack stack) {
      this(world, posX, posY, posZ);
      this.applyItemStack(stack);
   }
}
