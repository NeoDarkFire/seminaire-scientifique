package model;

class XorEncryption implements IEncryption {

    private byte[] key;

    public XorEncryption() {
        this.key = new byte[0];
    }
    public XorEncryption(final byte[] key) {
        this.key = key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    @Override
    public byte[] encrypt(final byte[] input) {
        return decrypt(input);
    }

    @Override
    public byte[] decrypt(final byte[] input) {
        if (key.length == 0) {
            return input;
        } else {
            final int size = input.length;
            final byte[] output_bytes = new byte[size];

            for (int i = 0; i < size; i++) {
                output_bytes[i] = (byte) (input[i] ^ key[i % key.length]);
            }

            return output_bytes;
        }
    }
}
