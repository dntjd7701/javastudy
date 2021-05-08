# 				< JAVA 2021.04.26 ~ 10.15 >



# Network

	Socket Programming

	MyEchoClient/MyEchoServer 참고 할 것 
	
	
###Socket이란?

소켓은 떨어져 있는 두 호스트를 연결해주는 도구로써 인터페이스 역할을 하는데, 데이터를 주고 받을 수 있는 구조체로 소켓을 통해 데이터 통로가 만들어 진다. 이러한 소켓은 역할에 따라 서버 소켓, 클라이언트 소켓으로 구분된다.


	o Server

클라이언트 소켓의 연결 요청을 대기하고, 연결 요청이 오면 클라이언트 소켓을 생성하여 통신이 가능하게 한다.

1)socket() 함수를 이용하여 소켓을 생성
2)bind() 함수로 ip와 port 번호를 설정
3)listen() 함수로 클라이언트의 접근 요청에 수신 대기열을 만들어서 몇 개의 클라이	언트를 대기 시킬지 결정 
4)accpet() 함수를 사용하여 클라이언트와의 연결을 대기 


	o Client

실제로 데이터 송수신이 일어나는 것은 클라이언트 소켓이다.

1)socket() 함수로 가장 먼저 소켓을 연다.
2)connect() 함수를 이용하여 통신할 서버의 설정된 ip와 port 번호에 통신을 시도한다.
3)통신을 시도 시, 서버가 accept() 함수를 이용하여 클라이언트의 socket descriptor를 반환한다.
4)이를 통해 클라이언트와 서버가 서로 read(), write()를 하며 통신한다(반복)


###TCP, UDP socket class 
	
TCP -> ServerSocket, Socket
UDP -> DatagramSocket



###BufferedWriter 대신 PrintWriter를 쓰는 이유 (메시지를 보낼 때, Server <-> Client)

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




###InputStreamReader, OutputStreamWriter



byte -> char -> String 총 3가지 단계로 스트림을 이용하여 source들을 받고 출력할 수 있다.


1. byte -> char
	InputStreamReader는 바이트 단위로 읽어 들이는 InputStream을 통해 데이터를 읽어 들여 문자 단위로 읽	어 들이는 방식으로 변형한다는 의미이며,


2. char -> byte
	OutputStreamWriter는 바이트 단위로 쓰는 OutputStream을 이용해서 문자 단위로 쓰는 것을 바이트 단위
	로 쓰도록 변형한다는 것을 의미합니다.
	
	읽어야 할 대상 -> InputStream -> InputStreamReader -> 메소드1, 메소드2, 메소드3 ...
	
	써야 할 대상 <- OutputStream <- OutputStreamWriter <- 메소드1, 메소드2, 메소드3 ...
	
	 BufferedReader

InputStreamReader로 받은 char 형을 String 단위로 읽어들이는 방식이다.

### InetAddress class
	
IP 주소를 표현한 클래스, 자바에서는 모든 IP 주소를 InetAddress 클래스를 사용한다.

	InetAddress 클래스의 생성자
InetAddress 클래스의 생성자는 하나만 존재하지만, 특이하게 기본 생성자의 접근 제한자 default이기 때문에 new 연산자 객체를 생성할 수 없습니다. 따라서 InetAddress 클래스는 객체를 생성해 줄 수 있는 5개의 static 메서드를 제공하고 있습니다.

	

# 05.08

	개인 공부 TCP
	
	
### Writer Class

java.io.Writer class is an abstract class. It is used to write to character streams.

Writer class는 추상 클래스로 이번에 진행한 chat 연습에서의 
List<Writer> ??? = new ArrayList<Writer>() 
에서는 각 소켓의 pw들(각 클라이언트의 소켓을 가리키며 메세지를 보낼 때 사용하는 PrintWriter, Stream의 형태로 적어서 보낼 수 있다.-> 보조 스트림으로 String->char->byte의 형태로 보조 스트림을 거쳐 순차적으로 쪼개지고 전달된다.)
이를 통해 형을 Writer로 지정해 데이터를 넣는다.


## 동기화

동기화, 즉, 멀티쓰레드 환경에서 공유하고 있는 공유 변수에 동시에 접근하여 값을 변경하지 않도록 하기 위한 방법이다.
화장실에서 동기화가 이루어져있지 않다면 화장실을 사용하고 있는 중에 다른 사용자가 들어와 내 무릎위에 앉는 것을 에로 들 수 있다.
즉, 중간에서 오는 개입을 막고 본인의 일을 마무리 할 때까지 데이터를 보존하고 그 변경된 데이터를 동기화 하는 것이다.
	https://reakwon.tistory.com/85 
	Synchronize의 좋은 예시와 동기화에 대한 글 
	
	
### Socket

서버-클라이언트 간에 통신을 한다고 가정하자.
이 관계에서 통신의 접점 역할을 하는 소켓은 하나 뿐이다
힙 영역에 소켓이 생성되면 각각 서버와 클라이언트가 하나인 소켓을 가리키고 있는 것이다.
이 소켓을 읽고 소켓에 쓰며 서버와 클라이언트는 서로의 데이터를 주고 받는 것이다.

만약에 이러한 관계가 서버와 클라이언트의 1대 n 관계로 넘어간다고 가정하자.
이러한 경우에 통신을 위해선 서버는 연결된 각 클라이언트들의 소켓을 여러개 참조하고 있어야 한다.
한 클라이언트와 통신을 위해선 그 고유한 소켓 하나만 이용되므로 
이번에 chat 실습에서 진행된 과정에서는

각 클라이언트들에게 메세지를 모두 보내주기 위해 Synchronize을 통해 모두의 데이터를 동기화 시키고
PrintWriter(각 소켓에 보낼 socket.getOutputStream 데이터를 가지고 있는)을 
리스트에 저장하고 broadcast 메소드의  for each 문을 통해 모두에게 데이터를 보내준다.

"mychat" 에서의 pw는 소켓의 정보를 담고 있다 라고 일단 생각하면 좋다.


	
	

