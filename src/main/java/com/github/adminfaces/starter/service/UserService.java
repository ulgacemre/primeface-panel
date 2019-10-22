/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;

import com.github.adminfaces.starter.model.User;
import com.github.adminfaces.template.exception.BusinessException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 *         Car Business logic
 */
@Stateless
public class UserService implements Serializable {

    @Inject
    List<User> allUsers;

    public List<User> listByModel(String model) {
        return allUsers.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<User> paginate(Filter<User> filter) {
        List<User> pagedUsers = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                pagedUsers = allUsers.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId().compareTo(c2.getId());
                        } else {
                            return c2.getId().compareTo(c1.getId());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int page = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            pagedUsers = pagedUsers.subList(filter.getFirst(), page > allUsers.size() ? allUsers.size() : page);
            return pagedUsers;
        }

        List<Predicate<User>> predicates = configFilter(filter);

        List<User> pagedList = allUsers.stream().filter(predicates
                .stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toList());

        if (page < pagedList.size()) {
            pagedList = pagedList.subList(filter.getFirst(), page);
        }

        if (has(filter.getSortField())) {
            pagedList = pagedList.stream().
                    sorted((c1, c2) -> {
                        boolean asc = SortOrder.ASCENDING.equals(filter.getSortOrder());
                        if (asc) {
                            return c1.getId().compareTo(c2.getId());
                        } else {
                            return c2.getId().compareTo(c1.getId());
                        }
                    })
                    .collect(Collectors.toList());
        }
        return pagedList;
    }

    private List<Predicate<User>> configFilter(Filter<User> filter) {
        List<Predicate<User>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<User> idPredicate = (User c) -> c.getId().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
            Predicate<User> minMaxPricePredicate = (User c) -> c.getPrice()
                    >= Double.valueOf((String) filter.getParam("minPrice")) && c.getPrice()
                    <= Double.valueOf((String) filter.getParam("maxPrice"));
            predicates.add(minMaxPricePredicate);
        } else if (filter.hasParam("minPrice")) {
            Predicate<User> minPricePredicate = (User c) -> c.getPrice()
                    >= Double.valueOf((String) filter.getParam("minPrice"));
            predicates.add(minPricePredicate);
        } else if (filter.hasParam("maxPrice")) {
            Predicate<User> maxPricePredicate = (User c) -> c.getPrice()
                    <= Double.valueOf((String) filter.getParam("maxPrice"));
            predicates.add(maxPricePredicate);
        }

        if (has(filter.getEntity())) {
            User filterEntity = filter.getEntity();
            if (has(filterEntity.getModel())) {
                Predicate<User> modelPredicate = (User c) -> c.getModel().toLowerCase().contains(filterEntity.getModel().toLowerCase());
                predicates.add(modelPredicate);
            }

            if (has(filterEntity.getPrice())) {
                Predicate<User> pricePredicate = (User c) -> c.getPrice().equals(filterEntity.getPrice());
                predicates.add(pricePredicate);
            }

            if (has(filterEntity.getName())) {
                Predicate<User> namePredicate = (User c) -> c.getName().toLowerCase().contains(filterEntity.getName().toLowerCase());
                predicates.add(namePredicate);
            }
        }
        return predicates;
    }

    public List<String> getModels(String query) {
        return allUsers.stream().filter(c -> c.getModel()
                .toLowerCase().contains(query.toLowerCase()))
                .map(User::getModel)
                .collect(Collectors.toList());
    }

    public void insert(User user) {
        validate(user);
        user.setId(allUsers.stream()
                .mapToInt(c -> c.getId())
                .max()
                .getAsInt()+1);
        allUsers.add(user);
    }

    public void validate(User user) {
        BusinessException be = new BusinessException();
        if (!user.hasModel()) {
            be.addException(new BusinessException("user model cannot be empty"));
        }
        if (!user.hasName()) {
           be.addException(new BusinessException("user name cannot be empty"));
        }

        if (!has(user.getPrice())) {
            be.addException(new BusinessException("user price cannot be empty"));
        }

        if (allUsers.stream().filter(c -> c.getName().equalsIgnoreCase(user.getName())
                && c.getId() != c.getId()).count() > 0) {
            be.addException(new BusinessException("user name must be unique"));
        }
        if(has(be.getExceptionList())) {
            throw be;
        }
    }


    public void remove(User user) {
        allUsers.remove(user);
    }

    public long count(Filter<User> filter) {
        return allUsers.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

    public User findById(Integer id) {
        return allUsers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException("user not found with id " + id));
    }

    public void update(User user) {
        validate(user);
        allUsers.remove(allUsers.indexOf(user));
        allUsers.add(user);
    }
}
