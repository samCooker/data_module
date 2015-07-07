/*
 * FileName:    RSAUtils.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.util;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @author LLM
 *
 */
public final class RSAUtils {
    /**
     * 加密
     *
     * @param key
     *            加密的密钥
     * @param data
     *            待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     *             异常
     */
    public static byte[] encrypt(Key key, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     *
     * @param key
     *            解密的密钥
     * @param raw
     *            已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     *             异常
     */
    public static byte[] decrypt(Key key, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(raw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回私钥对象
     *
     * @return 私钥对象
     */
    public static RSAPrivateKey getRSAPrivateKey() {
        try {
            InputStream fis = RSAUtils.class.getResourceAsStream("private_key.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (RSAPrivateKey) ois.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 返回公钥对象
     *
     * @return 公钥对象
     */
    public static RSAPublicKey getRSAPublicKey() {
        try {
            InputStream fis = RSAUtils.class.getResourceAsStream("public_key.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (RSAPublicKey) ois.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
