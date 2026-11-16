package client.util;

import java.io.Serializable;

public abstract class AbstractDESCBCController implements Serializable {
   public static final long serialVersionUID = -0x79536ae2f46b1f75L;

   public short getShortCode() {
      return (short)this.getCount();
   }

   public abstract double getValue();

   public abstract long getTimestamp();

   public abstract int getCount();

   public byte getByteCode() {
      return (byte)this.getCount();
   }

   public abstract float getFactor();
}
