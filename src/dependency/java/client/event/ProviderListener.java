package client.event;

import java.util.ArrayList;
import net.minecraft.util.Vec3;

public interface ProviderListener {
   ArrayList resolveArrayListByIndex(int count);

   Vec3 resolveVec3(int count, int count2);

   ArrayList resolveArrayList(int count);

   Vec3 resolveVec3(int count, int count2, int count3);
}
