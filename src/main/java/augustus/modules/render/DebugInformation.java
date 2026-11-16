package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.TrafficShapingTracker;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.DebugMetric;
import client.util.Packet;
import client.util.Pair;
import io.netty.channel.ChannelHandler;
import java.awt.Color;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.CAnimateHandPacket;
import org.lwjgl.opengl.GL11;

public class DebugInformation extends Module implements RenderListener, PacketListener, TickListener {
    public MultiSelectSetting settingDebugInformation;
    public NumberSetting settingScale;
    public NumberSetting settingPosX;
    public NumberSetting settingPosY;
    public GregorianCalendar gregorianCalendar;
    public ArrayList itemList3;
    public ArrayList speedHistory;
    public int keyCode2;
    public int tickDelay;
    public double reachDistance1;
    public double reachDistance;
    public float motionMultiplier;

    public DebugInformation() {
        super("DebugInformation", "DebugInformation", new Color(235, 122, 52), Category.RENDER);
        DebugMetric[] debugMetricArray = DebugMetric.values();
        this.settingDebugInformation = new MultiSelectSetting(this, "Debug information", debugMetricArray);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 0.5, 4.0, 2);
        this.settingPosX = new NumberSetting(this, "PosX", 0.0, 0.0, 100.0, 2);
        this.settingPosY = new NumberSetting(this, "PosY", 0.0, 0.0, 100.0, 2);
        this.gregorianCalendar = new GregorianCalendar();
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
        this.speedHistory = new ArrayList();
        this.keyCode2 = 0;
        this.tickDelay = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if ((packet2 instanceof C0APacketAnimation || packet2 instanceof CAnimateHandPacket || packet2 instanceof C08PacketPlayerBlockPlacement)
        && !minecraft.playerController.isState()
        && (!(packet2 instanceof C08PacketPlayerBlockPlacement) || ((C08PacketPlayerBlockPlacement)packet2).getEntityId() == 255)) {
            this.keyCode2++;
        }

        if (packet2 instanceof C03PacketPlayer) {
            if (!(packet2 instanceof C04PacketPlayerPosition) && !(packet2 instanceof C06PacketPlayerPosLook)) {
                this.processFloat(0.0F);
            } else {
                C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet2;
                double offset = Math.abs(c03PacketPlayer.getCoordinate1() - this.reachDistance1);
                float value = (float)(Math.hypot(Math.abs(c03PacketPlayer.getCoordinate3() - this.reachDistance), offset) * 20.0);
                this.processFloat(value);
                this.reachDistance1 = c03PacketPlayer.getCoordinate1();
                this.reachDistance = c03PacketPlayer.getCoordinate3();
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        GL11.glPushMatrix();
        GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        GL11.glTranslated(
        this.settingPosX.getValue() / 100.0 * (double)minecraft.displayWidth,
        this.settingPosY.getValue() / 100.0 * (double)minecraft.displayHeight,
        0.0
        );
        GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
        this.updateState();
        GL11.glPopMatrix();
        RenderUtils.processAction();
    }

    @Override
    public void onRunTick() {
        this.itemList3.add(this.keyCode2);

        while (this.itemList3.size() > 20) {
            this.itemList3.remove(0);
        }

        this.tickDelay = 0;

        for (Object object : this.itemList3) {
            int index = (Integer)object;
            this.tickDelay += index;
        }

        this.keyCode2 = 0;
    }

    private void updateState() {
        ArrayList arrayList = new ArrayList();
        if (DebugMetric.checkDebugMetric(DebugMetric.FPS)) {
            arrayList.add(new Pair("FPS", Minecraft.getLimitFramerate()));
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.PING)) {
            arrayList.add(
            new Pair(
            "Ping",
            ClientGlobals.moduleManager.hotbar.settingOtherPingCalculation.isEnabled()
            ? RuntimeBridge.calculatePingDelta()
            : (
            minecraft.getNetHandlerPlayClient() != null
            && minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(minecraft.thePlayer.getEntityUniqueID()) != null
            ? minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(minecraft.thePlayer.getEntityUniqueID()).getIndexOffset3()
            : 0
            )
            )
            );
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.CPS)) {
            arrayList.add(new Pair("CPS", this.tickDelay));
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.POS_X)) {
            arrayList.add(
            new Pair(
            "X",
            ClientGlobals.moduleManager.protector.isToggled()
            ? (int)minecraft.thePlayer.posX + ClientGlobals.moduleManager.protector.getIndex4()
            : (int)minecraft.thePlayer.posX
            )
            );
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.POS_Y)) {
            arrayList.add(new Pair("Y", (int)minecraft.thePlayer.posY));
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.POS_Z)) {
            arrayList.add(
            new Pair(
            "Z",
            ClientGlobals.moduleManager.protector.isToggled()
            ? (int)minecraft.thePlayer.posZ + ClientGlobals.moduleManager.protector.getIndex2()
            : (int)minecraft.thePlayer.posZ
            )
            );
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.BPS)) {
            arrayList.add(new Pair("BPS", this.motionMultiplier));
        }

        ChannelHandler channelHandler = minecraft.getNetHandlerPlayClient().getNetworkManager().channel.pipeline().get(TrafficShapingTracker.class);
        if (channelHandler instanceof TrafficShapingTracker) {
            TrafficShapingTracker trafficShapingTracker = (TrafficShapingTracker)channelHandler;
            if (DebugMetric.checkDebugMetric(DebugMetric.RX)) {
                arrayList.add(new Pair("RX", Math.round(trafficShapingTracker.getCoordinate()) + "/s"));
            }

            if (DebugMetric.checkDebugMetric(DebugMetric.TX)) {
                arrayList.add(new Pair("TX", Math.round(trafficShapingTracker.getCoordinate2()) + "/s"));
            }

            if (DebugMetric.checkDebugMetric(DebugMetric.DOWNLOAD)) {
                arrayList.add(new Pair("Download", RuntimeBridge.formatTimeDelta(trafficShapingTracker.getTimestamp2()) + "/s"));
            }

            if (DebugMetric.checkDebugMetric(DebugMetric.UPLOAD)) {
                arrayList.add(new Pair("Upload", RuntimeBridge.formatTimeDelta(trafficShapingTracker.getTimestamp()) + "/s"));
            }
        }

        Date date = null;
        if (DebugMetric.checkDebugMetric(DebugMetric.DATE)) {
            arrayList.add(new Pair("Date", DateFormat.getDateInstance(2).format(date = new Date())));
        }

        if (DebugMetric.checkDebugMetric(DebugMetric.TIME)) {
            arrayList.add(new Pair("Time", DateFormat.getTimeInstance(3).format(date == null ? new Date() : date)));
        }

        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair)arrayList.get(i);
            minecraft.fontRendererObj
            .getIndex2(
            (String)pair.getSecond() + ": " + ClientSettingsBlackType.GRAY + pair.getFirst(),
            0.0F,
            (float)(i * minecraft.fontRendererObj.indexOffset),
            -1
            );
        }
    }

    private void processFloat(float value) {
        this.speedHistory.add(value);

        while (this.speedHistory.size() > 5) {
            this.speedHistory.remove(0);
        }

        float value2 = 0.0F;

        for (float value3 : (Iterable<Float>) this.speedHistory) {
            value2 += value3;
        }

        this.motionMultiplier = (float)((double)Math.round(value2 / (float)this.speedHistory.size() * 100.0F) / 100.0);
    }
}
