package net.minecraft.client.entity;

import client.util.BacktrackRecord;
import client.util.MinecraftAccessor;
import client.util.SpecialHashMapEntry;
import client.util.Vec3Entry;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityOtherPlayerMP extends AbstractClientPlayer implements MinecraftAccessor {
   public boolean enabled26;
   public int otherPlayerMPPosRotationIncrements;
   public double coordOffset3;
   public double coordOffset1;
   public double coordOffset4;
   public double value53;
   public double value48;

   @Override
   public boolean matchesText(int count, String text) {
      return false;
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
      if (count == 0) {
         this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem] = stack;
      } else {
         this.inventoryPlayer.armorInventory[count - 1] = stack;
      }
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      Minecraft.getMinecraft().ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return true;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      this.coordOffset3 = posX;
      this.coordOffset1 = posY;
      this.coordOffset4 = posZ;
      this.value53 = (double)factor;
      this.value48 = (double)factor2;
      this.otherPlayerMPPosRotationIncrements = count;
   }

   @Override
   public void updateState18() {
      if (SpecialHashMapEntry.resolveNativeStringEntryByEntityPlayer(this, 2) == null) {
         super.updateState18();
      }
   }

   @Override
   public void updateState() {
      this.factor31 = 0.0F;
      super.updateState();
      this.factor9 = this.factor11;
      double offset = this.posX - this.prevPosX;
      double offset2 = this.posZ - this.prevPosZ;
      float factor = MathHelper.sqrt_double(offset * offset + offset2 * offset2) * 4.0F;
      if (factor > 1.0F) {
         factor = 1.0F;
      }

      this.factor11 = this.factor11 + (factor - this.factor11) * 0.4F;
      this.factor14 = this.factor14 + this.factor11;
      if (!this.enabled26 && this.canExecute6() && this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem] != null) {
         ItemStack stack = this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem];
         this.applyItemStack(this.inventoryPlayer.mainInventory[this.inventoryPlayer.currentItem], stack.getItem().processAction(stack));
         this.enabled26 = true;
      } else if (this.enabled26 && !this.canExecute6()) {
         this.updateState44();
         this.enabled26 = false;
      }
   }

   @Override
   public void updateState33() {
      this.positionVec = new Vec3(this.positionVec3.xCoord, this.positionVec3.yCoord, this.positionVec3.zCoord);
      this.positionVec5 = new Vec3(this.positionVec4.xCoord, this.positionVec4.yCoord, this.positionVec4.zCoord);
      if (this.indexOffset23 > 0) {
         double offset = this.positionVec3.xCoord + (this.coordOffset5 - this.positionVec3.xCoord) / (double)this.indexOffset23;
         double offset2 = this.positionVec3.yCoord + (this.coordOffset20 - this.positionVec3.yCoord) / (double)this.indexOffset23;
         double offset3 = this.positionVec3.zCoord + (this.coordOffset2 - this.positionVec3.zCoord) / (double)this.indexOffset23;
         this.indexOffset23--;
         this.positionVec3 = new Vec3(offset, offset2, offset3);
         float factor = this.width / 2.0F;
         float height = this.height;
         this.axisAlignedBB2 = new AxisAlignedBB(
            offset - (double)factor, offset2, offset3 - (double)factor, offset + (double)factor, offset2 + (double)height, offset3 + (double)factor
         );
      }

      this.positionVec4 = new Vec3(this.coordOffset5, this.coordOffset20, this.coordOffset2);
      if (this.otherPlayerMPPosRotationIncrements > 0) {
         double offset4 = this.posX + (this.coordOffset3 - this.posX) / (double)this.otherPlayerMPPosRotationIncrements;
         double offset5 = this.posY + (this.coordOffset1 - this.posY) / (double)this.otherPlayerMPPosRotationIncrements;
         double offset6 = this.posZ + (this.coordOffset4 - this.posZ) / (double)this.otherPlayerMPPosRotationIncrements;
         double offset7 = this.value53 - (double)this.rotationYaw;

         while (offset7 < -180.0) {
            offset7 += 360.0;
         }

         while (offset7 >= 180.0) {
            offset7 -= 360.0;
         }

         this.rotationYaw = (float)((double)this.rotationYaw + offset7 / (double)this.otherPlayerMPPosRotationIncrements);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.value48 - (double)this.rotationPitch) / (double)this.otherPlayerMPPosRotationIncrements);
         this.otherPlayerMPPosRotationIncrements--;
         this.processDouble2(offset4, offset5, offset6);
         this.processFloat4(this.rotationYaw, this.rotationPitch);
      }

      if (this.ticksExisted > 5 && Minecraft.getMinecraft().isSingleplayer()) {
         BacktrackRecord backtrackRecord = this.backtrackRecord3;
         Vec3Entry vec3Entry = new Vec3Entry(this);
         backtrackRecord.applyObject(vec3Entry);
      }

      this.factor30 = this.factor47;
      this.updateState37();
      float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      float factor3 = (float)Math.atan(-this.motionY * 0.2F) * 15.0F;
      if (factor2 > 0.1F) {
         factor2 = 0.1F;
      }

      if (!this.onGround || this.getFloat2() <= 0.0F) {
         factor2 = 0.0F;
      }

      if (this.onGround || this.getFloat2() <= 0.0F) {
         factor3 = 0.0F;
      }

      this.factor47 = this.factor47 + (factor2 - this.factor47) * 0.4F;
      this.factor8 = this.factor8 + (factor3 - this.factor8) * 0.8F;
   }

   public EntityOtherPlayerMP(World world, GameProfile gameProfile) {
      super(world, gameProfile);
      this.stepHeight = 0.0F;
      this.noClip = true;
      this.factor31 = 0.25F;
      this.value3 = 10.0;
   }
}
