package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.AngleMatcher;
import client.util.AuxiliaryIInventoryExtension;
import client.util.SecondaryContainerAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLitFurnace;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.IronRing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGoldenHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class TileEntityFurnace extends TileEntityAdapter implements AuxiliaryIInventoryExtension, PreUpdateConsumer {
   public static final int[] countArray2 = new int[]{0};
   public static final int[] countArray = new int[]{2, 1};
   public static final int[] countArray3 = new int[]{1};
   public ItemStack[] itemStackArray = new ItemStack[3];
   public int count3;
   public int count4;
   public int count5;
   public int count2;
   public String labelText;

   @Override
   public int processAction(int count) {
      switch (count) {
         case 0:
            return this.count3;
         case 1:
            return this.count4;
         case 2:
            return this.count5;
         case 3:
            return this.count2;
         default:
            return 0;
      }
   }

   @Override
   public ItemStack getItemStack(int count, int count6) {
      if (this.itemStackArray[count] != null) {
         if (this.itemStackArray[count].animationsToGo <= count6) {
            ItemStack stack = this.itemStackArray[count];
            this.itemStackArray[count] = null;
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count].resolveItemStack(count6);
            if (this.itemStackArray[count].animationsToGo == 0) {
               this.itemStackArray[count] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.furnace";
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
   public void processAction(int count, ItemStack stack) {
      boolean flag = stack != null && stack.matchesItemStack(this.itemStackArray[count]) && ItemStack.matchesItemStack2(stack, this.itemStackArray[count]);
      this.itemStackArray[count] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }

      if (count == 0 && !flag) {
         this.count2 = this.computeCount(stack);
         this.count5 = 0;
         this.processAction5();
      }
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public static boolean matchesItemStack(ItemStack stack) {
      return computeCountByItemStack(stack) > 0;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   private boolean isActive3() {
      if (this.itemStackArray[0] == null) {
         return false;
      } else {
         ItemStack stack = IronRing.getIronRing().resolveItemStack(this.itemStackArray[0]);
         return stack == null
            ? false
            : (
               this.itemStackArray[2] == null
                  ? true
                  : (
                     !this.itemStackArray[2].matchesItemStack(stack)
                        ? false
                        : (
                           this.itemStackArray[2].animationsToGo < this.processAction2()
                                 && this.itemStackArray[2].animationsToGo < this.itemStackArray[2].processAction5()
                              ? true
                              : this.itemStackArray[2].animationsToGo < stack.processAction5()
                        )
                  )
            );
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return count == 2 ? false : (count != 1 ? true : matchesItemStack(stack) || AngleMatcher.matchesItemStack(stack));
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new SecondaryContainerAdapter(inventoryPlayer, this);
   }

   @Override
   public boolean matchesItemStackByItemStack(int count, ItemStack stack, EnumFacing enumFacing) {
      if (enumFacing == EnumFacing.DOWN && count == 1) {
         Item item = stack.getItem();
         if (item != Items.waterBucket && item != Items.bucket) {
            return false;
         }
      }

      return true;
   }

   @Override
   public int processAction3() {
      return 4;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   public void updateState6() {
      if (this.isActive3()) {
         ItemStack stack = IronRing.getIronRing().resolveItemStack(this.itemStackArray[0]);
         if (this.itemStackArray[2] == null) {
            this.itemStackArray[2] = stack.getItemStack();
         } else if (this.itemStackArray[2].getItem() == stack.getItem()) {
            this.itemStackArray[2].animationsToGo++;
         }

         if (this.itemStackArray[0].getItem() == Item.processAction2(Blocks.block11)
            && this.itemStackArray[0].getIndexOffset() == 1
            && this.itemStackArray[1] != null
            && this.itemStackArray[1].getItem() == Items.bucket) {
            this.itemStackArray[1] = new ItemStack(Items.waterBucket);
         }

         this.itemStackArray[0].animationsToGo--;
         if (this.itemStackArray[0].animationsToGo <= 0) {
            this.itemStackArray[0] = null;
         }
      }
   }

   public static boolean matchesIInventory(IInventory iInventory) {
      return iInventory.processAction(0) > 0;
   }

   @Override
   public int[] resolveIndexArray(EnumFacing enumFacing) {
      return enumFacing == EnumFacing.DOWN ? countArray : (enumFacing == EnumFacing.UP ? countArray2 : countArray3);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         byte byteValue = nBTTagCompound.computeByteCode("Slot");
         if (byteValue >= 0 && byteValue < this.itemStackArray.length) {
            this.itemStackArray[byteValue] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }

      this.count3 = tagCompound.processValue("BurnTime");
      this.count5 = tagCompound.processValue("CookTime");
      this.count2 = tagCompound.processValue("CookTimeTotal");
      this.count4 = computeCountByItemStack(this.itemStackArray[1]);
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack, EnumFacing enumFacing) {
      return this.processAction2(count, stack);
   }

   @Override
   public void updateState() {
      boolean active = this.isActive2();
      boolean flag = false;
      if (this.isActive2()) {
         this.count3--;
      }

      if (!this.world.state) {
         if (this.isActive2() || this.itemStackArray[1] != null && this.itemStackArray[0] != null) {
            if (!this.isActive2() && this.isActive3()) {
               this.count4 = this.count3 = computeCountByItemStack(this.itemStackArray[1]);
               if (this.isActive2()) {
                  flag = true;
                  if (this.itemStackArray[1] != null) {
                     this.itemStackArray[1].animationsToGo--;
                     if (this.itemStackArray[1].animationsToGo == 0) {
                        Item item = this.itemStackArray[1].getItem().getItem();
                        this.itemStackArray[1] = item != null ? new ItemStack(item) : null;
                     }
                  }
               }
            }

            if (this.isActive2() && this.isActive3()) {
               this.count5++;
               if (this.count5 == this.count2) {
                  this.count5 = 0;
                  this.count2 = this.computeCount(this.itemStackArray[0]);
                  this.updateState6();
                  flag = true;
               }
            } else {
               this.count5 = 0;
            }
         } else if (!this.isActive2() && this.count5 > 0) {
            this.count5 = MathHelper.getInt12(this.count5 - 2, 0, this.count2);
         }

         if (active != this.isActive2()) {
            flag = true;
            BlockLitFurnace.applyWorldByWorld(this.isActive2(), this.world, this.blockPos);
         }
      }

      if (flag) {
         this.processAction5();
      }
   }

   public boolean isActive2() {
      return this.count3 > 0;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:furnace";
   }

   @Override
   public void processAction(int count, int count6) {
      switch (count) {
         case 0:
            this.count3 = count6;
            break;
         case 1:
            this.count4 = count6;
            break;
         case 2:
            this.count5 = count6;
            break;
         case 3:
            this.count2 = count6;
      }
   }

   public static int computeCountByItemStack(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock && Block.resolveBlock(item) != Blocks.air) {
            Block block = Block.resolveBlock(item);
            if (block == Blocks.extraBlockAdapter2) {
               return 150;
            }

            if (block.getMaterial() == Material.material) {
               return 300;
            }

            if (block == Blocks.block9) {
               return 16000;
            }
         }

         return item instanceof ItemTool && ((ItemTool)item).getText().equals("WOOD")
            ? 200
            : (
               item instanceof ItemSword && ((ItemSword)item).getText().equals("WOOD")
                  ? 200
                  : (
                     item instanceof ItemGoldenHoe && ((ItemGoldenHoe)item).getText().equals("WOOD")
                        ? 200
                        : (
                           item == Items.stick
                              ? 100
                              : (
                                 item == Items.coal
                                    ? 1600
                                    : (
                                       item == Items.lavaBucket
                                          ? 20000
                                          : (item == Item.processAction2(Blocks.block83) ? 100 : (item == Items.blazeRod ? 2400 : 0))
                                    )
                              )
                        )
                  )
            );
      }
   }

   @Override
   public int processAction4() {
      return this.itemStackArray.length;
   }

   public int computeCount(ItemStack stack) {
      return 200;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("BurnTime", (short)this.count3);
      tagCompound.put("CookTime", (short)this.count5);
      tagCompound.put("CookTimeTotal", (short)this.count2);
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

   @Override
   public ItemStack processAction3(int count) {
      return this.itemStackArray[count];
   }
}
