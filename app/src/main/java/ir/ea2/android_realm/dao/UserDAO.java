package ir.ea2.android_realm.dao;

import android.util.Log;

import io.realm.Realm;
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

    public void close() {
        realm.close();
    }
}
