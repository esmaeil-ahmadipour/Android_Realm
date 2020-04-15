package ir.ea2.android_realm.dao;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import ir.ea2.android_realm.model.Category;

public class CategoryDAO {
    private Realm realm;

    public CategoryDAO() {
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<Category> findAll() {
        RealmResults<Category> realmResults = realm.where(Category.class).findAll();

        try {
            for (Category category : realmResults) {
                Log.i("REALM_TAG", category.toString());
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return realmResults;
    }

    public void save(final Category category) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // * Doing Increment ID .
                Number currentId = realm.where(Category.class).max("id");
                long nextId;
                if (category.getId() != 0) {
                    nextId = category.getId();
                } else if (currentId == null) {
                    nextId = 1;
                } else {
                    nextId = currentId.longValue() + 1;
                }

                category.setId(nextId);
                realm.copyToRealmOrUpdate(category);
            }
        });
    }

    public void close() {
        realm.close();
    }
}
