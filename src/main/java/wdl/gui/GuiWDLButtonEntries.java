package wdl.gui;

import java.util.ArrayList;

public class GuiWDLButtonEntries extends ArrayList {
   public final GuiWDLButtonList parentList;

   public GuiWDLButtonEntries(GuiWDLButtonList guiWDLButtonList) {
       super();
       this.parentList = guiWDLButtonList;
      this.add(new GuiWDLButtonEntry(this.parentList, "worldOverrides", new GuiWDLWorld(this.parentList.parentGui), true));
      this.add(new GuiWDLButtonEntry(this.parentList, "generatorOverrides", new GuiWDLGenerator(this.parentList.parentGui), true));
      this.add(new GuiWDLButtonEntry(this.parentList, "playerOverrides", new GuiWDLPlayer(this.parentList.parentGui), true));
      this.add(new GuiWDLButtonEntry(this.parentList, "entityOptions", new GuiWDLEntities(this.parentList.parentGui), true));
      this.add(new GuiWDLButtonEntry(this.parentList, "backupOptions", new GuiWDLBackup(this.parentList.parentGui), true));
      this.add(new GuiWDLButtonEntry(this.parentList, "messageOptions", new GuiWDLMessages(this.parentList.parentGui), false));
      this.add(new GuiWDLButtonEntry(this.parentList, "permissionsInfo", new GuiWDLPermissions(this.parentList.parentGui), false));
      this.add(new GuiWDLButtonEntry(this.parentList, "about", new GuiWDLAbout(this.parentList.parentGui), false));
   }
}
