package be.fooda.backend.store.dao;


import be.fooda.backend.store.model.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class StoreSearch {

    private final EntityManager entityManager;

    private FullTextEntityManager entityManager() {
        return Search.getFullTextEntityManager(entityManager);
    }

    private static final Integer RESULTS_PER_PAGE = 25;

    @Transactional
    public List<StoreEntity> searchByStoreName(String storeName, int pageNo, int pageSize) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        Query storeNameQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("storeName")
                .matching(storeName)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(storeNameQuery, StoreEntity.class);

        //  fullTextQuery.setFirstResult(pageNo );
        //  fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }


    @Transactional
    public List<StoreEntity> searchByAddress(String postcode, int pageNo, int pageSize) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query postcodeQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(1)
                .withPrefixLength(0)
                .onField("address.postcode")
                .matching(postcode)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(postcodeQuery, StoreEntity.class);
        // fullTextQuery.setFirstResult(pageNo);
        //    fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }


    @Transactional
    public List<StoreEntity> searchByAddress(String postcode, String municipality) {
        return searchByAddress(postcode, 1, RESULTS_PER_PAGE);
    }


    @Transactional
    public List<StoreEntity> searchByAddress(String postcode, String municipality, int pageNo, int pageSize) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query postcodeQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(1)
                .withPrefixLength(0)
                .onField("address.postcode")
                .matching(postcode)
                .createQuery();


        //Generate a Lucene query using the builder
        Query municipalityQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("address.municipality")
                .matching(municipality)
                .createQuery();

        Query addressQuery = qb.bool()
                .should(postcodeQuery)
                .should(municipalityQuery)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(addressQuery, StoreEntity.class);
        //   fullTextQuery.setFirstResult(pageNo);
        //   fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    //TODO
    public StoreEntity filterByGeolocation(String latitude, String longitude) {
        return null;
    }


    @Transactional
    public List<StoreEntity> filterByDeliveryCosts(BigDecimal minCost, BigDecimal maxCost, int pageNo, int pageSize) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("deliveryLocations.deliveryCost")
                .from(minCost)
                .to(maxCost)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);
