package com.chris.mobile_subscribers.config;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper extends ConfigurableMapper {

  /*  @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);

        factory.registerClassMap(factory.classMap(MsisdnMappingData.class, MsisdnDetail.class)
                .customize(new CustomMapper<MsisdnMappingData, MsisdnDetail>() {
                    @Override
                    public void mapAtoB(MsisdnMappingData msisdnMappingData, MsisdnDetail msisdnDetail, MappingContext context) {

                    }
                }).byDefault().toClassMap());
    }*/
}
