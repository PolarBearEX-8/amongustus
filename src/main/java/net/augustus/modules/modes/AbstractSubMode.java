package net.augustus.modules.modes;

import net.augustus.config.NamedConfigComponent;
import net.augustus.events.EventManager;
import java.util.function.Supplier;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.interfaces.MC;

public abstract class AbstractSubMode implements MC, NamedConfigComponent {
   private boolean enabled;
   private Supplier visibilitySupplier;
   private final String name;
   public final Module parentModule;
   public final SettingGroup modeSetting;

   public void setEnabled(boolean flag) {
      if (this.enabled != flag) {
         this.updateEventSubscriptions();
         if (this.isEnabled()) {
            this.onDisable();
         } else {
            this.onEnable();
         }
      }

      this.enabled = flag;
   }

   public void setVisibilitySupplier(Supplier supplier) {
      this.visibilitySupplier = supplier;
   }

   public void toggleEventSubscription(int n, int n2) {
      if (this.isEnabled()) {
         EventManager.getEventBus().unsubscribe(n, this);
      } else {
         EventManager.getEventBus().subscribe(n, this, n2);
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public abstract void updateEventSubscriptions();

   public AbstractSubMode(Module module, SettingGroup doubleValue, String str) {
      this.modeSetting = doubleValue;
      this.name = str;
      this.parentModule = module;
   }

   public String getName() {
      return this.name;
   }

   public void toggleEventSubscription(int n) {
      this.toggleEventSubscription(n, (byte)0);
   }

   public abstract void onDisable();

   public abstract void onEnable();

   public boolean isSelected() {
      return this.visibilitySupplier != null && (Boolean)this.visibilitySupplier.get();
   }



}
