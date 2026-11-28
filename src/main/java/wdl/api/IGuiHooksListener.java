package wdl.api;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;

public interface IGuiHooksListener extends IWDLMod {
   boolean onEntityGuiClosed(WorldClient world, Entity entity, Container container);

   boolean onBlockGuiClosed(WorldClient world, BlockPos pos, Container container);
}
