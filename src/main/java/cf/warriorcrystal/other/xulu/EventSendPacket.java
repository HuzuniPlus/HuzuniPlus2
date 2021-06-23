package cf.warriorcrystal.other.xulu;

import me.travis.wurstplus.wurstplustwo.event.WurstplusEventCancellable;
import net.minecraft.network.Packet;

public class EventSendPacket extends WurstplusEventCancellable {
        private Packet packet;

        public EventSendPacket(Packet packet) {
            this.packet = packet;
        }

        public Packet getPacket() {
            return packet;
        }
        public void setPacket(Packet packet) {
            this.packet = packet;
        }
}
