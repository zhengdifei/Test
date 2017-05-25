package zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipTest1 {

	public static final byte[] compress(String str) {
		if (str == null)
			return null;

		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;

		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
		}
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		return compressed;
	}

	public static final String decompress(byte[] compressed) {
		if (compressed == null)
			return null;

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed;
		try {
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			ZipEntry entry = zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				System.out.println("c:" +  new sun.misc.BASE64Encoder().encodeBuffer(buffer));
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String str = "zhengdifei";
		String comStr = new sun.misc.BASE64Encoder().encodeBuffer(compress(str));
		System.out.println("a:" + comStr);
		comStr = "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";
		String deStr = decompress(Base64.getDecoder().decode(comStr));
		//String deStr = decompress(new sun.misc.BASE64Decoder().decodeBuffer(comStr));
		System.out.println("b:" + deStr);
	}

}
