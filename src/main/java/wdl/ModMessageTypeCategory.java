package wdl;


import wdl.api.ModInfo;
public class ModMessageTypeCategory extends MessageTypeCategory {
   private ModInfo mod;

   @Override
   public String getDisplayName() {
      return this.mod.getDisplayName();
   }

   public ModMessageTypeCategory(ModInfo modInfo) {
      super(modInfo.id);
   }
}
