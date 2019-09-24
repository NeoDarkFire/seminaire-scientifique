package model;

class XorEncryption implements IEncryption {

    private byte[] key;

    public XorEncryption(final byte[] key) {
        this.key = key;
    }

    @Override
    public byte[] encrypt(final byte[] input) {
        return decrypt(input);
    }

    @Override
    public byte[] decrypt(final byte[] input) {
        final int size = input.length;
        final byte[] output_bytes = new byte[size];

        for (int i = 0; i < size; i++) {
            output_bytes[i] = (byte) (input[i] ^ key[i % (key.length-1)]);
        }

        return output_bytes;
    }
}
