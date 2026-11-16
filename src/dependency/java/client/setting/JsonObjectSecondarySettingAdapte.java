package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import java.util.List;

public class JsonObjectSecondarySettingAdapte extends SecondarySettingAdapter {
   public final JsonObjectValuePropertyImpl jsonObjectValuePropertyImpl = new JsonObjectValuePropertyImpl(this, "L", Lists.newArrayList());

   @Override
   public void updateState() {
      this.jsonObjectValuePropertyImpl.updateState();
   }

   @Override
   public List getList() {
      return (List)this.jsonObjectValuePropertyImpl.getValue();
   }

   @Override
   public void applyList(List list) {
      this.jsonObjectValuePropertyImpl.applyObject2(list);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter().resolveJsonObject(this);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter().applyPostMotionInterceptor(this, jsonObject);
   }

   public JsonObjectSecondarySettingAdapte(Module module, String text, List list) {
      super(module, text);
      this.applyList(list);
      this.jsonObjectValuePropertyImpl.setDataRef(list);
   }

   public JsonObjectSecondarySettingAdapte(Module module, String text) {
      super(module, text);
   }
}
