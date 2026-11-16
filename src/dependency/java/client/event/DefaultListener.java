package client.event;

import java.util.Map;
import net.minecraft.block.Block;

public interface DefaultListener {
   Map resolveMap(Block block);
}
