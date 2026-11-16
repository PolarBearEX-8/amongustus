package augustus.modules.legit;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventSilentMove;
import client.event.MoveListener;
import client.event.RenderTickListener;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.RotationManager;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.EagleSneakHelper;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.RayTraceHit;
import client.util.RayTraceUtils;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class AutoEagle extends Module implements ClickListener, MoveListener, TickListener, SilentMoveListener, RenderTickListener {
    public ModeSetting settingMode;
    public RangeSetting settingMinSneakTicks;
    public ActionSetting settingMoonWalkWideness;
    public ActionSetting settingStartDistToBlock;
    public BooleanSetting settingSilentRotation;
    public BooleanSetting settingIntave;
    public BooleanSetting settingMidRotation;
    public BooleanSetting settingFastPlace;
    public RangeSetting settingCPS;
    public ModeSetting settingDCPSMode;
    public RangeSetting settingDCPS;
    public BooleanSetting settingAlwaysPlaceWhenPossible;
    public BooleanSetting settingDelayClick;
    public BooleanSetting settingClickPause;
    public BooleanSetting settingOneClick;
    public BooleanSetting settingOnlyWhileSneak;
    public EagleSneakHelper internalManager;
    public MovingObjectPosition movingObjectPosition;
    public boolean visible2;
    public boolean activeState1;
    public int tickDelay4;
    public int tickDelay2;
    public double reachDistance3;
    public double reachDistance;
    public boolean activeState7;
    public boolean expanded2;
    public boolean activeState;
    public long timestampMillis9;
    public long timestampMillis;
    public int keyCode2;
    public int tickDelay6;
    public boolean enabled2;
    public int tickDelay;
    public int tickDelay8;
    public int tickDelay5;

    public AutoEagle() {
        super("AutoEagle", "AutoAdler", new Color(194, 222, 146), Category.LEGIT);
        String[] textArray = new String[3];
        textArray[0] = "Eagle";
        textArray[1] = "MoonWalk";
        textArray[2] = "UnLegit";
        this.settingMode = new ModeSetting(this, "Mode", "Eagle", textArray);
        this.settingMinSneakTicks = new RangeSetting(this, "MinSneakTicks", 1.0, 2.0, 1.0, 5.0, 0)
        .getRangeSetting2().getRangeSetting(3.0, 3.0, 1.0, 2.0, 15.0, 25.0).getRangeSetting(15343, () -> this.isMinSneakTicksVisible());
        this.settingMoonWalkWideness = new ActionSetting(this, "MoonWalkWideness", 0.05, 0.1, 0.0, 0.3, 2).getActionSetting(() -> this.isMoonWalkWidenessVisible());
        this.settingStartDistToBlock = new ActionSetting(this, "StartDistToBlock", 0.05, 0.2, -0.2, 0.5, 2).getActionSetting(() -> this.isStartDistToBlockVisible2());
        this.settingSilentRotation = (BooleanSetting)new BooleanSetting(this, "SilentRotation", false).getSetting(() -> this.isStartDistToBlockVisible());
        this.settingIntave = (BooleanSetting)new BooleanSetting(this, "Intave", false).getSetting(() -> this.isStartDistToBlockVisible3());
        this.settingMidRotation = (BooleanSetting)new BooleanSetting(this, "MidRotation", false).getSetting(() -> this.isStartDistToBlockVisible4());
        this.settingFastPlace = new BooleanSetting(this, "FastPlace", true);
        this.settingCPS = new RangeSetting(this, "CPS", 10.0, 20.0, 0.0, 40.0, 0).getRangeSetting2().getRangeSetting(15343, () -> this.settingFastPlace.isEnabled());
        String[] textArray2 = new String[2];
        textArray2[0] = "Internal";
        textArray2[1] = "External";
        this.settingDCPSMode = new ModeSetting(this, "DCPSMode", "External", textArray2).getModeSetting(() -> this.settingFastPlace.isEnabled());
        this.settingDCPS = new RangeSetting(this, "DCPS", 2.0, 4.0, 0.0, 20.0, 0).getRangeSetting2().getRangeSetting(15343, () -> this.isDCPSVisible());
        this.settingAlwaysPlaceWhenPossible = (BooleanSetting)new BooleanSetting(this, "AlwaysPlaceWhenPossible", true).getSetting(() -> this.settingFastPlace.isEnabled());
        this.settingDelayClick = (BooleanSetting)new BooleanSetting(this, "DelayClick", false).getSetting(() -> this.settingFastPlace.isEnabled());
        this.settingClickPause = (BooleanSetting)new BooleanSetting(this, "ClickPause", false).getSetting(() -> this.settingFastPlace.isEnabled());
        this.settingOneClick = (BooleanSetting)new BooleanSetting(this, "OneClick", false).getSetting(() -> this.settingFastPlace.isEnabled());
        this.settingOnlyWhileSneak = new BooleanSetting(this, "OnlyWhileSneak", true);
        this.internalManager = new EagleSneakHelper(this.settingCPS, this.settingDCPSMode, this.settingDCPS, this.settingAlwaysPlaceWhenPossible, this.settingOneClick, this.settingDelayClick);
        this.movingObjectPosition = (MovingObjectPosition)null;
        this.visible2 = false;
        this.tickDelay4 = 0;
        this.tickDelay2 = 1;
        this.reachDistance3 = 0.0;
        this.reachDistance = 0.0;
        this.activeState7 = false;
        this.expanded2 = false;
        this.activeState = false;
        this.timestampMillis9 = 0L;
        this.timestampMillis = 0L;
        this.keyCode2 = 0;
        this.tickDelay6 = 0;
        this.tickDelay = 0;
        this.tickDelay8 = SettingContainer.settingProvider.getInt2(0, 2);
        this.tickDelay5 = SettingContainer.settingProvider.getInt2(0, 2);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(21);
        this.registerEvent(40);
        this.registerEvent(19);
        this.registerEvent(29);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.settingMinSneakTicks.recalculate();
        this.visible2 = true;
    }

    @Override
    public void onClick(EventClick event) {
        double offset = minecraft.thePlayer.posY - 0.5;
        boolean state18 = minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ).getBlock().isState18();
        this.enabled2 = state18;
        int index = this.tickDelay8 - 1;
        CombatUtil.getCoordOffsetArray(index, minecraft.thePlayer);
        if (state18
        && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
        && minecraft.thePlayer.getHeldItem().getItem() instanceof ItemBlock
        && minecraft.objectMouseOver.getBlockPos() != null) {
            event.setState3(true);
            event.setState11(false);
        }
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        this.timestampMillis9 = System.currentTimeMillis();
    }

    @Override
    public void onMove(EventMove event) {
        if (!this.settingOnlyWhileSneak.isEnabled()) {
            event.setState4(true);
        }

        this.activeState = event.isState4();
        boolean enabled;
        if (!this.settingFastPlace.isEnabled()
        || !event.isState4() && (!this.settingMode.getSelected().equals("MoonWalk") || !this.checkCondition())) {
            enabled = false;
        } else {
            enabled = true;
        }

        this.activeState1 = enabled;
        if (event.isState4() || !this.settingOnlyWhileSneak.isEnabled()) {
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "MoonWalk":
                this.onMove3(event);
                break;
            case "Eagle":
                this.onMove4(event);
                break;
            case "UnLegit":
                this.onMove6(event);
        }
        }

        if (!event.isState4() && this.activeState7 && this.settingMode.getSelected().equals("Eagle")) {
            double double3 = this.settingStartDistToBlock.getDouble3();
            double double6 = this.settingStartDistToBlock.getDouble6();
            this.reachDistance3 = -SettingContainer.settingProvider.getDouble4(double3, double6);
            this.settingMinSneakTicks.recalculate();
        }

        this.activeState7 = event.isState4();
    }

    @Override
    public void onPostRenderTick() {
        if (this.movingObjectPosition != null) {
            minecraft.objectMouseOver = this.movingObjectPosition;
        }
    }

    @Override
    public void onClientTick() {
        this.keyCode2 = (int)Math.abs(this.timestampMillis - System.currentTimeMillis());
        this.timestampMillis = System.currentTimeMillis();
    }

    @Override
    public void onPreTick() {
        this.movingObjectPosition = null;
        if ((this.settingMode.getSelected().equals("UnLegit") ? 1 : 0) > 0) {
            float rotationYaw = minecraft.thePlayer.rotationYaw;
            ArrayList arrayList = new ArrayList();
            byte state;
            if (this.settingIntave.isEnabled()) {
                state = 85;
            } else {
                state = 90;
            }

            if (72.0F == (float)state) {
                RayTraceHit movingObjectPositionEntry = (RayTraceHit)arrayList.get(0);
                if (this.settingIntave.isEnabled()) {
                    arrayList.sort(Comparator.comparingDouble(object -> this.getCoordinate(rotationYaw, (RayTraceHit)object)));
                }

                arrayList.sort(Comparator.comparingDouble(object2 -> this.getCoordinate((RayTraceHit)object2)));
                RayTraceHit movingObjectPositionEntry2 = (RayTraceHit)arrayList.get(0);
                float scaleFactor = movingObjectPositionEntry2.scaleFactor;
                ClientGlobals.rotationManager.applyModule(scaleFactor, -2, this);
                this.tickDelay6 = SimulatedPlayer.getIntArray(
                0.0F, movingObjectPositionEntry2.scaleFactor, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch
                )[1];
                this.movingObjectPosition = movingObjectPositionEntry2.movingObjectPosition;
            }
        }
    }

    private float getFloat(float value) {
        float float4 = MathHelper.sqrt_float(value);
        if (float4 > -25.0F && float4 < 25.0F) {
            return 0.0F;
        } else {
            if (float4 > -25.0F && float4 < 70.0F) {
                return 45.0F;
            } else {
                if (float4 > 70.0F && float4 < 115.0F) {
                    return 90.0F;
                } else {
                    if (float4 > 115.0F
                    && float4 < 160.0F) {
                        return 135.0F;
                    } else {
                        if (float4 <= 160.0F) {
                            if (float4 < -160.0F) {
                                return 180.0F;
                            } else {
                                if (float4 > -160.0F
                                && float4 < -115.0F) {
                                    return -135.0F;
                                } else {
                                    if (float4 > -115.0F
                                    && float4 < -70.0F) {
                                        return -90.0F;
                                    } else {
                                        return float4 > -70.0F && float4 < -25.0F ? -45.0F : value;
                                    }
                                }
                            }
                        } else {
                            return 180.0F;
                        }
                    }
                }
            }
        }
    }

    private double getCoordinate(RayTraceHit movingObjectPositionEntry, float value) {
        double offset = RayTraceUtils.getMovingObjectPosition6(1.0F, value, movingObjectPositionEntry.scaleFactor).hitVec.yCoord % 1.0;
        return Math.abs(0.5 - offset);
    }

    private void onMove2(EventMove event, BlockPos blockPos, double offset, boolean enabled) {
        if ((enabled ? 1 : 0) > 0) {
            this.tickDelay2 = 1;
        }

        event.setScaleFactor2((float)this.tickDelay2);
    }

    private void onMove3(EventMove event) {
        if (this.checkCondition()) {
            event.setState4(false);
        }

        BlockPos blockPos15 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ).down();
        minecraft.theWorld.getBlockState(blockPos15).getBlock().isState18();
        byte state = ((byte)(this.expanded2 ? 1 : 0));
        Iterator iterator = minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, minecraft.thePlayer.getBoundingBox2().expand(0.0, -1.0, 0.0)).iterator();
        if (iterator.hasNext()) {
            AxisAlignedBB boundingBox = (AxisAlignedBB)iterator.next();
        } else {
            switch (state) {
                case 1:
                    this.onMove5(event, blockPos15, minecraft.thePlayer.posZ, true);
                    this.onMove5(event, blockPos15, minecraft.thePlayer.posZ, false);
                    this.onMove2(event, blockPos15, minecraft.thePlayer.posX, true);
                    this.onMove2(event, blockPos15, minecraft.thePlayer.posX, false);
                    return;
                case 2:
                    this.onMove5(event, blockPos15, minecraft.thePlayer.posZ, false);
                    double posX = minecraft.thePlayer.posX;
                    this.onMove2(event, blockPos15, posX, true);
                    this.onMove2(event, blockPos15, minecraft.thePlayer.posX, false);
                    return;
                case 3:
                    double posX2 = minecraft.thePlayer.posX;
                    this.onMove2(event, blockPos15, posX2, true);
                    this.onMove2(event, blockPos15, minecraft.thePlayer.posX, false);
                    return;
                case 4:
                    this.onMove2(event, blockPos15, minecraft.thePlayer.posX, false);
                    return;
            }
        }
    }

    private void onMove4(EventMove event) {
        if (MoveUtil.canExecute()) {
            boolean enabled = false;

            for (AxisAlignedBB boundingBox : ((Iterable<AxisAlignedBB>) minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, minecraft.thePlayer.getBoundingBox2().expand(0.0, -1.0, 0.0)))) {
                AxisAlignedBB axisAlignedBB6 = minecraft.thePlayer.getBoundingBox2().offset(-0.3, 0.0, -0.3).expand(0.0, -1.0, 0.0);
                double offset = Math.abs(minecraft.thePlayer.motionX) > 0.02 ? this.reachDistance3 : 0.0;
                double offset2 = Math.abs(minecraft.thePlayer.motionZ) > 0.02 ? this.reachDistance3 : 0.0;
                if (axisAlignedBB6.intersectsWith(boundingBox.offset(offset, 0.0, offset2))) {
                    enabled = true;
                    break;
                }
            }

            if (event.isState4() && !enabled) {
                event.setState4(true);
                this.tickDelay4 = 0;
            } else {
                int tickDelay42 = this.tickDelay4;
                int indexOffset2 = this.settingMinSneakTicks.getIndexOffset2();
                if (tickDelay42 >= indexOffset2 && minecraft.gameSettings.keyBindUseItem.isKeyDown()) {
                    event.setState4(false);
                }
            }

            int index = this.tickDelay4 + 1;
            this.tickDelay4 = index;
        }
    }

    private double getCoordinate(RayTraceHit movingObjectPositionEntry) {
        RotationManager rotationManager = ClientGlobals.rotationManager;
        return (double)Math.abs(movingObjectPositionEntry.scaleFactor - rotationManager.getRotation3().pitch);
    }

    private boolean isStartDistToBlockVisible() {
        return this.settingMode.getSelected().equals("UnLegit");
    }

    private double getCoordinate(float value, RayTraceHit movingObjectPositionEntry) {
        return this.getCoordinate(movingObjectPositionEntry, value);
    }

    private void onMove5(EventMove event, BlockPos blockPos, double offset, boolean enabled) {
        if ((enabled ? 1 : 0) > 0) {
            this.tickDelay2 = 1;
        }

        event.setScaleFactor2((float)this.tickDelay2);
    }

    private void onMove6(EventMove event) {
        if ((MoveUtil.canExecute() ? 1 : 0) > 0) {
            event.setState4(false);
        }
    }

    private boolean isStartDistToBlockVisible2() {
        return this.settingMode.getSelected().equals("UnLegit");
    }

    private boolean isMoonWalkWidenessVisible() {
        return this.settingMode.getSelected().equals("MoonWalk");
    }

    private boolean isMinSneakTicksVisible() {
        return this.settingMode.getSelected().equals("Eagle");
    }

    private boolean checkCondition() {
        return minecraft.thePlayer.rotationPitch > 81.0F;
    }

    private boolean isStartDistToBlockVisible3() {
        return this.settingMode.getSelected().equals("UnLegit");
    }

    private boolean isDCPSVisible() {
        return this.settingDCPSMode.isVisible();
    }

    private boolean isStartDistToBlockVisible4() {
        return this.settingMode.getSelected().equals("UnLegit");
    }
}
