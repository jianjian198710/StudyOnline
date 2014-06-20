package importnew;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/*
 * ʹ��Java8��ConcurrentHashMap���lambda���ʽʵ��һ�����ػ���
 */
public class Java8Cache{

	static ConcurrentHashMap<Integer,Integer> cache = new ConcurrentHashMap<>();
	static ConcurrentHashMap<Integer,Integer> cache2 = new ConcurrentHashMap<>();
	@Test
	public void test(){
		for(int i=0;i<10;i++){
			System.out.println("i="+i+", fibonacci("+i+")="+fibonacci(i));
		}
		
		for(int i=0;i<10;i++){
			System.out.println("i="+i+", fibonacciJava7("+i+")="+fibonacci(i));
		}
	}
	
	public int fibonacci(int i){
		if(i==0){
			return 0;
		}
		if(i==1){
			return 1;
		}
		//���û�и�key�ͼ���,�о�ֱ�ӷ���
		return cache.computeIfAbsent(i, (key)->{System.out.println("Calculate key: "+key);
											  return fibonacci(key-1)+fibonacci(key-2);
											  });
	}
	
	public int fibonacciJava7(int i){
		if(i==0){
			return 0;
		}
		if(i==1){
			return 1;
		}
		
		Integer result = cache2.get(i);
		if(result==null){
			cache2.putIfAbsent(i, fibonacciJava7(i-2)+fibonacciJava7(i-1));
		}
		return result;
	}
}
