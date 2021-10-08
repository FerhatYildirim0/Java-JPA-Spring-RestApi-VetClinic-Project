package com.works.redisrepositories;

import com.works.model.AgendaRedis;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface AgendaRedisRepository extends CrudRepository<AgendaRedis, String> {
}
