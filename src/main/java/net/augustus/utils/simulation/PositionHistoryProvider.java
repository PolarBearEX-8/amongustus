package net.augustus.utils.simulation;

import java.util.ArrayList;
import net.minecraft.util.Vec3;

public interface PositionHistoryProvider {
   Vec3 getPlayerPositionAtTick(int n, int n2, int n3);

   ArrayList getPlayerTrajectory(int n);

   Vec3 getTargetPositionAtTick(int n, int n2);

   ArrayList getTargetTrajectory(int n);
}
