package com.qingchen.study.vlife;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AESUtil.java
 * 
 * @author xuliang
 */
public class AESUtil {
	
	/**
	 * 加密算法 AES
	 */
	private static final String KEY_FACTORY = "AES";  
	private static final String KEY_CIPHER = "AES/CBC/PKCS5Padding";  
//	private static final String KEY_CIPHER = "AES/CBC/NoPadding";  
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static SecretKeySpec DEFAULT_SECRET_KEY;
	private static final String DEFAULT_SEED = "vlifetimerboxkey";

	/**
	 * 加密
	 * @param data	要加密的数据
	 * @param key	加密使用的秘钥
	 * @param iv	加密使用的向量
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key, String iv) throws Exception {
        return create(Cipher.ENCRYPT_MODE, key, iv).doFinal(data);
	}

	/**
	 * 解密
	 * @param data	要解密的数据
	 * @param key	解密使用的秘钥
	 * @param iv	解密使用的向量
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key, String iv) throws Exception {
        return create(Cipher.DECRYPT_MODE, key, iv).doFinal(data);
	}
	
	/**
	 * 创建Cipher对象
	 * @param mode	模式
	 * @param key	秘钥
	 * @param iv	向量
	 * @return
	 * @throws Exception
	 */
	private static Cipher create(int mode, String key, String iv) throws Exception {
		Cipher cipher = Cipher.getInstance(KEY_CIPHER);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), KEY_FACTORY);
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(mode, skeySpec, new IvParameterSpec(iv.getBytes()));
        return cipher;
	}
	
	/**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
	 * @throws InvalidKeyException 
     */
	public static String encrypt(String content) throws Exception {
		Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, getDefaultSecretKey());
		byte[] result = cipher.doFinal(byteContent);
		return Base64.encodeBase64String(result);
	}

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     */
	public static String decrypt(String content) throws Exception {
		Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getDefaultSecretKey());
		byte[] result = cipher.doFinal(Base64.decodeBase64(content));
		return new String(result, "utf-8");
	}
	
	/**
     * 生成加密秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException 
     */	
	private static SecretKeySpec getDefaultSecretKey() throws NoSuchAlgorithmException {
		if (DEFAULT_SECRET_KEY == null) {
			synchronized (AESUtil.class) {
				if (DEFAULT_SECRET_KEY == null) {
					DEFAULT_SECRET_KEY = new SecretKeySpec(DEFAULT_SEED.getBytes(), KEY_FACTORY);
				}
			}
		}
		return DEFAULT_SECRET_KEY;
	}

	/**
	 * AES 加密操作
	 *
	 * @param content 待加密内容
	 * @param password 加密密码
	 * @return 返回Base64转码后的加密数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrypt(String content, String password) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		int blockSize = cipher.getBlockSize();
		System.out.println(blockSize);
		byte[] dataBytes = content.getBytes();
		int plaintextLength = dataBytes.length;
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
		}
		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
		SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(password.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		byte[] encrypted = cipher.doFinal(plaintext);
		return Base64.encodeBase64String(encrypted);
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String decrypt(String content, String password) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(password.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		byte[] original = cipher.doFinal(Base64.decodeBase64(content));
		String originalString = new String(original);
		return originalString;
	}
	
	public static void main(String[] args) throws Exception {
		String decrypt = decrypt(
				"9Tz728Kw5EqvftrJh21o/NcDFKewE/eUO6s7AEpXNCay9gFnlCqnrIFGtIHZKxnbfNdWwsnIEPScC2VgemMziH4ejmSVlWtk6MA1YxqiNBbOQyTOjS7OgGPHXmvKGiNV/ht8jNxshhOW8qbYsy6RMw==\n");
		System.out.println(decrypt);
		// byte[] data = "12345678".getBytes("UTF-8");
		// String key = StringUtils.randomString(16);
		// String iv = StringUtils.randomString(16);
		// data = AESUtil.encrypt(data, key, iv);
		// System.out.println(new String(data));
		// key = StringUtils.randomString(16);
		// data = AESUtil.decrypt(data, key, iv);
		// System.out.println(new String(data));
	}
	
}