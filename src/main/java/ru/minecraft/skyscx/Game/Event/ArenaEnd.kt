package ru.minecraft.skyscx.Game.Event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import ru.minecraft.skyscx.Game.Arena.Arena

class ArenaEnd(_arena: Arena) : Event() {
    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }

    override fun getHandlers(): HandlerList = handlerList

    val arena : Arena = _arena
}