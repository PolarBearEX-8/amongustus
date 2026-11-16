package client.util;

import client.event.IndirectListener;
import client.event.PreUpdateObserver;
import client.setting.OptionManager;
import java.util.HashMap;
import java.util.Map;

public class AimWriter implements IndirectListener {
   public Map map = new HashMap();

   @Override
   public PreUpdateObserver get(String text) {
      return (RayTraceDeserializerTargetObserver)this.map.get(text);
   }

   public AimWriter(OptionManager[] optionManagerArray) {
      for (int index = 0; index < optionManagerArray.length; index++) {
         OptionManager optionManager = optionManagerArray[index];
         if (optionManager instanceof RayTraceDeserializer) {
            RayTraceDeserializer rayTraceDeserializer = (RayTraceDeserializer)optionManager;
            RayTraceDeserializerTargetObserver rayTraceDeserializerTargetObserver = new RayTraceDeserializerTargetObserver(rayTraceDeserializer);
            this.map.put(optionManager.getLabelText(), rayTraceDeserializerTargetObserver);
         }
      }
   }
}
