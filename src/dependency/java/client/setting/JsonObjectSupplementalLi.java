package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;

public class JsonObjectSupplementalLi extends AbstractSupplementalListenerSetting {
   public final ListenerValuePropertySupport listenerValuePropertySupport = new ListenerValuePropertySupport(this, "A", new Double[0]);

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter13().resolveJsonObject(this);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter13().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public void updateState() {
      this.listenerValuePropertySupport.updateState();
   }

   @Override
   public Object[] getTargetArray() {
      return this.getDoubleArray();
   }

   @Override
   public void applyTargetArray(Object[] entryArray) {
      Double[] offsetArray = (Double[])entryArray;
      this.applyDoubleArray(offsetArray);
   }

   @Override
   public void updateState3() {
   }

   public JsonObjectSupplementalLi(Module module, String text, Double[] offsetArray) {
      super(module, text);
      this.applyDoubleArray(offsetArray);
      this.listenerValuePropertySupport.setDataRef(offsetArray);
   }

   public void applyDoubleArray(Double[] offsetArray) {
      this.listenerValuePropertySupport.applyObject2(offsetArray);
   }

   public Double[] getDoubleArray() {
      return (Double[])this.listenerValuePropertySupport.getValue();
   }

   public JsonObjectSupplementalLi(Module module, String text) {
      super(module, text);
   }
}
