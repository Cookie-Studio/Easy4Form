package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowCustom;
import java.util.List;
import java.util.function.Consumer;

public class BFormWindowCustom extends FormWindowCustom {

    private int formId;
    private Consumer<PlayerFormRespondedEvent> onResponse;
    private boolean listenerRegisterFlag;

    public BFormWindowCustom(String title) {
        super(title);
    }

    public BFormWindowCustom(String title, List<Element> contents) {
        super(title, contents);
    }

    public BFormWindowCustom(String title, List<Element> contents, String icon) {
        super(title, contents, icon);
    }

    public BFormWindowCustom(String title, List<Element> contents, ElementButtonImageData icon) {
        super(title, contents, icon);
    }

    public BFormWindowCustom(String title, List<Element> contents, ElementButtonImageData icon, Consumer<PlayerFormRespondedEvent> onResponse) {
        super(title, contents, icon);
        this.onResponse = onResponse;
    }

    public BFormWindowCustom setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    public Consumer<PlayerFormRespondedEvent> getResponseAction() {
        return onResponse;
    }

    public void invokeResponseAction(PlayerFormRespondedEvent response) {
        this.onResponse.accept(response);
    }

    public int sendToPlayer(Player player) {
        if (!this.listenerRegisterFlag)
            Server.getInstance().getPluginManager().registerEvents(new Listener(), PluginMain.getPluginMain());
        this.formId = player.showFormWindow(this);
        return this.formId;
    }

    private class Listener implements cn.nukkit.event.Listener {
        @EventHandler
        public void onFormResponse(PlayerFormRespondedEvent event) {
            if (event.getResponse() == null)
                return;
            if (event.getFormID() == BFormWindowCustom.this.formId) {
                ((BFormWindowCustom) event.getWindow()).invokeResponseAction(event);
            }
        }
    }
}
