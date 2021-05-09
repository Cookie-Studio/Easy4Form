package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowModal;
import java.util.function.Consumer;

public class BFormWindowModal extends FormWindowModal {

    private int formId;
    private Consumer<PlayerFormRespondedEvent> onResponse;
    private boolean listenerRegisterFlag;

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText, Consumer<PlayerFormRespondedEvent> onResponse) {
        super(title, content, trueButtonText, falseButtonText);
        this.onResponse = onResponse;
    }

    public BFormWindowModal setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse) {
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
        if (!this.listenerRegisterFlag){
            Server.getInstance().getPluginManager().registerEvents(new Listener(), PluginMain.getPluginMain());
            this.listenerRegisterFlag = true;
        }
        this.formId = player.showFormWindow(this);
        return this.formId;
    }

    private class Listener implements cn.nukkit.event.Listener {
        @EventHandler
        public void onFormResponse(PlayerFormRespondedEvent event) {
            if (event.getResponse() == null)
                return;
            if (event.getFormID() == BFormWindowModal.this.formId) {
                ((BFormWindowModal) event.getWindow()).invokeResponseAction(event);
            }
        }
    }

    public Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{

        private Consumer<PlayerFormRespondedEvent> onResponse;
        private String title;
        private String content;
        private String trueButton;
        private String falseButton;
        private String response;

        public Builder setOnResponse(Consumer<PlayerFormRespondedEvent> onResponse) {
            this.onResponse = onResponse;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTrueButton(String trueButton) {
            this.trueButton = trueButton;
            return this;
        }

        public Builder setFalseButton(String falseButton) {
            this.falseButton = falseButton;
            return this;
        }

        public Builder setResponse(String response) {
            this.response = response;
            return this;
        }

        public BFormWindowModal build(){
            BFormWindowModal form =  new BFormWindowModal(title,content, trueButton, falseButton);
            if (this.response != null)
                form.setResponse(this.response);
            return form;
        }
    }
}
