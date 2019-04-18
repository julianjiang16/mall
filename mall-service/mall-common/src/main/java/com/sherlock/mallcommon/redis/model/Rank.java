package com.sherlock.mallcommon.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2015-2019
 * FileName: Rank
 * Author:   jcj
 * Date:     2019/4/10 9:50
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    private String id;
    private String name;
    private double score;
}
