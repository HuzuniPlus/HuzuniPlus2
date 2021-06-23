package me.travis.wurstplus.wurstplustwo.event.events;

import me.travis.wurstplus.wurstplustwo.event.WurstplusEventCancellable;
import net.minecraft.entity.Entity;

public class WurstplusEventRenderEntity extends WurstplusEventCancellable {
    public final Entity entity;
    public final Type type;

    public WurstplusEventRenderEntity(Entity e, Type t) {
        entity = e;
        type = t;
    }

    public enum Type {
        TEXTURE, COLOR
    }

    public Entity getEntity() {
        return entity;
    }

    public Type getType() {
        return type;
    }

    public static class Head extends WurstplusEventRenderEntity {
        public Head(Entity e, Type t) {
            super(e, t);
        }
    }

    public static class Return extends WurstplusEventRenderEntity {
        public Return(Entity e, Type t) {
            super(e, t);
        }
    }
}