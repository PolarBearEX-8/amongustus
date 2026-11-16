package client.util;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class DampenerFilter {
   public static final Random random = new Random();

   private static void applyWorld(World world, double posX, double posY, double posZ, ItemStack stack) {
      float factor = random.nextFloat() * 0.8F + 0.1F;
      float factor2 = random.nextFloat() * 0.8F + 0.1F;
      float factor3 = random.nextFloat() * 0.8F + 0.1F;

      while (stack.animationsToGo > 0) {
         int count = random.nextInt(21) + 10;
         if (count > stack.animationsToGo) {
            count = stack.animationsToGo;
         }

         stack.animationsToGo -= count;
         EntityItem entityItem = new EntityItem(
            world, posX + (double)factor, posY + (double)factor2, posZ + (double)factor3, new ItemStack(stack.getItem(), count, stack.getIndexOffset())
         );
         if (stack.canExecute()) {
            entityItem.getItemStack().processAction((NBTTagCompound)stack.getStackTagCompound().getLISTManager());
         }

         float factor4 = 0.05F;
         entityItem.motionX = random.nextGaussian() * (double)factor4;
         entityItem.motionY = random.nextGaussian() * (double)factor4 + 0.2F;
         entityItem.motionZ = random.nextGaussian() * (double)factor4;
         world.matchesEntity(entityItem);
      }
   }

   public static void applyWorldByWorld(World world, BlockPos blockPos, IInventory iInventory) {
      applyWorld(world, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), iInventory);
   }

   public static void applyWorld(World world, Entity entity, IInventory iInventory) {
      applyWorld(world, entity.posX, entity.posY, entity.posZ, iInventory);
   }

   private static void applyWorld(World world, double posX, double posY, double posZ, IInventory iInventory) {
      for (int index = 0; index < iInventory.processAction4(); index++) {
         ItemStack stack = iInventory.processAction3(index);
         if (stack != null) {
            applyWorld(world, posX, posY, posZ, stack);
         }
      }
   }
}
