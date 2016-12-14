package de.fenmore.tobiasThirolf.selfmadeEssentials.essentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created by Fenmore on 12.12.2016.
 */
public class MessageListener implements PluginMessageListener {



    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        System.out.println(channel + "   " + message);
        if (!channel.equals("SimplePermission")) {
            return;
        }
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            String command = in.readUTF();

            if(command.equals("VanishPermission")) {
                final String name = in.readUTF();
                final int count = in.readInt();
                Bukkit.broadcastMessage(message.toString());
            }

            if(command.equals("PlayerList")) {
                final String server = in.readUTF();
                String[] playerArray = in.readUTF().split(", ");
            }
        }
        catch(Exception e) {

        }
    }
}
