package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GeometryCoordinator extends GuiScreen {
   public static final Logger logger2 = LogManager.getLogger();
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
   public final VectorDispatcher vectorDispatcher;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public boolean enabled6;

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyIndex(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("advMode.setCommand", new Object[0]), this.width / 2, 20, 16777215);
      this.applyFontRenderer(this.fontRenderer, StatCollector.getText("advMode.command", new Object[0]), this.width / 2 - 150, 37, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      byte byteValue = 75;
      int count3 = 0;
      FontRenderer fontRenderer = this.fontRenderer;
      String text = StatCollector.getText("advMode.nearestPlayer", new Object[0]);
      int count4 = this.width / 2 - 150;
      count3++;
      this.applyFontRenderer(fontRenderer, text, count4, 75 + 0 * this.fontRenderer.indexOffset, 10526880);
      fontRenderer = this.fontRenderer;
      text = StatCollector.getText("advMode.randomPlayer", new Object[0]);
      count4 = this.width / 2 - 150;
      count3++;
      this.applyFontRenderer(fontRenderer, text, count4, 75 + 1 * this.fontRenderer.indexOffset, 10526880);
      fontRenderer = this.fontRenderer;
      text = StatCollector.getText("advMode.allPlayers", new Object[0]);
      count4 = this.width / 2 - 150;
      count3++;
      this.applyFontRenderer(fontRenderer, text, count4, 75 + 2 * this.fontRenderer.indexOffset, 10526880);
      fontRenderer = this.fontRenderer;
      text = StatCollector.getText("advMode.allEntities", new Object[0]);
      count4 = this.width / 2 - 150;
      count3++;
      this.applyFontRenderer(fontRenderer, text, count4, 75 + 3 * this.fontRenderer.indexOffset, 10526880);
      count4 = this.width / 2 - 150;
      count3++;
      this.applyFontRenderer(this.fontRenderer, "", count4, 75 + 4 * this.fontRenderer.indexOffset, 10526880);
      if (this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().length() > 0) {
         int count5 = byteValue + count3 * this.fontRenderer.indexOffset + 16;
         this.applyFontRenderer(this.fontRenderer, StatCollector.getText("advMode.previousOutput", new Object[0]), this.width / 2 - 150, count5, 10526880);
         this.internalTexturesGuiIconsPngManagerAdapter2.updateState();
      }

      super.applyIndex(count, count2, factor);
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
   }

   private void updateState() {
      if (this.vectorDispatcher.isEnabled()) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = "O";
         if (this.vectorDispatcher.getIChatComponent() != null) {
            this.internalTexturesGuiIconsPngManagerAdapter2.applyText(this.vectorDispatcher.getIChatComponent().getString());
         }
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = "X";
         this.internalTexturesGuiIconsPngManagerAdapter2.applyText("-");
      }
   }

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, StatCollector.getText("gui.done", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list
         .add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(4, this.width / 2 + 150 - 20, 150, 20, 20, "O"));
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(2, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(32767);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.vectorDispatcher.getLabelText());
      this.internalTexturesGuiIconsPngManagerAdapter2 = new InternalTexturesGuiIconsPngManagerAdapter(3, this.fontRenderer, this.width / 2 - 150, 150, 276, 20);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyIndex3(32767);
      this.internalTexturesGuiIconsPngManagerAdapter2.setEnabled5(false);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyText("-");
      this.enabled6 = this.vectorDispatcher.isEnabled();
      this.updateState();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim().length() > 0;
   }

   public GeometryCoordinator(VectorDispatcher vectorDispatcher2) {
      this.vectorDispatcher = vectorDispatcher2;
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      this.internalTexturesGuiIconsPngManagerAdapter2.checkChar(character, count);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim().length() > 0;
      if (count == 28 || count == 156) {
         this.applyAuxiliaryTexturesGuiIconsP(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      } else if (count == 1) {
         this.applyAuxiliaryTexturesGuiIconsP(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.vectorDispatcher.setEnabled(this.enabled6);
            GuiScreen screen = null;
            this.mc.displayGuiScreen(screen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
            packet.writeByte(this.vectorDispatcher.getCountAsIndex());
            this.vectorDispatcher.applyByteBuf(packet);
            packet.getPacketBuffer(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
            packet.writeBoolean(this.vectorDispatcher.isEnabled());
            this.mc.getNetHandlerPlayClient().handlePacket(new C17PacketCustomPayload("MC|AdvCdm", packet));
            if (!this.vectorDispatcher.isEnabled()) {
               this.vectorDispatcher.setIChatComponent((IChatComponent)null);
            }

            GuiScreen screen2 = null;
            this.mc.displayGuiScreen(screen2);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
            this.vectorDispatcher.setEnabled(!this.vectorDispatcher.isEnabled());
            this.updateState();
         }
      }
   }
}
