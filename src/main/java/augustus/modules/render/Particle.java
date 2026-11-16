package augustus.modules.render;

import client.event.EventMouseClick;
import client.event.MouseClickListener;
import client.module.Category;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.MoveUtil;
import client.util.Pair;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Particle extends Module implements MouseClickListener {
    public Map footstepParticles = new HashMap();
    public BooleanSetting settingOnlyWhileMoving = new BooleanSetting(this, "OnlyWhileMoving", true);

    public Particle() {
        super("Particle", "PartkelBeimLaufenUnterEinem", new Color(247, 186, 106), Category.RENDER);

        for (AttackEffectsExplosionNormalType attackEffectsExplosionNormalTypeKind : AttackEffectsExplosionNormalType.values()) {
            if (attackEffectsExplosionNormalTypeKind.getIndex() >= 36 && attackEffectsExplosionNormalTypeKind.getIndex() <= 38) {
                return;
            }

            StringBuilder sb = new StringBuilder().append("Use ");
            String text = attackEffectsExplosionNormalTypeKind.name();
            BooleanSetting booleanSetting = new BooleanSetting(this, sb.append(this.getString3(text)).toString(), false);
            StringBuilder stringBuilder = new StringBuilder();
            String text2 = attackEffectsExplosionNormalTypeKind.name();
            NumberSetting numberSetting2 = new NumberSetting(
                this, stringBuilder.append(this.getString3(text2)).append("Delay ").toString(), 250.0, 1.0, 1000.0, 0
            ).getNumberSetting(booleanSetting::isEnabled);
            this.footstepParticles.put(attackEffectsExplosionNormalTypeKind, new Pair(booleanSetting, new Pair(numberSetting2, new TimeHelper())));
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(16);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMouseClick(EventMouseClick event) {
        if (MoveUtil.canExecute2() || !this.settingOnlyWhileMoving.isEnabled()) {
            Iterator iterator = this.footstepParticles.entrySet().iterator();

            while (true) {
                Entry entry;
                TimeHelper timeHelper;
                while (true) {
                    boolean enabled;
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }

                        entry = (Entry)iterator.next();
                        if (((BooleanSetting)((Pair)entry.getValue()).getSecond()).isEnabled()) {
                            try {
                                TimeHelper timeHelper2 = (TimeHelper)((Pair)((Pair)entry.getValue()).getFirst()).getFirst();
                                Object second = ((Pair)((Pair)entry.getValue()).getFirst()).getSecond();
                                enabled = timeHelper2.hasReachedDouble(((NumberSetting)second).getValue());
                                break;
                            } catch (Exception e) {
                            }
                        }
                    }

                    try {
                        if (enabled) {
                            timeHelper = (TimeHelper)((Pair)((Pair)entry.getValue()).getFirst()).getFirst();
                            break;
                        }
                    } catch (Exception e2) {
                    }
                }

                try {
                    timeHelper.reset();
                    minecraft.effectRenderer
                        .applyEntity4(minecraft.thePlayer, (AttackEffectsExplosionNormalType)entry.getKey(), minecraft.thePlayer.height / -2.0F + 0.1F);
                } catch (Exception e3) {
                }
            }
        }
    }

    private String getString3(String text) {
        String text2 = text.replace("_", " ");
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArray = text2.toCharArray();

        for (int i = 0; i < cArray.length; i++) {
            char character = cArray[i];
            if (i != 0 && cArray[i - 1] != ' ') {
                stringBuilder.append(String.valueOf(character).toLowerCase());
            } else {
                stringBuilder.append(character);
            }
        }

        return stringBuilder.toString();
    }
}
