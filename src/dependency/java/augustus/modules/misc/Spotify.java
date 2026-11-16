package augustus.modules.misc;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSettingImpl;
import com.viaversion.viaversion.libs.gson.ClientRedirectController;
import java.awt.Color;

public class Spotify extends Module implements RenderListener {
   public final ActionSettingImpl actionSettingImpl = new ActionSettingImpl(this, "Authorize with Spotify", this::updateState2);
   public final ActionSettingImpl actionSettingImpl2 = new ActionSettingImpl(this, "Set Spotify API Key", this::updateState);
   public String labelText4 = "";
   public boolean enabled3 = false;

   public Spotify() {
      super("Spotify", "Spotify", Color.GREEN, Category.FUN);
   }

   @Override
   public void onPreDisable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(32);
   }

   private void updateState() {
      ClientRedirectController.updateState4();
      ClientRedirectController.resourceLocation = null;
      this.enabled3 = true;
      ClientRedirectController.schedule();
   }

   private void updateState2() {
      try {
         this.labelText4 = ClientRedirectController.getText();
      } catch (Exception exception) {
         throw new RuntimeException(exception);
      }
   }
}
