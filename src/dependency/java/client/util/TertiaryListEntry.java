package client.util;

import client.util.AdvancedStringArrayEntry;
import java.util.List;
import net.minecraft.client.Minecraft;

public class TertiaryListEntry {
    public final List list;
    public final String labelText;

    public List getList() {
        return this.list;
    }

    public int getCount() {
        int n = Minecraft.getMinecraft().fontRendererObj.indexOffset + 1;
        int n2 = 0 + n;
        int n3 = n2 + n;
        for (AdvancedStringArrayEntry advancedStringArrayEntry : (Iterable<AdvancedStringArrayEntry>)(Iterable)this.list) {
            n3 += advancedStringArrayEntry.getStringArray().length * n;
        }
        return n3;
    }

    public String getLabelText() {
        return this.labelText;
    }

    public TertiaryListEntry(String string, List list) {
        this.labelText = string;
        this.list = list;
    }
}

