package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.TabListIntegerType;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public interface HealthHandler {
   Map map = Maps.newHashMap();
   HealthHandler healthHandler3 = new HealthHandlerAdapter("dummy");
   HealthHandler healthHandler6 = new HealthHandlerAdapter("trigger");
   HealthHandler healthHandler5 = new HealthHandlerAdapter("deathCount");
   HealthHandler healthHandler4 = new HealthHandlerAdapter("playerKillCount");
   HealthHandler healthHandler2 = new HealthHandlerAdapter("totalKillCount");
   HealthHandler healthHandler = new HealthHandlerBridge("health");
   HealthHandler[] healthHandlerArray = new HealthHandler[]{
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.BLACK),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_BLUE),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_GREEN),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_AQUA),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_RED),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_PURPLE),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.GOLD),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.GRAY),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.DARK_GRAY),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.BLUE),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.GREEN),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.AQUA),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.RED),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.LIGHT_PURPLE),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.YELLOW),
      new VirtualStringEntry("teamkill.", ClientSettingsBlackType.WHITE)
   };
   HealthHandler[] healthHandlerArray2 = new HealthHandler[]{
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.BLACK),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_BLUE),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_GREEN),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_AQUA),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_RED),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_PURPLE),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.GOLD),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.GRAY),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.DARK_GRAY),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.BLUE),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.GREEN),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.AQUA),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.RED),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.LIGHT_PURPLE),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.YELLOW),
      new VirtualStringEntry("killedByTeam.", ClientSettingsBlackType.WHITE)
   };

   boolean isActive();

   String getLabelText();

   TabListIntegerType getINTEGER();

   int computeCount(List list);


}
