package net.augustus.settings;

import net.augustus.events.listeners.AsyncTickListener;
import net.augustus.modules.misc.Dummy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SettingManager {
   private final CopyOnWriteArrayList settings = new CopyOnWriteArrayList();

   public List getSettings() {
      return this.settings;
   }

   static {
      new Thread(() -> {
         while (true) {
            AsyncTickListener.postAsyncTick();
            try {
               Thread.sleep(1L);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
      }).start();
   }

   public void registerSetting(Setting setting) {
      if (setting.getParentModule() != null && !(setting.getParentModule() instanceof Dummy)) {
         this.settings.add(setting);
      }
   }
}