//   fullTextQuery.setFirstResult(pageNo);
//        fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<StoreEntity> filterByDeliveryDuration(Double minDuration, Double maxDuration, int pageNo, int pageSize) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("deliveryLocations.deliveryTime")
                .from(minDuration)
                .to(maxDuration)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

        //    fullTextQuery.setFirstResult(pageNo);
        //  fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }


 /*   @Transactional
    public List<FoodaStore> filterByDeliveryLocations(String postcode, int pageNo, int pageSize ) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query postcodeQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("deliveryLocations.postcode")
                .matching(postcode)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(postcodeQuery, FoodaStore.class);
        fullTextQuery.setFirstResult(pageNo);
        fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<FoodaStore>) fullTextQuery.getResultList();
    }*/

    public List<StoreEntity> filterByDeliveryLocations(Set<String> postcodes, int pageNo, int pageSize) {
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                qBuilder(fullTextEntityManager);


        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = postcodes.stream()
                .map(postcode -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onField("deliveryLocations.postcode")
                        .matching(postcode)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, StoreEntity.class);
        //   jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //    jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<StoreEntity> results = jpaQuery.getResultList();
        return results;
    }


    @Transactional
    public List<StoreEntity> searchByMenuItemsName(String productName, int pageNo, int pageSize) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query menuItemQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("menuItems.productName")
                .matching(productName)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(menuItemQuery, StoreEntity.class);
        //    fullTextQuery.setFirstResult(pageNo);
        //    fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }



   /* @Transactional
    public List<FoodaStore> searchByMenuItems(Set<String> menuItems, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                qBuilder(fullTextEntityManager);


        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = menuItems.stream()
                .map(menuItem -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onField("menuItems.productName")
                        .matching(menuItem)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, FoodaStore.class);
        jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<FoodaStore> results = jpaQuery.getResultList();
        return results;
    }*/

    @Transactional
    public List<StoreEntity> filterByMenuItemsPrice(BigDecimal maxPrice, int pageNo, int pageSize) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("menuItems.price")
                .from(0.00)
                .to(maxPrice.doubleValue())
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

        //    fullTextQuery.setFirstResult(pageNo);
        //  fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    public List<StoreEntity> searchByCuisine(String cuisine, Integer pageNo, Integer pageSize) {
        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query query = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("menuItems.cuisine")
                .matching(cuisine)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, StoreEntity.class);
        //  fullTextQuery.setMaxResults(pageNo);
        //   fullTextQuery.setFirstResult(pageSize);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    public List<StoreEntity> searchByDietary(String dietary, Integer pageNo, Integer pageSize) {
        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //Generate a Lucene query using the builder
        Query query = qb
                .keyword()
                .onField("menuItems.dietary")
                .matching(dietary)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, StoreEntity.class);
        //    fullTextQuery.setMaxResults(RESULTS_PER_PAGE);
        //   fullTextQuery.setFirstResult(1);

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<StoreEntity> filterByMinimumOrderPrice(BigDecimal minOrderPrice, int pageNo, int pageSize) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                qBuilder(fullTextEntityManager);


        Query minimumOrderPriceQuery = queryBuilder
                .range()
                .onField("deliveryLocations.minOrderPrice")
                .from(BigDecimal.valueOf(0))
                .to(minOrderPrice)
                .createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(minimumOrderPriceQuery, StoreEntity.class);
        jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<StoreEntity> results = jpaQuery.getResultList();
        return results;
    }

    public List<StoreEntity> searchByCategories(Set<String> categories) {
        FullTextEntityManager fullTextEntityManager = entityManager();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                qBuilder(fullTextEntityManager);


        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = categories.stream()
                .map(category -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onField("menuItems.categories")
                        .matching(category)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, StoreEntity.class);
        //   jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        //    jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<StoreEntity> results = jpaQuery.getResultList();
        return results;
    }


    @Transactional
    public List<StoreEntity> searchCombined(Set<String> keywords) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = Search.
                getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(StoreEntity.class).get();

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = keywords.stream()
                .map(keyword -> {

                    //search by store name ..
                    Query storeNameQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("storeName")
                            .matching(keyword)
                            .createQuery();

                    //Postcode search ..
                    Query postcodeQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(1)
                            .withPrefixLength(0)
                            .onField("address.postcode")
                            .matching(keyword)
                            .createQuery();


                    //Generate a Lucene query using the builder
                    Query municipalityQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("address.municipality")
                            .matching(keyword)
                            .createQuery();

                    //Generate a Lucene query using the builder
                    Query menuItemNameQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("menuItems.productName")
                            .matching(keyword)
                            .createQuery();


                    //Generate a Lucene query using the builder
                    Query menuItemCuisineQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("menuItems.cuisine")
                            .matching(keyword)
                            .createQuery();


                    // a combined query from all sub queries for each keyword..
                    return queryBuilder
                            .bool()
                            .should(storeNameQuery) // fuzzy search
                            .should(postcodeQuery) // s// fuzzy search ..
                            .should(municipalityQuery) // s// fuzzy search ..
                            .should(menuItemNameQuery) // fuzzy search
                            //  .should(menuItemPriceQuery) // range -> 0 ... max: 25.00 (keyword)
                            .should(menuItemCuisineQuery) // fuzzy
                            //    .should(minimumOrderPriceQuery) // range .. -> max is the keyword .. . 0 - 25.00..
                            //   .should(deliveryCostQuery) // range -> 0 ... max: 25.00 (keyword)
                            //  .should(deliveryTimeDurationQuery) // range -> 0 ... max: 45.00 (keyword)
                            .createQuery();
                })
                .collect(Collectors.toList());

        List<Query> deliveryPostcodeQueries = keywords.stream()
                .map(postcode -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onField("deliveryLocations.postcode")
                        .matching(postcode)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : deliveryPostcodeQueries) {
            combinedJunction.should(query);
        }

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, StoreEntity.class);
        //    jpaQuery.setFirstResult(pageNo);
        //     jpaQuery.setMaxResults(pageSize);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<StoreEntity> results = jpaQuery.getResultList();
        return results;
    }


    @Transactional
    public List<StoreEntity> searchCombinedRange(Set<String> keywords) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = Search.
                getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(StoreEntity.class).get();

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = keywords.stream()
                .map(keyword -> {


                    Query menuItemPriceQuery = queryBuilder
                            .range()
                            .onField("menuItems.price")
                            .from(0.00)
                            .to(Double.valueOf(keyword).doubleValue())
                            .createQuery();


                    Query deliveryCostQuery = queryBuilder
                            .range()
                            .onField("deliveryLocations.deliveryCost")
                            .from(0.00)
                            .to(Double.valueOf(keyword).doubleValue())
                            .createQuery();


                    Query deliveryTimeDurationQuery = queryBuilder
                            .range()
                            .onField("deliveryLocations.deliveryTime")
                            .from(0.00)
                            .to(Double.valueOf(keyword).doubleValue()).excludeLimit()
                            .createQuery();


                    // a combined query from all sub queries for each keyword..
                    return queryBuilder
                            .bool()
                            // .should(storeNameQuery) // fuzzy search
                            //  .should(postcodeQuery) // s// fuzzy search ...should(municipalityQuery) // s// fuzzy search ..
                            //    .should(menuItemNameQuery) // fuzzy search
                            .should(menuItemPriceQuery) // range -> 0 ... max: 25.00 (keyword)
                            //    .should(menuItemCuisineQuery) // fuzzy
                            //     .should(minimumOrderPriceQuery) // range .. -> max is the keyword .. . 0 - 25.00..
                            .should(deliveryCostQuery) // range -> 0 ... max: 25.00 (keyword)
                            .should(deliveryTimeDurationQuery) // range -> 0 ... max: 45.00 (keyword)
                            .createQuery();
                })
                .collect(Collectors.toList());

        List<Query> deliveryPostcodeQueries = keywords.stream()
                .map(postcode -> queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(0)
                        .onField("deliveryLocations.postcode")
                        .matching(postcode)
                        .createQuery())
                .collect(Collectors.toList());

        for (Query query : deliveryPostcodeQueries) {
            combinedJunction.should(query);
        }

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, StoreEntity.class);
        //    jpaQuery.setFirstResult(pageNo);
        //     jpaQuery.setMaxResults(pageSize);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<StoreEntity> results = jpaQuery.getResultList();
        return results;
    }

    @Transactional
    public List<StoreEntity> simple(Set<String> keywords, Pageable pageable) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .onField("menu.price")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, StoreEntity.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<StoreEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StoreEntity> fuzzy(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder with fuzzyQuery
                    Query query = qb
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("about")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<StoreEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StoreEntity> range(Long start, Long end, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("id")
                .from(start)
                .to(end)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

        fullTextQuery.setSort(qb.sort()
                .byField("name")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    private QueryBuilder qBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(StoreEntity.class)
                // .overridesForField("ngram","stem_analyzer_definition")
                .get();
    }


    @Transactional
    public List<StoreEntity> wildcard(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene wildcard query using the builder
                    Query query = qb
                            .keyword()
                            .wildcard()
                            .onField("about")
                            .matching("1*")
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<StoreEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StoreEntity> phrase(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene phrase query using the builder
                    Query query = qb
                            .phrase()
                            .withSlop(1)
                            .onField("about")
                            .sentence(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<StoreEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StoreEntity> structured(String queryString, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager fullTextEntityManager = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //The following query types are supported:
        //boolean (AND using “+”, OR using “|”, NOT using “-“)
        //prefix (prefix*)
        //phrase (“some phrase”)
        //precedence (using parentheses)
        //fuzzy (fuzy~2)
        //near operator for phrase queries (“some phrase”~3)
        Query query = qb
                .simpleQueryString()
                .onField("about")
                .matching(queryString)
                .createQuery();


        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, StoreEntity.class);

        fullTextQuery.setSort(qb.sort()
                .byField("name")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<StoreEntity>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<StoreEntity> subclass(Set<String> keywords, Pageable pageable) {

        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .onFields(
                                    "address.postcode",
                                    "address.municipality",
                                    "address.city",
                                    "bgImage.url",
                                    "bgVideo.url",
                                    "logoImage.url",
                                    "contact.phone",
                                    "contact.email",
                                    "contact.firstName",
                                    "contact.lastName",
                                    "menu.dietary",
                                    "menu.cuisine",
                                    "menu.product",
                                    "menu.price",
                                    "auth.authKey",
                                    "auth.secret",
                                    //       "auth.expiryDate",
                                    "acceptedPaymentMethods.minOrderAmount",
                                    //  "acceptedPaymentMethods.expiryDate",
                                    "deliveryLocations.postcode",
                                    //      "deliveryLocations.deliveryTime",
                                    "deliveryLocations.minOrderPrice",
                                    "deliveryLocations.maxOrderPrice",
                                    "deliveryLocations.deliveryCost"
                                    //   "workingHours.workingDate",
                                    //   "workingHours.openTime",
                                    //   "workingHours.closeTime"
                            )
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, StoreEntity.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<StoreEntity>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }


}
/*
QueryBuilder mythQB = searchFactory.buildQueryBuilder()
    .forEntity( Myth.class )
        .overridesForField("history","stem_analyzer_definition")
    .get();
@AnalyzerDef(name = "ngram",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class ),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = StopFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class,
                        params = {
                                @Parameter(name = "minGramSize", value = "3"),
                                @Parameter(name = "maxGramSize", value = "3") } )
        }
)


 */