package com.afagoal.utildto.cache;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by BaoCai on 2019/1/5. Description:
 */
@Getter
@Setter
public class CacheDto<T> {

    private T value;

    private LocalDateTime expire;

    private CacheDto(T value, Long timeToLeave) {
        this.setValue(value);
        this.setExpire(LocalDateTime.now().plusSeconds(timeToLeave));
    }

    public static CacheDto instance(Object value, Long timeToLeave) {
        return new CacheDto(value, timeToLeave);
    }

}
