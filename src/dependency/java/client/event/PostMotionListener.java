package client.event;

import net.minecraft.entity.Entity;

public interface PostMotionListener {
   String getTextAsText();

   Entity getEntityAsEntity();
}
