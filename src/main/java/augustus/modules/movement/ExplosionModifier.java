package augustus.modules.movement;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class ExplosionModifier extends Module implements PacketListener {
    public NumberSetting settingXZMultiplierExplosion = new NumberSetting(this, "XZMultiplierExplosion", 1.0, 0.0, 10.0, 4);
    public NumberSetting settingYMultiplierExplosion = new NumberSetting(this, "YMultiplierExplosion", 1.0, 0.0, 10.0, 4);
    public BooleanSetting settingModifyVelocityAfterExplosion = new BooleanSetting(this, "ModifyVelocityAfterExplosion", true);
    public NumberSetting settingXZMultiplierVelocity = new NumberSetting(this, "XZMultiplierVelocity", 1.0, 0.0, 10.0, 4);
    public NumberSetting settingYMultiplierVelocity = new NumberSetting(this, "YMultiplierVelocity", 1.0, 0.0, 10.0, 4);
    public TimeHelper timer = new TimeHelper();

    public ExplosionModifier() {
        super("ExplosionModifier", "ExplosionModifizierer", new Color(235, 91, 52), Category.MOVEMENT);
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
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if ((byte)((packet2 instanceof S27PacketExplosion) ? 1 : 0) != 0) {
            S27PacketExplosion s27PacketExplosion = (S27PacketExplosion)packet2;
            double offset;
            if ((byte)(
                    (offset = minecraft.thePlayer.getDouble4(s27PacketExplosion.getCoordinate2(), s27PacketExplosion.getCoordinate3(), s27PacketExplosion.getCoordinate()) - 5.0) == 0.0
                        ? 0
                        : (offset < 0.0 ? -1 : 1)
                )
                < 0) {
                this.timer.reset();
            }

            s27PacketExplosion.processAction3((float)((double)s27PacketExplosion.getScaleFactor2() * this.settingXZMultiplierExplosion.getValue()));
            s27PacketExplosion.processAction2((float)((double)s27PacketExplosion.getScaleFactor3() * this.settingYMultiplierExplosion.getValue()));
            s27PacketExplosion.processAction((float)((double)s27PacketExplosion.getScaleFactor() * this.settingXZMultiplierExplosion.getValue()));
        }

        if ((byte)((packet2 instanceof S12PacketEntityVelocity) ? 1 : 0) != 0) {
            if (!this.timer.hasReached(200L)) {
                if (this.settingModifyVelocityAfterExplosion.isEnabled()) {
                    S12PacketEntityVelocity s12PacketEntityVelocity = (S12PacketEntityVelocity)packet2;
                    s12PacketEntityVelocity.setEntityId3((int)((double)s12PacketEntityVelocity.getEntityId3() * this.settingXZMultiplierVelocity.getValue()));
                    s12PacketEntityVelocity.setEntityId2((int)((double)s12PacketEntityVelocity.getEntityId2() * this.settingYMultiplierVelocity.getValue()));
                    s12PacketEntityVelocity.setEntityId4((int)((double)s12PacketEntityVelocity.getEntityId4() * this.settingXZMultiplierVelocity.getValue()));
                }
            }
        }
    }
}
