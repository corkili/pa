package com.corkili.pa.server.builder;

import com.corkili.pa.server.core.Server;

public class ServerBuilder {

    private static ServerBuilder instance;

    public static ServerBuilder getInstance() {
        if (instance == null) {
            synchronized (ServerBuilder.class) {
                if (instance == null) {
                    instance = new ServerBuilder();
                }
            }
        }
        return instance;
    }

    private ServerBuilder() {

    }

    public Server build() {
        return null;
    }

}
