package net.minecraft.scoreboard;

import client.setting.ClientSettingsBlackType;
import client.setting.MovementBooleanProperty;
import client.util.SecondaryClientManager;
import client.util.ThePlayerNameManager;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class ScorePlayerTeam extends SecondaryClientManager {
   public final ThePlayerNameManager thePlayerNameManager;
   public final String labelText2;
   public final Set set = Sets.newHashSet();
   public String labelText4;
   public String labelText3 = "";
   public String labelText = "";
   public boolean enabled = true;
   public boolean enabled2 = true;
   public MovementBooleanProperty movementBooleanProperty2 = MovementBooleanProperty.ALWAYS;
   public MovementBooleanProperty movementBooleanProperty = MovementBooleanProperty.ALWAYS;
   public ClientSettingsBlackType clientSettingsBlackType = ClientSettingsBlackType.RESET;

   public void applyMovementBooleanPropertyByMovementBooleanP(MovementBooleanProperty movementBooleanProperty3) {
      this.movementBooleanProperty2 = movementBooleanProperty3;
      this.thePlayerNameManager.applyScorePlayerTeam2(this);
   }

   @Override
   public String resolveText(String text) {
      return this.getText() + text + this.getLabelText();
   }

   public ClientSettingsBlackType getClientSettingsBlackType() {
      return this.clientSettingsBlackType;
   }

   @Override
   public Collection getCollection() {
      return this.set;
   }

   public void applyMovementBooleanProperty(MovementBooleanProperty movementBooleanProperty3) {
      this.movementBooleanProperty = movementBooleanProperty3;
      this.thePlayerNameManager.applyScorePlayerTeam2(this);
   }

   public void applyTextByText(String text) {
      this.labelText = text;
      this.thePlayerNameManager.applyScorePlayerTeam2(this);
   }

   public int getCount() {
      byte byteValue = 0;
      if (this.isEnabled()) {
         byteValue |= 1;
      }

      if (this.isEnabled2()) {
         byteValue |= 2;
      }

      return byteValue;
   }

   public void applyText(String text) {
      if (text == null) {
         throw new IllegalArgumentException("Prefix cannot be null");
      } else {
         this.labelText3 = text;
         this.thePlayerNameManager.applyScorePlayerTeam2(this);
      }
   }

   public void applyIndex(int count) {
      this.setEnabled((count & 1) > 0);
      this.setEnabledByFlag((count & 2) > 0);
   }

   public String getText() {
      return this.labelText3;
   }

   @Override
   public MovementBooleanProperty getMovementBooleanProperty() {
      return this.movementBooleanProperty;
   }

   public void setClientSettingsBlackType(ClientSettingsBlackType clientSettingsBlackType2) {
      this.clientSettingsBlackType = clientSettingsBlackType2;
   }

   @Override
   public String getLabelText2() {
      return this.labelText2;
   }

   public void applyText2(String text) {
      if (text == null) {
         throw new IllegalArgumentException("Name cannot be null");
      } else {
         this.labelText4 = text;
         this.thePlayerNameManager.applyScorePlayerTeam2(this);
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      this.enabled2 = enabledByFlag;
      this.thePlayerNameManager.applyScorePlayerTeam2(this);
   }

   public String getText2() {
      return this.labelText4;
   }

   public static String getText(SecondaryClientManager secondaryClientManager, String text) {
      return secondaryClientManager == null ? text : secondaryClientManager.resolveText(text);
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
      this.thePlayerNameManager.applyScorePlayerTeam2(this);
   }

   @Override
   public MovementBooleanProperty getMovementBooleanProperty2() {
      return this.movementBooleanProperty2;
   }

   public ScorePlayerTeam(ThePlayerNameManager thePlayerNameManager2, String text) {
      this.thePlayerNameManager = thePlayerNameManager2;
      this.labelText2 = text;
      this.labelText4 = text;
   }
}
