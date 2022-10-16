package me.PolakOn420.botcommons.web.requests;

import me.PolakOn420.botcommons.web.ContentType;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FormRequestBody implements IRequestBody {
    private final Map<String, String> params = new HashMap<>();

    public FormRequestBody append(@NotNull String key, @NotNull String value) {
        this.params.put(key, value);
        return this;
    }

    @Override
    public @NotNull ContentType getContentType() {
        return ContentType.URLENCODED;
    }

    @Override
    public @NotNull RequestBody toRequestBody() {
        // this builder has a weird impl so we can't reuse it (and we probably shouldn't)
        final FormBody.Builder builder = new FormBody.Builder();

        // Add all the params to the builder
        this.params.forEach(builder::add);

        return builder.build();
    }
}
