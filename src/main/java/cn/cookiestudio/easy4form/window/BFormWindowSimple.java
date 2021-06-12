package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.BFormListener;
import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
public class BFormWindowSimple extends FormWindowSimple implements BForm{

    private int formId = -1;
    private Consumer<PlayerFormRespondedEvent> responseAction;
    private boolean listenerRegisterFlag;

    public BFormWindowSimple(String title, String content) {
        super(title, content);
    }

    public BFormWindowSimple(String title, String content, List<ElementButton> buttons) {
        super(title, content, buttons);
    }

    public BFormWindowSimple(String title, String content, List<ElementButton> buttons, Consumer<PlayerFormRespondedEvent> responseAction) {
        super(title, content, buttons);
        this.responseAction = responseAction;
    }

    public BFormWindowSimple setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse) {
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
        BFormWindowSimple clone = null;
        try {
            clone = (BFormWindowSimple) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Server.getInstance().getPluginManager().registerEvents(new BFormListener(clone), PluginMain.getPluginMain());
        clone.setFormId(player.showFormWindow(clone));
        return clone.getFormId();
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{

        private Consumer<PlayerFormRespondedEvent> responseAction;
        private String title = "";
        private String content = "";
        private List<ElementButton> buttons;
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

        public Builder setButtons(List<ElementButton> buttons) {
            this.buttons = buttons;
            return this;
        }

        public Builder addButton(ElementButton button){
            this.buttons.add(button);
            return this;
        }

        public Builder setResponse(String response) {
            this.response = response;
            return this;
        }

        public BFormWindowSimple build(){
            BFormWindowSimple form = new BFormWindowSimple(this.title,this.content,this.buttons,this.responseAction);
            if (this.response != null)
                form.setResponse(this.response);
            return form;
        }
    }
}
