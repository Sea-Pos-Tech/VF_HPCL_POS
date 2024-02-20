package com.hpcl_paytm.activity.encryption_decryption

import android.content.Context
import android.util.Base64.DEFAULT
import android.util.Base64.NO_WRAP
import android.util.Base64.encodeToString
import android.util.Log
import com.hpcl_paytm.activity.utils.Base64
import com.hpcl_paytm.activity.utils.RSAUtils
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.InvalidParameterSpecException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.*


class AesDesWrapper {

    companion object {
        var publicKeyString: String? = null
        var privateKeyString: String? = null
        private var publicKey: PublicKey? = null
        private var privateKey: PrivateKey? = null
        public var RANDOM_ALGORITHM:String = "SHA1PRNG"
        const val IV_LENGTH = 16

        //Generate RSA Public Key and Private key
        private fun createRSAkeys() {
            try {
                val keyPairGen = KeyPairGenerator.getInstance("RSA")
                keyPairGen.initialize(2048)
                val keyPair = keyPairGen.generateKeyPair()
                publicKey = keyPair.public as RSAPublicKey
                privateKey = keyPair.private as RSAPrivateKey
                publicKeyString = getKeyString(publicKey!!)
                privateKeyString = getKeyString(privateKey!!)
                Log.e("Test", "public key :\n$publicKeyString")
                Log.e("Test", "private key :\n$privateKeyString")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @Throws(java.lang.Exception::class)
        fun getKeyString(key: Key): String? {
            val keyBytes = key.encoded
            return encodeToString(keyBytes, NO_WRAP)
        }
        @JvmStatic
        fun generateKey(): SecretKey? {
            try {
                val key: Key
                val rand = SecureRandom()
                val generator = KeyGenerator.getInstance("AES")
                generator.init(256, rand)
                key = generator.generateKey()
                return key
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return null
        }

        fun getInitializationVector(): String? {
            val IV = ByteArray(16)
            val random = SecureRandom()
            random.nextBytes(IV)
            return encodeToString(IV, DEFAULT).substring(0, 16)
        }

        @Throws(UnsupportedEncodingException::class)
        fun getBytes(str: String?): ByteArray? {
            return str?.toByteArray(charset("UTF-8"))
        }

        @Throws(
            NoSuchAlgorithmException::class,
            NoSuchPaddingException::class,
            InvalidKeyException::class,
            InvalidParameterSpecException::class,
            IllegalBlockSizeException::class,
            BadPaddingException::class,
            UnsupportedEncodingException::class
        )
        fun encryptMsg(message: String, secret: SecretKey?): ByteArray? {
            /* Encrypt the message. */
            var cipher: Cipher? = null
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secret)
            return cipher.doFinal(message.toByteArray(charset("UTF-8")))
        }

        @Throws(
            NoSuchPaddingException::class,
            NoSuchAlgorithmException::class,
            InvalidParameterException::class,
            InvalidAlgorithmParameterException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class,
            UnsupportedEncodingException::class
        )
        fun decryptMsg(cipherText: ByteArray?, secret: SecretKey?): String? {
            /* Decrypt the message, given derived encContentValues and initialization vector. */
            var cipher: Cipher? = null
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secret)
            return String(cipher.doFinal(cipherText), Charset.forName("UTF-8"))
        }

        //Encrypt data by Public key
        @JvmStatic
        fun encryptByPublicKey(strplainData: String?, context: Context): String? {
            var signData: String? = null
            try {
                val publicKey = RSAUtils.loadPublicKeyviaAsset(context.assets.open("publickey.pem"))
                var mSignData = RSAUtils.encryptDataStr(getBytes(strplainData), publicKey)
                Log.d("VendorVerifyActivity", "mSignData:" + mSignData)
                return mSignData
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return ""
        }

        //Encryption Logic Implemented
        @JvmStatic
        fun encryptAndEncode(raw: String?, iv: ByteArray, keyLength: Int,secretKey: Key?): String? {
            try {
                val c = getCipher(Cipher.ENCRYPT_MODE,iv, keyLength,secretKey)
                val encryptedVal = c.doFinal(getBytes(raw!!))
                return Base64.encode(encryptedVal)
            } catch (t: Throwable) {
                throw RuntimeException(t)
            }
            return ""
        }

        @Throws(java.lang.Exception::class)
        fun decodeAndDecrypt(encrypted: String?, key: SecretKey?, iv: ByteArray, keyLength: Int): String? {
            try {
                val decodedValue = Base64.decode(encrypted)
                val c = getCipher(Cipher.DECRYPT_MODE, iv, keyLength, key)

                val decValue = c.doFinal(decodedValue)
                return String(decValue)
            }catch (ex:Exception) {
                ex.printStackTrace()
            }
            return ""
        }


        @Throws(java.lang.Exception::class)
        private fun getCipher(mode: Int, _iv: ByteArray, keyLength: Int,secretKey: Key?): Cipher {
            val c = Cipher.getInstance("AES/CBC/PKCS7Padding")

            val iv = _iv
            c.init(mode, secretKey, IvParameterSpec(iv))
            // c.init(mode, secretKey,IvParameterSpec(Arrays.copyOf(iv, iv.size)));
            return c
        }

/*        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
        System.out.println(plaintext);*/

/*
        @Throws(java.lang.Exception::class)
         fun generateKey(salt: String, _password: String, keyLength: Int): Key? {
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val password = _password.toCharArray()
            val saltb = getBytes(salt)
            val spec: KeySpec = PBEKeySpec(password, saltb, 65536, keyLength)
            val tmp = factory.generateSecret(spec)
            val encoded = tmp.encoded
            return SecretKeySpec(encoded, "AES")
        }
*/



    }
}