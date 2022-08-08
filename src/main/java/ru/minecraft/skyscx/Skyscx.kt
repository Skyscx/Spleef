package ru.minecraft.skyscx

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import ru.minecraft.skyscx.Game.Arena.SYSTEM
import ru.minecraft.skyscx.Game.Event.EVENTS

class Skyscx : JavaPlugin() {
    val system = SYSTEM()
    val lobby : Location = Location(Bukkit.getWorld("world"), -284.0, 122.0,278.0, 45f, 0f)
    override fun onEnable() {
        instance = this
        plugin = this

        Bukkit.getServer().pluginManager.registerEvents(EVENTS, this)

        for (arena in system.ArenaLIST){
            Bukkit.getServer().pluginManager.registerEvents(arena, this)
        }

        for (i in Bukkit.getOnlinePlayers()){
            i.teleport(lobby)
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
    companion object {
        var instance: Skyscx? = null
            private set
        var plugin: Plugin? = null
            private set
    }
}