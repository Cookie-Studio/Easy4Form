package cn.cookiestudio.easy4form.window;

import cn.cookiestudio.easy4form.BFormPool;
import cn.cookiestudio.easy4form.PluginMain;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowCustom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
public class BFormWindowCustom extends FormWindowCustom implements BForm{

    private Consumer<PlayerFormRespondedEvent> responseAction;

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

    public BFormWindowCustom(String title, List<Element> contents, ElementButtonImageData icon, Consumer<PlayerFormRespondedEvent> responseAction) {
        super(title, contents, icon);
        this.responseAction = responseAction;
    }

    public BFormWindowCustom setResponseAction(Consumer<PlayerFormRespondedEvent> onResponse) {
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
        private List<Element> elements;
        private ElementButtonImageData icon;
        private String response;

        public Builder setResponseAction(Consumer<PlayerFormRespondedEvent> responseAction) {
            this.responseAction = responseAction;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setElements(List<Element> elements) {
            this.elements = elements;
            return this;
        }

        public Builder addElements(Element element){
            this.elements.add(element);
            return this;
        }

        public Builder setIcon(ElementButtonImageData icon) {
            this.icon = icon;
            return this;
        }

        public Builder setResponse(String response) {
            this.response = response;
            return this;
        }

        public BFormWindowCustom build(){
            BFormWindowCustom form = new BFormWindowCustom(this.title,this.elements,this.icon,this.responseAction);
            if (this.response != null)
                form.setResponse(this.response);
            return form;
        }
    }
}
