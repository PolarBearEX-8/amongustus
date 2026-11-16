package augustus.modules.world;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MotionListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.ActionModule;
import client.module.Category;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.CombatRayTrace;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.PlayerDiggingActionType;
import client.util.PlayerUtil;
import client.util.PredictedHitPoint;
import client.util.RayTraceUtils;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.TimeHelper;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Fucker extends ActionModule implements ClickListener, NetworkPacketListener, TickListener, PacketListener, MotionListener {
    public ModeSetting settingBlock;
    public ModeSetting settingAction;
    public NumberSetting settingDelay;
    public NumberSetting settingAimRange;
    public NumberSetting settingRange;
    public BooleanSetting settingBreakOnlyFreeBeds;
    public BooleanSetting settingThroughWall;
    public BooleanSetting settingSurrounding;
    public BooleanSetting settingInstant;
    public BooleanSetting settingFriendMyBlock;
    public NumberSetting settingCustomID;
    public BooleanSetting settingBedBlink;
    public NumberSetting settingBedBlinkTime;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public BlockPos blockPos;
    public EnumFacing enumFacing;
    public MovingObjectPosition movingObjectPosition;
    public boolean enabled2;

    public Fucker() {
        super("Fucker", "Ficker", new Color(161, 17, 51), Category.WORLD);
        String[] textArray = new String[3];
        textArray[0] = "Bed";
        textArray[1] = "Cake";
        textArray[2] = "Custom";
        this.settingBlock = new ModeSetting(this, "Block", "Bed", textArray);
        String[] textArray2 = new String[3];
        textArray2[0] = "Break";
        textArray2[1] = "Click";
        textArray2[2] = "Use";
        this.settingAction = new ModeSetting(this, "Action", "Break", textArray2);
        this.settingDelay = new NumberSetting(this, "Delay", 500.0, 0.0, 1000.0, 0);
        this.settingAimRange = new NumberSetting(this, "AimRange", 5.5, 0.0, 6.0, 1);
        this.settingRange = new NumberSetting(this, "Range", 4.5, 0.0, 6.0, 1);
        this.settingBreakOnlyFreeBeds = new BooleanSetting(this, "Break only free beds", false);
        this.settingThroughWall = (BooleanSetting)new BooleanSetting(this, "ThroughWall", true).getSetting(() -> this.isRangeVisible2());
        this.settingSurrounding = (BooleanSetting)new BooleanSetting(this, "Surrounding", false).getSetting(() -> this.isRangeVisible());
        this.settingInstant = (BooleanSetting)new BooleanSetting(this, "Instant", true).getSetting(() -> this.isRangeVisible3());
        this.settingFriendMyBlock = new BooleanSetting(this, "FriendMyBlock", true);
        this.settingCustomID = new NumberSetting(this, "CustomID", 26.0, 0.0, 400.0, 0).getNumberSetting(() -> canExecute5());
        this.settingBedBlink = new BooleanSetting(this, "BedBlink", false);
        this.settingBedBlinkTime = new NumberSetting(this, "BedBlinkTime", 10000.0, 0.0, 20000.0, 0).getNumberSetting(() -> this.settingBedBlink.isEnabled());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvent(7);
        this.registerEvent(25);
        this.registerEvent(40);
        this.registerEvent(26);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onPreDisable() {
        super.onPreDisable();
        if (minecraft.thePlayer != null) {
            this.blockPos = null;
            this.enumFacing = null;
            this.timer2.reset();
            this.enabled2 = false;
            this.movingObjectPosition = null;
        }
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        boolean completed = false;
        if (this.blockPos != null && minecraft.thePlayer != null && !minecraft.thePlayer.isDead && this.settingBedBlink.isEnabled()) {
            if (minecraft.thePlayer.getPositionVec10() != null) {
                double offset = minecraft.thePlayer
                .getPositionVec10()
                .getCoordinate4(
                new Vec3(
                (double)minecraft.objectMouseOver.getBlockPos().getX(),
                (double)minecraft.objectMouseOver.getBlockPos().getY(),
                (double)minecraft.objectMouseOver.getBlockPos().getZ()
                )
                )
                - 20.0;
                if (offset <= 0.0 && this.settingFriendMyBlock.isEnabled()) {
                    completed = true;
                }
            }
            if (!completed) {
                double offset2 = minecraft.thePlayer
                .getVec35(1.0F)
                .getCoordinate4(
                new Vec3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5)
                )
                - 4.5;
                if (offset2 < 0.0) {
                    TimeHelper timer22 = this.timer2;
                    long time2 = (long)this.settingBedBlinkTime.getValue();
                    if (!timer22.hasReached(time2) && this.timer3.hasReached(500L)) {
                        packet.setCancelled(true);
                        return;
                    }
                }
            }
        }


        this.timer2.reset();
        packet.setCancelled(false);
    }

    @Override
    public void onClick(EventClick event) {
        boolean enabled;
        if (this.blockPos != null) {
            this.expanded2 = ClientGlobals.rotationManager.checkModule(this);
            enabled = ClientGlobals.moduleManager.killAura.isToggled();
        } else {
            this.expanded2 = false;
            enabled = ClientGlobals.moduleManager.killAura.isToggled();
        }

        if ((!enabled || ClientGlobals.moduleManager.killAura.target == null) && !BlockCache.canExecute() && this.blockPos != null) {
            event.setState3(true);
            event.setState11(false);
            event.setState1(false);
            if (this.checkCondition()) {
                BlockPos blockPos2 = this.blockPos;
                float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
                float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
                double value = this.settingRange.getValue();
                MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition(blockPos2, yaw, pitch, value);
                if (movingObjectPosition2 == null) {
                    return;
                }

                this.movingObjectPosition = movingObjectPosition2;
                if (movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                    String selected = this.settingAction.getSelected();
                    switch (selected) {
                        case "Break":
                            if (this.settingInstant.isEnabled()) {
                                EnumFacing sideHit = minecraft.objectMouseOver.sideHit;
                                BlockPos blockPos3 = this.blockPos;
                                this.applyBlockPos(blockPos3, sideHit);
                            } else {
                                if (!minecraft.playerController.state) {
                                    event.updateInt(1);
                                    event.setRunnable3(() -> this.applyMovingObjectPosition3(movingObjectPosition2));
                                }

                                event.setRunnable(() -> this.applyMovingObjectPosition2(movingObjectPosition2));
                                this.enumFacing = movingObjectPosition2.sideHit;
                            }
                            break;
                        case "Use":
                            TimeHelper timer4 = this.timer;
                            long time = (long)this.settingDelay.getValue();
                            if (timer4.hasReached(time)
                            && minecraft.theWorld.getBlockState(this.blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                                event.updateInt2(1);
                                event.setRunnable2(() -> this.applyMovingObjectPosition(movingObjectPosition2));
                            }
                            break;
                        default:
                            TimeHelper timer5 = this.timer;
                            long time2 = (long)this.settingDelay.getValue();
                            if (timer5.hasReached(time2)) {
                                event.updateInt(1);
                                event.setRunnable3(() -> this.applyMovingObjectPosition4(movingObjectPosition2));
                                this.timer.reset();
                            }
                    }
                }
            } else {
                if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && !minecraft.objectMouseOver.getBlockPos().equals(this.blockPos)) {
                    if (this.settingInstant.isEnabled()) {
                        BlockPos blockPos4 = minecraft.objectMouseOver.getBlockPos();
                        EnumFacing sideHit2 = minecraft.objectMouseOver.sideHit;
                        this.applyBlockPos(blockPos4, sideHit2);
                    } else {
                        event.setState1(true);
                    }

                    return;
                }

                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                    String selected2 = this.settingAction.getSelected();
                    switch (selected2) {
                        case "Break":
                            if (this.settingInstant.isEnabled()) {
                                BlockPos blockPos5 = minecraft.objectMouseOver.getBlockPos();
                                EnumFacing sideHit3 = minecraft.objectMouseOver.sideHit;
                                this.applyBlockPos(blockPos5, sideHit3);
                            } else {
                                this.enumFacing = minecraft.objectMouseOver.sideHit;
                                event.setState1(true);
                            }
                            break;
                        case "Use":
                            TimeHelper timer6 = this.timer;
                            long time3 = (long)this.settingDelay.getValue();
                            if (timer6.hasReached(time3)) {
                                event.updateInt2(1);
                                this.timer.reset();
                            }
                            break;
                        default:
                            TimeHelper timer7 = this.timer;
                            long time4 = (long)this.settingDelay.getValue();
                            if (timer7.hasReached(time4)) {
                                event.updateInt(1);
                                this.timer.reset();
                            }
                    }
                }
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C07PacketPlayerDigging) {
            C07PacketPlayerDigging c07PacketPlayerDigging = (C07PacketPlayerDigging)packet2;
            if (c07PacketPlayerDigging.getPlayerDiggingActionType() != PlayerDiggingActionType.STOP_DESTROY_BLOCK) {
                if (!minecraft.playerController.processAction2()) {
                    return;
                }

                if (c07PacketPlayerDigging.getPlayerDiggingActionType() != PlayerDiggingActionType.START_DESTROY_BLOCK) {
                    return;
                }
            }

            BlockPos blockPos2 = c07PacketPlayerDigging.getBlockPos();
            if (this.settingSurrounding.isEnabled() && !this.enabled2) {
                this.enabled2 = true;
            }

            if (blockPos2 != null) {
                int int3 = this.getInt3();
                if (!this.checkBlockPos(blockPos2, int3)) {
                    return;
                }

                this.timer3.reset();
            }
        }
    }

    @Override
    public void onPostMotion() {
        if (this.blockPos != null) {
            if (ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null) {
                return;
            }

            if (BlockCache.canExecute()) {
                return;
            }

            int int2 = this.getInt2();
            if (int2 == -1) {
                return;
            }

            SlotManager.applyModule3(this, 0, int2);
        }
    }

    @Override
    public void onPreTick() {
        if (this.settingBreakOnlyFreeBeds.isEnabled()) {
            this.settingThroughWall.setState3(false);
        }

        String selected = this.settingBlock.getSelected();
        this.applyString4(selected);
        if ((!ClientGlobals.moduleManager.killAura.isToggled() || ClientGlobals.moduleManager.killAura.target == null)
        && !BlockCache.canExecute()) {
            Vec3 vec = this.getVec3();
            this.blockPos = vec == null ? null : new BlockPos(vec);
        } else {
            this.blockPos = null;
        }

        if (this.blockPos != null) {
            BlockPos blockPos2 = this.blockPos;
            AxisAlignedBB axisAlignedBB = this.getAxisAlignedBB(blockPos2);
            Vec3 vec2 = minecraft.thePlayer.getVec35(1.0F);
            double double7 = MathHelper.getDouble7(vec2.xCoord, axisAlignedBB.minX + 0.1, axisAlignedBB.maxX - 0.1);
            double double72 = MathHelper.getDouble7(vec2.yCoord, axisAlignedBB.minY + 0.1, axisAlignedBB.maxY - 0.1);
            double double73 = MathHelper.getDouble7(vec2.zCoord, axisAlignedBB.minZ + 0.1, axisAlignedBB.maxZ - 0.1);
            Rotation rotation4 = this.getRotation4(double7, double72, double73);
            MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(1.0F, rotation4);
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            rotation4.getRotation(rotation3);
            boolean enabled = this.settingBreakOnlyFreeBeds.isEnabled();
            if (this.settingBreakOnlyFreeBeds.isEnabled()) {
                Rotation rotation32 = ClientGlobals.rotationManager.getRotation3();
                Rotation rotation2 = SimulatedPlayer.getRotation2(double7, double72, double73, rotation32, 180.0F, 180.0F, false);
                MovingObjectPosition movingObjectPosition3 = CombatRayTrace.getMovingObjectPosition(1.0F, rotation2);
                if (movingObjectPosition3 != null && movingObjectPosition3.typeOfHit == MovingObjectType.BLOCK) {
                    BlockPos blockPos3 = movingObjectPosition3.getBlockPos();
                    int int3 = this.getInt3();
                    if (this.checkBlockPos(blockPos3, int3)) {
                        enabled = false;
                    }
                }
            }

            if (!this.checkCondition() && movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                BlockPos blockPos4 = movingObjectPosition2.getBlockPos();
                int int32 = this.getInt3();
                if (!this.checkBlockPos(blockPos4, int32)) {
                    double offset = (double)this.blockPos.getX() + 0.5;
                    double offset2 = offset - minecraft.thePlayer.posX;
                    double offset3 = minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2();
                    double offset4 = (double)this.blockPos.getZ() + 0.5;
                    PredictedHitPoint tertiaryDoubleEntry = new PredictedHitPoint(offset2, offset3, offset4 - minecraft.thePlayer.posZ);
                    List list = PlayerUtil.getList(axisAlignedBB, tertiaryDoubleEntry);
                    Vec3 vec3 = this.getVec4(list);
                    if (this.settingBreakOnlyFreeBeds.isEnabled()) {
                        enabled = this.getVec3(list) == null;
                    }

                    if (vec3 != null) {
                        rotation4 = this.getRotation(vec3);
                    }
                }
            }

            if (enabled) {
                this.blockPos = null;
            } else {
                ClientGlobals.rotationManager.applyRotation2(rotation4, 3, 1898960900, this);
            }
        }
    }

    private Vec3 getVec3(BlockPos blockPos2, AxisAlignedBB boundingBox) {
        return this.getVec4(
        PlayerUtil.getList(
        
        
        boundingBox,
        new PredictedHitPoint(
        (double)blockPos2.getX() + 0.5 - minecraft.thePlayer.posX,
        minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2(),
        (double)blockPos2.getZ() + 0.5 - minecraft.thePlayer.posZ
        )
        )
        );
    }

    private void applyMovingObjectPosition(MovingObjectPosition hitResult) {
        ItemStack itemStack = minecraft.thePlayer.getItemStack();
        if (itemStack != null) {
            int animationsToGo = itemStack.animationsToGo;
            if (minecraft.playerController.onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, itemStack, this.blockPos, hitResult.sideHit, hitResult.hitVec)) {
                minecraft.thePlayer.respawnPlayer();
                this.timer.reset();
            }

            if (itemStack.animationsToGo == 0) {
                minecraft.thePlayer.inventoryPlayer.mainInventory[minecraft.thePlayer.inventoryPlayer.currentItem] = null;
            } else if (itemStack.animationsToGo != animationsToGo || minecraft.playerController.processAction2()) {
                minecraft.entityRenderer.itemRenderer.updateState();
            }
        } else {
            if (minecraft.playerController.onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, itemStack, this.blockPos, hitResult.sideHit, hitResult.hitVec)) {
                minecraft.thePlayer.respawnPlayer();
                this.timer.reset();
            }
        }
    }

    private static double getCoordinate(Vec3 vec) {
        return minecraft.thePlayer.getDouble4(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    private static double getCoordinate2(Vec3 vec) {
        return minecraft.thePlayer.getDouble4(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    private void applyMovingObjectPosition2(MovingObjectPosition hitResult) {
        boolean enabled = false;
        boolean completed = false;
        if (minecraft.currentScreen == null) {
            if (minecraft.inGameHasFocus) {
                enabled = true;
                completed = true;
            }
        }
        if (!completed) {
            enabled = false;
        }


        int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
        BlockPos blockPos2 = this.blockPos;
        minecraft.sendClickBlockToController(enabled, hitResult, blockPos2, indexOffset);
    }

    private Vec3 getVec3(List list) {
        Vec3 vec = null;
        double offset = 15.0;

        for (Object object : list) {
            PredictedHitPoint tertiaryDoubleEntry = (PredictedHitPoint)object;
            double coordOffset2 = tertiaryDoubleEntry.coordOffset2;
            double coordOffset1 = tertiaryDoubleEntry.coordOffset1;
            double coordOffset = tertiaryDoubleEntry.coordOffset;
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            Rotation rotation2 = SimulatedPlayer.getRotation2(coordOffset2, coordOffset1, coordOffset, rotation3, 180.0F, 180.0F, false);
            MovingObjectPosition movingObjectPosition2 = minecraft.thePlayer.getMovingObjectPosition((double)minecraft.playerController.getBlockReachDistance(), 1.0F, rotation2.yaw, rotation2.pitch);
            if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                BlockPos blockPos2 = movingObjectPosition2.getBlockPos();
                int int3 = this.getInt3();
                if (this.checkBlockPos(blockPos2, int3)) {
                    double offset2 = Math.pow(tertiaryDoubleEntry.coordOffset2 - minecraft.thePlayer.posX, 2.0);
                    double offset3 = offset2 + Math.pow(tertiaryDoubleEntry.coordOffset1 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2()), 2.0);
                    double offset4 = Math.sqrt(offset3 + Math.pow(tertiaryDoubleEntry.coordOffset - minecraft.thePlayer.posZ, 2.0));
                    if (offset4 <= offset) {
                        offset = offset4;
                        vec = new Vec3(tertiaryDoubleEntry.coordOffset2, tertiaryDoubleEntry.coordOffset1, tertiaryDoubleEntry.coordOffset);
                    }
                }
            }
        }

        return vec;
    }

    private Vec3 getVec2(BlockPos blockPos2, AxisAlignedBB boundingBox) {
        return this.getVec3(
        PlayerUtil.getList(
        
        
        boundingBox,
        new PredictedHitPoint(
        (double)blockPos2.getX() + 0.5 - minecraft.thePlayer.posX,
        minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2(),
        (double)blockPos2.getZ() + 0.5 - minecraft.thePlayer.posZ
        )
        )
        );
    }

    private boolean checkCondition() {
        return !this.settingThroughWall.isEnabled() ? false : !this.settingBreakOnlyFreeBeds.isEnabled();
    }

    private boolean checkBlockPos(BlockPos blockPos2, int index) {
        return blockPos2 != null ? this.checkCondition(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), index) : false;
    }

    private void applyMovingObjectPosition3(MovingObjectPosition hitResult) {
        if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            minecraft.thePlayer.respawnPlayer();
        }

        minecraft.playerController.onPlayerDamageBlock(this.blockPos, hitResult.sideHit);
        if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
            minecraft.thePlayer.respawnPlayer();
        }
    }

    private float getScaleFactor(ItemStack itemStack, Block block) {
        if (block.equals(Block.processAction3(this.getInt3()))) {
            return 1.0F;
        } else {
            Item item = itemStack.getItem();
            float value;
            if (!(item instanceof ItemTool) && !(item instanceof ItemShears)) {
                value = 1.0F;
            } else {
                value = 0.0F
                + itemStack.getItem().processAction(itemStack, block)
                + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager5.indexOffset, itemStack)
                + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager1.indexOffset, itemStack) / 11.0F
                + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager4.indexOffset, itemStack) / 11.0F
                + (float)LvlManager.getIndex(EnchantmentManager.enchantmentManager2.indexOffset, itemStack) / 33.0F;
            }

            return value;
        }
    }

    private AxisAlignedBB getAxisAlignedBB(BlockPos blockPos2) {
        Block block = minecraft.theWorld.getBlockState(blockPos2).getBlock();
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
        (double)blockPos2.getX() + block.getCoordOffset(),
        (double)blockPos2.getY() + block.getCoordOffset11(),
        (double)blockPos2.getZ() + block.getCoordOffset2(),
        (double)blockPos2.getX() + block.getCoordOffset9(),
        (double)blockPos2.getY() + block.getCoordOffset6(),
        (double)blockPos2.getZ() + block.getCoordOffset15()
        );
        if (!(block instanceof BlockBed)) {
            return axisAlignedBB;
        } else {
            for (EnumFacing enumFacing2 : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST}) {
                Block block2 = minecraft.theWorld.getBlockState(blockPos2.getBlockPos(enumFacing2, 1)).getBlock();
                if (block2 instanceof BlockBed) {
                    BlockPos blockPos3 = blockPos2.getBlockPos(enumFacing2, 1);
                    AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(
                    (double)blockPos3.getX() + block2.getCoordOffset(),
                    (double)blockPos3.getY() + block2.getCoordOffset11(),
                    (double)blockPos3.getZ() + block2.getCoordOffset2(),
                    (double)blockPos3.getX() + block2.getCoordOffset9(),
                    (double)blockPos3.getY() + block2.getCoordOffset6(),
                    (double)blockPos3.getZ() + block2.getCoordOffset15()
                    );
                    axisAlignedBB = axisAlignedBB.getAxisAlignedBB4(axisAlignedBB2);
                    break;
                }
            }

            return axisAlignedBB;
        }
    }

    private void applyBlockPos(BlockPos blockPos2, EnumFacing enumFacing2) {
        TimeHelper timer4 = this.timer;
        long time2 = (long)this.settingDelay.getValue();
        if (timer4.hasReached(time2)) {
            minecraft.thePlayer.respawnPlayer();
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing2));
            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.STOP_DESTROY_BLOCK, blockPos2, enumFacing2));
            this.timer.reset();
        }
    }

    private Vec3 getVec4(List list) {
        Vec3 vec = null;
        double offset = 15.0;

        for (Object object : list) {
            PredictedHitPoint tertiaryDoubleEntry = (PredictedHitPoint)object;
            double coordOffset = tertiaryDoubleEntry.coordOffset;
            double coordOffset1 = tertiaryDoubleEntry.coordOffset1;
            double coordOffset2 = tertiaryDoubleEntry.coordOffset2;
            Rotation rotation4 = this.getRotation4(coordOffset2, coordOffset1, coordOffset);
            MovingObjectPosition movingObjectPosition2 = minecraft.thePlayer.getMovingObjectPosition((double)minecraft.playerController.getBlockReachDistance(), 1.0F, rotation4.yaw, rotation4.pitch);
            if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                BlockPos blockPos2 = movingObjectPosition2.getBlockPos();
                int int3 = this.getInt3();
                if (this.checkBlockPos(blockPos2, int3)) {
                    double offset2 = Math.pow(tertiaryDoubleEntry.coordOffset2 - minecraft.thePlayer.posX, 2.0);
                    double offset3 = offset2 + Math.pow(tertiaryDoubleEntry.coordOffset1 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2()), 2.0);
                    double offset4 = Math.sqrt(offset3 + Math.pow(tertiaryDoubleEntry.coordOffset - minecraft.thePlayer.posZ, 2.0));
                    if (offset4 <= offset) {
                        offset = offset4;
                        vec = new Vec3(tertiaryDoubleEntry.coordOffset2, tertiaryDoubleEntry.coordOffset1, tertiaryDoubleEntry.coordOffset);
                    }
                }
            }
        }

        return vec;
    }

    private int getInt2() {
        float value = 1.0F;
        int index = -1;
        MovingObjectPosition hitResult;
        if (this.checkCondition() && this.settingSurrounding.isEnabled()) {
            hitResult = this.movingObjectPosition;
        } else {
            hitResult = minecraft.objectMouseOver;
        }

        if (hitResult == null) {
            return index;
        } else {
            if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
                int index2 = 36;

                while (true) {
                    if (index2 >= minecraft.thePlayer.container.itemList2.size()) {
                        break;
                    }

                    ItemStack itemStack = minecraft.thePlayer.container.getSlot(index2).getStack();
                    if (itemStack != null && (itemStack.getItem() instanceof ItemTool || itemStack.getItem() instanceof ItemShears)) {
                        Block block = minecraft.theWorld.getBlockState(hitResult.getBlockPos()).getBlock();
                        if (!(this.getScaleFactor(itemStack, block) <= value)) {
                            value = this.getScaleFactor(itemStack, block);
                            index = index2 - 36;
                        }
                    }

                    index2++;
                }
            }

            return index;
        }
    }

    private boolean checkCondition(int x, int x2, int y, int z) {
        return minecraft.theWorld.getIBlockState(x, x2, y).getBlock().equals(Block.processAction3(z));
    }

    private Vec3 getVec3() {
        BlockPos blockPos2 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
        ArrayList arrayList = new ArrayList();
        int int3 = this.getInt3();
        int aimRange = (int)this.settingAimRange.getValue();
        int index4 = blockPos2.getX() - aimRange;
        Vec3 vec;
        boolean enabled;
        if (index4 < blockPos2.getX() + aimRange) {
            int index5 = blockPos2.getY() - aimRange;
            if (index5 >= blockPos2.getY() + aimRange) {
                index4++;

                while (index4 < blockPos2.getX() + aimRange) {
                    for (int i = blockPos2.getY() - aimRange; i < blockPos2.getY() + aimRange; i++) {
                        for (int i2 = blockPos2.getZ() - aimRange; i2 < blockPos2.getZ() + aimRange; i2++) {
                            if (this.checkCondition(index4, i, i2, int3)) {
                                Vec3 vec2 = new Vec3((double)index4, (double)i, (double)i2);
                                if (this.settingFriendMyBlock.isEnabled() && minecraft.thePlayer.getPositionVec10() != null) {
                                    if (minecraft.thePlayer.getPositionVec10().getCoordinate4(vec2) > 30.0) {
                                        arrayList.add(vec2);
                                    }
                                } else {
                                    arrayList.add(vec2);
                                }
                            }
                        }
                    }

                    index4++;
                }
            } else {
                int index = blockPos2.getZ() - aimRange;

                while (true) {
                    if (index >= blockPos2.getZ() + aimRange) {
                        index5++;
                        break;
                    }

                    if (this.checkCondition(index4, index5, index, int3)) {
                        Vec3 vec3 = new Vec3((double)index4, (double)index5, (double)index);
                        if (this.settingFriendMyBlock.isEnabled() && minecraft.thePlayer.getPositionVec10() != null) {
                            if (minecraft.thePlayer.getPositionVec10().getCoordinate4(vec3) > 30.0) {
                                arrayList.add(vec3);

                                while (++index < blockPos2.getZ() + aimRange) {
                                    if (this.checkCondition(index4, index5, index, int3)) {
                                        Vec3 vec4 = new Vec3((double)index4, (double)index5, (double)index);
                                        if (this.settingFriendMyBlock.isEnabled() && minecraft.thePlayer.getPositionVec10() != null) {
                                            if (minecraft.thePlayer.getPositionVec10().getCoordinate4(vec4) > 30.0) {
                                                arrayList.add(vec4);
                                            }
                                        } else {
                                            arrayList.add(vec4);
                                        }
                                    }
                                }

                                index5++;
                                break;
                            }
                        } else {
                            arrayList.add(vec3);
                        }
                    }

                    index++;
                }

                while (true) {
                    while (index5 < blockPos2.getY() + aimRange) {
                        for (int i3 = blockPos2.getZ() - aimRange; i3 < blockPos2.getZ() + aimRange; i3++) {
                            if (this.checkCondition(index4, index5, i3, int3)) {
                                Vec3 vec5 = new Vec3((double)index4, (double)index5, (double)i3);
                                if (this.settingFriendMyBlock.isEnabled() && minecraft.thePlayer.getPositionVec10() != null) {
                                    if (minecraft.thePlayer.getPositionVec10().getCoordinate4(vec5) > 30.0) {
                                        arrayList.add(vec5);
                                    }
                                } else {
                                    arrayList.add(vec5);
                                }
                            }
                        }

                        index5++;
                    }

                    if (++index4 >= blockPos2.getX() + aimRange) {
                        break;
                    }

                    index5 = blockPos2.getY() - aimRange;
                }
            }

            arrayList.sort(Comparator.comparingDouble(object -> getCoordinate((Vec3)object)));
            if (arrayList.isEmpty()) {
                this.enabled2 = false;
                return null;
            }

            vec = (Vec3)arrayList.get(0);
            enabled = this.settingSurrounding.isEnabled();
        } else {
            arrayList.sort(Comparator.comparingDouble(object2 -> getCoordinate((Vec3)object2)));
            if (arrayList.isEmpty()) {
                this.enabled2 = false;
                return null;
            }

            vec = (Vec3)arrayList.get(0);
            enabled = this.settingSurrounding.isEnabled();
        }

        if (enabled && !this.enabled2 && vec != null) {
            BlockPos blockPos3 = new BlockPos(vec.xCoord, vec.yCoord, vec.zCoord);
            AxisAlignedBB axisAlignedBB = this.getAxisAlignedBB(blockPos3);
            if (this.getVec3(blockPos3, axisAlignedBB) == null) {
                ArrayList arrayList2 = new ArrayList();

                for (EnumFacing enumFacing2 : EnumFacing.VALUES) {
                    if (enumFacing2 != EnumFacing.DOWN) {
                        BlockPos blockPos4 = blockPos3.offset(enumFacing2);
                        if (!this.checkBlockPos(blockPos4, int3)
                        && minecraft.theWorld.getBlockState(blockPos4).getBlock().getMaterial() != Material.coreItemEntry) {
                            arrayList2.add(new Vec3((double)blockPos4.getX() + 0.5, (double)blockPos4.getY() + 0.5, (double)blockPos4.getZ() + 0.5));
                        }
                    }
                }

                arrayList2.sort(Comparator.comparingDouble(object3 -> getCoordinate2((Vec3)object3)));
                if (!arrayList2.isEmpty()) {
                    return (Vec3)arrayList2.get(0);
                }
            }
        }

        return vec;
    }

    private int getInt3() {
        String selected = this.settingBlock.getSelected();
switch (selected) {
            case "Bed":
                return 26;
            case "Cake":
                return 92;
            default:
                return (int)this.settingCustomID.getValue();
        }
    }

    private void applyMovingObjectPosition4(MovingObjectPosition hitResult) {
        if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            minecraft.thePlayer.respawnPlayer();
        }

        minecraft.playerController.onPlayerDamageBlock(this.blockPos, hitResult.sideHit);
        if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
            minecraft.thePlayer.respawnPlayer();
        }
    }

    private static boolean canExecute5() {
        return false;
    }

    private boolean isRangeVisible() {
        return this.settingBreakOnlyFreeBeds.isEnabled() ? false : this.settingThroughWall.isEnabled();
    }

    private boolean isRangeVisible2() {
        return !this.settingBreakOnlyFreeBeds.isEnabled();
    }

    private boolean isRangeVisible3() {
        return this.settingAction.getSelected().equals("Break");
    }
}
