package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import client.util.AuxiliaryListenerAdapter6;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import java.util.List;

public class ItemListSetting extends SecondarySettingAdapter {
   public final AuxiliaryListenerAdapter6 auxiliaryListenerAdapter6 = new AuxiliaryListenerAdapter6(this, "L", Lists.newArrayList());

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatterAsRotationFormatte().resolveJsonObject(this);
   }

   @Override
   public void updateState() {
      this.auxiliaryListenerAdapter6.updateState();
   }

   @Override
   public List getList() {
      return (List)this.auxiliaryListenerAdapter6.getValue();
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatterAsRotationFormatte().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public void applyList(List list) {
      this.auxiliaryListenerAdapter6.applyObject2(list);
   }

   public ItemListSetting(Module module, String text) {
      super(module, text);
   }

   public ItemListSetting(Module module, String text, List list) {
      super(module, text);
      this.applyList(list);
      this.auxiliaryListenerAdapter6.setDataRef(list);
   }
}
