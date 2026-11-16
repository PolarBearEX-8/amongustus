package augustus.modules.movement;

import client.ClientGlobals;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.VClipDirection;
import client.util.BooleanSupplier;
import client.util.IBlockState;
import client.util.RayTraceUtil;
import client.util.VClipCollisionFilter;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockCache;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGlassPane;
import net.minecraft.block.BlockIronTrapdoor;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockReeds;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class VClip extends Module implements TickListener, PacketListener {
    public NumberSetting settingDistance;
    public BooleanSetting settingSmartDistance;
    public NumberSetting settingMaxSmartDistance;
    public NumberSetting settingDistanceToHeadBlock;
    public BooleanSetting settingIntave;
    public boolean expanded2;
    public int tickDelay;
    public int keyCode2;
    public boolean enabled2;

    public VClip() {
        super("VClip", "VKlammer", new Color(63, 255, 0, 255), Category.MOVEMENT);
        this.settingDistance = new NumberSetting(this, "Distance", -6.0, -10.0, 10.0, 2).getNumberSetting(() -> canExecute6());
        this.settingSmartDistance = new BooleanSetting(this, "SmartDistance", true);
        this.settingMaxSmartDistance = new NumberSetting(this, "MaxSmartDistance", -9.9, -10.0, -1.0, 2).getNumberSetting(() -> this.settingSmartDistance.isEnabled());
        this.settingDistanceToHeadBlock = new NumberSetting(this, "DistanceToHeadBlock", 0.1, 0.0, 1.0, 2).getNumberSetting(() -> this.settingSmartDistance.isEnabled());
        this.settingIntave = new BooleanSetting(this, "Intave", false);
        this.expanded2 = false;
        this.tickDelay = 0;
        this.keyCode2 = 0;
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.expanded2 = false;
        this.enabled2 = false;
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onPreDisable() {
        if (this.expanded2) {
            this.updateState();
        } else {
            this.processAction();
            this.onReset();
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (this.expanded2) {
            if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
                double coordinate1 = ((S08PacketPlayerPosLook)packet.getPacket()).getCoordinate1();
                if (coordinate1 > minecraft.thePlayer.posY + 0.5) {
                    String text = "Toggle at " + this.keyCode2;
                    RayTraceUtil.applyString(text);
                    this.onReset();
                }

                if (this.enabled2) {
                    packet.cancel();
                } else {
                    this.enabled2 = true;
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        if ((this.expanded2 ? 1 : 0) >= 1) {
            RayTraceUtil.applyString("Stop clipping");
            this.onReset();
        } else {
            int index = 0;
            this.tickDelay++;
            if (this.tickDelay >= 1) {
                this.tickDelay = 0;
            }

            index++;
        }
    }

    private boolean canExecute5() {
        EntityPlayerSP player = minecraft.thePlayer;
        int index2 = -1;
        int index3 = -1;

        while (index3 <= 1) {
            for (int i = -1; i <= 1; i++) {
                int int15 = MathHelper.ceiling_double_int(player.posX + (double)index2);
                int int152 = MathHelper.ceiling_double_int(player.posY + (double)index3);
                int int153 = MathHelper.ceiling_double_int(player.posZ + (double)i);
                IBlockState iBlockState = minecraft.theWorld.getIBlockState(int15, int152, int153);
                Block block = iBlockState.getBlock();
                if (block instanceof BlockGlassPane) {
                    return true;
                }

                if ((byte)((block instanceof BlockAcaciaFence) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockCobblestoneWall) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockDarkOakDoor) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockLadder) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockVine) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockReeds) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof VClipCollisionFilter) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockWeb) ? 1 : 0) != 0) {
                    return true;
                }

                if ((byte)((block instanceof BlockFire) ? 1 : 0) != 0) {
                    return true;
                }

                if (this.checkIBlockState(iBlockState) || block.getMaterial() == Material.coreItemEntry1) {
                    return true;
                }
            }

            index3++;
        }

        index2++;
        return false;
    }

    private void updateState() {
        if (this.expanded2) {
            this.expanded2 = true;
            this.tickDelay = 0;
        }

        RayTraceUtil.applyString("Invalid clip position");
        this.onReset();
    }

    private static boolean canExecute6() {
        return !ClientGlobals.moduleManager.vClip.settingSmartDistance.isEnabled();
    }

    private void processAction() {
        if (minecraft.theWorld != null) {
            if (minecraft.thePlayer != null) {
                if (!this.settingSmartDistance.isEnabled()) {
                    double posX = minecraft.thePlayer.posX;
                    double offset = minecraft.thePlayer.posY + this.settingDistance.getValue();
                    minecraft.thePlayer.processDouble2(posX, offset, minecraft.thePlayer.posZ);
                } else {
                    double offset2 = 0.0;
                    int index2 = 1;

                    double offset3;
                    while (true) {
                        if (index2 >= 10) {
                            offset3 = this.settingMaxSmartDistance.getValue();
                            break;
                        }

                        double offset4 = minecraft.thePlayer.posY - (double)index2;
                        BlockPos blockPos = new BlockPos(minecraft.thePlayer.posX, offset4, minecraft.thePlayer.posZ);
                        if (BlockCache.checkBlockPos(blockPos)) {
                            double offset5 = minecraft.theWorld.getBlockState(blockPos).getBlock().getCoordOffset6() + (double)blockPos.getY();
                            offset3 = Math.abs(minecraft.thePlayer.posY - offset5);
                            break;
                        }

                        index2++;
                    }

                    for (int i = (int)(-1.0 - offset2); (double)i >= Math.min(offset3, -1.0); i--) {
                        double offset6 = minecraft.thePlayer.posY + (double)i;
                        BlockPos blockPos2 = new BlockPos(minecraft.thePlayer.posX, offset6, minecraft.thePlayer.posZ);
                        if (!BlockCache.checkBlockPos3(blockPos2) && !BlockCache.checkBlockPos3(blockPos2.up())) {
                            double offset7 = (double)i + 0.2 - offset2 % 1.0;
                            double offset8 = offset7 - Math.max(this.settingDistanceToHeadBlock.getValue(), 0.0);
                            if (offset8 >= Math.min(offset3, -1.0)) {
                                double offset9 = minecraft.thePlayer.posY + offset8;
                                minecraft.thePlayer.processDouble2(minecraft.thePlayer.posX, offset9, minecraft.thePlayer.posZ);
                                return;
                            }
                        }
                    }

                    RayTraceUtil.applyString("No vclip possible");
                }
            }
        }
    }

    private boolean checkIBlockState(IBlockState blockState) {
        return blockState.getBlock() instanceof BlockIronTrapdoor ? false : blockState.getComparable(BlockIronTrapdoor.immutableSetEntry) == VClipDirection.BOTTOM;
    }
}
