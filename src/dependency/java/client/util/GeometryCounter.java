package client.util;

import client.setting.OptionManager;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GeometryCounter {
   public String labelText = null;
   public Map map = new LinkedHashMap();
   public Set set = new LinkedHashSet();

   public void remove(String text) {
      this.set.remove(text);
   }

   public GeometryCounter(String text) {
      this.labelText = text;
   }

   public void put(String text, String text2) {
      this.map.put(text, text2);
   }

   public void add(String text) {
      this.set.add(text);
   }

   public void putAll(GeometryCounter geometryCounter) {
      if (geometryCounter != null) {
         this.map.putAll(geometryCounter.map);
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String get(String text) {
      return (String)this.map.get(text);
   }

   public boolean contains(String text) {
      return this.set.contains(text);
   }

   public Collection getCollection() {
      return new LinkedHashSet(this.set);
   }

   public void addAll(Collection collection) {
      this.set.addAll(collection);
   }

   public String[] getTextArray() {
      Set keys = this.map.keySet();
      return (String[]) keys.toArray(new String[keys.size()]);
   }

   public void applyOptionManagerArray(OptionManager[] optionManagerArray) {
      for (int index = 0; index < optionManagerArray.length; index++) {
         OptionManager optionManager = optionManagerArray[index];
         String labelText2 = optionManager.getLabelText();
         String text = (String)this.map.get(labelText2);
         if (text != null) {
            optionManager.matchesText3(text);
         }
      }
   }
}
