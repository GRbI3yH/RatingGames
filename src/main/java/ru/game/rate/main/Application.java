package ru.game.rate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import ru.game.rate.main.configure.ConverterConfiguration;
import ru.game.rate.main.configure.DataBaseConfiguration;
import ru.game.rate.main.configure.RestConfiguration;
import ru.game.rate.main.service.domain.*;
import ru.game.rate.main.service.repository.RepositoryGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableAutoConfiguration
@Import({
        DataBaseConfiguration.class,
        RestConfiguration.class,
        ConverterConfiguration.class
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

        addGames(repo,10);
    }

    private static void addGames(RepositoryGame repo,Integer quantity){
        List<Game> games = new ArrayList<Game>();
        for(int i = 0; i < quantity; i++){
            games.add(generateGame(i,quantity));
        }

        for (Game game:games){
            repo.save(game);
        }
    }

    private static Integer iterGlob = 0;

    private static Game generateGame(Integer iter,Integer quantity){
        Random rand = new Random();

        Game game = new Game();
        game.setId(String.valueOf(iter));
        game.setName("game "+rand.nextInt(quantity));
        game.setDate(new Date());
        game.setAssessment(rand.nextInt(100));
        game.setLicense("MIT "+rand.nextInt(3));
        game.setPlatform(Platform.WINDOWS);
        game.setDeveloper("Dev "+rand.nextInt(3));
        game.setPrice(rand.nextInt(3000)/2.1);
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i<4; i++){
            GenreType genreType = GenreType.STRATEGY;
            switch (rand.nextInt(5)){
                case 0:
                    genreType = GenreType.ACTION;
                    break;
                case 1:
                    genreType = GenreType.ADVENTURE;
                    break;
                case 2:
                    genreType = GenreType.FIGHTING;
                    break;
                case 3:
                    genreType = GenreType.QUEST;
                    break;
                case 4:
                    genreType = GenreType.RPG;
                    break;
                case 5:
                    genreType = GenreType.SIMULATION;
                    break;
            }
            Genre genre = new Genre(genreType);
            genre.setOwner(game);
            iterGlob++;
            genre.setId(String.valueOf(iterGlob));
            genres.add(genre);
        }
        game.setGenres(genres);
        List<SystemRequirements> requirements1 = new ArrayList< SystemRequirements>();
        SystemRequirements SystemRequirements = new SystemRequirements(game,"цпу"+rand.nextInt(quantity),rand.nextInt(quantity),"видео"+rand.nextInt(quantity),(double)rand.nextInt(quantity));
        SystemRequirements.setId(String.valueOf(iter));
        requirements1.add(SystemRequirements);
        game.setSystemRequirements(requirements1);
        return game;
    }
}
