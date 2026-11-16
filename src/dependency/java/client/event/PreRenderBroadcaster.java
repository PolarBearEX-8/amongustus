package client.event;

import java.awt.image.BufferedImage;

public interface PreRenderBroadcaster {
   void updateState();

   BufferedImage resolveBufferedImage(BufferedImage bufferedImage);
}
