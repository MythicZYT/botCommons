package me.PolakOn420.botcommons.web.requests;

import me.PolakOn420.botcommons.web.ContentType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

public class PlainTextRequestBody implements IRequestBody {
    private final StringBuilder body = new StringBuilder();

    @Override
    public @NotNull ContentType getContentType() {
        return ContentType.TEXT_PLAIN;
    }

    public PlainTextRequestBody setContent(@NotNull String content) {
        this.body.setLength(0);
        this.body.append(content);

        return this;
    }

    public PlainTextRequestBody appendContent(@NotNull String content) {
        this.body.append(content);

        return this;
    }

    public StringBuilder getBuilder() {
        return body;
    }

    @Override
    public @NotNull RequestBody toRequestBody() {
        return RequestBody.create(null, this.body.toString().getBytes());
    }
}
