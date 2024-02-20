package com.hpcl_paytm.activity.utils

import android.content.Context
import android.util.Base64
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.hpcl_paytm.activity.constants.Constants
import com.hpcl_paytm.activity.constants.SharedPreferencesData
import com.hpcl_paytm.activity.encryption_decryption.AesDesWrapper
import okhttp3.RequestBody
import okio.Buffer
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Locale
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object RSAUtils {
    private val MAX_ENCRYPT_BLOCK = 117
    private val MAX_DECRYPT_BLOCK = 128
    // private static String RSA = "RSA";

    // private static String RSA = "RSA";
    private val RSA = "RSA/ECB/PKCS1Padding"
    private val RSA_KEY_FACTORY = "RSA"
    private var cipher: Cipher? = null
    private val keySize = 128
    lateinit var IV: ByteArray

    fun generateRSAKeyPair(): KeyPair? {
        return generateRSAKeyPair(2048)
    }


    fun generateRSAKeyPair(keyLength: Int): KeyPair? {
        return try {
            val kpg = KeyPairGenerator.getInstance(RSA_KEY_FACTORY)
            kpg.initialize(keyLength)
            kpg.genKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

    fun setTransformation(transformation: String?) {
        try {
            cipher = Cipher.getInstance(transformation)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        }
    }

    fun encryptData(data: ByteArray?, publicKey: PublicKey?): ByteArray? {
        return try {
            if (cipher == null) cipher = Cipher.getInstance(RSA)
            cipher!!.init(Cipher.ENCRYPT_MODE, publicKey)
            cipher!!.doFinal(data)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun encryptDataWithSecretKey(data: ByteArray?, key: Key?): ByteArray? {
        return try {
            if (cipher == null) cipher = Cipher.getInstance(RSA)
            cipher!!.init(Cipher.ENCRYPT_MODE, key)
            cipher!!.doFinal(data)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun encryptDataStr(data: ByteArray?, publicKey: PublicKey?): String? {
        return try {
            if (cipher == null) cipher = Cipher.getInstance(RSA)
            cipher!!.init(Cipher.ENCRYPT_MODE, publicKey)
            Base64Utils.encode(cipher!!.doFinal(data))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun decryptData(encryptedData: ByteArray?, privateKey: PrivateKey?): ByteArray? {
        return try {
            if (cipher == null) cipher = Cipher.getInstance(RSA)
            cipher!!.init(Cipher.DECRYPT_MODE, privateKey)
            cipher!!.doFinal(encryptedData)
        } catch (e: Exception) {
            null
        }
    }


    fun decryptDataStr(encryptedData: ByteArray?, privateKey: PrivateKey?): String? {
        return try {
            if (cipher == null) cipher = Cipher.getInstance(RSA)
            cipher!!.init(Cipher.DECRYPT_MODE, privateKey)
            String(cipher!!.doFinal(encryptedData))
        } catch (e: Exception) {
            null
        }
    }


    @Throws(
        NoSuchAlgorithmException::class,
        InvalidKeySpecException::class
    )
    fun getPublicKey(keyBytes: ByteArray?): PublicKey? {
        val keySpec =
            X509EncodedKeySpec(keyBytes)
        val keyFactory =
            KeyFactory.getInstance(RSA_KEY_FACTORY)
        return keyFactory.generatePublic(keySpec)
    }


    @Throws(
        NoSuchAlgorithmException::class,
        InvalidKeySpecException::class
    )
    fun getPrivateKey(keyBytes: ByteArray?): PrivateKey? {
        val keySpec =
            PKCS8EncodedKeySpec(keyBytes)
        val keyFactory =
            KeyFactory.getInstance(RSA_KEY_FACTORY)
        return keyFactory.generatePrivate(keySpec)
    }


    @Throws(
        NoSuchAlgorithmException::class,
        InvalidKeySpecException::class
    )
    fun getPublicKey(
        modulus: String?,
        publicExponent: String?
    ): PublicKey? {
        val bigIntModulus = BigInteger(modulus)
        val bigIntPrivateExponent = BigInteger(publicExponent)
        val keySpec =
            RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent)
        val keyFactory =
            KeyFactory.getInstance(RSA_KEY_FACTORY)
        return keyFactory.generatePublic(keySpec)
    }


    @Throws(
        NoSuchAlgorithmException::class,
        InvalidKeySpecException::class
    )
    fun getPrivateKey(
        modulus: String?,
        privateExponent: String?
    ): PrivateKey? {
        val bigIntModulus = BigInteger(modulus)
        val bigIntPrivateExponent = BigInteger(privateExponent)
        val keySpec =
            RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent)
        val keyFactory =
            KeyFactory.getInstance(RSA_KEY_FACTORY)
        return keyFactory.generatePrivate(keySpec)
    }


    @Throws(Exception::class)
    fun loadPublicKey(publicKeyStr: String?): PublicKey {
        val buffer: ByteArray = publicKeyStr?.let { Base64Utils.decode(it) }!!
        val keyFactory = KeyFactory.getInstance(RSA_KEY_FACTORY)
        val keySpec = X509EncodedKeySpec(buffer)
        return keyFactory.generatePublic(keySpec) as RSAPublicKey
    }


    @Throws(Exception::class)
    fun loadPrivateKey(privateKeyStr: String?): PrivateKey {
        val buffer: ByteArray = privateKeyStr?.let { Base64Utils.decode(it) }!!
        val keySpec = PKCS8EncodedKeySpec(buffer)
        //For N3 Android generate key
        // KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_FACTORY,"BC");
        //For N5 Android generate key
        val keyFactory = KeyFactory.getInstance(RSA_KEY_FACTORY)
        return keyFactory.generatePrivate(keySpec) as RSAPrivateKey
    }


    @Throws(Exception::class)
    fun loadPublicKeyviaAsset(`in`: InputStream): PublicKey? {
        return loadPublicKey(readKey(`in`))
    }


    @Throws(Exception::class)
    fun loadPrivateKey(`in`: InputStream): PrivateKey? {
        return loadPrivateKey(readKey(`in`))
    }


    @Throws(IOException::class)
    private fun readKey(`in`: InputStream): String? {
        val br = BufferedReader(InputStreamReader(`in`))
        var readLine: String? = null
        val sb = StringBuilder()
        while (br.readLine().also { readLine = it } != null) {
            if (readLine!![0] == '-') {
                continue
            } else {
                sb.append(readLine)
                sb.append('\r')
            }
        }
        return sb.toString()
    }

    fun printPublicKeyInfo(publicKey: PublicKey) {
        val rsaPublicKey = publicKey as RSAPublicKey
        println("----------RSAPublicKey----------")
        println("Modulus.length=" + rsaPublicKey.modulus.bitLength())
        println("Modulus=" + rsaPublicKey.modulus.toString())
        println("PublicExponent.length=" + rsaPublicKey.publicExponent.bitLength())
        println("PublicExponent=" + rsaPublicKey.publicExponent.toString())
     //   System.out.println("PublicKey=" + Base64Utils.encode(rsaPublicKey.encoded))
    }

    fun printPrivateKeyInfo(privateKey: PrivateKey) {
        val rsaPrivateKey = privateKey as RSAPrivateKey
        println("----------RSAPrivateKey ----------")
        println("Modulus.length=" + rsaPrivateKey.modulus.bitLength())
        println("Modulus=" + rsaPrivateKey.modulus.toString())
        println("PrivateExponent.length=" + rsaPrivateKey.privateExponent.bitLength())
        println("PrivatecExponent=" + rsaPrivateKey.privateExponent.toString())
     //   System.out.println("PrivatecKey=" + Base64Utils.encode(rsaPrivateKey.encoded))
    }

    //Loading Public key
    @Throws(Exception::class)
    fun loadPublicKey(`in`: InputStream): PublicKey? {
        return try {
            loadPublicKey(readKey(`in`))
        } catch (e: IOException) {
            throw Exception("公钥数据流读取错误")
        } catch (e: NullPointerException) {
            throw Exception("公钥输入流为空")
        }
    }

    //Encrypt data using public key
    @Throws(Exception::class)
    fun encrypt(data: ByteArray?, publicKey: PublicKey?): ByteArray? {
        if (data == null) {
            return null
        }
        val cipher = Cipher.getInstance("RSA") //RSA/ECB/PKCS1Padding
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val inputLen = data.size
        val out = ByteArrayOutputStream()
        var offset = 0
        var cache: ByteArray
        var i = 0
        while (inputLen - offset > 0) {
            cache = if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK)
            } else {
                cipher.doFinal(data, offset, inputLen - offset)
            }
            out.write(cache, 0, cache.size)
            i++
            offset = i * MAX_ENCRYPT_BLOCK
        }
        val encryptedData = out.toByteArray()
        out.close()
        return encryptedData
    }

    @Throws(IllegalArgumentException::class)
    fun bcdToStr(b: ByteArray?): String? {
        if (b == null) {
            Log.e("TAG", "bcdToStr input arg is null")
            throw IllegalArgumentException("bcdToStr input arg is null")
        }
        val HEX_DIGITS = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'A',
            'B',
            'C',
            'D',
            'E',
            'F'
        )
        val sb = StringBuilder(b.size * 2)
        for (i in b.indices) {
            sb.append(HEX_DIGITS[b[i].toInt() and 0xf0 ushr 4])
            sb.append(HEX_DIGITS[b[i].toInt() and 0x0f])
        }
        return sb.toString()
    }

    fun byteArrayToHexString(b: ByteArray): String? {
        val sb = StringBuffer(b.size * 2)
        for (i in b.indices) {
            val v = b[i].toInt() and 0xff
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }
        return sb.toString().uppercase(Locale.getDefault())
    }

    fun hexStringToByteArray(s: String): ByteArray? {
        val b = ByteArray(s.length / 2)
        for (i in b.indices) {
            val index = i * 2
            val v = s.substring(index, index + 2).toInt(16)
            b[i] = v.toByte()
        }
        return b
    }

    //disable copy and paste option
    fun disableCopyPasteOperations(editText: EditText) {
        editText.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(
                actionMode: ActionMode,
                menu: Menu
            ): Boolean {
                return false
            }

            override fun onPrepareActionMode(
                actionMode: ActionMode,
                menu: Menu
            ): Boolean {
                return false
            }

            override fun onActionItemClicked(
                actionMode: ActionMode,
                item: MenuItem
            ): Boolean {
                return false
            }

            override fun onDestroyActionMode(actionMode: ActionMode) {}
        }
        editText.isLongClickable = false
        editText.setTextIsSelectable(false)
    }


    fun encrypt(value: String, initVector: String, key: String): String? {
        try {
            val iv = IvParameterSpec(hexStringToByteArray(initVector))
            val skeySpec = SecretKeySpec(hexStringToByteArray(key), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
            val encrypted = cipher.doFinal(value.toByteArray())
            return Base64.encodeToString(encrypted, Base64.DEFAULT)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun decrypt(encrypted: String?, initVector: String, key: String): String? {
        try {
            val iv = IvParameterSpec(hexStringToByteArray(initVector))
            val skeySpec = SecretKeySpec(hexStringToByteArray(key), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
            val original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT))
            return String(original)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    @Throws(Exception::class)
    fun decryptKEYFromServer(
        strPrivateKey: String?,
        strKey: String
    ): String? {
        val decryptKeyByteArray: ByteArray
        val privateKey: PrivateKey
        privateKey = RSAUtils.loadPrivateKey(strPrivateKey)
        decryptKeyByteArray = RSAUtils.decryptData(Base64Utils.decode(strKey), privateKey)!!
        return String(decryptKeyByteArray, StandardCharsets.UTF_8)
    }

    @Throws(Exception::class)
    fun decryptIVFromServer(strPrivateKey: String?, strIV: String): String? {
        val decryptKeyByteArray: ByteArray
        val privateKey: PrivateKey
        privateKey = RSAUtils.loadPrivateKey(strPrivateKey)
        decryptKeyByteArray = RSAUtils.decryptData(Base64Utils.decode(strIV), privateKey)!!
        return String(decryptKeyByteArray, StandardCharsets.UTF_8)
    }

    fun encryptKEY_IV_DATA(
        sharedPreferencesData: SharedPreferencesData,
        IV: ByteArray,
        secretKey: SecretKey,
        context: Context,
        planData: String
    ): String? {
        val strHexIV: String = RSAUtils.byteArrayToHexString(IV).toString()
        val strHexKey: String = RSAUtils.byteArrayToHexString(secretKey.encoded).toString()
        val encryptedIV: String = AesDesWrapper.encryptByPublicKey(strHexIV, context).toString()
        val encryptedKey: String = AesDesWrapper.encryptByPublicKey(strHexKey, context).toString()
        sharedPreferencesData.setSharedPreferenceData(
            Constants.ENCRYPTIVPREF,
            Constants.IV,
            encryptedIV
        )
        sharedPreferencesData.setSharedPreferenceData(
            Constants.ENCRYPTKEYPREF,
            Constants.KEY,
            encryptedKey
        )
        val encryptData: String = AesDesWrapper.encryptAndEncode(planData, IV, 256, secretKey).toString()
        Log.e("Plain Request Body", "Plain Request Body: $planData")
        Log.e("Encrypted Request Body", "Encrypted Request Body: $encryptData")
        return strHexIV
    }

    @Throws(IOException::class)
    fun requestBodyToString(requestBody: RequestBody): String? {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return buffer.readUtf8()
    }
}