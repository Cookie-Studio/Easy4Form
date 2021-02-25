package cn.cookiestudio.easy4form;

import cn.nukkit.plugin.PluginBase;

public class PluginMain extends PluginBase {

    private static PluginMain pluginMain;

    @Override
    public void onEnable() {
        pluginMain = this;
    }

    public static PluginMain getPluginMain() {
        return pluginMain;
    }
}
