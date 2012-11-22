/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;

/**
 *
 * @author Quality of Service
 */
public class RSAKeys {

    private RSAPrivateKey privateKey;
    private BigInteger exponente;
    private BigInteger modulus;

    /**
     * @return the privateKey
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return the exponente
     */
    public BigInteger getExponente() {
        return exponente;
    }

    /**
     * @param exponente the exponente to set
     */
    public void setExponente(BigInteger exponente) {
        this.exponente = exponente;
    }

    /**
     * @return the modulus
     */
    public BigInteger getModulus() {
        return modulus;
    }

    /**
     * @param modulus the modulus to set
     */
    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }
}
