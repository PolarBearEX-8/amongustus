package cc.baritone.utils;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;

public interface DepthFirstAuditor extends SpecialListener {
   boolean matchesWorldClientByWorldClient(WorldClient world, Entity entity, Container container);

   boolean matchesWorldClientByWorldClient(WorldClient world, BlockPos blockPos, Container container);
}
