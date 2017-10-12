package ru.game.rate.main.service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        return em.createNamedQuery("Game.findAll").getResultList();
    }

    @Override
    public List<Game> findByCriteria(GameSearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Game> root = criteriaQuery.from(Game.class);

        List<Predicate> predicates = new ArrayList<>();

        //Предикаты

        if(nonNull(searchCriteria.getAssessment())){
            buildDatePredicate(searchCriteria, criteriaBuilder, root, predicates);
        }

        if(nonNull(searchCriteria.getPriceRange())){
            buildAssessmentPredicate(searchCriteria, criteriaBuilder, root, predicates);
        }

        if(nonNull(searchCriteria.getDateRange())){
            buildPricePredicate(searchCriteria, criteriaBuilder, root, predicates);
        }

        if(nonNull(searchCriteria.getName()))
            predicates.add(criteriaBuilder.like(root.get("name"),searchCriteria.getName()));

        if(nonNull(searchCriteria.getLicense()))
            predicates.add(criteriaBuilder.like(root.get("license"),searchCriteria.getLicense()));

        if(nonNull(searchCriteria.getPlatform()))
            predicates.add(criteriaBuilder.like(root.get("platform"),searchCriteria.getPlatform().name()));

        if(nonNull(searchCriteria.getDeveloper()))
            predicates.add(criteriaBuilder.like(root.get("developer"),searchCriteria.getDeveloper()));

        if(nonNull(searchCriteria.getPublisher()))
            predicates.add(criteriaBuilder.like(root.get("publisher"),searchCriteria.getPublisher()));

        if(nonNull(searchCriteria.getRequirements()))
            predicates.add(criteriaBuilder.equal(root.get("systemRequirements"),searchCriteria.getRequirements()));

        if(nonNull(searchCriteria.getGenres()))
            predicates.add(criteriaBuilder.in(root.get("genre")).in(searchCriteria.getGenres()));
//        predicates.add(root.get("genre").in(searchCriteria.getGenres()));

        criteriaQuery.select(root).where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
        return em.createQuery(criteriaQuery).getResultList();
    }

    private void buildDatePredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if (nonNull(searchCriteria.getDateRange().getStartRange()) && nonNull(searchCriteria.getDateRange().getEndRange())) {
            Predicate predicateStart = criteriaBuilder.greaterThanOrEqualTo(root.get("date"), searchCriteria.getDateRange().getStartRange());
            Predicate predicateEnd = criteriaBuilder.lessThanOrEqualTo(root.get("date"), searchCriteria.getDateRange().getEndRange());
            predicates.add(criteriaBuilder.and(predicateStart, predicateEnd));

        } else if (nonNull(searchCriteria.getDateRange().getStartRange())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), searchCriteria.getDateRange().getStartRange()));
        } else if (nonNull(searchCriteria.getDateRange().getEndRange())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), searchCriteria.getDateRange().getEndRange()));
        }
    }

    private void buildAssessmentPredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if(nonNull(searchCriteria.getAssessment().getStartRange()) && nonNull(searchCriteria.getAssessment().getEndRange())){
            predicates.add(criteriaBuilder.between(root.get("assessment"),searchCriteria.getAssessment().getStartRange(),searchCriteria.getAssessment().getEndRange()));
        } else if (!(searchCriteria.getAssessment().getStartRange() == null)){
            predicates.add(criteriaBuilder.ge(root.get("assessment"), searchCriteria.getAssessment().getStartRange()));
        } else if (!(searchCriteria.getAssessment().getEndRange() == null)){
            predicates.add(criteriaBuilder.le(root.get("assessment"), searchCriteria.getAssessment().getEndRange()));
        }
    }

    private void buildPricePredicate(GameSearchCriteria searchCriteria, CriteriaBuilder criteriaBuilder, Root<Game> root, List<Predicate> predicates) {
        if(nonNull(searchCriteria.getPriceRange().getStartRange()) && nonNull(searchCriteria.getPriceRange().getEndRange())){
            predicates.add(criteriaBuilder.between(root.get("price"), searchCriteria.getPriceRange().getStartRange(), searchCriteria.getPriceRange().getEndRange()));
        } else if (nonNull(searchCriteria.getPriceRange().getStartRange())){
            predicates.add(criteriaBuilder.ge(root.get("price"), searchCriteria.getPriceRange().getStartRange()));
        } else if (nonNull(searchCriteria.getPriceRange().getEndRange())){
            predicates.add(criteriaBuilder.le(root.get("price"), searchCriteria.getPriceRange().getEndRange()));
        }
    }

    private boolean isNew(Game game) {
        return isNull(game.getId());
    }
}
