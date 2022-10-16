package me.PolakOn420.botcommons.web;

import com.github.natanbc.reliqua.util.PendingRequestBuilder;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PendingRequestFunction {
    @NotNull PendingRequestBuilder apply(@NotNull PendingRequestBuilder builder);
}
