package net.minecraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityChest extends TileEntityLockable implements IInventory, ITickable {
   private ItemStack[] chestContents = new ItemStack[27];
   public boolean adjacentChestChecked;
   public TileEntityChest adjacentChestZNeg;
   public TileEntityChest adjacentChestXPos;
   public TileEntityChest adjacentChestXNeg;
   public TileEntityChest adjacentChestZPos;
   public float lidAngle;
   public float prevLidAngle;
   public int numPlayersUsing;
   private int ticksSinceSync;
   private int cachedChestType;
   public String customName;

   private boolean isChestAt(BlockPos pos2) {
      if (this.worldObj == null) {
         return false;
      } else {
         Block block = this.worldObj.getBlockState(pos2).getBlock();
         return block instanceof BlockChest && ((BlockChest)block).chestType == this.getChestType();
      }
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public boolean receiveClientEvent(int n, int n2) {
      if (n == 1) {
         this.numPlayersUsing = n2;
         return true;
      } else {
         return super.receiveClientEvent(n, n2);
      }
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public int getSizeInventory() {
      return 27;
   }

   public void checkForAdjacentChests() {
      if (!this.adjacentChestChecked) {
         this.adjacentChestChecked = true;
         this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
         this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
         this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
         this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
      }
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.chest";
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.chestContents[n] != null) {
         ItemStack stack = this.chestContents[n];
         this.chestContents[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.chestContents[n];
   }

   @Override
   public String getGuiID() {
      return "minecraft:chest";
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.chestContents = new ItemStack[this.getSizeInventory()];
      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         int n = compoundTagAt.getByte("Slot") & 255;
         if (n >= 0 && n < this.chestContents.length) {
            this.chestContents[n] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }
   }

   public TileEntityChest() {
      this.cachedChestType = -1;
   }

   @Override
   public void closeInventory(EntityPlayer player) {
      if (!player.isSpectator() && this.getBlockType() instanceof BlockChest) {
         this.numPlayersUsing--;
         this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
         this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
      }
   }

   public int getChestType() {
      if (this.cachedChestType == -1) {
         if (this.worldObj == null || !(this.getBlockType() instanceof BlockChest)) {
            return 0;
         }

         this.cachedChestType = ((BlockChest)this.getBlockType()).chestType;
      }

      return this.cachedChestType;
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   public TileEntityChest(int n) {
      this.cachedChestType = n;
   }

   public void setCustomName(String str) {
      this.customName = str;
   }

   @Override
   public void update() {
      this.checkForAdjacentChests();
      int x = this.pos.getX();
      int y = this.pos.getY();
      int z = this.pos.getZ();
      this.ticksSinceSync++;
      if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + x + y + z) % 200 == 0) {
         this.numPlayersUsing = 0;
         float f = 5.0F;

         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.worldObj
            .getEntitiesWithinAABB(
               EntityPlayer.class,
               new AxisAlignedBB(
                  (double)((float)x - f),
                  (double)((float)y - f),
                  (double)((float)z - f),
                  (double)((float)(x + 1) + f),
                  (double)((float)(y + 1) + f),
                  (double)((float)(z + 1) + f)
               )
            ))) {
            if (player.openContainer instanceof ContainerChest) {
               IInventory lowerChestInventory = ((ContainerChest)player.openContainer).getLowerChestInventory();
               if (lowerChestInventory == this || lowerChestInventory instanceof InventoryLargeChest && ((InventoryLargeChest)lowerChestInventory).isPartOfLargeChest(this)) {
                  this.numPlayersUsing++;
               }
            }
         }
      }

      this.prevLidAngle = this.lidAngle;
      float f2 = 0.1F;
      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
         double d = (double)x + 0.5;
         double d2 = (double)z + 0.5;
         if (this.adjacentChestZPos != null) {
            d2 += 0.5;
         }

         if (this.adjacentChestXPos != null) {
            d += 0.5;
         }

         this.worldObj.playSoundEffect(d, (double)y + 0.5, d2, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float lidAngle2 = this.lidAngle;
         if (this.numPlayersUsing > 0) {
            this.lidAngle += f2;
         } else {
            this.lidAngle -= f2;
         }

         if (this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float f3 = 0.5F;
         if (this.lidAngle < f3 && lidAngle2 >= f3 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
            double d3 = (double)x + 0.5;
            double d4 = (double)z + 0.5;
            if (this.adjacentChestZPos != null) {
               d4 += 0.5;
            }

            if (this.adjacentChestXPos != null) {
               d3 += 0.5;
            }

            this.worldObj.playSoundEffect(d3, (double)y + 0.5, d4, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
         }

         if (this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }
   }

   @Override
   public void updateContainingBlockInfo() {
      super.updateContainingBlockInfo();
      this.adjacentChestChecked = false;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.chestContents.length; i++) {
         if (this.chestContents[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.customName);
      }
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
      this.checkForAdjacentChests();
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.chestContents.length; i++) {
         this.chestContents[i] = null;
      }
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public void openInventory(EntityPlayer player) {
      if (!player.isSpectator()) {
         if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         this.numPlayersUsing++;
         this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
         this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
      }
   }

   public TileEntityChest getAdjacentChest(EnumFacing facing) {
      BlockPos pos2 = this.pos.offset(facing);
      if (this.isChestAt(pos2)) {
         TileEntity tileEntity = this.worldObj.getTileEntity(pos2);
         if (tileEntity instanceof TileEntityChest) {
            TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
            tileEntityChest.validateAdjacentChest(this, facing.getOpposite());
            return tileEntityChest;
         }
      }

      return null;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerChest(inventoryPlayer, this, player);
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.chestContents[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   private void validateAdjacentChest(TileEntityChest tileEntityChest, EnumFacing facing) {
      if (tileEntityChest.isInvalid()) {
         this.adjacentChestChecked = false;
      } else if (this.adjacentChestChecked) {
         switch (facing) {
            case NORTH:
               if (this.adjacentChestZNeg != tileEntityChest) {
                  this.adjacentChestChecked = false;
               }
               break;
            case SOUTH:
               if (this.adjacentChestZPos != tileEntityChest) {
                  this.adjacentChestChecked = false;
               }
               break;
            case EAST:
               if (this.adjacentChestXPos != tileEntityChest) {
                  this.adjacentChestChecked = false;
               }
               break;
            case WEST:
               if (this.adjacentChestXNeg != tileEntityChest) {
                  this.adjacentChestChecked = false;
               }
         }
      }
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.chestContents[n] != null) {
         if (this.chestContents[n].stackSize <= n2) {
            ItemStack stack = this.chestContents[n];
            this.chestContents[n] = null;
            this.markDirty();
            return stack;
         } else {
            ItemStack stack2 = this.chestContents[n].splitStack(n2);
            if (this.chestContents[n].stackSize == 0) {
               this.chestContents[n] = null;
            }

            this.markDirty();
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

}
