package me.PolakOn420.botcommons.web.requests;

import me.PolakOn420.botcommons.web.ContentType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

public class EmptyFromRequestBody implements IRequestBody {
    @Override
    public @NotNull ContentType getContentType() {
        return ContentType.URLENCODED;
    }

    @Override
    public @NotNull RequestBody toRequestBody() {
        return RequestBody.create(null, new byte[0]);
    }
}
