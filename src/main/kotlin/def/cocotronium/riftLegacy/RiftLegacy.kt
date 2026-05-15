package def.cocotronium.riftLegacy


import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.Lamp
import revxrsal.commands.annotation.Command
import revxrsal.commands.bukkit.BukkitLamp

import revxrsal.commands.bukkit.actor.BukkitCommandActor



class RiftLegacy : JavaPlugin() {

    companion object {
        lateinit var instance: RiftLegacy
            private set
    }

    val lamp: Lamp<BukkitCommandActor> = BukkitLamp.builder(this).build()

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        server.pluginManager.registerEvents(FreezeListener(this), this)
        lamp.register(FreezeCommand(this), this)


    }
    override fun onDisable() {}
}


class FreezeCommand( private val  plugin : JavaPlugin)   {

    @Command("freeze <target>")

    fun freezePlayer(player: Player, target: Player, ) {
        
        val players =  plugin.config.getStringList("frozenPlayers")

        if (players.contains(target.uniqueId.toString())) {
            player.sendMessage("You can't freeze ${target.name}")}

        else { (players.add(target.uniqueId.toString()))

            plugin.config.set("frozenPlayers", players)
            plugin.saveConfig()
        }
    }
    @Command("unfreeze <target>")

    fun unfreezePlayer(player: Player, target: Player, ) {


        val players = plugin.config.getStringList("frozenPlayers")
        if (players.contains(target.uniqueId.toString())) {
            players.remove(target.uniqueId.toString())
            plugin.config.set("frozenPlayers", players)
            plugin.saveConfig()

        }
        else {(player.sendMessage("You can't unfreeze ${target.name}"))
             }


    }
}

class  FreezeListener ( private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val frozenPlayers = plugin.config.getStringList("frozenPlayers")

        if (frozenPlayers.contains(player.uniqueId.toString())) {
            event.isCancelled = true
        }
        else
            null

    }
}




















