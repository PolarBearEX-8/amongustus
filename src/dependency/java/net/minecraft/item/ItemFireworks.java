package net.minecraft.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFireworks extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         EntityFireworksrocketentity entityFireworksrocketentity = new EntityFireworksrocketentity(
            world, (double)((float)blockPos.getX() + posX), (double)((float)blockPos.getY() + posY), (double)((float)blockPos.getZ() + posZ), stack
         );
         world.matchesEntity(entityFireworksrocketentity);
         if (!player.invulnerableManager.enabled2) {
            stack.animationsToGo--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.canExecute()) {
         NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("Fireworks");
         if (nBTTagCompound != null) {
            if (nBTTagCompound.matchesText("Flight", 99)) {
               list.add(I18n.getString2("item.fireworks.flight") + " " + nBTTagCompound.computeByteCode("Flight"));
            }

            NBTTagList extendedLoggerEntry = nBTTagCompound.getExtendedLoggerEntry("Explosions", 10);
            if (extendedLoggerEntry != null && extendedLoggerEntry.getInt() > 0) {
               for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
                  NBTTagCompound nBTTagCompound2 = extendedLoggerEntry.getNBTTagCompound(index);
                  ArrayList list2 = Lists.newArrayList();
                  ItemFireworkCharge.applyNBTTagCompound(nBTTagCompound2, list2);
                  if (list2.size() > 0) {
                     for (int index2 = 1; index2 < list2.size(); index2++) {
                        list2.set(index2, "  " + (String)list2.get(index2));
                     }

                     list.addAll(list2);
                  }
               }
            }
         }
      }
   }
}
