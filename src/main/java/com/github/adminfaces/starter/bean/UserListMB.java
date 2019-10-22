package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;

import com.github.adminfaces.starter.model.User;
import com.github.adminfaces.starter.service.UserService;
import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class UserListMB implements Serializable {

    @Inject
    UserService userService;

    Integer id;

    LazyDataModel<User> users;

    Filter<User> filter = new Filter<>(new User());

    List<User> selectedCars; //cars selected in checkbox column

    List<User> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        users = new LazyDataModel<User>() {
            @Override
            public List<User> load(int first, int pageSize,
                                  String sortField, SortOrder sortOrder,
                                  Map<String, Object> filters) {
                com.github.adminfaces.starter.infra.model.SortOrder order = null;
                if (sortOrder != null) {
                    order = sortOrder.equals(SortOrder.ASCENDING) ? com.github.adminfaces.starter.infra.model.SortOrder.ASCENDING
                            : sortOrder.equals(SortOrder.DESCENDING) ? com.github.adminfaces.starter.infra.model.SortOrder.DESCENDING
                            : com.github.adminfaces.starter.infra.model.SortOrder.UNSORTED;
                }
                filter.setFirst(first).setPageSize(pageSize)
                        .setSortField(sortField).setSortOrder(order)
                        .setParams(filters);
                List<User> list = userService.paginate(filter);
                setRowCount((int) userService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public User getRowData(String key) {
                return userService.findById(new Integer(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<User>(new User());
    }

    public List<String> completeModel(String query) {
        List<String> result = userService.getModels(query);
        return result;
    }

    public void findCarById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(userService.findById(id));
    }

    public void delete() {
        int numCars = 0;
        for (User selectedCar : selectedCars) {
            numCars++;
            userService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<User> getSelectedCars() {
        return selectedCars;
    }

    public List<User> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<User> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<User> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<User> getCars() {
        return users;
    }

    public void setCars(LazyDataModel<User> cars) {
        this.users = cars;
    }

    public Filter<User> getFilter() {
        return filter;
    }

    public void setFilter(Filter<User> filter) {
        this.filter = filter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
