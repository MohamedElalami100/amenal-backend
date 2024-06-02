package com.amenal.amenalbackend;

import com.amenal.amenalbackend.aspects.DoubleAspect;
import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestConfig {

    @Bean
    public DoubleAspect doubleAspect() {
        return new DoubleAspect();
    }

    @Bean
    public DetailProduit detailProduit() {
        return new DetailProduit();
    }
}
