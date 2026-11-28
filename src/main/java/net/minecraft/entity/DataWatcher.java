package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.augustus.modules.misc.ExploitFixer;
import net.augustus.Augustus;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotations;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {
   private static final Map dataTypes = Maps.newHashMap();
   private final Entity owner;
   private final Map watchedObjects = Maps.newHashMap();
   public BiomeGenBase spawnBiome = BiomeGenBase.plains;
   public BlockPos spawnPosition = BlockPos.ORIGIN;
   private boolean isBlank = true;
   private boolean objectChanged;
   private final ReadWriteLock lock = new ReentrantReadWriteLock();

   private static void writeWatchableObjectToPacketBuffer(PacketBuffer buf, DataWatcher.WatchableObject watchableObject) throws IOException {
      buf.writeByte((watchableObject.getObjectType() << 5 | watchableObject.getDataValueId() & 31) & 0xFF);
      switch (watchableObject.getObjectType()) {
         case 0:
            buf.writeByte((Byte)watchableObject.getObject());
            break;
         case 1:
            buf.writeShort((Short)watchableObject.getObject());
            break;
         case 2:
            buf.writeInt((Integer)watchableObject.getObject());
            break;
         case 3:
            buf.writeFloat((Float)watchableObject.getObject());
            break;
         case 4:
            buf.writeString((String)watchableObject.getObject());
            break;
         case 5:
            buf.writeItemStackToBuffer((ItemStack)watchableObject.getObject());
            break;
         case 6:
            BlockPos object2 = (BlockPos)watchableObject.getObject();
            buf.writeInt(object2.getX());
            buf.writeInt(object2.getY());
            buf.writeInt(object2.getZ());
            break;
         case 7:
            Rotations object3 = (Rotations)watchableObject.getObject();
            buf.writeFloat(object3.getX());
            buf.writeFloat(object3.getY());
            buf.writeFloat(object3.getZ());
      }
   }

   public byte getWatchableObjectByte(int n) {
      return (Byte)this.getWatchedObject(n).getObject();
   }

   public List getAllWatched() {
      ArrayList list = null;
      this.lock.readLock().lock();

      for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(this.watchedObjects.values())) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         list.add(watchableObject);
      }

      this.lock.readLock().unlock();
      return list;
   }

   public short getWatchableObjectShort(int n) {
      return (Short)this.getWatchedObject(n).getObject();
   }

   public boolean hasObjectChanged() {
      return this.objectChanged;
   }

   public static void writeWatchedListToPacketBuffer(List list, PacketBuffer buf) throws IOException {
      if (list != null) {
         for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(list)) {
            writeWatchableObjectToPacketBuffer(buf, watchableObject);
         }
      }

      buf.writeByte(127);
   }

   public List getChanged() {
      ArrayList list = null;
      if (this.objectChanged) {
         this.lock.readLock().lock();

         for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(this.watchedObjects.values())) {
            if (watchableObject.isWatched()) {
               watchableObject.setWatched(false);
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(watchableObject);
            }
         }

         this.lock.readLock().unlock();
      }

      this.objectChanged = false;
      return list;
   }

   static {
      dataTypes.put(Byte.class, 0);
      dataTypes.put(Short.class, 1);
      dataTypes.put(Integer.class, 2);
      dataTypes.put(Float.class, 3);
      dataTypes.put(String.class, 4);
      dataTypes.put(ItemStack.class, 5);
      dataTypes.put(BlockPos.class, 6);
      dataTypes.put(Rotations.class, 7);
   }

   public Rotations getWatchableObjectRotations(int n) {
      return (Rotations)this.getWatchedObject(n).getObject();
   }

   public boolean getIsBlank() {
      return this.isBlank;
   }

   private DataWatcher.WatchableObject getWatchedObject(int n) {
      this.lock.readLock().lock();

      DataWatcher.WatchableObject watchableObject;
      try {
         watchableObject = (DataWatcher.WatchableObject)this.watchedObjects.get(n);
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Getting synched entity data");
         crashReport.makeCategory("Synched entity data").addCrashSection("Data ID", n);
         throw new ReportedException(crashReport);
      }

      this.lock.readLock().unlock();
      return watchableObject;
   }

   public float getWatchableObjectFloat(int n) {
      return (Float)this.getWatchedObject(n).getObject();
   }

   public void updateObject(int n, Object obj) {
      DataWatcher.WatchableObject watchedObject = this.getWatchedObject(n);
      if (ObjectUtils.notEqual(obj, watchedObject.getObject())) {
         watchedObject.setObject(obj);
         this.owner.onDataWatcherUpdate(n);
         watchedObject.setWatched(true);
         this.objectChanged = true;
      }
   }

   public String getWatchableObjectString(int n) {
      return (String)this.getWatchedObject(n).getObject();
   }

   public ItemStack getWatchableObjectItemStack(int n) {
      return (ItemStack)this.getWatchedObject(n).getObject();
   }

   public void setObjectWatched(int n) {
      DataWatcher.WatchableObject.assignWatched(this.getWatchedObject(n), true);
      this.objectChanged = true;
   }

   public void writeTo(PacketBuffer buf) throws IOException {
      this.lock.readLock().lock();

      for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(this.watchedObjects.values())) {
         writeWatchableObjectToPacketBuffer(buf, watchableObject);
      }

      this.lock.readLock().unlock();
      buf.writeByte(127);
   }

   public void addObject(int n, Object obj) {
      Integer num = (Integer)dataTypes.get(obj.getClass());
      if (num == null) {
         throw new IllegalArgumentException("Unknown data type: " + obj.getClass());
      } else if (n > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + n + "! (Max is " + 31 + ")");
      } else if (this.watchedObjects.containsKey(n)) {
         throw new IllegalArgumentException("Duplicate id value for " + n + "!");
      } else {
         DataWatcher.WatchableObject watchableObject = new DataWatcher.WatchableObject(num, n, obj);
         this.lock.writeLock().lock();
         this.watchedObjects.put(n, watchableObject);
         this.lock.writeLock().unlock();
         this.isBlank = false;
      }
   }

   public static List readWatchedListFromPacketBuffer(PacketBuffer buf) throws IOException {
      ArrayList list = null;

      for (byte i = buf.readByte(); i != 127; i = buf.readByte()) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         int n = (i & 224) >> 5;
         int n2 = i & 31;
         DataWatcher.WatchableObject watchableObject = null;
         switch (n) {
            case 0:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readByte());
               break;
            case 1:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readShort());
               break;
            case 2:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readInt());
               break;
            case 3:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readFloat());
               break;
            case 4:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readStringFromBuffer(32767));
               break;
            case 5:
               watchableObject = new DataWatcher.WatchableObject(n, n2, buf.readItemStackFromBuffer());
               break;
            case 6:
               int n3 = buf.readInt();
               int n4 = buf.readInt();
               int n5 = buf.readInt();
               watchableObject = new DataWatcher.WatchableObject(n, n2, new BlockPos(n3, n4, n5));
               break;
            case 7:
               float f = buf.readFloat();
               float f2 = buf.readFloat();
               float f3 = buf.readFloat();
               watchableObject = new DataWatcher.WatchableObject(n, n2, new Rotations(f, f2, f3));
         }

         list.add(watchableObject);
      }

      return list;
   }

   public DataWatcher(Entity entity) {
      this.owner = entity;
   }

   public void updateWatchedObjectsFromList(List list) {
      this.lock.writeLock().lock();

      for (DataWatcher.WatchableObject watchableObject : (Iterable<DataWatcher.WatchableObject>)(list)) {
         DataWatcher.WatchableObject watchableObject2 = (DataWatcher.WatchableObject)this.watchedObjects.get(watchableObject.getDataValueId());
         if (watchableObject2 != null) {
            ExploitFixer exploitFixer = Augustus.getInstance().getModuleManager().exploitFixer;
            boolean flag;
            if (exploitFixer.isToggled() && exploitFixer.dataWatcherFix.getValue()) {
               flag = DataWatcher.WatchableObject.getObjectType(watchableObject2) == watchableObject.getObjectType();
            } else {
               flag = true;
            }

            if (flag) {
               watchableObject2.setObject(watchableObject.getObject());
               this.owner.onDataWatcherUpdate(watchableObject.getDataValueId());
            }
         }
      }

      this.lock.writeLock().unlock();
      this.objectChanged = true;
   }

   public void addObjectByDataType(int n, int n2) {
      DataWatcher.WatchableObject watchableObject = new DataWatcher.WatchableObject(n2, n, null);
      this.lock.writeLock().lock();
      this.watchedObjects.put(n, watchableObject);
      this.lock.writeLock().unlock();
      this.isBlank = false;
   }

   public int getWatchableObjectInt(int n) {
      return (Integer)this.getWatchedObject(n).getObject();
   }

   public void markClean() {
      this.objectChanged = false;
   }



   public static class WatchableObject {
      private final int objectType;
      private final int dataValueId;
      private Object watchedObject;
      private boolean watched;

      public void setObject(Object obj) {
         this.watchedObject = obj;
      }

      public Object getObject() {
         return this.watchedObject;
      }

      public static boolean assignWatched(WatchableObject watchableObject, boolean flag) {
         return watchableObject.watched = flag;
      }

      public boolean isWatched() {
         return this.watched;
      }

      public static int getObjectType(WatchableObject watchableObject) {
         return watchableObject.objectType;
      }

      public int getDataValueId() {
         return this.dataValueId;
      }

      public int getObjectType() {
         return this.objectType;
      }

      public void setWatched(boolean flag) {
         this.watched = flag;
      }

      public WatchableObject(int n, int n2, Object obj) {
         this.dataValueId = n2;
         this.watchedObject = obj;
         this.objectType = n;
         this.watched = true;
      }
   }
}
