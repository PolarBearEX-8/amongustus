package client.util;

import client.Client;
import com.google.common.collect.Maps;
import java.util.Map;

public class MapEntry {
   public final Map map = Maps.newLinkedHashMap();

   public SecondaryResourceLocationEntry getSecondaryResourceLocationEnt() {
      return (SecondaryResourceLocationEntry)this.map.get(Client.getClient().getModuleManager().emojify.settingModes.getSelected());
   }

   public void updateState() {
      this.map.clear();
      this.map.put("default1", new SecondaryResourceLocationEntry("default1.png", 6, 16));
      this.map.put("default2", new SecondaryResourceLocationEntry("default2.png", 4, 20));
      this.map.put("default3", new SecondaryResourceLocationEntry("default3.png", 8, 17));
      this.map.put("angry", new SecondaryResourceLocationEntry("angry.png", 5, 18));
      this.map.put("pogchamp", new SecondaryResourceLocationEntry("pogchamp.png", 3, 21));
      this.map.put("prettygood", new SecondaryResourceLocationEntry("prettygood.png", 3, 5, 18));
      this.map.put("trashmeme", new SecondaryResourceLocationEntry("trashmeme.png", 0, 5, 15));
   }

   public int getCountAsIndex() {
      return (int)Client.getClient().getModuleManager().emojify.settingComboHits.getValue();
   }

   public int getCount() {
      return (int)Client.getClient().getModuleManager().emojify.settingComboTime.getValue();
   }
}
