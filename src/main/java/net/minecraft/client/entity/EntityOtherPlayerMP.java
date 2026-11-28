package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityOtherPlayerMP extends AbstractClientPlayer implements MC {
   private boolean isItemInUse;
   public int otherPlayerMPPosRotationIncrements;
   public double otherPlayerMPX;
   public double otherPlayerMPY;
   public double otherPlayerMPZ;
   public double otherPlayerMPYaw;
   public double otherPlayerMPPitch;

   @Override
   public boolean canCommandSenderUseCommand(int n, String str) {
      return false;
   }

   public EntityOtherPlayerMP(World world, GameProfile profile) {
      super(world, profile);
      this.stepHeight = 0.0F;
      this.noClip = true;
      this.renderOffsetY = 0.25F;
      this.renderDistanceWeight = 10.0;
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return true;
   }

   @Override
   public Vec3 getPredictedPosition() {
      if (this.otherPlayerMPPosRotationIncrements > 0) {
         double d = this.posX + (this.otherPlayerMPX - this.posX) / (double)this.otherPlayerMPPosRotationIncrements;
         double d2 = this.posY + (this.otherPlayerMPY - this.posY) / (double)this.otherPlayerMPPosRotationIncrements;
         double d3 = this.posZ + (this.otherPlayerMPZ - this.posZ) / (double)this.otherPlayerMPPosRotationIncrements;
         return new Vec3(d, d2, d3);
      } else {
         return new Vec3(this.posX, this.posY, this.posZ);
      }
   }

   @Override
   public void spawnRunningParticles() {
      super.spawnRunningParticles();
   }

   @Override
   public void setCurrentItemOrArmor(int n, ItemStack stack) {
      if (n == 0) {
         this.inventory.mainInventory[this.inventory.currentItem] = stack;
      } else {
         this.inventory.armorInventory[n - 1] = stack;
      }
   }

   @Override
   public void onUpdate() {
      this.renderOffsetY = 0.0F;
      super.onUpdate();
      this.prevLimbSwingAmount = this.limbSwingAmount;
      double d = this.posX - this.prevPosX;
      double d2 = this.posZ - this.prevPosZ;
      float f = MathHelper.sqrt_double(d * d + d2 * d2) * 4.0F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      this.limbSwingAmount = this.limbSwingAmount + (f - this.limbSwingAmount) * 0.4F;
      this.limbSwing = this.limbSwing + this.limbSwingAmount;
      if (!this.isItemInUse && this.isEating() && this.inventory.mainInventory[this.inventory.currentItem] != null) {
         ItemStack stack = this.inventory.mainInventory[this.inventory.currentItem];
         this.setItemInUse(this.inventory.mainInventory[this.inventory.currentItem], stack.getItem().getMaxItemUseDuration(stack));
         this.isItemInUse = true;
      } else if (this.isItemInUse && !this.isEating()) {
         this.clearItemInUse();
         this.isItemInUse = false;
      }
   }

   @Override
   public void onLivingUpdate() {
      this.prevInterpolatedServerPos = new Vec3(this.interpolatedServerPos.xCoord, this.interpolatedServerPos.yCoord, this.interpolatedServerPos.zCoord);
      this.prevRawServerPos = new Vec3(this.rawServerPos.xCoord, this.rawServerPos.yCoord, this.rawServerPos.zCoord);
      if (this.realPosRotationIncrements > 0) {
         double d = this.interpolatedServerPos.xCoord + (this.realPosX - this.interpolatedServerPos.xCoord) / (double)this.realPosRotationIncrements;
         double d2 = this.interpolatedServerPos.yCoord + (this.realPosY - this.interpolatedServerPos.yCoord) / (double)this.realPosRotationIncrements;
         double d3 = this.interpolatedServerPos.zCoord + (this.realPosZ - this.interpolatedServerPos.zCoord) / (double)this.realPosRotationIncrements;
         this.realPosRotationIncrements--;
         this.interpolatedServerPos = new Vec3(d, d2, d3);
         float f = this.width / 2.0F;
         float height2 = this.height;
         this.serverBoundingBox = new AxisAlignedBB(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height2, d3 + (double)f);
      }

      this.rawServerPos = new Vec3(this.realPosX, this.realPosY, this.realPosZ);
      if (this.otherPlayerMPPosRotationIncrements > 0) {
         double d4 = this.posX + (this.otherPlayerMPX - this.posX) / (double)this.otherPlayerMPPosRotationIncrements;
         double d5 = this.posY + (this.otherPlayerMPY - this.posY) / (double)this.otherPlayerMPPosRotationIncrements;
         double d6 = this.posZ + (this.otherPlayerMPZ - this.posZ) / (double)this.otherPlayerMPPosRotationIncrements;
         double d7 = this.otherPlayerMPYaw - (double)this.rotationYaw;

         while (d7 < -180.0) {
            d7 += 360.0;
         }

         while (d7 >= 180.0) {
            d7 -= 360.0;
         }

         this.rotationYaw = (float)((double)this.rotationYaw + d7 / (double)this.otherPlayerMPPosRotationIncrements);
         this.rotationPitch = (float)(
            (double)this.rotationPitch + (this.otherPlayerMPPitch - (double)this.rotationPitch) / (double)this.otherPlayerMPPosRotationIncrements
         );
         this.otherPlayerMPPosRotationIncrements--;
         this.setPosition(d4, d5, d6);
         this.setRotation(this.rotationYaw, this.rotationPitch);
      }

      if (this.ticksExisted > 5 && Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         this.motionSnapshotHistory.add(new EntityMotionSnapshot(this));
      }

      this.prevCameraYaw = this.cameraYaw;
      this.updateArmSwingProgress();
      float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      float f3 = (float)Math.atan(-this.motionY * 0.20000000298023224) * 15.0F;
      if (f2 > 0.1F) {
         f2 = 0.1F;
      }

      if (!this.onGround || this.getCurrentHealth() <= 0.0F) {
         f2 = 0.0F;
      }

      if (this.onGround || this.getCurrentHealth() <= 0.0F) {
         f3 = 0.0F;
      }

      this.cameraYaw = this.cameraYaw + (f2 - this.cameraYaw) * 0.4F;
      this.cameraPitch = this.cameraPitch + (f3 - this.cameraPitch) * 0.8F;
   }

   @Override
   public void addChatMessage(IChatComponent component) {
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.otherPlayerMPX = d;
      this.otherPlayerMPY = d2;
      this.otherPlayerMPZ = d3;
      this.otherPlayerMPYaw = (double)f;
      this.otherPlayerMPPitch = (double)f2;
      this.otherPlayerMPPosRotationIncrements = n;
   }
}
