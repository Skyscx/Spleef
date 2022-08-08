package ru.minecraft.skyscx.Game

import org.bukkit.ChatColor
import ru.minecraft.skyscx.Game.Arena.Arena

object messages {
    var MAIN =
        ChatColor.WHITE.toString() + "[" + ChatColor.GOLD + "SPLEEF" + ChatColor.WHITE + "] " + ChatColor.GRAY
    var ERROR = ChatColor.WHITE.toString() + "[" + ChatColor.RED + "ERROR" + ChatColor.WHITE + "] " + ChatColor.GRAY
    var WARN = ChatColor.YELLOW.toString() + "[" + ChatColor.GOLD + "WARNING" + ChatColor.WHITE + "] " + ChatColor.GRAY
    var CONSOLE =
        ChatColor.WHITE.toString() + "[" + ChatColor.RED + "ERROR" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Данную команду может использовать только игрок!"
    var Turn1 =
        ChatColor.WHITE.toString() + "[" + ChatColor.GOLD + "SPLEEF" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Вы уже состоите в очереди!"
    var Turn2 =
        ChatColor.WHITE.toString() + "[" + ChatColor.GOLD + "SPLEEF" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Вы встали в очередь."
    var Start =
        ChatColor.WHITE.toString() + "[" + ChatColor.GOLD + "SPLEEF" + ChatColor.WHITE + "] " + ChatColor.GRAY + "[Режим: 1x1] тут чот еще можно"
}