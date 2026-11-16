package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.AuxiliaryIInventoryExtension;
import client.util.InternalIInventoryExtension;
import client.util.InterpolationPipeline;
import client.util.LivingEntityFilter;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntityAdapter implements PreUpdateConsumer, InternalIInventoryExtension {
   public ItemStack[] itemStackArray = new ItemStack[5];
   public String labelText;
   public int count2 = -1;

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   @Override
   public ItemStack getItemStack(int count, int count3) {
      if (this.itemStackArray[count] != null) {
         if (this.itemStackArray[count].animationsToGo <= count3) {
            ItemStack stack = this.itemStackArray[count];
            this.itemStackArray[count] = null;
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count].resolveItemStack(count3);
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
   public int processAction(int count) {
      return 0;
   }

   @Override
   public double getValue27() {
      return (double)this.blockPos.getZ() + 0.5;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   private static boolean matchesInternalIInventoryExtensio(InternalIInventoryExtension internalIInventoryExtension, IInventory iInventory, int count, EnumFacing enumFacing) {
      ItemStack stack = iInventory.processAction3(count);
      if (stack != null && matchesIInventory(iInventory, stack, count, enumFacing)) {
         ItemStack stack2 = stack.getItemStack();
         ItemStack stack3 = resolveItemStack(internalIInventoryExtension, iInventory.getItemStack(count, 1), (EnumFacing)null);
         if (stack3 == null || stack3.animationsToGo == 0) {
            iInventory.processAction5();
            return true;
         }

         iInventory.processAction(count, stack2);
      }

      return false;
   }

   public static IInventory resolveIInventory(InternalIInventoryExtension internalIInventoryExtension) {
      return resolveIInventory(internalIInventoryExtension.getWorld3(), internalIInventoryExtension.getValue28(), internalIInventoryExtension.getValue29() + 1.0, internalIInventoryExtension.getValue27());
   }

   private static boolean matchesIInventory(IInventory iInventory, ItemStack stack, int count, EnumFacing enumFacing) {
      return !(iInventory instanceof AuxiliaryIInventoryExtension) || ((AuxiliaryIInventoryExtension)iInventory).matchesItemStackByItemStack(count, stack, enumFacing);
   }

   public static IInventory resolveIInventory(World world, double posX, double posY, double posZ) {
      Object entry = null;
      int count = MathHelper.ceiling_double_int(posX);
      int count3 = MathHelper.ceiling_double_int(posY);
      int count4 = MathHelper.ceiling_double_int(posZ);
      BlockPos blockPos = new BlockPos(count, count3, count4);
      Block block = world.getBlockState(blockPos).getBlock();
      if (block.isState8()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof IInventory) {
            entry = (IInventory)tileEntity;
            if (entry instanceof TileEntityChest && block instanceof BlockTrappedChest) {
               entry = ((BlockTrappedChest)block).resolveIInventoryExtension(world, blockPos);
            }
         }
      }

      if (entry == null) {
         List entitiesInAABBexcluding = world.getEntitiesInAABBexcluding(
            (Entity)null, new AxisAlignedBB(posX - 0.5, posY - 0.5, posZ - 0.5, posX + 0.5, posY + 0.5, posZ + 0.5), LivingEntityFilter.predicate3
         );
         if (entitiesInAABBexcluding.size() > 0) {
            entry = (IInventory)entitiesInAABBexcluding.get(world.random.nextInt(entitiesInAABBexcluding.size()));
         }
      }

      return (IInventory)entry;
   }

   private boolean isActive2() {
      IInventory iInventory = this.getIInventory();
      if (iInventory == null) {
         return false;
      } else {
         EnumFacing enumFacing = BlockHopper.resolveEnumFacing(this.getCount()).getEnumFacing5();
         if (this.matchesIInventory(iInventory, enumFacing)) {
            return false;
         } else {
            for (int index = 0; index < this.processAction4(); index++) {
               if (this.processAction3(index) != null) {
                  ItemStack stack = this.processAction3(index).getItemStack();
                  ItemStack stack2 = resolveItemStack(iInventory, this.getItemStack(index, 1), enumFacing);
                  if (stack2 == null || stack2.animationsToGo == 0) {
                     iInventory.processAction5();
                     return true;
                  }

                  this.processAction(index, stack);
               }
            }

            return false;
         }
      }
   }

   private static ItemStack resolveItemStack(IInventory iInventory, ItemStack stack, int count, EnumFacing enumFacing) {
      ItemStack stack2 = stack;
      ItemStack stack3 = iInventory.processAction3(count);
      if (matchesIInventoryByIInventory(iInventory, stack, count, enumFacing)) {
         boolean flag = false;
         if (stack3 == null) {
            iInventory.processAction(count, stack);
            stack2 = null;
            flag = true;
         } else if (matchesItemStack(stack3, stack)) {
            int count3 = stack.processAction5() - stack3.animationsToGo;
            int count4 = Math.min(stack.animationsToGo, count3);
            stack.animationsToGo -= count4;
            stack3.animationsToGo += count4;
            flag = count4 > 0;
         }

         if (flag) {
            if (iInventory instanceof TileEntityHopper) {
               TileEntityHopper tileEntityHopper = (TileEntityHopper)iInventory;
               if (tileEntityHopper.isActive6()) {
                  tileEntityHopper.setCount2(8);
               }

               iInventory.processAction5();
            }

            iInventory.processAction5();
         }
      }

      return stack2;
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.hopper";
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      this.itemStackArray[count] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }
   }

   @Override
   public World getWorld3() {
      return this.getWorld();
   }

   private IInventory getIInventory() {
      EnumFacing enumFacing = BlockHopper.resolveEnumFacing(this.getCount());
      return resolveIInventory(
         this.getWorld3(),
         (double)(this.blockPos.getX() + enumFacing.getInt2()),
         (double)(this.blockPos.getY() + enumFacing.getInt3()),
         (double)(this.blockPos.getZ() + enumFacing.getInt())
      );
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

   public static ItemStack resolveItemStack(IInventory iInventory, ItemStack stack, EnumFacing enumFacing) {
      ItemStack stack2 = stack;
      if (iInventory instanceof AuxiliaryIInventoryExtension && enumFacing != null) {
         int[] countArray = ((AuxiliaryIInventoryExtension)iInventory).resolveIndexArray(enumFacing);

         for (int index = 0; index < countArray.length && stack2 != null && stack2.animationsToGo > 0; index++) {
            stack2 = resolveItemStack(iInventory, stack2, countArray[index], enumFacing);
         }
      } else {
         int count = iInventory.processAction4();

         for (int index2 = 0; index2 < count && stack2 != null && stack2.animationsToGo > 0; index2++) {
            stack2 = resolveItemStack(iInventory, stack2, index2, enumFacing);
         }
      }

      if (stack2 != null && stack2.animationsToGo == 0) {
         stack2 = null;
      }

      return stack2;
   }

   public static boolean matchesIInventory(IInventory iInventory, EntityItem entityItem) {
      boolean flag = false;
      if (entityItem == null) {
         return false;
      } else {
         ItemStack stack = entityItem.getItemStack().getItemStack();
         ItemStack stack2 = resolveItemStack(iInventory, stack, (EnumFacing)null);
         if (stack2 != null && stack2.animationsToGo != 0) {
            entityItem.applyItemStack(stack2);
         } else {
            flag = true;
            entityItem.updateState9();
         }

         return flag;
      }
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public int processAction3() {
      return 0;
   }

   public static boolean matchesInternalIInventoryExtensio(InternalIInventoryExtension internalIInventoryExtension) {
      IInventory iInventory = resolveIInventory(internalIInventoryExtension);
      if (iInventory != null) {
         EnumFacing enumFacing = EnumFacing.DOWN;
         if (matchesIInventoryByIInventory(iInventory, enumFacing)) {
            return false;
         }

         if (iInventory instanceof AuxiliaryIInventoryExtension) {
            int[] countArray = ((AuxiliaryIInventoryExtension)iInventory).resolveIndexArray(enumFacing);

            for (int index = 0; index < countArray.length; index++) {
               if (matchesInternalIInventoryExtensio(internalIInventoryExtension, iInventory, countArray[index], enumFacing)) {
                  return true;
               }
            }
         } else {
            int count = iInventory.processAction4();

            for (int index2 = 0; index2 < count; index2++) {
               if (matchesInternalIInventoryExtensio(internalIInventoryExtension, iInventory, index2, enumFacing)) {
                  return true;
               }
            }
         }
      } else {
         for (EntityItem entityItem : (Iterable<EntityItem>) (resolveListByWorld(internalIInventoryExtension.getWorld3(), internalIInventoryExtension.getValue28(), internalIInventoryExtension.getValue29() + 1.0, internalIInventoryExtension.getValue27()))) {
            if (matchesIInventory(internalIInventoryExtension, entityItem)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isActive7() {
      ItemStack[] itemStackArray2 = this.itemStackArray;
      int length = itemStackArray2.length;

      for (int index = 0; index < length; index++) {
         if (itemStackArray2[index] != null) {
            return false;
         }
      }

      return true;
   }

   private static boolean matchesItemStack(ItemStack stack, ItemStack stack2) {
      return stack.getItem() != stack2.getItem()
         ? false
         : (
            stack.getIndexOffset() != stack2.getIndexOffset()
               ? false
               : (stack.animationsToGo > stack.processAction5() ? false : ItemStack.matchesItemStack2(stack, stack2))
         );
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new InterpolationPipeline(inventoryPlayer, this, player);
   }

   @Override
   public double getValue28() {
      return (double)this.blockPos.getX() + 0.5;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }

      this.count2 = tagCompound.computeCount("TransferCooldown");

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         byte byteValue = nBTTagCompound.computeByteCode("Slot");
         if (byteValue >= 0 && byteValue < this.itemStackArray.length) {
            this.itemStackArray[byteValue] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }
   }

   private static boolean matchesIInventoryByIInventory(IInventory iInventory, ItemStack stack, int count, EnumFacing enumFacing) {
      return !iInventory.processAction2(count, stack)
         ? false
         : !(iInventory instanceof AuxiliaryIInventoryExtension) || ((AuxiliaryIInventoryExtension)iInventory).matchesItemStack(count, stack, enumFacing);
   }

   private boolean matchesIInventory(IInventory iInventory, EnumFacing enumFacing) {
      if (iInventory instanceof AuxiliaryIInventoryExtension) {
         AuxiliaryIInventoryExtension auxiliaryIInventoryExtension = (AuxiliaryIInventoryExtension)iInventory;
         int[] countArray = auxiliaryIInventoryExtension.resolveIndexArray(enumFacing);

         for (int index = 0; index < countArray.length; index++) {
            ItemStack stack = auxiliaryIInventoryExtension.processAction3(countArray[index]);
            if (stack == null || stack.animationsToGo != stack.processAction5()) {
               return false;
            }
         }
      } else {
         int count = iInventory.processAction4();

         for (int index2 = 0; index2 < count; index2++) {
            ItemStack stack2 = iInventory.processAction3(index2);
            if (stack2 == null || stack2.animationsToGo != stack2.processAction5()) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean isActive6() {
      return this.count2 <= 1;
   }

   @Override
   public void updateState() {
      if (this.world != null && !this.world.state) {
         this.count2--;
         if (!this.isActive5()) {
            this.setCount2(0);
            this.isActive3();
         }
      }
   }

   public static List resolveListByWorld(World world, double posX, double posY, double posZ) {
      return world.resolveList(
         EntityItem.class, new AxisAlignedBB(posX - 0.5, posY - 0.5, posZ - 0.5, posX + 0.5, posY + 0.5, posZ + 0.5), LivingEntityFilter.predicate
      );
   }

   private static boolean matchesIInventoryByIInventory(IInventory iInventory, EnumFacing enumFacing) {
      if (iInventory instanceof AuxiliaryIInventoryExtension) {
         AuxiliaryIInventoryExtension auxiliaryIInventoryExtension = (AuxiliaryIInventoryExtension)iInventory;
         int[] countArray = auxiliaryIInventoryExtension.resolveIndexArray(enumFacing);

         for (int index = 0; index < countArray.length; index++) {
            if (auxiliaryIInventoryExtension.processAction3(countArray[index]) != null) {
               return false;
            }
         }
      } else {
         int count = iInventory.processAction4();

         for (int index2 = 0; index2 < count; index2++) {
            if (iInventory.processAction3(index2) != null) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public void processAction(int count, int count3) {
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:hopper";
   }

   @Override
   public int processAction4() {
      return this.itemStackArray.length;
   }

   @Override
   public double getValue29() {
      return (double)this.blockPos.getY() + 0.5;
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public void processAction5() {
      super.processAction5();
   }

   public void setCount2(int count) {
      this.count2 = count;
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
      tagCompound.put("TransferCooldown", this.count2);
      if (this.processAction()) {
         tagCompound.put("CustomName", this.labelText);
      }
   }

   private boolean isActive4() {
      for (ItemStack stack : this.itemStackArray) {
         if (stack == null || stack.animationsToGo != stack.processAction5()) {
            return false;
         }
      }

      return true;
   }

   public boolean isActive3() {
      if (this.world != null && !this.world.state) {
         if (!this.isActive5() && BlockHopper.checkIndex(this.getCount())) {
            boolean flag = false;
            if (!this.isActive7()) {
               flag = this.isActive2();
            }

            if (!this.isActive4()) {
               flag = matchesInternalIInventoryExtensio(this) || flag;
            }

            if (flag) {
               this.setCount2(8);
               this.processAction5();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean isActive5() {
      return this.count2 > 0;
   }
}
