package com.vessteros.groovie.app.models.cloud.responses.data

class DataList {
    data class AuthData(
        val id: Int?,
        val login: String?,
        val password: String?,
        val token: String?,
        val name: String?,
        val lastName: String?,
        val secondName: String?
    ) : BaseData

    data class PasswordValidationData(val valid: Boolean?) : BaseData

    data class ProfileUpdateData(val updated: Any?): BaseData

    data class AccessTokenData(val token: String?): BaseData

    data class ConnectedNetworksList(
        val network_list: ArrayList<ConnectedNetwork>?
    ) : BaseData

    data class ConnectedNetwork(
        val id: Int?,
        val api_user_id: Int?,
        val network_id: String?,
        val access_token: String?
    ) : BaseData
}