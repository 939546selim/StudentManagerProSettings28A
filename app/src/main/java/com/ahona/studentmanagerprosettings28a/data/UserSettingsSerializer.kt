package com.ahona.studentmanagerprosettings28a.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import com.google.protobuf.InvalidProtocolBufferException

object UserSettingsSerializer : Serializer<UserSettings> {

    override val defaultValue: UserSettings =
        UserSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettings {
        try {
            return UserSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(
                "Cannot read proto.",
                exception
            )
        }
    }

    override suspend fun writeTo(
        t: UserSettings,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}