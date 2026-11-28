package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class EntitySenses {
   public EntityLiving entityObj;
   public List seenEntities = Lists.newArrayList();
   public List unseenEntities = Lists.newArrayList();

   public void clearSensingCache() {
      this.seenEntities.clear();
      this.unseenEntities.clear();
   }

   public EntitySenses(EntityLiving entityLiving) {
      this.entityObj = entityLiving;
   }

   public boolean canSee(Entity entity) {
      if (this.seenEntities.contains(entity)) {
         return true;
      } else if (this.unseenEntities.contains(entity)) {
         return false;
      } else {
         this.entityObj.entityWorld.theProfiler.startSection("canSee");
         boolean flag = this.entityObj.canEntityBeSeen(entity);
         this.entityObj.entityWorld.theProfiler.endSection();
         if (flag) {
            this.seenEntities.add(entity);
         } else {
            this.unseenEntities.add(entity);
         }

         return flag;
      }
   }
}
