# 				< JAVA 2021.04.26 ~ 10.15 >



# Network

	Socket Programming

	MyEchoClient/MyEchoServer 참고 할 것 

### BufferedWriter 대신 PrintWriter를 쓰는 이유 (메시지를 보낼 때, Server <-> Client)
https://dream-space.tistory.com/4


PrintWriter의 메서드들이BufferedWriter를 포함하고 있으며, 
생성자 역시 PrintWriter가 훨씬 다양하기 때문에, 
굳이 데코레이터 패턴을 사용하지 않더라도, File클래스 객체나,
OutputStream객체 등을 바로 인수로 입력받을 수 있는 장점이 있어, 
PrintWriter가 더 자주 쓰인다.

BufferedWriter는, 생성자의 인수로 Writer만을 갖는 반면,
PrintWriter는 File(String), OutputStream, Writer 등의 객체를 
인수로 받아 더 간편하게 스트림을 연결할 수 있다.
자주 사용되는 메서드로는,

1. print : boolean, int, char, float, double 등등의 데이터형을 String으로 변환해서 쓰지 않고 직접 입력한다. (메모장에 int형 변수값을 변수를 '숫자'의 String으로 변환하여 넣는것과는 다르다.)

2. println : print할 데이터 뒤에 /r/n을 추가하여, 데이터 와 함께 개행을 출력한다.

3. write : write메서드 역시 존재하지만 PrintWriter을 사용할 경우는 잘 쓰이지 않는다.

그 외에도, 다른 Writer 클래스와 같이, flush, close 등의 메서드역시 존재한다.



# InputStreamReader, OutputStreamWriter



byte -> char -> String 총 3가지 단계로 스트림을 이용하여 source들을 받고 출력할 수 있다.


1. byte -> char
	InputStreamReader는 바이트 단위로 읽어 들이는 InputStream을 통해 데이터를 읽어 들여 문자 단위로 읽	어 들이는 방식으로 변형한다는 의미이며,


2. char -> byte
	OutputStreamWriter는 바이트 단위로 쓰는 OutputStream을 이용해서 문자 단위로 쓰는 것을 바이트 단위
	로 쓰도록 변형한다는 것을 의미합니다.
	
	읽어야 할 대상 -> InputStream -> InputStreamReader -> 메소드1, 메소드2, 메소드3 ...
	
	써야 할 대상 <- OutputStream <- OutputStreamWriter <- 메소드1, 메소드2, 메소드3 ...
	
## BufferedReader

InputStreamReader로 받은 char 형을 String 단위로 읽어들이는 방식이다.


