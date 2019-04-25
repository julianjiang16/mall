# mall
goods speed kill system


1.系统设计
    商品秒杀库存，秒杀，高并发处理
    redis：goodsID::库存数

springboot 2.0 
cloud Finchley
guava RateLimiter
hystrix 
redis
kafka
db + 乐观锁


redis 实现自动补全：
参数传zset key 和查询参数
redisTemplate.execute
lua = "local startStr = ARGV[1]..'`'\n" +
                "local endStr = ARGV[1]..'{'\n" +
                "local result = {}\n" +
                "redis.call('zadd',KEYS[1],0,startStr)\n" +
                "redis.call('zadd',KEYS[1],0,endStr)\n" +
                "local startIndex = redis.call('zrank',KEYS[1],startStr)+1\n" +
                "local endIndex = redis.call('zrank',KEYS[1],endStr)-1\n" +
                "result = redis.call('zrange',KEYS[1],startIndex,endIndex)\n" +
                "redis.call('zrem',KEYS[1],startStr,endStr)\n" +
                "return result";