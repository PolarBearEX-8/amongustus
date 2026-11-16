package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;

public class CanSeeManager {
   public EntityMob entityMob;
   public List list = Lists.newArrayList();
   public List list2 = Lists.newArrayList();

   public boolean matchesEntityByEntity(Entity entity) {
      if (this.list.contains(entity)) {
         return true;
      } else if (this.list2.contains(entity)) {
         return false;
      } else {
         this.entityMob.worldObj.uNKNOWNManager.applyTextByText("canSee");
         boolean flag = this.entityMob.checkEntity4(entity);
         this.entityMob.worldObj.uNKNOWNManager.updateState();
         if (flag) {
            this.list.add(entity);
         } else {
            this.list2.add(entity);
         }

         return flag;
      }
   }

   public void updateState() {
      this.list.clear();
      this.list2.clear();
   }

   public CanSeeManager(EntityMob entityMob2) {
      this.entityMob = entityMob2;
   }
}
