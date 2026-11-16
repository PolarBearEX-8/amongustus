package augustus.modules.fun;

import augustus.modes.fun.SpammerCustomSource;
import augustus.modes.fun.SpammerRandomSource;
import augustus.modes.fun.SpammerSentenceSource;
import augustus.modes.misc.SpammerFileSource;
import client.Client;
import client.ClientGlobals;
import client.command.CommandCallbackHandler;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.DynamicModeSetting;
import client.setting.ModeSetting;
import client.setting.RangeSetting;
import client.setting.SpammerModeOption;
import client.util.BooleanSupplier;
import java.awt.Color;
import java.util.Locale;
import java.util.function.BiFunction;
import net.minecraft.client.entity.EntityPlayerSP;

public class Spammer extends Module implements TickListener {
    public RangeSetting settingTickDelay;
    public DynamicModeSetting settingRandomMode;
    public ModeSetting settingSpammMessage;
    public int keyCode2;
    public String displayName2;
    public int tickDelay;

    public Spammer() {
        super("Spammer", "Vollmüller", new Color(77, 112, 107), Category.FUN);
        this.settingTickDelay = new RangeSetting(this, "TickDelay", 20.0, 40.0, 0.0, 200.0, 0)
            .getRangeSetting2();
        BiFunction[] biFunctionArray = new BiFunction[4];
        BiFunction biFunction = SpammerFileSource.getBiFunction();
        biFunctionArray[0] = biFunction;
        BiFunction biFunction2 = SpammerCustomSource.getBiFunction();
        biFunctionArray[1] = biFunction2;
        BiFunction biFunction3 = SpammerRandomSource.getBiFunction();
        biFunctionArray[2] = biFunction3;
        BiFunction biFunction4 = SpammerSentenceSource.getBiFunction();
        biFunctionArray[3] = biFunction4;
        this.settingRandomMode = new DynamicModeSetting(this, "RandomMode", "RandomAlphanumeric", biFunctionArray);
        String[] textArray = new String[0];
        this.settingSpammMessage = new ModeSetting(this, "SpammMessage", "Augustus on top!", textArray).getModeSetting(() -> canExecute5());
        this.keyCode2 = 0;
        this.displayName2 = "";
        this.tickDelay = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.settingTickDelay.recalculate();
    }

    @Override
    public void onPostTick() {
        this.keyCode2++;
        if (minecraft.thePlayer != null) {
            if (minecraft.theWorld != null) {
                if (minecraft.thePlayer.ticksExisted > 10) {
                    if (minecraft.isIntegratedServerRunning()) {
                        if (this.keyCode2 > this.settingTickDelay.getIndexOffset2()) {
                            this.keyCode2 = 0;
                            SpammerModeOption auxiliarySettingArrayEntry;
                            if ((auxiliarySettingArrayEntry = (SpammerModeOption)this.settingRandomMode.getSupplierEntry()) != null) {
                                this.applyString(auxiliarySettingArrayEntry.getString2());
                            }

                            this.settingTickDelay.recalculate();
                        }
                    }
                }
            }
        }
    }

    private void applyString(String text) {
        this.displayName2 = text;
        String text2 = this.settingSpammMessage.getSelected().toLowerCase(Locale.ROOT);
        CommandCallbackHandler eventCommandCallbackAdapter = Client.getClient().getEventCommandCallbackAdapter();
        if (!text2.startsWith(eventCommandCallbackAdapter.tertiaryClientManager.getString().toLowerCase(Locale.ROOT))) {
            this.tickDelay = 0;
            StringBuilder sb = new StringBuilder().append(this.settingSpammMessage.getSelected());
            String text3 = text.equals("") ? "" : "  " + text;
            minecraft.thePlayer.sendChatMessage(sb.append(text3).toString());
        } else {
            if (this.tickDelay > 1) {
                ClientGlobals.moduleManager.shuffleMode.setActive(true);
            }

            this.tickDelay++;
        }
    }

    private static boolean canExecute5() {
        return false;
    }
}
