package net.minecraft.nbt;

import java.util.concurrent.Callable;
import com.google.common.collect.Maps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public class NBTTagCompound extends NBTBase {
   private Map tagMap = Maps.newHashMap();

   public void setByte(String str, byte b) {
      this.tagMap.put(str, new NBTTagByte(b));
   }

   public void setTag(String str, NBTBase nbt) {
      if (nbt == null) {
         throw new IllegalArgumentException("Invalid null NBT value with key " + str);
      } else {
         this.tagMap.put(str, nbt);
      }
   }

   private static byte readType(DataInput dataInput, NBTSizeTracker nbtSizeTracker) throws IOException {
      return dataInput.readByte();
   }

   public long getLong(String str) {
      try {
         return !this.hasKey(str, 99) ? 0L : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getLong();
      } catch (ClassCastException e) {
         return 0L;
      }
   }

   public void setString(String str, String str2) {
      this.tagMap.put(str, new NBTTagString(str2));
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.tagMap.hashCode();
   }

   public void setShort(String str, short s) {
      this.tagMap.put(str, new NBTTagShort(s));
   }

   private CrashReport createCrashReport(String str, int n, ClassCastException ex) {
      CrashReport crashReport = CrashReport.makeCrashReport(ex, "Reading NBT data");
      CrashReportCategory crashReportCategory = crashReport.makeCategoryDepth("Corrupt NBT tag", 1);
      crashReportCategory.addCrashSectionCallable("Tag type found", new NBTTagCompound.ValueCallable(this, str));
      crashReportCategory.addCrashSectionCallable("Tag type expected", new NBTTagCompound.ValueCallable2(this, n));
      crashReportCategory.addCrashSection("Tag name", str);
      return crashReport;
   }

   public int getInteger(String str) {
      try {
         return !this.hasKey(str, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getInt();
      } catch (ClassCastException e) {
         return 0;
      }
   }

   public static NBTBase readNBT(byte b, String str, DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      NBTBase nbt = NBTBase.createNewByType(b);

      try {
         nbt.read(dataInput, n, nbtSizeTracker);
         return nbt;
      } catch (IOException e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Loading NBT data");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("NBT Tag");
         crashReportCategory.addCrashSection("Tag name", str);
         crashReportCategory.addCrashSection("Tag type", b);
         throw new ReportedException(crashReport);
      }
   }

   public byte[] getByteArray(String str) {
      try {
         return !this.hasKey(str, 7) ? new byte[0] : ((NBTTagByteArray)this.tagMap.get(str)).getByteArray();
      } catch (ClassCastException e) {
         throw new ReportedException(this.createCrashReport(str, 7, e));
      }
   }

   public void setBoolean(String str, boolean flag) {
      this.setByte(str, (byte)(flag ? 1 : 0));
   }

   public String getString(String str) {
      try {
         return !this.hasKey(str, 8) ? "" : ((NBTBase)this.tagMap.get(str)).getString();
      } catch (ClassCastException e) {
         return "";
      }
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("{");

      for (Entry entry : (Iterable<Entry>)(this.tagMap.entrySet())) {
         if (sb.length() != 1) {
            sb.append(',');
         }

         sb.append((String)entry.getKey()).append(':').append(entry.getValue());
      }

      return sb.append('}').toString();
   }

   public boolean hasKey(String str) {
      return this.tagMap.containsKey(str);
   }

   public Set getKeySet() {
      return this.tagMap.keySet();
   }

   public void setIntArray(String str, int[] ints) {
      this.tagMap.put(str, new NBTTagIntArray(ints));
   }

   private static void writeEntry(String str, NBTBase nbt, DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(nbt.getId());
      if (nbt.getId() != 0) {
         dataOutput.writeUTF(str);
         nbt.write(dataOutput);
      }
   }

   public float getFloat(String str) {
      try {
         return !this.hasKey(str, 99) ? 0.0F : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getFloat();
      } catch (ClassCastException e) {
         return 0.0F;
      }
   }

   public short getShort(String str) {
      try {
         return !this.hasKey(str, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getShort();
      } catch (ClassCastException e) {
         return 0;
      }
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(384L);
      if (n > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagMap.clear();

         byte b;
         while ((b = readType(dataInput, nbtSizeTracker)) != 0) {
            String str = readKey(dataInput, nbtSizeTracker);
            nbtSizeTracker.read((long)(224 + 16 * str.length()));
            NBTBase nbt = readNBT(b, str, dataInput, n + 1, nbtSizeTracker);
            if (this.tagMap.put(str, nbt) != null) {
               nbtSizeTracker.read(288L);
            }
         }
      }
   }

   public void setLong(String str, long l) {
      this.tagMap.put(str, new NBTTagLong(l));
   }

   public int[] getIntArray(String str) {
      try {
         return !this.hasKey(str, 11) ? new int[0] : ((NBTTagIntArray)this.tagMap.get(str)).getIntArray();
      } catch (ClassCastException e) {
         throw new ReportedException(this.createCrashReport(str, 11, e));
      }
   }

   public boolean hasKey(String str, int n) {
      byte tagId = this.getTagId(str);
      if (tagId == n) {
         return true;
      } else if (n != 99) {
         if (tagId > 0) {
         }

         return false;
      } else {
         return tagId == 1 || tagId == 2 || tagId == 3 || tagId == 4 || tagId == 5 || tagId == 6;
      }
   }

   public boolean getBoolean(String str) {
      return this.getByte(str) != 0;
   }

   public byte getTagId(String str) {
      NBTBase nbt = (NBTBase)this.tagMap.get(str);
      return nbt != null ? nbt.getId() : 0;
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      for (String str : (Iterable<String>)(this.tagMap.keySet())) {
         writeEntry(str, (NBTBase)this.tagMap.get(str), dataOutput);
      }

      dataOutput.writeByte(0);
   }

   public void merge(NBTTagCompound tag) {
      for (String str : (Iterable<String>)(tag.tagMap.keySet())) {
         NBTBase nbt = (NBTBase)tag.tagMap.get(str);
         if (nbt.getId() == 10) {
            if (this.hasKey(str, 10)) {
               this.getCompoundTag(str).merge((NBTTagCompound)nbt);
            } else {
               this.setTag(str, nbt.copy());
            }
         } else {
            this.setTag(str, nbt.copy());
         }
      }
   }

   public NBTBase getTag(String str) {
      return (NBTBase)this.tagMap.get(str);
   }

   @Override
   public boolean hasNoTags() {
      return this.tagMap.isEmpty();
   }

   public void setFloat(String str, float f) {
      this.tagMap.put(str, new NBTTagFloat(f));
   }

   public byte getByte(String str) {
      try {
         return !this.hasKey(str, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getByte();
      } catch (ClassCastException e) {
         return 0;
      }
   }

   public void setInteger(String str, int n) {
      this.tagMap.put(str, new NBTTagInt(n));
   }

   public double getDouble(String str) {
      try {
         return !this.hasKey(str, 99) ? 0.0 : ((NBTBase.NBTPrimitive)this.tagMap.get(str)).getDouble();
      } catch (ClassCastException e) {
         return 0.0;
      }
   }

   public void setByteArray(String str, byte[] bytes) {
      this.tagMap.put(str, new NBTTagByteArray(bytes));
   }

   @Override
   public byte getId() {
      return 10;
   }

   public NBTTagCompound getCompoundTag(String str) {
      try {
         return !this.hasKey(str, 10) ? new NBTTagCompound() : (NBTTagCompound)this.tagMap.get(str);
      } catch (ClassCastException e) {
         throw new ReportedException(this.createCrashReport(str, 10, e));
      }
   }

   public void setDouble(String str, double d) {
      this.tagMap.put(str, new NBTTagDouble(d));
   }

   public void removeTag(String str) {
      this.tagMap.remove(str);
   }

   public static Map accessTagMap(NBTTagCompound tag) {
      return tag.tagMap;
   }

   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         NBTTagCompound tag = (NBTTagCompound)obj;
         return this.tagMap.entrySet().equals(tag.tagMap.entrySet());
      } else {
         return false;
      }
   }

   public NBTTagList getTagList(String str, int n) {
      try {
         if (this.getTagId(str) != 9) {
            return new NBTTagList();
         } else {
            NBTTagList tagList = (NBTTagList)this.tagMap.get(str);
            return tagList.tagCount() > 0 && tagList.getTagType() != n ? new NBTTagList() : tagList;
         }
      } catch (ClassCastException e) {
         throw new ReportedException(this.createCrashReport(str, 9, e));
      }
   }

   private static String readKey(DataInput dataInput, NBTSizeTracker nbtSizeTracker) throws IOException {
      return dataInput.readUTF();
   }

   @Override
   public NBTBase copy() {
      NBTTagCompound tag = new NBTTagCompound();

      for (String str : (Iterable<String>)(this.tagMap.keySet())) {
         tag.setTag(str, ((NBTBase)this.tagMap.get(str)).copy());
      }

      return tag;
   }


   public static class ValueCallable implements Callable {
      public final String key;
      public final NBTTagCompound tagCompound;

      public String callAsString() throws Exception {
         return NBTBase.NBT_TYPES[((NBTBase)NBTTagCompound.accessTagMap(this.tagCompound).get(this.key)).getId()];
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable(NBTTagCompound tag, String str) {
         this.tagCompound = tag;
         this.key = str;
      }
   }

   public static class ValueCallable2 implements Callable {
      public final int expectedType;
      public final NBTTagCompound tagCompound;

      public String callAsString() throws Exception {
         return NBTBase.NBT_TYPES[this.expectedType];
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(NBTTagCompound tag, int n) {
         this.tagCompound = tag;
         this.expectedType = n;
      }
   }
}
