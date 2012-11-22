/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Quality of Service
 */
public class RSAEncriptacion {

    private final String ALGORITMO = "RSA";
    private final String CODIFICACION = "UTF-8";
    private final Integer LONGITUD = 1024;
    private final Integer BASE = 16;

    public RSAKeys generarKeys() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITMO);
        kpg.initialize(LONGITUD);
        KeyPair kp = kpg.genKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
        RSAKeys keys = new RSAKeys();
        keys.setExponente(publicKey.getPublicExponent());
        keys.setModulus(publicKey.getModulus());
        keys.setPrivateKey(privateKey);
        return keys;
    }

    public RSAPublicKey obtenPublicKey(BigInteger modulo, BigInteger exponente)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory fact = KeyFactory.getInstance(ALGORITMO);
        RSAPublicKeySpec publicKey = new RSAPublicKeySpec(modulo, exponente);
        PublicKey pubKey = fact.generatePublic(publicKey);
        return (RSAPublicKey) pubKey;
    }

    public String encriptar(String texto, BigInteger modulo, BigInteger exponente)
            throws Exception {
        RSAPublicKey publicKey = obtenPublicKey(modulo, exponente);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cifrado = cipher.doFinal(texto.getBytes());
        char[] charEncriptado = Hex.encodeHex(cifrado);
        String textoEncriptado = new String(charEncriptado);
        return textoEncriptado;
    }
    
    public String encriptarPrivado(String texto, RSAPrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, 
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] cifrado = cipher.doFinal(texto.getBytes());
        char[] charEncriptado = Hex.encodeHex(cifrado);
        String textoEncriptado = new String(charEncriptado);
        return textoEncriptado;
    }

    public String desencriptar(String decript, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] desencriptado = Hex.decodeHex(decript.toCharArray());
        byte[] desci = cipher.doFinal(desencriptado);
        return new String(desci, CODIFICACION);
    }
    
    public String desencriptarPublico(String decript, BigInteger modulo, BigInteger exponente) throws NoSuchAlgorithmException, 
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, DecoderException, 
            IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException{
        
        RSAPublicKey publicKey = obtenPublicKey(modulo, exponente);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] desencriptado = Hex.decodeHex(decript.toCharArray());
        byte[] desci = cipher.doFinal(desencriptado);
        return new String(desci, CODIFICACION);
    }
}
