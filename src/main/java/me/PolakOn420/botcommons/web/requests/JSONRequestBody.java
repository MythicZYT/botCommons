package me.PolakOn420.botcommons.web.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.duncte123.botcommons.JSONHelper;
import me.duncte123.botcommons.web.ContentType;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JSONRequestBody implements IRequestBody {

    private final byte[] json;

    private JSONRequestBody(byte[] json) {
        this.json = json;
    }

    public static JSONRequestBody fromDataObject(@NotNull DataObject data) {
        return new JSONRequestBody(data.toString().getBytes());
    }

    public static JSONRequestBody fromDataArray(@NotNull DataArray data) {
        return new JSONRequestBody(data.toString().getBytes());
    }

    public static JSONRequestBody fromJSONObject(@NotNull org.json.JSONObject jsonObject) {
        return new JSONRequestBody(jsonObject.toString().getBytes());
    }

    public static JSONRequestBody fromJSONArray(@NotNull org.json.JSONArray jsonObject) {
        return new JSONRequestBody(jsonObject.toString().getBytes());
    }

    public static JSONRequestBody fromJackson(@NotNull JsonNode jsonNode) throws JsonProcessingException {
        return new JSONRequestBody(JSONHelper.createObjectMapper().writeValueAsBytes(jsonNode));
    }

    public static JSONRequestBody fromString(@NotNull String json) throws IOException {
        final ObjectMapper mapper = JSONHelper.createObjectMapper();

        return new JSONRequestBody(mapper.writeValueAsBytes(mapper.readTree(json)));
    }

    @Override
    public @NotNull ContentType getContentType() {
        return ContentType.JSON;
    }

    @Override
    public @NotNull RequestBody toRequestBody() {
        return RequestBody.create(null, json);
    }
}
