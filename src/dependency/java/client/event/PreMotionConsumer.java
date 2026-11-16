package client.event;

import net.minecraft.util.AxisAlignedBB;

public interface PreMotionConsumer {
   boolean checkAxisAlignedBB(AxisAlignedBB boundingBox);

   boolean checkCondition(double posX, double posX2, double posX3, double posX4, double posY, double posZ);

   void processDouble(double posX, double posY, double posZ);
}
