package client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EntityOffsetYController;

public class EntityOffsetYControlController {
   public Map map = new HashMap();
   public List list = new ArrayList();
   public boolean enabled = false;

   public EntityOffsetYController resolveEntityOffsetYController(int count, EntityOffsetYController entityOffsetYController) {
      EntityOffsetYController entityOffsetYController2 = ((EntityOffsetYController)this.map.put(count, entityOffsetYController));
      this.updateState2();
      return entityOffsetYController2;
   }

   public List getList() {
      if (this.enabled) {
         this.list.clear();
         this.list.addAll(this.map.values());
         this.enabled = false;
      }

      return this.list;
   }

   public EntityOffsetYController resolveEntityOffsetYControllerByIndex(int count) {
      EntityOffsetYController entityOffsetYController = (EntityOffsetYController)this.map.remove(count);
      if (entityOffsetYController != null) {
         this.updateState2();
      }

      return entityOffsetYController;
   }

   public void updateState() {
      this.map.clear();
      this.list.clear();
      this.updateState2();
   }

   public EntityOffsetYController resolveEntityOffsetYController(int count) {
      return (EntityOffsetYController)this.map.get(count);
   }

   public int size() {
      return this.map.size();
   }

   private void updateState2() {
      this.enabled = true;
   }
}
