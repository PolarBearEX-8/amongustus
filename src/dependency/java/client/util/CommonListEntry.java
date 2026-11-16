package client.util;

import client.event.PostMotionDispatcher;
import client.setting.AutoPlayTargetEntity;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;

public class CommonListEntry implements PostMotionDispatcher {
   public final List list = Lists.newArrayList();
   public final Random random = new Random();
   public final ResourceLocation resourceLocation;
   public final AutoPlayTargetEntity autoPlayTargetEntity;
   public double value2;
   public double value;

   @Override
   public int getCount() {
      int count = 0;

      for (PostMotionDispatcher postMotionDispatcher : (Iterable<PostMotionDispatcher>)this.list) {
         count += postMotionDispatcher.getCount();
      }

      return count;
   }

   @Override
   public Object getTarget() {
      return this.getAuxiliaryResourceLocationHol();
   }

   public AuxiliaryResourceLocationHolder getAuxiliaryResourceLocationHol() {
      int count = this.getCount();
      if (!this.list.isEmpty() && count != 0) {
         int count2 = this.random.nextInt(count);

         for (PostMotionDispatcher postMotionDispatcher : (Iterable<PostMotionDispatcher>)this.list) {
            count2 -= postMotionDispatcher.getCount();
            if (count2 < 0) {
               AuxiliaryResourceLocationHolder target = (AuxiliaryResourceLocationHolder)postMotionDispatcher.getTarget();
               target.setValue(target.getValue() * this.value2);
               target.setValue2(target.getValue2() * this.value);
               return target;
            }
         }

         return SoundHandler.auxiliaryResourceLocationHolder;
      } else {
         return SoundHandler.auxiliaryResourceLocationHolder;
      }
   }

   public void add(PostMotionDispatcher postMotionDispatcher) {
      this.list.add(postMotionDispatcher);
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public AutoPlayTargetEntity getAutoPlayTargetEntity() {
      return this.autoPlayTargetEntity;
   }

   public CommonListEntry(ResourceLocation location, double offset, double offset2, AutoPlayTargetEntity autoPlayTargetEntity2) {
      this.resourceLocation = location;
      this.value = offset2;
      this.value2 = offset;
      this.autoPlayTargetEntity = autoPlayTargetEntity2;
   }
}
