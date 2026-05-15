package def.cocotronium.riftLegacy.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


class JoinListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
            val player = event.player
            player.sendMessage("Hallo")
    }

}