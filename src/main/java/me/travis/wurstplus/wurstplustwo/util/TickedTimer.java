package me.travis.wurstplus.wurstplustwo.util;

import java.util.function.Predicate;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
import me.travis.wurstplus.wurstplustwo.event.events.EventClientTick;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class TickedTimer implements Listenable {

    public int ticks = 0;
    @EventHandler
    private Listener<EventClientTick> tickEventListener = new Listener<EventClientTick>(event -> ++this.ticks, new Predicate[0]);

    public void reset() {
        this.ticks = 0;
    }

    public boolean passed(int ticks) {
        return this.ticks >= ticks;
    }

    public void start() {
        this.ticks = 0;
        WurstplusEventBus.EVENT_BUS.subscribe(this);
    }

    public void stop() {
        WurstplusEventBus.EVENT_BUS.unsubscribe(this);
    }

    public TickedTimer() {
        this.start();
    }

    protected void finalize() throws Throwable {
        WurstplusEventBus.EVENT_BUS.unsubscribe(this);
    }
}


