package augustus.modules.movement;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventBlockCollision;
import client.event.EventCameraTransform;
import client.event.EventCameraTransformCallback;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSendPacket;
import client.event.EventTickState;
import client.event.EventTickStateCallback;
import client.event.MotionListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.BugUpMoveFlyingType;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.AlertNotificationSound;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.IBlockState;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.SlotManager;
import client.util.TimeHelper;
import client.via.ViaLoadingBase;
import de.florianmichael.dietrichevents2.StateTypes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCache;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;

public class BugUp
extends Module
implements MoveEntityListener,
UpdateStateListener,
PacketListener,
TickListener,
BlockCollisionListener,
MoveListener,
NetworkPacketListener,
ClickListener,
MotionListener {
    public ModeSetting settingMode;
    public NumberSetting settingMaxDistance;
    public BooleanSetting settingBugUpOnlyIfFallingIntoVoid;
    public NumberSetting settingFallDistance;
    public ActionSetting settingBackFlagDist;
    public ActionSetting settingBlinkPackets;
    public TimeHelper timer;
    public double[] coordOffsetArray;
    public boolean activeState1;
    public double reachDistance;
    public int keyCode2;
    public boolean enabled2;
    public boolean activeState3;
    public int tickDelay;
    public boolean expanded2;
    public boolean activeState;
    public boolean visible2;
    public int tickDelay4;
    public TimeHelper timer2;
    public AnimationTimer animationTimer;
    public int tickDelay2;
    public ArrayList itemList3;

    public BugUp() {
        super("BugUp", "Zurücksetzten", Color.DARK_GRAY, Category.MOVEMENT);
        String[] textArray = new String[12];
        textArray[0] = "Teleport";
        textArray[1] = "SetPosition";
        textArray[2] = "OnGround";
        textArray[3] = "OnGroundSpoof";
        textArray[4] = "Speed";
        textArray[5] = "Jump";
        textArray[6] = "Collision";
        textArray[7] = "AirStuck";
        textArray[8] = "BlinkOutgoing";
        textArray[9] = "BlinkBoth";
        textArray[10] = "AutoPlatform";
        textArray[11] = "NCP";
        this.settingMode = new ModeSetting(this, "Mode", "OnGround", textArray);
        NumberSetting numberSetting = new NumberSetting(this, "MaxDistance", 15.0, 3.0, 30.0, 0);
        String text = "MaxFallDistanceWithoutBugUp";
        this.settingMaxDistance = numberSetting.getNumberSetting(text).getNumberSetting(() -> this.isMaxDistanceVisible2());
        this.settingBugUpOnlyIfFallingIntoVoid = (BooleanSetting)new BooleanSetting(this, "BugUpOnlyIfFallingIntoVoid", false).getSetting(() -> this.isMaxDistanceVisible());
        this.settingFallDistance = new NumberSetting(this, "FallDistance", 4.0, 3.0, 15.0, 0).getNumberSetting(() -> this.isFallDistanceVisible());
        this.settingBackFlagDist = new ActionSetting(this, "BackFlagDist", 2.0, 3.0, 0.0, 10.0, 0).getActionSetting(() -> this.isBackFlagDistVisible());
        this.settingBlinkPackets = new ActionSetting(this, "BlinkPackets", 16.0, 16.0, 1.0, 20.0, 0).getActionSetting(() -> this.isBlinkPacketsVisible());
        this.timer = new TimeHelper();
        double[] coordArray = new double[3];
        this.coordOffsetArray = coordArray;
        this.activeState1 = false;
        this.reachDistance = 0.0;
        this.keyCode2 = 0;
        this.enabled2 = false;
        this.activeState3 = false;
        this.expanded2 = false;
        this.activeState = false;
        this.visible2 = false;
        this.tickDelay4 = -1;
        this.timer2 = new TimeHelper();
        this.animationTimer = new AnimationTimer();
        this.tickDelay2 = 1;
        this.itemList3 = new ArrayList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(20);
        this.registerEvent(43);
        this.registerEvent(26);
        this.registerEvent(40);
        this.registerEvent(3);
        this.registerEvent(21);
        this.registerEvent(25);
        this.registerEvent(7);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
        this.activeState1 = false;
        this.enabled2 = false;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (this.settingMode.getSelected().equals("BlinkBoth")) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.settingMode.getSelected().equalsIgnoreCase("AutoPlatform")) {
            event.updateInt2(1);
            this.timer2.reset();
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (minecraft.thePlayer != null && minecraft.theWorld != null) {
            boolean completed7 = false;
            boolean completed6 = false;
            boolean enabled = false;
            boolean completed5 = false;
            BugUp bugUp = null;
            boolean completed4 = false;
            boolean completed3 = false;
            boolean enabled3 = false;
            boolean completed2 = false;
            if (this.canExecute6()) {
                String selected = this.settingMode.getSelected();
                boolean completed = false;
switch (selected) {
            case "AirStuck":
                {
                                                {
                                                    if (packet2 instanceof C03PacketPlayer) {
                                                        packet.cancel();
                                                        { completed2 = true; break; }
                                                    }
                                                    if (!completed2) {
                                                        { completed = true; break; }
                                                    }
                                                }
                                            }
            case "OnGroundSpoof":
                {
                                                enabled = packet2 instanceof C03PacketPlayer;
                                                { completed5 = true; break; }
                                            }
            default:
                {
                                                { completed = true; break; }
                                            }
        }
            }
            if (!completed6) {
                if (!completed5) {
                    if (!completed2) {
                        if (!this.settingMode.getSelected().equals("NCP")
                        || ClientGlobals.moduleManager.speed.isToggled()
                        || !(packet2 instanceof C03PacketPlayer)) {
                            if (!(packet2 instanceof C03PacketPlayer) || !this.enabled2) {
                                boolean enabled4 = packet2 instanceof S08PacketPlayerPosLook;
                                if (enabled4) {
                                    this.expanded2 = true;
                                }

                                return;
                            }
                            bugUp = this;
                            completed4 = true;
                        }
                        if (!completed4) {
                            enabled3 = this.checkCondition();
                        }
                    } else {
                        boolean enabled5 = this.settingMode.getSelected().equals("NCP");
                        if (!enabled5 || ClientGlobals.moduleManager.speed.isToggled() || !(packet2 instanceof C03PacketPlayer)) {
                            completed3 = true;
                        }
                        if (!completed3) {
                            enabled3 = this.checkCondition();
                        }
                    }
                }
            }
            if (!completed6) {
                if (!completed5) {
                    if (!completed4) {
                        if (!completed3) {
                            if (!enabled3) {
                                double fallDistance = (double)minecraft.thePlayer.fallDistance;
                                NumberSetting settingFallDistance2 = this.settingFallDistance;
                                if (fallDistance < settingFallDistance2.getValue()) {
                                    packet.cancel();
                                    this.itemList3.add(packet.getPacket());
                                } else if (!this.itemList3.isEmpty()) {
                                    this.itemList3.forEach(object -> handlePacket2((Packet)object));
                                    this.itemList3.clear();
                                }
                            } else if (!this.itemList3.isEmpty()) {
                                this.itemList3.forEach(object2 -> handlePacket((Packet)object2));
                                this.itemList3.clear();
                            }
                        }
                        boolean enabled6 = packet2 instanceof C03PacketPlayer;
                        if (!enabled6 || !this.enabled2) {
                            completed7 = true;
                        }
                        if (!completed7) {
                            bugUp = this;
                        }
                    }
                    if (!completed7) {
                        if (bugUp.settingMode.getSelected().equals("BlinkOutgoing")
                        || this.settingMode.getSelected().equals("BlinkBoth")) {
                            this.keyCode2++;
                        }
                        completed7 = true;
                    }
                }
                if (!completed7) {
                    if (enabled) {
                        ((C03PacketPlayer)packet2).setOnGround(true);
                    }
                }
            }
            if (!completed7) {
                if (this.settingMode.getSelected().equals("NCP")
                && !ClientGlobals.moduleManager.speed.isToggled()
                && packet2 instanceof C03PacketPlayer) {
                    if (!this.checkCondition()) {
                        double fallDistance2 = (double)minecraft.thePlayer.fallDistance;
                        NumberSetting settingFallDistance3 = this.settingFallDistance;
                        if (fallDistance2 < settingFallDistance3.getValue()) {
                            packet.cancel();
                            this.itemList3.add(packet.getPacket());
                        } else if (!this.itemList3.isEmpty()) {
                            this.itemList3.forEach(object3 -> handlePacket2((Packet)object3));
                            this.itemList3.clear();
                        }
                    } else if (!this.itemList3.isEmpty()) {
                        this.itemList3.forEach(object4 -> handlePacket((Packet)object4));
                        this.itemList3.clear();
                    }
                }
                if (packet2 instanceof C03PacketPlayer
                && this.enabled2
                && (
                this.settingMode.getSelected().equals("BlinkOutgoing")
                || this.settingMode.getSelected().equals("BlinkBoth")
                )) {
                    this.keyCode2++;
                }
                if (packet2 instanceof S08PacketPlayerPosLook) {
                    this.expanded2 = true;
                }
                return;
            }


            boolean enabled7 = packet2 instanceof S08PacketPlayerPosLook;
            if (enabled7) {
                this.expanded2 = true;
            }
        }
    }

    @Override
    public void onBlockCollision(EventBlockCollision event) {
        if ((this.activeState1 ? 1 : 0) < 0) {
            BlockPos blockPos = event.getBlockPos();
            event.setBoundingBox(
            new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)(blockPos.getX() + 1),
            (double)(blockPos.getY() + 1),
            (double)(blockPos.getZ() + 1)
            )
            );
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.settingMode.getSelected().equals("BlinkOutgoing")) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMode.getSelected().equals("BlinkOutgoing")) {
            float value = 1.0F;
            event.setScaleFactor7(value);
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
            this.activeState1 = false;
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (!this.canExecute6()) {
            this.reachDistance = minecraft.thePlayer.posY + event.getCoordOffset1();
        } else if (this.settingMode.getSelected().equals("AirStuck")) {
            event.setCoordOffset2(0.0);
            event.setCoordOffset1(0.0);
            event.setCoordOffset(0.0);
        }
    }

    @Override
    public void onPostMotion() {
        this.animationTimer.processAction();
        this.tickDelay4 = -1;
        byte state = ((byte)(this.settingMode.getSelected().equalsIgnoreCase("AutoPlatform") ? 1 : 0));
        minecraft.thePlayer.container.getSlot(state + 36).getStack();
        int tickDelay42 = this.tickDelay4;
        SlotManager.applyModule3(this, -3, tickDelay42);
    }

    @Override
    public void onPreUpdateState() {
        boolean completed = false;
        boolean enabled = false;
        if (!this.settingMode.getSelected().equals("BlinkOutgoing")) {
            if (!this.settingMode.getSelected().equals("BlinkBoth")) {
                completed = true;
            }
            if (!completed) {
                Minecraft mc = minecraft;
                if (mc.thePlayer.ticksExisted <= 50) {
                    completed = true;
                }
                if (!completed) {
                    enabled = minecraft.playerController.processAction();
                }
            }
        } else {
            Minecraft mc2 = minecraft;
            if (mc2.thePlayer.ticksExisted <= 50) {
                Minecraft mc3 = minecraft;
                this.activeState3 = mc3.thePlayer.onGround;
                return;
            }

            enabled = minecraft.playerController.processAction();
        }
        if (!completed) {
            if (enabled) {
                if (this.activeState3 && (this.timer.hasReached(2000L) || this.expanded2) && !BlockCache.canExecute()) {
                    this.enabled2 = this.canExecute5();
                    this.keyCode2 = 0;
                    if (this.enabled2) {
                        this.expanded2 = false;
                        SettingProvider settingProvider = SettingContainer.settingProvider;
                        double double3 = this.settingBlinkPackets.getDouble3();
                        double double6 = this.settingBlinkPackets.getDouble6();
                        this.tickDelay = Math.max(settingProvider.getInt(double3, double6), 1);
                    }
                }

                if (this.enabled2 && this.keyCode2 > this.tickDelay) {
                    this.enabled2 = false;
                    this.timer.reset();
                    this.keyCode2 = 0;
                }

                if (BlockCache.canExecute()) {
                    this.keyCode2 = 0;
                    this.enabled2 = false;
                }
            }
            Minecraft mc4 = minecraft;
            this.activeState3 = mc4.thePlayer.onGround;
            return;
        }


        Minecraft mc5 = minecraft;
        this.activeState3 = mc5.thePlayer.onGround;
    }

    @Override
    public void onPostUpdateState() {
        if (this.canExecute6()) {
            minecraft.thePlayer.motionY = 0.0;
            minecraft.thePlayer.motionX = 0.0;
            minecraft.thePlayer.motionZ = 0.0;
        }
    }

    @Override
    public void onRunTick() {
        String selected = this.settingMode.getSelected();
        this.applyString4(selected);
        if (minecraft.thePlayer.onGround) {
            double[] coordArray = new double[3];
            Minecraft mc = minecraft;
            coordArray[0] = mc.thePlayer.posX;
            Minecraft mc2 = minecraft;
            coordArray[1] = mc2.thePlayer.posY;
            Minecraft mc3 = minecraft;
            coordArray[2] = mc3.thePlayer.posZ;
            this.coordOffsetArray = coordArray;
        }

        if (this.canExecute6()) {
            this.activeState1 = true;
            String selected2 = this.settingMode.getSelected();
switch (selected2) {
            case "Teleport":
                if (this.timer.hasReached(200L)) {
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C04PacketPlayerPosition(this.coordOffsetArray[0], this.coordOffsetArray[1], this.coordOffsetArray[2], true));
                        this.timer.reset();
                    }
                break;
            case "SetPosition":
                minecraft.thePlayer.processDouble2(this.coordOffsetArray[0], this.coordOffsetArray[1], this.coordOffsetArray[2]);
                break;
            case "OnGround":
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C03PacketPlayer(true));
                break;
            case "AirStuck":
                minecraft.gameSettings.keyBindForward.pressed = false;
                minecraft.gameSettings.keyBindBack.pressed = false;
                minecraft.gameSettings.keyBindLeft.pressed = false;
                minecraft.gameSettings.keyBindRight.pressed = false;
                break;
            case "Speed":
                MoveUtil.processFloat(1.0F);
                break;
            case "Jump":
                minecraft.thePlayer.closeScreen();
        }
        } else {
            this.activeState1 = false;
        }
    }

    private static void handlePacket(Packet packet) {
        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(packet);
    }

    private boolean isMaxDistanceVisible() {
        return this.settingMode.getSelected().equals("NCP");
    }

    private boolean isFallDistanceVisible() {
        return this.settingMode.getSelected().equals("NCP");
    }

    private static void handlePacket2(Packet packet) {
        C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet;
        Minecraft mc = minecraft;
        c03PacketPlayer.setCoordinate(mc.thePlayer.coordOffset9);
        minecraft.thePlayer.netHandlerPlayClient.handlePacket2(c03PacketPlayer);
    }

    private boolean isBlinkPacketsVisible() {
        return this.settingMode.getSelected().equals("BlinkOutgoing");
    }

    private boolean checkCondition() {
        int index = Double.compare(minecraft.thePlayer.posY, 0.0);
        if (index < (int)minecraft.thePlayer.posY + 2) {
            AxisAlignedBB axisAlignedBB6 = minecraft.thePlayer.getBoundingBox2().expand(0.0, (double)(-index), 0.0);
            return minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, axisAlignedBB6).isEmpty();
        } else {
            return false;
        }
    }

    private boolean isMaxDistanceVisible2() {
        return this.settingMode.getSelected().equals("NCP");
    }

    private boolean canExecute5() { boolean enabled = false; boolean enabled3 = false; List list = null; boolean enabled4 = false; int index = 0; boolean enabled5 = false; boolean enabled6 = false;
        Minecraft mc = minecraft;
        EntityPlayerSP player = mc.thePlayer;
        double posX = player.posX;
        double posY = player.posY;
        double posZ = player.posZ;
        double motionX = player.motionX;
        double motionY = player.motionY;
        double motionZ = player.motionZ;
        boolean state31 = player.state31;
        boolean enabled7 = state31;
        int indexOffset18 = player.getIndexOffset18();
        state31 = player.onGround;
        int index2 = (state31 ? 1 : 0);
        MovementInput secondaryFloatEntry = player.secondaryFloatEntry;
        secondaryFloatEntry = player.secondaryFloatEntry;
        AxisAlignedBB boundingBox2 = player.getBoundingBox2();
        double minX = boundingBox2.minX;
        double minY = boundingBox2.minY;
        double minZ = boundingBox2.minZ;
        double maxX = boundingBox2.maxX;
        double maxY = boundingBox2.maxY;
        double maxZ = boundingBox2.maxZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        state31 = player.isRiding();
        boolean enabled8 = state31;
        maxZ = posX;
        maxX = posZ;
        minY = motionX;
        posZ = motionY;
        int index3 = 0;

        outerLoop2:
        while (index3 <= 200) {
            MovementInput secondaryFloatEntry2 = player.secondaryFloatEntry;
            float scaleFactor1 = secondaryFloatEntry2.moveStrafe;
            secondaryFloatEntry2 = player.secondaryFloatEntry;
            float scaleFactor = secondaryFloatEntry2.moveForward;
            EventCameraTransform eventCameraTransform = EventCameraTransformCallback.getEventCameraTransform(0.2F, 0.2F);
            state31 = player.isUsingItem();
            if (!state31) {
                posY = minY;
                maxY = motionZ;
                index2 = indexOffset18;
                index3 = (byte)index3;
            } else {
                state31 = player.isSneaking();
                posY = minY;
                index2 = indexOffset18;
                if (state31) {
                    maxY = motionZ;
                    index3 = (byte)index3;
                } else {
                    float scaleFactor2 = eventCameraTransform.getScaleFactor2();
                    float value = scaleFactor1 * scaleFactor2;
                    scaleFactor2 = eventCameraTransform.getScaleFactor();
                    scaleFactor2 = scaleFactor * scaleFactor2;
                    maxY = motionZ;
                    index3 = (byte)index3;
                    scaleFactor1 = value;
                    scaleFactor = scaleFactor2;
                }
            }

            motionX = posY;
            indexOffset18 = index2;
            int index4;
            if (index2 <= 0) {
                index4 = index2;
            } else {
                index4 = index2;
                indexOffset18 = index2 + -1;
            }

            maxY = ViaLoadingBase.processAction2();
            minZ = Math.abs(posY);
            int index5 = !Double.isNaN(minZ) && !Double.isNaN(maxY) ? (minZ > maxY ? 1 : (minZ == maxY ? 0 : -1)) : 1;
            if (index5 >= 0) {
            } else {
                motionX = 0.0;
            }

            maxX = Math.abs(posZ);
            index3 = !Double.isNaN(maxX) && !Double.isNaN(maxY) ? (maxX > maxY ? 1 : (maxX == maxY ? 0 : -1)) : 1;
            maxY = posZ;
            int index6 = 0;
            if (index3 >= 0) {
                minY = maxY;
                index6 = index3;
                minX = maxY;
            } else {
                maxY = 0.0;
                minY = maxY;
                index6 = index3;
                minX = maxY;
            }

            motionY = Math.abs(minY);
            index3 = !Double.isNaN(motionY) && !Double.isNaN(minX) ? (motionY > minX ? 1 : (motionY == minX ? 0 : -1)) : 1;
            int index7 = 0;
            if (index3 >= 0) {
                index7 = index4;
            } else {
                posY = 0.0;
                minY = posY;
                index7 = index4;
            }

            int index8 = index7;
            if (!enabled7) {
                maxX = maxY;
                minZ = minY;
                index8 = index7;
                minY = minX;
                indexOffset18 = 0;
                maxZ = posY;
                motionY = maxX;
                index7 = (byte)index6;
            } else {
                motionY = maxX;
                maxZ = posY;
                if (((index7) == 0)) {
                    maxX = maxY;
                    minZ = minY;
                    index7 = (byte)index6;
                    minY = minX;
                } else {
                    AxisAlignedBB boundingBox = axisAlignedBB;
                    if (indexOffset18 != 0) {
                        maxX = maxY;
                        minZ = minY;
                        index7 = (byte)index6;
                        minY = minX;
                    } else {
                        float float6 = player.getFloat6();
                        posZ = (double)float6;
                        Potion potion9 = Potion.potion9;
                        state31 = player.checkPotion(potion9);
                        if (!state31) {
                            float6 = scaleFactor;
                        } else {
                            potion9 = Potion.potion9;
                            PotionEffect potionEffect = player.getPotionEffect(potion9);
                            index8 = potionEffect.getIndexOffset2();
                            float6 = (float)(++index8);
                            float6 *= 0.1F;
                            maxX = (double)float6;
                            posZ += maxX;
                            float6 = scaleFactor;
                        }

                        boolean enabled9;
                        byte state;
                        if (!enabled8) {
                            motionY = posY;
                            posY = maxX;
                            maxX = motionX;
                            minZ = posZ;
                            enabled9 = ((index7) != 0);
                            axisAlignedBB = boundingBox;
                            state = (byte)index6;
                        } else {
                            motionY = posY;
                            posY = maxX;
                            enabled9 = ((index7) != 0);
                            axisAlignedBB = boundingBox;
                            float6 = player.rotationYaw;
                            float6 *= (float) (Math.PI / 180.0);
                            float float7 = MathHelper.abs(float6);
                            float7 *= 0.2F;
                            maxX = (double)float7;
                            maxY = motionX - maxX;
                            float6 = MathHelper.cos(float6);
                            float6 *= 0.2F;
                            maxX = (double)float6;
                            motionX = minY + maxX;
                            maxX = maxY;
                            minZ = posZ;
                            minY = motionX;
                            state = (byte)index6;
                        }

                        indexOffset18 = 10;
                        scaleFactor = float6;
                        maxZ = motionY;
                        motionY = posY;
                        motionX = maxX;
                        maxX = minZ;
                        minZ = minY;
                        index8 = (enabled9 ? 1 : 0);
                        index7 = state;
                        minY = minX;
                    }
                }
            }

            float value2 = scaleFactor1 * 0.98F;
            scaleFactor1 = scaleFactor * 0.98F;
            float value3 = 0.91F;
            int index9 = 0;
            AxisAlignedBB boundingBox3 = null;
            if (((index8) == 0)) {
                index9 = index8;
                boundingBox3 = axisAlignedBB;
                scaleFactor = scaleFactor1;
                index4 = indexOffset18;
                scaleFactor1 = value3;
            } else {
                index9 = index8;
                World world = player.worldObj;
                int int15 = MathHelper.ceiling_double_int(maxZ);
                index6 = MathHelper.ceiling_double_int(maxZ);
                int index10 = index6 - 1;
                index6 = MathHelper.ceiling_double_int(motionY);
                BlockPos blockPos = new BlockPos(int15, index10, index6);
                float scaleFactor3 = world.getBlockState(blockPos).getBlock().scaleFactor;
                value3 = scaleFactor3 * 0.91F;
                boundingBox3 = axisAlignedBB;
                scaleFactor = scaleFactor1;
                index4 = indexOffset18;
                scaleFactor1 = value3;
            }

            value3 = scaleFactor1 * scaleFactor1;
            value3 *= scaleFactor1;
            value3 = 0.16277136F / value3;
            int index11 = 0;
            AxisAlignedBB boundingBox4;
            if (((index9) == 0)) {
                scaleFactor1 = player.scaleFactor10;
                posY = motionY;
                scaleFactor = value2;
                indexOffset18 = index4;
                value3 = scaleFactor1;
                maxY = maxZ;
                posZ = maxZ;
                motionY = motionX;
                maxZ = maxX;
                motionZ = minZ;
                boundingBox4 = boundingBox3;
                index11 = index7;
                scaleFactor1 = scaleFactor;
                motionX = minY;
            } else {
                indexOffset18 = index4;
                float scaleFactor6 = player.getScaleFactor6();
                value3 = scaleFactor6 * value3;
                posY = motionY;
                motionY = motionX;
                index11 = index7;
                scaleFactor = value2;
                motionX = minY;
                posZ = maxZ;
                maxZ = maxX;
                scaleFactor1 = scaleFactor;
                maxY = maxZ;
                motionZ = minZ;
                boundingBox4 = boundingBox3;
            }

            float rotationYaw = player.rotationYaw;
            BugUpMoveFlyingType bugUpMoveFlyingTypeKind = BugUpMoveFlyingType.MOVE_FLYING;
            EventRotation eventRotation = RotationListener.getEventRotation(rotationYaw, bugUpMoveFlyingTypeKind);
            float value4 = scaleFactor * scaleFactor;
            rotationYaw = scaleFactor1 * scaleFactor1;
            rotationYaw = value4 + rotationYaw;
            index7 = !Float.isNaN(rotationYaw) && !Float.isNaN(1.0E-4F) ? (rotationYaw > 1.0E-4F ? 1 : (rotationYaw == 1.0E-4F ? 0 : -1)) : -1;
            motionY = maxZ;
            maxZ = motionZ;
            motionZ = motionX;
            if (index7 < 0) {
                motionX = motionY;
                scaleFactor = scaleFactor1;
            } else {
                value2 = MathHelper.sin(rotationYaw);
                rotationYaw = 1.0F;
                index7 = !Float.isNaN(value2) && !Float.isNaN(rotationYaw) ? (value2 > rotationYaw ? 1 : (value2 == rotationYaw ? 0 : -1)) : 1;
                if (index7 >= 0) {
                    maxX = motionZ;
                    index7 = indexOffset18;
                    rotationYaw = value3;
                    scaleFactor1 = value2;
                } else {
                    scaleFactor1 = 1.0F;
                    maxX = motionZ;
                    index7 = indexOffset18;
                    rotationYaw = value3;
                }

                value3 = rotationYaw / scaleFactor1;
                float value5 = scaleFactor * value3;
                value2 = scaleFactor1 * value3;
                scaleFactor = eventRotation.getScaleFactor();
                value3 = scaleFactor * (float) Math.PI;
                scaleFactor1 = 180.0F;
                scaleFactor1 = value3 / scaleFactor1;
                value3 = MathHelper.abs(scaleFactor1);
                scaleFactor = value2;
                indexOffset18 = index7;
                float scaleFactor4 = eventRotation.getScaleFactor();
                float value6 = scaleFactor4 * (float) Math.PI;
                float value7 = value6 / 180.0F;
                float float5 = MathHelper.cos(value7);
                value4 = value5 * float5;
                rotationYaw = value2 * value3;
                rotationYaw = value4 - rotationYaw;
                double offset = (double)rotationYaw;
                motionX = motionY + offset;
                float value8 = value2 * float5;
                value2 = value5 * value3;
                value2 = value8 + value2;
                double offset2 = (double)value2;
                minY = maxX + offset2;
                motionY = maxZ;
                maxZ = minY;
                motionZ = motionX;
            }

            scaleFactor1 = 0.91F;
            int index12 = 0;
            AxisAlignedBB boundingBox5 = null;
            WorldClient world2;
            if (((index9) == 0)) {
                double offset3 = 0.0;
                boolean completed = false;
                boundingBox5 = boundingBox4;
                value2 = scaleFactor;
                state31 = player.isSprinting();
                state31 = player.onGround;
                double offset4 = motionX;
                minZ = motionY;
                minX = maxZ;
                offset3 = motionZ;
                posX = motionX;
                minY = motionY;
                motionZ = maxY;
                motionY = posZ;
                boolean enabled10 = ((index9) != 0);
                double offset5 = 0.0;
                double offset6 = 0.0;
                byte state2 = (byte)0;
                double offset7 = 0.0;
                double offset8 = 0.0;
                if (!state31) {
                    state2 = (byte)index11;
                    index6 = indexOffset18;
                    offset8 = maxZ;
                    offset7 = motionX;
                    offset5 = motionY;
                    offset6 = maxZ;
                } else {
                    state2 = (byte)index11;
                    offset3 = motionZ;
                    posX = motionX;
                    minY = motionY;
                    if (state31) {
                        index9 = indexOffset18;
                        maxZ = posZ;
                        motionY = posY;
                        posY = motionY;
                        posZ = maxZ;
                        index12 = index9;
                        index11 = (byte)index11;
                        maxX = motionZ;
                        maxY = motionX;
                        minZ = motionY;
                        minY = maxZ;
                        offset3 = motionX;
                        posX = motionY;
                        minX = maxZ;
                        enabled = true;
                        completed = true;
                    }
                    if (!completed) {
                        motionY = posZ;
                        offset4 = motionX;
                        minZ = motionY;
                        minX = maxZ;
                        enabled10 = ((index9) != 0);
                        index6 = indexOffset18;
                        offset8 = maxZ;
                        offset7 = motionX;
                        offset5 = motionY;
                        offset6 = maxZ;
                    }
                }
                if (!completed) {
                    maxZ = motionY;
                    motionY = posY;
                    motionX = offset4;
                    posY = minZ;
                    posZ = minX;
                    index12 = (enabled10 ? 1 : 0);
                    index11 = state2;
                    maxX = offset3;
                    index9 = index6;
                    maxY = posX;
                    minZ = minY;
                    minY = offset8;
                    offset3 = offset7;
                    posX = offset5;
                    minX = offset6;
                    enabled = false;
                }


                double offset9 = 0.0;
                if (!enabled) {
                    index7 = index9;
                    value3 = scaleFactor1;
                    offset9 = offset3;
                    offset3 = minX;
                } else {
                    value2 = scaleFactor1;
                    double offset10 = posX;
                    double offset11 = minX;
                    double offset12 = 0.05;
                    double offset13 = maxX;
                    double offset14 = maxY;
                    minX = minZ;
                    posX = offset3;

                    while (true) {
                        index7 = !Double.isNaN(offset14) && !Double.isNaN(0.0) ? (offset14 > 0.0 ? 1 : (offset14 == 0.0 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            offset9 = offset12;
                            break;
                        }

                        Minecraft mc2 = minecraft;
                        WorldClient world3 = mc2.theWorld;
                        boundingBox3 = boundingBox5.expand(offset14, -1.0, 0.0);
                        List collidingBoundingBoxes = world3.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes.isEmpty();
                        if (!state31) {
                            offset9 = offset12;
                            break;
                        }

                        double offset15 = 0.0;
                        double offset16 = 0.0;
                        boolean completed2 = false;
                        index7 = !Double.isNaN(offset14) && !Double.isNaN(offset12) ? (offset14 > offset12 ? 1 : (offset14 == offset12 ? 0 : -1)) : 1;
                        maxX = offset13;
                        double offset17 = offset14;
                        double offset18 = motionZ;
                        double offset19 = maxZ;
                        double offset20 = motionY;
                        double offset21 = motionX;
                        double offset22 = posY;
                        maxY = posZ;
                        if (index7 < 0) {
                            offset9 = -offset12;
                            index7 = !Double.isNaN(offset14) && !Double.isNaN(offset9) ? (offset14 > offset9 ? 1 : (offset14 == offset9 ? 0 : -1)) : -1;
                            offset18 = motionZ;
                            offset19 = maxZ;
                            offset20 = motionY;
                            offset21 = motionX;
                            offset22 = posY;
                            maxY = posZ;
                            if (index7 >= 0) {
                                minZ = offset13;
                                offset3 = minY;
                                offset11 = offset10;
                                offset9 = offset11;
                                offset15 = offset12;
                                offset16 = minZ;
                                completed2 = true;
                            }
                            if (!completed2) {
                                maxX = offset13;
                                offset17 = offset14;
                            }
                        }
                        if (!completed2) {
                            motionY = offset20;
                            posY = offset22;
                            offset9 = 0.0;
                            motionZ = offset18;
                            maxZ = offset19;
                            motionX = offset21;
                            posZ = maxY;
                            index7 = !Double.isNaN(offset17) && !Double.isNaN(offset9) ? (offset17 > offset9 ? 1 : (offset17 == offset9 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                motionZ = offset17 + offset12;
                                offset11 = offset10;
                                offset16 = motionZ;
                                motionZ = offset18;
                                motionY = offset20;
                                motionX = offset21;
                                posY = offset22;
                                minZ = maxX;
                                offset3 = minY;
                                offset9 = offset11;
                                offset15 = offset12;
                            } else {
                                offset11 = offset10;
                                offset15 = offset17 - offset12;
                                offset3 = minY;
                                minZ = maxX;
                                offset9 = offset11;
                                offset15 = offset12;
                                offset16 = offset15;
                            }
                        }


                        offset13 = minZ;
                        minY = offset3;
                        posX = offset16;
                        offset10 = offset11;
                        offset11 = offset9;
                        offset14 = offset16;
                        offset12 = offset15;
                    }

                    while (true) {
                        maxX = 0.0;
                        index7 = !Double.isNaN(minY) && !Double.isNaN(maxX) ? (minY > maxX ? 1 : (minY == maxX ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            maxX = offset13;
                            value3 = value2;
                            maxY = offset14;
                            minZ = minX;
                            minX = posX;
                            posX = offset10;
                            offset3 = offset11;
                            break;
                        }

                        Minecraft mc3 = minecraft;
                        WorldClient world4 = mc3.theWorld;
                        boundingBox3 = boundingBox5.expand(0.0, -1.0, minY);
                        List collidingBoundingBoxes2 = world4.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes2.isEmpty();
                        maxY = offset14;
                        minZ = minX;
                        minX = posX;
                        posX = offset10;
                        offset3 = offset11;
                        if (!state31) {
                            maxX = offset13;
                            value3 = value2;
                            break;
                        }

                        double offset23 = 0.0;
                        boolean completed3 = false;
                        index7 = !Double.isNaN(minY) && !Double.isNaN(offset9) ? (minY > offset9 ? 1 : (minY == offset9 ? 0 : -1)) : 1;
                        maxY = posZ;
                        double offset24 = minY;
                        double offset25 = maxZ;
                        double offset26 = motionY;
                        double offset27 = motionX;
                        posZ = posY;
                        maxX = offset13;
                        minZ = offset14;
                        minY = minX;
                        if (index7 < 0) {
                            offset9 = -offset9;
                            index7 = !Double.isNaN(minY) && !Double.isNaN(offset9) ? (minY > offset9 ? 1 : (minY == offset9 ? 0 : -1)) : -1;
                            offset25 = maxZ;
                            offset26 = motionY;
                            offset27 = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = offset13;
                            minZ = offset14;
                            if (index7 >= 0) {
                                maxX = offset13;
                                minX = offset14;
                                offset3 = minX;
                                offset11 = offset10;
                                offset23 = maxX;
                                completed3 = true;
                            }
                            if (!completed3) {
                                minY = minX;
                                offset24 = minY;
                            }
                        }
                        if (!completed3) {
                            motionY = offset26;
                            posY = posZ;
                            offset9 = 0.0;
                            maxZ = offset25;
                            motionX = offset27;
                            posZ = maxY;
                            index7 = !Double.isNaN(offset24) && !Double.isNaN(offset9) ? (offset24 > offset9 ? 1 : (offset24 == offset9 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                double offset28 = offset24 + offset9;
                                motionY = offset26;
                                posY = posZ;
                                minX = minZ;
                                offset3 = minY;
                                offset11 = offset10;
                                offset23 = offset28;
                            } else {
                                offset23 = offset24 - offset9;
                                offset3 = minY;
                                minX = minZ;
                                offset11 = offset10;
                            }
                        }


                        offset13 = maxX;
                        offset14 = minX;
                        offset10 = offset11;
                        offset11 = offset23;
                        minX = offset3;
                        minY = offset23;
                    }

                    while (true) {
                        offset11 = 0.0;
                        index7 = !Double.isNaN(maxY) && !Double.isNaN(offset11) ? (maxY > offset11 ? 1 : (maxY == offset11 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            value2 = scaleFactor;
                            index7 = index9;
                            offset9 = minX;
                            break;
                        }

                        index7 = !Double.isNaN(minY) && !Double.isNaN(0.0) ? (minY > 0.0 ? 1 : (minY == 0.0 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            value2 = scaleFactor;
                            index7 = index9;
                            offset9 = minX;
                            break;
                        }

                        Minecraft mc4 = minecraft;
                        WorldClient world5 = mc4.theWorld;
                        boundingBox3 = boundingBox5.expand(maxY, -1.0, minY);
                        List collidingBoundingBoxes3 = world5.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes3.isEmpty();
                        value2 = scaleFactor;
                        if (!state31) {
                            index7 = index9;
                            offset9 = minX;
                            break;
                        }

                        double offset29 = 0.0;
                        boolean completed4 = false;
                        value2 = value3;
                        index7 = !Double.isNaN(maxY) && !Double.isNaN(offset9) ? (maxY > offset9 ? 1 : (maxY == offset9 ? 0 : -1)) : 1;
                        double offset30 = posZ;
                        double offset31 = maxY;
                        minX = posX;
                        double offset32 = motionZ;
                        double offset33 = maxZ;
                        double offset34 = motionY;
                        double offset35 = motionX;
                        posZ = posY;
                        if (index7 < 0) {
                            offset9 = -offset9;
                            index7 = !Double.isNaN(maxY) && !Double.isNaN(offset9) ? (maxY > offset9 ? 1 : (maxY == offset9 ? 0 : -1)) : -1;
                            offset32 = motionZ;
                            offset33 = maxZ;
                            offset34 = motionY;
                            offset35 = motionX;
                            posZ = posY;
                            offset30 = posZ;
                            if (index7 >= 0) {
                                offset11 = 0.0;
                                minZ = maxX;
                                posX = minZ;
                                offset3 = minY;
                                offset29 = offset11;
                                completed4 = true;
                            }
                            if (!completed4) {
                                offset31 = maxY;
                            }
                        }
                        if (!completed4) {
                            motionY = offset34;
                            posY = posZ;
                            minX = posX;
                            offset9 = 0.0;
                            motionZ = offset32;
                            maxZ = offset33;
                            motionX = offset35;
                            posZ = offset30;
                            index7 = !Double.isNaN(offset31) && !Double.isNaN(offset9) ? (offset31 > offset9 ? 1 : (offset31 == offset9 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                motionZ = offset31 + offset9;
                                value2 = value3;
                                minX = posX;
                                motionZ = offset32;
                                motionY = offset34;
                                motionX = offset35;
                                posY = posZ;
                                minZ = maxX;
                                posX = minZ;
                                offset3 = minY;
                                offset29 = motionZ;
                            } else {
                                double offset36 = offset31 - offset9;
                                posX = minZ;
                                minZ = maxX;
                                offset3 = minY;
                                offset29 = offset36;
                            }
                        }


                        boolean completed5 = false;
                        index7 = !Double.isNaN(offset3) && !Double.isNaN(offset9) ? (offset3 > offset9 ? 1 : (offset3 == offset9 ? 0 : -1)) : 1;
                        motionZ = offset3;
                        if (index7 >= 0) {
                            maxZ = motionZ;
                            motionY = maxZ;
                            motionX = motionY;
                            posY = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = minZ;
                            minZ = posX;
                        } else {
                            offset9 = -offset9;
                            index7 = !Double.isNaN(offset3) && !Double.isNaN(offset9) ? (offset3 > offset9 ? 1 : (offset3 == offset9 ? 0 : -1)) : -1;
                            maxZ = motionZ;
                            motionY = maxZ;
                            motionX = motionY;
                            posY = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = minZ;
                            if (index7 >= 0) {
                                posX = minX;
                                offset11 = offset9;
                                maxX = minZ;
                                minY = posX;
                                offset3 = offset29;
                                offset9 = offset29;
                                offset29 = maxX;
                                completed5 = true;
                            }
                            if (!completed5) {
                                minZ = posX;
                                motionZ = offset3;
                            }
                        }
                        if (!completed5) {
                            motionY = motionX;
                            posY = posZ;
                            offset9 = 0.0;
                            motionZ = maxZ;
                            maxZ = motionY;
                            motionX = posY;
                            posZ = maxY;
                            index7 = !Double.isNaN(motionZ) && !Double.isNaN(offset9) ? (motionZ > offset9 ? 1 : (motionZ == offset9 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                minY = minZ;
                                motionZ += offset9;
                                motionZ = maxZ;
                                motionY = motionX;
                                posY = posZ;
                                posZ = maxY;
                                offset3 = offset29;
                                posX = minX;
                                offset11 = offset9;
                                offset9 = offset29;
                                offset29 = motionZ;
                            } else {
                                double offset37 = motionZ - offset9;
                                offset3 = offset29;
                                posX = minX;
                                minY = minZ;
                                offset11 = offset9;
                                offset9 = offset29;
                                offset29 = offset37;
                            }
                        }


                        value3 = value2;
                        minZ = minY;
                        minX = offset3;
                        offset3 = offset29;
                        minY = offset29;
                        maxY = offset9;
                        offset9 = offset11;
                    }
                }

                Minecraft mc5 = minecraft;
                WorldClient world6 = mc5.theWorld;
                scaleFactor = value2;
                index9 = index7;
                value2 = value3;
                maxX = minZ;
                minZ = minY;
                minY = offset9;
                minX = posX;
                posX = offset3;
                world2 = world6;
            } else {
                double offset38 = 0.0;
                boolean completed6 = false;
                WorldClient world7 = minecraft.theWorld;
                minZ = posZ;
                index7 = MathHelper.ceiling_double_int(maxY);
                index6 = MathHelper.ceiling_double_int(posZ);
                int index13 = index6 - 1;
                index6 = MathHelper.ceiling_double_int(posY);
                BlockPos blockPos2 = new BlockPos(index7, index13, index6);
                Block block2 = world7.getBlockState(blockPos2).getBlock();
                value2 = block2.scaleFactor;
                value2 *= 0.91F;
                state31 = player.isSprinting();
                state31 = player.onGround;
                value3 = value2;
                byte state3 = (byte)0;
                double offset39 = 0.0;
                double offset40 = 0.0;
                double offset41 = 0.0;
                double offset42 = 0.0;
                double offset43 = 0.0;
                double offset44 = 0.0;
                double offset45 = 0.0;
                if (!state31) {
                    offset39 = posY;
                    offset40 = motionX;
                    offset38 = motionY;
                    minX = maxZ;
                    state3 = (byte)index11;
                    value2 = scaleFactor;
                    offset42 = motionZ;
                    index6 = indexOffset18;
                    offset41 = motionX;
                    posX = motionY;
                    minY = maxZ;
                    offset43 = motionX;
                    offset44 = motionY;
                    offset45 = maxZ;
                } else {
                    offset38 = motionY;
                    state3 = (byte)index11;
                    value2 = scaleFactor;
                    offset39 = posY;
                    offset40 = motionX;
                    if (state31) {
                        scaleFactor = value2;
                        motionZ = maxY;
                        boundingBox5 = boundingBox4;
                        index9 = indexOffset18;
                        maxZ = posZ;
                        motionY = posY;
                        posY = motionY;
                        posZ = maxZ;
                        index12 = index9;
                        index11 = (byte)index11;
                        maxX = motionZ;
                        maxY = motionX;
                        minZ = motionY;
                        minY = maxZ;
                        offset38 = motionX;
                        posX = motionY;
                        minX = maxZ;
                        enabled3 = true;
                        completed6 = true;
                    }
                    if (!completed6) {
                        minZ = posZ;
                        minX = maxZ;
                        offset42 = motionZ;
                        index6 = indexOffset18;
                        value3 = value2;
                        offset41 = motionX;
                        posX = motionY;
                        minY = maxZ;
                        offset43 = motionX;
                        offset44 = motionY;
                        offset45 = maxZ;
                    }
                }
                if (!completed6) {
                    motionZ = maxY;
                    maxZ = minZ;
                    motionY = offset39;
                    motionX = offset40;
                    posY = offset38;
                    posZ = minX;
                    index12 = index9;
                    boundingBox5 = boundingBox4;
                    index11 = state3;
                    maxX = offset42;
                    index9 = index6;
                    scaleFactor = value3;
                    maxY = offset41;
                    minZ = posX;
                    offset38 = offset43;
                    posX = offset44;
                    minX = offset45;
                    enabled3 = false;
                }


                double offset46 = 0.0;
                if (!enabled3) {
                    index7 = index9;
                    value3 = scaleFactor;
                    offset46 = offset38;
                    offset38 = minX;
                } else {
                    float value9 = scaleFactor;
                    double offset47 = posX;
                    double offset48 = minX;
                    double offset49 = 0.05;
                    scaleFactor = value2;
                    double offset50 = maxX;
                    double offset51 = maxY;
                    minX = minZ;
                    posX = offset38;

                    while (true) {
                        index7 = !Double.isNaN(offset51) && !Double.isNaN(0.0) ? (offset51 > 0.0 ? 1 : (offset51 == 0.0 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            offset46 = offset49;
                            break;
                        }

                        WorldClient world8 = minecraft.theWorld;
                        boundingBox3 = boundingBox5.expand(offset51, -1.0, 0.0);
                        List collidingBoundingBoxes4 = world8.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes4.isEmpty();
                        if (!state31) {
                            offset46 = offset49;
                            break;
                        }

                        double offset52 = 0.0;
                        double offset53 = 0.0;
                        boolean completed7 = false;
                        index7 = !Double.isNaN(offset51) && !Double.isNaN(offset49) ? (offset51 > offset49 ? 1 : (offset51 == offset49 ? 0 : -1)) : 1;
                        maxX = offset50;
                        double offset54 = offset51;
                        double offset55 = motionZ;
                        double offset56 = maxZ;
                        double offset57 = motionY;
                        double offset58 = motionX;
                        double offset59 = posY;
                        maxY = posZ;
                        if (index7 < 0) {
                            offset46 = -offset49;
                            index7 = !Double.isNaN(offset51) && !Double.isNaN(offset46) ? (offset51 > offset46 ? 1 : (offset51 == offset46 ? 0 : -1)) : -1;
                            offset55 = motionZ;
                            offset56 = maxZ;
                            offset57 = motionY;
                            offset58 = motionX;
                            offset59 = posY;
                            maxY = posZ;
                            if (index7 >= 0) {
                                minZ = offset50;
                                offset38 = minY;
                                offset48 = offset47;
                                offset46 = offset48;
                                offset52 = offset49;
                                offset53 = minZ;
                                completed7 = true;
                            }
                            if (!completed7) {
                                maxX = offset50;
                                offset54 = offset51;
                            }
                        }
                        if (!completed7) {
                            motionY = offset57;
                            posY = offset59;
                            offset46 = 0.0;
                            motionZ = offset55;
                            maxZ = offset56;
                            motionX = offset58;
                            posZ = maxY;
                            index7 = !Double.isNaN(offset54) && !Double.isNaN(offset46) ? (offset54 > offset46 ? 1 : (offset54 == offset46 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                motionZ = offset54 + offset49;
                                offset48 = offset47;
                                offset53 = motionZ;
                                motionZ = offset55;
                                motionY = offset57;
                                motionX = offset58;
                                posY = offset59;
                                minZ = maxX;
                                offset38 = minY;
                                offset46 = offset48;
                                offset52 = offset49;
                            } else {
                                offset48 = offset47;
                                offset52 = offset54 - offset49;
                                offset38 = minY;
                                minZ = maxX;
                                offset46 = offset48;
                                offset52 = offset49;
                                offset53 = offset52;
                            }
                        }


                        offset50 = minZ;
                        minY = offset38;
                        posX = offset53;
                        offset47 = offset48;
                        offset48 = offset46;
                        offset51 = offset53;
                        offset49 = offset52;
                    }

                    while (true) {
                        maxX = 0.0;
                        index7 = !Double.isNaN(minY) && !Double.isNaN(maxX) ? (minY > maxX ? 1 : (minY == maxX ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            maxX = offset50;
                            value3 = value9;
                            maxY = offset51;
                            minZ = minX;
                            minX = posX;
                            posX = offset47;
                            offset38 = offset48;
                            break;
                        }

                        WorldClient world9 = minecraft.theWorld;
                        boundingBox3 = boundingBox5.expand(0.0, -1.0, minY);
                        List collidingBoundingBoxes5 = world9.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes5.isEmpty();
                        maxY = offset51;
                        minZ = minX;
                        minX = posX;
                        posX = offset47;
                        offset38 = offset48;
                        if (!state31) {
                            maxX = offset50;
                            value3 = value9;
                            break;
                        }

                        double offset60 = 0.0;
                        boolean completed8 = false;
                        index7 = !Double.isNaN(minY) && !Double.isNaN(offset46) ? (minY > offset46 ? 1 : (minY == offset46 ? 0 : -1)) : 1;
                        maxY = posZ;
                        double offset61 = minY;
                        double offset62 = maxZ;
                        double offset63 = motionY;
                        double offset64 = motionX;
                        posZ = posY;
                        maxX = offset50;
                        minZ = offset51;
                        minY = minX;
                        if (index7 < 0) {
                            offset46 = -offset46;
                            index7 = !Double.isNaN(minY) && !Double.isNaN(offset46) ? (minY > offset46 ? 1 : (minY == offset46 ? 0 : -1)) : -1;
                            offset62 = maxZ;
                            offset63 = motionY;
                            offset64 = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = offset50;
                            minZ = offset51;
                            if (index7 >= 0) {
                                maxX = offset50;
                                minX = offset51;
                                offset38 = minX;
                                offset48 = offset47;
                                offset60 = maxX;
                                completed8 = true;
                            }
                            if (!completed8) {
                                minY = minX;
                                offset61 = minY;
                            }
                        }
                        if (!completed8) {
                            motionY = offset63;
                            posY = posZ;
                            offset46 = 0.0;
                            maxZ = offset62;
                            motionX = offset64;
                            posZ = maxY;
                            index7 = !Double.isNaN(offset61) && !Double.isNaN(offset46) ? (offset61 > offset46 ? 1 : (offset61 == offset46 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                double offset65 = offset61 + offset46;
                                motionY = offset63;
                                posY = posZ;
                                minX = minZ;
                                offset38 = minY;
                                offset48 = offset47;
                                offset60 = offset65;
                            } else {
                                offset60 = offset61 - offset46;
                                offset38 = minY;
                                minX = minZ;
                                offset48 = offset47;
                            }
                        }


                        offset50 = maxX;
                        offset51 = minX;
                        offset47 = offset48;
                        offset48 = offset60;
                        minX = offset38;
                        minY = offset60;
                    }

                    while (true) {
                        offset48 = 0.0;
                        index7 = !Double.isNaN(maxY) && !Double.isNaN(offset48) ? (maxY > offset48 ? 1 : (maxY == offset48 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            value2 = scaleFactor;
                            index7 = index9;
                            offset46 = minX;
                            break;
                        }

                        index7 = !Double.isNaN(minY) && !Double.isNaN(0.0) ? (minY > 0.0 ? 1 : (minY == 0.0 ? 0 : -1)) : -1;
                        if (index7 == 0) {
                            value2 = scaleFactor;
                            index7 = index9;
                            offset46 = minX;
                            break;
                        }

                        WorldClient world10 = minecraft.theWorld;
                        boundingBox3 = boundingBox5.expand(maxY, -1.0, minY);
                        List collidingBoundingBoxes6 = world10.getCollidingBoundingBoxes(player, boundingBox3);
                        state31 = collidingBoundingBoxes6.isEmpty();
                        value2 = scaleFactor;
                        if (!state31) {
                            index7 = index9;
                            offset46 = minX;
                            break;
                        }

                        double offset66 = 0.0;
                        boolean completed9 = false;
                        value2 = value3;
                        index7 = !Double.isNaN(maxY) && !Double.isNaN(offset46) ? (maxY > offset46 ? 1 : (maxY == offset46 ? 0 : -1)) : 1;
                        double offset67 = posZ;
                        double offset68 = maxY;
                        minX = posX;
                        double offset69 = motionZ;
                        double offset70 = maxZ;
                        double offset71 = motionY;
                        double offset72 = motionX;
                        posZ = posY;
                        if (index7 < 0) {
                            offset46 = -offset46;
                            index7 = !Double.isNaN(maxY) && !Double.isNaN(offset46) ? (maxY > offset46 ? 1 : (maxY == offset46 ? 0 : -1)) : -1;
                            offset69 = motionZ;
                            offset70 = maxZ;
                            offset71 = motionY;
                            offset72 = motionX;
                            posZ = posY;
                            offset67 = posZ;
                            if (index7 >= 0) {
                                offset48 = 0.0;
                                minZ = maxX;
                                posX = minZ;
                                offset38 = minY;
                                offset66 = offset48;
                                completed9 = true;
                            }
                            if (!completed9) {
                                offset68 = maxY;
                            }
                        }
                        if (!completed9) {
                            motionY = offset71;
                            posY = posZ;
                            minX = posX;
                            offset46 = 0.0;
                            motionZ = offset69;
                            maxZ = offset70;
                            motionX = offset72;
                            posZ = offset67;
                            index7 = !Double.isNaN(offset68) && !Double.isNaN(offset46) ? (offset68 > offset46 ? 1 : (offset68 == offset46 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                motionZ = offset68 + offset46;
                                value2 = value3;
                                minX = posX;
                                motionZ = offset69;
                                motionY = offset71;
                                motionX = offset72;
                                posY = posZ;
                                minZ = maxX;
                                posX = minZ;
                                offset38 = minY;
                                offset66 = motionZ;
                            } else {
                                double offset73 = offset68 - offset46;
                                posX = minZ;
                                minZ = maxX;
                                offset38 = minY;
                                offset66 = offset73;
                            }
                        }


                        boolean completed10 = false;
                        index7 = !Double.isNaN(offset38) && !Double.isNaN(offset46) ? (offset38 > offset46 ? 1 : (offset38 == offset46 ? 0 : -1)) : 1;
                        motionZ = offset38;
                        if (index7 >= 0) {
                            maxZ = motionZ;
                            motionY = maxZ;
                            motionX = motionY;
                            posY = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = minZ;
                            minZ = posX;
                        } else {
                            offset46 = -offset46;
                            index7 = !Double.isNaN(offset38) && !Double.isNaN(offset46) ? (offset38 > offset46 ? 1 : (offset38 == offset46 ? 0 : -1)) : -1;
                            maxZ = motionZ;
                            motionY = maxZ;
                            motionX = motionY;
                            posY = motionX;
                            posZ = posY;
                            maxY = posZ;
                            maxX = minZ;
                            if (index7 >= 0) {
                                posX = minX;
                                offset48 = offset46;
                                maxX = minZ;
                                minY = posX;
                                offset38 = offset66;
                                offset46 = offset66;
                                offset66 = maxX;
                                completed10 = true;
                            }
                            if (!completed10) {
                                minZ = posX;
                                motionZ = offset38;
                            }
                        }
                        if (!completed10) {
                            motionY = motionX;
                            posY = posZ;
                            offset46 = 0.0;
                            motionZ = maxZ;
                            maxZ = motionY;
                            motionX = posY;
                            posZ = maxY;
                            index7 = !Double.isNaN(motionZ) && !Double.isNaN(offset46) ? (motionZ > offset46 ? 1 : (motionZ == offset46 ? 0 : -1)) : -1;
                            if (index7 <= 0) {
                                minY = minZ;
                                motionZ += offset46;
                                motionZ = maxZ;
                                motionY = motionX;
                                posY = posZ;
                                posZ = maxY;
                                offset38 = offset66;
                                posX = minX;
                                offset48 = offset46;
                                offset46 = offset66;
                                offset66 = motionZ;
                            } else {
                                double offset74 = motionZ - offset46;
                                offset38 = offset66;
                                posX = minX;
                                minY = minZ;
                                offset48 = offset46;
                                offset46 = offset66;
                                offset66 = offset74;
                            }
                        }


                        value3 = value2;
                        minZ = minY;
                        minX = offset38;
                        offset38 = offset66;
                        minY = offset66;
                        maxY = offset46;
                        offset46 = offset48;
                    }
                }

                WorldClient world11 = minecraft.theWorld;
                scaleFactor = value2;
                index9 = index7;
                value2 = value3;
                maxX = minZ;
                minZ = minY;
                minY = offset46;
                minX = posX;
                posX = offset38;
                world2 = world11;
            }

            boundingBox3 = boundingBox5.contract(maxY, maxX, minZ);
            List collidingBoundingBoxes7 = world2.getCollidingBoundingBoxes(player, boundingBox3);
            Iterator iterator = collidingBoundingBoxes7.iterator();
            double offset75 = maxZ;
            posX = motionX;
            minX = posY;
            AxisAlignedBB boundingBox6 = boundingBox5;
            minY = maxX;

            while (true) {
                state31 = iterator.hasNext();
                if (!state31) {
                    AxisAlignedBB boundingBox7 = null;
                    AxisAlignedBB boundingBox8 = null;
                    double offset76 = 0.0;
                    double offset77 = 0.0;
                    double offset78 = 0.0;
                    double offset79 = 0.0;
                    double offset80 = 0.0;
                    boolean completed12 = false;
                    boundingBox8 = boundingBox6.expand(0.0, minY, 0.0);
                    posX = maxY;
                    minX = minY;
                    minY = minZ;
                    posY = motionY;
                    int index14 = 0;
                    AxisAlignedBB boundingBox9 = null;
                    if ((index12) != 0) {
                        maxY = posX;
                        maxX = minX;
                        minZ = posZ;
                        index14 = index11;
                        value2 = scaleFactor;
                        value3 = value2;
                        offset77 = minY;
                        offset76 = minX;
                        offset75 = posX;
                        list = collidingBoundingBoxes7;
                        boundingBox9 = boundingBox5;
                    } else {
                        boolean completed11 = false;
                        index12 = !Double.isNaN(minX) && !Double.isNaN(minY) ? (minX > minY ? 1 : (minX == minY ? 0 : -1)) : -1;
                        AxisAlignedBB boundingBox10 = boundingBox5;
                        posY = motionY;
                        maxY = posX;
                        double offset81 = minX;
                        value2 = scaleFactor;
                        maxX = minZ;
                        list = collidingBoundingBoxes7;
                        posX = posZ;
                        index14 = index11;
                        if (index12 == 0) {
                            value3 = value2;
                            offset77 = maxY;
                            minX = minY;
                            offset79 = minX;
                            offset80 = posX;
                        } else {
                            index14 = !Double.isNaN(minX) && !Double.isNaN(0.0) ? (minX > 0.0 ? 1 : (minX == 0.0 ? 0 : -1)) : 1;
                            minX = minY;
                            minY = minZ;
                            offset77 = maxY;
                            boundingBox10 = boundingBox5;
                            if (index14 < 0) {
                                posY = motionY;
                                maxY = posX;
                                maxX = minX;
                                minZ = posZ;
                                index14 = index11;
                                value2 = scaleFactor;
                                value3 = value2;
                                posX = maxY;
                                offset77 = minY;
                                offset76 = minX;
                                offset75 = posX;
                                list = collidingBoundingBoxes7;
                                boundingBox9 = boundingBox5;
                                completed11 = true;
                            }
                            if (!completed11) {
                                posY = motionY;
                                maxY = posX;
                                offset81 = minX;
                                posX = posZ;
                                index14 = index11;
                                value2 = scaleFactor;
                                value3 = value2;
                                maxX = minZ;
                                offset79 = minX;
                                offset80 = posX;
                                list = collidingBoundingBoxes7;
                            }
                        }
                        if (!completed11) {
                            motionY = posY;
                            motionX = maxY;
                            posY = offset81;
                            posZ = posX;
                            index12 = index14;
                            scaleFactor = value2;
                            index11 = index9;
                            offset76 = minX;
                            offset75 = maxX;
                            offset78 = minY;
                            boundingBox7 = boundingBox10;
                            enabled4 = false;
                            completed12 = true;
                        }
                    }
                    if (!completed12) {
                        motionY = posY;
                        motionX = maxY;
                        posY = maxX;
                        posZ = minZ;
                        index12 = (byte)index14;
                        scaleFactor = value2;
                        enabled4 = true;
                        index11 = index9;
                        offset77 = posX;
                        offset76 = minX;
                        offset75 = minY;
                        offset78 = offset77;
                        offset79 = offset76;
                        offset80 = offset75;
                        boundingBox7 = boundingBox9;
                    }


                    Iterator iterator2 = list.iterator();
                    minZ = motionX;
                    AxisAlignedBB boundingBox11 = boundingBox8;
                    int index15 = index12;
                    posZ = offset77;
                    int index16 = (enabled4 ? 1 : 0);
                    Iterator iterator3 = iterator2;

                    while (true) {
                        state31 = iterator3.hasNext();
                        if (!state31) {
                            boundingBox8 = boundingBox11.expand(posZ, 0.0, 0.0);
                            Iterator iterator4 = list.iterator();
                            AxisAlignedBB boundingBox12 = boundingBox8;
                            value2 = scaleFactor;
                            scaleFactor = value3;
                            offset77 = offset75;
                            posX = offset80;

                            while (true) {
                                state31 = iterator4.hasNext();
                                if (!state31) {
                                    AxisAlignedBB axisAlignedBB6 = boundingBox12.expand(0.0, 0.0, offset77);
                                    AxisAlignedBB boundingBox13 = axisAlignedBB6;
                                    index16 = (byte)index15;
                                    value2 = scaleFactor;
                                    maxY = posZ;
                                    maxX = offset76;
                                    value3 = player.stepHeight;
                                    StateTypes stateTypes = StateTypes.PRE;
                                    EventTickState eventTickState = EventTickStateCallback.getEventTickState(value3, stateTypes);
                                    value3 = eventTickState.getScaleFactor();
                                    index15 = !Float.isNaN(value3) && !Float.isNaN(0.0F) ? (value3 > 0.0F ? 1 : (value3 == 0.0F ? 0 : -1)) : -1;
                                    motionY = minZ;
                                    if (index15 <= 0) {
                                        minZ = offset77;
                                        minY = offset78;
                                        posX = offset79;
                                        offset77 = posX;
                                    } else {
                                        minZ = offset77;
                                        if (((index16) == 0)) {
                                            motionY = minZ;
                                            minY = offset78;
                                            posX = offset79;
                                            offset77 = posX;
                                        } else {
                                            boolean completed13 = false;
                                            index16 = !Double.isNaN(offset78) && !Double.isNaN(posZ) ? (offset78 > posZ ? 1 : (offset78 == posZ ? 0 : -1)) : -1;
                                            posX = posZ;
                                            index7 = index11;
                                            minX = offset76;
                                            double offset82 = motionY;
                                            maxX = minZ;
                                            minY = posY;
                                            byte state4 = (byte)index15;
                                            motionZ = posZ;
                                            EventTickState event = null;
                                            if (index16 != 0) {
                                                index15 = index11;
                                                value3 = scaleFactor;
                                                offset76 = offset78;
                                                offset75 = offset79;
                                                offset78 = posX;
                                                event = eventTickState;
                                            } else {
                                                value2 = scaleFactor;
                                                maxX = offset76;
                                                motionY = minZ;
                                                maxY = posZ;
                                                index16 = !Double.isNaN(posX) && !Double.isNaN(offset77)
                                                ? (posX > offset77 ? 1 : (posX == offset77 ? 0 : -1))
                                                : -1;
                                                index16 = (byte)index15;
                                                if (index16 == 0) {
                                                    minZ = offset77;
                                                    minY = offset78;
                                                    posX = offset79;
                                                    offset77 = posX;
                                                    completed13 = true;
                                                }
                                                if (!completed13) {
                                                    minY = posY;
                                                    posX = posZ;
                                                    offset82 = motionY;
                                                    maxX = minZ;
                                                    state4 = (byte)index15;
                                                    index15 = index7;
                                                    value3 = scaleFactor;
                                                    motionZ = posZ;
                                                    minX = offset76;
                                                    offset76 = offset78;
                                                    offset75 = offset79;
                                                    offset78 = posX;
                                                    event = eventTickState;
                                                }
                                            }
                                            if (!completed13) {
                                                value2 = event.getScaleFactor();
                                                double offset83 = (double)value2;
                                                WorldClient world12 = minecraft.theWorld;
                                                boundingBox3 = boundingBox7.contract(offset76, offset83, offset78);
                                                List collidingBoundingBoxes8 = world12.getCollidingBoundingBoxes(player, boundingBox3);
                                                boundingBox7 = boundingBox7.contract(offset76, 0.0, offset78);
                                                Iterator iterator5 = collidingBoundingBoxes8.iterator();
                                                offset75 = maxX;
                                                offset77 = offset83;
                                                Iterator iterator6 = iterator5;
                                                outerLoop:
                                                while (true) {
                                                    state31 = iterator6.hasNext();
                                                    if (!state31) {
                                                        boundingBox8 = boundingBox7.expand(0.0, offset77, 0.0);
                                                        AxisAlignedBB boundingBox14 = boundingBox8;
                                                        Iterator iterator7 = collidingBoundingBoxes8.iterator();
                                                        Iterator iterator8 = iterator7;

                                                        while (true) {
                                                            state31 = iterator8.hasNext();
                                                            if (!state31) {
                                                                boundingBox8 = boundingBox14.expand(offset76, 0.0, 0.0);
                                                                double offset84 = offset75;
                                                                AxisAlignedBB boundingBox15 = boundingBox8;
                                                                Iterator iterator9 = collidingBoundingBoxes8.iterator();
                                                                offset75 = minY;
                                                                offset76 = offset78;
                                                                Iterator iterator10 = iterator9;

                                                                while (true) {
                                                                    state31 = iterator10.hasNext();
                                                                    if (!state31) {
                                                                        boundingBox8 = boundingBox15.expand(0.0, 0.0, offset76);
                                                                        Iterator iterator11 = collidingBoundingBoxes8.iterator();
                                                                        offset79 = offset75;
                                                                        offset78 = offset77;
                                                                        AxisAlignedBB boundingBox16 = boundingBox7;
                                                                        offset75 = offset83;
                                                                        Iterator iterator12 = iterator11;

                                                                        while (true) {
                                                                            state31 = iterator12.hasNext();
                                                                            if (!state31) {
                                                                                boundingBox7 = boundingBox16.expand(0.0, offset75, 0.0);
                                                                                AxisAlignedBB boundingBox17 = boundingBox7;
                                                                                Iterator iterator13 = collidingBoundingBoxes8.iterator();
                                                                                offset78 = offset76;
                                                                                Iterator iterator14 = iterator13;

                                                                                while (true) {
                                                                                    state31 = iterator14.hasNext();
                                                                                    if (!state31) {
                                                                                        boundingBox7 = boundingBox17.expand(offset78, 0.0, 0.0);
                                                                                        AxisAlignedBB boundingBox18 = boundingBox7;
                                                                                        Iterator iterator15 = collidingBoundingBoxes8.iterator();
                                                                                        double offset85 = offset82;
                                                                                        offset83 = offset84;
                                                                                        offset80 = offset79;
                                                                                        offset79 = posX;
                                                                                        Iterator iterator16 = iterator15;

                                                                                        while (true) {
                                                                                            state31 = iterator16.hasNext();
                                                                                            if (!state31) {
                                                                                                boundingBox7 = boundingBox18.expand(0.0, 0.0, offset78);
                                                                                                double offset86 = offset76 * offset76;
                                                                                                offset80 = offset76 * offset76;
                                                                                                offset77 = offset86 + offset80;
                                                                                                AxisAlignedBB boundingBox19 = boundingBox7;
                                                                                                motionY = offset78 * offset78;
                                                                                                maxZ = offset78 * offset78;
                                                                                                offset85 = motionY + maxZ;
                                                                                                minY = offset83;
                                                                                                index16 = !Double.isNaN(offset77) && !Double.isNaN(offset85)
                                                                                                ? (offset77 > offset85 ? 1 : (offset77 == offset85 ? 0 : -1))
                                                                                                : -1;
                                                                                                List list2 = collidingBoundingBoxes8;
                                                                                                byte state5;
                                                                                                AxisAlignedBB boundingBox20;
                                                                                                double offset87;
                                                                                                double offset88;
                                                                                                double offset89;
                                                                                                double offset90;
                                                                                                if (index16 <= 0) {
                                                                                                    double offset91 = -offset75;
                                                                                                    offset87 = motionZ;
                                                                                                    minX = offset80;
                                                                                                    maxX = offset79;
                                                                                                    state5 = state4;
                                                                                                    rotationYaw = value3;
                                                                                                    offset83 = offset78;
                                                                                                    offset79 = offset91;
                                                                                                    offset80 = offset78;
                                                                                                    offset85 = offset76;
                                                                                                    offset76 = offset75;
                                                                                                    offset88 = offset78;
                                                                                                    offset89 = minX;
                                                                                                    offset90 = offset77;
                                                                                                    boundingBox20 = axisAlignedBB6;
                                                                                                    list2 = collidingBoundingBoxes8;
                                                                                                } else {
                                                                                                    offset90 = -offset77;
                                                                                                    offset76 = offset75;
                                                                                                    boundingBox20 = axisAlignedBB6;
                                                                                                    minY = offset83;
                                                                                                    maxX = offset79;
                                                                                                    offset79 = offset90;
                                                                                                    offset80 = offset76;
                                                                                                    minX = offset80;
                                                                                                    boundingBox19 = boundingBox8;
                                                                                                    state5 = state4;
                                                                                                    rotationYaw = value3;
                                                                                                    offset83 = offset76;
                                                                                                    offset85 = offset76;
                                                                                                    offset88 = offset78;
                                                                                                    offset87 = motionZ;
                                                                                                    offset89 = minX;
                                                                                                    offset90 = offset77;
                                                                                                }

                                                                                                Iterator iterator17 = list2.iterator();
                                                                                                posX = minX;
                                                                                                AxisAlignedBB boundingBox21 = boundingBox19;
                                                                                                minX = offset79;

                                                                                                while (true) {
                                                                                                    state31 = iterator17.hasNext();
                                                                                                    if (!state31) {
                                                                                                        AxisAlignedBB axisAlignedBB62 = boundingBox21.expand(
                                                                                                        0.0, minX, 0.0
                                                                                                        );
                                                                                                        offset78 = offset87 * offset87;
                                                                                                        offset75 = offset90 * offset90;
                                                                                                        offset75 = offset78 + offset75;
                                                                                                        offset79 = offset83 * offset83;
                                                                                                        offset78 = offset80 * offset80;
                                                                                                        offset78 = offset79 + offset78;
                                                                                                        int index17 = !Double.isNaN(offset75)
                                                                                                        && !Double.isNaN(offset78)
                                                                                                        ? (
                                                                                                        offset75 > offset78
                                                                                                        ? 1
                                                                                                        : (offset75 == offset78 ? 0 : -1)
                                                                                                        )
                                                                                                        : -1;
                                                                                                        index11 = index15;
                                                                                                        maxX = minX;
                                                                                                        boundingBox13 = axisAlignedBB62;
                                                                                                        value2 = rotationYaw;
                                                                                                        posY = posX;
                                                                                                        posZ = maxX;
                                                                                                        index16 = state5;
                                                                                                        maxY = offset83;
                                                                                                        motionY = minY;
                                                                                                        minZ = offset80;
                                                                                                        if (index17 < 0) {
                                                                                                            minY = offset85;
                                                                                                            posX = offset76;
                                                                                                            offset77 = offset88;
                                                                                                        } else {
                                                                                                            maxY = offset87;
                                                                                                            maxX = offset89;
                                                                                                            minZ = offset90;
                                                                                                            boundingBox13 = boundingBox20;
                                                                                                            minY = offset85;
                                                                                                            posX = offset76;
                                                                                                            offset77 = offset88;
                                                                                                        }
                                                                                                        break outerLoop;
                                                                                                    }

                                                                                                    iterator = (Iterator)iterator17.next();
                                                                                                    AxisAlignedBB boundingBox22 = (AxisAlignedBB)iterator;
                                                                                                    offset80 = boundingBox22.getCoordinate3(boundingBox21, minX);
                                                                                                    minX = offset80;
                                                                                                }
                                                                                            }

                                                                                            boundingBox7 = (AxisAlignedBB)iterator16.next();
                                                                                            offset77 = boundingBox7.getCoordinate(boundingBox18, offset78);
                                                                                            offset78 = offset77;
                                                                                        }
                                                                                    }

                                                                                    boundingBox7 = (AxisAlignedBB)iterator14.next();
                                                                                    double coordinate2 = boundingBox7.getCoordinate2(boundingBox17, offset78);
                                                                                    offset78 = coordinate2;
                                                                                }
                                                                            }

                                                                            boundingBox7 = (AxisAlignedBB)iterator12.next();
                                                                            double coordinate3 = boundingBox7.getCoordinate3(boundingBox16, offset75);
                                                                            offset75 = coordinate3;
                                                                        }
                                                                    }

                                                                    boundingBox8 = (AxisAlignedBB)iterator10.next();
                                                                    double coordinate = boundingBox8.getCoordinate(boundingBox15, offset76);
                                                                    offset76 = coordinate;
                                                                }
                                                            }

                                                            boundingBox8 = (AxisAlignedBB)iterator8.next();
                                                            double coordinate22 = boundingBox8.getCoordinate2(boundingBox14, offset76);
                                                            offset76 = coordinate22;
                                                        }
                                                    }

                                                    boundingBox8 = (AxisAlignedBB)iterator6.next();
                                                    offset79 = boundingBox8.getCoordinate3(boundingBox7, offset77);
                                                    offset77 = offset79;
                                                }
                                            }

                                        }
                                    }

                                    maxZ = boundingBox13.minX;
                                    motionZ = boundingBox13.maxX;
                                    motionZ = maxZ + motionZ;
                                    motionZ /= 2.0;
                                    maxZ = boundingBox13.minY;
                                    minX = boundingBox13.minZ;
                                    motionX = boundingBox13.maxZ;
                                    motionX = minX + motionX;
                                    motionX /= 2.0;
                                    index15 = !Double.isNaN(posX) && !Double.isNaN(maxX) ? (posX > maxX ? 1 : (posX == maxX ? 0 : -1)) : -1;
                                    posY = minZ;
                                    motionX = minY;
                                    int index18 = 0;
                                    if (index15 == 0) {
                                        posX = maxZ;
                                        scaleFactor = value2;
                                        posZ = maxX;
                                        maxZ = offset77;
                                        minX = motionX;
                                        minY = motionY;
                                        minZ = posY;
                                        maxX = posZ;
                                        index18 = 0;
                                    } else {
                                        scaleFactor = value2;
                                        maxZ = offset77;
                                        index18 = 1;
                                        posX = maxZ;
                                        minX = motionX;
                                        minY = motionY;
                                        minZ = posY;
                                        maxX = posZ;
                                        posZ = maxX;
                                        posY = minZ;
                                        motionX = minY;
                                    }

                                    boolean completed14 = false;
                                    posZ = motionX;
                                    posY = minX;
                                    maxY = minY;
                                    index18 = index11;
                                    motionX = posX;
                                    if ((index18) == 0) {
                                        offset77 = maxX;
                                        index = index16;
                                        value3 = scaleFactor;
                                        offset76 = maxY;
                                        offset75 = posZ;
                                        offset78 = posY;
                                        offset79 = posX;
                                        offset80 = maxZ;
                                    } else {
                                        int index19 = !Double.isNaN(posX) && !Double.isNaN(0.0) ? (posX > 0.0 ? 1 : (posX == 0.0 ? 0 : -1)) : 1;
                                        index = index16;
                                        if (index19 < 0) {
                                            index18 = (((1) != 0) ? 1 : 0);
                                            motionY = motionZ;
                                            maxZ = posX;
                                            motionZ = minX;
                                            posX = minY;
                                            minX = minZ;
                                            minY = maxX;
                                            value2 = scaleFactor;
                                            minZ = maxY;
                                            maxX = posZ;
                                            maxY = posY;
                                            posZ = motionX;
                                            posY = posX;
                                            motionX = maxZ;
                                            completed14 = true;
                                        }
                                        if (!completed14) {
                                            motionX = posX;
                                            posY = minX;
                                            maxY = minY;
                                            offset77 = maxX;
                                            index18 = index11;
                                            value3 = scaleFactor;
                                            offset76 = maxY;
                                            offset75 = posZ;
                                            offset78 = posY;
                                            offset79 = posX;
                                            offset80 = maxZ;
                                        }
                                    }
                                    if (!completed14) {
                                        motionY = motionZ;
                                        maxZ = motionX;
                                        motionZ = posY;
                                        posX = maxY;
                                        minX = minZ;
                                        minY = offset77;
                                        index11 = index18;
                                        value2 = value3;
                                        minZ = offset76;
                                        maxX = offset75;
                                        maxY = offset78;
                                        posY = offset79;
                                        motionX = offset80;
                                        index18 = (((0) != 0) ? 1 : 0);
                                    }


                                    int int152 = MathHelper.ceiling_double_int(motionY);
                                    offset77 = maxZ - 0.2F;
                                    index6 = MathHelper.ceiling_double_int(offset77);
                                    index7 = MathHelper.ceiling_double_int(motionZ);
                                    BlockPos blockPos3 = new BlockPos(int152, index6, index7);
                                    Block block3 = minecraft.theWorld.getBlockState(blockPos3).getBlock();
                                    index15 = index11;
                                    minX = maxX;
                                    Block block4 = block3;
                                    Material coreItemEntry = block3.getMaterial();
                                    Material coreItemEntry2 = Material.coreItemEntry;
                                    int index20 = 0;
                                    int index21 = 0;
                                    AxisAlignedBB boundingBox23 = null;
                                    if (coreItemEntry != coreItemEntry2) {
                                        motionZ = motionY;
                                        motionX = posX;
                                        posY = minX;
                                        posZ = minY;
                                        boundingBox23 = boundingBox13;
                                        index20 = index;
                                        value3 = value2;
                                        offset77 = minZ;
                                        posX = maxY;
                                        minY = posZ;
                                        offset75 = posY;
                                        offset79 = motionX;
                                        index21 = index18;
                                    } else {
                                        boolean completed15 = false;
                                        WorldClient world14 = minecraft.theWorld;
                                        BlockPos blockPos15 = blockPos3.down();
                                        Block block5 = world14.getBlockState(blockPos15).getBlock();
                                        BlockPos blockPos4 = blockPos3;
                                        motionZ = motionY;
                                        motionX = posX;
                                        posY = minX;
                                        posZ = minY;
                                        index20 = index;
                                        posX = maxY;
                                        state31 = block5 instanceof BlockAcaciaFence;
                                        Block block6 = null;
                                        if (state31) {
                                            boundingBox23 = boundingBox13;
                                            index15 = index11;
                                            maxX = minZ;
                                            offset77 = maxY;
                                            offset76 = posZ;
                                            offset75 = posY;
                                            index21 = index18;
                                            block6 = block5;
                                        } else {
                                            state31 = block5 instanceof BlockCobblestoneWall;
                                            index15 = index11;
                                            blockPos4 = blockPos3;
                                            boundingBox23 = boundingBox13;
                                            index20 = index;
                                            maxX = minZ;
                                            if (state31) {
                                                offset77 = maxY;
                                                offset76 = posZ;
                                                offset75 = posY;
                                                index21 = index18;
                                                block6 = block5;
                                            } else {
                                                state31 = block5 instanceof BlockAcaciaFenceGate;
                                                minX = maxX;
                                                minY = posZ;
                                                blockPos4 = blockPos3;
                                                block6 = block5;
                                                if (!state31) {
                                                    boundingBox23 = boundingBox13;
                                                    value3 = value2;
                                                    offset77 = minZ;
                                                    offset75 = posY;
                                                    offset79 = motionX;
                                                    index21 = index18;
                                                    block4 = block3;
                                                    completed15 = true;
                                                }
                                                if (!completed15) {
                                                    boundingBox23 = boundingBox13;
                                                    offset77 = maxY;
                                                    offset76 = posZ;
                                                    offset75 = posY;
                                                    index21 = index18;
                                                }
                                            }
                                        }
                                        if (!completed15) {
                                            blockPos4.down();
                                            value3 = value2;
                                            offset77 = maxX;
                                            posX = offset77;
                                            minY = offset76;
                                            offset79 = motionX;
                                            block4 = block6;
                                        }

                                    }

                                    index11 = !Double.isNaN(minY) && !Double.isNaN(offset77) ? (minY > offset77 ? 1 : (minY == offset77 ? 0 : -1)) : -1;
                                    axisAlignedBB = boundingBox23;
                                    posZ = motionX;
                                    int index22 = 0;
                                    if (index11 == 0) {
                                        minX = posX;
                                        maxY = offset75;
                                        index22 = index21;
                                    } else {
                                        posZ = 0.0;
                                        minX = posX;
                                        maxY = offset75;
                                        index22 = index21;
                                    }

                                    index21 = !Double.isNaN(offset79) && !Double.isNaN(minX) ? (offset79 > minX ? 1 : (offset79 == minX ? 0 : -1)) : -1;
                                    maxX = posZ;
                                    int index23 = 0;
                                    if (index21 == 0) {
                                        index23 = index22;
                                    } else {
                                        maxX = 0.0;
                                        index23 = index22;
                                    }

                                    index22 = !Double.isNaN(maxY) && !Double.isNaN(minX) ? (maxY > minX ? 1 : (maxY == minX ? 0 : -1)) : -1;
                                    value2 = value3;
                                    motionY = motionZ;
                                    index11 = index15;
                                    int index24 = 0;
                                    if (index22 == 0) {
                                        motionX = posZ;
                                        maxY = posY;
                                        index24 = index23;
                                    } else {
                                        state31 = block4 instanceof BlockSlime;
                                        if (!state31) {
                                            motionX = posZ;
                                            maxY = 0.0;
                                            index11 = index15;
                                            value2 = value3;
                                            index24 = index23;
                                        } else {
                                            state31 = player.isSprinting();
                                            if (!state31) {
                                                index11 = !Double.isNaN(posY) && !Double.isNaN(0.0) ? (posY > 0.0 ? 1 : (posY == 0.0 ? 0 : -1)) : 1;
                                                value2 = value3;
                                                motionY = motionZ;
                                                index11 = index15;
                                                motionX = posZ;
                                                index24 = index23;
                                                if (index11 >= 0) {
                                                    maxY = posY;
                                                } else {
                                                    double offset92 = -posY;
                                                    maxY = offset92;
                                                }
                                            } else {
                                                motionX = posZ;
                                                value2 = value3;
                                                index24 = index23;
                                                motionY = 0.0;
                                                index11 = index15;
                                                maxY = motionY;
                                                motionY = motionZ;
                                            }
                                        }
                                    }

                                    boolean completed17 = false;
                                    World world15 = player.worldObj;
                                    state31 = world15.state;
                                    posZ = maxY;
                                    index24 = index11;
                                    posY = maxX;
                                    enabled5 = ((index24) != 0);
                                    boolean completed16 = false;
                                    if (state31) {
                                        index23 = index20;
                                        int index25 = index24;
                                        World world16 = player.worldObj;
                                        index22 = (int)motionZ;
                                        index11 = (int)motionY;
                                        BlockPos blockPos5 = new BlockPos(index22, 0, index11);
                                        state31 = world16.checkBlockPos12(blockPos5);
                                        axisAlignedBB = boundingBox23;
                                        maxY = motionZ;
                                        motionZ = maxZ;
                                        if (!state31) {
                                            posZ = motionY;
                                            posY = maxX;
                                            index23 = index20;
                                            index24 = index11;
                                        } else {
                                            World world17 = player.worldObj;
                                            index22 = (int)motionZ;
                                            index25 = (int)motionY;
                                            BlockPos blockPos6 = new BlockPos(index22, 0, index25);
                                            AlertNotificationSound loggerEntry = world17.getLoggerEntry(blockPos6);
                                            state31 = loggerEntry.canExecute2();
                                            posZ = maxY;
                                            posY = maxX;
                                            enabled5 = ((index24) != 0);
                                            index24 = index11;
                                            if (state31) {
                                                completed16 = true;
                                            }
                                            if (!completed16) {
                                                maxY = motionZ;
                                                motionZ = maxZ;
                                                posZ = motionY;
                                                index25 = index24;
                                            }
                                        }
                                        if (!completed16) {
                                            maxZ = motionZ;
                                            motionZ = maxY;
                                            index20 = index23;
                                            index23 = !Double.isNaN(motionZ) && !Double.isNaN(0.0) ? (motionZ > 0.0 ? 1 : (motionZ == 0.0 ? 0 : -1)) : -1;
                                            if (index23 <= 0) {
                                                motionY = posZ;
                                                posZ = 0.0;
                                                maxY = motionX;
                                                motionX = posZ;
                                                enabled6 = ((index25) != 0);
                                            } else {
                                                double offset93 = -0.1;
                                                motionY = posZ;
                                                maxY = motionX;
                                                index20 = index23;
                                                enabled6 = ((index25) != 0);
                                                motionX = offset93;
                                            }
                                            completed17 = true;
                                        }
                                    }
                                    if (!completed17) {
                                        posZ -= 0.08;
                                        maxY = motionX;
                                        enabled6 = enabled5;
                                        motionX = posZ;
                                    }


                                    posZ = motionX * 0.98F;
                                    motionX = (double)value2;
                                    motionX = maxY * motionX;
                                    maxY = (double)value2;
                                    posY *= maxY;
                                    if (enabled6) {
                                        return false;
                                    }

                                    EntityPlayerSP player2 = minecraft.thePlayer;
                                    motionY = player2.posY;
                                    motionY -= maxZ;
                                    maxX = Math.abs(motionY);
                                    NumberSetting settingMaxDistance2 = this.settingMaxDistance;
                                    motionY = settingMaxDistance2.getValue();
                                    index11 = !Double.isNaN(maxX) && !Double.isNaN(motionY) ? (maxX > motionY ? 1 : (maxX == motionY ? 0 : -1)) : -1;
                                    boolean enabled11 = enabled6;
                                    if (index11 <= 0) {
                                        motionY = maxZ;
                                        maxY = motionY;
                                        maxX = motionX;
                                        minZ = posY;
                                        index11 = index20;
                                        index23 = index24;
                                        minY = posZ;
                                    } else {
                                        BooleanSetting settingBugUpOnlyIfFallingIntoVoid2 = this.settingBugUpOnlyIfFallingIntoVoid;
                                        state31 = settingBugUpOnlyIfFallingIntoVoid2.isEnabled();
                                        if (!state31) {
                                            return true;
                                        }

                                        maxY = motionY;
                                        maxX = motionX;
                                        enabled11 = enabled6;
                                        motionY = maxZ;
                                        minZ = posY;
                                        index11 = index20;
                                        index23 = index24;
                                        minY = posZ;
                                    }

                                    index22 = !Double.isNaN(motionY) && !Double.isNaN(0.0) ? (motionY > 0.0 ? 1 : (motionY == 0.0 ? 0 : -1)) : 1;
                                    posY = motionY;
                                    if (index22 < 0) {
                                        return true;
                                    }

                                    maxX = maxY;
                                    index2 = (enabled11 ? 1 : 0);
                                    posZ = minY;
                                    index11++;
                                    maxZ = motionZ;
                                    minY = maxX;
                                    motionZ = minZ;
                                    indexOffset18 = index23;
                                    continue outerLoop2;
                                }

                                iterator = (Iterator)iterator4.next();
                                double coordinate4 = ((AxisAlignedBB)iterator).getCoordinate(boundingBox12, offset77);
                                offset77 = coordinate4;
                            }
                        }

                        boundingBox8 = (AxisAlignedBB)iterator3.next();
                        offset75 = boundingBox8.getCoordinate2(boundingBox11, posZ);
                        posZ = offset75;
                    }
                }

                Object object = iterator.next();
                object = (AxisAlignedBB)object;
                minY = ((AxisAlignedBB)object).getCoordinate3(boundingBox6, minY);
            }
        }

        return true;
    }

    private boolean isBackFlagDistVisible() {
        return this.settingMode.getSelected().equals("BlinkOutgoing");
    }

    private boolean canExecute6() {
        return minecraft.thePlayer == null ? false : ClientGlobals.moduleManager.longJump.isToggled();
    }
}
