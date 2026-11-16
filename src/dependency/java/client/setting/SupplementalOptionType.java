package client.setting;

public enum SupplementalOptionType {
    SEND,
    READ,
    SEND_NETTY_DELAY,
    SEND_NETTY,
    READ_NETTY;

   public static final SupplementalOptionType[] ENUM_VALUES = new SupplementalOptionType[]{SEND, SEND_NETTY, SEND_NETTY_DELAY, READ_NETTY, READ};

}
