package com.connect.metering;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

import static com.connect.security.VerificationService.verify;

public class MeteringDecoder extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        QueryStringDecoder decoder = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = decoder.parameters();

        if (parameters.containsKey("key")) {
            String key = parameters.get("key").get(0);
            if (verify(key)) {
                if (parameters.containsKey("odometer")) {
                    System.out.println("\nMetering Decoder");
                    String odometer = parameters.get("odometer").get(0);
                    System.out.println("Odometer: " + odometer);
                }
            }
            else {
                System.out.println("\nInvalid key provided.");
            }
        }
        else {
            System.out.println("\nNo key provided.");
        }
        ctx.fireChannelRead(request.retain());
    }
}
