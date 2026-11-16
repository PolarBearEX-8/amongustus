package augustus.modules.fun;

import client.ClientGlobals;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.ModeSetting;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class AutoJumpAndRun extends Module implements TickListener, MoveListener {
    public ModeSetting settingMode;
    public TimeHelper timer;
    public BlockPos blockPos;
    public BlockPos blockPos2;
    public boolean expanded2;

    public AutoJumpAndRun() {
        super("AutoJumpAndRun", "AutoSpringenUndLaufen", new Color(119, 148, 61), Category.FUN);
        String[] textArray = new String[1];
        textArray[0] = "Gomme";
        this.settingMode = new ModeSetting(this, "Mode", "Gomme", textArray);
        this.timer = new TimeHelper();
        this.blockPos = (BlockPos)null;
        this.blockPos2 = (BlockPos)null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.timer.reset();
    }

    @Override
    public void onMove(EventMove event) {
        if (minecraft.isIntegratedServerRunning() && minecraft.thePlayer != null && this.blockPos != null && this.blockPos2 != null) {
            if (this.timer.hasReached(2000L)) {
                double offset = minecraft.thePlayer.posY - 1.0;
                Block block = minecraft.theWorld.getBlockState(new BlockPos(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ)).getBlock();
                double offset2 = 1.29;
                double offset3 = 0.29;
                double posX = minecraft.thePlayer.posX;
                double posZ = minecraft.thePlayer.posZ;
                double offset4 = posX + (minecraft.thePlayer.posX - minecraft.thePlayer.prevPosX) * 0.0;
                double offset5 = posZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.prevPosZ) * 0.0;
                double offset6 = (double)this.blockPos2.getX() + offset2;
                double offset7 = (double)this.blockPos2.getX() - offset3;
                double offset8 = (double)this.blockPos2.getZ() + offset2;
                double offset9 = (double)this.blockPos2.getZ() - offset3;
                if (minecraft.thePlayer
                .getDouble4((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ()) > 2.6) {
                    if (offset4 > offset6
                    || offset4 < offset7
                    || offset5 > offset8
                    || !(offset5 >= offset9)) {
                        event.setState(true);
                    }

                    event.setScaleFactor7(1.0F);

                    for (int i = 1; i < 4; i++) {
                        double offset10 = minecraft.thePlayer.posY - (double)i;
                        BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, offset10, minecraft.thePlayer.posZ);
                        if (BlockCache.checkBlockPos(blockPos3) && blockPos3.checkBlockPos2(this.blockPos)) {
                            float value = -1.0F;
                            event.setScaleFactor7(value);
                            if (minecraft.thePlayer.onGround) {
                                event.setState4(true);
                            }
                        }
                    }
                } else {
                    if (!block.isState18()) {
                        event.setState(true);
                    }

                    event.setScaleFactor7(1.0F);
                    int index = 1;
                    double offset11 = minecraft.thePlayer.posY - (double)1;
                    BlockPos blockPos4 = new BlockPos(minecraft.thePlayer.posX, offset11, minecraft.thePlayer.posZ);
                    boolean enabled = BlockCache.checkBlockPos(blockPos4);

                    while (true) {
                        if (enabled && blockPos4.checkBlockPos2(this.blockPos)) {
                            float value2 = -1.0F;
                            event.setScaleFactor7(value2);
                            if (minecraft.thePlayer.onGround) {
                                event.setState4(true);
                            }
                        }

                        if (++index >= 4) {
                            break;
                        }

                        double offset12 = minecraft.thePlayer.posY - (double)index;
                        blockPos4 = new BlockPos(minecraft.thePlayer.posX, offset12, minecraft.thePlayer.posZ);
                        enabled = BlockCache.checkBlockPos(blockPos4);
                    }
                }

                return;
            }

            if (!this.timer.hasReached(1000L)) {
                event.setScaleFactor7(1.0F);
                event.setState4(true);
            }
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.thePlayer.onGround) {
            double offset = minecraft.thePlayer.posY - 0.5;
            BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ);
            Block block = minecraft.theWorld.getBlockState(blockPos3).getBlock();
            this.blockPos2 = block.isState18() ? blockPos3 : this.blockPos2;
            if (this.blockPos2 != null) {
                if (this.expanded2) {
                    this.timer.reset();
                }

                double offset2 = minecraft.thePlayer.posY - 0.5;
                BlockPos blockPos4 = new BlockPos(minecraft.thePlayer.posX, offset2, minecraft.thePlayer.posZ);
                this.blockPos = this.getBlockPos(blockPos4);
                if (this.blockPos != null) {
                    float value;
                    if (this.timer.hasReached(1000L)) {
                        double offset3 = (double)this.blockPos.getX() + 0.5;
                        double offset4 = (double)this.blockPos.getY() + 0.5;
                        double offset5 = (double)this.blockPos.getZ() + 0.5;
                        Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                        value = SimulatedPlayer.getRotation2(offset3, offset4, offset5, rotation3, 180.0F, 180.0F, false).yaw;
                    } else {
                        double offset6 = (double)this.blockPos2.getX() + 0.5;
                        double offset7 = (double)this.blockPos2.getY() + 0.5;
                        double offset8 = (double)this.blockPos2.getZ() + 0.5;
                        Rotation rotation32 = ClientGlobals.rotationManager.getRotation3();
                        value = SimulatedPlayer.getRotation2(offset6, offset7, offset8, rotation32, 180.0F, 180.0F, false).yaw;
                    }

                    ClientGlobals.rotationManager.applyModule2(value, 10, this);
                } else {
                    RotationManager rotationManager = ClientGlobals.rotationManager;
                    float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
                    rotationManager.applyModule2(yaw, 10, this);
                }

                this.expanded2 = false;
            }
        } else {
            this.expanded2 = true;
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            float yaw2 = ClientGlobals.rotationManager.getRotation3().yaw;
            rotationManager2.applyModule2(yaw2, 10, this);
        }
    }

    private BlockPos getBlockPos(BlockPos blockPos3) {
        for (int i = -6; i <= 6; i++) {
            for (int i2 = 0; i2 <= 1; i2++) {
                for (int i3 = -6; i3 <= 6; i3++) {
                    if (BlockCache.checkCondition((double)i, (double)i2, (double)i3)) {
                        Block block = minecraft.theWorld.getIBlockState(i, i2, i3).getBlock();
                        Block block2 = minecraft.theWorld.getIBlockState(i, i2 + 1, i3).getBlock();
                        if (block.equals(Blocks.wool) && !block2.isState18()) {
                            return blockPos3.add(i, i2, i3);
                        }
                    }
                }
            }
        }

        return null;
    }
}
