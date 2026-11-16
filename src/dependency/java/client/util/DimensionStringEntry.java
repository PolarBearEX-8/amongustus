package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DimensionStringEntry extends AdapterStringEntry {
   public int count2;
   public int count;
   public byte byteFlag;
   public byte byteFlag2;
   public byte[] byteFlagArray = new byte[16384];
   public List list = Lists.newArrayList();
   public Map map2 = Maps.newHashMap();
   public Map map = Maps.newLinkedHashMap();

   public void applyDouble(double offset, double offset2, int count3) {
      int count4 = 128 * (1 << count3);
      int count5 = MathHelper.ceiling_double_int((offset + 64.0) / (double)count4);
      int count6 = MathHelper.ceiling_double_int((offset2 + 64.0) / (double)count4);
      this.count2 = count5 * count4 + count4 / 2 - 64;
      this.count = count6 * count4 + count4 / 2 - 64;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("dimension", this.byteFlag);
      tagCompound.put("xCenter", this.count2);
      tagCompound.put("zCenter", this.count);
      tagCompound.put("scale", this.byteFlag2);
      tagCompound.put("width", (short)128);
      tagCompound.put("height", (short)128);
      tagCompound.put("colors", this.byteFlagArray);
   }

   public Packet resolvePacketByItemStack(ItemStack stack, World world, EntityPlayer player) {
      AimQueue aimQueue = (AimQueue)this.map2.get(player);
      return aimQueue == null ? null : aimQueue.resolvePacket(stack);
   }

   private void applyWorldByWorld(int count3, World world, String text, double posX, double posY, double posZ) {
      int count4 = count3;
      int count5 = 1 << this.byteFlag2;
      float factor = (float)(posX - (double)this.count2) / (float)count5;
      float factor2 = (float)(posY - (double)this.count) / (float)count5;
      byte byteValue = (byte)((int)((double)(factor * 2.0F) + 0.5));
      byte byteValue2 = (byte)((int)((double)(factor2 * 2.0F) + 0.5));
      byte byteValue3;
      if (factor >= (float)-63 && factor2 >= (float)-63 && factor <= (float)63 && factor2 <= (float)63) {
         double offset = posZ + (posZ < 0.0 ? -8.0 : 8.0);
         byteValue3 = (byte)((int)(offset * 16.0 / 360.0));
         if (this.byteFlag < 0) {
            int count6 = (int)(world.getRandomSeedManager().getTimestamp() / 10L);
            byteValue3 = (byte)(count6 * count6 * 34187121 + count6 * 121 >> 15 & 15);
         }
      } else {
         if (Math.abs(factor) >= 320.0F || Math.abs(factor2) >= 320.0F) {
            this.map.remove(text);
            return;
         }

         count4 = 6;
         byteValue3 = 0;
         if (factor <= (float)-63) {
            byteValue = (byte)((int)((double)126 + 2.5));
         }

         if (factor2 <= (float)-63) {
            byteValue2 = (byte)((int)((double)126 + 2.5));
         }

         if (factor >= (float)63) {
            byteValue = 127;
         }

         if (factor2 >= (float)63) {
            byteValue2 = 127;
         }
      }

      this.map.put(text, new ByteEntry((byte)count4, byteValue, byteValue2, byteValue3));
   }

   public void applyEntityPlayer(EntityPlayer player, ItemStack stack) {
      if (!this.map2.containsKey(player)) {
         AimQueue aimQueue = new AimQueue(this, player);
         this.map2.put(player, aimQueue);
         this.list.add(aimQueue);
      }

      if (!player.inventoryPlayer.checkItemStack2(stack)) {
         this.map.remove(player.getText());
      }

      for (int index = 0; index < this.list.size(); index++) {
         AimQueue aimQueue2 = (AimQueue)this.list.get(index);
         if (!aimQueue2.entityPlayer.isDead && (aimQueue2.entityPlayer.inventoryPlayer.checkItemStack2(stack) || stack.isActive4())) {
            if (!stack.isActive4() && aimQueue2.entityPlayer.count4 == this.byteFlag) {
               this.applyWorldByWorld(
                  0,
                  aimQueue2.entityPlayer.worldObj,
                  aimQueue2.entityPlayer.getText(),
                  aimQueue2.entityPlayer.posX,
                  aimQueue2.entityPlayer.posZ,
                  (double)aimQueue2.entityPlayer.rotationYaw
               );
            }
         } else {
            this.map2.remove(aimQueue2.entityPlayer);
            this.list.remove(aimQueue2);
         }
      }

      if (stack.isActive4()) {
         EntityItemframe entityItemframe = stack.getEntityItemframe();
         BlockPos blockPos = entityItemframe.getBlockPos2();
         this.applyWorldByWorld(
            1, player.worldObj, "frame-" + entityItemframe.getEntityId(), (double)blockPos.getX(), (double)blockPos.getZ(), (double)(entityItemframe.enumFacing2.getIndexOffset() * 90)
         );
      }

      if (stack.canExecute() && stack.getStackTagCompound().matchesText("Decorations", 9)) {
         NBTTagList extendedLoggerEntry = stack.getStackTagCompound().getExtendedLoggerEntry("Decorations", 10);

         for (int index2 = 0; index2 < extendedLoggerEntry.getInt(); index2++) {
            NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index2);
            if (!this.map.containsKey(nBTTagCompound.resolveText("id"))) {
               this.applyWorldByWorld(
                  nBTTagCompound.computeByteCode("type"),
                  player.worldObj,
                  nBTTagCompound.resolveText("id"),
                  nBTTagCompound.computeValue("x"),
                  nBTTagCompound.computeValue("z"),
                  nBTTagCompound.computeValue("rot")
               );
            }
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.byteFlag = tagCompound.computeByteCode("dimension");
      this.count2 = tagCompound.computeCount("xCenter");
      this.count = tagCompound.computeCount("zCenter");
      this.byteFlag2 = tagCompound.computeByteCode("scale");
      this.byteFlag2 = (byte)MathHelper.getInt12(this.byteFlag2, 0, 4);
      short shortValue = tagCompound.processValue("width");
      short shortValue2 = tagCompound.processValue("height");
      if (shortValue == 128 && shortValue2 == 128) {
         this.byteFlagArray = tagCompound.resolveByteArray("colors");
      } else {
         byte[] byteValueArray = tagCompound.resolveByteArray("colors");
         this.byteFlagArray = new byte[16384];
         int count3 = (128 - shortValue) / 2;
         int count4 = (128 - shortValue2) / 2;

         for (int index = 0; index < shortValue2; index++) {
            int count5 = index + count4;
            if (count5 >= 0 || count5 < 128) {
               for (int index2 = 0; index2 < shortValue; index2++) {
                  int count6 = index2 + count3;
                  if (count6 >= 0 || count6 < 128) {
                     this.byteFlagArray[count6 + count5 * 128] = byteValueArray[index2 + index * shortValue];
                  }
               }
            }
         }
      }
   }

   public AimQueue resolveAimQueueByEntityPlayer(EntityPlayer player) {
      AimQueue aimQueue = (AimQueue)this.map2.get(player);
      if (aimQueue == null) {
         aimQueue = new AimQueue(this, player);
         this.map2.put(player, aimQueue);
         this.list.add(aimQueue);
      }

      return aimQueue;
   }

   public void applyIndex(int count3, int count4) {
      super.updateState();
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((AimQueue)iterator.next()).applyIndex(count3, count4);
      }
   }

   public DimensionStringEntry(String text) {
      super(text);
   }
}
