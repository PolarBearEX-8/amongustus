package client.event;

import client.util.ByteBufHandler;

public interface PostMotionProcessor {
   void writeInt(ByteBufHandler byteBufHandler) throws Exception;

   void readInt(ByteBufHandler byteBufHandler) throws Exception;
}
