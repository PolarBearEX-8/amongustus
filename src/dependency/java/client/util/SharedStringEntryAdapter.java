package client.util;

import client.Client;
import client.module.Module;

public class SharedStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      ((Module)Client.getClient().getModuleManager().mysteryBox).setEnabled(false);
      RayTraceUtil.applyString("Opened MysteryBox");
   }

   public SharedStringEntryAdapter() {
      super("mysterybox");
   }
}
