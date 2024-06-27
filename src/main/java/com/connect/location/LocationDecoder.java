package com.connect.location;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

public class LocationDecoder extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        QueryStringDecoder decoder = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = decoder.parameters();

        if (parameters.containsKey("latitude") && parameters.containsKey("longitude")) {
            System.out.println("\nLocation Decoder");
            String latitude = parameters.get("latitude").get(0);
            System.out.println("Latitude: " + latitude);

            String longitude = parameters.get("longitude").get(0);
            System.out.println("Longitude: " + longitude);
        }
        ctx.fireChannelRead(request.retain());
    }
}
