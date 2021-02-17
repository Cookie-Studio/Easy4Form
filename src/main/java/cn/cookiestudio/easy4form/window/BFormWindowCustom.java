package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import java.util.List;
import java.util.function.Function;

public class BFormWindowCustom extends FormWindowCustom {

    private int formId;
    private Function<FormResponseCustom, Void> onResponse;

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

    public BFormWindowCustom(String title, List<Element> contents, ElementButtonImageData icon, Function<FormResponseCustom, Void> onResponse){
        super(title, contents, icon);
        this.onResponse = onResponse;
    }

    public Function<FormResponseCustom, Void> getResponseAction() {
        return onResponse;
    }

    public void invokeAction(FormResponseCustom response){
        this.onResponse.apply(response);
    }

    public int sendToPlayer(Player player){
        Server.getInstance().getPluginManager().registerEvents(new BFormWindowCustom.Listener(), PluginMain.getPluginMain());
        this.formId = player.showFormWindow(this);
        return this.formId;
    }

    private class Listener implements cn.nukkit.event.Listener{
        @EventHandler
        public void onFormResponse(PlayerFormRespondedEvent event){
            if (event.getFormID() == BFormWindowCustom.this.formId){
                ((BFormWindowCustom)event.getWindow()).invokeAction((FormResponseCustom)event.getResponse());
            }
        }
    }
}
