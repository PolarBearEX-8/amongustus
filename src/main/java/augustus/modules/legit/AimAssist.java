package augustus.modules.legit;

import client.Client;
import client.ClientGlobals;
import client.event.EventSilentMove;
import client.event.EventWorldLoad;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.AimAssistTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.TargetFilterSetting;
import client.util.BooleanSupplier;
import client.util.HitBoxUtils;
import client.util.MovingObjectType;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.Comparator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AimAssist extends Module implements WorldLoadListener, TickListener, SilentMoveListener {
    public RangeSetting settingPreAimRange;
    public ModeSetting settingMode;
    public BooleanSupplier booleanSupplier;
    public BooleanSupplier booleanSupplier2;
    public NumberSetting settingStrength;
    public NumberSetting settingFasterMouseMovement;
    public SettingGroup settingImproveYourAimInput;
    public NumberSetting settingOwnHorizontalRotationMultiplier;
    public NumberSetting settingOwnVerticalRotationMultiplier;
    public NumberSetting settingHorizontalAssistMultiplier;
    public NumberSetting settingVerticalAssistMultiplier;
    public SettingGroup settingAutoAim;
    public RangeSetting settingHorizontalSpeed;
    public RangeSetting settingVerticalSpeed;
    public NumberSetting settingHorizontalSmoothness;
    public NumberSetting settingVerticalSmoothness;
    public BooleanSetting settingSmoothEarly;
    public ModeSetting settingNoRotationFovMode;
    public NumberSetting settingNoRotationHorizontalFov;
    public NumberSetting settingNoRotationVerticalFov;
    public NumberSetting settingNoRotationHorizontal;
    public NumberSetting settingNoRotationVertical;
    public NumberSetting settingHorizontalBoxSize;
    public NumberSetting settingRandomizationStrength;
    public BooleanSetting settingRandomizeInNoRotationFovs;
    public NumberSetting settingHorizontalFov;
    public NumberSetting settingVerticalFov;
    public BooleanSetting settingClick;
    public BooleanSetting settingDiggingCheck;
    public BooleanSetting settingSingleTarget;
    public MultiSelectSetting settingTargets;
    public TargetFilterSetting auxiliarySettingAdapter;
    public float motionMultiplier;
    public EntityLivingBase target;
    public EntityLivingBase targetEntity2;
    public double[] coordOffsetArray3;
    public double[] coordOffsetArray;
    public int[] intValueArray;
    public int[] intValueArray2;
    public double[] coordOffsetArray2;
    public int[] intValueArray3;
    public SimulatedPlayer simulatedPlayer;
    public long timestampMillis;
    public int tickDelay;
    public long timestampMillis1;
    public static int[] intValueArray4;

    public AimAssist() {
        super("AimAssist", "Ziehlhilfe", new Color(66, 135, 245), Category.LEGIT);
        this.settingPreAimRange = new RangeSetting(this, "Pre aim range", 5.0, 5.0, 0.0, 10.0, 1)
        .getRangeSetting2();
        String[] textArray = new String[2];
        textArray[0] = "Stable";
        textArray[1] = "Experimental";
        this.settingMode = new ModeSetting(this, "Mode", "Stable", textArray);
        this.booleanSupplier = () -> this.isModeVisible();
        this.booleanSupplier2 = () -> this.canExecute5();
        NumberSetting numberSetting = new NumberSetting(this, "Strength", 2.0, 1.0, 5.0, 2);
        BooleanSupplier booleanSupplier22 = this.booleanSupplier2;
        this.settingStrength = numberSetting.getNumberSetting(booleanSupplier22);
        NumberSetting numberSetting3 = new NumberSetting(this, "Faster mouse movement", 1.0, 1.0, 5.0, 2);
        BooleanSupplier booleanSupplier23 = this.booleanSupplier2;
        this.settingFasterMouseMovement = numberSetting3.getNumberSetting(booleanSupplier23);
        SettingGroup settingGroup = new SettingGroup(this, "Improve your aim input");
        BooleanSupplier booleanSupplier3 = this.booleanSupplier;
        this.settingImproveYourAimInput = settingGroup.getSettingGroup(booleanSupplier3);
        this.settingOwnHorizontalRotationMultiplier = new NumberSetting(this, "Own horizontal rotation multiplier", 1.0, 0.0, 1.0, 2, this.settingImproveYourAimInput);
        this.settingOwnVerticalRotationMultiplier = new NumberSetting(this, "Own vertical rotation multiplier", 1.0, 0.0, 1.0, 2, this.settingImproveYourAimInput);
        this.settingHorizontalAssistMultiplier = new NumberSetting(this, "Horizontal assist multiplier", 1.0, 0.0, 1.0, 2, this.settingImproveYourAimInput);
        this.settingVerticalAssistMultiplier = new NumberSetting(this, "Vertical assist multiplier", 1.0, 0.0, 1.0, 2, this.settingImproveYourAimInput);
        SettingGroup settingGroup3 = new SettingGroup(this, "Auto aim");
        BooleanSupplier booleanSupplier4 = this.booleanSupplier;
        this.settingAutoAim = settingGroup3.getSettingGroup(booleanSupplier4);
        this.settingHorizontalSpeed = new RangeSetting(this, " horizontal speed", 4.0, 6.0, 0.0, 15.0, 1, this.settingAutoAim).getRangeSetting2();
        this.settingVerticalSpeed = new RangeSetting(this, " vertical speed", 4.0, 6.0, 0.0, 15.0, 1, this.settingAutoAim).getRangeSetting2();
        this.settingHorizontalSmoothness = new NumberSetting(this, "Horizontal smoothness", 5.0, 1.0, 10.0, 0, this.settingAutoAim);
        this.settingVerticalSmoothness = new NumberSetting(this, "Vertical smoothness", 5.0, 1.0, 10.0, 0, this.settingAutoAim);
        this.settingSmoothEarly = new BooleanSetting(this, "Smooth early", true, this.settingAutoAim);
        String[] textArray2 = new String[2];
        textArray2[0] = "2D";
        textArray2[1] = "3D";
        this.settingNoRotationFovMode = new ModeSetting(this, "NoRotationFovMode", "2D", textArray2, this.settingAutoAim);
        this.settingNoRotationHorizontalFov = new NumberSetting(this, "No rotation horizontal fov", 5.0, 0.0, 15.0, 0, this.settingAutoAim).getNumberSetting(() -> this.isNoRotationHorizontalFovVisible());
        this.settingNoRotationVerticalFov = new NumberSetting(this, "No rotation vertical fov", 5.0, 0.0, 15.0, 0, this.settingAutoAim).getNumberSetting(() -> this.isNoRotationVerticalFovVisible());
        this.settingNoRotationHorizontal = new NumberSetting(this, "No rotation horizontal", 1.75, 0.0, 3.0, 2, this.settingAutoAim).getNumberSetting(() -> this.isNoRotationHorizontalVisible());
        this.settingNoRotationVertical = new NumberSetting(this, "No rotation vertical", 1.0, 0.0, 3.0, 2, this.settingAutoAim).getNumberSetting(() -> this.isNoRotationVerticalVisible());
        this.settingHorizontalBoxSize = new NumberSetting(this, "Horizontal box size", 20.0, 0.0, 100.0, 0, this.settingAutoAim);
        this.settingRandomizationStrength = new NumberSetting(this, "Randomization strength", 0.0, 0.0, 5.0, 0, this.settingAutoAim);
        this.settingRandomizeInNoRotationFovs = (BooleanSetting)new BooleanSetting(this, "Randomize in no rotation fovs", true, this.settingAutoAim).getSetting(() -> this.isRandomizeInNoRotationFovsVisible());
        this.settingHorizontalFov = new NumberSetting(this, "Horizontal fov", 90.0, 0.0, 180.0, 0);
        this.settingVerticalFov = new NumberSetting(this, "Vertical fov", 90.0, 0.0, 180.0, 0);
        this.settingClick = new BooleanSetting(this, "Click", true);
        this.settingDiggingCheck = new BooleanSetting(this, "Digging check", true);
        this.settingSingleTarget = new BooleanSetting(this, "Single target", true);
        AimAssistTargetEntity[] aimAssistTargetEntityArray = AimAssistTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", aimAssistTargetEntityArray);
        this.auxiliarySettingAdapter = new TargetFilterSetting(this);
        this.motionMultiplier = 0.0F;
        this.target = (EntityLivingBase)null;
        this.targetEntity2 = (EntityLivingBase)null;
        this.coordOffsetArray3 = new double[2];
        this.coordOffsetArray = new double[2];
        int[] countArray = new int[2];
        this.intValueArray = countArray;
        this.intValueArray2 = new int[2];
        this.coordOffsetArray2 = new double[2];
        this.intValueArray3 = new int[2];
        this.simulatedPlayer = new SimulatedPlayer();
        this.timestampMillis = 0L;
        this.tickDelay = 0;
        this.timestampMillis1 = 0L;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(19);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.target = null;
        this.targetEntity2 = null;
    }

    @Override
    public void onPreDisable() {
        this.timestampMillis1 = System.currentTimeMillis();
        this.timestampMillis = System.currentTimeMillis();
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        if (ClientGlobals.moduleManager.freelook.isToggled()) {
            event.processAction(0);
            event.processAction2(0);
        }

        if (this.checkCondition()) {
            if (this.settingMode.getSelected().equals("Stable")) {
                this.onSilentMove2(event);
            } else {
                this.onSilentMove3(event);
            }
        }

        this.motionMultiplier = event.getScaleFactor();
        this.timestampMillis1 = System.currentTimeMillis();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.target = null;
        this.targetEntity2 = null;
    }

    @Override
    public void onTick() {
        this.motionMultiplier = 0.0F;
        this.targetEntity2 = this.target;
        this.target = null;
        if (minecraft.isIntegratedServerRunning()) {
            Object[] itemArray = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object -> this.checkEntity((Entity)object))
            .sorted(Comparator.comparingDouble(object2 -> this.getCoordinate2((Entity)object2)))
            .toArray();
            if (itemArray.length > 0) {
                this.target = (EntityLivingBase)itemArray[0];
            }

            this.settingPreAimRange.recalculate();
        }
    }

    @Override
    public void onPostTick() {
        this.tickDelay = (int)Math.abs(this.timestampMillis - System.currentTimeMillis());
        this.timestampMillis = System.currentTimeMillis();
        this.intValueArray3[0] = this.intValueArray3[1] = 0;
        this.coordOffsetArray2[0] = this.coordOffsetArray2[1] = 0.0;
    }

    public static void setIntValueArray4(int[] countArray) {
        intValueArray4 = countArray;
    }

    private double getCoordinate(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = this.targetEntity2.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
        }
    }

    private void onSilentMove2(EventSilentMove event) {
        int index = (int)(System.currentTimeMillis() - this.timestampMillis1);
        Vec3 vec = SimulatedPlayer.getVec3(this.target, event.getScaleFactor());
        Rotation rotation9 = SimulatedPlayer.getRotation9(
        
        vec.xCoord,
        vec.yCoord,
        vec.zCoord,
        new Rotation(this.getFloat(), this.getFloat2()),
        180.0F,
        180.0F,
        false,
        event.getScaleFactor()
        );
        int[] intArray = SimulatedPlayer.getIntArray(
        rotation9.yaw,
        rotation9.pitch,
        this.getFloat(),
        this.getFloat2()
        );
        Rotation rotation92 = SimulatedPlayer.getRotation9(
        
        vec.xCoord,
        vec.yCoord,
        vec.zCoord,
        new Rotation(this.getFloat(), this.getFloat2()),
        4.0F * (event.getScaleFactor() - this.motionMultiplier),
        4.0F,
        false,
        event.getScaleFactor()
        );
        SimulatedPlayer.getIntArray(
        rotation92.yaw,
        rotation92.pitch,
        this.getFloat(),
        this.getFloat2()
        );
        double offset = (double)this.intValueArray2[0] / (double)this.tickDelay * (double)index;
        double offset2 = (double)this.intValueArray2[1] / (double)this.tickDelay * (double)index;
        this.coordOffsetArray2[0] = this.coordOffsetArray2[0] + offset;
        this.coordOffsetArray2[1] = this.coordOffsetArray2[1] + offset2;
        int index2 = (int)Math.round(this.coordOffsetArray2[0] - (double)this.intValueArray3[0]);
        int index3 = (int)Math.round(this.coordOffsetArray2[1] - (double)this.intValueArray3[1]);
        this.intValueArray3[0] = this.intValueArray3[0] + index2;
        this.intValueArray3[1] = this.intValueArray3[1] + index3;
        if (event.getIndex2() != 0) {
            int index4 = intArray[0];
            if (index4 < 0) {
                if (index4 - event.getIndex2() <= index4) {
                    double offset3 = (double)event.getIndex2() * this.settingHorizontalAssistMultiplier.getValue() + this.coordOffsetArray3[0];
                    int index5 = (int)Math.round(offset3);
                    event.processAction(index5);
                    this.coordOffsetArray3[0] = offset3 - (double)index5;
                }
            } else if (index4 - event.getIndex2() >= index4) {
                double offset4 = (double)event.getIndex2() * this.settingHorizontalAssistMultiplier.getValue() + this.coordOffsetArray3[0];
                int index6 = (int)Math.round(offset4);
                event.processAction(index6);
                this.coordOffsetArray3[0] = offset4 - (double)index6;
            }
        }

        if (event.getIndex() != 0) {
            int index7 = intArray[1];
            if (index7 < 0) {
                if (index7 - event.getIndex() <= index7) {
                    double offset5 = (double)event.getIndex() * this.settingVerticalAssistMultiplier.getValue() + this.coordOffsetArray3[1];
                    int index8 = (int)Math.round(offset5);
                    event.processAction2(index8);
                    this.coordOffsetArray3[1] = offset5 - (double)index8;
                }
            } else if (index7 - event.getIndex() >= index7) {
                double offset6 = (double)event.getIndex() * this.settingVerticalAssistMultiplier.getValue() + this.coordOffsetArray3[1];
                int index9 = (int)Math.round(offset6);
                event.processAction2(index9);
                this.coordOffsetArray3[1] = offset6 - (double)index9;
            }
        }

        double offset7 = (double)event.getIndex2() * this.settingOwnHorizontalRotationMultiplier.getValue() + this.coordOffsetArray[0];
        int index10 = (int)Math.round(offset7);
        event.processAction(index10);
        this.coordOffsetArray[0] = offset7 - (double)index10;
        double offset8 = (double)event.getIndex() * this.settingOwnVerticalRotationMultiplier.getValue() + this.coordOffsetArray[1];
        int index11 = (int)Math.round(offset8);
        event.processAction2(index11);
        this.coordOffsetArray[1] = offset8 - (double)index11;
        event.processAction(event.getIndex2() + index2);
        event.processAction2(event.getIndex() + index3);
    }

    private float getFloat() {
        return Client.getClient().getViaProtocolHelper().scaleFactor1;
    }

    private void onSilentMove3(EventSilentMove event) {
        Vec3 vec = SimulatedPlayer.getVec3(this.target, event.getScaleFactor());
        Rotation rotation9 = SimulatedPlayer.getRotation9(
        
        vec.xCoord,
        vec.yCoord,
        vec.zCoord,
        new Rotation(this.getFloat(), this.getFloat2()),
        180.0F,
        180.0F,
        false,
        event.getScaleFactor()
        );
        double doubleEntry = HitBoxUtils.getDouble(
        (double)MathHelper.sqrt_float(minecraft.thePlayer.rotationYaw), MathHelper.getDouble4((double)rotation9.yaw)
        );
        double offset = (double)(-(rotation9.pitch - minecraft.thePlayer.rotationPitch));
        if (minecraft.gameSettings.state20 && !event.canExecute()) {
            offset *= -1.0;
        }

        double double2 = this.settingStrength.getDouble2(true);
        double double22 = this.settingFasterMouseMovement.getDouble2(true);
        if ((double)Math.signum((float)event.getIndex2()) == Math.signum(doubleEntry)
        && (
        (double)Math.abs(event.getIndex2()) <= Math.abs(doubleEntry)
        || Math.abs((double)event.getIndex2() / double2 - doubleEntry) >= Math.abs(doubleEntry - (double)event.getIndex2())
        )) {
            if ((double)((int)((double)Math.abs(event.getIndex2()) * double22)) < Math.abs(doubleEntry)) {
                event.processAction((int)((double)event.getIndex2() * double22));
            }
        } else {
            int index = (int)((double)event.getIndex2() / double2);
            event.processAction(index);
        }

        if ((double)Math.signum((float)event.getIndex()) == Math.signum(offset)
        && (
        (double)Math.abs(event.getIndex()) <= Math.abs(offset)
        || Math.abs((double)event.getIndex() / double2 - offset) >= Math.abs(offset - (double)event.getIndex())
        )) {
            if ((double)((int)((double)Math.abs(event.getIndex()) * double22)) < Math.abs(offset)) {
                int index2 = (int)((double)event.getIndex() * double22);
                event.processAction2(index2);
            }
        } else {
            int index3 = (int)((double)event.getIndex() / double2);
            event.processAction2(index3);
        }
    }

    public static int[] getIntValueArray4() {
        return intValueArray4;
    }

    private boolean isNoRotationVerticalFovVisible() {
        return this.settingNoRotationFovMode.getSelected().equals("2D");
    }

    private double getCoordinate2(Entity entity) {
        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;
        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
        Client client = Client.getClient();
        return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
    }

    private boolean isModeVisible() {
        return this.settingMode.getSelected().equals("Stable");
    }

    private boolean checkEntity(Entity entity) {
        return entity instanceof EntityPlayerSP;
    }

    private float getFloat2() {
        return Client.getClient().getViaProtocolHelper().scaleFactor2;
    }

    private boolean canExecute5() {
        return this.settingMode.getSelected().equals("Experimental");
    }

    private boolean isNoRotationHorizontalVisible() {
        return this.settingNoRotationFovMode.getSelected().equals("3D");
    }

    private boolean isNoRotationVerticalVisible() {
        return this.settingNoRotationFovMode.getSelected().equals("3D");
    }

    private boolean isRandomizeInNoRotationFovsVisible() {
        return this.settingRandomizationStrength.getValue() != 0.0;
    }

    private boolean checkCondition() {
        if (minecraft.thePlayer.isDead) {
            return false;
        } else if (minecraft.thePlayer.ticksExisted <= 20) {
            return false;
        } else {
            if (this.target != null) {
                if (!minecraft.isIntegratedServerRunning()) {
                    return false;
                }

                if (this.settingClick.isEnabled() && !minecraft.gameSettings.keyBindAttack.isKeyDown()) {
                    return false;
                }

                if (ClientGlobals.rotationManager.checkModule(ClientGlobals.moduleManager.autoRod)) {
                    return false;
                }

                if (!this.settingDiggingCheck.isEnabled()) {
                    return true;
                }

                if (!minecraft.gameSettings.keyBindAttack.isKeyDown()) {
                    return true;
                }

                if (minecraft.objectMouseOver == null) {
                    return true;
                }

                if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.BLOCK) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean isNoRotationHorizontalFovVisible() {
        return this.settingNoRotationFovMode.getSelected().equals("2D");
    }
}
