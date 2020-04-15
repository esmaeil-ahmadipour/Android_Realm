package ir.ea2.android_realm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ir.ea2.android_realm.dao.CategoryDAO;
import ir.ea2.android_realm.dao.UserDAO;
import ir.ea2.android_realm.model.Category;
import ir.ea2.android_realm.model.User;

public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO = new UserDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveCategory();
        updateCategory();
        findAllCategory();
//        saveData();
//        updateData();
//        findAllData();
//        findByParameters();
//        findByEmail("User2@gmail.com");
//        deleteByEmail("User2@gmail.com");
//        deleteAllData();

    }

    private void updateCategory() {
        Category category = new Category();
        category.setId(1);
        category.setName("Shoes");
        categoryDAO.save(category);
    }

    private void findAllCategory() {
        categoryDAO.findAll();
    }

    private void saveCategory() {
        Category category = new Category();
        category.setName("Food");
        categoryDAO.save(category);
    }

    private void findByParameters() {
        userDAO.findByMultiParameters("", "User9");
        userDAO.findByMultiParameters("User2@gmail.com", "");

    }

    private void deleteAllData() {
        userDAO.deleteAll();
    }

    private void deleteByEmail(String email) {
        userDAO.deleteByEmail(email);
    }

    private void findByEmail(String email) {
        userDAO.findByEmail(email);
    }

    private void findAllData() {
        userDAO.findAll();
    }

    private void saveData() {
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("User1@gmail.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("User2@gmail.com");

        userDAO.save(user1);
        userDAO.save(user2);
    }

    private void updateData() {

        User user2 = new User();
        user2.setName("User9");
        user2.setEmail("User9@gmail.com");

        userDAO.updateData(user2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
        categoryDAO.close();
    }
}
