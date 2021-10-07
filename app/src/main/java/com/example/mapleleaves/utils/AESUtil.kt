package com.example.mapleleaves.utils

import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESUtil {
    private val TAG = AESUtil::class.java.simpleName

    /**
     * 采用AES加密算法
     */
    private const val KEY_ALGORITHM = "AES"

    /**
     * 字符编码(用哪个都可以，要注意new String()默认使用UTF-8编码 getBytes()默认使用ISO8859-1编码)
     */
    private val CHARSET_UTF8 = StandardCharsets.UTF_8

    /**
     * 加解密算法/工作模式/填充方式
     */
    private const val CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"

    /**
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位
     */
    private const val SECRET_KEY="128373c04bfd1112"

    /**
     * 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     */
    private const val IV_PARAMETER="0102030405060708"

    /**
     * AES 加密
     *
     * @param secretKey 加密密码，长度：16 或 32 个字符
     * @param data      待加密内容
     * @return 返回Base64转码后的加密数据
     */
    fun encrypt(secretKey: String= SECRET_KEY, data: String): String? {
        try {
            // 创建AES秘钥
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(CHARSET_UTF8), KEY_ALGORITHM)
            // 创建密码器
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            val iv= IvParameterSpec(IV_PARAMETER.encodeToByteArray())
            // 初始化加密器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,iv)
            // 执行加密操作
            val encryptByte = cipher.doFinal(data.toByteArray(CHARSET_UTF8))
            // Log.d("AESUtil","encryptByte:$encryptByte")
            // 将加密以后的数据进行 Base64 编码
            return base64Encode(encryptByte)
        } catch (e: Exception) {
            handleException("encrypt", e)
        }
        return null
    }

    /**
     * AES 解密
     *
     * @param secretKey  解密的密钥，长度：16 或 32 个字符
     * @param base64Data 加密的密文 Base64 字符串
     */
    fun decrypt(secretKey: String= SECRET_KEY, base64Data: String?): String? {
        try {
            val data = base64Decode(base64Data)
            //Log.d("AESUtil","decryptByte:$data")
            // 创建AES秘钥
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(CHARSET_UTF8), KEY_ALGORITHM)
            // 创建密码器
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            val iv= IvParameterSpec(IV_PARAMETER.encodeToByteArray())
            // 初始化解密器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,iv)
            // 执行解密操作
            val result = cipher.doFinal(data)
            return String(result, CHARSET_UTF8)
        } catch (e: Exception) {
            handleException("decrypt", e)
        }
        return null
    }

    /**
     * 将 字节数组 转换成 Base64 编码
     * 用Base64.DEFAULT模式会导致加密的text下面多一行（在应用中显示是这样）
     */
    private fun base64Encode(data: ByteArray?): String {
        return Base64.encodeToString(data, Base64.NO_WRAP)
    }

    /**
     * 将 Base64 字符串 解码成 字节数组
     */
    private fun base64Decode(data: String?): ByteArray {
        return Base64.decode(data, Base64.NO_WRAP)
    }

    /**
     * 处理异常
     */
    private fun handleException(methodName: String, e: Exception) {
        e.printStackTrace()
        Log.e(TAG, "$methodName---->$e")
    }
}