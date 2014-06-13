package importnew;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * 在一些JVM的封装类缓存对象中,例如Integer,在范围-128 到127之间如果通过"=="进行比较得到的返回值是true的话,那么他们返回的就是相同的对象,
 * 但是在这个范围之外就不行了.更糟的是这种行为更多依赖于JVM.因此检查时最好是用equals()方法而避免使用"==".
 */
public class AutoBoxing{

	@Test
	public void test(){
		Integer i1 = 260;
		Integer i2 = 260;
		if(i1==i2){
			System.out.println("i1 and i2 are equals");
		}else{
			System.out.println("is and i2 are not equals");
		}
	}
	
	/*
	 * 因为int的值在-127到127这个范围内JVM会将值缓存起来,
	 * 因此i1和i2,虚拟机确确实实使用了相同的对象实例(内存地址相同),所以"=="会返回true的结果
	 */
	@Test
	public void test2(){
		Integer i1 = 100;
		Integer i2 = 100;
		if(i1==i2){
			System.out.println("i1 and i2 are equals");
		}else{
			System.out.println("is and i2 are not equals");
		}
	}

}
