package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.BezierTransformer;
import client.util.Listener63Extension;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class TileEntityEnchanttable extends TileEntity implements Listener63Extension, PreUpdateConsumer {
   public int count2;
   public float factor5;
   public float factor6;
   public float factor8;
   public float factor3;
   public float factor7;
   public float factor2;
   public float factor4;
   public float factor;
   public float factor9;
   public static Random random = new Random();
   public String labelText;

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.enchant";
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new BezierTransformer(inventoryPlayer, this.world, this.blockPos);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }
   }

   @Override
   public void updateState() {
      this.factor2 = this.factor7;
      this.factor = this.factor4;
      EntityPlayer closestPlayer = this.world
         .getClosestPlayer(
            (double)((float)this.blockPos.getX() + 0.5F), (double)((float)this.blockPos.getY() + 0.5F), (double)((float)this.blockPos.getZ() + 0.5F), 3.0
         );
      if (closestPlayer != null) {
         double offset = closestPlayer.posX - (double)((float)this.blockPos.getX() + 0.5F);
         double offset2 = closestPlayer.posZ - (double)((float)this.blockPos.getZ() + 0.5F);
         this.factor9 = (float)MathHelper.getDouble6(offset2, offset);
         this.factor7 += 0.1F;
         if (this.factor7 < 0.5F || random.nextInt(40) == 0) {
            float factor10 = this.factor8;

            do {
               this.factor8 = this.factor8 + (float)(random.nextInt(4) - random.nextInt(4));
            } while (factor10 == this.factor8);
         }
      } else {
         this.factor9 += 0.02F;
         this.factor7 -= 0.1F;
      }

      while (this.factor4 >= (float) Math.PI) {
         this.factor4 -= (float) (Math.PI * 2);
      }

      while (this.factor4 < (float) -Math.PI) {
         this.factor4 += (float) (Math.PI * 2);
      }

      while (this.factor9 >= (float) Math.PI) {
         this.factor9 -= (float) (Math.PI * 2);
      }

      while (this.factor9 < (float) -Math.PI) {
         this.factor9 += (float) (Math.PI * 2);
      }

      float factor11 = this.factor9 - this.factor4;

      while (factor11 >= (float) Math.PI) {
         factor11 -= (float) (Math.PI * 2);
      }

      while (factor11 < (float) -Math.PI) {
         factor11 += (float) (Math.PI * 2);
      }

      this.factor4 += factor11 * 0.4F;
      this.factor7 = MathHelper.clamp_float(this.factor7, 0.0F, 1.0F);
      this.count2++;
      this.factor6 = this.factor5;
      float factor12 = (this.factor8 - this.factor5) * 0.4F;
      float factor13 = 0.2F;
      float factor14 = MathHelper.clamp_float(factor12, -factor13, factor13);
      this.factor3 = this.factor3 + (factor14 - this.factor3) * 0.9F;
      this.factor5 = this.factor5 + this.factor3;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:enchanting_table";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (this.processAction()) {
         tagCompound.put("CustomName", this.labelText);
      }
   }
}
