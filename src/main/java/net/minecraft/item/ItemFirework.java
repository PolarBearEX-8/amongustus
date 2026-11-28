package net.minecraft.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFirework extends Item {

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         world.spawnEntityInWorld(new EntityFireworkRocket(
            world, (double)((float)pos.getX() + f), (double)((float)pos.getY() + f2), (double)((float)pos.getZ() + f3), stack
         ));
         if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.hasTagCompound()) {
         NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("Fireworks");
         if (compoundTag != null) {
            if (compoundTag.hasKey("Flight", 99)) {
               list.add(StatCollector.translateToLocal("item.fireworks.flight") + " " + compoundTag.getByte("Flight"));
            }

            NBTTagList tagList = compoundTag.getTagList("Explosions", 10);
            if (tagList != null && tagList.tagCount() > 0) {
               for (int i = 0; i < tagList.tagCount(); i++) {
                  NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
                  ArrayList list2 = Lists.newArrayList();
                  ItemFireworkCharge.addExplosionInfo(compoundTagAt, list2);
                  if (list2.size() > 0) {
                     for (int j = 1; j < list2.size(); j++) {
                        list2.set(j, "  " + (String)list2.get(j));
                     }

                     list.addAll(list2);
                  }
               }
            }
         }
      }
   }
}
