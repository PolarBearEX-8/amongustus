package wdl.gui;

import wdl.EntityUtils;
import wdl.WDLMessageTypes;
import wdl.WDLMessages;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiWDLEntitiesListEntries extends ArrayList {
   public final GuiEntityList parentList;

   public GuiWDLEntitiesListEntries(GuiEntityList guiEntityList) throws Error {
       super();
       this.parentList = guiEntityList;

      try {
         int n = 0;
         Multimap entitiesByGroup = EntityUtils.getEntitiesByGroup();
         ArrayList list = new ArrayList(entitiesByGroup.keySet());
         list.remove("Passive");
         list.remove("Hostile");
         list.remove("Other");
         Collections.sort(list);
         list.add(0, "Hostile");
         list.add(1, "Passive");
         list.add("Other");

         for (String str : (Iterable<String>)(list)) {
            GuiWDLEntitiesCategoryEntry entry = new GuiWDLEntitiesCategoryEntry(this.parentList, str);
            this.add(entry);
            ArrayList list2 = new ArrayList(entitiesByGroup.get(str));
            Collections.sort(list2);

            for (String str2 : (Iterable<String>)(list2)) {
               this.add(new GuiWDLEntitiesEntityEntry(this.parentList, entry, str2));
               int stringWidth = GuiWDLEntities.getFontRenderer(this.parentList.parentGui).getStringWidth(str2);
               if (stringWidth > n) {
                  n = stringWidth;
               }
            }
         }

         GuiEntityList.setLargestWidth(this.parentList, n);
         GuiEntityList.setTotalWidth(this.parentList, GuiEntityList.getLargestWidth(this.parentList) + 255);
      } catch (Exception e) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.ERROR, "wdl.messages.generalError.failedToSetUpEntityUI", new Object[]{e});
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      }
   }
}
