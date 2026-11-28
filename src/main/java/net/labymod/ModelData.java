package net.labymod;

import com.thealtening.api.TheAlteningException;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

public class ModelData extends SettingOverlay {
   public SettingOverlay[] model;
   public SettingOverlay[] texture;
   public SettingOverlay[] item;
   @NoSerialization
   @SerializedName("v")
   public int version;
   @NoSerialization
   private boolean active = true;

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this.clone_m1();
   }

   public void setVersion(int n) {
      this.version = n;
   }

   public void setSubModels(SettingOverlay[] settingOverlays) {
      this.model = settingOverlays;
   }

   @Override
   public boolean equals(Object obj) {
      try {
         if (this == obj) {
            return true;
         }
      } catch (TheAlteningException e) {
         throw e;
      }

      try {
         if (!super.equals(obj)) {
            return false;
         }
      } catch (TheAlteningException e2) {
         throw e2;
      }

      try {
         if (this.getClass() != obj.getClass()) {
            return false;
         }
      } catch (TheAlteningException e3) {
         throw e3;
      }

      ModelData modelData = (ModelData)obj;

      try {
         if (!Arrays.equals((Object[])this.model, (Object[])modelData.model)) {
            return false;
         }
      } catch (TheAlteningException e4) {
         throw e4;
      }

      try {
         if (!Arrays.equals((Object[])this.texture, (Object[])modelData.texture)) {
            return false;
         }
      } catch (TheAlteningException e5) {
         throw e5;
      }

      return Arrays.equals((Object[])this.item, (Object[])modelData.item);
   }

   public boolean isActive() {
      return this.active;
   }

   public int getVersion() {
      return this.version;
   }

   public void setTextureModels(SettingOverlay[] settingOverlays) {
      this.texture = settingOverlays;
   }

   public void setActive(boolean flag) {
      this.active = flag;
   }

   public void setItemModels(SettingOverlay[] settingOverlays) {
      this.item = settingOverlays;
   }

   @Override
   public int hashCode() {
      byte b = 31;
      int n = super.hashCode();
      n = 31 * n + Arrays.hashCode(this.item);
      n = 31 * n + Arrays.hashCode(this.model);
      return 31 * n + Arrays.hashCode(this.texture);
   }

   public ModelData clone_m1() {
      ModelData modelData = new ModelData();

      modelData.model = this.model != null ? Arrays.copyOf(this.model, this.model.length) : null;
      modelData.texture = this.texture != null ? Arrays.copyOf(this.texture, this.texture.length) : null;
      modelData.item = this.item != null ? Arrays.copyOf(this.item, this.item.length) : null;
      modelData.version = this.version;
      modelData.active = this.active;
      return modelData;
   }

}
