package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public abstract class PassiveFloatEntry {
   public float factor;
   public boolean enabled;
   public boolean enabled2 = true;
   public List list = Lists.newArrayList();
   public Map map = Maps.newHashMap();
   public int count2 = 64;
   public int count = 32;

   public BoxesManager resolveBoxesManager(Random random) {
      return (BoxesManager)this.list.get(random.nextInt(this.list.size()));
   }

   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
   }

   public void put(String text, int count3, int count4) {
      this.map.put(text, new RotationAdapter(count3, count4));
   }

   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
   }

   public void applyPassiveFloatEntry(PassiveFloatEntry passiveFloatEntry) {
      this.factor = passiveFloatEntry.factor;
      this.enabled = passiveFloatEntry.enabled;
      this.enabled2 = passiveFloatEntry.enabled2;
   }

   public static void applyBoxesManager(BoxesManager boxesManager, BoxesManager boxesManager2) {
      boxesManager2.factor = boxesManager.factor;
      boxesManager2.factor9 = boxesManager.factor9;
      boxesManager2.factor8 = boxesManager.factor8;
      boxesManager2.factor5 = boxesManager.factor5;
      boxesManager2.factor2 = boxesManager.factor2;
      boxesManager2.factor10 = boxesManager.factor10;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
   }

   public RotationAdapter get(String text) {
      return (RotationAdapter)this.map.get(text);
   }
}
