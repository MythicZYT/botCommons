package me.PolakOn420.botcommons.web.requests;

import me.PolakOn420.botcommons.web.ContentType;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IRequestBody {

    @NotNull
    ContentType getContentType();

    @NotNull
    RequestBody toRequestBody();

    @Nullable
    default MediaType getMediaType() {
        return MediaType.parse(getContentType().getType());
    }
}
