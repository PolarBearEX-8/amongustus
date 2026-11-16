package augustus.modules.misc;

import client.module.Category;
import client.module.Module;
import client.util.ConfigManager;
import client.util.OnlineConfigsManager;
import java.awt.Color;
import java.io.File;

public class CustomHitSound extends Module {
   public CustomHitSound() {
      super("CustomHitSound", "KonfigurierbarerAngriffTon", new Color(66, 245, 191), Category.MISC);
      ConfigManager.applyString2(OnlineConfigsManager.text + File.separator + "hitsounds");
   }

   @Override
   public void onPreDisable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
   }
}
