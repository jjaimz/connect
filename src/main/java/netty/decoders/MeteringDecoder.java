package netty.decoders;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.List;
import java.util.Map;

public class MeteringDecoder extends HttpRequestDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            QueryStringDecoder decoder = new QueryStringDecoder(uri);
            Map<String, List<String>> parameters = decoder.parameters();

            if (parameters.containsKey("odometer")) {
                String odometer = parameters.get("odometer").get(0);
                System.out.println("Odometer: " + odometer);
            }
            out.add(request);
        }
    }
}
