package net.minecraft.nbt;

import client.util.BytesWhereMaxAllowedManager;
import client.util.InternalClientManagerRegistry;
import client.util.InterpolationPicker;
import client.util.LISTManager;
import client.util.PrimaryDoubleEntry;
import client.util.ProviderIntArrayEntry;
import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NBTTagList extends LISTManager {
   public static final Logger logger = LogManager.getLogger();
   public List list = Lists.newArrayList();
   public byte byteFlag = 0;

   public int getInt() {
      return this.list.size();
   }

   public NBTTagCompound getNBTTagCompound(int count) {
      if (count >= 0 && count < this.list.size()) {
         LISTManager lISTManager = (LISTManager)this.list.get(count);
         return lISTManager.getByteCode() == 10 ? (NBTTagCompound)lISTManager : new NBTTagCompound();
      } else {
         return new NBTTagCompound();
      }
   }

   public void applyLISTManager(LISTManager lISTManager) {
      if (lISTManager.getByteCode() == 0) {
         logger.warn("Invalid TagEnd added to ListTag");
      } else {
         if (this.byteFlag == 0) {
            this.byteFlag = lISTManager.getByteCode();
         } else if (this.byteFlag != lISTManager.getByteCode()) {
            logger.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.list.add(lISTManager);
      }
   }

   @Override
   public LISTManager getLISTManager() {
      NBTTagList nBTTagList = new NBTTagList();
      nBTTagList.byteFlag = this.byteFlag;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         LISTManager lISTManager = ((LISTManager)iterator.next()).getLISTManager();
         nBTTagList.list.add(lISTManager);
      }

      return nBTTagList;
   }

   public double computeValue(int count) {
      if (count >= 0 && count < this.list.size()) {
         LISTManager lISTManager = (LISTManager)this.list.get(count);
         return lISTManager.getByteCode() == 6 ? ((PrimaryDoubleEntry)lISTManager).getValue() : 0.0;
      } else {
         return 0.0;
      }
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(296L);
      if (count > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.byteFlag = dataInput.readByte();
         int count2 = dataInput.readInt();
         if (this.byteFlag == 0 && count2 > 0) {
            throw new RuntimeException("Missing type on ListTag");
         } else {
            bytesWhereMaxAllowedManager.applyTime(32L * (long)count2);
            this.list = Lists.newArrayListWithCapacity(count2);

            for (int index = 0; index < count2; index++) {
               LISTManager lISTManager = LISTManager.resolveLISTManager(this.byteFlag);
               lISTManager.applyDataInput(dataInput, count + 1, bytesWhereMaxAllowedManager);
               this.list.add(lISTManager);
            }
         }
      }
   }

   @Override
   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public int getCount() {
      return this.byteFlag;
   }

   @Override
   public byte getByteCode() {
      return 9;
   }

   public int[] resolveIndexArray(int count) {
      if (count >= 0 && count < this.list.size()) {
         LISTManager lISTManager = (LISTManager)this.list.get(count);
         return lISTManager.getByteCode() == 11 ? ((ProviderIntArrayEntry)lISTManager).getCountArray() : new int[0];
      } else {
         return new int[0];
      }
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      if (!this.list.isEmpty()) {
         this.byteFlag = ((LISTManager)this.list.get(0)).getByteCode();
      } else {
         this.byteFlag = 0;
      }

      dataOutput.writeByte(this.byteFlag);
      dataOutput.writeInt(this.list.size());

      for (int index = 0; index < this.list.size(); index++) {
         ((LISTManager)this.list.get(index)).writeInt(dataOutput);
      }
   }

   public LISTManager remove(int count) {
      return (LISTManager)this.list.remove(count);
   }

   public LISTManager resolveLISTManager(int count) {
      return (LISTManager)(count >= 0 && count < this.list.size() ? (LISTManager)this.list.get(count) : new InterpolationPicker());
   }

   public String resolveText(int count) {
      if (count >= 0 && count < this.list.size()) {
         LISTManager lISTManager = (LISTManager)this.list.get(count);
         return lISTManager.getByteCode() == 8 ? lISTManager.getLabelText() : lISTManager.toString();
      } else {
         return "";
      }
   }

   public float computeFactor(int count) {
      if (count >= 0 && count < this.list.size()) {
         LISTManager lISTManager = (LISTManager)this.list.get(count);
         return lISTManager.getByteCode() == 5 ? ((InternalClientManagerRegistry)lISTManager).getFactor() : 0.0F;
      } else {
         return 0.0F;
      }
   }

   public void applyLISTManager(int count, LISTManager lISTManager) {
      if (lISTManager.getByteCode() == 0) {
         logger.warn("Invalid TagEnd added to ListTag");
      } else if (count >= 0 && count < this.list.size()) {
         if (this.byteFlag == 0) {
            this.byteFlag = lISTManager.getByteCode();
         } else if (this.byteFlag != lISTManager.getByteCode()) {
            logger.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.list.set(count, lISTManager);
      } else {
         logger.warn("index out of bounds to set tag in tag list");
      }
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         NBTTagList nBTTagList = (NBTTagList)other;
         if (this.byteFlag == nBTTagList.byteFlag) {
            return this.list.equals(nBTTagList.list);
         }
      }

      return false;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");

      for (int index = 0; index < this.list.size(); index++) {
         if (index != 0) {
            builder.append(',');
         }

         builder.append(index).append(':').append(this.list.get(index));
      }

      return builder.append(']').toString();
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.list.hashCode();
   }
}
