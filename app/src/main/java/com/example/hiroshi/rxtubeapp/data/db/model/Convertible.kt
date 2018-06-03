package com.example.hiroshi.rxtubeapp.data.db.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created on 2018/03/28.
 */

interface ConvertibleToRealmModel<out T: RealmModel> {

    fun toRealmModel(): T
}


interface ConvertibleFromRealmModel<out T>: RealmModel {
    fun toModel():T
}

interface RealmModelPrimaryKeyType {
    fun primaryKey(): RealmModelPrimaryKey
}

data class RealmModelPrimaryKey(val fieldName: String, val value: ValueType) {
    sealed class ValueType {

        data class String(val string: kotlin.String): ValueType()
        data class Int(val int: kotlin.Int): ValueType()
        data class Short(val short: kotlin.Short): ValueType()
        data class Long(val long: kotlin.Long): ValueType()
        data class Byte(val byte: kotlin.Byte): ValueType()
    }
}