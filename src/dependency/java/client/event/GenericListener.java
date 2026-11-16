package client.event;

import client.util.AlertNotificationSound;
import client.util.ExceptionAdapter;
import java.io.IOException;
import net.minecraft.world.World;

public interface GenericListener {
   void applyWorld(World world, AlertNotificationSound alertNotificationSound) throws ExceptionAdapter, IOException;

   AlertNotificationSound resolveAlertNotificationSound(World world, int count, int count2) throws IOException;

   void updateState();

   void updateState2();

   void applyWorldByWorld(World world, AlertNotificationSound alertNotificationSound) throws IOException;
}
