package baritone.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsRegistry {
   public final Map byLowerName = new HashMap();
   public final List allSettings = new ArrayList();
   public final Map settingTypes = new HashMap();
}
