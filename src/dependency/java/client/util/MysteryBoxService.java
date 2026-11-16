package client.util;

import client.network.MysteryBoxPacketListener;

public interface MysteryBoxService {
   MysteryBoxPacketListener mysteryBoxPacketListener = new MysteryBoxPacketListener();
}
