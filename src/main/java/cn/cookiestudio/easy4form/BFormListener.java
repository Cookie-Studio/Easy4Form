package cn.cookiestudio.easy4form;

import cn.cookiestudio.easy4form.window.BForm;
import cn.cookiestudio.easy4form.window.BFormWindowSimple;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;

public class BFormListener implements cn.nukkit.event.Listener {
    BForm form;
    public BFormListener(BForm form){
        this.form = form;
    }

    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent event) {
        if (event.getResponse() == null)
            return;
        if (event.getFormID() == form.getFormId()) {
            ((BFormWindowSimple) event.getWindow()).invokeResponseAction(event);
        }
    }
}
