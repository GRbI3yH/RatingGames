package ru.game.rate.main.service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.domain.Genre;
import ru.game.rate.main.service.domain.GenreType;
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
    public void delete(String id) {
        Game game = em.find(Game.class, id);
        if (isNull(game)) throw new IllegalArgumentException("Entity is not found");
        em.remove(game);
    }

    @Override
    public Game get(String id) {
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
        List<Predicate> predicates = new ArrayList<>();
        //Предикаты
        buildPredicateGame(searchCriteria, criteriaBuilder, gameRoot, predicates);
        buildPredicateGenresJoin(searchCriteria, criteriaBuilder, gameRoot, predicates);
        buildPredicateSystemRequirementsJoin(searchCriteria, criteriaBuilder, gameRoot, predicates);
        criteriaQuery.select(gameRoot).where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(criteriaQuery).getResultList();
    }

    private void buildPredicateGame(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates){
        buildDatePredicate(searchCriteria, criteriaBuilder, root, predicates);
        buildAssessmentPredicate(searchCriteria, criteriaBuilder, root, predicates);
        buildPricePredicate(searchCriteria, criteriaBuilder, root, predicates);
        if(nonNull(searchCriteria.getName()))
            predicates.add(criteriaBuilder.like(root.get("name"),partialCoincidence(searchCriteria.getName())));
        if(nonNull(searchCriteria.getLicense()))
            predicates.add(criteriaBuilder.like(root.get("license"),partialCoincidence(searchCriteria.getLicense())));
        if(nonNull(searchCriteria.getPlatform()))
            predicates.add(criteriaBuilder.equal(root.get("platform"),searchCriteria.getPlatform()));
        if(nonNull(searchCriteria.getDeveloper()))
            predicates.add(criteriaBuilder.like(root.get("developer"),partialCoincidence(searchCriteria.getDeveloper())));
        if(nonNull(searchCriteria.getPublisher()))
            predicates.add(criteriaBuilder.like(root.get("publisher"),partialCoincidence(searchCriteria.getPublisher())));
    }

    private void buildPredicateGenresJoin(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates){
        if(nonNull(searchCriteria.getGenres())){
            if(searchCriteria.getGenres() == null){return;}
            Join<Game, Genre> genresJoin = root.join("genres",JoinType.INNER);
            predicates.add(criteriaBuilder.equal(genresJoin.get("genre"),searchCriteria.getGenres()));
        }
    }

    private void buildPredicateSystemRequirementsJoin(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates){
        if(nonNull(searchCriteria.getSystemRequirements())){
            Join<Game,SystemRequirements> systemRequirementsJoin = root.join("systemRequirements",JoinType.INNER);
            if(nonNull(searchCriteria.getSystemRequirements().getCpu())){
                predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("cpu"),partialCoincidence(searchCriteria.getSystemRequirements().getCpu())));
            }
            if(nonNull(searchCriteria.getSystemRequirements().getDiskSpace())){
                predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("diskSpace"),searchCriteria.getSystemRequirements().getDiskSpace()));
            }
            if(nonNull(searchCriteria.getSystemRequirements().getRam())){
                predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("ram"),searchCriteria.getSystemRequirements().getRam()));
            }
            if(nonNull(searchCriteria.getSystemRequirements().getVideoCard())){
                predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("videoCard"),partialCoincidence(searchCriteria.getSystemRequirements().getVideoCard())));
            }
            if(nonNull(searchCriteria.getSystemRequirements().getType())){
                predicates.add(criteriaBuilder.equal(systemRequirementsJoin.get("type"),searchCriteria.getSystemRequirements().getType()));
            }
        }
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
