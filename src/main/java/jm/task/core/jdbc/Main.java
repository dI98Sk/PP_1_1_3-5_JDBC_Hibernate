package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        User user1 = new User("Dmitrii", "Skakun", (byte) 25);
        User user2 = new User("Alex", "Smith", (byte) 44);
        User user3 = new User("Dan", "Polonskii", (byte) 18);
        User user4 = new User("Ben", "Mask", (byte) 14);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        for (User elem : userList) {
            service.saveUser(elem.getName(), elem.getLastName(), elem.getAge());
            System.out.println("User с именем - " + elem.getName() + " добавлен в базу данных");
        }

        List<User> select = service.getAllUsers();
        select.stream().forEach(System.out::println);

        service.cleanUsersTable();

        service.dropUsersTable();
    }
}
