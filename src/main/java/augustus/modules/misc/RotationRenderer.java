package augustus.modules.misc;

import client.Client;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.BoundingBoxDimension;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.util.ArrayListData;
import client.util.BacktrackRecord;
import client.util.HitBoxUtils;
import client.util.KeyUtils;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.RotationRecordPoint;
import client.util.SimulatedPlayer;
import client.util.TrajectoryPredictor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RotationRenderer extends Module implements RenderListener, TickListener {
    public BooleanSetting settingRecordedPredictions;
    public BooleanSetting settingRecordedAura;
    public ArrayList itemList3;
    public ArrayList rotationVectors;
    public ArrayList itemList4;
    public double reachDistance;
    public Vec3 positionVec;
    public long timestampMillis;
    public int keyCode2;

    public RotationRenderer() {
        super("RotationRenderer", "RotationsAnzeiger", Color.BLACK, Category.MISC);
        this.settingRecordedPredictions = new BooleanSetting(this, "Recorded Predictions", false);
        this.settingRecordedAura = new BooleanSetting(this, "Recorded Aura", false);
        this.itemList3 = new ArrayList();
        this.rotationVectors = new ArrayList();
        this.itemList4 = new ArrayList();
        this.reachDistance = 0.0;
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
        this.rotationVectors.clear();
        this.itemList4.clear();
    }

    @Override
    public void onPreDisable() {
        if (minecraft.theWorld == null) {
            this.onReset();
        } else {
            FileDialog fileDialog = new FileDialog((Frame)null);
            fileDialog.setVisible(true);
            File file = new File(fileDialog.getDirectory() + fileDialog.getFile());

            try {
                this.itemList4.clear();
                this.itemList3.clear();
                this.rotationVectors.clear();
                File file2 = file;

                JsonArray jsonArray;
                try {
                    jsonArray = this.getJsonArray(file2);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }

                JsonArray jsonArray2 = jsonArray;
                Gson gson = new Gson();
                ArrayList arrayList = new ArrayList();

                for (int i = 0; i < jsonArray2.size(); i++) {
                    JsonObject asJsonObject = jsonArray2.get(i).getAsJsonObject();
                    arrayList.add(gson.fromJson(asJsonObject, RotationRecordPoint.class));
                }

                for (RotationRecordPoint auxiliaryDoubleEntry : (Iterable<RotationRecordPoint>) arrayList) {
                    this.itemList4.add(new double[]{(double)auxiliaryDoubleEntry.scaleFactor6, (double)auxiliaryDoubleEntry.scaleFactor});
                    this.itemList3.add(new Vec3(auxiliaryDoubleEntry.coordOffset4, auxiliaryDoubleEntry.coordOffset1, auxiliaryDoubleEntry.coordOffset5));
                    this.rotationVectors.add(new Vec3(auxiliaryDoubleEntry.coordOffset2, auxiliaryDoubleEntry.coordOffset, auxiliaryDoubleEntry.coordOffset3));
                }

                this.positionVec = ((Vec3)this.rotationVectors.get(0)).getVec312(0.0, -100.0, 0.0);
                this.reachDistance = 0.0;
                RayTraceUtil.applyString("Loaded " + this.itemList4.size() + " ticks!");
            } catch (Throwable e2) {
                e2.printStackTrace();
                this.onReset();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        long time2 = System.currentTimeMillis();
        if (KeyUtils.checkCondition(203)) {
            this.reachDistance = this.reachDistance - (double)(time2 - this.timestampMillis) / 200.0;
        }

        if (KeyUtils.checkCondition(205)) {
            this.reachDistance = this.reachDistance + (double)(time2 - this.timestampMillis) / 200.0;
        }

        this.reachDistance = MathHelper.getDouble7(this.reachDistance, 0.0, (double)(this.itemList3.size() - 1));
        int index4 = Double.compare((double)this.keyCode2, Math.floor(this.reachDistance));
        this.keyCode2 = (int)Math.floor(this.reachDistance);
        this.timestampMillis = time2;
        RenderUtils.updateState();
        GL11.glPushMatrix();
        GL11.glTranslated(
        -minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ()
        );
        GlStateManager.color4f2(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glBegin(7);
        GL11.glVertex3d(-100.0, 90.0, -100.0);
        GL11.glVertex3d(-100.0, 90.0, 100.0);
        GL11.glVertex3d(100.0, 90.0, 100.0);
        GL11.glVertex3d(100.0, 90.0, -100.0);
        GL11.glEnd();
        int index5 = 0;

        for (int i = Math.max((int)this.reachDistance - 10, 0);
        (double)i < Math.min(Math.ceil(this.reachDistance) - 1.0, (double)this.itemList4.size());
        i++
        ) {
            double[] coordArray = (double[])this.itemList4.get(i);
            Vec3 vec = SimulatedPlayer.processAction((float)coordArray[1], (float)coordArray[0]);
            Vec3 vec2 = ((Vec3)this.itemList3.get(i)).getVec311(this.positionVec).getVec312(0.0, 1.62, 0.0);
            Vec3 vec3 = ((Vec3)this.rotationVectors.get(i)).getVec311(this.positionVec);
            Vec3 vec4 = vec2.getVec36(vec.getVec3(6.0));
            Vec3 vec5 = vec2.getVec36(vec.getVec3(0.1));
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec3, true);
            double offset = (double)index5 / 20.0;
            if (index5 >= 18) {
                offset *= 0.1;
            }

            if (index5 == 17) {
                offset *= 0.05;
            }

            if (index5 < 17) {
                offset *= 0.01;
            }

            GL11.glColor4d(1.0, 1.0, 1.0, offset);
            GL11.glBegin(3);
            GL11.glVertex3d(vec2.xCoord, vec2.yCoord, vec2.zCoord);
            GL11.glVertex3d(vec4.xCoord, vec4.yCoord, vec4.zCoord);
            GL11.glEnd();
            GL11.glColor4d(1.0, 0.0, 0.0, offset);
            GL11.glBegin(3);
            GL11.glVertex3d(vec2.xCoord, vec2.yCoord, vec2.zCoord);
            GL11.glVertex3d(vec5.xCoord, vec5.yCoord, vec5.zCoord);
            GL11.glEnd();
            GL11.glColor4d(1.0, 1.0, 1.0, offset);
            RenderUtils.processAction(axisAlignedBB);
            index5++;
        }

        int int12 = MathHelper.getInt12((int)(Math.ceil(this.reachDistance) - 1.0), 0, this.itemList4.size());
        int index = (int)Math.min(Math.ceil(this.reachDistance), (double)this.itemList4.size());
        double offset2 = this.reachDistance % 1.0;
        Vec3 vec6 = ((Vec3)this.rotationVectors.get(int12)).getVec39((Vec3)this.rotationVectors.get(index), offset2).getVec311(this.positionVec);
        AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(vec6, true);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        RenderUtils.processAction(AxisAlignedBB.getAxisAlignedBB(vec6.getVec312(0.0, 1.62, 0.0), 0.05));
        GlStateManager.color4f2(0.0F, 1.0F, 0.0F, 1.0F);
        RenderUtils.processAction(AxisAlignedBB.getAxisAlignedBB(vec6.getVec312(0.0, 1.62, 0.0), 0.4));
        double[] coordArray2 = (double[])this.itemList4.get(int12);
        double[] coordArray3 = (double[])this.itemList4.get(index);
        double[] coordArray4 = null;
        double offset3 = Double.NaN;
        double offset4 = coordArray2[0];
        int index6 = int12;

        BacktrackRecord backtrackRecord = null;
        while (true) {
            if (index6 >= this.itemList4.size()) {
                backtrackRecord = new BacktrackRecord(10);
                break;
            }

            double[] coordArray5 = (double[])this.itemList4.get(index6);
            if (coordArray4 != null) {
                double offset5 = 0.0;
                boolean completed = false;
                offset5 = HitBoxUtils.getDouble(MathHelper.getDouble4(coordArray4[0]), MathHelper.getDouble4(coordArray5[0]));
                if (Math.abs(offset5) <= 0.15) {
                    offset4 = coordArray5[0];
                } else {
                    if (!Double.isFinite(offset3)) {
                        completed = true;
                    }
                    if (!completed) {
                        double offset6 = Math.signum(offset3);
                        double offset7 = Math.signum(offset5);
                        if (offset6 == offset7) {
                            completed = true;
                        }
                        if (!completed) {
                            double offset8 = coordArray5[0];
                            double offset9 = offset3 / (offset5 - offset3) * offset5;
                            offset4 = MathHelper.getDouble4(offset8 + offset9);
                        }
                    }
                }
                if (!completed) {
                    backtrackRecord = new BacktrackRecord(10);
                    break;
                }


                offset3 = offset5;
            }

            coordArray4 = coordArray5;
            index6++;
        }

        BacktrackRecord backtrackRecord2 = new BacktrackRecord(10);

        for (int i2 = Math.max((int)this.reachDistance - 10, 0);
        (double)i2 < Math.min(Math.ceil(this.reachDistance) - 1.0, (double)this.itemList3.size());
        i2++
        ) {
            backtrackRecord.applyObject(new BoundingBoxDimension((Vec3)this.itemList3.get(i2), i2));
        }

        for (int i3 = Math.max((int)this.reachDistance - 10, 0);
        (double)i3 < Math.min(Math.ceil(this.reachDistance) - 1.0, (double)this.rotationVectors.size());
        i3++
        ) {
            backtrackRecord2.applyObject(this.rotationVectors.get(i3));
        }

        if (backtrackRecord.canExecute()) {
            double coordinate = this.getCoordinate(backtrackRecord, backtrackRecord2, 0, (index4 & 1) != 0);
            double coordinate2 = this.getCoordinate(backtrackRecord, backtrackRecord2, 1, (index4 & 1) != 0);
            this.applyVec3(
            vec6.getVec312(0.0, 1.62, 0.0),
            new double[]{coordinate, coordArray2[1]},
            new double[]{coordinate, coordArray3[1]},
            offset2,
            int12,
            index,
            
            1.0,
            1.0,
            0.0
            );
            this.applyVec3(
            vec6.getVec312(0.0, 1.62, 0.0),
            new double[]{coordinate2, coordArray2[1]},
            new double[]{coordinate2, coordArray3[1]},
            offset2,
            int12,
            index,
            
            1.0,
            1.0,
            0.0
            );
        }

        this.applyVec3(vec6.getVec312(0.0, 1.62, 0.0), coordArray2, coordArray3, offset2, int12, index, 1.0, 1.0, 1.0);
        this.applyVec3(
        vec6.getVec312(0.0, 1.62, 0.0), new double[]{offset4, coordArray2[1]}, new double[]{offset4, coordArray3[1]}, offset2, int12, index, 0.8, 0.5, 0.4
        );
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        RenderUtils.processAction(axisAlignedBB2);
        RenderUtils.prepareScissor();
        GL11.glPopMatrix();
        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void onUpdate() {
        Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList().remove(this);
        Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList2().remove(this);
    }

    @Override
    public void onPreTick() {
        if (minecraft.currentScreen != null) {
            minecraft.thePlayer.motionY = 0.0;
        } else {
            minecraft.gameSettings.keyBindForward.pressed = false;
            minecraft.gameSettings.keyBindBack.pressed = false;
            minecraft.gameSettings.keyBindLeft.pressed = false;
            minecraft.gameSettings.keyBindRight.pressed = false;
            minecraft.gameSettings.keyBindSneak.pressed = false;
            minecraft.gameSettings.keyBindJump.pressed = false;
            double offset = (double)(minecraft.thePlayer.rotationYaw + 90.0F);
            boolean enabled = false;
            boolean enabled2 = KeyUtils.checkCondition(minecraft.gameSettings.keyBindForward.getKeyCode());
            boolean enabled3 = KeyUtils.checkCondition(minecraft.gameSettings.keyBindBack.getKeyCode());
            if (enabled2) {
                enabled = true;
            }

            if (enabled3) {
                offset -= 180.0;
                enabled = true;
            }

            if (KeyUtils.checkCondition(minecraft.gameSettings.keyBindLeft.getKeyCode())) {
                if (!enabled3) {
                    double offset2;
                    double offset3;
                    if (enabled2) {
                        offset2 = offset;
                        offset3 = 45.0;
                    } else {
                        offset2 = offset;
                        offset3 = 90.0;
                    }

                    offset = offset2 - offset3;
                } else {
                    offset += 45.0;
                }

                enabled = true;
            }

            if (KeyUtils.checkCondition(minecraft.gameSettings.keyBindRight.getKeyCode())) {
                if (!enabled3) {
                    double offset4;
                    double offset5;
                    if (enabled2) {
                        offset4 = offset;
                        offset5 = 45.0;
                    } else {
                        offset4 = offset;
                        offset5 = 90.0;
                    }

                    offset = offset4 + offset5;
                } else {
                    offset -= 45.0;
                }

                enabled = true;
            }

            minecraft.thePlayer.motionY = 0.0;
            if (KeyUtils.checkCondition(minecraft.gameSettings.keyBindSneak.getKeyCode())) {
                minecraft.thePlayer.motionY -= 0.05;
            }

            if (KeyUtils.checkCondition(minecraft.gameSettings.keyBindJump.getKeyCode())) {
                minecraft.thePlayer.motionY += 0.05;
            }

            if (enabled) {
                double offset6 = Math.cos(Math.toRadians(offset)) * 0.8944271909999159 / 10.0;
                double offset7 = Math.sin(Math.toRadians(offset)) * 0.8944271909999159 / 10.0;
                minecraft.thePlayer.motionX = offset6;
                minecraft.thePlayer.motionZ = offset7;
            } else {
                minecraft.thePlayer.motionX = 0.0;
                minecraft.thePlayer.motionZ = 0.0;
            }
        }
    }

    private JsonArray getJsonArray(File file) throws IOException {
        byte[] byteArray2 = RotationRecorder.getByteArray2(Files.readAllBytes(file.toPath()));
        return JsonParser.parseString(new String(byteArray2, StandardCharsets.UTF_8)).getAsJsonArray();
    }

    private double getCoordinate(BacktrackRecord backtrackRecord, BacktrackRecord backtrackRecord2, int index2, boolean enabled) {
        ArrayListData tertiaryArrayListEntry = new ArrayListData(4);
        double offset = Double.NaN;
        double offset2 = 0.0;
        int index3 = 0;
        int index4 = 3;

        double[] coordArray;
        while (true) {
            if (index4 <= 0) {
                coordArray = tertiaryArrayListEntry.getDoubleArray();
                break;
            }

            Object object3 = backtrackRecord.getObject3(index4 + 4);
            BoundingBoxDimension floatEntry = (BoundingBoxDimension)object3;
            Vec3 positionVec2 = floatEntry.positionVec2;
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)backtrackRecord2.getObject3(index4 + 4), true);
            double[] coordOffsetArray2 = TrajectoryPredictor.getCoordOffsetArray2(positionVec2, axisAlignedBB);
            if (!Double.isNaN(offset)) {
                HitBoxUtils.getDouble(MathHelper.getDouble4(offset2), MathHelper.getDouble4(coordOffsetArray2[index2]));
                coordArray = tertiaryArrayListEntry.getDoubleArray();
                break;
            }

            offset = MathHelper.getDouble4(coordOffsetArray2[index2]);
            tertiaryArrayListEntry.processAction(0.0);
            offset2 = offset;
            index3 = ((BoundingBoxDimension)backtrackRecord.getObject3(0)).indexOffset - floatEntry.indexOffset;
            index4--;
        }

        double offset3 = coordArray[0] * (double)index3 + coordArray[1];
        double offset4 = coordArray[0] * (double)(index3 + 1) + coordArray[1];
        double double7 = tertiaryArrayListEntry.getDouble7();
        double offset5 = 0.0;
        double offset6 = 0.0;

        for (int i = 0; i < tertiaryArrayListEntry.getItemList().size(); i++) {
            double offset7 = (Double)tertiaryArrayListEntry.getItemList().get(i);
            offset5 += Math.pow(offset7 - double7, 2.0);
            offset6 += Math.pow(offset7 - (coordArray[0] * (double)i + coordArray[1]), 2.0);
        }

        double offset8 = Math.abs(
        HitBoxUtils.getDouble(
        
        MathHelper.getDouble4(
        TrajectoryPredictor.getCoordOffsetArray2(
        ((BoundingBoxDimension)backtrackRecord.getObject3(0)).positionVec2,
        HitBoxUtils.getAxisAlignedBB((Vec3)backtrackRecord2.getObject3(0), true)
        )[index2]
        ),
        MathHelper.getDouble4(offset3 + offset)
        )
        );
        double offset9 = Math.abs(
        HitBoxUtils.getDouble(
        
        MathHelper.getDouble4(
        TrajectoryPredictor.getCoordOffsetArray2(
        ((BoundingBoxDimension)backtrackRecord.getObject3(0)).positionVec2,
        HitBoxUtils.getAxisAlignedBB((Vec3)backtrackRecord2.getObject3(0), true)
        )[index2]
        ),
        MathHelper.getDouble4(offset4 + offset)
        )
        );
        if (enabled) {
            RayTraceUtil.applyString(
            
            "#"
            + this.reachDistance
            + " delta="
            + PlayerUtil.getString(offset3, 3)
            + " r^2="
            + PlayerUtil.getString(1.0 - offset6 / offset5, 7)
            + " fd="
            + PlayerUtil.getString(offset8, 4)
            + " fd2="
            + PlayerUtil.getString(offset9, 4)
            + " "
            + Arrays.toString(tertiaryArrayListEntry.getItemList().toArray())
            + " x="
            + index3
            + " func="
            + Arrays.toString(coordArray)
            );
        }

        return MathHelper.getDouble4(offset3 + offset);
    }

    private void applyVec3(Vec3 vec, double[] coordArray, double[] coordArray2, double offset, int index, int index2, double x, double y, double z) {
        double doubleEntry = HitBoxUtils.getDouble(MathHelper.getDouble4(coordArray[0]), MathHelper.getDouble4(coordArray2[0]));
        double offset2 = coordArray2[1] - coordArray[1];
        Vec3 vec2 = SimulatedPlayer.processAction((float)(coordArray[1] + offset2 * offset), (float)(coordArray[0] + doubleEntry * offset));
        Vec3 vec3 = ((Vec3)this.itemList3.get(index)).getVec39((Vec3)this.itemList3.get(index2), offset).getVec311(this.positionVec).getVec312(0.0, 1.62, 0.0);
        Vec3 vec4 = vec3.getVec36(vec2.getVec3(6.0));
        Vec3 vec5 = vec3.getVec36(vec2.getVec3(0.1));
        GL11.glColor4d(x, y, z, 1.0);
        GL11.glBegin(3);
        GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
        GL11.glVertex3d(vec4.xCoord, vec4.yCoord, vec4.zCoord);
        GL11.glEnd();
        GL11.glColor4d(1.0, 0.0, 0.0, 1.0);
        GL11.glBegin(3);
        GL11.glVertex3d(vec3.xCoord, vec3.yCoord, vec3.zCoord);
        GL11.glVertex3d(vec5.xCoord, vec5.yCoord, vec5.zCoord);
        GL11.glEnd();
        GL11.glColor4d(0.0, 0.0, 1.0, 1.0);
    }
}
