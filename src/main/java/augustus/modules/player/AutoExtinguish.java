package augustus.modules.player;

import augustus.modes.combat.AuraRenderMode;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventWorldLoad;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.RangeSetting;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class AutoExtinguish extends Module implements TickListener, ClickListener, MotionListener, WorldLoadListener, MoveListener {
    public RangeSetting settingVerticalSpeed = new RangeSetting(this, "Vertical Speed", 15.0, 30.0, 0.0, 180.0, 0);
    public BooleanSetting settingRandomizeYaw = new BooleanSetting(this, "RandomizeYaw", true);
    public BooleanSetting settingNotWhileKillAura = new BooleanSetting(this, "NotWhileKillAura", false);
    public int keyCode2 = -1;
    public int tickDelay = -1;
    public Vec3 positionVec = null;
    public BlockPos blockPos = null;
    public int tickDelay1 = 0;

    public AutoExtinguish() {
        super("AutoExtinguish", "AutomatischesLöschen", new Color(252, 152, 3), Category.PLAYER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(7);
        this.registerEvent(34);
        this.registerEvent(17);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        this.keyCode2 = -1;
        this.tickDelay = -1;
        this.positionVec = null;
        this.blockPos = null;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        Vec3 vec;
        if ((this.checkCondition() ? 1 : 0) > 0) {
            vec = this.positionVec;
        } else {
            vec = this.positionVec;
        }

        if (vec != null) {
            if (this.tickDelay1 == 0) {
                MovingObjectType typeOfHit = minecraft.objectMouseOver.typeOfHit;
                MovingObjectType movingObjectTypeKind = MovingObjectType.BLOCK;
                if (typeOfHit == movingObjectTypeKind) {
                    BlockPos blockPos2 = this.blockPos;
                    if (blockPos2.checkBlockPos2(minecraft.objectMouseOver.getBlockPos())) {
                        event.updateInt2(1);
                        this.positionVec = null;
                        this.tickDelay1 = 0;
                    }
                }
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.keyCode2 = -1;
        this.tickDelay = -1;
        this.positionVec = null;
    }

    @Override
    public void onMove(EventMove event) {
        if (ClientGlobals.rotationManager.checkModule(this)) {
            event.setAuraRenderMode(AuraRenderMode.DEFAULT);
        }
    }

    @Override
    public void onPostMotion() {
        if (this.checkCondition()) {
            this.tickDelay = this.keyCode2;
            if (this.checkCondition9() || this.positionVec != null) {
                this.keyCode2 = this.getInt2();
                if (this.keyCode2 >= 0) {
                    int keyCode22 = this.keyCode2;
                    SlotManager.applyModule3(this, -2, keyCode22);
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        if ((this.checkCondition() ? 1 : 0) >= 0) {
            double xCoord = this.positionVec.xCoord;
            double yCoord = this.positionVec.yCoord;
            double zCoord = this.positionVec.zCoord;
            Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
            float value = (float)this.settingVerticalSpeed.recalculate();
            float value2 = (float)this.settingVerticalSpeed.recalculate();
            Rotation rotation2 = SimulatedPlayer.getRotation2(xCoord, yCoord, zCoord, rotation3, value, value2, true);
            Rotation rotation32 = ClientGlobals.rotationManager.getRotation3();
            Rotation rotation5 = SimulatedPlayer.getRotation5(rotation2, rotation32);
            ClientGlobals.rotationManager.applyRotation2(rotation5, -2, 1898960900, this);
        }
    }

    private Vec3 getVec3(int x, int y, int z) {
        for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            if (minecraft.theWorld.getIBlockState(x + enumFacing.getInt2(), y + enumFacing.getInt3(), z + enumFacing.getInt()).getBlock().getMaterial()
                != Material.coreItemEntry) {
                return this.getVec3(x, y, z, enumFacing);
            }
        }

        return null;
    }

    private Vec3 getVec32(int index3) {
        for (int i = -10; i <= 10; i++) {
            for (int i2 = -10; i2 <= 10; i2++) {
                IBlockState iBlockState = minecraft.theWorld
                    .getIBlockState(
                        MathHelper.ceiling_double_int(minecraft.thePlayer.posX + (double)i), index3, MathHelper.ceiling_double_int(minecraft.thePlayer.posZ + (double)i2)
                    );
                Block block = iBlockState.getBlock();
                if (block instanceof BlockLiquid
                    && (Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0
                    && block.getMaterial() == Material.coreItemEntry1) {
                    return this.getVec3(
                        MathHelper.ceiling_double_int(minecraft.thePlayer.posX + (double)i),
                        index3,
                        
                        
                        MathHelper.ceiling_double_int(minecraft.thePlayer.posZ + (double)i2)
                    );
                }
            }
        }

        return null;
    }

    private void updateState() {
        BlockPos blockPos2 = minecraft.objectMouseOver.getBlockPos();
        if (blockPos2.checkBlockPos2(minecraft.movingObjectPosition2.getBlockPos())) {
            minecraft.rightClickMouse();
            MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
            this.positionVec = this.getVec3(objectMouseOver);
            this.blockPos = minecraft.objectMouseOver.getBlockPos();
            this.tickDelay1 = 0;
        }
    }

    private boolean checkCondition() {
        return minecraft.isIntegratedServerRunning();
    }

    private int getInt2() {
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(36).getStack();
        }

        return -1;
    }

    private boolean checkCondition9() {
        return minecraft.thePlayer.canExecute2();
    }

    private Vec3 getVec3(BlockPos blockPos2, EnumFacing enumFacing) {
        return this.getVec3(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), enumFacing);
    }

    private Vec3 getVec3(MovingObjectPosition hitResult) {
        return this.getVec3(hitResult.getBlockPos(), hitResult.sideHit);
    }

    private Vec3 getVec3(int x, int y, int z, EnumFacing enumFacing) {
        return new Vec3(
            (double)x + (enumFacing.getInt2() == 0 ? 0.5 : (double)enumFacing.getInt2()),
            (double)y + (enumFacing.getInt3() == 0 ? 0.5 : (double)enumFacing.getInt3()),
            (double)z + (enumFacing.getInt() == 0 ? 0.5 : (double)enumFacing.getInt())
        );
    }
}
