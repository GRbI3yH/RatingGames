package ru.game.rate.main.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.game.rate.main.service.dtoConverter.DtoConverter;
import ru.game.rate.main.service.dtoConverter.GameConverter;
import ru.game.rate.main.service.dtoConverter.SystemRequirementsConverter;

@Configuration
public class ConverterConfiguration {

    @Bean
    public GameConverter gameConverter(){
        return new GameConverter();
    }

    @Bean
    public SystemRequirementsConverter systemRequirementsConverter(){
        return new  SystemRequirementsConverter();
    }

    @Bean
    public DtoConverter dtoConverter(){
        return new DtoConverter();
    }
}
