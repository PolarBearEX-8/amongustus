package augustus.modules.movement;

import augustus.modules.combat.KillAura;
import client.Client;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.EventMoveInput;
import client.event.EventWorldLoad;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.ActionModule;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.render.PathRenderer;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.AutoWalkTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.AutoWalkPath;
import client.util.BooleanSupplier;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.PathNode;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import org.lwjgl.opengl.GL11;

public class AutoWalk extends ActionModule implements TickListener, MoveInputListener, MoveListener, RenderListener, WorldLoadListener {
    public BooleanSetting settingPathFinding;
    public BooleanSetting settingDebugLine;
    public NumberSetting settingDistance;
    public NumberSetting settingMaxSearchTime;
    public NumberSetting settingUpdateTicks;
    public BooleanSetting settingSmooth;
    public BooleanSetting settingKeepRotation;
    public ModeSetting settingMovementmode;
    public NumberSetting settingRotationUpdateTicks;
    public MultiSelectSetting settingTargets;
    public BooleanSetting settingOnlyWithKillAuraTarget;
    public NumberSetting settingNoWalkDistanceToTarget;
    public BooleanSetting settingJump;
    public BooleanSetting settingStopWhileEating;
    public BooleanSetting settingStopWhileSlowness;
    public BooleanSetting settingAdStafeOnHit;
    public NumberSetting settingAdStafeTime;
    public NumberSetting settingDelayAfterRespawn;
    public TimeHelper timer;
    public HashSet entrySet;
    public SimulatedPlayer simulatedPlayer;
    public ArrayList itemList3;
    public TimeHelper timer2;
    public int tickDelay;
    public boolean visible2;
    public String name2;
    public int[] intValueArray;
    public float motionMultiplier;
    public long timestampMillis2;
    public long timestampMillis;
    public boolean enabled2;
    public boolean activeState1;
    public Rotation rotation;
    public long timestampMillis3;
    public Rotation rotation2;
    public long timestampMillis4;

