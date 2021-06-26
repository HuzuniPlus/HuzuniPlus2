package cf.warriorcrystal.other.gamesense;

import net.minecraft.util.EnumHandSide;

public class TransformSideFirstPersonEvent extends GameSenseEvent {

    private final EnumHandSide enumHandSide;

    public TransformSideFirstPersonEvent(EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }

    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}