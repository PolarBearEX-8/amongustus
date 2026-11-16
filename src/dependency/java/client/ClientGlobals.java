package client;

import client.module.ModuleManager;
import client.rotation.RotationManager;
import client.util.AuxiliaryCopyOnWriteArrayListEntry;
import client.util.CopyOnWriteListHolder;

public class ClientGlobals {
   public static AuxiliaryCopyOnWriteArrayListEntry auxiliaryCopyOnWriteArrayListEntry = Client.getClient().getAuxiliaryCopyOnWriteArrayLis();
   public static ModuleManager moduleManager = Client.getClient().getModuleManager();
   public static CopyOnWriteListHolder copyOnWriteListHolder = Client.getClient().getCopyOnWriteArrayListEntry();
   public static RotationManager rotationManager = Client.getClient().getRotationManager();

   public static void updateState() {
      rotationManager = Client.getClient().getRotationManager();
      auxiliaryCopyOnWriteArrayListEntry = Client.getClient().getAuxiliaryCopyOnWriteArrayLis();
      moduleManager = Client.getClient().getModuleManager();
      copyOnWriteListHolder = Client.getClient().getCopyOnWriteArrayListEntry();
   }
}
