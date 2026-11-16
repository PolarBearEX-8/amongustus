package client.util;

import client.network.StateUpdateListener;
import client.setting.Setting;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AuxiliaryCopyOnWriteArrayListEntry {
   public final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

   public List getList() {
      return this.copyOnWriteArrayList;
   }

   public void applySettingBySetting(Setting setting) {
      if (setting.getModule() != null && !(setting.getModule() instanceof AuxiliaryModuleAdapter)) {
         this.copyOnWriteArrayList.add(setting);
      }
   }

   private static void updateState() {
      while (true) {
         StateUpdateListener.updateState2();

         try {
            Thread.sleep(1L);
         } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
         }
      }
   }

   static {
      new Thread(AuxiliaryCopyOnWriteArrayListEntry::updateState).start();
   }
}
