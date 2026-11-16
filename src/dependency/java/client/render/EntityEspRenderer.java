package client.render;

import client.event.PreMotionConsumer;
import client.util.CustomFloatArrayEntry;
import net.minecraft.item.DiamondEssence;
import net.minecraft.util.AxisAlignedBB;

public class EntityEspRenderer implements PreMotionConsumer {
   public CustomFloatArrayEntry customFloatArrayEntry;
   public double value2;
   public double value3;
   public double value;

   @Override
   public boolean checkAxisAlignedBB(AxisAlignedBB boundingBox) {
      return this.checkCondition(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
   }

   @Override
   public boolean checkCondition(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.customFloatArrayEntry
         .checkDouble(posX - this.value2, posX2 - this.value3, posX3 - this.value, posX4 - this.value2, posY - this.value3, posZ - this.value);
   }

   @Override
   public void processDouble(double posX, double posY, double posZ) {
      this.value2 = posX;
      this.value3 = posY;
      this.value = posZ;
   }

   public boolean checkDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return this.customFloatArrayEntry
         .checkDoubleByDouble(posX - this.value2, posX2 - this.value3, posX3 - this.value, posX4 - this.value2, posY - this.value3, posZ - this.value);
   }

   public EntityEspRenderer(CustomFloatArrayEntry customFloatArrayEntry2) {
      this.customFloatArrayEntry = customFloatArrayEntry2;
   }

   public EntityEspRenderer() {
      this(DiamondEssence.getCustomFloatArrayEntry());
   }
}
