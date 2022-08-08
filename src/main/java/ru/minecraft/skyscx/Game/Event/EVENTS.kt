package ru.minecraft.skyscx.Game.Event

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import ru.minecraft.skyscx.Game.messages
import ru.minecraft.skyscx.Skyscx

object EVENTS : Listener{
    @EventHandler
    fun playerJoinTurn(event: PlayerInteractEvent){
        if (event.item?.type != Material.SNOW_BALL) return

        if (Skyscx.instance?.system?.turn?.hasPlayerInTurn(event.player) == false){
            event.player.sendMessage(messages.Turn2)
            Skyscx.instance?.system?.addPlayerInTurn(event.player, 2)
        }else{
            event.player.sendMessage(messages.Turn1)
        }
    }
    @EventHandler
    fun playerDamage(event: EntityDamageEvent){
        if (event.entity.type != EntityType.PLAYER) return
        event.isCancelled = true
    }
    @EventHandler
    fun playerJoinServer(event: PlayerJoinEvent){
        val player: Player = event.player
        player.inventory.clear()

        player.inventory.addItem(ItemStack(Material.SNOW_BALL))
        event.player.teleport(Skyscx.instance?.lobby!!)
    }
    @EventHandler
    fun playerLeaveServer(event: PlayerQuitEvent){
        Skyscx.instance?.system?.turn?.removePlayer(event.player)
    }
    @EventHandler
    fun playerFood(event: FoodLevelChangeEvent){
        event.foodLevel = 20
    }
    @EventHandler
    fun playerAddInTurn(event: PlayerAddInTurn){
        Skyscx.instance?.system?.addPlayerInTurn(event.player, event.size)
    }
    @EventHandler
    fun ArenaEnd(event: ArenaEnd){
        Skyscx.instance?.system?.checkStartArena(event.arena.size)
    }

}