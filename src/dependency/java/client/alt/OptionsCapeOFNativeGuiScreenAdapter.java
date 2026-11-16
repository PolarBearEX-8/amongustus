package client.alt;

import client.gui.AuxiliaryTexturesGuiIc;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.NativeGuiScreenAdapter;
import client.util.BuildManager;
import client.util.GeneratorDefaultManager;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class OptionsCapeOFNativeGuiScreenAdapter extends NativeGuiScreenAdapter {
   public final GuiScreen guiScreen2;
   public String labelText;
   public String labelText2;
   public long timestamp2;
   public String labelText3;
   public AuxiliaryTexturesGuiIc auxiliaryTexturesGuiIc;
   public FontRenderer fontRenderer2 = BuildManager.getMc().fontRendererObj;

   public OptionsCapeOFNativeGuiScreenAdapter(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   public void applyText(String text) {
      this.labelText3 = text;
      this.auxiliaryTexturesGuiIc.enabled2 = text != null;
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.capeOF.title", new Object[0]);
      this.list
         .add(
            new AuxiliaryTexturesGuiIc(
               210, this.width / 2 - 155, this.height / 6 + 24, 150, 20, StatCollector.getText("of.options.capeOF.openEditor", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIc(
               220, this.width / 2 - 155 + 160, this.height / 6 + 24, 150, 20, StatCollector.getText("of.options.capeOF.reloadCape", new Object[0])
            )
         );
      byte byteValue = 8;
      this.auxiliaryTexturesGuiIc = new AuxiliaryTexturesGuiIc(
         230, this.width / 2 - 100, this.height / 6 + 96, 200, 20, StatCollector.getText("of.options.capeOF.copyEditorLink", new Object[0])
      );
      this.auxiliaryTexturesGuiIc.enabled2 = this.labelText3 != null;
      this.list.add(this.auxiliaryTexturesGuiIc);
      int count = byteValue + 4;
      this.list
         .add(new AuxiliaryTexturesGuiIc(200, this.width / 2 - 100, this.height / 6 + 24 * (count >> 1), StatCollector.getText("gui.done", new Object[0])));
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer2, this.labelText, this.width / 2, 20, 16777215);
      if (this.labelText2 != null) {
         applyFontRenderer4(this.fontRenderer2, this.labelText2, this.width / 2, this.height / 6 + 60, 16777215);
         if (System.currentTimeMillis() > this.timestamp2) {
            this.labelText2 = null;
            this.applyText((String)null);
         }
      }

      super.applyIndex(count, count2, factor);
   }

   private void applyText(String text, long value) {
      this.labelText2 = text;
      this.timestamp2 = System.currentTimeMillis() + value;
      this.applyText((String)null);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 210) {
            try {
               if (this.mc.getSession().getGameProfile().getId() == null) {
                  throw new InvalidCredentialsException("Cracked Account?");
               }

               String name = this.mc.getSession().getGameProfile().getName();
               String text = this.mc.getSession().getGameProfile().getId().toString().replace("-", "");
               String text2 = this.mc.getSession().getText2();
               Random random = new Random();
               Random random2 = new Random((long)System.identityHashCode(new Object()));
               BigInteger bigInteger = new BigInteger(128, random);
               BigInteger bigInteger2 = new BigInteger(128, random2);
               String text3 = bigInteger.xor(bigInteger2).toString(16);
               this.mc.getMinecraftSessionService().joinServer(this.mc.getSession().getGameProfile(), text2, text3);
               String text4 = "https://optifine.net/capeChange?u=" + text + "&n=" + name + "&s=" + text3;
               if (BuildManager.matchesURI(new URI(text4))) {
                  this.applyText(GeneratorDefaultManager.resolveText("of.message.capeOF.openEditor"), 10000L);
               } else {
                  this.applyText(GeneratorDefaultManager.resolveText("of.message.capeOF.openEditorError"), 10000L);
                  this.applyText(text4);
               }
            } catch (InvalidCredentialsException invalidCredentialsException) {
               BuildManager.applyText(
                  StatCollector.getText("of.message.capeOF.error1", new Object[0]),
                  StatCollector.getText("of.message.capeOF.error2", new Object[]{invalidCredentialsException.getMessage()})
               );
               BuildManager.applyText3("Mojang authentication failed");
               BuildManager.applyText3(invalidCredentialsException.getClass().getName() + ": " + invalidCredentialsException.getMessage());
            } catch (Exception exception) {
               exception.printStackTrace();
               BuildManager.applyText3("Error opening OptiFine cape link");
               BuildManager.applyText3(exception.getClass().getName() + ": " + exception.getMessage());
            }
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 220) {
            this.applyText(GeneratorDefaultManager.resolveText("of.message.capeOF.reloadCape"), 15000L);
            if (this.mc.thePlayer != null) {
               long value = System.currentTimeMillis() + 15000L;
               this.mc.thePlayer.setTimestamp2(value);
            }
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 230 && this.labelText3 != null) {
            applyText4(this.labelText3);
         }
      }
   }
}
