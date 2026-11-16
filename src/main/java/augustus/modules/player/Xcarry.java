package augustus.modules.player;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import java.awt.Color;
import net.minecraft.network.play.client.C0DPacketCloseWindow;

public class Xcarry extends Module implements PacketListener {
    public Xcarry() {
        super("Xcarry", "Behalten des X Förmigen Inventares", Color.WHITE, Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (((C0DPacketCloseWindow)packet.getPacket()).getIndex() == minecraft.thePlayer.container2.indexOffset2) {
            packet.cancel();
        }
    }
}
