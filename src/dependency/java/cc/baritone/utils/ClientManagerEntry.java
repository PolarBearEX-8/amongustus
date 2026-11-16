package cc.baritone.utils;

import cc.baritone.api.IntArrayEntry;

public final class ClientManagerEntry {
   public final ClientManager clientManager = new ClientManager();
   public final AuthserverThealteningComManager authserverThealteningComManager = new AuthserverThealteningComManager();
   public static ClientManagerEntry clientManagerEntry;
   public ExtendedOptionType extendedOptionType;

   public static ClientManagerEntry getClientManagerEntryAsClientManagerEnt() {
      return resolveClientManagerEntry(ExtendedOptionType.THEALTENING);
   }

   public void applyExtendedOptionType(ExtendedOptionType extendedOptionType2) {
      if (extendedOptionType2 != null) {
         switch (IntArrayEntry.countArray[extendedOptionType2.ordinal()]) {
            case 1:
               this.authserverThealteningComManager.updateState2();
               break;
            case 2:
               this.authserverThealteningComManager.updateState();
         }

         this.extendedOptionType = this.clientManager.resolveExtendedOptionType(extendedOptionType2);
      }
   }

   private static ClientManagerEntry resolveClientManagerEntry(ExtendedOptionType extendedOptionType2) {
      if (clientManagerEntry == null) {
         clientManagerEntry = new ClientManagerEntry(extendedOptionType2);
      } else if (clientManagerEntry.getExtendedOptionType() != extendedOptionType2) {
         clientManagerEntry.applyExtendedOptionType(extendedOptionType2);
      }

      return clientManagerEntry;
   }

   public ExtendedOptionType getExtendedOptionType() {
      return this.extendedOptionType;
   }

   public static ClientManagerEntry getClientManagerEntry() {
      return resolveClientManagerEntry(ExtendedOptionType.MOJANG);
   }

   private ClientManagerEntry(ExtendedOptionType extendedOptionType2) {
      this.applyExtendedOptionType(extendedOptionType2);
   }
}
