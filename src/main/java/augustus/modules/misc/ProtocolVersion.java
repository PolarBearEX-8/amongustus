package augustus.modules.misc;

import client.module.Category;
import client.module.Module;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.via.ViaLoadingBase;
import com.viaversion.vialoader.util.ProtocolVersionList;
import java.awt.Color;

public class ProtocolVersion extends Module {
    public NumberSetting settingProtocolVersionVal;

    public ProtocolVersion() {
        super("ProtocolVersion", "ProtokollVersion", true, Color.RED, Category.MISC);
        NumberSetting numberSetting2 = new NumberSetting(this, "ProtocolVersionVal", 47.0, 0.0, 0.0, 0).getNumberSetting(() -> canExecute5());
        this.settingProtocolVersionVal = (NumberSetting)numberSetting2.getSetting(() -> this.onProtocolVersionValAction());
        this.setActive(true);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    private static boolean canExecute5() {
        return false;
    }

    private void onProtocolVersionValAction() {
        for (com.viaversion.viaversion.api.protocol.version.ProtocolVersion protocolVersion : ProtocolVersionList.getProtocolsNewToOld()) {
            double version = (double)protocolVersion.getVersion();
            if (version == this.settingProtocolVersionVal.getValue()) {
                ViaLoadingBase.protocolVersion = protocolVersion;
                return;
            }
        }
    }
}
