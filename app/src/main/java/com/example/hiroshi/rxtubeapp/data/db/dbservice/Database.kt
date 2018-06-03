package com.example.hiroshi.rxtubeapp.data.db.dbservice

import android.content.Context
import com.example.hiroshi.rxtubeapp.data.db.model.ConvertibleFromRealmModel
import com.example.hiroshi.rxtubeapp.data.db.model.ConvertibleToRealmModel
import com.example.hiroshi.rxtubeapp.data.db.model.RealmModelPrimaryKey
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.realm.*
import io.realm.kotlin.where

/**
 * Created on 2018/03/21.
 */
class Database {


    fun config(context: Context) {

        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder()
                .name("RxTube.realm")
                .build()

        Realm.setDefaultConfiguration(realmConfig)
    }

    inline fun <reified T: ConvertibleFromRealmModel<U>, U>fetch():Maybe<U> {

        return Maybe.create { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getDefaultInstance()
                val realmModel:T? = realm
                        .where<T>()
                        .findFirst()
                if (realmModel != null) {
                    emitter.onSuccess(realmModel.toModel())
                } else {
                    emitter.onComplete()
                }

            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }

    inline fun <reified T: ConvertibleFromRealmModel<U>, U>fetchByPrimaryKey(primaryKey: RealmModelPrimaryKey):Maybe<U> {

        return Maybe.create { emitter ->
            var realm: Realm? = null
            try {

                realm = Realm.getDefaultInstance()
                val realmModel:T? = realm
                        .where<T>()
                        .equalTo(primaryKey)
                        .findFirst()
                if (realmModel != null) {
                    emitter.onSuccess(realmModel.toModel())
                } else {
                    emitter.onComplete()
                }
            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }

    inline fun <reified T: ConvertibleFromRealmModel<U>, U>fetchAll(): Observable<U> {
        return Observable.create { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getDefaultInstance()
                realm
                        .where<T>()
                        .findAll()
                        .toTypedArray()
                        .forEach { emitter.onNext(it.toModel()) }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }

    fun <T: ConvertibleToRealmModel<U>, U: RealmModel>store(model: T): Completable {
        return Completable.create { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getDefaultInstance()

                realm.executeTransaction { r ->
                    r.copyToRealm(model.toRealmModel())
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }

    inline fun <reified T: ConvertibleFromRealmModel<U>, U>deleteAll(): Completable {
        return Completable.create { emitter ->
            var realm: Realm? = null
            try {
                realm = Realm.getDefaultInstance()
                var realmResults = realm.where<T>().findAll()
                realm.executeTransaction { r ->
                    realmResults.deleteAllFromRealm()
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }

    inline fun <reified T: ConvertibleFromRealmModel<U>, U>deleteByPrimaryKey(primaryKey: RealmModelPrimaryKey):Completable {

        return Completable.create { emitter ->
            var realm: Realm? = null
            try {

                realm = Realm.getDefaultInstance()
                val realmResults:RealmResults<T> = realm
                        .where<T>()
                        .equalTo(primaryKey)
                        .findAll()
                realm.executeTransaction { r ->
                    realmResults.deleteAllFromRealm()
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(t)
            } finally {
                if (realm != null) {
                    realm.close()
                }
            }
        }
    }





    fun <E>RealmQuery<E>.equalTo(primaryKey: RealmModelPrimaryKey): RealmQuery<E> {

        when (primaryKey.value) {
            is RealmModelPrimaryKey.ValueType.String -> return this.equalTo(primaryKey.fieldName, primaryKey.value.string)
            is RealmModelPrimaryKey.ValueType.Int -> return this.equalTo(primaryKey.fieldName, primaryKey.value.int)
            is RealmModelPrimaryKey.ValueType.Short -> return this.equalTo(primaryKey.fieldName, primaryKey.value.short)
            is RealmModelPrimaryKey.ValueType.Long -> return this.equalTo(primaryKey.fieldName, primaryKey.value.long)
            is RealmModelPrimaryKey.ValueType.Byte -> return this.equalTo(primaryKey.fieldName, primaryKey.value.byte)
        }
    }



}