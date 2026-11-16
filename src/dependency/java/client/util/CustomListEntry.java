package client.util;

import client.setting.AutoPlayTargetEntity;
import com.google.common.collect.Lists;
import java.util.List;

public class CustomListEntry {
   public final List list = Lists.newArrayList();
   public boolean enabled;
   public AutoPlayTargetEntity autoPlayTargetEntity;

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public List getList() {
      return this.list;
   }

   public void setAutoPlayTargetEntity(AutoPlayTargetEntity autoPlayTargetEntity2) {
      this.autoPlayTargetEntity = autoPlayTargetEntity2;
   }

   public AutoPlayTargetEntity getAutoPlayTargetEntity() {
      return this.autoPlayTargetEntity;
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
