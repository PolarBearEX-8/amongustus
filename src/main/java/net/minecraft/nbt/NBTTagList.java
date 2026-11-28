package net.minecraft.nbt;

import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NBTTagList extends NBTBase {
   private static final Logger LOGGER = LogManager.getLogger();
   private List tagList = Lists.newArrayList();
   private byte tagType = 0;

   public NBTTagCompound getCompoundTagAt(int n) {
      if (n >= 0 && n < this.tagList.size()) {
         NBTBase nbt = (NBTBase)this.tagList.get(n);
         return nbt.getId() == 10 ? (NBTTagCompound)nbt : new NBTTagCompound();
      } else {
         return new NBTTagCompound();
      }
   }

   public String getStringTagAt(int n) {
      if (n >= 0 && n < this.tagList.size()) {
         NBTBase nbt = (NBTBase)this.tagList.get(n);
         return nbt.getId() == 8 ? nbt.getString() : nbt.toString();
      } else {
         return "";
      }
   }

   public NBTBase removeTag(int n) {
      return (NBTBase)this.tagList.remove(n);
   }

   public void appendTag(NBTBase nbt) {
      if (nbt.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else {
         if (this.tagType == 0) {
            this.tagType = nbt.getId();
         } else if (this.tagType != nbt.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.add(nbt);
      }
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      if (!this.tagList.isEmpty()) {
         this.tagType = ((NBTBase)this.tagList.get(0)).getId();
      } else {
         this.tagType = 0;
      }

      dataOutput.writeByte(this.tagType);
      dataOutput.writeInt(this.tagList.size());

      for (int i = 0; i < this.tagList.size(); i++) {
         ((NBTBase)this.tagList.get(i)).write(dataOutput);
      }
   }

   public void set(int n, NBTBase nbt) {
      if (nbt.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else if (n >= 0 && n < this.tagList.size()) {
         if (this.tagType == 0) {
            this.tagType = nbt.getId();
         } else if (this.tagType != nbt.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.set(n, nbt);
      } else {
         LOGGER.warn("index out of bounds to set tag in tag list");
      }
   }

   @Override
   public boolean hasNoTags() {
      return this.tagList.isEmpty();
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagList tagList2 = (NBTTagList)obj;
         if (this.tagType == tagList2.tagType) {
            return this.tagList.equals(tagList2.tagList);
         }
      }

      return false;
   }

   public NBTBase get(int n) {
      return (NBTBase)(n >= 0 && n < this.tagList.size() ? (NBTBase)this.tagList.get(n) : new NBTTagEnd());
   }

   public double getDoubleAt(int n) {
      if (n >= 0 && n < this.tagList.size()) {
         NBTBase nbt = (NBTBase)this.tagList.get(n);
         return nbt.getId() == 6 ? ((NBTTagDouble)nbt).getDouble() : 0.0;
      } else {
         return 0.0;
      }
   }

   @Override
   public byte getId() {
      return 9;
   }

   public float getFloatAt(int n) {
      if (n >= 0 && n < this.tagList.size()) {
         NBTBase nbt = (NBTBase)this.tagList.get(n);
         return nbt.getId() == 5 ? ((NBTTagFloat)nbt).getFloat() : 0.0F;
      } else {
         return 0.0F;
      }
   }

   public int getTagType() {
      return this.tagType;
   }

   public int[] getIntArrayAt(int n) {
      if (n >= 0 && n < this.tagList.size()) {
         NBTBase nbt = (NBTBase)this.tagList.get(n);
         return nbt.getId() == 11 ? ((NBTTagIntArray)nbt).getIntArray() : new int[0];
      } else {
         return new int[0];
      }
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("[");

      for (int i = 0; i < this.tagList.size(); i++) {
         if (i != 0) {
            sb.append(',');
         }

         sb.append(i).append(':').append(this.tagList.get(i));
      }

      return sb.append(']').toString();
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(296L);
      if (n > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagType = dataInput.readByte();
         int n2 = dataInput.readInt();
         if (this.tagType == 0 && n2 > 0) {
            throw new RuntimeException("Missing type on ListTag");
         } else {
            nbtSizeTracker.read(32L * (long)n2);
            this.tagList = Lists.newArrayListWithCapacity(n2);

            for (int i = 0; i < n2; i++) {
               NBTBase nbt = NBTBase.createNewByType(this.tagType);
               nbt.read(dataInput, n + 1, nbtSizeTracker);
               this.tagList.add(nbt);
            }
         }
      }
   }

   @Override
   public NBTBase copy() {
      NBTTagList tagList2 = new NBTTagList();
      tagList2.tagType = this.tagType;

      for (NBTBase nbt : (Iterable<NBTBase>)(this.tagList)) {
         NBTBase nbt2 = nbt.copy();
         tagList2.tagList.add(nbt2);
      }

      return tagList2;
   }

   public int tagCount() {
      return this.tagList.size();
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.tagList.hashCode();
   }
}
