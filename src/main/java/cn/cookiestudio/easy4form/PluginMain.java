package cn.cookiestudio.easy4form;

import cn.nukkit.plugin.PluginBase;

public class PluginMain extends PluginBase {

    private static PluginMain pluginMain;

    @Override
    public void onLoad() {
        this.getLogger().info("plugin load!");
    }

    @Override
    public void onEnable() {
        this.getLogger().info("plugin enable!");
        pluginMain = this;
    }

    @Override
    public void onDisable() {
        this.getLogger().info("plugin disable!");
    }

    public static PluginMain getPluginMain() {
        return pluginMain;
    }
}
