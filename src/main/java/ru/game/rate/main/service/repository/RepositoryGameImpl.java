package ru.game.rate.main.service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.domain.Genre;
import ru.game.rate.main.service.domain.SystemRequirements;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
@Transactional
public class RepositoryGameImpl implements RepositoryGame{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Game save(Game game) {
        if (isNew(game)) {
            em.persist(game);
//            em.flush();
            return game;
        } else {
            return em.merge(game);
        }
    }

    @Override
    public void delete(Integer id) {
        Game game = em.find(Game.class, id);
        if (isNull(game)) throw new IllegalArgumentException("Entity is not found");
        em.remove(game);
    }

    @Override
    public Game get(Integer id) {
        return em.find(Game.class, id);
    }

    @Override
    public List<Game> findAll() {
        return em.createNamedQuery("Game.findAll", Game.class).getResultList();
    }

    @Override
    public List<Game> findByCriteria(GameSearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Game> gameRoot = criteriaQuery.from(Game.class);
        Join<Game, Genre> genresJoin = gameRoot.join("genres");
        Join<Game,SystemRequirements> systemRequirementsJoin = gameRoot.join("systemRequirements");


//        criteriaQuery.select(gameRoot);
//        List<Game> gameList = (List<Game>) em.createQuery(criteriaQuery).getResultList();

//        Root<Genre> genreRoot = criteriaQuery.from(Genre.class);
//        criteriaQuery.select(genreRoot);
//        List<Genre> genreList = (List<Genre>) em.createQuery(criteriaQuery).getResultList();
//        for (Genre genre :genreList){
//            System.out.println(genre);
//        }


        List<Predicate> predicates = new ArrayList<>();
        //Предикаты

        buildDatePredicate(searchCriteria, criteriaBuilder, gameRoot, predicates);

        buildAssessmentPredicate(searchCriteria, criteriaBuilder, gameRoot, predicates);

        buildPricePredicate(searchCriteria, criteriaBuilder, gameRoot, predicates);

        if(nonNull(searchCriteria.getName()))
            predicates.add(criteriaBuilder.like(gameRoot.get("name"),partialCoincidence(searchCriteria.getName())));

        if(nonNull(searchCriteria.getLicense()))
            predicates.add(criteriaBuilder.like(gameRoot.get("license"),partialCoincidence(searchCriteria.getLicense())));

        if(nonNull(searchCriteria.getPlatform()))
            predicates.add(criteriaBuilder.equal(gameRoot.get("platform"),searchCriteria.getPlatform()));

        if(nonNull(searchCriteria.getDeveloper()))
            predicates.add(criteriaBuilder.like(gameRoot.get("developer"),partialCoincidence(searchCriteria.getDeveloper())));

        if(nonNull(searchCriteria.getPublisher()))
            predicates.add(criteriaBuilder.like(gameRoot.get("publisher"),partialCoincidence(searchCriteria.getPublisher())));

        if(nonNull(searchCriteria.getRequirements())){
            predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("cpu"),partialCoincidence(searchCriteria.getRequirements().getCpu())));
            predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("diskSpace"),searchCriteria.getRequirements().getDiskSpace()));
            predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("ram"),searchCriteria.getRequirements().getRam()));
            predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("videoCard"),partialCoincidence(searchCriteria.getRequirements().getVideoCard())));
            predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("type"),searchCriteria.getRequirements().getType()));
        }

        if(nonNull(searchCriteria.getGenres())){
            predicates.add(criteriaBuilder.equal(genresJoin.get("genre"),searchCriteria.getGenres()));
        }

        criteriaQuery.select(gameRoot).where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(criteriaQuery).getResultList();
    }

    private void buildDatePredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if (nonNull(searchCriteria.getStartDate()) && nonNull(searchCriteria.getEndDate())) {
            Predicate predicateStart = criteriaBuilder.greaterThanOrEqualTo(root.get("date"), searchCriteria.getStartDate());
            Predicate predicateEnd = criteriaBuilder.lessThanOrEqualTo(root.get("date"), searchCriteria.getEndDate());
            predicates.add(criteriaBuilder.and(predicateStart, predicateEnd));
        } else if (nonNull(searchCriteria.getStartDate())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), searchCriteria.getStartDate()));
        } else if (nonNull(searchCriteria.getEndDate())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), searchCriteria.getEndDate()));
        }
    }

    private void buildAssessmentPredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if(nonNull(searchCriteria.getStartAssessment()) && nonNull(searchCriteria.getEndAssessment())){
            predicates.add(criteriaBuilder.between(root.get("assessment"),searchCriteria.getStartAssessment(),searchCriteria.getEndAssessment()));
        } else if (!(searchCriteria.getStartAssessment() == null)){
            predicates.add(criteriaBuilder.ge(root.get("assessment"), searchCriteria.getStartAssessment()));
        } else if (!(searchCriteria.getEndAssessment() == null)){
            predicates.add(criteriaBuilder.le(root.get("assessment"), searchCriteria.getEndAssessment()));
        }
    }

    private void buildPricePredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if(nonNull(searchCriteria.getStartPrice()) && nonNull(searchCriteria.getEndPrice())){
            predicates.add(criteriaBuilder.between(root.get("price"), searchCriteria.getStartPrice(), searchCriteria.getEndPrice()));
        } else if (nonNull(searchCriteria.getStartPrice())){
            predicates.add(criteriaBuilder.ge(root.get("price"), searchCriteria.getStartPrice()));
        } else if (nonNull(searchCriteria.getEndPrice())){
            predicates.add(criteriaBuilder.le(root.get("price"), searchCriteria.getEndPrice()));
        }
    }

    private String partialCoincidence(String str){
        return "%"+str+"%";
    }

    private boolean isNew(Game game) {
        return isNull(game.getId());
    }
}
