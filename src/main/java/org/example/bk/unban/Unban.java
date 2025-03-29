package org.example.bk.unban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Unban extends JavaPlugin {

    @Override
    public void onEnable() {
        // Lógica de inicialização do plugin
        getLogger().info("Unban plugin ativado!");
        getCommand("unban").setExecutor(this::onCommand); // Registra o comando
    }

    @Override
    public void onDisable() {
        // Lógica de encerramento do plugin
        getLogger().info("Unban plugin desativado!");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unban")) {
            if (!sender.hasPermission("unban.use")) {
                sender.sendMessage("§cVocê não tem permissão para usar este comando!");
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("§cUso correto: /unban <nome_do_jogador>");
                return false;
            }

            String playerName = args[0];
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(playerName);
            sender.sendMessage("§aO jogador " + playerName + " foi desbanido com sucesso!");
            return true;
        }
        return false;
    }
}
