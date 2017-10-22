package ru.game.rate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import ru.game.rate.main.configure.DataBaseConfiguration;
import ru.game.rate.main.service.domain.*;
import ru.game.rate.main.service.repository.RepositoryGame;

import java.util.*;

@SpringBootApplication
@EnableAutoConfiguration
@Import({
        DataBaseConfiguration.class
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
        Set<Genre> genres = new HashSet<Genre>();
        genres.add(new Genre(game, GenreType.ACTION));
        game.setGenres(genres);
        List<SystemRequirements> requirements1 = new ArrayList< SystemRequirements>();
        requirements1.add(new SystemRequirements(game,"цпу",6,"видео",14.2));
        game.setSystemRequirements(requirements1);
        repo.save(game);

    }
}
