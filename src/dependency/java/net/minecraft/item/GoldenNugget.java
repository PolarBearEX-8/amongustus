package net.minecraft.item;

import client.event.SupplementalListener;
import client.module.Module;
import client.setting.SecondarySettingAdapter;
import client.util.ListenerAdapter25;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import java.util.List;

public class GoldenNugget extends SecondarySettingAdapter {
   public final ListenerAdapter25 listenerAdapter25 = new ListenerAdapter25(this, "L", Lists.newArrayList());

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter8().resolveJsonObject(this);
   }

   @Override
   public void updateState() {
      this.listenerAdapter25.updateState();
   }

   @Override
   public List getList() {
      return (List)this.listenerAdapter25.getValue();
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter8().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public void applyList(List list) {
      this.listenerAdapter25.applyObject2(list);
   }

   public GoldenNugget(Module module, String text) {
      super(module, text);
   }

   public GoldenNugget(Module module, String text, List list) {
      super(module, text);
      this.applyList(list);
      this.listenerAdapter25.setDataRef(list);
   }
}
