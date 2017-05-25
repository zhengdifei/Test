package encrypt;

public class BitConverter {
	public static String encode(byte value) {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 7; i >= 0; i--) {
			if ((value & (1 << i)) > 0) {
				buffer.append("1");
			} else {
				buffer.append("0");
			}
		}
		
		return buffer.toString();
	}

	public static String encode(byte[] valueArray) {
		StringBuffer buffer = new StringBuffer();

		for (byte value : valueArray) {
			buffer.append(encode(value));
		}
		
		return buffer.toString();
	}

	private static byte getByteFromString(String bitString) {
		byte result = 0;
		int position = 0;
		
		for (int i = bitString.length() - 1; i >= 0; i--) {
			if (bitString.charAt(i) == '1') {
				result |= 1 << position;
			}
			position++;
		}
		
		return result;
	}

	public static byte[] decode(String bitString) {
		if (bitString.length() == 0) {
			byte[] nullByte = {0};
			return nullByte;
		}
		
		int size = (int)Math.ceil(bitString.length() / 8.0f);
		byte[] result = new byte[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = getByteFromString(bitString.substring(i*8, Math.min(i*8 + 8, bitString.length())));
		}
		
		return result;
	}
}
