package augustus.modules.movement;

import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventEntitySync;
import client.event.EventEntitySyncCallback;
import client.event.EventLiquidCollision;
import client.event.EventLiquidCollisionCallback;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.SimulatedPlayer;
import client.via.ViaProtocolHelper;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class FreeCam extends Module implements ClickListener, MoveListener, RenderListener, EventLiquidCollisionCallback, EventEntitySyncCallback, TickListener {
    public NumberSetting settingSpeed = new NumberSetting(this, "Speed", 1.0, 0.0, 4.0, 2);
    public BooleanSetting settingSpectatorRender = new BooleanSetting(this, "Spectator Render", false);
    public BooleanSetting settingDisplayCoordinates = new BooleanSetting(this, "Display Coordinates", false);
    public BooleanSetting settingUpdateRaytrace = new BooleanSetting(this, "Update Raytrace", false);
    public BooleanSetting settingShowRealPlayerModel = new BooleanSetting(this, "Show real Player Model", true);
    public ColorSetting settingHighlightColor;
    public ColorSetting settingTextColor;
    public boolean expanded2;
    public Vec3 positionVec;
    public Vec3 positionVec2;
    public double reachDistance6;
    public double reachDistance5;
    public double reachDistance1;
    public double reachDistance4;
    public double reachDistance;
    public double reachDistance3;
    public float motionMultiplier;
    public float motionMultiplier7;
    public float motionMultiplier8;
    public float motionMultiplier2;
    public static int tickDelay;

    public FreeCam() {
        super("FreeCam", "FreieKamera", new Color(235, 116, 52), Category.MOVEMENT);
        ColorSetting colorSetting = new ColorSetting(this, "Highlight Color", new Color(5, 245, 69, 50));
        this.settingHighlightColor = (ColorSetting)colorSetting.getSetting(() -> this.settingDisplayCoordinates.isEnabled());
        ColorSetting colorSetting2 = new ColorSetting(this, "Text Color", new Color(255, 255, 255, 255));
        this.settingTextColor = (ColorSetting)colorSetting2.getSetting(() -> this.settingDisplayCoordinates.isEnabled());
        this.expanded2 = false;
        this.positionVec = new Vec3(0.0, 0.0, 0.0);
        this.positionVec2 = new Vec3(0.0, 0.0, 0.0);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(21, 100);
        this.registerEvent(32);
        this.registerEvent(57);
        this.registerEvent(58);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        float rotationYaw = minecraft.thePlayer.rotationYaw;
        float rotationPitch = minecraft.thePlayer.rotationPitch;
        ClientGlobals.rotationManager.applyRotation2(new Rotation(rotationYaw, rotationPitch), 3, 1898960900, this);
        ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
        viaProtocolHelper.scaleFactor1 = rotationYaw;
        viaProtocolHelper.scaleFactor2 = rotationPitch;
        viaProtocolHelper.scaleFactor3 = rotationYaw;
        viaProtocolHelper.scaleFactor = rotationPitch;
        minecraft.thePlayer.rotationYaw = rotationYaw;
        minecraft.thePlayer.rotationPitch = rotationPitch;
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            this.motionMultiplier = minecraft.thePlayer.rotationYaw;
            this.motionMultiplier7 = minecraft.thePlayer.rotationPitch;
            this.motionMultiplier8 = minecraft.thePlayer.rotationYaw;
            this.motionMultiplier2 = minecraft.thePlayer.rotationPitch;
            this.positionVec = minecraft.thePlayer.getVec32(1.0F);
            this.positionVec2 = minecraft.thePlayer.getVec32(0.0F);
        }
    }

    @Override
    public void onEntitySync(EventEntitySync event) {
        event.setActive(true);
    }

    @Override
    public void onClick(EventClick event) {
        if (!this.settingUpdateRaytrace.isEnabled()) {
            event.cancel();
            event.setState2(true);
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingDisplayCoordinates.isEnabled()) {
            Vec3 vec = SimulatedPlayer.processAction(minecraft.thePlayer.rotationPitch, minecraft.thePlayer.rotationYaw).getVec3(200.0);
            Vec3 vec2 = this.positionVec2.getVec39(this.positionVec, (double)partialTicks);
            BlockPos blockPos = minecraft.theWorld
                .getMovingObjectPosition(
                    vec2.xCoord,
                    vec2.yCoord + (double)minecraft.thePlayer.getHeight2(),
                    vec2.zCoord,
                    vec.xCoord + vec2.xCoord,
                    vec.yCoord + vec2.yCoord + (double)minecraft.thePlayer.getHeight2(),
                    vec.zCoord + vec2.zCoord
                )
                .getBlockPos();
            String text = "x=" + blockPos.getX() + " y=" + blockPos.getY() + " z=" + blockPos.getZ();
            minecraft.fontRendererObj
                .getIndex(
                    text,
                    (scaledResolution.getScaledWidth() - minecraft.fontRendererObj.getIndex(text)) / 2,
                    scaledResolution.getScaledHeight() / 2 + minecraft.fontRendererObj.indexOffset * 3,
                    this.settingTextColor.getInt12(0)
                );
        }
    }

    @Override
    public void onLiquidCollision(EventLiquidCollision event) {
        if (this.settingShowRealPlayerModel.isEnabled()) {
            EntityPlayerSP player = minecraft.thePlayer;
            double posX = player.posX;
            double posY = player.posY;
            double posZ = player.posZ;
            double lastTickPosX = player.lastTickPosX;
            double lastTickPosY = player.lastTickPosY;
            double lastTickPosZ = player.lastTickPosZ;
            player.posX = this.reachDistance6;
            player.posY = this.reachDistance5;
            player.posZ = this.reachDistance1;
            player.lastTickPosX = this.reachDistance4;
            player.lastTickPosY = this.reachDistance;
            player.lastTickPosZ = this.reachDistance3;
            minecraft.renderGlobal.indexOffset++;
            event.canExecute();
            player.posX = posX;
            player.posY = posY;
            player.posZ = posZ;
            player.lastTickPosX = lastTickPosX;
            player.lastTickPosY = lastTickPosY;
            player.lastTickPosZ = lastTickPosZ;
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.onReset();
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.motionMultiplier8 = minecraft.thePlayer.rotationYaw;
        this.motionMultiplier2 = minecraft.thePlayer.rotationPitch;
        if (this.settingDisplayCoordinates.isEnabled()) {
            Vec3 vec = SimulatedPlayer.processAction(minecraft.thePlayer.rotationPitch, minecraft.thePlayer.rotationYaw).getVec3(200.0);
            Vec3 vec2 = this.positionVec2.getVec39(this.positionVec, (double)partialTicks);
            BlockPos blockPos = minecraft.theWorld
                .getMovingObjectPosition(
                    vec2.xCoord,
                    vec2.yCoord + (double)minecraft.thePlayer.getHeight2(),
                    vec2.zCoord,
                    vec.xCoord + vec2.xCoord,
                    vec.yCoord + vec2.yCoord + (double)minecraft.thePlayer.getHeight2(),
                    vec.zCoord + vec2.zCoord
                )
                .getBlockPos();
            RenderUtils.processAction2(
                (float)blockPos.getX(),
                (float)blockPos.getY(),
                (float)blockPos.getZ(),
                (float)(blockPos.getX() + 1),
                (float)(blockPos.getY() + 1),
                (float)(blockPos.getZ() + 1)
            );
            GL11.glPopMatrix();
            RenderUtils.prepareScissor();
            GL11.glEnable(3008);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public void onClientTick() {
        minecraft.thePlayer.rotationPitch = this.motionMultiplier2;
    }

    @Override
    public void onPostTick() {
        minecraft.thePlayer.rotationPitch = minecraft.thePlayer.scaleFactor27;
    }

    public static void processAction(int index) {
        tickDelay = index;
    }

    public static int getIndex() {
        return tickDelay;
    }

    public static int getInt2() {
        return getIndex() == 0 ? 60 : 0;
    }
}
