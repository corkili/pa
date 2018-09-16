package com.corkili.pa.server.core;

import com.corkili.pa.server.request.Request;

public interface Server extends AutoCloseable {

    void start();

    void execute(Request request);

}
