package ru.game.rate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import ru.game.rate.main.configure.DataBaseConfiguration;
import ru.game.rate.main.configure.RestConfiguration;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.domain.GenreType;
import ru.game.rate.main.service.domain.Platform;
import ru.game.rate.main.service.domain.SystemRequirements;
import ru.game.rate.main.service.repository.RepositoryGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@Import({
        DataBaseConfiguration.class,
        RestConfiguration.class
})
@ComponentScans({
        @ComponentScan("ru.game.rate.main.service"),
        @ComponentScan("ru.game.rate.main.facade"),
        @ComponentScan("ru.game.rate.main.rest"),
})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
        RepositoryGame repo = run.getBean(RepositoryGame.class);

        Game game = new Game();
        game.setName("game 1");
        game.setDate(new Date());
        game.setAssessment(Byte.parseByte("3"));
        game.setLicense("MIT");
        game.setPlatform(Platform.WINDOWS);
        game.setDeveloper("Dev");
        List<GenreType> genres = new ArrayList<>();
        genres.add(GenreType.ACTION);
        genres.add(GenreType.QUEST);
        game.setGenres(genres);
        List<SystemRequirements> requirements1 = new ArrayList< SystemRequirements>();
        requirements1.add(new SystemRequirements(game,"цпу",6,"видео",14.2));
        game.setSystemRequirements(requirements1);
        repo.save(game);

    }
}
