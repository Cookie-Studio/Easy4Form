package cn.cookiestudio.easy4form;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;

public class PluginMain extends PluginBase {

    private static PluginMain pluginMain;

    @Override
    public void onEnable() {
        pluginMain = this;
        Server.getInstance().getPluginManager().registerEvents(new BFormPool(),this);
    }

    public static PluginMain getPluginMain() {
        return pluginMain;
    }
}
