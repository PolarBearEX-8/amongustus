package augustus.modules.movement;

import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.MotionSettingGroup;
import java.awt.Color;

public class MotionModifier extends Module implements MoveEntityListener, UpdateStateListener, MoveListener, NetworkPacketListener, PacketListener {
    public MotionSettingGroup motionHandler = new MotionSettingGroup(this);

    public MotionModifier() {
        super("MotionModifier", "BewegungsModifizierer", new Color(50, 168, 82), Category.MOVEMENT);
        this.motionHandler.updateState3();
    }

    @Override
    public void onEnable() {
        this.registerEvent(20);
        this.registerEvent(43);
        this.registerEvent(21);
        this.registerEvent(25);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.motionHandler.processAction();
    }

    @Override
    public void onPreDisable() {
        this.motionHandler.updateState2();
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        this.motionHandler.onPacket(packet);
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        this.motionHandler.onSendPacket(packet);
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        this.motionHandler.onMoveEntity(event);
    }

    @Override
    public void onMove(EventMove event) {
        this.motionHandler.onMove(event);
    }

    @Override
    public void onPreUpdateState() {
        this.motionHandler.updateState4();
    }

    @Override
    public void onPostUpdateState() {
        this.motionHandler.updateState();
    }
}
