package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HandlerStringEntry extends LISTManager {
   public String labelText;

   @Override
   public LISTManager getLISTManager() {
      return new HandlerStringEntry(this.labelText);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(288L);
      this.labelText = dataInput.readUTF();
      bytesWhereMaxAllowedManager.applyTime((long)(16 * this.labelText.length()));
   }

   @Override
   public boolean isEmpty() {
      return this.labelText.isEmpty();
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public byte getByteCode() {
      return 8;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeUTF(this.labelText);
   }

   public HandlerStringEntry(String text) {
      this.labelText = text;
      if (text == null) {
         throw new IllegalArgumentException("Empty string not allowed");
      }
   }

   public HandlerStringEntry() {
      this.labelText = "";
   }

   @Override
   public boolean equals(Object other) {
      if (!super.equals(other)) {
         return false;
      } else {
         HandlerStringEntry handlerStringEntry = (HandlerStringEntry)other;
         return this.labelText == null && handlerStringEntry.labelText == null || this.labelText != null && this.labelText.equals(handlerStringEntry.labelText);
      }
   }

   @Override
   public String toString() {
      return "\"" + this.labelText.replace("\"", "\\\"") + "\"";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.labelText.hashCode();
   }
}
