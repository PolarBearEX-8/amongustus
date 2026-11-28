package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IInteractionObject;

public class TileEntityEnchantmentTable extends TileEntity implements ITickable, IInteractionObject {
   public int tickCount;
   public float pageFlip;
   public float pageFlipPrev;
   public float flipT;
   public float flipA;
   public float bookSpread;
   public float bookSpreadPrev;
   public float bookRotation;
   public float bookRotationPrev;
   public float tRot;
   private static Random rand = new Random();
   public String customName;

   @Override
   public String getGuiID() {
      return "minecraft:enchanting_table";
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerEnchantment(inventoryPlayer, this.worldObj, this.pos);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.customName);
      }
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public void update() {
      this.bookSpreadPrev = this.bookSpread;
      this.bookRotationPrev = this.bookRotation;
      EntityPlayer closestPlayer = this.worldObj
         .getClosestPlayer((double)((float)this.pos.getX() + 0.5F), (double)((float)this.pos.getY() + 0.5F), (double)((float)this.pos.getZ() + 0.5F), 3.0);
      if (closestPlayer != null) {
         double d = closestPlayer.posX - (double)((float)this.pos.getX() + 0.5F);
         this.tRot = (float)MathHelper.atan2(closestPlayer.posZ - (double)((float)this.pos.getZ() + 0.5F), d);
         this.bookSpread += 0.1F;
         if (this.bookSpread < 0.5F || rand.nextInt(40) == 0) {
            float flipT2 = this.flipT;

            do {
               this.flipT = this.flipT + (float)(rand.nextInt(4) - rand.nextInt(4));
            } while (flipT2 == this.flipT);
         }
      } else {
         this.tRot += 0.02F;
         this.bookSpread -= 0.1F;
      }

      while (this.bookRotation >= (float) Math.PI) {
         this.bookRotation -= (float) (Math.PI * 2);
      }

      while (this.bookRotation < (float) -Math.PI) {
         this.bookRotation += (float) (Math.PI * 2);
      }

      while (this.tRot >= (float) Math.PI) {
         this.tRot -= (float) (Math.PI * 2);
      }

      while (this.tRot < (float) -Math.PI) {
         this.tRot += (float) (Math.PI * 2);
      }

      float f = this.tRot - this.bookRotation;

      while (f >= (float) Math.PI) {
         f -= (float) (Math.PI * 2);
      }

      while (f < (float) -Math.PI) {
         f += (float) (Math.PI * 2);
      }

      this.bookRotation += f * 0.4F;
      this.bookSpread = MathHelper.clamp_float(this.bookSpread, 0.0F, 1.0F);
      this.tickCount++;
      this.pageFlipPrev = this.pageFlip;
      float f2 = (this.flipT - this.pageFlip) * 0.4F;
      float f3 = 0.2F;
      f2 = MathHelper.clamp_float(f2, -f3, f3);
      this.flipA = this.flipA + (f2 - this.flipA) * 0.9F;
      this.pageFlip = this.pageFlip + this.flipA;
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.enchant";
   }

   public void setCustomName(String str) {
      this.customName = str;
   }
}