    public AutoWalk() {
        super("AutoWalk", "AutoLaufen", Color.red, Category.MOVEMENT);
        this.settingPathFinding = new BooleanSetting(this, "PathFinding", false);
        Setting setting = new BooleanSetting(this, "DebugLine", true).getSetting(() -> this.settingPathFinding.isEnabled());
        this.settingDebugLine = (BooleanSetting)setting;
        this.settingDistance = new NumberSetting(this, "Distance", 50.0, 3.0, 50.0, 1).getNumberSetting(() -> this.settingPathFinding.isEnabled());
        this.settingMaxSearchTime = new NumberSetting(this, "MaxSearchTime", 50.0, 10.0, 1000.0, 0).getNumberSetting(() -> this.settingPathFinding.isEnabled());
        this.settingUpdateTicks = new NumberSetting(this, "UpdateTicks", 1.0, 1.0, 10.0, 0).getNumberSetting(() -> this.settingPathFinding.isEnabled());
        Setting setting2 = new BooleanSetting(this, "Smooth", true).getSetting(() -> this.settingPathFinding.isEnabled());
        this.settingSmooth = (BooleanSetting)setting2;
        Setting setting3 = new BooleanSetting(this, "KeepRotation", true).getSetting(() -> this.settingPathFinding.isEnabled());
        this.settingKeepRotation = (BooleanSetting)setting3;
        String[] textArray = new String[2];
        textArray[0] = "Rotation";
        textArray[1] = "Silent";
        this.settingMovementmode = new ModeSetting(this, "Movementmode", "Rotation", textArray).getModeSetting(() -> this.settingPathFinding.isEnabled());
        this.settingRotationUpdateTicks = new NumberSetting(this, "RotationUpdateTicks", 0.0, 0.0, 10.0, 0).getNumberSetting(() -> this.isRotationUpdateTicksVisible());
        AutoWalkTargetEntity[] autoWalkTargetEntityArray = AutoWalkTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", autoWalkTargetEntityArray).getMultiSelectSetting(() -> this.settingPathFinding.isEnabled());
        this.settingOnlyWithKillAuraTarget = new BooleanSetting(this, "OnlyWithKillAuraTarget", true);
        this.settingNoWalkDistanceToTarget = new NumberSetting(this, "NoWalkDistanceToTarget", 1.0, 0.0, 6.0, 2);
        this.settingJump = new BooleanSetting(this, "Jump", true);
        this.settingStopWhileEating = new BooleanSetting(this, "StopWhileEating", true);
        this.settingStopWhileSlowness = new BooleanSetting(this, "StopWhileSlowness", false);
        BooleanSetting booleanSetting9 = new BooleanSetting(this, "AdStafeOnHit", true);
        String text = "AdStrafeOnHit";
        this.settingAdStafeOnHit = (BooleanSetting)booleanSetting9.getSetting(text);
        NumberSetting numberSetting10 = new NumberSetting(this, "AdStafeTime", 200.0, 0.0, 1000.0, 0);
        String text2 = "AdStrafeTime";
        this.settingAdStafeTime = numberSetting10.getNumberSetting(text2).getNumberSetting(() -> this.settingAdStafeOnHit.isEnabled());
        this.settingDelayAfterRespawn = new NumberSetting(this, "DelayAfterRespawn", 0.0, 2000.0, 10000.0, 0);
        this.timer = new TimeHelper();
        this.entrySet = new HashSet();
        SimulatedPlayer simulatedPlayer2 = new SimulatedPlayer();
        this.simulatedPlayer = simulatedPlayer2;
        this.itemList3 = new ArrayList();
        this.timer2 = new TimeHelper();
        this.visible2 = false;
        this.name2 = "";
        this.expanded2 = true;
        this.modeSetting3.setSelected("None");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvent(40, 1);
        this.registerEvent(36);
        this.registerEvent(32);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onPreDisable() {
        super.onPreDisable();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.timer2.reset();
    }

    @Override
    public void onMove(EventMove event) {
        if (!Client.getClient().getModuleManager().freeCam.isToggled()) {
            if (!this.canExecute6()) {
                return;
            }

            if (!minecraft.isIntegratedServerRunning()) {
                return;
            }

            if (this.canExecute5()) {
                EntityLivingBase livingEntity = null;
                if (this.settingPathFinding.isEnabled() && this.itemList3 != null && !this.itemList3.isEmpty()) {
                    livingEntity = (EntityLivingBase)this.getEntity();
                }

                if (this.canExecute7()) {
                    return;
                }

                if (ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null || livingEntity != null) {
                    if (ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null) {
                        livingEntity = ClientGlobals.moduleManager.killAura.target;
                    }

                    if (livingEntity != null) {
                        if (this.settingMovementmode.getSelected().equalsIgnoreCase("rotation") || !this.settingPathFinding.isEnabled()) {
                            double coord = (double)minecraft.thePlayer.getDistanceToEntity(livingEntity);
                            NumberSetting settingNoWalkDistanceToTarget2 = this.settingNoWalkDistanceToTarget;
                            if (coord > settingNoWalkDistanceToTarget2.getValue()) {
                                float factor = 1.0F;
                                event.setScaleFactor7(factor);
                            }
                        } else if (this.intValueArray != null) {
                            float factor2 = (float)this.intValueArray[0];
                            event.setScaleFactor2(factor2);
                            float factor3 = (float)this.intValueArray[1];
                            event.setScaleFactor7(factor3);
                            if (this.enabled2) {
                                event.setState(true);
                            }
                        }

                        if (this.settingJump.isEnabled()
                        && minecraft.thePlayer.getDistanceToEntity(livingEntity) > 6.0F
                        && (
                        !this.settingMovementmode.getSelected().equalsIgnoreCase("silent")
                        || this.activeState1
                        || !this.settingPathFinding.isEnabled()
                        )) {
                            event.setState(true);
                        }

                        if (this.settingAdStafeOnHit.isEnabled()
                        && (double)minecraft.thePlayer.getDistanceToEntity(livingEntity) < 4.5) {
                            if (livingEntity.hurtTime == 9) {
                                this.timer.reset();
                                this.visible2 = !this.visible2;
                            }

                            TimeHelper timer3 = this.timer;
                            double value = this.settingAdStafeTime.getValue();
                            if (!timer3.hasReachedDouble(value)) {
                                float factor4 = this.visible2 ? 1.0F : -1.0F;
                                event.setScaleFactor2(factor4);
                            }
                        }
                    }

                    return;
                }

                if (!this.settingOnlyWithKillAuraTarget.isEnabled()) {
                    float factor5 = 1.0F;
                    event.setScaleFactor7(factor5);
                }
            }
        }
    }

    @Override
    public void onRender3D(float factor) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GlStateManager.disableAlpha();
        GL11.glDepthMask(false);
        GL11.glTranslated(
        -minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ()
        );
        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.settingDebugLine.isEnabled() && this.itemList3 != null && !this.itemList3.isEmpty() && this.settingPathFinding.isEnabled()) {
            GL11.glBegin(3);
            if (this.itemList3 != null) {
                for (PathNode pathNode : (Iterable<PathNode>) this.itemList3) {
                    GL11.glVertex3d(
                    (double)pathNode.getBlockPlaceData().indexOffset1 + 0.5,
                    (double)pathNode.getBlockPlaceData().indexOffset2 + 0.01,
                    (double)pathNode.getBlockPlaceData().indexOffset + 0.5
                    );
                }
            }

            GL11.glEnd();
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GlStateManager.enableLighting();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2848);
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        if (!this.activeState1 && this.settingPathFinding.isEnabled() && this.settingMovementmode.getSelected().equalsIgnoreCase("silent")) {
            Object[] itemArray = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(item -> this.checkEntity((Entity)item))
            .sorted(Comparator.comparingDouble(item2 -> getCoordinate((Entity)item2)))
            .toArray();
            if (itemArray.length > 0 && this.itemList3 != null && !this.itemList3.isEmpty() && (EntityLivingBase)itemArray[0] != null) {
                event.setActive5(false);
            }
        }
    }

    @Override
    public void onPreTick() { Vec3 vec = null; Vec3 vec2 = null; MovingObjectPosition hitResult = null; MovingObjectPosition hitResult2 = null; MovingObjectPosition hitResult3 = null; Vec3 vec3 = null; Vec3 vec4 = null; PathNode pathNode = null; MovingObjectPosition hitResult4 = null; Vec3 vec5 = null; MovingObjectPosition hitResult5 = null; Vec3 vec6 = null; byte b = 0; MovingObjectPosition hitResult6 = null; MovingObjectPosition hitResult7 = null; MovingObjectPosition hitResult8 = null; Vec3 vec7 = null; MovingObjectPosition hitResult9 = null; MovingObjectPosition hitResult10 = null; Vec3 vec8 = null; Vec3 vec9 = null; EntityPlayerSP player = null; boolean condition = false; boolean condition2 = false; ArrayList list = null; EntityPlayerSP player2 = null; int count = 0; BlockPlaceData blockPlaceData = null; PathNode pathNode2 = null; IBlockState blockState = null; boolean condition3 = false; Vec3 vec10 = null; int count2 = 0; Vec3 vec11 = null; MovingObjectPosition hitResult11 = null; MovingObjectPosition hitResult12 = null; Vec3 vec12 = null; Vec3 vec13 = null; boolean condition4 = false; int count3 = 0; Vec3 vec14 = null; Vec3 vec15 = null; MovingObjectPosition hitResult13 = null; MovingObjectPosition hitResult14 = null; Vec3 vec16 = null; MovingObjectPosition hitResult15 = null; MovingObjectPosition hitResult16 = null; MovingObjectPosition hitResult17 = null; Vec3 vec17 = null; MovingObjectPosition hitResult18 = null; Vec3 vec18 = null; Vec3 vec19 = null;
        this.expanded2 = true;
        ModeSetting modeSetting3 = this.modeSetting3;
        String text = "None";
        ModeSetting setting = modeSetting3;
        String text2 = text;
        setting.setSelected(text2);

        int count5 = 0;
        try {
            Module freeCam = Client.getClient().getModuleManager().freeCam;
            count5 = (freeCam.isToggled() ? 1 : 0);
        } catch (Exception e) {
            return;
        }

        try {
            if (((count5) != 0)) {
                return;
            }
        } catch (Exception e2) {
            return;
        }

        try {
            count5 = (this.canExecute6() ? 1 : 0);
        } catch (Exception e3) {
            return;
        }

        try {
            if (((count5) == 0)) {
                return;
            }
        } catch (Exception e4) {
            return;
        }

        int count6 = 0;
        try {
            Minecraft mc = minecraft;
            count5 = (mc.isIntegratedServerRunning() ? 1 : 0);
            count6 = count5;
        } catch (Exception e5) {
            return;
        }

        try {
            if (((count6) == 0)) {
                return;
            }
        } catch (Exception e6) {
            return;
        }

        try {
            BooleanSetting settingPathFinding2 = this.settingPathFinding;
            count6 = (settingPathFinding2.isEnabled() ? 1 : 0);
        } catch (Exception e7) {
            return;
        }

        try {
            if (((count6) == 0)) {
                return;
            }
        } catch (Exception e8) {
            return;
        }

        try {
            BooleanSetting settingKeepRotation2 = this.settingKeepRotation;
            count6 = (settingKeepRotation2.isEnabled() ? 1 : 0);
        } catch (Exception e9) {
            return;
        }

        boolean completed = false;
        try {
            if ((count6) == 0) {
                completed = true;
            }
        } catch (Exception e10) {
            return;
        }
        if (!completed) {
            try {
                Rotation rotation22 = this.rotation2;
                if (rotation22 == null) {
                    completed = true;
                }
            } catch (Exception e11) {
                return;
            }
            if (!completed) {
                try {
                    long time2 = System.currentTimeMillis();
                    long timestampMillis42 = this.timestampMillis4;
                    long time3 = time2 - timestampMillis42;
                    count5 = time3 > 10000L ? 1 : (time3 == 10000L ? 0 : -1);
                    if (count5 >= 0) {
                        completed = true;
                    }
                } catch (Exception e12) {
                    return;
                }
                if (!completed) {
                    try {
                        RotationManager rotationManager = ClientGlobals.rotationManager;
                        float[] floatArray = this.rotation2.getFloatArray();
                        RotationManager rotationManager2 = rotationManager;
                        float[] factorArray = floatArray;
                        rotationManager2.applyFloatArray(factorArray, 99, this);
                    } catch (Exception e13) {
                        return;
                    }
                }
            }
        }


        EntityLivingBase livingEntity;
        Vec3 vec20;
        try {
            livingEntity = (EntityLivingBase)this.getEntity();
            SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
            String selected = ClientGlobals.moduleManager.killAura.settingBestHitVec.getSelected();
            double value = ClientGlobals.moduleManager.killAura.settingHitBoxPercentageVertical2.getValue();
            count5 = (int)value;
            int count7 = (int)ClientGlobals.moduleManager.killAura.settingHitBoxPercentageHorizontal2.getValue();
            NumberSetting settingHitBoxPercentageVertical = ClientGlobals.moduleManager.killAura.settingHitBoxPercentageVertical;
            NumberSetting setting2 = settingHitBoxPercentageVertical;
            value = setting2.getValue();
            int count8 = (int)value;
            double value2 = ClientGlobals.moduleManager.killAura.settingHitBoxPercentageHorizontal.getValue();
            int count9 = (int)value2;
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            SimulatedPlayer simulatedPlayer3 = simulatedPlayer2;
            Entity entity = livingEntity;
            String text3 = selected;
            Rotation rotation4 = rotation3;
            vec20 = simulatedPlayer3.getVec3(entity, text3, count5, count7, count8, count9, rotation4);
            if (livingEntity == null) {
                return;
            }
        } catch (Exception e14) {
            return;
        }

        KillAura killAura2 = null;
        try {
            killAura2 = ClientGlobals.moduleManager.killAura;
        } catch (Exception e15) {
            return;
        }

        boolean completed2 = false;
        try {
            boolean toggled = killAura2.isToggled();
            if (!toggled) {
                completed2 = true;
            }
        } catch (Exception e16) {
            return;
        }
        if (!completed2) {
            KillAura killAura3 = null;
            try {
                killAura2 = ClientGlobals.moduleManager.killAura;
                killAura3 = killAura2;
            } catch (Exception e17) {
                return;
            }
            try {
                EntityLivingBase target = killAura3.target;
                if (target == null) {
                    completed2 = true;
                }
            } catch (Exception e18) {
                return;
            }
            if (!completed2) {
                EntityPlayerSP player3 = null;
                try {
                    Minecraft mc2 = minecraft;
                    Minecraft mc3 = mc2;
                    player3 = mc3.thePlayer;
                } catch (Exception e19) {
                    return;
                }
                try {
                    double coord = (double)player3.getDistanceToEntity(livingEntity);
                    if ((!Double.isNaN(coord) && !Double.isNaN(3.5) ? (coord > 3.5 ? 1 : (coord == 3.5 ? 0 : -1)) : -1) > 0) {
                        completed2 = true;
                    }
                } catch (Exception e20) {
                    return;
                }
                if (!completed2) {
                    try {
                        Minecraft mc4 = minecraft;
                        player = mc4.thePlayer;
                    } catch (Exception e21) {
                        return;
                    }
                    try {
                        double xCoord = vec20.xCoord;
                        double yCoord = vec20.yCoord;
                        double zCoord = vec20.zCoord;
                        boolean condition5 = player.checkCondition(xCoord, yCoord, zCoord);
                        if (condition5) {
                            return;
                        }
                    } catch (Exception e22) {
                        return;
                    }
                }
            }
        }


        EntityPlayerSP player4;
        try {
            Minecraft mc5 = minecraft;
            player4 = mc5.thePlayer;
        } catch (Exception e23) {
            return;
        }

        BlockPlaceData blockPlaceData2;
        String text4;
        BlockPlaceData blockPlaceData3;
        Vec3 vec21;
        try {
            vec21 = player4.getVec32(1.0F);
            Vec3 vec32 = livingEntity.getVec32(1.0F);
            count5 = (int)Math.floor(vec21.xCoord);
            int count10 = (int)Math.floor(vec21.yCoord);
            int count11 = (int)Math.floor(vec21.zCoord);
            blockPlaceData3 = new BlockPlaceData(count5, count10, count11);
            count5 = (int)Math.floor(vec32.xCoord);
            double coord2 = Math.floor(vec32.yCoord);
            count10 = (int)coord2;
            double zCoord2 = vec32.zCoord;
            double coord3 = Math.floor(zCoord2);
            count11 = (int)coord3;
            blockPlaceData2 = new BlockPlaceData(count5, count10, count11);
            text4 = blockPlaceData3.getString();
            boolean condition6 = this.name2.equals(text4);
            condition = condition6;
        } catch (Exception e24) {
            return;
        }

        boolean completed6 = false;
        boolean completed3 = false;
        try {
            if (!condition) {
                completed3 = true;
            }
        } catch (Exception e25) {
            return;
        }
        if (!completed3) {
            int count12 = 0;
            try {
                long time4 = System.currentTimeMillis();
                long timestampMillis32 = this.timestampMillis3;
                long time5 = time4 - timestampMillis32;
                count12 = time5 > 1000L ? 1 : (time5 == 1000L ? 0 : -1);
            } catch (Exception e26) {
                return;
            }
            try {
                if (count12 >= 0) {
                    completed6 = true;
                }
            } catch (Exception e27) {
                return;
            }
        }
        if (!completed6) {
            int count13 = 0;
            try {
                double coordinate = blockPlaceData3.getCoordinate(blockPlaceData2);
                count13 = !Double.isNaN(coordinate) && !Double.isNaN(500.0) ? (coordinate > 500.0 ? 1 : (coordinate == 500.0 ? 0 : -1)) : 1;
            } catch (Exception e28) {
                return;
            }
            try {
                if (count13 >= 0) {
                    completed6 = true;
                }
            } catch (Exception e29) {
                return;
            }
            if (!completed6) {
                try {
                    this.name2 = text4;
                    EntityPlayerSP player5 = minecraft.thePlayer;
                    count13 = player5.ticksExisted;
                    NumberSetting settingUpdateTicks2 = this.settingUpdateTicks;
                    NumberSetting setting3 = settingUpdateTicks2;
                    count5 = (int)setting3.getValue();
                    count13 %= count5;
                } catch (Exception e30) {
                    return;
                }
                boolean completed5 = false;
                try {
                    if (count13 == 0) {
                        completed5 = true;
                    }
                } catch (Exception e31) {
                    return;
                }
                if (!completed5) {
                    try {
                        PrintStream out = System.out;
                        StringBuilder sb = new StringBuilder().append(text4).append(" ");
                        boolean condition7 = this.entrySet.contains(text4);
                        StringBuilder sb2 = sb.append(condition7).append(" (nodes=");
                        int size = this.entrySet.size();
                        String text5 = sb2.append(size).append(")").toString();
                        out.println(text5);
                    } catch (Exception e32) {
                        return;
                    }
                    try {
                        boolean condition8 = this.entrySet.contains(text4);
                        condition2 = condition8;
                    } catch (Exception e33) {
                        return;
                    }
                    boolean completed4 = false;
                    try {
                        if (!condition2) {
                            completed4 = true;
                        }
                    } catch (Exception e34) {
                        return;
                    }
                    if (!completed4) {
                        ArrayList list2 = null;
                        try {
                            count5 = this.tickDelay;
                            this.tickDelay = count5 + 1;
                            list2 = this.itemList3;
                        } catch (Exception e35) {
                            return;
                        }
                        try {
                            int size2 = list2.size();
                            count5 = this.tickDelay;
                            if (size2 > count5) {
                                completed6 = true;
                            }
                        } catch (Exception e36) {
                            return;
                        }
                        if (!completed6) {
                            try {
                                this.tickDelay = this.itemList3.size() - 1;
                                completed6 = true;
                            } catch (Exception e37) {
                                return;
                            }
                        }
                    }
                    if (!completed6) {
                        try {
                            BlockPlaceData blockPlaceData4 = blockPlaceData3;
                            BlockPlaceData blockPlaceData5 = blockPlaceData2;
                            this.applyBlockPlaceData(blockPlaceData4, blockPlaceData5);
                        } catch (Exception e38) {
                            return;
                        }
                        try {
                            this.timestampMillis3 = System.currentTimeMillis();
                            completed6 = true;
                        } catch (Exception e39) {
                            return;
                        }
                    }
                }
                if (!completed6) {
                    try {
                        BlockPlaceData blockPlaceData6 = blockPlaceData3;
                        BlockPlaceData blockPlaceData7 = blockPlaceData2;
                        this.applyBlockPlaceData(blockPlaceData6, blockPlaceData7);
                        this.timestampMillis3 = System.currentTimeMillis();
                    } catch (Exception e40) {
                        return;
                    }
                }
            }
        }


        ArrayList list3;
        try {
            ArrayList itemList32 = this.itemList3;
            list3 = itemList32;
        } catch (Exception e41) {
            return;
        }

        ArrayList list4;
        list4 = list3;

        try {
            if (list4 == null) {
                return;
            }
        } catch (Exception e43) {
            return;
        }

        try {
            list = this.itemList3;
        } catch (Exception e44) {
            return;
        }

        int count16 = 0;
        try {
            count16 = (list.isEmpty() ? 1 : 0);
        } catch (Exception e45) {
            return;
        }

        try {
            if (((count16) != 0)) {
                return;
            }
        } catch (Exception e46) {
            return;
        }

        try {
            ArrayList itemList33 = this.itemList3;
            count16 = itemList33.size();
        } catch (Exception e47) {
            return;
        }

        try {
            if (count16 <= 1) {
                return;
            }
        } catch (Exception e48) {
            return;
        }

        try {
            Minecraft mc8 = minecraft;
            player2 = mc8.thePlayer;
        } catch (Exception e49) {
            return;
        }

        boolean completed7 = false;
        try {
            double motionY = player2.motionY;
            if ((!Double.isNaN(motionY) && !Double.isNaN(0.0) ? (motionY > 0.0 ? 1 : (motionY == 0.0 ? 0 : -1)) : -1) <= 0) {
                count = 1;
                completed7 = true;
            }
        } catch (Exception e50) {
            return;
        }
        if (!completed7) {
            count = 2;
        }


        try {
            ArrayList itemList34 = this.itemList3;
            count16 = this.itemList3.size();
            int tickDelay2 = this.tickDelay;
            count16 = count16 - tickDelay2 - count - 1;
            blockPlaceData2 = (BlockPlaceData)itemList34.get(count16);
        } catch (Exception e52) {
            return;
        }

        PathNode pathNode3;
        try {
            pathNode3 = (PathNode)((Object)(blockPlaceData2));
            blockPlaceData = pathNode3.getBlockPlaceData();
        } catch (Exception e53) {
            return;
        }

        boolean completed8 = false;
        try {
            double coord4 = (double)blockPlaceData.indexOffset2;
            double posY = minecraft.thePlayer.posY;
            count16 = !Double.isNaN(coord4) && !Double.isNaN(posY) ? (coord4 > posY ? 1 : (coord4 == posY ? 0 : -1)) : -1;
            if (count16 <= 0) {
                pathNode2 = pathNode3;
                completed8 = true;
            }
        } catch (Exception e54) {
            return;
        }
        if (!completed8) {
            try {
                ArrayList itemList35 = this.itemList3;
                ArrayList itemList36 = this.itemList3;
                count16 = itemList36.size();
                count5 = this.tickDelay;
                count16 -= count5;
                count16 -= 3;
                int count17 = count16 - 1;
                PathNode pathNode4 = (PathNode)itemList35.get(count17);
                count = 3;
                pathNode2 = pathNode4;
            } catch (Exception e55) {
                return;
            }
        }


        int count18;
        BlockPlaceData blockPlaceData8;
        try {
            BlockPlaceData blockPlaceData9 = pathNode2.getBlockPlaceData();
            count18 = count;
            pathNode = pathNode2;
            blockPlaceData8 = blockPlaceData9;
        } catch (Exception e56) {
            return;
        }

        Vec3 vec22 = null;
        try {
            vec22 = blockPlaceData8.getVec3();
            blockPlaceData2 = pathNode.getBlockPlaceData();
            count5 = blockPlaceData2.indexOffset2;
            double coord5 = (double)count5;
            double posY2 = minecraft.thePlayer.posY;
            count5 = !Double.isNaN(coord5) && !Double.isNaN(posY2) ? (coord5 > posY2 ? 1 : (coord5 == posY2 ? 0 : -1)) : -1;
        } catch (Exception e57) {
            return;
        }

        boolean completed15 = false;
        boolean completed12 = false;
        try {
            if (count5 <= 0) {
                completed12 = true;
            }
        } catch (Exception e58) {
            return;
        }
        if (!completed12) {
            int count19 = 0;
            count19 = count18;
            BlockPos blockPos = null;
            try {
                EntityPlayerSP player7 = minecraft.thePlayer;
                count16 = MathHelper.ceiling_double_int(player7.posX);
                EntityPlayerSP player8 = minecraft.thePlayer;
                count5 = MathHelper.ceiling_double_int(player8.posY);
                double posZ = minecraft.thePlayer.posZ;
                int int15 = MathHelper.ceiling_double_int(posZ);
                blockPos = new BlockPos(count16, count5, int15);
                Minecraft mc17 = minecraft;
                blockState = mc17.theWorld.getBlockState(blockPos);
                Block block = blockState.getBlock();
                boolean condition9 = block instanceof BlockLadder;
                condition3 = condition9;
            } catch (Exception e60) {
                return;
            }
            boolean completed11 = false;
            try {
                if (condition3) {
                    completed11 = true;
                }
            } catch (Exception e61) {
                return;
            }
            if (!completed11) {
                try {
                    boolean enabled = this.settingSmooth.isEnabled();
                    count6 = (enabled ? 1 : 0);
                } catch (Exception e62) {
                    return;
                }
                Vec3 vec23 = null;
                try {
                    if ((count6) == 0) {
                        vec10 = vec22;
                        completed15 = true;
                    }
                    if (!completed15) {
                        vec23 = vec22;
                    }
                } catch (Exception e63) {
                    return;
                }
                if (!completed15) {
                    Vec3 vec24 = null;
                    count2 = count19;
                    vec24 = vec23;
                    try {
                        int size3 = this.itemList3.size();
                        int tickDelay3 = this.tickDelay;
                        count5 = size3 - tickDelay3 - count2 - 1;
                    } catch (Exception e65) {
                        return;
                    }
                    vec10 = vec24;
                    while (true) {
                        try {
                            if (count5 <= 0) {
                                { completed15 = true; break; }
                            }
                        } catch (Exception e67) {
                            return;
                        }
                        if (!completed15) {
                            try {
                                vec11 = ((PathNode)this.itemList3.get(count5)).getBlockPlaceData().getVec3();
                                double coord6 = vec11.xCoord + 0.5;
                                double yCoord2 = vec11.yCoord;
                                double coord7 = (double)minecraft.thePlayer.getHeight2();
                                yCoord2 += coord7;
                                coord7 = vec11.zCoord;
                                double coord8 = coord7 + 0.5;
                                Rotation rotation42 = this.getRotation4(coord6, yCoord2, coord8);
                                EntityPlayerSP player10 = minecraft.thePlayer;
                                EntityPlayerSP player11 = minecraft.thePlayer;
                                coord6 = vec11.xCoord + 0.5;
                                yCoord2 = vec21.yCoord;
                                coord7 = vec11.zCoord + 0.5;
                                coord6 = player11.getDouble4(coord6, yCoord2, coord7) + 0.2;
                                float yaw = rotation42.yaw;
                                float pitch = rotation42.pitch;
                                hitResult11 = player10.getMovingObjectPosition(coord6, 1.0F, yaw, pitch);
                                EntityPlayerSP player12 = minecraft.thePlayer;
                                coord6 = minecraft.thePlayer.posX;
                                yCoord2 = minecraft.thePlayer.posY + 0.1;
                                Minecraft mc19 = minecraft;
                                coord7 = mc19.thePlayer.posZ;
                                Vec3 vec33 = new Vec3(coord6, yCoord2, coord7);
                                EntityPlayerSP player13 = minecraft.thePlayer;
                                coord6 = vec11.xCoord;
                                yCoord2 = coord6 + 0.5;
                                coord6 = vec21.yCoord;
                                coord7 = vec11.zCoord + 0.5;
                                coord6 = player13.getDouble4(yCoord2, coord6, coord7) + 0.2;
                                yaw = rotation42.yaw;
                                pitch = rotation42.pitch;
                                hitResult12 = player12.getMovingObjectPosition(vec33, coord6, 1.0F, yaw, pitch);
                            } catch (Exception e68) {
                                return;
                            }
                            hitResult4 = hitResult11;
                            boolean completed9 = false;
                            try {
                                if (hitResult4 == null) {
                                    completed9 = true;
                                }
                            } catch (Exception e70) {
                                return;
                            }
                            if (!completed9) {
                                vec5 = vec10;
                                vec24 = vec11;
                                vec2 = vec5;
                                int count20 = 0;
                                count20 = count5;
                                vec12 = vec24;
                                hitResult5 = hitResult12;
                                try {
                                    MovingObjectType typeOfHit = hitResult11.typeOfHit;
                                    MovingObjectType movingObjectTypeKind = MovingObjectType.BLOCK;
                                    if (typeOfHit == movingObjectTypeKind) {
                                        vec10 = vec2;
                                        { completed15 = true; break; }
                                    }
                                } catch (Exception e74) {
                                    return;
                                }
                                if (!completed15) {
                                    vec10 = vec2;
                                    count5 = count20;
                                    vec11 = vec12;
                                    hitResult12 = hitResult5;
                                }
                            }
                            if (!completed15) {
                                MovingObjectPosition hitResult19 = null;
                                hitResult19 = hitResult12;
                                int count21 = 0;
                                vec6 = vec10;
                                count21 = count5;
                                vec22 = vec11;
                                boolean completed10 = false;
                                Vec3 vec25 = null;
                                try {
                                    if (hitResult19 == null) {
                                        b = ((byte)(count2));
                                        count16 = count21;
                                        vec24 = vec22;
                                        completed10 = true;
                                    }
                                    if (!completed10) {
                                        vec25 = vec6;
                                        vec13 = vec22;
                                    }
                                } catch (Exception e78) {
                                    return;
                                }
                                if (!completed10) {
                                    hitResult6 = hitResult12;
                                    Vec3 vec26 = null;
                                    vec26 = vec13;
                                    hitResult = hitResult6;
                                    try {
                                        MovingObjectType typeOfHit2 = hitResult.typeOfHit;
                                        MovingObjectType movingObjectTypeKind2 = MovingObjectType.BLOCK;
                                        if (typeOfHit2 == movingObjectTypeKind2) {
                                            vec10 = vec25;
                                            { completed15 = true; break; }
                                        }
                                        if (!completed15) {
                                            b = ((byte)(count2));
                                            count16 = count21;
                                            vec24 = vec26;
                                        }
                                    } catch (Exception e81) {
                                        return;
                                    }
                                }
                                if (!completed15) {
                                    try {
                                        int count22 = count16 + -1;
                                        count2 = b;
                                        vec10 = vec24;
                                        count5 = count22;
                                    } catch (Exception e82) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!completed15) {
                try {
                    AutoWalkPath autoWalkPath = BlockLadder.autoWalkPath;
                    Comparable comparable = blockState.getComparable(autoWalkPath);
                    Vec3i vec3i = ((EnumFacing)comparable).getEnumFacing5().getVec3i();
                    BlockPos blockPos2 = blockPos.getBlockPos2(vec3i);
                    vec10 = new Vec3(blockPos2);
                    completed15 = true;
                } catch (Exception e83) {
                    return;
                }
            }
        }
        if (!completed15) {
            Vec3 vec27 = null;
            try {
                condition4 = this.settingSmooth.isEnabled();
                vec27 = vec22;
            } catch (Exception e84) {
                return;
            }
            vec10 = vec27;
            Vec3 vec28 = null;
            try {
                if (!condition4) {
                    completed15 = true;
                }
                if (!completed15) {
                    vec28 = vec10;
                }
            } catch (Exception e86) {
                return;
            }
            if (!completed15) {
                count3 = count18;
                try {
                    int size4 = this.itemList3.size();
                    int tickDelay4 = this.tickDelay;
                    count16 = size4 - tickDelay4 - count3 - 1;
                } catch (Exception e88) {
                    return;
                }
                while (true) {
                    try {
                        if (count16 <= 0) {
                            vec10 = vec28;
                            break;
                        }
                    } catch (Exception e89) {
                        return;
                    }

                    MovingObjectPosition hitResult20;
                    try {
                        vec22 = ((PathNode)this.itemList3.get(count16)).getBlockPlaceData().getVec3();
                        double coord9 = vec22.xCoord + 0.5;
                        double yCoord3 = vec22.yCoord;
                        Minecraft mc20 = minecraft;
                        Minecraft mc21 = mc20;
                        double coord10 = (double)mc21.thePlayer.getHeight2();
                        yCoord3 += coord10;
                        coord10 = vec22.zCoord + 0.5;
                        Rotation rotation43 = this.getRotation4(coord9, yCoord3, coord10);
                        EntityPlayerSP player14 = minecraft.thePlayer;
                        EntityPlayerSP player15 = minecraft.thePlayer;
                        coord10 = vec22.xCoord;
                        coord10 += 0.5;
                        coord9 = vec21.yCoord;
                        double coord11 = vec22.zCoord + 0.5;
                        coord9 = player15.getDouble4(coord10, coord9, coord11);
                        coord9 += 0.2;
                        float yaw2 = rotation43.yaw;
                        float pitch2 = rotation43.pitch;
                        MovingObjectPosition movingObjectPosition = player14.getMovingObjectPosition(coord9, 1.0F, yaw2, pitch2);
                        EntityPlayerSP player16 = minecraft.thePlayer;
                        EntityPlayerSP player17 = minecraft.thePlayer;
                        coord9 = player17.posX;
                        Minecraft mc25 = minecraft;
                        yCoord3 = mc25.thePlayer.posY;
                        yCoord3 += 0.1;
                        Minecraft mc26 = minecraft;
                        Minecraft mc27 = mc26;
                        coord10 = mc27.thePlayer.posZ;
                        Vec3 vec34 = new Vec3(coord9, yCoord3, coord10);
                        EntityPlayerSP player18 = minecraft.thePlayer;
                        coord9 = vec22.xCoord + 0.5;
                        yCoord3 = vec21.yCoord;
                        coord10 = vec22.zCoord + 0.5;
                        coord9 = player18.getDouble4(coord9, yCoord3, coord10) + 0.2;
                        yaw2 = rotation43.yaw;
                        pitch2 = rotation43.pitch;
                        MovingObjectPosition movingObjectPosition2 = player16.getMovingObjectPosition(vec34, coord9, 1.0F, yaw2, pitch2);
                        hitResult20 = movingObjectPosition2;
                        vec14 = vec28;
                        vec15 = vec22;
                        hitResult13 = movingObjectPosition;
                    } catch (Exception e90) {
                        return;
                    }

                    int count23;
                    count23 = count16;
                    hitResult7 = hitResult13;
                    hitResult14 = hitResult20;
                    hitResult8 = hitResult13;

                    MovingObjectPosition hitResult21;
                    Vec3 vec29 = null;
                    vec29 = vec14;
                    count16 = count23;
                    vec7 = vec15;
                    hitResult21 = hitResult7;
                    hitResult9 = hitResult14;
                    hitResult2 = hitResult8;

                    int count24 = 0;
                    boolean completed13 = false;
                    try {
                        if (hitResult2 == null) {
                            count24 = count16;
                            hitResult10 = hitResult9;
                            completed13 = true;
                        }
                        if (!completed13) {
                            vec16 = vec29;
                            hitResult3 = hitResult9;
                        }
                    } catch (Exception e93) {
                        return;
                    }
                    if (!completed13) {
                        MovingObjectPosition hitResult22 = null;
                        vec8 = vec16;
                        count5 = count16;
                        vec3 = vec7;
                        hitResult15 = hitResult21;
                        hitResult22 = hitResult3;
                        MovingObjectPosition hitResult23 = null;
                        vec4 = vec8;
                        hitResult16 = hitResult22;
                        hitResult23 = hitResult15;
                        vec29 = vec4;
                        count16 = count5;
                        hitResult10 = hitResult16;
                        hitResult17 = hitResult23;
                        try {
                            MovingObjectType typeOfHit3 = hitResult17.typeOfHit;
                            MovingObjectType movingObjectTypeKind3 = MovingObjectType.BLOCK;
                            if (typeOfHit3 == movingObjectTypeKind3) {
                                vec10 = vec29;
                                break;
                            }

                            vec = vec3;
                        } catch (Exception e97) {
                            return;
                        }
                        count24 = count16;
                        vec7 = vec;
                    }


                    int count25;
                    Vec3 vec30;
                    MovingObjectPosition hitResult24;
                    vec30 = vec29;
                    count25 = count24;
                    vec28 = vec7;
                    hitResult24 = hitResult10;

                    count16 = count25;
                    vec17 = vec28;
                    hitResult18 = hitResult24;

                    boolean completed14 = false;
                    try {
                        if (hitResult10 == null) {
                            vec28 = vec17;
                            completed14 = true;
                        }
                    } catch (Exception e101) {
                        return;
                    }
                    if (!completed14) {
                        int count26 = 0;
                        Vec3 vec31 = null;
                        vec22 = vec30;
                        count26 = count16;
                        vec31 = vec17;
                        Vec3 vec35 = null;
                        vec10 = vec22;
                        count16 = count26;
                        vec35 = vec31;
                        try {
                            MovingObjectType typeOfHit4 = hitResult18.typeOfHit;
                            MovingObjectType movingObjectTypeKind4 = MovingObjectType.BLOCK;
                            if (typeOfHit4 == movingObjectTypeKind4) {
                                break;
                            }

                            vec28 = vec35;
                        } catch (Exception e104) {
                            return;
                        }
                    }


                    try {
                        count16 += -1;
                    } catch (Exception e105) {
                        return;
                    }
                }
            }
        }


        boolean condition10;
        try {
            ModeSetting settingMovementmode2 = this.settingMovementmode;
            condition10 = settingMovementmode2.getSelected().equalsIgnoreCase("rotation");
        } catch (Exception e106) {
            return;
        }

        boolean completed22 = false;
        try {
            if (condition10) {
                completed22 = true;
            }
        } catch (Exception e107) {
            return;
        }
        if (!completed22) {
            Vec3 vec36 = null;
            vec36 = vec10;
            int count27 = 0;
            try {
                double value3 = this.settingRotationUpdateTicks.getValue();
                count27 = !Double.isNaN(value3) && !Double.isNaN(0.0) ? (value3 > 0.0 ? 1 : (value3 == 0.0 ? 0 : -1)) : -1;
            } catch (Exception e109) {
                return;
            }
            boolean completed16 = false;
            try {
                if (count27 <= 0) {
                    completed16 = true;
                }
                if (!completed16) {
                    vec18 = vec36;
                }
            } catch (Exception e110) {
                return;
            }
            if (!completed16) {
                double coord12 = 0.0;
                double coord13 = 0.0;
                try {
                    Minecraft mc30 = minecraft;
                    coord12 = (double)mc30.thePlayer.ticksExisted;
                    NumberSetting settingRotationUpdateTicks3 = this.settingRotationUpdateTicks;
                    coord13 = settingRotationUpdateTicks3.getValue();
                    coord12 %= coord13;
                    coord13 = 0.0;
                } catch (Exception e111) {
                    return;
                }
                try {
                    if ((!Double.isNaN(coord12) && !Double.isNaN(coord13) ? (coord12 > coord13 ? 1 : (coord12 == coord13 ? 0 : -1)) : -1) != 0) {
                        vec36 = vec18;
                        completed16 = true;
                    }
                } catch (Exception e112) {
                    return;
                }
                if (!completed16) {
                    Vec3 vec37 = null;
                    try {
                        coord12 = vec18.xCoord + 0.5;
                        coord13 = minecraft.thePlayer.posX;
                        vec37 = vec18;
                    } catch (Exception e113) {
                        return;
                    }
                    try {
                        double coord14 = coord12 - coord13;
                        coord12 = vec37.zCoord + 0.5;
                        Minecraft mc31 = minecraft;
                        Minecraft mc32 = mc31;
                        coord13 = mc32.thePlayer.posZ;
                        float factor = (float)(MathHelper.getDouble6(coord12 - coord13, coord14) * 180.0 / Math.PI - 90.0);
                        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                        coord13 = threadLocalRandom.nextDouble(-1.0, 1.0);
                        float factor2 = (float)coord13;
                        this.rotation = this.getRotation(factor, factor2);
                        this.timestampMillis = System.currentTimeMillis();
                        vec36 = vec37;
                    } catch (Exception e114) {
                        return;
                    }
                }
            }
            Vec3 vec38 = null;
            vec38 = vec36;
            boolean completed17 = false;
            try {
                if (this.rotation == null) {
                    completed17 = true;
                }
            } catch (Exception e116) {
                return;
            }
            if (!completed17) {
                int count28 = 0;
                try {
                    long time9 = System.currentTimeMillis();
                    long timestampMillis22 = this.timestampMillis2;
                    long time10 = time9 - timestampMillis22;
                    count28 = time10 > 100L ? 1 : (time10 == 100L ? 0 : -1);
                } catch (Exception e117) {
                    return;
                }
                try {
                    if (count28 >= 0) {
                        completed17 = true;
                    }
                } catch (Exception e118) {
                    return;
                }
                if (!completed17) {
                    Vec3 vec39 = null;
                    vec39 = vec38;
                    double coord15 = 0.0;
                    double coord16 = 0.0;
                    try {
                        long time11 = System.currentTimeMillis();
                        long timestampMillis5 = this.timestampMillis;
                        coord15 = (double)(time11 - timestampMillis5);
                        coord16 = this.settingRotationUpdateTicks.getValue() * 50.0;
                        vec19 = vec39;
                    } catch (Exception e120) {
                        return;
                    }
                    int count29 = 0;
                    try {
                        count29 = !Double.isNaN(coord15) && !Double.isNaN(coord16) ? (coord15 > coord16 ? 1 : (coord15 == coord16 ? 0 : -1)) : 1;
                    } catch (Exception e121) {
                        return;
                    }
                    try {
                        if (count29 >= 0) {
                            vec38 = vec19;
                            completed17 = true;
                        }
                        if (!completed17) {
                            vec9 = vec19;
                        }
                    } catch (Exception e122) {
                        return;
                    }
                    if (!completed17) {
                        try {
                            RotationManager rotationManager4 = ClientGlobals.rotationManager;
                            float[] floatArray2 = this.rotation.getFloatArray();
                            RotationManager rotationManager5 = rotationManager4;
                            float[] factorArray2 = floatArray2;
                            rotationManager5.applyFloatArray(factorArray2, -10000, this);
                            this.timestampMillis4 = System.currentTimeMillis();
                            this.rotation2 = this.rotation;
                            vec38 = vec9;
                        } catch (Exception e123) {
                            return;
                        }
                    }
                }
            }
            Vec3 vec40 = null;
            double coord17 = 0.0;
            double coord18 = 0.0;
            try {
                coord17 = vec38.xCoord;
                coord18 = coord17 + 0.5;
                EntityPlayerSP player19 = minecraft.thePlayer;
                coord17 = player19.posX;
                vec40 = vec38;
            } catch (Exception e124) {
                return;
            }
            int count30 = 0;
            float factor3 = 0.0F;
            try {
                coord18 -= coord17;
                coord17 = vec40.zCoord + 0.5;
                double posZ2 = minecraft.thePlayer.posZ;
                coord17 = MathHelper.getDouble6(coord17 - posZ2, coord18) * 180.0 / Math.PI;
                coord18 = coord17 - 90.0;
                factor3 = (float)coord18;
                Rotation rotation24 = ClientGlobals.rotationManager.getRotation2();
                this.intValueArray = getIntArray(rotation24.yaw, factor3);
                this.timestampMillis2 = System.currentTimeMillis();
                coord17 = vec40.yCoord;
                EntityPlayerSP player20 = minecraft.thePlayer;
                coord18 = player20.posY;
                count30 = !Double.isNaN(coord17) && !Double.isNaN(coord18) ? (coord17 > coord18 ? 1 : (coord17 == coord18 ? 0 : -1)) : -1;
            } catch (Exception e125) {
                return;
            }
            boolean completed19 = false;
            boolean completed18 = false;
            try {
                if (count30 <= 0) {
                    completed18 = true;
                }
            } catch (Exception e126) {
                return;
            }
            if (!completed18) {
                try {
                    count6 = (true ? 1 : 0);
                    completed19 = true;
                } catch (Exception e127) {
                    return;
                }
            }
            if (!completed19) {
                try {
                    count6 = (false ? 1 : 0);
                } catch (Exception e128) {
                    return;
                }
            }
            int count31 = 0;
            try {
                this.enabled2 = ((count6 & (true ? 1 : 0)) != 0);
                float factor4 = MathHelper.sqrt_float(ClientGlobals.rotationManager.getRotation2().yaw) + 180.0F;
                factor3 = MathHelper.sqrt_float(factor3) + 180.0F;
                factor3 = getFloat2(factor4, factor3);
                count31 = !Float.isNaN(factor3) && !Float.isNaN(45.0F) ? (factor3 > 45.0F ? 1 : (factor3 == 45.0F ? 0 : -1)) : 1;
            } catch (Exception e129) {
                return;
            }
            boolean completed21 = false;
            boolean completed20 = false;
            try {
                if (count31 > 0) {
                    completed20 = true;
                }
            } catch (Exception e130) {
                return;
            }
            if (!completed20) {
                try {
                    count6 = (true ? 1 : 0);
                    completed21 = true;
                } catch (Exception e131) {
                    return;
                }
            }
            if (!completed21) {
                try {
                    count6 = (false ? 1 : 0);
                } catch (Exception e132) {
                    return;
                }
            }
            try {
                this.activeState1 = ((count6 & (true ? 1 : 0)) != 0);
                return;
            } catch (Exception e133) {
                return;
            }
        }


        Vec3 vec41;
        vec41 = vec10;

        try {
            double coord19 = vec41.xCoord + 0.5;
            double posX = minecraft.thePlayer.posX;
            coord19 -= posX;
            posX = vec41.zCoord + 0.5;
            double posZ3 = minecraft.thePlayer.posZ;
            float factor5 = (float)(MathHelper.getDouble6(posX - posZ3, coord19) * 180.0 / Math.PI - 90.0);
            coord19 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            float factor6 = (float)coord19;
            Rotation rotation7 = this.getRotation(factor5, factor6);
            RotationManager rotationManager6 = ClientGlobals.rotationManager;
            float[] floatArray3 = rotation7.getFloatArray();
            RotationManager rotationManager7 = rotationManager6;
            float[] factorArray3 = floatArray3;
            rotationManager7.applyFloatArray(factorArray3, -10000, this);
            this.timestampMillis4 = System.currentTimeMillis();
            this.rotation2 = rotation7;
        } catch (Exception e135) {
            return;
        }
    }

    public static float getFloat2(float factor, float factor2) {
        float factor3 = Math.abs(factor - factor2);
        return Math.min(factor3, 360.0F - factor3);
    }

    public static double[] getDoubleArray(float factor, float factor2, float factor3) {
        double[] coordArray = new double[Float.compare(factor2 * factor2 + factor3 * factor3, 1.0E-4F)];
        coordArray[0] = 0.0;
        coordArray[1] = 0.0;
        return coordArray;
    }

    private static double getCoordinate(Entity entity) {
        return (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
    }

    private boolean canExecute5() {
        return minecraft.thePlayer.canExecute6();
    }

    private boolean canExecute6() {
        return this.timer2.hasReachedDouble(this.settingDelayAfterRespawn.getValue());
    }

    public Entity getEntity() {
        Entity entity = null;
        double coord = 0.0;

        for (Entity entity2 : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (this.checkEntity(entity2)) {
                double coord2 = (double)minecraft.thePlayer.getScaleFactor3(entity2);
                if (coord2 < coord || entity == null) {
                    entity = entity2;
                    coord = coord2;
                }
            }
        }

        return entity;
    }

    public static int[] getIntArray(float factor, float factor2) {
        double[] doubleArray = getDoubleArray(factor2, 0.0F, 1.0F);
        int[] countArray = new int[]{0, 0};
        double coord = -1.0;

        for (int index = -1; index <= 1; index++) {
            for (int index2 = -1; index2 <= 1; index2++) {
                double[] doubleArray2 = getDoubleArray(factor, (float)index, (float)index2);
                double coord2 = (doubleArray2[0] - doubleArray[0]) * (doubleArray2[0] - doubleArray[0]) + (doubleArray2[1] - doubleArray[1]) * (doubleArray2[1] - doubleArray[1]);
                if (coord > coord2 || coord == -1.0) {
                    int[] countArray2 = new int[]{index, index2};
                    countArray = countArray2;
                    coord = coord2;
                }
            }
        }

        return countArray;
    }

    public void applyBlockPlaceData(BlockPlaceData blockPlaceData, BlockPlaceData blockPlaceData2) {
        boolean toggled = Client.getClient().getModuleManager().jesus.isToggled();
        PathRenderer renderListenerAdapter = new PathRenderer(blockPlaceData, blockPlaceData2, false, toggled, false);
        renderListenerAdapter.state1 = true;
        this.itemList3 = renderListenerAdapter.getArrayList2(true, (long)this.settingMaxSearchTime.getValue());
        this.entrySet.clear();
        Iterator iterator = this.itemList3.iterator();
        if (iterator.hasNext()) {
            Object item = iterator.next();

            while (true) {
                PathNode pathNode = (PathNode)item;
                this.entrySet.add(pathNode.getBlockPlaceData().getString());
                item = this;
            }
        }

        while (true) {
            PathNode pathNode2 = (PathNode)((Object)(this));
            this.entrySet.add(pathNode2.getBlockPlaceData().getString());
        }
    }

    private boolean checkEntity(Entity entity) {
        return entity instanceof EntityPlayerSP;
    }

    private boolean isRotationUpdateTicksVisible() {
        return this.settingMovementmode.isVisible();
    }

    private boolean canExecute7() {
        return minecraft.thePlayer.getPotionEffect(Potion.potion3) != null ? this.settingStopWhileSlowness.isEnabled() : false;
    }
}
