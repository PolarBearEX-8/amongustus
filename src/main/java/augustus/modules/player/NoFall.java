package augustus.modules.player;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventBlockCollision;
import client.event.EventClick;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MotionListener;
import client.event.MoveEntityListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.CurveManager;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.RayTraceUtils;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class NoFall
extends Module
implements ClickListener,
TickListener,
PacketListener,
MotionListener,
BlockCollisionListener,
MoveEntityListener,
UpdateStateListener,
NetworkPacketListener {
    public ModeSetting settingMode;
    public NumberSetting settingCollisionFallDistance;
    public NumberSetting settingMinFallDist;
    public NumberSetting settingMinLegitFallDist;
    public SettingGroup settingRotations;
    public NumberSetting settingDelay;
    public BooleanSetting settingRandomizeYaw;
    public ModeSetting settingBlinkMode;
    public NumberSetting settingBlinkStartFallDistance;
    public SimulatedPlayer simulatedPlayer;
    public CurveManager curveManager;
    public BlockPos blockPos;
    public int tickDelay1;
    public double reachDistance;
    public int tickDelay;
    public BlockPos blockPos2;
    public int keyCode2;

    public NoFall() {
        super("NoFall", "KeinFallen", new Color(222, 80, 95, 255), Category.PLAYER);
        String[] textArray = new String[9];
        textArray[0] = "OnGround1";
        textArray[1] = "OnGround2";
        textArray[2] = "NoGround";
        textArray[3] = "Stop";
        textArray[4] = "Legit";
        textArray[5] = "Verus";
        textArray[6] = "AAC3";
        textArray[7] = "Collision";
        textArray[8] = "Vulcan";
        this.settingMode = new ModeSetting(this, "Mode", "OnGround", textArray);
        this.settingCollisionFallDistance = new NumberSetting(this, "CollisionFallDistance", 2.0, 0.0, 6.0, 2).getNumberSetting("FallDistance").getNumberSetting(() -> this.isCollisionFallDistanceVisible());
        this.settingMinFallDist = new NumberSetting(this, "MinFallDist", 2.0, 1.0, 24.0, 0).getNumberSetting(() -> this.isMinFallDistVisible());
        this.settingMinLegitFallDist = new NumberSetting(this, "MinLegitFallDist", 6.0, 2.0, 24.0, 1).getNumberSetting("MinFallDist").getNumberSetting(() -> this.isMinLegitFallDistVisible());
        this.settingRotations = new SettingGroup(this, "Rotations", true).getSettingGroup(() -> this.isRotationsVisible());
        this.settingDelay = new NumberSetting(this, "Delay", 110.0, 0.0, 2000.0, 0).getNumberSetting(() -> this.isDelayVisible2());
        this.settingRandomizeYaw = (BooleanSetting)new BooleanSetting(this, "RandomizeYaw", true).getSetting(() -> this.isDelayVisible());
        String[] textArray2 = new String[4];
        textArray2[0] = "None";
        textArray2[1] = "OnlyIncoming";
        textArray2[2] = "OnlyOutgoing";
        textArray2[3] = "Both";
        this.settingBlinkMode = new ModeSetting(this, "BlinkMode", "None", textArray2);
        this.settingBlinkStartFallDistance = new NumberSetting(this, "BlinkStartFallDistance", 3.0, 1.0, 6.0, 2).getNumberSetting(() -> this.isBlinkStartFallDistanceVisible());
        this.simulatedPlayer = new SimulatedPlayer();
        CurveManager curveManager2 = new CurveManager(this, this.settingRotations);
        this.curveManager = curveManager2;
        this.blockPos = (BlockPos)null;
        this.reachDistance = 0.0;
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(26);
        this.registerEvent(34);
        this.registerEvent(3);
        this.registerEvent(20);
        this.registerEvent(43);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.simulatedPlayer = new SimulatedPlayer();
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (minecraft.thePlayer.ticksExisted != 0) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (minecraft.thePlayer != null && minecraft.theWorld != null) {
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "NoGround":
                boolean enabled = packet2 instanceof C03PacketPlayer;
                if (!enabled) {
                        return;
                    }
                ((C03PacketPlayer)packet2).setOnGround(false);
                minecraft.thePlayer.fallDistance = -1.0F;
                return;
            case "Verus":
                if ((byte)((packet2 instanceof C03PacketPlayer) ? 1 : 0) == 0) {
                        return;
                    } else {
                        if (minecraft.thePlayer.fallDistance <= 2.0F) {
                            return;
                        } else {
                            if ((byte)((packet2 instanceof C06PacketPlayerPosLook) ? 1 : 0) == 0
                            && (byte)((packet2 instanceof C05PacketPlayerLook) ? 1 : 0) == 0
                            && (byte)((packet2 instanceof C04PacketPlayerPosition) ? 1 : 0) == 0) {
                                ((C03PacketPlayer)packet2).setOnGround(true);
                                minecraft.thePlayer.fallDistance = 0.0F;
                                return;
                            }

                            packet.cancel();
                            double offset = minecraft.thePlayer.posY + 1.0;
                            minecraft.thePlayer
                            .netHandlerPlayClient
                            .handlePacket2(new C04PacketPlayerPosition(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ, true));
                            minecraft.thePlayer.fallDistance = 0.0F;
                            return;
                        }
                    }
            case "OnGround2":
                if (minecraft.thePlayer.fallDistance > 3.0F && packet2 instanceof C03PacketPlayer) {
                        ((C03PacketPlayer)packet2).setOnGround(true);
                    }
                return;
            case "Vulcan":
                if (!(packet2 instanceof C03PacketPlayer)) {
                        return;
                    } else {
                        C03PacketPlayer c03PacketPlayer = (C03PacketPlayer)packet2;
                        if (minecraft.thePlayer.fallDistance > 3.0F) {
                            c03PacketPlayer.setOnGround(true);
                            minecraft.thePlayer.fallDistance = 0.0F;
                            this.keyCode2 = 1;
                        }

                        if (this.keyCode2 != 1) {
                            return;
                        }

                        minecraft.thePlayer.motionY = -0.011;
                        this.keyCode2 = 0;
                        return;
                    }
        }
        }
    }

    @Override
    public void onClick(EventClick event) {
        this.tickDelay1++;
        if (this.blockPos2 != null && ClientGlobals.rotationManager.checkModule(this) && minecraft.objectMouseOver != null) {
            if (this.tickDelay > -1 && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                event.updateInt2(1);
                this.tickDelay1 = 0;
            }
        }
    }

    @Override
    public void onBlockCollision(EventBlockCollision event) {
        if (this.settingMode.getSelected().equals("Collision")) {
            double fallDistance = (double)minecraft.thePlayer.fallDistance;
            if (fallDistance >= this.settingCollisionFallDistance.getValue()) {
                return;
            }

            BlockPos blockPos3 = event.getBlockPos();
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
            (double)blockPos3.getX(),
            (double)blockPos3.getY(),
            (double)blockPos3.getZ(),
            (double)(blockPos3.getX() + 1),
            (double)(blockPos3.getY() + 1),
            (double)(blockPos3.getZ() + 1)
            );
            event.setBoundingBox(axisAlignedBB);
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (minecraft.thePlayer.ticksExisted != 0) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if ((this.settingMode.getSelected().equals("Collision") ? 1 : 0) <= 0) {
            this.reachDistance = minecraft.thePlayer.posY + event.getCoordOffset1();
        }
    }

    @Override
    public void onPostMotion() {
        this.tickDelay = -1;
        if (minecraft.thePlayer != null && this.settingMode.getSelected().equals("Legit")) {
            boolean enabled = false;
            IBlockState iBlockState = minecraft.theWorld.getBlockState(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ));
            if (iBlockState.getBlock() instanceof BlockLiquid) {
                enabled = (Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0;
            }

            double fallDistance = (double)minecraft.thePlayer.fallDistance;
            if ((fallDistance > this.settingMinLegitFallDist.getValue() || minecraft.thePlayer.isInWater() && enabled)
            && this.blockPos2 != null
            && this.getInt2() != -1
            && minecraft.objectMouseOver != null) {
                if (minecraft.thePlayer.fallDistance == 0.0F
                && enabled
                && this.blockPos != null
                && minecraft.objectMouseOver.getBlockPos() != null) {
                    if (this.blockPos.checkBlockPos2(minecraft.objectMouseOver.getBlockPos())) {
                        this.tickDelay = this.getInt2();
                    }
                }

                double fallDistance2 = (double)minecraft.thePlayer.fallDistance;
                if (fallDistance2 > this.settingMinLegitFallDist.getValue() && !enabled) {
                    Rotation rotation2 = ClientGlobals.rotationManager.getRotation2();
                    if (RayTraceUtils.getMovingObjectPosition(1.0F, rotation2, 10.0, 10.0, 0.0).typeOfHit
                    == MovingObjectType.BLOCK) {
                        this.tickDelay = this.getInt2();
                    }
                }
            }

            if (this.tickDelay != -1) {
                int tickDelay2 = this.tickDelay;
                SlotManager.applyModule3(this, 1, tickDelay2);
            }
        }

        BlockPos blockPos3;
        if (minecraft.objectMouseOver == null) {
            blockPos3 = null;
        } else {
            blockPos3 = minecraft.objectMouseOver.getBlockPos();
        }

        this.blockPos = blockPos3;
    }

    @Override
    public void onRunTick() {
        if (minecraft.thePlayer != null) {
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "OnGround1":
                double fallDistance = (double)minecraft.thePlayer.fallDistance;
                if (fallDistance > this.settingMinFallDist.getValue()) {
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C03PacketPlayer(true));
                    }
                break;
            case "Stop":
                if (minecraft.thePlayer.fallDistance > 2.0F) {
                        minecraft.thePlayer.motionY = 0.0;
                        minecraft.thePlayer.onGround = true;
                        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C03PacketPlayer(true));
                        minecraft.thePlayer.fallDistance = 0.0F;
                        return;
                    }
                break;
            case "AAC3":
                if (!minecraft.isFullScreen() && minecraft.thePlayer.fallDistance > 2.0F) {
                        minecraft.thePlayer
                        .netHandlerPlayClient
                        .handlePacket(new C04PacketPlayerPosition(minecraft.thePlayer.posX, Double.NaN, minecraft.thePlayer.posZ, false));
                        minecraft.thePlayer.fallDistance = -99999.0F;
                    }
        }
        }
    }

    @Override
    public void onPreTick() {
        this.settingMode.getSelected().equals("Legit");
        if ((byte)((
        minecraft.theWorld.getBlockState(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ)).getBlock() instanceof BlockLiquid
        ) ? 1 : 0)
        > 0
        || minecraft.thePlayer.isInWater()) {
            CurveManager curveManager2 = this.curveManager;
            float rotationYaw = minecraft.thePlayer.rotationYaw;
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            Rotation rotation6 = curveManager2.getRotation6(rotationYaw, 90.0F, rotation3);
            ClientGlobals.rotationManager.applyRotation2(rotation6, 4, 1898960900, this);
        }
    }

    private boolean isDelayVisible() {
        return this.settingMode.getSelected().equals("Legit");
    }

    private boolean isCollisionFallDistanceVisible() {
        return this.settingMode.getSelected().equals("Collision");
    }

    private int getInt2() {
        ArrayList arrayList = new ArrayList();

        for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack != null) {
                Item item = itemStack.getItem();
                Item item2 = Item.processAction(326);
                if (item != item2 || minecraft.thePlayer.isInWater()) {
                    Item item3 = itemStack.getItem();
                    Item item4 = Item.processAction(325);
                    if (item3 != item4
                    || minecraft.thePlayer.fallDistance != 0.0F
                    || !minecraft.thePlayer.isInWater()) {
                        continue;
                    }
                }

                arrayList.add(i - 36);
            }
        }

        return (arrayList.isEmpty() ? 1 : 0);
    }

    private BlockPos getBlockPos() {
        double posX = minecraft.thePlayer.posX;
        double posY = minecraft.thePlayer.posY;
        double posZ = minecraft.thePlayer.posZ;
        double motionX = minecraft.thePlayer.motionX;
        double motionY = minecraft.thePlayer.motionY;
        double motionZ = minecraft.thePlayer.motionZ;
        boolean state31 = minecraft.thePlayer.state31;
        int index3 = 0;
        double[] doubleArray2 = CombatUtil.getDoubleArray2(
        minecraft.thePlayer.secondaryFloatEntry.moveForward,
        minecraft.thePlayer.secondaryFloatEntry.moveStrafe,
        motionX,
        
        motionY,
        motionZ,
        posX,
        posY,
        posZ,
        state31
        );
        double offset = doubleArray2[0];
        double offset2 = doubleArray2[1];
        double offset3 = doubleArray2[2];
        double offset4 = doubleArray2[3];
        offset4 = doubleArray2[4];
        offset4 = doubleArray2[5];
        BlockPos blockPos3 = new BlockPos(offset, offset2, offset3);
        if (!(minecraft.theWorld.getBlockState(blockPos3).getBlock() instanceof BlockLiquid)) {
            index3++;
            return null;
        } else {
            return blockPos3;
        }
    }

    private static boolean checkBlockPos(BlockPos blockPos3) {
        IBlockState iBlockState = minecraft.theWorld.getBlockState(blockPos3);
        return iBlockState.getBlock().processAction(minecraft.theWorld, iBlockState, blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()) != null;
    }

    private boolean isMinFallDistVisible() {
        return this.settingMode.getSelected().equals("OnGround1");
    }

    private boolean isDelayVisible2() {
        return this.settingMode.getSelected().equals("Legit");
    }

    private boolean isRotationsVisible() {
        return this.settingMode.getSelected().equals("Legit");
    }

    private boolean isBlinkStartFallDistanceVisible() {
        return this.settingBlinkMode.getSelected().equals("None");
    }

    private boolean isMinLegitFallDistVisible() {
        return this.settingMode.getSelected().equals("Legit");
    }
}
