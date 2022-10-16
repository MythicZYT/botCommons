package me.PolakOn420.botcommons.web;

import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface RequestBuilderFunction {
    @NotNull Request.Builder apply(@NotNull Request.Builder builder);
}
