package cn.cookiestudio.easy4form.window;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;

import java.util.function.Consumer;

public interface BForm {
    void invokeResponseAction(PlayerFormRespondedEvent response);
    int sendToPlayer(Player player);
    BForm setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse);
    Consumer<PlayerFormRespondedEvent> getResponseAction();
}
