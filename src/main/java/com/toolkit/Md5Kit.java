package main.java.com.toolkit;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Md5Kit {
	
	public static String bin2hex(ArrayList<Long> array) {
		String str = "";
		String tab = "0123456789abcdef";
		for (int i = 0; i < array.size()*4; i=i+4) {
			str += tab.charAt((int) ((array.get(i>>2) >> ((i%4)*8+4)) & 0xF)) + tab.charAt((int) ((array.get(i>>2) >> ((i%4)*8)) & 0xF));
		}
		return str;
	}
	
	public static String hex_md5(String src){
		if (src==null) {
			return null;
		} else {
			return bin2hex(core_md5(str2bin(src), (long) (src.length()*8)));
		}
	}
	
	public static ArrayList<Long> core_md5(HashMap<Long,Long> x, Long len){
		ArrayList<Long> array = new ArrayList<Long>();
		x = andEqual(x, len>>5 , (long) (0x80 << ((len)%32)));
		x.put( ((((len+64) >>> 9) << 4)+14), len);
		Long a=(long) 1732584193;Long b= (long) -271733879;Long c= (long) -1732584194;Long d=(long) 271733878;
		for (int i = 0; i < x.size(); i++) {
			Long olda = 1732584193L;
			Long oldb = -271733879L;
			Long oldc = -1732584194L;
			Long oldd = 271733878L;
			a = md5_ff(a, b, c, d, getByIndex(x, i), 7L, -680876936L);
			d = md5_ff(d, a, b, c, getByIndex(x, i+1), 12L, -389564586L);
			c = md5_ff(c, d, a, b, getByIndex(x, i+2), 17L, 606105819L);
			b = md5_ff(b, c, d, a, getByIndex(x, i+3), 22L, -1044525330L);
			a = md5_ff(a, b, c, d, getByIndex(x, i+4), 7L, -176418897L);
			d = md5_ff(d, a, b, c, getByIndex(x, i+5), 12L, 1200080426L);
			c = md5_ff(c, d, a, b, getByIndex(x, i+6), 17L, -1473231341L);
			b = md5_ff(b, c, d, a, getByIndex(x, i+7), 22L, -45705983L);
			a = md5_ff(a, b, c, d, getByIndex(x, i+8), 7L, 1770035416L);
			d = md5_ff(d, a, b, c, getByIndex(x, i+9), 12L, -1958414417L);
			c = md5_ff(c, d, a, b, getByIndex(x, i+10), 17L, -42063L);
			b = md5_ff(b, c, d, a, getByIndex(x, i+11), 22L, -1990404162L);
			a = md5_ff(a, b, c, d, getByIndex(x, i+12), 7L, 1804603682L);
			d = md5_ff(d, a, b, c, getByIndex(x, i+13), 12L, -40341101L);
			c = md5_ff(c, d, a, b, getByIndex(x, i+14), 17L, -1502002290L);
			b = md5_ff(b, c, d, a, getByIndex(x, i+15), 22L, 1236535329L);
			a = md5_gg(a, b, c, d, getByIndex(x, i+1), 5L, -165796510L);
			d = md5_gg(d, a, b, c, getByIndex(x, i+6), 9L, -1069501632L);
			c = md5_gg(c, d, a, b, getByIndex(x, i+11), 14L, 643717713L);
			b = md5_gg(b, c, d, a, getByIndex(x, i), 20L, -373897302L);
			a = md5_gg(a, b, c, d, getByIndex(x, i+5), 5L, -701558691L);
			d = md5_gg(d, a, b, c, getByIndex(x, i+10), 9L, 38016083L);
			c = md5_gg(c, d, a, b, getByIndex(x, i+15), 14L, -660478335L);
			b = md5_gg(b, c, d, a, getByIndex(x, i+4), 20L, -405537848L);
			a = md5_gg(a, b, c, d, getByIndex(x, i+9), 5L, 568446438L);
			d = md5_gg(d, a, b, c, getByIndex(x, i+14), 9L, -1019803690L);
			c = md5_gg(c, d, a, b, getByIndex(x, i+13), 14L, -187363961L);
			b = md5_gg(b, c, d, a, getByIndex(x, i+8), 20L, 1163531501L);
			a = md5_gg(a, b, c, d, getByIndex(x, i+13), 5L, -1444681467L);
			d = md5_gg(d, a, b, c, getByIndex(x, i+2), 9L, -51403784L);
			c = md5_gg(c, d, a, b, getByIndex(x, i+7), 14L, 1735328473L);
			b = md5_gg(b, c, d, a, getByIndex(x, i+12), 20L, -1926607734L);
			a = md5_hh(a, b, c, d, getByIndex(x, i+5), 4L, -378558L);
			d = md5_hh(d, a, b, c, getByIndex(x, i+8), 11L, -2022574463L);
			c = md5_hh(c, d, a, b, getByIndex(x, i+11), 16L, 1839030562L);
			b = md5_hh(b, c, d, a, getByIndex(x, i+14), 23L, -35309556L);
			a = md5_hh(a, b, c, d, getByIndex(x, i+1), 4L, -1530992060L);
			d = md5_hh(d, a, b, c, getByIndex(x, i+4), 11L, 1272893353L);
			c = md5_hh(c, d, a, b, getByIndex(x, i+7), 16L, -155497632L);
			b = md5_hh(b, c, d, a, getByIndex(x, i+10), 23L, -1094730640L);
			a = md5_hh(a, b, c, d, getByIndex(x, i+13), 4L, 681279174L);
			d = md5_hh(d, a, b, c, getByIndex(x, i), 11L, -358537222L);
			c = md5_hh(c, d, a, b, getByIndex(x, i+3), 16L, -722521979L);
			b = md5_hh(b, c, d, a, getByIndex(x, i+6), 23L, 76029189L);
			a = md5_hh(a, b, c, d, getByIndex(x, i+9), 4L, -640364487L);
			d = md5_hh(d, a, b, c, getByIndex(x, i+12), 11L, -421815835L);
			c = md5_hh(c, d, a, b, getByIndex(x, i+15), 16L, 530742520L);
			b = md5_hh(b, c, d, a, getByIndex(x, i+2), 23L, -995338651L);
			a = md5_ii(a, b, c, d, getByIndex(x, i), 6L, -198630844L);
			d = md5_ii(d, a, b, c, getByIndex(x, i+7), 10L, 1126891415L);
			c = md5_ii(c, d, a, b, getByIndex(x, i+14), 15L, -1416354905L);
			b = md5_ii(b, c, d, a, getByIndex(x, i+5), 21L, -57434055L);
			a = md5_ii(a, b, c, d, getByIndex(x, i+12), 6L, 1700485571L);
			d = md5_ii(d, a, b, c, getByIndex(x, i+3), 10L, -1894986606L);
			c = md5_ii(c, d, a, b, getByIndex(x, i+10), 15L, -1051523L);
			b = md5_ii(b, c, d, a, getByIndex(x, i+1), 21L, -2054922799L);
			a = md5_ii(a, b, c, d, getByIndex(x, i+8), 6L, 1873313359L);
			d = md5_ii(d, a, b, c, getByIndex(x, i+15), 10L, -30611744L);
			c = md5_ii(c, d, a, b, getByIndex(x, i+6), 15L, -1560198380L);
			b = md5_ii(b, c, d, a, getByIndex(x, i+13), 21L, 1309151649L);
			a = md5_ii(a, b, c, d, getByIndex(x, i+4), 6L, -145523070L);
			d = md5_ii(d, a, b, c, getByIndex(x, i+11), 10L, -1120210379L);
			c = md5_ii(c, d, a, b, getByIndex(x, i+2), 15L, 718787259L);
			b = md5_ii(b, c, d, a, getByIndex(x, i+9), 21L, -343485551L);
			a = safe_add(a, olda); b = safe_add(b, oldb);
			c = safe_add(c, oldc); d = safe_add(d, oldd);
		}
		array.add(a);array.add(b);array.add(c);array.add(d);
		return array;
	}
	
	public static Long md5_ff(Long a,Long b,Long c,Long d,Long x,Long s,Long t){
		return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
	}
	
	public static Long md5_gg(Long a,Long b,Long c,Long d,Long x,Long s,Long t){
		return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
	}
	
	public static Long md5_hh(Long a,Long b,Long c,Long d,Long x,Long s,Long t){
		return md5_cmn(b ^ c ^ d, a, b, x, s, t);
	}
	
	public static Long md5_ii(Long a,Long b,Long c,Long d,Long x,Long s,Long t){
		return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
	}
	
	public static Long md5_cmn(Long q,Long a,Long b,Long x,Long s,Long t){
		return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
	}
	
	public static Long bit_rol(Long num,Long cnt){
		return (num << cnt) | (num >>> (32-cnt));
	}
	
	public static Long safe_add(Long x,Long y) {
		Long lsw = 0L;Long msw = 0L;
		if (x==null) {
			lsw = (y & 0xFFFF);
			msw = (y >> 16)+(lsw >> 16);
		} else {
			lsw = (x & 0xFFFF)+(y & 0xFFFF);
			msw = (x >> 16)+(y >> 16)+(lsw >> 16);
		}
		return (msw << 16) | (lsw & 0xFFFF);
	}
	
	public static Long getByIndex(HashMap<Long,Long> array,Integer index){
		if (!array.containsKey(index)) {
			return null;
		} else {
			return array.get(index);
		}
	}
	
	public static HashMap<Long,Long> andEqual(HashMap<Long,Long> array,Long i,Long index){
		if (array.containsKey(index)) {
			array.put(index, array.get(index)|i);
		} else {
			array.put(index, i);
		}
		return array;
	}

	public static HashMap<Long,Long> str2bin(String str) {
		Long mask = (long) ((1<<8) -1);
		HashMap<Long,Long> Array = new HashMap<Long,Long>();
		for (int i = 0; i < str.length()*8; i=i+8) {
			long c = (str.charAt(i/8) &mask)<<(i%32);
			long index = i>>5;
			Array = andEqual(Array, c, index);
		}
		return Array;
	}
	
	public static void main(String[] args) throws Exception {
//		String s ="abcccchello";
		String s ="lz1008";
		String k ="#d19i4o-";
		SecretKey sk = new SecretKeySpec(k.getBytes(), "DES");
		IvParameterSpec iv = new IvParameterSpec(k.getBytes());
		Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");//这里的模式不用简单的 “DES" ,要与flex 对应，用cbc
		c.init(Cipher.ENCRYPT_MODE, sk,iv);
		System.out.println(byte2hex(c.doFinal(s.getBytes())));

	}
	
	public static String getMd5(String s) throws Exception{
		String k ="#d19i4o-";
		SecretKey sk = new SecretKeySpec(k.getBytes(), "DES");
		IvParameterSpec iv = new IvParameterSpec(k.getBytes());
		Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");//这里的模式不用简单的 “DES" ,要与flex 对应，用cbc
		c.init(Cipher.ENCRYPT_MODE, sk,iv);
		System.out.println(byte2hex(c.doFinal(s.getBytes())));
		return byte2hex(c.doFinal(s.getBytes()));
	}

	public static String byte2hex(byte[] b) { // 一个字节的数，
        // 转成16进制字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs; 
    }}
