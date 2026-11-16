package augustus.modules.render;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.DefaultVertexFormats;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.PlayerUtil;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Line extends Module implements RenderListener, TickListener, PacketListener {
    public SettingGroup settingMoveLine;
    public BooleanSetting settingActivateMoveLine;
    public ColorSetting settingColor;
    public NumberSetting settingLineWidth;
    public NumberSetting settingLineTime;
    public BooleanSetting settingResetOnTeleportMoveLine;
    public SettingGroup settingAuraLine;
    public BooleanSetting settingActivateAuraLine;
    public BooleanSetting settingDisplayLegitimateRotations;
    public BooleanSetting settingDisplayRotationsAtCurrentPosition;
    public ColorSetting settingAuraColor;
    public NumberSetting settingAuraLineWidth;
    public NumberSetting settingAuraLineTime;
    public BooleanSetting settingResetOnTeleportAuraLine;
    public ShaderOptions settingResetOnTeleportAuraLine2;
    public ArrayList itemList3;
    public ArrayList itemList4;
    public ArrayList itemList5;
    public ArrayList rotationHistory;
    public ArrayList itemList8;
    public ArrayList itemList6;
    public ArrayList itemList7;
    public int tickDelay;
    public double[] coordOffsetArray;
    public static int[] intValueArray;

    public Line() {
        super("Line", "Linie", new Color(10, 20, 15), Category.RENDER);
        this.settingMoveLine = new SettingGroup(this, "MoveLine", true);
        this.settingActivateMoveLine = (BooleanSetting)new BooleanSetting(this, "ActivateMoveLine", true, this.settingMoveLine).getSetting("Activate");
        Color color = new Color(77, 0, 255, 255);
        this.settingColor = (ColorSetting)new ColorSetting(this, "Color", color, this.settingMoveLine).getSetting(() -> this.settingActivateMoveLine.isEnabled());
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 2.0, 0.1, 4.0, 2, this.settingMoveLine).getNumberSetting(() -> this.settingActivateMoveLine.isEnabled());
        this.settingLineTime = new NumberSetting(this, "LineTime", 3000.0, 500.0, 20000.0, 0, this.settingMoveLine).getNumberSetting(() -> this.settingActivateMoveLine.isEnabled());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "ResetOnTeleportMoveLine", false, this.settingMoveLine);
        this.settingResetOnTeleportMoveLine = (BooleanSetting)(((BooleanSetting)booleanSetting2.getSetting("ResetOnTeleport")).getSetting(() -> this.settingActivateMoveLine.isEnabled()));
        this.settingAuraLine = new SettingGroup(this, "AuraLine", true);
        this.settingActivateAuraLine = (BooleanSetting)new BooleanSetting(this, "ActivateAuraLine", false, this.settingAuraLine).getSetting("Activate");
        this.settingDisplayLegitimateRotations = (BooleanSetting)new BooleanSetting(this, "Display legitimate rotations", false, this.settingAuraLine).getSetting(() -> this.settingActivateAuraLine.isEnabled());
        this.settingDisplayRotationsAtCurrentPosition = (BooleanSetting)new BooleanSetting(this, "Display rotations at current position", false, this.settingAuraLine).getSetting(() -> this.isDisplayRotationsAtCurrentPositionVisible());
        Color color2 = new Color(77, 0, 255, 255);
        ColorSetting colorSetting2 = new ColorSetting(this, "AuraColor", color2, this.settingAuraLine);
        this.settingAuraColor = (ColorSetting)(((ColorSetting)colorSetting2.getSetting("Color")).getSetting(() -> this.settingActivateAuraLine.isEnabled()));
        this.settingAuraLineWidth = new NumberSetting(this, "AuraLineWidth", 2.0, 0.1, 4.0, 2, this.settingAuraLine).getNumberSetting("LineWidth").getNumberSetting(() -> this.settingActivateAuraLine.isEnabled());
        this.settingAuraLineTime = new NumberSetting(this, "AuraLineTime", 3000.0, 500.0, 20000.0, 0, this.settingAuraLine).getNumberSetting("LineTime").getNumberSetting(() -> this.settingActivateAuraLine.isEnabled());
        BooleanSetting booleanSetting6 = new BooleanSetting(this, "ResetOnTeleportAuraLine", false, this.settingAuraLine);
        this.settingResetOnTeleportAuraLine = (BooleanSetting)(((BooleanSetting)booleanSetting6.getSetting("ResetOnTeleport")).getSetting(() -> this.settingActivateAuraLine.isEnabled()));
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingResetOnTeleportAuraLine2 = shaderOptions.getShaderOptions(black).getShaderOptions2();
        this.itemList3 = new ArrayList();
        this.itemList4 = new ArrayList();
        this.itemList5 = new ArrayList();
        this.rotationHistory = new ArrayList();
        this.itemList8 = new ArrayList();
        this.itemList6 = new ArrayList();
        this.itemList7 = new ArrayList();
        this.tickDelay = -100;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.itemList3 = new ArrayList();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S07PacketRespawn) {
            S07PacketRespawn s07PacketRespawn = (S07PacketRespawn)packet2;
            if (s07PacketRespawn.getEntityId() != this.tickDelay) {
                this.tickDelay = s07PacketRespawn.getEntityId();
                this.updateState();
            }
        }

        if (packet2 instanceof S08PacketPlayerPosLook) {
            if (this.settingResetOnTeleportMoveLine.isEnabled()) {
                this.itemList3.clear();
            }

            if (this.settingResetOnTeleportAuraLine.isEnabled()) {
                this.updateState();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingActivateMoveLine.isEnabled()) {
            RenderUtils.updateState();
            GL11.glLineWidth((float)this.settingLineWidth.getValue());
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            MovementHandler.processBoolean(
                this.settingResetOnTeleportAuraLine2.canExecute(),
                this.settingResetOnTeleportAuraLine2.canExecute2()
            );
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(partialTicks, renderPass));
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtils.prepareScissor();
        }

        if (this.settingActivateAuraLine.isEnabled()) {
            if (this.settingDisplayLegitimateRotations.isEnabled()) {
                RenderUtils.updateState();
                RenderUtils.setColor(this.settingColor.getInt12(0));
                GL11.glLineWidth((float)this.settingLineWidth.getValue());
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                if (!this.canExecute5()) {
                    this.itemList4.add(System.currentTimeMillis());
                    this.rotationHistory.add(new float[]{minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch});
                    this.itemList6.add(minecraft.thePlayer.getVec35(partialTicks));
                    if (!this.itemList4.isEmpty()) {
                        if ((double)(System.currentTimeMillis() - (Long)this.itemList4.get(0)) > this.settingAuraLineTime.getValue()) {
                            this.itemList4.remove(0);
                            this.rotationHistory.remove(0);
                            this.itemList6.remove(0);

                            while (true) {
                                if (this.itemList4.isEmpty()) {
                                    this.updateState8();
                                    GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                                    break;
                                }

                                if (!((double)(System.currentTimeMillis() - (Long)this.itemList4.get(0)) > this.settingAuraLineTime.getValue())) {
                                    this.updateState8();
                                    GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                                    break;
                                }

                                this.itemList4.remove(0);
                                this.rotationHistory.remove(0);
                                this.itemList6.remove(0);
                            }
                        } else {
                            this.updateState8();
                            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                        }
                    } else {
                        this.updateState8();
                        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                    }
                } else {
                    this.updateState8();
                    GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                }

                RenderUtils.prepareScissor();
            }
        }
    }

    @Override
    public void onTick() {
        this.coordOffsetArray = (double[])null;
        long time2 = System.currentTimeMillis();
        if (this.settingActivateMoveLine.isEnabled()) {
            double lastTickPosX = minecraft.thePlayer.lastTickPosX;
            double posX = minecraft.thePlayer.posX;
            double lastTickPosX2 = minecraft.thePlayer.lastTickPosX;
            float value3 = (float)(lastTickPosX + (posX - lastTickPosX2));
            double lastTickPosY = minecraft.thePlayer.lastTickPosY;
            double offset = this.settingLineWidth.getValue() / 100.0;
            double posY = minecraft.thePlayer.posY;
            double lastTickPosY2 = minecraft.thePlayer.lastTickPosY;
            float value4 = (float)(lastTickPosY + offset + (posY - lastTickPosY2));
            double lastTickPosZ = minecraft.thePlayer.lastTickPosZ;
            double posZ = minecraft.thePlayer.posZ;
            double lastTickPosZ2 = minecraft.thePlayer.lastTickPosZ;
            float value5 = (float)(lastTickPosZ + (posZ - lastTickPosZ2));
            if (!this.itemList3.isEmpty()) {
                ArrayList itemList32 = this.itemList3;
                int index = this.itemList3.size() - 1;
                double[] coordArray = (double[])itemList32.get(index);
                if (coordArray[0] != (double)value3 || coordArray[1] != (double)value4 || coordArray[2] != (double)value5) {
                    this.itemList3.add(new double[]{(double)value3, (double)value4, (double)value5, (double)time2});
                }
            } else {
                this.itemList3.add(new double[]{(double)value3, (double)value4, (double)value5, (double)time2});
            }

            while (0 < this.itemList3.size()) {
                double[] coordArray2 = (double[])this.itemList3.get(0);
                if (!this.checkCondition(coordArray2[3])) {
                    break;
                }

                this.itemList3.remove(0);
                this.coordOffsetArray = coordArray2;
            }
        } else if (!this.itemList3.isEmpty()) {
            this.itemList3.clear();
            this.coordOffsetArray = (double[])null;
        }

        if (this.settingActivateAuraLine.isEnabled()) {
            if (this.settingDisplayLegitimateRotations.isEnabled()) {
                float[] factorArray = null;
                Vec3 vec = null;
                if (!this.canExecute6()) {
                    factorArray = new float[]{minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch};
                    vec = minecraft.thePlayer.getVec35(1.0F);
                    this.itemList5.add(time2);
                    this.itemList8.add(factorArray);
                    this.itemList7.add(vec);

                    while (!this.itemList5.isEmpty()) {
                        long time3 = (Long)this.itemList5.get(0);
                        double offset2 = (double)(time2 - time3);
                        double value = this.settingAuraLineTime.getValue();
                        if (!(offset2 > value)) {
                            break;
                        }

                        this.itemList5.remove(0);
                        this.itemList8.remove(0);
                        this.itemList7.remove(0);
                    }
                }

                if (this.canExecute5()) {
                    if (!this.canExecute6()) {
                        this.itemList4.add(time2);
                        ArrayList settings22 = this.rotationHistory;
                        if (factorArray == null) {
                            factorArray = new float[]{minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch};
                        }

                        settings22.add(factorArray);
                        ArrayList itemList62 = this.itemList6;
                        if (vec == null) {
                            vec = minecraft.thePlayer.getVec35(1.0F);
                        }

                        itemList62.add(vec);

                        while (!this.itemList4.isEmpty()) {
                            long time4 = (Long)this.itemList4.get(0);
                            double offset3 = (double)(time2 - time4);
                            double value2 = this.settingAuraLineTime.getValue();
                            if (!(offset3 > value2)) {
                                break;
                            }

                            this.itemList4.remove(0);
                            this.rotationHistory.remove(0);
                            this.itemList6.remove(0);
                        }
                    }
                }
            }
        }
    }

    public static void setIntValueArray(int[] countArray) {
        intValueArray = countArray;
    }

    private void applyRenderPass(float partialTicks, RenderPass renderPass) {
        this.applyRenderPass2(renderPass, partialTicks);
    }

    public static int[] getIntValueArray() {
        return intValueArray;
    }

    private void updateState() {
        this.itemList8.clear();
        this.itemList7.clear();
        this.itemList5.clear();
        this.rotationHistory.clear();
        this.itemList6.clear();
        this.itemList4.clear();
        this.coordOffsetArray = null;
        this.itemList3.clear();
    }

    private void updateState8() {
        GL11.glPushMatrix();
        Vec3 vec = new Vec3(
            minecraft.getRenderManager().getRenderPosX(), minecraft.getRenderManager().getRenderPosY(), minecraft.getRenderManager().getRenderPosZ()
        );
        GL11.glDisable(3008);
        GL11.glBegin(3);
        GlStateManager.color4f2(
            this.settingAuraColor.getFloat(),
            this.settingAuraColor.getFloat2(),
            this.settingAuraColor.getFloat4(),
            1.0F
        );
        Vec3 vec2 = vec.getVec312(0.0, (double)minecraft.thePlayer.getHeight2(), 0.0);

        for (int i = 0; i < this.itemList6.size(); i++) {
            float[] factorArray = (float[])this.rotationHistory.get(i);
            float[] factorArray2 = (float[])this.rotationHistory.get(Math.max(i - 1, 0));
            this.itemList4.get(Math.max(i - 1, 0));
            this.itemList4.get(i);
            if (i > 0) {
                this.settingAuraColor.getInt12(0);
            }

            float float5 = MathHelper.cos(-factorArray[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
            float float7 = MathHelper.abs(-factorArray[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
            float value = -MathHelper.cos(-factorArray[1] * (float) (Math.PI / 180.0));
            float float72 = MathHelper.abs(-factorArray[1] * (float) (Math.PI / 180.0));
            Vec3 vec3 = new Vec3(this.settingDisplayRotationsAtCurrentPosition.isEnabled() ? vec2 : (Vec3)this.itemList6.get(i));
            vec3.xCoord = vec3.xCoord + ((double)(float7 * value) - vec.xCoord);
            vec3.yCoord = vec3.yCoord + ((double)float72 - vec.yCoord);
            vec3.zCoord = vec3.zCoord + ((double)(float5 * value) - vec.zCoord);
            GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
        }

        GL11.glEnd();
        RenderUtils.processAction(255, 0, 0, 255);

        for (int i2 = 0; i2 < this.itemList8.size(); i2++) {
            float[] factorArray3 = (float[])this.itemList8.get(i2);
            float float52 = MathHelper.cos(-factorArray3[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
            float float73 = MathHelper.abs(-factorArray3[0] * (float) (Math.PI / 180.0) - (float) Math.PI);
            float value2 = -MathHelper.cos(-factorArray3[1] * (float) (Math.PI / 180.0));
            float float74 = MathHelper.abs(-factorArray3[1] * (float) (Math.PI / 180.0));
            Vec3 vec4 = new Vec3(this.settingDisplayRotationsAtCurrentPosition.isEnabled() ? vec2 : (Vec3)this.itemList7.get(i2));
            vec4.xCoord = vec4.xCoord + ((double)(float73 * value2) - vec.xCoord);
            vec4.yCoord = vec4.yCoord + ((double)float74 - vec.yCoord);
            vec4.zCoord = vec4.zCoord + ((double)(float52 * value2) - vec.zCoord);
            RenderUtils.processAction4(
                vec4.xCoord - 0.002, vec4.yCoord - 0.002, vec4.zCoord - 0.002, vec4.xCoord + 0.002, vec4.yCoord + 0.002, vec4.zCoord + 0.002
            );
        }

        GL11.glEnable(3008);
        RenderUtils.processAction(255, 255, 255, 255);
        GL11.glPopMatrix();
    }

    private boolean isDisplayRotationsAtCurrentPositionVisible() {
        return !this.settingDisplayLegitimateRotations.isEnabled() ? false : this.settingDisplayLegitimateRotations.isVisible();
    }

    public void applyRenderPass2(RenderPass renderPass, float partialTicks) {
        if (this.itemList3.size() >= 2) {
            RenderUtils.setColor(
                this.settingResetOnTeleportAuraLine2.getIndex(renderPass, this.settingColor.getInt12(0))
            );
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
            if (this.coordOffsetArray != null) {
                double[] coordArray = (double[])this.itemList3.get(0);
                double[] coordArray2 = new double[]{
                    PlayerUtil.getDouble(this.coordOffsetArray[0], coordArray[0], (double)partialTicks),
                    PlayerUtil.getDouble(this.coordOffsetArray[1], coordArray[1], (double)partialTicks),
                    PlayerUtil.getDouble(this.coordOffsetArray[2], coordArray[2], (double)partialTicks)
                };
                worldRenderer.getWorldRenderer(
                        (double)((float)coordArray2[0]) - minecraft.getRenderManager().getRenderPosX(),
                        (double)((float)coordArray2[1]) - minecraft.getRenderManager().getRenderPosY(),
                        (double)((float)coordArray2[2]) - minecraft.getRenderManager().getRenderPosZ()
                    )
                    .updateState();
            }

            for (int i = 0; i < this.itemList3.size() - 1; i++) {
                ArrayList itemList32 = this.itemList3;
                double[] coordArray3 = (double[])itemList32.get(i);
                worldRenderer.getWorldRenderer(
                        (double)((float)coordArray3[0]) - minecraft.getRenderManager().getRenderPosX(),
                        (double)((float)coordArray3[1]) - minecraft.getRenderManager().getRenderPosY(),
                        (double)((float)coordArray3[2]) - minecraft.getRenderManager().getRenderPosZ()
                    )
                    .updateState();
            }

            ArrayList itemList33 = this.itemList3;
            int index = this.itemList3.size() - 1;
            double[] coordArray4 = (double[])itemList33.get(index);
            double[] coordArray5 = (double[])this.itemList3.get(this.itemList3.size() - 2);
            worldRenderer.getWorldRenderer(
                    (double)((float)PlayerUtil.getDouble(coordArray5[0], coordArray4[0], (double)partialTicks)) - minecraft.getRenderManager().getRenderPosX(),
                    (double)((float)PlayerUtil.getDouble(coordArray5[1], coordArray4[1], (double)partialTicks)) - minecraft.getRenderManager().getRenderPosY(),
                    (double)((float)PlayerUtil.getDouble(coordArray5[2], coordArray4[2], (double)partialTicks)) - minecraft.getRenderManager().getRenderPosZ()
                )
                .updateState();
            tessellator.updateState();
        }
    }

    private boolean checkCondition(double offset) {
        double offset2;
        return (
                (offset2 = Math.abs(offset - (double)System.currentTimeMillis()) - this.settingLineTime.getValue()) == 0.0
                    ? 0
                    : (offset2 < 0.0 ? -1 : 1)
            )
            > 0;
    }

    private boolean canExecute5() {
        return !ClientGlobals.moduleManager.killAura.isToggled() ? false : ClientGlobals.moduleManager.killAura.target != null;
    }

    private boolean canExecute6() {
        return !ClientGlobals.moduleManager.killAura.settingOnlyRotation.getSelected().equalsIgnoreCase("noise2")
                && !ClientGlobals.moduleManager.killAura.settingOnlyRotation.getSelected().equalsIgnoreCase("noise3")
            ? false
            : false;
    }
}
