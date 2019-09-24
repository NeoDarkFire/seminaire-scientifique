package model;

interface IEncryption {
    byte[] encrypt(byte[] input);
    byte[] decrypt(byte[] input);
}
