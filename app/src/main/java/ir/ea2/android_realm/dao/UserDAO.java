package ir.ea2.android_realm.dao;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import ir.ea2.android_realm.model.User;

public class UserDAO {
    private Realm realm;

    public UserDAO() {
        realm = Realm.getDefaultInstance();
    }

    public void save(final User user) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User realObject = realm.createObject(User.class, user.getEmail());
                realObject.setName(user.getName());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i("REALM_TAG", "onSuccess");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i("REALM_TAG", "error");
                Log.i("REALM_TAG", error.getMessage().toString());
            }
        });
    }

    public RealmResults<User> findAll() {
        RealmResults<User> realmResults = realm.where(User.class).findAll();

        try {

            for (User user : realmResults) {
                Log.i("REALM_TAG", user.toString());
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return realmResults;
    }

    public User findByEmail(String email) {
        User user = realm.where(User.class).equalTo("email", email).findFirst();

        try {
            Log.i("REALM_TAG", user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void close() {
        realm.close();
    }
}
