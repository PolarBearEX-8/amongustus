package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;

public class SupplementalListenerSupplementalLi extends AbstractSupplementalListenerSetting {
   public final JsonObjectValueProperty jsonObjectValueProperty = new JsonObjectValueProperty(this, "A", new String[0]);

   public String[] getTextArrayAsTextArray() {
      return (String[])this.jsonObjectValueProperty.getValue();
   }

   @Override
   public void updateState() {
      this.jsonObjectValueProperty.updateState();
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter12().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter12().resolveJsonObject(this);
   }

   @Override
   public Object[] getTargetArray() {
      return this.getTextArrayAsTextArray();
   }

   @Override
   public void applyTargetArray(Object[] entryArray) {
      String[] textArray = (String[])entryArray;
      this.applyTextArray(textArray);
   }

   public SupplementalListenerSupplementalLi(Module module, String text) {
      super(module, text);
   }

   public void applyTextArray(String[] textArray) {
      this.jsonObjectValueProperty.applyObject2(textArray);
   }

   public SupplementalListenerSupplementalLi(Module module, String text, String[] textArray) {
      super(module, text);
      this.applyTextArray(textArray);
      this.jsonObjectValueProperty.setDataRef(textArray);
   }
}
