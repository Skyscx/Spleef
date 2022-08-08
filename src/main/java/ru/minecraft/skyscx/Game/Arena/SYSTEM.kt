package ru.minecraft.skyscx.Game.Arena

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

class SYSTEM {
    val ArenaLIST : ArrayList<Arena> = ArrayList()
    val turn : TURN = TURN()

    constructor(){
        generateArenas()
    }

    fun addPlayerInTurn(_player: Player, _count: Int = 2) {
        turn.addPlayerTurn(_player, _count)
        Bukkit.getConsoleSender().sendMessage("Player ${_player.name} add in turn")
        checkStartArena(_count)
    }

    fun checkStartArena(_count: Int) {
        if (turn.size(_count)!! >= _count) {
            val arena: Arena? = findArena()

            for (i in 0 until _count) {
                val player: Player? = turn.getPlayer(_count)
                arena?.AddPlayer(player)
                Bukkit.getConsoleSender().sendMessage("Player ${player?.name} add in arena: ${arena?.name}")
            }
        }
    }

    fun findArena() : Arena? {

        for (arena in ArenaLIST) {
            if (arena.GameStatus == Arena.ArenaStatus.WAIT) return arena
        }

        return null
    }

    fun generateArenas() {
        var arena = Arena("ARENA1", Location(Bukkit.getWorld("world"), -259.0, 107.0, 253.0), Location(Bukkit.getWorld("world"), -309.0, 107.0, 303.0))
        arena.addSpawnLoc(-284.5, 108.0, 301.0, -150f, 0f)
        arena.addSpawnLoc(-284.5, 108.0, 255.0, 30f, 0f)

        ArenaLIST.add(arena)

        /*
        arena = Arena("ARENA2", Location(Bukkit.getWorld("world"), -259.0, 107.0, 253.0), Location(Bukkit.getWorld("world"), -309.0, 107.0, 303.0))
        arena.addSpawnLoc(-284.5, 108.0, 301.0, -150f, 0f)
        arena.addSpawnLoc(-284.5, 108.0, 255.0, 30f, 0f)
        ArenaLIST.add(arena)*/
    }
}