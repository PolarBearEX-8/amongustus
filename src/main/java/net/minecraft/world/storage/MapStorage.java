package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.world.WorldSavedData;

public class MapStorage {
   private ISaveHandler saveHandler;
   public Map loadedDataMap = Maps.newHashMap();
   private List loadedDataList = Lists.newArrayList();
   private Map idCounts = Maps.newHashMap();

   public MapStorage(ISaveHandler saveHandler2) {
      this.saveHandler = saveHandler2;
      this.loadIdCounts();
   }

   public void saveAllData() {
      for (int i = 0; i < this.loadedDataList.size(); i++) {
         WorldSavedData worldSavedData = (WorldSavedData)this.loadedDataList.get(i);
         if (worldSavedData.isDirty()) {
            this.saveData(worldSavedData);
            worldSavedData.setDirty(false);
         }
      }
   }

   public WorldSavedData loadData(Class clazz, String str) {
      WorldSavedData worldSavedData = (WorldSavedData)this.loadedDataMap.get(str);
      if (worldSavedData != null) {
         return worldSavedData;
      } else {
         if (this.saveHandler != null) {
            try {
               File mapFileFromName = this.saveHandler.getMapFileFromName(str);
               if (mapFileFromName != null && mapFileFromName.exists()) {
                  try {
                     worldSavedData = (WorldSavedData)clazz.getConstructor(String.class).newInstance(str);
                  } catch (Exception e) {
                     throw new RuntimeException("Failed to instantiate " + clazz.toString(), e);
                  }

                  FileInputStream fileInputStream = new FileInputStream(mapFileFromName);
                  NBTTagCompound tag = CompressedStreamTools.readCompressed(fileInputStream);
                  fileInputStream.close();
                  worldSavedData.readFromNBT(tag.getCompoundTag("data"));
               }
            } catch (Exception e2) {
               e2.printStackTrace();
            }
         }

         if (worldSavedData != null) {
            this.loadedDataMap.put(str, worldSavedData);
            this.loadedDataList.add(worldSavedData);
         }

         return worldSavedData;
      }
   }

   private void loadIdCounts() {
      try {
         this.idCounts.clear();
         if (this.saveHandler == null) {
            return;
         }

         File mapFileFromName = this.saveHandler.getMapFileFromName("idcounts");
         if (mapFileFromName != null && mapFileFromName.exists()) {
            DataInputStream in = new DataInputStream(new FileInputStream(mapFileFromName));
            NBTTagCompound tag = CompressedStreamTools.read(in);
            in.close();

            for (String str : (Iterable<String>)(tag.getKeySet())) {
               NBTBase tag2 = tag.getTag(str);
               if (tag2 instanceof NBTTagShort) {
                  short s = ((NBTTagShort)tag2).getShort();
                  this.idCounts.put(str, s);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void saveData(WorldSavedData worldSavedData) {
      if (this.saveHandler != null) {
         try {
            File mapFileFromName = this.saveHandler.getMapFileFromName(worldSavedData.mapName);
            if (mapFileFromName != null) {
               NBTTagCompound tag = new NBTTagCompound();
               worldSavedData.writeToNBT(tag);
               NBTTagCompound tag2 = new NBTTagCompound();
               tag2.setTag("data", tag);
               FileOutputStream fileOutputStream = new FileOutputStream(mapFileFromName);
               CompressedStreamTools.writeCompressed(tag2, fileOutputStream);
               fileOutputStream.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public int getUniqueDataId(String str) {
      Short s = (Short)this.idCounts.get(str);
      if (s == null) {
         s = (short)0;
      } else {
         s = (short)(s + 1);
      }

      this.idCounts.put(str, s);
      if (this.saveHandler == null) {
         return s;
      } else {
         try {
            File mapFileFromName = this.saveHandler.getMapFileFromName("idcounts");
            if (mapFileFromName != null) {
               NBTTagCompound tag = new NBTTagCompound();

               for (String str2 : (Iterable<String>)(this.idCounts.keySet())) {
                  short s2 = (Short)this.idCounts.get(str2);
                  tag.setShort(str2, s2);
               }

               DataOutputStream out = new DataOutputStream(new FileOutputStream(mapFileFromName));
               CompressedStreamTools.write(tag, out);
               out.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

         return s;
      }
   }

   public void setData(String str, WorldSavedData worldSavedData) {
      if (this.loadedDataMap.containsKey(str)) {
         this.loadedDataList.remove(this.loadedDataMap.remove(str));
      }

      this.loadedDataMap.put(str, worldSavedData);
      this.loadedDataList.add(worldSavedData);
   }
}
