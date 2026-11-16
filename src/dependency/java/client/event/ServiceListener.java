package client.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface ServiceListener {
   TileEntity resolveTileEntityByWorld(World world, int count);
}
