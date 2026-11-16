package client.setting;

import client.event.EventBusManager;
import client.event.PostMotionInterceptor;
import client.module.Module;
import client.util.MinecraftAccessor;
import java.util.function.Supplier;

public abstract class ModeOptionSupplier implements PostMotionInterceptor, MinecraftAccessor {
   public boolean enabled;
   public Supplier supplier;
   public final String labelText;
   public final Module module;
   public final SettingGroup settingGroup;

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean booleanValue() {
      return this.supplier != null && (Boolean)this.supplier.get();
   }

   public abstract void updateState();

   public void applyIndex(int count, int count2) {
      if (this.isEnabled()) {
         EventBusManager.getDietrichEvents2().unsubscribe(count, this);
      } else {
         EventBusManager.getDietrichEvents2().subscribe(count, this, count2);
      }
   }

   public void applyIndex(int count) {
      this.applyIndex(count, 0);
   }

   @Override
   public String getString() {
      return this.labelText;
   }

   public void setSupplier(Supplier supplier2) {
      this.supplier = supplier2;
   }

   public abstract void clear();

   public void setEnabled(boolean enabled2) {
      if (this.enabled != enabled2) {
         this.updateState2();
         if (this.isEnabled()) {
            this.clear();
         } else {
            this.updateState();
         }
      }

      this.enabled = enabled2;
   }

   public abstract void updateState2();

   public ModeOptionSupplier(Module module2, SettingGroup settingGroup2, String text) {
      this.settingGroup = settingGroup2;
      this.labelText = text;
      this.module = module2;
   }
}
