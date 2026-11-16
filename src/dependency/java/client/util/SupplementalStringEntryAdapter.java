package client.util;

import client.Client;
import java.util.Iterator;
import net.minecraft.util.ChatComponentText;

public class SupplementalStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      String text2 = "§6[§9" + Client.getClient().getString4() + "§6] ";
      MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry().applyIChatComponent(new ChatComponentText("\n\n" + text2 + "§7Help commands:"));
      Iterator iterator = Client.getClient().getEventCommandCallbackAdapter().getList().iterator();

      while (iterator.hasNext()) {
         ((AuxiliaryStringEntry)iterator.next()).updateState();
      }
   }

   public SupplementalStringEntryAdapter() {
      super("help");
   }
}
