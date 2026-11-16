package augustus.modules.render;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventPacket;
import client.event.EventRenderEntity;
import client.event.PacketListener;
import client.event.RenderEntityListener;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.EmojiUuidMapping;
import client.util.Packet;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S19PacketEntityStatus;

public class Emojify extends Module implements PacketListener, AttackEntityListener, RenderEntityListener {
    public ModeSetting settingModes = new ModeSetting(
        this, "Modes", "default1", new String[]{"default1", "default2", "default3", "angry", "pogchamp", "prettygood", "trashmeme"}
    );
    public NumberSetting settingComboHits = new NumberSetting(this, "ComboHits", 2.0, 0.0, 5.0, 0);
    public NumberSetting settingComboTime = new NumberSetting(this, "ComboTime", 2.0, 0.0, 5.0, 0);
    public EmojiUuidMapping uUIDEntry = new EmojiUuidMapping();

    public Emojify() {
        super("Emojify", "Emojify", new Color(66, 135, 245), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
        this.registerEvent(1);
        this.registerEvent(31, 100);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.uUIDEntry.mapEntry.updateState();
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        EntityLivingBase targetEntity = event.getTargetEntity();
        if (targetEntity instanceof EntityPlayer) {
            this.uUIDEntry.applyEntityPlayer((EntityPlayer)targetEntity, event.getCoordinate3(), event.getCoordinate(), event.getCoordinate2());
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        this.uUIDEntry.applyEntity2(event.getEntity(), 389592725, 63226);
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S19PacketEntityStatus) {
            S19PacketEntityStatus s19PacketEntityStatus = (S19PacketEntityStatus)packet2;
            if (s19PacketEntityStatus.processValue() == 2 && minecraft.theWorld != null) {
                Entity entity = s19PacketEntityStatus.getEntity(minecraft.theWorld);
                if (entity != null) {
                    this.uUIDEntry.applyEntity(entity);
                }
            }
        }
    }
}
