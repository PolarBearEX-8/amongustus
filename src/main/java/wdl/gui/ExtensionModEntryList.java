package wdl.gui;

import wdl.api.ModInfo;
import wdl.api.WDLApi;
import java.util.ArrayList;

public class ExtensionModEntryList extends ArrayList {
   public final ExtensionModList parentList;

   public ExtensionModEntryList(ExtensionModList extensionModList) {
       super();
       this.parentList = extensionModList;

      for (ModInfo modInfo : (Iterable<ModInfo>)(WDLApi.getWDLMods().values())) {
         this.add(new GuiWDLExtensionsModEntry(this.parentList, modInfo));
      }
   }
}
