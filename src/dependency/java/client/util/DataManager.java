package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveHandler;

public class DataManager {
   public ISaveHandler iSaveHandler;
   public Map map = Maps.newHashMap();
   public List list = Lists.newArrayList();
   public Map map2 = Maps.newHashMap();

   private void applyAdapterStringEntry(AdapterStringEntry adapterStringEntry) {
      if (this.iSaveHandler != null) {
         try {
            File file = this.iSaveHandler.resolveFile(adapterStringEntry.labelText);
            if (file != null) {
               NBTTagCompound tagCompound = new NBTTagCompound();
               adapterStringEntry.applyNBTTagCompound(tagCompound);
               NBTTagCompound tagCompound2 = new NBTTagCompound();
               tagCompound2.applyText("data", tagCompound);
               FileOutputStream outputStream = new FileOutputStream(file);
               LoadingNBTDataManager.applyNBTTagCompound(tagCompound2, outputStream);
               outputStream.close();
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   public void updateState() {
      for (int index = 0; index < this.list.size(); index++) {
         AdapterStringEntry adapterStringEntry = (AdapterStringEntry)this.list.get(index);
         if (adapterStringEntry.isEnabled()) {
            this.applyAdapterStringEntry(adapterStringEntry);
            adapterStringEntry.setEnabled(false);
         }
      }
   }

   private void updateState2() {
      try {
         this.map2.clear();
         if (this.iSaveHandler == null) {
            return;
         }

         File file = this.iSaveHandler.resolveFile("idcounts");
         if (file != null && file.exists()) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(dataInputStream);
            dataInputStream.close();

            for (Object textObj : tagCompound.keySet()) {
               String text = (String)textObj;
               LISTManager lISTManager = tagCompound.get(text);
               if (lISTManager instanceof ClientManagerController) {
                  short shortCode = ((ClientManagerController)lISTManager).getShortCode();
                  this.map2.put(text, shortCode);
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public void put(String text, AdapterStringEntry adapterStringEntry) {
      if (this.map.containsKey(text)) {
         this.list.remove(this.map.remove(text));
      }

      this.map.put(text, adapterStringEntry);
      this.list.add(adapterStringEntry);
   }

   public int computeCount(String text) {
      Short shortValue = (Short)this.map2.get(text);
      Short shortValue2;
      if (shortValue == null) {
         shortValue2 = (short)0;
      } else {
         shortValue2 = (short)(shortValue + 1);
      }

      this.map2.put(text, shortValue2);
      if (this.iSaveHandler == null) {
         return shortValue2;
      } else {
         try {
            File file = this.iSaveHandler.resolveFile("idcounts");
            if (file != null) {
               NBTTagCompound tagCompound = new NBTTagCompound();

               for (Object text2Obj : this.map2.keySet()) {
                  String text2 = (String)text2Obj;
                  short shortValue3 = (Short)this.map2.get(text2);
                  tagCompound.put(text2, shortValue3);
               }

               DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
               LoadingNBTDataManager.applyNBTTagCompound(tagCompound, (DataOutput)dataOutputStream);
               dataOutputStream.close();
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }

         return shortValue2;
      }
   }

   public AdapterStringEntry get(Class clazz, String text) {
      AdapterStringEntry adapterStringEntry = (AdapterStringEntry)this.map.get(text);
      if (adapterStringEntry != null) {
         return adapterStringEntry;
      } else {
         if (this.iSaveHandler != null) {
            try {
               File file = this.iSaveHandler.resolveFile(text);
               if (file != null && file.exists()) {
                  try {
                     adapterStringEntry = (AdapterStringEntry)clazz.getConstructor(String.class).newInstance(text);
                  } catch (Exception exception) {
                     throw new RuntimeException("Failed to instantiate " + clazz.toString(), exception);
                  }

                  FileInputStream inputStream = new FileInputStream(file);
                  NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(inputStream);
                  inputStream.close();
                  adapterStringEntry.applyNBTTagCompoundByNBTTagCompound(tagCompound.getNBTTagCompound("data"));
               }
            } catch (Exception exception2) {
               exception2.printStackTrace();
            }
         }

         if (adapterStringEntry != null) {
            this.map.put(text, adapterStringEntry);
            this.list.add(adapterStringEntry);
         }

         return adapterStringEntry;
      }
   }

   public DataManager(ISaveHandler iSaveHandler2) {
      this.iSaveHandler = iSaveHandler2;
      this.updateState2();
   }
}
