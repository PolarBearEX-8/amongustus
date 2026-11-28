package net.optifine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicLightsMap {
   private Map map = new HashMap();
   private List list = new ArrayList();
   private boolean dirty = false;

   public List valueList() {
      if (this.dirty) {
         this.list.clear();
         this.list.addAll(this.map.values());
         this.dirty = false;
      }

      return this.list;
   }

   public int size() {
      return this.map.size();
   }

   private void setDirty() {
      this.dirty = true;
   }

   public DynamicLight get(int n) {
      return (DynamicLight)this.map.get(n);
   }

   public void clear() {
      this.map.clear();
      this.list.clear();
      this.setDirty();
   }

   public DynamicLight put(int n, DynamicLight dynamicLight) {
      DynamicLight dynamicLight2 = (DynamicLight)(this.map.put(n, dynamicLight));
      this.setDirty();
      return dynamicLight2;
   }

   public DynamicLight remove(int n) {
      DynamicLight dynamicLight = (DynamicLight)this.map.remove(n);
      if (dynamicLight != null) {
         this.setDirty();
      }

      return dynamicLight;
   }
}
