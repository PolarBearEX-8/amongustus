package augustus.modules.movement;

import client.event.EventMove;
import client.event.EventSprint;
import client.event.MoveListener;
import client.event.SprintListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class SafeWalk extends Module implements SprintListener, MoveListener {
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Basic", new String[]{"Basic", "Sneak"});
    public BooleanSetting settingInAir = new BooleanSetting(this, "InAir", true);
    public BooleanSetting settingEdgeCheck;

    public SafeWalk() {
        super("SafeWalk", "SichererWeg", new Color(115, 135, 26), Category.MOVEMENT);
        BooleanSetting booleanSetting = new BooleanSetting(this, "EdgeCheck", true);
        this.settingEdgeCheck = (BooleanSetting)booleanSetting.getSetting(this.settingInAir::isEnabled);
    }

    @Override
    public void onEnable() {
        this.registerEvent(33);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onSprint(EventSprint event) {
        Block block = minecraft.theWorld
            .getBlockState(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 1.0, minecraft.thePlayer.posZ))
            .getBlock();
        Block block2 = minecraft.theWorld
            .getBlockState(new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 2.0, minecraft.thePlayer.posZ))
            .getBlock();
        if (minecraft.thePlayer.onGround) {
            if (this.settingMode.getSelected().equals("Basic")) {
                event.setActive(true);
            }
        } else if (this.settingInAir.isEnabled()) {
            if (block instanceof BlockAir && block2 instanceof BlockAir || !this.settingEdgeCheck.isEnabled()) {
                event.setActive(true);
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.settingMode.getSelected().equals("Sneak")) {
            minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 1.0, minecraft.thePlayer.posZ).getBlock();
            minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 2.0, minecraft.thePlayer.posZ).getBlock();
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer);
            float value = minecraft.thePlayer.width / 2.0F;
            new AxisAlignedBB(
                coordOffsetArray[0] - (double)value,
                coordOffsetArray[1],
                coordOffsetArray[2] - (double)value,
                coordOffsetArray[0] + (double)value,
                coordOffsetArray[1] + (double)minecraft.thePlayer.height,
                coordOffsetArray[2] + (double)value
            );
            if (minecraft.thePlayer.onGround) {
                event.setState4(true);
            }
        }
    }
}
