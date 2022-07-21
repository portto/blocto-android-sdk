package com.portto.sdk.wallet

sealed interface SignType {
    val typeName: String

    enum class Evm(override val typeName: String) : SignType {
        ETH_SIGN("sign"),
        PERSONAL_SIGN("personal_sign"),
        TYPED_DATA_SIGN("typed_data_sign"),
        TYPED_DATA_SIGN_V3("typed_data_sign_v3"),
        TYPED_DATA_SIGN_V4("typed_data_sign_v4")
    }

    enum class Flow(override val typeName: String) : SignType {
        USER_SIGNATURE("user_signature")
    }

    companion object {
        val allSignTypes: Set<SignType> = setOf(*Evm.values(), *Flow.values())
    }
}