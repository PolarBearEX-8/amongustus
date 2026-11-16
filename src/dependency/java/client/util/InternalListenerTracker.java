package client.util;

import client.Client;
import client.event.PostMotionBroadcaster;
import client.module.Module;
import net.minecraft.entity.EntityBeeAdapter;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class InternalListenerTracker implements PostMotionBroadcaster {
   @Override
   public TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      if (((Module)Client.getClient().getModuleManager().exploitFixer).isToggled() && countArray.length == 0) {
         return null;
      } else {
         int count2 = countArray.length > 1 ? countArray[1] : 0;
         return new EntityBeeAdapter(world, posX, posX2, posX3, posX4, posY, posZ, Item.processAction(countArray[0]), count2);
      }
   }
}
