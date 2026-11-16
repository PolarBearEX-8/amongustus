package net.minecraft.item;

import client.event.PostMotionObserver;
import client.util.BaseMapEntry;
import client.util.DistanceCounter;
import client.util.DistanceFormatter;
import client.util.ExtendedExceptionAdapter;
import client.util.ExtendedListenerController;
import client.util.ExtendedMapHolder;
import client.util.InternalListenerRegistry;
import client.util.ListenerAdapter10;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;

public class DefaultItemEntry extends ExtendedMapHolder {
   public BaseMapEntry baseMapEntry;
   public static final ListenerAdapter10 listenerAdapter2 = new IronBoots((DefaultItemEntry)null, new ExtendedListenerController(1.0));
   public static final String labelText2 = "return ";
   public static final ListenerAdapter10 listenerAdapter10 = new IronBoots((DefaultItemEntry)null, new ExtendedListenerController(0.0));

   public void applyText(String text, double offset) {
      InternalListenerRegistry internalListenerRegistry = this.get(text);
      if (internalListenerRegistry != null) {
         internalListenerRegistry.setValue(offset);
      }
   }

   @Override
   public boolean matchesText(String text) {
      return super.matchesText(text) || text.equals("=");
   }

   public ListenerAdapter10 resolveListenerAdapter10(String text) throws ExtendedExceptionAdapter {
      String text2 = text.trim();
      if (text2.startsWith("return ")) {
         try {
            String text3 = text2.substring("return ".length());
            return (new IronBoots(this, this.resolvePostMotionObserver(text3))).getListenerAdapter10();
         } catch (Exception exception) {
            throw new ExtendedExceptionAdapter("Couldn't parse return '" + text2 + "' expression!");
         }
      } else {
         try {
            String[] textArray = this.resolveTextArray(text2);
            List list = this.resolveList(textArray);
            if (list.size() >= 3 && list.get(0) instanceof String) {
               Object entry = list.get(0);
               if (this.matchesTargetByTarget(entry) && list.get(1).equals("=")) {
                  String text4 = (String)list.get(0);
                  List list2 = list.subList(2, list.size());
                  InternalListenerRegistry internalListenerRegistry;
                  if (!this.map2.containsKey(text4) && !this.baseMapEntry.map.containsKey(text4)) {
                     internalListenerRegistry = new InternalListenerRegistry(text4, 0.0);
                     this.baseMapEntry.map.put(text4, internalListenerRegistry);
                  } else {
                     internalListenerRegistry = this.get(text4);
                  }

                  return new IronLeggings(this, internalListenerRegistry, this.resolvePostMotionObserver2(list2));
               }
            }

            return new IronBoots(this, this.resolvePostMotionObserver2(list));
         } catch (Exception exception2) {
            throw new ExtendedExceptionAdapter("Couldn't parse '" + text2 + "' expression!");
         }
      }
   }

   @Override
   public InternalListenerRegistry get(String text) {
      InternalListenerRegistry internalListenerRegistry = this.baseMapEntry == null ? null : (InternalListenerRegistry)this.baseMapEntry.map.get(text);
      if (internalListenerRegistry == null) {
         internalListenerRegistry = super.get(text);
      }

      if (internalListenerRegistry == null) {
         internalListenerRegistry = new InternalListenerRegistry(text, 0.0);
         this.applyInternalListenerRegistry(internalListenerRegistry);
      }

      return internalListenerRegistry;
   }

   private PostMotionObserver resolvePostMotionObserver2(List list) throws ExtendedExceptionAdapter {
      try {
         return this.resolvePostMotionObserver(list);
      } catch (Exception exception) {
         exception.printStackTrace();
         throw new ExtendedExceptionAdapter("Couldn't parse an expression!");
      }
   }

   public ListenerAdapter10 resolveListenerAdapter10(JsonElement jsonElement) throws ExtendedExceptionAdapter {
      if (jsonElement.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
         if (asJsonPrimitive.isString()) {
            try {
               return new IronBoots(this, new ExtendedListenerController((double)Float.parseFloat(asJsonPrimitive.getAsString())));
            } catch (Exception exception) {
               String asString = asJsonPrimitive.getAsString();
               return this.resolveListenerAdapterByText(asString);
            }
         } else {
            return new IronBoots(this, new ExtendedListenerController(asJsonPrimitive.getAsDouble()));
         }
      } else {
         return listenerAdapter10;
      }
   }

   public ListenerAdapter10 resolveListenerAdapterByText(String text) throws ExtendedExceptionAdapter {
      ArrayList list = new ArrayList();

      for (String text2 : text.toLowerCase().trim().split(";")) {
         if (!text2.trim().isEmpty()) {
            list.add(text2);
         }
      }

      if (list.size() == 0) {
         throw new ExtendedExceptionAdapter("Molang expression cannot be blank!");
      } else {
         BaseMapEntry baseMapEntry2 = new BaseMapEntry(this);
         this.baseMapEntry = baseMapEntry2;

         try {
            for (String text3 : (Iterable<String>)(Iterable)list) {
               baseMapEntry2.list.add(this.resolveListenerAdapter10(text3));
            }
         } catch (Exception exception) {
            this.baseMapEntry = null;
            throw exception;
         }

         this.baseMapEntry = null;
         return baseMapEntry2;
      }
   }

   public void applyText(String text, String text2) {
      this.map.put(text2, this.map.remove(text));
   }

   public DefaultItemEntry() {
      this.map.put("cos", DistanceFormatter.class);
      this.map.put("sin", DistanceCounter.class);
      String text = "math.abs";
      String text2 = "abs";
      this.applyText(text2, text);
      String text3 = "math.ceil";
      String text4 = "ceil";
      this.applyText(text4, text3);
      String text5 = "math.clamp";
      String text6 = "clamp";
      this.applyText(text6, text5);
      String text7 = "math.cos";
      String text8 = "cos";
      this.applyText(text8, text7);
      String text9 = "math.exp";
      String text10 = "exp";
      this.applyText(text10, text9);
      String text11 = "math.floor";
      String text12 = "floor";
      this.applyText(text12, text11);
      String text13 = "math.lerp";
      String text14 = "lerp";
      this.applyText(text14, text13);
      String text15 = "math.lerprotate";
      String text16 = "lerprotate";
      this.applyText(text16, text15);
      String text17 = "math.ln";
      String text18 = "ln";
      this.applyText(text18, text17);
      String text19 = "math.max";
      String text20 = "max";
      this.applyText(text20, text19);
      String text21 = "math.min";
      String text22 = "min";
      this.applyText(text22, text21);
      String text23 = "math.mod";
      String text24 = "mod";
      this.applyText(text24, text23);
      String text25 = "math.pow";
      String text26 = "pow";
      this.applyText(text26, text25);
      String text27 = "math.random";
      String text28 = "random";
      this.applyText(text28, text27);
      String text29 = "math.round";
      String text30 = "round";
      this.applyText(text30, text29);
      String text31 = "math.sin";
      String text32 = "sin";
      this.applyText(text32, text31);
      String text33 = "math.sqrt";
      String text34 = "sqrt";
      this.applyText(text34, text33);
      String text35 = "math.trunc";
      String text36 = "trunc";
      this.applyText(text36, text35);
   }
}
