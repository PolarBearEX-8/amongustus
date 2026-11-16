package augustus.modes.render;

import augustus.modules.render.BlockESP;
import client.Client;
import client.setting.NamedOption;
import client.util.InternalClientManager;

public class BlockEspEntry implements NamedOption {
   public String labelText;
   public boolean state;
   public final BlockESP blockESP;

   @Override
   public void setActive(boolean active) {
      BlockESP.applyBlockESP(Client.getClient().getModuleManager().blockESP);
      this.state = active;
      BlockESP.checkBlockESP(this.blockESP, true);
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.state;
   }

   private BlockEspEntry(BlockESP blockESP2, String text, boolean flag) {
      this.blockESP = blockESP2;
      this.labelText = text;
      this.state = flag;
   }

   public BlockEspEntry(BlockESP blockESP2, String text, boolean flag, InternalClientManager internalClientManager) {
      this(blockESP2, text, flag);
   }
}
