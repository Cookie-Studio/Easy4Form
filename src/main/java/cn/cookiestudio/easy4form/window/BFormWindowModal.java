package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.BFormPool;
import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowModal;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
public class BFormWindowModal extends FormWindowModal implements BForm{

    private Consumer<PlayerFormRespondedEvent> responseAction;

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    public BFormWindowModal(String title, String content, String trueButtonText, String falseButtonText, Consumer<PlayerFormRespondedEvent> responseAction) {
        super(title, content, trueButtonText, falseButtonText);
        this.responseAction = responseAction;
    }

    public BFormWindowModal setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse) {
        this.responseAction = onResponse;
        return this;
    }

    public Consumer<PlayerFormRespondedEvent> getResponseAction() {
        return responseAction;
    }

    public void invokeResponseAction(PlayerFormRespondedEvent response) {
        this.responseAction.accept(response);
    }

    public int sendToPlayer(Player player) {
        int formId = player.showFormWindow(this);
        BFormPool.handle(formId,this);
        return formId;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{

        private Consumer<PlayerFormRespondedEvent> responseAction;
        private String title = "";
        private String content = "";
        private String trueButton;
        private String falseButton;
        private String response;

        public Builder setResponseAction(Consumer<PlayerFormRespondedEvent> responseAction) {
            this.responseAction = responseAction;
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
