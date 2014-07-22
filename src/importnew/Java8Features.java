package importnew;

import importnew.RepeatingAnnotations.Filter;
import importnew.RepeatingAnnotations.Filterable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.Test;

/*
 * Java 8新特性终极指南 
 */
public class Java8Features{

	private interface Defaulable {
	    default String notRequired() { 
	        return "Default implementation"; 
	    }        
	}
	         
	private static class DefaultableImpl implements Defaulable {}
	     
	private static class OverridableImpl implements Defaulable {
	    @Override
	    public String notRequired() {
	        return "Overridden implementation";
	    }
	}

	private interface DefaulableFactory {
	    // Interfaces now allow static methods
	    static Defaulable create( Supplier< Defaulable > supplier ) {
	        return supplier.get();
	    }
	}
	/*
	 * 2.2接口的默认方法与静态方法
	 * 默认方法与抽象方法不同之处在于抽象方法必须要求实现,但是默认方法则没有这个要求.
	 */
	@Test
	public void test(){
		 Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
		 System.out.println( defaulable.notRequired() );
		 defaulable = DefaulableFactory.create( OverridableImpl::new );
		 System.out.println( defaulable.notRequired() );
	}
	
	private static class Car {
		private String brand;
		public Car(String brand){
			this.brand = brand;
		}
		
		public static void collide( final Car car ) {
			System.out.println( "Collided " + car.toString() );
		}
		
		public void follow( final Car another ) {
			System.out.println( "Following the " + another.toString() );
		}
		
		public void repair() {
			System.out.println( "Repaired " + this.toString() );
		}
		
		public String toString(){
			return brand;
		}
	}
	
	private interface CarFactory {
	    Car create(String brand);
	}
	
	/*
	 * 2.3 方法引用
	 */
	@Test
	public void test1(){
		//第一种方法引用是构造器引用,它的语法是Class::new 通过构造函数引用 将create与Car的构造函数绑定
		CarFactory carFactory = Car::new;
		Car car = carFactory.create("Bens");
		System.out.println(car);
		
		List<Car> cars = new ArrayList<Car>();
		cars.add(car);
		
		//静态方法引用
		cars.forEach(Car::collide);
		//特定类的任意对象的方法引用,它的语法是Class::method.没有参数
		cars.forEach(Car::repair);
		//第四种方法引用是特定对象的方法引用,它的语法是instance::method.这个方法接受一个Car类型的参数 这个car代表的是参数
		cars.forEach(car::follow);
	}
	
	/*
	 * 2.4重复注释
	 */
	@Test
	public void test2(){
		for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class)){
			System.out.println(filter.value());
		}
	}
	
	/*
	 * 3.1参数名字
	 */
	@Test
	public void test3() throws NoSuchMethodException, SecurityException{
		Method method = Java8Features.class.getMethod( "say",String.class);
		for(final Class<?> clazz:method.getParameterTypes()){
			System.out.println("Parameter Type: "+clazz.getName());
		}
		for(final Parameter parameter:method.getParameters()){
			System.out.println("Parameter Value: "+parameter.getName());
		}
	}
	
	public String say(String s){
		return "say"+s;
	}
	
	/*
	 * 4.1 Optional
	 */
	@Test
	public void test4(){
		Optional<String> fullname = Optional.ofNullable(null);
		//如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false
		System.out.println("Full name is set? "+fullname.isPresent());
		//为了防止Optional为空值，orElseGet()方法通过回调函数来产生一个默认值
		System.out.println("Full name: "+fullname.orElseGet(()->"[None]"));
		//orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数
		System.out.println(fullname.map(s -> "Hello "+s+"!").orElse("Hello Stranger!"));
	}
	
	/*
	 * 4.2 Stream
	 */
	private enum Sex{
		Male,Female
	}
	
	private static final class Cat{
		private Sex sex;
		private Integer age;
		public Cat(final Sex sex,final Integer age){
			this.sex = sex;
			this.age = age;
		}
		public Sex getSex(){
			return sex;
		}
		
		public Integer getAge(){
			return age;
		}
	}
	
	@Test
	public void test5(){
		List<Cat> cats = new ArrayList<Cat>();
		cats.add(new Cat(Sex.Male,25));
		cats.add(new Cat(Sex.Female,26));
		cats.add(new Cat(Sex.Male,24));
		System.out.println(cats.stream().filter(cat -> cat.getSex() == Sex.Male).mapToInt(Cat::getAge).sum());
	}
}

class RepeatingAnnotations{
	@Target( ElementType.TYPE )
	@Retention( RetentionPolicy.RUNTIME )
	public @interface Filters {
		Filter[] value();
	}
	
	@Target( ElementType.TYPE )
	@Retention( RetentionPolicy.RUNTIME )
	@Repeatable( Filters.class )
	public @interface Filter {
		String value();
	};
	
	@Filter( "filter1" )
	@Filter( "filter2" )
	public interface Filterable {} 
}