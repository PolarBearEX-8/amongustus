package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.AdvancedContainerAdapter;
import client.util.DistanceBuilder;
import client.util.SmoothDirector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityChest extends TileEntityAdapter implements IInventory, PreUpdateConsumer {
   public ItemStack[] itemStackArray = new ItemStack[27];
   public boolean enabled2;
   public TileEntityChest tileEntityChest2;
   public TileEntityChest tileEntityChest3;
   public TileEntityChest tileEntityChest;
   public TileEntityChest tileEntityChest1;
   public float factor;
   public float factor2;
   public int count3;
   public int count4;
   public int count2;
   public String labelText;

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      if (this.world == null) {
         return false;
      } else {
         Block block = this.world.getBlockState(blockPos).getBlock();
         return block instanceof BlockTrappedChest && ((BlockTrappedChest)block).count == this.getCountAsIndex();
      }
   }

   @Override
   public ItemStack getItemStack(int count, int count5) {
      if (this.itemStackArray[count] != null) {
         if (this.itemStackArray[count].animationsToGo <= count5) {
            ItemStack stack = this.itemStackArray[count];
            this.itemStackArray[count] = null;
            this.processAction5();
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count].resolveItemStack(count5);
            if (this.itemStackArray[count].animationsToGo == 0) {
               this.itemStackArray[count] = null;
            }

            this.processAction5();
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public int processAction(int count) {
      return 0;
   }

   @Override
   public void processAction(EntityPlayer player) {
      if (!player.isActive23() && this.getBlock() instanceof BlockTrappedChest) {
         this.count3--;
         this.world.applyBlockPosByBlockPos(this.blockPos, this.getBlock(), 1, this.count3);
         this.world.applyBlockPosByBlockPos(this.blockPos, this.getBlock());
         this.world.applyBlockPosByBlockPos(this.blockPos.down(), this.getBlock());
      }
   }

   public void updateState6() {
      if (!this.enabled2) {
         this.enabled2 = true;
         this.tileEntityChest = this.resolveTileEntityChest(EnumFacing.WEST);
         this.tileEntityChest3 = this.resolveTileEntityChest(EnumFacing.EAST);
         this.tileEntityChest2 = this.resolveTileEntityChest(EnumFacing.NORTH);
         this.tileEntityChest1 = this.resolveTileEntityChest(EnumFacing.SOUTH);
      }
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      this.itemStackArray[count] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }

      this.processAction5();
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      if (this.itemStackArray[count] != null) {
         ItemStack stack = this.itemStackArray[count];
         this.itemStackArray[count] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.chest";
   }

   public int getCountAsIndex() {
      if (this.count2 == -1) {
         if (this.world == null || !(this.getBlock() instanceof BlockTrappedChest)) {
            return 0;
         }

         this.count2 = ((BlockTrappedChest)this.getBlock()).count;
      }

      return this.count2;
   }

   @Override
   public void updateState3() {
      super.updateState3();
      this.updateState4();
      this.updateState6();
   }

   public TileEntityChest resolveTileEntityChest(EnumFacing enumFacing) {
      BlockPos blockPos = this.blockPos.offset(enumFacing);
      if (this.matchesBlockPos(blockPos)) {
         TileEntity tileEntity = this.world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityChest) {
            TileEntityChest tileEntityChest4 = (TileEntityChest)tileEntity;
            tileEntityChest4.applyTileEntityChest(this, enumFacing.getEnumFacing5());
            return tileEntityChest4;
         }
      }

      return null;
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new AdvancedContainerAdapter(inventoryPlayer, this, player);
   }

   @Override
   public int processAction3() {
      return 0;
   }

   private void applyTileEntityChest(TileEntityChest tileEntityChest4, EnumFacing enumFacing) {
      if (tileEntityChest4.isEnabled()) {
         this.enabled2 = false;
      } else if (this.enabled2) {
         switch (SmoothDirector.countArray[enumFacing.ordinal()]) {
            case 1:
               if (this.tileEntityChest2 != tileEntityChest4) {
                  this.enabled2 = false;
               }
               break;
            case 2:
               if (this.tileEntityChest1 != tileEntityChest4) {
                  this.enabled2 = false;
               }
               break;
            case 3:
               if (this.tileEntityChest3 != tileEntityChest4) {
                  this.enabled2 = false;
               }
               break;
            case 4:
               if (this.tileEntityChest != tileEntityChest4) {
                  this.enabled2 = false;
               }
         }
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   @Override
   public boolean checkIndex(int count, int count5) {
      if (count == 1) {
         this.count3 = count5;
         return true;
      } else {
         return super.checkIndex(count, count5);
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         int count = nBTTagCompound.computeByteCode("Slot") & 255;
         if (count >= 0 && count < this.itemStackArray.length) {
            this.itemStackArray[count] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }
   }

   @Override
   public void updateState() {
      this.updateState6();
      int coordX = this.blockPos.getX();
      int coordY = this.blockPos.getY();
      int coordZ = this.blockPos.getZ();
      this.count4++;
      if (!this.world.state && this.count3 != 0 && (this.count4 + coordX + coordY + coordZ) % 200 == 0) {
         this.count3 = 0;
         float factor3 = 5.0F;

         for (EntityPlayer player : (Iterable<EntityPlayer>)this.world
            .resolveList(
               EntityPlayer.class,
               new AxisAlignedBB(
                  (double)((float)coordX - factor3),
                  (double)((float)coordY - factor3),
                  (double)((float)coordZ - factor3),
                  (double)((float)(coordX + 1) + factor3),
                  (double)((float)(coordY + 1) + factor3),
                  (double)((float)(coordZ + 1) + factor3)
               )
            )) {
            if (player.container2 instanceof AdvancedContainerAdapter) {
               IInventory iInventory = ((AdvancedContainerAdapter)player.container2).getIInventory();
               if (iInventory == this || iInventory instanceof DistanceBuilder && ((DistanceBuilder)iInventory).matchesIInventory(this)) {
                  this.count3++;
               }
            }
         }
      }

      this.factor2 = this.factor;
      float factor4 = 0.1F;
      if (this.count3 > 0 && this.factor == 0.0F && this.tileEntityChest2 == null && this.tileEntityChest == null) {
         double offset = (double)coordX + 0.5;
         double offset2 = (double)coordZ + 0.5;
         if (this.tileEntityChest1 != null) {
            offset2 += 0.5;
         }

         if (this.tileEntityChest3 != null) {
            offset += 0.5;
         }

         this.world.applyText(offset, (double)coordY + 0.5, offset2, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
      }

      if (this.count3 == 0 && this.factor > 0.0F || this.count3 > 0 && this.factor < 1.0F) {
         float factor5 = this.factor;
         if (this.count3 > 0) {
            this.factor += factor4;
         } else {
            this.factor -= factor4;
         }

         if (this.factor > 1.0F) {
            this.factor = 1.0F;
         }

         float factor6 = 0.5F;
         if (this.factor < factor6 && factor5 >= factor6 && this.tileEntityChest2 == null && this.tileEntityChest == null) {
            double offset3 = (double)coordX + 0.5;
            double offset4 = (double)coordZ + 0.5;
            if (this.tileEntityChest1 != null) {
               offset4 += 0.5;
            }

            if (this.tileEntityChest3 != null) {
               offset3 += 0.5;
            }

            this.world.applyText(offset3, (double)coordY + 0.5, offset4, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
         }

         if (this.factor < 0.0F) {
            this.factor = 0.0F;
         }
      }
   }

   @Override
   public void processAction(int count, int count5) {
   }

   @Override
   public void processAction2(EntityPlayer player) {
      if (!player.isActive23()) {
         if (this.count3 < 0) {
            this.count3 = 0;
         }

         this.count3++;
         this.world.applyBlockPosByBlockPos(this.blockPos, this.getBlock(), 1, this.count3);
         this.world.applyBlockPosByBlockPos(this.blockPos, this.getBlock());
         this.world.applyBlockPosByBlockPos(this.blockPos.down(), this.getBlock());
      }
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:chest";
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.enabled2 = false;
   }

   @Override
   public int processAction4() {
      return 27;
   }

   @Override
   public ItemStack processAction3(int count) {
      return this.itemStackArray[count];
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.itemStackArray.length; index++) {
         if (this.itemStackArray[index] != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            tagCompound2.put("Slot", (byte)index);
            this.itemStackArray[index].resolveNBTTagCompound(tagCompound2);
            nBTTagList.applyLISTManager(tagCompound2);
         }
      }

      tagCompound.applyText("Items", nBTTagList);
      if (this.processAction()) {
         tagCompound.put("CustomName", this.labelText);
      }
   }

   public TileEntityChest() {
      this.count2 = -1;
   }

   public TileEntityChest(int count) {
      this.count2 = count;
   }
}
