package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.setting.LocalOptionType;
import client.util.AchievementTheEnd2Manager;
import client.util.AimPicker;
import client.util.BezierEvaluator;
import client.util.IBlockState;
import client.util.Packet;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class TileEntityBeacon extends TileEntityAdapter implements PreUpdateConsumer, IInventory {
   public static final Potion[][] potionArray = new Potion[][]{
      {Potion.potion2, Potion.potion28}, {Potion.potion12, Potion.potion9}, {Potion.potion}, {Potion.potion11}
   };
   public final List list = Lists.newArrayList();
   public long timestamp;
   public float factor;
   public boolean enabled2;
   public int count2 = -1;
   public int count4;
   public int count3;
   public ItemStack itemStack;
   public String labelText;

   @Override
   public int processAction(int count) {
      switch (count) {
         case 0:
            return this.count2;
         case 1:
            return this.count4;
         case 2:
            return this.count3;
         default:
            return 0;
      }
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public ItemStack getItemStack(int count, int count5) {
      if (count != 0 || this.itemStack == null) {
         return null;
      } else if (count5 >= this.itemStack.animationsToGo) {
         ItemStack itemStack2 = this.itemStack;
         this.itemStack = null;
         return itemStack2;
      } else {
         this.itemStack.animationsToGo -= count5;
         return new ItemStack(this.itemStack.getItem(), count5, this.itemStack.getIndexOffset());
      }
   }

   public void updateState7() {
      this.updateState6();
      this.updateState8();
   }

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   @Override
   public double getValue() {
      return 65536.0;
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.beacon";
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      if (count == 0) {
         this.itemStack = stack;
      }
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      if (count == 0 && this.itemStack != null) {
         ItemStack itemStack2 = this.itemStack;
         this.itemStack = null;
         return itemStack2;
      } else {
         return null;
      }
   }

   private void updateState6() {
      int count = this.count2;
      int coordX = this.blockPos.getX();
      int coordY = this.blockPos.getY();
      int coordZ = this.blockPos.getZ();
      this.count2 = 0;
      this.list.clear();
      this.enabled2 = true;
      AimPicker aimPicker = new AimPicker(EntitySheep.get(LocalOptionType.WHITE));
      this.list.add(aimPicker);
      boolean flag = true;
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = coordY + 1; index < 256; index++) {
         IBlockState blockState = this.world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(coordX, index, coordZ));
         float[] factorArray;
         if (blockState.getBlock() == Blocks.blockStainedGlass) {
            factorArray = EntitySheep.get((LocalOptionType)blockState.getComparable(BlockStainedGlass.immutableSetEntry));
         } else {
            if (blockState.getBlock() != Blocks.blockStainedGlassPane) {
               if (blockState.getBlock().getIndexOffset() >= 15 && blockState.getBlock() != Blocks.block95) {
                  this.enabled2 = false;
                  this.list.clear();
                  break;
               }

               aimPicker.updateState();
               continue;
            }

            factorArray = EntitySheep.get((LocalOptionType)blockState.getComparable(BlockStainedGlassPane.immutableSetEntry));
         }

         if (!flag) {
            factorArray = new float[]{
               (aimPicker.getFactorArray()[0] + factorArray[0]) / 2.0F, (aimPicker.getFactorArray()[1] + factorArray[1]) / 2.0F, (aimPicker.getFactorArray()[2] + factorArray[2]) / 2.0F
            };
         }

         if (Arrays.equals(factorArray, aimPicker.getFactorArray())) {
            aimPicker.updateState();
         } else {
            aimPicker = new AimPicker(factorArray);
            this.list.add(aimPicker);
         }

         flag = false;
      }

      if (this.enabled2) {
         for (int index2 = 1; index2 <= 4; this.count2 = index2++) {
            int count5 = coordY - index2;
            if (count5 < 0) {
               break;
            }

            boolean flag2 = true;

            for (int index3 = coordX - index2; index3 <= coordX + index2 && flag2; index3++) {
               for (int index4 = coordZ - index2; index4 <= coordZ + index2; index4++) {
                  Block block = this.world.getBlockState(new BlockPos(index3, count5, index4)).getBlock();
                  if (block != Blocks.block93 && block != Blocks.block35 && block != Blocks.block91 && block != Blocks.block16) {
                     flag2 = false;
                     break;
                  }
               }
            }

            if (!flag2) {
               break;
            }
         }

         if (this.count2 == 0) {
            this.enabled2 = false;
         }
      }

      if (!this.world.state && this.count2 == 4 && count < this.count2) {
         Iterator iterator = this.world
            .resolveList(
               EntityPlayer.class,
               new AxisAlignedBB((double)coordX, (double)coordY, (double)coordZ, (double)coordX, (double)(coordY - 4), (double)coordZ).offset(10.0, 5.0, 10.0)
            )
            .iterator();

         while (iterator.hasNext()) {
            ((EntityPlayer)iterator.next()).applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter8);
         }
      }
   }

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public void updateState2() {
      this.itemStack = null;
   }

   @Override
   public boolean checkIndex(int count, int count5) {
      if (count == 1) {
         this.updateState7();
         return true;
      } else {
         return super.checkIndex(count, count5);
      }
   }

   @Override
   public int processAction3() {
      return 3;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new BezierEvaluator(inventoryPlayer, this);
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return stack.getItem() == Items.emerald || stack.getItem() == Items.diamond || stack.getItem() == Items.goldIngot || stack.getItem() == Items.ironIngot;
   }

   private void updateState8() {
      if (this.enabled2 && this.count2 > 0 && !this.world.state && this.count4 > 0) {
         double offset = (double)(this.count2 * 10 + 10);
         byte byteValue = 0;
         if (this.count2 >= 4 && this.count4 == this.count3) {
            byteValue = 1;
         }

         int coordX = this.blockPos.getX();
         int coordY = this.blockPos.getY();
         int coordZ = this.blockPos.getZ();
         AxisAlignedBB boundingBox = new AxisAlignedBB((double)coordX, (double)coordY, (double)coordZ, (double)(coordX + 1), (double)(coordY + 1), (double)(coordZ + 1))
            .offset(offset, offset, offset)
            .contract(0.0, (double)this.world.getCount(), 0.0);
         List list2 = this.world.resolveList(EntityPlayer.class, boundingBox);
         Iterator iterator = list2.iterator();

         while (iterator.hasNext()) {
            ((EntityPlayer)iterator.next()).applyPotionEffectByPotionEffect(new PotionEffect(this.count4, 180, byteValue, true, true));
         }

         if (this.count2 >= 4 && this.count4 != this.count3 && this.count3 > 0) {
            Iterator iterator2 = list2.iterator();

            while (iterator2.hasNext()) {
               ((EntityPlayer)iterator2.next()).applyPotionEffectByPotionEffect(new PotionEffect(this.count3, 180, 0, true, true));
            }
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.count4 = this.computeCount(tagCompound.computeCount("Primary"));
      this.count3 = this.computeCount(tagCompound.computeCount("Secondary"));
      this.count2 = tagCompound.computeCount("Levels");
   }

   private int computeCount(int count) {
      if (count >= 0 && count < Potion.potionArray.length && Potion.potionArray[count] != null) {
         Potion potion = Potion.potionArray[count];
         return potion != Potion.potion2
               && potion != Potion.potion28
               && potion != Potion.potion12
               && potion != Potion.potion9
               && potion != Potion.potion
               && potion != Potion.potion11
            ? 0
            : count;
      } else {
         return 0;
      }
   }

   @Override
   public void updateState() {
      if (this.world.getTimestamp2() % 80L == 0L) {
         this.updateState7();
      }
   }

   public List getList() {
      return this.list;
   }

   @Override
   public int processAction4() {
      return 1;
   }

   @Override
   public void processAction(int count, int count5) {
      switch (count) {
         case 0:
            this.count2 = count5;
            break;
         case 1:
            this.count4 = this.computeCount(count5);
            break;
         case 2:
            this.count3 = this.computeCount(count5);
      }
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public String getLabelText2() {
      return "minecraft:beacon";
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      return new S35PacketUpdateTileEntity(this.blockPos, 3, tagCompound);
   }

   public float getFactor() {
      if (!this.enabled2) {
         return 0.0F;
      } else {
         int count = (int)(this.world.getTimestamp2() - this.timestamp);
         this.timestamp = this.world.getTimestamp2();
         if (count > 1) {
            this.factor -= (float)count / 40.0F;
            if (this.factor < 0.0F) {
               this.factor = 0.0F;
            }
         }

         this.factor += 0.025F;
         if (this.factor > 1.0F) {
            this.factor = 1.0F;
         }

         return this.factor;
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("Primary", this.count4);
      tagCompound.put("Secondary", this.count3);
      tagCompound.put("Levels", this.count2);
   }

   @Override
   public ItemStack processAction3(int count) {
      return count == 0 ? this.itemStack : null;
   }
}
