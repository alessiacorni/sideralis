package com.acorni.sideralis.apod.monitoring;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admin/cache")
public class CacheController {
    private CacheManager cacheManager;

    @Autowired
    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping()
    public List<cacheInfo> getCacheInfoList() {
        return cacheManager.getCacheNames()
                .stream()
                .map(this::buildCacheInfo)
                .toList();
    }

    private cacheInfo buildCacheInfo(String cacheName) {
        Cache nativeCache =
                (Cache)cacheManager.getCache(cacheName).getNativeCache();
        Set<Object> keys = nativeCache.asMap().keySet();
        CacheStats stats = nativeCache.stats();
        return new cacheInfo(
                cacheName, keys.size(), keys, stats.toString());
    }

    private record cacheInfo(String name, int size, Set<Object> keys, String stats) {}
}
