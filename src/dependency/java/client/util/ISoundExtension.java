package client.util;

import client.event.PreUpdateConsumer;
import net.minecraft.client.audio.ISound;

public interface ISoundExtension extends ISound, PreUpdateConsumer {
   boolean isEnabled();
}
