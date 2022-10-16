package me.PolakOn420.botcommons.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.natanbc.reliqua.request.RequestContext;
import com.github.natanbc.reliqua.request.RequestException;
import me.PolakOn420.botcommons.JSONHelper;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class WebParserUtils {
    // Only null when invalid json is found
    @Nullable
    public static ObjectNode toJSONObject(Response response) throws IOException {
        return toJSONObject(response, JSONHelper.createObjectMapper());
    }

    // Only null when invalid json is found
    @Nullable
    public static ObjectNode toJSONObject(Response response, ObjectMapper mapper) throws IOException {
        return (ObjectNode) mapper.readTree(getInputStream(response));
    }

    public static InputStream getInputStream(Response response) {
        final ResponseBody body = response.body();

        if (body == null) {
            throw new IllegalStateException("Body should never be null");
        }

        final String encoding = response.header("Content-Encoding");

        if (encoding != null) {
            switch (encoding.toLowerCase()) {
                case "gzip":
                    try {
                        return new GZIPInputStream(body.byteStream());
                    } catch (IOException e) {
                        throw new IllegalStateException("Received Content-Encoding header of gzip, but data is not valid gzip", e);
                    }
                case "deflate":
                    return new InflaterInputStream(body.byteStream());
            }
        }

        return body.byteStream();
    }

    public static <T> void handleError(RequestContext<T> context) {
        final Response response = context.getResponse();
        final ResponseBody body = response.body();

        if (body == null) {
            context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code() + " (No body)", context.getCallStack()));
            return;
        }

        JsonNode json = null;

        try {
            json = toJSONObject(response);
        } catch (Exception ignored) {
        }

        if (json != null) {
            context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code() + ": " + json, context.getCallStack()));
        } else {
            context.getErrorConsumer().accept(new RequestException("Unexpected status code " + response.code(), context.getCallStack()));
        }
    }
}
