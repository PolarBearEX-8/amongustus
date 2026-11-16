package augustus.modules.misc;

import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChestContainer;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.util.Vec3;

public class FlagDetector extends Module implements ClickListener, PacketListener, RenderListener {
    public BooleanSetting settingTeleportFix;
    public BooleanSetting settingFlagCounter;
    public BooleanSetting settingSendChatMessage;
    public BooleanSetting settingShowOverHotbar;
    public BooleanSetting settingInventoryFlags;
    public BooleanSetting settingTextShadow;
    public NumberSetting settingXCoord;
    public NumberSetting settingYCoord;
    public Vec3 positionVec;
    public TimeHelper timer;
    public long timestampMillis;
    public TimeHelper timer2;

    public FlagDetector() {
        super("FlagDetector", "Flaggendetektor", new Color(232, 180, 135), Category.MISC);
        this.settingTeleportFix = new BooleanSetting(this, "TeleportFix", true);
        this.settingFlagCounter = new BooleanSetting(this, "FlagCounter", true);
        this.settingSendChatMessage = new BooleanSetting(this, "SendChatMessage", true);
        this.settingShowOverHotbar = new BooleanSetting(this, "ShowOverHotbar", false);
        this.settingInventoryFlags = new BooleanSetting(this, "InventoryFlags", false);
        this.settingTextShadow = new BooleanSetting(this, "TextShadow", false);
        this.settingXCoord = new NumberSetting(this, "X-Coord", 50.0, 0.0, 100.0, 2).getNumberSetting(() -> this.settingShowOverHotbar.isEnabled());
        this.settingYCoord = new NumberSetting(this, "Y-Coord", 0.0, 0.0, 100.0, 2).getNumberSetting(() -> this.settingShowOverHotbar.isEnabled());
        this.positionVec = new Vec3(0.0, 0.0, 0.0);
        this.timer = new TimeHelper();
        this.timestampMillis = 0L;
        this.timer2 = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(26);
        this.registerEvent(32);
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
        if (packet2 instanceof C04PacketPlayerPosition) {
            C04PacketPlayerPosition c04PacketPlayerPosition = (C04PacketPlayerPosition)packet2;
            this.positionVec = new Vec3(c04PacketPlayerPosition.getCoordinate1(), c04PacketPlayerPosition.getCoordinate(), c04PacketPlayerPosition.getCoordinate3());
        }

        if (packet2 instanceof C06PacketPlayerPosLook) {
            C06PacketPlayerPosLook c06PacketPlayerPosLook = (C06PacketPlayerPosLook)packet2;
            this.positionVec = new Vec3(c06PacketPlayerPosLook.getCoordinate1(), c06PacketPlayerPosLook.getCoordinate(), c06PacketPlayerPosLook.getCoordinate3());
        }

        if (packet2 instanceof C0EPacketClickWindow) {
            this.timer2.reset();
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.settingTeleportFix.isEnabled()) {
            if (minecraft.thePlayer.getHeldItem() == null) {
                return;
            }

            if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemEnderPearl)) {
                return;
            }

            if (minecraft.gameSettings.keyBindUseItem.getPressTime() > 0) {
                this.timer.reset();
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingShowOverHotbar.isEnabled()) {
            ScaledResolution scaledResolution2 = new ScaledResolution(minecraft);
            StringBuilder sb = new StringBuilder().append("§cFlag Detected");
            String text = this.settingFlagCounter.isEnabled() ? ": §7" + this.timestampMillis : "";
            String text2 = sb.append(text).toString();
            int scaledHeight = scaledResolution2.getScaledHeight();
            double offset = (double)(scaledHeight - minecraft.fontRendererObj.indexOffset) / 100.0;
            int index = (int)(offset * this.settingYCoord.getValue());
            int scaledWidth = scaledResolution2.getScaledWidth();
            double offset2 = (double)(scaledWidth - minecraft.fontRendererObj.getIndex(text2)) / 100.0;
            int index2 = (int)(offset2 * this.settingXCoord.getValue());
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            int rGB = Color.white.getRGB();
            fontRendererObj.getIndex(text2, index2, index, rGB, this.settingTextShadow.isEnabled());
        }

        RenderUtils.processAction();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook s08PacketPlayerPosLook = (S08PacketPlayerPosLook)packet2;
            double offset;
            if ((
                            (offset = this.positionVec.getCoordinate4(new Vec3(s08PacketPlayerPosLook.getCoordinate(), s08PacketPlayerPosLook.getCoordinate1(), s08PacketPlayerPosLook.getCoordinate4())) - 40.0)
                                    == 0.0
                                ? 0
                                : (offset < 0.0 ? -1 : 1)
                        )
                        < 0
                    && this.timer.hasReached(4000L)
                || !this.settingTeleportFix.isEnabled()) {
                this.timestampMillis++;
                if (this.settingSendChatMessage.isEnabled()) {
                    StringBuilder sb = new StringBuilder().append("§cFlag Detected");
                    String text = this.settingFlagCounter.isEnabled() ? ": §7" + this.timestampMillis : "";
                    String text2 = sb.append(text).toString();
                    RayTraceUtil.applyString(text2);
                }
            }
        }

        if (packet2 instanceof S30PacketWindowItems) {
            if (this.settingInventoryFlags.isEnabled()) {
                if (!this.timer2.hasReached(150L)) {
                    if (minecraft.currentScreen == null) {
                        return;
                    }

                    if (minecraft.currentScreen instanceof GuiChestContainer) {
                        return;
                    }

                    this.timestampMillis++;
                    if (this.settingSendChatMessage.isEnabled()) {
                        StringBuilder sb2 = new StringBuilder().append("§cInventory flag detected");
                        String text3 = this.settingFlagCounter.isEnabled() ? ": §7" + this.timestampMillis : "";
                        String text4 = sb2.append(text3).toString();
                        RayTraceUtil.applyString(text4);
                    }
                }
            }
        }
    }
}
