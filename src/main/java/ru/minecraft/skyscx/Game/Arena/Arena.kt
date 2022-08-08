package ru.minecraft.skyscx.Game.Arena

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import ru.minecraft.skyscx.Skyscx
import java.lang.Double.max
import java.lang.Double.min

//SPLEEF 1x1
class Arena (nameA: String, val locSnow1: Location, val locSnow2: Location) : Listener {
    enum class ArenaStatus {
        WAIT, LIVE
    }
    var PlayersInGame : ArrayList<Player?> = ArrayList()
    var PlayersDeath : ArrayList<Player> = ArrayList()
    var GameStatus : ArenaStatus = ArenaStatus.WAIT
    var SpawnLocation : ArrayList<Location> = ArrayList()
    var size: Int = SpawnLocation.size
    var name = nameA

    fun AddPlayer(player: Player?) : Boolean {
        if (GameStatus != ArenaStatus.WAIT) return false
        PlayersInGame.add(player)
        if (PlayersInGame.size == size) {
            startGame()
        }
        return true
    }

    //NIGHT

    fun startGame(){
        GameStatus = ArenaStatus.LIVE
        for ((i, player) in PlayersInGame.withIndex()){
            player?.teleport(SpawnLocation[i])
            player?.gameMode = GameMode.SURVIVAL
            sendArenaTitle("§eИгра начата!","" )
            player?.inventory?.clear()
            val item = ItemStack(Material.DIAMOND_SPADE)
            item.addEnchantment(Enchantment.DIG_SPEED, 5)
            player?.inventory?.setItem(0, item)
        }
    }

    fun playerLose(player: Player) {
        player.gameMode = GameMode.SPECTATOR

        sendArenaTitle("Вы проиграли","Не растраивайтесь :)" )
        PlayersDeath.add(player)
        PlayersInGame.remove(player)

        if (CheckGame()){
            gameEND()
        }
    }

    fun CheckGame() : Boolean{
        if (PlayersInGame.size == 1) {
            playerWin(PlayersInGame[0])
            return true
        }
        return false
    }

    fun playerWin(player: Player?){
        sendArenaTitle("Вы победили!", " ")
    }

    fun gameEND() {
        for(player in PlayersInGame){
            SelectLobby(player)
        }
        for (player in PlayersDeath){
            SelectLobby(player)
        }
        PlayersInGame = ArrayList()
        PlayersDeath = ArrayList()
        GameStatus = ArenaStatus.WAIT
        remakeArena()
    }

    fun addSpawnLoc(x: Double, y: Double, z: Double, move1: Float = 0f, move2: Float = 0f){
        SpawnLocation.add(Location(Bukkit.getWorld("world"), x, y, z, move1, move2))
        size = SpawnLocation.size
    }

    fun sendArenaTitle(msg: String, subMsg: String){
        for ((i, player) in PlayersInGame.withIndex()){
            player?.sendTitle(msg, subMsg, 40, 20, 10)
        }
    }
    private fun SelectLobby(player: Player?){
        player?.teleport(Skyscx.instance?.lobby) // переделать!!!
        player?.gameMode = GameMode.ADVENTURE
        player?.inventory?.clear()
        player?.inventory?.addItem(ItemStack((Material.SNOW_BALL)))

    }

    private fun remakeArena(){
        var X1 = min(locSnow1.x, locSnow2.x)
        var X2 = max(locSnow1.x, locSnow2.x)
        var Z1 = min(locSnow1.z, locSnow2.z)
        var Z2 = max(locSnow1.z, locSnow2.z)

        for (x in X1.toInt() until X2.toInt()+1){
            for (z in Z1.toInt() until Z2.toInt()+1){
                Location(Bukkit.getWorld("world"), x.toDouble(), locSnow1.y, z.toDouble()).block.type = Material.SNOW_BLOCK
            }
        }
    }


    @EventHandler
    fun playerLose(event: PlayerMoveEvent) {
        if (!PlayersInGame.contains(event.player)) return
        if (event.player.location.y < locSnow1.y - 4){
            playerLose(event.player)
        }
    }
    @EventHandler
    fun playerBreakBlock(event: BlockBreakEvent){
        if (event.block.type == Material.SNOW_BLOCK){
            event.isCancelled = true
            event.block.type = Material.AIR
            return
        }else
            event.isCancelled = true
    }
}

