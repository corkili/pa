package com.corkili.pa.server.app;

import com.corkili.pa.server.builder.ServerBuilder;
import com.corkili.pa.server.core.Server;

public class ServerLauncher {

    private void launchServer() {
        ServerBuilder serverBuilder = ServerBuilder.getInstance();
        try (Server server = serverBuilder.build()) {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerLauncher launcher = new ServerLauncher();
        launcher.launchServer();
    }

}
