package importnew;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * ��һЩJVM�ķ�װ�໺�������,����Integer,�ڷ�Χ-128 ��127֮�����ͨ��"=="���бȽϵõ��ķ���ֵ��true�Ļ�,��ô���Ƿ��صľ�����ͬ�Ķ���,
 * �����������Χ֮��Ͳ�����.�������������Ϊ����������JVM.��˼��ʱ�������equals()����������ʹ��"==".
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
	 * ��Ϊint��ֵ��-127��127�����Χ��JVM�Ὣֵ��������,
	 * ���i1��i2,�����ȷȷʵʵʹ������ͬ�Ķ���ʵ��(�ڴ��ַ��ͬ),����"=="�᷵��true�Ľ��
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
