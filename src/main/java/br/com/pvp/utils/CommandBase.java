package br.com.pvp.utils;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandBase extends Command {

    public CommandBase(String cmd) {
        super(cmd);
    }

    public CommandBase(String name, List<String> aliases) {
        super(name, "", "", aliases);
    }

    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public abstract boolean execute(CommandSender sender, String lb, String[] args);

    protected void sendExecutorMessage(CommandSender sender) {
        sender.sendMessage("�b�lLOBBY �fO comando executado somente por jogadores.");
    }

    protected void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage("�c�lPERMISSION �fVoc� n�o tem permiss�o para �6executar�f o �9comando�f.");
    }

    protected void sendOfflinePlayerMessage(CommandSender sender) {
        sender.sendMessage("�4�lOFFLINE �fO jogador est� offline no momento.");
    }

    protected boolean hasPermission(CommandSender sender, String perm) {
        return sender.hasPermission("lobby." + perm);
    }

    protected boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    protected String getArgs(String[] args, int start) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        return stringBuilder.toString();
    }
}
