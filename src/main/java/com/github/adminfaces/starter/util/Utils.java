package com.github.adminfaces.starter.util;


import com.github.adminfaces.starter.model.User;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by rmpestano on 07/02/17.
 */
@ApplicationScoped
public class Utils implements Serializable {

    private List<User> users;


    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        IntStream.rangeClosed(1, 50)
                .forEach(i -> users.add(create(i)));
    }

    private static User create(int i) {
        return new User(i).model("model " + i).name("name" + i).price(Double.valueOf(i));
    }

    public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }

    public static void addDetailMessage(String message, FacesMessage.Severity severity) {

        FacesMessage facesMessage = Messages.create("").detail(message).get();
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }

    @Produces
    public List<User> getCars() {
        return users;
    }

}
