package com.giffing.bucket4j.spring.boot.starter.config.cache.ignite;

import org.apache.ignite.Ignite;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.bucket4j.spring.boot.starter.config.cache.AsyncCacheResolver;

@Configuration
@ConditionalOnClass({ Ignite.class })
@ConditionalOnBean(Ignite.class)
@ConditionalOnMissingBean(AsyncCacheResolver.class)
public class IgniteBucket4jCacheConfiguration {
	
	private Ignite ignite;
	
	public IgniteBucket4jCacheConfiguration(Ignite ignite) {
		this.ignite = ignite;
	}
	
	@Bean
	public AsyncCacheResolver hazelcastCacheResolver() {
		return new IgniteCacheResolver(ignite);
	}
}
