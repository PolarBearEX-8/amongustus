package net.raphimc.vialegacy.api.protocol;

import com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType;
import com.viaversion.viaversion.api.protocol.packet.PacketType;
import com.viaversion.viaversion.api.protocol.packet.ServerboundPacketType;
import com.viaversion.viaversion.api.protocol.packet.State;
import com.viaversion.viaversion.api.protocol.remapper.PacketHandler;

public abstract class StatelessTransitionProtocol<CU extends ClientboundPacketType, CM extends ClientboundPacketType, SM extends ServerboundPacketType, SU extends ServerboundPacketType>
   extends StatelessProtocol<CU, CM, SM, SU> {
   public StatelessTransitionProtocol(
      Class<CU> unmappedClientboundPacketType,
      Class<CM> mappedClientboundPacketType,
      Class<SM> mappedServerboundPacketType,
      Class<SU> unmappedServerboundPacketType
   ) {
      super(unmappedClientboundPacketType, mappedClientboundPacketType, mappedServerboundPacketType, unmappedServerboundPacketType);
   }

   public void registerServerboundTransition(ServerboundPacketType unmappedPacketType, SM mappedPacketType, PacketHandler handler) {
      this.registerServerbound(unmappedPacketType.state(), unmappedPacketType.getId(), mappedPacketType != null ? mappedPacketType.getId() : -1, wrapper -> {
         wrapper.setPacketType(mappedPacketType);
         if (handler != null) {
            handler.handle(wrapper);
         }
      });
   }

   public void registerClientboundTransition(CU unmappedPacketType, Object... handlers) {
      if (handlers.length % 2 != 0) {
         throw new IllegalArgumentException("handlers.length % 2 != 0");
      } else {
         this.registerClientbound(unmappedPacketType.state(), unmappedPacketType.getId(), -1, wrapper -> {
            State currentState = wrapper.user().getProtocolInfo().getServerState();
            int i = 0;

            while (true) {
               if (i >= handlers.length) {
                  String str = String.valueOf(unmappedPacketType);
                  String str2 = String.valueOf(currentState);
                  throw new IllegalStateException("No handler found for packet " + str + " in state " + str2);
               }

               Object patt2681$temp = handlers[i];
               if (patt2681$temp instanceof State) {
                  if (((State)patt2681$temp) == currentState) {
                     break;
                  }
               } else {
                  patt2681$temp = (ClientboundPacketType)handlers[i];
                  if (((PacketType)patt2681$temp).state() == currentState) {
                     wrapper.setPacketType((PacketType)patt2681$temp);
                     break;
                  }
               }

               i += 2;
            }

            PacketHandler handler = (PacketHandler)handlers[i + 1];
            if (handler != null) {
               handler.handle(wrapper);
            }
         });
      }
   }
}
