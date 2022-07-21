package com.portto.sdk.flow.method

import android.net.Uri
import com.portto.sdk.core.Blockchain
import com.portto.sdk.core.method.Method
import com.portto.sdk.wallet.BloctoSDKError
import com.portto.sdk.wallet.Const
import com.portto.sdk.wallet.METHOD_SIGN_MESSAGE
import com.portto.sdk.wallet.SignType

class SignMessageMethod(
    private val fromAddress: String,
    private val signType: SignType.Flow,
    private val message: String,
    blockchain: Blockchain,
    onSuccess: (String) -> Unit,
    onError: (BloctoSDKError) -> Unit
) : Method<String>(blockchain, onSuccess, onError) {

    override val name: String
        get() = METHOD_SIGN_MESSAGE

    override fun handleCallback(uri: Uri) {
        val signature = uri.getQueryParameter(Const.KEY_SIGNATURE)
        if (signature.isNullOrEmpty()) {
            onError(BloctoSDKError.INVALID_RESPONSE)
            return
        }
        onSuccess(signature)
    }

    override fun encodeToUri(authority: String, appId: String, requestId: String): Uri.Builder {
        return super.encodeToUri(authority, appId, requestId)
            .appendQueryParameter(Const.KEY_FROM, fromAddress)
            .appendQueryParameter(Const.KEY_TYPE, signType.typeName)
            .appendQueryParameter(Const.KEY_MESSAGE, message)
    }
}
