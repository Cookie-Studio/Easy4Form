package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;

import java.util.function.Function;

public class BFormWindowModal extends FormWindowModal {

    private int formId;
    private Function<FormResponseModal, Void> onResponse;

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText, Function<FormResponseModal, Void> onResponse){
        super(title, content, trueButtonText, falseButtonText);
        this.onResponse = onResponse;
    }

    public BFormWindowModal setResponseAction(Function<FormResponseModal, Void> onResponse){
        this.onResponse = onResponse;
        return this;
    }

    public Function<FormResponseModal, Void> getResponseAction() {
        return onResponse;
    }

    public void invokeAction(FormResponseModal response){
        this.onResponse.apply(response);
    }

    public int sendToPlayer(Player player){
        Server.getInstance().getPluginManager().registerEvents(new BFormWindowModal.Listener(), PluginMain.getPluginMain());
        this.formId = player.showFormWindow(this);
        return this.formId;
    }

    private class Listener implements cn.nukkit.event.Listener{
        @EventHandler
        public void onFormResponse(PlayerFormRespondedEvent event){
            if (event.getFormID() == BFormWindowModal.this.formId){
                ((BFormWindowModal)event.getWindow()).invokeAction((FormResponseModal)event.getResponse());
            }
        }
    }
}
