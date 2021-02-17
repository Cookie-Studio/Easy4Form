package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import java.util.List;
import java.util.function.Consumer;

public class BFormWindowSimple extends FormWindowSimple {

    private int formId;
    private Consumer<FormResponseSimple> onResponse;

    public BFormWindowSimple(String title, String content) {
        super(title, content);
    }

    public BFormWindowSimple(String title, String content, List<ElementButton> buttons) {
        super(title, content, buttons);
    }

    public BFormWindowSimple(String title, String content, List<ElementButton> buttons, Consumer<FormResponseSimple> onResponse) {
        super(title, content, buttons);
        this.onResponse = onResponse;
    }

    public BFormWindowSimple setResponseAction(Consumer<FormResponseSimple> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    public Consumer<FormResponseSimple> getResponseAction() {
        return onResponse;
    }

    public void invokeAction(FormResponseSimple response) {
        this.onResponse.accept(response);
    }

    public int sendToPlayer(Player player) {
        Server.getInstance().getPluginManager().registerEvents(new Listener(), PluginMain.getPluginMain());
        this.formId = player.showFormWindow(this);
        return this.formId;
    }

    private class Listener implements cn.nukkit.event.Listener {
        @EventHandler
        public void onFormResponse(PlayerFormRespondedEvent event) {
            if (event.getFormID() == BFormWindowSimple.this.formId) {
                ((BFormWindowSimple) event.getWindow()).invokeAction((FormResponseSimple) event.getResponse());
            }
        }
    }
}
