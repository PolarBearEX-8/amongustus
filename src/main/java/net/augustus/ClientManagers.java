package net.augustus;

import net.augustus.modules.ModuleRegistry;
import net.augustus.settings.SettingManager;
import net.augustus.utils.rotation.RotationController;
import net.augustus.modules.ModuleManager;

public class ClientManagers {
   public static ModuleManager moduleManager;
   public static ModuleRegistry moduleRegistry;
   public static SettingManager settingManager;
   public static RotationController rotationController;

   public static void init() {
      rotationController = Augustus.getInstance().getRotationController();
      settingManager = Augustus.getInstance().getSettingManager();
      moduleManager = Augustus.getInstance().getModuleManager();
      moduleRegistry = Augustus.getInstance().getModuleRegistry();
   }



   static {
      rotationController = Augustus.getInstance().getRotationController();
      settingManager = Augustus.getInstance().getSettingManager();
      moduleManager = Augustus.getInstance().getModuleManager();
      moduleRegistry = Augustus.getInstance().getModuleRegistry();
   }
}
