package com.app.carritodecompras.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAxV0RCtp+jT1QkVMqml/NxoWfc+qMNoYp/AH/g5VsTJK7veQ6\r\n" + 
			"2Xbm3G3p28eMvbklfbxCfk0kh81vmi7AjlmLlVnslFFuS5/iDM7oYFBA0Ki3xYF5\r\n" + 
			"EinyEwd5L49B5Jtr9aBPNcrCxfTpUikrwV3BTes4ys+ojnK3YhT4MTrnuTqW1txt\r\n" + 
			"e/P8CK7/2U+shkEhVg8jUVLXsrs7RMo3Kh0z4P67l6w7tEywLAsoR4FyEViTPMAm\r\n" + 
			"XOsQIP4N01cekjt5YN/MhRtE7T/d96OvZ1Q70VkxZEt/7pQkm6AI1KOArEq8rno3\r\n" + 
			"R0kcRxmGzgiD2qhTW4MbHtyZdaCS/TGo5cj3bQIDAQABAoIBAQCoZKC+3u/tOGx+\r\n" + 
			"ojQOZUGgabBD6dJaTxyRXW8SaTJvHx86hPnFRBLz7oADOn6V/dvELRWax90sVZcs\r\n" + 
			"OUmn6zUSaBhX8CaoNbFcdkmEq9Sxc9LgnHidi9Z0cALxFk0Fbaqud2o+HlMTLcTJ\r\n" + 
			"ikFACet9vP3G5WkOqUhYUkX1UyoVOi2XifK1VuXd7tmZai+iGP5K8GYvZhimizgW\r\n" + 
			"8d3XYLzZ7Amy8p+Y7GjPJzwmrkiwSRI2h2GCDLatj0NESDLih+dOnY/9/sT/qqpg\r\n" + 
			"ZumDO6WvY/fj8GcK9Q43ryS1yTVp5wjQHov2MRjvAO7PhmBD19exY/P037QslQZ/\r\n" + 
			"bw+GLo0hAoGBAOHCypvm9jfw5t5Dd2KXjAhiceVdD872B7mWtNr5xiEbDeKD+VGx\r\n" + 
			"q5D088pwK4PVzmLCK3qRlSO3W51lDyCsFWfu2jfeq+G8IUHxB6XwT0/And9A+ltG\r\n" + 
			"EDxiyWaI/SDMq1B+P7Vd4p14vn5z5AdYNJ4LKYGI7v0hTybAVYnMhgylAoGBAN/M\r\n" + 
			"i9hjc8S9PDIVgTmErFCN6R2+JWUA5hx6U7GHuyNoTGxSUErT7U8pBtxXmM/hiICX\r\n" + 
			"GtllWa72bI3s/FmK2Ko+2NzKhGDsdQE3wDEHeGYXyKRlKNUEwbUnOpx3v2S+hlgu\r\n" + 
			"TwIijMl1PcPT8MM98prQaKpOW8aogYQ+aMhghV0pAoGABlI48BKQdFKHdFO5RQZv\r\n" + 
			"QlDXVY+8gJP89WJV4Ae8V3L1eZEuau4pmp5xMQrg/QkAhV1gcEchQOXCZ+7LdKO8\r\n" + 
			"2bMjYwsWDvUAjWMdTrUi013WTcj4+ZNFoxs0/mz5gXCZn2BZkk91p+kYlFvUV6xN\r\n" + 
			"lNtFJz5ZSrcjCze5bWlPm40CgYEAnLz5+Ev2yYQrA3yyK2Fy+VvaNz1znSCbCLjX\r\n" + 
			"os9Hzk1+sKKZnz57xmQabPlgib8PxdeOM1FbFUF6149xURkAE4O+iCvBuMwCSh8M\r\n" + 
			"mIn91aq0mAZNKZuSOthFoU3wmpMhLeMI7Jrb9UAKSPtHdMYPAkQR+ay1dBWR19eJ\r\n" + 
			"tUR8QrkCgYAVjFviQ0zZgSYPrSRYVe2mDChaQit49icI5SipnTXoSCiQ3eT9dknI\r\n" + 
			"U1OTt3qT0Cv9WC2hllsbnqMJ5J7QnIiNwueErBSzYzlRpCNZH0K1SXkYsXJRRHW2\r\n" + 
			"IxozX5op+Q2blVH1z81VPKSL4T+XqGBFTEZEQrQBN31ovKmiKEpbyA==\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxV0RCtp+jT1QkVMqml/N\r\n" + 
			"xoWfc+qMNoYp/AH/g5VsTJK7veQ62Xbm3G3p28eMvbklfbxCfk0kh81vmi7AjlmL\r\n" + 
			"lVnslFFuS5/iDM7oYFBA0Ki3xYF5EinyEwd5L49B5Jtr9aBPNcrCxfTpUikrwV3B\r\n" + 
			"Tes4ys+ojnK3YhT4MTrnuTqW1txte/P8CK7/2U+shkEhVg8jUVLXsrs7RMo3Kh0z\r\n" + 
			"4P67l6w7tEywLAsoR4FyEViTPMAmXOsQIP4N01cekjt5YN/MhRtE7T/d96OvZ1Q7\r\n" + 
			"0VkxZEt/7pQkm6AI1KOArEq8rno3R0kcRxmGzgiD2qhTW4MbHtyZdaCS/TGo5cj3\r\n" + 
			"bQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
