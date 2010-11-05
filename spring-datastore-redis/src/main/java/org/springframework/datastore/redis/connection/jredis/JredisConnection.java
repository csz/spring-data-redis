/*
 * Copyright 2010 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.datastore.redis.connection.jredis;

import java.util.Collection;

import org.jredis.JRedis;
import org.jredis.RedisException;
import org.springframework.dao.DataAccessException;
import org.springframework.datastore.keyvalue.UncategorizedKeyvalueStoreException;
import org.springframework.datastore.redis.UncategorizedRedisException;
import org.springframework.datastore.redis.connection.DataType;
import org.springframework.datastore.redis.connection.RedisConnection;

/**
 * @author Costin Leau
 */
public class JredisConnection implements RedisConnection {

	private final JRedis jredis;
	private final String encoding;

	public JredisConnection(JRedis jredis, String encoding) {
		this.jredis = jredis;
		this.encoding = encoding;
	}

	protected DataAccessException convertJedisAccessException(Exception ex) {
		if (ex instanceof RedisException) {
			return JredisUtils.convertJredisAccessException((RedisException) ex);
		}
		throw new UncategorizedKeyvalueStoreException("Unknown JRedis exception", ex);
	}

	@Override
	public void close() throws UncategorizedRedisException {
		jredis.quit();

	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	@Override
	public JRedis getNativeConnection() {
		return jredis;
	}

	@Override
	public boolean isClosed() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isQueueing() {
		return false;
	}

	@Override
	public Integer dbSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer del(String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void discard() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void exec() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean exists(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean expire(String key, int seconds) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> keys(String pattern) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void multi() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean persist(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String randomKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean rename(String oldName, String newName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean renameNx(String oldName, String newName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void select(int dbIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer ttl(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DataType type(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unwatch() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void watch(String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer hSet(String key, String field, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer lPush(String key, String value) {
		try {
			jredis.lpush(key, value);
			return null;
		} catch (RedisException ex) {
			throw JredisUtils.convertJredisAccessException(ex);
		}
	}

	@Override
	public Integer rPush(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String get(String key) {
		try {
			return JredisUtils.convertToString(jredis.get(key), encoding);
		} catch (RedisException ex) {
			throw JredisUtils.convertJredisAccessException(ex);
		}
	}

	@Override
	public void set(String key, String value) {
		try {
			jredis.set(key, value);
		} catch (RedisException ex) {
			throw JredisUtils.convertJredisAccessException(ex);
		}
	}
}