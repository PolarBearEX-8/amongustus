package net.augustus.modules.combat;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.BooleanValue;
import net.minecraft.entity.Entity;

public class TargetArea extends Module {
   private final RangeSetting x;
   private final RangeSetting y;
   private final RangeSetting z;
   private final BooleanValue reverse;

   public TargetArea() {
       super("TargetArea", "GegnerBereich", Color.red, Categorys.COMBAT);
      this.x = (RangeSetting)(new RangeSetting(this, "x", -1000.0, 1000.0, -1000.0, 1000.0, 0)).markVisualOnly();
      this.y = (RangeSetting)(new RangeSetting(this, "y", 0.0, 255.0, 0.0, 255.0, 0)).markVisualOnly();
      this.z = (RangeSetting)(new RangeSetting(this, "z", -1000.0, 1000.0, -1000.0, 1000.0, 0)).markVisualOnly();
      this.reverse = (BooleanValue)(new BooleanValue(this, "Reverse", false)).markVisualOnly();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public boolean isEntityInArea(Entity entity) {
      boolean flag;
      if (entity.posX >= this.x.getMinValue()) {
if (entity.posX <= this.x.getMaxValue() && entity.posY >= this.y.getMinValue()) {
if (entity.posY <= this.y.getMaxValue() && entity.posZ >= this.z.getMinValue()) {
if (entity.posZ <= this.z.getMaxValue()) {
flag = true;
} else {
flag = false;
}
} else {
flag = false;
}
} else {
flag = false;
}
} else {
flag = false;
}

      boolean flag2 = flag;
      return this.reverse.getValue() != flag2;
   }

}
