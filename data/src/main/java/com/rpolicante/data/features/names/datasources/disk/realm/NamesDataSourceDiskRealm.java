package com.rpolicante.data.features.names.datasources.disk.realm;

import com.rpolicante.data.features.names.datasources.disk.NamesDataSourceDisk;
import com.rpolicante.data.features.names.datasources.disk.realm.entities.NamesDiskRealm;
import com.rpolicante.data.features.names.datasources.disk.realm.entities.NamesDiskRealmMapper;
import com.rpolicante.domain.Names;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by policante on 1/19/16.
 */
public class NamesDataSourceDiskRealm implements NamesDataSourceDisk {

    private final NamesDiskRealmMapper mapper;

    public NamesDataSourceDiskRealm(NamesDiskRealmMapper namesDiskRealmMapper){
        if (namesDiskRealmMapper == null){
            throw new IllegalArgumentException("namesDiskRealmMapper cannot be null!!!");
        }
        this.mapper = namesDiskRealmMapper;
    }

    @Override
    public Observable<Names> findByID(final Integer identifier) {
        return Observable.create(new Observable.OnSubscribe<Names>() {

            @Override
            public void call(Subscriber<? super Names> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    NamesDiskRealm result = realm.where(NamesDiskRealm.class)
                            .equalTo("id",identifier)
                            .findFirst();

                    subscriber.onNext(mapper.dataToModel(result));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<List<Names>> getNames() {
        return Observable.create(new Observable.OnSubscribe<List<Names>>() {
            @Override
            public void call(Subscriber<? super List<Names>> subscriber) {
                Realm realm = null;
                List<Names> listNames = new ArrayList<>();

                try{
                    realm = Realm.getDefaultInstance();
                    RealmResults<NamesDiskRealm> results = realm.allObjects(NamesDiskRealm.class);

                    if (!results.isEmpty()){
                        for (NamesDiskRealm ndr : results) {
                            listNames.add( mapper.dataToModel(ndr) );
                        }
                    }

                    Collections.reverse(listNames);

                    subscriber.onNext(listNames);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                } finally {
                    if (realm != null){
                        realm.close();
                    }
                }

            }
        });
    }

    @Override
    public Observable<Integer> saveName(final Names names) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    NamesDiskRealm namesDiskRealm = mapper.modelToData(names);
                    realm.copyToRealm(namesDiskRealm);
                    realm.commitTransaction();

                    subscriber.onNext( namesDiskRealm.getId() );
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<Boolean> removeName(final Names name) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    NamesDiskRealm result = realm.where(NamesDiskRealm.class)
                            .equalTo("id",name.getId())
                            .findFirst();

                    result.removeFromRealm();

                    realm.commitTransaction();

                    boolean isEmpty = realm.allObjects(NamesDiskRealm.class).size() <= 0;

                    subscriber.onNext( isEmpty );
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

}
