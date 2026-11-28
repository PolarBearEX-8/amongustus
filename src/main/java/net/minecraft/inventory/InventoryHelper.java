package net.minecraft.inventory;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InventoryHelper {
   private static final Random RANDOM = new Random();

   public static void dropInventoryItems(World world, BlockPos pos, IInventory inventory) {
      dropInventoryItems(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), inventory);
   }

   private static void spawnItemStack(World world, double d, double d2, double d3, ItemStack stack) {
      float f = RANDOM.nextFloat() * 0.8F + 0.1F;
      float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;
      float f3 = RANDOM.nextFloat() * 0.8F + 0.1F;

      while (stack.stackSize > 0) {
         int n = RANDOM.nextInt(21) + 10;
         if (n > stack.stackSize) {
            n = stack.stackSize;
         }

         stack.stackSize -= n;
         EntityItem entityItem = new EntityItem(
            world, d + (double)f, d2 + (double)f2, d3 + (double)f3, new ItemStack(stack.getItem(), n, stack.getMetadata())
         );
         if (stack.hasTagCompound()) {
            entityItem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
         }

         float f4 = 0.05F;
         entityItem.motionX = RANDOM.nextGaussian() * (double)f4;
         entityItem.motionY = RANDOM.nextGaussian() * (double)f4 + 0.2F;
         entityItem.motionZ = RANDOM.nextGaussian() * (double)f4;
         world.spawnEntityInWorld(entityItem);
      }
   }

   private static void dropInventoryItems(World world, double d, double d2, double d3, IInventory inventory) {
      for (int i = 0; i < inventory.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventory.getStackInSlot(i);
         if (stackInSlot != null) {
            spawnItemStack(world, d, d2, d3, stackInSlot);
         }
      }
   }

   public static void dropInventoryItems2(World world, Entity entity, IInventory inventory) {
      dropInventoryItems(world, entity.posX, entity.posY, entity.posZ, inventory);
   }
}
