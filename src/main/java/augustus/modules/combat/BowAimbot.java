package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.MoveListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationHelper;
import client.setting.BooleanSetting;
import client.setting.BowAimbotTargetEntity;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CurveManager;
import client.util.Pair;
import client.util.SimulatedPlayer;
import client.util.TrajectoryPredictor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class BowAimbot extends Module implements RotationListener, ClickListener, TickListener, MoveListener {
    public ModeSetting settingMode;
    public SettingGroup settingRotations;
    public NumberSetting settingFov;
    public ModeSetting settingMoveFix;
    public BooleanSetting settingMoveFixIntave;
    public BooleanSetting settingAutoShoot;
    public BooleanSetting settingNoHorizontalBruteforce;
    public MultiSelectSetting settingEntities;
    public EntityLivingBase targetEntity;
    public long timestampMillis;
    public boolean expanded2;
    public boolean enabled2;
    public CurveManager curveManager;

    public BowAimbot() {
        super("BowAimbot", "BogenZiehlhilfe", new Color(12, 219, 77), Category.COMBAT);
        String[] textArray = new String[2];
        textArray[0] = "Old";
        textArray[1] = "New";
        this.settingMode = new ModeSetting(this, "Mode", "Old", textArray);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingFov = new NumberSetting(this, "Fov", 90.0, 0.0, 180.0, 0);
        String[] textArray2 = new String[3];
        textArray2[0] = "None";
        textArray2[1] = "Default";
        textArray2[2] = "Advanced";
        this.settingMoveFix = new ModeSetting(this, "MoveFix", "Default", textArray2).getModeSetting("SilentMoveFix");
        this.settingMoveFixIntave = (BooleanSetting)new BooleanSetting(this, "MoveFixIntave", true).getSetting(() -> canExecute7());
        this.settingAutoShoot = new BooleanSetting(this, "AutoShoot", true);
        this.settingNoHorizontalBruteforce = (BooleanSetting)new BooleanSetting(this, "No horizontal bruteforce", false).getSetting(() -> this.isMoveFixVisible());
        BowAimbotTargetEntity[] bowAimbotTargetEntityArray = BowAimbotTargetEntity.values();
        this.settingEntities = new MultiSelectSetting(this, "Entities", bowAimbotTargetEntityArray);
        this.targetEntity = (EntityLivingBase)null;
        this.timestampMillis = 0L;
        this.expanded2 = false;
        this.enabled2 = false;
        CurveManager curveManager2 = new CurveManager(this, this.settingRotations);
        this.curveManager = curveManager2;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.timestampMillis = 0L;
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.canExecute6()) {
            if (this.settingMoveFix.getSelected().equals("None")) {
                if (this.targetEntity != null) {
                    BowAimbot bowAimbot = ClientGlobals.moduleManager.bowAimbot;
                    if (ClientGlobals.rotationManager.checkModule(bowAimbot)) {
                        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                        event.setScaleFactor(scaleFactor1);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.canExecute6()) {
            if (this.targetEntity != null) {
                if (this.settingAutoShoot.isEnabled()) {
                    BowAimbot bowAimbot = ClientGlobals.moduleManager.bowAimbot;
                    if (ClientGlobals.rotationManager.checkModule(bowAimbot)) {
                        long time;
                        if ((byte)((time = this.timestampMillis - 1L) == 0L ? 0 : (time < 0L ? -1 : 1)) > 0) {
                            if (this.checkCondition()) {
                                event.setState2(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.canExecute6()) {
            if (this.settingMoveFix.getSelected().equals("None")) {
                if (this.targetEntity != null) {
                    BowAimbot bowAimbot = ClientGlobals.moduleManager.bowAimbot;
                    if (ClientGlobals.rotationManager.checkModule(bowAimbot)) {
                        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                        event.setScaleFactor(scaleFactor1);
                    }
                }
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.canExecute6()) {
            if (!this.settingMoveFix.getSelected().equals("None")) {
                if (ClientGlobals.rotationManager.checkModule(this) && this.targetEntity != null) {
                    BowAimbot bowAimbot = ClientGlobals.moduleManager.bowAimbot;
                    if (!ClientGlobals.rotationManager.checkModule(bowAimbot)) {
                        return;
                    }

                    if (this.settingMoveFix.getSelected().equals("Advanced")) {
                        AuraRenderMode auraRenderMode = AuraRenderMode.ADVANCED;
                        event.setAuraRenderMode(auraRenderMode);
                    } else {
                        AuraRenderMode auraRenderMode2 = AuraRenderMode.DEFAULT;
                        event.setAuraRenderMode(auraRenderMode2);
                    }

                    if (this.settingMoveFixIntave.isEnabled()) {
                        event.setState1(true);
                    }
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        this.targetEntity = null;
        this.expanded2 = false;
        if (this.canExecute6()) {
            Object[] itemArray = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object -> this.checkEntity((Entity)object))
            .sorted(Comparator.comparingDouble(object2 -> this.getCoordinate((Entity)object2)))
            .toArray();
            if (itemArray.length != 0) {
                this.targetEntity = (EntityLivingBase)itemArray[0];
            }

            if (this.targetEntity != null) {
                float value;
                float value2;
                if (this.settingMode.getSelected().equals("New")) {
                    EntityLivingBase targetEntity2 = this.targetEntity;
                    ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                    Pair pair = this.getPair(targetEntity2, itemStack5, 4);
                    pair.getSecond();
                    value = ((float[])pair.getFirst())[1];
                    value2 = ((float[])pair.getFirst())[0];
                    if ((Integer)pair.getSecond() == -1) {
                        this.timestampMillis = 0L;
                        return;
                    }

                    this.expanded2 = true;
                } else {
                    double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity);
                    int index = (int)(distance / 1.75);
                    Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(
                    RuntimeBridge.calculateTrajectory(
                    minecraft.thePlayer, this.targetEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index, 136
                    ),
                    999999
                    );
                    double offset = minecraft.thePlayer.getCoordinate(trajectoryPoint) - distance;
                    if (offset > 3.0) {
                        if (distance > 80.0) {
                            index = (int)(distance / 1.25);
                        } else {
                            index = (int)(distance / 1.5);
                        }
                    } else if (offset < 3.0) {
                        index = (int)(distance / 1.8);
                    }

                    Vec3 trajectoryPoint2 = RuntimeBridge.getTrajectoryPoint(
                    RuntimeBridge.predictTargetPoints(minecraft.thePlayer, this.targetEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index),
                    999999
                    );
                    double xCoord = trajectoryPoint2.xCoord;
                    double posX = minecraft.thePlayer.posX;
                    double offset2 = xCoord - posX;
                    double offset3 = trajectoryPoint2.yCoord + (double)this.targetEntity.getHeight2();
                    double offset4 = offset3 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                    double offset5 = trajectoryPoint2.zCoord - minecraft.thePlayer.posZ;
                    value2 = (float)Math.toDegrees(Math.atan2(offset5, offset2)) - 90.0F;
                    double offset6 = (double)MathHelper.sqrt_double(offset2 * offset2 + offset5 * offset5);
                    if (distance > 30.0) {
                        distance += (distance - 30.0) * 0.75;
                    }

                    double offset7 = Math.atan2(offset4, offset6) * 180.0;
                    double offset8 = Math.PI;
                    value = (float)(-(offset7 / offset8)) - (float)distance * 0.11F;
                }

                CurveManager curveManager2 = this.curveManager;
                Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                Rotation rotation6 = curveManager2.getRotation6(value2, value, rotation3);
                ClientGlobals.rotationManager.applyRotation2(rotation6, 4, 1898960900, this);
                this.timestampMillis++;
            } else {
                this.timestampMillis = 0L;
            }
        } else {
            this.timestampMillis = 0L;
        }
    }

    private double getCoordinate(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = entity.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            float[] floatArray = SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2);
            return (double)((Math.abs(floatArray[0]) + Math.abs(floatArray[1])) / 2.0F);
        }
    }

    private Pair getPair(EntityLivingBase livingEntity, ItemStack itemStack, int index3) {
        int index4 = -1;
        float value = 0.0F;
        float value2 = 0.0F;
        ArrayList arrayList = new ArrayList();
        int index5 = 1;
        int index6 = 0;
        if (0 >= index3) {
            return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
        } else {
            minecraft.thePlayer.getHeight2();
            int size = arrayList.size();
            float value3;
            AxisAlignedBB boundingBox;
            boolean enabled;
            float value4;
            int index7;
            double[] coordArray;
            float value5;
            boolean enabled3;
            if (index5 <= size) {
                ArrayList arrayList2 = arrayList;

                outerLoop:
                while (true) {
                    Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(arrayList2, index5);
                    Vec3 vec = minecraft.thePlayer.getVec35(1.0F);
                    double offset = trajectoryPoint.xCoord - minecraft.thePlayer.posX;
                    double offset2 = trajectoryPoint.yCoord + (double)(livingEntity.height / 2.0F) - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                    double offset3 = trajectoryPoint.zCoord - minecraft.thePlayer.posZ;
                    value3 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
                    double offset4 = (double)(livingEntity.width / 2.0F);
                    boundingBox = new AxisAlignedBB(
                    trajectoryPoint.xCoord - offset4,
                    trajectoryPoint.yCoord + (double)(livingEntity.height / 2.0F),
                    trajectoryPoint.zCoord - offset4,
                    trajectoryPoint.xCoord + offset4,
                    trajectoryPoint.yCoord + (double)livingEntity.height,
                    trajectoryPoint.zCoord + offset4
                    );
                    coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec, boundingBox);
                    index7 = index5;
                    enabled = this.settingNoHorizontalBruteforce.isEnabled();

                    for (value4 = 0.15F; value4 < 60.0F; value4 += 0.2F) {
                        value5 = 0.0F;
                        if ((double)value5 < 0.5) {
                            enabled3 = enabled;
                            break outerLoop;
                        }
                    }

                    if (index5 == index5 || ++index6 >= index3) {
                        return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
                    }

                    minecraft.thePlayer.getHeight2();
                    if (index5 <= arrayList.size()) {
                        arrayList2 = arrayList;
                    } else {
                        arrayList2 = arrayList = RuntimeBridge.calculateTrajectory(
                        minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                        );
                    }
                }
            } else {
                Vec3 trajectoryPoint2 = RuntimeBridge.getTrajectoryPoint(
                arrayList = RuntimeBridge.calculateTrajectory(
                minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                ),
                index5
                );
                Vec3 vec2 = minecraft.thePlayer.getVec35(1.0F);
                double offset5 = trajectoryPoint2.xCoord - minecraft.thePlayer.posX;
                double offset6 = trajectoryPoint2.yCoord + (double)(livingEntity.height / 2.0F) - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                double offset7 = trajectoryPoint2.zCoord - minecraft.thePlayer.posZ;
                value3 = (float)(-(MathHelper.getDouble6(offset6, (double)MathHelper.sqrt_double(offset5 * offset5 + offset7 * offset7)) * 180.0 / Math.PI));
                double offset8 = (double)(livingEntity.width / 2.0F);
                boundingBox = new AxisAlignedBB(
                trajectoryPoint2.xCoord - offset8,
                trajectoryPoint2.yCoord + (double)(livingEntity.height / 2.0F),
                trajectoryPoint2.zCoord - offset8,
                trajectoryPoint2.xCoord + offset8,
                trajectoryPoint2.yCoord + (double)livingEntity.height,
                trajectoryPoint2.zCoord + offset8
                );
                coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec2, boundingBox);
                index7 = index5;
                enabled = this.settingNoHorizontalBruteforce.isEnabled();
                value4 = 0.15F;
                if (value4 < 60.0F) {
                    value5 = 0.0F;
                    if (!((double)value5 < 0.5)) {
                        value4 += 0.2F;

                        while (true) {
                            if (value4 < 60.0F) {
                                value5 = 0.0F;
                                if ((double)value5 < 0.5) {
                                    enabled3 = enabled;
                                    break;
                                }

                                value4 += 0.2F;
                            } else {
                                if (index7 == index5 || ++index6 >= index3) {
                                    return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
                                }

                                minecraft.thePlayer.getHeight2();
                                ArrayList arrayList3;
                                if (index5 <= arrayList.size()) {
                                    arrayList3 = arrayList;
                                } else {
                                    arrayList3 = arrayList = RuntimeBridge.calculateTrajectory(
                                    minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                                    );
                                }

                                Vec3 trajectoryPoint3 = RuntimeBridge.getTrajectoryPoint(arrayList3, index5);
                                Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);
                                double offset9 = trajectoryPoint3.xCoord - minecraft.thePlayer.posX;
                                double offset10 = trajectoryPoint3.yCoord
                                + (double)(livingEntity.height / 2.0F)
                                - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                                double offset11 = trajectoryPoint3.zCoord - minecraft.thePlayer.posZ;
                                value3 = (float)(
                                -(MathHelper.getDouble6(offset10, (double)MathHelper.sqrt_double(offset9 * offset9 + offset11 * offset11)) * 180.0 / Math.PI)
                                );
                                double offset12 = (double)(livingEntity.width / 2.0F);
                                boundingBox = new AxisAlignedBB(
                                trajectoryPoint3.xCoord - offset12,
                                trajectoryPoint3.yCoord + (double)(livingEntity.height / 2.0F),
                                trajectoryPoint3.zCoord - offset12,
                                trajectoryPoint3.xCoord + offset12,
                                trajectoryPoint3.yCoord + (double)livingEntity.height,
                                trajectoryPoint3.zCoord + offset12
                                );
                                coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec3, boundingBox);
                                index7 = index5;
                                enabled = this.settingNoHorizontalBruteforce.isEnabled();
                                value4 = 0.15F;
                            }
                        }
                    } else {
                        enabled3 = enabled;
                    }
                } else {
                    outerLoop:
                    while (true) {
                        if (index7 == index5 || ++index6 >= index3) {
                            return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
                        }

                        minecraft.thePlayer.getHeight2();
                        ArrayList arrayList4;
                        if (index5 <= arrayList.size()) {
                            arrayList4 = arrayList;
                        } else {
                            arrayList4 = arrayList = RuntimeBridge.calculateTrajectory(
                            minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                            );
                        }

                        Vec3 trajectoryPoint4 = RuntimeBridge.getTrajectoryPoint(arrayList4, index5);
                        Vec3 vec4 = minecraft.thePlayer.getVec35(1.0F);
                        double offset13 = trajectoryPoint4.xCoord - minecraft.thePlayer.posX;
                        double offset14 = trajectoryPoint4.yCoord + (double)(livingEntity.height / 2.0F) - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                        double offset15 = trajectoryPoint4.zCoord - minecraft.thePlayer.posZ;
                        value3 = (float)(-(MathHelper.getDouble6(offset14, (double)MathHelper.sqrt_double(offset13 * offset13 + offset15 * offset15)) * 180.0 / Math.PI));
                        double offset16 = (double)(livingEntity.width / 2.0F);
                        boundingBox = new AxisAlignedBB(
                        trajectoryPoint4.xCoord - offset16,
                        trajectoryPoint4.yCoord + (double)(livingEntity.height / 2.0F),
                        trajectoryPoint4.zCoord - offset16,
                        trajectoryPoint4.xCoord + offset16,
                        trajectoryPoint4.yCoord + (double)livingEntity.height,
                        trajectoryPoint4.zCoord + offset16
                        );
                        coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec4, boundingBox);
                        index7 = index5;
                        enabled = this.settingNoHorizontalBruteforce.isEnabled();

                        for (value4 = 0.15F; value4 < 60.0F; value4 += 0.2F) {
                            value5 = 0.0F;
                            if ((double)value5 < 0.5) {
                                enabled3 = enabled;
                                break outerLoop;
                            }
                        }
                    }
                }
            }

            outerLoop:
            while (true) {
                if (enabled3 && value5 > 0.0F) {
                    do {
                        for (value4 += 0.2F; value4 >= 60.0F; value4 = 0.15F) {
                            if (index7 == index5 || ++index6 >= index3) {
                                return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
                            }

                            minecraft.thePlayer.getHeight2();
                            ArrayList arrayList5;
                            if (index5 <= arrayList.size()) {
                                arrayList5 = arrayList;
                            } else {
                                arrayList5 = arrayList = RuntimeBridge.calculateTrajectory(
                                minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                                );
                            }

                            Vec3 trajectoryPoint5 = RuntimeBridge.getTrajectoryPoint(arrayList5, index5);
                            Vec3 vec5 = minecraft.thePlayer.getVec35(1.0F);
                            double offset17 = trajectoryPoint5.xCoord - minecraft.thePlayer.posX;
                            double offset18 = trajectoryPoint5.yCoord + (double)(livingEntity.height / 2.0F) - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                            double offset19 = trajectoryPoint5.zCoord - minecraft.thePlayer.posZ;
                            value3 = (float)(
                            -(MathHelper.getDouble6(offset18, (double)MathHelper.sqrt_double(offset17 * offset17 + offset19 * offset19)) * 180.0 / Math.PI)
                            );
                            double offset20 = (double)(livingEntity.width / 2.0F);
                            boundingBox = new AxisAlignedBB(
                            trajectoryPoint5.xCoord - offset20,
                            trajectoryPoint5.yCoord + (double)(livingEntity.height / 2.0F),
                            trajectoryPoint5.zCoord - offset20,
                            trajectoryPoint5.xCoord + offset20,
                            trajectoryPoint5.yCoord + (double)livingEntity.height,
                            trajectoryPoint5.zCoord + offset20
                            );
                            coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec5, boundingBox);
                            index7 = index5;
                            enabled = this.settingNoHorizontalBruteforce.isEnabled();
                        }

                        value5 = 0.0F;
                    } while (!((double)value5 < 0.5));

                    enabled3 = enabled;
                } else {
                    for (int i = -1; i <= 1; i += 2) {
                        if (i != 1 || value5 != 0.0F) {
                            float floatEntry = MathHelper.clamp_float(value3 - value4, -90.0F, 90.0F);
                            float float4 = MathHelper.sqrt_float((float)TrajectoryPredictor.getCoordinate(coordArray, 0.5 + (double)(value5 * (float)i)));
                            RotationHelper rotationHelper = new RotationHelper();
                            rotationHelper.applyEntityLivingBase4(minecraft.thePlayer, itemStack, float4, floatEntry);
                            int index = rotationHelper.getIndex(50, livingEntity, boundingBox);
                            if (index != Integer.MAX_VALUE) {
                                index4 = index5 = index + 1;
                                value2 = float4;
                                value = floatEntry;

                                while (index7 != index5) {
                                    if (++index6 >= index3) {
                                        break;
                                    }

                                    minecraft.thePlayer.getHeight2();
                                    ArrayList arrayList6;
                                    if (index5 <= arrayList.size()) {
                                        arrayList6 = arrayList;
                                    } else {
                                        arrayList6 = arrayList = RuntimeBridge.calculateTrajectory(
                                        minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                                        );
                                    }

                                    Vec3 trajectoryPoint6 = RuntimeBridge.getTrajectoryPoint(arrayList6, index5);
                                    Vec3 vec6 = minecraft.thePlayer.getVec35(1.0F);
                                    double offset21 = trajectoryPoint6.xCoord - minecraft.thePlayer.posX;
                                    double offset22 = trajectoryPoint6.yCoord
                                    + (double)(livingEntity.height / 2.0F)
                                    - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                                    double offset23 = trajectoryPoint6.zCoord - minecraft.thePlayer.posZ;
                                    value3 = (float)(
                                    -(MathHelper.getDouble6(offset22, (double)MathHelper.sqrt_double(offset21 * offset21 + offset23 * offset23)) * 180.0 / Math.PI)
                                    );
                                    double offset24 = (double)(livingEntity.width / 2.0F);
                                    boundingBox = new AxisAlignedBB(
                                    trajectoryPoint6.xCoord - offset24,
                                    trajectoryPoint6.yCoord + (double)(livingEntity.height / 2.0F),
                                    trajectoryPoint6.zCoord - offset24,
                                    trajectoryPoint6.xCoord + offset24,
                                    trajectoryPoint6.yCoord + (double)livingEntity.height,
                                    trajectoryPoint6.zCoord + offset24
                                    );
                                    coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec6, boundingBox);
                                    index7 = index5;
                                    enabled = this.settingNoHorizontalBruteforce.isEnabled();

                                    for (value4 = 0.15F; value4 < 60.0F; value4 += 0.2F) {
                                        value5 = 0.0F;
                                        if ((double)value5 < 0.5) {
                                            enabled3 = enabled;
                                            continue outerLoop;
                                        }
                                    }
                                }

                                return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{float4, floatEntry});
                            }
                        }
                    }

                    for (value5 += 0.1F; !((double)value5 < 0.5); value5 = 0.0F) {
                        for (value4 += 0.2F; !(value4 < 60.0F); value4 = 0.15F) {
                            if (index7 == index5 || ++index6 >= index3) {
                                return index4 == -1 ? new Pair(-1, new float[]{0.0F, 0.0F}) : new Pair(index4, new float[]{value2, value});
                            }

                            minecraft.thePlayer.getHeight2();
                            ArrayList arrayList7;
                            if (index5 <= arrayList.size()) {
                                arrayList7 = arrayList;
                            } else {
                                arrayList7 = arrayList = RuntimeBridge.calculateTrajectory(
                                minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index5, 136
                                );
                            }

                            Vec3 trajectoryPoint7 = RuntimeBridge.getTrajectoryPoint(arrayList7, index5);
                            Vec3 vec7 = minecraft.thePlayer.getVec35(1.0F);
                            double offset25 = trajectoryPoint7.xCoord - minecraft.thePlayer.posX;
                            double offset26 = trajectoryPoint7.yCoord + (double)(livingEntity.height / 2.0F) - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                            double offset27 = trajectoryPoint7.zCoord - minecraft.thePlayer.posZ;
                            value3 = (float)(-(MathHelper.getDouble6(offset26, (double)MathHelper.sqrt_double(offset25 * offset25 + offset27 * offset27)) * 180.0 / Math.PI));
                            double offset28 = (double)(livingEntity.width / 2.0F);
                            boundingBox = new AxisAlignedBB(
                            trajectoryPoint7.xCoord - offset28,
                            trajectoryPoint7.yCoord + (double)(livingEntity.height / 2.0F),
                            trajectoryPoint7.zCoord - offset28,
                            trajectoryPoint7.xCoord + offset28,
                            trajectoryPoint7.yCoord + (double)livingEntity.height,
                            trajectoryPoint7.zCoord + offset28
                            );
                            coordArray = TrajectoryPredictor.getCoordOffsetArray2(vec7, boundingBox);
                            index7 = index5;
                            enabled = this.settingNoHorizontalBruteforce.isEnabled();
                        }
                    }

                    enabled3 = enabled;
                }
            }
        }
    }

    private boolean checkEntity(Entity entity) {
        double offset = this.getCoordinate(entity) - this.settingFov.getValue();
        if ((offset == 0.0 ? 0.0 : (double)(offset > 0.0 ? 1 : -1)) > 0.0) {
            return false;
        } else if (entity.isDead) {
            return false;
        } else if (entity.ticksExisted < 1) {
            return false;
        } else if (!(entity instanceof EntityLivingBase)) {
            return false;
        } else if (((EntityLivingBase)entity).indexOffset29 > 1) {
            return false;
        } else if (minecraft.thePlayer == entity) {
            return false;
        } else if (entity instanceof EntityPlayerSP) {
            return false;
        } else {
            BowAimbotTargetEntity bowAimbotTargetEntity = BowAimbotTargetEntity.PLAYERS;
            BowAimbotTargetEntity bowAimbotTargetEntity2 = BowAimbotTargetEntity.MOBS;
            BowAimbotTargetEntity bowAimbotTargetEntity3 = BowAimbotTargetEntity.ANIMALS;
            BowAimbotTargetEntity bowAimbotTargetEntity4 = BowAimbotTargetEntity.MOBS;
            BowAimbotTargetEntity bowAimbotTargetEntity5 = BowAimbotTargetEntity.INVISIBLE;
            return ChatUtil.checkEntity3(
            entity,
            bowAimbotTargetEntity.isSelected(),
            bowAimbotTargetEntity2.isSelected(),
            bowAimbotTargetEntity3.isSelected(),
            bowAimbotTargetEntity4.isSelected(),
            false,
            bowAimbotTargetEntity5.isSelected(),
            true,
            true,
            
            true,
            true,
            true
            );
        }
    }

    private static boolean canExecute7() {
        return !ClientGlobals.moduleManager.scaffold.settingMoveFix.getSelected().equalsIgnoreCase("None");
    }

    private boolean canExecute5() {
        for (int i = 8; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack != null && itemStack.getItem().equals(Items.arrow)) {
                return true;
            }
        }

        return false;
    }

    public EntityLivingBase getTargetEntity() {
        return this.targetEntity;
    }

    private boolean checkCondition() {
        if (!this.expanded2 && this.settingMode.getSelected().equals("New")) {
            return false;
        } else {
            ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
            if (itemStack5 != null) {
                int index = ((ItemBow)itemStack5.getItem()).processAction(itemStack5) - minecraft.thePlayer.getEntityId2();
                if (index >= 62000 && this.targetEntity != null) {
                    minecraft.gameSettings.keyBindUseItem.setPressTime(1);
                    this.enabled2 = false;
                    return false;
                }

                float value = (float)index / 20.0F;
                if ((value * value + value * 2.0F) / 3.0F > 1.0F) {
                    boolean enabled22 = this.enabled2;
                    this.enabled2 = true;
                    if (!enabled22) {
                        return false;
                    }

                    return true;
                }
            }

            this.enabled2 = false;
            return false;
        }
    }

    private boolean canExecute6() {
        if (minecraft.thePlayer == null) {
            return false;
        } else if (minecraft.theWorld == null) {
            return false;
        } else if (!minecraft.isIntegratedServerRunning()) {
            return false;
        } else if (!minecraft.gameSettings.keyBindUseItem.pressed) {
            return false;
        } else if (minecraft.thePlayer.getHeldItem() == null) {
            return false;
        } else {
            return !(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemBow) ? false : this.canExecute5();
        }
    }

    private boolean isMoveFixVisible() {
        return this.settingMode.getSelected().equals("New");
    }
}
