package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {
   public final Entity entity;
   public boolean enabled = true;
   public static final Map map = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public boolean enabled2;
   public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   public BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35;
   public BlockPos blockPos = BlockPos.origin;

   public void applyIndex(int count) {
      TargetFilter.matchesTargetFilter(this.resolveTargetFilter(count), true);
      this.enabled2 = true;
   }

   public ManagedFloatEntry resolveManagedFloatEntry(int count) {
      return (ManagedFloatEntry)this.resolveTargetFilter(count).getDataRef();
   }

   public void applyTarget(int count, Object entry) {
      Integer count2 = (Integer)map.get(entry.getClass());
      if (count2 == null) {
         throw new IllegalArgumentException("Unknown data type: " + entry.getClass());
      } else if (count > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + count + "! (Max is " + 31 + ")");
      } else if (this.map2.containsKey(count)) {
         throw new IllegalArgumentException("Duplicate id value for " + count + "!");
      } else {
         TargetFilter targetFilter = new TargetFilter(count2, count, entry);
         this.readWriteLock.writeLock().lock();
         this.map2.put(count, targetFilter);
         this.readWriteLock.writeLock().unlock();
         this.enabled = false;
      }
   }

   private static void applyPacketBuffer(PacketBuffer packet, TargetFilter targetFilter) throws IOException {
      int count = (targetFilter.getCount2() << 5 | targetFilter.getCount() & 31) & 0xFF;
      packet.writeByte(count);
      switch (targetFilter.getCount2()) {
         case 0:
            packet.writeByte((Byte)targetFilter.getDataRef());
            break;
         case 1:
            packet.writeShort((Short)targetFilter.getDataRef());
            break;
         case 2:
            packet.writeInt((Integer)targetFilter.getDataRef());
            break;
         case 3:
            packet.writeFloat((Float)targetFilter.getDataRef());
            break;
         case 4:
            packet.getPacketBuffer((String)targetFilter.getDataRef());
            break;
         case 5:
            ItemStack dataRef = (ItemStack)targetFilter.getDataRef();
            packet.applyItemStack(dataRef);
            break;
         case 6:
            BlockPos dataRef2 = (BlockPos)targetFilter.getDataRef();
            packet.writeInt(dataRef2.getX());
            packet.writeInt(dataRef2.getY());
            packet.writeInt(dataRef2.getZ());
            break;
         case 7:
            ManagedFloatEntry dataRef3 = (ManagedFloatEntry)targetFilter.getDataRef();
            packet.writeFloat(dataRef3.getFactor3());
            packet.writeFloat(dataRef3.getFactor());
            packet.writeFloat(dataRef3.getFactor2());
      }
   }

   public static void applyList(List list, PacketBuffer packet) throws IOException {
      if (list != null) {
         for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)list) {
            applyPacketBuffer(packet, targetFilter);
         }
      }

      packet.writeByte(127);
   }

   public short computeShortCode(int count) {
      return (Short)this.resolveTargetFilter(count).getDataRef();
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int computeCount(int count) {
      return (Integer)this.resolveTargetFilter(count).getDataRef();
   }

   public List getList() {
      ArrayList list = null;
      this.readWriteLock.readLock().lock();

      for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)this.map2.values()) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         list.add(targetFilter);
      }

      this.readWriteLock.readLock().unlock();
      return list;
   }

   public void updateState() {
      this.enabled2 = false;
   }

   private TargetFilter resolveTargetFilter(int count) {
      this.readWriteLock.readLock().lock();

      TargetFilter targetFilter;
      try {
         targetFilter = (TargetFilter)this.map2.get(count);
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Getting synched entity data");
         crashReport.resolveCrashReportCategory("Synched entity data").add("Data ID", count);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }

      this.readWriteLock.readLock().unlock();
      return targetFilter;
   }

   public ItemStack getItemStack(int count) {
      return (ItemStack)this.resolveTargetFilter(count).getDataRef();
   }

   public List getListAsList() {
      ArrayList list = null;
      if (this.enabled2) {
         this.readWriteLock.readLock().lock();

         for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)this.map2.values()) {
            if (targetFilter.isEnabled()) {
               targetFilter.setEnabled(false);
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(targetFilter);
            }
         }

         this.readWriteLock.readLock().unlock();
      }

      this.enabled2 = false;
      return list;
   }

   public String resolveText(int count) {
      return (String)this.resolveTargetFilter(count).getDataRef();
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void applyIndex(int count, int count2) {
      TargetFilter targetFilter = new TargetFilter(count2, count, null);
      this.readWriteLock.writeLock().lock();
      this.map2.put(count, targetFilter);
      this.readWriteLock.writeLock().unlock();
      this.enabled = false;
   }

   public void applyPacketBuffer(PacketBuffer packet) throws IOException {
      this.readWriteLock.readLock().lock();

      for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)this.map2.values()) {
         applyPacketBuffer(packet, targetFilter);
      }

      this.readWriteLock.readLock().unlock();
      packet.writeByte(127);
   }

   public static List resolveList(PacketBuffer packet) throws IOException {
      ArrayList list = null;

      for (int index = packet.readByte(); index != 127; index = packet.readByte()) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         int count = (index & 224) >> 5;
         index &= 31;
         TargetFilter targetFilter = null;
         switch (count) {
            case 0:
               targetFilter = new TargetFilter(count, index, packet.readByte());
               break;
            case 1:
               targetFilter = new TargetFilter(count, index, packet.readShort());
               break;
            case 2:
               targetFilter = new TargetFilter(count, index, packet.readInt());
               break;
            case 3:
               targetFilter = new TargetFilter(count, index, packet.readFloat());
               break;
            case 4:
               targetFilter = new TargetFilter(count, index, packet.resolveTextByIndex(32767));
               break;
            case 5:
               targetFilter = new TargetFilter(count, index, packet.getItemStack());
               break;
            case 6:
               int count2 = packet.readInt();
               int count3 = packet.readInt();
               int count4 = packet.readInt();
               targetFilter = new TargetFilter(count, index, new BlockPos(count2, count3, count4));
               break;
            case 7:
               float factor = packet.readFloat();
               float factor2 = packet.readFloat();
               float factor3 = packet.readFloat();
               targetFilter = new TargetFilter(count, index, new ManagedFloatEntry(factor, factor2, factor3));
         }

         list.add(targetFilter);
      }

      return list;
   }

   public byte computeByteCode(int count) {
      return (Byte)this.resolveTargetFilter(count).getDataRef();
   }

   public void applyTargetByTarget(int count, Object entry) {
      TargetFilter targetFilter = this.resolveTargetFilter(count);
      if (ObjectUtils.notEqual(entry, targetFilter.getDataRef())) {
         targetFilter.setDataRef(entry);
         this.entity.applyIndex4(count);
         targetFilter.setEnabled(true);
         this.enabled2 = true;
      }
   }

   public void applyList(List list) {
      this.readWriteLock.writeLock().lock();

      for (TargetFilter targetFilter : (Iterable<TargetFilter>)(Iterable<?>)list) {
         TargetFilter targetFilter2 = (TargetFilter)this.map2.get(targetFilter.getCount());
         if (targetFilter2 != null) {
            targetFilter2.setDataRef(targetFilter.getDataRef());
            this.entity.applyIndex4(targetFilter.getCount());
         }
      }

      this.readWriteLock.writeLock().unlock();
      this.enabled2 = true;
   }

   public float computeFactor(int count) {
      return (Float)this.resolveTargetFilter(count).getDataRef();
   }

   public DataWatcher(Entity entity2) {
      this.entity = entity2;
   }

   static {
      map.put(Byte.class, 0);
      map.put(Short.class, 1);
      map.put(Integer.class, 2);
      map.put(Float.class, 3);
      map.put(String.class, 4);
      map.put(ItemStack.class, 5);
      map.put(BlockPos.class, 6);
      map.put(ManagedFloatEntry.class, 7);
   }
}
