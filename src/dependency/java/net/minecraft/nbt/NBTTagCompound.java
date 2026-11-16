package net.minecraft.nbt;

import client.util.AuxiliaryClientManagerRegistry;
import client.util.BytesWhereMaxAllowedManager;
import client.util.ClientManagerController;
import client.util.ExtendedClientManagerHandler;
import client.util.HandlerStringEntry;
import client.util.InternalClientManagerRegistry;
import client.util.InterpolationMatcher;
import client.util.InterpolationSorter;
import client.util.LISTManager;
import client.util.LISTManagerAdapter;
import client.util.PrimaryDoubleEntry;
import client.util.ProviderIntArrayEntry;
import client.util.RotationSerializer;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SmoothAccumulator;
import com.google.common.collect.Maps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;

public class NBTTagCompound extends LISTManager {
   public Map map = Maps.newHashMap();

   public LISTManager get(String text) {
      return (LISTManager)this.map.get(text);
   }

   public double computeValue(String text) {
      try {
         return !this.matchesText(text, 99) ? 0.0 : ((InterpolationSorter)this.map.get(text)).getValue();
      } catch (ClassCastException classCastException) {
         return 0.0;
      }
   }

   public static LISTManager resolveLISTManager(byte byteValue, String text, DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      LISTManager lISTManager = LISTManager.resolveLISTManager(byteValue);

      try {
         lISTManager.applyDataInput(dataInput, count, bytesWhereMaxAllowedManager);
         return lISTManager;
      } catch (IOException ioException) {
         CrashReport crashReport = CrashReport.resolveCrashReport(ioException, "Loading NBT data");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("NBT Tag");
         crashReportCategory.add("Tag name", text);
         crashReportCategory.add("Tag type", byteValue);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public byte computeByteCode(String text) {
      try {
         return !this.matchesText(text, 99) ? 0 : ((InterpolationSorter)this.map.get(text)).getByteFlag();
      } catch (ClassCastException classCastException) {
         return 0;
      }
   }

   public void put(String text, byte byteValue) {
      this.map.put(text, new AuxiliaryClientManagerRegistry(byteValue));
   }

   public String resolveText(String text) {
      try {
         return !this.matchesText(text, 8) ? "" : ((LISTManager)this.map.get(text)).getLabelText();
      } catch (ClassCastException classCastException) {
         return "";
      }
   }

   private CrashReport resolveCrashReport(String text, int count, ClassCastException classCastException) {
      CrashReport crashReport = CrashReport.resolveCrashReport(classCastException, "Reading NBT data");
      CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Corrupt NBT tag", 1);
      crashReportCategory.applyText("Tag type found", new SmoothAccumulator(this, text));
      crashReportCategory.applyText("Tag type expected", new RotationSerializer(this, count));
      crashReportCategory.add("Tag name", text);
      return crashReport;
   }

   public static Map resolveMap(NBTTagCompound tagCompound) {
      return tagCompound.map;
   }

   public int computeCount(String text) {
      try {
         return !this.matchesText(text, 99) ? 0 : ((InterpolationSorter)this.map.get(text)).getCount();
      } catch (ClassCastException classCastException) {
         return 0;
      }
   }

   @Override
   public LISTManager getLISTManager() {
      NBTTagCompound tagCompound = new NBTTagCompound();

      for (String text : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
         tagCompound.applyText(text, ((LISTManager)this.map.get(text)).getLISTManager());
      }

      return tagCompound;
   }

   public boolean matchesText(String text) {
      return this.computeByteCode(text) != 0;
   }

   public boolean matchesText(String text, int count) {
      byte byteValue = this.computeByteCodeByText(text);
      if (byteValue == count) {
         return true;
      } else {
         return count != 99 ? false : byteValue == 1 || byteValue == 2 || byteValue == 3 || byteValue == 4 || byteValue == 5 || byteValue == 6;
      }
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(384L);
      if (count > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.map.clear();

         byte byteValue;
         while ((byteValue = readByte(dataInput, bytesWhereMaxAllowedManager)) != 0) {
            String text = readUTF(dataInput, bytesWhereMaxAllowedManager);
            bytesWhereMaxAllowedManager.applyTime((long)(224 + 16 * text.length()));
            LISTManager lISTManager = resolveLISTManager(byteValue, text, dataInput, count + 1, bytesWhereMaxAllowedManager);
            if (this.map.put(text, lISTManager) != null) {
               bytesWhereMaxAllowedManager.applyTime(288L);
            }
         }
      }
   }

   public long computeTimestamp(String text) {
      try {
         return !this.matchesText(text, 99) ? 0L : ((InterpolationSorter)this.map.get(text)).floor();
      } catch (ClassCastException classCastException) {
         return 0L;
      }
   }

   public boolean containsKey(String text) {
      return this.map.containsKey(text);
   }

   public void put(String text, int count) {
      this.map.put(text, new InterpolationMatcher(count));
   }

   public byte computeByteCodeByText(String text) {
      LISTManager lISTManager = (LISTManager)this.map.get(text);
      return lISTManager != null ? lISTManager.getByteCode() : 0;
   }

   public NBTTagList getExtendedLoggerEntry(String text, int count) {
      try {
         if (this.computeByteCodeByText(text) != 9) {
            return new NBTTagList();
         } else {
            NBTTagList nBTTagList = (NBTTagList)this.map.get(text);
            return nBTTagList.getInt() > 0 && nBTTagList.getCount() != count ? new NBTTagList() : nBTTagList;
         }
      } catch (ClassCastException classCastException) {
         throw new SecondaryRuntimeExceptionAdapter(this.resolveCrashReport(text, 9, classCastException));
      }
   }

   public void put(String text, short shortValue) {
      this.map.put(text, new ClientManagerController(shortValue));
   }

   @Override
   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public Set keySet() {
      return this.map.keySet();
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      for (String text : (Iterable<String>)(Iterable<?>)tagCompound.map.keySet()) {
         LISTManager lISTManager = (LISTManager)tagCompound.map.get(text);
         if (lISTManager.getByteCode() == 10) {
            if (this.matchesText(text, 10)) {
               this.getNBTTagCompound(text).applyNBTTagCompound((NBTTagCompound)lISTManager);
            } else {
               this.applyText(text, lISTManager.getLISTManager());
            }
         } else {
            this.applyText(text, lISTManager.getLISTManager());
         }
      }
   }

   public NBTTagCompound getNBTTagCompound(String text) {
      try {
         return !this.matchesText(text, 10) ? new NBTTagCompound() : (NBTTagCompound)this.map.get(text);
      } catch (ClassCastException classCastException) {
         throw new SecondaryRuntimeExceptionAdapter(this.resolveCrashReport(text, 10, classCastException));
      }
   }

   public short processValue(String text) {
      try {
         return !this.matchesText(text, 99) ? 0 : ((InterpolationSorter)this.map.get(text)).getShortCode();
      } catch (ClassCastException classCastException) {
         return 0;
      }
   }

   @Override
   public byte getByteCode() {
      return 10;
   }

   public void applyText(String text, LISTManager lISTManager) {
      if (lISTManager == null) {
         throw new IllegalArgumentException("Invalid null NBT value with key " + text);
      } else {
         this.map.put(text, lISTManager);
      }
   }

   private static byte readByte(DataInput dataInput, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      return dataInput.readByte();
   }

   public float computeFactor(String text) {
      try {
         return !this.matchesText(text, 99) ? 0.0F : ((InterpolationSorter)this.map.get(text)).getFactor();
      } catch (ClassCastException classCastException) {
         return 0.0F;
      }
   }

   public void put(String text, float factor) {
      this.map.put(text, new InternalClientManagerRegistry(factor));
   }

   public void put(String text, int[] countArray) {
      this.map.put(text, new ProviderIntArrayEntry(countArray));
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      for (String text : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
         LISTManager lISTManager = (LISTManager)this.map.get(text);
         applyText(text, lISTManager, dataOutput);
      }

      dataOutput.writeByte(0);
   }

   private static void applyText(String text, LISTManager lISTManager, DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(lISTManager.getByteCode());
      if (lISTManager.getByteCode() != 0) {
         dataOutput.writeUTF(text);
         lISTManager.writeInt(dataOutput);
      }
   }

   public int[] resolveIndexArray(String text) {
      try {
         return !this.matchesText(text, 11) ? new int[0] : ((ProviderIntArrayEntry)this.map.get(text)).getCountArray();
      } catch (ClassCastException classCastException) {
         throw new SecondaryRuntimeExceptionAdapter(this.resolveCrashReport(text, 11, classCastException));
      }
   }

   public void put(String text, long value) {
      this.map.put(text, new ExtendedClientManagerHandler(value));
   }

   public void remove(String text) {
      this.map.remove(text);
   }

   public void put(String text, double offset) {
      this.map.put(text, new PrimaryDoubleEntry(offset));
   }

   public byte[] resolveByteArray(String text) {
      try {
         return !this.matchesText(text, 7) ? new byte[0] : ((LISTManagerAdapter)this.map.get(text)).getByteFlagArray();
      } catch (ClassCastException classCastException) {
         throw new SecondaryRuntimeExceptionAdapter(this.resolveCrashReport(text, 7, classCastException));
      }
   }

   private static String readUTF(DataInput dataInput, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      return dataInput.readUTF();
   }

   public void put(String text, byte[] byteValueArray) {
      this.map.put(text, new LISTManagerAdapter(byteValueArray));
   }

   public void applyText(String text, boolean flag) {
      this.put(text, (byte)(flag ? 1 : 0));
   }

   public void put(String text, String text2) {
      this.map.put(text, new HandlerStringEntry(text2));
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         NBTTagCompound tagCompound = (NBTTagCompound)other;
         return this.map.entrySet().equals(tagCompound.map.entrySet());
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("{");

      for (Entry entry : (Iterable<Entry>)(Iterable<?>)this.map.entrySet()) {
         if (builder.length() != 1) {
            builder.append(',');
         }

         builder.append((String)entry.getKey()).append(':').append(entry.getValue());
      }

      return builder.append('}').toString();
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.map.hashCode();
   }
}
