package augustus.modules.render;

import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.module.Category;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.Pair;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.entity.Entity;

public class AttackEffects extends Module implements AttackEntityListener {
    public Map attackEffectMap;

    public AttackEffects() {
        super("AttackEffects", "AngriffEffekte", new Color(36, 169, 176), Category.RENDER);
        this.attackEffectMap = new HashMap();
        AttackEffectsExplosionNormalType[] attackEffectsExplosionNormalTypeArray = AttackEffectsExplosionNormalType.values();
        int length = Array.getLength(attackEffectsExplosionNormalTypeArray);
        int index = 0;

        while (index < length) {
            Object object = attackEffectsExplosionNormalTypeArray[index];
            if (((AttackEffectsExplosionNormalType)object).getIndex() < 36) {
                StringBuilder sb = new StringBuilder().append("Use ");
                String text = ((AttackEffectsExplosionNormalType)object).name();
                String text9 = this.getString3(text);
                String text2 = sb.append(text9).toString();
                BooleanSetting booleanSetting = new BooleanSetting(this, text2, false);
                StringBuilder stringBuilder = new StringBuilder();
                String text3 = ((AttackEffectsExplosionNormalType)object).name();
                String text10 = this.getString3(text3);
                String text4 = stringBuilder.append(text10).append(" Count ").toString();
                NumberSetting numberSetting2 = new NumberSetting(this, text4, 1.0, 1.0, 20.0, 0).getNumberSetting(booleanSetting::isEnabled);
                Pair pair = new Pair(booleanSetting, numberSetting2);
                this.attackEffectMap.put(object, pair);
                index++;
            } else {
                if (((AttackEffectsExplosionNormalType)object).getIndex() <= 38) {
                    return;
                }

                StringBuilder sb2 = new StringBuilder().append("Use ");
                String text5 = ((AttackEffectsExplosionNormalType)object).name();
                String text11 = this.getString3(text5);
                String text6 = sb2.append(text11).toString();
                BooleanSetting booleanSetting2 = new BooleanSetting(this, text6, false);
                StringBuilder stringBuilder2 = new StringBuilder();
                String text7 = ((AttackEffectsExplosionNormalType)object).name();
                String text12 = this.getString3(text7);
                String text8 = stringBuilder2.append(text12).append(" Count ").toString();
                NumberSetting numberSetting4 = new NumberSetting(this, text8, 1.0, 1.0, 20.0, 0).getNumberSetting(booleanSetting2::isEnabled);
                Pair pair2 = new Pair(booleanSetting2, numberSetting4);
                this.attackEffectMap.put(object, pair2);
                index++;
            }
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (event.getEntity() != null) {
            Iterator iterator = this.attackEffectMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                if (((BooleanSetting)((Pair)entry.getValue()).getSecond()).isEnabled()) {
                    int index = 0;

                    try {
                        while (true) {
                            double value = ((NumberSetting)((Pair)entry.getValue()).getFirst()).getValue();
                            if (!((double)index < value)) {
                                break;
                            }

                            EffectRenderer effectRenderer = minecraft.effectRenderer;
                            Entity entity = event.getEntity();
                            AttackEffectsExplosionNormalType attackEffectsExplosionNormalTypeKind = (AttackEffectsExplosionNormalType)entry.getKey();
                            effectRenderer.applyEntity3(entity, attackEffectsExplosionNormalTypeKind);
                            index++;
                        }
                    } catch (Exception e) {
                    }
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
