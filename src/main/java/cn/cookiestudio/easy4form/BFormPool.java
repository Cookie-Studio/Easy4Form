package cn.cookiestudio.easy4form;

import cn.cookiestudio.easy4form.window.BForm;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BFormPool implements cn.nukkit.event.Listener {

    private static Map<Integer, BForm> bFormPool = new HashMap<>();

    public static void handle(int formId, BForm bform){
        bFormPool.put(formId,bform);
    }

    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent event) {
        int formId = event.getFormID();
        if (!bFormPool.containsKey(formId))
            return;
        BForm form = bFormPool.get(formId);
        form.invokeResponseAction(event);
        bFormPool.remove(formId);
    }
}
