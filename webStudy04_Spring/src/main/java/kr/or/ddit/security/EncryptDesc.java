package kr.or.ddit.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 1017 암호화
 * 1. encode(부호화)  : 매체를 통해 저장이나 전송을 목적으로 매체가 
 * 						이해할 수 있는 형태로 문자를 변환하는 과정
 * 					URLEncoding/Percent Encoding / Base64 :img태그 데이터스키마 + 이미지, 비디오 사용가능
 * 2. encrypt(암호화) : 허가받지 않은 사용자가 무단으로 데이터를 읽을수 없도록 
 * 					 기밀성을 보장하기 위한 변환 과정.
 * 	1) 단방향 암호화(해시 함수)  : 암호화는 가능, 복호화 불가능 (비밀번호 저장)
 * 				: 해시 키(일정한 길이의 코드) 생성 구조
 * 		 MD5(128bit) -> SHA1(128bit) -> SHA2(256,384,512)
 * 	
 * 	2) 양방향 암호화  : 암호화, 복호화 가능 (평문으로 돌릴수있어) => 전송 데이터 암호화, 자기서명
 * 		- 전치(위치변경)/ 치환(다른문자로 변경)
 * 		- 블록 암호화 : 대상이 되는 데이터를  일정 구간(블록)으로 쪼개서 암호화수행
 * 		- 대칭키 방식 : 하나의 비밀키로 암복호화 수행 , AES-128(길이:128,256) 
 * 				        블록길이는 128  패딩문자(구간쪼갰을때 남은구간 채운거)? 초기화 백터  ,CBC
 * 		- 비대칭키 방식 : 한쌍의 공개키/개인키로 암복호화 수행, RSA(1024,2048)
 * 
 *
 */
public class EncryptDesc {
//	public static void main(String[] args) throws Exception{
//		String plain="1234";
//		String encoded = encryptSha512(plain);
//		
//	}
	public static void exampleRAS2048(String plain) throws Exception {
//3 암복호화
		Cipher cipher =Cipher.getInstance("RSA");
		KeyPairGenerator pairGen=KeyPairGenerator.getInstance("RSA");
		//길이 설정
		pairGen.initialize(2048);
		KeyPair pair =  pairGen.generateKeyPair();
		PrivateKey privateKey =  pair.getPrivate(); //비밀키
		PublicKey publicKey = pair.getPublic(); //공개키 
		
		cipher.init(cipher.ENCRYPT_MODE,privateKey ); //전자서명
		byte[] encrypted = cipher.doFinal(plain.getBytes());
		String encoded = Base64.getEncoder().encodeToString(encrypted); //암호화 -> 부호화
		System.out.println(encoded);
		//decoed
		cipher.init(cipher.DECRYPT_MODE,publicKey); // 한쌍의 키
		//복호화
		encrypted = Base64.getDecoder().decode(encoded);
		byte[] decryted =  cipher.doFinal(encrypted); //=plain.getBytes()
		System.out.println(new String(decryted));
				
	}
	
	public static void exampleAes128(String plain) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES"); // 하나의 비밀키 만들려고
		keyGen.init(128);
		
		SecretKey secretKey = keyGen.generateKey(); //인터페이스여서 이걸 만들어줄수있는게 필요함.=> 비밀키 만들어냄
		//초기화 백터****
		byte[] iv = MessageDigest.getInstance("MD5").digest(UUID.randomUUID().toString().getBytes());
		
		IvParameterSpec spec= new IvParameterSpec(iv); 
		cipher.init(Cipher.ENCRYPT_MODE, secretKey,spec); //사이퍼를 암호화할때 쓰겟다. 복호화는 decrypt로 입력하면됨 ^_^
		byte[] encrypted = cipher.doFinal(plain.getBytes());// encrpyting 함
		//db에 넣으면 바이트로 바꿔야하닌까 인코딩
		String encoded = Base64.getEncoder().encodeToString(encrypted); //치최종 저장
		System.out.println(encoded);
		
		//복호화~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//키 , 초기화백터 => 이걸 어떻게 가지고 다닐지 
		cipher.init(Cipher.DECRYPT_MODE,secretKey,spec);
		//encoded를  decode
		encrypted =  Base64.getDecoder().decode(encoded); // =
		byte[] decrypted =  cipher.doFinal(encrypted); //=> plain.getBytes()
		System.out.println(new String(decrypted));
	}
	
	public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes()); //바이트 단윌 ㅗ쪼개고 바이트로 암호화 됨.
		System.out.println(encrypted.length);
		//db가 이해할수있는 문자형태로 바꿔야함 => encoding 가진건 바이트인데 문자열로 바꿔애햐서 base64(1.3배 커짐)를 사용함. bo_pass를 200바이트
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		return encoded;
	}

}
