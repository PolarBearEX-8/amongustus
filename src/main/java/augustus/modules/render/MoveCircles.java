package augustus.modules.render;

import augustus.modes.render.MoveCircleRing;
import client.event.RenderListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import client.util.MoveUtil;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class MoveCircles extends Module implements RenderListener, UpdateStateListener {
    public NumberSetting settingCircleRadius;
    public NumberSetting settingCircleWidth;
    public ColorSetting settingCircleColor;
    public ModeSetting settingCircleAnimation;
    public NumberSetting settingAnimationTime;
    public BooleanSetting settingFadeOut;
    public ModeSetting settingFadeOutAnimation;
    public NumberSetting settingFadeOutTime;
    public NumberSetting settingTicksBetweenCircles;
    public BooleanSetting settingOnlyAfterJump;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public boolean expanded2;
    public int tickDelay;
    public int keyCode2;

    public MoveCircles() {
        super("MoveCircles", "BewegungsKreise", new Color(245, 152, 217), Category.RENDER);
        this.settingCircleRadius = new NumberSetting(this, "CircleRadius", 1.5, 0.1, 3.0, 2);
        this.settingCircleWidth = new NumberSetting(this, "CircleWidth", 1.0, 0.1, 7.0, 2);
        Color color = new Color(75, 209, 242);
        this.settingCircleColor = new ColorSetting(this, "CircleColor", color);
        String[] stringArray = KeybindAnimation.getStringArray(false);
        this.settingCircleAnimation = new ModeSetting(this, "CircleAnimation", "easeInSine", stringArray);
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 500.0, 50.0, 1500.0, 0);
        this.settingFadeOut = new BooleanSetting(this, "FadeOut", true);
        String[] stringArray2 = KeybindAnimation.getStringArray(false);
        this.settingFadeOutAnimation = new ModeSetting(this, "FadeOutAnimation", "easeInSine", stringArray2).getModeSetting(() -> this.settingFadeOut.isEnabled());
        this.settingFadeOutTime = new NumberSetting(this, "FadeOutTime", 500.0, 50.0, 1500.0, 0).getNumberSetting(() -> this.settingFadeOut.isEnabled());
        this.settingTicksBetweenCircles = new NumberSetting(this, "TicksBetweenCircles", 5.0, 0.0, 10.0, 0);
        this.settingOnlyAfterJump = new BooleanSetting(this, "OnlyAfterJump", false);
        this.copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.tickDelay = 0;
        this.keyCode2 = 0;
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (!this.copyOnWriteArrayList.isEmpty()) {
            RenderUtils.updateState();

            for (MoveCircleRing moveCircleRing : (Iterable<MoveCircleRing>) this.copyOnWriteArrayList) {
                if (moveCircleRing.keybindAnimation.getDouble2() >= 1.0) {
                    if (this.settingFadeOut.isEnabled()) {
                        if (moveCircleRing.coordOffset < 1.0) {
                            moveCircleRing.keybindAnimation2
                            .updateEasingFunction(
                            (int)this.settingFadeOutTime.getValue(),
                            KeybindAnimation.getEasingFunction(this.settingFadeOutAnimation.getSelected()),
                            0,
                            false
                            );
                        }

                        moveCircleRing.coordOffset2 = MathHelper.getDouble7(moveCircleRing.coordOffset2 * moveCircleRing.keybindAnimation2.getDouble3(), 0.0, 255.0);
                    }

                    if (!this.settingFadeOut.isEnabled() || !(moveCircleRing.keybindAnimation2.getDouble2() < 1.0)) {
                        this.copyOnWriteArrayList.remove(moveCircleRing);
                        continue;
                    }
                }

                moveCircleRing.coordOffset = moveCircleRing.keybindAnimation.getDouble2();
                GL11.glPushMatrix();
                GL11.glTranslated(
                moveCircleRing.coordOffset3 - minecraft.getRenderManager().getRenderPosX(),
                moveCircleRing.coordOffset5 - minecraft.getRenderManager().getRenderPosY(),
                moveCircleRing.coordOffset1 - minecraft.getRenderManager().getRenderPosZ()
                );
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glLineWidth(moveCircleRing.scaleFactor4);
                RenderUtils.setColor(moveCircleRing.indexOffset);
                GL11.glBegin(3);
                double scaleFactor = (double)moveCircleRing.scaleFactor;
                double double3 = moveCircleRing.keybindAnimation.getDouble3();
                float value = (float)(scaleFactor * double3);

                for (int i = 0; i <= 360; i++) {
                    GL11.glVertex2f(
                    (float)((double)value * Math.cos((double)i * Math.PI / 180.0)), (float)((double)value * Math.sin((double)i * Math.PI / 180.0))
                    );
                }

                GL11.glEnd();
                GL11.glPopMatrix();
            }

            RenderUtils.prepareScissor();
        }
    }

    @Override
    public void onPreUpdateState() {
        if (minecraft.thePlayer.onGround
        && (double)this.keyCode2 >= this.settingTicksBetweenCircles.getValue()
        && (!this.settingOnlyAfterJump.isEnabled() || this.expanded2 != minecraft.thePlayer.onGround)) {
            this.keyCode2 = 0;
            this.copyOnWriteArrayList
            .add(
            new MoveCircleRing(
            
            this,
            minecraft.thePlayer.posX,
            minecraft.thePlayer.posY,
            minecraft.thePlayer.posZ,
            (float)this.settingCircleWidth.getValue(),
            (float)this.settingCircleRadius.getValue(),
            this.settingCircleColor.getInt12(this.tickDelay)
            )
            );
            this.tickDelay++;
        }

        this.expanded2 = minecraft.thePlayer.onGround;
        if (MoveUtil.canExecute2()) {
            this.keyCode2++;
        }
    }

    public static ModeSetting getModeSetting(MoveCircles moveCircles) {
        return moveCircles.settingCircleAnimation;
    }

    public static NumberSetting getNumberSetting(MoveCircles moveCircles) {
        return moveCircles.settingAnimationTime;
    }
}
